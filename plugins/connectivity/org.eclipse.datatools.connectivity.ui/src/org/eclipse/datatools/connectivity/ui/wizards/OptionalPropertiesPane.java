/*
 *************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 *               Actuate Corporation - re-factored to a generic component
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DelimitedStringList;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 *  A generic pane that collects user input of optional properties in delimited name-value pairs.
 */
public class OptionalPropertiesPane extends Composite
    implements ModifyListener
{
    protected boolean isReadOnly = false;
    protected DelimitedStringList delimitedStringControl;
    protected Properties properties;

    /**
     * Constructor.
     * @param parent    a widget which will be the parent of the new instance (cannot be null)
     * @param style     the style of widget to construct
     * @param isReadOnly    true to use read-only controls; false otherwise
     */
    public OptionalPropertiesPane( Composite parent, int style, boolean isReadOnly )
    {
        super(parent, style);
        Composite parentComposite = this;
        
        this.isReadOnly = isReadOnly;

        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        setLayout( layout );
        
        Label optionalPropertiesLabel = new Label( parentComposite, SWT.NULL );
        GridData gdata = new GridData( GridData.FILL_HORIZONTAL );
        gdata.horizontalSpan = 2;
        optionalPropertiesLabel.setLayoutData( gdata );
        optionalPropertiesLabel.setText( ConnectivityUIPlugin.getDefault()
                .getResourceString( "CommonDriverUIContributor.optionalProps.label" ) ); //$NON-NLS-1$

        delimitedStringControl = new DelimitedStringList( parentComposite, SWT.NONE, isReadOnly );
        GridData gd = new GridData( GridData.FILL_HORIZONTAL );
        gd.horizontalSpan = 2;
        delimitedStringControl.setLayoutData( gd );
        
        addListeners();
    }

    /**
     * Passes in the IDriverUIContributorInformation to this pane for the properties information.
     * This method must be called by the client of this pane.
     * @param contributorInformation    cannot be null
     */
    public void setDriverUIContributorInformation(
            IDriverUIContributorInformation contributorInformation) {
        this.properties = contributorInformation.getProperties();
    }

    protected void addListeners() {
        delimitedStringControl.addModifyListener( this );
    }

    protected void removeListeners() {
        delimitedStringControl.removeModifyListener( this );
    }

    /**
     * Indicates whether all the controls in this pane are in a valid state.
     * @param page  the dialog page to display an error message if controls are not valid
     * @return  true if controls are valid; false otherwise
     */
    public boolean validateControl( DialogPage page ) {
        boolean isValid = true;
        if ( delimitedStringControl.getWarning() != null ) {
            page.setErrorMessage( delimitedStringControl.getWarning() );
            isValid = false;
        }    
        return isValid;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
     */
    public void modifyText( ModifyEvent e )
    {
        if( isReadOnly )
            return;
    
        // optional properties are updated directly in this.properties, and are not included in 
        // the parent contributor's generated URL
        setConnectionInformation();
    }
    
    /**
     * Loads and displays the connection property values found in the Properties specified by 
     * {@link #setDriverUIContributorInformation(IDriverUIContributorInformation)}.
     */
    public void loadProperties() {
        removeListeners();

        String propertyList = properties.getProperty(
                IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
        if( propertyList != null ) 
            delimitedStringControl.setSelection( propertyList );

        addListeners();
    }
    
    /**
     * Updates the connection property values in the Properties specified by
     * {@link #setDriverUIContributorInformation(IDriverUIContributorInformation)}.
     */
    public void setConnectionInformation() {
        properties.setProperty(
                IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID,
                delimitedStringControl.getSelection() );
    }
    
}
