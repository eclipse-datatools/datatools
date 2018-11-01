/*
 *************************************************************************
 * Copyright (c) 2006, 2012 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.pages.impl;

import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.PropertyAttributes;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueChoices;
import org.eclipse.datatools.connectivity.oda.design.ScalarValueDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.Property;
import org.eclipse.datatools.connectivity.oda.util.manifest.PropertyChoice;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
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
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    private static final String COLON_CHAR = ":"; //$NON-NLS-1$
    private static final String REQUIRED_FIELD_SYMBOL = " *"; //$NON-NLS-1$

    private DefaultDataSourceWizardPage m_wizardPage = null;
    private DefaultDataSourcePropertyPage m_propertyPage = null;
    private transient Control m_propCtrls[] = null;
    private ExtensionManifest m_manifest = null;
    private Property[] m_extendedManifestProps = null;
    private Property[] m_manifestProps = null;
    private Properties m_manifestPropsVisibility = null;
    private org.eclipse.datatools.connectivity.oda.design.Properties
    	m_dataSourceDesignProps = null;
    private ArrayList m_orderedPropNameList = null;
    
    protected DefaultDataSourcePageHelper( DefaultDataSourceWizardPage page )
    {
    	assert( page != null );
        m_wizardPage = page;
        init();
    }

    protected DefaultDataSourcePageHelper( DefaultDataSourcePropertyPage page )
    {
    	assert( page != null );
        m_propertyPage = page;
        init();
        
        // Get the current editing data source design.
    	DataSourceDesign dataSourceDesign = m_propertyPage.getCurrentDataSource();
    	
    	// Get the data source design public properties.
    	if ( dataSourceDesign != null )
    		m_dataSourceDesignProps = dataSourceDesign.getPublicProperties();
    }
    
    private void init()
    {
        // get manifest properties that this page would provide UI control
        m_manifestProps = getEditableManifestProperties();
        m_manifestPropsVisibility = getManifest().getPropertiesVisibility();    

        // get all inherited and extension-defined property definitions
        m_extendedManifestProps = getManifest().getProperties( true );
    }

    /**
     * Returns manifest properties that this page would provide UI control
     * for user input.
     * @return  an array of ODA property definition, including group
     *          definition if available
     */
    protected Property[] getEditableManifestProperties()
    {
        /* the default data source page does not provide UI control to edit the
         * connection profile properties, since they are expected to be edited
         * by the dedicated ProfileSelection pages;
         * returns just the extension defined properties, excluding
         * those inherited profile properties from the manifest list
         */
        return getManifest().getProperties( false );
    }
    
    protected void createCustomControl( Composite parent ) throws OdaException
    {
    	// Create a ScrolledComposite as the child of the parent wizard page.
    	final ScrolledComposite scrolledComposite = new ScrolledComposite( parent, SWT.V_SCROLL | SWT.BORDER );
    	scrolledComposite.setExpandVertical( true );
    	scrolledComposite.setExpandHorizontal( true );
    	
    	// Create a Composite with the ScrolledComposite as the parent.
    	final Composite content = new Composite( scrolledComposite, SWT.NONE );
    	scrolledComposite.setContent( content );
    	
    	// Set up control listener to monitor the resizing of the properties pane.
    	scrolledComposite.addControlListener( 
    			new ControlAdapter() 
    			{
    				public void controlResized( ControlEvent e ) 
    				{
    					Rectangle r = scrolledComposite.getClientArea();
    					scrolledComposite.setMinSize( content.computeSize( r.width, SWT.DEFAULT ));
    				}
    			});

    	// Set up the property field controls.
        setupPropFields( content );
    }
    
    protected Properties collectCustomProperties( Properties props )
    {
        if( props == null )
            props = new Properties();
        
        for( int i = 0 ; i < getPropCount(); i++ )
        {
            String propVal = EMPTY_STRING;
            
            if ( m_propCtrls[ i ] instanceof Text )
                propVal = ( ( Text ) m_propCtrls[ i ] ).getText();
            else if ( m_propCtrls[ i ] instanceof Combo )
            {
            	int selectionIndex = ( ( Combo ) m_propCtrls[ i ] ).getSelectionIndex();
            	if ( selectionIndex != -1 )
            	{
            		propVal = getPropChoiceSelection( i , selectionIndex );
            	}
            	else
            	{
            		assert( false );
            		propVal = EMPTY_STRING;            		
            	}
            }
            else if ( m_propCtrls[ i ] == null )
            {
                // The property is hidden from the user.  Therefore, just 
                // return the default value.  Notice that if the property
            	// is a choice, the propVal will be the choice's name, not 
            	// the choice value.
                propVal = getPropDefaultValue( i );
            }
            else
                assert( false );
            
            if ( propVal == null )
                propVal = EMPTY_STRING;
            
            String propName = getPropName( i );
            props.setProperty( propName, propVal );
        }
        
        return props;
    }
    
    protected int getPropCount()
    {
    	return getOrderedPropNameList().size();    		
    }
    
    protected String getPropGroupName( int propIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	return getManifestPropGroupName( propName );
    }
    
    protected String getPropChoiceSelection( int propIndex, int choiceIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	// First, attempt to read the choices from the existing data source design.
    	String choiceName = getDesignPropChoiceSelection( propName, choiceIndex );
    	if ( choiceName != null )
    		return choiceName;
    	
    	// Otherwise, try look up from the manifest.
    	return getManifestPropChoiceSelection( propName, choiceIndex );
    }
    
   
    protected String getPropName( int propIndex )
    {
    	return ( String ) getOrderedPropNameList().get( propIndex );
    }

    protected String getPropDisplayName( int propIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	// Get display name from data source design
    	String displayName = getDesignPropDisplayName( propName );
    	
    	// If the value exists, return it.
    	if ( displayName != null )
    		return displayName;
    	
    	// Otherwise, return the display name found from the manifest property.
    	return getManifestPropDisplayName( propName );
    }
    
    protected String getPropDefaultValue( int propIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	// Get default value from Data Source Design.
    	String defaultVal = getDesignPropDefaultValue( propName );
    	
    	// If the value exists, return it.
    	if ( defaultVal != null )
    		return defaultVal;

    	// Otherwise, return the default value found from the manifest property.
        return getManifestPropDefaultValue( propName );
    }
    
    protected Boolean isPropHidden( int propIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	// The attribute of whether the attribute is hidden is
    	// only available in the manifest, but not in the data source design.
    	return isManifestPropHidden( propName );
    }
    
    protected boolean isPropRequired( int propIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	// Look up the required/optional property info from the 
    	// existing data source design.
    	Boolean boolObj = isDesignPropRequired( propName );
    	if ( boolObj != null )
    		return ( boolObj.booleanValue() == false );

    	// Otherwise, by default we return false.
		// Currently we don't have any information about required field
		// in the manifest.
    	return false;
    }
    
    protected Boolean isPropReadOnly( int propIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	// Get Editable property from the existing Data Source Design.
    	Boolean isEditable = isDesignPropReadOnly( propName );
    	
    	// If value exists, return the information.
    	if ( isEditable != null )
    		return isEditable;
    	
    	// Otherwise, return the value from the manifest property.
    	return isManifestPropReadOnly( propName );
    }
    
    protected Boolean isPropEncryptable( int propIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	// Get Encryptable value from the existing data source design.
    	Boolean isEncryptable = isDesignPropEncryptable( propName );
    	
    	// If value exists, return it.
    	if ( isEncryptable != null )
    		return isEncryptable;
    	
    	// Otherwise, return the value obtained from the manifest property.
    	return isManifestPropEncryptable( propName );
    }
    
    protected ArrayList getPropChoices( int propIndex )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    
    	// Get choice label list from the existing data source design.
    	ArrayList choiceLabelList = getDesignPropChoiceLabels( propName );
    	
    	// If exists, use it.
    	if ( choiceLabelList != null && choiceLabelList.size() > 0 )
    		return choiceLabelList;
    	
    	// Otherwise, get choice label list from the manifest property.
    	return getManifestPropChoiceLabels( propName );
    }
    
    protected Integer findPropChoiceIndex( int propIndex, String propVal )
    {
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	Integer choiceIndex = findDesignPropChoiceIndex( propName, propVal );
    	if ( choiceIndex != null )
    		return choiceIndex;
    	
    	return findManifestPropChoiceIndex( propName, propVal );
    }
    
    protected org.eclipse.datatools.connectivity.oda.design.Property
	    getDesignProperty( String propName )
	{
    	if ( m_dataSourceDesignProps == null )
    		return null;
    	
		return m_dataSourceDesignProps.findProperty( propName );
	}
    
    protected String getDesignPropDisplayName( String propName )
    {
    	PropertyAttributes propAttrs = getDesignPropDesignAttrs( propName );
    	if ( propAttrs == null )
    		return null;
    	
    	return propAttrs.getDisplayName();
    }
    
    protected PropertyAttributes getDesignPropDesignAttrs( String propName )
    {
    	org.eclipse.datatools.connectivity.oda.design.Property prop = 
    		getDesignProperty( propName );
    	if ( prop == null )
    		return null;    	
    	
    	return prop.getDesignAttributes();
    }
    
    protected InputElementAttributes getDesignPropElementAttrs( String propName )
    {
    	PropertyAttributes propAttrs = getDesignPropDesignAttrs( propName );
    	if ( propAttrs == null )
    		return null;
    	
    	return propAttrs.getElementAttributes();
    }

    protected String getDesignPropDefaultValue( String propName )
    {
    	InputElementAttributes inputElemAttrs = getDesignPropElementAttrs( propName );
    	if ( inputElemAttrs == null )
    		return null;
    	
    	return inputElemAttrs.getDefaultScalarValue();
    }
    
    protected Boolean isDesignPropReadOnly( String propName )
    {
    	InputElementAttributes inputElemAttrs = getDesignPropElementAttrs( propName );
    	if ( inputElemAttrs == null )
    		return null;
    	
    	if ( inputElemAttrs.isSetEditable() )
    		return new Boolean( inputElemAttrs.isEditable() == false );
    	
    	return null;
    }
    
    protected Boolean isDesignPropRequired( String propName )
    {
    	InputElementAttributes inputElemAttrs = getDesignPropElementAttrs( propName );
    	if ( inputElemAttrs == null )
    		return null;
    	
    	if ( inputElemAttrs.isSetOptional() )
    		return new Boolean( inputElemAttrs.isOptional() );
    	
    	return null;
    }
    
    protected Boolean isDesignPropEncryptable( String propName )
    {
    	InputElementAttributes inputElemAttrs = getDesignPropElementAttrs( propName );
    	if ( inputElemAttrs == null )
    		return null;
    	
    	if ( inputElemAttrs.isSetMasksValue() )
    		return new Boolean( inputElemAttrs.isMasksValue() );
    	
    	return null;
    }
    
    protected EList getDesignPropChoices( String propName )
    {
    	InputElementAttributes inputElemAttrs = getDesignPropElementAttrs( propName );
    	if ( inputElemAttrs == null )
    		return null;
    	
    	ScalarValueChoices scalarValueChoices = inputElemAttrs.getStaticValueChoices();
    	if ( scalarValueChoices == null )
    		return null;
    	
    	return scalarValueChoices.getScalarValues();    	
    }
    
    protected ArrayList getDesignPropChoiceLabels( String propName ) 
    {
    	// Look up the choice list from the existing data source design.
    	EList choiceList = getDesignPropChoices( propName );
    	if ( choiceList == null || choiceList.size() == 0 )
    		return null;
    	
    	ArrayList choiceLabelList = new ArrayList();
    	for( int i = 0; i < choiceList.size(); i++ )
    	{
    		ScalarValueDefinition scalarValueDefn = 
    			( ( ScalarValueDefinition ) choiceList.get( i ) );
    		
    		// Use the display name as label.
    		String label = scalarValueDefn.getDisplayName();
    		
    		// If display name is empty, use the value.
    		if ( isEmpty( label ) )
    			label = scalarValueDefn.getValue();
    		
    		choiceLabelList.add( label );
    	}
    	
    	return choiceLabelList;
    }
    
    protected String getDesignPropChoiceSelection( String propName, int choiceIndex )
    {
    	EList choiceList = getDesignPropChoices( propName );
    	if ( choiceList == null || choiceList.size() == 0 )
    		return null;
    	
   		ScalarValueDefinition scalarValueDefn = 
   			( ( ScalarValueDefinition ) choiceList.get( choiceIndex ) );
    	
   		assert( scalarValueDefn != null );
   		return scalarValueDefn.getValue();
    }
    
    protected Integer findDesignPropChoiceIndex( String propName, String propVal )
    {
    	// Try obtain the choice labe list from the existing data source design.
    	EList choiceList = getDesignPropChoices( propName );
    	if ( choiceList == null || choiceList.size() == 0 )
    		return null;

    	for( int i = 0; i < choiceList.size(); i++ )
    	{
    		ScalarValueDefinition scalarValueDefn = 
    			( ( ScalarValueDefinition ) choiceList.get( i ) );
    		
    		if ( propVal.equals( scalarValueDefn.getValue() ) )
    			return new Integer( i );
    	}
    	
    	return new Integer( -1 );
    }
    
    protected void validatePropertyFields()
    {
        for( int i = 0 ; i < getPropCount(); i++ )
        {
            String propVal = null;
            
            if ( m_propCtrls[ i ] instanceof Text )
                propVal = ( ( Text ) m_propCtrls[ i ] ).getText();
            else if ( m_propCtrls[ i ] instanceof Combo )
            {
            	int index = ( ( Combo ) m_propCtrls[ i ] ).getSelectionIndex();
            	if ( index == -1 )
            		propVal = EMPTY_STRING;
            	else
            		propVal = getPropChoiceSelection( i , index );
            }
            else if ( m_propCtrls[ i ] == null )
            {
                // The property is hidden from the user.  Therefore, just 
                // return the default value.  Notice that if the property
            	// is a choice, the propVal will be the choice's name, not 
            	// the choice value.
                propVal = getPropDefaultValue( i );
            }
            else
                assert( false );
            
            if ( isPropRequired( i ) && isEmpty( propVal ) )
            {
                setMessage( Messages.ui_requiredFieldsMissingValue, 
                		IMessageProvider.WARNING );
                return;
            }
        }
        
        // If the code reaches here, all the required fields are non-empty.
        setMessage( DEFAULT_MESSAGE, IMessageProvider.NONE );
    }
    
    protected void initCustomControl( Properties profileProps )
    {
        for( int i = 0; i < getPropCount(); i++ )
        {
            String propVal = EMPTY_STRING;
            
            if ( profileProps != null )
                propVal = profileProps.getProperty( getPropName( i ) );    
            
            setPropertyControlValue( i, propVal );
        }
        
        validatePropertyFields();
    }

    protected ArrayList getOrderedPropNameList()
    {
    	// Check if we already have the ordered property name list
    	if ( m_orderedPropNameList != null )
    		return m_orderedPropNameList;

    	// Form a list of ordered property names.  This list keeps track
    	// of the actual sequence of the properties to be displayed in the UI page
    	ArrayList orderedPropNameList = new ArrayList();

    	// There are two cases:
    	// (1) If data source design exists, its public props takes precedence
    	//     over the manifest props.  The props will be displayed as follows:
    	//     - If there are props that exist in the data source design 
    	//       but not in the manifest, they will be displayed first.
    	//     - Then the props which are common to the data source design
    	//       and the manifest will be displayed.
    	// (2) If a data source design does not exist, the manifest props 
    	//     will be used.
    	
    	if( m_dataSourceDesignProps != null )
    	{
        	// First, we iterate through all the data source design public 
        	// props.  Add the ones that aren't existing in the manifest to the
        	// ordered prop name list.  We choose to display them first.
    		EList propList = m_dataSourceDesignProps.getProperties();
    		for( int i = 0; i < propList.size(); i++ )
    		{
    			org.eclipse.datatools.connectivity.oda.design.Property prop
    				= ( org.eclipse.datatools.connectivity.oda.design.Property ) propList.get( i );
    			if( getExtendedManifestProp( prop.getName() ) == null )
    			{
    			    /* this property is only defined in the data source design,
    			     * and not found in the extension manifest list
    			     * of extension-defined and framework-defined properties,
    			     * add it to the ordered property name list
    			     */
    				orderedPropNameList.add( prop.getName() );
    			}
    		}
    		
        	/* next we iterate through all the manifest properties intended for UI control, and 
    		 * filter/include only those that are also defined in the data source design.
    		 * Note that the loop is made on the manifest property collection
    		 * because it contains property defintion that has grouping information,
    		 * whereas the property defintion defined in data source design
    		 * has no group info.
    		 */
    		for( int i = 0; i < m_manifestProps.length; i++ )
    		{
    			Property prop = m_manifestProps[ i ];
                assert( prop != null );
    			String propName = prop.getName();
    			if( getDesignProperty( propName ) != null )  // also defined in dataSourceDesign
    			{
    			    orderedPropNameList.add( propName );    				
    			}
    		}
    	}
    	else
    	{
    		// Data Source Design does not exist, simply use all
    		// the properties in the manifest intended for UI control
    		for( int i = 0; i < m_manifestProps.length; i++ )
    		{
    			Property prop = m_manifestProps[ i ];
    			assert( prop != null );
   				orderedPropNameList.add( prop.getName() );    				
    		}    		
    	}
    	
    	m_orderedPropNameList = orderedPropNameList;
    	return m_orderedPropNameList;
    }
    
    protected void setupPropFields( Composite composite )
    {
        GridLayout layout = new GridLayout( );
        
        layout.numColumns = 5;
        composite.setLayout( layout );
        
        m_propCtrls = new Control[ getPropCount() ];
        
        String curGroupName = null;
        Composite curCtrlParent = null;
        
        for( int i = 0; i < getPropCount(); i++ )
        {
        	Boolean isHidden = isPropHidden( i );
        	
            // If the property is hidden, move on to the next prop.
        	if ( isHidden != null && isHidden.booleanValue() )
                continue;
            
            String propGroupName = getPropGroupName( i );
            
            if ( isEmpty( propGroupName ) )
            {
            	curCtrlParent = composite;
            	curGroupName = null;
            }
            else
            {
            	// This property belongs to a group.  See if this is 
            	// a new group or an existing group.
            	if ( hasSameGroup( propGroupName, curGroupName ) == false )
            	{
            		String propName = getPropName( i );
            		assert( propName != null );
            		
            		curCtrlParent = createGroupControl( composite, 
            				getManifestPropGroupDisplayName( propName ) );
            		
                    // Set the location of the group control.
                    GridData data = new GridData( GridData.FILL_HORIZONTAL );
                    data.horizontalSpan = 5;
                    curCtrlParent.setLayoutData( data );   
                    
            		curGroupName = propGroupName;
            	}
            }
            
            // Create property label and control.
            setUpPropertyLabelAndControl( curCtrlParent, i );
        }
        
        setPageComplete( true );
    }

    protected void setUpPropertyLabelAndControl( Composite parent, int propIndex )
    {
    	assert( parent != null );
    	
        // Set up the label.
        createPropertyLabel( parent, propIndex );

        // Set up the control for specifying the value.
        m_propCtrls[ propIndex ] = createPropertyControl( parent, propIndex );

        // Set the location of the control.
        GridData data = new GridData( GridData.FILL_HORIZONTAL );
        data.horizontalSpan = 4;
        m_propCtrls[ propIndex ].setLayoutData( data );    	
    }
    
    protected Group createGroupControl( Composite parent, String groupName )
    {
		Group groupCtrl = new Group( parent, SWT.NULL);
		groupCtrl.setLayout( new GridLayout() );
		groupCtrl.setText( groupName );
		
        GridLayout layout = new GridLayout( );
        
        layout.numColumns = 5;
        groupCtrl.setLayout( layout );
		
		return groupCtrl;
    }
    
    protected boolean isEmpty( String name )
    {
    	return ( name == null || name.length() == 0 );
    }
    
    protected boolean hasSameGroup( String propGroupName, String curGroupName )
    {
    	if ( isEmpty( propGroupName ) != isEmpty( curGroupName ) )
    		return false;
    	
    	if ( isEmpty( propGroupName ) && isEmpty( curGroupName ) )
    		return true;
    	
    	return ( propGroupName.equals( curGroupName ) );
    }
    
    private Property getExtendedManifestProp( String propName )
    {
        return getManifestProp( m_extendedManifestProps, propName );
    }
    
    protected Property getManifestProp( String propName )
    {
        return getManifestProp( m_manifestProps, propName );
    }
    
    private Property getManifestProp( Property[] properties, String propName )
    {
        if( properties == null )
            return null;
        
    	for( int i = 0; i < properties.length; i++ )
    	{
    		if ( properties[ i ].getName().equals( propName ) )
    			return properties[ i ];
    	}
    	
    	return null;
    }
    
    protected String getManifestPropDefaultValue( String propName )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;
    	
    	return prop.getDefaultValue();
    }
    
    protected String getManifestPropDisplayName( String propName )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;
    	
    	return prop.getDisplayName();
    }
    
    protected String getManifestPropGroupName( String propName )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;
    	
    	return prop.getGroupName();
    }
    
    protected String getManifestPropGroupDisplayName( String propName )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;
    	
    	return prop.getGroupDisplayName();
    }
    
    protected String getManifestPropChoiceSelection( String propName, int choiceIndex )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;
    	
    	PropertyChoice[] choices = prop.getChoices();
    	PropertyChoice choice = choices[ choiceIndex ];
    	assert( choice != null );
    	return choice.getName();    	
    }

    protected Boolean isManifestPropHidden( String propName )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;
    	
    	boolean boolVal = 
    		( prop.isVisible( m_manifestPropsVisibility ) == false );
    	
        return new Boolean( boolVal );
    }
    
    protected Boolean isManifestPropReadOnly( String propName )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;
    	
    	boolean boolVal = 
    		( prop.isEditable( m_manifestPropsVisibility ) == false ) ;
    	
    	return new Boolean( boolVal );
    }
    
    protected Boolean isManifestPropEncryptable( String propName )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;

    	return new Boolean( prop.isEncryptable() );
    }

    protected PropertyChoice[] getManifestPropChoices( String propName )
    {
    	Property prop = getManifestProp( propName );
    	if ( prop == null )
    		return null;
    	
    	return prop.getChoices();    	
    }
    
    protected Integer findManifestPropChoiceIndex( String propName, String propVal )
    {
    	PropertyChoice[] choices = getManifestPropChoices( propName );
    	if ( choices == null || choices.length == 0 )
    		return null;
    	
    	for( int j = 0; j < choices.length; j++ )
    	{
   			// Compare the manifest choice name with the prop value.
   			if ( choices[ j ].getName().equals( propVal ) )
   				return new Integer( j );
   		}
        
    	// the match by choice names is done first for backward compatibility;
        // if no match to any choice name, then compare to the manifest choice value
        for( int j = 0; j < choices.length; j++ )
        {
            // Compare the manifest choice name with the prop value.
            if ( choices[ j ].getValue().equals( propVal ) )
                return new Integer( j );
        }

    	return new Integer( -1 );    	
    }
    
    protected ArrayList getManifestPropChoiceLabels( String propName )
    {
    	PropertyChoice[] choices = getManifestPropChoices( propName );
    	if ( choices == null || choices.length == 0 )
    		return null;
    	
    	ArrayList choiceLabelList = new ArrayList();
    	for( int j = 0; j < choices.length; j++ )
    	{
    		// We first try use the display name as the label.
    		String label = choices[ j ].getDisplayName();
    		
    		// Make sure label is not empty.
    		if ( isEmpty( label ) )
    		{
    			// If empty, use the name instead.  Name must be non-empty.
    			label = choices[ j ].getName();
    		}
    		
    		choiceLabelList.add( label );
    	}
    	
    	return choiceLabelList;
    }
    


    protected void createPropertyLabel( Composite composite, int propIndex )
    {
        Label labelCtrl = new Label( composite, SWT.NONE );
        String displayName = getPropDisplayName( propIndex );
        
        if ( isEmpty( displayName ) )
            displayName = getPropName( propIndex );
        
        String displayText = displayName + COLON_CHAR;
        if ( isPropRequired( propIndex ) )
        	displayText += REQUIRED_FIELD_SYMBOL;
        
        labelCtrl.setText( displayText );
    }
    
    protected Control createPropertyControl( Composite composite, int propIndex )
    {
        Control propCtrl = null;
        
        int style =  SWT.BORDER;

        ArrayList choiceLabelList = getPropChoices( propIndex );
        if ( choiceLabelList != null && choiceLabelList.size() > 0 )
        {
            // Use read-only style for combo means that the display text is 
            // not editable.  The selection can still be changed.
            Combo choiceCombo = new Combo( composite, 
                    style | SWT.DROP_DOWN | SWT.READ_ONLY );
            
            for( int j = 0; j < choiceLabelList.size(); j++ )
            {
             	String label = ( String ) choiceLabelList.get( j );
                	
               	// Choice label must not be empty.
               	assert( ! isEmpty( label ) );
                choiceCombo.add( label );
            }

            // Disable the control if it is read only.
            Boolean isReadOnly = isPropReadOnly( propIndex );
           	choiceCombo.setEnabled( isReadOnly != null && isReadOnly.booleanValue() == false );
           	
            propCtrl = choiceCombo;
        }
        else
        {
            // Since there are currently only two types:  string or choices,
            // if it is not choices then it must be string.
        	Boolean isReadOnly = isPropReadOnly( propIndex );
            if ( isReadOnly != null && isReadOnly.booleanValue() )
            	style |= SWT.READ_ONLY;
            
            Boolean isEncryptable = isPropEncryptable( propIndex );
            if ( isEncryptable != null && isEncryptable.booleanValue() )
            	style |= SWT.PASSWORD;
                
            Text textCtrl = new Text( composite, style | SWT.SINGLE );
                
            propCtrl = textCtrl;
        }
        
        if ( propCtrl instanceof Combo )
        	( ( Combo ) propCtrl ).addModifyListener( createModifyListener() );
        else if ( propCtrl instanceof Text )
        	( ( Text ) propCtrl ).addModifyListener( createModifyListener() );
        else
        	assert( false );
       
        return propCtrl;
    }
    
    protected ModifyListener createModifyListener()
    {
        return new ModifyListener()
        {    
            public void modifyText( ModifyEvent e )
            {
                validatePropertyFields();
            }
        };
    }
    
    protected void setPropertyControlValue( int propIndex, String propertyVal )
    {
    	Control propCtrl = m_propCtrls[ propIndex ];
    	
    	String propName = getPropName( propIndex );
    	assert( propName != null );
    	
    	Boolean isHidden = isManifestPropHidden( propName );
        if ( isHidden != null && isHidden.booleanValue() )
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
        	propVal = getPropDefaultValue( propIndex );
            if ( propVal == null )
            	propVal = EMPTY_STRING;
        }
        
        if ( propCtrl instanceof Text )
        {
            ( ( Text ) propCtrl ).setText( propVal );
        }
        else if ( propCtrl instanceof Combo )
        {
            Combo choiceCombo = ( Combo ) propCtrl;

            // From the property value, look up the corresponding
            // property choice and select it in the combobox.
            Integer choiceIndex = findPropChoiceIndex( propIndex , propVal );
            if ( choiceIndex != null )
            {
            	choiceCombo.select( choiceIndex.intValue() );
            }
            else
            {
            	// There is no matching selection.  Will just use
            	// the first choice for now.
            	choiceCombo.select( 0 );
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
