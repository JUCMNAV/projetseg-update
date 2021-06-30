package seg.jUCMNav.model.commands.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.ISourceProviderService;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.changeConstraints.AlignCommand;
import seg.jUCMNav.sourceProviders.AlignStateSourceProvider;

public class AlignCommandHandler extends AbstractHandler implements IHandler {

    
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		CommandStack cmdStack = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
				getActiveEditor()).getDelegatingCommandStack();
		
		// getting the selection type
		ISourceProviderService service = (ISourceProviderService) 
        		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(ISourceProviderService.class);
        AlignStateSourceProvider alignStateSourceProvider = (AlignStateSourceProvider) 
        		service.getSourceProvider(AlignStateSourceProvider.SELECTION_TYPE);
       
       String selectionType = alignStateSourceProvider.getCurrentState().get("seg.jUCMNav.AlignSelectionType");
       
       String alignType = event.getParameter("seg.jUCMNav.AlignType");
       
       List sel = alignStateSourceProvider.getFilterdSelection();
       
       cmdStack.execute(new AlignCommand(sel, Integer.valueOf(selectionType), alignType ));

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
