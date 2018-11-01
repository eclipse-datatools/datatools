/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/

package org.eclipse.datatools.sqltools.core.services;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.editor.IExtendedSaveSupport;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

/**
 * A SQL Editor related service specific to a database definition. This interface allows clients to customize the
 * default behavior of the SQL Editor.
 * 
 * @author Hui Cao
 * 
 */
public class SQLEditorUIService
{

    /**
     * Returns a <code>IExtendedSaveSupport</code> object which is used to perform additional operations before/after
     * saving a procedural object
     * 
     * @return a <code>IExtendedSaveSupport</code> object to customize the saving behavior
     */
    public IExtendedSaveSupport getExtendedSaveSupport()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns data server specific actions, which will be added to menu/context menu and tool bar. To appear in the
     * toolbar, the action must define action id. Note: because more than one action may use the same action path, the
     * value might be collection of <code>org.eclipse.jface.action.IContributionItem</code> objects.
     * 
     * @return key: action path, if it's empty string "" or null, the action will be added to vendor actions sub menu;
     *         value: <code>org.eclipse.jface.action.IContributionItem</code> objects or Collection of
     *         <code>org.eclipse.jface.action.IContributionItem</code> objects.
     */
    public HashMap getAdditionalActions()
    {
        return new HashMap();
    }

    /**
     * Return a <code>org.eclipse.jface.text.source.ICharacterPairMatcher</code> which is used to matching pairs in
     * SQL Editor.
     * 
     * @return a <code>org.eclipse.jface.text.source.ICharacterPairMatcher</code> to matching pairs.
     */
    public ICharacterPairMatcher getSQLPairMatcher()
    {
        return null;
    }
}
