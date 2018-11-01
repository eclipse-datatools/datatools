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

package org.eclipse.datatools.connectivity.sqm.core.rte.jdbc;

import java.sql.Connection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.ForeignKeyImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EStructuralFeature;


public class JDBCForeignKey extends ForeignKeyImpl implements ICatalogObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4142241344656964532L;


	public void refresh() {
		this.eAnnotationLoaded = false;
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public EList getEAnnotations() {
		if(!this.eAnnotationLoaded) this.loadEAnnotations();
		return this.eAnnotations;
	}
	
	public Database getCatalogDatabase() {
		return getBaseTable().getSchema().getCatalog().getDatabase();
	}

	public Connection getConnection() {
		Database db = getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if(id == SQLConstraintsPackage.FOREIGN_KEY__EANNOTATIONS) {
			this.getEAnnotations();
		}
		
		return super.eIsSet(eFeature);
	}

	private synchronized void loadEAnnotations() {
		if(this.eAnnotationLoaded) return;
		EList memberList = super.getEAnnotations();
		
		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);	
		this.eAnnotationLoaded = true;
		
		JDBCForeignKey.setAsIdentifyingRelatinship(this,this.isIdentifyingRelationship(super.getMembers()));
		
		this.eSetDeliver(deliver);		
	}
	
	public static void setAsIdentifyingRelatinship(ForeignKey fk,boolean identifying){
		EAnnotation eAnnotation = fk.addEAnnotation(RDBCorePlugin.FK_MODELING_RELATIONSHIP);
		fk.addEAnnotationDetail(eAnnotation,RDBCorePlugin.FK_IS_IDENTIFYING_RELATIONSHIP,new Boolean(identifying).toString());

		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_CHILD_MULTIPLICITY, RDBCorePlugin.MANY);
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_CHILD_ROLE_NAME, new String ());
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_PARENT_MULTIPLICITY, (fk.getMembers().size() > 0) ? RDBCorePlugin.ZERO_TO_ONE : RDBCorePlugin.ONE);
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_PARENT_ROLE_NAME, new String ());
	}

	public boolean isIdentifyingRelationship(EList columns){
		boolean isIdentifying = true;
		Iterator it = columns.iterator();
		while(it.hasNext()) {
			Column column = (Column) it.next();
			if(!column.isPartOfPrimaryKey()){
				isIdentifying = false;
				break;
			}
		}
		return isIdentifying;
	}
	
	
	private boolean eAnnotationLoaded=false;
	

}
