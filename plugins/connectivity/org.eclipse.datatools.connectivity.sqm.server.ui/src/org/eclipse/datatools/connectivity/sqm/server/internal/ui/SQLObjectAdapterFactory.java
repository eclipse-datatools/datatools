/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.sqm.server.internal.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.datatools.modelbase.sql.accesscontrol.provider.SQLAccessControlItemProviderAdapterFactory;
import org.eclipse.datatools.modelbase.sql.constraints.provider.SQLConstraintsItemProviderAdapterFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.provider.SQLDataTypesItemProviderAdapterFactory;
import org.eclipse.datatools.modelbase.sql.expressions.provider.SQLExpressionsItemProviderAdapterFactory;
import org.eclipse.datatools.modelbase.sql.routines.provider.SQLRoutinesItemProviderAdapterFactory;
import org.eclipse.datatools.modelbase.sql.schema.provider.SQLSchemaItemProviderAdapterFactory;
import org.eclipse.datatools.modelbase.sql.statements.provider.SQLStatementsItemProviderAdapterFactory;
import org.eclipse.datatools.modelbase.sql.tables.provider.SQLTablesItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.ui.views.properties.IPropertySource;


public class SQLObjectAdapterFactory implements IAdapterFactory {
	
	private static Class[] sAdapterList = new Class[] {IPropertySource.class};
	private static AdapterFactoryContentProvider sAdapterFactory;

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		// RJC: Replace once EMF generated property adapters have been corrected.
		//return sAdapterFactory.getPropertySource(adaptableObject);
		return null;
	}

	public Class[] getAdapterList() {
		return sAdapterList;
	}
	
	static {
		ComposedAdapterFactory caf = new ComposedAdapterFactory();
		caf.addAdapterFactory(new SQLSchemaItemProviderAdapterFactory());
		caf.addAdapterFactory(new SQLTablesItemProviderAdapterFactory());
		caf.addAdapterFactory(new SQLRoutinesItemProviderAdapterFactory());
		caf.addAdapterFactory(new SQLDataTypesItemProviderAdapterFactory());
		caf.addAdapterFactory(new SQLConstraintsItemProviderAdapterFactory());
		caf.addAdapterFactory(new SQLAccessControlItemProviderAdapterFactory());
		caf.addAdapterFactory(new SQLExpressionsItemProviderAdapterFactory());
		caf.addAdapterFactory(new SQLStatementsItemProviderAdapterFactory());
		sAdapterFactory = new AdapterFactoryContentProvider(caf);
	}

}
