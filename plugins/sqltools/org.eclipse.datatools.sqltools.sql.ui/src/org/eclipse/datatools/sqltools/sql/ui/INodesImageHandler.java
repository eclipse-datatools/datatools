/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.ui;

import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.swt.graphics.Image;

public interface INodesImageHandler 
{
	public Image getImage(Node node);
}
