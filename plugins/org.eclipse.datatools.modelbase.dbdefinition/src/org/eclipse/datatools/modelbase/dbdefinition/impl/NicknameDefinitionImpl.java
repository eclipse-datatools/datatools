/**
 * <copyright>
 * </copyright>
 *
 * $Id: NicknameDefinitionImpl.java,v 1.1 2005/08/02 22:56:23 ledunnel Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nickname Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.NicknameDefinitionImpl#isConstraintSupported <em>Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.NicknameDefinitionImpl#isIndexSupported <em>Index Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.NicknameDefinitionImpl#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NicknameDefinitionImpl extends EObjectImpl implements NicknameDefinition {
	/**
	 * The default value of the '{@link #isConstraintSupported() <em>Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSTRAINT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConstraintSupported() <em>Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean constraintSupported = CONSTRAINT_SUPPORTED_EDEFAULT;

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
	protected NicknameDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.eINSTANCE.getNicknameDefinition();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstraintSupported() {
		return constraintSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintSupported(boolean newConstraintSupported) {
		boolean oldConstraintSupported = constraintSupported;
		constraintSupported = newConstraintSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.NICKNAME_DEFINITION__CONSTRAINT_SUPPORTED, oldConstraintSupported, constraintSupported));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.NICKNAME_DEFINITION__INDEX_SUPPORTED, oldIndexSupported, indexSupported));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.NICKNAME_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH, oldMaximumIdentifierLength, maximumIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__CONSTRAINT_SUPPORTED:
				return isConstraintSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__INDEX_SUPPORTED:
				return isIndexSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return new Integer(getMaximumIdentifierLength());
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
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__CONSTRAINT_SUPPORTED:
				setConstraintSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__INDEX_SUPPORTED:
				setIndexSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(((Integer)newValue).intValue());
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
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__CONSTRAINT_SUPPORTED:
				setConstraintSupported(CONSTRAINT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__INDEX_SUPPORTED:
				setIndexSupported(INDEX_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT);
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
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__CONSTRAINT_SUPPORTED:
				return constraintSupported != CONSTRAINT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__INDEX_SUPPORTED:
				return indexSupported != INDEX_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return maximumIdentifierLength != MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;
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
		result.append(" (constraintSupported: "); //$NON-NLS-1$
		result.append(constraintSupported);
		result.append(", indexSupported: "); //$NON-NLS-1$
		result.append(indexSupported);
		result.append(", maximumIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumIdentifierLength);
		result.append(')');
		return result.toString();
	}

} //NicknameDefinitionImpl
