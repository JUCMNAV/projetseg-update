package seg.jUCMNav.model.commands.handlers;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.ISourceProviderService;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.changeConstraints.DistributeCommand;
import seg.jUCMNav.sourceProviders.AlignStateSourceProvider;
import seg.jUCMNav.views.wizards.DistributeCustomDialog;

public class DistributeCommandHandler extends AbstractHandler implements IHandler {

    
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		final CommandStack cmdStack = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
				getActiveEditor()).getDelegatingCommandStack();
		
		// getting the selection type
		ISourceProviderService service = (ISourceProviderService) 
        		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(ISourceProviderService.class);
        AlignStateSourceProvider alignStateSourceProvider = (AlignStateSourceProvider) 
        		service.getSourceProvider(AlignStateSourceProvider.SELECTION_TYPE);
       
       final String selectionType = alignStateSourceProvider.getCurrentState().get("seg.jUCMNav.AlignSelectionType");
       
       final String distributeType = event.getParameter("seg.jUCMNav.DistributeType");
       
       final List sel = alignStateSourceProvider.getFilterdSelection();

      
   	
       if( distributeType.compareTo("seg.jUCMNav.DistributeCustom") == 0 ){
   		  
    	   DistributeCustomDialog dialog = new DistributeCustomDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
    	   HashMap<String, String> values = dialog.getValues();
    	  
    	   if( values != null){
    		   cmdStack.execute(new DistributeCommand(sel, Integer.valueOf(selectionType), values.get("DistributeType"), true));
    	   }
    	  
   		}else{
       
   			cmdStack.execute(new DistributeCommand(sel, Integer.valueOf(selectionType), distributeType, false));
   		}
		return null;
	}
	
	@Override
	public void setEnabled(Object evaluationContext) {	
	}
	
	
	@Override
	public boolean isEnabled() {
		
		return true;
	}
}
