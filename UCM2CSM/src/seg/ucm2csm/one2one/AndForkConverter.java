package seg.ucm2csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.AndFork;

/**
 * Creates the CSM representation(And-Fork) of the AndFork object.
 * 
 * @see seg.ucm2csm.one2one
 */

public class AndForkConverter implements AbstractConverter {

    private AndFork andForkNode;

    private PathConnAttributes patthConnAttribs = new PathConnAttributes();

    // constructors
    public AndForkConverter(AndFork af) {
        andForkNode = af;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String id_attribute = "<Fork id=\"" + "h" + andForkNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String traceabilityLink = "traceabilityLink=\"" + andForkNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + id_attribute + traceabilityLink); //$NON-NLS-1$

        String closing_attribute = "/>"; //$NON-NLS-1$

        // optional attributes
        patthConnAttribs.OptionalAttributes(andForkNode, ps, source, target);

        ps.println(closing_attribute);
        ps.flush();
    }

}