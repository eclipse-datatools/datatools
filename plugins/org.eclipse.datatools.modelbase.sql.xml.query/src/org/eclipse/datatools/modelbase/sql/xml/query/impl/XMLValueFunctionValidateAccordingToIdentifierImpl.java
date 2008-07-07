/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateAccordingToIdentifierImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToIdentifier;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateElement;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Validate According To Identifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToIdentifierImpl#getSchemaName <em>Schema Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToIdentifierImpl#getRegisteredXMLSchemaName <em>Registered XML Schema Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateAccordingToIdentifierImpl extends XMLValueFunctionValidateAccordingToImpl implements XMLValueFunctionValidateAccordingToIdentifier {
	/**
     * The default value of the '{@link #getSchemaName() <em>Schema Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSchemaName()
     * @generated
     * @ordered
     */
    protected static final String SCHEMA_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getSchemaName() <em>Schema Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSchemaName()
     * @generated
     * @ordered
     */
    protected String schemaName = SCHEMA_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getRegisteredXMLSchemaName() <em>Registered XML Schema Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRegisteredXMLSchemaName()
     * @generated
     * @ordered
     */
    protected static final String REGISTERED_XML_SCHEMA_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getRegisteredXMLSchemaName() <em>Registered XML Schema Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRegisteredXMLSchemaName()
     * @generated
     * @ordered
     */
    protected String registeredXMLSchemaName = REGISTERED_XML_SCHEMA_NAME_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionValidateAccordingToIdentifierImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSchemaName() {
        return schemaName;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSchemaName(String newSchemaName) {
        String oldSchemaName = schemaName;
        schemaName = newSchemaName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME, oldSchemaName, schemaName));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRegisteredXMLSchemaName() {
        return registeredXMLSchemaName;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRegisteredXMLSchemaName(String newRegisteredXMLSchemaName) {
        String oldRegisteredXMLSchemaName = registeredXMLSchemaName;
        registeredXMLSchemaName = newRegisteredXMLSchemaName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME, oldRegisteredXMLSchemaName, registeredXMLSchemaName));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME:
                return getSchemaName();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME:
                return getRegisteredXMLSchemaName();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME:
                setSchemaName((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME:
                setRegisteredXMLSchemaName((String)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME:
                setSchemaName(SCHEMA_NAME_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME:
                setRegisteredXMLSchemaName(REGISTERED_XML_SCHEMA_NAME_EDEFAULT);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__SCHEMA_NAME:
                return SCHEMA_NAME_EDEFAULT == null ? schemaName != null : !SCHEMA_NAME_EDEFAULT.equals(schemaName);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_IDENTIFIER__REGISTERED_XML_SCHEMA_NAME:
                return REGISTERED_XML_SCHEMA_NAME_EDEFAULT == null ? registeredXMLSchemaName != null : !REGISTERED_XML_SCHEMA_NAME_EDEFAULT.equals(registeredXMLSchemaName);
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
        result.append(" (schemaName: ");
        result.append(schemaName);
        result.append(", registeredXMLSchemaName: ");
        result.append(registeredXMLSchemaName);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionValidateAccordingToIdentifierImpl
