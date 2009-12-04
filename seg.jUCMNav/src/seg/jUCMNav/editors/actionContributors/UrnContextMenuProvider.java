package seg.jUCMNav.editors.actionContributors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.AddAndForkAction;
import seg.jUCMNav.actions.AddAndJoinAction;
import seg.jUCMNav.actions.AddBeliefAction;
import seg.jUCMNav.actions.AddBranchAction;
import seg.jUCMNav.actions.AddBranchOnStubAction;
import seg.jUCMNav.actions.AddConditionLabelAction;
import seg.jUCMNav.actions.AddContainerRefAction;
import seg.jUCMNav.actions.AddDirectionArrow;
import seg.jUCMNav.actions.AddEmptyPoint;
import seg.jUCMNav.actions.AddGrlGraphAction;
import seg.jUCMNav.actions.AddLabelAction;
import seg.jUCMNav.actions.AddMapAction;
import seg.jUCMNav.actions.AddOrForkAction;
import seg.jUCMNav.actions.AddOrJoinAction;
import seg.jUCMNav.actions.AddResponsibilityAction;
import seg.jUCMNav.actions.AddStartPointAction;
import seg.jUCMNav.actions.AddStubAction;
import seg.jUCMNav.actions.AddTimeoutPathAction;
import seg.jUCMNav.actions.AddWaitingPlaceAction;
import seg.jUCMNav.actions.BindChildren;
import seg.jUCMNav.actions.BindWithParent;
import seg.jUCMNav.actions.ChangeColorAction;
import seg.jUCMNav.actions.ChangeComponentTypeAction;
import seg.jUCMNav.actions.ChangeDecompositionTypeAction;
import seg.jUCMNav.actions.ChangeStubTypeAction;
import seg.jUCMNav.actions.ChangeWaitPlaceTypeAction;
import seg.jUCMNav.actions.ConnectAction;
import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.actions.DeleteUnreferencedDefinitionAction;
import seg.jUCMNav.actions.DisconnectAction;
import seg.jUCMNav.actions.DisconnectTimeoutPathAction;
import seg.jUCMNav.actions.DuplicateMapAction;
import seg.jUCMNav.actions.EditStubPluginsAction;
import seg.jUCMNav.actions.EditURNLinksAction;
import seg.jUCMNav.actions.ExportAction;
import seg.jUCMNav.actions.GenerateReportAction;
import seg.jUCMNav.actions.ImportAction;
import seg.jUCMNav.actions.MergeStartEndAction;
import seg.jUCMNav.actions.SetNumericalEvaluationAction;
import seg.jUCMNav.actions.SetNumericalImportanceAction;
import seg.jUCMNav.actions.SetQualitativeEvaluationAction;
import seg.jUCMNav.actions.SetQualitativeImportanceAction;
import seg.jUCMNav.actions.SubmenuAction;
import seg.jUCMNav.actions.TransmogrifyAndForkOrJoinAction;
import seg.jUCMNav.actions.TransmogrifyOrForkOrJoinAction;
import seg.jUCMNav.actions.UnbindChildren;
import seg.jUCMNav.actions.UnbindFromParent;
import seg.jUCMNav.actions.concerns.ManageConcernsAction;
import seg.jUCMNav.actions.debug.MakeWellFormedAction;
import seg.jUCMNav.actions.debug.SimplifyForksAndJoinsAction;
import seg.jUCMNav.actions.debug.TrimEmptyPointsAction;
import seg.jUCMNav.actions.hyperlinks.AddHyperlinkAction;
import seg.jUCMNav.actions.hyperlinks.ChangeHyperlinkAction;
import seg.jUCMNav.actions.hyperlinks.DeleteHyperlinkAction;
import seg.jUCMNav.actions.hyperlinks.NavigateHyperlinkAction;
import seg.jUCMNav.actions.kpi.EditIndicatorGroupsAction;
import seg.jUCMNav.actions.metadata.EditMetadataAction;
import seg.jUCMNav.actions.performance.ManageDemandAction;
import seg.jUCMNav.actions.performance.ManageResourcesAction;
import seg.jUCMNav.actions.scenarios.DeleteEvaluationAction;
import seg.jUCMNav.actions.scenarios.EditCodeAction;

/**
 * This class builds the context menu used in our editor and views.
 * 
 * @author Etienne Tremblay, gunterm, pchen
 */
public class UrnContextMenuProvider extends ContextMenuProvider {

    public static final String SUBMENU_INSERTNODE = "seg.jUCMnav.InsertNodeSubMenu"; //$NON-NLS-1$
    public static final String SUBMENU_PATHOPERATIONS = "seg.jUCMnav.PathOperationsSubMenu"; //$NON-NLS-1$
    private ActionRegistry actionRegistry;
    protected static final String GROUP_UNCOMMON = "seg.jUCMNav.Uncommon"; //$NON-NLS-1$

    /**
     * Looks up a set of actions in the action registry. If they are enabled, adds them to the correct groups.
     */
    public void buildContextMenu(IMenuManager manager) {
        GEFActionConstants.addStandardActionGroups(manager);
        manager.add(new Separator(GROUP_UNCOMMON));

        IAction action;

        action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.REDO.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

        action = getActionRegistry().getAction(ActionFactory.CUT.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_COPY, action);

        action = getActionRegistry().getAction(ActionFactory.COPY.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_COPY, action);

        action = getActionRegistry().getAction(ActionFactory.PASTE.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_COPY, action);

        action = getActionRegistry().getAction(GEFActionConstants.ZOOM_IN);
        manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

        action = getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT);
        manager.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

        action = getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId());
        manager.appendToGroup(GEFActionConstants.GROUP_COPY, action);

        action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        action = getActionRegistry().getAction(DeleteEvaluationAction.DELETEEVALUATION);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

        action = getActionRegistry().getAction(AddLabelAction.ADDLABEL);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddConditionLabelAction.ADDLABEL);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(MergeStartEndAction.MERGESTARTEND);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(ConnectAction.CONNECT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(DisconnectAction.DISCONNECT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        IAction[] actions = new IAction[18];
        actions[0] = getActionRegistry().getAction(AddOrForkAction.ADDORFORK);
        actions[1] = getActionRegistry().getAction(AddAndForkAction.ADDANDFORK);
        actions[2] = getActionRegistry().getAction(AddOrJoinAction.ADDORJOIN);
        actions[3] = getActionRegistry().getAction(AddAndJoinAction.ADDANDJOIN);
        actions[4] = getActionRegistry().getAction(AddBranchAction.ADDBRANCH);
        actions[5] = getActionRegistry().getAction(AddTimeoutPathAction.ADDTIMEOUTPATH);
        actions[6] = getActionRegistry().getAction(AddBranchOnStubAction.ADDBRANCH);
        actions[7] = getActionRegistry().getAction(AddBranchOnStubAction.ADDBRANCH2);
        actions[8] = getActionRegistry().getAction(DisconnectTimeoutPathAction.DISCONNECTTIMEOUTPATH);
        actions[9] = getActionRegistry().getAction(CutPathAction.CUTPATH);
        actions[10] = getActionRegistry().getAction(TransmogrifyOrForkOrJoinAction.TRANSMOGRIFYFORK);
        actions[11] = getActionRegistry().getAction(TransmogrifyAndForkOrJoinAction.TRANSMOGRIFYJOIN);
        actions[12] = getActionRegistry().getAction(AddStartPointAction.ADDSTART);
        actions[13] = getActionRegistry().getAction(ChangeStubTypeAction.generateId(0));
        actions[14] = getActionRegistry().getAction(ChangeStubTypeAction.generateId(1));
        actions[15] = getActionRegistry().getAction(ChangeStubTypeAction.generateId(2));
        actions[16] = getActionRegistry().getAction(ChangeWaitPlaceTypeAction.generateId(0));
        actions[17] = getActionRegistry().getAction(ChangeWaitPlaceTypeAction.generateId(1));

        SubmenuAction submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.PathOperations"), Messages.getString("UrnContextMenuProvider.PathOperations"), actions[0].getImageDescriptor(), true); //$NON-NLS-1$ //$NON-NLS-2$
        submenu.setId(SUBMENU_PATHOPERATIONS);
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        actions = new IAction[8];
        actions[0] = getActionRegistry().getAction(AddResponsibilityAction.ADDRESPONSIBILITY);
        actions[1] = getActionRegistry().getAction(AddDirectionArrow.ADDDIRECTIONARROW);
        actions[2] = getActionRegistry().getAction(AddEmptyPoint.ADDEMPTYPOINT);
        actions[3] = getActionRegistry().getAction(AddStubAction.ADDSTUB);
        actions[4] = getActionRegistry().getAction(AddStubAction.ADDDYNAMICSTUB);
        actions[5] = getActionRegistry().getAction(AddStubAction.ADDPOINTCUTSTUB);
        actions[6] = getActionRegistry().getAction(AddWaitingPlaceAction.ADDWP);
        actions[7] = getActionRegistry().getAction(AddWaitingPlaceAction.ADDTIMER);

        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.InsertNode"), Messages.getString("UrnContextMenuProvider.InsertNode"), actions[0].getImageDescriptor(), true); //$NON-NLS-1$ //$NON-NLS-2$
        submenu.setId(SUBMENU_INSERTNODE);
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        actions = new IAction[6];
        actions[0] = getActionRegistry().getAction(AddContainerRefAction.ADDTEAM);
        actions[1] = getActionRegistry().getAction(AddContainerRefAction.ADDOBJECT);
        actions[2] = getActionRegistry().getAction(AddContainerRefAction.ADDPROCESS);
        actions[3] = getActionRegistry().getAction(AddContainerRefAction.ADDAGENT);
        actions[4] = getActionRegistry().getAction(AddContainerRefAction.ADDACTOR);
        actions[5] = getActionRegistry().getAction(AddContainerRefAction.ADDOTHER);

        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.InsertComponent"), Messages.getString("UrnContextMenuProvider.InsertComponent"), actions[0].getImageDescriptor(), true); //$NON-NLS-1$ //$NON-NLS-2$
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        actions = new IAction[6];
        for (int i = 0; i < 6; i++)
            actions[i] = getActionRegistry().getAction(ChangeComponentTypeAction.generateId(i));

        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.ChangeComponentType"), Messages.getString("UrnContextMenuProvider.ChangeComponentType"), actions[0].getImageDescriptor(), true); //$NON-NLS-1$ //$NON-NLS-2$
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        actions = new IAction[16];
        for (int i = 0; i < 16; i++)
            actions[i] = getActionRegistry().getAction(ChangeColorAction.generateId(i));

        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.ChangeFillColor"), Messages.getString("UrnContextMenuProvider.ChangeFillColor"), actions[3].getImageDescriptor(), true); //$NON-NLS-1$ //$NON-NLS-2$
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        manager.add(new Separator(GROUP_UNCOMMON));

        actions = new IAction[8];
        for (int i = 0; i <= 7; i++)
            actions[i] = getActionRegistry().getAction(SetNumericalImportanceAction.generateId(i));
        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.SetNumericalImportance"), Messages.getString("UrnContextMenuProvider.SetNumericalImportance"), JUCMNavPlugin.getImageDescriptor("icons/StrategyNumImp16.gif"), true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        actions = new IAction[6];
        for (int i = 0; i <= 5; i++)
            actions[i] = getActionRegistry().getAction(SetQualitativeImportanceAction.generateId(i));
        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.SetQualitativeImportance"), Messages.getString("UrnContextMenuProvider.SetQualitativeImportance"), JUCMNavPlugin.getImageDescriptor("icons/StrategyQualImp16.gif"), true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        actions = new IAction[12];
        for (int i = 0; i <= 11; i++)
            actions[i] = getActionRegistry().getAction(SetNumericalEvaluationAction.generateId(i));
        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.SetNumericalEvaluation"), Messages.getString("UrnContextMenuProvider.SetNumericalEvaluation"), JUCMNavPlugin.getImageDescriptor("icons/StrategyNumEval16.gif"), true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        actions = new IAction[9];
        for (int i = 0; i <= 8; i++)
            actions[i] = getActionRegistry().getAction(SetQualitativeEvaluationAction.generateId(i));
        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.SetQualitativeEvaluation"), Messages.getString("UrnContextMenuProvider.SetQualitativeEvaluation"), JUCMNavPlugin.getImageDescriptor("icons/StrategyQualEval16.gif"), true); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GEFActionConstants.GROUP_REST, submenu);

        action = getActionRegistry().getAction(BindWithParent.BINDWITHPARENT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(UnbindFromParent.UNBINDFROMPARENT);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(UnbindChildren.UNBINDCHILDREN);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(BindChildren.BINDCHILDREN);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddBeliefAction.ADDBELIEF);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(ChangeDecompositionTypeAction.CHANGEDECOMPOSITIONTYPE);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddMapAction.ADDMAP);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(AddGrlGraphAction.ADDGRLGRAPH);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        actions = new IAction[4];
        actions[0] = getActionRegistry().getAction(AddHyperlinkAction.ADDHYPERLINK);
        actions[1] = getActionRegistry().getAction(NavigateHyperlinkAction.NAVIGATEHYPERLINK);
        actions[2] = getActionRegistry().getAction(ChangeHyperlinkAction.CHANGEHYPERLINK);
        actions[3] = getActionRegistry().getAction(DeleteHyperlinkAction.DELETEHYPERLINK);

        submenu = new SubmenuAction(
                actions,
                Messages.getString("UrnContextMenuProvider.Hyperlink"), Messages.getString("UrnContextMenuProvider.Hyperlink"), actions[0].getImageDescriptor(), true); //$NON-NLS-1$ //$NON-NLS-2$
        if (submenu.getActiveOperationCount() > 0)
            manager.appendToGroup(GROUP_UNCOMMON, submenu);

        action = getActionRegistry().getAction(ManageConcernsAction.MANAGECONCERNS);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(DeleteUnreferencedDefinitionAction.DELETEDEF);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(ImportAction.IMPORT);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(ExportAction.EXPORT);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(GenerateReportAction.GENERATEREPORT);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(EditStubPluginsAction.EDITSTUBPLUGINS);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(EditURNLinksAction.EDITURNLINKS);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(EditCodeAction.EDITCODEACTION);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(DuplicateMapAction.DUPLICATEMAP);
        if (action.isEnabled())
            manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        action = getActionRegistry().getAction(EditMetadataAction.EDITMETADATAACTION);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        action = getActionRegistry().getAction(EditIndicatorGroupsAction.EDITINDICATORGROUPSACTION);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        // _js_
        action = getActionRegistry().getAction(ManageDemandAction.MANAGEDEMANDACTION);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        // _js_
        action = getActionRegistry().getAction(ManageResourcesAction.MANAGERESOURCESACTION);
        if (action.isEnabled())
            manager.appendToGroup(GROUP_UNCOMMON, action);

        // only available when debugging jucmnav

        if (JUCMNavPlugin.isInDebug()) {
            action = getActionRegistry().getAction(MakeWellFormedAction.MAKEWELLFORMED);
            if (action != null && action.isEnabled())
                manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

            action = getActionRegistry().getAction(SimplifyForksAndJoinsAction.SIMPLIFYFORKSANDJOINS);
            if (action != null && action.isEnabled())
                manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

            action = getActionRegistry().getAction(TrimEmptyPointsAction.TRIMEMPTYPOINTS);
            if (action != null && action.isEnabled())
                manager.appendToGroup(GEFActionConstants.GROUP_REST, action);

        }

    }

    /**
     * @param viewer
     * @param registry
     *            has to be passed in case we don't want to use the action registry used in the viewer. [is this bad coding?]
     */
    public UrnContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
        super(viewer);
        setActionRegistry(registry);
    }

    /**
     * 
     * @return the action registry used by the context menu provider.
     */
    private ActionRegistry getActionRegistry() {
        return actionRegistry;
    }

    /**
     * 
     * @param registry
     *            the action registry used by the context menu provider.
     */
    private void setActionRegistry(ActionRegistry registry) {
        actionRegistry = registry;
    }

    public void dispose() {
        setActionRegistry(null);
        super.dispose();
    }
}
