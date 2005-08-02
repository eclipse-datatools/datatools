/*
 * Created on Nov 4, 2004
 *
 */
package org.eclipse.datatools.modelbase.sql.tables.helper;

import java.util.Iterator;

import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;



/**
 * @author tomlyn
 *
 */
public class TableHelper 
{
  public static Column findColumn(Table table, String columnName) 
  {
    for (Iterator it = table.getColumns().iterator(); it.hasNext();)
    {
      Column column = (Column) it.next();
            
      if (column.getName().equals(columnName)) 
      {
        return column;
      }
    }

    return null;
  }
}
