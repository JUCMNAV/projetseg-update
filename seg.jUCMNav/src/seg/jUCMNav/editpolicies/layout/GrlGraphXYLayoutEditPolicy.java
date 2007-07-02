/**
 * 
 */
package seg.jUCMNav.editpolicies.layout;

import grl.ActorRef;
import grl.Belief;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import seg.jUCMNav.model.commands.changeConstraints.SetConstraintBoundContainerRefCompoundCommand;
import seg.jUCMNav.model.commands.changeConstraints.SetConstraintCommand;
import seg.jUCMNav.model.commands.create.AddBeliefCommand;
import seg.jUCMNav.model.commands.create.AddContainerRefCommand;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateAllLinkRefCommand;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.Label;

/**
 * XYLayoutEditPolicy for the GrlGraphEditPart. Handles creation of new elements and moving/resizing of existing ones.
 * @author Jean-Fran�ois Roy
 *
 */
public class GrlGraphXYLayoutEditPolicy extends AbstractDiagramXYLayoutEditPolicy {


	/**
	 * Returns a command to be executed when the palette tries to create something
	 * 
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
	 */
	protected Command getCreateCommand(CreateRequest request) {
		Object newObjectType = null;
		if (request.getNewObject() != null)
			newObjectType = request.getNewObjectType();

		// converts relative to absolute positions (so that zooms work properly)
		Rectangle constraint = (Rectangle) getConstraintFor(request);
		Command createCommand = null;

		if ((newObjectType == IntentionalElementRef.class) || (newObjectType == Belief.class)) {
			createCommand = handleCreateGrlNode(request, constraint);
		}else if (newObjectType == ActorRef.class) {
			createCommand = handleCreateActorRef(request, constraint);
		}
		return createCommand;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getDeleteDependantCommand(org.eclipse.gef.Request)
	 */
	protected Command getDeleteDependantCommand(Request request) {
		return null;
	}

	/**
	 * Convenience method to prevent casting
	 * 
	 * @return the graph
	 */
	protected GRLGraph getGraph() {
		return (GRLGraph) getHost().getModel();
	}

	/**
	 * Handles moving/resizing of Graph children.
	 * 
	 * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#createChangeConstraintCommand(org.eclipse.gef.EditPart, java.lang.Object)
	 */
	public Command createChangeConstraintCommand(EditPart child, Object constraint) {
		if (child.getModel() instanceof IURNNode) {
			return handleMoveGRLNode(child, constraint);
		} else if (child.getModel() instanceof Label) {
			return handleMoveLabel(child, constraint);
		}else if (child.getModel() instanceof IURNContainerRef){
			return handleMoveResizeContainerRef(child, constraint);
		}else {
			System.out.println("Unknown model element"); //$NON-NLS-1$
			return null;
		}

	}

	/**
	 * @param request
	 *            the CreateRequest containing the new ActorRef
	 * @param constraint
	 *            where the new object should be created
	 * @return a command that adds a new ActorRef on the Map and moves/resizes it into place.
	 */
	private Command handleCreateActorRef(CreateRequest request, Rectangle constraint) {
		Command createCommand;
		ActorRef actorRef = (ActorRef) request.getNewObject();

		AddContainerRefCommand create = new AddContainerRefCommand(getGraph(), actorRef);
		SetConstraintBoundContainerRefCompoundCommand moveResize = new SetConstraintBoundContainerRefCompoundCommand(actorRef, constraint.x, constraint.y, constraint.width,
				constraint.height);

		// after creation, move and resize the component;
		createCommand = create.chain(moveResize);
		return createCommand;
	}

	/**
	 * @param request
	 *            the CreateRequest containing the new ComponentRef
	 * @param constraint
	 *            where the new object should be created
	 * @return a command that adds a new IntentionalElementRef on the Graph and moves/resizes it into place.
	 */ 
	private Command handleCreateGrlNode(CreateRequest request, Rectangle constraint) {
		Command createCommand=null;
		Command create = null;
		GRLNode node = null;
		if (request.getNewObject() instanceof IntentionalElementRef){
			node = (IntentionalElementRef) request.getNewObject();
			create = new AddIntentionalElementRefCommand(getGraph(), (IntentionalElementRef)node);

			if (GeneralPreferencePage.getGrlAutoAddLinks()) {
				if (((IntentionalElementRef) node).getDef()
						.getLinksSrc().size()
						+ ((IntentionalElementRef) node).getDef()
						.getLinksDest().size() > 0) {
					// dragged existing element from outline: Add existing links automatically
					CreateAllLinkRefCommand createCmd = new CreateAllLinkRefCommand(
							getGraph(),
							(IntentionalElementRef) node);
					if (createCmd.canExecute())
						create = create.chain(createCmd);
				}
			}                        
		} else if (request.getNewObject() instanceof Belief){   
			node = (Belief) request.getNewObject();
			create = new AddBeliefCommand(getGraph(), (Belief)node);
		}
		SetConstraintCommand move  = new SetConstraintCommand(node, constraint.x, constraint.y);

		// after creation, move and resize the node;
		if (create!=null)
			createCommand = create.chain(move);

		return createCommand;
	}

	/**
	 * Handles moving an GRLNode.
	 * 
	 * @param child
	 *            the IntentionalElementRefEditPart or BeliefEditPart
	 * @param constraint
	 *            where it should be moved and resize.
	 * @return a SetConstraintIntentionalElementRefCommand
	 */
	private Command handleMoveGRLNode(EditPart child, Object constraint) {
		Rectangle rect = (Rectangle) constraint;
		GRLNode node = (GRLNode) child.getModel();

		return new SetConstraintCommand(node, rect.getLocation().x, rect
				.getLocation().y);

	}

}
