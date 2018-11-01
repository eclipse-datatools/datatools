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
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#getPercentFreeTerminology <em>Percent Free Terminology</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isPercentFreeChangeable <em>Percent Free Changeable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isClusteringSupported <em>Clustering Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isClusterChangeable <em>Cluster Changeable</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isFillFactorSupported <em>Fill Factor Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isIncludedColumnsSupported <em>Included Columns Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getIndexDefinition()
 * @model
 * @generated
 */
public interface IndexDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Percent Free Terminology</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Percent Free Terminology</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Percent Free Terminology</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology
	 * @see #setPercentFreeTerminology(PercentFreeTerminology)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getIndexDefinition_PercentFreeTerminology()
	 * @model
	 * @generated
	 */
	PercentFreeTerminology getPercentFreeTerminology();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#getPercentFreeTerminology <em>Percent Free Terminology</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percent Free Terminology</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology
	 * @see #getPercentFreeTerminology()
	 * @generated
	 */
	void setPercentFreeTerminology(PercentFreeTerminology value);

	/**
	 * Returns the value of the '<em><b>Percent Free Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Percent Free Changeable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Percent Free Changeable</em>' attribute.
	 * @see #setPercentFreeChangeable(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getIndexDefinition_PercentFreeChangeable()
	 * @model
	 * @generated
	 */
	boolean isPercentFreeChangeable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isPercentFreeChangeable <em>Percent Free Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percent Free Changeable</em>' attribute.
	 * @see #isPercentFreeChangeable()
	 * @generated
	 */
	void setPercentFreeChangeable(boolean value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getIndexDefinition_ClusteringSupported()
	 * @model
	 * @generated
	 */
	boolean isClusteringSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isClusteringSupported <em>Clustering Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Clustering Supported</em>' attribute.
	 * @see #isClusteringSupported()
	 * @generated
	 */
	void setClusteringSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Cluster Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cluster Changeable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cluster Changeable</em>' attribute.
	 * @see #setClusterChangeable(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getIndexDefinition_ClusterChangeable()
	 * @model
	 * @generated
	 */
	boolean isClusterChangeable();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isClusterChangeable <em>Cluster Changeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cluster Changeable</em>' attribute.
	 * @see #isClusterChangeable()
	 * @generated
	 */
	void setClusterChangeable(boolean value);

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getIndexDefinition_FillFactorSupported()
	 * @model
	 * @generated
	 */
	boolean isFillFactorSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isFillFactorSupported <em>Fill Factor Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill Factor Supported</em>' attribute.
	 * @see #isFillFactorSupported()
	 * @generated
	 */
	void setFillFactorSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Included Columns Supported</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Columns Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Columns Supported</em>' attribute.
	 * @see #setIncludedColumnsSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getIndexDefinition_IncludedColumnsSupported()
	 * @model default="false"
	 * @generated
	 */
	boolean isIncludedColumnsSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#isIncludedColumnsSupported <em>Included Columns Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Included Columns Supported</em>' attribute.
	 * @see #isIncludedColumnsSupported()
	 * @generated
	 */
	void setIncludedColumnsSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Identifier Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #setMaximumIdentifierLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getIndexDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

} // IndexDefinition
