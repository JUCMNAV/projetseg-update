/*
 * Created on 31.01.2005
 *
 */
package seg.UCMScenarioViewer.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.Label;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.UCMScenarioViewer.UCMScenarioViewer;
import seg.UCMScenarioViewer.utils.DefaultFigureSize;
import seg.UCMScenarioViewer.utils.Helper;
import seg.UCMScenarioViewer.utils.Properties;

/**
 * @author Sasha
 *
 */
public class Scenario extends AbstractModelElement {

	private static final long serialVersionUID = 1L;
	public static final int LIFELINES = 0;
	public static final int SEQUENCE = 1;

	private String description;
	private double zoom = 1.0;
	protected static final IPropertyDescriptor[] descriptors = new IPropertyDescriptor[]{
		new PropertyDescriptor(Properties.ID_INSERT_LIFELINE, "Insert LifeLine")
	};


	public Scenario() {

	}

	public Scenario(String id, String name, String description) {
		super(id,name);
		this.description = description;
		children = new ArrayList();
		children.add(new ArrayList());
		children.add(null);
	}

	public String getDescription() {
		return description;
	}

	private void setSequence(Sequence sequence) {
		children.add(SEQUENCE, sequence);
		sequence.setParent(this);
	}

	private void setLifeLines(ArrayList lifelines) {
		int i=0;
		for (; i<lifelines.size() && lifelines.get(i) instanceof LifeLine; i++);
		if (i == lifelines.size()) {
			for (i=0; i<lifelines.size(); i++) {
				((LifeLine)lifelines.get(i)).setNumber(i);
				((LifeLine)lifelines.get(i)).setParent(this);				
			}
			children.add(LIFELINES, lifelines);					
		}
	}

	private void addLifeLine(LifeLine lifeline) {
		Object childLifeLines = children.get(LIFELINES);
		if (childLifeLines == null) {
			ArrayList<LifeLine> lifelines = new ArrayList<LifeLine>();
			lifelines.add(lifeline);
			return;
		}

		if (childLifeLines instanceof ArrayList) {
			((ArrayList) childLifeLines).add(lifeline);
		}
		lifeline.setNumber( ((ArrayList)children.get(LIFELINES)).size()-1 );
		lifeline.setParent(this);
	}

	public void addChild(Object child) {
		if (child instanceof Sequence) {
			setSequence((Sequence) child);
			((Sequence)child).setNumber(0);
		}
		else if (child instanceof ArrayList) {
			setLifeLines((ArrayList) child);
		}
		else if (child instanceof LifeLine) {
			addLifeLine((LifeLine) child);
		}
	}

	public List getViewChildren() {
		if (children.get(LIFELINES) == null)
			return Collections.EMPTY_LIST;
		ArrayList elements = (ArrayList)children.get(LIFELINES);
		if (elements.size() == 0)
			return Collections.EMPTY_LIST;
		ArrayList allChildren = new ArrayList();
		for (int i=0; i<elements.size(); i++) {
			allChildren.add(i,elements.get(i));
		}
		if (children.get(SEQUENCE) != null) {
			Iterator i = getSequence().getViewChildren().iterator();
			while (i.hasNext())
				allChildren.add(i.next());
		}
		return allChildren;
	}

	protected List getChildren() {
		if (children.get(LIFELINES) == null)
			return Collections.EMPTY_LIST;
		ArrayList elements = (ArrayList)children.get(LIFELINES);
		if (elements.size() == 0)
			return Collections.EMPTY_LIST;
		ArrayList allChildren = new ArrayList();
		for (int i=0; i<elements.size(); i++) {
			allChildren.add(i,elements.get(i));
		}
		if (allChildren.isEmpty())
			return Collections.EMPTY_LIST;
		if (children.get(SEQUENCE) != null) {
			allChildren.add(children.get(SEQUENCE));
		}
		return allChildren;
	}

	public double getZoom() {
		return zoom;
	}

	private void readObject(java.io.ObjectInputStream s)
	throws IOException, ClassNotFoundException {
		s.defaultReadObject();
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}

	public boolean isVisible() {
		return ((ScenarioGroup) getParent()).getSelectedScenario().getId().equals(getId());
	}

	public int getLifeLinesNumber() {
		return ((ArrayList) children.get(LIFELINES)).size();
	}

	public int getSequenceElementsNumber() {
		return ((Sequence)children.get(SEQUENCE)).getSequenceElementNumber();
	}

	/* (non-Javadoc)
	 * @see seg.UCMViewer.model.AbstractModelElement#getXdimension()
	 */
	public int getXdimension() {
		if (getWidth()<0) {
			setWidth(2*DefaultFigureSize.DIAGRAM_PADDING_X + getMaxParallelSequenceWidth());	
		}
		return getWidth();
	}

	/* (non-Javadoc)
	 * @see seg.UCMViewer.model.AbstractModelElement#getYdimension()
	 */
	public int getYdimension() {
		if (getHeight() < 0) {
			int dimY = getLabel().getTextBounds().height + 2*getYSpacing();
			if (children.get(SEQUENCE) == null)
				return dimY;
			if (children.get(LIFELINES) == null || ((ArrayList)children.get(LIFELINES)).isEmpty())
				return dimY;
			LifeLine lfl = (LifeLine)((ArrayList)children.get(LIFELINES)).get(0);
			dimY += lfl.getLifeLineYdimesion();
			setHeight(dimY);
		}
		return getHeight();
	}

	public Sequence getSequence() {
		return (Sequence) children.get(SEQUENCE);
	}

	public int getLifeLinesSpan() {
		int dimX = 0;
		if (children.get(LIFELINES) == null)
			return dimX;
		ArrayList lifelines = (ArrayList)children.get(LIFELINES);
		for (int i = 0; i < lifelines.size(); i++) {
			dimX += ((AbstractModelElement)lifelines.get(i)).getXdimension() + 
			DefaultFigureSize.SPACING_X;
		}
		dimX -= DefaultFigureSize.SPACING_X;
		return dimX;
	}

	private int getMaxParallelSequenceWidth() {
		int width = getLifeLinesSpan();
		Iterator i = getSequence().getViewChildren().iterator();
		while(i.hasNext()) {
			int curWidth = ((AbstractModelElement)i.next()).getXdimension();
			if (curWidth > width)
				width = curWidth;
		}
		return width;
	}

	public void insertLifeLine(int oldNumber, int newNumber) {
		ArrayList lifelines = (ArrayList) children.get(LIFELINES);
		lifelines.add(newNumber,lifelines.remove(oldNumber));
		for (int i=0; i<lifelines.size(); i++) {
			LifeLine l = (LifeLine)lifelines.get(i);
			l.setNumber(i);
			l.invalidateCoordinates();
		}
		firePropertyChange(Properties.ID_INSERT_LIFELINE, null, null);
		fireRefreshEvent();
	}

	public void changeFont(FontData oldFont, FontData newFont) {
		applyFont(newFont.getName(), newFont.getHeight(), newFont.getStyle());
		invalidateDimensions();
		fireRefreshEvent();
	}

	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}

	/* (non-Javadoc)
	 * @see seg.UCMScenarioViewer.model.AbstractModelElement#getLabel
	 */
	public Label getLabel() {
		Label label = super.getLabel();
		label.setFont(UCMScenarioViewer.getLargerApplicationFont());
		return label;
	}

	/**
	 * assigns an icon to this Scenario in the Outline View of Eclipse IDE
	 * 
	 * @see seg.UCMScenarioViewer.utils.Helper
	 * 
	 * @return  image of an icon
	 */
	public Image getIconImage() {
		return SCENARIO_ICON;
	}

	private static Image SCENARIO_ICON = new Image(null,	
			Scenario.class.getResourceAsStream(Helper.ICON_OUTLINE_SCENARIO));  //$NON-NLS-1$


}
