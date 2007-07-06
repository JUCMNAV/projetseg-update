package seg.ucm2csm.one2one;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IMarker;

import ucm.map.InBinding;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.Stub;

/**
 * Creates the CSM representation(Step) of the Stub object Component-Ref object.
 * 
 * @see seg.ucm2csm.one2one
 */
public class StubConverter implements AbstractConverter {

    private Stub stub;

    private StepAttributes stepAttribs = new StepAttributes();

    // constructors
    public StubConverter(Stub stub) {
        this.stub = stub;
    }

    // prints XML representation of object to output file
    public void Convert(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {
        // ?js ((PluginBinding) stub.getBindings().get(0)).getProbability();

        // TODO: predecessor/successor (from parameters source/target) may be
        // (correctly) pointing at newly inserted (CSM) DummySequences
        // while PluginBinding still points at the elements referred within the
        // UCM structure.

        // object attributes
        String predecessorWithCommas = (String) source.toString().subSequence(1, (source.toString().length() - 1));
        String predecessor = predecessorWithCommas.replaceAll(",", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (stub.getPred().size() > 1) {
            warnings.add(new CsmExportWarning(Messages.getString("StubConverter.Stub") + stub.getName() + Messages.getString("StubConverter.HasMoreThanOnePredecessor"), stub)); //$NON-NLS-1$ //$NON-NLS-2$
        }

        String successorWithCommas = (String) target.toString().subSequence(1, (target.toString().length() - 1));
        String successor = successorWithCommas.replaceAll(",", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if (stub.getSucc().size() > 1) {
            warnings.add(new CsmExportWarning(Messages.getString("StubConverter.Stub") + stub.getName() + Messages.getString("StubConverter.HasMoreThanOneSuccessor"), stub)); //$NON-NLS-1$ //$NON-NLS-2$
        }

        String name = null;
        if (stub.getBindings().size() == 0) {
            warnings.add(new CsmExportWarning(Messages.getString("StubConverter.Stub") + stub.getName() + Messages.getString("StubConverter.HasNoBindings"), stub, IMarker.SEVERITY_ERROR)); //$NON-NLS-1$ //$NON-NLS-2$
            name = stub.isDynamic() ? stub.getName() : stub.getName() + "/" + "ERROR_NO_BINDING"; //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            name = stub.isDynamic() ? stub.getName() : stub.getName() + "/" + ((PluginBinding) (stub.getBindings().get(0))).getPlugin().getName(); //$NON-NLS-1$
        }

        String mandatory_attribute = "<Step id=\"" + "h" + stub.getId() + "\" " + "name=\"" + name + "\" " + "predecessor=\"" + predecessor + "\" " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                + "successor=\"" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        ps.print("            " + mandatory_attribute); //$NON-NLS-1$

        // optional attributes
        this.stepAttribs.OptionalAttributes(this.stub, ps);
        ps.println("> <!-- Stub -->"); //$NON-NLS-1$

        // Dynamic Stub
        if (this.stub.isDynamic()) {
            String stubId = this.stub.getId();
            String fake_stubId = "fs_" + stubId; //$NON-NLS-1$
            String plugBind_head = "<Refinement parent=\"" + "h" + stubId + "\" sub=\"" + fake_stubId + "\" >"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

            String plugBind_tail = "</Refinement>"; //$NON-NLS-1$
            // String oneTab = " ";
            // String twoTab = " ";
            String threeTab = "                "; //$NON-NLS-1$
            String fourTab = "                    "; //$NON-NLS-1$
            ps.println(threeTab + plugBind_head);

            /**
             * All bindings should have the same cardinality. We're using the first binding one as "skeleton" for all of them. The proper binding of the maps
             * should be checked. JS
             */
            if (this.stub.getBindings().size() != 0) {
                PluginBinding pb = (PluginBinding) this.stub.getBindings().get(0);
                int nthIn = 0;
                for (Iterator inBindingIterator = pb.getIn().iterator(); inBindingIterator.hasNext();) {
                    InBinding ib = (InBinding) inBindingIterator.next();
                    String inBind = "<InBinding start=\"" + fake_stubId + "_start_" + nthIn + "\" " + "in=\"h" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                            + ((PathNode) ib.getStubEntry().getSource()).getId() + "\" />"; //$NON-NLS-1$
                    ps.println(fourTab + inBind);
                    nthIn++;
                }
                int nthOut = 0;
                for (Iterator outBindingIterator = pb.getOut().iterator(); outBindingIterator.hasNext();) {
                    OutBinding ob = (OutBinding) outBindingIterator.next();
                    String outBind = "<OutBinding end=\"" + fake_stubId + "_end_" + nthOut + "\" " + "out=\"h" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                            + ((PathNode) ob.getStubExit().getTarget()).getId() + "\" />"; //$NON-NLS-1$
                    ps.println(fourTab + outBind);
                    nthOut++;
                }
            }

            ps.println(threeTab + plugBind_tail);
            // PluginBindings will be output as branches in another map
            // Static Stub
        } else {
            // process bindings as CSM refinements
            for (Iterator iter = this.stub.getBindings().iterator(); iter.hasNext();) {
                PluginBinding binding = (PluginBinding) iter.next();
                PluginBindingConverter bind_obj = new PluginBindingConverter(binding);
                bind_obj.Convert(ps, source, target, warnings);
            }
        }

        // output to file
        String closing_attribute = "</Step>"; //$NON-NLS-1$
        ps.println("            " + closing_attribute); //$NON-NLS-1$
        ps.flush();
    }
}
