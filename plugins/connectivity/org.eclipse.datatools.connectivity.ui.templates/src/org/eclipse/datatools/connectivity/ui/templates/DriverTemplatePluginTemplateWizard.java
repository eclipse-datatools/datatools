/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.templates;

import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;

/**
 * Wizard to wrap the driver template PDE template 
 * 
 * @author brianf
 *
 */
public class DriverTemplatePluginTemplateWizard extends NewPluginTemplateWizard {

	protected IFieldData fData;
	
	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.NewPluginTemplateWizard#createTemplateSections()
	 */
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] {new DriverTemplateSection()};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.pde.ui.templates.AbstractNewPluginTemplateWizard#init(org.eclipse.pde.ui.IFieldData)
	 */
	public void init(IFieldData data) {
        super.init( data );
        setWindowTitle( Messages.getString("DriverTemplatePluginTemplateWizard.WindowTitle") );  //$NON-NLS-1$
        setNeedsProgressMonitor( true );
		fData = data;
	}

}
