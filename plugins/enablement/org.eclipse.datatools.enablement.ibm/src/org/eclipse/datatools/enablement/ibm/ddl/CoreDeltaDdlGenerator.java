/*******************************************************************************
 * Copyright (c) 2007, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.ddl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.IEngineeringCallBack;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDeltaDdlGenerator;
import org.eclipse.datatools.enablement.ibm.util.DependencyImpactAnalyst;
import org.eclipse.datatools.enablement.ibm.util.DependencyImpactDescription;
import org.eclipse.datatools.enablement.ibm.util.EngineeringOptionID;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class CoreDeltaDdlGenerator extends GenericDeltaDdlGenerator {

	protected boolean dataPreservationRequired = false;
	protected Set<SQLObject> suppressCreateElementSet = null;
	protected EngineeringOption[] selectedOptions = null;
	private boolean isDefaultSelectedOptionsSet = false;
	protected DDLGenerator ddlGenerator = null;
	
	public DDLGenerator getDdlGeneratorWithDeltaDDLOptions() {
		if(ddlGenerator == null)
		{
			ddlGenerator = getDDLGenerator();
		}
		return ddlGenerator;
	}


	public void setDdlGeneratorWithDeltaDDLOptions(DDLGenerator ddlGenerator) {
		this.ddlGenerator = ddlGenerator;
	}
	public CoreDeltaDdlGenerator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Subclass must implement this method to take the engineering callback.
	 * @param callback
	 */
	protected abstract void setEngineeringCallback(IEngineeringCallBack callback);

	protected boolean checkModel(SQLObject[] sqlObjects, EngineeringOption[] options) {
		boolean ret = true;
		if (! EngineeringOptionID.checkModel(options)) return ret;
		
		ModelValidationProvider provider = DdlGenerationUtility.getModelValidationProvider();
		if (provider != null) {
			ret = provider.checkModel(sqlObjects);
		}
		return ret;
	}

	public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, SQLObject[] impacts, IProgressMonitor monitor, IEngineeringCallBack callback) {
		dataPreservationRequired = false;
		setDestructive(false);
		setEngineeringOption(null);
		setEngineeringCallback(callback);
		
		// the selected options will be null if not invoked from compare merge screen
		if (selectedOptions == null
				|| (selectedOptions != null && isDefaultSelectedOptionsSet == true)) {
			this.rootObject = rootObject;
			populateDefaultSelectedOptions(rootObject);
			isDefaultSelectedOptionsSet = true;
		}
		
        if (!this.checkModel(new SQLObject[]{(SQLObject) rootObject}, selectedOptions)) {
        	callback.writeMessage(DdlGenerationMessages.FE_INVALID_MODEL);
        	return new String[0];
        }

		String[] statements = super.generateDeltaDDL(rootObject, changeDescription, monitor);
		this.changeDescription = changeDescription;
		impacts = analyzeImpacts(impacts, statements);
		String[] dropImpactedStatements = getDependentsDropStatements(changeDescription, impacts, monitor, callback);
		dropImpactedStatements = removeDuplicates(statements, dropImpactedStatements);		
		String[] createImpactedStatements = getDependentsCreateStatements(changeDescription, impacts, monitor, callback);
		createImpactedStatements = removeDuplicates(statements, createImpactedStatements);
		String[] m1 = merge(dropImpactedStatements, statements);
		String[] m2 = merge(m1, createImpactedStatements);
		this.changeDescription = null;
		m2 = postProcess(m2);
		return m2;
	}

	private void populateDefaultSelectedOptions(EObject rootObject) {
		selectedOptions = getDdlGeneratorOptionsForDeltaDdl(
				getDdlGeneratorWithDeltaDDLOptions(),
				new SQLObject[] { (SQLObject) rootObject });
		// set the default options as true
		for (int i=0; i < selectedOptions.length; i++)
		{
			EngineeringOption option = selectedOptions[i];
			if (!option.getBoolean()
					&& !option.getId().equals(
							EngineeringOptionID.GENERATE_CREATE_OR_REPLACE))
				option.setBoolean(true);
		}
	}


	/**
	 * Allows subclasses to perform final processing of DDL before completion
	 * 
	 * @param m2
	 * @return
	 */
	protected String[] postProcess(String[] m2) {
		return m2;
	}

	protected boolean hasDescription(SQLObject object) {
		if (object != null) {
			String description = object.getDescription();
			if (description != null && description.length() > 0) return true;
		}
		return false;
	}
	
	private String[] removeDuplicates(String[] existingStatements,
			String[] statements) {
		TreeSet set = new TreeSet(Arrays.asList(existingStatements));
		ArrayList list = new ArrayList();
		for (int i = 0; i < statements.length; ++i) {
			if (!set.contains(statements[i])) {
				list.add(statements[i]);
			}
		}
		String[] newStatements = new String[list.size()];
		list.toArray(newStatements);
		return newStatements;
	}

	protected boolean isChangeAnnotationRelated(EObject element,EObject changed,EStructuralFeature feature) {
		if (EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(element.eClass())) return true;
		EObject container = element.eContainer();
		if (container != null &&
				EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(container.eClass())) return true;
		if (EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(changed.eClass())) return true;
		container = changed.eContainer();
		if (container != null &&
				EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(container.eClass())) return true;
        if(feature == EcorePackage.eINSTANCE.getEModelElement_EAnnotations()) return true;

		return false;
	}
	/**
	 * This method is here to support callback. The one in GenericDeltaDdlGenerator does not support callback.
	 * 
	 */
	protected String[] getDropStatements(DDLGenerator gen, Map changeMap, IProgressMonitor monitor, IEngineeringCallBack callback) {
		Vector elements = new Vector();
		Iterator it = changeMap.keySet().iterator();
		while(it.hasNext()) {
			EObject key = (EObject) it.next();
			int flag = ((Integer) changeMap.get(key)).intValue();
			if((flag & (DROP)) != 0) {
				// if this is a drop->create which can be replaced by a CREATE OR REPLACE then we don't need to drop
				if (((flag & CREATE) == 0) || (((flag & CREATE) != 0) && !isSuppressedDrop(key))) {
					elements.add(key);
				} 
				if (((flag & (CREATE)) != 0) && 
						(SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(key.eClass()) ||
								(SQLSchemaPackage.eINSTANCE.getSchema().isSuperTypeOf(key.eClass()) &&
										((Schema)key).getTables() != null && 
										!((Schema)key).getTables().isEmpty()))) 
					setDestructive(true);
			}
		}
		if(elements.size() > 0) {
			SQLObject[] d = new SQLObject[elements.size()];
			elements.copyInto(d);
			return gen.dropSQLObjects(d, this.generateQuotedIdentifiers(this.getSelectedOptions()), this.generateFullyQualifiedNames(this.getSelectedOptions()),  monitor, callback);
		}
		else {
			return new String[0];
		}
	}
	
	/**
	 * Add an object to the record of which objects should have their DROP statements suppressed
	 * 
	 * @param The object for which a DROP should be suppressed
	 */
	protected void addSuppressedDrop(EObject key) {
	}

	/**
	 * Determine if an object should have its DROP statement suppressed.
	 * 
	 * @return
	 */
	protected boolean isSuppressedDrop(EObject key) {
		return false;
	}
	
	/**
	 * Reset the record of objects which should have DROP statements suppressed
	 */
	protected void resetSuppressedDrops() {
	}

	/**
	 * Determine whether a drop can be suppressed due to use of CREATE OR REPLACE statement usage
	 * 
	 * @param Object for which the drop suppression is to be determined
	 * @return true if the drop is unnecessary because a CREATE OR REPLACE will be issued
	 */
	protected boolean isDropSuppressedForCreateOrReplace(EObject key) {
		return false;
	}

	protected boolean shouldExcludeElement(EObject element) {
		if (element instanceof PersistentTable) {
			if (!EngineeringOptionID.generateTables(selectedOptions))
				return true;
		} else if (element instanceof ViewTable) {
			if (!EngineeringOptionID.generateViews(selectedOptions))
				return true;
		} else if (element instanceof Procedure) {
			if (!EngineeringOptionID.generateStoredProcedures(selectedOptions))
				return true;
		} else if (element instanceof UserDefinedFunction) {
			if (!EngineeringOptionID.generateFunctions(selectedOptions))
				return true;
		} else if (element instanceof Trigger) {
			if (!EngineeringOptionID.generateTriggers(selectedOptions))
				return true;
		} else if (element instanceof CheckConstraint) {
			if (!EngineeringOptionID.generateCKConstraints(selectedOptions))
				return true;
		} else if (element instanceof ForeignKey) {
			if (!EngineeringOptionID.generateFKConstraints(selectedOptions))
				return true;
		} else if (element instanceof Index) {
			if (!EngineeringOptionID.generateIndexes(selectedOptions)
					|| ((Index) element).isSystemGenerated())
				return true;
		} else if (element instanceof UserDefinedType) {
			if (!EngineeringOptionID.generateUserDefinedTypes(selectedOptions))
				return true;
		} else if (element instanceof Sequence) {
			if (!EngineeringOptionID.generateSequences(selectedOptions))
				return true;
		} else if (element instanceof Role) {
			if (!EngineeringOptionID.generateRoles(selectedOptions))
				return true;
		} else if (element instanceof Privilege) {
			if (!EngineeringOptionID.generateRevokeStatement(selectedOptions))
				return true;
		} else if (element instanceof RoleAuthorization) {
			if (!EngineeringOptionID.generateRevokeStatement(selectedOptions))
				return true;
		} else if (element instanceof UniqueConstraint) {
			if (!EngineeringOptionID.generatePKConstraints(selectedOptions)
					|| !((UniqueConstraint) element).isEnforced())
				return true;
		} else if (element instanceof Schema) {
			if (!EngineeringOptionID.generateSchemas(selectedOptions))
				return true;
		} else if (element instanceof Privilege) {
			if (!EngineeringOptionID.generateRevokeStatement(selectedOptions))
				return true;
		} else if (element instanceof AuthorizationIdentifier) {
			if (EngineeringOptionID.generateRevokeStatement(selectedOptions)) {
				return true;
			}
		} else if (element instanceof Role) {
			if (!EngineeringOptionID.generateRoles(selectedOptions))
				return true;
		} else if (element instanceof RoleAuthorization) {
			if (!EngineeringOptionID.generateRevokeStatement(selectedOptions))
				return true;
		} 
		return false;
	}
	/**
	 * This method is here to support callback. The one in GenericDeltaDdlGenerator does not support callback.
	 * 
	 */
	protected String[] getCreateStatements(DDLGenerator gen, Map changeMap, IProgressMonitor monitor, IEngineeringCallBack callback) {
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
			return gen.createSQLObjects(d, this.generateQuotedIdentifiers(this.getSelectedOptions()), this.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, callback);
		}
		else {
			return new String[0];
		}
	}

	
	//@Override
	protected void analyze(Map changeMap) {
		List elements = new LinkedList();
		elements.addAll(changeMap.keySet());
		Iterator it = elements.iterator();
		while(it.hasNext()) {
			EObject e = (EObject) it.next();
			int flag = ((Integer) changeMap.get(e)).intValue();
			if(!needRecreate(e, flag)) {
				continue;
			}
			// Recreate required
			flag = setFlagsForRecreate(flag);
			if(e instanceof Column) {
				Column column = (Column) e;
				Table table = column.getTable();
	      		changeMap.put(table, new Integer(flag));
				if(table instanceof PersistentTable){
					processModifiedTable((PersistentTable)table, changeMap);
				}
			}
			else if(e instanceof PersistentTable) {
	      		changeMap.put(e, new Integer(flag));
				processModifiedTable((PersistentTable) e, changeMap);
			}
			else if(e instanceof UniqueConstraint) {
	      		changeMap.put(e, new Integer(flag));
				processModifiedUniqueConstraint((UniqueConstraint) e, changeMap);				
			}
			else if(e instanceof Index) {
	      		changeMap.put(e, new Integer(flag));
				processModifiedIndex((Index) e, changeMap);				
			}
			else {
	      		changeMap.put(e, new Integer(flag));
			}
		}
	}

	/**
	 * This method takes an existing changeFlag value and modifies it
	 * to be a drop and recreate instead of an alter.  This implementation
	 * retains the COMMENT and LABEL flags only.
	 * @param flag changeFlag
	 * @return int changeFlag
	 */
	protected int setFlagsForRecreate(int flag) {
		// Data object must be dropped and recreated
		//   COMMENT and LABEL flags need to be retained
		//   DROP and CREATE flags need to be set
		//   ALL OTHER flags need to be reset
		flag = flag & (COMMENT | LABEL);
		flag = flag | CREATE | DROP;
		return flag;
	}
	
	Pattern[] ALTER_PATTERNS;
	
	// the following patterns are REs (Regular Expressions). If any of the REs match a 
	// given DDL Statement, then that statement does not require a DROP/CREATE of the
	// impacted objects.
	private final String[] ALTER_STRINGS=
		{
			"^alter table .* (drop|add) constraint",//$NON-NLS-1$ //wsdbu00297616: don't recreate objects on ADD or DROP constraints
			"^alter table .*\\s*compress (yes|no)", //$NON-NLS-1$ //wsdbu00297613
			"^alter table .*\\s*(activate|deactivate)", //$NON-NLS-1$
			"^alter tablespace ", //$NON-NLS-1$ //wsdbu00297630			
		};
	
	protected synchronized Pattern[] getAlterPatterns()
	{
		if (null==ALTER_PATTERNS)
		{
			ALTER_PATTERNS=new Pattern[ALTER_STRINGS.length];
			for (int i=0; i<ALTER_STRINGS.length; ++i)
			{
				ALTER_PATTERNS[i]=Pattern.compile(ALTER_STRINGS[i], Pattern.CASE_INSENSITIVE);
			}
		}
		return ALTER_PATTERNS;
	}

	/**
	 * Analyze the impacts of the statements on the dependents.
	 * Returns the dependents that may be impacted.
	 * @param dependents
	 * @param statements
	 * @return
	 */
	protected SQLObject[] analyzeImpacts(SQLObject[] dependents,
			String[] statements) {
		// Here is a simple implementation. It only looks for any DDL
		// that is not a COMMENT ON, REVOKE, or GRANT statement
		// and determines there will be impacts on every dependent.
		boolean hasImpact = false;
		testAlter: for (int i = 0; i < statements.length; i++) {
			if (statements[i].startsWith("COMMENT ON") || //$NON-NLS-1$
					statements[i].startsWith("REVOKE") || //$NON-NLS-1$
					statements[i].startsWith("GRANT")) { //$NON-NLS-1$
				continue;
			}
			for (Pattern pattern: getAlterPatterns())
			{
				Matcher matcher=pattern.matcher(statements[i]);
				if (matcher.find()) continue testAlter;
			}
				
			// If we have fallen through to here, all the exceptions are not true, so me must
			// deal with the impacted objects.
			hasImpact = true;
			break;
		}
		if (hasImpact) {
			return dependents;
		} else {
			return new SQLObject[0];
		}
	}
	
	protected boolean isAncestorModified(Map changeMap, EObject e) {
		if (isRenameTableSupported()) {
			Iterator it = ContainmentServiceImpl.INSTANCE.getAllContainers(e).iterator();
			while(it.hasNext()) {
				Object c = it.next();
				if(changeMap.containsKey(c)) {
					int flag = ((Integer) changeMap.get(c)).intValue();
					if((flag & (CREATE | DROP | MODIFIED)) != 0) return true;
					if (!(c instanceof PersistentTable) &&
							((flag & RENAME) != 0)) return true;
				}
			}
			return false;
		}
		return super.ancestorModified(changeMap, e);
	}

	protected boolean isRenameTableSupported() {
		return false;
	}
	
	protected boolean isRenamedTable(EObject e,int flag) {
		if (((flag & RENAME) != 0) &&
				SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(e.eClass()))
			return true;
		return false;
	}
	
	protected void processModifiedUniqueConstraint(UniqueConstraint uk, Map changeMap) {
		Iterator it = uk.getForeignKey().iterator();
		while(it.hasNext()) {
			ForeignKey fk = (ForeignKey) it.next();
			if (isCreated(fk)) continue;
			int flag = 0;
			if(changeMap.containsKey(fk)) {
				flag = ((Integer) changeMap.get(fk)).intValue();
			}
			flag = flag & ~(MODIFIED | RENAME);
			flag = flag | (CREATE | DROP);
			changeMap.put(fk, new Integer(flag));
		}
	}
		
	private void processModifiedIndex(Index index, Map changeMap) {
		Iterator it = index.getForeignKey().iterator();
		while(it.hasNext()) {
			ForeignKey fk = (ForeignKey) it.next();
			if (isCreated(fk)) continue;
			int flag = 0;
			if(changeMap.containsKey(fk)) {
				flag = ((Integer) changeMap.get(fk)).intValue();
			}
			flag = flag & ~(MODIFIED | RENAME);
			flag = flag | (CREATE | DROP);
			changeMap.put(fk, new Integer(flag));
		}
	}

	private void processModifiedTable(PersistentTable table, Map changeMap) {
		Iterator it = table.getUniqueConstraints().iterator();
		while(it.hasNext()) {
			UniqueConstraint uk = (UniqueConstraint) it.next();
			if (isCreated(uk)) continue;
			int flag = 0;
			if(changeMap.containsKey(uk)) {
				flag = ((Integer) changeMap.get(uk)).intValue();
			}
			flag = flag & ~(MODIFIED | RENAME);
			flag = flag | (CREATE | DROP);
			changeMap.put(uk, new Integer(flag));
			processModifiedUniqueConstraint(uk, changeMap);
		}
		
		it = table.getIndex().iterator();
		while(it.hasNext()) {
			Index index = (Index) it.next();
			if (isCreated(index)) continue;
			int flag = 0;
			if(changeMap.containsKey(index)) {
				flag = ((Integer) changeMap.get(index)).intValue();
			}
			flag = modifyIndexChangeFlag(index,changeMap,flag);
			changeMap.put(index, new Integer(flag));
			processModifiedIndex(index, changeMap);
		}

		it = table.getForeignKeys().iterator();
		while(it.hasNext()) {
			ForeignKey fk = (ForeignKey) it.next();
			if (isCreated(fk)) continue;
			int flag = 0;
			if(changeMap.containsKey(fk)) {
				flag = ((Integer) changeMap.get(fk)).intValue();
			}
			flag = flag & ~(MODIFIED | RENAME);
			flag = flag | (CREATE | DROP);
			changeMap.put(fk, new Integer(flag));
		}

		it = table.getReferencingForeignKeys().iterator();
		while(it.hasNext()) {
			ForeignKey fk = (ForeignKey) it.next();
			if (isCreated(fk)) continue;
			int flag = 0;
			if(changeMap.containsKey(fk)) {
				flag = ((Integer) changeMap.get(fk)).intValue();
			}
			flag = flag & ~(MODIFIED | RENAME);
			flag = flag | (CREATE | DROP);
			changeMap.put(fk, new Integer(flag));
		}

		it = table.getConstraints().iterator();
		while(it.hasNext()) {
			Constraint ck = (Constraint) it.next();
			if (!SQLConstraintsPackage.eINSTANCE.getCheckConstraint().isSuperTypeOf(ck.eClass())) continue;
			if (isCreated(ck)) continue;
			int flag = 0;
			if(changeMap.containsKey(ck)) {
				flag = ((Integer) changeMap.get(ck)).intValue();
			}
			flag = flag & ~(MODIFIED | RENAME);
			flag = flag | (CREATE | DROP);
			changeMap.put(ck, new Integer(flag));
		}

		it = table.getTriggers().iterator();
		while(it.hasNext()) {
			Trigger trigger = (Trigger) it.next();
			if (isCreated(trigger)) continue;
			int flag = 0;
			if(changeMap.containsKey(trigger)) {
				flag = ((Integer) changeMap.get(trigger)).intValue();
			}
			flag = flag & ~(MODIFIED | RENAME);
			flag = flag | (CREATE | DROP);
			changeMap.put(trigger, new Integer(flag));
		}
		
		it = table.getColumns().iterator();
		while (it.hasNext()) {
			Column column = (Column)it.next();
			if (hasDescription(column)) {
				Integer flag = 0;
				if(changeMap.containsKey(column)) {
					flag = (Integer)changeMap.get(column);
				}
				flag = flag | COMMENT;
				changeMap.put(column, flag);
			}
		}
		// Find views with dependencies 
		List<ViewTable> impactedViews = getDependentViews(table);
		for (ViewTable view:impactedViews) {
			int flag = 0;
			if(changeMap.containsKey(view)) {
				flag = ((Integer) changeMap.get(view)).intValue();
			}
			flag = flag & ~(MODIFIED | RENAME);
			flag = flag | (CREATE | DROP);
			changeMap.put(view, new Integer(flag));
		}
	}

	protected List<ViewTable> getDependentViews(Table table) {
        List<ViewTable> impactedViews = new ArrayList<ViewTable>();
        
        DependencyImpactDescription[] impacts = DependencyImpactAnalyst.getInstance().getDirectImpacted(table);
        for (int i=0; i<impacts.length; i++)
        {
        	EObject target = impacts[i].getTarget();
        	if ( (target != null) && (target instanceof ViewTable)) {
        		impactedViews.add((ViewTable)target);
        	}
        }
		return impactedViews;
	}
	
	protected int modifyIndexChangeFlag(Index index,Map changeMap,int flag) {
		flag = flag & ~(MODIFIED | RENAME);
		flag = flag | (CREATE | DROP);
		return flag;
	}

	protected void suppressCreate(SQLObject element) {
		if (suppressCreateElementSet  == null)
			suppressCreateElementSet = new HashSet<SQLObject>();
		suppressCreateElementSet.add(element);
	}
	
    protected void propagateColumnRename(Column column, Map changeMap) {
		Table table = column.getTable();
		Set<UniqueConstraint> uCs = new HashSet<UniqueConstraint>();
		Set<Index> uIs = new HashSet<Index>();
		if ((table == null) || 
				!SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(table.eClass())) 
			return;
    	// Unique Constraints
		Iterator it = ((PersistentTable)table).getUniqueConstraints().iterator();
		while( it.hasNext() ) {
			UniqueConstraint current = (UniqueConstraint)it.next();
			if (current != null) {
				EList columns = current.getMembers();
				if ( (columns != null) && columns.contains(column) ) {
					updateMapFlags(current,changeMap,0,DROP | CREATE);
					uCs.add(current);
				}
			}
		}
		// PK
		{
			PrimaryKey current = ((PersistentTable)table).getPrimaryKey();
			if (current != null) {
				EList columns = current.getMembers();
				if ( (columns != null) && columns.contains(column) ) {
					updateMapFlags(current,changeMap,0,DROP | CREATE);
					uCs.add(current);
				}
			}
		}
    	// Indexes
		it = ((PersistentTable)table).getIndex().iterator();
		while( it.hasNext() ) {
			Index current = (Index)it.next();
			if (current != null) {
				EList columns = current.getMembers();
				if ( indexMemberListContains(columns, column) ) {
					updateMapFlags(current,changeMap,0,DROP | CREATE);
					if (current.isUnique()) {
						uIs.add(current);
					}
					continue;
				}
				columns = current.getIncludedMembers();
				if ( indexMemberListContains(columns, column) ) {
					updateMapFlags(current,changeMap,0,DROP | CREATE);
				}
			}
		}
    	// FKs
		it = ((PersistentTable)table).getForeignKeys().iterator();
		while( it.hasNext() ) {
			ForeignKey current = (ForeignKey)it.next();
			if (current != null) {
				EList columns = current.getMembers();
				if ( (columns != null) && columns.contains(column) ) {
					updateMapFlags(current,changeMap,0,DROP | CREATE);
				}
			}
		}
    	// Referencing FKs
		it = ((PersistentTable)table).getReferencingForeignKeys().iterator();
		RefFkLoop: while( it.hasNext() ) {
			ForeignKey current = (ForeignKey)it.next();
			if (current != null) {
				Iterator<UniqueConstraint> it1 = uCs.iterator();
				while (it1.hasNext()) {
					if (current.getUniqueConstraint() == it1.next()) {
						updateMapFlags(current,changeMap,0,DROP | CREATE);
						continue RefFkLoop;
					}
				}
				Iterator<Index> it2 = uIs.iterator();
				while (it2.hasNext()) {
					if (current.getUniqueIndex() == it2.next()) {
						updateMapFlags(current,changeMap,0,DROP | CREATE);
						continue RefFkLoop;
					}
				}
				EList columns = current.getReferencedMembers();
				if ( (columns != null) && columns.contains(column) ) {
					updateMapFlags(current,changeMap,0,DROP | CREATE);
				}
			}
		}
    	// Triggers
		it = ((PersistentTable)table).getTriggers().iterator();
		while( it.hasNext() ) {
			Trigger current = (Trigger)it.next();
			if (current != null) {
				EList columns = current.getTriggerColumn();
				if ( (columns != null) && columns.contains(column) ) {
					updateMapFlags(current,changeMap,0,DROP | CREATE);
				}
			}
		}
    }
	
    /**
     * Implementation of contains(column) for index member column list - since the
     * list that is returned by the getMembers method is a list of IndexMember
     * objects we can't use the contains method of the list. The comparison is done
     * using the == operator.
     * 
     * @param columns The columns members of the list (as IndexMember objects)
     * @param column The table column for which membership is to be tested
     * @return true if the index member list includes column
     */
    protected boolean indexMemberListContains(EList columns, Column column) {
		if (columns != null && column != null) {
			for (Object indexMember : columns) {
				if (indexMember != null && indexMember instanceof IndexMember) {
					if (((IndexMember)indexMember).getColumn() == column) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	protected void removeRedundantColumnChange(Map changeMap, Map colMap) {
		int flag;
	    Iterator it = colMap.keySet().iterator();
		Table table = null;
        while (it.hasNext()) {
        	Column column = (Column)it.next();
        	flag = ((Integer) colMap.get(column)).intValue();
        	if (flag == DROP) {
        		EObject obj = ((ChangeDescriptionImpl) changeDescription).getOldContainer(column);
        		if (SQLTablesPackage.eINSTANCE.getTable().isSuperTypeOf(obj.eClass())) 
        			table = (Table)((ChangeDescriptionImpl) changeDescription).getOldContainer(column);
        	}
        	if (table == null)
        		table = column.getTable();
    		if (SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(table.eClass())) {
        		if(changeMap.containsKey(table)) {
        			flag = ((Integer) changeMap.get(table)).intValue();
        			if ((flag & DROP) != 0) {
        				it.remove();
        			}
        		}
        	}
        }
	}

	/**
	 * This method is here to support callback. The one in GenericDeltaDdlGenerator does not support callback. 
	 */
	protected String[] processChangeMap(Map changeMap, IProgressMonitor monitor, IEngineeringCallBack callback) {
        DDLGenerator ddlGenerator = getDdlGeneratorWithDeltaDDLOptions();
        this.undo();
        String[] drops = new String[0];
        String[] creates = new String[0];
        
        if (EngineeringOptionID.generateDropStatement(selectedOptions))
          drops = getDropStatements(ddlGenerator, changeMap, monitor, callback);
        this.redo();
        if (EngineeringOptionID.generateCreateStatement(selectedOptions))
          creates = getCreateStatements(ddlGenerator, changeMap, monitor, callback);
        this.changeDescription = null;
        return merge(drops, creates);		        
	}

    protected boolean generateQuotedIdentifiers(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER, options);
    }

    protected boolean generateFullyQualifiedNames(EngineeringOption[] options) {
        return getOptionValueByID(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME, options);
    }

    private boolean getOptionValueByID(String optionID, EngineeringOption[] options){
    	return EngineeringOptionID.getOptionValueByID(optionID, options);
    }

	/**
	 * This generateDeltaDDL method also generates DDL for the impacted (dependent) database objects.
	 * @param changeSummary
	 * @param impacts
	 * @param monitor
	 * @return
	 * @deprecated
	 */
	public String[] generateDeltaDDL(EObject rootObject, ChangeDescription changeDescription, SQLObject[] impacts, IProgressMonitor monitor) {		
        return this.generateDeltaDDL(rootObject, changeDescription, impacts, monitor, null);
	}

	/**
	 * Get the SQL statements for dropping the dependents (impacted objects).
	 * @param changeDescription
	 * @param impacts
	 * @param monitor
	 * @param callback
	 * @return
	 */
	protected String[] getDependentsDropStatements(
			ChangeDescription changeDescription, SQLObject[] impacts,
			IProgressMonitor monitor, IEngineeringCallBack callback) {
		CoreDdlGenerator generator = (CoreDdlGenerator)this.getDdlGeneratorWithDeltaDDLOptions();
		if(impacts.length > 0) {
			generator.getSelectedOptions(impacts);
			for (int count = 0; count < impacts.length; count++) {
				if (SQLTablesPackage.eINSTANCE.getPersistentTable().isSuperTypeOf(impacts[count].eClass())) {
					this.setDestructive(true);
				}
			}
			return generator.dropSQLObjects(impacts,EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions()), EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, callback);
		}
		else {
			return new String[0];
		}
	}

	/**
	 * Get the SQL statements for creating the dependents (impacted objects).
	 * @param changeDescription
	 * @param impacts
	 * @param monitor
	 * @param callback	 * 
	 * @return
	 * @deprecated Use the alternative getDependentsCreateStatements.
	 */
	protected final String[] getDependentsCreateStatements(ChangeDescription changeDescription,  SQLObject[] impacts, IProgressMonitor monitor) {
		CoreDdlGenerator generator = (CoreDdlGenerator)this.getDdlGeneratorWithDeltaDDLOptions();
		if(impacts.length > 0) {
			generator.getSelectedOptions(impacts);
			return generator.createSQLObjects(impacts, EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions()), EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, null);
		}
		else {
			return new String[0];
		}
	}

	/**
	 * Get the SQL statements for creating the dependents (impacted objects).
	 * @param changeDescription
	 * @param impacts
	 * @param monitor
	 * @param callback
	 * @return
	 */
	protected String[] getDependentsCreateStatements(
			ChangeDescription changeDescription, SQLObject[] impacts,
			IProgressMonitor monitor, IEngineeringCallBack callback) {
		CoreDdlGenerator generator = (CoreDdlGenerator)this.getDdlGeneratorWithDeltaDDLOptions();
		if(impacts.length > 0) {
			generator.getSelectedOptions(impacts);
			return generator.createSQLObjects(impacts, EngineeringOptionID.generateQuotedIdentifiers(this.getSelectedOptions()), EngineeringOptionID.generateFullyQualifiedNames(this.getSelectedOptions()), monitor, callback);
		}
		else {
			return new String[0];
		}
	}

	public Table getOldContainer(Column column) {
		return (Table)((ChangeDescriptionImpl)this.changeDescription).getOldContainer(column);
	}

	protected boolean isColumnInPrimaryKey(Column column) {
		Table table = column.getTable();
		if (table instanceof BaseTable) {
			PrimaryKey key = (PrimaryKey)(((BaseTable)table).getPrimaryKey());
			if (key != null && key.getMembers().contains(column)) return true;
		}
		return false;
	}
	
	public boolean isDataPreservationRequired() {
		return dataPreservationRequired;
	}
	
	/**
	 * Given a DDL generator and a list of SQL objects, ask the DDL generator
	 * to set up a list of DDL generation options. Then change two of
	 * the options to true. The client can further modify the values of the
	 * options.
	 * @param ddlGenerator A DDL generator
	 * @param sqlObjects An array of SQLObjects
	 * @return An array of DDL generation options.
	 */
	public EngineeringOption[] getDdlGeneratorOptionsForDeltaDdl(DDLGenerator ddlGenerator, SQLObject[] sqlObjects) {
		EngineeringOption[] ddlGenOptions = ddlGenerator.getOptions(sqlObjects);
    	for (int i = 0; i < ddlGenOptions.length; i++){
            EngineeringOption option = (EngineeringOption) ddlGenOptions[i];
            if (option == null) {
           	 	continue;
            }
            if (option.getId().equals(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME)) { 
                option.setBoolean(DdlGenerationUtility.getQualifyNamesDefault());
            }
            else if (option.getId().equals(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER)) {
            	option.setBoolean(DdlGenerationUtility.getQuoteIdentifiersDefault());
            }
        }
    	return ddlGenOptions;
	}

	/**
	 * Check to see if there are column order changes.
	 * Returns 0 if no changes to the columns, otherwise return MODIFIED.
	 * @param element
	 * @param feature
	 * @return
	 */
	protected int getColumnsChangeFlag(EObject element, EStructuralFeature feature) {
		if (feature == SQLTablesPackage.eINSTANCE.getTable_Columns()) {
			List currentValue = (List)element.eGet(feature);
			List previousValue = (List)this.getOldValue(feature, element);
			if (isOrderChanged(currentValue, previousValue)) {
				return MODIFIED;
			}
		}
		return 0;
	}

	/**
	 * Updates the passed map (where the map's key is a changed element, and the
	 * map's value is the changeFlags), using the passed parameters.
	 * @param element EObject which has changed.
	 * @param map Map which should maintain the change flags for this element.
	 * @param flagsToReset int containing change flags to be reset.
	 * @param flagsToSet int containing change flags to set.
	 * @return int the new set of change flags stored for the element.
	 */
	protected int updateMapFlags(EObject element,Map map,int flagsToReset,int flagsToSet) {
		int flags = 0;
		if (map.containsKey(element)) {
			flags = ((Integer) map.get(element)).intValue();
			if (flagsToReset != 0) 
				flags &= ~flagsToReset;
		}
		if (flagsToSet != 0) 
			flags |= flagsToSet;
		map.put(element,new Integer(flags));
		return flags;
	}

	protected boolean isRenameColumnSupported() {
		return false;
	}
	
	/**
	 * Returns true if the order in current is different than that in previous.
	 * The current list derives from the previous list through add, delete, or modification.
	 * @param current A list of SQL objects.
	 * @param previous A list of SQL objects.
	 * @return
	 */
	private boolean isOrderChanged (List current, List previous) {
		ArrayList currentList = new ArrayList(current.size());				
		for (Iterator it = current.iterator(); it.hasNext();) {
			SQLObject object = (SQLObject)it.next();
			currentList.add(object);
		}		
		ArrayList previousList = new ArrayList(previous.size());
		for (Iterator it = previous.iterator(); it.hasNext();) {
			SQLObject object = (SQLObject)it.next();
			previousList.add(object);
		}

		// We should tolerate columns being dropped, and columns being
		//   added at the END of the list, but we should not tolerate
		//   columns being added in the middle of the list
		
		// Remove columns which have been dropped (present only in previous
		//   list)
		for (int index = 0; index < previousList.size(); ++index) {
			if (!currentList.contains(previousList.get(index))) {
				previousList.remove(index--);
			}
		}

		// Previous list should now be same or shorter size than current
		//   list.  Compare the first N columns, where N is the size of
		//   the possibly shortened previous list.
		for (int index = 0; index < previousList.size(); ++index) {
			if (currentList.get(index) != previousList.get(index)) return true;
		}
		return false;
	}
	/**
	 * This method returns the DeltaDDLGenerator options
	 * 
	 * @return selectedOptions
	 */
	public EngineeringOption[] getSelectedOptions() {
		return selectedOptions;
	}

	/**
	 * Sets the options for Delta DDL generation
	 * @param selectedOptions
	 */
	public void setSelectedOptions(EngineeringOption[] selectedOptions) {
		this.selectedOptions = selectedOptions;
	}
}
