package seg.jUCMNav.actions;

import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Contribution;
import grl.LinkRef;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeCorrelationCommand;
import seg.jUCMNav.strategies.util.ReusedElementUtil;

/**
 * Changes the correlation attribute of a GRL Contribution.
 * 
 * @author damyot
 */
public class ChangeCorrelationAction extends URNSelectionAction {
    private Vector<LinkRef> linkRefs;

    public static final String CHANGECORRELATION = "seg.jUCMNav.ChangeCorrelation"; //$NON-NLS-1$

    /**
     * @param part
     */
    public ChangeCorrelationAction(IWorkbenchPart part) {
        super(part);
        setId(CHANGECORRELATION);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Correlation16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a link reference (contribution) selected.
     */
    protected boolean calculateEnabled() {
        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (!(obj instanceof LinkRefEditPart))
                return false;

            LinkRef lr = (LinkRef) (((LinkRefEditPart) obj).getModel());
            if (!(lr.getLink() instanceof Contribution) || lr.getLink() instanceof OptionalFMLink
            		|| lr.getLink() instanceof MandatoryFMLink || ReusedElementUtil.isReuseLink(lr.getLink()))
                return false;
        }

        linkRefs = new Vector<LinkRef>(); // all tests passed, create list

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            LinkRef lr = (LinkRef) (((LinkRefEditPart) iter.next()).getModel());
            linkRefs.add(lr);
        }

        return true;
    }

    /**
     * Returns the change command, given the current selection.
     */
    protected Command getCommand() {
        return new ChangeCorrelationCommand(linkRefs);
    }

}