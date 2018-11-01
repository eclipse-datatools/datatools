/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.plan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.plan.IPlanOption;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.jface.action.Action;

/**
 * The action class to explain the SQL statement or routine object.
 * 
 * @author Hui Cao
 * @author Dafan Yang
 */
public abstract class BaseExplainAction extends Action
{
    protected PlanRequest _planRequest;
    protected Connection  _conn;

    /**
     * Subclass should implement this method to returns the database identifier
     * 
     * @return the database identifier
     */
    public abstract DatabaseIdentifier getDatabaseIdentifier();

    /**
     * Subclass should implement this method to returns the SQL statement
     * 
     * @return the SQL statement
     */
    public abstract String getSQLStatements();

    /**
     * Subclass should implement this method to returns the post runnable
     * 
     * @return the post runnable
     */
    public abstract Runnable getPostRun();

    public void run()
    {
        if (!isEnabled())
        {
            return;
        }
        String sql = getSQLStatements();
        DatabaseIdentifier databaseIdentifier = getDatabaseIdentifier();
        if (databaseIdentifier == null || sql == null)
        {
            return;
        }
        IPlanOption option = null;
        IPlanService planService = SQLToolsUIFacade.getPlanService(databaseIdentifier);
        if (planService != null)
        {
            option = planService.getPlanOption();
        }

        String databaseDefinitionId = ProfileUtil.getDatabaseVendorDefinitionId(databaseIdentifier.getProfileName())
                .toString();
        int planType = option.getCurrentType();

        _planRequest = createPlanRequest(sql, databaseDefinitionId, planType, PlanRequest.VIEW_ACTIVATE);

        GroupPlanSupportRunnable thread = createGroupPlanSupportRunnable(_planRequest, databaseIdentifier);
        if(_conn != null)
        {
            thread.setConnection(_conn);
        }
        thread.setUser(true);
        thread.schedule();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
        }
        handleEnd(_conn);
    }

    /**
     * Checks if the neccessary classes are obtainable
     * 
     * @return <code>true</code> if the neccessary classes are obtainable
     */
    protected boolean canBeEnabled()
    {
        DatabaseIdentifier databaseIdentifier = getDatabaseIdentifier();
        if (databaseIdentifier == null)
        {
            return false;
        }
        IPlanService planService = SQLToolsUIFacade.getPlanService(databaseIdentifier);
        if (planService != null && planService.getPlanOption() != null)
        {
            return true;
        }

        return false;
    }

    /**
     * Returns the variable declarations since the statements to be explained may reference some variables, but the
     * declarations of the variable are not included
     * 
     * @return the variable declarations
     */
    protected HashMap getVariableDeclarations()
    {
        return null;
    }

    /**
     * Returns an instance of <code>GroupPlanSupportRunnable</code>
     * 
     * @param request the plan request
     * @param databaseIdentifier the database identifier
     * @return an instance of <code>GroupPlanSupportRunnable</code>
     */
    protected GroupPlanSupportRunnable createGroupPlanSupportRunnable(PlanRequest request,
            DatabaseIdentifier databaseIdentifier)
    {
        return new GroupPlanSupportRunnable(request, databaseIdentifier, getPostRun());
    }

    /**
     * Creates an instance of <code>PlanRequest</code>
     * 
     * @param sql the SQL statement
     * @param databaseDefinitionId the database definition id
     * @param planType the type of plan
     * @param mode the show view mode
     * @return an instance of <code>PlanRequest</code>
     */
    protected PlanRequest createPlanRequest(String sql, String databaseDefinitionId, int planType, int mode)
    {
        PlanRequest request = new PlanRequest(sql, databaseDefinitionId, planType, mode);
        request.setNoexec(false);
        request.setVarDecs(getVariableDeclarations());
        return request;
    }

    public PlanRequest getPlanRequest()
    {
        return _planRequest;
    }
    
    protected void handleEnd(Connection connection)
    {
        try
        {
            if(connection != null && !connection.isClosed())
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            
        }
    }
}
