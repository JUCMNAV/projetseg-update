//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.9-03/31/2009 04:14 PM(snajper)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.07.19 at 07:21:12 PM EDT 
//


package seg.jUCMNav.importexport.z151.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _URNspec_QNAME = new QName("", "URNspec");
    private final static QName _ComponentTypeInstances_QNAME = new QName("", "instances");
    private final static QName _ConcernElements_QNAME = new QName("", "elements");
    private final static QName _ElementLinkRefs_QNAME = new QName("", "refs");
    private final static QName _ActorRefNodes_QNAME = new QName("", "nodes");
    private final static QName _GRLLinkableElementLinksSrc_QNAME = new QName("", "linksSrc");
    private final static QName _GRLLinkableElementLinksDest_QNAME = new QName("", "linksDest");
    private final static QName _URNmodelElementFromLinks_QNAME = new QName("", "fromLinks");
    private final static QName _URNmodelElementToLinks_QNAME = new QName("", "toLinks");
    private final static QName _ProcessingResourceComponents_QNAME = new QName("", "components");
    private final static QName _GRLNodeSucc_QNAME = new QName("", "succ");
    private final static QName _GRLNodePred_QNAME = new QName("", "pred");
    private final static QName _ComponentIncludingComponents_QNAME = new QName("", "includingComponents");
    private final static QName _ComponentCompRefs_QNAME = new QName("", "compRefs");
    private final static QName _ComponentIncludedComponents_QNAME = new QName("", "includedComponents");
    private final static QName _ScenarioGroupScenarios_QNAME = new QName("", "scenarios");
    private final static QName _StartPointInBindings_QNAME = new QName("", "inBindings");
    private final static QName _EndPointOutBindings_QNAME = new QName("", "outBindings");
    private final static QName _EvaluationStrategyGroup_QNAME = new QName("", "group");
    private final static QName _ScenarioDefEndPoints_QNAME = new QName("", "endPoints");
    private final static QName _ScenarioDefGroups_QNAME = new QName("", "groups");
    private final static QName _ScenarioDefParentScenarios_QNAME = new QName("", "parentScenarios");
    private final static QName _UCMmapParentStub_QNAME = new QName("", "parentStub");
    private final static QName _ResponsibilityRespRefs_QNAME = new QName("", "respRefs");
    private final static QName _ComponentRefPluginBindings_QNAME = new QName("", "pluginBindings");
    private final static QName _ComponentRefChildren_QNAME = new QName("", "children");
    private final static QName _ComponentRefParentBindings_QNAME = new QName("", "parentBindings");
    private final static QName _ExternalOperationDemands_QNAME = new QName("", "demands");
    private final static QName _StrategiesGroupStrategies_QNAME = new QName("", "strategies");
    private final static QName _ActorElems_QNAME = new QName("", "elems");
    private final static QName _ActorActorRefs_QNAME = new QName("", "actorRefs");
    private final static QName _ActorCollapsedRefs_QNAME = new QName("", "collapsedRefs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WaitingPlace }
     * 
     */
    public WaitingPlace createWaitingPlace() {
        return new WaitingPlace();
    }

    /**
     * Create an instance of {@link ConcreteStyle }
     * 
     */
    public ConcreteStyle createConcreteStyle() {
        return new ConcreteStyle();
    }

    /**
     * Create an instance of {@link OWPoisson }
     * 
     */
    public OWPoisson createOWPoisson() {
        return new OWPoisson();
    }

    /**
     * Create an instance of {@link PassiveResource }
     * 
     */
    public PassiveResource createPassiveResource() {
        return new PassiveResource();
    }

    /**
     * Create an instance of {@link RespRef }
     * 
     */
    public RespRef createRespRef() {
        return new RespRef();
    }

    /**
     * Create an instance of {@link GeneralResource }
     * 
     */
    public GeneralResource createGeneralResource() {
        return new GeneralResource();
    }

    /**
     * Create an instance of {@link Evaluation }
     * 
     */
    public Evaluation createEvaluation() {
        return new Evaluation();
    }

    /**
     * Create an instance of {@link EnumerationType }
     * 
     */
    public EnumerationType createEnumerationType() {
        return new EnumerationType();
    }

    /**
     * Create an instance of {@link ActorRef }
     * 
     */
    public ActorRef createActorRef() {
        return new ActorRef();
    }

    /**
     * Create an instance of {@link InBinding }
     * 
     */
    public InBinding createInBinding() {
        return new InBinding();
    }

    /**
     * Create an instance of {@link ProcessingResource }
     * 
     */
    public ProcessingResource createProcessingResource() {
        return new ProcessingResource();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link Timer }
     * 
     */
    public Timer createTimer() {
        return new Timer();
    }

    /**
     * Create an instance of {@link Dependency }
     * 
     */
    public Dependency createDependency() {
        return new Dependency();
    }

    /**
     * Create an instance of {@link Stub }
     * 
     */
    public Stub createStub() {
        return new Stub();
    }

    /**
     * Create an instance of {@link Component }
     * 
     */
    public Component createComponent() {
        return new Component();
    }

    /**
     * Create an instance of {@link PluginBinding }
     * 
     */
    public PluginBinding createPluginBinding() {
        return new PluginBinding();
    }

    /**
     * Create an instance of {@link ComponentBinding }
     * 
     */
    public ComponentBinding createComponentBinding() {
        return new ComponentBinding();
    }

    /**
     * Create an instance of {@link GRLGraph }
     * 
     */
    public GRLGraph createGRLGraph() {
        return new GRLGraph();
    }

    /**
     * Create an instance of {@link ClosedWorkload }
     * 
     */
    public ClosedWorkload createClosedWorkload() {
        return new ClosedWorkload();
    }

    /**
     * Create an instance of {@link EmptyPoint }
     * 
     */
    public EmptyPoint createEmptyPoint() {
        return new EmptyPoint();
    }

    /**
     * Create an instance of {@link ScenarioGroup }
     * 
     */
    public ScenarioGroup createScenarioGroup() {
        return new ScenarioGroup();
    }

    /**
     * Create an instance of {@link OWPeriodic }
     * 
     */
    public OWPeriodic createOWPeriodic() {
        return new OWPeriodic();
    }

    /**
     * Create an instance of {@link DirectionArrow }
     * 
     */
    public DirectionArrow createDirectionArrow() {
        return new DirectionArrow();
    }

    /**
     * Create an instance of {@link EvaluationStrategy }
     * 
     */
    public EvaluationStrategy createEvaluationStrategy() {
        return new EvaluationStrategy();
    }

    /**
     * Create an instance of {@link GRLmodelElement }
     * 
     */
    public GRLmodelElement createGRLmodelElement() {
        return new GRLmodelElement();
    }

    /**
     * Create an instance of {@link LinkRef }
     * 
     */
    public LinkRef createLinkRef() {
        return new LinkRef();
    }

    /**
     * Create an instance of {@link NodeConnection }
     * 
     */
    public NodeConnection createNodeConnection() {
        return new NodeConnection();
    }

    /**
     * Create an instance of {@link Initialization }
     * 
     */
    public Initialization createInitialization() {
        return new Initialization();
    }

    /**
     * Create an instance of {@link ComponentRef }
     * 
     */
    public ComponentRef createComponentRef() {
        return new ComponentRef();
    }

    /**
     * Create an instance of {@link Description }
     * 
     */
    public Description createDescription() {
        return new Description();
    }

    /**
     * Create an instance of {@link UCMmodelElement }
     * 
     */
    public UCMmodelElement createUCMmodelElement() {
        return new UCMmodelElement();
    }

    /**
     * Create an instance of {@link Size }
     * 
     */
    public Size createSize() {
        return new Size();
    }

    /**
     * Create an instance of {@link ConcreteGRLspec }
     * 
     */
    public ConcreteGRLspec createConcreteGRLspec() {
        return new ConcreteGRLspec();
    }

    /**
     * Create an instance of {@link LinkRefBendpoint }
     * 
     */
    public LinkRefBendpoint createLinkRefBendpoint() {
        return new LinkRefBendpoint();
    }

    /**
     * Create an instance of {@link StrategiesGroup }
     * 
     */
    public StrategiesGroup createStrategiesGroup() {
        return new StrategiesGroup();
    }

    /**
     * Create an instance of {@link Actor }
     * 
     */
    public Actor createActor() {
        return new Actor();
    }

    /**
     * Create an instance of {@link Connect }
     * 
     */
    public Connect createConnect() {
        return new Connect();
    }

    /**
     * Create an instance of {@link AndJoin }
     * 
     */
    public AndJoin createAndJoin() {
        return new AndJoin();
    }

    /**
     * Create an instance of {@link Concern }
     * 
     */
    public Concern createConcern() {
        return new Concern();
    }

    /**
     * Create an instance of {@link ComponentType }
     * 
     */
    public ComponentType createComponentType() {
        return new ComponentType();
    }

    /**
     * Create an instance of {@link Decomposition }
     * 
     */
    public Decomposition createDecomposition() {
        return new Decomposition();
    }

    /**
     * Create an instance of {@link ElementLink }
     * 
     */
    public ElementLink createElementLink() {
        return new ElementLink();
    }

    /**
     * Create an instance of {@link Metadata }
     * 
     */
    public Metadata createMetadata() {
        return new Metadata();
    }

    /**
     * Create an instance of {@link OWUniform }
     * 
     */
    public OWUniform createOWUniform() {
        return new OWUniform();
    }

    /**
     * Create an instance of {@link GRLLinkableElement }
     * 
     */
    public GRLLinkableElement createGRLLinkableElement() {
        return new GRLLinkableElement();
    }

    /**
     * Create an instance of {@link Position }
     * 
     */
    public Position createPosition() {
        return new Position();
    }

    /**
     * Create an instance of {@link ConcreteCondition }
     * 
     */
    public ConcreteCondition createConcreteCondition() {
        return new ConcreteCondition();
    }

    /**
     * Create an instance of {@link OWPhaseType }
     * 
     */
    public OWPhaseType createOWPhaseType() {
        return new OWPhaseType();
    }

    /**
     * Create an instance of {@link URNmodelElement }
     * 
     */
    public URNmodelElement createURNmodelElement() {
        return new URNmodelElement();
    }

    /**
     * Create an instance of {@link OpenWorkload }
     * 
     */
    public OpenWorkload createOpenWorkload() {
        return new OpenWorkload();
    }

    /**
     * Create an instance of {@link ActiveResource }
     * 
     */
    public ActiveResource createActiveResource() {
        return new ActiveResource();
    }

    /**
     * Create an instance of {@link IntentionalElementRef }
     * 
     */
    public IntentionalElementRef createIntentionalElementRef() {
        return new IntentionalElementRef();
    }

    /**
     * Create an instance of {@link URNspec }
     * 
     */
    public URNspec createURNspec() {
        return new URNspec();
    }

    /**
     * Create an instance of {@link Demand }
     * 
     */
    public Demand createDemand() {
        return new Demand();
    }

    /**
     * Create an instance of {@link URNlink }
     * 
     */
    public URNlink createURNlink() {
        return new URNlink();
    }

    /**
     * Create an instance of {@link GRLNode }
     * 
     */
    public GRLNode createGRLNode() {
        return new GRLNode();
    }

    /**
     * Create an instance of {@link Label }
     * 
     */
    public Label createLabel() {
        return new Label();
    }

    /**
     * Create an instance of {@link IntentionalElement }
     * 
     */
    public IntentionalElement createIntentionalElement() {
        return new IntentionalElement();
    }

    /**
     * Create an instance of {@link UCMspec }
     * 
     */
    public UCMspec createUCMspec() {
        return new UCMspec();
    }

    /**
     * Create an instance of {@link Workload }
     * 
     */
    public Workload createWorkload() {
        return new Workload();
    }

    /**
     * Create an instance of {@link OrJoin }
     * 
     */
    public OrJoin createOrJoin() {
        return new OrJoin();
    }

    /**
     * Create an instance of {@link PathNode }
     * 
     */
    public PathNode createPathNode() {
        return new PathNode();
    }

    /**
     * Create an instance of {@link OrFork }
     * 
     */
    public OrFork createOrFork() {
        return new OrFork();
    }

    /**
     * Create an instance of {@link StartPoint }
     * 
     */
    public StartPoint createStartPoint() {
        return new StartPoint();
    }

    /**
     * Create an instance of {@link CollapsedActorRef }
     * 
     */
    public CollapsedActorRef createCollapsedActorRef() {
        return new CollapsedActorRef();
    }

    /**
     * Create an instance of {@link EndPoint }
     * 
     */
    public EndPoint createEndPoint() {
        return new EndPoint();
    }

    /**
     * Create an instance of {@link ScenarioDef }
     * 
     */
    public ScenarioDef createScenarioDef() {
        return new ScenarioDef();
    }

    /**
     * Create an instance of {@link Contribution }
     * 
     */
    public Contribution createContribution() {
        return new Contribution();
    }

    /**
     * Create an instance of {@link UCMmap }
     * 
     */
    public UCMmap createUCMmap() {
        return new UCMmap();
    }

    /**
     * Create an instance of {@link ConcreteURNspec }
     * 
     */
    public ConcreteURNspec createConcreteURNspec() {
        return new ConcreteURNspec();
    }

    /**
     * Create an instance of {@link OutBinding }
     * 
     */
    public OutBinding createOutBinding() {
        return new OutBinding();
    }

    /**
     * Create an instance of {@link GRLspec }
     * 
     */
    public GRLspec createGRLspec() {
        return new GRLspec();
    }

    /**
     * Create an instance of {@link Responsibility }
     * 
     */
    public Responsibility createResponsibility() {
        return new Responsibility();
    }

    /**
     * Create an instance of {@link Condition }
     * 
     */
    public Condition createCondition() {
        return new Condition();
    }

    /**
     * Create an instance of {@link Variable }
     * 
     */
    public Variable createVariable() {
        return new Variable();
    }

    /**
     * Create an instance of {@link ExternalOperation }
     * 
     */
    public ExternalOperation createExternalOperation() {
        return new ExternalOperation();
    }

    /**
     * Create an instance of {@link AndFork }
     * 
     */
    public AndFork createAndFork() {
        return new AndFork();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link URNspec }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "URNspec")
    public JAXBElement<URNspec> createURNspec(URNspec value) {
        return new JAXBElement<URNspec>(_URNspec_QNAME, URNspec.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "instances", scope = ComponentType.class)
    @XmlIDREF
    public JAXBElement<Object> createComponentTypeInstances(Object value) {
        return new JAXBElement<Object>(_ComponentTypeInstances_QNAME, Object.class, ComponentType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "elements", scope = Concern.class)
    @XmlIDREF
    public JAXBElement<Object> createConcernElements(Object value) {
        return new JAXBElement<Object>(_ConcernElements_QNAME, Object.class, Concern.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "refs", scope = ElementLink.class)
    @XmlIDREF
    public JAXBElement<Object> createElementLinkRefs(Object value) {
        return new JAXBElement<Object>(_ElementLinkRefs_QNAME, Object.class, ElementLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "nodes", scope = ActorRef.class)
    @XmlIDREF
    public JAXBElement<Object> createActorRefNodes(Object value) {
        return new JAXBElement<Object>(_ActorRefNodes_QNAME, Object.class, ActorRef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "instances", scope = EnumerationType.class)
    @XmlIDREF
    public JAXBElement<Object> createEnumerationTypeInstances(Object value) {
        return new JAXBElement<Object>(_ComponentTypeInstances_QNAME, Object.class, EnumerationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "linksSrc", scope = GRLLinkableElement.class)
    @XmlIDREF
    public JAXBElement<Object> createGRLLinkableElementLinksSrc(Object value) {
        return new JAXBElement<Object>(_GRLLinkableElementLinksSrc_QNAME, Object.class, GRLLinkableElement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "linksDest", scope = GRLLinkableElement.class)
    @XmlIDREF
    public JAXBElement<Object> createGRLLinkableElementLinksDest(Object value) {
        return new JAXBElement<Object>(_GRLLinkableElementLinksDest_QNAME, Object.class, GRLLinkableElement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fromLinks", scope = URNmodelElement.class)
    @XmlIDREF
    public JAXBElement<Object> createURNmodelElementFromLinks(Object value) {
        return new JAXBElement<Object>(_URNmodelElementFromLinks_QNAME, Object.class, URNmodelElement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "toLinks", scope = URNmodelElement.class)
    @XmlIDREF
    public JAXBElement<Object> createURNmodelElementToLinks(Object value) {
        return new JAXBElement<Object>(_URNmodelElementToLinks_QNAME, Object.class, URNmodelElement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "components", scope = ProcessingResource.class)
    @XmlIDREF
    public JAXBElement<Object> createProcessingResourceComponents(Object value) {
        return new JAXBElement<Object>(_ProcessingResourceComponents_QNAME, Object.class, ProcessingResource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "succ", scope = GRLNode.class)
    @XmlIDREF
    public JAXBElement<Object> createGRLNodeSucc(Object value) {
        return new JAXBElement<Object>(_GRLNodeSucc_QNAME, Object.class, GRLNode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pred", scope = GRLNode.class)
    @XmlIDREF
    public JAXBElement<Object> createGRLNodePred(Object value) {
        return new JAXBElement<Object>(_GRLNodePred_QNAME, Object.class, GRLNode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "includingComponents", scope = Component.class)
    @XmlIDREF
    public JAXBElement<Object> createComponentIncludingComponents(Object value) {
        return new JAXBElement<Object>(_ComponentIncludingComponents_QNAME, Object.class, Component.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "compRefs", scope = Component.class)
    @XmlIDREF
    public JAXBElement<Object> createComponentCompRefs(Object value) {
        return new JAXBElement<Object>(_ComponentCompRefs_QNAME, Object.class, Component.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "includedComponents", scope = Component.class)
    @XmlIDREF
    public JAXBElement<Object> createComponentIncludedComponents(Object value) {
        return new JAXBElement<Object>(_ComponentIncludedComponents_QNAME, Object.class, Component.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "refs", scope = IntentionalElement.class)
    @XmlIDREF
    public JAXBElement<Object> createIntentionalElementRefs(Object value) {
        return new JAXBElement<Object>(_ElementLinkRefs_QNAME, Object.class, IntentionalElement.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "scenarios", scope = ScenarioGroup.class)
    @XmlIDREF
    public JAXBElement<Object> createScenarioGroupScenarios(Object value) {
        return new JAXBElement<Object>(_ScenarioGroupScenarios_QNAME, Object.class, ScenarioGroup.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "succ", scope = PathNode.class)
    @XmlIDREF
    public JAXBElement<Object> createPathNodeSucc(Object value) {
        return new JAXBElement<Object>(_GRLNodeSucc_QNAME, Object.class, PathNode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pred", scope = PathNode.class)
    @XmlIDREF
    public JAXBElement<Object> createPathNodePred(Object value) {
        return new JAXBElement<Object>(_GRLNodePred_QNAME, Object.class, PathNode.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "inBindings", scope = StartPoint.class)
    @XmlIDREF
    public JAXBElement<Object> createStartPointInBindings(Object value) {
        return new JAXBElement<Object>(_StartPointInBindings_QNAME, Object.class, StartPoint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "outBindings", scope = EndPoint.class)
    @XmlIDREF
    public JAXBElement<Object> createEndPointOutBindings(Object value) {
        return new JAXBElement<Object>(_EndPointOutBindings_QNAME, Object.class, EndPoint.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "group", scope = EvaluationStrategy.class)
    @XmlIDREF
    public JAXBElement<Object> createEvaluationStrategyGroup(Object value) {
        return new JAXBElement<Object>(_EvaluationStrategyGroup_QNAME, Object.class, EvaluationStrategy.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "endPoints", scope = ScenarioDef.class)
    @XmlIDREF
    public JAXBElement<Object> createScenarioDefEndPoints(Object value) {
        return new JAXBElement<Object>(_ScenarioDefEndPoints_QNAME, Object.class, ScenarioDef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "groups", scope = ScenarioDef.class)
    @XmlIDREF
    public JAXBElement<Object> createScenarioDefGroups(Object value) {
        return new JAXBElement<Object>(_ScenarioDefGroups_QNAME, Object.class, ScenarioDef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parentScenarios", scope = ScenarioDef.class)
    @XmlIDREF
    public JAXBElement<Object> createScenarioDefParentScenarios(Object value) {
        return new JAXBElement<Object>(_ScenarioDefParentScenarios_QNAME, Object.class, ScenarioDef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parentStub", scope = UCMmap.class)
    @XmlIDREF
    public JAXBElement<Object> createUCMmapParentStub(Object value) {
        return new JAXBElement<Object>(_UCMmapParentStub_QNAME, Object.class, UCMmap.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "respRefs", scope = Responsibility.class)
    @XmlIDREF
    public JAXBElement<Object> createResponsibilityRespRefs(Object value) {
        return new JAXBElement<Object>(_ResponsibilityRespRefs_QNAME, Object.class, Responsibility.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "inBindings", scope = NodeConnection.class)
    @XmlIDREF
    public JAXBElement<Object> createNodeConnectionInBindings(Object value) {
        return new JAXBElement<Object>(_StartPointInBindings_QNAME, Object.class, NodeConnection.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "outBindings", scope = NodeConnection.class)
    @XmlIDREF
    public JAXBElement<Object> createNodeConnectionOutBindings(Object value) {
        return new JAXBElement<Object>(_EndPointOutBindings_QNAME, Object.class, NodeConnection.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "nodes", scope = ComponentRef.class)
    @XmlIDREF
    public JAXBElement<Object> createComponentRefNodes(Object value) {
        return new JAXBElement<Object>(_ActorRefNodes_QNAME, Object.class, ComponentRef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pluginBindings", scope = ComponentRef.class)
    @XmlIDREF
    public JAXBElement<Object> createComponentRefPluginBindings(Object value) {
        return new JAXBElement<Object>(_ComponentRefPluginBindings_QNAME, Object.class, ComponentRef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "children", scope = ComponentRef.class)
    @XmlIDREF
    public JAXBElement<Object> createComponentRefChildren(Object value) {
        return new JAXBElement<Object>(_ComponentRefChildren_QNAME, Object.class, ComponentRef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "parentBindings", scope = ComponentRef.class)
    @XmlIDREF
    public JAXBElement<Object> createComponentRefParentBindings(Object value) {
        return new JAXBElement<Object>(_ComponentRefParentBindings_QNAME, Object.class, ComponentRef.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "demands", scope = ExternalOperation.class)
    @XmlIDREF
    public JAXBElement<Object> createExternalOperationDemands(Object value) {
        return new JAXBElement<Object>(_ExternalOperationDemands_QNAME, Object.class, ExternalOperation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "strategies", scope = StrategiesGroup.class)
    @XmlIDREF
    public JAXBElement<Object> createStrategiesGroupStrategies(Object value) {
        return new JAXBElement<Object>(_StrategiesGroupStrategies_QNAME, Object.class, StrategiesGroup.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "elems", scope = Actor.class)
    @XmlIDREF
    public JAXBElement<Object> createActorElems(Object value) {
        return new JAXBElement<Object>(_ActorElems_QNAME, Object.class, Actor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "actorRefs", scope = Actor.class)
    @XmlIDREF
    public JAXBElement<Object> createActorActorRefs(Object value) {
        return new JAXBElement<Object>(_ActorActorRefs_QNAME, Object.class, Actor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "collapsedRefs", scope = Actor.class)
    @XmlIDREF
    public JAXBElement<Object> createActorCollapsedRefs(Object value) {
        return new JAXBElement<Object>(_ActorCollapsedRefs_QNAME, Object.class, Actor.class, value);
    }

}
