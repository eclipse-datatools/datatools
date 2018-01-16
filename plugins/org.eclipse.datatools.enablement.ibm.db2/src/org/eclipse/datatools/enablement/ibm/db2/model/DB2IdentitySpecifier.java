/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2IdentitySpecifier.java,v 1.10 2009/07/30 00:21:45 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import java.math.BigInteger;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Identity Specifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#getCache <em>Cache</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#isOrder <em>Order</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#isSystemGenerated <em>System Generated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#getRestartValue <em>Restart Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2IdentitySpecifier()
 * @model
 * @generated
 */
public interface DB2IdentitySpecifier extends IdentitySpecifier {
	/**
	 * Returns the value of the '<em><b>Cache</b></em>' attribute.
	 * The default value is <code>"20"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cache</em>' attribute.
	 * @see #setCache(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2IdentitySpecifier_Cache()
	 * @model default="20"
	 * @generated
	 */
	int getCache();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#getCache <em>Cache</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cache</em>' attribute.
	 * @see #getCache()
	 * @generated
	 */
	void setCache(int value);

	/**
	 * Returns the value of the '<em><b>Order</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Sequences values are dispensed in order if True else unique but not necissarily unique.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Order</em>' attribute.
	 * @see #setOrder(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2IdentitySpecifier_Order()
	 * @model
	 * @generated
	 */
	boolean isOrder();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#isOrder <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order</em>' attribute.
	 * @see #isOrder()
	 * @generated
	 */
	void setOrder(boolean value);

	/**
	 * Returns the value of the '<em><b>System Generated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Generated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Generated</em>' attribute.
	 * @see #setSystemGenerated(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2IdentitySpecifier_SystemGenerated()
	 * @model
	 * @generated
	 */
	boolean isSystemGenerated();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#isSystemGenerated <em>System Generated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Generated</em>' attribute.
	 * @see #isSystemGenerated()
	 * @generated
	 */
	void setSystemGenerated(boolean value);

	/**
	 * Returns the value of the '<em><b>Restart Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Restart Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Restart Value</em>' attribute.
	 * @see #setRestartValue(BigInteger)
	 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2IdentitySpecifier_RestartValue()
	 * @model
	 * @generated
	 */
	BigInteger getRestartValue();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier#getRestartValue <em>Restart Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Restart Value</em>' attribute.
	 * @see #getRestartValue()
	 * @generated
	 */
	void setRestartValue(BigInteger value);

} // DB2IdentitySpecifier
