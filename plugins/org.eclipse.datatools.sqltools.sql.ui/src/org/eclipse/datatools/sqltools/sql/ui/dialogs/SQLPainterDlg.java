/*
 * Created on Jun 4, 2004 Copyright (c) Sybase, Inc. 2004 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.ui.dialogs;

import java.util.HashMap;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author asarvesh
 */
public abstract class SQLPainterDlg extends Dialog
{
    protected Shell         _parShell;
    protected String        _statementType;
    protected String        _statement;
    protected String        _profileName;
    protected String        _database;
    protected String        _parametersType;
    protected String        _parameter;
    protected String        _table;
    protected HashMap       _info;

    /**
     * @param parentShell
     */
    public SQLPainterDlg(Shell parentShell, String statementType, String statement, String profileName, String database, String parametersType, String parameter)
    {
        this(parentShell, statementType, statement, profileName, database, parametersType, parameter, null, null);
    }

    /**
     * @param parentShell
     */
    public SQLPainterDlg(Shell parentShell, String statementType, String statement, String profileName, String database, String parametersType, String parameter, String table, HashMap info)
    {
        super(parentShell);
        _parShell = parentShell;
        _statementType = statementType;
        _statement = statement;
        _profileName = profileName;
        _database = database;
        _parametersType = parametersType;
        _parameter = parameter;        
        _info = info;        
        _table = table;
    }

    /**
     * Opens the visual query dialog and returns the constructed sql statements.
     * @return
     */
    public abstract String load();

}
