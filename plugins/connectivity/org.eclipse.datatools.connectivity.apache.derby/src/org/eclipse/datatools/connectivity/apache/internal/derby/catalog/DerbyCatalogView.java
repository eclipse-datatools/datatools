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

import org.eclipse.datatools.connectivity.apache.internal.derby.ddl.DerbyDdlParser;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCView;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.CheckType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

public class DerbyCatalogView extends JDBCView {
	private static final long serialVersionUID = 3258125856181269553L;
	
	public void refresh() {
		this.viewLoaded = false;
		super.refresh();
	}
	
	public QueryExpression getQueryExpression() {
		if(!this.viewLoaded) this.loadView();
		return this.queryExpression;
	}
	
	public CheckType getCheckType() {
		if(!this.viewLoaded) this.loadView();
		return this.checkType;
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLTablesPackage.VIEW_TABLE__COLUMNS) {
			this.getColumns();
		}
		if(id == SQLTablesPackage.VIEW_TABLE__CHECK_TYPE) {
			this.getCheckType();
		}
		if(id == SQLTablesPackage.VIEW_TABLE__QUERY_EXPRESSION) {
			this.getQueryExpression();
		}
		return super.eIsSet(eFeature);
	}
	
	private synchronized void loadView() {
		if(this.viewLoaded) return;
		Connection connection = this.getConnection();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		
		try {
			Statement s = connection.createStatement();
			String currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
			String query=" SELECT CHECKOPTION, VIEWDEFINITION" + //$NON-NLS-1$
						" FROM SYS.SYSVIEWS A, SYS.SYSTABLES B, SYS.SYSSCHEMAS C" + //$NON-NLS-1$
						" WHERE A.TABLEID=B.TABLEID"+ //$NON-NLS-1$
						" AND B.TABLENAME='"+ this.getName() +"'"+ //$NON-NLS-1$ //$NON-NLS-2$
						" AND B.SCHEMAID=C.SCHEMAID"+ //$NON-NLS-1$
						" AND C.SCHEMANAME='"+this.getSchema().getName()+"'";  //$NON-NLS-1$//$NON-NLS-2$
			ResultSet r = s.executeQuery(query);
			while (r.next()){
				final String viewCheck = r.getString("CHECKOPTION"); //$NON-NLS-1$
				if(viewCheck.equals("N")) this.setCheckType(CheckType.NONE_LITERAL); //$NON-NLS-1$
				else if(viewCheck.equals("L")) this.setCheckType(CheckType.LOCAL_LITERAL); //$NON-NLS-1$
				else if(viewCheck.equals("C")) this.setCheckType(CheckType.CASCADED_LITERAL); //$NON-NLS-1$
	
				final String text = r.getString("VIEWDEFINITION"); //$NON-NLS-1$
				DerbyDdlParser ddlParser = new DerbyDdlParser(this.getDatabaseDefinition());
				ddlParser.parseView(this,text);
				
				
			}
			this.viewLoaded = true;
			r.close();
			DerbySchemaLoader.setSchema(s, currentSchema);
			s.close();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		this.eSetDeliver(deliver);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable#getSupertable()
	 */
	public Table getSupertable() {
		return null;
	}

	private DatabaseDefinition getDatabaseDefinition() {
		Database d = this.getSchema().getCatalog().getDatabase();
		return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(d);
	}
	
	private boolean viewLoaded= false;
}
