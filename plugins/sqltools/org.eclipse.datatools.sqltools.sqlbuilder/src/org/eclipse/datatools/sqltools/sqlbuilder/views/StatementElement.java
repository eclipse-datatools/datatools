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
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import org.eclipse.datatools.modelbase.sql.query.QueryStatement;

/**
 * Base class for select, update, insert element
 */
public abstract class StatementElement {

    protected Object column;
    protected Object target;
    protected QueryStatement statement;

    public StatementElement(Object target) {
        this.target = target;
        this.statement = (QueryStatement) target;
    }

    public Object getColumn() {
        return column;
    }

    public Object getTarget() {
        return target;
    }
}
