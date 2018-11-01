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

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * This interface should be implemented for preference UI components that can be used both as global preference setting. and as launch configuration settings. 
 * @author Dafan Yang
 * @author Hui Cao
 */
public interface IDataServerLaunchPreferenceSection extends IDataServerPreferenceSection
{
    public static int LAUNCH_MODE     = 1;
    public static int PREFERENCE_MODE = 2;

    /**
     * initialize this UI section with the specific configuration and preference store
     * 
     * @param configuration
     */
    public abstract void launchConfigurationInitialize(ILaunchConfiguration configuration, IPreferenceStore store);

    /**
     * save the UI section's status to launch configuration
     * 
     * @param wc
     */
    public abstract void saveConfiguration(ILaunchConfigurationWorkingCopy wc);

    /**
     * @param mode indicates whether this component is used for launch configuration or preference page. Implementing
     *            classes should disable/enable specific controls that are only relevant to a particular mode.
     * @return
     */
    public abstract void setMode(int mode);
}
