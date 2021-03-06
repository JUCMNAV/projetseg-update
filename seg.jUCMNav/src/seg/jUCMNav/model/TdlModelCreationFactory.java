package seg.jUCMNav.model;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.requests.CreationFactory;
import org.etsi.mts.tdl.Comment;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TdlFactory;

import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urncore.Metadata;

/**
 * This class implements the CreationFactory to be used as the central point to obtain new model elements. It sets up the default values for all new elements.
 * It in turn uses the EMF-generated factories to create the model instances
 * 
 * Our application will use the static getNewObject methods to access the factories. The palette needs to be passed a CreationFactory; that is the reason of the
 * non-static methods.
 * 
 * Since it would make no sense to provide a URNspec to be able to obtain one, an additional specific method was created for this task: getNewURNspec().
 * 
 * See DevDocModelCreationFactory
 * 
 * @author pboul037
 * 
 */

public class TdlModelCreationFactory implements CreationFactory {
    private Class targetClass;
    private int type;
    private Package tdlPackage;
    //public static final int DEFAULT_UCM_COMPONENT_HEIGHT = 100;
    //public static final int DEFAULT_UCM_COMPONENT_WIDTH = 100;

    //public static final int DEFAULT_GRL_COMPONENT_HEIGHT = 200;
    //public static final int DEFAULT_GRL_COMPONENT_WIDTH = 200;
    //public static final String URNSPEC_VERSION = "0.925"; //$NON-NLS-1$
    
    // distinguish task from feature (must have different value than IntentionalElementType)
    public static final int FEATURE = -1;
    
    //private Object preDefinedDefinition;


    /**
     * @param tdlPackage
     *            The Package which contains information about the last ID created (for unique IDs). Use null if the class does not have an id/name.
     * @param targetClass
     *            The class we need to create from this factory.
     */
    public TdlModelCreationFactory(Package tdlPackage, Class targetClass) {
        this.tdlPackage = tdlPackage;
        this.targetClass = targetClass;
        this.type = 0;
    }
/*
    /**
     * @param tdlPackage
     *            The Package which contains information about the last ID created (for unique IDs). Use null if the class does not have an id/name.
     * @param targetClass
     *            The class we need to create from this factory.
     * @param definition
     *            An instance of IURNContainer if targetClass is IURNContainerRef, or Responsibility if is a RespRef. Instead of creating a new definition for
     *            the reference, it will use the one that is passed in.
     
    public TdlModelCreationFactory(Package tdlPackage, Class targetClass, Object definition) {
        this.tdlPackage = tdlPackage;
        this.targetClass = targetClass;
        this.type = 0;
        this.preDefinedDefinition = definition;
    }

 
    /**
     * @param tdlPackage
     *            The Package which contains information about the last ID created (for unique IDs). Use null if the class does not have an id/name.
     * @param targetClass
     *            The class we need to create from this factory.
     * @param type
     *            If this is a ComponentRef or an IntentionalElementRef or a KPIInformationElement, we can pass the type.
     
    public TdlModelCreationFactory(Package tdlPackage, Class targetClass, int type) {
        this.tdlPackage = tdlPackage;
        this.targetClass = targetClass;
        this.type = type;
    }
    
  

    /**
     * @param tdlPackage
     *            The Package which contains information about the last ID created (for unique IDs). Use null if the class does not have an id/name.
     * @param targetClass
     *            The class we need to create from this factory.
     * @param type
     *            If this is a ComponentRef or an IntentionalElementRef or a KPIInformationElement, we can pass the type.
     * @param definition
     *            An instance of IURNContainer if targetClass is IURNContainerRef, or Responsibility if is a RespRef. Instead of creating a new definition for
     *            the reference, it will use the one that is passed in.
     
    public TdlModelCreationFactory(Package tdlPackage, Class targetClass, int type, Object definition) {
        this.tdlPackage = tdlPackage;
        this.targetClass = targetClass;
        this.type = type;
        this.preDefinedDefinition = definition;
    }
    
    */

    /**
     * @return the target class.
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return targetClass;
    }

    
    
    /**
     * @return the object defined by the constructor parameters
     */
    public Object getNewObject() {
        return getNewObject(tdlPackage, targetClass, type);
    }
    
   
    
    /**
     * check if the metadata Elist contains a specific metadata
     * @param metadataList the metadata list
     * @param metadata the specific metadata
     * @return true if contains, otherwise false
     */
    public static boolean containsMetadata(EList metadataList, Metadata metadata) {
    	boolean result = false;
    	for (int i = 0; i < metadataList.size(); i++)
    	{
    		Metadata m = (Metadata) metadataList.get(i);
    		if ((m.getName().equals(metadata.getName())) && (m.getValue().equals(metadata.getValue()))) {
    			result = true;
    			break;
    		}
    	}
    	return result;
    }

    /**
     * Equivalent to getNewObject(tdlPackage, targetClass, 0);
     * 
     * @param targetClass
     *            the class to obtain a new instance of
     * @return the class to obtain a new instance of
     */
    public static Object getNewObject(Package tdlPackage, Class targetClass) {
        return getNewObject(tdlPackage, targetClass, 0);
    }

    /**
     * Returns a new model element preset with its default values. Note that no exception will be thrown for unknown classes but there will be a message printed
     * on the standard output to facilitate debugging for new developers.
     * 
     * @param tdlPackage
     *            The Package containing the ID seed. Use null if the targetClass does not have an id/name.
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public static Object getNewObject(Package tdlPackage, Class targetClass, int type) {
        return getNewObject(tdlPackage, targetClass, type);
    }

    /* TODO : Cleanup if not used 

    /**
     * Returns a new model element preset with its default values. Note that no exception will be thrown for unknown classes but there will be a message printed
     * on the standard output to facilitate debugging for new developers.
     * 
     * @param urn
     *            The URNspec containing the ID seed. Use null if the targetClass does not have an id/name.
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     
    public static Object getNewObject(Package tdlPackage, Class targetClass, int type, Object definition) {
        TdlFactory tdlFactory = TdlFactory.eINSTANCE;
    	
        MapFactory mapfactory = MapFactory.eINSTANCE;
        UcmFactory ucmfactory = UcmFactory.eINSTANCE;
        ScenarioFactory ucmscenariofactory = ScenarioFactory.eINSTANCE;
        UrncoreFactory urncorefactory = UrncoreFactory.eINSTANCE;
        PerformanceFactory performancefactory = PerformanceFactory.eINSTANCE;
        FmFactory fmfactory = FmFactory.eINSTANCE; 
        UrnFactory urnmainfactory = UrnFactory.eINSTANCE;
        KpimodelFactory kpiFactory = KpimodelFactory.eINSTANCE;
        Object result = null;

        if (targetClass != null) {

            // Simple creations
            if (targetClass.equals(URNspec.class)) {
                result = getNewURNspec();
            } else if (targetClass.equals(UCMspec.class)) {
                result = ucmfactory.createUCMspec();
            } else if (targetClass.equals(URNdefinition.class)) {
                result = urncorefactory.createURNdefinition();
            } else if (targetClass.equals(URNlink.class)) {
                result = urnmainfactory.createURNlink();
            } else if (targetClass.equals(EmptyPoint.class)) {
                result = mapfactory.createEmptyPoint();
            } else if (targetClass.equals(NodeConnection.class)) {
                result = mapfactory.createNodeConnection();
            } else if (targetClass.equals(DirectionArrow.class)) {
                result = mapfactory.createDirectionArrow();
            } else if (targetClass.equals(Responsibility.class)) {
                result = urncorefactory.createResponsibility();
            } else if (targetClass.equals(IntentionalElement.class)) {
                if (type == IntentionalElementType.INDICATOR) {
                    result = kpiFactory.createIndicator();
                }
            } else if (targetClass.equals(Indicator.class)) {
                result = kpiFactory.createIndicator();
            } else if (targetClass.equals(IndicatorGroup.class)) {
                result = kpiFactory.createIndicatorGroup();
            } else if (targetClass.equals(KPIInformationElement.class)) {
                result = kpiFactory.createKPIInformationElement();
            } else if (targetClass.equals(KPIModelLink.class)) {
                result = kpiFactory.createKPIModelLink();
            } else if (targetClass.equals(NodeLabel.class)) {
                result = urncorefactory.createNodeLabel();
            } else if (targetClass.equals(ComponentLabel.class)) {
                result = urncorefactory.createComponentLabel();
            } else if (targetClass.equals(Comment.class)) {
                result = urncorefactory.createComment();
                Comment comment = (Comment) result;
                comment.setWidth(DEFAULT_UCM_COMPONENT_WIDTH);
                comment.setHeight(DEFAULT_UCM_COMPONENT_HEIGHT);
                comment.setFillColor(StringConverter.asString(ColorManager.FILL_COMMENTS.getRGB()));
            } else if (targetClass.equals(OrFork.class)) {
                result = mapfactory.createOrFork();
            } else if (targetClass.equals(AndFork.class)) {
                result = mapfactory.createAndFork();
            } else if (targetClass.equals(OrJoin.class)) {
                result = mapfactory.createOrJoin();
            } else if (targetClass.equals(AndJoin.class)) {
                result = mapfactory.createAndJoin();
            } else if (targetClass.equals(WaitingPlace.class)) {
                result = mapfactory.createWaitingPlace();
            } else if (targetClass.equals(Anything.class)) {
                result = mapfactory.createAnything();
            } else if (targetClass.equals(FailurePoint.class)) {
                result = mapfactory.createFailurePoint();
            } else if (targetClass.equals(Timer.class)) {
                result = mapfactory.createTimer();
            } else if (targetClass.equals(InBinding.class)) {
                result = mapfactory.createInBinding();
            } else if (targetClass.equals(OutBinding.class)) {
                result = mapfactory.createOutBinding();
            } else if (targetClass.equals(Workload.class)) {
                result = performancefactory.createWorkload();
            } else if (targetClass.equals(PassiveResource.class)) { // _js_
                result = performancefactory.createPassiveResource();
            } else if (targetClass.equals(ProcessingResource.class)) { // _js_
                result = performancefactory.createProcessingResource();
            } else if (targetClass.equals(ExternalOperation.class)) { // _js_
                result = performancefactory.createExternalOperation();
            } else if (targetClass.equals(Demand.class)) { // _js_
                result = performancefactory.createDemand();
            } else if (targetClass.equals(Connect.class)) {
                result = mapfactory.createConnect();
            } else if (targetClass.equals(Decomposition.class)) {
            	result = grlfactory.createDecomposition();
            } else if (targetClass.equals(Contribution.class)) {
            	result = grlfactory.createContribution();
            } else if (targetClass.equals(MandatoryFMLink.class)) {
            	result = fmfactory.createMandatoryFMLink();
            } else if (targetClass.equals(OptionalFMLink.class)) {
            	result = fmfactory.createOptionalFMLink();
            } else if (targetClass.equals(Dependency.class)) {
                result = grlfactory.createDependency();
            } else if (targetClass.equals(LinkRef.class)) {
                result = grlfactory.createLinkRef();
            } else if (targetClass.equals(KPIModelLinkRef.class)) {
                result = kpiFactory.createKPIModelLinkRef();
            } else if (targetClass.equals(BeliefLink.class)) {
                result = grlfactory.createBeliefLink();
            } else if (targetClass.equals(LinkRefBendpoint.class)) {
                result = grlfactory.createLinkRefBendpoint();
            } else if (targetClass.equals(StrategiesGroup.class)) {
                result = grlfactory.createStrategiesGroup();
            } else if (targetClass.equals(ContributionContextGroup.class)) {
                result = grlfactory.createContributionContextGroup();
            } else if (targetClass.equals(ContributionContext.class)) {
                if (definition != null)
                    return definition; // drag & drop of existing
                result = grlfactory.createContributionContext();
            } else if (targetClass.equals(ContributionChange.class)) {
                result = grlfactory.createContributionChange();
            } else if (targetClass.equals(EvaluationStrategy.class)) {
                if (definition != null)
                    return definition; // drag & drop of existing
                result = grlfactory.createEvaluationStrategy();
                ((EvaluationStrategy) result).setAuthor(GeneralPreferencePage.getAuthor());
            } else if (targetClass.equals(ScenarioGroup.class)) {
                result = ucmscenariofactory.createScenarioGroup();
            } else if (targetClass.equals(ScenarioDef.class)) {
                if (definition != null)
                    return definition; // drag & drop of existing
                result = ucmscenariofactory.createScenarioDef();
            } else if (targetClass.equals(ScenarioStartPoint.class)) {
                result = ucmscenariofactory.createScenarioStartPoint();
                ((ScenarioStartPoint) result).setEnabled(true);
            } else if (targetClass.equals(ScenarioEndPoint.class)) {
                result = ucmscenariofactory.createScenarioEndPoint();
                ((ScenarioEndPoint) result).setEnabled(true);
                ((ScenarioEndPoint) result).setMandatory(true);
            } else if (targetClass.equals(Evaluation.class)) {
                result = grlfactory.createEvaluation();
                ((Evaluation) result).setKpiEvalValueSet(kpiFactory.createKPIEvalValueSet());
            } else if (targetClass.equals(KPIEvalValueSet.class)) {
                KPIEvalValueSet set = kpiFactory.createKPIEvalValueSet();
                double defaultValue = 0.0;
                set.setTargetValue(defaultValue);
                set.setThresholdValue(defaultValue);
                set.setWorstValue(defaultValue);
                set.setEvaluationValue(defaultValue);
                result = set;

            } else if (targetClass.equals(KPINewEvalValue.class)) {
                result = kpiFactory.createKPINewEvalValue();
            } else if (targetClass.equals(QualitativeMappings.class)) {
                result = kpiFactory.createQualitativeMappings();
            } else if (targetClass.equals(QualitativeMapping.class)) {
                result = kpiFactory.createQualitativeMapping();
                QualitativeMapping mapping = ((QualitativeMapping)result);
                mapping.setRealWorldLabel("Real World Label"); // a default value.
                mapping.setQualitativeEvaluation(QualitativeLabel.UNKNOWN_LITERAL);
            } else if (targetClass.equals(KPIInformationConfig.class)) {
                result = kpiFactory.createKPIInformationConfig();
            } else if (targetClass.equals(EnumerationType.class)) {
                result = ucmscenariofactory.createEnumerationType();
            } else if (targetClass.equals(Variable.class)) {
                result = ucmscenariofactory.createVariable();
                if (definition != null)
                    ((Variable) result).setType(definition.toString());
            } else if (targetClass.equals(Initialization.class)) {
                result = ucmscenariofactory.createInitialization();
            } else if (targetClass.equals(Metadata.class)) {
                result = urncorefactory.createMetadata();
            } else if (targetClass.equals(ComponentBinding.class)) {
                result = mapfactory.createComponentBinding();
            } else if (targetClass.equals(ResponsibilityBinding.class)) {
                result = mapfactory.createResponsibilityBinding();
            } else if (targetClass.equals(Anything.class)) {
                result = mapfactory.createAnything();
            } else if (targetClass.equals(ConnectionLabel.class)) {
                result = urncorefactory.createConnectionLabel();
            } else if (targetClass.equals(EvaluationRange.class)) {
                result = grlfactory.createEvaluationRange();
            } else if (targetClass.equals(ContributionRange.class)) {
                result = grlfactory.createContributionRange();
            } else {
                // complex creations
                if (targetClass.equals(UCMmap.class)) {
                    // create a map
                    result = mapfactory.createUCMmap();
                    URNNamingHelper.setElementNameAndID(urn, result);

                } else if (targetClass.equals(ComponentRef.class)) {
                    // create the component ref
                    result = mapfactory.createComponentRef();

                    // new component refs must have a component definition
                    Component compdef = null;
                    if (definition instanceof Component)
                        compdef = (Component) definition;
                    else
                        compdef = urncorefactory.createComponent();

                    ((ComponentRef) result).setContDef(compdef);

                    // define the ComponentKind according to what was set in the construction
                    compdef.setKind(ComponentKind.get(type));

                    if (definition == null) {
                        URNNamingHelper.setElementNameAndID(urn, compdef);
                        URNNamingHelper.resolveNamingConflict(urn, compdef);
                    }

                    ((ComponentRef) result).setHeight(DEFAULT_UCM_COMPONENT_HEIGHT);
                    ((ComponentRef) result).setWidth(DEFAULT_UCM_COMPONENT_WIDTH);

                    ((ComponentRef) result).setLabel(urncorefactory.createComponentLabel());
                } else if (targetClass.equals(Component.class)) {
                    result = urncorefactory.createComponent();
                    ((Component) result).setKind(ComponentKind.get(type));
                } else if (targetClass.equals(Stub.class)) {
                    // static stub by default
                    result = mapfactory.createStub();
                    Stub stub = (Stub) result;

                    if (type == 1) {
                        // dynamic stub
                        stub.setDynamic(true);
                    } else if (type == 2) {
                        // pointcut stub
                        stub.setDynamic(true);
                        stub.setAopointcut(PointcutKind.REGULAR_LITERAL);
                    } else if (type == 3) {
                        // pointcut replacement
                        stub.setDynamic(true);
                        stub.setAopointcut(PointcutKind.REPLACEMENT_LITERAL);
                    } else if (type == 4) {
                        // Synchronizing Stub
                        stub.setDynamic(true);
                        stub.setSynchronization(true);
                    } else if (type == 5) {
                        // Blocking Stub
                        stub.setDynamic(true);
                        stub.setSynchronization(true);
                        stub.setBlocking(true);
                    } else if (type == 6) {
                        // Aspect Marker
                        stub.setAspect(AspectKind.REGULAR_LITERAL);
                    } else if (type == 7) {
                        // Aspect Marker Entrance
                        stub.setAspect(AspectKind.ENTRANCE_LITERAL);
                    } else if (type == 8) {
                        // Aspect Marker Exit
                        stub.setAspect(AspectKind.EXIT_LITERAL);
                    } else if (type == 9) {
                        // Aspect Marker Conditional
                        stub.setAspect(AspectKind.CONDITIONAL_LITERAL);
                    }
                } else if (targetClass.equals(RespRef.class)) {
                    // should create responsibility definition
                    result = mapfactory.createRespRef();

                    // new component refs must have a component definition
                    Responsibility respdef = null;
                    if (definition instanceof Responsibility)
                        respdef = (Responsibility) definition;
                    else
                        respdef = urncorefactory.createResponsibility();

                    ((RespRef) result).setRespDef(respdef);
                    ((RespRef) result).setRepetitionCount("1"); //$NON-NLS-1$

                    if (definition == null) {
                        URNNamingHelper.setElementNameAndID(urn, respdef);
                        URNNamingHelper.resolveNamingConflict(urn, respdef);
                    }
                } else if (targetClass.equals(Condition.class)) {
                    Condition cond = urncorefactory.createCondition();
                    cond.setExpression("true"); //$NON-NLS-1$
                    cond.setLabel(""); //$NON-NLS-1$
                    result = cond;
                } else if (targetClass.equals(StartPoint.class)) {
                    StartPoint sp = mapfactory.createStartPoint();
                    sp.setPrecondition((Condition) getNewObject(urn, Condition.class));
                    sp.getPrecondition().setDeltaX(40);
                    sp.getPrecondition().setDeltaY(-17);
                    result = sp;
                } else if (targetClass.equals(EndPoint.class)) {
                    EndPoint ep = mapfactory.createEndPoint();
                    ep.setPostcondition((Condition) getNewObject(urn, Condition.class));
                    ep.getPostcondition().setDeltaX(-40);
                    ep.getPostcondition().setDeltaY(-20);
                    result = ep;
                } else if (targetClass.equals(PluginBinding.class)) {
                    PluginBinding plug = mapfactory.createPluginBinding();

                    plug.setPrecondition((Condition) getNewObject(urn, Condition.class));
                    plug.getPrecondition().setExpression("true"); //$NON-NLS-1$

                    result = plug;
                } else if (targetClass.equals(GRLGraph.class)) {
                    // create a graph
                    result = grlfactory.createGRLGraph();
                    URNNamingHelper.setElementNameAndID(urn, result);
                } else if (targetClass.equals(FeatureModel.class)) {
                    // create a diagram
                    result = fmfactory.createFeatureModel();
                    URNNamingHelper.setElementNameAndID(urn, result);
                } else if (targetClass.equals(IntentionalElementRef.class)) {
                    // create the intentional Element ref
                    result = grlfactory.createIntentionalElementRef();

                    IntentionalElement elementdef = null;

                    if (definition instanceof IntentionalElement) {
                        elementdef = (IntentionalElement) definition;
                    } else {
                        if (type == IntentionalElementType.INDICATOR) {
                            elementdef = kpiFactory.createIndicator();
                        } else if (type == FEATURE) {
                        	elementdef = fmfactory.createFeature();
                        } else {
                            elementdef = grlfactory.createIntentionalElement();
                        }
                    }

                    ((IntentionalElementRef) result).setDef(elementdef);
                    // a feature is a subclass of an IntentionalElement of type TASK
                    // since there is no FEATURE type in IntentionalElementType, it needs to be set to TASK
                    if (type == FEATURE)
                    	type = IntentionalElementType.TASK;
                    elementdef.setType(IntentionalElementType.get(type));

                    if (definition == null) {
                        URNNamingHelper.setElementNameAndID(urn, elementdef);
                        URNNamingHelper.resolveNamingConflict(urn, elementdef);
                    }
                } else if (targetClass.equals(KPIInformationElementRef.class)) {
                    // create the KPIModel Element ref
                    result = kpiFactory.createKPIInformationElementRef();

                    KPIInformationElement elementdef = null;

                    if (definition instanceof KPIInformationElement) {
                        elementdef = (KPIInformationElement) definition;
                    } else {
                        elementdef = kpiFactory.createKPIInformationElement();
                    }

                    ((KPIInformationElementRef) result).setDef(elementdef);

                    if (definition == null) {
                        URNNamingHelper.setElementNameAndID(urn, elementdef);
                        URNNamingHelper.resolveNamingConflict(urn, elementdef);
                    }
                } else if (targetClass.equals(ActorRef.class)) {
                    // Create the actor
                    result = grlfactory.createActorRef();

                    Actor actor = null;

                    if (definition instanceof Actor)
                        actor = (Actor) definition;
                    else
                        actor = grlfactory.createActor();

                    ((ActorRef) result).setContDef(actor);

                    if (definition == null) {
                        URNNamingHelper.setElementNameAndID(urn, actor);
                        URNNamingHelper.resolveNamingConflict(urn, actor);
                    }

                    ((ActorRef) result).setHeight(DEFAULT_GRL_COMPONENT_HEIGHT);
                    ((ActorRef) result).setWidth(DEFAULT_GRL_COMPONENT_WIDTH);

                    ((ActorRef) result).setLabel(urncorefactory.createComponentLabel());
                } else if (targetClass.equals(Actor.class)) {
                    result = grlfactory.createActor();
                } else if (targetClass.equals(Belief.class)) {
                    // Create the belief
                    result = grlfactory.createBelief();
                    URNNamingHelper.setElementNameAndID(urn, result);

                    // Set the author to the author specify in the preferences
                    ((Belief) result).setAuthor(GeneralPreferencePage.getAuthor());
                    // New belief description should be set to "" by default (because we use it in the description
                    ((Belief) result).setDescription(""); //$NON-NLS-1$
                } else if (targetClass.equals(Concern.class)) {
                    // create a concern
                    result = urncorefactory.createConcern();
                    URNNamingHelper.setElementNameAndID(urn, result);
                } else {
                    System.out.println("Unknown class passed to ModelCreationFactory"); //$NON-NLS-1$
                }
            }
        }

        // add labels automatically to the required pathnodes.
        if (result instanceof StartPoint || result instanceof EndPoint || result instanceof Stub || result instanceof RespRef || result instanceof WaitingPlace
                || result instanceof Timer || result instanceof FailurePoint || result instanceof Anything) {
            ((IURNNode) result).setLabel(urncorefactory.createNodeLabel());
        }

        // set the name and id of model elements
        // doesn't verify unique names.
        if (result instanceof UCMmodelElement || result instanceof GRLmodelElement) {
            URNNamingHelper.setElementNameAndID(urn, result);
        }

        // verify unique names
        if (result instanceof Responsibility || result instanceof Component || result instanceof Variable) {
            URNNamingHelper.resolveNamingConflict(urn, (UCMmodelElement) result);
        }

        return result;

    }
     */
    
    /**
     * 
     * Creates a new Package.
     *
     * @return a new Package
     */
    public static Package getNewTdlPackage(String scenarioName) {
    	    	
    	Package result = null;

        // create the Package
    	Package tdlPackage = TdlFactory.eINSTANCE.createPackage();

    	//old version for URN spec to have an example to work on for the next TODO
        // name the Package
    	//tdlPackage.setName(URNNamingHelper.getPrefix(URNspec.class));


    	tdlPackage.setName(scenarioName);
    	
    	//No equivalent in TdlPackage
        // seed the global id
        //urnspec.setNextGlobalID("1"); //$NON-NLS-1$

        
        String sDate;
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
        sDate = df.format(new Date());
        
        Comment tdlPackageCreated = tdlPackage.createComment();
        tdlPackageCreated.setName("Created");
        tdlPackageCreated.setBody(sDate);
        
        Comment tdlPackageModified = tdlPackage.createComment();
        tdlPackageModified.setName("Modified");
        tdlPackageModified.setBody(sDate);

        //Not available in TdlPackage?
        //urnspec.setUrnVersion(URNSPEC_VERSION); //$NON-NLS-1$

        // Set the author to the current user
        Comment tdlPackageAuthor = tdlPackage.createComment();
        tdlPackageAuthor.setName("Author");
        tdlPackageAuthor.setBody(GeneralPreferencePage.getAuthor());

        // Not available in TdlPackage?
        // add its URN definition
        // urnspec.setUrndef(UrncoreFactory.eINSTANCE.createURNdefinition());

        /* TODO: Not sure of the equivalent here for TdlPackage
        // Create a Scenario and ScenarioGroup
        ScenarioGroup scenariogroup = (ScenarioGroup) TdlModelCreationFactory.getNewObject(urnspec, ScenarioGroup.class);
        urnspec.getUcmspec().getScenarioGroups().add(scenariogroup);
        ScenarioDef scenario = (ScenarioDef) TdlModelCreationFactory.getNewObject(urnspec, ScenarioDef.class);
        scenariogroup.getScenarios().add(scenario);

        // Create the default IndicatorGroups
        IndicatorGroup indicatorGroup = (IndicatorGroup) TdlModelCreationFactory.getNewObject(urnspec, IndicatorGroup.class);
        indicatorGroup.setName(Messages.getString("InitialIndicatorGroup.time")); //$NON-NLS-1$
        indicatorGroup.setIsRedesignCategory(true);
        urnspec.getGrlspec().getIndicatorGroup().add(indicatorGroup);

        indicatorGroup = (IndicatorGroup) TdlModelCreationFactory.getNewObject(urnspec, IndicatorGroup.class);
        indicatorGroup.setName(Messages.getString("InitialIndicatorGroup.cost")); //$NON-NLS-1$
        indicatorGroup.setIsRedesignCategory(true);
        urnspec.getGrlspec().getIndicatorGroup().add(indicatorGroup);

        indicatorGroup = (IndicatorGroup) TdlModelCreationFactory.getNewObject(urnspec, IndicatorGroup.class);
        indicatorGroup.setName(Messages.getString("InitialIndicatorGroup.quality")); //$NON-NLS-1$
        indicatorGroup.setIsRedesignCategory(true);
        urnspec.getGrlspec().getIndicatorGroup().add(indicatorGroup);

        indicatorGroup = (IndicatorGroup) TdlModelCreationFactory.getNewObject(urnspec, IndicatorGroup.class);
        indicatorGroup.setName(Messages.getString("InitialIndicatorGroup.flexibility")); //$NON-NLS-1$
        indicatorGroup.setIsRedesignCategory(true);
        urnspec.getGrlspec().getIndicatorGroup().add(indicatorGroup);

        // set default value.
        StrategyEvaluationRangeHelper.setCurrentRange(urnspec, StrategyEvaluationPreferences.getVisualizeAsPositiveRange(null));

		*/

        result = tdlPackage;
        return result;
    }

}
