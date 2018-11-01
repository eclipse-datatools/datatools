/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.List;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.widgets.Composite;

public interface IDriverUIContributor {
	public Composite getContributedDriverUI(Composite parent, boolean isReadOnly);

	public boolean determineContributorCompletion();

	public void setDialogPage(DialogPage parentPage);

	public void setDriverUIContributorInformation(
			IDriverUIContributorInformation contributorInformation);

	public void loadProperties();

	public List getSummaryData();
}
