package org.eclipse.datatools.sqltools.sqlbuilder.sqlbuilderdialog;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.sql.ui.dialogs.SQLPainterDlg;
import org.eclipse.datatools.sqltools.core.services.UIComponentService;
import org.eclipse.swt.widgets.Shell;

public class SQLBuilderUIComponentService extends UIComponentService {
	
	public SQLPainterDlg getDMLDialog(Shell parentShell, String statementType,
			String statement, String profileName, String database,
			String parametersType, String parameter, String table, HashMap info)
	{
		return new SQLBuilderDialog(parentShell, statementType, statement, profileName,
				database, parametersType, parameter, table, info);
	}
	
	public boolean supportsDMLDialog()
	{
		return true;
	}
	
}
