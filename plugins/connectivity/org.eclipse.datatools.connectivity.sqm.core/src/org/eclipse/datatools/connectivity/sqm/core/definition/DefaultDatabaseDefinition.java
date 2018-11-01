/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.definition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DDLParser;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DeltaDDLGenerator;
import org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.LanguageType;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EClass;

/**
 * This class lets implementors of DatabaseDefinition choose which APIs
 * to implement and buffers against compilation problems when new APIs are 
 * added.
 * 
 * @author Thomas Sharp, sharpt@us.ibm.com
 */
public class DefaultDatabaseDefinition implements DatabaseDefinition
{

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getActionElementClasses(org.eclipse.emf.ecore.EClass, java.lang.String)
    */
   public List getActionElementClasses(EClass privilegedElementClass,
      String action)
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getCheckConstraintMaximumIdentifierLength()
    */
   public int getCheckConstraintMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getColumnMaximumIdentifierLength()
    */
   public int getColumnMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getDDLGenerator()
    */
   public DDLGenerator getDDLGenerator()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getDataModelElementFactory()
    */
   public DataModelElementFactory getDataModelElementFactory()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getDatabaseCatalogProvider()
    */
   public ICatalogProvider getDatabaseCatalogProvider()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getDatabaseMaximumIdentifierLength()
    */
   public int getDatabaseMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getDdlParser()
    */
   public DDLParser getDdlParser()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getDebuggerDefinition()
    */
   public DebuggerDefinition getDebuggerDefinition()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getDeltaDDLGenerator()
    */
   public DeltaDDLGenerator getDeltaDDLGenerator()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getDescription()
    */
   public String getDescription()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getForeignKeyMaximumIdentifierLength()
    */
   public int getForeignKeyMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getFunctionLanguageType()
    */
   public List getFunctionLanguageType()
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getHostVariableMarker()
    */
   public String getHostVariableMarker()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getIdentifierQuoteString()
    */
   public String getIdentifierQuoteString()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getIdentityColumnSupportedPredefinedDataTypes()
    */
   public Iterator getIdentityColumnSupportedPredefinedDataTypes()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getLenghtSemantic(org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType)
    */
   public String getLenghtSemantic(
      CharacterStringDataType characterStringDataType)
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getMaximumIdentifierLength()
    */
   public int getMaximumIdentifierLength()
   {     
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getMaximumIdentifierLength(org.eclipse.datatools.modelbase.sql.schema.SQLObject)
    */
   public int getMaximumIdentifierLength(SQLObject sqlObject)
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getNicknameMaximumIdentifierLength()
    */
   public int getNicknameMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getParentDeleteDRIRules()
    */
   public List getParentDeleteDRIRules()
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getParentUpdateDRIRules()
    */
   public List getParentUpdateDRIRules()
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataType(java.lang.String)
    */
   public PredefinedDataType getPredefinedDataType(String dataTypeName)
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataType(org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition)
    */
   public PredefinedDataType getPredefinedDataType(
      PredefinedDataTypeDefinition predefinedDataTypeDefinition)
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataTypeByNameAndJDBCEnumType(java.lang.String, int)
    */
   public PredefinedDataType getPredefinedDataTypeByNameAndJDBCEnumType(
      String dataTypeName, int jdbcEnumType)
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataTypeDefinition(java.lang.String)
    */
   public PredefinedDataTypeDefinition getPredefinedDataTypeDefinition(
      String dataTypeName)
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataTypeDefinitionByNameAndJDBCEnumType(java.lang.String, int)
    */
   public PredefinedDataTypeDefinition getPredefinedDataTypeDefinitionByNameAndJDBCEnumType(
      String dataTypeName, int jdbcEnumType)
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataTypeDefinitionsByJDBCEnumType(int)
    */
   public List getPredefinedDataTypeDefinitionsByJDBCEnumType(int jdbcEnumType)
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataTypeFormattedName(org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType)
    */
   public String getPredefinedDataTypeFormattedName(
      PredefinedDataType predefinedDataType)
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataTypes()
    */
   public Iterator getPredefinedDataTypes()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPredefinedDataTypesByJDBCEnumType(int)
    */
   public List getPredefinedDataTypesByJDBCEnumType(int jdbcEnumType)
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPrimarykeyIdentifierLength()
    */
   public int getPrimarykeyIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPrivilegeActions(org.eclipse.emf.ecore.EClass)
    */
   public List getPrivilegeActions(EClass privilegedElementClass)
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getPrivilegedElementClasses()
    */
   public List getPrivilegedElementClasses()
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getProcedureLanguageType()
    */
   public List getProcedureLanguageType()
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getProduct()
    */
   public String getProduct()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getProductDisplayString()
    */
   public String getProductDisplayString()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getRoutineParameterPredefinedDataTypeDefinitions()
    */
   public Iterator getRoutineParameterPredefinedDataTypeDefinitions()
   {
      return null;
   }
   
   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getRoutineParameterPredefinedDataTypeDefinitions(LanguageType languageType)
    */
   public Iterator getRoutineParameterPredefinedDataTypeDefinitions(LanguageType languageType)
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getSQLKeywords()
    */
   public List getSQLKeywords()
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getSQLOperators()
    */
   public List getSQLOperators()
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getSQLTerminationCharacter()
    */
   public String getSQLTerminationCharacter()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getSchemaMaximumIdentifierLength()
    */
   public int getSchemaMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getSequenceSupportedPredefinedDataTypes()
    */
   public Iterator getSequenceSupportedPredefinedDataTypes()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getTableMaximumIdentifierLength()
    */
   public int getTableMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getTablespaceMaximumIdentifierLength()
    */
   public int getTablespaceMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getTriggerMaximumIdentifierLength()
    */
   public int getTriggerMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getUserDefinedTypeMaximumIdentifierLength()
    */
   public int getUserDefinedTypeMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getVersion()
    */
   public String getVersion()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getVersionDisplayString()
    */
   public String getVersionDisplayString()
   {
      return null;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getViewCheckOptionLevels()
    */
   public List getViewCheckOptionLevels()
   {
      return new ArrayList();
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#getViewMaximumIdentifierLength()
    */
   public int getViewMaximumIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isAuthorizationIdentifierSupported()
    */
   public boolean isAuthorizationIdentifierSupported()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isGroupSupported()
    */
   public boolean isGroupSupported()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isKeyConstraintSupported(org.eclipse.datatools.modelbase.sql.datatypes.DataType)
    */
   public boolean isKeyConstraintSupported(DataType dataType)
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isPrivilegedElementClass(org.eclipse.emf.ecore.EClass)
    */
   public boolean isPrivilegedElementClass(EClass clss)
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isRoleAuthorizationSupported()
    */
   public boolean isRoleAuthorizationSupported()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isRoleSupported()
    */
   public boolean isRoleSupported()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isSQLKeyword(java.lang.String)
    */
   public boolean isSQLKeyword(String word)
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isSQLOperator(java.lang.String)
    */
   public boolean isSQLOperator(String word)
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isUniqueKeyNullable()
    */
   public boolean isUniqueKeyNullable()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#isUserSupported()
    */
   public boolean isUserSupported()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#queryMaxCheckExpression()
    */
   public int queryMaxCheckExpression()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#queryMaxCommentLength()
    */
   public int queryMaxCommentLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#queryMaxIdentifierLength()
    */
   public int queryMaxIdentifierLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#queryStoredProcedureMaxActionBodyLength()
    */
   public int queryStoredProcedureMaxActionBodyLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#queryTriggerMaxActionBodyLength()
    */
   public int queryTriggerMaxActionBodyLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#queryTriggerMaxReferencePartLength()
    */
   public int queryTriggerMaxReferencePartLength()
   {
      return 0;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#setLenghtSemantic(org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType, java.lang.String)
    */
   public void setLenghtSemantic(
      CharacterStringDataType characterStringDataType, String value)
   {
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportTriggerWhenClause()
    */
   public boolean supportTriggerWhenClause()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsAlias()
    */
   public boolean supportsAlias()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsArrayDataType()
    */
   public boolean supportsArrayDataType()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsCastExpression()
    */
   public boolean supportsCastExpression()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsClusteredIndexes()
    */
   public boolean supportsClusteredIndexes()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsComputedColumns()
    */
   public boolean supportsComputedColumns()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsConstraints()
    */
   public boolean supportsConstraints()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsConstructedDataType()
    */
   public boolean supportsConstructedDataType()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsDefaultKeywordForInsertValue()
    */
   public boolean supportsDefaultKeywordForInsertValue()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsDeferrableConstraints()
    */
   public boolean supportsDeferrableConstraints()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsDistinctUserDefinedType()
    */
   public boolean supportsDistinctUserDefinedType()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsEvents()
    */
   public boolean supportsEvents()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsExtendedGrouping()
    */
   public boolean supportsExtendedGrouping()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsHostVariableMarker()
    */
   public boolean supportsHostVariableMarker()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsIdentityColumns()
    */
   public boolean supportsIdentityColumns()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsInformationalConstraints()
    */
   public boolean supportsInformationalConstraints()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsInsteadOfTrigger()
    */
   public boolean supportsInsteadOfTrigger()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsMQT()
    */
   public boolean supportsMQT()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsMQTIndex()
    */
   public boolean supportsMQTIndex()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsMultiSetDataType()
    */
   public boolean supportsMultiSetDataType()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsNicknameConstraints()
    */
   public boolean supportsNicknameConstraints()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsNicknameIndex()
    */
   public boolean supportsNicknameIndex()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsNicknames()
    */
   public boolean supportsNicknames()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsPackage()
    */
   public boolean supportsPackage()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsPerColumnUpdateTrigger()
    */
   public boolean supportsPerColumnUpdateTrigger()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsQuotedDDL()
    */
   public boolean supportsQuotedDDL()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsQuotedDML()
    */
   public boolean supportsQuotedDML()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsReferenceDataType()
    */
   public boolean supportsReferenceDataType()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsRowDataType()
    */
   public boolean supportsRowDataType()
   {
      return false;
   }
   
   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsCursorDataType()
    */
   public boolean supportsCursorDataType()
   {
	   return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsRowTriggerReference()
    */
   public boolean supportsRowTriggerReference()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsSQLStatement()
    */
   public boolean supportsSQLStatement()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsSQLUDFs()
    */
   public boolean supportsSQLUDFs()
   {
      return false;
   }
   
   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsUDF()
    */
   public boolean supportsUDF()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsSchema()
    */
   public boolean supportsSchema()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsSequence()
    */
   public boolean supportsSequence()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsSnapshotViews()
    */
   public boolean supportsSnapshotViews()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsStoredProcedureNullInputAction()
    */
   public boolean supportsStoredProcedureNullInputAction()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsStoredProcedures()
    */
   public boolean supportsStoredProcedures()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsStructuredUserDefinedType()
    */
   public boolean supportsStructuredUserDefinedType()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsSynonym()
    */
   public boolean supportsSynonym()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsTableAliasInDelete()
    */
   public boolean supportsTableAliasInDelete()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsTableTriggerReference()
    */
   public boolean supportsTableTriggerReference()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsTriggerGranularity()
    */
   public boolean supportsTriggerGranularity()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsTriggerReferencesClause()
    */
   public boolean supportsTriggerReferencesClause()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsTriggerTypes()
    */
   public boolean supportsTriggerTypes()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsTriggers()
    */
   public boolean supportsTriggers()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsUserDefinedType()
    */
   public boolean supportsUserDefinedType()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsViewCheckOption()
    */
   public boolean supportsViewCheckOption()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsViewCheckOptionLevels()
    */
   public boolean supportsViewCheckOptionLevels()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsViewIndex()
    */
   public boolean supportsViewIndex()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsViewTriggers()
    */
   public boolean supportsViewTriggers()
   {
      return false;
   }

   /* (non-Javadoc)
    * @see org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition#supportsXML()
    */
   public boolean supportsXML()
   {
      return false;
   }

}
