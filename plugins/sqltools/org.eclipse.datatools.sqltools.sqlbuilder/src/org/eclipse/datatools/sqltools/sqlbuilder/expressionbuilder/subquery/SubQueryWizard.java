/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.expressionbuilder.subquery;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

public class SubQueryWizard extends Wizard implements INewWizard {

    protected SubQueryPage subQueryPage;

    private QueryValueExpression inputSQLExpression;
    private QueryValueExpression updatedSQLExpression;
    private SQLDomainModel domainModel;

    public SubQueryWizard(SQLDomainModel domainModel, QueryValueExpression sqlExpr) {
        inputSQLExpression = sqlExpr;
        this.domainModel = domainModel;
        setWindowTitle(Messages._UI_WINDOW_TITLE_CHOICES);
        setDefaultPageImageDescriptor(ImageDescriptor.createFromFile(SQLBuilderPlugin.getPlugin().getClass(), SQLResource.IMG_EXPRESSION_BUILDER_WIZARD));
    }

    public Wizard createSmartGuide() {
        return this;
    }

    public void addPages() {
        subQueryPage = new SubQueryPage(domainModel, inputSQLExpression);
        addPage(subQueryPage);
    }

    public void init(IWorkbench workbench, IStructuredSelection selection) {
    }

    public void setSQLExpression(QueryValueExpression sqlExpr) {
        updatedSQLExpression = sqlExpr;
    }

    public QueryValueExpression getSQLExpression() {
        return updatedSQLExpression;
    }

    public boolean performFinish() {
        if (subQueryPage.currentPage()) {
            subQueryPage.performOk();
        }
        return true;
    }
}