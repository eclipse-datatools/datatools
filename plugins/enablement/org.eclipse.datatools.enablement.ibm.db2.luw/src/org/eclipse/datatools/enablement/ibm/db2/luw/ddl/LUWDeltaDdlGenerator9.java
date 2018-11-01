/*******************************************************************************
 * Copyright (c) 2009, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.ddl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTable;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.impl.ChangeDescriptionImpl;

public class LUWDeltaDdlGenerator9 extends LUWDeltaDdlGenerator {

	public LUWDeltaDdlGenerator9() {
		super();
		builder = new LUWDdlBuilder9();
	}

	protected boolean isTransferOwnershipSupported() {
		// Support added in v9
		return true;
	}

	protected boolean analyzeDropColumnAndContinue(Column column, Map changeMap, Iterator it) {
		int flag;
		Table table = (Table)((ChangeDescriptionImpl) changeDescription).getOldContainer(column);
		if (table == null) {
			changeMap.remove(column);
			it.remove();
			return true;
		}
		if (LUWPackage.eINSTANCE.getLUWTable().isSuperTypeOf(table.eClass()) &&
				!(LUWPackage.eINSTANCE.getLUWNickname().isSuperTypeOf(table.eClass()))) {
			if(changeMap.containsKey(table)) {
				flag = ((Integer) changeMap.get(table)).intValue();
				if ((flag & (DROP | BACKUP | MODIFIED)) != 0) {
					changeMap.remove(column);
					it.remove();
					return true;
				}
			}
			if (containsXMLColumn((LUWTable)table)) {
				changeMap.put(table, new Integer(MODIFIED));
				changeMap.remove(column);
				it.remove();
				return true;
			}
		}
		return false;
	}

	protected int getColumnDatatypeChangeFlag(Column column) {
		EObject table = column.eContainer();
		//According to DB2 LUW V9 SQL Reference: 
		//The column being altered cannot be part of a table containing an XML data type column (SQLSTATE 42997). 
		//The table cannot have data capture enabled (SQLSTATE 42997).
		if (table != null && 
				LUWPackage.eINSTANCE.getLUWTable().isSuperTypeOf(table.eClass()) &&
				containsXMLColumn((LUWTable)table)) {
			return MODIFIED;
		}
		return DATA_TYPE;
	}
	
	protected int getColumnNullableChangeFlag(Column column) {
		EObject table = column.eContainer();
		//According to DB2 LUW V9 SQL Reference: 
		//The column being altered cannot be part of a table containing an XML data type column (SQLSTATE 42997). 
		//The table cannot have data capture enabled (SQLSTATE 42997).
		if (table != null && 
				LUWPackage.eINSTANCE.getLUWTable().isSuperTypeOf(table.eClass()) &&
				containsXMLColumn((LUWTable)table)) {
			return MODIFIED;
		}
		return NULLABLE;
	}
	
	/**
	 * Returns true if the table contains a column of XML data type.
	 * @param table
	 * @return
	 */
	protected boolean containsXMLColumn(LUWTable table) {
		for (Iterator iter = table.getColumns().iterator(); iter.hasNext();) {
			Column column = (Column)iter.next();
			SQLDataType type = column.getContainedType();
			if (type != null && 
					SQLDataTypesPackage.eINSTANCE.getXMLDataType().isSuperTypeOf(type.eClass())) 
				return true;
		}
		List columns = (List)getOldValue(SQLTablesPackage.eINSTANCE.getTable_Columns(), table);
		for (Iterator iter = columns.iterator(); iter.hasNext();) {
			Column column = (Column)iter.next();
			SQLDataType type = column.getContainedType();
			if (type != null && 
					SQLDataTypesPackage.eINSTANCE.getXMLDataType().isSuperTypeOf(type.eClass())) 
				return true;
		}
		return false;
	}
}
