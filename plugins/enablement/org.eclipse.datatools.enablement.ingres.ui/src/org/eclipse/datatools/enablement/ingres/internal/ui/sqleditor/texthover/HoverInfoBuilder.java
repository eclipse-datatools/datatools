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
package org.eclipse.datatools.enablement.ingres.internal.ui.sqleditor.texthover;

import org.eclipse.datatools.enablement.ingres.internal.ui.util.Messages;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLStatement;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;

/**
 * Translates given objects into hover texts.
 * 
 * @author enrico.schenk@ingres.com
 */
public class HoverInfoBuilder {

	/**
	 * translates a given node (of type IASTSQLStatement) into an hovering text.
	 * simple maps (IASTSQLStatement) node).getType() to a string. Help text is
	 * stored in the property file
	 * org.eclipse.datatools.enablement.ingres.internal.ui.l10n.messages.properties
	 * with the prefix "HoverInfoBuilder."
	 * 
	 * @param node
	 *            the node
	 * @return the mapped string or null
	 */
	public static String getHoverInfoString(final Node node) {
		String hoverInfo = null;

		if (node instanceof IASTSQLStatement) {
			switch (((IASTSQLStatement) node).getType()) {
			case SQLParserConstants.TYPE_SQL_ROOT:
				hoverInfo = Messages.getString("HoverInfoBuilder.root"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_OTHERS:
				hoverInfo = Messages.getString("HoverInfoBuilder.others"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_SELECT:
				hoverInfo = Messages.getString("HoverInfoBuilder.select"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_INSERT:
				hoverInfo = Messages.getString("HoverInfoBuilder.insert"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_DELETE:
				hoverInfo = Messages.getString("HoverInfoBuilder.delete"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_UPDATE:
				hoverInfo = Messages.getString("HoverInfoBuilder.update"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_DATABASE:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.create.database"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_TABLE:
				hoverInfo = Messages.getString("HoverInfoBuilder.create.table"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_VIEW:
				hoverInfo = Messages.getString("HoverInfoBuilder.create.view"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_PROCEDURE:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.create.procedure"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_FUNCTION:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.create.function"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_EVENT:
				hoverInfo = Messages.getString("HoverInfoBuilder.create.event"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_TRIGGER:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.create.trigger"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_DEFAULT:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.create.default"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_DECLARE:
				hoverInfo = Messages.getString("HoverInfoBuilder.declare"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_BEGIN:
				hoverInfo = Messages.getString("HoverInfoBuilder.begin"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CREATE_INDEX:
				hoverInfo = Messages.getString("HoverInfoBuilder.create.index"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_ALTER_DATABASE:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.alter.database"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_ALTER_TABLE:
				hoverInfo = Messages.getString("HoverInfoBuilder.alter.table"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_ALTER_VIEW:
				hoverInfo = Messages.getString("HoverInfoBuilder.alter.view"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_ALTER_PROCEDURE:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.alter.procedure"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_ALTER_FUNCTION:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.alter.function"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_ALTER_EVENT:
				hoverInfo = Messages.getString("HoverInfoBuilder.alter.event"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_ALTER_TRIGGER:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.alter.trigger"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_CALL:
				hoverInfo = Messages.getString("HoverInfoBuilder.call"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_BEGIN_TRANSACTION:
				hoverInfo = Messages
						.getString("HoverInfoBuilder.begin.transaction"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_ALTER_INDEX:
				hoverInfo = Messages.getString("HoverInfoBuilder.alter.index"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_DROP_VIEW:
				hoverInfo = Messages.getString("HoverInfoBuilder.drop.view"); //$NON-NLS-1$
				break;

			case SQLParserConstants.TYPE_SQL_SELECT_INTO:
				hoverInfo = Messages.getString("HoverInfoBuilder.select.into"); //$NON-NLS-1$
				break;
			}
		}

		return hoverInfo;
	}

}
