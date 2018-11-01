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
package org.eclipse.datatools.sqltools.sql.updater;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.Token;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeployable;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;

/**
 * 
 * @author Hui Cao
 * 
 */
public abstract class ProceduralObjectSourceUpdater
{
    protected DatabaseDefinition _dbDefinition;
    protected DatabaseVendorDefinitionId _dbDefinitionId;
    protected SQLParser _parser;
    protected SQLObject _routine = null;
    protected ParserParameters _parserParameters;
    protected boolean _testMode = false;
    
    public ProceduralObjectSourceUpdater(SQLObject _object, DatabaseDefinition dbDefinition)
    {
        this._routine = _object;
        _dbDefinition = dbDefinition;
        _dbDefinitionId = new DatabaseVendorDefinitionId(_dbDefinition.getProduct(), _dbDefinition.getVersion());
        SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByVendorIdentifier(_dbDefinitionId);
        _parser = f.getSQLService().getSQLParser();
        _parserParameters = new ParserParameters(false);
        _parserParameters.setProperty(ParserParameters.PARAM_HEADER_MODE, Boolean.TRUE);
    }

    /**
     * Updates the source by giving it a new name
     * @param newName should be quoted when necessary
     * @return
     */
    public boolean updateName(String newName)
    {
        String body = getBody();
        ParsingResult result = _parser.parse(body, _parserParameters);
        IASTStart root = result.getRootNode();
        for (int i = 0; i < root.jjtGetNumChildren(); i++)
        {
            Node node = root.jjtGetChild(i);
            if (node instanceof IASTDeployable)
            {
                Node nameNode = ((IASTDeployable) node).getNameNode();
                Token nameToken = nameNode.getLastToken();
                int[] range = _parser.getRange(nameToken);
                body = body.substring(0, range[0]) + newName + body.substring(range[1]);
                setBody(body);
                return true;
            }
        }
    
        return false;
    }

    public abstract String getBody();
//    {
//        if (_routine instanceof Routine)
//        {
//            return ((Routine)_routine).getSource().getBody();
//        }
//        else if (_routine instanceof Trigger)
//        {
//            SybaseDdlBuilderWrapper.getInstance().getTriggerBody((Trigger)_routine);
//        }
//        else if (_routine instanceof Event)
//        {
//            
//        }
//        return "";
//    }
    
    public abstract void setBody(String body);
    
    /**
     * In test mode, the updater should validate the updated source and revert it back if error occurs.
     * 
     * @param testMode
     */
    public void setTestMode(boolean testMode)
    {
    	_testMode = testMode;
    }
    
    public boolean getTestMode()
    {
    	return _testMode;
    }
}