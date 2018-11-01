/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlBuilderWrapper;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsFactory;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTStart;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTTrigger;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.updater.ProceduralObjectSourceUpdater;



/**
 * 
 * @author Hui Cao
 * 
 */
public class TriggerSourceUpdater extends ProceduralObjectSourceUpdater implements IGenericDdlConstants
{
    protected Trigger         _trigger  = null;

    public TriggerSourceUpdater(Trigger trigger, DatabaseDefinition definition)
    {
        super(trigger, definition);
        this._trigger = trigger;
    }
    
    public String getBody()
    {
        return SybaseDdlBuilderWrapper.getInstance().getTriggerBody(_trigger);
    }
    
    public void setBody(String body)
    {
        _trigger.getActionStatement().clear();
        SQLStatement stmt = SQLStatementsFactory.eINSTANCE.createSQLStatementDefault();
        stmt.setSQL(body);
        _trigger.getActionStatement().add(stmt);
    }
    
    /**
     * Updates the trigger events
     * @return
     */
    public boolean updateEvents()
    {
        //sanity check
        String newEvents = getTriggerEvents();
        if (newEvents == null || newEvents.trim().equals(""))
        {
            return false;
        }
        String body = getBody();
        ParsingResult result = _parser.parse(body, _parserParameters);
        IASTTrigger node = getASTTrigger(result);
        if (node != null)
        {
            Node eventsNode = ((IASTTrigger) node).getTriggerEvents();
            int start = _parser.getStartIndex(eventsNode.getFirstToken());
            int end = _parser.getStartIndex(eventsNode.getLastToken().next);//remove white spaces as well
            body = body.substring(0, start) + newEvents + body.substring(end);
            setBody(body);
            return true;
        }
        return false;
    }

    protected String getTriggerEvents()
    {
        return SybaseDdlBuilderWrapper.getInstance().getTriggerEvents(_trigger);
    }

    protected IASTTrigger getASTTrigger(ParsingResult result)
    {
        IASTStart root = result.getRootNode();
        for (int i = 0; i < root.jjtGetNumChildren(); i++)
        {
            Node node = root.jjtGetChild(i);
            if (node instanceof IASTTrigger)
            {
                return (IASTTrigger) node;
            }
        }
        return null;
    }

}
