package seg.jUCMNav.model.commands.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

public class DistributePulldownCommandHandler extends AbstractHandler implements IHandler {

    
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {	
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
