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
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ibm.catalog.util.CatalogLoadUtil;
import org.eclipse.datatools.enablement.ibm.db2.DB2PluginActivator;
import org.eclipse.datatools.enablement.ibm.util.CloneUtil;
import org.eclipse.datatools.enablement.ibm.util.DBReverseProvider;
//bgp import org.eclipse.datatools.enablement.ibm.db2.util.ImplicitRelationshipHandler;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

public class DatabaseREProvider
{
	public static final int EXCLUDE_INDEXES = 1;
	public static final int EXCLUDE_TRIGGERS = 2;
	public static final int GENERATE_IMPLICIT_RELATIONSHIPS= 4;
	public static final int EXCLUDE_TABLES = 8;
	public static final int EXCLUDE_ROUTINES = 16;
	public static final int EXCLUDE_USER_DEFINED_TYPE = 32;
	public static final int EXCLUDE_SEQUENCE = 64;
	public static final int EXCLUDE_TABLESPACE = 128;
	public static final int EXCLUDE_VIEWS = 256;
	public static final int EXCLUDE_SYNONYMS = 512;
	public static final int GENERATE_IMPLICIT_PKS = 1024;
	public static final int EXCLUDE_ACCESS_CONTROL = 2048;
	public static final int EXCLUDE_FGAC = 4096;
	
	public static final String LOAD_PROPERTY = "LOAD_PROPERTY"; //$NON-NLS-1$
	public static final String IS_BATCH_LOAD = "BATCH_LOAD"; //$NON-NLS-1$
	public static final String LOAD_OPTIONS = "LOAD_OPTIONS"; //$NON-NLS-1$
	public static final String OWNER_LOAD_OPTION ="OWNER_LOAD_OPTION"; //$NON-NLS-1$
	public static final String OwnerFilterSelected ="isOwnerFilterSelected"; //$NON-NLS-1$
	
	private boolean isOwnerForRE;
	
//<bgp	public void reverseEngineer( //
//			Database db, int opts, List<String> colNamesToIgnore, //
//			EObject[] objsToRE, IProgressMonitor progMonitor)
//	{ 
//		this.database = db;
//        this.options = opts;
//        this.columnNamesToIgnore = colNamesToIgnore;
//        this.objectsToRE = objsToRE;
//        this.monitor = progMonitor;
//
//		this.loadAndBuild();
//bgp>	}
	
//<bgp	public void loadAndBuild()
//	{
//        int len = objectsToRE.length;
//        if (len ==0)
//        	return;
//
//        ContainmentService service = RDBCorePlugin.getDefault().getContainmentService();
//        EObject db = service.getRootElement(objectsToRE[0]);
//
//		this.setLoadProperties(db);
//
//		//owner EAnnoation check
//		EAnnotation ownerEAnnoation=((SQLObject)objectsToRE[0]).getEAnnotation(OWNER_LOAD_OPTION);
//		if (ownerEAnnoation != null) {
//			EMap<String, String> ownerEmap = ownerEAnnoation.getDetails();
//			String ownerValue = ownerEmap.get(OwnerFilterSelected);
//			isOwnerForRE = Boolean.parseBoolean(ownerValue);
//		}
//		
//        //DBReverseProvider provider = DatabaseREProvider.getDBReverseProvider(database);
//		DBReverseProvider reProvider = getDBReverseProvider(database);
//		if (reProvider != null) {
//			reProvider.prepareLoader(database, options, objectsToRE);
//		}
//
//		if(!isOwnerForRE) {
//	        int count = 100 / len;
//
//	        for (int objCount = 0; objCount < objectsToRE.length; objCount++)
//	        {
//	            CatalogLoadUtil.load(objectsToRE[objCount], monitor, count >> 2);
//
//	            if (monitor.isCanceled())
//	            {
//	                return;
//	            }
//	        }
//		}
//
//		this.handleDatabaseVendors();
//        
//        if ((this.options & GENERATE_IMPLICIT_PKS) == GENERATE_IMPLICIT_PKS && !monitor.isCanceled())
//        {
//            this.generateImplicitPK(this.database);
//        }
//
//        if ((this.options & GENERATE_IMPLICIT_RELATIONSHIPS) == GENERATE_IMPLICIT_RELATIONSHIPS && !monitor.isCanceled())
//        {
//            this.generateExplicitRelationship(this.database);
//        }
//
//        this.resetLoadProperties(db);
//        
//        this.resetOwnerProperties(objectsToRE[0]);
//        
//        copyAttribute(db,this.database);
//bgp>    }

	protected void handleDatabaseVendors()
	{
        if (database.getVendor().equals("Derby")) { //$NON-NLS-1$
        	this.buildVertex(objectsToRE);        	
        }
        else {
	   		this.cloneVertex(objectsToRE); 		
        }

        this.refresh(objectsToRE);     
	}
	
	public void buildVertex(EObject[] objsToRE) {
		EObject[] targets = new EObject[objsToRE.length];
		for (int i = 0; i< objsToRE.length; i++){
			targets[i] = this.database;
		}
		
		EObject[] cloned = this.buildVertex(targets,objsToRE);

		for (int i = 0; i < cloned.length; i++) {
			if ((cloned[i] instanceof Database)) {
				Database db = (Database)cloned[i];

				if ((this.options & EXCLUDE_ACCESS_CONTROL)== EXCLUDE_ACCESS_CONTROL) {
					Iterator it = db.getAuthorizationIds().iterator();
					while(it.hasNext()) {
						AuthorizationIdentifier aId = (AuthorizationIdentifier) it.next();

						aId.getReceivedPrivilege().clear();
						aId.getReceivedRoleAuthorization().clear();
					}

					db.getAuthorizationIds().clear();
				}
			}
			else if ((cloned[i] instanceof Schema)) {
				Schema schema = (Schema)cloned[i];

				if ( (this.options & (EXCLUDE_TRIGGERS|EXCLUDE_INDEXES)) != 0 ) {
					Iterator it = schema.getTables().iterator();
					while(it.hasNext()) {
						Table t = (Table) it.next();

						if ((this.options & EXCLUDE_TRIGGERS)== EXCLUDE_TRIGGERS) {
							t.getTriggers().clear();
						}

						if ((this.options & EXCLUDE_INDEXES)== EXCLUDE_INDEXES) {
							t.getIndex().clear();
						}
					}
				}
			}
		}
	}
	
	protected EObject[] buildVertex(EObject[] parent, EObject[] object){
        EObject[] newObjects = CloneUtil.clone(parent, object);
        
        return newObjects;
	}

//<bgp	protected void generateExplicitRelationship(Database db) {
//		ImplicitRelationshipHandler handler = ImplicitRelationshipHandler.INSTANCE;
//		handler.setColumnNamesToIgnore(columnNamesToIgnore);
//
//		Iterator it = db.getSchemas().iterator();
//		while(it.hasNext()) {
//			Schema s = (Schema) it.next();
//
//			handler.FindAndCreate(s);
//		}
//
//		// Clear out columnNamesToIgnore just to be sure
//		handler.setColumnNamesToIgnore(null);
//bgp>	}

	protected void generateImplicitPK(Database db) {
		CatalogLoadUtil.generateImplicitPK(db);
	}
	
	protected void cloneVertex(EObject[] objsToRE) {
		EObject[] targets = new EObject[objsToRE.length];
		EObject[] newObjects = new EObject[1];
		
		for (int i = 0; i< objsToRE.length; i++){
			targets[i] = this.database;
		}
		
		if(!isOwnerForRE){      
			newObjects = CloneUtil.clone(targets, objsToRE);
		}
		
		DBReverseProvider reProvider = getDBReverseProvider(database);

		if (reProvider != null) {
    		reProvider.reverseEngineer(database, options, objsToRE, monitor);

    		//fix for wsdbu00679720, for some specific vendors eg. z/os,remove the needless object in the specific handler
    		if(newObjects != null){
    			reProvider.removeNeedlessObjects(newObjects,options);
    		}
    	}
		else {
    		//fix for wsdbu00641207, remove the needless object from the model
    		if (newObjects != null) {
    			removeNeedlessObjects(newObjects);	
    		}
    	}		
	}	
	
	protected void removeNeedlessObjects(EObject[] clonedObjects)
	{
		for (int i = 0; i < clonedObjects.length; i++) {
			if ((clonedObjects[i] instanceof Database)) {
				Database db = (Database) clonedObjects[i];

				if ((this.options & EXCLUDE_ACCESS_CONTROL) == EXCLUDE_ACCESS_CONTROL) {
					Iterator it = db.getAuthorizationIds().iterator();
					while (it.hasNext()) {
						AuthorizationIdentifier aId = (AuthorizationIdentifier)it.next();

						aId.getReceivedPrivilege().clear();
						aId.getReceivedRoleAuthorization().clear();
					}

					db.getAuthorizationIds().clear();
				}
			}
			else if ((clonedObjects[i] instanceof Schema)) {
				Schema schema = (Schema) clonedObjects[i];

				if ((this.options & EXCLUDE_ROUTINES) == EXCLUDE_ROUTINES) {
					schema.getRoutines().clear();
				}

				if ((this.options & EXCLUDE_USER_DEFINED_TYPE) == EXCLUDE_USER_DEFINED_TYPE) {
					schema.getUserDefinedTypes().clear();
				}

				if ((this.options & EXCLUDE_SEQUENCE) == EXCLUDE_SEQUENCE) {
					schema.getSequences().clear();
				}

				if ( (this.options & EXCLUDE_TABLES) == EXCLUDE_TABLES
						|| (this.options & EXCLUDE_VIEWS) == EXCLUDE_VIEWS
						|| (this.options & EXCLUDE_SYNONYMS) == EXCLUDE_SYNONYMS)
				{
					List<Table> needRemovedTables = new ArrayList<Table>();

					Iterator it = schema.getTables().iterator();
					while (it.hasNext()) {
						Table t = (Table) it.next();

						// TODO GLD odd that we don't actually look for synonyms?
						if ((this.options & EXCLUDE_TABLES) == EXCLUDE_TABLES) {
							if (t instanceof BaseTable) {
								needRemovedTables.add(t);
							}
						}
						else if ((this.options & EXCLUDE_VIEWS) == EXCLUDE_VIEWS) {
							if (t instanceof ViewTable) {
								needRemovedTables.add(t);
							}
						}
					}

					schema.getTables().removeAll( needRemovedTables );
				}

				if ((this.options & EXCLUDE_TABLES) != EXCLUDE_TABLES
						&& ((this.options & EXCLUDE_TRIGGERS) == EXCLUDE_TRIGGERS //
							|| (this.options & EXCLUDE_INDEXES) == EXCLUDE_INDEXES))
				{
					Iterator it = schema.getTables().iterator();
					while (it.hasNext()) {
						Table t = (Table) it.next();
		
						if ((this.options & EXCLUDE_TRIGGERS) == EXCLUDE_TRIGGERS) {
							t.getTriggers().clear();
						}

						if ((this.options & EXCLUDE_INDEXES) == EXCLUDE_INDEXES) {
							t.getIndex().clear();
						}
					}
				}
			}
		}
	}

	protected void refresh(EObject[] objsToRE)
	{
		for (int i = 0; i < objsToRE.length; i++) {
			EObject obj = objsToRE[i];

			if (obj instanceof ICatalogObject)
				((ICatalogObject) obj).refresh();
		}
	}

	protected void setLoadProperties(EObject db){
		if (!(db instanceof SQLObject))
			return;

		EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		eAnnotation.setSource(LOAD_PROPERTY);
		eAnnotation.getDetails().put(IS_BATCH_LOAD, Boolean.TRUE.toString());
		eAnnotation.getDetails().put(LOAD_OPTIONS, Integer.toString( this.options ));

		((SQLObject)db).getEAnnotations().add(eAnnotation);
	}

	protected void resetLoadProperties(EObject db)
	{
		if ( !(db instanceof SQLObject) )
			return;

		EList eAnnotations = ((SQLObject)db).getEAnnotations();
        for (Iterator iter = eAnnotations.iterator(); iter.hasNext();){
			EAnnotation eAnnotation = (EAnnotation)iter.next();

			String source = eAnnotation.getSource();

			if (source != null && source.equals(LOAD_PROPERTY)) {
	        	eAnnotations.remove( eAnnotation);
	        	
	        	return;
			}
		}
	}

	protected void resetOwnerProperties(EObject ownerObject)
	{
		if ( !(ownerObject instanceof SQLObject) )
			return;

		EList eAnnotations = ((SQLObject)ownerObject).getEAnnotations();
        for (Iterator iter = eAnnotations.iterator(); iter.hasNext();){
			EAnnotation eAnnotation = (EAnnotation)iter.next();

			String source = eAnnotation.getSource();

			if (source != null && source.equals(OWNER_LOAD_OPTION)) {
	        	eAnnotations.remove( eAnnotation);
	        	return;
			}
		}
	}
	
	private static void copyAttribute(EObject src,EObject target)
	{
		EClass c = src.eClass();

		Iterator it = c.getEAllAttributes().iterator();
		while(it.hasNext()) {
			EAttribute a = (EAttribute) it.next();

			if (a.isChangeable() && !a.isDerived())
				copyAttribute(a, src, target);
		}
	}
	
	private static void copyAttribute(EAttribute attribute, EObject src, EObject target)
	{
        if ( !src.eIsSet(attribute)) {
        	return;
        }
        
        if ( FeatureMapUtil.isFeatureMap(attribute) )
        {
            FeatureMap srcFeatureMap = (FeatureMap)src.eGet(attribute);
            FeatureMap tgtFeatureMap = (FeatureMap)src.eGet(attribute);

            Iterator i = srcFeatureMap.iterator();
            while(i.hasNext()) {
            	FeatureMap.Entry entry = (FeatureMap.Entry)i.next();

            	EStructuralFeature feature = entry.getEStructuralFeature();

            	if ( !(feature instanceof EReference) ) {
            		tgtFeatureMap.add(entry);
            	}
            }
        }
 		else if(attribute.isMany()) {
 			((Collection)target.eGet(attribute)).addAll( (Collection)src.eGet(attribute) );
        }
        else {
        	target.eSet(attribute, src.eGet(attribute));
        }
	}
	
	protected DBReverseProvider getDBReverseProvider(Database db)
	{
		if ( this.provider != null)
			return this.provider;
	
		String dbProduct = db.getVendor();

		IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core","dbReverseProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		IExtension[] extensions = extensionPoint.getExtensions();

		for(int i=0; i<extensions.length; ++i) {
			IConfigurationElement[] configElements = extensions[i].getConfigurationElements();

			for ( int j = 0; j < configElements.length; ++j ) {
				if ( configElements[j].getName().equals("provider") ) { //$NON-NLS-1$
					String product = configElements[j].getAttribute("product"); //$NON-NLS-1$

					if ( !product.equals(dbProduct) )
						continue;

					String version = configElements[j].getAttribute("version"); //$NON-NLS-1$

					try {
						this.provider = (DBReverseProvider)configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
					}
					catch(CoreException e) {
						IStatus status = new Status( IStatus.ERROR, //
								RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR, //
								"The error was detected when creating the db reverse provider for " //$NON-NLS-1$
										+ product + " " + version, e ); //$NON-NLS-1$

						DB2PluginActivator.getInstance().getLog().log(status);
					}

					break;
				}
			}
		}
		
		return this.provider;
	}

	protected Database database = null;
	protected int options;
	protected List<String> columnNamesToIgnore;
	protected IProgressMonitor monitor;
	protected EObject[] objectsToRE;
 	protected DBReverseProvider provider;
}
