/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.db.generic.ui;

import org.eclipse.core.runtime.Assert;
import org.eclipse.datatools.sqltools.db.generic.parser.ASTSQLStatement;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.ui.ASTSQLStatementUIUtil;
import org.eclipse.datatools.sqltools.sql.ui.INodesImageHandler;
import org.eclipse.swt.graphics.Image;

public class GenericASTSQLStatementImageHandler implements INodesImageHandler {

	public Image getImage(Node node) 
	{
		Assert.isTrue(node instanceof ASTSQLStatement);
		return ASTSQLStatementUIUtil.getImage(((ASTSQLStatement)node).getType());
	}

}
