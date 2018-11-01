 /*******************************************************************************
  * Copyright (c) 2005, 2009 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License 2.0
  * which accompanies this distribution, and is available at
  * https://www.eclipse.org/legal/epl-2.0/
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *     Brianf - updates to get catalog loading working with filtering
  *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;

/**
 * This class is the Schema implementation, its purpose is to load columns,
 * constraint, index and primaryKeys
 */
public class MySqlCatalogTable extends JDBCTable {

	private static final long serialVersionUID = 3761127145711088689L;

	private String tableType;
	private boolean isAutoInc;
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if (id == SQLTablesPackage.PERSISTENT_TABLE__TRIGGERS) {
			this.getTriggers();
		}

		return super.eIsSet(eFeature);
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public boolean isAutoInc() {
		return isAutoInc;
	}

	public void setAutoInc(boolean autoInc) {
		isAutoInc = autoInc;
	}

	public EList getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentWithInverseEList(TableConstraint.class, this, SQLTablesPackage.BASE_TABLE__CONSTRAINTS, SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE);
		}
		return constraints;
	}

	public Index findIndexWithColumnName(String colName) {
		EList eList = this.getIndex();
		for (Iterator it = eList.iterator(); it.hasNext();) {
			MySqlCatalogIndex index = (MySqlCatalogIndex) it.next();
			EList list = index.getMembers();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				IndexMember member = (IndexMember) iter.next();
				if (member.getColumn().getName().equals(colName)) {
					return index;
				}
			}
		}
		return null;
	}

	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();		
	}

	protected void loadSupertable() {
		supertableLoaded = Boolean.FALSE;
	}
}
