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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class DerbyTableConstraintLoader extends JDBCTableConstraintLoader {
	
	public DerbyTableConstraintLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableConstraintLoader#loadForeignKeys(java.util.List, java.util.Collection)
	 */
	public void loadForeignKeys(List containmentList, Collection existingFKs) throws SQLException {
		super.loadForeignKeys(containmentList, existingFKs);
		loadCheckConstraint(getCatalogObject().getConnection(), containmentList, this.getTable());
	}

	private void loadCheckConstraint(Connection connection, List containmentList, Table table){
		final String query = "SELECT CONSTRAINTNAME,CHECKDEFINITION" + //$NON-NLS-1$
							" FROM SYS.SYSCONSTRAINTS A,SYS.SYSSCHEMAS B, SYS.SYSTABLES C, SYS.SYSCHECKS D"+ //$NON-NLS-1$
							" WHERE A.TYPE='C'" + //$NON-NLS-1$
							" AND A.SCHEMAID=B.SCHEMAID" + //$NON-NLS-1$
							" AND B.SCHEMANAME='"+ table.getSchema().getName()+"'" +  //$NON-NLS-1$//$NON-NLS-2$
							" AND A.TABLEID= C.TABLEID" + //$NON-NLS-1$
							" AND C.TABLENAME='"+ table.getName()+"'" +  //$NON-NLS-1$//$NON-NLS-2$
							" AND A.CONSTRAINTID=D.CONSTRAINTID"; //$NON-NLS-1$
		try {

			
			Statement s = connection.createStatement();
			String currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
			ResultSet r = s.executeQuery(query); 
			while(r.next()) {
				String check_name = r.getString("CONSTRAINTNAME"); //$NON-NLS-1$
				DerbyCatalogCheckConstraint check= new DerbyCatalogCheckConstraint();
				check.setName(check_name);
				containmentList.add(check);
			}
			r.close();
			DerbySchemaLoader.setSchema(s, currentSchema);
			s.close();
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
