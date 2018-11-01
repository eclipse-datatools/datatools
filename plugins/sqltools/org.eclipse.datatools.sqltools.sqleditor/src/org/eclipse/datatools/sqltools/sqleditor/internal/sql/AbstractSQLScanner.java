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

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import java.util.List;

import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.IRule;

/**
 * Abstract scanner which defines methods common to all types of SQL scanner.
 * @author Li Huang
 */
public abstract class AbstractSQLScanner extends BufferedRuleBasedScanner
{

    /**
     * Creates the list of rules controlling this scanner.
     */
    abstract protected List createRules();

    public AbstractSQLScanner()
    {
    }

    /**
     * Must be called after the constructor has been called.
     */
    public final void initialize()
    {
        initializeRules();
    }

    private void initializeRules()
    {
        List rules = createRules();
        if (rules != null)
        {
            IRule[] result = new IRule[rules.size()];
            rules.toArray(result);
            setRules(result);
        }
    }

}
