package seg.jUCMNav.model.commands.changeImpactAnalysis;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;

public class ForSliceColorList {

	public UCMNavMultiPageEditor editor;
	private NodeConnection NodeConn_criterion=null;
	public  ArrayList<PathNode> green;
	public  ArrayList<PathNode> red;
	public  ArrayList<PathNode> gray;
	public  ArrayList<NodeConnection> blackLinks;
	private boolean isorAndTimerCriterion = false;
	
	public ForSliceColorList(UCMNavMultiPageEditor editor) {	
	
         this.editor=editor;	
		green=new ArrayList<PathNode>();
		red=new ArrayList<PathNode>();
		gray=new ArrayList<PathNode>();
		blackLinks=new ArrayList<NodeConnection>();
	}
	public NodeConnection getNodeConn_criterion() {
		return NodeConn_criterion;
	}
	public void setNodeConn_criterion(NodeConnection nodeConn_criterion) {
		NodeConn_criterion = nodeConn_criterion;
	}
	public boolean isIsorAndTimerCriterion() {
		return isorAndTimerCriterion;
	}
	public void setIsorAndTimerCriterion(boolean isorAndTimerCriterion) {
		this.isorAndTimerCriterion = isorAndTimerCriterion;
	}
	

	

}
