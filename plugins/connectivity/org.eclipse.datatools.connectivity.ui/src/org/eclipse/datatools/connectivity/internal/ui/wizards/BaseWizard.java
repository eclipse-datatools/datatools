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

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * Base class for all wizards.
 * 
 * @author shongxum
 */
abstract public class BaseWizard extends Wizard implements ISummaryDataSource,
		IMediator {

	/**
	 * Method declared on IWizard. Returns the next page for the wizard to
	 * display. This implementation ignores skipped pages.
	 * 
	 * @return the next wizard page
	 * @see org.eclipse.jface.wizard.IWizard#getNextPage(IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardAction wizActions = null;
		IWizardPage nextPage = null;
		ISkippable nextPG = null;
		if (page instanceof IWizardAction) {
			wizActions = (IWizardAction) page;
			if (!wizActions.onWizardNext()) {
				return page;
			}
		}
		nextPage = super.getNextPage(page);
		while ((nextPage != null) && (nextPage instanceof ISkippable)) {
			nextPG = (ISkippable) nextPage;
			if (!nextPG.getSkippable()) {
				break;
			}
			nextPage = super.getNextPage(nextPage);
		}
		if (nextPage != null && nextPage instanceof IWizardAction) {
			wizActions = (IWizardAction) nextPage;
			wizActions.onSetActive();
		}
		return nextPage;
	}

	/**
	 * Method declared on IWizard. Returns the previous page displayed in the
	 * wizard. This implementation ignores skipped pages.
	 * 
	 * @return the previous wizard page
	 * @see org.eclipse.jface.wizard.IWizard#getPreviousPage(IWizardPage)
	 */
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage prevPage = null;
		ISkippable prevPG = null;
		prevPage = super.getPreviousPage(page);
		while ((prevPage != null) && (prevPage instanceof ISkippable)) {
			prevPG = (ISkippable) prevPage;
			if (!prevPG.getSkippable()) {
				break;
			}
			prevPage = super.getPreviousPage(prevPage);
		}
		return prevPage;
	}

	/**
	 * Method declared on IWizard. By default this is the first page inserted
	 * into the wizard. This implementation ignores skipped pages.
	 * 
	 * @return the first wizard page
	 * @see org.eclipse.jface.wizard.IWizard#getStartingPage()
	 */
	public IWizardPage getStartingPage() {
		IWizardAction wizActions = null;
		IWizardPage[] pages = getPages();
		IWizardPage page = null;
		ISkippable pg = null;
		if (pages.length == 0)
			return page;
		for (int i = 0; i < pages.length; i++) {
			page = pages[i];
			if (page instanceof ISkippable) {
				pg = (ISkippable) page;
				if (!pg.getSkippable()) {
					break;
				}
			}
			else {
				break;
			}
		}
		if (page != null && page instanceof IWizardAction) {
			wizActions = (IWizardAction) page;
			wizActions.onSetActive();
		}
		return page;
	}

	/**
	 * Method declared on IWizard. Default implementation allows the wizard to
	 * finish if current page is a summary page.
	 * 
	 * @return <tt>true</tt> if it is valid to finish the wizard in its
	 *         current state; <tt>false</tt> otherwise
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		IWizardPage[] pages = getPages();
		for (int i = 0; i < pages.length; i++) {
			if (!pages[i].isPageComplete())
				if ((pages[i] instanceof ISkippable)
						&& ((ISkippable) pages[i]).getSkippable())
					continue;
				else
					return false;
		}
		return true;
		// return getContainer().getCurrentPage() instanceof ISummary;
	}

	/**
	 * Traverses the set of pages owned by the wizard and selects the summary
	 * data from ones that are summary data sources. This implementation ignores
	 * skipped pages.
	 * 
	 * @return the list of summary data from all (not skipped) wizard pages
	 * @see SummaryDataSource#getSummaryData()
	 */
	public List getSummaryData() {
		List data = new ArrayList();
		IWizardPage[] pages = getPages();
		for (int i = 0; i < pages.length; i++) {
			if (pages[i] instanceof ISummaryDataSource) {
				if (pages[i] instanceof ISkippable) {
					ISkippable page = (ISkippable) pages[i];
					if (page.getSkippable()) {
						continue;
					}
				}
				data.addAll(((ISummaryDataSource) pages[i]).getSummaryData());
			}
		}
		return data;
	}

	/**
	 * Client need to override this method if mediation is needed. Mediation is
	 * made by the wizard controller to allow wizard pages independent of each
	 * other, it can be used to initialize data for wizard page, or can be used
	 * to make some wizard pages invisible if a certain condition is met.
	 * 
	 * @see org.eclipse.datatools.connectivity.common.ui.wizards.IMediator#mediatePage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public void mediatePage(IWizardPage wizardPage) {
		// Do nothing by default
	}
}