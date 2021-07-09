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
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;

public class UCMChangeImpactAnalysisAction extends URNSelectionAction{
	public static final String UCMChangeImpactAnalysis = "seg.jUCMNav.UCMChangeImpactAnalysis"; //$NON-NLS-1$
	public static EditPartViewer Viewr;
	public static boolean dialogFinished;
	RespRef R;URNspec urn;
	EObject criterion;
	
	public UCMChangeImpactAnalysisCommand UCMCIAmcd;
	 /**
     * @param part
     */
	 public UCMChangeImpactAnalysisAction(IWorkbenchPart part) {
	        super(part);
	             
	        setId(UCMChangeImpactAnalysis);
	        dialogFinished=false;
	        //setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/delete16.gif")); //$NON-NLS-1$
	    }
	 
	 /**
     * True if we select one responsibility .
     */
	    protected boolean calculateEnabled() {
	 	   EditPart t;
		   SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		   if ( getSelectedObjects().size()==1 && (sel.getSelectionType()==SelectionHelper.RESPONSIBILITYREF || sel.getSelectionType()==SelectionHelper.NODECONNECTION || sel.getSelectionType()==SelectionHelper.STARTPOINT) ) 
		   {
			   t=  ((EditPart) getSelectedObjects().get(0));
			   Viewr= t.getViewer();
			   EObject obj= (EObject) t.getModel();

		 		if(obj instanceof RespRef){
		 			R= (RespRef) obj ;
		 			criterion=R;		 	
		 			return true;
		 		}else if(obj instanceof StartPoint){
	 			criterion=obj;
	 			return true;
		 		}else if(obj instanceof NodeConnection ){
		 			PathNode pred=(PathNode) ((NodeConnection) obj).getSource();
		 			if(pred instanceof OrFork  || pred instanceof Timer || pred instanceof WaitingPlace){
		 				criterion=obj;
		 				return true;
		 			}
	 		}
		   }
		  return false;
	    }
	    
	    /**
	     * @return a {@link UCMChangeImpactAnalysisCommand}
	     */
	  protected Command getCommand() {
		  //*********************
	        
	    	//Boolean isEmptyExpression=false;
	    	if(calculateEnabled())
	        
	    	{   
	    		ArrayList<String> leftexpressionVariables=new ArrayList<String>();
	    		//check if it's not empty
	    		if(criterion instanceof RespRef)
	    		{
	    			if(R.getRespDef().getExpression()!=null && !R.getRespDef().getExpression().isEmpty())
	        		{
	        			leftexpressionVariables=new ArrayList<String>();	
	        			ForDataControlDep obj=new ForDataControlDep(null);
	        			obj.setExpression(R.getRespDef().getExpression());
	        			leftexpressionVariables=obj.getLeftVar();
	        		}
	        		//otherwise it's empty
	        		//else
	        			//isEmptyExpression=true;
	    	    	
	    		}
	    		
	    		//if criterion is startPoint
	    		else if(criterion instanceof StartPoint)
	    		{
	    			StartPoint endpoint= (StartPoint) criterion;
	    			leftexpressionVariables=new ArrayList<String>();	
	    	    	DataControlDep obj=new DataControlDep(null);
	    	    	String condition=endpoint.getPrecondition().getExpression();
	    	    	if(condition!=null)
	    	    		leftexpressionVariables=obj.getConditionVariables(condition);
	    		}
	    		
	    		//otherwise it's node connection criterion
	    		else
	    		{
	    			NodeConnection nodeConn= (NodeConnection) criterion;
	    			String condition=nodeConn.getCondition().getExpression();
	    			leftexpressionVariables=new ArrayList<String>();	
	    	    	DataControlDep obj=new DataControlDep(null);
	    	    	if(condition!=null)
	    	    	leftexpressionVariables=obj.getConditionVariables(condition);
	    		}
	    	
	    		if(!leftexpressionVariables.isEmpty())
	    			UCMCIAmcd = new UCMChangeImpactAnalysisCommand(criterion,leftexpressionVariables,2);
	    		else
	    			UCMCIAmcd = new UCMChangeImpactAnalysisCommand(criterion,null,2);	
		  
	    		UCMChangeImpactAnalysisCommand.prevCommand= UCMCIAmcd;
		  
	    		return UCMCIAmcd; 
	  }
	    	
	    	//if calculateEnables==false
	    	else 
	    		return null;
	  }
}
