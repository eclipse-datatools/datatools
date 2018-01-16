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
	protected IdentitySpecifier identitySpecifier;

/**
	 * The cached value of the '{@link #getGenerateExpression() <em>Generate Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerateExpression()
	 * @generated
	 * @ordered
	 */
	protected ValueExpression generateExpression;

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
		return SQLTablesPackage.Literals.COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table getTable() {
		if (eContainerFeatureID != SQLTablesPackage.COLUMN__TABLE) return null;
		return (Table)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(Table newTable, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTable, SQLTablesPackage.COLUMN__TABLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(Table newTable) {
		if (newTable != eInternalContainer() || (eContainerFeatureID != SQLTablesPackage.COLUMN__TABLE && newTable != null)) {
			if (EcoreUtil.isAncestor(this, newTable))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTable != null)
				msgs = ((InternalEObject)newTable).eInverseAdd(this, SQLTablesPackage.TABLE__COLUMNS, Table.class, msgs);
			msgs = basicSetTable(newTable, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SQLTablesPackage.COLUMN__TABLE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTable((Table)otherEnd, msgs);
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
			case SQLTablesPackage.COLUMN__TABLE:
				return basicSetTable(null, msgs);
			case SQLTablesPackage.COLUMN__IDENTITY_SPECIFIER:
				return basicSetIdentitySpecifier(null, msgs);
			case SQLTablesPackage.COLUMN__GENERATE_EXPRESSION:
				return basicSetGenerateExpression(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case SQLTablesPackage.COLUMN__TABLE:
				return eInternalContainer().eInverseRemove(this, SQLTablesPackage.TABLE__COLUMNS, Table.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
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
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
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
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
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
		return super.eIsSet(featureID);
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
