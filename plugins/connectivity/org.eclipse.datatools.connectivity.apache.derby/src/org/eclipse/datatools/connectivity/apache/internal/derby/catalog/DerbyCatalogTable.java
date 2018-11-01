/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.catalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementDefault;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;
import org.eclipse.datatools.modelbase.sql.tables.ActionGranularityType;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.ibm.icu.util.StringTokenizer;

public class DerbyCatalogTable extends JDBCTable {

	private static final long serialVersionUID = 1426200108473580544L;

	private boolean triggerLoaded = false;

	public DerbyCatalogTable() {
		super();
	}

	public synchronized void refresh() {
		if(this.triggerLoaded) this.triggerLoaded = false;
		super.refresh();
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if ( id == SQLTablesPackage.TABLE__TRIGGERS ) {
			this.getTriggers();
		}
		return super.eIsSet(eFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable#createConstraintLoader()
	 */
	protected JDBCTableConstraintLoader createConstraintLoader() {
		return new DerbyTableConstraintLoader(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable#getSupertable()
	 */
	public Table getSupertable() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable#createColumnLoader()
	 */
	protected JDBCTableColumnLoader createColumnLoader() {
		return new DerbyTableColumnLoader(this);
	}

	private Schema getSchema(String schemaName) {
		Schema s = this.getSchema();
		if(s.getName().equals(schemaName)) return s;
		Database d = s.getCatalog().getDatabase();
		Iterator it = d.getSchemas().iterator();
		while(it.hasNext()) {
			s = (Schema) it.next();
			if(s.getName().equals(schemaName)) return s;
		}
		Schema schema = new DerbyCatalogSchema();
		schema.setName(schemaName);
		schema.setCatalog(s.getCatalog());
		schema.setDatabase(d);
		
		return schema;
	}

	private synchronized void loadTriggers(){
		if(this.triggerLoaded) return;
		EList triggerList = super.getTriggers();
		Object[] list = triggerList.toArray();
		clearTriggers(triggerList);
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	

		try {
			DataModelElementFactory factory = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(this.getCatalogDatabase()).getDataModelElementFactory();

			Statement s = connection.createStatement();
			String currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
			final String query = "SELECT TRIGGERNAME, SCHEMANAME,EVENT,FIRINGTIME,TYPE,TRIGGERDEFINITION" + //$NON-NLS-1$
								",REFERENCINGOLD,REFERENCINGNEW,OLDREFERENCINGNAME,NEWREFERENCINGNAME,REFERENCEDCOLUMNS" + //$NON-NLS-1$
								" FROM SYS.SYSTRIGGERS A, SYS.SYSTABLES B, SYS.SYSSCHEMAS C"+ //$NON-NLS-1$
								" WHERE A.TABLEID=B.TABLEID"+ //$NON-NLS-1$
								" AND B.TABLENAME='"+ this.getName()+"'"+  //$NON-NLS-1$//$NON-NLS-2$
								" AND A.SCHEMAID=C.SCHEMAID"; //$NON-NLS-1$
			ResultSet r = s.executeQuery(query); 
			while(r.next()) {
				DerbyCatalogTrigger trigger;
				final String trigName = r.getString("TRIGGERNAME"); //$NON-NLS-1$

				Object element = findTrigger(list, trigName);
				
				if (element != null){
					trigger = (DerbyCatalogTrigger) element;
					((ICatalogObject)element).refresh();
				} else {
					trigger = new DerbyCatalogTrigger();
					trigger.setName(trigName);
				}	
				
				
				
				trigger.setName(trigName);
				
				final String trigSchema = r.getString("SCHEMANAME"); //$NON-NLS-1$
				trigger.setSchema(this.getSchema(trigSchema));

				final String trigTime = r.getString("FIRINGTIME"); //$NON-NLS-1$
				if(trigTime.equals("A")) trigger.setActionTime(ActionTimeType.AFTER_LITERAL); //$NON-NLS-1$
				else if(trigTime.equals("B")) trigger.setActionTime(ActionTimeType.BEFORE_LITERAL); //$NON-NLS-1$
				else if(trigTime.equals("I")) trigger.setActionTime(ActionTimeType.INSTEADOF_LITERAL); //$NON-NLS-1$
				else continue;
				
				final String trigEvent = r.getString("EVENT"); //$NON-NLS-1$
				if(trigEvent.equals("I")) trigger.setInsertType(true); //$NON-NLS-1$
				if(trigEvent.equals("D")) trigger.setDeleteType(true); //$NON-NLS-1$
				if(trigEvent.equals("U")) trigger.setUpdateType(true); //$NON-NLS-1$

				final String granularity = r.getString("TYPE"); //$NON-NLS-1$
				if(granularity.equals("S")) trigger.setActionGranularity(ActionGranularityType.STATEMENT_LITERAL); //$NON-NLS-1$
				else if(granularity.equals("R")) trigger.setActionGranularity(ActionGranularityType.ROW_LITERAL); //$NON-NLS-1$
				else continue;
				
				final boolean referencingOld = r.getBoolean("REFERENCINGOLD"); //$NON-NLS-1$
				if (referencingOld) {
					trigger.setOldRow(r.getString("OLDREFERENCINGNAME")); //$NON-NLS-1$
				}

				final boolean referencingNew = r.getBoolean("REFERENCINGNEW"); //$NON-NLS-1$
				if (referencingNew) {
					trigger.setNewRow(r.getString("NEWREFERENCINGNAME")); //$NON-NLS-1$
				}

				String columns = r.getString("REFERENCEDCOLUMNS"); //$NON-NLS-1$
				if (columns != null){
					EList updateColumns = trigger.getTriggerColumn();
		            EList columnList = this.getColumns();
		            columns = columns.substring(columns.indexOf("(")+1,columns.indexOf(")")); //$NON-NLS-1$ //$NON-NLS-2$
			        StringTokenizer tokenizer = new StringTokenizer(columns, ","); //$NON-NLS-1$
			        while (tokenizer.hasMoreTokens()) {
			            String token = tokenizer.nextToken().trim();
			            Column column = (Column) columnList.get(Integer.parseInt(token)-1);
			            updateColumns.add(column);
			        }
				}
				
				//body
				final String text = r.getString("TRIGGERDEFINITION"); //$NON-NLS-1$
				SQLStatement body = (SQLStatement) factory.create(SQLStatementsPackage.eINSTANCE.getSQLStatementDefault());
				((SQLStatementDefault)body).setSQL(text);
				trigger.getActionStatement().add(body);
				
				triggerList.add(trigger);
			}

			this.triggerLoaded = true;
			r.close();
			DerbySchemaLoader.setSchema(s, currentSchema);
			s.close();
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		
		this.eSetDeliver(deliver);
		
	}
	
	private void clearTriggers(EList triggerList) {
		for (Iterator it = triggerList.iterator(); it.hasNext();) {
			DerbyCatalogTrigger trigger = (DerbyCatalogTrigger)it.next();
			trigger.getActionStatement().clear();
			trigger.setSchema(null);
		}
		triggerList.clear();
	}

	public EList getTriggers() {
		if(!this.triggerLoaded) this.loadTriggers();
		return this.triggers;
	}

	protected Object findTrigger(Object[] list, String name){
		Object object = null;
		for (int i = 0; i < list.length; i++){
			SQLObject sqlObject = (SQLObject) list[i];
			if (sqlObject.getName().equals(name) && sqlObject.eClass() == SQLTablesPackage.eINSTANCE.getTrigger() ){
				object = list[i];
				break;
			}
		}
		return object;
	}
}
