/**
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *   Ingres Corporation - initial API and implementation
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.ingres.models.ingressqlmodel;

import java.math.BigInteger;

import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ingres Identity Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getSeqLength <em>Seq Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getSeqPrecision <em>Seq Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getMaximumOption <em>Maximum Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getMinimumOption <em>Minimum Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getCacheSize <em>Cache Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getCacheOption <em>Cache Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getOrderOption <em>Order Option</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier()
 * @model
 * @generated
 */
public interface IngresIdentitySpecifier extends IdentitySpecifier {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2008 Ingres Corporation and others.\r\nAll rights reserved. This program and the accompanying materials\r\nare made available under the terms of the Eclipse Public License 2.0\r\nwhich accompanies this distribution, and is available at\r\nhttps://www.eclipse.org/legal/epl-2.0/\r\n\r\nContributors:\r\n  Ingres Corporation - initial API and implementation";

	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' attribute.
	 * @see #setDataType(String)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier_DataType()
	 * @model
	 * @generated
	 */
	String getDataType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(String value);

	/**
	 * Returns the value of the '<em><b>Seq Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seq Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seq Length</em>' attribute.
	 * @see #setSeqLength(BigInteger)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier_SeqLength()
	 * @model
	 * @generated
	 */
	BigInteger getSeqLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getSeqLength <em>Seq Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seq Length</em>' attribute.
	 * @see #getSeqLength()
	 * @generated
	 */
	void setSeqLength(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Seq Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seq Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Seq Precision</em>' attribute.
	 * @see #setSeqPrecision(BigInteger)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier_SeqPrecision()
	 * @model
	 * @generated
	 */
	BigInteger getSeqPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getSeqPrecision <em>Seq Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Seq Precision</em>' attribute.
	 * @see #getSeqPrecision()
	 * @generated
	 */
	void setSeqPrecision(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Maximum Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Option</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Option</em>' attribute.
	 * @see #setMaximumOption(Boolean)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier_MaximumOption()
	 * @model
	 * @generated
	 */
	Boolean getMaximumOption();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getMaximumOption <em>Maximum Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Option</em>' attribute.
	 * @see #getMaximumOption()
	 * @generated
	 */
	void setMaximumOption(Boolean value);

	/**
	 * Returns the value of the '<em><b>Minimum Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minimum Option</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Minimum Option</em>' attribute.
	 * @see #setMinimumOption(Boolean)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier_MinimumOption()
	 * @model
	 * @generated
	 */
	Boolean getMinimumOption();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getMinimumOption <em>Minimum Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Minimum Option</em>' attribute.
	 * @see #getMinimumOption()
	 * @generated
	 */
	void setMinimumOption(Boolean value);

	/**
	 * Returns the value of the '<em><b>Cache Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache Size</em>' attribute.
	 * @see #setCacheSize(BigInteger)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier_CacheSize()
	 * @model
	 * @generated
	 */
	BigInteger getCacheSize();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getCacheSize <em>Cache Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache Size</em>' attribute.
	 * @see #getCacheSize()
	 * @generated
	 */
	void setCacheSize(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cache Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache Option</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache Option</em>' attribute.
	 * @see #setCacheOption(Boolean)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier_CacheOption()
	 * @model
	 * @generated
	 */
	Boolean getCacheOption();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getCacheOption <em>Cache Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache Option</em>' attribute.
	 * @see #getCacheOption()
	 * @generated
	 */
	void setCacheOption(Boolean value);

	/**
	 * Returns the value of the '<em><b>Order Option</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Option</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Option</em>' attribute.
	 * @see #setOrderOption(Boolean)
	 * @see org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngressqlmodelPackage#getIngresIdentitySpecifier_OrderOption()
	 * @model
	 * @generated
	 */
	Boolean getOrderOption();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ingres.models.ingressqlmodel.IngresIdentitySpecifier#getOrderOption <em>Order Option</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order Option</em>' attribute.
	 * @see #getOrderOption()
	 * @generated
	 */
	void setOrderOption(Boolean value);

} // IngresIdentitySpecifier
