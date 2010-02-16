/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.tables.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.ReferenceType;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Base Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.BaseTableImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.BaseTableImpl#getReferencingForeignKeys <em>Referencing Foreign Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BaseTableImpl extends TableImpl implements BaseTable {
	/**
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList constraints;

	/**
	 * The cached value of the '{@link #getReferencingForeignKeys() <em>Referencing Foreign Keys</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencingForeignKeys()
	 * @generated
	 * @ordered
	 */
	protected EList referencingForeignKeys;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BaseTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLTablesPackage.Literals.BASE_TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentWithInverseEList(TableConstraint.class, this, SQLTablesPackage.BASE_TABLE__CONSTRAINTS, SQLConstraintsPackage.TABLE_CONSTRAINT__BASE_TABLE);
		}
		return constraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReferencingForeignKeys() {
		if (referencingForeignKeys == null) {
			referencingForeignKeys = new EObjectWithInverseResolvingEList(ForeignKey.class, this, SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS, SQLConstraintsPackage.FOREIGN_KEY__REFERENCED_TABLE);
		}
		return referencingForeignKeys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getUniqueConstraints() {
		Vector uniqueConstraints = new Vector();
		Iterator allConstraints = this.getConstraints().iterator();
		while( allConstraints.hasNext() ) {
			Constraint currentConstraint = (Constraint)allConstraints.next();
			if (currentConstraint instanceof UniqueConstraint) {
				uniqueConstraints.add(currentConstraint);
			}
		}
		
		return uniqueConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getForeignKeys() {
		Vector foreignKeys = new Vector();
		Iterator allConstraints = this.getConstraints().iterator();
		while( allConstraints.hasNext() ) {
			Constraint currentConstraint = (Constraint)allConstraints.next();
			if (currentConstraint instanceof ForeignKey) {
				foreignKeys.add(currentConstraint);
			}
		}
		
		return foreignKeys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public PrimaryKey getPrimaryKey() {
		Iterator allConstraints = this.getConstraints().iterator();
		while( allConstraints.hasNext() ) {
			Constraint currentConstraint = (Constraint)allConstraints.next();
			if (currentConstraint instanceof PrimaryKey) {
				return (PrimaryKey)currentConstraint;
			}
		}
		
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLTablesPackage.BASE_TABLE__CONSTRAINTS:
				return ((InternalEList)getConstraints()).basicAdd(otherEnd, msgs);
			case SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS:
				return ((InternalEList)getReferencingForeignKeys()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLTablesPackage.BASE_TABLE__CONSTRAINTS:
				return ((InternalEList)getConstraints()).basicRemove(otherEnd, msgs);
			case SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS:
				return ((InternalEList)getReferencingForeignKeys()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SQLTablesPackage.BASE_TABLE__CONSTRAINTS:
				return getConstraints();
			case SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS:
				return getReferencingForeignKeys();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SQLTablesPackage.BASE_TABLE__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS:
				getReferencingForeignKeys().clear();
				getReferencingForeignKeys().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case SQLTablesPackage.BASE_TABLE__CONSTRAINTS:
				getConstraints().clear();
				return;
			case SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS:
				getReferencingForeignKeys().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SQLTablesPackage.BASE_TABLE__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
			case SQLTablesPackage.BASE_TABLE__REFERENCING_FOREIGN_KEYS:
				return referencingForeignKeys != null && !referencingForeignKeys.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BaseTableImpl
