/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.TableDefinition;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl#isAuditSupported <em>Audit Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl#isDataCaptureSupported <em>Data Capture Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl#isEditProcSupported <em>Edit Proc Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl#isEncodingSupported <em>Encoding Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl#isValidProcSupported <em>Valid Proc Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.TableDefinitionImpl#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TableDefinitionImpl extends EObjectImpl implements TableDefinition {
	/**
	 * The default value of the '{@link #isAuditSupported() <em>Audit Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAuditSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUDIT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAuditSupported() <em>Audit Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAuditSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean auditSupported = AUDIT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isDataCaptureSupported() <em>Data Capture Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDataCaptureSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DATA_CAPTURE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDataCaptureSupported() <em>Data Capture Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDataCaptureSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean dataCaptureSupported = DATA_CAPTURE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isEditProcSupported() <em>Edit Proc Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditProcSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EDIT_PROC_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEditProcSupported() <em>Edit Proc Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEditProcSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean editProcSupported = EDIT_PROC_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isEncodingSupported() <em>Encoding Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEncodingSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENCODING_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEncodingSupported() <em>Encoding Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEncodingSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean encodingSupported = ENCODING_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isValidProcSupported() <em>Valid Proc Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValidProcSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VALID_PROC_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isValidProcSupported() <em>Valid Proc Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isValidProcSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean validProcSupported = VALID_PROC_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.TABLE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAuditSupported() {
		return auditSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuditSupported(boolean newAuditSupported) {
		boolean oldAuditSupported = auditSupported;
		auditSupported = newAuditSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_DEFINITION__AUDIT_SUPPORTED, oldAuditSupported, auditSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDataCaptureSupported() {
		return dataCaptureSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataCaptureSupported(boolean newDataCaptureSupported) {
		boolean oldDataCaptureSupported = dataCaptureSupported;
		dataCaptureSupported = newDataCaptureSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_DEFINITION__DATA_CAPTURE_SUPPORTED, oldDataCaptureSupported, dataCaptureSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEditProcSupported() {
		return editProcSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditProcSupported(boolean newEditProcSupported) {
		boolean oldEditProcSupported = editProcSupported;
		editProcSupported = newEditProcSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_DEFINITION__EDIT_PROC_SUPPORTED, oldEditProcSupported, editProcSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEncodingSupported() {
		return encodingSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncodingSupported(boolean newEncodingSupported) {
		boolean oldEncodingSupported = encodingSupported;
		encodingSupported = newEncodingSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_DEFINITION__ENCODING_SUPPORTED, oldEncodingSupported, encodingSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isValidProcSupported() {
		return validProcSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValidProcSupported(boolean newValidProcSupported) {
		boolean oldValidProcSupported = validProcSupported;
		validProcSupported = newValidProcSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_DEFINITION__VALID_PROC_SUPPORTED, oldValidProcSupported, validProcSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumIdentifierLength() {
		return maximumIdentifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumIdentifierLength(int newMaximumIdentifierLength) {
		int oldMaximumIdentifierLength = maximumIdentifierLength;
		maximumIdentifierLength = newMaximumIdentifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.TABLE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH, oldMaximumIdentifierLength, maximumIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.TABLE_DEFINITION__AUDIT_SUPPORTED:
				return isAuditSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__DATA_CAPTURE_SUPPORTED:
				return isDataCaptureSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__EDIT_PROC_SUPPORTED:
				return isEditProcSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__ENCODING_SUPPORTED:
				return isEncodingSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__VALID_PROC_SUPPORTED:
				return isValidProcSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return new Integer(getMaximumIdentifierLength());
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
			case DatabaseDefinitionPackage.TABLE_DEFINITION__AUDIT_SUPPORTED:
				setAuditSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__DATA_CAPTURE_SUPPORTED:
				setDataCaptureSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__EDIT_PROC_SUPPORTED:
				setEditProcSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__ENCODING_SUPPORTED:
				setEncodingSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__VALID_PROC_SUPPORTED:
				setValidProcSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(((Integer)newValue).intValue());
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
			case DatabaseDefinitionPackage.TABLE_DEFINITION__AUDIT_SUPPORTED:
				setAuditSupported(AUDIT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__DATA_CAPTURE_SUPPORTED:
				setDataCaptureSupported(DATA_CAPTURE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__EDIT_PROC_SUPPORTED:
				setEditProcSupported(EDIT_PROC_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__ENCODING_SUPPORTED:
				setEncodingSupported(ENCODING_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__VALID_PROC_SUPPORTED:
				setValidProcSupported(VALID_PROC_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT);
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
			case DatabaseDefinitionPackage.TABLE_DEFINITION__AUDIT_SUPPORTED:
				return auditSupported != AUDIT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__DATA_CAPTURE_SUPPORTED:
				return dataCaptureSupported != DATA_CAPTURE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__EDIT_PROC_SUPPORTED:
				return editProcSupported != EDIT_PROC_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__ENCODING_SUPPORTED:
				return encodingSupported != ENCODING_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__VALID_PROC_SUPPORTED:
				return validProcSupported != VALID_PROC_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.TABLE_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return maximumIdentifierLength != MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;
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
		result.append(" (auditSupported: "); //$NON-NLS-1$
		result.append(auditSupported);
		result.append(", dataCaptureSupported: "); //$NON-NLS-1$
		result.append(dataCaptureSupported);
		result.append(", editProcSupported: "); //$NON-NLS-1$
		result.append(editProcSupported);
		result.append(", encodingSupported: "); //$NON-NLS-1$
		result.append(encodingSupported);
		result.append(", validProcSupported: "); //$NON-NLS-1$
		result.append(validProcSupported);
		result.append(", maximumIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumIdentifierLength);
		result.append(')');
		return result.toString();
	}

} //TableDefinitionImpl
