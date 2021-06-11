package seg.jUCMNav.actions;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.Slicing.DataControlDep;
import seg.jUCMNav.model.commands.changeImpactAnalysis.ForSelectSlicingCriterionWizard;
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

public class UCMChangeImpactAnalysisModificationAction extends URNSelectionAction{
	public static final String UCMChangeImpactAnalysisModification = "seg.jUCMNav.UCMChangeImpactAnalysisModification"; //$NON-NLS-1$
	public static EditPartViewer Viewr;
	public static boolean dialogFinished;
	RespRef R;URNspec urn;
	EObject criterion;
	
	public UCMChangeImpactAnalysisCommand UCMCIAmcd;
	 /**
     * @param part
     */
	public UCMChangeImpactAnalysisModificationAction(IWorkbenchPart part) {
		super(part);
	    setId(UCMChangeImpactAnalysisModification);
	    dialogFinished = false;
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
			EObject obj = (EObject)t.getModel();
	     
			if(obj instanceof RespRef){
				R = (RespRef) obj ;
				criterion = R;
				return true;
			}else if(obj instanceof StartPoint){
				criterion=obj;
				return true;
 		}else if(obj instanceof NodeConnection ){
 			PathNode pred=(PathNode) ((NodeConnection) obj).getSource();
 			
 			if(pred instanceof OrFork || pred instanceof Timer || pred instanceof WaitingPlace){
 				criterion=obj;
 				return true;
 			}
 		}else if(obj instanceof OrFork ||obj instanceof AndFork || obj instanceof Timer){
 			NodeConnection pred = (NodeConnection)((IURNNode)obj).getPred().get(0);
 			
 			criterion = pred;
 			
 			//JOptionPane.showMessageDialog(null, pred.toString());
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
	protected Command getCommand(){
		Boolean isEmptyExpression = false;
		if(calculateEnabled()){
			ArrayList<String> expressionVariables = null;
			if(criterion instanceof RespRef){
	    		if(R.getRespDef().getExpression()!=null && !R.getRespDef().getExpression().isEmpty())
	        	{
	        		expressionVariables = new ArrayList<String>();	
		    	    DataControlDep obj = new DataControlDep(null);
		    	    obj.setExpression(R.getRespDef().getExpression());
		    	    expressionVariables = obj.getAllVariables();
	        	}
	    		//otherwise it's empty
	        	else
	        		isEmptyExpression = true;	    	    	
	    	}
			//if criterion is StartPoint
			else if(criterion instanceof StartPoint){
				StartPoint stratpoint = (StartPoint) criterion;
	    		expressionVariables = new ArrayList<String>();	
	    	    DataControlDep obj = new DataControlDep(null);
	    	    String condition=stratpoint.getPrecondition().getExpression();
	    	    expressionVariables = obj.getConditionVariables(condition);
	    	}
			//otherwise it's node connection criterion
		    	else if(criterion instanceof NodeConnection)
		    	{
		    			    		
		    		NodeConnection nodeConn = (NodeConnection) criterion;
		    		
		    		if(nodeConn.getTarget() instanceof OrFork || nodeConn.getTarget() instanceof AndFork || nodeConn.getTarget() instanceof Timer){
		    			isEmptyExpression = true;
		    			expressionVariables = null;
			    	}else if(nodeConn.getSource() instanceof OrJoin || nodeConn.getSource() instanceof AndJoin){
			    		isEmptyExpression = true;
		    			expressionVariables = null;
		    	   	}else{
		    	   		String condition = nodeConn.getCondition().getExpression();
			    		expressionVariables = new ArrayList<String>();	
			    	   	DataControlDep obj = new DataControlDep(null);
			    	   	expressionVariables = obj.getConditionVariables(condition);
		    	   	}
		    	}
			  
			String name = null;
			  
			if(criterion instanceof RespRef)
				name = R.getRespDef().getName();
			
			else if(criterion instanceof StartPoint)
				name = ((StartPoint) criterion).getName();
			  	else{
				  name =((PathNode) ((NodeConnection)criterion).getSource()).getName()+" Condition";
			  	}
			  
			UCMChangeImpactAnalysisCommand cmd;
			
			if(expressionVariables!=null && !expressionVariables.isEmpty()){
				WizardDialog dialog = new WizardDialog(null, new ForSelectSlicingCriterionWizard(expressionVariables,name));
				dialog.open();
				ForSelectSlicingCriterionWizard wiz = (ForSelectSlicingCriterionWizard)dialog.getCurrentPage().getWizard();
			  
			 //coloring can be done
		    	if(dialogFinished){
		    		System.out.println("Finish clicked");
	    			
		    		if(wiz.getSelectedVariables()!=null && !wiz.getSelectedVariables().isEmpty())
		    			cmd = new UCMChangeImpactAnalysisCommand(criterion,wiz.getSelectedVariables(),1);
		    		else
		    			cmd = new UCMChangeImpactAnalysisCommand(criterion,null,1);
		    		
		    		UCMChangeImpactAnalysisCommand.prevCommand=(UCMChangeImpactAnalysisCommand) cmd; 
					//JOptionPane.showMessageDialog(null,"Command - 1");
		    		wiz.dispose();
		    		return  cmd;		    	
		    	}
		    	else
		    	{
		    		System.out.println("Cancel Pressed");
		    		return null;		    		
		    	}
		  }
		//otherwise there is no code expression
			else
			{
				cmd = new UCMChangeImpactAnalysisCommand(criterion,null,1);
		    	UCMChangeImpactAnalysisCommand.prevCommand = (UCMChangeImpactAnalysisCommand) cmd;
		    	
		    	if(criterion instanceof NodeConnection){
		    		NodeConnection ncTemp = (NodeConnection)criterion;
		    		if(ncTemp.getTarget() instanceof OrFork || ncTemp.getTarget() instanceof AndFork || ncTemp.getTarget() instanceof Timer){
		    			cmd.isorAndTimerCriterion = true;
		    		}
		    	}
		    	return cmd;
			 }
		  }//end if (CalculatedEnabled)
		  else
		  {return null;}
	  }  	    
}
