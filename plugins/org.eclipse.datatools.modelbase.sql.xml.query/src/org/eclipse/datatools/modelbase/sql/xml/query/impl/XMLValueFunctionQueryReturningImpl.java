/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLValueFunctionQueryReturningImpl.java,v 1.1 2005/12/16 13:16:51 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;



import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLReturningType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQuery;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLValueFunctionQueryReturning;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Value Function Query Returning</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryReturningImpl#getReturningOption <em>Returning Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryReturningImpl#getPassingOption <em>Passing Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLValueFunctionQueryReturningImpl#getValueFunctionQuery <em>Value Function Query</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLValueFunctionQueryReturningImpl extends SQLQueryObjectImpl implements XMLValueFunctionQueryReturning {
	/**
	 * The default value of the '{@link #getReturningOption() <em>Returning Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getReturningOption()
	 * @generated
	 * @ordered
	 */
    protected static final XMLReturningType RETURNING_OPTION_EDEFAULT = XMLReturningType.RETURNING_CONTENT_LITERAL;

	/**
	 * The cached value of the '{@link #getReturningOption() <em>Returning Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getReturningOption()
	 * @generated
	 * @ordered
	 */
    protected XMLReturningType returningOption = RETURNING_OPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassingOption() <em>Passing Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPassingOption()
	 * @generated
	 * @ordered
	 */
    protected static final XMLPassingType PASSING_OPTION_EDEFAULT = XMLPassingType.BY_REF_LITERAL;

	/**
	 * The cached value of the '{@link #getPassingOption() <em>Passing Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPassingOption()
	 * @generated
	 * @ordered
	 */
    protected XMLPassingType passingOption = PASSING_OPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLValueFunctionQueryReturningImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryModelPackage.eINSTANCE.getXMLValueFunctionQueryReturning();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLReturningType getReturningOption() {
		return returningOption;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setReturningOption(XMLReturningType newReturningOption) {
		XMLReturningType oldReturningOption = returningOption;
		returningOption = newReturningOption == null ? RETURNING_OPTION_EDEFAULT : newReturningOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__RETURNING_OPTION, oldReturningOption, returningOption));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPassingType getPassingOption() {
		return passingOption;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPassingOption(XMLPassingType newPassingOption) {
		XMLPassingType oldPassingOption = passingOption;
		passingOption = newPassingOption == null ? PASSING_OPTION_EDEFAULT : newPassingOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__PASSING_OPTION, oldPassingOption, passingOption));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLValueFunctionQuery getValueFunctionQuery() {
		if (eContainerFeatureID != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY) return null;
		return (XMLValueFunctionQuery)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueFunctionQuery(XMLValueFunctionQuery newValueFunctionQuery) {
		if (newValueFunctionQuery != eContainer || (eContainerFeatureID != SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY && newValueFunctionQuery != null)) {
			if (EcoreUtil.isAncestor(this, newValueFunctionQuery))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newValueFunctionQuery != null)
				msgs = ((InternalEObject)newValueFunctionQuery).eInverseAdd(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING, XMLValueFunctionQuery.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newValueFunctionQuery, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY, newValueFunctionQuery, newValueFunctionQuery));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY:
					return eBasicSetContainer(null, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY:
					return eContainer.eInverseRemove(this, SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY__QUERY_RETURNING, XMLValueFunctionQuery.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__NAME:
				return getName();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__LABEL:
				return getLabel();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__RETURNING_OPTION:
				return getReturningOption();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__PASSING_OPTION:
				return getPassingOption();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY:
				return getValueFunctionQuery();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__RETURNING_OPTION:
				setReturningOption((XMLReturningType)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__PASSING_OPTION:
				setPassingOption((XMLPassingType)newValue);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY:
				setValueFunctionQuery((XMLValueFunctionQuery)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__RETURNING_OPTION:
				setReturningOption(RETURNING_OPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__PASSING_OPTION:
				setPassingOption(PASSING_OPTION_EDEFAULT);
				return;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY:
				setValueFunctionQuery((XMLValueFunctionQuery)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__RETURNING_OPTION:
				return returningOption != RETURNING_OPTION_EDEFAULT;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__PASSING_OPTION:
				return passingOption != PASSING_OPTION_EDEFAULT;
			case SQLXMLQueryModelPackage.XML_VALUE_FUNCTION_QUERY_RETURNING__VALUE_FUNCTION_QUERY:
				return getValueFunctionQuery() != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (returningOption: ");
		result.append(returningOption);
		result.append(", passingOption: ");
		result.append(passingOption);
		result.append(')');
		return result.toString();
	}

} //XMLValueFunctionQueryReturningImpl
