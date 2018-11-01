/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import java.util.Collection;

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.ScrapbookEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * TODO add preference page to set the default connection profile type
 * This type of composite separates the connection profile type drop down and the profile name drop down
 * <p>
 * Supported styles:
 * <li>STYLE_CREATE_PROFILE</li>
 * <li>STYLE_MUST_CONNECT</li>
 * <li>STYLE_LAZY_INIT</li>
 * <li>STYLE_SEPARATE_TYPE_NAME(always)</li>
 * <li>STYLE_SINGLE_GROUP/STYLE_LABEL_GROUP</li>
 * <li>STYLE_SHOW_STATUS</li>
 * </p>
 * @author Hui Cao
 * 
 */
public class ConnectionInfoComposite2 extends AbstractConnectionInfoComposite implements SelectionListener,
Listener {
    private Label _labelName = null;

    private Combo _comboProfileName = null;

    private Combo _comboType = null;

    private Label _labelType = null;

    private Label _labelStatus = null;
    
    private Label _labelDbName = null;

    private Combo _combodbName = null;

    private Button _create = null;

    public ConnectionInfoComposite2(Composite parent, int style, Listener listener, ISQLEditorConnectionInfo connInfo,
            Collection supportedDBDefinitionNames, int infoStyle)
    {
        this(parent, style, listener, connInfo, supportedDBDefinitionNames, infoStyle, null);
    }

    public ConnectionInfoComposite2(Composite parent, int style, Listener listener, ISQLEditorConnectionInfo connInfo,
            Collection supportedDBDefinitionNames, int infoStyle, String title)
    {
        super(parent, style, listener, connInfo, supportedDBDefinitionNames, STYLE_SEPARATE_TYPE_NAME | infoStyle);
        createContents(title);
        if ((_style & STYLE_LAZY_INIT ) == 0)
        {
            init();
        }        
    }
    
    protected Control createContents(String title) {
        this.setLayoutData(new GridData(GridData.FILL_BOTH));
        org.eclipse.swt.layout.GridLayout gridLayout1 = new GridLayout();
        gridLayout1.marginWidth = 0;
        gridLayout1.marginHeight = 0;
        
        Group group = null;
        if ((_style & STYLE_SINGLE_GROUP) > 0 )
        {
            //STYLE_SINGLE_GROUP
            gridLayout1.numColumns = 1;
            group = new Group(this, SWT.NONE);
            group.setText(title == null? Messages.ConnectionInfoGroup_profile_group: title);
        }
        else
        {
            gridLayout1.numColumns = 2;    
            Label prefix = new Label(this, SWT.NONE);
            prefix.setText(title == null?Messages.ConnectionInfoGroup_profile: title);
            prefix.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER, GridData.VERTICAL_ALIGN_END, false, true));
            group = new Group(this, SWT.NONE);
        }
        this.setLayout(gridLayout1);
        createGroupContents(group);

        return this;
    }
    
    protected void updateFields()
    {
        readControlValues();
        boolean isAuto = true;
        if(_connInfo instanceof ScrapbookEditorConnectionInfo)
        {
            isAuto = ((ScrapbookEditorConnectionInfo) _connInfo).isAuto();
        }
        
        ScrapbookEditorConnectionInfo seConnInfo = new ScrapbookEditorConnectionInfo(new SQLEditorConnectionInfo(_dbVendorId, _profileName, _dbName));
        seConnInfo.setAutoCommit(isAuto);
        _connInfo = seConnInfo;
    }
    
    private void createGroupContents(Group group)
    {
        GridData gridData = new GridData(SWT.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false);
        group.setLayoutData(gridData);
        
        // add controls to composite as necessary
        org.eclipse.swt.layout.GridLayout gridLayout1 = new GridLayout();
        org.eclipse.swt.layout.GridData gridData3 = new org.eclipse.swt.layout.GridData();
        org.eclipse.swt.layout.GridData gridData4 = new org.eclipse.swt.layout.GridData();

        gridLayout1.marginWidth = 5;
        gridLayout1.marginHeight = 5;

        if ((_style & STYLE_SHOW_STATUS) > 0)
        {
            gridLayout1.numColumns = 8;
        }
        else
        {
            gridLayout1.numColumns = 6;
        }
        group.setLayout(gridLayout1);
        
        _labelType = new Label(group, SWT.NONE);
        _labelType.setText(Messages.ConnectionInfoGroup_type); //$NON-NLS-1$
        createComboType(group);

        _labelName = new Label(group, SWT.NONE);
        _labelName.setText(Messages.ConnectionInfoGroup_name); //$NON-NLS-1$
        //connectivity layer does not associate profile with database defintions,
        //so we have to use UIComponentService to create those wizards
        Composite compositeName = new Composite(group, SWT.NONE);
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        compositeName.setLayoutData(gridData3);
        createComboProfileName(compositeName);

        _labelDbName = new Label(group, SWT.NONE);
        _labelDbName.setText(Messages.ConnectionInfoGroup_database);
        createComboDbName(group);

        if ((_style & STYLE_SHOW_STATUS) > 0)
        {
            Label label = new Label(group, SWT.NONE);
            label.setText(Messages.ConnectionInfoGroup_status);
            
            _labelStatus = new Label(group, SWT.NONE);
            _labelStatus.setText(Messages.ConnectionInfoGroup_status_disconnected);//to take up the place
            _labelStatus.setText(getStatus());
            gridData4.grabExcessHorizontalSpace = true;
            gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
// Don't use the widthHint, since it causes text truncation in some languages
//            gridData4.widthHint = 200;
            _labelStatus.setLayoutData(gridData4);
            
        }
    }

    /**
     * This method initializes _comboType
     * 
     */
    private void createComboType(Composite composite) {
        // com.sybase.stf.servers.core.category.database
        org.eclipse.swt.layout.GridData gridData4 = new org.eclipse.swt.layout.GridData();
        _comboType = new Combo(composite, SWT.NONE | SWT.READ_ONLY);
        gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData4.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData4.grabExcessHorizontalSpace = true;
        _comboType.setLayoutData(gridData4);
        _comboType.setVisibleItemCount(20);

        _comboType.addSelectionListener(this);

    }

    private void createComboProfileName(Composite composite) {
        org.eclipse.swt.layout.GridLayout gridLayout2 = new GridLayout();
        if ((_style & STYLE_CREATE_PROFILE) > 0)
        {
            gridLayout2.numColumns = 2;
        }
        else
        {
            gridLayout2.numColumns = 1;
        }
        gridLayout2.marginHeight = 0;
        gridLayout2.marginWidth = 0;
        composite.setLayout(gridLayout2);

        org.eclipse.swt.layout.GridData gridData5 = new org.eclipse.swt.layout.GridData();
        _comboProfileName = new Combo(composite, SWT.NONE | SWT.READ_ONLY);
        gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData5.grabExcessHorizontalSpace = true;
        _comboProfileName.setVisibleItemCount(30);
        _comboProfileName.setLayoutData(gridData5);

        if ((_style & STYLE_CREATE_PROFILE) > 0)
        {
            _create = new Button(composite, SWT.PUSH);
            _create.setText(Messages.SelectProfileDialog_create); //$NON-NLS-1$
            _create.addListener(SWT.Selection, this);
        }

        _comboProfileName.addSelectionListener(this);
    }

    private void createComboDbName(Composite composite) {
        org.eclipse.swt.layout.GridData gridData5 = new org.eclipse.swt.layout.GridData();
        _combodbName = new Combo(composite, SWT.READ_ONLY);
        gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData5.grabExcessHorizontalSpace = true;
        _combodbName.setVisibleItemCount(30);
        _combodbName.setLayoutData(gridData5);

        if (_comboProfileName.getSelectionIndex() == -1) {
            _combodbName.setEnabled(false);
        }

        _combodbName.addSelectionListener(this);

    }



    public Combo getProfileTypeControl()
    {
        return _comboType;
    }
    
    public Combo getDbNamesControl()
    {
        return _combodbName;
    }

    public Combo getProfileNamesControl()
    {
        return _comboProfileName;
    }

    public void init(String dbVendorName, String initialProfName, String initialDBName)
    {
        super.init(dbVendorName, initialProfName, initialDBName);
        
        if (_labelStatus != null)
        {
            _labelStatus.setText(getStatus());
        }
    }

    protected void setConnectionInfo(String dbVendorName, String initialProfName, String initialDBName)
    {
        super.setConnectionInfo(dbVendorName, initialProfName, initialDBName);
        
        ScrapbookEditorConnectionInfo seConnInfo = new ScrapbookEditorConnectionInfo(_connInfo);
        
        _connInfo = seConnInfo;
    }
    
    private String getStatus()
    {
        StringBuffer sb = new StringBuffer();
        
        if (_connInfo!= null && _connInfo.isConnected())
        {
            sb.append(Messages.ConnectionInfoGroup_status_connected);
        }
        else
        {
            sb.append(Messages.ConnectionInfoGroup_status_disconnected);
        }
        
        if ((_style & STYLE_SHOW_COMMIT_MODE) > 0 && _connInfo instanceof ScrapbookEditorConnectionInfo)
        {
            sb.append(", " + (((ScrapbookEditorConnectionInfo) _connInfo).isAuto() ? Messages.ConnectionInfoGroup_status_autocommit : Messages.ConnectionInfoGroup_status_manualcommit));
        }
        
        return sb.toString();
    }
    
    public void refreshConnectionStatus()
    {
        if (_labelStatus != null)
        {
            _labelStatus.setText(getStatus());
        }
    }

    public Button getCreateButton()
    {
        return _create;
    }
}
