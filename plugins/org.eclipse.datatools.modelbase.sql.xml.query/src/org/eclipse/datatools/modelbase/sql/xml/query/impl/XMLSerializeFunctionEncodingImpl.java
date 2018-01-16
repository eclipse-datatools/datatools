/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLSerializeFunctionEncodingImpl.java,v 1.5 2007/02/08 17:04:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Serialize Function Encoding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionEncodingImpl#getEncodingName <em>Encoding Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLSerializeFunctionEncodingImpl extends SQLQueryObjectImpl implements XMLSerializeFunctionEncoding {
	/**
     * The default value of the '{@link #getEncodingName() <em>Encoding Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEncodingName()
     * @generated
     * @ordered
     */
    protected static final String ENCODING_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getEncodingName() <em>Encoding Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEncodingName()
     * @generated
     * @ordered
     */
    protected String encodingName = ENCODING_NAME_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLSerializeFunctionEncodingImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_SERIALIZE_FUNCTION_ENCODING;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEncodingName() {
        return encodingName;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEncodingName(String newEncodingName) {
        String oldEncodingName = encodingName;
        encodingName = newEncodingName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_ENCODING__ENCODING_NAME, oldEncodingName, encodingName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_ENCODING__ENCODING_NAME:
                return getEncodingName();
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
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_ENCODING__ENCODING_NAME:
                setEncodingName((String)newValue);
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
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_ENCODING__ENCODING_NAME:
                setEncodingName(ENCODING_NAME_EDEFAULT);
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
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_ENCODING__ENCODING_NAME:
                return ENCODING_NAME_EDEFAULT == null ? encodingName != null : !ENCODING_NAME_EDEFAULT.equals(encodingName);
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
        result.append(" (encodingName: ");
        result.append(encodingName);
        result.append(')');
        return result.toString();
    }

} //XMLSerializeFunctionEncodingImpl
