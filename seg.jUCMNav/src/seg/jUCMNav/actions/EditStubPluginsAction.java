package seg.jUCMNav.actions;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.stub.StubBindingsDialog;
import ucm.map.Stub;

/**
 * Opens the stub plugin dialog.
 * 
 * @author Etienne Tremblay
 */
public class EditStubPluginsAction extends URNSelectionAction {

    public static final String EDITSTUBPLUGINS = "seg.jUCMNav.EditStubPluginsAction"; //$NON-NLS-1$

    private Stub stub;

    /**
     * @param part
     */
    public EditStubPluginsAction(IWorkbenchPart part) {
        super(part);
        setId(EDITSTUBPLUGINS);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Binding16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected a stub.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.STUB:
            stub = sel.getStub();
            return true;
        }
        return false;
    }

    /**
     * Open the {@link StubBindingsDialog}
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        StubBindingsDialog d = new StubBindingsDialog(shell, getCommandStack());
        d.open(stub);
    }

}