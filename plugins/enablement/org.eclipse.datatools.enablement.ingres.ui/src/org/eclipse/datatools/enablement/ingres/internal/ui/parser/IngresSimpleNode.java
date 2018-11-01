/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.parser;

import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;

public class IngresSimpleNode extends org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode implements Node {

    public IngresSimpleNode(int i) {
        super(i);
    }

    public IngresSimpleNode(SQLParser p, int i) {
        super(p, i);
    }

}
