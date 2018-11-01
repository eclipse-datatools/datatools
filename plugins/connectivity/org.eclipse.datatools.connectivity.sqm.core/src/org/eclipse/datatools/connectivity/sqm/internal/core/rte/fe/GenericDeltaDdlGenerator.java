/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe;

import java.util.ArrayList;
import java.util.Collection;
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
import org.eclipse.datatools.connectivity.sqm.core.definition.EngineeringOptionID;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DeltaDDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategoryID;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.ChangeDescriptionUtil;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.FeatureChange;

public class GenericDeltaDdlGenerator implements DeltaDDLGenerator {
	protected static final int CREATE       = 1;
	protected static final int DROP         = 2;
	protected static final int MODIFIED     = 4;
	protected static final int RENAME       = 8;
	protected static final int COMMENT      = 16;
	protected static final int LABEL        = 32;
	
	protected EObject rootObject = null;
	protected ChangeDescription changeDescription = null;
	protected Collection redoChanges = null;
	private EngineeringOption[] options = null;
	private EngineeringOptionCategory[] categories = null;
	private ChangeDescriptionUtil changeDescriptionUtil;
	private boolean destructive = false;

	
	public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, SQLObject[] impacts, IProgressMonitor monitor) 
	{
		return generateDeltaDDL (rootObject, changeDescription, monitor);
	}

	public final String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, IProgressMonitor monitor) {
        ResourceBundle resource = ResourceBundle.getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$
        setDestructive(false);
	    this.rootObject = rootObject;
		this.changeDescription = changeDescription;
		changeDescriptionUtil = new ChangeDescriptionUtil(this.changeDescription);
        monitor.setTaskName(resource.getString("DELTA_DDL_MONITOR_TASK_LOOKING_FOR_CHANGES")); //$NON-NLS-1$
        Map changeMap = buildChangeMap(monitor);
        monitor.setTaskName(resource.getString("DELTA_DDL_MONITOR_TASK_ANALYZING_CHANGES")); //$NON-NLS-1$
        analyze(changeMap);
        monitor.setTaskName(resource.getString("DELTA_DDL_MONITOR_TASK_GENERATING_DDL")); //$NON-NLS-1$
        String[] statements = processChangeMap(changeMap, monitor);
        this.changeDescription = null;
        this.redoChanges = null;
        return statements;
	}
	
	public EngineeringOption[] getOptions(){
        if(this.options == null) {
            ResourceBundle resource = ResourceBundle.getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$

            EngineeringOptionCategory general_options =null;
            for (int i = 0; i < this.getOptionCategories().length; i++) {
            	if (categories[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS)){
            		general_options = categories[i];
            	}
            }
            
            Vector optionVec = new Vector();
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME,resource.getString("GENERATE_FULLY_QUALIFIED_NAME"), resource.getString("GENERATE_FULLY_QUALIFIED_NAME_DES"), true,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER,resource.getString("GENERATE_QUOTED_IDENTIFIER"), resource.getString("GENERATE_QUOTED_IDENTIFIER_DES"),true,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
            
            EngineeringOption[] options = new EngineeringOption[optionVec.size()];
            optionVec.copyInto(options);
            this.options =  options;
        }
        
        return this.options;
	}
	
    public EngineeringOptionCategory[] getOptionCategories(){
        if(this.categories == null) {
            ResourceBundle resource = ResourceBundle.getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$

            Vector categoryVec = new Vector();
            categoryVec.add(new EngineeringOptionCategory(EngineeringOptionCategoryID.GENERATE_OPTIONS,resource.getString("GENERATION_OPTIONS"), resource.getString("GENERATION_OPTIONS_DES"))); //$NON-NLS-1$ //$NON-NLS-2$
        
            EngineeringOptionCategory[] categories = new EngineeringOptionCategory[categoryVec.size()];
            categoryVec.copyInto(categories);
            this.categories =  categories;
        }
        return this.categories;
    }

    protected EngineeringOption[] getEngineeringOption(){
    	return this.options;
    }
    
    protected void setEngineeringOption(EngineeringOption[] options){
    	this.options = options ;
    }
	
	protected int getChangeFlag(EObject element, EObject changed, EStructuralFeature feature, FeatureChange setting) {
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
        this.changeDescription = null;
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
		Iterator it = changeDescriptionUtil.getChangedDataObjectsGen().iterator();
		while(it.hasNext()) {
			Object changedObject = it.next();
	      	EObject changed = (EObject)changedObject;
			List oldValues = (List)changeDescriptionUtil.getOldValues(changed);
			if(oldValues == null) continue;
			Iterator vi = oldValues.iterator();
			while(vi.hasNext()) {
				FeatureChange changeSetting = (FeatureChange) vi.next();
				EStructuralFeature f= changeSetting.getFeature();
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
                if (((flag & (CREATE)) != 0) && 
                        SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(key.eClass())) 
                    setDestructive(true);
			}
		}
		if(elements.size() > 0) {
			SQLObject[] d = new SQLObject[elements.size()];
			elements.copyInto(d);
			return gen.dropSQLObjects(d, this.generateQuotedIdentifiers(this.getOptions()), this.generateFullyQualifiedNames(this.getOptions()),  monitor);
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
			return gen.createSQLObjects(d, this.generateQuotedIdentifiers(this.getOptions()), this.generateFullyQualifiedNames(this.getOptions()), monitor);
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
		Database database = (Database) ContainmentServiceImpl.INSTANCE.getRootElement(rootObject);
		DatabaseDefinition def = DatabaseDefinitionRegistryImpl.INSTANCE.getDefinition(database);
		return def.getDDLGenerator();
	}
	

	
	protected final Object getOldValue(EStructuralFeature feature, EObject changed) {
		Iterator it = changeDescriptionUtil.getOldValues(changed).iterator();
		while(it.hasNext()) {
			FeatureChange changeSetting = (FeatureChange) it.next();
			if(changeSetting.getFeature() == feature) {
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
				int flag = ((Integer) changeMap.get(c)).intValue();
				if((flag & (CREATE | DROP | MODIFIED | RENAME)) != 0) return true;
			}
		}
		return false;
	}


	private Map buildChangeMap(IProgressMonitor monitor) {
        Map changeMap = new LinkedHashMap();
		Iterator it = changeDescriptionUtil.getChangedDataObjectsGen().iterator();
		while(it.hasNext()) {
			Object changedObject = it.next();
			EObject changed = (EObject)changedObject;
	      	EObject element = getDisplayableElement(changed);

	      	// ignore all disconnected nondisplayable elements
	      	if(element == null) continue;
	      		
	      	int flag = 0;
	      	if(changeMap.containsKey(element)) flag = ((Integer) changeMap.get(element)).intValue();
	      	if(flag == DROP || flag == CREATE) continue;

      		if(changeDescriptionUtil.isCreated(element)) {
      			if (changeDescriptionUtil.isDeleted(element)) continue;
      			flag = CREATE;
      		}
      		else if (changeDescriptionUtil.isDeleted(element)) {
      			flag = DROP;	      			
      		}
	      	else {
	      		if(changeDescriptionUtil.isCreated(changed)) continue;
	      		if (changeDescriptionUtil.isDeleted(changed)) continue;
	      		List oldValues = changeDescriptionUtil.getOldValues(changed);
				if(oldValues == null) continue;
				Iterator vi = oldValues.iterator();
				while(vi.hasNext()) {
					FeatureChange changeSetting = (FeatureChange) vi.next();
					EStructuralFeature f= changeSetting.getFeature();
					//The isSet value returned from the changeSetting is not accurate
					//when the feature is an attribute and the feature's value type is Boolean.
					//For example, the nullable attribute of a Column. 
					if(!(f instanceof EAttribute && changeSetting.getValue() != null && changeSetting.getValue() instanceof Boolean)) {
						if(!changeSetting.isSet() && !changed.eIsSet(f)) continue;
					}
					Object currentValue = changed.eGet(f);
					Object previousValue = changeSetting.getValue();
					if(previousValue == null) previousValue = ""; //$NON-NLS-1$
					if(currentValue == null) {
						if (this.underContainer(f,changed,previousValue)) {
	                        if (isDetach(f, changed, previousValue)) flag = DROP;
	                        else flag = 0;
							break;
						} else {
							currentValue = ""; //$NON-NLS-1$
						}
					}
					// Since we are adding the authorization ids in SDOUtilities (at initialize time),
					//   we can detect all new and revoked privileges through the auth id received 
					//   privileges reference.  I think...  Didn't work for new authId to add privilege
					if (f == SQLSchemaPackage.eINSTANCE.getSQLObject_Privileges()) {
						buildPrivilegeGrantChangeMapEntries(changeMap,currentValue,previousValue);
						continue;
					}
					if (f == SQLAccessControlPackage.eINSTANCE.getAuthorizationIdentifier_ReceivedPrivilege()) {
						buildPrivilegeGrantChangeMapEntries(changeMap,currentValue,previousValue);
						continue;
					}
					if (f.getEContainingClass() == SQLAccessControlPackage.eINSTANCE.getPrivilege()) {
						buildPrivilegeChangeMapEntries(changeMap,(Privilege)changedObject,f,currentValue,previousValue);
						continue;
					}
					if (f == SQLAccessControlPackage.eINSTANCE.getAuthorizationIdentifier_ReceivedRoleAuthorization()) {
						buildRoleAuthGrantChangeMapEntries(changeMap,currentValue,previousValue);
						continue;
					}
					if (f.getEContainingClass() == SQLAccessControlPackage.eINSTANCE.getRoleAuthorization()) {
						buildRoleAuthChangeMapEntries(changeMap,(RoleAuthorization)changedObject,f,currentValue,previousValue);
						continue;
					}
					
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

	protected boolean isCreated(EObject element) {
	    return changeDescriptionUtil.isCreated(element);
	}

	// START Privilege Specific
	private void buildPrivilegeGrantChangeMapEntries(Map changeMap,Object currentValue,Object previousValue) {
		// We need to compare new elements to old elements in the ELists, since
		// we allow the user to remove privileges from the privileges list.
		Iterator cVIter;
		Iterator pVIter;
		if (currentValue instanceof EList) {
			cVIter = ((EList)currentValue).iterator();
			if (previousValue instanceof EList) {
				// There are previous values to compare against
				while (cVIter.hasNext()) {
					Privilege cPrivilege = (Privilege)(cVIter.next());
					pVIter = ((EList)previousValue).iterator();
					boolean match = false;
					while (pVIter.hasNext()) {
						Privilege pPrivilege = (Privilege)(pVIter.next());
						if (cPrivilege == pPrivilege) {
							// We have a match
							match = true;
							break;
						}
					}
					if (!match) changeMap.put(cPrivilege,new Integer(CREATE));
				}
			}
			else {
				// All current values represent new privileges
				while (cVIter.hasNext()) {
					Privilege privilege = (Privilege)(cVIter.next());
					changeMap.put(privilege,new Integer(CREATE));
				}
			}
		}
		if (previousValue instanceof EList) {
			pVIter = ((EList)previousValue).iterator();
			if (currentValue instanceof EList) {
				// There are previous values to compare against
				while (pVIter.hasNext()) {
					Privilege pPrivilege = (Privilege)(pVIter.next());
					cVIter = ((EList)currentValue).iterator();
					boolean match = false;
					while (cVIter.hasNext()) {
						Privilege cPrivilege = (Privilege)(cVIter.next());
						if (cPrivilege == pPrivilege) {
							// We have a match
							match = true;
							break;
						}
					}
					if (!match) changeMap.put(pPrivilege,new Integer(DROP));
				}
			}
			else {
				// All current values represent new privileges
				while (pVIter.hasNext()) {
					Privilege privilege = (Privilege)(pVIter.next());
					changeMap.put(privilege,new Integer(DROP));
				}
			}
		}
	}
	
	private void buildPrivilegeChangeMapEntries(Map changeMap,Privilege changedObject,EStructuralFeature f,Object currentValue,Object previousValue) {
		if (f.getName().equals("grantable")) { //$NON-NLS-1$
			// Revoke and re-grant the previous privilege with or without the grant option
			changeMap.put(changedObject,new Integer(DROP | CREATE));
		}
	}
// END Privilege Specific
	
// START RoleAuthorization Specific
	private void buildRoleAuthGrantChangeMapEntries(Map changeMap,Object currentValue,Object previousValue) {
		Iterator cVIter;
		Iterator pVIter;
		if (currentValue instanceof EList) {
			cVIter = ((EList)currentValue).iterator();
			if (previousValue instanceof EList) {
				// There are previous values to compare against
				while (cVIter.hasNext()) {
					RoleAuthorization cRoleAuth = (RoleAuthorization)(cVIter.next());
					pVIter = ((EList)previousValue).iterator();
					boolean match = false;
					while (pVIter.hasNext()) {
						RoleAuthorization pRoleAuth = (RoleAuthorization)(pVIter.next());
						if (cRoleAuth == pRoleAuth) {
							// We have a match
							match = true;
							break;
						}
					}
					if (!match) {
						changeMap.put(cRoleAuth,new Integer(CREATE));
					}
				}
			}
			else {
				// All current values represent new role authorizations
				while (cVIter.hasNext()) {
					RoleAuthorization roleAuth = (RoleAuthorization)(cVIter.next());
					changeMap.put(roleAuth,new Integer(CREATE));
				}
			}
		}
		if (previousValue instanceof EList) {
			pVIter = ((EList)previousValue).iterator();
			if (currentValue instanceof EList) {
				// There are previous values to compare against
				while (pVIter.hasNext()) {
					RoleAuthorization pRoleAuth = (RoleAuthorization)(pVIter.next());
					cVIter = ((EList)currentValue).iterator();
					boolean match = false;
					while (cVIter.hasNext()) {
						RoleAuthorization cRoleAuth = (RoleAuthorization)(cVIter.next());
						if (cRoleAuth == pRoleAuth) {
							// We have a match
							match = true;
							break;
						}
					}
					if (!match) {
						changeMap.put(pRoleAuth,new Integer(DROP));
					}
				}
			}
			else {
				// All current values represent new role authorizations
				while (pVIter.hasNext()) {
					RoleAuthorization roleAuth = (RoleAuthorization)(pVIter.next());
					changeMap.put(roleAuth,new Integer(DROP));
				}
			}
		}
	}

	private void buildRoleAuthChangeMapEntries(Map changeMap,RoleAuthorization changedObject,EStructuralFeature f,Object currentValue,Object previousValue) {
		if (f.getName().equals("grantable")) { //$NON-NLS-1$
			// Revoke and re-grant the previous privilege with or without the grant option
			changeMap.put(changedObject,new Integer(DROP | CREATE));
		}
	}
// END RoleAuthorization Specific
	
	private  boolean underContainer(EStructuralFeature f,Object obj, Object container) {
		if (!(obj instanceof EObject) || !(container instanceof EObject)) return false;
		EStructuralFeature feature  = ((EObject)obj).eContainingFeature();
		if (feature != null) {
			return feature.getEContainingClass().isInstance(container);
		}
		
		Iterator it = ((EObject)container).eClass().getEAllReferences().iterator();
		while(it.hasNext()) {
			EReference reference = (EReference) it.next();
			if(reference.isMany()) {
				EReference opposite = reference.getEOpposite();
				if (opposite != null && opposite.getContainerClass().isAssignableFrom(obj.getClass())){
					return true;
				}
			}
		}

		return false;

	}

    private boolean isDetach (EStructuralFeature f,Object obj, Object container) {
        if (!(obj instanceof EObject) || !(container instanceof EObject)) return false;
        if (f instanceof EReference){ 
            Object oldValue = this.getOldValue(((EReference)f).getEOpposite(), (EObject) container);
            if (oldValue != null) {
                List oldValueList;
                if (oldValue instanceof List) {
                    oldValueList = (List) oldValue;
                }
                else {
                    oldValueList = new ArrayList();
                    oldValueList.add(oldValue);
                }
                
                List currentValueList;
                Object currentValue = ((EObject) container).eGet(((EReference)f).getEOpposite());
                if (currentValue instanceof List) {
                    currentValueList = (List) currentValue;
                }
                else {
                    currentValueList = new ArrayList();
                    currentValueList.add(currentValue);
                }
                
                if (oldValueList.contains(obj) && !currentValueList.contains(obj))
                    return true;
            }
        }
        return false;
    }
	
	private void executeChangeRecords(Collection changeRecords) {
		Iterator it = changeRecords.iterator();
		while(it.hasNext()) {
			ChangeRecord r = (ChangeRecord) it.next();
			boolean deliver = r.element.eDeliver();
			r.element.eSetDeliver(false);	
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
			r.element.eSetDeliver(deliver);
		}
	}
	
    private boolean generateQuotedIdentifiers(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER, options);
    }

    private boolean generateFullyQualifiedNames(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME, options);
    }

    private boolean getOptionValueByID(String optionID, EngineeringOption[] options){
    	return EngineeringOptionID.getOptionValueByID(optionID, options);
    }
    
    public boolean isDestructive() {
        return destructive;
    }
    
    protected void setDestructive(boolean iDestructive) {
        destructive = iDestructive;
    }

	private static class ChangeRecord {
		public EObject element;
		public EStructuralFeature feature;
		public Object value;
		public boolean isSet;
	}
}