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

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.LanguageType;
import org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ProcedureType;
import org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stored Procedure Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isNullInputActionSupported <em>Null Input Action Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isPackageGenerationSupported <em>Package Generation Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isDetermininsticSupported <em>Determininstic Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isReturnedNullSupported <em>Returned Null Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isReturnedTypeDeclarationConstraintSupported <em>Returned Type Declaration Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isParameterInitValueSupported <em>Parameter Init Value Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isParameterStyleSupported <em>Parameter Style Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isReturnTypeSupported <em>Return Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#isParameterDeclarationConstraintSupported <em>Parameter Declaration Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#getMaximumActionBodyLength <em>Maximum Action Body Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#getParameterStyle <em>Parameter Style</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#getLanguageType <em>Language Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#getFunctionLanguageType <em>Function Language Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#getProcedureType <em>Procedure Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.StoredProcedureDefinitionImpl#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StoredProcedureDefinitionImpl extends EObjectImpl implements StoredProcedureDefinition {
	/**
	 * The cached value of the '{@link #getPredefinedDataTypeDefinitions() <em>Predefined Data Type Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefinedDataTypeDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList predefinedDataTypeDefinitions;

	/**
	 * The default value of the '{@link #isNullInputActionSupported() <em>Null Input Action Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullInputActionSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NULL_INPUT_ACTION_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNullInputActionSupported() <em>Null Input Action Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullInputActionSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean nullInputActionSupported = NULL_INPUT_ACTION_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isPackageGenerationSupported() <em>Package Generation Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPackageGenerationSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PACKAGE_GENERATION_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPackageGenerationSupported() <em>Package Generation Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPackageGenerationSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean packageGenerationSupported = PACKAGE_GENERATION_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isDetermininsticSupported() <em>Determininstic Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDetermininsticSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DETERMININSTIC_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDetermininsticSupported() <em>Determininstic Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDetermininsticSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean determininsticSupported = DETERMININSTIC_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isReturnedNullSupported() <em>Returned Null Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnedNullSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RETURNED_NULL_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReturnedNullSupported() <em>Returned Null Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnedNullSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean returnedNullSupported = RETURNED_NULL_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isReturnedTypeDeclarationConstraintSupported() <em>Returned Type Declaration Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnedTypeDeclarationConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReturnedTypeDeclarationConstraintSupported() <em>Returned Type Declaration Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnedTypeDeclarationConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean returnedTypeDeclarationConstraintSupported = RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isParameterInitValueSupported() <em>Parameter Init Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParameterInitValueSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARAMETER_INIT_VALUE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isParameterInitValueSupported() <em>Parameter Init Value Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParameterInitValueSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean parameterInitValueSupported = PARAMETER_INIT_VALUE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isParameterStyleSupported() <em>Parameter Style Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParameterStyleSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARAMETER_STYLE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isParameterStyleSupported() <em>Parameter Style Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParameterStyleSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean parameterStyleSupported = PARAMETER_STYLE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isReturnTypeSupported() <em>Return Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RETURN_TYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReturnTypeSupported() <em>Return Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean returnTypeSupported = RETURN_TYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isParameterDeclarationConstraintSupported() <em>Parameter Declaration Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParameterDeclarationConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isParameterDeclarationConstraintSupported() <em>Parameter Declaration Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isParameterDeclarationConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean parameterDeclarationConstraintSupported = PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumActionBodyLength() <em>Maximum Action Body Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumActionBodyLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_ACTION_BODY_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumActionBodyLength() <em>Maximum Action Body Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumActionBodyLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumActionBodyLength = MAXIMUM_ACTION_BODY_LENGTH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameterStyle() <em>Parameter Style</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterStyle()
	 * @generated
	 * @ordered
	 */
	protected EList parameterStyle;

	/**
	 * The cached value of the '{@link #getLanguageType() <em>Language Type</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguageType()
	 * @generated
	 * @ordered
	 */
	protected EList languageType;

	/**
	 * The cached value of the '{@link #getFunctionLanguageType() <em>Function Language Type</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionLanguageType()
	 * @generated
	 * @ordered
	 */
	protected EList functionLanguageType;

	/**
	 * The cached value of the '{@link #getProcedureType() <em>Procedure Type</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcedureType()
	 * @generated
	 * @ordered
	 */
	protected EList procedureType;

	/**
	 * The default value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StoredProcedureDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.STORED_PROCEDURE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPredefinedDataTypeDefinitions() {
		if (predefinedDataTypeDefinitions == null) {
			predefinedDataTypeDefinitions = new EObjectContainmentEList(PredefinedDataTypeDefinition.class, this, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS);
		}
		return predefinedDataTypeDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNullInputActionSupported() {
		return nullInputActionSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullInputActionSupported(boolean newNullInputActionSupported) {
		boolean oldNullInputActionSupported = nullInputActionSupported;
		nullInputActionSupported = newNullInputActionSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__NULL_INPUT_ACTION_SUPPORTED, oldNullInputActionSupported, nullInputActionSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPackageGenerationSupported() {
		return packageGenerationSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageGenerationSupported(boolean newPackageGenerationSupported) {
		boolean oldPackageGenerationSupported = packageGenerationSupported;
		packageGenerationSupported = newPackageGenerationSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PACKAGE_GENERATION_SUPPORTED, oldPackageGenerationSupported, packageGenerationSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDetermininsticSupported() {
		return determininsticSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDetermininsticSupported(boolean newDetermininsticSupported) {
		boolean oldDetermininsticSupported = determininsticSupported;
		determininsticSupported = newDetermininsticSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__DETERMININSTIC_SUPPORTED, oldDetermininsticSupported, determininsticSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReturnedNullSupported() {
		return returnedNullSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnedNullSupported(boolean newReturnedNullSupported) {
		boolean oldReturnedNullSupported = returnedNullSupported;
		returnedNullSupported = newReturnedNullSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_NULL_SUPPORTED, oldReturnedNullSupported, returnedNullSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReturnedTypeDeclarationConstraintSupported() {
		return returnedTypeDeclarationConstraintSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnedTypeDeclarationConstraintSupported(boolean newReturnedTypeDeclarationConstraintSupported) {
		boolean oldReturnedTypeDeclarationConstraintSupported = returnedTypeDeclarationConstraintSupported;
		returnedTypeDeclarationConstraintSupported = newReturnedTypeDeclarationConstraintSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED, oldReturnedTypeDeclarationConstraintSupported, returnedTypeDeclarationConstraintSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isParameterInitValueSupported() {
		return parameterInitValueSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterInitValueSupported(boolean newParameterInitValueSupported) {
		boolean oldParameterInitValueSupported = parameterInitValueSupported;
		parameterInitValueSupported = newParameterInitValueSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_INIT_VALUE_SUPPORTED, oldParameterInitValueSupported, parameterInitValueSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isParameterStyleSupported() {
		return parameterStyleSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterStyleSupported(boolean newParameterStyleSupported) {
		boolean oldParameterStyleSupported = parameterStyleSupported;
		parameterStyleSupported = newParameterStyleSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE_SUPPORTED, oldParameterStyleSupported, parameterStyleSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReturnTypeSupported() {
		return returnTypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnTypeSupported(boolean newReturnTypeSupported) {
		boolean oldReturnTypeSupported = returnTypeSupported;
		returnTypeSupported = newReturnTypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURN_TYPE_SUPPORTED, oldReturnTypeSupported, returnTypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isParameterDeclarationConstraintSupported() {
		return parameterDeclarationConstraintSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterDeclarationConstraintSupported(boolean newParameterDeclarationConstraintSupported) {
		boolean oldParameterDeclarationConstraintSupported = parameterDeclarationConstraintSupported;
		parameterDeclarationConstraintSupported = newParameterDeclarationConstraintSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED, oldParameterDeclarationConstraintSupported, parameterDeclarationConstraintSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumActionBodyLength() {
		return maximumActionBodyLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumActionBodyLength(int newMaximumActionBodyLength) {
		int oldMaximumActionBodyLength = maximumActionBodyLength;
		maximumActionBodyLength = newMaximumActionBodyLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH, oldMaximumActionBodyLength, maximumActionBodyLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParameterStyle() {
		if (parameterStyle == null) {
			parameterStyle = new EDataTypeUniqueEList(ParameterStyle.class, this, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE);
		}
		return parameterStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLanguageType() {
		if (languageType == null) {
			languageType = new EDataTypeUniqueEList(LanguageType.class, this, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__LANGUAGE_TYPE);
		}
		return languageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFunctionLanguageType() {
		if (functionLanguageType == null) {
			functionLanguageType = new EDataTypeUniqueEList(LanguageType.class, this, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__FUNCTION_LANGUAGE_TYPE);
		}
		return functionLanguageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getProcedureType() {
		if (procedureType == null) {
			procedureType = new EDataTypeUniqueEList(ProcedureType.class, this, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PROCEDURE_TYPE);
		}
		return procedureType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumIdentifierLength() {
		return maximumIdentifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumIdentifierLength(int newMaximumIdentifierLength) {
		int oldMaximumIdentifierLength = maximumIdentifierLength;
		maximumIdentifierLength = newMaximumIdentifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH, oldMaximumIdentifierLength, maximumIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				return ((InternalEList)getPredefinedDataTypeDefinitions()).basicRemove(otherEnd, msgs);
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
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				return getPredefinedDataTypeDefinitions();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__NULL_INPUT_ACTION_SUPPORTED:
				return isNullInputActionSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PACKAGE_GENERATION_SUPPORTED:
				return isPackageGenerationSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__DETERMININSTIC_SUPPORTED:
				return isDetermininsticSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_NULL_SUPPORTED:
				return isReturnedNullSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED:
				return isReturnedTypeDeclarationConstraintSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_INIT_VALUE_SUPPORTED:
				return isParameterInitValueSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE_SUPPORTED:
				return isParameterStyleSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURN_TYPE_SUPPORTED:
				return isReturnTypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED:
				return isParameterDeclarationConstraintSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH:
				return new Integer(getMaximumActionBodyLength());
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE:
				return getParameterStyle();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__LANGUAGE_TYPE:
				return getLanguageType();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__FUNCTION_LANGUAGE_TYPE:
				return getFunctionLanguageType();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PROCEDURE_TYPE:
				return getProcedureType();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return new Integer(getMaximumIdentifierLength());
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
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				getPredefinedDataTypeDefinitions().clear();
				getPredefinedDataTypeDefinitions().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__NULL_INPUT_ACTION_SUPPORTED:
				setNullInputActionSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PACKAGE_GENERATION_SUPPORTED:
				setPackageGenerationSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__DETERMININSTIC_SUPPORTED:
				setDetermininsticSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_NULL_SUPPORTED:
				setReturnedNullSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED:
				setReturnedTypeDeclarationConstraintSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_INIT_VALUE_SUPPORTED:
				setParameterInitValueSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE_SUPPORTED:
				setParameterStyleSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURN_TYPE_SUPPORTED:
				setReturnTypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED:
				setParameterDeclarationConstraintSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH:
				setMaximumActionBodyLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE:
				getParameterStyle().clear();
				getParameterStyle().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__LANGUAGE_TYPE:
				getLanguageType().clear();
				getLanguageType().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__FUNCTION_LANGUAGE_TYPE:
				getFunctionLanguageType().clear();
				getFunctionLanguageType().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PROCEDURE_TYPE:
				getProcedureType().clear();
				getProcedureType().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(((Integer)newValue).intValue());
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
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				getPredefinedDataTypeDefinitions().clear();
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__NULL_INPUT_ACTION_SUPPORTED:
				setNullInputActionSupported(NULL_INPUT_ACTION_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PACKAGE_GENERATION_SUPPORTED:
				setPackageGenerationSupported(PACKAGE_GENERATION_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__DETERMININSTIC_SUPPORTED:
				setDetermininsticSupported(DETERMININSTIC_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_NULL_SUPPORTED:
				setReturnedNullSupported(RETURNED_NULL_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED:
				setReturnedTypeDeclarationConstraintSupported(RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_INIT_VALUE_SUPPORTED:
				setParameterInitValueSupported(PARAMETER_INIT_VALUE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE_SUPPORTED:
				setParameterStyleSupported(PARAMETER_STYLE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURN_TYPE_SUPPORTED:
				setReturnTypeSupported(RETURN_TYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED:
				setParameterDeclarationConstraintSupported(PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH:
				setMaximumActionBodyLength(MAXIMUM_ACTION_BODY_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE:
				getParameterStyle().clear();
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__LANGUAGE_TYPE:
				getLanguageType().clear();
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__FUNCTION_LANGUAGE_TYPE:
				getFunctionLanguageType().clear();
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PROCEDURE_TYPE:
				getProcedureType().clear();
				return;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT);
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
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				return predefinedDataTypeDefinitions != null && !predefinedDataTypeDefinitions.isEmpty();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__NULL_INPUT_ACTION_SUPPORTED:
				return nullInputActionSupported != NULL_INPUT_ACTION_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PACKAGE_GENERATION_SUPPORTED:
				return packageGenerationSupported != PACKAGE_GENERATION_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__DETERMININSTIC_SUPPORTED:
				return determininsticSupported != DETERMININSTIC_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_NULL_SUPPORTED:
				return returnedNullSupported != RETURNED_NULL_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED:
				return returnedTypeDeclarationConstraintSupported != RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_INIT_VALUE_SUPPORTED:
				return parameterInitValueSupported != PARAMETER_INIT_VALUE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE_SUPPORTED:
				return parameterStyleSupported != PARAMETER_STYLE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__RETURN_TYPE_SUPPORTED:
				return returnTypeSupported != RETURN_TYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED:
				return parameterDeclarationConstraintSupported != PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH:
				return maximumActionBodyLength != MAXIMUM_ACTION_BODY_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE:
				return parameterStyle != null && !parameterStyle.isEmpty();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__LANGUAGE_TYPE:
				return languageType != null && !languageType.isEmpty();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__FUNCTION_LANGUAGE_TYPE:
				return functionLanguageType != null && !functionLanguageType.isEmpty();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__PROCEDURE_TYPE:
				return procedureType != null && !procedureType.isEmpty();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return maximumIdentifierLength != MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;
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
		result.append(" (nullInputActionSupported: "); //$NON-NLS-1$
		result.append(nullInputActionSupported);
		result.append(", packageGenerationSupported: "); //$NON-NLS-1$
		result.append(packageGenerationSupported);
		result.append(", determininsticSupported: "); //$NON-NLS-1$
		result.append(determininsticSupported);
		result.append(", returnedNullSupported: "); //$NON-NLS-1$
		result.append(returnedNullSupported);
		result.append(", returnedTypeDeclarationConstraintSupported: "); //$NON-NLS-1$
		result.append(returnedTypeDeclarationConstraintSupported);
		result.append(", parameterInitValueSupported: "); //$NON-NLS-1$
		result.append(parameterInitValueSupported);
		result.append(", parameterStyleSupported: "); //$NON-NLS-1$
		result.append(parameterStyleSupported);
		result.append(", returnTypeSupported: "); //$NON-NLS-1$
		result.append(returnTypeSupported);
		result.append(", parameterDeclarationConstraintSupported: "); //$NON-NLS-1$
		result.append(parameterDeclarationConstraintSupported);
		result.append(", maximumActionBodyLength: "); //$NON-NLS-1$
		result.append(maximumActionBodyLength);
		result.append(", parameterStyle: "); //$NON-NLS-1$
		result.append(parameterStyle);
		result.append(", languageType: "); //$NON-NLS-1$
		result.append(languageType);
		result.append(", functionLanguageType: "); //$NON-NLS-1$
		result.append(functionLanguageType);
		result.append(", procedureType: "); //$NON-NLS-1$
		result.append(procedureType);
		result.append(", maximumIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumIdentifierLength);
		result.append(')');
		return result.toString();
	}

} //StoredProcedureDefinitionImpl
