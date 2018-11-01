/*******************************************************************************
 * Copyright (c) 2007, 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Brian Fitzpatrick - initial API and implementation
 * 			IBM Corporation - refactored plug-in
 ******************************************************************************/
package org.eclipse.datatools.enablement.hsqldb.ui.catalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.StoredProcedureNode;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.IPipelinedTreeContentProvider;
import org.eclipse.ui.navigator.PipelinedShapeModification;
import org.eclipse.ui.navigator.PipelinedViewerUpdate;

/**
 * @author brianf
 *
 */
public class HSQLDBPipelineTreeContentProvider extends ServerExplorerContentProviderNav implements IPipelinedTreeContentProvider {

	/**
	 * 
	 */
	public HSQLDBPipelineTreeContentProvider() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#getPipelinedChildren(java.lang.Object, java.util.Set)
	 */
	public void getPipelinedChildren(Object aParent, Set theCurrentChildren) {
		Object[] children = getChildren(aParent);
		ArrayList kids = new ArrayList(Arrays.asList(children));
		theCurrentChildren.clear();
		for (Iterator iter = kids.iterator(); iter.hasNext();) {
			if (iter.next() instanceof StoredProcedureNode)
				iter.remove();
		}
		theCurrentChildren.addAll(kids);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#getPipelinedElements(java.lang.Object, java.util.Set)
	 */
	public void getPipelinedElements(Object anInput, Set theCurrentElements) {
		Object[] children = getElements(anInput);

		theCurrentElements.addAll(Arrays.asList(children));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#getPipelinedParent(java.lang.Object, java.lang.Object)
	 */
	public Object getPipelinedParent(Object anObject, Object aSuggestedParent) {
		return getParent(anObject);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#interceptAdd(org.eclipse.ui.navigator.PipelinedShapeModification)
	 */
	public PipelinedShapeModification interceptAdd(
			PipelinedShapeModification anAddModification) {
		return anAddModification;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#interceptRefresh(org.eclipse.ui.navigator.PipelinedViewerUpdate)
	 */
	public boolean interceptRefresh(
			PipelinedViewerUpdate aRefreshSynchronization) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#interceptRemove(org.eclipse.ui.navigator.PipelinedShapeModification)
	 */
	public PipelinedShapeModification interceptRemove(
			PipelinedShapeModification aRemoveModification) {
		return aRemoveModification;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.navigator.IPipelinedTreeContentProvider#interceptUpdate(org.eclipse.ui.navigator.PipelinedViewerUpdate)
	 */
	public boolean interceptUpdate(PipelinedViewerUpdate anUpdateSynchronization) {
		return true;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	public void init(ICommonContentExtensionSite aConfig) {
	}

	public void restoreState(IMemento aMemento) {
	}

	public void saveState(IMemento aMemento) {
	}
}
