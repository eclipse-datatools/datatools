/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWAttributeDefinitionImpl.java,v 1.6 2008/01/29 00:04:54 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.AttributeDefinitionImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWAttributeDefinition;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAttributeDefinitionImpl#isLOBLogged <em>LOB Logged</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWAttributeDefinitionImpl#isLOBCompacted <em>LOB Compacted</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWAttributeDefinitionImpl extends AttributeDefinitionImpl implements LUWAttributeDefinition {
	/**
	 * The default value of the '{@link #isLOBLogged() <em>LOB Logged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLOBLogged()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOB_LOGGED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isLOBLogged() <em>LOB Logged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLOBLogged()
	 * @generated
	 * @ordered
	 */
	protected boolean lobLogged = LOB_LOGGED_EDEFAULT;

	/**
	 * The default value of the '{@link #isLOBCompacted() <em>LOB Compacted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLOBCompacted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOB_COMPACTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLOBCompacted() <em>LOB Compacted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLOBCompacted()
	 * @generated
	 * @ordered
	 */
	protected boolean lobCompacted = LOB_COMPACTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWAttributeDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_ATTRIBUTE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLOBLogged() {
		return lobLogged;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLOBLogged(boolean newLOBLogged) {
		boolean oldLOBLogged = lobLogged;
		lobLogged = newLOBLogged;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_LOGGED, oldLOBLogged, lobLogged));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLOBCompacted() {
		return lobCompacted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLOBCompacted(boolean newLOBCompacted) {
		boolean oldLOBCompacted = lobCompacted;
		lobCompacted = newLOBCompacted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_COMPACTED, oldLOBCompacted, lobCompacted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_LOGGED:
				return isLOBLogged() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_COMPACTED:
				return isLOBCompacted() ? Boolean.TRUE : Boolean.FALSE;
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
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_LOGGED:
				setLOBLogged(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_COMPACTED:
				setLOBCompacted(((Boolean)newValue).booleanValue());
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
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_LOGGED:
				setLOBLogged(LOB_LOGGED_EDEFAULT);
				return;
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_COMPACTED:
				setLOBCompacted(LOB_COMPACTED_EDEFAULT);
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
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_LOGGED:
				return lobLogged != LOB_LOGGED_EDEFAULT;
			case LUWPackage.LUW_ATTRIBUTE_DEFINITION__LOB_COMPACTED:
				return lobCompacted != LOB_COMPACTED_EDEFAULT;
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
		result.append(" (LOBLogged: "); //$NON-NLS-1$
		result.append(lobLogged);
		result.append(", LOBCompacted: "); //$NON-NLS-1$
		result.append(lobCompacted);
		result.append(')');
		return result.toString();
	}

} //LUWAttributeDefinitionImpl
