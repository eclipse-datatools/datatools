/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.template.internal.ui;

import org.eclipse.datatools.connectivity.oda.template.ui.nls.Messages;
import org.eclipse.pde.ui.IFieldData;
import org.eclipse.pde.ui.templates.ITemplateSection;
import org.eclipse.pde.ui.templates.NewPluginTemplateWizard;

/**
 * An ODA runtime driver plug-in template wizard that implements the
 * <code>org.eclipse.pde.ui.pluginContent</code> extension point.
 * It is intended for use in the PDE new plug-in project wizard to generate
 * an ODA runtime plug-in project.
 */
public class NewOdaDriverWizard extends NewPluginTemplateWizard
{
    /*
     * (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.AbstractNewPluginTemplateWizard#init(org.eclipse.pde.ui.IFieldData)
     */
    public void init( IFieldData data ) 
    {
        super.init( data );
        setWindowTitle( Messages.driverWizard_windowTitle ); 
        setNeedsProgressMonitor( true );
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.ui.templates.NewPluginTemplateWizard#createTemplateSections()
     */
    public ITemplateSection[] createTemplateSections()
    {
        return new ITemplateSection[] 
                   { new RuntimeTemplateSection( this ) };       
    }
    
}
