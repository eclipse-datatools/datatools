/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionValidateAccordingToURIImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidate;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionValidateAccordingToURI;
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
 * An implementation of the model object '<em><b>XML Value Function Validate According To URI</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl#isNoNamespace <em>No Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl#getTargetNamespaceURI <em>Target Namespace URI</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionValidateAccordingToURIImpl#getSchemaLocationURI <em>Schema Location URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionValidateAccordingToURIImpl extends XMLValueFunctionValidateAccordingToImpl implements XMLValueFunctionValidateAccordingToURI {
	/**
     * The default value of the '{@link #isNoNamespace() <em>No Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNoNamespace()
     * @generated
     * @ordered
     */
    protected static final boolean NO_NAMESPACE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isNoNamespace() <em>No Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isNoNamespace()
     * @generated
     * @ordered
     */
    protected boolean noNamespace = NO_NAMESPACE_EDEFAULT;

	/**
     * The default value of the '{@link #getTargetNamespaceURI() <em>Target Namespace URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetNamespaceURI()
     * @generated
     * @ordered
     */
    protected static final String TARGET_NAMESPACE_URI_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getTargetNamespaceURI() <em>Target Namespace URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetNamespaceURI()
     * @generated
     * @ordered
     */
    protected String targetNamespaceURI = TARGET_NAMESPACE_URI_EDEFAULT;

	/**
     * The default value of the '{@link #getSchemaLocationURI() <em>Schema Location URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSchemaLocationURI()
     * @generated
     * @ordered
     */
    protected static final String SCHEMA_LOCATION_URI_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getSchemaLocationURI() <em>Schema Location URI</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSchemaLocationURI()
     * @generated
     * @ordered
     */
    protected String schemaLocationURI = SCHEMA_LOCATION_URI_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueFunctionValidateAccordingToURIImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isNoNamespace() {
        return noNamespace;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNoNamespace(boolean newNoNamespace) {
        boolean oldNoNamespace = noNamespace;
        noNamespace = newNoNamespace;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE, oldNoNamespace, noNamespace));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTargetNamespaceURI() {
        return targetNamespaceURI;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetNamespaceURI(String newTargetNamespaceURI) {
        String oldTargetNamespaceURI = targetNamespaceURI;
        targetNamespaceURI = newTargetNamespaceURI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI, oldTargetNamespaceURI, targetNamespaceURI));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSchemaLocationURI() {
        return schemaLocationURI;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSchemaLocationURI(String newSchemaLocationURI) {
        String oldSchemaLocationURI = schemaLocationURI;
        schemaLocationURI = newSchemaLocationURI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI, oldSchemaLocationURI, schemaLocationURI));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE:
                return isNoNamespace() ? Boolean.TRUE : Boolean.FALSE;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI:
                return getTargetNamespaceURI();
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI:
                return getSchemaLocationURI();
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE:
                setNoNamespace(((Boolean)newValue).booleanValue());
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI:
                setTargetNamespaceURI((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI:
                setSchemaLocationURI((String)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE:
                setNoNamespace(NO_NAMESPACE_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI:
                setTargetNamespaceURI(TARGET_NAMESPACE_URI_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI:
                setSchemaLocationURI(SCHEMA_LOCATION_URI_EDEFAULT);
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
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__NO_NAMESPACE:
                return noNamespace != NO_NAMESPACE_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__TARGET_NAMESPACE_URI:
                return TARGET_NAMESPACE_URI_EDEFAULT == null ? targetNamespaceURI != null : !TARGET_NAMESPACE_URI_EDEFAULT.equals(targetNamespaceURI);
            case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_VALIDATE_ACCORDING_TO_URI__SCHEMA_LOCATION_URI:
                return SCHEMA_LOCATION_URI_EDEFAULT == null ? schemaLocationURI != null : !SCHEMA_LOCATION_URI_EDEFAULT.equals(schemaLocationURI);
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
        result.append(" (noNamespace: ");
        result.append(noNamespace);
        result.append(", targetNamespaceURI: ");
        result.append(targetNamespaceURI);
        result.append(", schemaLocationURI: ");
        result.append(schemaLocationURI);
        result.append(')');
        return result.toString();
    }

} //XMLValueFunctionValidateAccordingToURIImpl
