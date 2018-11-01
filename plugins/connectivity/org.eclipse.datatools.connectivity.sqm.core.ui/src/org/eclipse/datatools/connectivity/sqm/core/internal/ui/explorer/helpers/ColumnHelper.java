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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IColumnHelperService;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;


/**
 * @author ljulien
 */
public class ColumnHelper implements IColumnHelperService
{
    private DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
    private static final String BLANK = ""; //$NON-NLS-1$
	/**
	 * Will get the constraint associated with the parent Table
	 * @param column
	 * @param type - the type of constraint that we want PrimaryKey, ForeignKey...
	 * @return the constraints found
	 */
	private ReferenceConstraint[] getConstraints (Column column, Class type)
	{
		Table parentTable = column.getTable();
		List list = new ArrayList ();
		if (parentTable instanceof BaseTable)
		{	
			EList constraints = ((BaseTable)parentTable).getConstraints();
			for (Iterator i = constraints.iterator(); i.hasNext();)
			{
				Constraint constraint = (Constraint) i.next();
				if (type.isAssignableFrom(constraint.getClass()))
				{
					list.add (constraint);
				}
			}
		}
		return (ReferenceConstraint []) list.toArray(new ReferenceConstraint[list.size()]);
	}
	
	/**
	 * @param column
	 * @param type
	 * @return true if the column is a constraint of the given type
	 */
	private ReferenceConstraint getConstraint (Column column, Class type)
	{
		ReferenceConstraint [] constraints = getConstraints (column, type);
		for (int i = 0, n = constraints.length; i < n; i++)
		{
			for (Iterator iterator = constraints[i].getMembers().iterator(); iterator.hasNext();)
			{
				if (column.equals(iterator.next()))
				{
					return constraints[i];
				}
			}
		}
		return null;
	}
	
	/**
	 * @param column the foreign Key
	 * @return the parent table
	 */
	public Table getParentTableOfForeignKey (Column column)
	{
		ForeignKey constraint = (ForeignKey) getConstraint (column, ForeignKey.class);
		return constraint.getUniqueConstraint().getBaseTable();
	}
	
	/**
	 * @param column the column to look at
	 * @return True if the column is a primary key
	 */
	public boolean isPrimaryKey (Column column)
	{
		return getConstraint (column, PrimaryKey.class) != null;
	}
	
	/**
	 * @param column the column to look at
	 * @return true if the column is a foreign key
	 */
	public boolean isForeignKey (Column column)
	{
		return getConstraint (column, ForeignKey.class) != null;
	}
	
	/**
	 * @param column - The column that has been removed
	 * @param constraint - The Reference constraint to remove as this column was its last member
	 */
	private void removeConstraint (Column column, ReferenceConstraint constraint)
	{
		Table table = column.getTable();
		if (table instanceof BaseTable)
		{
			// ME TODO : Should we set the Resource to null ?
			((BaseTable)table).getConstraints().remove(constraint);
		}
	}

	public String getDataType (Column column)
	{
        Table table;
        Schema schema;
        Catalog catalog;
        Database database;
	    DataType datatype = column.getDataType();
        if ((table = column.getTable()) != null && (schema = table.getSchema()) != null && (((catalog = schema.getCatalog()) != null && (database = catalog.getDatabase()) != null) || (database = schema.getDatabase()) != null))
        {
	        DatabaseDefinition definition = dbRegistry.getDefinition(database);
	        if(datatype != null && datatype.getName() != null) 
	        {
				if (datatype instanceof PredefinedDataType) 
				{
					return definition.getPredefinedDataTypeFormattedName((PredefinedDataType)datatype);		
				}
				else 
				{
					return datatype.getName();
				}
	        }
        }
        return BLANK;
	}
	
	/**
	 * Will update all the reference constraints when a column has been deleted
	 * Might delete the constraint is the deleted column was the last member
	 * @param column - The deleted column
	 */
	public void updateConstraintsOnColumnDeleted (Column column)
	{
		ReferenceConstraint [] constraints = getConstraints(column, ReferenceConstraint.class);
		for (int i = 0, n = constraints.length; i < n; i++)
		{
			ReferenceConstraint constraint = constraints[i];
			EList members = constraint.getMembers();
			for (int j = 0; j < members.size(); j++)
			{
				Column currentColumn = (Column) members.get(j);
				if (currentColumn.equals(column))
				{
					members.remove(currentColumn);
					break;
				}
			}
			if (members.size() == 0)
			{
				removeConstraint (column, constraint);
			}
		} 
	}
}
