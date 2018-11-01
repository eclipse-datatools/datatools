/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui.actions;

import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.common.ui.actions.CommonSQLObjectAction;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IActionDelegate;

/**
 * 
 * @author Hui Cao
 * 
 */
public abstract class RoutineAction extends CommonSQLObjectAction implements IActionDelegate {

	/**
	 * This field is to identify the catalog database name. If the database
	 * server does not support catalog, this field is useless.
	 */
	protected String _dbName = null;
	/**
	 * 
	 */
	public RoutineAction() {
	}

	/**
	 * Constructs a CommonSQLObjectAction from the selected resource. This
	 * happens when this action is instantiated by the common action provider.
	 */
	public RoutineAction(Object selectedResource) {
		initSQLObject(this, selectedResource);
		initConnectionProfile();
	}

	protected void initSQLObject(IAction action, Object selectedResource) {
		if (selectedResource instanceof Trigger) {
			Trigger trigger = (Trigger) selectedResource;
			_sqlObject = trigger;
			Schema schema = (trigger).getSchema();
			_database = schema.getDatabase();
			//TODO MO fix this special treatment for catalog database
			initCatalogDatabase(schema);
			action.setEnabled(true);
		} else if (selectedResource instanceof Routine) {
			Routine routine = (Routine) selectedResource;
			_sqlObject = routine;
			Schema schema = (routine).getSchema();
			_database = schema.getDatabase();
			initCatalogDatabase(schema);
			action.setEnabled(true);
		}else if (selectedResource instanceof Event)
		{
			_sqlObject = (Event) selectedResource;
			_database = ((Event) selectedResource)
					.getDatabase();
			action.setEnabled(true);
		}
        
        if (selectedResource instanceof EObject)
        {
            _dbName = ModelUtil.getDatabaseName((EObject)selectedResource);
        }
		// TODO check Routine.sourceVisible
	}

	private void initCatalogDatabase(Schema schema) {
		
		_database = ModelUtil.getDatabase(schema);
	}
	
	/**
	 * Returns _dbName if it's not null. Otherwise return the Database object name.
	 */
	protected String getDatabaseName()
	{
		return _dbName;
	}
	
}