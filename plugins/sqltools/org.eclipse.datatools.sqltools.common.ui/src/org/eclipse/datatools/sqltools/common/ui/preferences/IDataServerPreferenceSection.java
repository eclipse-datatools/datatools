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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;

/**
 * This section will appear inside a preference page as a Tab for data servers to provide vendor specific preferences.
 * The implementation should be contributed in via the preferenceSections extension point.  
 * @author Dafan Yang
 * @author Hui Cao
 *
 */
public interface IDataServerPreferenceSection
{

    /**
     * load default preferences to this UI section
     * 
     * @param store
     */
    public abstract void loadDefaultPreference(IPreferenceStore store);

    /**
     * save the UI section's status to preference store
     * 
     * @param store
     */
    public abstract void saveToPreferenceStore(IPreferenceStore store);

    /**
     * load preferences to this UI section
     * 
     * @param store
     */
    public abstract void loadFromPreferenceStore(IPreferenceStore store);

    /**
     * get the UI section's composite
     * @return
     */
    public abstract Composite getSectionComposite();

    /**
     * create the UI section's composite. Implementing classes should call createSectionComposite first before calling other APIs
     * @return
     */
    public abstract Composite createSectionComposite(Composite parent);

    /**
     * get the validation status of this section.
     * @return
     */
    public IStatus getStatus();

}
