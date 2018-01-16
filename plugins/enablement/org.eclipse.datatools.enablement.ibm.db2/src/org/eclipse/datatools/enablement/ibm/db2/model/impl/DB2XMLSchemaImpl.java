/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2XMLSchemaImpl.java,v 1.9 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDecomposition;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaDocument;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchemaStatus;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2XML Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaImpl#getDecomposition <em>Decomposition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2XMLSchemaImpl#getXmlSchemaDocs <em>Xml Schema Docs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2XMLSchemaImpl extends DB2XSRObjectImpl implements DB2XMLSchema {
	/**
	 * The default value of the '{@link #getDecomposition() <em>Decomposition</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getDecomposition()
	 * @generated
	 * @ordered
	 */
   protected static final DB2XMLSchemaDecomposition DECOMPOSITION_EDEFAULT = DB2XMLSchemaDecomposition.ENABLED_LITERAL;

	/**
	 * The cached value of the '{@link #getDecomposition() <em>Decomposition</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getDecomposition()
	 * @generated
	 * @ordered
	 */
   protected DB2XMLSchemaDecomposition decomposition = DECOMPOSITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
   protected static final DB2XMLSchemaStatus STATUS_EDEFAULT = DB2XMLSchemaStatus.COMPLETE_LITERAL;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
   protected DB2XMLSchemaStatus status = STATUS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getXmlSchemaDocs() <em>Xml Schema Docs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getXmlSchemaDocs()
	 * @generated
	 * @ordered
	 */
   protected EList xmlSchemaDocs;

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected DB2XMLSchemaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2XML_SCHEMA;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2XMLSchemaDecomposition getDecomposition() {
		return decomposition;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setDecomposition(DB2XMLSchemaDecomposition newDecomposition) {
		DB2XMLSchemaDecomposition oldDecomposition = decomposition;
		decomposition = newDecomposition == null ? DECOMPOSITION_EDEFAULT : newDecomposition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA__DECOMPOSITION, oldDecomposition, decomposition));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2XMLSchemaStatus getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setStatus(DB2XMLSchemaStatus newStatus) {
		DB2XMLSchemaStatus oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2XML_SCHEMA__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList getXmlSchemaDocs() {
		if (xmlSchemaDocs == null) {
			xmlSchemaDocs = new EObjectContainmentWithInverseEList(DB2XMLSchemaDocument.class, this, DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS, DB2ModelPackage.DB2XML_SCHEMA_DOCUMENT__XML_SCHEMA);
		}
		return xmlSchemaDocs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS:
				return ((InternalEList)getXmlSchemaDocs()).basicAdd(otherEnd, msgs);
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
			case DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS:
				return ((InternalEList)getXmlSchemaDocs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2XML_SCHEMA__DECOMPOSITION:
				return getDecomposition();
			case DB2ModelPackage.DB2XML_SCHEMA__STATUS:
				return getStatus();
			case DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS:
				return getXmlSchemaDocs();
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
			case DB2ModelPackage.DB2XML_SCHEMA__DECOMPOSITION:
				setDecomposition((DB2XMLSchemaDecomposition)newValue);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA__STATUS:
				setStatus((DB2XMLSchemaStatus)newValue);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS:
				getXmlSchemaDocs().clear();
				getXmlSchemaDocs().addAll((Collection)newValue);
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
			case DB2ModelPackage.DB2XML_SCHEMA__DECOMPOSITION:
				setDecomposition(DECOMPOSITION_EDEFAULT);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS:
				getXmlSchemaDocs().clear();
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
			case DB2ModelPackage.DB2XML_SCHEMA__DECOMPOSITION:
				return decomposition != DECOMPOSITION_EDEFAULT;
			case DB2ModelPackage.DB2XML_SCHEMA__STATUS:
				return status != STATUS_EDEFAULT;
			case DB2ModelPackage.DB2XML_SCHEMA__XML_SCHEMA_DOCS:
				return xmlSchemaDocs != null && !xmlSchemaDocs.isEmpty();
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
		result.append(" (decomposition: "); //$NON-NLS-1$
		result.append(decomposition);
		result.append(", status: "); //$NON-NLS-1$
		result.append(status);
		result.append(')');
		return result.toString();
	}

} //DB2XMLSchemaImpl
