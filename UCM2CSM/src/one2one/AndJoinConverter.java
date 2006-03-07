package one2one;

import java.io.PrintStream;

import ucm.map.AndJoin;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
/**
 * <!-- begin-user-doc -->
 * Creates the CSM representation(Join) of the AndJoin object
 * <!-- end-user-doc -->
 * @see one2one 
 * @generated
 */
public class AndJoinConverter implements AbstractConverter {
    private AndJoin aj;
    // constructors
    public AndJoinConverter(AndJoin aj){
       this.aj = aj;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps){
       
       // object attributes 
        String object_attributes = "<Join id=\"" + "h" + aj.getId() + "\""; //+ " " +
                                   
        ps.print("			" + object_attributes);
        String closing_attribute = "/>";
       
        if (aj.getDescription() != null){
        	String description_attribute = "description=\"" + aj.getDescription() +"\"";
        	ps.print(description_attribute);
        }
        if ((NodeConnection)aj.getSucc().get(0)!= null && (PathNode) ((NodeConnection)aj.getSucc().get(0)).getTarget()!= null  ){
        	PathNode target = (PathNode) ((NodeConnection)aj.getSucc().get(0)).getTarget(); 
        	String target_attribute = "target= \"h" +target.getId() +"\"";
        	ps.print(" " + target_attribute);
        }
        if ((NodeConnection)aj.getPred().get(0) != null && (PathNode) ((NodeConnection)aj.getPred().get(0)).getSource() != null){
        	PathNode source = (PathNode) ((NodeConnection)aj.getPred().get(0)).getSource();
        	String source_attribute = "source= \"h" + source.getId() +"\"";
        	ps.print(" " + source_attribute);
        }
       // output to file
      // ps.println("            " + object_attributes);
       ps.println(closing_attribute);
       ps.flush();                    
                        
    }
}
