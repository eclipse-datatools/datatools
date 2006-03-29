/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.ui.pages.impl;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.Property;
import org.eclipse.datatools.connectivity.oda.util.manifest.PropertyChoice;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Common implementation of data source properties page controls,
 * shared by the default data source wizard page
 * and property page classes.
 */
public class DefaultDataSourcePageHelper
{
    public static final String DEFAULT_MESSAGE = Messages.ui_defaultDataSourceTitle;
    public static final String EMPTY_STRING = ""; //$NON-NLS-1$
    public static final String COLON_CHAR = ":"; //$NON-NLS-1$

    private DefaultDataSourceWizardPage m_wizardPage = null;
    private DefaultDataSourcePropertyPage m_propertyPage = null;
    private transient Control m_propCtrls[] = null;
    private ExtensionManifest m_manifest = null;
    private Property[] m_dataSourceProps = null;
    private Properties m_dataSourcePropsVisibility = null;
    
    protected DefaultDataSourcePageHelper( DefaultDataSourceWizardPage page )
    {
        m_wizardPage = page;
        init();
    }

    protected DefaultDataSourcePageHelper( DefaultDataSourcePropertyPage page )
    {
        m_propertyPage = page;
        init();
    }
    
    private void init()
    {
        m_dataSourceProps = getManifest().getProperties();
        m_dataSourcePropsVisibility = getManifest().getPropertiesVisibility();        
    }
    
    protected void createCustomControl( Composite parent ) throws OdaException
    {
        Composite content = new Composite( parent, SWT.NULL );
        setupPropFields( content, m_dataSourceProps );
    }
    
    protected Properties collectCustomProperties( Properties props )
    {
        if( props == null )
            props = new Properties();
        
        for( int i = 0 ; i < m_dataSourceProps.length; i++ )
        {
            String propVal = EMPTY_STRING;
            
            if ( m_propCtrls[ i ] instanceof Text )
                propVal = ( ( Text ) m_propCtrls[ i ] ).getText();
            else if ( m_propCtrls[ i ] instanceof Combo )
            {
            	int index = ( ( Combo ) m_propCtrls[ i ] ).getSelectionIndex();
            	propVal = m_dataSourceProps[ i ].getChoices()[ index ].getName();
            }
            else if ( m_propCtrls[ i ] == null )
            {
                // The property is hidden from the user.  Therefore, just 
                // return the default value.  Notice that if the property
            	// is a choice, the propVal will be the choice's name, not 
            	// the choice value.
                propVal = m_dataSourceProps[ i ].getDefaultValue();
            }
            else
                assert( false );
            
            if ( propVal == null )
                propVal = EMPTY_STRING;
            
            props.setProperty( m_dataSourceProps[ i ].getName(), propVal );
        }
        
        return props;
    }
    
    protected void initCustomControl( Properties profileProps )
    {
        for( int i = 0; i < m_dataSourceProps.length ; i++ )
        {
            String propVal = EMPTY_STRING;
            
            if ( profileProps != null )
            {
                propVal = profileProps.getProperty( m_dataSourceProps[ i ].getName() );    
                assert( propVal != null );
            }
            
            setPropertyControlValue( m_propCtrls[ i ], m_dataSourceProps[ i ], propVal );
        }
    }

    protected void setupPropFields( Composite composite, Property[] props )
    {
        GridLayout layout = new GridLayout( );
        
        layout.numColumns = 5;
        composite.setLayout( layout );
        
        m_propCtrls = new Control[ props.length ];
        
        for( int i = 0; i < props.length; i++)
        {
            // If the property is hidden, move on to the next prop.
            if ( isPropHidden( props[ i ] ) )
                continue;
            
            // Set up the label.
            createPropertyLabel( composite, props[ i ] );

            // Set up the control for specifying the value.
            m_propCtrls[ i ] = createPropertyControl( composite, props[ i ] );

            // Set the location of the control.
            GridData data = new GridData( GridData.FILL_HORIZONTAL );
            data.horizontalSpan = 4;
            m_propCtrls[ i ].setLayoutData( data );
        }
        
        setPageComplete( true );
    }

    protected boolean isPropHidden( Property prop )
    {
        return ( prop.isVisible( m_dataSourcePropsVisibility ) == false ) ;
    }
    
    protected boolean isPropReadOnly( Property prop )
    {
        return ( prop.isEditable( m_dataSourcePropsVisibility ) == false ) ;
    }
    
    protected void createPropertyLabel( Composite composite, Property prop )
    {
        Label label = new Label( composite, SWT.NONE );
        String displayName = prop.getDisplayName();
        
        if ( displayName == null || displayName.length() == 0 )
            displayName = prop.getName();
        
        label.setText( displayName + COLON_CHAR );
    }
    
    protected Control createPropertyControl( Composite composite, Property prop )
    {
        Control propCtrl = null;
        
        int style =  SWT.BORDER;

        PropertyChoice[] choices = prop.getChoices();
        if ( choices != null && choices.length > 0 )
        {
            if ( isPropReadOnly( prop ) )
            {
                // Since SWT.READ_ONLY combobox still allows the user to change
                // the selection, we opt to use text box here instead.
                propCtrl = new Text( composite, style | SWT.READ_ONLY );
            }
            else
            {
                // Use read-only style for combo means that the display text is 
                // not editable.  The selection can still be changed.
                Combo choiceCombo = new Combo( composite, 
                        style | SWT.DROP_DOWN | SWT.READ_ONLY );
            
                for( int j = 0; j < choices.length; j++ )
                {
                    String choiceDisplayName = choices[ j ].getDisplayName();
                    if ( choiceDisplayName != null && choiceDisplayName.length() != 0 )
                        choiceCombo.add( choiceDisplayName );
                    else
                        choiceCombo.add( choices[ j ].getName() );
                }
            
                propCtrl = choiceCombo;
            }
        }
        else
        {
            // Since there are currently only two types:  string or choices,
            // if it is not choices then it must be string.
            style |= ( isPropReadOnly( prop ) ? SWT.READ_ONLY : 0 );
            style |= ( prop.isEncryptable() ? SWT.PASSWORD : 0 );
                
            Text textCtrl = new Text( composite, style | SWT.SINGLE );
                
            propCtrl = textCtrl;
        }
        
        return propCtrl;
    }
    
    protected void setPropertyControlValue( Control propCtrl, Property prop, String propertyVal )
    {
        if ( isPropHidden( prop ) )
        {
            // If the property is hidden, then no need to set the value.
            return;
        }
        
        // Instantiate local copy of prop val.
        String propVal = ( propertyVal == null ? EMPTY_STRING : propertyVal );
        
        if ( propVal.length() == 0 )
        {
            // See if there is a default value, since no value has 
            // been specified in the property so far.
        	propVal = prop.getDefaultValue();
            if ( propVal == null )
            	propVal = EMPTY_STRING;
        }
        
        if ( propCtrl instanceof Text )
            ( ( Text ) propCtrl ).setText( propVal );
        else if ( propCtrl instanceof Combo )
        {
            Combo choiceCombo = ( Combo ) propCtrl;

            // From the property value, look up the corresponding
            // property choice and select it in the combobox.
            PropertyChoice[] choices = prop.getChoices();
            for( int j = 0; j < choices.length; j++ )
            {
                // propVal contains the name (not the value) of the choice.
                if ( choices[ j ].getName().equals( propVal ) )
                {
                    choiceCombo.select( j );
                    break;
                }
            }
        }
        else
            assert( false );        
    }
    
    protected void setPageComplete( boolean complete )
    {
        if( m_wizardPage != null )
            m_wizardPage.setPageComplete( complete );
        else if( m_propertyPage != null )
            m_propertyPage.setValid( complete );
    }
    
    protected void setMessage( String newMessage, int newType )
    {
        if( m_wizardPage != null )
            m_wizardPage.setMessage( newMessage, newType );
        else if( m_propertyPage != null )
            m_propertyPage.setMessage( newMessage, newType );
    }
    
    protected ExtensionManifest getManifest()
    {
    	if ( m_manifest == null )
    	{
	        String dsId = null;
	        
	        if ( m_wizardPage != null )
	            dsId = m_wizardPage.getOdaDataSourceId();
	        else if ( m_propertyPage != null )
	            dsId = m_propertyPage.getOdaDataSourceId();
	        else
	        {
	        	// Logic error.  The wizard page or property page should have a valid 
	        	// data source id in the first place.
	            assert( false );
	            return null;            
	        }
	        
	        try 
	        {
				m_manifest = ManifestExplorer.getInstance().getExtensionManifest( dsId );
			} 
	        catch (OdaException e) 
	        {
	        	// Logic error.  The data source id should have been verified 
	        	// to be valid by the wizard page or property page in the first place.
	        	assert( false );
	        	return null;
			}
    	}
    	
    	return m_manifest;
    }

}
