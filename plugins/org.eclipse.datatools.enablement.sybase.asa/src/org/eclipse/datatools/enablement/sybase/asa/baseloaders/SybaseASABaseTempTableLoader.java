package org.eclipse.datatools.enablement.sybase.asa.baseloaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption;

public class SybaseASABaseTempTableLoader extends BaseTableASABaseLoader {

	protected SybaseASABaseTempTable tempTable;
	
	public SybaseASABaseTempTableLoader(SybaseASABaseTempTable catalogTable)
	{
		super(catalogTable);
		this.tempTable = catalogTable;
	}
	
	protected void processTableInfoResultSet(ResultSet rs) throws SQLException {
		String remark = rs.getString(7);
		int lastPage = rs.getInt(2);
	
		tempTable.setDescription(remark);
		tempTable.setTransactionOption(getTransactionOption(lastPage));
	}
	
	private TransactionOption getTransactionOption(int iOption)
	{
	        switch(iOption)
	        {
	        case 0: // '\0'
	            return TransactionOption.PRESERVE_LITERAL;

	        case 1: // '\001'
	            return TransactionOption.DELETE_LITERAL;

	        case 3: // '\003'
	            return TransactionOption.NOT_TRANSACTION_LITERAL;

	        case 2: // '\002'
	        default:
	        	return TransactionOption.DELETE_LITERAL;
	        }
	}
}
