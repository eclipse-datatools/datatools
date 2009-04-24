package org.eclipse.datatools.enablement.sybase.deltaddl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategoryID;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.ChangeDescriptionUtil;
import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlGenerator;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.FeatureChange;

/**
 * some code copied from GenericDeltaDdlGenerator, since some methods of GenericDeltaDdlGenerator are final or private.
 * 
 * @author linsong
 * 
 */
public class SybaseDeltaDdlGeneration implements ISybaseDeltaDdlGenerator
{
    public static final int       CREATE        = 1;
    public static final int       DROP          = 2;
    public static final int       MODIFIED      = 4;

    protected Map                 modifyRecords = null;
    protected ChangeDescription      changeDescription = null;
    protected Collection          redoChanges   = null;
    protected EngineeringOption[] options       = null;
    private DDLGenerator          _ddlGen;
    private Object                _parameter;
    
    protected EObject                   rootObject        = null;
    private EngineeringOptionCategory[] categories        = null;
    private ChangeDescriptionUtil       changeDescriptionUtil;

    public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, IProgressMonitor monitor)
    {
        this.rootObject = rootObject;
        this.changeDescription = changeDescription;
        changeDescriptionUtil = new ChangeDescriptionUtil(changeDescription);
        Map changeMap = buildChangeMap(monitor);
        analyze(changeMap);
        String[] statements = processChangeMap(changeMap, monitor);
        this.changeDescription = null;
        this.redoChanges = null;
        this.modifyRecords = new HashMap();
        this.options = null;
        return statements;
    }
    
    public SybaseDdlScript generateDeltaDDLReturnScript(EObject rootObject, ChangeDescription changeDescription, IProgressMonitor monitor)
    {
        this.rootObject = rootObject;
        this.changeDescription = changeDescription;
        changeDescriptionUtil = new ChangeDescriptionUtil(changeDescription);
        Map changeMap = buildChangeMap(monitor);
        analyze(changeMap);
        SybaseDdlScript script = processChangeMapReturnScript(changeMap, monitor);
        this.changeDescription = null;
        this.redoChanges = null;
        this.modifyRecords = new HashMap();
        this.options = null;
        return script;
    }

    public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, EngineeringOption[] options, IProgressMonitor monitor)
    {
        this.options = options;
        return generateDeltaDDL(rootObject, changeDescription, monitor);
    }
    
    public SybaseDdlScript generateDeltaDDLReturnScript(EObject rootObject, ChangeDescription changeDescription,
            EngineeringOption[] options, IProgressMonitor monitor)
    {
        this.options = options;
        return generateDeltaDDLReturnScript(rootObject, changeDescription, monitor);
    }

    protected int getChangeFlag(EObject element, EObject changed, EStructuralFeature feature, FeatureChange setting)
    {
        int flag = MODIFIED;

        if (modifyRecords == null)
            modifyRecords = new HashMap();
        List records = (List) modifyRecords.get(element);
        if (records == null)
        {
            records = new ArrayList();
            modifyRecords.put(element, records);
        }

        FeatureChangeRecord cr = new FeatureChangeRecord();
        cr.changed = changed;
        cr.feature = feature;
        cr.oldValue = setting.getValue();
        cr.newValue = changed.eGet(feature);

        records.add(cr);

        return flag;
    }

    protected void analyze(Map changeMap)
    {
        List elements = new LinkedList();
        elements.addAll(changeMap.keySet());
        Iterator it = elements.iterator();
        while (it.hasNext())
        {
            EObject e = (EObject) it.next();
            if(!changeMap.containsKey(e))
                continue;
            if (e instanceof SQLObject)
            {
                SQLObject sqlObj = (SQLObject) e;
                IDeltaDdlGenProvider provider = getProvider(sqlObj);
                if (provider != null)
                {
                    provider.analyze(sqlObj, changeMap, modifyRecords);
                }
            }
        }
    }

    protected String[] processChangeMap(Map changeMap, IProgressMonitor monitor)
    {
        ISybaseDdlGenerator ddlGenerator = (ISybaseDdlGenerator) getDDLGenerator();
        SybaseDdlScript script = new SybaseDdlScript();
        //use this flag to avoid duplicated 'use db' statements
        boolean useDBStmtAdded = false; 
        undo();
        processDropStatements(ddlGenerator, changeMap, script, monitor);
        useDBStmtAdded = polishScripts(changeMap, script);
        redo();
        processCreateStatements(ddlGenerator, changeMap, script, monitor);
        processAlterStatements(changeMap, script, monitor);
        changeDescription = null;
        if(!useDBStmtAdded)
        {
        	polishScripts(changeMap, script);
        }
        return script.getStatements();
    }
    
    protected SybaseDdlScript processChangeMapReturnScript(Map changeMap, IProgressMonitor monitor)
    {
        ISybaseDdlGenerator ddlGenerator = (ISybaseDdlGenerator) getDDLGenerator();
        SybaseDdlScript script = new SybaseDdlScript();
        //use this flag to avoid duplicated 'use db' statements
        boolean useDBStmtAdded = false; 
        undo();
        processDropStatements(ddlGenerator, changeMap, script, monitor);
        useDBStmtAdded = polishScripts(changeMap, script);
        redo();
        processCreateStatements(ddlGenerator, changeMap, script, monitor);
        processAlterStatements(changeMap, script, monitor);
        changeDescription = null;
        if(!useDBStmtAdded)
        {
        	polishScripts(changeMap, script);
        }
        return script;
    }
    
    protected boolean polishScripts(Map changeMap, SybaseDdlScript script)
    {
    	return true;
    }

    protected void processCreateStatements(ISybaseDdlGenerator ddlGenerator, Map changeMap, SybaseDdlScript script,
            IProgressMonitor monitor)
    {
        Iterator it = changeMap.keySet().iterator();
        while (it.hasNext())
        {
            EObject key = (EObject) it.next();
            int flag = ((Integer) changeMap.get(key)).intValue();
            if ((flag & CREATE) != 0)
            {
                if (key instanceof SQLObject)
                {
                    SQLObject sqlObj = (SQLObject) key;
                    IDeltaDdlGenProvider provider = getProvider(sqlObj);
                    if (provider != null)
                    {
                        provider.processCreateStatement(sqlObj, generateQuotedIdentifiers(),
                                generateFullyQualifiedNames(), generateFullSyntax(), script,
                                ddlGenerator, monitor);
                    }
                }
            }
        }
    }

    protected void processDropStatements(ISybaseDdlGenerator ddlGenerator, Map changeMap, SybaseDdlScript script,
            IProgressMonitor monitor)
    {
        Iterator it = changeMap.keySet().iterator();
        while (it.hasNext())
        {
            EObject key = (EObject) it.next();
            int flag = ((Integer) changeMap.get(key)).intValue();
            if ((flag & DROP) != 0)
            {
                if (key instanceof SQLObject)
                {
                    SQLObject sqlObj = (SQLObject) key;
                    IDeltaDdlGenProvider provider = getProvider(sqlObj);
                    if (provider != null)
                    {
                        provider.processDropStatement(sqlObj, generateQuotedIdentifiers(),
                                generateFullyQualifiedNames(), script, ddlGenerator, monitor);
                    }
                }
            }
        }
    }

    protected void processAlterStatements(Map changeMap, SybaseDdlScript script, IProgressMonitor monitor)
    {
        Iterator it = changeMap.keySet().iterator();
        while (it.hasNext())
        {
            EObject key = (EObject) it.next();
            int flag = ((Integer) changeMap.get(key)).intValue();
            if ((flag & (DROP | CREATE)) == 0)
            {
                if (key instanceof SQLObject)
                {
                    SQLObject sqlObj = (SQLObject) key;
                    IDeltaDdlGenProvider provider = getProvider(sqlObj);
                    if (provider != null)
                    {
                        provider.processAlterStatement(sqlObj, modifyRecords, generateQuotedIdentifiers(),
                                generateFullyQualifiedNames(), generateFullSyntax(), script, monitor);
                    }
                }
            }
        }

    }

    private IDeltaDdlGenProvider getProvider(SQLObject obj)
    {
        EObject root = ContainmentServiceImpl.INSTANCE.getRootElement(obj);
        if (root instanceof Database)
        {
            DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition((Database) root);
            if (def != null)
            {
                IDeltaDdlGenProvider provider = DeltaDdlGenServiceImpl.INSTANCE.getDeltaDdlGenProvider(
                        def.getProduct(), ((Database)root).getVersion(), obj);
                if (provider instanceof AbstractDeltaDdlGenProvider)
                {
                    ((AbstractDeltaDdlGenProvider) provider).setParameter(getParameter());
                }
                return provider;
            }

        }

        return null;
    }

    public static class FeatureChangeRecord
    {
        /**
         * The object whose feature is changed
         */
        public EObject changed;
        
        public EStructuralFeature feature;

        public Object             oldValue;

        public Object             newValue;
    }

    public EngineeringOption[] getOptions()
    {
        if (this.options == null)
        {
            // if no options specified, return global generation options
            return ((SybaseDdlGenerator) getDDLGenerator()).getOptions();
        }

        return this.options;
    }
    
    public void setEngineeringOption(EngineeringOption[] options)
    {
        this.options = options;
    }
    
    public EngineeringOptionCategory[] getOptionCategories()
    {
        if (this.categories == null)
        {
            ResourceBundle resource = ResourceBundle
                    .getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$

            Vector categoryVec = new Vector();
            categoryVec.add(new EngineeringOptionCategory(EngineeringOptionCategoryID.GENERATE_OPTIONS, resource
                    .getString("GENERATION_OPTIONS"), resource.getString("GENERATION_OPTIONS_DES"))); //$NON-NLS-1$ //$NON-NLS-2$

            EngineeringOptionCategory[] categories = new EngineeringOptionCategory[categoryVec.size()];
            categoryVec.copyInto(categories);
            this.categories = categories;
        }
        return this.categories;
    }

    final protected boolean generateQuotedIdentifiers()
    {
        return ((SybaseDdlGenerator) getDDLGenerator()).generateQuotedIdentifiers(getOptions());
    }

    final protected boolean generateFullyQualifiedNames()
    {
        return ((SybaseDdlGenerator) getDDLGenerator()).generateFullyQualifiedNames(getOptions());
    }

    final protected boolean generateFullSyntax()
    {
        return ((SybaseDdlGenerator) getDDLGenerator()).generateFullSyntax(getOptions());
    }
    
    final protected boolean generateSetUser()
    {
        return ((SybaseDdlGenerator) getDDLGenerator()).generateSetUser(getOptions());
    }
    
    // copy from GenericDeltaDdlGenerator
    private Map buildChangeMap(IProgressMonitor monitor)
    {
        Map changeMap = new LinkedHashMap();
        Iterator it = changeDescriptionUtil.getChangedDataObjectsGen().iterator();
        while (it.hasNext())
        {
            Object changedObject = it.next();
            if (!(changedObject instanceof EObject))
                continue;
            EObject changed = (EObject) changedObject;
            EObject element = (EObject) getDisplayableElement(changed);

            // ignore all disconnected nondisplayable elements
            if (element == null)
                continue;

            int flag = 0;
            if (changeMap.containsKey(element))
                flag = ((Integer) changeMap.get(element)).intValue();
            if (flag == DROP || flag == CREATE)
                continue;

            if (changeDescriptionUtil.isCreated(element))
            {
                if (changeDescriptionUtil.isDeleted(element))
                    continue;
                flag = CREATE;
            }
            else if (changeDescriptionUtil.isDeleted(element))
            {
                flag = DROP;
            }
            else
            {
                if (changeDescriptionUtil.isCreated(changed))
                    continue;
                if (changeDescriptionUtil.isDeleted(changed))
                    continue;
                List oldValues = changeDescriptionUtil.getOldValues(changed);
                if (oldValues == null)
                    continue;
                Iterator vi = oldValues.iterator();
                while (vi.hasNext())
                {
                    FeatureChange changeSetting = (FeatureChange) vi.next();
                    EStructuralFeature f = changeSetting.getFeature();
//need not to compare with default value!
//                    if (!changeSetting.isSet() && !changed.eIsSet(f))
//                        continue;
                    Object currentValue = changed.eGet(f);
                    Object previousValue = changeSetting.getValue();
                    if (currentValue == null)
                        currentValue = ""; //$NON-NLS-1$
                    if (previousValue == null)
                        previousValue = ""; //$NON-NLS-1$
                    if (currentValue.equals(previousValue))
                        continue;
                    flag = flag | this.getChangeFlag(element, changed, f, changeSetting);
                }
            }

            if (flag != 0)
            {
                changeMap.put(element, new Integer(flag));
            }
        }
        return changeMap;
    }

    protected final DDLGenerator getDDLGenerator()
    {
        if (_ddlGen == null)
        {
            Database database = (Database) ContainmentServiceImpl.INSTANCE.getRootElement(rootObject);
            DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(database);
            _ddlGen = def.getDDLGenerator();
        }
        return _ddlGen;
    }

    protected final void undo()
    {
        List undoStack = new LinkedList();
        List redoStack = new LinkedList();
        Iterator it = changeDescriptionUtil.getChangedDataObjectsGen().iterator();
        while (it.hasNext())
        {
            Object changedObject = it.next();
            if (!(changedObject instanceof EObject))
                continue;
            EObject changed = (EObject) changedObject;
            List oldValues = changeDescriptionUtil.getOldValues(changed);
            if (oldValues == null)
                continue;
            Iterator vi = oldValues.iterator();
            while (vi.hasNext())
            {
                FeatureChange fc = (FeatureChange) vi.next();
                EStructuralFeature f = fc.getFeature();
                fc.getValue();
                ChangeRecord c1 = new ChangeRecord();
                c1.element = changed;
                c1.feature = f;

                c1.isSet = true;
                if (f.isUnsettable())
                {
                    c1.isSet = fc.isSet();
                }

                if (c1.isSet)
                {
                    c1.value = fc.getValue();
                    if (c1.value instanceof Collection)
                    {
                        List l = new LinkedList();
                        l.addAll((Collection) c1.value);
                        c1.value = l;
                    }
                }
                else
                {
                    c1.value = null;
                }
                undoStack.add(c1);

                ChangeRecord c2 = new ChangeRecord();
                c2.element = changed;
                c2.feature = f;

                c2.isSet = true;
                if (f.isUnsettable())
                {
                    c2.isSet = changed.eIsSet(f);
                }

                if (c2.isSet)
                {
                    c2.value = changed.eGet(f);
                    if (c2.value instanceof Collection)
                    {
                        List l = new LinkedList();
                        l.addAll((Collection) c2.value);
                        c2.value = l;
                    }
                }

                redoStack.add(c2);
            }
        }
        executeChangeRecords(undoStack);
        this.redoChanges = redoStack;
    }

    protected final void redo()
    {
        executeChangeRecords(this.redoChanges);
    }

    protected final EObject getDisplayableElement(EObject e)
    {
        while (e != null && !ContainmentServiceImpl.INSTANCE.isDisplayableElement(e))
        {
            e = ContainmentServiceImpl.INSTANCE.getContainer(e);
        }
        return e;
    }

    private void executeChangeRecords(Collection changeRecords)
    {
        Iterator it = changeRecords.iterator();
        while (it.hasNext())
        {
            ChangeRecord r = (ChangeRecord) it.next();
            boolean eDeliver = r.element.eDeliver();
            r.element.eSetDeliver(false);
            EObject container = r.element.eContainer();
            boolean containerEDeliver = true;
            if(container != null)
            {
                containerEDeliver = container.eDeliver();
                container.eSetDeliver(false);
            }
            
            if (r.isSet)
            {
                if (r.feature.isMany())
                {
                    Collection c = (Collection) r.element.eGet(r.feature);
                    c.clear();
                    c.addAll((Collection) r.value);
                }
                else
                {
                    r.element.eSet(r.feature, r.value);
                }
            }
            else
            {
                r.element.eUnset(r.feature);
            }
            r.element.eSetDeliver(eDeliver);
            if(container != null)
            {
                container.eSetDeliver(containerEDeliver);
            }
        }
    }

    private static class ChangeRecord
    {
        public EObject            element;
        public EStructuralFeature feature;
        public Object             value;
        public boolean            isSet;
    }
    
    public void setParameter(Object obj) {
        _parameter = obj;
    }

    public Object getParameter() {
        return _parameter;
    }
}
