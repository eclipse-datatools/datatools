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

import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.expressions.ValueExpression;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.schema.impl.TypedElementImpl;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl#getIdentitySpecifier <em>Identity Specifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl#getGenerateExpression <em>Generate Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl#isImplementationDependent <em>Implementation Dependent</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl#getScopeCheck <em>Scope Check</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.tables.impl.ColumnImpl#isScopeChecked <em>Scope Checked</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnImpl extends TypedElementImpl implements Column {
	/**
	 * The cached value of the '{@link #getIdentitySpecifier() <em>Identity Specifier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentitySpecifier()
	 * @generated
	 * @ordered
	 */
	protected IdentitySpecifier identitySpecifier = null;

	/**
	 * The cached value of the '{@link #getGenerateExpression() <em>Generate Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerateExpression()
	 * @generated
	 * @ordered
	 */
	protected ValueExpression generateExpression = null;

	/**
	 * The default value of the '{@link #isImplementationDependent() <em>Implementation Dependent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplementationDependent()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMPLEMENTATION_DEPENDENT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isImplementationDependent() <em>Implementation Dependent</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplementationDependent()
	 * @generated
	 * @ordered
	 */
	protected boolean implementationDependent = IMPLEMENTATION_DEPENDENT_EDEFAULT;

	/**
	 * The default value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NULLABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isNullable() <em>Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullable()
	 * @generated
	 * @ordered
	 */
	protected boolean nullable = NULLABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultValue() <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValue()
	 * @generated
	 * @ordered
	 */
	protected String defaultValue = DEFAULT_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getScopeCheck() <em>Scope Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeCheck()
	 * @generated
	 * @ordered
	 */
	protected static final ReferentialActionType SCOPE_CHECK_EDEFAULT = ReferentialActionType.NO_ACTION_LITERAL;

	/**
	 * The cached value of the '{@link #getScopeCheck() <em>Scope Check</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopeCheck()
	 * @generated
	 * @ordered
	 */
	protected ReferentialActionType scopeCheck = SCOPE_CHECK_EDEFAULT;

	/**
	 * The default value of the '{@link #isScopeChecked() <em>Scope Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScopeChecked()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCOPE_CHECKED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isScopeChecked() <em>Scope Checked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScopeChecked()
	 * @generated
	 * @ordered
	 */
	protected boolean scopeChecked = SCOPE_CHECKED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return SQLTablesPackage.eINSTANCE.getColumn();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getTable() {
		if (eContainerFeatureID != SQLTablesPackage.COLUMN__TABLE) return null;
		return (Table)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(Table newTable) {
		if (newTable != eContainer || (eContainerFeatureID != SQLTablesPackage.COLUMN__TABLE && newTable != null)) {
			if (EcoreUtil.isAncestor(this, newTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, SQLTablesPackage.TABLE__COLUMNS, Table.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newTable, SQLTablesPackage.COLUMN__TABLE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__TABLE, newTable, newTable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentitySpecifier getIdentitySpecifier() {
		return identitySpecifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIdentitySpecifier(IdentitySpecifier newIdentitySpecifier, NotificationChain msgs) {
		IdentitySpecifier oldIdentitySpecifier = identitySpecifier;
		identitySpecifier = newIdentitySpecifier;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER, oldIdentitySpecifier, newIdentitySpecifier);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentitySpecifier(IdentitySpecifier newIdentitySpecifier) {
		if (newIdentitySpecifier != identitySpecifier) {
			NotificationChain msgs = null;
			if (identitySpecifier != null)
				msgs = ((InternalEObject)identitySpecifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER, null, msgs);
			if (newIdentitySpecifier != null)
				msgs = ((InternalEObject)newIdentitySpecifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER, null, msgs);
			msgs = basicSetIdentitySpecifier(newIdentitySpecifier, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER, newIdentitySpecifier, newIdentitySpecifier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueExpression getGenerateExpression() {
		return generateExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGenerateExpression(ValueExpression newGenerateExpression, NotificationChain msgs) {
		ValueExpression oldGenerateExpression = generateExpression;
		generateExpression = newGenerateExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__GENERATE_EXPRESSION, oldGenerateExpression, newGenerateExpression);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenerateExpression(ValueExpression newGenerateExpression) {
		if (newGenerateExpression != generateExpression) {
			NotificationChain msgs = null;
			if (generateExpression != null)
				msgs = ((InternalEObject)generateExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLTablesPackage.COLUMN__GENERATE_EXPRESSION, null, msgs);
			if (newGenerateExpression != null)
				msgs = ((InternalEObject)newGenerateExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLTablesPackage.COLUMN__GENERATE_EXPRESSION, null, msgs);
			msgs = basicSetGenerateExpression(newGenerateExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__GENERATE_EXPRESSION, newGenerateExpression, newGenerateExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImplementationDependent() {
		return implementationDependent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementationDependent(boolean newImplementationDependent) {
		boolean oldImplementationDependent = implementationDependent;
		implementationDependent = newImplementationDependent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__IMPLEMENTATION_DEPENDENT, oldImplementationDependent, implementationDependent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullable(boolean newNullable) {
		boolean oldNullable = nullable;
		nullable = newNullable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__NULLABLE, oldNullable, nullable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultValue(String newDefaultValue) {
		String oldDefaultValue = defaultValue;
		defaultValue = newDefaultValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__DEFAULT_VALUE, oldDefaultValue, defaultValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferentialActionType getScopeCheck() {
		return scopeCheck;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScopeCheck(ReferentialActionType newScopeCheck) {
		ReferentialActionType oldScopeCheck = scopeCheck;
		scopeCheck = newScopeCheck == null ? SCOPE_CHECK_EDEFAULT : newScopeCheck;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__SCOPE_CHECK, oldScopeCheck, scopeCheck));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isScopeChecked() {
		return scopeChecked;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScopeChecked(boolean newScopeChecked) {
		boolean oldScopeChecked = scopeChecked;
		scopeChecked = newScopeChecked;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLTablesPackage.COLUMN__SCOPE_CHECKED, oldScopeChecked, scopeChecked));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isPartOfForeignKey() {
		Table table = this.getTable();
		if ( (table != null) && (table instanceof BaseTable) ) {
			Iterator foreignKeys = ((BaseTable)table).getForeignKeys().iterator();
			while( foreignKeys.hasNext() ) {
				ForeignKey currentForeignKey = (ForeignKey)foreignKeys.next();
				if (currentForeignKey != null) {
					EList columns = currentForeignKey.getMembers();
					if ( (columns != null) && columns.contains(this) ) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isPartOfUniqueConstraint() {
		Table table = this.getTable();
		if ( (table != null) && (table instanceof BaseTable) ) {
			Iterator uniqueConstraint = ((BaseTable)table).getUniqueConstraints().iterator();
			while( uniqueConstraint.hasNext() ) {
				UniqueConstraint currentUniqueConstraint = (UniqueConstraint)uniqueConstraint.next();
				if (currentUniqueConstraint != null) {
					EList columns = currentUniqueConstraint.getMembers();
					if ( (columns != null) && columns.contains(this) ) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean isPartOfPrimaryKey() {
		Table table = this.getTable();
		if ( (table != null) && (table instanceof BaseTable) ) {
			PrimaryKey primaryKey = ((BaseTable)table).getPrimaryKey();
			if (primaryKey != null) {
				EList columns = primaryKey.getMembers();
				if ( (columns != null) && columns.contains(this) ) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLTablesPackage.COLUMN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLTablesPackage.COLUMN__TABLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLTablesPackage.COLUMN__TABLE, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLTablesPackage.COLUMN__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.COLUMN__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLTablesPackage.COLUMN__CONTAINED_TYPE:
					return basicSetContainedType(null, msgs);
				case SQLTablesPackage.COLUMN__TABLE:
					return eBasicSetContainer(null, SQLTablesPackage.COLUMN__TABLE, msgs);
				case SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER:
					return basicSetIdentitySpecifier(null, msgs);
				case SQLTablesPackage.COLUMN__GENERATE_EXPRESSION:
					return basicSetGenerateExpression(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case SQLTablesPackage.COLUMN__TABLE:
					return eContainer.eInverseRemove(this, SQLTablesPackage.TABLE__COLUMNS, Table.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLTablesPackage.COLUMN__EANNOTATIONS:
				return getEAnnotations();
			case SQLTablesPackage.COLUMN__NAME:
				return getName();
			case SQLTablesPackage.COLUMN__DEPENDENCIES:
				return getDependencies();
			case SQLTablesPackage.COLUMN__DESCRIPTION:
				return getDescription();
			case SQLTablesPackage.COLUMN__LABEL:
				return getLabel();
			case SQLTablesPackage.COLUMN__CONTAINED_TYPE:
				return getContainedType();
			case SQLTablesPackage.COLUMN__REFERENCED_TYPE:
				if (resolve) return getReferencedType();
				return basicGetReferencedType();
			case SQLTablesPackage.COLUMN__TABLE:
				return getTable();
			case SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER:
				return getIdentitySpecifier();
			case SQLTablesPackage.COLUMN__GENERATE_EXPRESSION:
				return getGenerateExpression();
			case SQLTablesPackage.COLUMN__IMPLEMENTATION_DEPENDENT:
				return isImplementationDependent() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.COLUMN__NULLABLE:
				return isNullable() ? Boolean.TRUE : Boolean.FALSE;
			case SQLTablesPackage.COLUMN__DEFAULT_VALUE:
				return getDefaultValue();
			case SQLTablesPackage.COLUMN__SCOPE_CHECK:
				return getScopeCheck();
			case SQLTablesPackage.COLUMN__SCOPE_CHECKED:
				return isScopeChecked() ? Boolean.TRUE : Boolean.FALSE;
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLTablesPackage.COLUMN__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.COLUMN__NAME:
				setName((String)newValue);
				return;
			case SQLTablesPackage.COLUMN__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLTablesPackage.COLUMN__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLTablesPackage.COLUMN__LABEL:
				setLabel((String)newValue);
				return;
			case SQLTablesPackage.COLUMN__CONTAINED_TYPE:
				setContainedType((SQLDataType)newValue);
				return;
			case SQLTablesPackage.COLUMN__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)newValue);
				return;
			case SQLTablesPackage.COLUMN__TABLE:
				setTable((Table)newValue);
				return;
			case SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER:
				setIdentitySpecifier((IdentitySpecifier)newValue);
				return;
			case SQLTablesPackage.COLUMN__GENERATE_EXPRESSION:
				setGenerateExpression((ValueExpression)newValue);
				return;
			case SQLTablesPackage.COLUMN__IMPLEMENTATION_DEPENDENT:
				setImplementationDependent(((Boolean)newValue).booleanValue());
				return;
			case SQLTablesPackage.COLUMN__NULLABLE:
				setNullable(((Boolean)newValue).booleanValue());
				return;
			case SQLTablesPackage.COLUMN__DEFAULT_VALUE:
				setDefaultValue((String)newValue);
				return;
			case SQLTablesPackage.COLUMN__SCOPE_CHECK:
				setScopeCheck((ReferentialActionType)newValue);
				return;
			case SQLTablesPackage.COLUMN__SCOPE_CHECKED:
				setScopeChecked(((Boolean)newValue).booleanValue());
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLTablesPackage.COLUMN__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLTablesPackage.COLUMN__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLTablesPackage.COLUMN__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLTablesPackage.COLUMN__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLTablesPackage.COLUMN__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLTablesPackage.COLUMN__CONTAINED_TYPE:
				setContainedType((SQLDataType)null);
				return;
			case SQLTablesPackage.COLUMN__REFERENCED_TYPE:
				setReferencedType((UserDefinedType)null);
				return;
			case SQLTablesPackage.COLUMN__TABLE:
				setTable((Table)null);
				return;
			case SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER:
				setIdentitySpecifier((IdentitySpecifier)null);
				return;
			case SQLTablesPackage.COLUMN__GENERATE_EXPRESSION:
				setGenerateExpression((ValueExpression)null);
				return;
			case SQLTablesPackage.COLUMN__IMPLEMENTATION_DEPENDENT:
				setImplementationDependent(IMPLEMENTATION_DEPENDENT_EDEFAULT);
				return;
			case SQLTablesPackage.COLUMN__NULLABLE:
				setNullable(NULLABLE_EDEFAULT);
				return;
			case SQLTablesPackage.COLUMN__DEFAULT_VALUE:
				setDefaultValue(DEFAULT_VALUE_EDEFAULT);
				return;
			case SQLTablesPackage.COLUMN__SCOPE_CHECK:
				setScopeCheck(SCOPE_CHECK_EDEFAULT);
				return;
			case SQLTablesPackage.COLUMN__SCOPE_CHECKED:
				setScopeChecked(SCOPE_CHECKED_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLTablesPackage.COLUMN__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLTablesPackage.COLUMN__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLTablesPackage.COLUMN__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLTablesPackage.COLUMN__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLTablesPackage.COLUMN__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLTablesPackage.COLUMN__CONTAINED_TYPE:
				return containedType != null;
			case SQLTablesPackage.COLUMN__REFERENCED_TYPE:
				return referencedType != null;
			case SQLTablesPackage.COLUMN__TABLE:
				return getTable() != null;
			case SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER:
				return identitySpecifier != null;
			case SQLTablesPackage.COLUMN__GENERATE_EXPRESSION:
				return generateExpression != null;
			case SQLTablesPackage.COLUMN__IMPLEMENTATION_DEPENDENT:
				return implementationDependent != IMPLEMENTATION_DEPENDENT_EDEFAULT;
			case SQLTablesPackage.COLUMN__NULLABLE:
				return nullable != NULLABLE_EDEFAULT;
			case SQLTablesPackage.COLUMN__DEFAULT_VALUE:
				return DEFAULT_VALUE_EDEFAULT == null ? defaultValue != null : !DEFAULT_VALUE_EDEFAULT.equals(defaultValue);
			case SQLTablesPackage.COLUMN__SCOPE_CHECK:
				return scopeCheck != SCOPE_CHECK_EDEFAULT;
			case SQLTablesPackage.COLUMN__SCOPE_CHECKED:
				return scopeChecked != SCOPE_CHECKED_EDEFAULT;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (implementationDependent: "); //$NON-NLS-1$
		result.append(implementationDependent);
		result.append(", nullable: "); //$NON-NLS-1$
		result.append(nullable);
		result.append(", defaultValue: "); //$NON-NLS-1$
		result.append(defaultValue);
		result.append(", scopeCheck: "); //$NON-NLS-1$
		result.append(scopeCheck);
		result.append(", scopeChecked: "); //$NON-NLS-1$
		result.append(scopeChecked);
		result.append(')');
		return result.toString();
	}

} //ColumnImpl
