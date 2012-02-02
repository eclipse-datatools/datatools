/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.cp.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class RefreshOverrideAction extends Action {

	public void run() {
		MessageDialog.openInformation(new Shell(), 
				"Override Refresh", 
				"This action has been overridden to pop up this dialog.");
	}

}
