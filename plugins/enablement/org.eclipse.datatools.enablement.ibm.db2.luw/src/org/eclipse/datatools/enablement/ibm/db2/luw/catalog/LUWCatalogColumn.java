/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogStatistics;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWUtil;
import org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util.LUWCatalogMessages;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWFactory;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWNickname;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class LUWCatalogColumn extends LUWColumnImpl implements ICatalogObject, IDatabaseObject {
	public void refresh() {
		this.identitySpecifierLoaded = false;
		this.generateExpressionLoaded = false;
		if (this.optionsLoaded) {
			this.optionsLoaded = false;
			this.getOptions().clear();
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}	

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getTable().getSchema().getDatabase();		
	}
	
	public void refresh(int refreshType){
		if ((IDatabaseObject.STATISTICS & refreshType)  == IDatabaseObject.STATISTICS) {
			this.statistics.clear();
			this.statisticsLoaded = false;
		}
	}

	public IdentitySpecifier getIdentitySpecifier() {
		if(!this.identitySpecifierLoaded) this.loadIdentitySpecifier();
		return this.identitySpecifier;
	}
	
	public ValueExpression getGenerateExpression() {
		if(!this.generateExpressionLoaded) this.loadGenerateExrepression();
		return this.generateExpression;
	}

	public EList getOptions() {
		if (LUWOverwriteStatus.IS_OVERWRITE) {
			return super.getOptions();
		} else {
			if(!this.optionsLoaded) this.loadOptions();
			return super.getOptions();
		}
	}
	
	public ICatalogObject[] getImpacted(){
		ICatalogObject[] objs = new ICatalogObject[0];
		return objs;
	}

	public Collection getStatistics(){
		if (!this.statisticsLoaded) {
			this.statistics = LUWCatalogColumn.getStatistics(this.getConnection(), this);
			this.statisticsLoaded = true;
		}
		return this.statistics;
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER) {
			this.getIdentitySpecifier();
		}
		else if(id == SQLTablesPackage.COLUMN__GENERATE_EXPRESSION) {
			this.getGenerateExpression();
		}
		else if(id == LUWPackage.LUW_COLUMN__OPTIONS) {
			this.getOptions();
		}
		
		return super.eIsSet(eFeature);
	}

	public synchronized void loadGenerateExrepression() {
		if(this.generateExpressionLoaded) return;
		this.generateExpressionLoaded = true;
		if (!this.isGenerated) return;
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			final Table table = this.getTable();
			final Schema schema = table.getSchema();
			final Database database = ModelHelper.getDatabase(schema);
			final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
			final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();
			
			Statement s = this.getConnection().createStatement();
			ResultSet r = s.executeQuery("SELECT TEXT FROM SYSCAT.COLUMNS" //$NON-NLS-1$
				+ " WHERE TABSCHEMA='" + LUWUtil.getIdentifier(schema.getName())  //$NON-NLS-1$
				+ "' AND TABNAME='" + LUWUtil.getIdentifier(table.getName())  //$NON-NLS-1$
				+ "' AND COLNAME='" + LUWUtil.getIdentifier(this.getName()) + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		
			
			while (r.next()) {
				String exprValue = r.getString("TEXT"); //$NON-NLS-1$
				if (exprValue != null && exprValue.length() >0) {
					ValueExpression expr = (ValueExpression) factory.create(SQLExpressionsPackage.eINSTANCE.getValueExpressionDefault());
					this.setGenerateExpression(expr);
					int pos = exprValue.indexOf("AS"); //$NON-NLS-1$
					if (pos >= 0) {
						exprValue = exprValue.substring(pos+2).trim();
					}
					expr.setSQL(exprValue);
				}
			
			}
			r.close();
			s.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	private synchronized void loadIdentitySpecifier() {
		if(this.identitySpecifierLoaded) return;
		this.identitySpecifierLoaded = true;
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			//the column is identity column
			if (this.identitySpecifier != null) {
				LUWCatalogColumn.loadIdentitySpecifier(this.getConnection(), this.identitySpecifier,this);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}
	
	public static void loadIdentitySpecifier(Connection connection, IdentitySpecifier identitySpecifier, Column column) throws SQLException {
		final Table table = column.getTable();
		final Schema schema = table.getSchema();		
		final Database database = ModelHelper.getDatabase(schema);
		final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
		final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

		String query = "Select INCREMENT,START,MAXVALUE,MINVALUE,CYCLE, CACHE " + //$NON-NLS-1$
				" FROM SYSCAT.COLIDENTATTRIBUTES" + //$NON-NLS-1$
				" WHERE TABSCHEMA='" +  LUWUtil.getIdentifier(schema.getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
				" AND TABNAME= '" + LUWUtil.getIdentifier(table.getName()) + "'" + //$NON-NLS-1$ //$NON-NLS-2$
				" AND COLNAME ='" + LUWUtil.getIdentifier(column.getName()) + "'" ; //$NON-NLS-1$ //$NON-NLS-2$
		
		Statement s = connection.createStatement();
		ResultSet r = s.executeQuery(query);

		try {
			while(r.next()) {
				DB2IdentitySpecifier identity = (DB2IdentitySpecifier)identitySpecifier;
				identity.setIncrement(r.getBigDecimal("INCREMENT").toBigInteger()); //$NON-NLS-1$
				identity.setStartValue(r.getBigDecimal("START").toBigInteger()); //$NON-NLS-1$
				identity.setMinimum(r.getBigDecimal("MINVALUE").toBigInteger()); //$NON-NLS-1$
				identity.setMaximum(r.getBigDecimal("MAXVALUE").toBigInteger()); //$NON-NLS-1$
				identity.setCache(r.getInt("CACHE")); //$NON-NLS-1$

				if (r.getString("CYCLE").trim().equals("Y")){ //$NON-NLS-1$ //$NON-NLS-2$
					identitySpecifier.setCycleOption(true);
				}else {
					identitySpecifier.setCycleOption(false);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		r.close();
		s.close();
	}
	

	private synchronized void loadOptions() {
		if(this.optionsLoaded) return;
		this.optionsLoaded = true;
		EList options = super.getOptions();
		
		Table table = this.getTable();
		if (!(table instanceof LUWNickname)) return;  //only nickname column has option
		
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
		try {
			String query = "SELECT OPTION, SETTING FROM SYSCAT.COLOPTIONS WHERE TABSCHEMA='" //$NON-NLS-1$
				+ LUWUtil.getIdentifier(this.getTable().getSchema().getName()) + "' AND TABNAME='" + LUWUtil.getIdentifier(this.getTable().getName())  //$NON-NLS-1$
				+ "' AND COLNAME='" + LUWUtil.getIdentifier(this.getName()) + "'";  //$NON-NLS-1$ //$NON-NLS-2$
				
			Statement s = this.getConnection().createStatement();
			ResultSet r = s.executeQuery(query);
			try {
				while(r.next()) {
					final String name = r.getString(1);
					final String value = r.getString(2);
					LUWOption option = LUWFactory.eINSTANCE.createLUWOption();
					option.setName(name);
					option.setValue(value);
					options.add(option);
				}
			}
			catch (Exception e) {
			}
			r.close();
			s.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		this.eSetDeliver(deliver);
	}

	public static Collection getStatistics(Connection connection, Column column){
		Collection statistics = new ArrayList();
		statistics.addAll(getColumnStatistics(connection,column));
		statistics.addAll(getDistributeStatistics(connection,column));
		return statistics;
	}
	public static Collection getColumnStatistics(Connection connection, Column column){
		Collection statistics = new ArrayList();
		try {
			Table table = column.getTable();
			Schema schema = table.getSchema();

			String	query = "SELECT COLCARD,HIGH2KEY,LOW2KEY,AVGCOLLEN, NUMNULLS, SUB_COUNT" +
				",SUB_DELIM_LENGTH "+
				" FROM SYSSTAT.COLUMNS" +
				" WHERE TABSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
				" AND TABNAME ='" + LUWUtil.getIdentifier(table.getName()) + "'" +
				" AND COLNAME = '" + LUWUtil.getIdentifier(column.getName()) + "'" +
				" FOR FETCH ONLY";
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				CatalogStatistics stats = null;
				final BigInteger card = r.getBigDecimal("COLCARD").toBigInteger();
				if (card.intValue() != -1) {
					stats = new CatalogStatistics("COLCARD",LUWCatalogMessages.STAT_COL_CARD,LUWCatalogMessages.STAT_COL_CARD_DES,card,"SYSSTAT.COLUMNS");
					statistics.add(stats);
				}

				DataType datatype =  column.getDataType();
				boolean isBinaryData = false;
				if (datatype instanceof PredefinedDataType) {
					PredefinedDataType type = (PredefinedDataType)datatype;
					if (type.getPrimitiveType() == PrimitiveType.BINARY_LITERAL
							|| type.getPrimitiveType() == PrimitiveType.BINARY_VARYING_LITERAL) {
						isBinaryData = true;
					}
					
				}
				
				if (isBinaryData){
				} else {
					final String high2key = r.getString("HIGH2KEY");
					final String low2key  =  r.getString("LOW2KEY");
					boolean hasKeyValue = (high2key != null && !high2key.trim().equals("")
										&& low2key != null && !low2key.trim().equals("")
										&& !high2key.equals(low2key));
					
					if (!"".equals(high2key)) {
						stats = new CatalogStatistics("HIGH2KEY",LUWCatalogMessages.STAT_HIGH2KEY,LUWCatalogMessages.STAT_HIGH2KEY_DES,LUWUtil.getSingleQuotedString(high2key),hasKeyValue ? "SYSSTAT.COLUMNS":"" );
						statistics.add(stats);
					}
	
					if (!"".equals(low2key)) {
						stats = new CatalogStatistics("LOW2KEY",LUWCatalogMessages.STAT_LOW2KEY,LUWCatalogMessages.STAT_LOW2KEY_DES,LUWUtil.getSingleQuotedString(low2key),hasKeyValue ? "SYSSTAT.COLUMNS":"");
						statistics.add(stats);
					}
	
					final int avgLen = r.getInt("AVGCOLLEN");
					if (avgLen != -1) {
						stats = new CatalogStatistics("AVGCOLLEN",LUWCatalogMessages.STAT_AVG_COLLEN,LUWCatalogMessages.STAT_AVG_COLLEN_DES,avgLen,"SYSSTAT.COLUMNS");
						statistics.add(stats);
					}
				
				}
				final BigInteger numNulls = r.getBigDecimal("NUMNULLS").toBigInteger();;
				if (numNulls.intValue() != -1) {
					stats = new CatalogStatistics("NUMNULLS",LUWCatalogMessages.STAT_NUMNULLS,LUWCatalogMessages.STAT_NUMNULLS_DES,numNulls,"SYSSTAT.COLUMNS");
					statistics.add(stats);
				}

				final int subCount = r.getInt("SUB_COUNT");
				if (subCount != -1) {
					stats = new CatalogStatistics("SUB_COUNT",LUWCatalogMessages.STAT_SUB_COUNT,LUWCatalogMessages.STAT_SUB_COUNT_DES,subCount,"SYSSTAT.COLUMNS");
					statistics.add(stats);
				}

				final int subDelimLen = r.getInt("SUB_DELIM_LENGTH");
				if (subDelimLen != -1) {
					stats = new CatalogStatistics("SUB_DELIM_LENGTH",LUWCatalogMessages.STAT_SUB_DELIM_LENGTH,LUWCatalogMessages.STAT_SUB_DELIM_LENGTH_DES,subDelimLen,"SYSSTAT.COLUMNS");
					statistics.add(stats);
				}

			}
			r.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return statistics;
	}

	public static Collection getDistributeStatistics(Connection connection, Column column){
		Collection statistics = new ArrayList();
		Collection distStat = new ArrayList();
		Collection typeGroup = new ArrayList();
		try {
			Table table = column.getTable();
			Schema schema = table.getSchema();
			int groupNum = 1;
			
			String	query = "SELECT TYPE,SEQNO,COLVALUE,VALCOUNT,DISTCOUNT"+
				" FROM SYSSTAT.COLDIST" +
				" WHERE TABSCHEMA='" + LUWUtil.getIdentifier(table.getSchema().getName()) + "'" +
				" AND TABNAME ='" + LUWUtil.getIdentifier(table.getName()) + "'" +
				" AND COLNAME = '" + LUWUtil.getIdentifier(column.getName()) + "'" +
				" AND COLVALUE <>''" +
				" ORDER BY TYPE" +
				" FOR FETCH ONLY";
			Statement s = connection.createStatement();
			ResultSet r = s.executeQuery(query);
			while(r.next()) {
				CatalogStatistics stats = null;
				distStat = new ArrayList();
				final String type = r.getString("TYPE");
				if (type != null && !type.equals("")) {
					stats = new CatalogStatistics("TYPE",LUWCatalogMessages.STAT_COLDIST_TYPE,LUWCatalogMessages.STAT_COLDIST_TYPE_DES,type,"");
					distStat.add(stats);
				}

				final int seqno = r.getInt("SEQNO");
				if (seqno != -1 ) {
					stats = new CatalogStatistics("SEQNO",LUWCatalogMessages.STAT_COLDIST_SEQNO,LUWCatalogMessages.STAT_COLDIST_SEQNO_DES,seqno,"");
					distStat.add(stats);
				}

				final String colValue = r.getString("COLVALUE");
				if (colValue != null && !colValue.equals("")) {
					stats = new CatalogStatistics("COLVALUE",LUWCatalogMessages.STAT_COLDIST_COLVALUE,LUWCatalogMessages.STAT_COLDIST_COLVALUE_DES,colValue,"SYSSTAT.COLDIST");
					distStat.add(stats);
				}

				final BigInteger valueCount = r.getBigDecimal("VALCOUNT").toBigInteger();;
				if (valueCount.intValue() != -1) {
					stats = new CatalogStatistics("VALCOUNT",LUWCatalogMessages.STAT_COLDIST_VALCOUNT,LUWCatalogMessages.STAT_COLDIST_VALCOUNT_DES,valueCount,"SYSSTAT.COLDIST");
					distStat.add(stats);
				}

				final BigDecimal distColuntValue = r.getBigDecimal("DISTCOUNT");
				if (distColuntValue != null) {
					final BigInteger distCount = r.getBigDecimal("DISTCOUNT").toBigInteger();;
					if (distCount.intValue() != -1) {
						stats = new CatalogStatistics("DISTCOUNT",LUWCatalogMessages.STAT_COLDIST_DISTCOUNT,LUWCatalogMessages.STAT_COLDIST_DISTCOUNT_DES,distCount,"SYSSTAT.COLDIST");
						distStat.add(stats);
					}
				}

				if (distStat.size() >0 ) {
					statistics.add(new CatalogStatistics("Distribution " + seqno, MessageFormat.format(LUWCatalogMessages.STAT_COLDIST, new Object[]{new Integer(groupNum++)})
						,MessageFormat.format(LUWCatalogMessages.STAT_COLDIST_DES,new Object[]{new Integer(seqno)})
						,distStat,"SYSSTAT.COLDIST"));

				}

				
			}
			r.close();
			s.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return statistics;
	}
	
	
	void setGenerated(boolean isGenerated) {
		this.isGenerated = isGenerated;
	}
	
	private boolean identitySpecifierLoaded = false;
	private boolean generateExpressionLoaded = false;
	private boolean optionsLoaded = false;
	private boolean isGenerated = false;
	private boolean statisticsLoaded = false;
	
	private Collection statistics = new ArrayList();
}
