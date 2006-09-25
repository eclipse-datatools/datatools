/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    rcernich - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.datatools.connectivity.ui.dse.views;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.UndoContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.operations.UndoRedoActionGroup;


public class DataSourceExplorerView extends CommonNavigator {
	
	public DataSourceExplorerView() {
		super();
	}

	protected IAdaptable getInitialInput() {
		return ProfileManager.getInstance();
	}

}
