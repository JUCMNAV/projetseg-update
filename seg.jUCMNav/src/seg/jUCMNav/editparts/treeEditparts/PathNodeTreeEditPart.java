/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.PathNodeComponentEditPolicy;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * @author TremblaE
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class PathNodeTreeEditPart extends UcmModelElementTreeEditPart {

    private PathGraph grah;

    /**
     * @param model
     */
    public PathNodeTreeEditPart(Object model, PathGraph graph) {
        super(model);
        this.grah = graph;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new PathNodeComponentEditPolicy());
        if (getWidget() instanceof TreeItem) {
            if (getModel() instanceof EmptyPoint)
                ((TreeItem) getWidget()).setForeground(new Color(null, 200, 200, 200));
        }        
    }

    //	protected List getModelChildren() {
    //		ArrayList list = new ArrayList();
    //		if(getPathNode().getLabel() != null)
    //			list.add(getPathNode().getLabel());
    //		return list;
    //	}

    protected PathNode getPathNode() {
        return (PathNode) getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getImage()
     */
    protected Image getImage() {

        PathNode node = (PathNode) getModel();
        
        if (super.getImage() == null) {
            if (node instanceof StartPoint)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif")).createImage());
            else if (node instanceof EmptyPoint)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif")).createImage());
            else if (node instanceof EndPoint)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif")).createImage());
            else if (node instanceof RespRef)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage());
            else if (node instanceof Stub)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif")).createImage());
            else
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif")).createImage());
        }

        return super.getImage();
    }
}