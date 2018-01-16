/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XMLSchemaDocumentImpl.java,v 1.7 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocProperties;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2XML Schema Document</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl#getSchemaLocation <em>Schema Location</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl#isPrimary <em>Primary</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl#getXmlSchema <em>Xml Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaDocumentImpl#getXmlSchemaDocProperties <em>Xml Schema Doc Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2XMLSchemaDocumentImpl extends SQLObjectImpl implements DB2XMLSchemaDocument {
	/**
	 * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
   protected static final String FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
   protected String fileName = FILE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSchemaLocation() <em>Schema Location</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getSchemaLocation()
	 * @generated
	 * @ordered
	 */
   protected static final String SCHEMA_LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSchemaLocation() <em>Schema Location</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getSchemaLocation()
	 * @generated
	 * @ordered
	 */
   protected String schemaLocation = SCHEMA_LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getTargetNamespace()
	 * @generated
	 * @ordered
	 */
   protected static final String TARGET_NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getTargetNamespace()
	 * @generated
	 * @ordered
	 */
   protected String targetNamespace = TARGET_NAMESPACE_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrimary() <em>Primary</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isPrimary()
	 * @generated
	 * @ordered
	 */
   protected static final boolean PRIMARY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrimary() <em>Primary</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isPrimary()
	 * @generated
	 * @ordered
	 */
   protected boolean primary = PRIMARY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getXmlSchemaDocProperties() <em>Xml Schema Doc Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getXmlSchemaDocProperties()
	 * @generated
	 * @ordered
	 */
   protected EList xmlSchemaDocProperties;

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected DB2XMLSchemaDocumentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2XML_SCHEMA_DOCUMENT;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getFileName() {
		return fileName;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setFileName(String newFileName) {
		String oldFileName = fileName;
		fileName = newFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__FILE_NAME, oldFileName, fileName));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getSchemaLocation() {
		return schemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setSchemaLocation(String newSchemaLocation) {
		String oldSchemaLocation = schemaLocation;
		schemaLocation = newSchemaLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__SCHEMA_LOCATION, oldSchemaLocation, schemaLocation));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getTargetNamespace() {
		return targetNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setTargetNamespace(String newTargetNamespace) {
		String oldTargetNamespace = targetNamespace;
		targetNamespace = newTargetNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__TARGET_NAMESPACE, oldTargetNamespace, targetNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public boolean isPrimary() {
		return primary;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setPrimary(boolean newPrimary) {
		boolean oldPrimary = primary;
		primary = newPrimary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__PRIMARY, oldPrimary, primary));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2XMLSchema getXmlSchema() {
		if (eContainerFeatureID() != DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA) return null;
		return (DB2XMLSchema)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetXmlSchema(DB2XMLSchema newXmlSchema, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newXmlSchema, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setXmlSchema(DB2XMLSchema newXmlSchema) {
		if (newXmlSchema != eInternalContainer() || (eContainerFeatureID() != DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA && newXmlSchema != null)) {
			if (EcoreUtil.isAncestor(this, newXmlSchema))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newXmlSchema != null)
				msgs = ((InternalEObject)newXmlSchema).eInverseAdd(this, DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS, DB2XMLSchema.class, msgs);
			msgs = basicSetXmlSchema(newXmlSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA, newXmlSchema, newXmlSchema));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList getXmlSchemaDocProperties() {
		if (xmlSchemaDocProperties == null) {
			xmlSchemaDocProperties = new EObjectContainmentWithInverseEList(DB2XMLSchemaDocProperties.class, this, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES, DB2ModelPackage.DB2XML_SCHEMA_DOC_PROPERTIES__XML_SCHEMA_DOC);
		}
		return xmlSchemaDocProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetXmlSchema((DB2XMLSchema)otherEnd, msgs);
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES:
				return ((InternalEList)getXmlSchemaDocProperties()).basicAdd(otherEnd, msgs);
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA:
				return basicSetXmlSchema(null, msgs);
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES:
				return ((InternalEList)getXmlSchemaDocProperties()).basicRemove(otherEnd, msgs);
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA:
				return eInternalContainer().eInverseRemove(this, DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS, DB2XMLSchema.class, msgs);
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__FILE_NAME:
				return getFileName();
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__SCHEMA_LOCATION:
				return getSchemaLocation();
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__TARGET_NAMESPACE:
				return getTargetNamespace();
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__PRIMARY:
				return isPrimary() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA:
				return getXmlSchema();
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES:
				return getXmlSchemaDocProperties();
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__FILE_NAME:
				setFileName((String)newValue);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__SCHEMA_LOCATION:
				setSchemaLocation((String)newValue);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__TARGET_NAMESPACE:
				setTargetNamespace((String)newValue);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__PRIMARY:
				setPrimary(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA:
				setXmlSchema((DB2XMLSchema)newValue);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES:
				getXmlSchemaDocProperties().clear();
				getXmlSchemaDocProperties().addAll((Collection)newValue);
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__SCHEMA_LOCATION:
				setSchemaLocation(SCHEMA_LOCATION_EDEFAULT);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__TARGET_NAMESPACE:
				setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__PRIMARY:
				setPrimary(PRIMARY_EDEFAULT);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA:
				setXmlSchema((DB2XMLSchema)null);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES:
				getXmlSchemaDocProperties().clear();
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
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__SCHEMA_LOCATION:
				return SCHEMA_LOCATION_EDEFAULT == null ? schemaLocation != null : !SCHEMA_LOCATION_EDEFAULT.equals(schemaLocation);
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__TARGET_NAMESPACE:
				return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__PRIMARY:
				return primary != PRIMARY_EDEFAULT;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA:
				return getXmlSchema() != null;
			case DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA_DOC_PROPERTIES:
				return xmlSchemaDocProperties != null && !xmlSchemaDocProperties.isEmpty();
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
		result.append(" (fileName: "); //$NON-NLS-1$
		result.append(fileName);
		result.append(", schemaLocation: "); //$NON-NLS-1$
		result.append(schemaLocation);
		result.append(", targetNamespace: "); //$NON-NLS-1$
		result.append(targetNamespace);
		result.append(", primary: "); //$NON-NLS-1$
		result.append(primary);
		result.append(')');
		return result.toString();
	}

} //DB2XMLSchemaDocumentImpl
