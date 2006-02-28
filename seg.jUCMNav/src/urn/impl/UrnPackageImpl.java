/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn.impl;

import grl.GrlPackage;

import grl.impl.GrlPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import ucm.UcmPackage;

import ucm.impl.UcmPackageImpl;

import ucm.map.MapPackage;

import ucm.map.impl.MapPackageImpl;

import ucm.performance.PerformancePackage;

import ucm.performance.impl.PerformancePackageImpl;

import ucm.scenario.ScenarioPackage;

import ucm.scenario.impl.ScenarioPackageImpl;

import urn.URNLinkType;
import urn.URNlink;
import urn.URNspec;
import urn.UrnFactory;
import urn.UrnPackage;

import urncore.UrncorePackage;

import urncore.impl.UrncorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UrnPackageImpl extends EPackageImpl implements UrnPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass urNspecEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass urNlinkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum urnLinkTypeEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see urn.UrnPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private UrnPackageImpl() {
        super(eNS_URI, UrnFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static UrnPackage init() {
        if (isInited) return (UrnPackage)EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI);

        // Obtain or create and register package
        UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new UrnPackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackage.eINSTANCE);
        GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) : GrlPackage.eINSTANCE);
        UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
        PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
        MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
        ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);

        // Create package meta-data objects
        theUrnPackage.createPackageContents();
        theUrncorePackage.createPackageContents();
        theGrlPackage.createPackageContents();
        theUcmPackage.createPackageContents();
        thePerformancePackage.createPackageContents();
        theMapPackage.createPackageContents();
        theScenarioPackage.createPackageContents();

        // Initialize created meta-data
        theUrnPackage.initializePackageContents();
        theUrncorePackage.initializePackageContents();
        theGrlPackage.initializePackageContents();
        theUcmPackage.initializePackageContents();
        thePerformancePackage.initializePackageContents();
        theMapPackage.initializePackageContents();
        theScenarioPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theUrnPackage.freeze();

        return theUrnPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getURNspec() {
        return urNspecEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNspec_Name() {
        return (EAttribute)urNspecEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNspec_Description() {
        return (EAttribute)urNspecEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNspec_Author() {
        return (EAttribute)urNspecEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNspec_Created() {
        return (EAttribute)urNspecEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNspec_Modified() {
        return (EAttribute)urNspecEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNspec_SpecVersion() {
        return (EAttribute)urNspecEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNspec_UrnVersion() {
        return (EAttribute)urNspecEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNspec_NextGlobalID() {
        return (EAttribute)urNspecEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getURNspec_Ucmspec() {
        return (EReference)urNspecEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getURNspec_Grlspec() {
        return (EReference)urNspecEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getURNspec_Urndef() {
        return (EReference)urNspecEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getURNspec_UrnLinks() {
        return (EReference)urNspecEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getURNlink() {
        return urNlinkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getURNlink_Urnspec() {
        return (EReference)urNlinkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getURNlink_GrlModelElements() {
        return (EReference)urNlinkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getURNlink_UcmModelElements() {
        return (EReference)urNlinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getURNLinkType() {
        return urnLinkTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UrnFactory getUrnFactory() {
        return (UrnFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        urNspecEClass = createEClass(UR_NSPEC);
        createEAttribute(urNspecEClass, UR_NSPEC__NAME);
        createEAttribute(urNspecEClass, UR_NSPEC__DESCRIPTION);
        createEAttribute(urNspecEClass, UR_NSPEC__AUTHOR);
        createEAttribute(urNspecEClass, UR_NSPEC__CREATED);
        createEAttribute(urNspecEClass, UR_NSPEC__MODIFIED);
        createEAttribute(urNspecEClass, UR_NSPEC__SPEC_VERSION);
        createEAttribute(urNspecEClass, UR_NSPEC__URN_VERSION);
        createEAttribute(urNspecEClass, UR_NSPEC__NEXT_GLOBAL_ID);
        createEReference(urNspecEClass, UR_NSPEC__UCMSPEC);
        createEReference(urNspecEClass, UR_NSPEC__GRLSPEC);
        createEReference(urNspecEClass, UR_NSPEC__URNDEF);
        createEReference(urNspecEClass, UR_NSPEC__URN_LINKS);

        urNlinkEClass = createEClass(UR_NLINK);
        createEReference(urNlinkEClass, UR_NLINK__URNSPEC);
        createEReference(urNlinkEClass, UR_NLINK__GRL_MODEL_ELEMENTS);
        createEReference(urNlinkEClass, UR_NLINK__UCM_MODEL_ELEMENTS);

        // Create enums
        urnLinkTypeEEnum = createEEnum(URN_LINK_TYPE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        UcmPackageImpl theUcmPackage = (UcmPackageImpl)EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI);
        GrlPackageImpl theGrlPackage = (GrlPackageImpl)EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI);
        UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI);

        // Add supertypes to classes

        // Initialize classes and features; add operations and parameters
        initEClass(urNspecEClass, URNspec.class, "URNspec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getURNspec_Name(), ecorePackage.getEString(), "name", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNspec_Description(), ecorePackage.getEString(), "description", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNspec_Author(), ecorePackage.getEString(), "author", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNspec_Created(), ecorePackage.getEString(), "created", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNspec_Modified(), ecorePackage.getEString(), "modified", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNspec_SpecVersion(), ecorePackage.getEString(), "specVersion", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNspec_UrnVersion(), ecorePackage.getEString(), "urnVersion", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNspec_NextGlobalID(), ecorePackage.getEString(), "nextGlobalID", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNspec_Ucmspec(), theUcmPackage.getUCMspec(), theUcmPackage.getUCMspec_Urnspec(), "ucmspec", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNspec_Grlspec(), theGrlPackage.getGRLspec(), theGrlPackage.getGRLspec_Urnspec(), "grlspec", null, 0, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNspec_Urndef(), theUrncorePackage.getURNdefinition(), theUrncorePackage.getURNdefinition_Urnspec(), "urndef", null, 1, 1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNspec_UrnLinks(), this.getURNlink(), this.getURNlink_Urnspec(), "urnLinks", null, 0, -1, URNspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(urNlinkEClass, URNlink.class, "URNlink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getURNlink_Urnspec(), this.getURNspec(), this.getURNspec_UrnLinks(), "urnspec", null, 1, 1, URNlink.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNlink_GrlModelElements(), theUrncorePackage.getGRLmodelElement(), theUrncorePackage.getGRLmodelElement_Urnlinks(), "grlModelElements", null, 1, 1, URNlink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNlink_UcmModelElements(), theUrncorePackage.getUCMmodelElement(), theUrncorePackage.getUCMmodelElement_Urnlinks(), "ucmModelElements", null, 1, 1, URNlink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(urnLinkTypeEEnum, URNLinkType.class, "URNLinkType");
        addEEnumLiteral(urnLinkTypeEEnum, URNLinkType.STRATEGY_SCENARIO_LITERAL);
        addEEnumLiteral(urnLinkTypeEEnum, URNLinkType.AUTHOR_COMPONENT_LITERAL);
        addEEnumLiteral(urnLinkTypeEEnum, URNLinkType.ELEMENT_VARIABLE_LITERAL);
        addEEnumLiteral(urnLinkTypeEEnum, URNLinkType.ELEMENT_STUB_LITERAL);
        addEEnumLiteral(urnLinkTypeEEnum, URNLinkType.ELEMENT_RESPONSIBILITY_LITERAL);

        // Create resource
        createResource(eNS_URI);
    }

} //UrnPackageImpl
