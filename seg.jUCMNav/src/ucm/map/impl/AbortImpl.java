/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.Abort;
import ucm.map.ComponentRef;
import ucm.map.Condition;
import ucm.map.MapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abort</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.AbortImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbortImpl extends PathNodeImpl implements Abort {
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected EList condition = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MapPackage.eINSTANCE.getAbort();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCondition() {
		if (condition == null) {
			condition = new EObjectResolvingEList(Condition.class, this, MapPackage.ABORT__CONDITION);
		}
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.ABORT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case MapPackage.ABORT__SUCC:
					return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
				case MapPackage.ABORT__PRED:
					return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
				case MapPackage.ABORT__COMP_REF:
					if (compRef != null)
						msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
					return basicSetCompRef((ComponentRef)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.ABORT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case MapPackage.ABORT__SUCC:
					return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
				case MapPackage.ABORT__PRED:
					return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
				case MapPackage.ABORT__COMP_REF:
					return basicSetCompRef(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.ABORT__URN_LINKS:
				return getUrnLinks();
			case MapPackage.ABORT__ID:
				return getId();
			case MapPackage.ABORT__NAME:
				return getName();
			case MapPackage.ABORT__DESCRIPTION:
				return getDescription();
			case MapPackage.ABORT__X:
				return new Integer(getX());
			case MapPackage.ABORT__Y:
				return new Integer(getY());
			case MapPackage.ABORT__LABEL_X:
				return new Integer(getLabelX());
			case MapPackage.ABORT__LABEL_Y:
				return new Integer(getLabelY());
			case MapPackage.ABORT__SUCC:
				return getSucc();
			case MapPackage.ABORT__PRED:
				return getPred();
			case MapPackage.ABORT__COMP_REF:
				if (resolve) return getCompRef();
				return basicGetCompRef();
			case MapPackage.ABORT__CONDITION:
				return getCondition();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.ABORT__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case MapPackage.ABORT__ID:
				setId((String)newValue);
				return;
			case MapPackage.ABORT__NAME:
				setName((String)newValue);
				return;
			case MapPackage.ABORT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MapPackage.ABORT__X:
				setX(((Integer)newValue).intValue());
				return;
			case MapPackage.ABORT__Y:
				setY(((Integer)newValue).intValue());
				return;
			case MapPackage.ABORT__LABEL_X:
				setLabelX(((Integer)newValue).intValue());
				return;
			case MapPackage.ABORT__LABEL_Y:
				setLabelY(((Integer)newValue).intValue());
				return;
			case MapPackage.ABORT__SUCC:
				getSucc().clear();
				getSucc().addAll((Collection)newValue);
				return;
			case MapPackage.ABORT__PRED:
				getPred().clear();
				getPred().addAll((Collection)newValue);
				return;
			case MapPackage.ABORT__COMP_REF:
				setCompRef((ComponentRef)newValue);
				return;
			case MapPackage.ABORT__CONDITION:
				getCondition().clear();
				getCondition().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.ABORT__URN_LINKS:
				getUrnLinks().clear();
				return;
			case MapPackage.ABORT__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.ABORT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MapPackage.ABORT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MapPackage.ABORT__X:
				setX(X_EDEFAULT);
				return;
			case MapPackage.ABORT__Y:
				setY(Y_EDEFAULT);
				return;
			case MapPackage.ABORT__LABEL_X:
				setLabelX(LABEL_X_EDEFAULT);
				return;
			case MapPackage.ABORT__LABEL_Y:
				setLabelY(LABEL_Y_EDEFAULT);
				return;
			case MapPackage.ABORT__SUCC:
				getSucc().clear();
				return;
			case MapPackage.ABORT__PRED:
				getPred().clear();
				return;
			case MapPackage.ABORT__COMP_REF:
				setCompRef((ComponentRef)null);
				return;
			case MapPackage.ABORT__CONDITION:
				getCondition().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.ABORT__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case MapPackage.ABORT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.ABORT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MapPackage.ABORT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MapPackage.ABORT__X:
				return x != X_EDEFAULT;
			case MapPackage.ABORT__Y:
				return y != Y_EDEFAULT;
			case MapPackage.ABORT__LABEL_X:
				return labelX != LABEL_X_EDEFAULT;
			case MapPackage.ABORT__LABEL_Y:
				return labelY != LABEL_Y_EDEFAULT;
			case MapPackage.ABORT__SUCC:
				return succ != null && !succ.isEmpty();
			case MapPackage.ABORT__PRED:
				return pred != null && !pred.isEmpty();
			case MapPackage.ABORT__COMP_REF:
				return compRef != null;
			case MapPackage.ABORT__CONDITION:
				return condition != null && !condition.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //AbortImpl
