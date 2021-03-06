package seg.jUCMNav.actions.dynamicContexts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextLabelTreeEditPart;
import seg.jUCMNav.views.wizards.dynamicContexts.AddDynamicContextScenario;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.dyncontext.DynamicContext;

/**
 * Adds a scenario to a dynamic context.
 * 
 * @author aprajita
 * 
 */
public class AddDynamicContextScenarioAction extends URNSelectionAction {
	
	public static final String ADDCONTEXTSCENARIO = "seg.jUCMNav.AddDynamicContextScenario"; //$NON-NLS-1$

    protected DynamicContext dyn;
    protected ScenarioDef scenario;

    /**
     * @param part
     */
    public AddDynamicContextScenarioAction(IWorkbenchPart part) {
        super(part);
        setId(ADDCONTEXTSCENARIO);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucmscen16.gif")); //$NON-NLS-1$
    }

    /**
     * Can add a scenario if there are scenarios left that can be added without causing recursive loops.
     */
    protected boolean calculateEnabled() {
        initDynamicContext();
        int size = 0;
        if (dyn != null){
	        List scenarioGroups = dyn.getUrnspec().getUcmspec().getScenarioGroups();
	        
	        if (scenarioGroups != null && scenarioGroups.size() > 0){
		        for (int i=0; i < scenarioGroups.size(); i++){
		        	if (((ScenarioGroup)scenarioGroups.get(i)).getScenarios().size() > 0){
		        		size++;
		        	}
		        }
	        }
        }
        return dyn != null && size > 0 && dyn.getScenario() == null;
    }

    /**
     * Allows us to include a scenario inside a dynamic context even if we have selected an element in the tree which is not 
     * associated to a model element (the multiple folders that contain the children of a dynamic context).
     * 
     */
    protected void initDynamicContext() {
        List list = getSelectedObjects();
        ArrayList<Object> list2 = new ArrayList<Object>();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object object = iter.next();

            if (object instanceof DynamicContextLabelTreeEditPart)
                list2.add(((DynamicContextLabelTreeEditPart) object).getParent());
            else
                list2.add(object);
        }
        SelectionHelper sel = new SelectionHelper(list2);
        switch (sel.getSelectionType()) {
        case SelectionHelper.DYNAMICCONTEXT:
            dyn = sel.getDynamicContext();
            break;
        default:
            dyn = null;
        }
    }

    /**
     * Opens the add scenario in dynamic context wizard.
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        AddDynamicContextScenario wizard = new AddDynamicContextScenario();

        StructuredSelection selection = new StructuredSelection(dyn);
        wizard.init(PlatformUI.getWorkbench(), selection);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}
