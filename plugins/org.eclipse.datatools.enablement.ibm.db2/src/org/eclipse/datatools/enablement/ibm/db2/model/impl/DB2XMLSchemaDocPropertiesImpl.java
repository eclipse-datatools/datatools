/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XMLSchemaDocPropertiesImpl.java,v 1.6 2008/01/29 00:04:55 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2XML Schema Doc Properties</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocPropertiesImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocPropertiesImpl#getXmlSchemaDoc <em>Xml Schema Doc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2XMLSchemaDocPropertiesImpl extends SQLObjectImpl implements DB2XMLSchemaDocProperties {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
   protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
   protected String value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected DB2XMLSchemaDocPropertiesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2XML_SCHEMA_DOC_PROPERTIES;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2XMLSchemaDocument getXmlSchemaDoc() {
		if (eContainerFeatureID() != DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC) return null;
		return (DB2XMLSchemaDocument)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetXmlSchemaDoc(DB2XMLSchemaDocument newXmlSchemaDoc, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newXmlSchemaDoc, DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setXmlSchemaDoc(DB2XMLSchemaDocument newXmlSchemaDoc) {
		if (newXmlSchemaDoc != eInternalContainer() || (eContainerFeatureID() != DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC && newXmlSchemaDoc != null)) {
			if (EcoreUtil.isAncestor(this, newXmlSchemaDoc))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newXmlSchemaDoc != null)
				msgs = ((InternalEObject)newXmlSchemaDoc).eInverseAdd(this, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES, DB2XMLSchemaDocument.class, msgs);
			msgs = basicSetXmlSchemaDoc(newXmlSchemaDoc, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC, newXmlSchemaDoc, newXmlSchemaDoc));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetXmlSchemaDoc((DB2XMLSchemaDocument)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC:
				return basicSetXmlSchemaDoc(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC:
				return eInternalContainer().eInverseRemove(this, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES, DB2XMLSchemaDocument.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__VALUE:
				return getValue();
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC:
				return getXmlSchemaDoc();
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__VALUE:
				setValue((String)newValue);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC:
				setXmlSchemaDoc((DB2XMLSchemaDocument)newValue);
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC:
				setXmlSchemaDoc((DB2XMLSchemaDocument)null);
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC:
				return getXmlSchemaDoc() != null;
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
		result.append(" (value: "); //$NON-NLS-1$
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //DB2XMLSchemaDocPropertiesImpl
