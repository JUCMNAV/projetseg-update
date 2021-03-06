package seg.jUCMNav.views.wizards.dynamicContexts;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.PlatformUI;

import grl.EvaluationStrategy;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urn.dyncontext.DynamicContext;

/**
 * The page actually containing the code for including a strategy. 
 * 
 * @author aprajita
 */
public class AddDynamicContextStrategyPage extends WizardPage {
	
	private ISelection selection;
    private DynamicContext parent;
    private EvaluationStrategy strategy;
    private List strategies;

    /**
     * The selection contains a dynamic context definition for which we want to include a strategy. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public AddDynamicContextStrategyPage(ISelection selection) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        setTitle(Messages.getString("AddDynamicContextStrategyPage.AddDynamicContextStrategy")); //$NON-NLS-1$
        setDescription(Messages.getString("AddDynamicContextStrategyPage.PleaseChooseDynamicContextStrategy")); //$NON-NLS-1$

        // loaded in initialize()
        this.selection = selection;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.dynamiccontext_adddynamiccontextstrategy"); //$NON-NLS-1$
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 5;

        // label over the code box.
        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddDynamicContextStrategyPage.PleaseChooseDynamicContextStrategy")); //$NON-NLS-1$

        initialize();

        strategies = new List(container, SWT.MULTI | SWT.BORDER | SWT.SCROLL_LINE);
        strategies.setItems(getPossibleChildren());

        GridData gd = new GridData(GridData.FILL_BOTH);
        strategies.setLayoutData(gd);
        strategies.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                dialogChanged();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double-click does nothing.

            }
        });

        dialogChanged();
        setControl(container);

    }
    
    /**
     * 
     * @return all possible strategies that can be included
     */
    private String[] getPossibleChildren() {
        java.util.List possibleChildren = parent.getUrnspec().getGrlspec().getStrategies();

        ArrayList childrenStrings = new ArrayList();
        for (Iterator iter = possibleChildren.iterator(); iter.hasNext();) {
            EvaluationStrategy possibleChild = (EvaluationStrategy) iter.next();
            childrenStrings.add(URNNamingHelper.getName(possibleChild.getGroup())
                    + Messages.getString("IncludeDynamicContextPage.GroupDynamicContextSeperator") + URNNamingHelper.getName(possibleChild)); //$NON-NLS-1$
        }

        Object[] o = childrenStrings.toArray();
        String[] strings = new String[o.length];
        for (int i = 0; i < o.length; i++) {
            strings[i] = o[i].toString();
        }
        return strings;
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if (obj instanceof DynamicContext) {
                parent = (DynamicContext) obj;
            }
        }
    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {

        if (strategies.getSelectionIndex() < 0)
            updateStatus(Messages.getString("AddDynamicContextStrategyPage.SelectDynamicContextStrategy")); //$NON-NLS-1$
        else {
            strategy = null;
            //int a = 1;
            for (int i = 0; i < strategies.getSelectionIndices().length; i++) {
                int index = strategies.getSelectionIndices()[i];
                strategy = (EvaluationStrategy) parent.getUrnspec().getGrlspec().getStrategies().get(index);
            }
            updateStatus(null);
        }
    }

    /**
     * Updates the status of the window
     * 
     * @param message
     *            the error message or null if no error message.
     */
    private void updateStatus(String message) {
        setErrorMessage(message);

        if (GeneralPreferencePage.getStrictCodeEditor())
            setPageComplete(message == null);
        else
            setPageComplete(true);

    }

    /**
     * Returns the child scenarios that has been selected.
     * 
     * @return the children
     */
    public EvaluationStrategy getChildEvaluationstrategy() {
        return strategy;
    }

    /**
     * Returns the parent scenario that was initially given
     * 
     * @return the child
     */
    public DynamicContext getParentDynamicContext() {
        return parent;
    }

}
