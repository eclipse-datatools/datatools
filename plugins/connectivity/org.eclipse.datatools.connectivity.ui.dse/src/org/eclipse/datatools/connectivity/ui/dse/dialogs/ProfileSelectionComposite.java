/*******************************************************************************
 * Copyright (c) 2005-2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui.dse.dialogs;

import java.util.HashMap;
import java.util.Iterator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.ICategory;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IPropertySetChangeEvent;
import org.eclipse.datatools.connectivity.IPropertySetListener;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * Composite that hosts the DSE that can be used in a variety of 
 * ways. Currently we host the composite on a dialog, but it could
 * also be used on a property, preference, or wizard page where
 * needed.
 * 
 * @author brianf
 */
public class ProfileSelectionComposite {
	
	// Database category ID
	public static String JDBC_CATEGORY = "org.eclipse.datatools.connectivity.db.category"; //$NON-NLS-1$

	// Initial profile name for selection
	protected String _profile = null;
    
    // Initial category ID to filter profiles with
    protected String _category = null;
    
    // Hosted dialog page
    protected ProfileSelectionDialogPage page = null;

    // custom filter for viewer contents
    protected ViewerFilter _filter;
    
    // listener variables
    protected HashMap _connectListeners = null;
    protected boolean _listenersInited = false;
    protected ListenerList changeListeners;

    /**
     * Simple Constructor
     * @param parent
     * @param style
     */
    public ProfileSelectionComposite(Composite parent, int style) {
    	this(parent, style, null);
    }

    /**
     * Constructor with category ID to narrow the profile list
     * @param parent
     * @param style
     * @param category
     */
    public ProfileSelectionComposite(Composite parent, int style, String category) {
    	this(parent, style, category, true, true, true, false, true);
    }
    
    /**
     * Constructor with category ID and setting to either show
     * or hide profile contents.
     * @param parent
     * @param style
     * @param category
     * @param limitToProfiles
     */
    public ProfileSelectionComposite(Composite parent, int style, String category, boolean limitToProfiles) {
    	this(parent, style, category, limitToProfiles, true, true, false);
    }
    
    public ProfileSelectionComposite(Composite parent, int style, String category, boolean limitToProfiles, boolean canNew, boolean canConnect, boolean canSelect) {
    	this(parent, style, category, limitToProfiles, canNew, canConnect, canSelect, true);
    }
    /**
     * Constructor 
     * @param parent
     * @param style
     * @param category - category ID to filter
     * @param limitToProfiles - indicate whether to show or hide profile contents
     * @param canNew - indicate whether user can create a new profile or not
     * @param canConnect - indicate whether user can connect/disconnect profiles
     * @param canSelect - indicate whether user can select one or more profiles
     */
    public ProfileSelectionComposite(Composite parent, int style, String category, boolean limitToProfiles, boolean canNew, boolean canConnect, boolean canSelect, boolean createNow) {
        _connectListeners = new HashMap();
        changeListeners = new ListenerList();
        
    	this.setCategory(category);
    	((ProfileSelectionDialogPage)getPage()).setLimitToProfiles(limitToProfiles);
    	getPage().setShowConnect(canConnect);
    	getPage().setShowNew(canNew);
    	getPage().setShowSelectButtons(canSelect);
    	
    	if (createNow) {
    		getPage().createControl(parent, false, false);
    	
    		IConnectionProfile profile = null;
    		if (this._profile != null)
    			profile = ProfileManager.getInstance().getProfileByName(this._profile);
    		
    		((ProfileSelectionDialogPage)getPage()).init( profile, null, this._filter, getCategoryObject());
    	}
	}

	/**
	 * Set up listeners for selection changes 
	 */
	private void initListeners() {
        final CommonViewer viewer = (CommonViewer) getPage().getNavigatorViewer();
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection selection = (StructuredSelection) event.getSelection();
				if (selection.getFirstElement() instanceof IConnectionProfile) {
					IConnectionProfile profile = (IConnectionProfile) selection.getFirstElement();
					if (_connectListeners.get(profile) == null) {
						IPropertySetListener listener = new IPropertySetListener() {
							public void propertySetChanged(IPropertySetChangeEvent event) {
								if (IConnectionProfile.CONNECTION_PROFILE_PROPERTY_SET.equals(event
										.getPropertySetType())
										&& event
												.getChangedProperty(IConnectionProfile.CONNECTION_STATE_PROPERTY_ID) != null) {
									initListboxContent();
								}
							}
						};
						profile.addPropertySetListener(listener);
						_connectListeners.put(profile, listener);
					}
					if (_listenersInited) {
						if (ProfileSelectionComposite.this._profile != profile.getName()) {
							ProfileSelectionComposite.this._profile = profile.getName();
							fireChangedEvent(ProfileSelectionComposite.this);
						}
					}
				}
				validate();
			}
		});
        _listenersInited = true;
    }
    
    /**
     * Create the dialog page being hosted
     * @return
     */
    public ProfileSelectionDialogPage createProfileDatabaseSelectionDialogPage()
    {
        return new ProfileSelectionDialogPage(
                "Property Page Title"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Set the category ID to filter the viewer to
     * @param category
     */
    public void setCategory ( String category ) {
    	this._category = category;
    }
    
    /**
     * Return the category ID
     * @return
     */
    public String getCategory() {
    	return this._category;
    }
    
    /**
     * Get the category object
     * @return
     */
    protected ICategory getCategoryObject() {
    	if (this._category != null)
    		return ProfileManager.getInstance().getCategory(this._category);
    	return null;
    }

    /**
     * Return the underlying dialog page the composite is hosting
     * @return
     */
    public ProfileSelectionDialogPage getPage() {
    	if (this.page == null)
        	this.page = createProfileDatabaseSelectionDialogPage();
    	return this.page;
    }

	/**
	 * Set up the viewer
	 */
	public void fillInDefaultValues()
    {
   		initListboxContent();
		if (!_listenersInited) {
			initListeners();
		}
   		validate();
    }

	/**
	 * Initialize the selection
	 */
	protected void initListboxContent() {
		final TreeViewer viewer = (TreeViewer)getPage().getNavigatorViewer();
		Display.getCurrent().syncExec(new Runnable() {

			public void run() {
				viewer.refresh();
				if (getPage().getLimitToProfiles()) 
					viewer.expandAll();
			}
		});
		Display.getCurrent().readAndDispatch();
		if (this._profile != null) {
			Display.getCurrent().syncExec(new Runnable() {

				public void run() {
					IConnectionProfile profile = 
						ProfileManager.getInstance().getProfileByName(_profile);
					getPage().select(profile);
				}
			});
		}
	}

	/**
	 * Returns the profile name
	 * @return
	 */
	public String getCPName() {
		return this._profile;
	}
	
	/**
	 * Set the profile name if we are to set an 
	 * initial selection
	 * @param input
	 */
	public void setCPName ( String input ) {
		this._profile = input;
	}

	/**
	 * Returns the custom filter
	 * @return
	 */
	public ViewerFilter getCustomFilter() {
		return this._filter;
	}
	
	/**
	 * Set a custom filter for viewer contents
	 * @param input
	 */
	public void setCustomFilter ( ViewerFilter input ) {
		this._filter = input;
	}

	/**
	 * Validate the selection
	 * @return
	 */
	protected boolean validate() {
		boolean hasProfile = false;
		if (_profile != null && _profile.trim().length() > 0) {
			hasProfile = true;
		}
		getPage().validate();
		return hasProfile;
	}
	
	/**
	 * Dispose of connect listeners
	 */
	public void dispose() {
		if (!this._connectListeners.isEmpty()) {
			Iterator profileIter = this._connectListeners.keySet().iterator();
			while (profileIter.hasNext()) {
				IConnectionProfile profile = (IConnectionProfile) profileIter.next();
				profile.removePropertySetListener((IPropertySetListener) this._connectListeners.get(profile));
			}
		}
	}
	
	/**
	 * If we changed, fire a changed event.
	 * 
	 * @param source
	 */
	private void fireChangedEvent(Object source) {
		ChangeEvent e = new ChangeEvent(source);
		// inform any listeners of the resize event
		Object[] listeners = this.changeListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			((ChangeListener) listeners[i]).stateChanged(e);
		}
	}

	/**
	 * Add a change listener
	 * 
	 * @param listener
	 */
	public void addChangeListener(ChangeListener listener) {
		this.changeListeners.add(listener);
	}

	/**
	 * Remove a change listener.
	 * 
	 * @param listener
	 */
	public void removeChangeListener(ChangeListener listener) {
		this.changeListeners.remove(listener);
	}
}
