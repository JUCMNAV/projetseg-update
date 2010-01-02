package seg.jUCMNav.model.commands.transformations;

import grl.DecompositionType;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Changes the name/values of an enumeration type. For the moment, swaps between And and Or only.
 * 
 * @author damyot
 */
public class ChangeDecompositionTypeCommand extends Command implements JUCMNavCommand {
    private IntentionalElementRef intElemRef;
    private int oldType, newType;

    public ChangeDecompositionTypeCommand(IntentionalElementRef intElemRef) {
        this.intElemRef = intElemRef;

        switch (intElemRef.getDef().getDecompositionType().getValue()) {
        case DecompositionType.AND :
            oldType = DecompositionType.AND;
            newType = DecompositionType.OR;
            break;
        case DecompositionType.OR :
            oldType = DecompositionType.OR;
            newType = DecompositionType.XOR;
            break;
        case DecompositionType.XOR :
            oldType = DecompositionType.XOR;
            newType = DecompositionType.AND;
            break;      
        }

        setLabel(Messages.getString("ChangeDecompositionTypeCommand.ChangeDecompositionType")); //$NON-NLS-1$
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

        intElemRef.getDef().setDecompositionType(DecompositionType.get(newType));

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert intElemRef != null : "post no element!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert intElemRef != null : "pre no element!"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        intElemRef.getDef().setDecompositionType(DecompositionType.get(oldType));

        testPreConditions();
    }

}