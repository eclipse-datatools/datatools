/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
    private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.data.internal.ui.editor.messages";//$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);

    private Messages() {
    }

    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
    
    /**
     * Gets a String resource, given its index,
     * and uses java.text.MessageFormat to do argument substitution.
     * Arguments place holders in the resource string must have 
     * the form {0}, {1}, ...
     * @param key The index of the resource.
     * @param arguments Objects for substitution.
     * @return A resource String
     */
    public static String getString(String key, Object[] arguments) {
        try {
            String pattern = RESOURCE_BUNDLE.getString(key);
            return MessageFormat.format(pattern, arguments);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

}