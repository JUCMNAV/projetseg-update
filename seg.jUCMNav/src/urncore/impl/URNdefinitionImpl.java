/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.ComponentElement;
import urncore.Responsibility;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UR Ndefinition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getResponsibilities <em>Responsibilities</em>}</li>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URNdefinitionImpl extends EObjectImpl implements URNdefinition {
	/**
	 * The cached value of the '{@link #getResponsibilities() <em>Responsibilities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsibilities()
	 * @generated
	 * @ordered
	 */
	protected EList responsibilities = null;

	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList components = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected URNdefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UrncorePackage.eINSTANCE.getURNdefinition();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResponsibilities() {
		if (responsibilities == null) {
			responsibilities = new EObjectContainmentEList(Responsibility.class, this, UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES);
		}
		return responsibilities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComponents() {
		if (components == null) {
			components = new EObjectContainmentEList(ComponentElement.class, this, UrncorePackage.UR_NDEFINITION__COMPONENTS);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
					return ((InternalEList)getResponsibilities()).basicRemove(otherEnd, msgs);
				case UrncorePackage.UR_NDEFINITION__COMPONENTS:
					return ((InternalEList)getComponents()).basicRemove(otherEnd, msgs);
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
			case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
				return getResponsibilities();
			case UrncorePackage.UR_NDEFINITION__COMPONENTS:
				return getComponents();
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
			case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
				getResponsibilities().clear();
				getResponsibilities().addAll((Collection)newValue);
				return;
			case UrncorePackage.UR_NDEFINITION__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection)newValue);
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
			case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
				getResponsibilities().clear();
				return;
			case UrncorePackage.UR_NDEFINITION__COMPONENTS:
				getComponents().clear();
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
			case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
				return responsibilities != null && !responsibilities.isEmpty();
			case UrncorePackage.UR_NDEFINITION__COMPONENTS:
				return components != null && !components.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //URNdefinitionImpl
