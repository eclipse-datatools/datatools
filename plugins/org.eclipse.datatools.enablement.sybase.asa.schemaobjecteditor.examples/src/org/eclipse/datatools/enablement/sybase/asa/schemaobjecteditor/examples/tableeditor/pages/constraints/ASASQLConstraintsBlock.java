/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.constraints;

import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.ui.forms.DetailsPart;


/**
 * Constraints block for ASA constraints
 * 
 * @author Idull
 */
public class ASASQLConstraintsBlock extends SQLConstraintsBlock
{
    protected static final int PK_WIZARD        = 0;
    protected static final int UNIQUE_WIZARD    = 1;
    protected static final int FK_WIZARD        = 2;
    protected static final int TABLE_CK_WIZARD  = 3;
    protected static final int COLUMN_CK_WIZARD = 4;
    
    public ASASQLConstraintsBlock(SchemaObjectEditorPage page, BaseTable table)
    {
        super(page, table);
    }

	protected void registerPages(DetailsPart detailsPart) {
		
	}

}
