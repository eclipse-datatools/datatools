/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field Qualifier Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getValidTrailingFieldQualifierDefinitions <em>Valid Trailing Field Qualifier Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getMaximumPrecision <em>Maximum Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getDefaultPrecision <em>Default Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#isPrecisionSupported <em>Precision Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getMaximumScale <em>Maximum Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getDefaultScale <em>Default Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#isScaleSupported <em>Scale Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition()
 * @model
 * @generated
 */
public interface FieldQualifierDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Valid Trailing Field Qualifier Definitions</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valid Trailing Field Qualifier Definitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valid Trailing Field Qualifier Definitions</em>' reference list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition_ValidTrailingFieldQualifierDefinitions()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition"
	 * @generated
	 */
	EList getValidTrailingFieldQualifierDefinitions();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #setName(IntervalQualifierType)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition_Name()
	 * @model
	 * @generated
	 */
	IntervalQualifierType getName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #getName()
	 * @generated
	 */
	void setName(IntervalQualifierType value);

	/**
	 * Returns the value of the '<em><b>Maximum Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Precision</em>' attribute.
	 * @see #setMaximumPrecision(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition_MaximumPrecision()
	 * @model
	 * @generated
	 */
	int getMaximumPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getMaximumPrecision <em>Maximum Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Precision</em>' attribute.
	 * @see #getMaximumPrecision()
	 * @generated
	 */
	void setMaximumPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Default Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Precision</em>' attribute.
	 * @see #setDefaultPrecision(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition_DefaultPrecision()
	 * @model
	 * @generated
	 */
	int getDefaultPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getDefaultPrecision <em>Default Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Precision</em>' attribute.
	 * @see #getDefaultPrecision()
	 * @generated
	 */
	void setDefaultPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Precision Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precision Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precision Supported</em>' attribute.
	 * @see #setPrecisionSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition_PrecisionSupported()
	 * @model
	 * @generated
	 */
	boolean isPrecisionSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#isPrecisionSupported <em>Precision Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precision Supported</em>' attribute.
	 * @see #isPrecisionSupported()
	 * @generated
	 */
	void setPrecisionSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Maximum Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Scale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Scale</em>' attribute.
	 * @see #setMaximumScale(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition_MaximumScale()
	 * @model
	 * @generated
	 */
	int getMaximumScale();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getMaximumScale <em>Maximum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Scale</em>' attribute.
	 * @see #getMaximumScale()
	 * @generated
	 */
	void setMaximumScale(int value);

	/**
	 * Returns the value of the '<em><b>Default Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Scale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Scale</em>' attribute.
	 * @see #setDefaultScale(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition_DefaultScale()
	 * @model
	 * @generated
	 */
	int getDefaultScale();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#getDefaultScale <em>Default Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Scale</em>' attribute.
	 * @see #getDefaultScale()
	 * @generated
	 */
	void setDefaultScale(int value);

	/**
	 * Returns the value of the '<em><b>Scale Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scale Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scale Supported</em>' attribute.
	 * @see #setScaleSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getFieldQualifierDefinition_ScaleSupported()
	 * @model
	 * @generated
	 */
	boolean isScaleSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition#isScaleSupported <em>Scale Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale Supported</em>' attribute.
	 * @see #isScaleSupported()
	 * @generated
	 */
	void setScaleSupported(boolean value);

} // FieldQualifierDefinition
