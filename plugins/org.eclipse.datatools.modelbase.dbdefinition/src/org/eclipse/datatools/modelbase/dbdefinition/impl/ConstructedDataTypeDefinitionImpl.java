/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constructed Data Type Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl#isArrayDatatypeSupported <em>Array Datatype Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl#isMultisetDatatypeSupported <em>Multiset Datatype Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl#isRowDatatypeSupported <em>Row Datatype Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl#isReferenceDatatypeSupported <em>Reference Datatype Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ConstructedDataTypeDefinitionImpl#isCursorDatatypeSupported <em>Cursor Datatype Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstructedDataTypeDefinitionImpl extends EObjectImpl implements ConstructedDataTypeDefinition {
	/**
	 * The default value of the '{@link #isArrayDatatypeSupported() <em>Array Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isArrayDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ARRAY_DATATYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isArrayDatatypeSupported() <em>Array Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isArrayDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean arrayDatatypeSupported = ARRAY_DATATYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isMultisetDatatypeSupported() <em>Multiset Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultisetDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTISET_DATATYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultisetDatatypeSupported() <em>Multiset Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultisetDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean multisetDatatypeSupported = MULTISET_DATATYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isRowDatatypeSupported() <em>Row Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROW_DATATYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRowDatatypeSupported() <em>Row Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRowDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean rowDatatypeSupported = ROW_DATATYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isReferenceDatatypeSupported() <em>Reference Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReferenceDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REFERENCE_DATATYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReferenceDatatypeSupported() <em>Reference Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReferenceDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean referenceDatatypeSupported = REFERENCE_DATATYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isCursorDatatypeSupported() <em>Cursor Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCursorDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CURSOR_DATATYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCursorDatatypeSupported() <em>Cursor Datatype Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCursorDatatypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean cursorDatatypeSupported = CURSOR_DATATYPE_SUPPORTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstructedDataTypeDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.CONSTRUCTED_DATA_TYPE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isArrayDatatypeSupported() {
		return arrayDatatypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayDatatypeSupported(boolean newArrayDatatypeSupported) {
		boolean oldArrayDatatypeSupported = arrayDatatypeSupported;
		arrayDatatypeSupported = newArrayDatatypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ARRAY_DATATYPE_SUPPORTED, oldArrayDatatypeSupported, arrayDatatypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMultisetDatatypeSupported() {
		return multisetDatatypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultisetDatatypeSupported(boolean newMultisetDatatypeSupported) {
		boolean oldMultisetDatatypeSupported = multisetDatatypeSupported;
		multisetDatatypeSupported = newMultisetDatatypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__MULTISET_DATATYPE_SUPPORTED, oldMultisetDatatypeSupported, multisetDatatypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRowDatatypeSupported() {
		return rowDatatypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRowDatatypeSupported(boolean newRowDatatypeSupported) {
		boolean oldRowDatatypeSupported = rowDatatypeSupported;
		rowDatatypeSupported = newRowDatatypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ROW_DATATYPE_SUPPORTED, oldRowDatatypeSupported, rowDatatypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReferenceDatatypeSupported() {
		return referenceDatatypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceDatatypeSupported(boolean newReferenceDatatypeSupported) {
		boolean oldReferenceDatatypeSupported = referenceDatatypeSupported;
		referenceDatatypeSupported = newReferenceDatatypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__REFERENCE_DATATYPE_SUPPORTED, oldReferenceDatatypeSupported, referenceDatatypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCursorDatatypeSupported() {
		return cursorDatatypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCursorDatatypeSupported(boolean newCursorDatatypeSupported) {
		boolean oldCursorDatatypeSupported = cursorDatatypeSupported;
		cursorDatatypeSupported = newCursorDatatypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__CURSOR_DATATYPE_SUPPORTED, oldCursorDatatypeSupported, cursorDatatypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ARRAY_DATATYPE_SUPPORTED:
				return isArrayDatatypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__MULTISET_DATATYPE_SUPPORTED:
				return isMultisetDatatypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ROW_DATATYPE_SUPPORTED:
				return isRowDatatypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__REFERENCE_DATATYPE_SUPPORTED:
				return isReferenceDatatypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__CURSOR_DATATYPE_SUPPORTED:
				return isCursorDatatypeSupported() ? Boolean.TRUE : Boolean.FALSE;
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
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ARRAY_DATATYPE_SUPPORTED:
				setArrayDatatypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__MULTISET_DATATYPE_SUPPORTED:
				setMultisetDatatypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ROW_DATATYPE_SUPPORTED:
				setRowDatatypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__REFERENCE_DATATYPE_SUPPORTED:
				setReferenceDatatypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__CURSOR_DATATYPE_SUPPORTED:
				setCursorDatatypeSupported(((Boolean)newValue).booleanValue());
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
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ARRAY_DATATYPE_SUPPORTED:
				setArrayDatatypeSupported(ARRAY_DATATYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__MULTISET_DATATYPE_SUPPORTED:
				setMultisetDatatypeSupported(MULTISET_DATATYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ROW_DATATYPE_SUPPORTED:
				setRowDatatypeSupported(ROW_DATATYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__REFERENCE_DATATYPE_SUPPORTED:
				setReferenceDatatypeSupported(REFERENCE_DATATYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__CURSOR_DATATYPE_SUPPORTED:
				setCursorDatatypeSupported(CURSOR_DATATYPE_SUPPORTED_EDEFAULT);
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
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ARRAY_DATATYPE_SUPPORTED:
				return arrayDatatypeSupported != ARRAY_DATATYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__MULTISET_DATATYPE_SUPPORTED:
				return multisetDatatypeSupported != MULTISET_DATATYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__ROW_DATATYPE_SUPPORTED:
				return rowDatatypeSupported != ROW_DATATYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__REFERENCE_DATATYPE_SUPPORTED:
				return referenceDatatypeSupported != REFERENCE_DATATYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION__CURSOR_DATATYPE_SUPPORTED:
				return cursorDatatypeSupported != CURSOR_DATATYPE_SUPPORTED_EDEFAULT;
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
		result.append(" (arrayDatatypeSupported: "); //$NON-NLS-1$
		result.append(arrayDatatypeSupported);
		result.append(", multisetDatatypeSupported: "); //$NON-NLS-1$
		result.append(multisetDatatypeSupported);
		result.append(", rowDatatypeSupported: "); //$NON-NLS-1$
		result.append(rowDatatypeSupported);
		result.append(", referenceDatatypeSupported: "); //$NON-NLS-1$
		result.append(referenceDatatypeSupported);
		result.append(", cursorDatatypeSupported: "); //$NON-NLS-1$
		result.append(cursorDatatypeSupported);
		result.append(')');
		return result.toString();
	}

} //ConstructedDataTypeDefinitionImpl
