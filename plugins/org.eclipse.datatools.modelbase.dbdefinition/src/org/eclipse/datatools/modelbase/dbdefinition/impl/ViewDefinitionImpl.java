/**
 * <copyright>
 * </copyright>
 *
 * $Id: ViewDefinitionImpl.java,v 1.2 2006/10/11 20:34:54 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ViewDefinitionImpl#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.ViewDefinitionImpl#isIndexSupported <em>Index Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewDefinitionImpl extends EObjectImpl implements ViewDefinition {
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
	 * The default value of the '{@link #isIndexSupported() <em>Index Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIndexSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INDEX_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIndexSupported() <em>Index Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIndexSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean indexSupported = INDEX_SUPPORTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.VIEW_DEFINITION;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.VIEW_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH, oldMaximumIdentifierLength, maximumIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIndexSupported() {
		return indexSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexSupported(boolean newIndexSupported) {
		boolean oldIndexSupported = indexSupported;
		indexSupported = newIndexSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.VIEW_DEFINITION__INDEX_SUPPORTED, oldIndexSupported, indexSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.VIEW_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return new Integer(getMaximumIdentifierLength());
			case DatabaseDefinitionPackage.VIEW_DEFINITION__INDEX_SUPPORTED:
				return isIndexSupported() ? Boolean.TRUE : Boolean.FALSE;
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
			case DatabaseDefinitionPackage.VIEW_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.VIEW_DEFINITION__INDEX_SUPPORTED:
				setIndexSupported(((Boolean)newValue).booleanValue());
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
			case DatabaseDefinitionPackage.VIEW_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.VIEW_DEFINITION__INDEX_SUPPORTED:
				setIndexSupported(INDEX_SUPPORTED_EDEFAULT);
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
			case DatabaseDefinitionPackage.VIEW_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return maximumIdentifierLength != MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.VIEW_DEFINITION__INDEX_SUPPORTED:
				return indexSupported != INDEX_SUPPORTED_EDEFAULT;
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
		result.append(" (maximumIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumIdentifierLength);
		result.append(", indexSupported: "); //$NON-NLS-1$
		result.append(indexSupported);
		result.append(')');
		return result.toString();
	}

} //ViewDefinitionImpl
