/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import org.eclipse.datatools.enablement.sybase.parser.QuickSQLParser;
import org.eclipse.datatools.sqltools.core.DataTypeStringParser;

public class QuickDataTypeParser extends DataTypeStringParser
{
    public String[] parseDatatype(String dataType)
    {
        QuickSQLParser parser = QuickSQLParser.getInstance();
        return parser.getDatatypeInfo(dataType);
    }
}
