package org.eclipse.datatools.enablement.sybase.asa.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.connectivity.sqm.loader.Messages;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASABaseCatalog;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.internal.refresh.ICatalogObject2;

import com.ibm.icu.text.MessageFormat;

public class SybaseASACatalog extends SybaseASABaseCatalog implements ICatalogObject, IAdaptable, ICatalogObject2
{

    private static final long                  serialVersionUID = 3372967146783478978L;

    protected JDBCSchemaLoader createSchemaLoader()
    {
        return new ASASchemaLoader(this);
    }

    public class ASASchemaLoader extends JDBCSchemaLoader
    {

        public ASASchemaLoader(ICatalogObject catalogObject)
        {
            super(catalogObject, null);
        }

        protected Schema createSchema()
        {
            return new SybaseASACatalogSchema();
        }

        protected ResultSet createResultSet() throws SQLException
        {
            try
            {
                Connection conn = this.getCatalogObject().getConnection();
                PreparedStatement stmt = conn.prepareStatement(ASASQLs.QUERY_SCHEMAS);
                return stmt.executeQuery();
            }
            catch (RuntimeException e)
            {
                SQLException error = new SQLException(MessageFormat.format(
                        Messages.Error_Unsupported_DatabaseMetaData_Method, new Object[]
                        {
                            "java.sql.DatabaseMetaData.getSchemas()"})); //$NON-NLS-1$
                error.initCause(e);
                throw error;
            }
        }
    }

}
