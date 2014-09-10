package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import grl.ElementLink;
import grl.IntentionalElement;
import seg.jUCMNav.Messages;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

public class AddStandardElementLinkCommandHelper implements Command {

    private IntentionalElement src, dest;
    private URNspec urnspec;
    private ElementLink link;
    private String position;
    
    /**
     * 
     */
    public AddStandardElementLinkCommandHelper(URNspec urn, IntentionalElement source, ElementLink link, String position) {
        
        this.position = position;
        this.urnspec = urn;
        this.link = link;
        this.src = source;

     //   setLabel(Messages.getString("AddStandardElementLinkCommand.addElementLink")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        // disallow source -> source connections
        if (src.equals(dest)) {
            return false;
        }else{
            if( src != null && dest != null){
                if ( src.getGrlspec().getUrnspec().getCreated().compareTo(dest.getGrlspec().getUrnspec().getCreated()) == 0) {
                    // src and dest are in the same urndef, not a reuse case
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // Set the source and destination

        src.getLinksSrc().add(link);
       
        if( position != null ){
            dest.getLinksDest().add(Integer.valueOf(position), link);
        }else{
            dest.getLinksDest().add(link);
        }

        urnspec.getGrlspec().getLinks().add(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPostConditions();
    }

    /**
     * Set the target endpoint for the connection.
     * 
     * @param target
     *            that target endpoint (a non-null IntentionalElement instance)
     */
    public void setTarget(IntentionalElement target) {
        this.dest = target;
    }
    
    /**
     * Returns the target endpoint for the connection.
     * 
     * @return target
     *            that target endpoint (a non-null IntentionalElement instance)
     */
    public IntentionalElement getTarget() {
        return dest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null : "pre link"; //$NON-NLS-1$
        assert urnspec != null : "pre urn spec"; //$NON-NLS-1$
        assert src != null : "pre src"; //$NON-NLS-1$
        assert dest != null : "pre dest"; //$NON-NLS-1$

        assert !urnspec.getGrlspec().getLinks().contains(link) : "pre link in spec"; //$NON-NLS-1$
        assert !src.getLinksSrc().contains(link) : "pre link in source"; //$NON-NLS-1$
        assert !dest.getLinksDest().contains(link) : "pre link in destination"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null : "post link"; //$NON-NLS-1$
        assert urnspec != null : "post urn spec"; //$NON-NLS-1$
        assert src != null : "post src"; //$NON-NLS-1$
        assert dest != null : "post dest"; //$NON-NLS-1$

        assert urnspec.getGrlspec().getLinks().contains(link) : "post link in spec"; //$NON-NLS-1$
        assert src.getLinksSrc().contains(link) : "post link in source"; //$NON-NLS-1$
        assert dest.getLinksDest().contains(link) : "post link in destination"; //$NON-NLS-1$

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // remove the source
        src.getLinksSrc().remove(link);
        dest.getLinksDest().remove(link);

        urnspec.getGrlspec().getLinks().remove(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPreConditions();
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<?> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getAffectedObjects() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Command chain(Command command) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
