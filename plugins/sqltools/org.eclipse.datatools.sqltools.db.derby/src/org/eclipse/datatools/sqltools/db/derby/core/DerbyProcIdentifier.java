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
package org.eclipse.datatools.sqltools.db.derby.core;

import java.util.Map;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifierImpl;
import org.eclipse.datatools.sqltools.db.derby.internal.Messages;
import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author hcao
 *
 */
public class DerbyProcIdentifier extends ProcIdentifierImpl {

	public DerbyProcIdentifier(int type, DatabaseIdentifier db, Map map)
	{
		super(type, db, map);
	}

	public String getCallableStringWithoutGroupNumber(boolean quoted_id) {
		String call = super.getCallableStringWithoutGroupNumber(quoted_id);
		//derby doesn't support catalog name
		return call.substring(call.indexOf(".")+ 1);
	}

    public String getLongDisplayString()
    {
        String s = "";

        if (getType() == TYPE_TRIGGER && getTableName() != null && getTableName().length() > 0)
        {
            s = NLS.bind(Messages.ProcIdentifierImpl_trigger_long_display_string, new Object[]{getProfileName(), getOwnerName(), getTableName(), getDisplayString()});
        }
        else
        {
            s = NLS.bind(Messages.ProcIdentifierImpl_long_display_string, new Object[]{getProfileName(), getOwnerName(), getDisplayString()});
        }
        return s;
    }
	
}
