/**
 * Created on 2005-3-21
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.preferences;

import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.util.PropertyChangeEvent;


/**
 * @author Li Huang
 *  
 */
public class ContentAssistPreference
{

    /**
     * Changes the configuration of the given content assistant according to the given property change event and the
     * given preference store.
     */
    public static void changeConfiguration(ContentAssistant assistant, IPreferenceStore store, PropertyChangeEvent event)
    {

        String p = event.getProperty();
        IPreferenceStore preferenceStore = SQLEditorPlugin.getDefault().getPreferenceStore();
        if (PreferenceConstants.INSERT_SINGLE_PROPOSALS_AUTO.equals(p))
        {
            assistant.enableAutoInsert(preferenceStore.getBoolean(PreferenceConstants.INSERT_SINGLE_PROPOSALS_AUTO));
        }
        else if (PreferenceConstants.ENABLE_AUTO_ACTIVATION.equals(p))
        {
            assistant.enableAutoActivation(preferenceStore.getBoolean(PreferenceConstants.ENABLE_AUTO_ACTIVATION));
        }
        else if (PreferenceConstants.AUTO_ACTIVATION_DELAY.equals(p))
        {
            assistant.setAutoActivationDelay(preferenceStore.getInt(PreferenceConstants.AUTO_ACTIVATION_DELAY));
        }

    }

}
