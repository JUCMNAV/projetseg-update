/*
 * Created on 31.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.UCMScenarioViewer.model;

import java.util.Collections;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.UCMScenarioViewer.utils.DefaultFigureSize;
import seg.UCMScenarioViewer.utils.Properties;
import seg.UCMScenarioViewer.utils.Helper;

/**
 * @author Sasha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Message extends SequenceElement {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private LifeLine source;
	private LifeLine target;

	public Message() {}
	
	protected static final IPropertyDescriptor[] descriptors  = new IPropertyDescriptor[]{
        new PropertyDescriptor(Properties.ID_REFRESH_SOURCE, "Refresh Source Anchor"),
        new PropertyDescriptor(Properties.ID_REFRESH_TARGET, "Refresh Target Anchor")           
    };
    
	/**
	 * @param id
	 * @param name
	 */
	public Message(String id, String name) {
		super(id, name);
	}

	public Message(String id, String name, LifeLine source, LifeLine target) {
		super(id, name);
		this.source = source;
		this.target = target;
		source.addSourceConncetion(this);
		target.addTargetConncetion(this);
	}
	
	public LifeLine getSource() {
		return source; 
	}
	
	public LifeLine getTarget() {
		return target;
	}
	
	public void setSource(LifeLine l) {
		this.source = l;
	}
	
	public void setTarget(LifeLine l) {
		this.target = l;
	}
	
	/* (non-Javadoc)
	 * @see seg.UCMViewer.model.AbstractModelElement#getChildren()
	 */
	public List getChildren() {
		return Collections.EMPTY_LIST;
	}
	
	
	/* (non-Javadoc)
	 * @see seg.UCMViewer.model.AbstractModelElement#getXdimension()
	 */
	public int getXdimension() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see seg.UCMViewer.model.AbstractModelElement#getYdimension()
	 */
	public int getYdimension() {
		if (source == target)
			return 2*getLabel().getTextBounds().height;
		return DefaultFigureSize.MESSAGE_THICKNESS;
	}
	
	public void invalidateSource() {
		setX(-1);
		setY(-1);
	}

	public void invalidateTarget() {
		setX(-1);
		setY(-1);
	}
		
	/**
	 * assigns an icon to this Message in the Outline View of Eclipse IDE
	 * @see Helper
	 */
	private static Image MSG_ICON = new Image(null,	
			Message.class.getResourceAsStream(Helper.ICON_OUTLINE_MESSAGE));  //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see seg.UCMScenarioViewer.model.AbstractModelElement#getIconImage()
	 */
	public Image getIconImage() {
			return MSG_ICON;
		}

}
