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
package org.eclipse.datatools.sqltools.common.ui.preferences;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.datatools.sqltools.common.ui.internal.Messages;
import org.eclipse.datatools.sqltools.common.ui.util.SWTUtils;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The preference pages in DTP that want to accomodate vendor specific setting should extend this class, which manages the vendor settings 
 * by notifying the IDataServerPreferenceSection at propriate time. Also this is FieldEditorPreferencePage, subclass can choose not to use FieldEditor
 * by passing "false" to the constructor. Normally subclass needs to implement getPreferencePageId() to tell the parent class which IDataServerPreferenceSection
 * to load and to override init(IWorkbench workbench) to set the proper preference store.
 * @author Dafan Yang
 * @author Hui Cao
 *
 */
public abstract class AbstractDBPreferenceFieldPage extends FieldEditorPreferencePage  implements IWorkbenchPreferencePage, Listener
{
    private TabFolder        _folder;
    private Map              _sections       = new HashMap();
    private boolean          _useFieldEditor = false;


    public AbstractDBPreferenceFieldPage()
    {
        this(false);
    }

    /**
     * @param useFieldEditor Whether to use FieldEditor in the vendor-neatural part.
     */
    public AbstractDBPreferenceFieldPage(boolean useFieldEditor)
    {
        super(GRID);
        this._useFieldEditor = useFieldEditor;
    }

    public AbstractDBPreferenceFieldPage(int style)
    {
        super(style);
    }


    public AbstractDBPreferenceFieldPage(String title, ImageDescriptor image, int style)
    {
        super(title, image, style);
    }


    public AbstractDBPreferenceFieldPage(String title, int style)
    {
        super(title, style);
    }


    /**
     * subclass should override this method to set proper preference store
     */
    public void init(IWorkbench workbench)
    {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.IPreferencePage#performOk()
     */
    public boolean performOk()
    {
        Iterator iter = _sections.values().iterator();
        while(iter.hasNext())
        {
            IDataServerPreferenceSection accessor = (IDataServerPreferenceSection) iter.next();
            accessor.saveToPreferenceStore(getPreferenceStore());
        }
        return super.performOk();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent)
    {
        Composite comp = new Composite(parent, SWT.NONE);
        comp.setLayout(new TabFolderContainerLayout());
        GridData gridData = new GridData(GridData.FILL_BOTH);
        comp.setLayoutData(gridData);

        if (_useFieldEditor)
        {
            //Field editors are supposed to be data server agnostic and appear on top of the page
            super.createContents(comp);
        }

        _folder = null;

        Map sections = PreferencesRegistry.INSTANCE.getPageSections(getPreferencePageId());
        Object[] sectionsKeys = sections.keySet().toArray();
        Arrays.sort(sectionsKeys, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                // Replace the '&' in the label because of the short cut key.Otherwise, for instance, the "AS&E" will be before "ASA".
                int result = o1.toString().replace("&", "").compareTo(o2.toString().replace("&", ""));
                return result == 0 ? 0 : ( result > 0 ? 1 : -1);
            }

        });
        
        for (int i = 0; i < sectionsKeys.length; i++)
        {
            String id = (String)sectionsKeys[i];
            //TODO: this is just a temporary solution. actually name should be retrieved from dbdefinition by id. 
            String name = id;
            if (name == null)
            {
                Activator.getDefault().log(NLS.bind(Messages.AbstractDBPreferenceFieldPage_nodb, (new Object[]
				{
				    id
				}))); //$NON-NLS-1$
                continue;
            }
            IDataServerPreferenceSection section = (IDataServerPreferenceSection)sections.get(id);
            if (section != null)
            {
                if (_folder == null)
                {
                    _folder = new TabFolder(comp, SWT.NONE);
                    GridData gd = new GridData(GridData.FILL_BOTH);
                    _folder.setLayoutData(gd);
                    _folder.setLayout(new GridLayout());
                }
                TabItem tabItem = new TabItem(_folder, SWT.NONE);
                section.createSectionComposite(_folder);
                tabItem.setControl(section.getSectionComposite());
                tabItem.setText(name);
                if (section instanceof IDataServerLaunchPreferenceSection)
                {
                    ((IDataServerLaunchPreferenceSection)section).setMode(IDataServerLaunchPreferenceSection.PREFERENCE_MODE); 
                }
                _sections.put(name, section);
            }
        }

        if (_folder != null)
        {
            _folder.layout();
        }
        comp.pack();

        initializeValues();
        SWTUtils.listenModify(comp, this, null);
        return comp;
    }

    /*
     * Initializes states of the controls from the preference store.
     */
    protected void initializeValues()
    {
        Iterator iter = _sections.values().iterator();
        while(iter.hasNext())
        {
            IDataServerPreferenceSection accessor = (IDataServerPreferenceSection) iter.next();
            accessor.loadFromPreferenceStore(getPreferenceStore());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
     */
    protected void performDefaults()
    {
        //void NullPointException
        if(_folder == null)
        {
            return;
        }
        TabItem currentItem = _folder.getItem(_folder.getSelectionIndex());
        IDataServerPreferenceSection accessor = (IDataServerPreferenceSection)_sections.get(currentItem.getText());
        accessor.loadDefaultPreference(getPreferenceStore());
        super.performDefaults();
    }

    /**
     * Applies the status to the status line of a dialog page.
     */
    protected void applyToStatusLine(IStatus status)
    {
        String message = status.getMessage();
        if (message.length() == 0)
        message = null;
        switch (status.getSeverity())
        {
            case IStatus.OK:
                setErrorMessage(null);
                setMessage(message);
                break;
            case IStatus.WARNING:
                setErrorMessage(null);
                setMessage(message, WizardPage.WARNING);
                break;
            case IStatus.INFO:
                setErrorMessage(null);
                setMessage(message, WizardPage.INFORMATION);
                break;
            default:
                setErrorMessage(message);
                setMessage(null);
                break;
        }
    }

    /**
     * Find the severest status. If getSevereFirstStatus() returns null, a default OK status will be returned. Clients
     * should override getSevereFirstStatus() instead.
     * 
     * @see #getSevereFirstStatus()
     * @return the severest status
     */
    protected IStatus findMostSevere()
    {
        IStatus[] statuses = getSevereFirstStatus();
        if (statuses == null || statuses.length == 0) 
        {
            return new Status(IStatus.OK, Activator.PLUGIN_ID, 0, "", //$NON-NLS-1$
            null); 
        }
        for (int i = 0; i < statuses.length; i++)
        {
            if (statuses[i] == null)
            {
                continue;
            }
            //ERROR status has higher priority
            if (statuses[i].matches(IStatus.ERROR)) 
            {
                return statuses[i]; 
            }
        }
        for (int i = 0; i < statuses.length; i++)
        {
            if (statuses[i] == null)
            {
                continue;
            }
            //info means prompting user to input
            if (statuses[i].matches(IStatus.INFO)) 
            {
                return statuses[i]; 
            }
        }
        for (int i = 0; i < statuses.length; i++)
        {
            if (statuses[i] == null)
            {
                continue;
            }
            if (statuses[i].matches(IStatus.WARNING)) 
            {
                return statuses[i]; 
            }
        }
        return new Status(IStatus.OK, Activator.PLUGIN_ID, 0, "", null); //$NON-NLS-1$
    }


    public void handleEvent(Event event)
    {
        IStatus status = findMostSevere();
        applyToStatusLine(status);
        this.setValid(status.getSeverity()!= IStatus.ERROR && status.getSeverity()!= IStatus.INFO);
    }


    /**
     * by default, sort status by natrual order 
     * @return Status ordered by severity
     */
    protected IStatus[] getSevereFirstStatus()
    {
        IStatus[] status = new IStatus[_sections.size()];
        Iterator iter = _sections.values().iterator();
        int i = 0;
        while(iter.hasNext())
        {
            IDataServerPreferenceSection section = (IDataServerPreferenceSection) iter.next();
            status[i++] = section.getStatus();
        }
        return status;
    }

    /**
     * Empty implementation. Subclass can either choose to use FieldEditor or not.  
     */
    protected void createFieldEditors()
    {
    }


    public abstract String getPreferencePageId();

    /**
     * This layout should be used by the container of a <code>TabFolder</code> to compute size based on TabItem rather than TabFolder.
     * @author Hui Cao
     *
     */
    private class TabFolderContainerLayout extends Layout 
    {
        Point preferredSize = null;
        protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) 
        {
            if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT)
            return new Point(wHint, hHint);

            int x= 0; 
            int y= 0;               
            Control[] children= composite.getChildren();
            for (int i= 0; i < children.length; i++) 
            {
                Point size= children[i].computeSize(SWT.DEFAULT, SWT.DEFAULT, flushCache);
                x= Math.max(x, size.x);
                y= Math.max(y, size.y);
            }

            Point minSize= getMinSize();
            x= Math.max(x, minSize.x);
            y= Math.max(y, minSize.y);

            if (wHint != SWT.DEFAULT)
            {
                x= Math.max(x, wHint);
            }
            else
            {
                x= Math.min(x, getMaxSize().x);
            }
            if (hHint != SWT.DEFAULT)
            {
                y= Math.max(y, hHint);
            }
            else
            {
                y= Math.min(y, getMaxSize().y);
            }

            preferredSize = new Point(x, y); 
            return preferredSize;     
        }

        private Point getMinSize()
        {
            return new Point(200,200);
        }

        private Point getMaxSize()
        {
            return new Point(500,400);
        }


        protected void layout(Composite composite, boolean flushCache) 
        {
            Rectangle rect= composite.getClientArea();
            Control[] children= composite.getChildren();
            for (int i= 0; i < children.length; i++) 
            {
                children[i].setBounds(rect);
            }
        }
    }

}
