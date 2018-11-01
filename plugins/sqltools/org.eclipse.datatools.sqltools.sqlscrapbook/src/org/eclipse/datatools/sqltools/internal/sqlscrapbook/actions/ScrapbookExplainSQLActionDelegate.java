/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.actions;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditor;
import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.sqleditor.plan.ExplainSQLActionDelegate;
import org.eclipse.datatools.sqltools.sqleditor.plan.GroupPlanSupportRunnable;

/**
 * Action for getting execution plan of SQL statement in SQL Scrapbook editor.
 * 
 * @author juewu
 */
public class ScrapbookExplainSQLActionDelegate extends ExplainSQLActionDelegate
{
    public ScrapbookExplainSQLActionDelegate(SQLScrapbookEditor targetEditor)
    {
        super(targetEditor);
    }

    protected GroupPlanSupportRunnable createGroupPlanSupportRunnable(PlanRequest request,
            DatabaseIdentifier databaseIdentifier)
    {
        GroupPlanSupportRunnable gpsRunnable = super.createGroupPlanSupportRunnable(request, databaseIdentifier);
        if (_sqlEditor instanceof SQLScrapbookEditor)
        {
            gpsRunnable.setConnection(((SQLScrapbookEditor) _sqlEditor).getConnection());
        }
        return gpsRunnable;
    }
}
