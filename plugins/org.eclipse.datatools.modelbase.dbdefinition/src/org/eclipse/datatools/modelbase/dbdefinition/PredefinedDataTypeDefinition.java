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
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Predefined Data Type Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLengthSupported <em>Length Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isScaleSupported <em>Scale Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isPrecisionSupported <em>Precision Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isKeyConstraintSupported <em>Key Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isIdentitySupported <em>Identity Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isMultipleColumnsSupported <em>Multiple Columns Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isNullableSupported <em>Nullable Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isDefaultSupported <em>Default Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isClusteringSupported <em>Clustering Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isFillFactorSupported <em>Fill Factor Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isBitDataSupported <em>Bit Data Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumValue <em>Maximum Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMinimumValue <em>Minimum Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumLength <em>Maximum Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumPrecision <em>Maximum Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumScale <em>Maximum Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMinimumScale <em>Minimum Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultValueTypes <em>Default Value Types</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getPrimitiveType <em>Primitive Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getJdbcEnumType <em>Jdbc Enum Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCharacterSet <em>Character Set</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getEncodingScheme <em>Encoding Scheme</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCharacterSetSuffix <em>Character Set Suffix</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getEncodingSchemeSuffix <em>Encoding Scheme Suffix</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getJavaClassName <em>Java Class Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultLength <em>Default Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultPrecision <em>Default Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultScale <em>Default Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCutoffPrecision <em>Cutoff Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLengthUnit <em>Length Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getTrailingPrecision <em>Trailing Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultTrailingPrecision <em>Default Trailing Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isTrailingPrecisionSupported <em>Trailing Precision Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLeadingPrecision <em>Leading Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultLeadingPrecision <em>Default Leading Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLeadingPrecisionSupported <em>Leading Precision Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition()
 * @model 
 * @generated
 */
public interface PredefinedDataTypeDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Length Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length Supported</em>' attribute.
	 * @see #setLengthSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_LengthSupported()
	 * @model 
	 * @generated
	 */
	boolean isLengthSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLengthSupported <em>Length Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length Supported</em>' attribute.
	 * @see #isLengthSupported()
	 * @generated
	 */
	void setLengthSupported(boolean value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_ScaleSupported()
	 * @model 
	 * @generated
	 */
	boolean isScaleSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isScaleSupported <em>Scale Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale Supported</em>' attribute.
	 * @see #isScaleSupported()
	 * @generated
	 */
	void setScaleSupported(boolean value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_PrecisionSupported()
	 * @model 
	 * @generated
	 */
	boolean isPrecisionSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isPrecisionSupported <em>Precision Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precision Supported</em>' attribute.
	 * @see #isPrecisionSupported()
	 * @generated
	 */
	void setPrecisionSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Key Constraint Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key Constraint Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key Constraint Supported</em>' attribute.
	 * @see #setKeyConstraintSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_KeyConstraintSupported()
	 * @model 
	 * @generated
	 */
	boolean isKeyConstraintSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isKeyConstraintSupported <em>Key Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key Constraint Supported</em>' attribute.
	 * @see #isKeyConstraintSupported()
	 * @generated
	 */
	void setKeyConstraintSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Identity Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identity Supported</em>' attribute.
	 * @see #setIdentitySupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_IdentitySupported()
	 * @model 
	 * @generated
	 */
	boolean isIdentitySupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isIdentitySupported <em>Identity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identity Supported</em>' attribute.
	 * @see #isIdentitySupported()
	 * @generated
	 */
	void setIdentitySupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Multiple Columns Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiple Columns Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiple Columns Supported</em>' attribute.
	 * @see #setMultipleColumnsSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_MultipleColumnsSupported()
	 * @model 
	 * @generated
	 */
	boolean isMultipleColumnsSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isMultipleColumnsSupported <em>Multiple Columns Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiple Columns Supported</em>' attribute.
	 * @see #isMultipleColumnsSupported()
	 * @generated
	 */
	void setMultipleColumnsSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Nullable Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nullable Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nullable Supported</em>' attribute.
	 * @see #setNullableSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_NullableSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isNullableSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isNullableSupported <em>Nullable Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nullable Supported</em>' attribute.
	 * @see #isNullableSupported()
	 * @generated
	 */
	void setNullableSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Default Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Supported</em>' attribute.
	 * @see #setDefaultSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_DefaultSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isDefaultSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isDefaultSupported <em>Default Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Supported</em>' attribute.
	 * @see #isDefaultSupported()
	 * @generated
	 */
	void setDefaultSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Clustering Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clustering Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clustering Supported</em>' attribute.
	 * @see #setClusteringSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_ClusteringSupported()
	 * @model 
	 * @generated
	 */
	boolean isClusteringSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isClusteringSupported <em>Clustering Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clustering Supported</em>' attribute.
	 * @see #isClusteringSupported()
	 * @generated
	 */
	void setClusteringSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Fill Factor Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fill Factor Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill Factor Supported</em>' attribute.
	 * @see #setFillFactorSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_FillFactorSupported()
	 * @model 
	 * @generated
	 */
	boolean isFillFactorSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isFillFactorSupported <em>Fill Factor Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill Factor Supported</em>' attribute.
	 * @see #isFillFactorSupported()
	 * @generated
	 */
	void setFillFactorSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Bit Data Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bit Data Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bit Data Supported</em>' attribute.
	 * @see #setBitDataSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_BitDataSupported()
	 * @model 
	 * @generated
	 */
	boolean isBitDataSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isBitDataSupported <em>Bit Data Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bit Data Supported</em>' attribute.
	 * @see #isBitDataSupported()
	 * @generated
	 */
	void setBitDataSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Maximum Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Value</em>' attribute.
	 * @see #setMaximumValue(long)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_MaximumValue()
	 * @model 
	 * @generated
	 */
	long getMaximumValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumValue <em>Maximum Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Value</em>' attribute.
	 * @see #getMaximumValue()
	 * @generated
	 */
	void setMaximumValue(long value);

	/**
	 * Returns the value of the '<em><b>Minimum Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minimum Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minimum Value</em>' attribute.
	 * @see #setMinimumValue(long)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_MinimumValue()
	 * @model 
	 * @generated
	 */
	long getMinimumValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMinimumValue <em>Minimum Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minimum Value</em>' attribute.
	 * @see #getMinimumValue()
	 * @generated
	 */
	void setMinimumValue(long value);

	/**
	 * Returns the value of the '<em><b>Maximum Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Length</em>' attribute.
	 * @see #setMaximumLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_MaximumLength()
	 * @model 
	 * @generated
	 */
	int getMaximumLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumLength <em>Maximum Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Length</em>' attribute.
	 * @see #getMaximumLength()
	 * @generated
	 */
	void setMaximumLength(int value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_MaximumPrecision()
	 * @model 
	 * @generated
	 */
	int getMaximumPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumPrecision <em>Maximum Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Precision</em>' attribute.
	 * @see #getMaximumPrecision()
	 * @generated
	 */
	void setMaximumPrecision(int value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_MaximumScale()
	 * @model 
	 * @generated
	 */
	int getMaximumScale();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMaximumScale <em>Maximum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Scale</em>' attribute.
	 * @see #getMaximumScale()
	 * @generated
	 */
	void setMaximumScale(int value);

	/**
	 * Returns the value of the '<em><b>Minimum Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minimum Scale</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minimum Scale</em>' attribute.
	 * @see #setMinimumScale(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_MinimumScale()
	 * @model 
	 * @generated
	 */
	int getMinimumScale();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getMinimumScale <em>Minimum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minimum Scale</em>' attribute.
	 * @see #getMinimumScale()
	 * @generated
	 */
	void setMinimumScale(int value);

	/**
	 * Returns the value of the '<em><b>Default Value Types</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.DefaultValueType}.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.DefaultValueType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value Types</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value Types</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DefaultValueType
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_DefaultValueTypes()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.DefaultValueType"
	 * @generated
	 */
	EList getDefaultValueTypes();

	/**
	 * Returns the value of the '<em><b>Primitive Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primitive Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primitive Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType
	 * @see #setPrimitiveType(PrimitiveType)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_PrimitiveType()
	 * @model 
	 * @generated
	 */
	PrimitiveType getPrimitiveType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getPrimitiveType <em>Primitive Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primitive Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType
	 * @see #getPrimitiveType()
	 * @generated
	 */
	void setPrimitiveType(PrimitiveType value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_Name()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getName();

	/**
	 * Returns the value of the '<em><b>Jdbc Enum Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Jdbc Enum Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Jdbc Enum Type</em>' attribute.
	 * @see #setJdbcEnumType(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_JdbcEnumType()
	 * @model 
	 * @generated
	 */
	int getJdbcEnumType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getJdbcEnumType <em>Jdbc Enum Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Jdbc Enum Type</em>' attribute.
	 * @see #getJdbcEnumType()
	 * @generated
	 */
	void setJdbcEnumType(int value);

	/**
	 * Returns the value of the '<em><b>Character Set</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Character Set</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Character Set</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_CharacterSet()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getCharacterSet();

	/**
	 * Returns the value of the '<em><b>Encoding Scheme</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoding Scheme</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoding Scheme</em>' attribute list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_EncodingScheme()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getEncodingScheme();

	/**
	 * Returns the value of the '<em><b>Character Set Suffix</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Character Set Suffix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Character Set Suffix</em>' attribute.
	 * @see #setCharacterSetSuffix(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_CharacterSetSuffix()
	 * @model default=""
	 * @generated
	 */
	String getCharacterSetSuffix();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCharacterSetSuffix <em>Character Set Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Character Set Suffix</em>' attribute.
	 * @see #getCharacterSetSuffix()
	 * @generated
	 */
	void setCharacterSetSuffix(String value);

	/**
	 * Returns the value of the '<em><b>Encoding Scheme Suffix</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoding Scheme Suffix</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoding Scheme Suffix</em>' attribute.
	 * @see #setEncodingSchemeSuffix(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_EncodingSchemeSuffix()
	 * @model default=""
	 * @generated
	 */
	String getEncodingSchemeSuffix();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getEncodingSchemeSuffix <em>Encoding Scheme Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding Scheme Suffix</em>' attribute.
	 * @see #getEncodingSchemeSuffix()
	 * @generated
	 */
	void setEncodingSchemeSuffix(String value);

	/**
	 * Returns the value of the '<em><b>Java Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java Class Name</em>' attribute.
	 * @see #setJavaClassName(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_JavaClassName()
	 * @model 
	 * @generated
	 */
	String getJavaClassName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getJavaClassName <em>Java Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Class Name</em>' attribute.
	 * @see #getJavaClassName()
	 * @generated
	 */
	void setJavaClassName(String value);

	/**
	 * Returns the value of the '<em><b>Default Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Length</em>' attribute.
	 * @see #setDefaultLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_DefaultLength()
	 * @model 
	 * @generated
	 */
	int getDefaultLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultLength <em>Default Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Length</em>' attribute.
	 * @see #getDefaultLength()
	 * @generated
	 */
	void setDefaultLength(int value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_DefaultPrecision()
	 * @model 
	 * @generated
	 */
	int getDefaultPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultPrecision <em>Default Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Precision</em>' attribute.
	 * @see #getDefaultPrecision()
	 * @generated
	 */
	void setDefaultPrecision(int value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_DefaultScale()
	 * @model 
	 * @generated
	 */
	int getDefaultScale();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultScale <em>Default Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Scale</em>' attribute.
	 * @see #getDefaultScale()
	 * @generated
	 */
	void setDefaultScale(int value);

	/**
	 * Returns the value of the '<em><b>Cutoff Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cutoff Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cutoff Precision</em>' attribute.
	 * @see #setCutoffPrecision(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_CutoffPrecision()
	 * @model 
	 * @generated
	 */
	int getCutoffPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getCutoffPrecision <em>Cutoff Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cutoff Precision</em>' attribute.
	 * @see #getCutoffPrecision()
	 * @generated
	 */
	void setCutoffPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Length Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.LengthUnit}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length Unit</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.LengthUnit
	 * @see #setLengthUnit(LengthUnit)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_LengthUnit()
	 * @model 
	 * @generated
	 */
	LengthUnit getLengthUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLengthUnit <em>Length Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length Unit</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.LengthUnit
	 * @see #getLengthUnit()
	 * @generated
	 */
	void setLengthUnit(LengthUnit value);

	/**
	 * Returns the value of the '<em><b>Trailing Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trailing Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trailing Precision</em>' attribute.
	 * @see #setTrailingPrecision(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_TrailingPrecision()
	 * @model 
	 * @generated
	 */
	int getTrailingPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getTrailingPrecision <em>Trailing Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trailing Precision</em>' attribute.
	 * @see #getTrailingPrecision()
	 * @generated
	 */
	void setTrailingPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Default Trailing Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Trailing Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Trailing Precision</em>' attribute.
	 * @see #setDefaultTrailingPrecision(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_DefaultTrailingPrecision()
	 * @model 
	 * @generated
	 */
	int getDefaultTrailingPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultTrailingPrecision <em>Default Trailing Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Trailing Precision</em>' attribute.
	 * @see #getDefaultTrailingPrecision()
	 * @generated
	 */
	void setDefaultTrailingPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Trailing Precision Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trailing Precision Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trailing Precision Supported</em>' attribute.
	 * @see #setTrailingPrecisionSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_TrailingPrecisionSupported()
	 * @model 
	 * @generated
	 */
	boolean isTrailingPrecisionSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isTrailingPrecisionSupported <em>Trailing Precision Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trailing Precision Supported</em>' attribute.
	 * @see #isTrailingPrecisionSupported()
	 * @generated
	 */
	void setTrailingPrecisionSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Leading Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leading Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leading Precision</em>' attribute.
	 * @see #setLeadingPrecision(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_LeadingPrecision()
	 * @model 
	 * @generated
	 */
	int getLeadingPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getLeadingPrecision <em>Leading Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leading Precision</em>' attribute.
	 * @see #getLeadingPrecision()
	 * @generated
	 */
	void setLeadingPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Default Leading Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Leading Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Leading Precision</em>' attribute.
	 * @see #setDefaultLeadingPrecision(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_DefaultLeadingPrecision()
	 * @model 
	 * @generated
	 */
	int getDefaultLeadingPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#getDefaultLeadingPrecision <em>Default Leading Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Leading Precision</em>' attribute.
	 * @see #getDefaultLeadingPrecision()
	 * @generated
	 */
	void setDefaultLeadingPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Leading Precision Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leading Precision Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leading Precision Supported</em>' attribute.
	 * @see #setLeadingPrecisionSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getPredefinedDataTypeDefinition_LeadingPrecisionSupported()
	 * @model 
	 * @generated
	 */
	boolean isLeadingPrecisionSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition#isLeadingPrecisionSupported <em>Leading Precision Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leading Precision Supported</em>' attribute.
	 * @see #isLeadingPrecisionSupported()
	 * @generated
	 */
	void setLeadingPrecisionSupported(boolean value);

} // PredefinedDataTypeDefinition
