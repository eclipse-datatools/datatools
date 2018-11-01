/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core.load;


import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.common.Output;

public class LoadData
{
    // todo extra whitespaces beofre/after values

    protected Table table;
    protected String filePath;
    
    protected String colDelim = ","; //$NON-NLS-1$
    protected static final String ENDL = System.getProperty("line.separator"); //$NON-NLS-1$
    protected String stringDelim = "\""; //$NON-NLS-1$
    
    protected boolean replace = true;
    
    protected TableLoader loader = null;
    
    public LoadData(Table table, String filePath)
    {
        this.table = table;
        this.filePath = filePath;
    }
    
    public void setDelims(String colDelim, String stringDelim)
    {
        this.colDelim = colDelim;
        this.stringDelim = stringDelim;
    }

    public void setReplace(boolean replace)
    {
        this.replace = replace;
    }
    
    public int doLoad(Output output)
    {
        output.write( Messages.getString("LoadData.Loading") +  " " + getFullyQualifiedName() + "..." ); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        
        try {
            doLoad1();
        } catch (Exception ex) {
            DataCorePlugin.getDefault().writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
            output.write( ex.toString() );
            output.write( Messages.getString("LoadData.DataLoadingFailed") ); //$NON-NLS-1$            
            return Output.STATUS_FAILED;
        } 
        
        output.write( Messages.getString("LoadData.DataLoadingSuccessful") ); //$NON-NLS-1$
        
        String report = ""; //$NON-NLS-1$
        String endl = System.getProperty("line.separator"); //$NON-NLS-1$
        if (loader.getDeletedRows()>0)
            report += String.valueOf(loader.getDeletedRows()) + Messages.getString("LoadData.RowsDeleted"); //$NON-NLS-1$
        if (report.length()>0)
            report += endl;
        report += String.valueOf(loader.getInsertedRows()) + Messages.getString("LoadData.RowsLoaded"); //$NON-NLS-1$
        if (loader.getFailedRows()>0) {
            report += endl + String.valueOf(loader.getFailedRows()) + Messages.getString("LoadData.RowsFailed"); //$NON-NLS-1$
            report += endl + endl + loader.getFailedRowError();
        }
        output.write( report);                
        
        if (loader.getErrorColumns().size()>0) {
            StringBuffer sb = new StringBuffer();
            sb.append(Messages.getString("LoadData.ErrorSetting")); //$NON-NLS-1$
            Iterator it = loader.getErrorColumns().iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                if (it.hasNext())
                    sb.append(", "); //$NON-NLS-1$
            }
            output.write( sb.toString());            
        }
        
        if (loader.getFailedRows()>0 || loader.getErrorColumns().size()>0)
            return Output.STATUS_WARNING;
        else
            return Output.STATUS_SUCCEEDED;
    }
    
    public void doLoad1() throws Exception
    {
        DataFileTokenizer tokens = new DataFileTokenizer(filePath, colDelim+stringDelim);
        loader = new TableLoader(table);        

        try {
            tokens.open();
            loader.open();
            if (replace)
                loader.emptyTable();
            parseFile(tokens);
        } finally {
            tokens.close();
            loader.close();
        }  
    }
    
    
    // file: (row)* EOF
    protected void parseFile(DataFileTokenizer tokens) throws Exception
    {
        while (tokens.peek()!=null) {
            Vector values = parseRow(tokens);
            loader.loadRow((String[])values.toArray(new String[0]));
        }   
    }
    
    // row: col (COMMA col)* (ENDL|EOF)
    protected Vector parseRow(DataFileTokenizer tokens) throws Exception
    {
        Vector values = new Vector();
        values.add( parseCol(tokens) );
        while (tokens.peek()!=null && !tokens.peek().equals(ENDL)) {
            tokens.consume(colDelim);
            values.add( parseCol(tokens) );
        }
        if (tokens.peek()!=null)
            tokens.consume(ENDL);
        return values;
    }
    
    // col: string | NOTHING
    protected String parseCol(DataFileTokenizer tokens) throws Exception
    {
        if (tokens.peek()==null || tokens.peek().equals(colDelim) || tokens.peek().equals(ENDL))
            return null;
        else
            return parseString(tokens); 
    }
    
    // string: quotedstring | unquotedstring
    protected String parseString(DataFileTokenizer tokens) throws Exception
    {
        if (tokens.peek().equals(stringDelim))
            return parseQuotedString(tokens);
        else
            return parseUnquotedString(tokens);
    }
    
    // quotedstring = QUOTE (token)* QUOTE
    // '' replaced by ' in the string
    protected String parseQuotedString(DataFileTokenizer tokens) throws Exception
    {
        StringBuffer sb = new StringBuffer();
        tokens.consume(stringDelim);
        for (String token=tokens.nextToken();;token=tokens.nextToken() ) {
            if (token.equals(stringDelim)) {
                if (tokens.peek()!=null && tokens.peek().equals(stringDelim))
                    sb.append( tokens.nextToken() );
                else
                    return sb.toString();
            } else
                sb.append(token);
        }
    }
    
    // unquotedstring = token
    protected String parseUnquotedString(DataFileTokenizer tokens) throws IOException
    {
        return tokens.nextToken();
    }
    
    protected String getFullyQualifiedName() {
        Database db = table.getSchema().getCatalog() != null ?
            table.getSchema().getCatalog().getDatabase() :
            table.getSchema().getDatabase();
        
        RDBCorePlugin plugin = RDBCorePlugin.getDefault();

        DatabaseDefinition dbDefinition = 
            plugin.getDatabaseDefinitionRegistry().getDefinition(db);

        if (dbDefinition.supportsSchema()) {
            return "\"" + table.getSchema().getName() + "\".\"" + table.getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else {
            return "\"" + table.getName() + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
    }
    
}
