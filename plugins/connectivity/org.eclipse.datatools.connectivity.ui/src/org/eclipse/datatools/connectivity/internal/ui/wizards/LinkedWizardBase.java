/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;

/**
 * Base linked wizard. Linked wizards can contain other wizards in their series
 * of pages. To link wizards together, initalize the following wizard in your
 * wizard's addPages() method, e.g.
 * 
 * <pre>
 *  	public void addPages() {
 * 		super.addPages();
 * 		
 * 		// Add this wizard's pages.
 * 		...
 * 		
 * 		// Create the next wizard
 * 		MyNextWizard mnw = new MyNextWizard();
 * 		
 * 		// Initialize the wizard
 * 		mnw.init();
 * 		
 * 		// Create the wizards pages
 * 		mnw.addPages();
 * 		
 * 		// Link the wizard into the chain
 * 		setNext(mnw);
 * 	}
 * </pre>
 * 
 * The linked wizard's starting page is automatically returned by getNextPage()
 * when all this wizard's pages have been displayed.
 * 
 * @author rcernich
 */
public abstract class LinkedWizardBase extends BaseWizard implements
		ILinkedWizard {

	ILinkedWizard mPrevious = null;
	ILinkedWizard mNext = null;
	protected boolean mFinished = false;

	/**
	 * Default constructor.
	 */
	public LinkedWizardBase() {
		super();
	}

	public void setPrevious(ILinkedWizard previous) {
		mPrevious = previous;
		if (mPrevious != null
				&& (getWindowTitle() == null || getWindowTitle().length() == 0)) {
			setWindowTitle(mPrevious.getWindowTitle());
		}
	}

	public ILinkedWizard getPrevious() {
		return mPrevious;
	}

	public void setNext(ILinkedWizard next) {
		mNext = next;
		if (next != null)
			mNext.setPrevious(this);
	}

	public ILinkedWizard getNext() {
		return mNext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		boolean canFinish = super.canFinish();
		if (canFinish && getNext() != null) {
			canFinish = canFinish && getNext().canFinish();
		}
		return canFinish;
	}

	public boolean isFinished() {
		return mFinished;
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#dispose()
	 */
	public void dispose() {
		super.dispose();
		mPrevious = null;
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		boolean noFailures = true;
		if (!mFinished) {
			// Get the last unfinished wizard
			LinkedWizardBase lastWiz = this;
			if (getNext() != null && !getNext().isFinished()) {
				lastWiz = (LinkedWizardBase) getNext();
				while (lastWiz.getNext() != null
						&& !lastWiz.getNext().isFinished()) {
					lastWiz = (LinkedWizardBase) lastWiz.getNext();
				}
			}

			// finish the wizards from last to first
			do {
				lastWiz.mFinished = lastWiz.doFinish();
				noFailures = noFailures && lastWiz.isFinished();
				lastWiz = (LinkedWizardBase) lastWiz.getPrevious();
			}
			while (noFailures && lastWiz != null && !lastWiz.isFinished());
		}
		return mFinished && noFailures;
	}

	public abstract boolean doFinish();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage nextPage = super.getNextPage(page);
		if ((nextPage == null || nextPage instanceof SummaryWizardPage)
				&& getNext() != null) {
			nextPage = getNext().getStartingPage();
		}
		return nextPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.common.ui.wizards.ISummaryDataSource#getSummaryData()
	 */
	public List getSummaryData() {
		List summaryData;
		LinkedWizardBase prevWizard = (LinkedWizardBase) getPrevious();
		if (prevWizard == null) {
			summaryData = new ArrayList();
		}
		else {
			summaryData = prevWizard.getSummaryData();
		}
		IWizardPage[] pages = getPages();
		for (int i = 0; i < pages.length; i++) {
			if (pages[i] instanceof ISummaryDataSource) {
				if (pages[i] instanceof ISkippable) {
					if (((ISkippable) pages[i]).getSkippable()) {
						continue;
					}
				}
				summaryData.addAll(((ISummaryDataSource) pages[i])
						.getSummaryData());
			}
		}
		return summaryData;
	}

}
