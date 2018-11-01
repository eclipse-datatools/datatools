/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * This interface is used by the linked navigator project to link the project
 * wizard to the profile wizard and present a unified summary page.
 * 
 * @author brianf
 */
public interface ILinkedSummary {

	public void setParentWizard(Wizard wizard);

	public Wizard getParentWizard();

}
