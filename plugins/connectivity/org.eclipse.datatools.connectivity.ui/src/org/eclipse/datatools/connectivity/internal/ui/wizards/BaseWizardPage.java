/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardPage;

/**
 * Base class for every wizard page in the plugin. Provides default support for
 * listening to wizard switching pages and visibility (through skippable)
 * control.
 * 
 * @author shongxum
 */
abstract public class BaseWizardPage extends WizardPage implements
		IWizardAction, ISkippable, ISummaryDataSource {

	private boolean _skippable = false;
	private boolean _needMediation = false;

	/**
	 * Default constructor.
	 */
	public BaseWizardPage(String name) {
		super(name);
	}

	/**
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public BaseWizardPage(String pageName, String title,
							ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	/**
	 * @see ISkippable#getSkippable()
	 */
	public boolean getSkippable() {
		return _skippable;
	}

	/**
	 * @see ISkippable#setSkippable(boolean)
	 */
	public void setSkippable(boolean b) {
		_skippable = b;
	}

	public void setNeedMediation(boolean needMediation) {
		_needMediation = needMediation;
	}

	/**
	 * @see IWizardAction#onWizardNext()
	 */
	public boolean onWizardNext() {
		if (!_needMediation)
			return true;

		IWizard wiz = getWizard();
		if (wiz instanceof IMediator) {
			((IMediator) wiz).mediatePage(this);
		}
		return true;
	}

	/**
	 * @see com.sybase.wst.eclipse.internal.ui.wizards.IWizardAction#onSetActive()
	 */
	public void onSetActive() {
		// Empty
	}

	/**
	 * @see com.sybase.wst.eclipse.internal.ui.wizards.ISummaryDataSource#getSummaryData()
	 */
	public List getSummaryData() {
		return new ArrayList();
	}
}
