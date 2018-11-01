/*******************************************************************************
 * Copyright (c) 2001, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.definition;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogProvider;
import org.eclipse.datatools.connectivity.sqm.core.rte.fe.GenericDdlGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DDLParser;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.DeltaDDLGenerator;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDeltaDdlGenerator;
import org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.LanguageType;
import org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TableDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.CheckType;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;


public class DatabaseDefinitionImpl implements DatabaseDefinition {
	DatabaseDefinitionImpl(String product, String version, String desc, String productDisplayString, String versionDisplayString, URL modelURL) {
		this.product = product;
		this.version = version;
		this.description = desc;
		this.productDisplayString = productDisplayString;
		this.versionDisplayString = versionDisplayString;
		this.modelURL = modelURL;
	}

	public String getProduct() {
		return this.product;
	}

	public String getProductDisplayString() {
		return this.productDisplayString;
	}

	public String getVersion() {
		return this.version;
	}
	
	public String getVersionDisplayString() {
		return this.versionDisplayString;		
	}

	public String getDescription() {
		return this.description;
	}

	public DataModelElementFactory getDataModelElementFactory() {
		if(this.factory == null) {
			this.factory = DefaultDataModelElementFactory.INSTANCE;				
			
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "dataModelElementFactory"); //$NON-NLS-1$ //$NON-NLS-2$
			IExtension[] extensions = extensionPoint.getExtensions();
			for(int i=0; i<extensions.length; ++i) {
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for(int j=0; j<configElements.length; ++j) {
					if(configElements[j].getName().equals("factory")) { //$NON-NLS-1$
						String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
						if(!product.equals(this.product)) continue;
						String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
						if(!version.equals(this.version)) continue;
						try {
							this.factory = (DataModelElementFactory) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
						}
						catch(CoreException e) {
						    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
						            "The error was detected when creating the element factory for " + product + " " + version, e); //$NON-NLS-1$ //$NON-NLS-2$
							RDBCorePlugin.getDefault().getLog().log(status);
						}
						break;
					}
				}
			}
		}
		
		return this.factory;
	}

	public ICatalogProvider getDatabaseCatalogProvider() {
		if(this.catalogProvider == null) {
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "catalog"); //$NON-NLS-1$ //$NON-NLS-2$
			IExtension[] extensions = extensionPoint.getExtensions();
			int prePriority = -1;
			for(int i=0; i<extensions.length; ++i) {
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for(int j=0; j<configElements.length; ++j) {
					if(configElements[j].getName().equals("catalog")) { //$NON-NLS-1$
						String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
						if(!product.equals(this.product)) continue;
						String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
						if(!version.equals(this.version)) continue;
						String priority = configElements[j].getAttribute("priority"); //$NON-NLS-1$
						int currentPriority = 0;
						if (priority != null) {
							try {
								currentPriority = Integer.parseInt(priority);
							}catch (NumberFormatException formatEx){
							}
						} 
						if (currentPriority <= prePriority) continue;
						prePriority = currentPriority;
						try {
							this.catalogProvider = (ICatalogProvider) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
						}
						catch(CoreException e) {
						    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
						            "The error was detected when creating the catalog provider for " + product + " " + version, e); //$NON-NLS-1$ //$NON-NLS-2$
							RDBCorePlugin.getDefault().getLog().log(status);
						}
						break;
					}
				}
			}
		}

		//if no catalog provider, use JDBC provider
		if (this.catalogProvider == null) {
			this.catalogProvider = new JDBCProvider(this);
		}
		return this.catalogProvider;
	}

	public DDLParser getDdlParser() {
		if(this.parser == null) {
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "ddlParser"); //$NON-NLS-1$ //$NON-NLS-2$
			IExtension[] extensions = extensionPoint.getExtensions();
			int prePriority = -1;
			for(int i=0; i<extensions.length; ++i) {
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for(int j=0; j<configElements.length; ++j) {
					if(configElements[j].getName().equals("parser")) { //$NON-NLS-1$
						String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
						if(!product.equals(this.product)) continue;
						String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
						if(!version.equals(this.version)) continue;
						String priority = configElements[j].getAttribute("priority"); //$NON-NLS-1$
						int currentPriority = 0;
						if (priority != null) {
							try {
								currentPriority = Integer.parseInt(priority);
							}catch (NumberFormatException formatEx){
							}
						} 
						if (currentPriority <= prePriority) continue;
						prePriority = currentPriority;
						try {
							this.parser = (DDLParser) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
						}
						catch(CoreException e) {
							// log the error
							System.out.println(e);
						}
						break;
					}
				}
			}
		}

		return this.parser;
	}

	public DDLGenerator getDDLGenerator() {
		if(this.ddlGenerator == null) {
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "ddlGeneration"); //$NON-NLS-1$ //$NON-NLS-2$
			IExtension[] extensions = extensionPoint.getExtensions();
			int prePriority = -1;
			for(int i=0; i<extensions.length; ++i) {
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for(int j=0; j<configElements.length; ++j) {
					if(configElements[j].getName().equals("generator")) { //$NON-NLS-1$
						String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
						if(!product.equals(this.product)) continue;
						String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
						if(!version.equals(this.version)) continue;
						String priority = configElements[j].getAttribute("priority"); //$NON-NLS-1$
						int currentPriority = 0;
						if (priority != null) {
							try {
								currentPriority = Integer.parseInt(priority);
							}catch (NumberFormatException formatEx){
							}
						} 
						
						if (currentPriority <= prePriority) continue;
						
						prePriority = currentPriority;
						try {
							this.ddlGenerator = (DDLGenerator) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
						}
						catch(CoreException e) {
						    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
						            "The error was detected when creating the DDL generator for " + product + " " + version, e); //$NON-NLS-1$ //$NON-NLS-2$
							RDBCorePlugin.getDefault().getLog().log(status);
						}
						break;
					}
				}
			}
		}

		//if no ddl generator, use Generic ddl generator
		if (this.ddlGenerator == null) {
			this.ddlGenerator = new GenericDdlGenerator();
		}

		return this.ddlGenerator;
	}

	public DeltaDDLGenerator getDeltaDDLGenerator() {
		if(this.deltaDdlGenerator == null) {
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "ddlGeneration"); //$NON-NLS-1$ //$NON-NLS-2$
			IExtension[] extensions = extensionPoint.getExtensions();
			int prePriority = -1;
			for(int i=0; i<extensions.length; ++i) {
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for(int j=0; j<configElements.length; ++j) {
					if(configElements[j].getName().equals("delta")) { //$NON-NLS-1$
						String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
						if(!product.equals(this.product)) continue;
						String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
						if(!version.equals(this.version)) continue;
						String priority = configElements[j].getAttribute("priority"); //$NON-NLS-1$
						int currentPriority = 0;
						if (priority != null) {
							try {
								currentPriority = Integer.parseInt(priority);
							}catch (NumberFormatException formatEx){
							}
						} 
						if (currentPriority <= prePriority) continue;
						prePriority = currentPriority;
						try {
							this.deltaDdlGenerator = (DeltaDDLGenerator) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
						}
						catch(CoreException e) {
						    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
						            "The error was detected when creating the DDL generator for " + product + " " + version, e); //$NON-NLS-1$ //$NON-NLS-2$
							RDBCorePlugin.getDefault().getLog().log(status);
						}
						break;
					}
				}
			}
		}
		
		if(this.deltaDdlGenerator == null) this.deltaDdlGenerator = new GenericDeltaDdlGenerator();

		return this.deltaDdlGenerator;
	}

	public Iterator getPredefinedDataTypes() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.getPredefinedDataTypeDefinitions().iterator();
	}
	
	public List getPredefinedDataTypeDefinitionsByJDBCEnumType(int jdbcEnumType) {
		List predefinedDataTypeDefinitionList = new Vector();
		Iterator predefinedDataTypeDefinitionIterator = this.getPredefinedDataTypes();
	    while (predefinedDataTypeDefinitionIterator.hasNext()) {
	      Object o = predefinedDataTypeDefinitionIterator.next();
	      if (o instanceof PredefinedDataTypeDefinition) 
	        if(((PredefinedDataTypeDefinition)o).getJdbcEnumType() == jdbcEnumType) {
	        	predefinedDataTypeDefinitionList.add(o);
	        }
	    }
	    return predefinedDataTypeDefinitionList;
	}
	
	public List getPredefinedDataTypesByJDBCEnumType(int jdbcEnumType) {
		List predefinedDataTypeList = new Vector();
		Iterator predefinedDataTypeDefinitionIterator = this.getPredefinedDataTypes();
	    while (predefinedDataTypeDefinitionIterator.hasNext()) {
	      Object o = predefinedDataTypeDefinitionIterator.next();
	      if (o instanceof PredefinedDataTypeDefinition) 
	        if(((PredefinedDataTypeDefinition)o).getJdbcEnumType() == jdbcEnumType) {
	        	PredefinedDataType predefinedDataType = this.getPredefinedDataType((PredefinedDataTypeDefinition)o);
	        	if (predefinedDataType != null) {
	        		predefinedDataTypeList.add(predefinedDataType);
	        	}
	        }
	    }
	    return predefinedDataTypeList;
	}
	
	public PredefinedDataTypeDefinition getPredefinedDataTypeDefinitionByNameAndJDBCEnumType(String dataTypeName, int jdbcEnumType) {
		this.loadDatabaseDefinition();
		return (PredefinedDataTypeDefinition)this.nameAndJDBCEnumToPrimitiveDataTypeDefinitionMap.get(dataTypeName.toUpperCase() + "_" + jdbcEnumType); //$NON-NLS-1$
	}
	
	public PredefinedDataType getPredefinedDataTypeByNameAndJDBCEnumType(String dataTypeName, int jdbcEnumType) {
		PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinitionByNameAndJDBCEnumType(dataTypeName, jdbcEnumType);
		return this.getPredefinedDataType(predefinedDataTypeDefinition);
	}
	
	public PredefinedDataTypeDefinition getPredefinedDataTypeDefinition(String dataTypeName) {
		this.loadDatabaseDefinition();
		PredefinedDataTypeDefinition predefinedDataTypeDefinition = (PredefinedDataTypeDefinition)this.nameToPrimitiveDataTypeDefinitionMap.get(dataTypeName.toUpperCase());
		if (predefinedDataTypeDefinition == null) {
			predefinedDataTypeDefinition = (PredefinedDataTypeDefinition)this.nameToPrimitiveDataTypeDefinitionMap.get(dataTypeName);
		}
		return predefinedDataTypeDefinition;
	}
	
	public PredefinedDataType getPredefinedDataType(String dataTypeName) {
		this.loadDatabaseDefinition();
		PredefinedDataTypeDefinition predefinedDataTypeDefinition = (PredefinedDataTypeDefinition)this.nameToPrimitiveDataTypeDefinitionMap.get(dataTypeName.toUpperCase());
		return this.getPredefinedDataType(predefinedDataTypeDefinition);
	}
	
	public PredefinedDataType getPredefinedDataType(PredefinedDataTypeDefinition predefinedDataTypeDefinition) {
		
		if (predefinedDataTypeDefinition == null) return null;
		
		PrimitiveType primitiveType = predefinedDataTypeDefinition.getPrimitiveType();
		
		PredefinedDataType predefinedDataType = null;
		switch(primitiveType.getValue()) {
			case PrimitiveType.BIGINT: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
			}
			break;
			
			case PrimitiveType.BINARY_LARGE_OBJECT: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createBinaryStringDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((BinaryStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
			
			case PrimitiveType.BOOLEAN: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createBooleanDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
			}
			break;
			
			case PrimitiveType.CHARACTER: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((CharacterStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
			
			case PrimitiveType.CHARACTER_LARGE_OBJECT: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((CharacterStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
			
			case PrimitiveType.CHARACTER_VARYING: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((CharacterStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
			
			case PrimitiveType.DATE: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createDateDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
			}
			break;
			
			case PrimitiveType.DECIMAL: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createFixedPrecisionDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isPrecisionSupported()) {
					((FixedPrecisionDataType)predefinedDataType).setPrecision(predefinedDataTypeDefinition.getDefaultPrecision());
				}
				if (predefinedDataTypeDefinition.isScaleSupported()) {
					((FixedPrecisionDataType)predefinedDataType).setScale(predefinedDataTypeDefinition.getDefaultScale());
				}
			}
			break;
			
			case PrimitiveType.DOUBLE_PRECISION: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
			}
			break;
			
			case PrimitiveType.FLOAT: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isPrecisionSupported()) {
					((ApproximateNumericDataType)predefinedDataType).setPrecision(predefinedDataTypeDefinition.getDefaultPrecision());
				}
			}
			break;
			
			case PrimitiveType.INTEGER: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
			}
			break;
			
			case PrimitiveType.INTERVAL: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createIntervalDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));		
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLeadingFieldQualifierSupported()) {
					FieldQualifierDefinition leadingFieldQualifierDefinition = predefinedDataTypeDefinition.getDefaultLeadingFieldQualifierDefinition();
					if (leadingFieldQualifierDefinition != null) {
						((IntervalDataType)predefinedDataType).setLeadingQualifier(leadingFieldQualifierDefinition.getName());
						((IntervalDataType)predefinedDataType).setLeadingFieldPrecision(leadingFieldQualifierDefinition.getDefaultPrecision());
					}
				}
				if (predefinedDataTypeDefinition.isTrailingFieldQualifierSupported()) {
					FieldQualifierDefinition trailingFieldQualifierDefinition = predefinedDataTypeDefinition.getDefaultTrailingFieldQualifierDefinition();
					if (trailingFieldQualifierDefinition != null) {
						((IntervalDataType)predefinedDataType).setTrailingQualifier(trailingFieldQualifierDefinition.getName());
						((IntervalDataType)predefinedDataType).setTrailingFieldPrecision(trailingFieldQualifierDefinition.getDefaultPrecision());
					}
				}
			}
			break;
			
			case PrimitiveType.NATIONAL_CHARACTER: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));		
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((CharacterStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
						
			case PrimitiveType.NATIONAL_CHARACTER_VARYING: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));		
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((CharacterStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
						
			case PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createCharacterStringDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));		
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((CharacterStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
			
			case PrimitiveType.NUMERIC: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createFixedPrecisionDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isPrecisionSupported()) {
					((FixedPrecisionDataType)predefinedDataType).setPrecision(predefinedDataTypeDefinition.getDefaultPrecision());
				}
				if (predefinedDataTypeDefinition.isScaleSupported()) {
					((FixedPrecisionDataType)predefinedDataType).setScale(predefinedDataTypeDefinition.getDefaultScale());
				}
			}
			break;
			
			case PrimitiveType.REAL: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createApproximateNumericDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));	
				predefinedDataType.setPrimitiveType(primitiveType);
			}
			break;
			
			case PrimitiveType.SMALLINT: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createIntegerDataType();
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));	
				predefinedDataType.setPrimitiveType(primitiveType);
			}
			break;
			
			case PrimitiveType.TIME: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createTimeDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isPrecisionSupported()) {
					((TimeDataType)predefinedDataType).setFractionalSecondsPrecision(predefinedDataTypeDefinition.getDefaultPrecision());
				}
			}
			break;
			
			case PrimitiveType.TIMESTAMP: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createTimeDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));	
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isPrecisionSupported()) {
					((TimeDataType)predefinedDataType).setFractionalSecondsPrecision(predefinedDataTypeDefinition.getDefaultPrecision());
				}
			}
			break;
			
			case PrimitiveType.DATALINK: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createDataLinkDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));	
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((DataLinkDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
			
			case PrimitiveType.BINARY: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createBinaryStringDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));	
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((BinaryStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
			
			case PrimitiveType.BINARY_VARYING: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createBinaryStringDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));	
				predefinedDataType.setPrimitiveType(primitiveType);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					((BinaryStringDataType)predefinedDataType).setLength(predefinedDataTypeDefinition.getDefaultLength());
				}
			}
			break;
			
			case PrimitiveType.XML_TYPE: {
				predefinedDataType = SQLDataTypesFactory.eINSTANCE.createXMLDataType();	
				predefinedDataType.setName((String)predefinedDataTypeDefinition.getName().get(0));	
				predefinedDataType.setPrimitiveType(primitiveType);
			}
			break;
		}
		return predefinedDataType;
	}
	
	public String getPredefinedDataTypeFormattedName(PredefinedDataType predefinedDataType) {
		
		if (predefinedDataType == null) return null;
		
		PrimitiveType primitiveType = predefinedDataType.getPrimitiveType();
		
		String predefinedDataTypeFormattedName = null;
		switch(primitiveType.getValue()) {
			case PrimitiveType.BIGINT: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
			
			case PrimitiveType.BINARY: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataType.getName());
				if (predefinedDataTypeDefinition != null) {
					if (predefinedDataTypeDefinition.isDisplayNameSupported()) {
						String temp = predefinedDataTypeDefinition.getDisplayName();
						if ( (temp != null) && (temp.length() > 0) && predefinedDataTypeDefinition.isLengthSupported()) {
							predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(((BinaryStringDataType)predefinedDataType).getLength())});
						}
					}
					else {
						if (predefinedDataTypeDefinition.isLengthSupported() &&
								(((BinaryStringDataType)predefinedDataType).getLength() > 0) ) {
							predefinedDataTypeFormattedName += "(" + ((BinaryStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			}
			break;
			
			case PrimitiveType.BINARY_LARGE_OBJECT: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition.isLengthSupported() &&
						(((BinaryStringDataType)predefinedDataType).getLength() > 0) ) {
					predefinedDataTypeFormattedName += "(" + ((BinaryStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			break;
			
			case PrimitiveType.BINARY_VARYING: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataType.getName());
				if (predefinedDataTypeDefinition != null) {
					if (predefinedDataTypeDefinition.isDisplayNameSupported()) {
						String temp = predefinedDataTypeDefinition.getDisplayName();
						if ( (temp != null) && (temp.length() > 0) && predefinedDataTypeDefinition.isLengthSupported()) {
							predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(((BinaryStringDataType)predefinedDataType).getLength())});
						}
					}
					else {
						if (predefinedDataTypeDefinition.isLengthSupported()) {
							if (predefinedDataTypeDefinition.isLargeValueSpecifierSupported()) {
								if (((BinaryStringDataType)predefinedDataType).getLength() == predefinedDataTypeDefinition.getLargeValueSpecifierLength()) {
									predefinedDataTypeFormattedName += "(" + predefinedDataTypeDefinition.getLargeValueSpecifierName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
								}
								else {
									if (((BinaryStringDataType)predefinedDataType).getLength() > 0) {
										predefinedDataTypeFormattedName += "(" + ((BinaryStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
									}
								}
							}
							else {
								if (((BinaryStringDataType)predefinedDataType).getLength() > 0) {
									predefinedDataTypeFormattedName += "(" + ((BinaryStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
								}
							}
						}
					}
				}
			}
			break;
			
			case PrimitiveType.BOOLEAN: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
			
			case PrimitiveType.CHARACTER: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition.isDisplayNameSupported()) {
					String temp = predefinedDataTypeDefinition.getDisplayName();
					if ( (temp != null) && (temp.length() > 0) && predefinedDataTypeDefinition.isLengthSupported()) {
						if ( predefinedDataTypeDefinition.isLengthSemanticSupported() ) {
							String lengthSemantic = this.getLenghtSemantic((CharacterStringDataType)predefinedDataType);
							if ( (lengthSemantic != null) && (lengthSemantic.length() > 0) ) {
								predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(((CharacterStringDataType)predefinedDataType).getLength()) + " " + lengthSemantic}); //$NON-NLS-1$
							}
							else {
								predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(((CharacterStringDataType)predefinedDataType).getLength())});
							}
						}
						else {
							predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(((CharacterStringDataType)predefinedDataType).getLength())});
						}
					}
				}
				else {
					if (predefinedDataTypeDefinition.isLengthSupported() &&
							(((CharacterStringDataType)predefinedDataType).getLength() > 0) ) {
						predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
					}
				}
//				if (predefinedDataTypeDefinition.isLengthSupported() &&
//						(((CharacterStringDataType)predefinedDataType).getLength() > 0)	) {
//					predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
//				}
			}
			break;
			
			case PrimitiveType.CHARACTER_LARGE_OBJECT: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition.isLengthSupported() &&
						(((CharacterStringDataType)predefinedDataType).getLength() > 0)	) {
					predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			break;
			
			case PrimitiveType.CHARACTER_VARYING: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition != null) {
					if (predefinedDataTypeDefinition.isDisplayNameSupported()) {
						String temp = predefinedDataTypeDefinition.getDisplayName();
						if ( (temp != null) && (temp.length() > 0) && predefinedDataTypeDefinition.isLengthSupported()) {
							if ( predefinedDataTypeDefinition.isLengthSemanticSupported() ) {
								String lengthSemantic = this.getLenghtSemantic((CharacterStringDataType)predefinedDataType);
								if ( (lengthSemantic != null) && (lengthSemantic.length() > 0) ) {
									predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(((CharacterStringDataType)predefinedDataType).getLength()) + " " + lengthSemantic}); //$NON-NLS-1$
								}
								else {
									predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(((CharacterStringDataType)predefinedDataType).getLength())});
								}
							}
							else {
								predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(((CharacterStringDataType)predefinedDataType).getLength())});
							} 
						}
					}
					else {
						if (predefinedDataTypeDefinition.isLengthSupported()) {
							if (predefinedDataTypeDefinition.isLargeValueSpecifierSupported()) {
								if (((CharacterStringDataType)predefinedDataType).getLength() == predefinedDataTypeDefinition.getLargeValueSpecifierLength()) {
									predefinedDataTypeFormattedName += "(" + predefinedDataTypeDefinition.getLargeValueSpecifierName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
								}
								else {
									if (((CharacterStringDataType)predefinedDataType).getLength() > 0) {
										predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
									}
								}
							}
							else {
								if (((CharacterStringDataType)predefinedDataType).getLength() > 0) {
									predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
								}
							}
						}
					}
				}
			}
			break;
			
			case PrimitiveType.DATE: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
			
			case PrimitiveType.DECIMAL: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if ( predefinedDataTypeDefinition.isPrecisionSupported() && predefinedDataTypeDefinition.isScaleSupported() ) {
					int precision = ((FixedPrecisionDataType)predefinedDataType).getPrecision();
					if (precision > 0) {
						predefinedDataTypeFormattedName += "(" + precision + " , " + ((FixedPrecisionDataType)predefinedDataType).getScale() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				}
			}
			break;
			
			case PrimitiveType.DOUBLE_PRECISION: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
			
			case PrimitiveType.FLOAT: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition.isPrecisionSupported() &&
						(((ApproximateNumericDataType)predefinedDataType).getPrecision() > 0) ) {
					predefinedDataTypeFormattedName += "(" + ((ApproximateNumericDataType)predefinedDataType).getPrecision() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			break;
			
			case PrimitiveType.INTEGER: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
			
			case PrimitiveType.INTERVAL: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataType.getName());
				if (predefinedDataTypeDefinition.isDisplayNameSupported()) {
					String temp = predefinedDataTypeDefinition.getDisplayName();
					Vector parameterList = new Vector();
					IntervalQualifierType leadingQualifier = ((IntervalDataType)predefinedDataType).getLeadingQualifier();
					IntervalQualifierType trailingQualifier = ((IntervalDataType)predefinedDataType).getTrailingQualifier();
					
					if (  (leadingQualifier == null) && (trailingQualifier == null) ) {
						predefinedDataTypeFormattedName = predefinedDataType.getName();
						break;
					}
					
					if (  (leadingQualifier != null) && (leadingQualifier.toString().length() > 0) ) {
						String leadingQualifierString = leadingQualifier.toString();
						int leadingPrecision = ((IntervalDataType)predefinedDataType).getLeadingFieldPrecision();
						FieldQualifierDefinition fieldQualifierDefinition = this.getLeadingFieldQualifierDefinition(predefinedDataTypeDefinition, leadingQualifierString);
						int defaultLeadingPrecision = 0;
						if (fieldQualifierDefinition != null) {
							defaultLeadingPrecision = fieldQualifierDefinition.getDefaultPrecision();
						}
						if ( (leadingPrecision > 0) && fieldQualifierDefinition.isPrecisionSupported() && (leadingPrecision != defaultLeadingPrecision) ) {
							leadingQualifierString += "(" + Integer.toString(leadingPrecision) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
						}
						parameterList.add(leadingQualifierString);
					}
					
					
					if ( (trailingQualifier != null) && (trailingQualifier.toString().length() > 0) ) {
						String trailingQualifierString = trailingQualifier.toString();
						int trailingPrecision = ((IntervalDataType)predefinedDataType).getTrailingFieldPrecision();
						FieldQualifierDefinition fieldQualifierDefinition = this.getTrailingFieldQualifierDefinition(predefinedDataTypeDefinition, trailingQualifierString);
						int defaultTrailingPrecision = 0;
						if (fieldQualifierDefinition != null) {
							defaultTrailingPrecision = fieldQualifierDefinition.getDefaultPrecision();
						}
						if ( (trailingPrecision > 0) && fieldQualifierDefinition.isPrecisionSupported() && (trailingPrecision != defaultTrailingPrecision) )  {
								trailingQualifierString += "(" + Integer.toString(trailingPrecision) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
						}
						parameterList.add(trailingQualifierString);
					}
					
					Object[] parameters = new Object[parameterList.size()];
					for(int i=0; i<parameters.length; i++) {
						parameters[i] = parameterList.get(i);
					}
					
					predefinedDataTypeFormattedName = MessageFormat.format(temp, parameters);
				}
			}
			break;
			
			case PrimitiveType.NATIONAL_CHARACTER: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			break;
						
			case PrimitiveType.NATIONAL_CHARACTER_VARYING: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					if (predefinedDataTypeDefinition.isLargeValueSpecifierSupported()) {
						if (((CharacterStringDataType)predefinedDataType).getLength() == predefinedDataTypeDefinition.getLargeValueSpecifierLength()) {
							predefinedDataTypeFormattedName += "(" + predefinedDataTypeDefinition.getLargeValueSpecifierName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
						}
						else {
							if (((CharacterStringDataType)predefinedDataType).getLength() > 0) {
														predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
							}
						}
					}
					else {
						if (((CharacterStringDataType)predefinedDataType).getLength() > 0) {
							predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			}
			break;
						
			case PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					predefinedDataTypeFormattedName += "(" + ((CharacterStringDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			break;
			
			case PrimitiveType.NUMERIC: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if ( predefinedDataTypeDefinition.isPrecisionSupported() && predefinedDataTypeDefinition.isScaleSupported() ) {
					int precision = ((FixedPrecisionDataType)predefinedDataType).getPrecision();
					if (precision > 0) {
						predefinedDataTypeFormattedName += "(" + precision + " , " + ((FixedPrecisionDataType)predefinedDataType).getScale() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				}
			}
			break;
			
			case PrimitiveType.REAL: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
			
			case PrimitiveType.SMALLINT: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
			
			case PrimitiveType.TIME: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
			
			case PrimitiveType.TIMESTAMP: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
                if (predefinedDataTypeDefinition != null) {
                    if (predefinedDataTypeDefinition.isDisplayNameSupported()) {
                        String temp = predefinedDataTypeDefinition.getDisplayName();
                        if ( (temp != null) && (temp.length() > 0) && predefinedDataTypeDefinition.isPrecisionSupported()) {
                            int precision = ((TimeDataType)predefinedDataType).getFractionalSecondsPrecision();
                            if ( (precision > 0) && (precision != predefinedDataTypeDefinition.getDefaultPrecision()) ) {
                                predefinedDataTypeFormattedName = MessageFormat.format(temp, new Object[] {Integer.toString(precision)});
                            }
                        }
                    }
                    else {
                        if (predefinedDataTypeDefinition.isPrecisionSupported() ) {
                            int precision = ((TimeDataType)predefinedDataType).getFractionalSecondsPrecision();
                            if ( (precision > 0) && (precision != predefinedDataTypeDefinition.getDefaultPrecision()) ) {
                                predefinedDataTypeFormattedName += "(" + precision + ")"; //$NON-NLS-1$ //$NON-NLS-2$
                            }
                        }
                    }
                }
			}
			break;
			
			case PrimitiveType.DATALINK: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
				PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(predefinedDataTypeFormattedName);
				if (predefinedDataTypeDefinition.isLengthSupported()) {
					predefinedDataTypeFormattedName += "(" + ((DataLinkDataType)predefinedDataType).getLength() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			break;
			
			case PrimitiveType.XML_TYPE: {
				predefinedDataTypeFormattedName = predefinedDataType.getName();
			}
			break;
		}
		return predefinedDataTypeFormattedName;
	}
	
	public FieldQualifierDefinition getLeadingFieldQualifierDefinition(PredefinedDataTypeDefinition predefinedDataTypeDefinition, String fieldQualifierName) {
		Iterator leadingFieldQualifierDefinitionIter = predefinedDataTypeDefinition.getLeadingFieldQualifierDefinition().iterator();
		while(leadingFieldQualifierDefinitionIter.hasNext()) {
			FieldQualifierDefinition fieldQualifierDefinition = (FieldQualifierDefinition)leadingFieldQualifierDefinitionIter.next();
			if (fieldQualifierDefinition.getName().toString().equals(fieldQualifierName)) {
				return fieldQualifierDefinition;
			}
		}
		
		return null;
	}
	
	public FieldQualifierDefinition getTrailingFieldQualifierDefinition(PredefinedDataTypeDefinition predefinedDataTypeDefinition, String fieldQualifierName) {
		Iterator trailingFieldQualifierDefinitionIter = predefinedDataTypeDefinition.getTrailingFieldQualifierDefinition().iterator();
		while(trailingFieldQualifierDefinitionIter.hasNext()) {
			FieldQualifierDefinition fieldQualifierDefinition = (FieldQualifierDefinition)trailingFieldQualifierDefinitionIter.next();
			if (fieldQualifierDefinition.getName().toString().equals(fieldQualifierName)) {
				return fieldQualifierDefinition;
			}
		}
		
		return null;
	}
	
	public Iterator getSequenceSupportedPredefinedDataTypes() {
		this.loadDatabaseDefinition();
		SequenceDefinition sequenceDefinition = this.databaseVendorDefinition.getSequenceDefinition();
		if (sequenceDefinition != null) {
			return sequenceDefinition.getPredefinedDataTypeDefinitions().iterator();
		}
		else {
			return (new Vector()).iterator();
		}
	}
	
	public Iterator getIdentityColumnSupportedPredefinedDataTypes() {
		this.loadDatabaseDefinition();
		ColumnDefinition columnDefinition = this.databaseVendorDefinition.getColumnDefinition();
		if (columnDefinition != null) {
			return columnDefinition.getIdentityColumnDataTypeDefinitions().iterator();
		}
		else {
			return (new Vector()).iterator();
		}
	}
	
	public Iterator getRoutineParameterPredefinedDataTypeDefinitions() {
	    List datatypes = new ArrayList();
	    Iterator pdtIter = this.getPredefinedDataTypes();
	    while(pdtIter.hasNext()) {
	        datatypes.add(pdtIter.next());
	    }
	    Iterator rpdtIter = this.getRoutineParameterSpecificPredefinedDataTypeDefinitions();
	    while(rpdtIter.hasNext()) {
	        datatypes.add(rpdtIter.next());
	    }
	    return datatypes.iterator();
	}
	    
	public Iterator getRoutineParameterPredefinedDataTypeDefinitions(LanguageType languageType) {
	    List dataTypeDefinitions = new ArrayList();
	    Iterator pdtDefIter = this.getPredefinedDataTypes();
	    while(pdtDefIter.hasNext()) {
	        PredefinedDataTypeDefinition pdtDef = (PredefinedDataTypeDefinition)pdtDefIter.next();
	        if ( (languageType != null) && languageType.equals(LanguageType.PLSQL_LITERAL)) {
	            if (pdtDef.getLanguageType().contains(languageType)) {
	                dataTypeDefinitions.add(pdtDef);
	            }
	        }
	        else {
	            dataTypeDefinitions.add(pdtDef);    
	        }
	    }
	    Iterator rpdtIter = this.getRoutineParameterSpecificPredefinedDataTypeDefinitions(languageType);
	    while(rpdtIter.hasNext()) {
	        dataTypeDefinitions.add(rpdtIter.next());
	    }
	    return dataTypeDefinitions.iterator();
	}
	    
	private Iterator getRoutineParameterSpecificPredefinedDataTypeDefinitions() {
	    this.loadDatabaseDefinition();
	    StoredProcedureDefinition storedProcedureDefinition = this.databaseVendorDefinition.getStoredProcedureDefinition();
	    if (storedProcedureDefinition != null) {
	        return storedProcedureDefinition.getPredefinedDataTypeDefinitions().iterator();
	    }
	    else {
	        return (new ArrayList()).iterator();
	    }
	}
	    
	private Iterator getRoutineParameterSpecificPredefinedDataTypeDefinitions(LanguageType languageType) {
	    List dataTypeDefinitions = new ArrayList();
	    this.loadDatabaseDefinition();
	    StoredProcedureDefinition storedProcedureDefinition = this.databaseVendorDefinition.getStoredProcedureDefinition();
	    if (storedProcedureDefinition != null) {
	        Iterator pdtDefIter =  storedProcedureDefinition.getPredefinedDataTypeDefinitions().iterator();
	        while(pdtDefIter.hasNext()) {
	            PredefinedDataTypeDefinition pdtDef = (PredefinedDataTypeDefinition)pdtDefIter.next();
	            if ( (languageType != null) && languageType.equals(LanguageType.PLSQL_LITERAL)) {
	                if (pdtDef.getLanguageType().contains(languageType)) {
	                    dataTypeDefinitions.add(pdtDef);
	                }
	            }
	            else {
	                dataTypeDefinitions.add(pdtDef);    
	            }
	        }
	            
	        return dataTypeDefinitions.iterator();
	    }
	    else {
	        return (new ArrayList()).iterator();
	    }
	}

	public boolean isKeyConstraintSupported(DataType dataType) {
		if (dataType instanceof PredefinedDataType) {
			PredefinedDataTypeDefinition predefinedDataTypeDefinition = this.getPredefinedDataTypeDefinition(dataType.getName());
			if (predefinedDataTypeDefinition != null)
				return predefinedDataTypeDefinition.isKeyConstraintSupported();
		}
		
	    if(dataType instanceof DistinctUserDefinedType){
	        DistinctUserDefinedType userDefinedType = (DistinctUserDefinedType)dataType;
	        PredefinedDataTypeDefinition predefinedDataTypeDefinition  = this.getPredefinedDataTypeDefinition(userDefinedType.getPredefinedRepresentation().getName());
	        if (predefinedDataTypeDefinition  != null)
	            return predefinedDataTypeDefinition.isKeyConstraintSupported();
	    }

		return false;
	}
	
	public boolean supportsSchema() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isSchemaSupported();
	}
	
	public boolean supportsAlias() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isAliasSupported();
	}
	
	public boolean supportsSynonym() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isSynonymSupported();
	}
	
	public boolean supportsUserDefinedType() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isUserDefinedTypeSupported();
	}
	
	public boolean supportsStructuredUserDefinedType() {
		this.loadDatabaseDefinition();
		UserDefinedTypeDefinition udtDefinition = this.databaseVendorDefinition.getUdtDefinition();
		if ( (udtDefinition != null) && this.supportsUserDefinedType() ) {
			return udtDefinition.isStructuredTypeSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsDistinctUserDefinedType() {
		this.loadDatabaseDefinition();
		UserDefinedTypeDefinition udtDefinition = this.databaseVendorDefinition.getUdtDefinition();
		if ( (udtDefinition != null) && this.supportsUserDefinedType() ) {
			return udtDefinition.isDistinctTypeSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsConstructedDataType() {
	    this.loadDatabaseDefinition();
	    return this.databaseVendorDefinition.isUserDefinedTypeSupported();
	}
	    
	public boolean supportsArrayDataType() {
	    this.loadDatabaseDefinition();
	    ConstructedDataTypeDefinition constructedDataTypeDefinition = this.databaseVendorDefinition.getConstructedDataTypeDefinition();
	    if ( (constructedDataTypeDefinition != null) && this.supportsConstructedDataType() ) {
	        return constructedDataTypeDefinition.isArrayDatatypeSupported();
	    }
	    else {
	        return false;
	    }
	}
	    
	public boolean supportsMultiSetDataType() {
	    this.loadDatabaseDefinition();
	    ConstructedDataTypeDefinition constructedDataTypeDefinition = this.databaseVendorDefinition.getConstructedDataTypeDefinition();
	    if ( (constructedDataTypeDefinition != null) && this.supportsConstructedDataType() ) {
	        return constructedDataTypeDefinition.isMultisetDatatypeSupported();
	    }
	    else {
	        return false;
	    }
	}
	    
	public boolean supportsRowDataType() {
	    this.loadDatabaseDefinition();
	    ConstructedDataTypeDefinition constructedDataTypeDefinition = this.databaseVendorDefinition.getConstructedDataTypeDefinition();
	    if ( (constructedDataTypeDefinition != null) && this.supportsConstructedDataType() ) {
	        return constructedDataTypeDefinition.isRowDatatypeSupported();
	    }
	    else {
	        return false;
	    }
	}
	    
	public boolean supportsReferenceDataType() {
	    this.loadDatabaseDefinition();
	    ConstructedDataTypeDefinition constructedDataTypeDefinition = this.databaseVendorDefinition.getConstructedDataTypeDefinition();
	    if ( (constructedDataTypeDefinition != null) && this.supportsConstructedDataType() ) {
	        return constructedDataTypeDefinition.isReferenceDatatypeSupported();
	    }
	    else {
	        return false;
	    }
	}

	public boolean supportsCursorDataType() {
	    this.loadDatabaseDefinition();
	    ConstructedDataTypeDefinition constructedDataTypeDefinition = this.databaseVendorDefinition.getConstructedDataTypeDefinition();
	    if ( (constructedDataTypeDefinition != null) && this.supportsConstructedDataType() ) {
	        return constructedDataTypeDefinition.isCursorDatatypeSupported();
	    }
	    else {
	        return false;
	    }
	}
	
	public boolean supportsIdentityColumns() {
		this.loadDatabaseDefinition();
		ColumnDefinition columnDefinition = this.databaseVendorDefinition.getColumnDefinition();
		if (columnDefinition != null) {
			return columnDefinition.isIdentitySupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsComputedColumns() {
		this.loadDatabaseDefinition();
		ColumnDefinition columnDefinition = this.databaseVendorDefinition.getColumnDefinition();
		if (columnDefinition != null) {
			return columnDefinition.isComputedSupported();
		}
		else {
			return true;
		}
	}
	
	public boolean supportsDeferrableConstraints() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.isDeferrableConstraintSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsInformationalConstraints() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.isInformationalConstraintSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsSequence() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isSequenceSupported();
	}
	
	public boolean supportsMQT() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isMQTSupported();
	}
	
	public boolean supportsMQTIndex() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isMQTIndexSupported();
	}
	
	public boolean supportsClusteredIndexes() {
		this.loadDatabaseDefinition();
		IndexDefinition indexDefinition = this.databaseVendorDefinition.getIndexDefinition();
		if (indexDefinition != null) {
			return indexDefinition.isClusteringSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean isUniqueKeyNullable() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.isUniqueKeyNullable();
		}
		else {
			return false;
		}
	}
	
	public List getParentDeleteDRIRules() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.getParentDeleteDRIRuleType();
		}
		else {
			return new Vector();
		}
	}
	
	public List  getParentUpdateDRIRules() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.getParentUpdateDRIRuleType();
		}
		else {
			return new Vector();
		}
	}
	
	public boolean supportsConstraints() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isConstraintsSupported();
	}
	
	public int queryMaxCommentLength() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.getMaximumCommentLength();
	}
	
	public int queryMaxIdentifierLength() {
		this.loadDatabaseDefinition();
		int len =  this.databaseVendorDefinition.getMaximumIdentifierLength();
		if(len == 0) len = 128;
		return len;
	}
	
	public int getDatabaseMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		int len =  this.databaseVendorDefinition.getMaximumIdentifierLength();
		if(len == 0) len = 128;
		return len;
	}
	
	public int getSchemaMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		SchemaDefinition schemaDefinition = this.databaseVendorDefinition.getSchemaDefinition();
		if (schemaDefinition != null) {
			return schemaDefinition.getMaximumIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getTableMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		TableDefinition tableDefinition = this.databaseVendorDefinition.getTableDefinition();
		if (tableDefinition != null) {
			return tableDefinition.getMaximumIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getViewMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		ViewDefinition viewDefinition = this.databaseVendorDefinition.getViewDefinition();
		if (viewDefinition != null) {
			return viewDefinition.getMaximumIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getColumnMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		ColumnDefinition columnDefinition = this.databaseVendorDefinition.getColumnDefinition();
		if (columnDefinition != null) {
			return columnDefinition.getMaximumIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getTriggerMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.getMaximumIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getPrimarykeyIdentifierLength() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.getMaximumPrimaryKeyIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getForeignKeyMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.getMaximumForeignKeyIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getCheckConstraintMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.getMaximumCheckConstraintIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getNicknameMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		NicknameDefinition nicknameDefinition = this.databaseVendorDefinition.getNicknameDefinition();
		if (nicknameDefinition != null) {
			return nicknameDefinition.getMaximumIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getUserDefinedTypeMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		UserDefinedTypeDefinition udtDefinition = this.databaseVendorDefinition.getUdtDefinition();
		if (udtDefinition != null) {
			return udtDefinition.getMaximumIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int getTablespaceMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		TableSpaceDefinition tablespaceDefinition = this.databaseVendorDefinition.getTableSpaceDefinition();
		if (tablespaceDefinition != null) {
			return tablespaceDefinition.getMaximumIdentifierLength();
		}
		else {
			return 0;
		}
	}
	
	public int queryMaxCheckExpression() {
		this.loadDatabaseDefinition();
		ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
		if (constraintDefinition != null) {
			return constraintDefinition.getMaximumCheckExpressionLength();
		}
		else {
			return 0;
		}
	}
	
	public int queryTriggerMaxReferencePartLength() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.getMaximumReferencePartLength();
		}
		else {
			return 0;
		}
	}
	
	public int queryTriggerMaxActionBodyLength() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.getMaximumActionBodyLength();
		}
		else {
			return 0;
		}
	}
	
	public int queryStoredProcedureMaxActionBodyLength() {
		this.loadDatabaseDefinition();
		StoredProcedureDefinition storedProcedureDefinition = this.databaseVendorDefinition.getStoredProcedureDefinition();
		if (storedProcedureDefinition != null) {
			return storedProcedureDefinition.getMaximumActionBodyLength();
		}
		else {
			return 0;
		}
	}
	
	public boolean supportsStoredProcedureNullInputAction() {
		this.loadDatabaseDefinition();
		StoredProcedureDefinition storedProcedureDefinition = this.databaseVendorDefinition.getStoredProcedureDefinition();
		if (storedProcedureDefinition != null) {
			return storedProcedureDefinition.isNullInputActionSupported();
		}
		else {
			return false;
		}
	}
		
	public boolean supportsTriggers() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isTriggerSupported();
	}
	
	public boolean supportsTriggerTypes() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.isTypeSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsInsteadOfTrigger() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.isInsteadOfTriggerSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsPerColumnUpdateTrigger() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.isPerColumnUpdateTriggerSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsTriggerReferencesClause() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.isReferencesClauseSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsRowTriggerReference() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.isRowTriggerReferenceSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsTableTriggerReference() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.isTableTriggerReferenceSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportTriggerWhenClause() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.isWhenClauseSupported();
		}
		else {
			return true;
		}
	}
	
	public boolean supportsTriggerGranularity() {
		this.loadDatabaseDefinition();
		TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
		if (triggerDefinition != null) {
			return triggerDefinition.isGranularitySupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsSnapshotViews() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isSnapshotViewSupported();
	}
	
	public boolean supportsViewTriggers() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isViewTriggerSupported();	
	}
	
	public boolean supportsViewIndex() {
		this.loadDatabaseDefinition();
		ViewDefinition viewDefinition = this.databaseVendorDefinition.getViewDefinition();
		if (viewDefinition != null) {
			return viewDefinition.isIndexSupported();
		}
		else {
			return false;
		}	
	}
	
	public boolean supportsQuotedDML() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isQuotedDMLSupported();	
	}
	
	public boolean supportsQuotedDDL() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isQuotedDDLSupported();	
	}
	
	public String getIdentifierQuoteString() {
		this.loadDatabaseDefinition();
		QueryDefinition queryDefinition = this.databaseVendorDefinition.getQueryDefinition();
		if (queryDefinition != null) {
			return queryDefinition.getIdentifierQuoteString();
		}
		else {
			return ""; //$NON-NLS-1$
		}
	}
	
	public String getHostVariableMarker() {
		this.loadDatabaseDefinition();
		QueryDefinition queryDefinition = this.databaseVendorDefinition.getQueryDefinition();
		if (queryDefinition != null) {
			return queryDefinition.getHostVariableMarker();
		}
		else {
			return ""; //$NON-NLS-1$
		}
	}
	
	public boolean supportsHostVariableMarker() {
		this.loadDatabaseDefinition();
		QueryDefinition queryDefinition = this.databaseVendorDefinition.getQueryDefinition();
		if (queryDefinition != null) {
			return queryDefinition.isHostVariableMarkerSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsCastExpression() {
		this.loadDatabaseDefinition();
		QueryDefinition queryDefinition = this.databaseVendorDefinition.getQueryDefinition();
		if (queryDefinition != null) {
			return queryDefinition.isCastExpressionSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsDefaultKeywordForInsertValue() {
		this.loadDatabaseDefinition();
		QueryDefinition queryDefinition = this.databaseVendorDefinition.getQueryDefinition();
		if (queryDefinition != null) {
			return queryDefinition.isDefaultKeywordForInsertValueSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsExtendedGrouping() {
		this.loadDatabaseDefinition();
		QueryDefinition queryDefinition = this.databaseVendorDefinition.getQueryDefinition();
		if (queryDefinition != null) {
			return queryDefinition.isExtendedGroupingSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsTableAliasInDelete() {
		this.loadDatabaseDefinition();
		QueryDefinition queryDefinition = this.databaseVendorDefinition.getQueryDefinition();
		if (queryDefinition != null) {
			return queryDefinition.isTableAliasInDeleteSupported();
		}
		else {
			return false;
		}
	}
	
	public List getProcedureLanguageType() {
		this.loadDatabaseDefinition();
		StoredProcedureDefinition storedProcedureDefinition = this.databaseVendorDefinition.getStoredProcedureDefinition();
		if (storedProcedureDefinition != null) {
			return storedProcedureDefinition.getLanguageType();
		}
		else {
			return new Vector();
		}
	}
	
	public List getFunctionLanguageType() {
		this.loadDatabaseDefinition();
		StoredProcedureDefinition storedProcedureDefinition = this.databaseVendorDefinition.getStoredProcedureDefinition();
		if (storedProcedureDefinition != null) {
			return storedProcedureDefinition.getFunctionLanguageType();
		}
		else {
			return new Vector();
		}
	}
	
	public boolean supportsSQLStatement() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isSQLStatementSupported();
	}
	
	public List getSQLKeywords() {
		this.loadDatabaseDefinition();
		SQLSyntaxDefinition sqlSyntaxDefinition = this.databaseVendorDefinition.getSQLSyntaxDefinition();
		if (sqlSyntaxDefinition != null) {
			return sqlSyntaxDefinition.getKeywords();
		}
		else {
			return new Vector();
		}
	}
	
	public List getSQLOperators() {
		this.loadDatabaseDefinition();
		SQLSyntaxDefinition sqlSyntaxDefinition = this.databaseVendorDefinition.getSQLSyntaxDefinition();
		if (sqlSyntaxDefinition != null) {
			return sqlSyntaxDefinition.getOperators();
		}
		else {
			return new Vector();
		}
	}
	
	public String getSQLTerminationCharacter() {
		this.loadDatabaseDefinition();
		SQLSyntaxDefinition sqlSyntaxDefinition = this.databaseVendorDefinition.getSQLSyntaxDefinition();
		if (sqlSyntaxDefinition != null) {
			return sqlSyntaxDefinition.getTerminationCharacter();
		}
		else {
			return ";"; //$NON-NLS-1$
		}
	}
	
	public int getMaximumIdentifierLength() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.getMaximumIdentifierLength();
	}
	
	public int getMaximumIdentifierLength(SQLObject sqlObject) {
		int maximumLength = 0;
		
		this.loadDatabaseDefinition();
		if (sqlObject instanceof Database) {
			maximumLength = this.databaseVendorDefinition.getMaximumIdentifierLength();
		}
		else if (sqlObject instanceof Schema) {
			SchemaDefinition schemaDefinition = this.databaseVendorDefinition.getSchemaDefinition();
			if (schemaDefinition != null) {
				maximumLength = schemaDefinition.getMaximumIdentifierLength();
			}
		}
		else if (sqlObject instanceof BaseTable) {
			TableDefinition tableDefinition = this.databaseVendorDefinition.getTableDefinition();
			if (tableDefinition != null) {
				maximumLength = tableDefinition.getMaximumIdentifierLength();
			}
		}
		else if (sqlObject instanceof ViewTable) {
			ViewDefinition viewDefinition = this.databaseVendorDefinition.getViewDefinition();
			if (viewDefinition != null) {
				maximumLength = viewDefinition.getMaximumIdentifierLength();
			}
		}
		else if (sqlObject instanceof Column) {
			ColumnDefinition columnDefinition = this.databaseVendorDefinition.getColumnDefinition();
			if (columnDefinition != null) {
				maximumLength = columnDefinition.getMaximumIdentifierLength();
			}
		}
		else if (sqlObject instanceof PrimaryKey) {
			ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
			if (constraintDefinition != null) {
				maximumLength = constraintDefinition.getMaximumPrimaryKeyIdentifierLength();
			}
		}
		else if (sqlObject instanceof ForeignKey) {
			ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
			if (constraintDefinition != null) {
				maximumLength = constraintDefinition.getMaximumForeignKeyIdentifierLength();
			}
		}
		else if (sqlObject instanceof CheckConstraint) {
			ConstraintDefinition constraintDefinition = this.databaseVendorDefinition.getConstraintDefinition();
			if (constraintDefinition != null) {
				maximumLength = constraintDefinition.getMaximumCheckConstraintIdentifierLength();
			}
		}
		else if (sqlObject instanceof Trigger) {
			TriggerDefinition triggerDefinition = this.databaseVendorDefinition.getTriggerDefinition();
			if (triggerDefinition != null) {
				maximumLength = triggerDefinition.getMaximumIdentifierLength();
			}
		}
		else if (sqlObject instanceof UserDefinedType) {
			UserDefinedTypeDefinition udtDefinition = this.databaseVendorDefinition.getUdtDefinition();
			if (udtDefinition != null) {
				maximumLength = udtDefinition.getMaximumIdentifierLength();
			}
		}
		else if (sqlObject instanceof Procedure) {
			StoredProcedureDefinition storedProcedureDefinition = this.databaseVendorDefinition.getStoredProcedureDefinition();
			if (storedProcedureDefinition != null) {
				maximumLength = storedProcedureDefinition.getMaximumIdentifierLength();
			}
		}
		else if (sqlObject instanceof Index) {
			IndexDefinition indexDefinition = this.databaseVendorDefinition.getIndexDefinition();
			if (indexDefinition != null) {
				maximumLength = indexDefinition.getMaximumIdentifierLength();
			}
		}
		else {
			if (this.getMetaDataExtension() != null) {
				maximumLength = this.getMetaDataExtension().getMaximumIdentifierLength(sqlObject);
			}
		}
		
		return maximumLength;
	}
	
	public boolean isSQLKeyword(String word) {
		this.loadDatabaseDefinition();
		SQLSyntaxDefinition sqlSyntaxDefinition = this.databaseVendorDefinition.getSQLSyntaxDefinition();
		if (sqlSyntaxDefinition != null) {
			return sqlSyntaxDefinition.getKeywords().contains(word);
		}
		else {
			return false;
		}
	}
	
	public boolean isSQLOperator(String word) {
		this.loadDatabaseDefinition();
		SQLSyntaxDefinition sqlSyntaxDefinition = this.databaseVendorDefinition.getSQLSyntaxDefinition();
		if (sqlSyntaxDefinition != null) {
			return sqlSyntaxDefinition.getOperators().contains(word);
		}
		else {
			return false;
		}
	}
	
	public boolean supportsNicknames() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isNicknameSupported();
	}
	
	public boolean supportsNicknameConstraints() {
		this.loadDatabaseDefinition();
		NicknameDefinition nicknameDefinition = this.databaseVendorDefinition.getNicknameDefinition();
		if (nicknameDefinition != null) {
			return nicknameDefinition.isConstraintSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsNicknameIndex() {
		this.loadDatabaseDefinition();
		NicknameDefinition nicknameDefinition = this.databaseVendorDefinition.getNicknameDefinition();
		if (nicknameDefinition != null) {
			return nicknameDefinition.isIndexSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsXML()
	{
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isXmlSupported();	
	}
	
	public boolean supportsEvents() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isEventSupported();
	}
		
	public boolean supportsSQLUDFs() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isSqlUDFSupported();
	}
	
	public boolean supportsUDF() {
	    this.loadDatabaseDefinition();
	    return this.databaseVendorDefinition.isUDFSupported();
	}
		
	public boolean supportsStoredProcedures() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isStoredProcedureSupported();
	}
		
	public DebuggerDefinition getDebuggerDefinition() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.getDebuggerDefinition();
	}
	
	public boolean supportsPackage() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isPackageSupported();
	}
	
	public EClass getMetaClass(String metaClassName) {
		EClass eClass = null;
		
		eClass = (EClass)SQLSchemaPackage.eINSTANCE.getEClassifier(metaClassName);
		if (eClass == null) {
			eClass = (EClass)SQLTablesPackage.eINSTANCE.getEClassifier(metaClassName);
		}
		if (eClass == null) {
			eClass = (EClass)SQLRoutinesPackage.eINSTANCE.getEClassifier(metaClassName);
		}
		if (eClass == null) {
			eClass = (EClass)SQLConstraintsPackage.eINSTANCE.getEClassifier(metaClassName);
		}
		if (eClass == null) {
			eClass = (EClass)SQLSchemaPackage.eINSTANCE.getEClassifier(metaClassName);
		}
		if (eClass == null) {
			if (this.getMetaDataExtension() != null) {
				AbstractMetaDataExtension mde = this.getMetaDataExtension();
				try {
					if ( (mde != null) && (mde.getClass().getMethod("getMetaClass", new Class[] {String.class}) != null)) { //$NON-NLS-1$
						eClass = this.getMetaDataExtension().getMetaClass(metaClassName);
					}
				}
				catch(NoSuchMethodException e) {				
				}
			}
		}
		
		return eClass;
	}
	
	public boolean isAuthorizationIdentifierSupported() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isAuthorizationIdentifierSupported();
	}
	
	public boolean isRoleSupported() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isRoleSupported();
	}
	
	public boolean isUserSupported() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isUserSupported();
	}
	
	public boolean isGroupSupported() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isGroupSupported();
	}
	
	public boolean isRoleAuthorizationSupported() {
		this.loadDatabaseDefinition();
		return this.databaseVendorDefinition.isRoleAuthorizationSupported();
	}
	
	public List getPrivilegedElementClasses() {
		this.loadDatabaseDefinition();
		
		this.loadPrivilegeDefinitions();
		List privilegedElementClasses = new ArrayList();
		privilegedElementClasses.addAll(this.eClassToPrivilegeDefinitionMap.keySet());
		
		return privilegedElementClasses;
	}
	
	public boolean isPrivilegedElementClass(EClass clss) {
		this.loadDatabaseDefinition();
		this.loadPrivilegeDefinitions();
		
		boolean result = this.eClassToPrivilegeDefinitionMap.keySet().contains(clss);
		if (result == false) {
			EClass superClss = this.getSuperMetaClass(clss);
			result = this.eClassToPrivilegeDefinitionMap.keySet().contains(superClss);
		}
		
		return result;
	}
	
	public List getPrivilegeActions(EClass privilegedElementClass) {
		this.loadDatabaseDefinition();
		
		this.loadPrivilegeDefinitions();
		List privilegeNames = new ArrayList();
		List privilegeDefinitions = (List)this.eClassToPrivilegeDefinitionMap.get(privilegedElementClass);
		if ( (privilegeDefinitions == null) || privilegeDefinitions.isEmpty()) {
			EClass superClss = this.getSuperMetaClass(privilegedElementClass);
			privilegeDefinitions = (List)this.eClassToPrivilegeDefinitionMap.get(superClss);
		}
		if (privilegeDefinitions != null) {
			Iterator privilegeDefinitionsIter = privilegeDefinitions.iterator();
			while(privilegeDefinitionsIter.hasNext()) {
				PrivilegeDefinition privilegeDefinition = (PrivilegeDefinition)privilegeDefinitionsIter.next();
				privilegeNames.add(privilegeDefinition.getName());
			}
		}
		
		return privilegeNames;
	}
	
	public List getActionElementClasses(EClass privilegedElementClass, String action) {
		this.loadDatabaseDefinition();
		
		this.loadPrivilegeDefinitions();
		List actionElementClasses = new ArrayList();
		List privilegeDefinitions = (List)this.eClassToPrivilegeDefinitionMap.get(privilegedElementClass);
		if ( (privilegeDefinitions == null) || privilegeDefinitions.isEmpty()) {
			EClass superClss = this.getSuperMetaClass(privilegedElementClass);
			privilegeDefinitions = (List)this.eClassToPrivilegeDefinitionMap.get(superClss);
		}
		if (privilegeDefinitions != null) {
			Iterator privilegeDefinitionsIter = privilegeDefinitions.iterator();
			while(privilegeDefinitionsIter.hasNext()) {
				PrivilegeDefinition privilegeDefinition = (PrivilegeDefinition)privilegeDefinitionsIter.next();
				Iterator actionClassDefinitionsIter = privilegeDefinition.getActionElementDefinitions().iterator();
				if ( privilegeDefinition.getName().equalsIgnoreCase(action) && !privilegeDefinition.getActionElementDefinitions().isEmpty() ) {
					while(actionClassDefinitionsIter.hasNext()) {
						PrivilegedElementDefinition privilegedElementDefinition = (PrivilegedElementDefinition)actionClassDefinitionsIter.next();
						String metaClassName = privilegedElementDefinition.getName();
						if ( (metaClassName != null) && (metaClassName.length() > 0) ) {
							EClass eClass = this.getMetaClass(metaClassName);
							if (eClass != null) {
								actionElementClasses.add(eClass);
							}
						}
					}
				}
			}
		}
		
		return actionElementClasses;
	}
	
	private EClass getSuperMetaClass(EClass eClass) {
		if (eClass != null) {
			Iterator eClassIterator = this.eClassToPrivilegeDefinitionMap.keySet().iterator();
			while(eClassIterator.hasNext()) {
				EClass currentEClass = (EClass)eClassIterator.next();
				if (currentEClass.isSuperTypeOf(eClass)) {
					return currentEClass;
				}
			}
		}
		
		return eClass;
	}
	
	private void loadPrivilegeDefinitions() {
		if (this.eClassToPrivilegeDefinitionMap == null) {
			this.eClassToPrivilegeDefinitionMap = new HashMap();
		}
		List privilegedElementDefinitions = this.databaseVendorDefinition.getPrivilegedElementDefinitions();
		if (privilegedElementDefinitions != null) {
			Iterator privilegedElementIter = privilegedElementDefinitions.iterator();
			while(privilegedElementIter.hasNext()) {
				PrivilegedElementDefinition privilegedElementDefinition = (PrivilegedElementDefinition)privilegedElementIter.next();
				String metaClassName = privilegedElementDefinition.getName();
				if ( (metaClassName != null) && (metaClassName.length() > 0) ) {
						EClass eClass = this.getMetaClass(metaClassName);
						if (eClass != null) {
							Iterator privilegeDefinitionIter = privilegedElementDefinition.getPrivilegeDefinitions().iterator();
							List privilegesDefinitions = new ArrayList();
							while(privilegeDefinitionIter.hasNext()) {
								privilegesDefinitions.add((PrivilegeDefinition)privilegeDefinitionIter.next());
							}
							this.eClassToPrivilegeDefinitionMap.put(eClass, privilegesDefinitions);
						}
				}
			}
		}
	}
	
	private AbstractMetaDataExtension getMetaDataExtension() {
		if(this.metaDataExtension == null) {
			IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
			IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "metaDataExtension"); //$NON-NLS-1$ //$NON-NLS-2$
			IExtension[] extensions = extensionPoint.getExtensions();
			for(int i=0; i<extensions.length; ++i) {
				IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
				for(int j=0; j<configElements.length; ++j) {
					if(configElements[j].getName().equals("metaDataExtension")) { //$NON-NLS-1$
						String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
						if(!product.equals(this.product)) continue;
						String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
						if(!version.equals(this.version)) continue;
						try {
							this.metaDataExtension = (AbstractMetaDataExtension) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
						}
						catch(CoreException e) {
						    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
						            "The error was detected when creating the meta data extension for " + product + " " + version, e); //$NON-NLS-1$ //$NON-NLS-2$
							RDBCorePlugin.getDefault().getLog().log(status);
						}
						break;
					}
				}
			}
		}
		
		return this.metaDataExtension;
	}
	
	private DatabaseVendorDefinition loadDatabaseDefinition() {
		if(this.databaseVendorDefinition == null) {
		    //double check
		    synchronized(this)
		    {
		        if(this.databaseVendorDefinition == null)
		        {
		    
        			// Load specified databaseType on demand
        			try {
        				URI uri = URI.createURI(modelURL.toString());
        				Resource doc = new XMIResourceImpl(uri);
        				doc.load(null);
        				EList resourceContents = doc.getContents();
        				Iterator i = resourceContents.iterator();
        				while (i.hasNext()) {
        					Object o = i.next();
        					if (o instanceof DatabaseVendorDefinition) {
        						this.databaseVendorDefinition = (DatabaseVendorDefinition)o;
        						if (this.nameToPrimitiveDataTypeDefinitionMap == null) {
        							this.nameToPrimitiveDataTypeDefinitionMap = new HashMap();
        						}
        						if (this.nameAndJDBCEnumToPrimitiveDataTypeDefinitionMap == null) {
        							this.nameAndJDBCEnumToPrimitiveDataTypeDefinitionMap = new HashMap();
        						}
        						Iterator j = this.databaseVendorDefinition.getPredefinedDataTypeDefinitions().iterator();
        						while (j.hasNext()) {
        							Object p = j.next();
        							if (p instanceof PredefinedDataTypeDefinition) {
        								PredefinedDataTypeDefinition pd = (PredefinedDataTypeDefinition)p;
        								Iterator k = pd.getName().iterator();
        								while (k.hasNext()) {
        									Object q = k.next();
        									if (q instanceof String) {
        										String name = (String)q;
        										if (this.nameToPrimitiveDataTypeDefinitionMap.get(name) == null) {
        											this.nameToPrimitiveDataTypeDefinitionMap.put(name, pd);
        										}
        										if (this.nameAndJDBCEnumToPrimitiveDataTypeDefinitionMap.get(name + "_" + pd.getJdbcEnumType()) == null ) { //$NON-NLS-1$
        											this.nameAndJDBCEnumToPrimitiveDataTypeDefinitionMap.put(name + "_" + pd.getJdbcEnumType(), pd); //$NON-NLS-1$
        										} 
        									}
        								}
        							}
        						}
                                StoredProcedureDefinition spdef = this.databaseVendorDefinition.getStoredProcedureDefinition();
                                if (spdef != null) {
                                    Iterator m = spdef.getPredefinedDataTypeDefinitions().iterator();
                                    while (m.hasNext()) {
                                        Object p = m.next();
                                        if (p instanceof PredefinedDataTypeDefinition) {
                                            PredefinedDataTypeDefinition pd = (PredefinedDataTypeDefinition)p;
                                            Iterator k = pd.getName().iterator();
                                            while (k.hasNext()) {
                                                Object q = k.next();
                                                if (q instanceof String) {
                                                    String name = (String)q;
                                                    if (this.nameToPrimitiveDataTypeDefinitionMap.get(name) == null) {
                                                        this.nameToPrimitiveDataTypeDefinitionMap.put(name, pd);
                                                    } 
                                                }
                                            }
                                        }
                                    }
                                }
        					}
        				}
        			}	
        			catch (Exception e) {
        				System.out.println("Exception caught while loading database vendor definition document: " + e); //$NON-NLS-1$
        			}
        		}
		    }
		}
		
		return this.databaseVendorDefinition;
	}
	
	public String getLenghtSemantic(CharacterStringDataType characterStringDataType) {
		EAnnotation eAnnotation = characterStringDataType.getEAnnotation(LENGTH_SEMANTIC);
		if (eAnnotation != null) {
			String detail = (String) eAnnotation.getDetails().get(LENGTH_SEMANTIC_TYPE);
			if (detail != null) return detail;
		}
		return null;
	}
	
	public void setLenghtSemantic(CharacterStringDataType characterStringDataType, String value) {
		EAnnotation eAnnotation = characterStringDataType.addEAnnotation(LENGTH_SEMANTIC);
		if (eAnnotation != null) {
			characterStringDataType.addEAnnotationDetail(eAnnotation, LENGTH_SEMANTIC_TYPE, value);
		}
	}
	
	public boolean supportsViewCheckOption() {
		this.loadDatabaseDefinition();
		ViewDefinition viewDefinition = this.databaseVendorDefinition.getViewDefinition();
		if (viewDefinition != null) {
			return viewDefinition.isCheckOptionSupported();
		}
		else {
			return false;
		}
	}
	
	public boolean supportsViewCheckOptionLevels() {
		this.loadDatabaseDefinition();
		ViewDefinition viewDefinition = this.databaseVendorDefinition.getViewDefinition();
		if (viewDefinition != null) {
			return viewDefinition.isCheckOptionLevelsSupported();
		}
		else {
			return false;
		}
	}
	
	public List getViewCheckOptionLevels() {
		List checkOptionLevels = new ArrayList();
		if (this.supportsViewCheckOption()) {
			if (this.supportsViewCheckOptionLevels()) {
				checkOptionLevels.add(CheckType.NONE_LITERAL);
				checkOptionLevels.add(CheckType.CASCADED_LITERAL);
				checkOptionLevels.add(CheckType.LOCAL_LITERAL);
			}
			else {
				checkOptionLevels.add(CheckType.NONE_LITERAL);
			}
		}
		
		return checkOptionLevels;
	}
	
	private String product;
	private String version;
	private String description;
	private String productDisplayString;
	private String versionDisplayString;
	private URL modelURL;
	private DatabaseVendorDefinition databaseVendorDefinition = null;
	private HashMap nameToPrimitiveDataTypeDefinitionMap = null;
	private HashMap nameAndJDBCEnumToPrimitiveDataTypeDefinitionMap = null;
	private HashMap eClassToPrivilegeDefinitionMap = null;
	
	private DataModelElementFactory factory = null;
	private DDLParser parser = null;
	private DDLGenerator ddlGenerator = null;
	private DeltaDDLGenerator deltaDdlGenerator = null;
	private ICatalogProvider catalogProvider = null;
	private AbstractMetaDataExtension metaDataExtension = null;
	private static String LENGTH_SEMANTIC = "LENGTH_SEMANTIC";
	private static String LENGTH_SEMANTIC_TYPE = "LENGTH_SEMANTIC_TYPE";
}
