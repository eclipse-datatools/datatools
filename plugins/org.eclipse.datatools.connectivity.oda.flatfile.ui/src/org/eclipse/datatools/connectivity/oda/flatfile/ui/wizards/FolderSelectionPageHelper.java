/*
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile.ui.wizards;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.SortedMap;

import org.eclipse.datatools.connectivity.oda.flatfile.ui.FlatFileConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FolderSelectionPageHelper
{
    private WizardPage m_wizardPage;
    private PreferencePage m_propertyPage;

    private transient Text m_folderLocation = null;
    private transient Button m_typeLineCheckBox = null;
    private transient Button m_browseFolderButton = null;
    private transient Combo m_charSetSelectionCombo = null;
    private SortedMap m_charSetMap;

    static final String DEFAULT_MESSAGE = 
        Messages.getString( "wizard.defaultMessage.selectFolder" ); //$NON-NLS-1$
    
    private static final int ERROR_FOLDER = 1;
    private static final int ERROR_EMPTY_PATH = 2;
    private static final String EMPTY_STRING = "";
    
    FolderSelectionPageHelper( WizardPage page )
    {
        m_wizardPage = page;
    }

    FolderSelectionPageHelper( PreferencePage page )
    {
        m_propertyPage = page;
    }

    void createCustomControl( Composite parent )
    {
        Composite content = new Composite( parent, SWT.NULL );
        GridLayout layout = new GridLayout( 3, false );
        content.setLayout(layout);
        
        //GridData data;
        setupFolderLocation( content );
                
        setupCharset( content );
        
        setupTypeLineCheckBox( content );
    }
    
    String getFolderLocation()
    {
        if( m_folderLocation == null )
            return EMPTY_STRING;
        return m_folderLocation.getText();
    }

    String getWhetherUseSecondLineAsTypeLine()
    {
        if( m_typeLineCheckBox == null )
            return EMPTY_STRING;
        return m_typeLineCheckBox.getSelection() ? "YES" : "NO";
    }
    
    String getCharSet()
    {
        if( m_charSetSelectionCombo == null )
            return EMPTY_STRING;
        return m_charSetSelectionCombo.getItem(
                    m_charSetSelectionCombo.getSelectionIndex() );
    }

    Properties collectCustomProperties( Properties props )
    {
        if( props == null )
            props = new Properties();
        
        // set custom driver specific properties
        props.setProperty( FlatFileConstants.ODA_FOLDER_PROP, 
                            getFolderLocation() );
        props.setProperty( FlatFileConstants.ODA_INCLTYPELINE_PROP, 
                            getWhetherUseSecondLineAsTypeLine() );
        props.setProperty( FlatFileConstants.ODA_CHARSET_PROP, 
                            getCharSet() );
        return props;
    }
    
    void initCustomControl( Properties profileProps )
    {
        if( profileProps == null || profileProps.isEmpty() || 
            m_folderLocation == null )
            return;     // nothing to initialize
        
        String folderPath = profileProps.getProperty( FlatFileConstants.ODA_FOLDER_PROP );
        if( folderPath == null )
            folderPath = EMPTY_STRING;
        m_folderLocation.setText( folderPath );

        String useSecondLine = profileProps.getProperty( FlatFileConstants.ODA_INCLTYPELINE_PROP );
        if( useSecondLine == null )
            useSecondLine = EMPTY_STRING;
        m_typeLineCheckBox.setSelection( useSecondLine.equalsIgnoreCase( "YES" ) );

        String charSet = profileProps.getProperty( FlatFileConstants.ODA_CHARSET_PROP );
        if( charSet == null || charSet.trim().length() == 0 )
            m_charSetSelectionCombo.select( 0 );
        else
            m_charSetSelectionCombo.select( m_charSetSelectionCombo.indexOf( charSet ) );

        verifyFileLocation();
    }

    private void setupFolderLocation( Composite composite )
    {
        Label label = new Label( composite, SWT.NONE );
        label.setText( Messages.getString( "label.selectFolder" ) ); //$NON-NLS-1$

        GridData data = new GridData( GridData.FILL_HORIZONTAL );

        m_folderLocation = new Text( composite, SWT.BORDER );
        m_folderLocation.setLayoutData( data );
        setPageComplete( false );
        m_folderLocation.addModifyListener( 
            new ModifyListener()
            {    
                public void modifyText( ModifyEvent e )
                {
                    verifyFileLocation();
                }
    
            } );

        m_browseFolderButton = new Button( composite, SWT.NONE );
        m_browseFolderButton.setText( "..." ); //$NON-NLS-1$
        m_browseFolderButton.addSelectionListener( 
            new SelectionAdapter()
            {
               /*
                 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
                 */
                public void widgetSelected( SelectionEvent e )
                {
                    DirectoryDialog dialog = new DirectoryDialog( 
                                    m_folderLocation.getShell() );
                    if( m_folderLocation.getText() != null && 
                        m_folderLocation.getText().trim().length() > 0 )
                    {
                        dialog.setFilterPath( m_folderLocation.getText() );
                    }

                    dialog.setMessage( DEFAULT_MESSAGE );
                    String selectedLocation = dialog.open();
                    if( selectedLocation != null )
                    {
                        m_folderLocation.setText( selectedLocation );
                    }
                }
            } );
    }

    private int verifyFileLocation()
    {
        int result = 0;
        if( m_folderLocation.getText().trim().length() > 0 )
        {
            File f = new File( m_folderLocation.getText().trim() );
            if( f.exists() )
            {
                setMessage( DEFAULT_MESSAGE, IMessageProvider.NONE );
                setPageComplete( true );
            }
            else
            {
                setMessage( Messages.getString( "error.selectFolder" ), IMessageProvider.ERROR ); //$NON-NLS-1$
                setPageComplete( false );
                result = ERROR_FOLDER;
            }
        }
        else
        {
            setMessage( Messages.getString( "error.emptyPath" ), IMessageProvider.ERROR ); //$NON-NLS-1$
            setPageComplete( false );
            result = ERROR_EMPTY_PATH;
        }
        return result;
    }

    /**
     * @param composite
     */
    private void setupCharset( Composite composite )
    {
        Label labelCharSet = new Label( composite, SWT.NONE );
        labelCharSet.setText( Messages.getString( "label.selectCharset" ) ); //$NON-NLS-1$

        m_charSetSelectionCombo = new Combo( composite, SWT.READ_ONLY );

        GridData data = new GridData( GridData.HORIZONTAL_ALIGN_FILL );
        data.horizontalSpan = 2;
        m_charSetSelectionCombo.setLayoutData( data );

        m_charSetMap = Charset.availableCharsets();
        Object[] charSetsArray = m_charSetMap.keySet().toArray();
        for( int i = 0; i < charSetsArray.length; i++)
        {
            String charSetName = ((Charset) m_charSetMap.get( charSetsArray[i] ))
                    .name();
            m_charSetSelectionCombo.add( charSetName );
            if( FlatFileConstants.ODA_DEFAULT_CHARSET_PROP
                    .equalsIgnoreCase( charSetName ) )
                m_charSetSelectionCombo.select( i );
        }
    }

    /**
     * @param composite
     */
    private void setupTypeLineCheckBox( Composite composite )
    {
        m_typeLineCheckBox = new Button( composite, SWT.CHECK );
        GridData data = new GridData();
        data.horizontalSpan = 3;
        m_typeLineCheckBox.setLayoutData( data );
        m_typeLineCheckBox.setText( Messages
                .getString( "label.includeTypeLine" ) );
    }

    private void setPageComplete( boolean complete )
    {
        if( m_wizardPage != null )
            m_wizardPage.setPageComplete( complete );
        else if( m_propertyPage != null )
            m_propertyPage.setValid( complete );
    }
    
    private void setMessage( String newMessage, int newType )
    {
        if( m_wizardPage != null )
            m_wizardPage.setMessage( newMessage, newType );
        else if( m_propertyPage != null )
            m_propertyPage.setMessage( newMessage, newType );
    }
}
