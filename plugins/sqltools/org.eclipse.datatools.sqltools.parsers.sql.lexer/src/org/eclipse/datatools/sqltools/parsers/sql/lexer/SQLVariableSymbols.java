/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.lexer;

/**
 * @author ckadner
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface SQLVariableSymbols
{

    
    final static int STMT_TERM 			= SQLLexersym.Char_StmtTerm,
                     HOST_VAR_PREFIX 	= SQLLexersym.Char_HostVarPrfx,
                     PARAM_MARK 		= SQLLexersym.Char_ParamMark,
                     DELIM_ID_QT 		= SQLLexersym.Char_DelimIdQt;
    				
    
    
}
