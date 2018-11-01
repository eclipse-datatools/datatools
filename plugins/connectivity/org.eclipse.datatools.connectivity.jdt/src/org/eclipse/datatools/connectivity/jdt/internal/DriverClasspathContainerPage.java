/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.jdt.internal;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.internal.ui.DriverListCombo;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * Wizard page presented to the user to create the classpath
 * container from the driver instance.
 * @author brianf
 *
 */
public class DriverClasspathContainerPage extends WizardPage implements
		IClasspathContainerPage, IClasspathContainerPageExtension {

	private DriverListCombo combo;
	private Set fUsedPaths;
	private String initialSelectName = null;

	/**
	 * Constructor
	 */
	public DriverClasspathContainerPage() {
		this(DriverClasspathMessages.getString("DriverClasspathContainerPage.name"));
		this.setTitle(DriverClasspathMessages.getString("DriverClasspathContainerPage.title"));
		this.setMessage(DriverClasspathMessages.getString("DriverClasspathContainerPage.msg"));
	}
	
	/**
	 * @param pageName
	 */
	public DriverClasspathContainerPage(String pageName) {
		super(pageName);
		fUsedPaths= new HashSet();
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public DriverClasspathContainerPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		fUsedPaths= new HashSet();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#finish()
	 */
	public boolean finish() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#getSelection()
	 */
	public IClasspathEntry getSelection() {
		if (combo.getSelectedDriverInstance() != null) {
			DriverInstance di = combo.getSelectedDriverInstance();
			IClasspathContainer container = new DriverClasspathContainer(di.getName());
			IClasspathEntry entry = JavaCore.newContainerEntry(container.getPath());
			return entry;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPage#setSelection(org.eclipse.jdt.core.IClasspathEntry)
	 */
	public void setSelection(IClasspathEntry containerEntry) {
		if (containerEntry != null) {
			IPath path = containerEntry.getPath();
			if (isDriverContainer(path)) {
				initialSelectName = path.segment(1);
			}
		}
	}

	/**
	 * @param path
	 * @return
	 */
	private boolean isDriverContainer(IPath path) {
		return path != null && path.segmentCount() == 2 && ConnJDTPlugin.DRIVER_CONTAINER_ID.equals(path.segment(0));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension#initialize(org.eclipse.jdt.core.IJavaProject, org.eclipse.jdt.core.IClasspathEntry[])
	 */
	public void initialize(
			IJavaProject project,
			IClasspathEntry[] currentEntries) {
			for (int i= 0; i < currentEntries.length; i++) {
				IClasspathEntry curr= currentEntries[i];
				if (curr.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
					fUsedPaths.add(curr.getPath());
				}
			}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite= new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		composite.setFont(parent.getFont());
		
		combo = new DriverListCombo();
		combo.setLabelText(DriverClasspathMessages.getString("DriverClasspathContainerPage.combo.title"));
		combo.createContents(composite);
		combo.getCombo().setLayoutData(new GridData(SWT.FILL, 0, true, false));
		combo.setSelection(this.initialSelectName);
		
		Dialog.applyDialogFont(composite);
		setControl(composite);
	}

}
