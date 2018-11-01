/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.MetaDataExtension;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.AbstractMetaDataExtension;
import org.eclipse.datatools.enablement.ibm.db2.internal.luw.LUWDatabaseRecognizer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWBufferPool;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionGroup;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission;
import org.eclipse.datatools.enablement.ibm.util.ModelHelper;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EClass;

public class LUWMetaDataExtension extends AbstractMetaDataExtension implements MetaDataExtension {

	public int getMaximumIdentifierLength(SQLObject sqlObject) {
		int maximumIdentifierLength = 0;
		
		if (sqlObject instanceof LUWTableSpace) {
			LUWTableSpace tablespace = (LUWTableSpace)sqlObject;
			LUWPartitionGroup group = tablespace.getGroup();
			if (group != null) {
				Database database = group.getDatabase();
				if (database != null) {
					DatabaseDefinition dbDef = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
					maximumIdentifierLength = dbDef.getTablespaceMaximumIdentifierLength();
				}
			}
		}
		else if (sqlObject instanceof LUWIndex) {
			LUWIndex index = (LUWIndex)sqlObject;
		
			Schema schema = index.getSchema();
			if (schema != null) {
				Database database = ModelHelper.getDatabase(schema);
				if (database != null) {
					DatabaseDefinition dbDef = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
                    String ver = dbDef.getVersion();
                    if (LUWDatabaseRecognizer.VERSION81.equalsIgnoreCase(ver) || 
                        LUWDatabaseRecognizer.VERSION81.equalsIgnoreCase(ver)) {
						maximumIdentifierLength = 18; 
					}
                    else if (LUWDatabaseRecognizer.VERSION9.equalsIgnoreCase(ver) || 
                             LUWDatabaseRecognizer.VERSION95.equalsIgnoreCase(ver)) {
						maximumIdentifierLength = 128;
					}
				}
			}
		}
		else if (sqlObject instanceof LUWBufferPool) {
			LUWBufferPool bufferPool = (LUWBufferPool)sqlObject;
			if (bufferPool != null) {
				maximumIdentifierLength = 18;
			}
		}
		else if ( sqlObject instanceof DB2Permission ||
				  sqlObject instanceof DB2Mask )
		{
			maximumIdentifierLength = 128;
		}
		
		return maximumIdentifierLength;
	}
	
	//TODO
	public EClass getMetaClass(String metaClassName){
		EClass eClass = (EClass)LUWPackage.eINSTANCE.getEClassifier(metaClassName);
		
		if (eClass == null) {
			eClass = (EClass)DB2ModelPackage.eINSTANCE.getEClassifier(metaClassName);	
		}
		
		if (eClass == null) {
			if (metaClassName.equalsIgnoreCase("MaterializedQueryTable")) { //$NON-NLS-1$
				eClass = (EClass)LUWPackage.eINSTANCE.getEClassifier("LUWMaterializedQueryTable"); //$NON-NLS-1$
			}
			else if (metaClassName.equalsIgnoreCase("Nickname")) { //$NON-NLS-1$
				eClass = (EClass)LUWPackage.eINSTANCE.getEClassifier("LUWGenericNickname"); //$NON-NLS-1$
			}
			else if (metaClassName.equalsIgnoreCase("Tablespace")) { //$NON-NLS-1$
				eClass = (EClass)LUWPackage.eINSTANCE.getEClassifier("LUWTableSpace"); //$NON-NLS-1$
			}
			else if (metaClassName.equalsIgnoreCase("Package")) { //$NON-NLS-1$
				eClass = (EClass)LUWPackage.eINSTANCE.getEClassifier("LUWDatabasePackage"); //$NON-NLS-1$
			}
			else if (metaClassName.equalsIgnoreCase("Module")) { //$NON-NLS-1$
				eClass = (EClass)LUWPackage.eINSTANCE.getEClassifier("LUWModule"); //$NON-NLS-1$
			}
			else if (metaClassName.equalsIgnoreCase("PLSQLPackage")) { //$NON-NLS-1$
				eClass = (EClass)LUWPackage.eINSTANCE.getEClassifier("PLSQLPackage"); //$NON-NLS-1$
			}
			else if (metaClassName.equalsIgnoreCase("GlobalVariable")) { //$NON-NLS-1$
				eClass = (EClass)LUWPackage.eINSTANCE.getEClassifier("LUWGlobalVariable"); //$NON-NLS-1$
			}
		}
		
		return eClass;
	}

}
