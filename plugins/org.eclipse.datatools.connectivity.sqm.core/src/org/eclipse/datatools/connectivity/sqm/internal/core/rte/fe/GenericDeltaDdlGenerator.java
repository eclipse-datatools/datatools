package org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DeltaDDLGenerator;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.sdo.EChangeSummary;
import org.eclipse.emf.ecore.sdo.EDataObject;
import org.eclipse.emf.ecore.sdo.EProperty;

import commonj.sdo.ChangeSummary.Setting;

public class GenericDeltaDdlGenerator implements DeltaDDLGenerator {
	protected static final int CREATE       = 1;
	protected static final int DROP         = 2;
	protected static final int MODIFIED     = 4;
	protected static final int RENAME       = 8;
	protected static final int COMMENT      = 16;
	protected static final int LABEL        = 32;
	
	protected EChangeSummary changeSummary = null;
	protected Collection redoChanges = null;
	
	public final String[] generateDeltaDDL(EChangeSummary changeSummary, IProgressMonitor monitor) {
		this.changeSummary = changeSummary;
		this.changeSummary.summarize();
        Map changeMap = buildChangeMap(monitor);
        analyze(changeMap);
        String[] statements = processChangeMap(changeMap, monitor);
        this.changeSummary = null;
        this.redoChanges = null;
        return statements;
	}
	
	protected int getChangeFlag(EObject element, EObject changed, EStructuralFeature feature, Setting setting) {
		if(element != changed) return MODIFIED; 
		if(feature == EcorePackage.eINSTANCE.getENamedElement_Name()) return RENAME;
		if(feature == SQLSchemaPackage.eINSTANCE.getSQLObject_Description()) return COMMENT;
		if(feature == SQLSchemaPackage.eINSTANCE.getSQLObject_Label()) return LABEL;
		if(feature == SQLTablesPackage.eINSTANCE.getTable_Columns()) return MODIFIED;
		if(feature instanceof EReference && feature.isMany() && ((EReference) feature).getEOpposite() != null) return 0;
		return MODIFIED;
	}
	
	protected void analyze(Map changeMap) {
		List elements = new LinkedList();
		elements.addAll(changeMap.keySet());
		Iterator it = elements.iterator();
		while(it.hasNext()) {
			EObject e = (EObject) it.next();
			int flag = ((Integer) changeMap.get(e)).intValue();
			if(!needRecreate(e, flag)) continue;
			if(e instanceof Column) {
				Column column = (Column) e;
				Table table = column.getTable();
	      		changeMap.put(table, new Integer(CREATE | DROP));
				if(table instanceof PersistentTable){
					processModifiedTable((PersistentTable)table, changeMap);
				}
			}
			else if(e instanceof PersistentTable) {
				flag = CREATE | DROP;
	      		changeMap.put(e, new Integer(flag));
				processModifiedTable((PersistentTable) e, changeMap);
			}
			else if(e instanceof UniqueConstraint) {
				flag = CREATE | DROP;
	      		changeMap.put(e, new Integer(flag));
				processModifiedUniqueConstraint((UniqueConstraint) e, changeMap);				
			}
			else if(e instanceof Index) {
				flag = CREATE | DROP;
	      		changeMap.put(e, new Integer(flag));
				processModifiedIndex((Index) e, changeMap);				
			}
			else {
				flag = CREATE | DROP;
	      		changeMap.put(e, new Integer(flag));
			}
		}
	}
	
	protected boolean needRecreate(EObject e, int flag) {
		return (flag & (MODIFIED | RENAME | COMMENT | LABEL)) != 0;
	}
	
	protected String[] processChangeMap(Map changeMap, IProgressMonitor monitor) {
        DDLGenerator ddlGenerator = getDDLGenerator();
        this.undo();
        String[] drops = getDropStatements(ddlGenerator, changeMap, monitor);
        this.redo();
        String[] creates = getCreateStatements(ddlGenerator, changeMap, monitor);
        this.changeSummary = null;
        return merge(drops, creates);		
	}
	
	protected final String[] merge(String[] s1, String s2[]) {
        String[] all= new String[s1.length + s2.length];
        int k = 0;
        for(int i = 0; i<s1.length; ++i) {
        	all[k++] = s1[i];
        }
        for(int i = 0; i<s2.length; ++i) {
        	all[k++] = s2[i];        	
        }
        return all;		
	}
	
	private void processModifiedUniqueConstraint(UniqueConstraint uk, Map changeMap) {
		Iterator it = uk.getForeignKey().iterator();
		while(it.hasNext()) {
			ForeignKey fk = (ForeignKey) it.next();
			if(!changeMap.containsKey(fk)) {
				changeMap.put(fk, new Integer(CREATE | DROP));
			}
		}
	}
		
	private void processModifiedIndex(Index index, Map changeMap) {
		Iterator it = index.getForeignKey().iterator();
		while(it.hasNext()) {
			ForeignKey fk = (ForeignKey) it.next();
			if(!changeMap.containsKey(fk)) {
				changeMap.put(fk, new Integer(CREATE | DROP));
			}
		}
	}

	private void processModifiedTable(PersistentTable table, Map changeMap) {
		Iterator it = table.getUniqueConstraints().iterator();
		while(it.hasNext()) {
			UniqueConstraint uk = (UniqueConstraint) it.next();
			if(!changeMap.containsKey(uk)) {
				changeMap.put(uk, new Integer(CREATE | DROP));
				processModifiedUniqueConstraint(uk, changeMap);
			}
		}
		
		it = table.getIndex().iterator();
		while(it.hasNext()) {
			Index index = (Index) it.next();
			if(!changeMap.containsKey(index)) {
				changeMap.put(index, new Integer(CREATE | DROP));
				processModifiedIndex(index, changeMap);
			}
		}

		it = table.getForeignKeys().iterator();
		while(it.hasNext()) {
			ForeignKey fk = (ForeignKey) it.next();
			if(!changeMap.containsKey(fk)) {
				changeMap.put(fk, new Integer(CREATE | DROP));
			}
		}

		it = table.getConstraints().iterator();
		while(it.hasNext()) {
			Constraint ck = (Constraint) it.next();
			if(!changeMap.containsKey(ck)) {
				changeMap.put(ck, new Integer(CREATE));
			}
		}

		it = table.getTriggers().iterator();
		while(it.hasNext()) {
			Trigger trigger = (Trigger) it.next();
			if(!changeMap.containsKey(trigger)) {
				changeMap.put(trigger, new Integer(CREATE | DROP));
			}
		}
	}
	
	protected final void undo() {
		List undoStack = new LinkedList();
		List redoStack = new LinkedList();
		Iterator it = changeSummary.getChangedDataObjects().iterator();
		while(it.hasNext()) {
			Object changedObject = it.next();
			if(!(changedObject instanceof EDataObject)) continue;
	      	EDataObject changed = (EDataObject) changedObject;
			List oldValues = changeSummary.getOldValues(changed);
			if(oldValues == null) continue;
			Iterator vi = oldValues.iterator();
			while(vi.hasNext()) {
				Setting changeSetting = (Setting) vi.next();
				EProperty p = (EProperty) changeSetting.getProperty();
				EStructuralFeature f= p.getEStructuralFeature();
				changeSetting.getValue();
				ChangeRecord c1 = new ChangeRecord();
				c1.element = changed;
				c1.feature = f;
				
				c1.isSet = true;
				if(f.isUnsettable()) {
					c1.isSet = changeSetting.isSet();
				}
				
				if(c1.isSet) {
					c1.value = changeSetting.getValue();
					if(c1.value instanceof Collection) {
						List l = new LinkedList();
						l.addAll((Collection) c1.value);
						c1.value = l;
					}					
				}
				else {
					c1.value = null;
				}
				undoStack.add(c1);
				
				ChangeRecord c2 = new ChangeRecord();
				c2.element = changed;
				c2.feature = f;

				c2.isSet = true;
				if(f.isUnsettable()) {
					c2.isSet = changed.eIsSet(f);
				}
				
				if(c2.isSet) {
					c2.value = changed.eGet(f);
					if(c2.value instanceof Collection) {
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
	
	protected final void redo() {
		executeChangeRecords(this.redoChanges);
	}
	
	protected final String[] getDropStatements(DDLGenerator gen, Map changeMap, IProgressMonitor monitor) {
		Vector elements = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			int flag = ((Integer) changeMap.get(key)).intValue();
			if((flag & (DROP)) != 0) {
				elements.add(key);
			}
		}
		if(elements.size() > 0) {
			SQLObject[] d = new SQLObject[elements.size()];
			elements.copyInto(d);
			return gen.dropSQLObjects(d, true, true, monitor);
		}
		else {
			return new String[0];
		}
	}
	
	protected final String[] getCreateStatements(DDLGenerator gen, Map changeMap, IProgressMonitor monitor) {
		Vector elements = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			int flag = ((Integer) changeMap.get(key)).intValue();
			if((flag & CREATE) != 0) {
				elements.add(key);
			}
		}
		if(elements.size() > 0) {
			SQLObject[] d = new SQLObject[elements.size()];
			elements.copyInto(d);
			return gen.createSQLObjects(d, true, true, monitor);
		}
		else {
			return new String[0];
		}
	}

	protected final EObject getDisplayableElement(EObject e) {
		while(e != null && !ContainmentServiceImpl.INSTANCE.isDisplayableElement(e)) {
			e = ContainmentServiceImpl.INSTANCE.getContainer(e);
		}
		return e;
	}
	
	protected final DDLGenerator getDDLGenerator() {
		EObject x = (EObject) this.changeSummary.getDataGraph().getRootObject();
		Database database = (Database) ContainmentServiceImpl.INSTANCE.getRootElement(x);
		DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(database);
		return def.getDDLGenerator();
	}
	
	protected final Object getOldValue(EStructuralFeature feature, EDataObject changed) {
		Iterator it = changeSummary.getOldValues(changed).iterator();
		while(it.hasNext()) {
			Setting changeSetting = (Setting) it.next();
			EProperty p = (EProperty) changeSetting.getProperty();
			if(p.getEStructuralFeature() == feature) {
				return changeSetting.getValue();
			}
		}
		return changed.eGet(feature);
	}
	
	protected final boolean ancestorModified(Map changeMap, EObject e) {
		Iterator it = ContainmentServiceImpl.INSTANCE.getAllContainers(e).iterator();
		while(it.hasNext()) {
			Object c = it.next();
			if(changeMap.containsKey(c)) {
				int flag = ((Integer) changeMap.get(e)).intValue();
				if((flag & (CREATE | DROP | MODIFIED | RENAME)) != 0) return true;
			}
		}
		return false;
	}

	private Map buildChangeMap(IProgressMonitor monitor) {
        Map changeMap = new HashMap();
		Iterator it = changeSummary.getChangedDataObjects().iterator();
		while(it.hasNext()) {
			Object changedObject = it.next();
			if(!(changedObject instanceof EDataObject)) continue;
	      	EDataObject changed = (EDataObject) changedObject;
	      	EDataObject element = (EDataObject) getDisplayableElement(changed);

	      	// ignore all disconnected nondisplayable elements
	      	if(element == null) continue;
	      		
	      	int flag = 0;
	      	if(changeMap.containsKey(element)) flag = ((Integer) changeMap.get(element)).intValue();
	      	if(flag == DROP || flag == CREATE) continue;

      		if(changeSummary.isCreated(element)) {
      			if(changeSummary.isDeleted(element)) continue;
      			flag = CREATE;
      		}
      		else if(changeSummary.isDeleted(element)) {
      			flag = DROP;	      			
      		}
	      	else {
	      		if(changeSummary.isCreated(changed)) continue;
	      		if(changeSummary.isDeleted(changed)) continue;
      			List oldValues = changeSummary.getOldValues(changed);
				if(oldValues == null) continue;
				Iterator vi = oldValues.iterator();
				while(vi.hasNext()) {
					Setting changeSetting = (Setting) vi.next();
					EProperty p = (EProperty) changeSetting.getProperty();
					EStructuralFeature f= p.getEStructuralFeature();
					if(!changeSetting.isSet() && !changed.eIsSet(f)) continue;
					Object currentValue = changed.eGet(f);
					Object previousValue = changeSetting.getValue();
					if(currentValue == null) currentValue = ""; //$NON-NLS-1$
					if(previousValue == null) previousValue = ""; //$NON-NLS-1$
					if(currentValue.equals(previousValue)) continue;
					flag = flag | this.getChangeFlag(element, changed, f, changeSetting);
				}		
	      	}
	      	
	      	if(flag != 0) {
	      		changeMap.put(element, new Integer(flag));
	      	}
		}      	
		return changeMap;
	}

	private void executeChangeRecords(Collection changeRecords) {
		Iterator it = changeRecords.iterator();
		while(it.hasNext()) {
			ChangeRecord r = (ChangeRecord) it.next();
			if(r.isSet) {
				if(r.feature.isMany()) {
					Collection c = (Collection) r.element.eGet(r.feature);
					c.clear();
					c.addAll((Collection) r.value);
				}
				else {
					r.element.eSet(r.feature, r.value);
				}				
			}
			else {
				r.element.eUnset(r.feature);
			}
		}
	}
	
	private static class ChangeRecord {
		public EObject element;
		public EStructuralFeature feature;
		public Object value;
		public boolean isSet;
	}
}