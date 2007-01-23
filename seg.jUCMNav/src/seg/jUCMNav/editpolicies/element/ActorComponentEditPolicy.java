package seg.jUCMNav.editpolicies.element;

import grl.Actor;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteActorCommand;

/**
 * ComponentEditPolicy for actor definition. Policy return the delete command
 * @author Jean-Fran�ois Roy
 *
 */
public class ActorComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteActorCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object comp = getHost().getModel();
        if (comp instanceof Actor) {

            Actor elem = (Actor) comp;
            if (elem.getContRefs().size() == 0) {
                DeleteActorCommand deleteCommand = new DeleteActorCommand(elem);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }
}
