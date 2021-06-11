package seg.jUCMNav.actions;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.Slicing.DataControlDep;
import seg.jUCMNav.model.commands.changeImpactAnalysis.ForDataControlDep;
import seg.jUCMNav.model.commands.changeImpactAnalysis.UCMChangeImpactAnalysisCommand;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.IURNNode;

public class UCMChangeImpactAnalysisDeletionAction extends URNSelectionAction{
	public static final String UCMChangeImpactAnalysisDeletion= "seg.jUCMNav.UCMChangeImpactAnalysisDeletion"; //$NON-NLS-1$
	public static EditPartViewer Viewr;
	public static boolean dialogFinished;
	RespRef R;URNspec urn;
	EObject criterion;
	
	public UCMChangeImpactAnalysisCommand UCMCIAmcd;
	 /**
     * @param part
     */
	 public UCMChangeImpactAnalysisDeletionAction(IWorkbenchPart part) {
	        super(part);
	             
	        setId(UCMChangeImpactAnalysisDeletion);
	        dialogFinished=false;
	        //setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/delete16.gif")); //$NON-NLS-1$
	    }
	 
	 /**
     * True if we select one URN element.
     */
	 protected boolean calculateEnabled() {
 	 	EditPart t;
 	 	SelectionHelper sel = new SelectionHelper(getSelectedObjects());
 	 	if(getSelectedObjects().size() == 1 && (sel.getSelectionType() == SelectionHelper.RESPONSIBILITYREF || sel.getSelectionType() == SelectionHelper.NODECONNECTION || sel.getSelectionType() == SelectionHelper.STARTPOINT || sel.getSelectionType() == SelectionHelper.ORFORK
 	 	|| sel.getSelectionType() == SelectionHelper.ORJOIN || sel.getSelectionType() == SelectionHelper.ANDFORK || sel.getSelectionType() == SelectionHelper.ANDJOIN || sel.getSelectionType() == SelectionHelper.TIMER)) 
 	 	{
        	t = ((EditPart) getSelectedObjects().get(0));		     
        	Viewr = t.getViewer();
        	EObject obj= (EObject) t.getModel();
	     
	 		if(obj instanceof RespRef){
	 			R = (RespRef) obj ;
	 			criterion = R;
	 			return true;
	 		}else if(obj instanceof StartPoint){
	 			criterion = obj;
	 			return true;
	 		}else if(obj instanceof NodeConnection){
	 			PathNode pred=(PathNode) ((NodeConnection) obj).getSource();
	 			if(pred instanceof OrFork || pred instanceof Timer || pred instanceof WaitingPlace)
	 			{
	 				criterion=obj;
	 				return true;
	 			}
	 		}else if(obj instanceof OrFork ||obj instanceof AndFork || obj instanceof Timer){
	 			NodeConnection pred = (NodeConnection)((IURNNode)obj).getPred().get(0);
	 			
	 			criterion = pred;
	 			return true; 
	 			
	 		}else if(obj instanceof OrJoin || obj instanceof AndJoin){
	 			NodeConnection succ = (NodeConnection)((IURNNode)obj).getSucc().get(0);
	 			
	 			criterion = succ;
	 			return true;
	 		}
	   }
	  return false;
    } //calculateEnabled
	    
    /**
     * @return a {@link UCMChangeImpactAnalysisCommand}
     */
	protected Command getCommand() {
		Boolean isEmptyExpression = false;
		if(calculateEnabled()){   
    		ArrayList<String> leftexpressionVariables=new ArrayList<String>();
	    	//check if it's not empty
	    	if(criterion instanceof RespRef){
	    		if(R.getRespDef().getExpression()!= null && !R.getRespDef().getExpression().isEmpty()){
	        		leftexpressionVariables = new ArrayList<String>();	
	    	    	ForDataControlDep obj = new ForDataControlDep(null);
	    	    	obj.setExpression(R.getRespDef().getExpression());
	    	    	leftexpressionVariables=obj.getLeftVar();
	        	}//otherwise it's empty
	        	else
	        		isEmptyExpression=true;	    	    	
	    	}//if criterion is StartPoint
			else if(criterion instanceof StartPoint){
			  	StartPoint startPoint = (StartPoint) criterion;
			  	leftexpressionVariables = new ArrayList<String>();	
    	    	DataControlDep obj = new DataControlDep(null);
    	    	String condition = startPoint.getPrecondition().getExpression();
    	    	leftexpressionVariables = obj.getConditionVariables(condition);
		    		}
	    		//otherwise it's node connection criterion
	    		else if(criterion instanceof NodeConnection)
	    		{
	    			NodeConnection nodeConn= (NodeConnection) criterion;
	    			
	    			if(nodeConn.getTarget() instanceof OrFork || nodeConn.getTarget() instanceof AndFork || nodeConn.getTarget() instanceof Timer){
		    			isEmptyExpression = true;
			    	}else if(nodeConn.getSource() instanceof OrJoin || nodeConn.getSource() instanceof AndJoin){
			    		isEmptyExpression = true;
		    	   	}else{
		    	   		String condition=nodeConn.getCondition().getExpression();
		    			leftexpressionVariables=new ArrayList<String>();	
		    	    	DataControlDep obj=new DataControlDep(null);
		    	    	if(condition!=null)
		    	    		leftexpressionVariables=obj.getConditionVariables(condition);
		    	   	}
	    		}
	    	
	    		if(!leftexpressionVariables.isEmpty() && !isEmptyExpression)
	    			UCMCIAmcd = new UCMChangeImpactAnalysisCommand(criterion,leftexpressionVariables,2);
	    		else
	    			UCMCIAmcd = new UCMChangeImpactAnalysisCommand(criterion,null,2);	
		  
	    		UCMChangeImpactAnalysisCommand.prevCommand = UCMCIAmcd;
	    		
	    		if(criterion instanceof NodeConnection){
		    		NodeConnection ncTemp = (NodeConnection)criterion;
		    		if(ncTemp.getTarget() instanceof OrFork || ncTemp.getTarget() instanceof AndFork || ncTemp.getTarget() instanceof Timer){
		    			UCMCIAmcd.isorAndTimerCriterion = true;
		    		}
		    	}
	    		
	    		return UCMCIAmcd; 
	  }//calculateEnables
	  else 
		  return null;
	  }
}
