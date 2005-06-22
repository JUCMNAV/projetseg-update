package seg.jUCMNav.figures.router;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.AbstractRouter;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import seg.jUCMNav.editparts.ConditionEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ConnectionSplineFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ConnectionSplineFinder.QFindSpline;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.Stub;
import ucm.map.Timer;

/**
 * Created on 20-Jun-2005
 * 
 * Connection router for a use case map. Builds bsplines and refreshes them only when necessary.
 * 
 * Uses the ConnectionSplineFinder to find affected connections.
 * 
 * @author jkealey
 *  
 */
public class UCMConnectionRouter extends AbstractRouter implements Adapter {
    private HashMap connections;
    private Map editpartregistry;
    private PathGraph pathgraph;
    private Notifier target;

    /**
     *  
     */
    public UCMConnectionRouter(Map editpartregistry, PathGraph pathgraph) {
        assert pathgraph != null : "null path graph!";

        this.pathgraph = pathgraph;
        this.editpartregistry = editpartregistry;

        registerListeners(pathgraph);
        refreshConnections();
    }

    /**
     * @param nc
     * @param bspline
     */
    private void drawConnection(NodeConnection nc, BSpline bspline) {
        if (editpartregistry.get(nc) == null)
            return;
        SplineConnection conn = (SplineConnection) ((NodeConnectionEditPart) editpartregistry.get(nc)).getFigure();
        if (conn != null) {
            PointList pts = bspline.getPointsBetween(getLeftPoint(nc), getRightPoint(nc));
            conn.setPoints(pts);
            connections.put(nc, Boolean.TRUE);

            //refresh conditions.
            if (nc.getCondition() != null) {
                ConditionEditPart edit = (ConditionEditPart) editpartregistry.get(nc.getCondition());
                if (edit != null) {
                    edit.refreshVisuals();
                }

            }

            // refresh outgoing andjoin/timer only.
            if (nc.getSource() instanceof AndJoin || nc.getSource() instanceof Timer || nc.getSource() instanceof Stub) {
                PathNodeEditPart edit = (PathNodeEditPart) editpartregistry.get(nc.getSource());
                if (edit != null) {
                    edit.refreshVisuals();
                }

            }

            // rest are refreshed ingoing.
            PathNodeEditPart edit = (PathNodeEditPart) editpartregistry.get(nc.getTarget());
            if (edit != null) {
                edit.refreshVisuals();
            }
        }

    }

    /**
     * @param source
     */
    private void drawSpline(SplineConnection source) {
        // refresh spline
        QFindSpline qReachableConnections = new ConnectionSplineFinder().new QFindSpline(source.getLink());
        ConnectionSplineFinder.RSpline rReachableConnections = (ConnectionSplineFinder.RSpline) GraphExplorer.getInstance().run(qReachableConnections);
        Vector vReachable = rReachableConnections.getConnections();

        if (vReachable.size() > 0) {
            PointList pts = new PointList();
            NodeConnection nc;
            // build point sequence
            for (Iterator iter = vReachable.iterator(); iter.hasNext();) {
                nc = (NodeConnection) iter.next();
                Point left = getLeftPoint(nc);
                Point right = getRightPoint(nc);
                // prevent double adding
                if (pts.size() == 0 || !pts.getLastPoint().equals(left))
                    pts.addPoint(left);
                pts.addPoint(right);
            }

            // build a spline from the sequence
            BSpline bspline = new BSpline(pts);
            for (Iterator iter = vReachable.iterator(); iter.hasNext();) {
                nc = (NodeConnection) iter.next();
                drawConnection(nc, bspline);
            }
        }
    }

    /**
     * @return A hashmap with NodeConnection/Boolean couples. if the boolean is Boolean.TRUE, refresh is not needed.
     */
    public HashMap getConnections() {
        return connections;
    }

    /**
     * @return the editpart registry. Used for refresh for figures that must be rotated.
     */
    public Map getEditpartregistry() {
        return editpartregistry;
    }

    /**
     * To be modified for better handling of and forks/joins
     * 
     * @param nc
     * @return
     */
    private Point getLeftPoint(NodeConnection nc) {
        return new Point(nc.getSource().getX(), nc.getSource().getY());
    }

    public PathGraph getPathgraph() {
        return pathgraph;
    }

    /**
     * To be modified for better handling of and forks/joins
     * 
     * @param nc
     * @return
     */
    private Point getRightPoint(NodeConnection nc) {
        return new Point(nc.getTarget().getX(), nc.getTarget().getY());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(PathGraph.class) || type.equals(PathNode.class) || type.equals(NodeConnection.class);
    }

    /**
     * If the PathGraph, PathNodes or NodeConnections that the router listens to is changed, the impact of the change is calculated and the connections HashMap
     * is updated.
     */
    public void notifyChanged(Notification notification) {
        int type = notification.getEventType();
        EObject notifier = (EObject) notification.getNotifier();

        if (notification.getFeature() instanceof EStructuralFeature) {
            EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
            if (type == Notification.SET && notifier instanceof PathNode) {
                PathNode pn = (PathNode) notifier;
                if (feature.getName().equals("x") || feature.getName().equals("y")) {
                    if (pn.getPathGraph() != null) {
                        //System.out.println("moved pathnode");
                        refreshConnections(pn);
                    }
                }
            } else if (type == Notification.SET && notifier instanceof NodeConnection) {
                NodeConnection nc = (NodeConnection) notifier;
                if (feature.getName().equals("source") || feature.getName().equals("target")) {
                    if (nc.getPathGraph() != null && nc.getSource() != null && nc.getTarget() != null) {
                        //                        System.out.println("changed connections");
                        refreshConnections(nc);
                    }
                }
            } else if (notifier instanceof PathGraph) {
                PathGraph pg = (PathGraph) notifier;

                switch (type) {
                case Notification.ADD:
                    registerListeners(getPathgraph());
                case Notification.REMOVE:
                    /*
                     * if (feature.getName().equals("pathNodes")) { System.out.println("added or removed pathnodes"); } else if
                     * (feature.getName().equals("nodeConnections")) { System.out.println("added or removed nodeconnections"); }
                     */
                    refreshConnections();

                    break;
                }

            }
        }

    }

    /**
     *  
     */
    private void refreshConnections() {
        connections = new HashMap(getPathgraph().getNodeConnections().size());
        for (Iterator iter = getPathgraph().getNodeConnections().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();

            // never want to refresh Connects
            if (nc.getSource() instanceof Connect || nc.getTarget() instanceof Connect)
                connections.put(nc, Boolean.TRUE);
            else {
                connections.put(nc, Boolean.FALSE);
            }
        }
    }

    /**
     * @param qSpline
     */
    private void refreshConnections(ConnectionSplineFinder.QFindSpline qSpline) {
        ConnectionSplineFinder.RSpline rReachableConnections = (ConnectionSplineFinder.RSpline) GraphExplorer.getInstance().run(qSpline);
        Vector vReachable = rReachableConnections.getConnections();

        for (Iterator iter = vReachable.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            // never want to redraw connects
            if (nc.getSource() instanceof Connect || nc.getTarget() instanceof Connect)
                connections.put(nc, Boolean.TRUE);
            else {
                connections.put(nc, Boolean.FALSE);
            }
        }
    }

    /**
     * @param nc
     */
    private void refreshConnections(NodeConnection nc) {
        QFindSpline qReachableConnections = new ConnectionSplineFinder().new QFindSpline(nc);
        refreshConnections(qReachableConnections);
    }

    /**
     * @param pn
     */
    private void refreshConnections(PathNode pn) {
        if (pn instanceof Connect)
            return;
        QFindSpline qReachableConnections;
        for (Iterator iter = pn.getPred().iterator(); iter.hasNext();) {
            qReachableConnections = new ConnectionSplineFinder().new QFindSpline((NodeConnection) iter.next());
            refreshConnections(qReachableConnections);
        }
        for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
            qReachableConnections = new ConnectionSplineFinder().new QFindSpline((NodeConnection) iter.next());
            refreshConnections(qReachableConnections);
        }

    }

    /**
     * @param pathgraph
     */
    private void registerListeners(PathGraph pathgraph) {
        if (!pathgraph.eAdapters().contains(this))
            pathgraph.eAdapters().add(this);
        for (Iterator iter = pathgraph.getNodeConnections().iterator(); iter.hasNext();) {
            NodeConnection element = (NodeConnection) iter.next();
            if (!element.eAdapters().contains(this) && !(element.getSource() instanceof Connect) && !(element.getTarget() instanceof Connect))
                element.eAdapters().add(this);
        }
        for (Iterator iter = pathgraph.getPathNodes().iterator(); iter.hasNext();) {
            EObject element = (EObject) iter.next();
            if (!element.eAdapters().contains(this) && !(element instanceof Connect))
                element.eAdapters().add(this);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.draw2d.ConnectionRouter#route(org.eclipse.draw2d.Connection)
     */
    public void route(Connection connection) {
        SplineConnection spline = (SplineConnection) connection;
        if (connections.get(spline.getLink()).equals(Boolean.FALSE)) {
            drawSpline(spline);
        }
    }

    public void setConnections(HashMap connections) {
        this.connections = connections;
    }

    public void setEditpartregistry(Map editpartregistry) {
        this.editpartregistry = editpartregistry;
    }

    public void setPathgraph(PathGraph pathgraph) {
        this.pathgraph = pathgraph;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }
}