package seg.jUCMNav.actions.scenarios;

import grl.EvaluationStrategy;

import org.eclipse.ui.IWorkbenchPart;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

public class SetComparisonStrategyAction extends URNSelectionAction {

    public static final String SET_COMPARISON_STRATEGY = "seg.jUCMNav.SetComparisonStrategyAction"; //$NON-NLS-1$

	private EvaluationStrategy strategy2 = null;

	public SetComparisonStrategyAction(IWorkbenchPart part) {
		super(part);
		setId(SET_COMPARISON_STRATEGY);
	}

    protected boolean calculateEnabled()
    {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        
        if( sel.getSelectionType() == SelectionHelper.EVALUATIONSTRATEGY ) {
    		strategy2 = sel.getStrategy();
        	if( EvaluationStrategyManager.getInstance().isDifferenceMode( strategy2 ) ) {
        		return true;
        	}
        }
        
        return false;
    }

    public void run()
    {
    	if( strategy2 != null ) {
    		EvaluationStrategyManager.getInstance().setComparisonStrategy( strategy2 );
    	}
    }

}
