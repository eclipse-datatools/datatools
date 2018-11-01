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
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.CheckOption;
import org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType;
import org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#isDeferrableConstraintSupported <em>Deferrable Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#isInformationalConstraintSupported <em>Informational Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#isClusteredPrimaryKeySupported <em>Clustered Primary Key Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#isClusteredUniqueConstraintSupported <em>Clustered Unique Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#isPrimaryKeyNullable <em>Primary Key Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#isUniqueKeyNullable <em>Unique Key Nullable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#getMaximumCheckExpressionLength <em>Maximum Check Expression Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#getParentUpdateDRIRuleType <em>Parent Update DRI Rule Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#getParentDeleteDRIRuleType <em>Parent Delete DRI Rule Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#getCheckOption <em>Check Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#getMaximumPrimaryKeyIdentifierLength <em>Maximum Primary Key Identifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#getMaximumForeignKeyIdentifierLength <em>Maximum Foreign Key Identifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstraintDefinitionImpl#getMaximumCheckConstraintIdentifierLength <em>Maximum Check Constraint Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintDefinitionImpl extends EObjectImpl implements ConstraintDefinition {
	/**
	 * The default value of the '{@link #isDeferrableConstraintSupported() <em>Deferrable Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeferrableConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFERRABLE_CONSTRAINT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeferrableConstraintSupported() <em>Deferrable Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeferrableConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean deferrableConstraintSupported = DEFERRABLE_CONSTRAINT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isInformationalConstraintSupported() <em>Informational Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInformationalConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INFORMATIONAL_CONSTRAINT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInformationalConstraintSupported() <em>Informational Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInformationalConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean informationalConstraintSupported = INFORMATIONAL_CONSTRAINT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isClusteredPrimaryKeySupported() <em>Clustered Primary Key Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusteredPrimaryKeySupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLUSTERED_PRIMARY_KEY_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isClusteredPrimaryKeySupported() <em>Clustered Primary Key Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusteredPrimaryKeySupported()
	 * @generated
	 * @ordered
	 */
	protected boolean clusteredPrimaryKeySupported = CLUSTERED_PRIMARY_KEY_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isClusteredUniqueConstraintSupported() <em>Clustered Unique Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusteredUniqueConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isClusteredUniqueConstraintSupported() <em>Clustered Unique Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusteredUniqueConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean clusteredUniqueConstraintSupported = CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrimaryKeyNullable() <em>Primary Key Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimaryKeyNullable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRIMARY_KEY_NULLABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrimaryKeyNullable() <em>Primary Key Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrimaryKeyNullable()
	 * @generated
	 * @ordered
	 */
	protected boolean primaryKeyNullable = PRIMARY_KEY_NULLABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isUniqueKeyNullable() <em>Unique Key Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUniqueKeyNullable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_KEY_NULLABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUniqueKeyNullable() <em>Unique Key Nullable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUniqueKeyNullable()
	 * @generated
	 * @ordered
	 */
	protected boolean uniqueKeyNullable = UNIQUE_KEY_NULLABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumCheckExpressionLength() <em>Maximum Check Expression Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumCheckExpressionLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_CHECK_EXPRESSION_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumCheckExpressionLength() <em>Maximum Check Expression Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumCheckExpressionLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumCheckExpressionLength = MAXIMUM_CHECK_EXPRESSION_LENGTH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParentUpdateDRIRuleType() <em>Parent Update DRI Rule Type</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentUpdateDRIRuleType()
	 * @generated
	 * @ordered
	 */
	protected EList parentUpdateDRIRuleType;

	/**
	 * The cached value of the '{@link #getParentDeleteDRIRuleType() <em>Parent Delete DRI Rule Type</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentDeleteDRIRuleType()
	 * @generated
	 * @ordered
	 */
	protected EList parentDeleteDRIRuleType;

	/**
	 * The cached value of the '{@link #getCheckOption() <em>Check Option</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCheckOption()
	 * @generated
	 * @ordered
	 */
	protected EList checkOption;

	/**
	 * The default value of the '{@link #getMaximumPrimaryKeyIdentifierLength() <em>Maximum Primary Key Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPrimaryKeyIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumPrimaryKeyIdentifierLength() <em>Maximum Primary Key Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPrimaryKeyIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumPrimaryKeyIdentifierLength = MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumForeignKeyIdentifierLength() <em>Maximum Foreign Key Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumForeignKeyIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumForeignKeyIdentifierLength() <em>Maximum Foreign Key Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumForeignKeyIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumForeignKeyIdentifierLength = MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumCheckConstraintIdentifierLength() <em>Maximum Check Constraint Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumCheckConstraintIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumCheckConstraintIdentifierLength() <em>Maximum Check Constraint Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumCheckConstraintIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumCheckConstraintIdentifierLength = MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.CONSTRAINT_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeferrableConstraintSupported() {
		return deferrableConstraintSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeferrableConstraintSupported(boolean newDeferrableConstraintSupported) {
		boolean oldDeferrableConstraintSupported = deferrableConstraintSupported;
		deferrableConstraintSupported = newDeferrableConstraintSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__DEFERRABLE_CONSTRAINT_SUPPORTED, oldDeferrableConstraintSupported, deferrableConstraintSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInformationalConstraintSupported() {
		return informationalConstraintSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInformationalConstraintSupported(boolean newInformationalConstraintSupported) {
		boolean oldInformationalConstraintSupported = informationalConstraintSupported;
		informationalConstraintSupported = newInformationalConstraintSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__INFORMATIONAL_CONSTRAINT_SUPPORTED, oldInformationalConstraintSupported, informationalConstraintSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isClusteredPrimaryKeySupported() {
		return clusteredPrimaryKeySupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClusteredPrimaryKeySupported(boolean newClusteredPrimaryKeySupported) {
		boolean oldClusteredPrimaryKeySupported = clusteredPrimaryKeySupported;
		clusteredPrimaryKeySupported = newClusteredPrimaryKeySupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_PRIMARY_KEY_SUPPORTED, oldClusteredPrimaryKeySupported, clusteredPrimaryKeySupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isClusteredUniqueConstraintSupported() {
		return clusteredUniqueConstraintSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClusteredUniqueConstraintSupported(boolean newClusteredUniqueConstraintSupported) {
		boolean oldClusteredUniqueConstraintSupported = clusteredUniqueConstraintSupported;
		clusteredUniqueConstraintSupported = newClusteredUniqueConstraintSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED, oldClusteredUniqueConstraintSupported, clusteredUniqueConstraintSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrimaryKeyNullable() {
		return primaryKeyNullable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimaryKeyNullable(boolean newPrimaryKeyNullable) {
		boolean oldPrimaryKeyNullable = primaryKeyNullable;
		primaryKeyNullable = newPrimaryKeyNullable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PRIMARY_KEY_NULLABLE, oldPrimaryKeyNullable, primaryKeyNullable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUniqueKeyNullable() {
		return uniqueKeyNullable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUniqueKeyNullable(boolean newUniqueKeyNullable) {
		boolean oldUniqueKeyNullable = uniqueKeyNullable;
		uniqueKeyNullable = newUniqueKeyNullable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__UNIQUE_KEY_NULLABLE, oldUniqueKeyNullable, uniqueKeyNullable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumCheckExpressionLength() {
		return maximumCheckExpressionLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumCheckExpressionLength(int newMaximumCheckExpressionLength) {
		int oldMaximumCheckExpressionLength = maximumCheckExpressionLength;
		maximumCheckExpressionLength = newMaximumCheckExpressionLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_EXPRESSION_LENGTH, oldMaximumCheckExpressionLength, maximumCheckExpressionLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParentUpdateDRIRuleType() {
		if (parentUpdateDRIRuleType == null) {
			parentUpdateDRIRuleType = new EDataTypeUniqueEList(ParentUpdateDRIRuleType.class, this, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_UPDATE_DRI_RULE_TYPE);
		}
		return parentUpdateDRIRuleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParentDeleteDRIRuleType() {
		if (parentDeleteDRIRuleType == null) {
			parentDeleteDRIRuleType = new EDataTypeUniqueEList(ParentDeleteDRIRuleType.class, this, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_DELETE_DRI_RULE_TYPE);
		}
		return parentDeleteDRIRuleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCheckOption() {
		if (checkOption == null) {
			checkOption = new EDataTypeUniqueEList(CheckOption.class, this, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CHECK_OPTION);
		}
		return checkOption;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumPrimaryKeyIdentifierLength() {
		return maximumPrimaryKeyIdentifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumPrimaryKeyIdentifierLength(int newMaximumPrimaryKeyIdentifierLength) {
		int oldMaximumPrimaryKeyIdentifierLength = maximumPrimaryKeyIdentifierLength;
		maximumPrimaryKeyIdentifierLength = newMaximumPrimaryKeyIdentifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH, oldMaximumPrimaryKeyIdentifierLength, maximumPrimaryKeyIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumForeignKeyIdentifierLength() {
		return maximumForeignKeyIdentifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumForeignKeyIdentifierLength(int newMaximumForeignKeyIdentifierLength) {
		int oldMaximumForeignKeyIdentifierLength = maximumForeignKeyIdentifierLength;
		maximumForeignKeyIdentifierLength = newMaximumForeignKeyIdentifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH, oldMaximumForeignKeyIdentifierLength, maximumForeignKeyIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumCheckConstraintIdentifierLength() {
		return maximumCheckConstraintIdentifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumCheckConstraintIdentifierLength(int newMaximumCheckConstraintIdentifierLength) {
		int oldMaximumCheckConstraintIdentifierLength = maximumCheckConstraintIdentifierLength;
		maximumCheckConstraintIdentifierLength = newMaximumCheckConstraintIdentifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH, oldMaximumCheckConstraintIdentifierLength, maximumCheckConstraintIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__DEFERRABLE_CONSTRAINT_SUPPORTED:
				return isDeferrableConstraintSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__INFORMATIONAL_CONSTRAINT_SUPPORTED:
				return isInformationalConstraintSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_PRIMARY_KEY_SUPPORTED:
				return isClusteredPrimaryKeySupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED:
				return isClusteredUniqueConstraintSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PRIMARY_KEY_NULLABLE:
				return isPrimaryKeyNullable() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__UNIQUE_KEY_NULLABLE:
				return isUniqueKeyNullable() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_EXPRESSION_LENGTH:
				return new Integer(getMaximumCheckExpressionLength());
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_UPDATE_DRI_RULE_TYPE:
				return getParentUpdateDRIRuleType();
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_DELETE_DRI_RULE_TYPE:
				return getParentDeleteDRIRuleType();
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CHECK_OPTION:
				return getCheckOption();
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH:
				return new Integer(getMaximumPrimaryKeyIdentifierLength());
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH:
				return new Integer(getMaximumForeignKeyIdentifierLength());
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH:
				return new Integer(getMaximumCheckConstraintIdentifierLength());
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
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__DEFERRABLE_CONSTRAINT_SUPPORTED:
				setDeferrableConstraintSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__INFORMATIONAL_CONSTRAINT_SUPPORTED:
				setInformationalConstraintSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_PRIMARY_KEY_SUPPORTED:
				setClusteredPrimaryKeySupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED:
				setClusteredUniqueConstraintSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PRIMARY_KEY_NULLABLE:
				setPrimaryKeyNullable(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__UNIQUE_KEY_NULLABLE:
				setUniqueKeyNullable(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_EXPRESSION_LENGTH:
				setMaximumCheckExpressionLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_UPDATE_DRI_RULE_TYPE:
				getParentUpdateDRIRuleType().clear();
				getParentUpdateDRIRuleType().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_DELETE_DRI_RULE_TYPE:
				getParentDeleteDRIRuleType().clear();
				getParentDeleteDRIRuleType().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CHECK_OPTION:
				getCheckOption().clear();
				getCheckOption().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH:
				setMaximumPrimaryKeyIdentifierLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH:
				setMaximumForeignKeyIdentifierLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH:
				setMaximumCheckConstraintIdentifierLength(((Integer)newValue).intValue());
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
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__DEFERRABLE_CONSTRAINT_SUPPORTED:
				setDeferrableConstraintSupported(DEFERRABLE_CONSTRAINT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__INFORMATIONAL_CONSTRAINT_SUPPORTED:
				setInformationalConstraintSupported(INFORMATIONAL_CONSTRAINT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_PRIMARY_KEY_SUPPORTED:
				setClusteredPrimaryKeySupported(CLUSTERED_PRIMARY_KEY_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED:
				setClusteredUniqueConstraintSupported(CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PRIMARY_KEY_NULLABLE:
				setPrimaryKeyNullable(PRIMARY_KEY_NULLABLE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__UNIQUE_KEY_NULLABLE:
				setUniqueKeyNullable(UNIQUE_KEY_NULLABLE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_EXPRESSION_LENGTH:
				setMaximumCheckExpressionLength(MAXIMUM_CHECK_EXPRESSION_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_UPDATE_DRI_RULE_TYPE:
				getParentUpdateDRIRuleType().clear();
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_DELETE_DRI_RULE_TYPE:
				getParentDeleteDRIRuleType().clear();
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CHECK_OPTION:
				getCheckOption().clear();
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH:
				setMaximumPrimaryKeyIdentifierLength(MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH:
				setMaximumForeignKeyIdentifierLength(MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH:
				setMaximumCheckConstraintIdentifierLength(MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH_EDEFAULT);
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
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__DEFERRABLE_CONSTRAINT_SUPPORTED:
				return deferrableConstraintSupported != DEFERRABLE_CONSTRAINT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__INFORMATIONAL_CONSTRAINT_SUPPORTED:
				return informationalConstraintSupported != INFORMATIONAL_CONSTRAINT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_PRIMARY_KEY_SUPPORTED:
				return clusteredPrimaryKeySupported != CLUSTERED_PRIMARY_KEY_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED:
				return clusteredUniqueConstraintSupported != CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PRIMARY_KEY_NULLABLE:
				return primaryKeyNullable != PRIMARY_KEY_NULLABLE_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__UNIQUE_KEY_NULLABLE:
				return uniqueKeyNullable != UNIQUE_KEY_NULLABLE_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_EXPRESSION_LENGTH:
				return maximumCheckExpressionLength != MAXIMUM_CHECK_EXPRESSION_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_UPDATE_DRI_RULE_TYPE:
				return parentUpdateDRIRuleType != null && !parentUpdateDRIRuleType.isEmpty();
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__PARENT_DELETE_DRI_RULE_TYPE:
				return parentDeleteDRIRuleType != null && !parentDeleteDRIRuleType.isEmpty();
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__CHECK_OPTION:
				return checkOption != null && !checkOption.isEmpty();
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH:
				return maximumPrimaryKeyIdentifierLength != MAXIMUM_PRIMARY_KEY_IDENTIFIER_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH:
				return maximumForeignKeyIdentifierLength != MAXIMUM_FOREIGN_KEY_IDENTIFIER_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION__MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH:
				return maximumCheckConstraintIdentifierLength != MAXIMUM_CHECK_CONSTRAINT_IDENTIFIER_LENGTH_EDEFAULT;
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
		result.append(" (deferrableConstraintSupported: "); //$NON-NLS-1$
		result.append(deferrableConstraintSupported);
		result.append(", informationalConstraintSupported: "); //$NON-NLS-1$
		result.append(informationalConstraintSupported);
		result.append(", clusteredPrimaryKeySupported: "); //$NON-NLS-1$
		result.append(clusteredPrimaryKeySupported);
		result.append(", clusteredUniqueConstraintSupported: "); //$NON-NLS-1$
		result.append(clusteredUniqueConstraintSupported);
		result.append(", primaryKeyNullable: "); //$NON-NLS-1$
		result.append(primaryKeyNullable);
		result.append(", uniqueKeyNullable: "); //$NON-NLS-1$
		result.append(uniqueKeyNullable);
		result.append(", maximumCheckExpressionLength: "); //$NON-NLS-1$
		result.append(maximumCheckExpressionLength);
		result.append(", parentUpdateDRIRuleType: "); //$NON-NLS-1$
		result.append(parentUpdateDRIRuleType);
		result.append(", parentDeleteDRIRuleType: "); //$NON-NLS-1$
		result.append(parentDeleteDRIRuleType);
		result.append(", checkOption: "); //$NON-NLS-1$
		result.append(checkOption);
		result.append(", maximumPrimaryKeyIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumPrimaryKeyIdentifierLength);
		result.append(", maximumForeignKeyIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumForeignKeyIdentifierLength);
		result.append(", maximumCheckConstraintIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumCheckConstraintIdentifierLength);
		result.append(')');
		return result.toString();
	}

} //ConstraintDefinitionImpl
