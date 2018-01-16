/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.templates;

import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;

/**
 * Wizard to wrap the Profile Sample template
 * @author brianf
 *
 */
public class ProfileSamplePluginTemplateWizard extends NewPluginTemplateWizard {

	protected IFieldData fData;
	
	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.NewPluginTemplateWizard#createTemplateSections()
	 */
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] {
				new ProfileSampleTemplateSection()};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.AbstractNewPluginTemplateWizard#init(org.eclipse.pde.ui.IFieldData)
	 */
	public void init(IFieldData data) {
        super.init( data );
        setWindowTitle( Messages.getString("ProfileSamplePluginTemplateWizard.WizardWindowTitle") ); //$NON-NLS-1$
        setNeedsProgressMonitor( true );
		fData = data;
	}

}
