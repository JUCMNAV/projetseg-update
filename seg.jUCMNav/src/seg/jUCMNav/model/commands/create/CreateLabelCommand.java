/*
 * Created on Mar 30, 2005
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.ComponentLabel;
import urncore.Label;
import urncore.NodeLabel;
import urncore.UCMmodelElement;

/**
 * @author Jordan
 */
public class CreateLabelCommand extends Command implements JUCMNavCommand{
    private static final String	CreateCommand_Label = "CreateLabelCommand";
    public static final int DEFAULT_DELTAX_PATHNODE=0;
    public static final int DEFAULT_DELTAY_PATHNODE=0;
    
	private Label label;
	private UCMmodelElement modelElement;
	private int deltaX;
	private int deltaY;
	
	public CreateLabelCommand(UCMmodelElement elem)
	{
	    modelElement = elem; 
	}
	
	public boolean canExecute() {
		return modelElement != null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {

	    label.setDeltaX(deltaX);
	    label.setDeltaY(deltaY);
		
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		testPreConditions();
		
		if(modelElement instanceof PathNode) {
			((PathNode) modelElement).setLabel((NodeLabel)label);
		} else if(modelElement instanceof ComponentRef) {
			((ComponentRef) modelElement).setLabel((ComponentLabel)label);
        }
		
        testPostConditions();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();
		
		if(modelElement instanceof PathNode) {
			((PathNode) modelElement).setLabel(null);
		} else if(modelElement instanceof ComponentRef) {
			((ComponentRef) modelElement).setLabel(null);
        }

        testPreConditions();
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert label != null : "pre Label";
        assert modelElement != null : "pre UCMmodelElement";
        //assert label.getPathNode() == null : "pre NodeLabel not connected to a PathNode";
        //assert node.getLabel() == null : "pre PathNode not connected to a NodeLabel";
        
        assert label.getDeltaX() == deltaX : "pre Label deltaX";
        assert label.getDeltaY() == deltaY : "pre Label deltaY";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
    	assert label != null : "pre Label";
    	assert modelElement != null : "pre UCMmodelElement";
        //assert label.getPathNode().equals(node) : "pre NodeLabel connected to correct PathNode";
        //assert node.getLabel().equals(label) : "pre PathNode connected to correct NodeLabel";
        
    	assert label.getDeltaX() == deltaX : "pre Label deltaX";
        assert label.getDeltaY() == deltaY : "pre Label deltaY";
    }

	/**
	 * @param modelElement The modelElement to set.
	 */
	public void setModelElement(UCMmodelElement modelElement) {
		this.modelElement = modelElement;
	}
	
	/**
	 * @param deltaX The deltaX to set.
	 */
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
	
	/**
	 * @param deltaY The deltaY to set.
	 */
	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}
}

