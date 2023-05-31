/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueExpressionCastImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCastImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueExpressionCast;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Expression Cast</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueExpressionCastImpl#getPassingMechanism <em>Passing Mechanism</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueExpressionCastImpl extends ValueExpressionCastImpl implements XMLValueExpressionCast {
	/**
     * The default value of the '{@link #getPassingMechanism() <em>Passing Mechanism</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPassingMechanism()
     * @generated
     * @ordered
     */
    protected static final XMLPassingType PASSING_MECHANISM_EDEFAULT = XMLPassingType.BY_REF_LITERAL;

	/**
     * The cached value of the '{@link #getPassingMechanism() <em>Passing Mechanism</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPassingMechanism()
     * @generated
     * @ordered
     */
    protected XMLPassingType passingMechanism = PASSING_MECHANISM_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLValueExpressionCastImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_VALUE_EXPRESSION_CAST;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLPassingType getPassingMechanism() {
        return passingMechanism;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPassingMechanism(XMLPassingType newPassingMechanism) {
        XMLPassingType oldPassingMechanism = passingMechanism;
        passingMechanism = newPassingMechanism == null ? PASSING_MECHANISM_EDEFAULT : newPassingMechanism;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_EXPRESSION_CAST__PASSING_MECHANISM, oldPassingMechanism, passingMechanism));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_VALUE_EXPRESSION_CAST__PASSING_MECHANISM:
                return getPassingMechanism();
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
            case SQLXMLQueryModelPackage.XML_VALUE_EXPRESSION_CAST__PASSING_MECHANISM:
                setPassingMechanism((XMLPassingType)newValue);
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
            case SQLXMLQueryModelPackage.XML_VALUE_EXPRESSION_CAST__PASSING_MECHANISM:
                setPassingMechanism(PASSING_MECHANISM_EDEFAULT);
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
            case SQLXMLQueryModelPackage.XML_VALUE_EXPRESSION_CAST__PASSING_MECHANISM:
                return passingMechanism != PASSING_MECHANISM_EDEFAULT;
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
        result.append(" (passingMechanism: ");
        result.append(passingMechanism);
        result.append(')');
        return result.toString();
    }

} //XMLValueExpressionCastImpl
