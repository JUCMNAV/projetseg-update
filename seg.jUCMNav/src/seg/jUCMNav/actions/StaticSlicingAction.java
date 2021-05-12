package seg.jUCMNav.actions;

import java.net.URL;
import java.util.ArrayList;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.Slicing.DataControlDep;
import seg.jUCMNav.model.commands.Slicing.SelectSlicingCriterionWizard;
import seg.jUCMNav.model.commands.Slicing.StaticSlicingCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;

public class StaticSlicingAction extends URNSelectionAction {
	public static final String StaticSlicing = "seg.jUCMNav.StaticSlicing"; //$NON-NLS-1$
	public static EditPartViewer Viewr;
	public static boolean dialogFinished;
	RespRef R;
	URNspec urn;
	EObject criterion;

	/**
	 * @param part
	 */
	public StaticSlicingAction(IWorkbenchPart part) {
		super(part);

		setId(StaticSlicing);
		dialogFinished = false;
		Bundle bundle = Platform.getBundle(JUCMNavPlugin.PLUGIN_ID);
		URL fullPathString = BundleUtility.find(bundle, "icons/SlicingIcon.gif");
		setImageDescriptor(ImageDescriptor.createFromURL(fullPathString)); // $NON-NLS-1$
	}

	/**
	 * True if we select one responsibility .
	 */
	protected boolean calculateEnabled() {
		EditPart t;

		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		if (getSelectedObjects().size() == 1 && (sel.getSelectionType() == SelectionHelper.RESPONSIBILITYREF
				|| sel.getSelectionType() == SelectionHelper.NODECONNECTION
				|| sel.getSelectionType() == SelectionHelper.ENDPOINT)) {

			// RespRef temp=(RespRef) getSelectedObjects().get(0);
			// RespRef ref= (RespRef) ((EObject) getSelectedObjects().get(0));

			t = ((EditPart) getSelectedObjects().get(0));
			// ( (GraphicalEditPart)
			// t).getFigure().setForegroundColor(ColorConstants.green);
			Viewr = t.getViewer();
			EObject obj = (EObject) t.getModel();
			// EditPart modelEditPart=(EditPart)Viewr.getEditPartRegistry().get(obj);
			// ( (GraphicalEditPart)
			// modelEditPart).getFigure().setForegroundColor(ColorConstants.green);

			if (obj instanceof RespRef) {
				R = (RespRef) obj;
				criterion = R;

				return true;
			}

			else if (obj instanceof EndPoint) {
				criterion = obj;
				return true;
			}

			else if (obj instanceof NodeConnection) {
				PathNode pred = (PathNode) ((NodeConnection) obj).getSource();
				if (pred instanceof OrFork || pred instanceof Timer || pred instanceof WaitingPlace) {
					criterion = obj;
					return true;
				}
			}

		}

		return false;
	}

	/**
	 * @return a {@link StaticSlicingCommand}
	 */
	protected Command getCommand() {
		// ***********

		// *********************

		Boolean isEmptyExpression = false; //variable not used
		if (calculateEnabled())

		{
			ArrayList<String> expressionVariables = null;
			// check if it's not empty
			if (criterion instanceof RespRef) {
				if (R.getRespDef().getExpression() != null && !R.getRespDef().getExpression().isEmpty()) {
					expressionVariables = new ArrayList<String>();
					DataControlDep obj = new DataControlDep(null);
					obj.setExpression(R.getRespDef().getExpression());
					expressionVariables = obj.getAllVariables();
				}
				// otherwise it's empty
				else
					isEmptyExpression = true;

			}

			// if criterion is endpoint
			else if (criterion instanceof EndPoint) {
				EndPoint endpoint = (EndPoint) criterion;
				expressionVariables = new ArrayList<String>();
				DataControlDep obj = new DataControlDep(null);
				String condition = endpoint.getPostcondition().getExpression();
				expressionVariables = obj.getConditionVariables(condition);
			}

			// otherwise it's node connection criterion
			else {
				NodeConnection nodeConn = (NodeConnection) criterion;
				String condition = nodeConn.getCondition().getExpression();
				expressionVariables = new ArrayList<String>();
				DataControlDep obj = new DataControlDep(null);
				expressionVariables = obj.getConditionVariables(condition);
			}
			String name = null;
			if (criterion instanceof RespRef)
				name = R.getRespDef().getName();
			else if (criterion instanceof EndPoint)
				name = ((EndPoint) criterion).getName();
			else {
				name = ((PathNode) ((NodeConnection) criterion).getSource()).getName() + " Condition";
			}
			WizardDialog dialog = new WizardDialog(null, new SelectSlicingCriterionWizard(expressionVariables, name));
			// dialog.setMinimumPageSize(800, 200);
			dialog.open();
			SelectSlicingCriterionWizard wiz = (SelectSlicingCriterionWizard) dialog.getCurrentPage().getWizard();

			// removal can not be executed if no code exists, only coloring can
			if (dialogFinished) {
				// System.out.println("Finish clicked");
				StaticSlicingCommand slicing = new StaticSlicingCommand(criterion, wiz.getSelectedVariables(),
						wiz.Removetype, wiz.fileName);
				StaticSlicingCommand.prevCommand = slicing;
				wiz.dispose();
				// CreatePathCommand path=new CreatePathCommand((UCMmap)
				// ((PathNode)criterion).getDiagram(), 300,200);
				// path.execute();

				return slicing;

			} else {
				// System.out.println("Cancel Pressed");
				return null;

			}
		}

		else
			return null;
	}

}
