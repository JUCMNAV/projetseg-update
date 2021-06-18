/*
 * Created on 15-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.UCMScenarioViewer.model;

import org.eclipse.swt.graphics.Image;

import seg.UCMScenarioViewer.utils.Helper;

/**
 * @author oboyk022
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResetTimer extends SetTimer {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
	 * 
	 */
	public ResetTimer() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param owner
	 */
	public ResetTimer(String id, String name, LifeLine owner) {
		super(id, name, owner);
	}
	
	private static Image DOELEM_ICON_RESET_TIMER = new Image(null, DoElement.class.getResourceAsStream(Helper.ICON_OUTLINE_RESET_TIMER));  //$NON-NLS-1$
	
	/* (non-Javadoc)
	 * @see seg.UCMScenarioViewer.model.AbstractModelElement#getIconImage()
	 */
	public Image getIconImage() {
		return DOELEM_ICON_RESET_TIMER;
	}
}
