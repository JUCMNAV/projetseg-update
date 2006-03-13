/**
 * 
 */
package seg.jUCMNav.strategies;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.IntentionalElement;

import java.util.HashMap;
import java.util.Iterator;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddEvaluationCommand;

/**
 * This class is a singleton responsible to manage the current strategy. 
 * It does the evaluation calculation for IntentionalElement, create the Evaluation
 * and return the value of the evaluation given an IntentionalElement for the current strategy.
 * 
 * @author Jean-Fran�ois Roy
 *
 */
public class EvaluationStrategyManager {
    
    private class EvaluationCalculation{
        public IntentionalElement element;
        public int linkCalc;
        public int totalLinkDest;
        
        public EvaluationCalculation(IntentionalElement element, int totalLink){
            this.element = element;
            this.totalLinkDest = totalLink;
            linkCalc = 0;
        }
    }
    private UCMNavMultiPageEditor multieditor;
    
    private static EvaluationStrategyManager instance;
    public static synchronized EvaluationStrategyManager getInstance()
    {
        if (instance == null){
            instance = new EvaluationStrategyManager();
        }
        return instance;
    }
    
    private HashMap evaluations; //HashMap to keep link between intentionalElement and the evaluation for a particular strategy
    private EvaluationStrategy strategy;

    /**
     * 
     */
    private EvaluationStrategyManager() {
        
    }
    
//    private void calculateElementEvaluation(IntentionalElement element){
//        Evaluation eval = (Evaluation)evaluations.get(element);
//        if ((element.getLinksDest().size() == 0) || (eval.getIntElement() != null)){
//            return;
//        }
//        int result = 0;
//        int decompositionValue = -10000;
//        int dependencyValue = 10000;
//        int [] contributionValues = new int[100];
//        int contribArrayIt = 0;
//        
//        Iterator it = element.getLinksDest().iterator(); //Return the list of elementlink
//        while (it.hasNext()){
//            ElementLink link = (ElementLink)it.next();
//            if (link instanceof Decomposition){
//                if (decompositionValue < -100){
//                    decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
//                } else if (element.getDecompositionType().getValue() == DecompositionType.AND){
//                    if (decompositionValue > ((Evaluation)evaluations.get(link.getSrc())).getEvaluation()){
//                        decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
//                    }
//                } else if (element.getDecompositionType().getValue() == DecompositionType.OR){
//                    if (decompositionValue < ((Evaluation)evaluations.get(link.getSrc())).getEvaluation()){
//                        decompositionValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
//                    }
//                } 
//            } else if (link instanceof Dependency){
//                if (dependencyValue > ((Evaluation)evaluations.get(link.getSrc())).getEvaluation()){
//                    dependencyValue = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
//                }
//            } else if (link instanceof Contribution){
//                Contribution contrib = (Contribution)link;
//                if (contrib.getContribution().getValue() != ContributionType.UNKNOWN){
//                    int srcNode = ((Evaluation)evaluations.get(link.getSrc())).getEvaluation();
//                    //The source node value is between -100 and 100. For the contribution calculation, 
//                    //denied value correspond to 0. The value should be between 0 and 100 and the source evaluation should not be 0.
//                    if (srcNode != 0){
//                        srcNode = 50 + srcNode/2;
//                        
//                        double resultContrib;
//                        switch (contrib.getContribution().getValue()){
//                            case ContributionType.MAKE:
//                                resultContrib = srcNode;
//                                break;
//                            case ContributionType.HELP:
//                                resultContrib = srcNode * 0.5;
//                                break;
//                            case ContributionType.SOME_POSITIVE:
//                                resultContrib = srcNode * 0.25;
//                                break;
//                            case ContributionType.SOME_NEGATIVE:
//                                resultContrib = srcNode * -0.25;
//                                break;
//                            case ContributionType.HURT:
//                                resultContrib = srcNode * -0.5;
//                                break;
//                            case ContributionType.BREAK:
//                                resultContrib = srcNode * -1;
//                                break;
//                            default:
//                                resultContrib = 0;
//                                break;
//                        }
//                        if (resultContrib != 0){
//                            contributionValues[contribArrayIt] = 
//                                (new Double(Math.round(resultContrib))).intValue();
//                            contribArrayIt++;
//                        }
//                    }
//                }
//            }
//        }
//        if (decompositionValue >=-100){
//            result = decompositionValue;
//        }
//        if (contributionValues.length > 0){
//            int numDenied = 0;
//            int numSatisfied = 0;
//            int contribValue = 0;
//            
//            for (int i=0;i<contribArrayIt;i++){
//                if (contributionValues[i] == 100){
//                    numSatisfied++;
//                } else if(contributionValues[i] == -100){
//                    numDenied++;
//                }
//                contribValue += contributionValues[i];
//            }
//            
//            if (contribValue > 90 && numSatisfied == 0){
//                contribValue = 90;
//            } else if (contribValue < -90 && numDenied == 0){
//                contribValue = -90;
//            }
//            result = result + contribValue;
//            
//            if (result > 100 || result<-100){
//                result = (result/Math.abs(result))*100;
//            }
//            
//        }
//        if ((dependencyValue <= 100) && (result > dependencyValue)){
//            result = dependencyValue;
//        }
//        eval.setEvaluation(result);
//    }
    
    public void calculateEvaluation(){
        if (strategy == null){
            return;
        }
        IGRLStrategyAlgorithm algo = new DefaultGRLStrategyAlgorithm();
        
        algo.init(strategy,evaluations);
        
        while(algo.hasNextNode()){
            IntentionalElement element = algo.nextNode();
            Evaluation eval = (Evaluation)evaluations.get(element);
            eval.setEvaluation(algo.getEvaluation(element));
        }
//        Vector evalReady = new Vector();
//        HashMap evaluationCalculation = new HashMap();
        
        //We need to go through the list of IntentionalElement. If it is a leaf node, or if it has a Evaluation 
        //link, it is ready to be calculate.
        
//        Iterator it = strategy.getGrlspec().getIntElements().iterator();
//        while (it.hasNext()){
//            IntentionalElement element = (IntentionalElement)it.next();
//            if (element.getLinksDest().size() == 0 || ((Evaluation)evaluations.get(element)).getStrategies() != null){
//                evalReady.add(element);
//            } else{
//                EvaluationCalculation calculation = new EvaluationCalculation(element, element.getLinksDest().size());
//                evaluationCalculation.put(element,calculation);
//            }
//        }
        
        
//        while(evalReady.size() > 0){ //We loop until we empty this list
//            IntentionalElement intElem = (IntentionalElement)evalReady.remove(0);
//            calculateElementEvaluation(intElem);
//       
//            for (Iterator j=intElem.getLinksSrc().iterator();j.hasNext();){
//                IntentionalElement temp = ((ElementLink)j.next()).getDest();
//                if (evaluationCalculation.containsKey(temp)){
//                    EvaluationCalculation calc = (EvaluationCalculation)evaluationCalculation.get(temp);
//                    calc.linkCalc += 1;
//                    if (calc.linkCalc >= calc.totalLinkDest){
//                        evaluationCalculation.remove(temp);
//                        evalReady.add(calc.element);
//                    }
//                }
//            }
//        }
        //Refresh all the diagrams
        for (int i=0; i< multieditor.getPageCount(); i++){
            UrnEditor u = (UrnEditor) multieditor.getEditor(i);
            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).refreshChildren();         
        }
    }
    
    public int getEvaluation(IntentionalElement elem){
        Evaluation temp = (Evaluation)evaluations.get(elem);
        if (temp == null){
            temp = (Evaluation)ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), Evaluation.class);
            evaluations.put(elem, temp);
        }
        return temp.getEvaluation();
    }
    
    public Evaluation getEvaluationObject(IntentionalElement elem){
        Evaluation temp = (Evaluation)evaluations.get(elem);
        //if the evaluation is null, it is a new element and we need to create a new evaluation
        if (temp == null){
            temp = (Evaluation)ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), Evaluation.class);
            evaluations.put(elem, temp);
        }
        return temp;
    }
    
    public EvaluationStrategy getEvaluationStrategy(){
        return strategy;
    }
    
    public void setEvaluationForElement(IntentionalElement element, Evaluation eval){
        if (eval != null){
            evaluations.remove(element);
            evaluations.put(element,eval);
            calculateEvaluation();
        }
        
    }
    public void setStrategy(EvaluationStrategy strategy){
        this.strategy = strategy;

        //Create a new hash map for this strategy
        evaluations = new HashMap();
        if (strategy != null){
        //Go through all the intentionalElement and create a new Evaluation object if no one exist for this strategy
            GRLspec grl = strategy.getGrlspec();
            Iterator it = grl.getIntElements().iterator();
            while (it.hasNext()){
                IntentionalElement elem = (IntentionalElement)it.next();
                //Verify if an evaluation exist for this strategy. This could create performance problem!!!!
                Iterator sc = strategy.getEvaluations().iterator();
                Evaluation eval = null;
                while(sc.hasNext() && eval == null){
                    Evaluation temp = (Evaluation)sc.next();
                    if (temp.getIntElement() == elem){
                        eval = temp;
                    }
                }
                if (eval == null){
                    eval = (Evaluation)ModelCreationFactory.getNewObject(grl.getUrnspec(), Evaluation.class);
                }
                evaluations.put(elem,eval);
            }
            calculateEvaluation();
        }
    }
    
    public void setIntentionalElementEvaluation(IntentionalElement element, int value){
        //The evaluation could only be between 100 and -100. Do nothing if it is not the case
        if (value<=100 && value>=-100){
            Evaluation eval = (Evaluation)evaluations.get(element);
            //Change the value in the evaluation
            if (value != eval.getEvaluation()){
                eval.setEvaluation(value);
            }
            //If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
            AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
            if (cmd.canExecute()){
                cmd.execute();
            }

            calculateEvaluation();
        }
    }

    public void setMultieditor(UCMNavMultiPageEditor multieditor) {
        this.multieditor = multieditor;
    }
}
