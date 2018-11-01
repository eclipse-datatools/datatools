/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 * 			     IBM Corporation - fix for 213266
 *               Actuate Corporation - re-factored to an extendable base class
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.ui.drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCConnectionProfileConstants;
import org.eclipse.datatools.connectivity.internal.ui.DelimitedStringList;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.ase.JDBCASEProfileMessages;
import org.eclipse.datatools.enablement.sybase.ISybaseJDBCConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.ase.internal.ui.connection.drivers.AdditionalControlManager;
import org.eclipse.datatools.enablement.sybase.ase.internal.ui.connection.drivers.IAdditionalControl;
import org.eclipse.datatools.enablement.sybase.ui.JDBCProfileMessages;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class ASEDriverUIContributorBase implements IDriverUIContributor, Listener, ModifyListener
{
    protected static final String           EMPTY_STRING      = "";   //$NON-NLS-1$

    private ScrolledComposite               scrolledComposite = null;

    protected Text                          mHostText;
    protected Text                          mPortText;
    protected Text                          mDatabaseText;
    protected Text                          mDBUIDText;
    protected Text                          mDBPWDText;

    protected Button                        mSaveDBPWDCheckbox;

    protected DelimitedStringList           mDBConnProps;

    private IDriverUIContributorInformation contributorInformation;

    private Properties                      properties;

    protected DialogPage                    parentPage;

    protected boolean                       isReadOnly        = false;

    public boolean determineContributorCompletion()
    {
        boolean isComplete = true;
        boolean isPortValid = true;
        try
        {
            Integer.valueOf(this.mPortText.getText());
        }
        catch (NumberFormatException ex)
        {
            isPortValid = false;
        }

        if (mDBUIDText.getText().trim().length() < 1)
        {
            parentPage.setErrorMessage(JDBCASEProfileMessages.getString("JDBCProfileTabs.errors.requiredUID")); //$NON-NLS-1$
            isComplete = false;
        }
        else if (mHostText.getText().trim().length() < 1)
        {
            parentPage.setErrorMessage(JDBCASEProfileMessages.getString("JDBCASEProfileTabs.errors.requiredHost")); //$NON-NLS-1$
            isComplete = false;
        }
        else if (mPortText.getText().trim().length() < 1)
        {
            parentPage.setErrorMessage(JDBCASEProfileMessages.getString("JDBCASEProfileTabs.errors.requiredPort")); //$NON-NLS-1$
            isComplete = false;
        }
        else if (!isPortValid)
        {
            parentPage.setErrorMessage(JDBCASEProfileMessages
                    .getString("JDBCASEProfileTabs.errors.requiredPortValidNumber")); //$NON-NLS-1$
            isComplete = false;
        }
        else if (mDBConnProps.getWarning() != null)
        {
            parentPage.setErrorMessage(mDBConnProps.getWarning());
        }

        if (isComplete)
        {
            parentPage.setErrorMessage(null);
        }
        return isComplete;
    }

    public Composite getContributedDriverUI(Composite parent, boolean isReadOnly)
    {
        if ((scrolledComposite == null) || scrolledComposite.isDisposed() || (this.isReadOnly != isReadOnly))
        {
            this.isReadOnly = isReadOnly;
            int additionalStyles = SWT.NONE;
            if (isReadOnly)
            {
                additionalStyles = SWT.READ_ONLY;
            }

            scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
            scrolledComposite.setExpandHorizontal(true);
            scrolledComposite.setExpandVertical(true);
            scrolledComposite.setLayout(new GridLayout());

            TabFolder tabFolder = new TabFolder(scrolledComposite, SWT.TOP);

            TabItem generalTab = new TabItem(tabFolder, SWT.None);
            generalTab.setText(JDBCProfileMessages.getString("JDBCProfileTabs.tabs.label.connection")); //$NON-NLS-1$

            TabItem optionalTab = new TabItem(tabFolder, SWT.None);
            optionalTab.setText(JDBCProfileMessages.getString("JDBCProfileTabs.tabs.label.other")); //$NON-NLS-1$

            Composite generalComposite = new Composite(tabFolder, SWT.NULL);
            GridLayout layout = new GridLayout();
            layout.numColumns = 2;
            generalComposite.setLayout(layout);
            generalTab.setControl(generalComposite);

            Composite optionalComposite = new Composite(tabFolder, SWT.NULL);
            layout = new GridLayout();
            layout.numColumns = 1;
            optionalComposite.setLayout(layout);
            optionalTab.setControl(optionalComposite);

            // general properties UI
            layout = new GridLayout(2, false);
            generalComposite.setLayout(layout);
            
            // create additional control
            IAdditionalControl additionalControl = AdditionalControlManager.getInstance().getAdditionalControl();
            if (additionalControl != null)
            {
                additionalControl.setAgentPluginID(AdditionalControlManager.getInstance().getId());
                additionalControl.createControllerContents(generalComposite);
            }

            this.mHostText = (Text) createLabelTextPair(generalComposite, JDBCASEProfileMessages
                    .getString("JDBCASEPropertyWizardPage.hostName.label"), //$NON-NLS-1$
                    this.mHostText, SWT.BORDER | additionalStyles, GridData.FILL_HORIZONTAL);

            this.mPortText = (Text) createLabelTextPair(generalComposite, JDBCASEProfileMessages
                    .getString("JDBCASEPropertyWizardPage.portNumber.label"), //$NON-NLS-1$
                    this.mPortText, SWT.BORDER | additionalStyles, GridData.FILL_HORIZONTAL);
            
         // hook host and port to additional control
            if (additionalControl != null)
            {
                additionalControl.assignServerText(mHostText, mPortText);
            }
            
            this.mDatabaseText = (Text) createLabelTextPair(generalComposite, JDBCASEProfileMessages
                    .getString("JDBCASEPropertyWizardPage.databaseName.label"), //$NON-NLS-1$
                    this.mPortText, SWT.BORDER | additionalStyles, GridData.FILL_HORIZONTAL);

            this.mDBUIDText = (Text) createLabelTextPair(generalComposite, JDBCASEProfileMessages
                    .getString("JDBCPropertyWizardPage.userName.label"), //$NON-NLS-1$ 
                    this.mDBUIDText, SWT.BORDER | additionalStyles, GridData.FILL_HORIZONTAL);

            this.mDBPWDText = (Text) createLabelTextPair(generalComposite, JDBCASEProfileMessages
                    .getString("JDBCPropertyWizardPage.password.label"), //$NON-NLS-1$, 
                    this.mDBPWDText, SWT.BORDER | additionalStyles, GridData.FILL_HORIZONTAL); 
            this.mDBPWDText.setEchoChar('*');

            this.mSaveDBPWDCheckbox = new Button(generalComposite, SWT.CHECK);
            this.mSaveDBPWDCheckbox.setText(JDBCASEProfileMessages
                    .getString("JDBCPropertyWizardPage.persistpassword.label")); //$NON-NLS-1$
            this.mSaveDBPWDCheckbox.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, true, false, 2, 1));

            // other properties
            layout = new GridLayout(2, false);
            optionalComposite.setLayout(layout);
            this.mDBConnProps = new DelimitedStringList(optionalComposite, SWT.NONE, isReadOnly);
            GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
            gdata.horizontalSpan = 2;
            ((DelimitedStringList) this.mDBConnProps).setLayoutData(gdata);

            //
            scrolledComposite.setContent(tabFolder);
            scrolledComposite.setMinSize(tabFolder.computeSize(SWT.DEFAULT, SWT.DEFAULT));

            initialize();
        }

        return scrolledComposite;
    }

    private void addListeners()
    {
        mDatabaseText.addListener(SWT.Modify, this);
        mDBPWDText.addListener(SWT.Modify, this);
        mDBUIDText.addListener(SWT.Modify, this);
        mHostText.addListener(SWT.Modify, this);
        mPortText.addListener(SWT.Modify, this);
        mSaveDBPWDCheckbox.addListener(SWT.Selection, this);
        mDBConnProps.addModifyListener(this);
    }

    private void removeListeners()
    {
        mDatabaseText.removeListener(SWT.Modify, this);
        mDBPWDText.removeListener(SWT.Modify, this);
        mDBUIDText.removeListener(SWT.Modify, this);
        mHostText.removeListener(SWT.Modify, this);
        mPortText.removeListener(SWT.Modify, this);
        mSaveDBPWDCheckbox.removeListener(SWT.Selection, this);
        mDBConnProps.removeModifyListener(this);
    }

    private void initialize()
    {
        addListeners();
    }

    public void modifyText(ModifyEvent e)
    {
        handleEvent(new Event());
    }

    public void handleEvent(Event event)
    {
        if (isReadOnly)
        {
            if (event.widget == mSaveDBPWDCheckbox)
            {
                mSaveDBPWDCheckbox.setSelection(!mSaveDBPWDCheckbox.getSelection());
            }
        }
        else
        {
            setConnectionInformation();
        }
    }

    public void setConnectionInformation()
    {
        properties.setProperty(ISybaseJDBCConnectionProfileConstants.DATABASE_NAME_PROP_ID, this.mDatabaseText
                .getText().trim());
        properties.setProperty(ISybaseJDBCConnectionProfileConstants.PASSWORD_PROP_ID, this.mDBPWDText.getText());
        properties.setProperty(ISybaseJDBCConnectionProfileConstants.USERNAME_PROP_ID, this.mDBUIDText.getText());
        properties.setProperty(ISybaseJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID, String
                .valueOf(mSaveDBPWDCheckbox.getSelection()));
        properties.setProperty(ISybaseJDBCConnectionProfileConstants.PROP_HOST, this.mHostText.getText().trim());
        properties.setProperty(ISybaseJDBCConnectionProfileConstants.PROP_PORT, this.mPortText.getText().trim());
        properties.setProperty(IJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID, this.mDBConnProps
                .getSelection());
        properties.setProperty(ISybaseJDBCConnectionProfileConstants.URL_PROP_ID, getURL());
        this.contributorInformation.setProperties(properties);
    }

    /**
     * @param parent
     * @param labelText
     * @param ctl
     * @param style
     * @param gData
     * @return
     */
    protected Control createLabelTextPair(Composite parent, String labelText, Control ctl, int style, int gData)
    {
        Label label = new Label(parent, SWT.NULL);
        label.setLayoutData(new GridData(SWT.FILL, SWT.NULL, false, false));
        label.setText(labelText);

        ctl = new Text(parent, style);
        ctl.setLayoutData(new GridData(SWT.FILL, SWT.NULL, true, false));

        return ctl;
    }

    public List getSummaryData()
    {
        List summaryData = new ArrayList();
        summaryData.add(new String[]
        {
            JDBCASEProfileMessages.getString("JDBCASEPropertyWizardPage.summary.connProps.label"), //$NON-NLS-1$
            mDBConnProps.getSelection()
        });

        summaryData.add(new String[]
        {
            JDBCASEProfileMessages.getString("JDBCASEPropertyWizardPage.summary.host.label"), //$NON-NLS-1$
            mHostText.getText().trim()
        });

        summaryData.add(new String[]
        {
            JDBCASEProfileMessages.getString("JDBCASEPropertyWizardPage.summary.port.label"), //$NON-NLS-1$
            mPortText.getText().trim()
        });

        summaryData.add(new String[]
        {
            JDBCASEProfileMessages.getString("JDBCASEPropertyWizardPage.summary.userName.label"), //$NON-NLS-1$
            mDBUIDText.getText().trim()
        });

        String pwdMask = ""; //$NON-NLS-1$
        String dbPwd = mDBPWDText.getText().trim();

        for (int i = 0; i < dbPwd.length(); i++)
        {
            pwdMask = pwdMask + "*"; //$NON-NLS-1$
        }

        summaryData.add(new String[]
        {
            JDBCASEProfileMessages.getString("JDBCASEPropertyWizardPage.summary.password.label"), //$NON-NLS-1$
            pwdMask
        });
        return summaryData;
    }

    public void loadProperties()
    {
        removeListeners();

        String databaseName = this.properties.getProperty(ISybaseJDBCConnectionProfileConstants.DATABASE_NAME_PROP_ID);
        if (databaseName != null)
        {
            mDatabaseText.setText(databaseName);
        }

        String hostName = this.properties.getProperty(ISybaseJDBCConnectionProfileConstants.PROP_HOST);
        if (hostName != null)
        {
            mHostText.setText(hostName);
        }

        String portNo = this.properties.getProperty(ISybaseJDBCConnectionProfileConstants.PROP_PORT);
        if (portNo != null)
        {
            mPortText.setText(portNo);
        }

        String username = this.properties.getProperty(ISybaseJDBCConnectionProfileConstants.USERNAME_PROP_ID);
        if (username != null)
        {
            mDBUIDText.setText(username);
        }

        String password = this.properties.getProperty(ISybaseJDBCConnectionProfileConstants.PASSWORD_PROP_ID);
        if (password != null)
        {
            mDBPWDText.setText(password);
        }

        String savePassword = this.properties.getProperty(ISybaseJDBCConnectionProfileConstants.SAVE_PASSWORD_PROP_ID);
        if ((savePassword != null) && Boolean.valueOf(savePassword) == Boolean.TRUE)
        {
            mSaveDBPWDCheckbox.setSelection(true);
        }

        String connectionProperties = this.properties
                .getProperty(ISybaseJDBCConnectionProfileConstants.CONNECTION_PROPERTIES_PROP_ID);
        if (connectionProperties != null)
        {
            this.mDBConnProps.setSelection(connectionProperties);
        }

        addListeners();
        setConnectionInformation();
    }

    public void setDialogPage(DialogPage parentPage)
    {
        this.parentPage = parentPage;
    }

    public void setDriverUIContributorInformation(IDriverUIContributorInformation contributorInformation)
    {
        this.contributorInformation = contributorInformation;
        this.properties = contributorInformation.getProperties();
    }

    public String getURL()
    {
        String host = EMPTY_STRING;
        String port = EMPTY_STRING;
        String dbName = EMPTY_STRING;
        if (this.mPortText.getText() != null)
        {
            port = this.mPortText.getText();
        }
        if (this.mHostText.getText() != null)
        {
            host = this.mHostText.getText();
        }
        if (this.mDatabaseText.getText() != null)
        {
            dbName = this.mDatabaseText.getText();
        }
        String driverURL = JDBCASEPlugin.makeDriverURL(host, port, dbName);
        return driverURL;
    }

}
