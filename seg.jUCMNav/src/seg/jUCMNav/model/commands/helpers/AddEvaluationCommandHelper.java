package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import seg.jUCMNav.Messages;

public class AddEvaluationCommandHelper implements Command {

    
    private Evaluation evaluation;
    private IntentionalElement element;
    private EvaluationStrategy strategy;

    /**
     * Constructor
     */
    public AddEvaluationCommandHelper(Evaluation evaluation, IntentionalElement elem, EvaluationStrategy strategy) {
        this.evaluation = evaluation;
        this.element = elem;
        this.strategy = strategy;

     //   setLabel(Messages.getString("AddEvaluationCommand.addEvaluation")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if ((evaluation != null && element != null && strategy != null) && (evaluation.getIntElement() == null) && (evaluation.getStrategies() == null)) {
            return true;
        }
        return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        evaluation.setIntElement(element);
        strategy.getEvaluations().add(evaluation);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert evaluation != null && element != null && strategy != null : "pre null"; //$NON-NLS-1$
        assert evaluation.getIntElement() != element && evaluation.getStrategies() != strategy : "pre link set!"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert evaluation != null && element != null && strategy != null : "post null"; //$NON-NLS-1$
        assert !(evaluation.getIntElement() != element && evaluation.getStrategies() != strategy) : "post link set"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        strategy.getEvaluations().remove(evaluation);
        evaluation.setIntElement(null);
        testPreConditions();
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Collection<?> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getAffectedObjects() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Command chain(Command command) {
        // TODO Auto-generated method stub
        return null;
    }
    
}