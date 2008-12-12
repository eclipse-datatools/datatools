/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/

package org.eclipse.datatools.sqltools.core.services;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.editor.IExtendedSaveSupport;
import org.eclipse.datatools.sqltools.editor.contentassist.ISQLDBProposalsService;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.launching.IExtendedLaunchSupport;
import org.eclipse.jface.text.source.ICharacterPairMatcher;

/**
 * A SQL Editor related service specific to a database definition. This interface allows clients to customize the
 * default behavior of the SQL Editor.
 * 
 * @author Hui Cao
 * 
 */
public class SQLEditorService
{

    /**
     * Returns a <code>IExtendedLaunchSupport</code> object which is used to execute additional operations
     * before/after launching
     * 
     * @return a <code>IExtendedLaunchSupport</code> object to customize the launching behavior.
     */
    public IExtendedLaunchSupport getExtendedLaunchSupport()
    {
        // TODO Auto-generated method stub
        return null;
    }

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
     * Returns a <code>ISQLDBProposalsService</code> object which is used to execute operations to load db proposals
     * 
     * @param connInfo
     * @return a <code>ISQLDBProposalsService</code> object to perform the DB proposals loading behavior.
     */
    public ISQLDBProposalsService getSQLDBProposalsService(ISQLEditorConnectionInfo connInfo)
    {
        return null;
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
