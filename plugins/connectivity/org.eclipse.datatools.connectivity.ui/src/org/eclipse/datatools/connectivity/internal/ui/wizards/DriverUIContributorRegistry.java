/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public final class DriverUIContributorRegistry {

	private static DriverUIContributorRegistry instance = new DriverUIContributorRegistry();

	private Hashtable driverUIContributorTable = null;

	private BlankDriverUIContributor blankDriverUIContributor = new BlankDriverUIContributor();

	private DriverUIContributorRegistry() {
		initializeDriverCollection();
	}

	public static DriverUIContributorRegistry getInstance() {
		return instance;
	}

	public IDriverUIContributor getDriverUIContributor(String driverInstanceID) {
		IDriverUIContributor driverUIContributor = blankDriverUIContributor;
		if (driverInstanceID != null) {
			String driverTemplateID = DriverManager.getInstance()
					.getDriverInstanceByID(driverInstanceID).getTemplate()
					.getId();
			if (driverTemplateID != null) {
				IDriverUIContributor foundDriverUIContributor = (IDriverUIContributor) driverUIContributorTable
						.get(driverTemplateID);
				if (foundDriverUIContributor != null) {
					driverUIContributor = foundDriverUIContributor;
				}
			}
		}
		return driverUIContributor;
	}

	private void initializeDriverCollection() {
		driverUIContributorTable = new Hashtable();
		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(
				"org.eclipse.datatools.connectivity.ui", "driverUIContributor"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();
		for (int extensionIndex = 0; extensionIndex < extensions.length; extensionIndex++) {
			IConfigurationElement[] configElements = extensions[extensionIndex]
					.getConfigurationElements();
			for (int elementIndex = 0; elementIndex < configElements.length; ++elementIndex) {
				if (configElements[elementIndex].getName().equals(
						"driverUIContributor")) //$NON-NLS-1$
				{
					IConfigurationElement configElement = configElements[elementIndex];
					String driverTemplateID = configElement
							.getAttribute("driverTemplateID"); //$NON-NLS-1$
					IDriverUIContributor driverUIContributor = null;
					try {
						driverUIContributor = (IDriverUIContributor) configElement
								.createExecutableExtension("class"); //$NON-NLS-1$
					} catch (Exception e) {
					}
					if (driverUIContributor != null) {
						driverUIContributorTable.put(driverTemplateID,
								driverUIContributor);
					}
				}
			}
		}
	}

	private class BlankDriverUIContributor implements IDriverUIContributor {
		Composite baseComposite = null;

		public BlankDriverUIContributor() {
		}

		public Composite getContributedDriverUI(Composite parent, boolean isReadOnly) {
			if ((baseComposite == null) || baseComposite.isDisposed()) {
				baseComposite = new Composite(parent, SWT.NONE);
			}
			return baseComposite;
		}

		public boolean determineContributorCompletion() {
			return false;
		}

		public void setDialogPage(DialogPage parentPage) {
			// Do nothing
		}

		public void setDriverUIContributorInformation(
				IDriverUIContributorInformation information) {
			// Do nothing
		}

		public void loadProperties() {
			// Do nothing
		}

		public List getSummaryData() {
			return new ArrayList();
		}
	}
}
