package seg.jUCMNav.scenarios.model;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import urncore.Condition;

public class TraversalWarning {

	private String msg;
	private EObject location;
	private int severity;
	private Condition condition;
	
	public TraversalWarning(String msg) {
		this.msg =msg;
		this.location = null;
		setWarning();
	}
	
	public TraversalWarning(String msg, EObject location)
	{
		this.msg=msg;
		this.location=location;
		setWarning();
	}
	
	public TraversalWarning(String msg, int severity) {
		this.msg =msg;
		this.location = null;
		this.severity = severity;
	}
	
	public TraversalWarning(String msg, EObject location, int severity)
	{
		this.msg=msg;
		this.location=location;
		this.severity = severity;
	}
	

	public EObject getLocation() {
		return location;
	}

	public String getMsg() {
		return msg;
	}
	
	public String toString() {
		return msg;
	}

	public int getSeverity() {
		return severity;
	}
	
	public void setWarning() {
		severity = IMarker.SEVERITY_WARNING;
	}

	public void setError() {
		severity = IMarker.SEVERITY_ERROR;
	}

	public void setInfo() {
		severity = IMarker.SEVERITY_INFO;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	
}
