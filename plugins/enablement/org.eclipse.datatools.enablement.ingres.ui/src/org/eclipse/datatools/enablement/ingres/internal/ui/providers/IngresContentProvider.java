/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.enablement.ingres.internal.ui.util.Messages;
import org.eclipse.datatools.enablement.ingres.internal.ui.virtual.DBEventsFolder;
import org.eclipse.datatools.enablement.ingres.internal.ui.virtual.ProcedureParameterFolder;
import org.eclipse.datatools.enablement.ingres.internal.ui.virtual.SynonymFolder;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresDBEvent;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSchema;
import org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresSynonym;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

/**
 * A content provider that supports various Ingres catalog objects.
 * 
 * @author enrico.schenk@ingres.com
 * 
 */
public class IngresContentProvider extends ServerExplorerContentProviderNav
		implements ICommonContentProvider {

	protected static final IVirtualNodeServiceFactory nodeFactory = IDataToolsUIServiceManager.INSTANCE
			.getVirtualNodeServiceFactory();

	protected static final ResourceLoader resourceLoader = ResourceLoader.INSTANCE;

	private static final String DEPENDENCY = resourceLoader
			.queryString("DATATOOLS.SERVER.UI.EXPLORER.DEPENDENCY"); //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IngresSchema) {
			IDBEventsFolder dbef = new DBEventsFolder(
					Messages.getString("DBEvent.Name"), Messages.getString("DBEvent.DisplayName"), //$NON-NLS-1$ //$NON-NLS-2$
					parentElement);
			ISynonymsFolder sf = new SynonymFolder(
					Messages.getString("Synonym.Name"), Messages.getString("Synonym.DisplayName"), //$NON-NLS-1$ //$NON-NLS-2$
					parentElement);
			return new Object[] { dbef, sf };
		} else if (parentElement instanceof IDBEventsFolder) {
			IDBEventsFolder dbef = (IDBEventsFolder) parentElement;
			IngresSchema schema = (IngresSchema) ((IDBEventsFolder) parentElement)
					.getParent();
			dbef.addChildren(schema.getDBEvents());
			return dbef.getChildrenArray();
		} else if (parentElement instanceof ISynonymsFolder) {
			ISynonymsFolder sf = (ISynonymsFolder) parentElement;
			IngresSchema schema = (IngresSchema) ((ISynonymsFolder) parentElement)
					.getParent();
			sf.addChildren(schema.getSynonyms());
			return sf.getChildrenArray();
		} else if (parentElement instanceof IngresDBEvent) {
			List collection = new ArrayList();
			collection.add(nodeFactory.makeDependencyNode(DEPENDENCY,
					DEPENDENCY, parentElement));
			if (parentElement instanceof IVirtualNode
					&& !((IVirtualNode) parentElement).hasChildren()) {
				((IVirtualNode) parentElement).addChildren(collection);
			}
			return collection.toArray(new Object[collection.size()]);
		} else if (parentElement instanceof IngresSynonym) {
			List collection = new ArrayList();
			collection.add(nodeFactory.makeDependencyNode(DEPENDENCY,
					DEPENDENCY, parentElement));
			if (parentElement instanceof IVirtualNode
					&& !((IVirtualNode) parentElement).hasChildren()) {
				((IVirtualNode) parentElement).addChildren(collection);
			}
			return collection.toArray(new Object[collection.size()]);
		} else if (parentElement instanceof Routine) {
			IProcedureParameterFolder pcf = new ProcedureParameterFolder(
					Messages.getString("ProcedureParameter.Name"), Messages.getString("ProcedureParameter.DisplayName"), //$NON-NLS-1$ //$NON-NLS-2$
					parentElement);
			return new Object[] { pcf };
		} else if (parentElement instanceof IProcedureParameterFolder) {
			IProcedureParameterFolder pcf = (IProcedureParameterFolder) parentElement;
			Routine routine = (Routine) ((IProcedureParameterFolder) parentElement)
					.getParent();
			pcf.addChildren(routine.getParameters());
			return pcf.getChildrenArray();
		}

		return super.getChildren(parentElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.navigator.ICommonContentProvider#init(org.eclipse.ui.navigator.ICommonContentExtensionSite)
	 */
	public void init(ICommonContentExtensionSite config) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.navigator.IMementoAware#restoreState(org.eclipse.ui.IMemento)
	 */
	public void restoreState(IMemento memento) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.navigator.IMementoAware#saveState(org.eclipse.ui.IMemento)
	 */
	public void saveState(IMemento memento) {
	}

}
