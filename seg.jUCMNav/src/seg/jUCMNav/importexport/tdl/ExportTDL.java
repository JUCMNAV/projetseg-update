package seg.jUCMNav.importexport.tdl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.etsi.mts.tdl.Action;
import org.etsi.mts.tdl.ActionReference;
import org.etsi.mts.tdl.AlternativeBehaviour;
import org.etsi.mts.tdl.Annotation;
import org.etsi.mts.tdl.AnnotationType;
import org.etsi.mts.tdl.AtomicBehaviour;
import org.etsi.mts.tdl.Block;
import org.etsi.mts.tdl.ComponentInstance;
import org.etsi.mts.tdl.ComponentType;
import org.etsi.mts.tdl.Connection;
import org.etsi.mts.tdl.ElementImport;
import org.etsi.mts.tdl.GateType;
import org.etsi.mts.tdl.Interaction;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.ParallelBehaviour;
import org.etsi.mts.tdl.TdlFactory;
import org.etsi.mts.tdl.TestConfiguration;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.Time;
import org.etsi.mts.tdl.TimeOperation;
import org.etsi.mts.tdl.TimeUnit;
import org.etsi.mts.tdl.Timer;
import org.etsi.mts.tdl.TimerOperation;
import org.etsi.mts.tdl.TimerStart;
import org.etsi.mts.tdl.VerdictType;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.resourceManagement.TdlModelManager;
import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.importexport.reports.utils.jUCMNavErrorDialog;
import seg.jUCMNav.importexport.scenariosTools.ExportScenarios;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucmscenarios.Component;
import ucmscenarios.Condition;
import ucmscenarios.Event;
import ucmscenarios.Instance;
import ucmscenarios.Message;
import ucmscenarios.Metadata;
import ucmscenarios.Parallel;
import ucmscenarios.ScenarioDef;
import ucmscenarios.ScenarioGroup;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.Sequence;
import ucmscenarios.SequenceElement;
import urn.URNspec;


/*
 * A class that contains methods to export a .tdl file from
 * a ScenarioSpec.
 * 
 * @author Patrice Boulet 
 */

public class ExportTDL extends ExportScenarios implements IURNExport{
		
	/*
	 * An instance of a TDL factory to generate TDL objects
	 */
	private TdlFactory f = TdlFactory.eINSTANCE;
	
	/* 
	 * A preference value that could be used in a preference view
	 */
	private static final String EXPORT_TYPE = "2";
	
	/* 
	 * A reference to a TdlPackage
	 */
	//private TdlPackage tdlPackageRef = f.getTdlPackage();
	
	/* 
	 * A List that contains all the ComponentType 
	 */
	List<ComponentType> compTypeList = new LinkedList<ComponentType>();
	
	/*
	 * A List that contains all the Action for that Package
	 */	
	List<Action> actionList = new LinkedList<Action>();
	
	/*
	 * A List that contains all the Timer for that package
	 */	
	List<Timer> timerList = new LinkedList<Timer>();
	
	/*
	 * A HashMap where the name of a TimeUnit is the key and the TimeUnit object itself is the value
	 * 			
	 */
	
	HashMap<String, TimeUnit> timeUnitList = new HashMap<String, TimeUnit>();
	
	
	/*
	 * A method invoked by the IURNExport.  It sends a TdlTraversalListener to 
	 * the ScenarioUtils.setActiveScenario method to create a ScenarioSpec.
	 * 
	 * @author Patrice Boulet
	 */
	public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {

        if (mapDiagrams.values().size() == 0)
            return;

        if (!scenarioDefExists(urn)) { // No scenario definition. Avoid Invalid thread access exception.
            @SuppressWarnings("unused")
			jUCMNavErrorDialog warningMessage = new jUCMNavErrorDialog(Messages.getString("ExportTDL.NoScenarioDefined")); //$NON-NLS-1$
            return;
        }
        
        this.newFilename = filename;

        Vector v = new Vector();
        // TODO: find original filename
        v.add(new TdlTraversalListener(this.oldFilename, this.newFilename, ExportTDL.EXPORT_TYPE));

            ScenarioUtils.setActiveScenario(urn.getUcmspec(), v); 
        }
	
	/*
	 * This method generates a .tdl file from a ScenarioSpec object.
	 * 
	 * @author Patrice Boulet
	 * 
	 * @param	scenarioSpec
	 * 		the ScenarioSpec generated by the scenario traversal algorithm
	 * that will be used to generate the .tdl model.
	 * 
	 * @param path
	 * 		the path specified by the user (with .tdl concatenated at the end) 
	 * to the file directory where the .tdl file must be saved
	 */
	public void generateTdl(ScenarioSpec scenarioSpec, String path){

		System.out.println("Plugin execution started \n");
		
		//Creates annotation type representing Interaction names
		AnnotationType interactionTitle = f.createAnnotationType();
		interactionTitle.setName("STEP");
			
		
		// Creates annotation type representing Interaction names
		AnnotationType interactionDescription = f.createAnnotationType();
		interactionDescription.setName("PROCEDURE");
		
		// Creates annotation type representing the instance of a timer
		AnnotationType timerInstanceRef = f.createAnnotationType();
		timerInstanceRef.setName("INSTANCEREF");
			
		// Creates annotation type representing the instance of an alternative
		AnnotationType alternInstanceRef = f.createAnnotationType();
		alternInstanceRef.setName("ALTERNINSTANCEREF");
		
		// Creates annotation type representing the instance of an alternative
		AnnotationType actionInstanceRef = f.createAnnotationType();
		actionInstanceRef.setName("ACTIONINSTANCEREF");
				
		// Creates default GateType
		GateType defaultGT = f.createGateType();
		defaultGT.setName("DefaultGT");
			
		
		// Creates Predefined TimeUnits
		TimeUnit tick = f.createTimeUnit();
		tick.setName("TICK");
		timeUnitList.put("TICK", tick);
		
		TimeUnit nanosecond = f.createTimeUnit();
		nanosecond.setName("NANOSECOND");
		timeUnitList.put("NANOSECOND", nanosecond);
		
		TimeUnit microsecond = f.createTimeUnit();
		microsecond.setName("MICROSECOND");
		timeUnitList.put("MICROSECOND", microsecond);
		
		TimeUnit millisecond = f.createTimeUnit();
		millisecond.setName("MILLISECOND");
		timeUnitList.put("MILLISECOND", millisecond);
		
		TimeUnit second = f.createTimeUnit();
		second.setName("SECOND");
		timeUnitList.put("SECOND", second);
		
		TimeUnit minute = f.createTimeUnit();
		minute.setName("MINUTE");
		timeUnitList.put("MINUTE", minute);
		
		TimeUnit hour = f.createTimeUnit();
		hour.setName("HOUR");
		timeUnitList.put("HOUR", hour);
		
		
		// Creates Predefined VerdictTypes
		VerdictType passVerdict = f.createVerdictType();
		passVerdict.setName("PASS");
				
		VerdictType failVerdict = f.createVerdictType();
		failVerdict.setName("FAIL");
				
		VerdictType inconclusiveVerdict = f.createVerdictType();
		inconclusiveVerdict.setName("INCONCLUSIVE");
		
		Package tdlPackage = null;
		
		File file = null;
		
		//Visits all the ScenarioGroups
		EList<ScenarioGroup> groupList = scenarioSpec.getGroups();
		ListIterator<ScenarioGroup> groupListIt = groupList.listIterator();
		
		Boolean tempFirstFile = false;
		String tempPath = null;
		
		while(groupListIt.hasNext()){
			ScenarioGroup currentUCMGroup = groupListIt.next();
			System.out.println(currentUCMGroup.toString());
			
			
			//Visits all the Scenarios
			EList<ScenarioDef> scenariosList = currentUCMGroup.getScenarios();
			ListIterator<ScenarioDef> scenariosListIt = scenariosList.listIterator();
			
			ElementImport elemIn = f.createElementImport();

			
			while(scenariosListIt.hasNext()){
				ScenarioDef currentUCMScenario = scenariosListIt.next();
				System.out.println("\n************Processing Scenario named: " + currentUCMScenario.toString());
				
				/*
				 * A reference to the top element (tdlPackage) of the tdl model. 
				 */
				tdlPackage = seg.jUCMNav.model.TdlModelCreationFactory.getNewTdlPackage(currentUCMScenario.getName());
				
				if(tempFirstFile == false){
					file = new File(path);
					//System.out.println("This is a separator" + file.separator); 
					tempPath = path.substring(0, path.lastIndexOf(File.separator));
					tempFirstFile = true;
				}
				
				path = tempPath + File.separator + currentUCMScenario.getName() + File.separator + "TDL" + File.separator + currentUCMScenario.getName() + ".tdl";
				
				file = new File(path);
				
				// Creates a test configuration for the currently visited Scenario
				TestConfiguration currentTestConfig = f.createTestConfiguration();
				currentTestConfig.setOwningPackage(tdlPackage);
				
				/*
				 * A HashMap of all the Connections of the current TestConfiguration containing
				 * an Endpoint as a key and its Connecting Endpoints in a List as value
				 */
				 
				HashMap<String, List<String>> connections = new HashMap<String, List<String>>();

				// Sets the owningPackage for default parameters
				interactionTitle.setOwningPackage(tdlPackage);
				
				interactionDescription.setOwningPackage(tdlPackage);
				
				timerInstanceRef.setOwningPackage(tdlPackage);
				
				alternInstanceRef.setOwningPackage(tdlPackage);
				
				actionInstanceRef.setOwningPackage(tdlPackage);

				defaultGT.setOwningPackage(tdlPackage);

				passVerdict.setOwningPackage(tdlPackage);
				
				failVerdict.setOwningPackage(tdlPackage);

				inconclusiveVerdict.setOwningPackage(tdlPackage);
				
				tick.setOwningPackage(tdlPackage);
				
				nanosecond.setOwningPackage(tdlPackage);
				
				microsecond.setOwningPackage(tdlPackage);
				
				millisecond.setOwningPackage(tdlPackage);
				
				second.setOwningPackage(tdlPackage);
				
				minute.setOwningPackage(tdlPackage);
				
				hour.setOwningPackage(tdlPackage);
				
				createComponentTypes(tdlPackage, scenarioSpec, defaultGT);
				
				/* 
				 * Creates a test description for the currently visited scenario and 
				 * assign it to the TestConfiguration of the ScenarioGroup
				 */
				
				TestDescription currentTestDescription = f.createTestDescription();
				currentTestDescription.setOwningPackage(tdlPackage);
				currentTestDescription.setTestConfiguration(currentTestConfig); 
				currentTestDescription.setName("Test" + currentUCMScenario.getName());
				
				/*
				 * The main Block of the scenario.
				 */
				Block mainBlock = currentTestDescription.createBehaviour().createBlock();

				/*
				 * A List of the ComponentInstance in this scenario
				 */
				List<ComponentInstance> compInstList = new LinkedList<ComponentInstance>();
				
				/*
				 * A HashMap where the name of a ComponnetInstance is the key and the ComponentInstance object itself is the value
				 * 			
				 */
				
				HashMap<String, ComponentInstance> compInstNameList = new HashMap<String, ComponentInstance>();
				
				//Visits all the Instances
				EList<Instance> instancesList = currentUCMScenario.getInstances();
				ListIterator<Instance> instancesListIt = instancesList.listIterator();
				
				
				while(instancesListIt.hasNext()){
					
					Instance currentUCMInstance = instancesListIt.next();
					System.out.println("\n************Processing Instance named: " + currentUCMInstance.toString());			
					
					String currentCompInstName = getInstanceName(currentUCMInstance.getName());
					String currentCompInstType = getInstanceType(currentUCMInstance.getName());
					
					boolean found = false;
					
					
					for(ComponentInstance currentCompInstElem : compInstList){
								
						if (currentCompInstElem.getName().equals(currentCompInstName))
							found = true;
						}
					
					if(!found){
						// Creates a ComponentInstance for each type of Instance 
						ComponentInstance currentCompInst = f.createComponentInstance();
						currentCompInst.setName(currentCompInstName);
						
						compInstList.add(currentCompInst);
						compInstNameList.put(currentCompInstName, currentCompInst);
						
						
						// Assigning the currentCompInst a ComponentType and giving it a useful name
						for(ComponentType currentCompType : compTypeList){
							if( currentCompInstType.compareTo(currentCompType.getName()) == 0 )
								currentCompInst.setType(currentCompType);
												}
					
						// Creating a default GateInstance with a useful name
						currentCompInst.createGateInstance().setName("g" + currentCompInst.getName());
						
						currentCompInst.getGateInstances().get(0).setType(currentCompInst.getType().getGateTypes().get(0));
						
						
						
						// Creating a list of unique connections between ComponentInstances that have to communicate together
						List<Message> msgSent = currentUCMInstance.getSent();
						List<String> connectionList = new LinkedList<String>();					
						
						for( Message currentMsg : msgSent){
							Instance currentInst = currentMsg.getTarget();
							
							currentCompInstName = getInstanceName(currentInst.getName());
							
							if(connections.containsKey(currentCompInstName))
									connections.get(currentCompInstName).remove(currentCompInst.getName());
							if(!connectionList.contains(currentCompInstName))
								connectionList.add(currentCompInstName);
						}
						connections.put(currentCompInst.getName(), connectionList );		
						
						currentTestConfig.getComponentInstances().add(currentCompInst);
					}
					
					
				}
				
				// Creates a unique Connection between any ComponentInstances that have to interact together
				for(Map.Entry entry : connections.entrySet()){
					if(!connections.get(entry.getKey()).isEmpty() && connections.get(entry.getKey()) != null){
						for(String currentName : connections.get(entry.getKey())){
							Connection currentConnection = f.createConnection();
							
							currentConnection.setName(compInstNameList.get(entry.getKey()).getName() + "_" 
														+ compInstNameList.get(currentName).getName());
							
							currentConnection.getEndPoints().add(compInstNameList.get(entry.getKey()).getGateInstances().get(0));
							currentConnection.getEndPoints().add(compInstNameList.get(currentName).getGateInstances().get(0));
							currentTestConfig.getConnections().add(currentConnection);
						}
					}
				}

				
				//Traverse the scenario's SequenceElement
				
				// Sequence of the ScenarioDef
				if(!currentUCMScenario.getChildren().isEmpty()){
				SequenceElement seqElemLev1 = (SequenceElement)currentUCMScenario.getChildren().get(0);
				Sequence sequenceLev1 = (Sequence)seqElemLev1;
				
				// Sequence of the Sequence above
				SequenceElement seqElemLev2 = (SequenceElement)sequenceLev1.getChildren().get(0);
				Sequence sequenceLev2 = (Sequence)seqElemLev2;
				
				// List of SequenceElement of the Sequence above
				EList<SequenceElement> seqElemList = sequenceLev2.getChildren();
				ListIterator<SequenceElement> seqElemListIt = seqElemList.listIterator();
				
				
				scenarioSeqElemTraversal(tdlPackage, seqElemList, seqElemListIt, currentUCMScenario, mainBlock, 
											currentTestConfig, compInstNameList, interactionTitle,interactionDescription, timerInstanceRef, alternInstanceRef, actionInstanceRef );
				}
				
				save(file, tdlPackage);
				
				
				
				compTypeList.clear();
				actionList.clear();
				timerList.clear();
				}
			}	
		
		System.out.println("\nPlugin execution terminated ");
	}
	
	/* 
	 * Saves the generated .tdl file to the directory specified 
	 * in the IURNExport view.
	 * 
	 * @author Patrice Boulet
	 * 
	 * @param	path
	 * 		the path specified by the user (with .tdl concatenated at the end) 
	 * to the file directory where the .tdl file must be saved
	 */
	protected void save(File path, Package tdlPackage) {
        TdlModelManager mgr = new TdlModelManager();        
        mgr.createPackage(path, tdlPackage);
    }
	
	/*
	 * Splits the fullName of the Instance and returns
	 * it's type if it has a type specified.  If the type has not
	 * been defined in the UCM it returns it's name as type. 
	 * 
	 * Uses the following convention: 
	 * 	InstanceName : InstanceType
	 * 
	 * @author Patrice Boulet
	 * 
	 * @param	fullName
	 * 		the full name of the Instance following the convention above
	 * 
	 * @return currentCompTypeName
	 * 		the type of the Instance
	 */
	
	protected String getInstanceType(String fullName){
		String [] compInstCompType = fullName.split(":");
		String currentCompTypeName;
		
		if(compInstCompType.length == 1){
			currentCompTypeName = compInstCompType[0] + "Type";
		}else{
			currentCompTypeName = compInstCompType[1];
		}
		
		// Removing all special characters from the name because they are not supported by the PlantUML viewer plugin
		currentCompTypeName = currentCompTypeName.replaceAll("[\\s\\-\\+\\.\\^:,$%?*@!()]","");
		
		return currentCompTypeName;
	}
	
	/*
	 * Splits the fullName of the Instance and returns
	 * it's name. 
	 * 
	 * Uses the following convention: 
	 * 	InstanceName : InstanceType
	 * 
	 * @author Patrice Boulet
	 * 
	 *@param	fullName
	 * 		the full name of the Instance following the convention above
	 * 
	 * @return currentCompTypeName
	 * 		the name of the Instance
	 */
	protected String getInstanceName(String fullName){
		String [] compInstCompType = fullName.split(":");
		String currentInstanceName = compInstCompType[0];
		
		// Removing all special characters from the name because they are not supported by the PlantUML viewer plugin
		currentInstanceName = currentInstanceName.replaceAll("[\\s\\-\\+\\.\\^:,$%?*@!()]","");
		
		return currentInstanceName;
	}
	
	/*
	 * A method that traverses a sequence of SequenceElement 
	 * in depth and creates the TDL objects associated with the
	 * SequenceElements that are traversed.
	 * 
	 * @author Patrice Boulet
	 * 
	 * @param	seqElemList
	 * 		a List of seqElem to traverse  (see	line 341 for further details)
	 * @param	seqElemListIt
	 * 		a List Iterator for the List above
	 * @param	currentUCMScenario
	 * 		the ScenarioDef object that is currently traversed by the algorithm
	 * @param	
	 */	
	
	private void scenarioSeqElemTraversal(Package tdlPackage,
											EList<SequenceElement> seqElemList, 
												ListIterator<SequenceElement> seqElemListIt, 
													ScenarioDef currentUCMScenario,
														Block mainBlock,
															TestConfiguration currentTestConfig,
																HashMap<String, ComponentInstance> compInstNameList,
																	AnnotationType interactionTitle,
																		AnnotationType interactionDescription,
																			AnnotationType timerInstanceRef, 
																				AnnotationType alternInstanceRef,
																					AnnotationType actionInstanceRef){
		while(seqElemListIt.hasNext()){
			
			SequenceElement currentSeqElem = seqElemListIt.next();
			//System.out.println("\n" + currentSeqElem.toString());
		
			//Verifying the type of SequenceElement 
			if (currentSeqElem instanceof Parallel){
				
				Parallel parallelCurrentSeqElem = (Parallel)currentSeqElem;
				
				EList<Sequence> seqListNiv1 = parallelCurrentSeqElem.getChildren();
				ListIterator<Sequence> seqListNiv1It = seqListNiv1.listIterator();
				
		
				ParallelBehaviour parallelBehav = f.createParallelBehaviour();
				
				for (Sequence currentSequence : seqListNiv1){
					
					EList<SequenceElement> seqListNiv2 = currentSequence.getChildren();
					ListIterator<SequenceElement> seqListNiv2It = seqListNiv2.listIterator();
					
					Block currentParallelBlock = parallelBehav.createBlock();
					
					// Recursive call for each branch of the AndFork
					scenarioSeqElemTraversal(tdlPackage, seqListNiv2, seqListNiv2It, currentUCMScenario, currentParallelBlock, 
													currentTestConfig, compInstNameList, interactionTitle, interactionDescription, timerInstanceRef, alternInstanceRef, actionInstanceRef);
				}
				
				mainBlock.getBehaviours().add(parallelBehav);
				
			}else if(currentSeqElem instanceof Sequence){
				
				Sequence sequenceCurrentSeqElem = (Sequence)currentSeqElem;
				
				EList<SequenceElement> seqList = sequenceCurrentSeqElem.getChildren();
				ListIterator<SequenceElement> seqListIt = seqList.listIterator();

				scenarioSeqElemTraversal(tdlPackage, seqList, seqListIt, currentUCMScenario, mainBlock, 
											currentTestConfig, compInstNameList, interactionTitle, interactionDescription, timerInstanceRef, alternInstanceRef, actionInstanceRef);
				
			}else if(currentSeqElem instanceof Message){
				
				Message msgCurrentSeqElem = (Message)currentSeqElem;
			
				
				// Find Source and Target 
				String sourceName  = getInstanceName(msgCurrentSeqElem.getSource().getName());
				String targetName = getInstanceName(msgCurrentSeqElem.getTarget().getName());
				
				// Creates an Interaction with source and target corresponding to the source and target of the Message
				Interaction currentInteraction = f.createInteraction();
				
				currentInteraction.setSource(compInstNameList.get(sourceName).getGateInstances().get(0));
				
				currentInteraction.getTargets().add(compInstNameList.get(targetName).getGateInstances().get(0));
				currentInteraction.setName("From " + sourceName + " to " + targetName);
				
				// Gives a name to the Interaction
				Annotation currentInteractionName = currentInteraction.createAnnotation();
				currentInteractionName.setKey(interactionTitle);
				currentInteractionName.setValue(msgCurrentSeqElem.getName());
				
				// Gives a description to the Interaction
				Annotation currentInteractionDescription = currentInteraction.createAnnotation();
				currentInteractionDescription.setKey(interactionDescription);
				currentInteractionDescription.setValue("If we had a description for this Interaction we could put it here.");
				
				// Adds the new Interaction into the current Block as an AtomicBehaviour
				AtomicBehaviour currentAtomicBehavInteract = currentInteraction;
				mainBlock.getBehaviours().add(currentAtomicBehavInteract);
				
				
			}else if(currentSeqElem instanceof Event){
				
				Event eventCurrentSeqElem = (Event)currentSeqElem;
				
				
				if(eventCurrentSeqElem.getType().getName().equals("StartPoint")){
					// TODO: Define instructions for that case
				}else if (eventCurrentSeqElem.getType().getName().equals("Responsibility")){
					Action currentAction;
					boolean found = false;
					
					// Finds if the action is already defined for that Package
					for(Action currentElem : actionList){																																
						if(eventCurrentSeqElem.getName() == currentElem.getName()){
							currentAction = currentElem;
							found = true;
						}
					}
					
					// Creates a new action if it is not already defined
					if(!found){
						currentAction = f.createAction();
						currentAction.setName(eventCurrentSeqElem.getName());
						currentAction.setOwningPackage(tdlPackage);		
						currentAction.setBody(eventCurrentSeqElem.getName());
						actionList.add(currentAction);																										
					}
					
					// Creates a new ActionReference 
					ActionReference currentActionRef = f.createActionReference();
					currentActionRef.setName(eventCurrentSeqElem.getName());
					
					// Matches the ActionReference to its corresponding Action
					for(Action currentElem : actionList){																											
						if(eventCurrentSeqElem.getName() == currentElem.getName())
							currentActionRef.setAction(currentElem);
						}	
					
					// Creates a Behaviour object to insert the ActionReference into the Block
					AtomicBehaviour currentAtomicBehavAction = currentActionRef;
					mainBlock.getBehaviours().add(currentAtomicBehavAction);
					
					// Gives a reference to the Instance of the action ( useful in PlantUml visualisation )
					Annotation currentActionInstanceRef = currentAtomicBehavAction.createAnnotation();
					currentActionInstanceRef.setKey(actionInstanceRef);
					currentActionInstanceRef.setValue("g" + eventCurrentSeqElem.getInstance().getName());
									
				}else if (eventCurrentSeqElem.getType().getName().equals("EndPoint")){
					// TODO: Define instructions for that case
				}else if (eventCurrentSeqElem.getType().getName().equals("WP_Enter")){
					String timeOperationInstanceName = getInstanceName(eventCurrentSeqElem.getInstance().getName());
										
					TimeOperation currentTimeOperation  = f.createWait();
					currentTimeOperation.setComponentInstance(compInstNameList.get(timeOperationInstanceName));
					currentTimeOperation.setName(timeOperationInstanceName + "_Wait");

					// Creates a Behaviour object to insert the Wait TimeOperation into the Block
					AtomicBehaviour currentAtomicBehavWait = currentTimeOperation;
					mainBlock.getBehaviours().add(currentAtomicBehavWait);		
					
					// TODO : refine instructions above to be more specific 
					
				}else if (eventCurrentSeqElem.getType().getName().equals("WP_Leave")){
					// TODO: Define instructions for that case
				}else if (eventCurrentSeqElem.getType().getName().equals("Connect_Start")){
					// TODO: Define instructions for that case
				}else if (eventCurrentSeqElem.getType().getName().equals("Connect_End")){
					// TODO: Define instructions for that case
				}else if (eventCurrentSeqElem.getType().getName().equals("Trigger_End")){
					// TODO: Define instructions for that case
				}else if (eventCurrentSeqElem.getType().getName().equals("Timer_Set")){
							
					// Setting a reference to the Timer object to use and creating a new one if it doesn't exists.
					String timerInstanceName = getInstanceName(eventCurrentSeqElem.getInstance().getName());
					String timerName = eventCurrentSeqElem.getName();
					Timer currentTimer = null;
					
					boolean found = false;
					
					for ( Timer aTimer : timerList){
						
						if(aTimer.getName().compareTo(timerName) == 0){
							found = true;
							currentTimer = aTimer; 
						}
					}
					
					if (!found){
					currentTimer = compInstNameList.get(timerInstanceName).getType().createTimer();
					currentTimer.setName(eventCurrentSeqElem.getName());
					timerList.add(currentTimer);
					}
					
					// Creating the TimeOperation that starts the Timer 
					TimerStart currentTimerOperation = f.createTimerStart();
					currentTimerOperation.setTimer(currentTimer);
					currentTimerOperation.setName(currentTimer.getName() + "_Start");
					// Defines the period 
					for (Object currentObject : eventCurrentSeqElem.getMetadata()){
						Metadata currentMetadata = (Metadata)currentObject;
						if(currentMetadata.getName().toLowerCase().equals("period")){
							Time currentTime = f.createTime();
							
							currentTime.setUnit(setTimeUnitForPeriod(currentMetadata.getValue().toLowerCase()));
							
							String[] periodNameArray = currentMetadata.getValue().split(" ");
							
							Double periodValue = Double.parseDouble(periodNameArray[0]);
							
							currentTime.setValue(periodValue);
							
							currentTimerOperation.setPeriod(currentTime);
						}
					}
					// Gives a reference to the gateInst for the timer ( useful in PlantUml visualisation )
					Annotation currentTimerInstanceRef = currentTimerOperation.createAnnotation();
					currentTimerInstanceRef.setKey(timerInstanceRef);
					currentTimerInstanceRef.setValue("g" + eventCurrentSeqElem.getInstance().getName());
					
					AtomicBehaviour currentAtomicBehavAction = currentTimerOperation;
					mainBlock.getBehaviours().add(currentAtomicBehavAction);		
					
				}else if (eventCurrentSeqElem.getType().getName() == "Timer_Reset"){
					
					// Setting a reference to the Timer object to use
					String timerName = eventCurrentSeqElem.getName();
					Timer currentTimer = null;
					
					boolean found = false;
					
					for ( Timer aTimer : timerList){

						
						if(aTimer.getName().compareTo(timerName) == 0){
							found = true;
							currentTimer = aTimer; 
						}
					}
					
					if (!found){
					throw new NoSuchElementException("Timer doesn't exist");
					}
							
					// Creating the TimeOperation that inform the Timer to timeout 
					TimerOperation currentTimerOperation = f.createTimerStop();
					currentTimerOperation.setTimer(currentTimer);
					currentTimerOperation.setName(currentTimer.getName() + "_TimerStop");
					
					// Gives a reference to the gateInst for the timer ( useful in PlantUml visualisation )
					Annotation currentTimerInstanceRef = currentTimerOperation.createAnnotation();
					currentTimerInstanceRef.setKey(timerInstanceRef);
					currentTimerInstanceRef.setValue("g" + eventCurrentSeqElem.getInstance().getName());
					
					AtomicBehaviour currentAtomicBehavAction = currentTimerOperation;
					mainBlock.getBehaviours().add(currentAtomicBehavAction);
					
				}else{ // Timeout
					
					// Setting a reference to the Timer object to use
					String timerName = eventCurrentSeqElem.getName();
					Timer currentTimer = null;
					
					boolean found = false;
					
					for ( Timer aTimer : timerList){

						
						if(aTimer.getName().compareTo(timerName) == 0){
							found = true;
							currentTimer = aTimer; 
						}
					}
					
					if (!found){
					throw new NoSuchElementException("Timer doesn't exist");
					}
							
					// Creating the TimeOperation that inform the Timer to timeout 
					TimerOperation currentTimerOperation = f.createTimeOut();
					currentTimerOperation.setTimer(currentTimer);
					currentTimerOperation.setName(currentTimer.getName() + "_Timeout");
					
					// Gives a reference to the gateInst for the timer ( useful in PlantUml visualisation )
					Annotation currentTimerInstanceRef = currentTimerOperation.createAnnotation();
					currentTimerInstanceRef.setKey(timerInstanceRef);
					currentTimerInstanceRef.setValue("g" + eventCurrentSeqElem.getInstance().getName());
					
					AtomicBehaviour currentAtomicBehavAction = currentTimerOperation;
					mainBlock.getBehaviours().add(currentAtomicBehavAction);
				}				
			}else{
				Condition conditionCurrentSeqElem = (Condition)currentSeqElem;
				
				AlternativeBehaviour alternBehav = f.createAlternativeBehaviour();
				alternBehav.setName(conditionCurrentSeqElem.getLabel() + "\\n" + conditionCurrentSeqElem.getExpression());
				
				// Gives a reference to the Instance of the alternative ( useful in PlantUml visualisation )
				Annotation currentAlternInstanceRef = alternBehav.createAnnotation();
				currentAlternInstanceRef.setKey(alternInstanceRef);
				currentAlternInstanceRef.setValue("g" + conditionCurrentSeqElem.getInstance().getName());
				
				mainBlock.getBehaviours().add(alternBehav);
				
			}
			
		}
	}
	
/**
 * Creates ComponentTypes for each UCMScenario Component in the ScenarioSpec
 * 
 * @author pboul037
 * 
 * @param tdlPackage
 * 		owning Package
 * @param scenarioSpec
 * 		current scenarioSpec
 * @param defaultGT
 * 		default GateType
 */
private void createComponentTypes(Package tdlPackage, ScenarioSpec scenarioSpec, GateType defaultGT){
	
	//Visits all the Components
	EList<Component> compList = scenarioSpec.getComponents();
	ListIterator<Component> compListIt = compList.listIterator();
		
	
	while(compListIt.hasNext()){
	
	Component currentUCMComp = compListIt.next();
	System.out.println(currentUCMComp.toString());
	if(! currentUCMComp.getMetadata().isEmpty() && currentUCMComp.getMetadata() != null)
		System.out.println("This is a component metadata" + currentUCMComp.getMetadata().get(0));
	
	String currentCompTypeName = getInstanceType(currentUCMComp.getName());
	
	boolean found = false;
	
	// Checks if the ComponentType has already been created 
	for(ComponentType currentCompTypeElem : compTypeList){
		if (currentCompTypeElem.getName().compareTo(currentCompTypeName) == 0)
			found = true;
		}
	
	if(!found){
		
		// Creates a ComponentType for each type of Component 
		ComponentType currentCompType = f.createComponentType();
		currentCompType.getGateTypes().add(defaultGT);
		currentCompType.setOwningPackage(tdlPackage);
		currentCompType.setName(currentCompTypeName);
		
		compTypeList.add(currentCompType);	
		
		}
	}
}

/**
 * Returns the TimeUnit of the period of a TimeOperation.
 * 
 * @author pboul037
 * 
 * @param period
 * 		a reference to the period
 * @param periodName
 * 		the name of the period
 * @return selectedTimeUnit
 * 		selected TimeUnit according to the period's name
 */
private TimeUnit setTimeUnitForPeriod(String periodName){
	TimeUnit selectedTimeUnit = null;
	
	if ( periodName.contains("tick"))
		selectedTimeUnit = timeUnitList.get("TICK");
	else if ( periodName.contains("nanosecond"))
		selectedTimeUnit = timeUnitList.get("NANOSECOND");
	else if ( periodName.contains("microsecond"))
		selectedTimeUnit = timeUnitList.get("MICROSECOND");
	else if ( periodName.contains("millisecond"))
		selectedTimeUnit = timeUnitList.get("MILLISECOND");	
	else if ( periodName.contains("second"))
		selectedTimeUnit = timeUnitList.get("SECOND");
	else if ( periodName.contains("minute"))
		selectedTimeUnit = timeUnitList.get("MINUTE");
	else if ( periodName.contains("hour"))
		selectedTimeUnit = timeUnitList.get("HOUR");
		
	return selectedTimeUnit;
}
}
	
	  
