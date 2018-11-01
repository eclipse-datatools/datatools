 /*******************************************************************************
  * Copyright (c) 2005, 2009 Versant Corporation and others.
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the Eclipse Public License 2.0
  * which accompanies this distribution, and is available at
  * https://www.eclipse.org/legal/epl-2.0/
  * 
  * Contributors:
  *     Versant Corporation - initial API and implementation
  *     Sybase, Inc. - updates to make catalog loaders work with filtering
  *******************************************************************************/
 package org.eclipse.datatools.enablement.mysql.catalog;

import java.sql.Connection;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCForeignKey;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * This class is the ForeignKey implementation
 */
public class MySqlCatalogForeignKey extends JDBCForeignKey {

	private static final long serialVersionUID = 3833460717268643894L;

	private boolean eAnnotationLoaded = false;

	public void refresh() {
	}

	public boolean isSystemObject() {
		return false;
	}

	public EList getEAnnotations() {
		if (!this.eAnnotationLoaded)
			this.loadEAnnotations();
		return this.eAnnotations;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((MySqlCatalogDatabase) database).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		int id = eDerivedStructuralFeatureID(eFeature);
		if (id == SQLConstraintsPackage.FOREIGN_KEY__EANNOTATIONS) {
			this.getEAnnotations();
		}

		return super.eIsSet(eFeature);
	}

	private synchronized void loadEAnnotations() {
		if (this.eAnnotationLoaded)
			return;
		this.eAnnotationLoaded = true;
		super.getEAnnotations();

		boolean deliver = this.eDeliver();
		this.eSetDeliver(false);

		MySqlCatalogForeignKey.setAsIdentifyingRelatinship(this, this
				.isIdentifyingRelationship(super.getMembers()));

		this.eSetDeliver(deliver);
	}

	public static void setAsIdentifyingRelatinship(ForeignKey fk,
			boolean identifying) {
		EAnnotation eAnnotation = fk
				.addEAnnotation(RDBCorePlugin.FK_MODELING_RELATIONSHIP);
		fk.addEAnnotationDetail(eAnnotation,
				RDBCorePlugin.FK_IS_IDENTIFYING_RELATIONSHIP, new Boolean(
						identifying).toString());
		fk.addEAnnotationDetail(eAnnotation,
				RDBCorePlugin.FK_CHILD_MULTIPLICITY, RDBCorePlugin.MANY);
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_CHILD_ROLE_NAME,
				new String());
		fk.addEAnnotationDetail(eAnnotation,
				RDBCorePlugin.FK_PARENT_MULTIPLICITY,
				(fk.getMembers().size() > 0) ? RDBCorePlugin.ZERO_TO_ONE
						: RDBCorePlugin.ONE);
		fk.addEAnnotationDetail(eAnnotation, RDBCorePlugin.FK_PARENT_ROLE_NAME,
				new String());
	}

	public boolean isIdentifyingRelationship(EList columns) {
		boolean isIdentifying = true;
		Iterator it = columns.iterator();
		while (it.hasNext()) {
			Column column = (Column) it.next();
			if (!column.isPartOfPrimaryKey()) {
				isIdentifying = false;
				break;
			}
		}
		return isIdentifying;
	}

}
