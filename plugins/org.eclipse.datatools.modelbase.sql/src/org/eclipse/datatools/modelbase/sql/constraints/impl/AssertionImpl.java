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
package org.eclipse.datatools.modelbase.sql.constraints.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.constraints.Assertion;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assertion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.AssertionImpl#getSearchCondition <em>Search Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.AssertionImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.constraints.impl.AssertionImpl#getConstrainedTables <em>Constrained Tables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssertionImpl extends ConstraintImpl implements Assertion {
	/**
	 * The cached value of the '{@link #getSearchCondition() <em>Search Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSearchCondition()
	 * @generated
	 * @ordered
	 */
	protected SearchCondition searchCondition;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected Schema schema;

	/**
	 * The cached value of the '{@link #getConstrainedTables() <em>Constrained Tables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstrainedTables()
	 * @generated
	 * @ordered
	 */
	protected EList constrainedTables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssertionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLConstraintsPackage.Literals.ASSERTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SearchCondition getSearchCondition() {
		return searchCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSearchCondition(SearchCondition newSearchCondition, NotificationChain msgs) {
		SearchCondition oldSearchCondition = searchCondition;
		searchCondition = newSearchCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION, oldSearchCondition, newSearchCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSearchCondition(SearchCondition newSearchCondition) {
		if (newSearchCondition != searchCondition) {
			NotificationChain msgs = null;
			if (searchCondition != null)
				msgs = ((InternalEObject)searchCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION, null, msgs);
			if (newSearchCondition != null)
				msgs = ((InternalEObject)newSearchCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION, null, msgs);
			msgs = basicSetSearchCondition(newSearchCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION, newSearchCondition, newSearchCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SQLConstraintsPackage.ASSERTION__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(Schema newSchema, NotificationChain msgs) {
		Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.ASSERTION__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__ASSERTIONS, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__ASSERTIONS, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLConstraintsPackage.ASSERTION__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getConstrainedTables() {
		if (constrainedTables == null) {
			constrainedTables = new EObjectResolvingEList(BaseTable.class, this, SQLConstraintsPackage.ASSERTION__CONSTRAINED_TABLES);
		}
		return constrainedTables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLConstraintsPackage.ASSERTION__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__ASSERTIONS, Schema.class, msgs);
				return basicSetSchema((Schema)otherEnd, msgs);
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
			case SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION:
				return basicSetSearchCondition(null, msgs);
			case SQLConstraintsPackage.ASSERTION__SCHEMA:
				return basicSetSchema(null, msgs);
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
			case SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION:
				return getSearchCondition();
			case SQLConstraintsPackage.ASSERTION__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case SQLConstraintsPackage.ASSERTION__CONSTRAINED_TABLES:
				return getConstrainedTables();
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
			case SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION:
				setSearchCondition((SearchCondition)newValue);
				return;
			case SQLConstraintsPackage.ASSERTION__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case SQLConstraintsPackage.ASSERTION__CONSTRAINED_TABLES:
				getConstrainedTables().clear();
				getConstrainedTables().addAll((Collection)newValue);
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
			case SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION:
				setSearchCondition((SearchCondition)null);
				return;
			case SQLConstraintsPackage.ASSERTION__SCHEMA:
				setSchema((Schema)null);
				return;
			case SQLConstraintsPackage.ASSERTION__CONSTRAINED_TABLES:
				getConstrainedTables().clear();
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
			case SQLConstraintsPackage.ASSERTION__SEARCH_CONDITION:
				return searchCondition != null;
			case SQLConstraintsPackage.ASSERTION__SCHEMA:
				return schema != null;
			case SQLConstraintsPackage.ASSERTION__CONSTRAINED_TABLES:
				return constrainedTables != null && !constrainedTables.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AssertionImpl
