package seg.ucm2csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import ucm.map.EmptyPoint;

/**
 * Creates the CSM representation(Sequence) of the EmptyPoint object.
 * 
 * @see seg.ucm2csm.one2one
 */

public class EmptyPointConverter implements AbstractConverter {

    private EmptyPoint emptyPointNode;

    PathConnAttributes pathConnAttribs = new PathConnAttributes();

    // constructors
    public EmptyPointConverter(EmptyPoint ep) {
        this.emptyPointNode = ep;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {

        // object attributes
        String Object_attributes = "<Sequence id=\"" + "h" + this.emptyPointNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String traceabilityLink = "traceabilityLink=\"" + this.emptyPointNode.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + Object_attributes + traceabilityLink); //$NON-NLS-1$
        String closing_attribute = "/> <!-- EmptyPoint -->"; //$NON-NLS-1$

        this.pathConnAttribs.OptionalAttributes(this.emptyPointNode, ps, source, target);

        // output to file
        ps.println(closing_attribute);
        ps.flush();
    }
}
