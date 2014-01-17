/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWColumnImpl.java,v 1.14 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ColumnImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWColumn;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWOption;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWSecurityLabel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl#isLobLogged <em>Lob Logged</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl#isLobCompacted <em>Lob Compacted</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl#getCompression <em>Compression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl#getInlineLength <em>Inline Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl#isHidden <em>Hidden</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl#getSecurityLabel <em>Security Label</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWColumnImpl#getOptions <em>Options</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWColumnImpl extends DB2ColumnImpl implements LUWColumn {
	/**
	 * The default value of the '{@link #isLobLogged() <em>Lob Logged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLobLogged()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOB_LOGGED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isLobLogged() <em>Lob Logged</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLobLogged()
	 * @generated
	 * @ordered
	 */
	protected boolean lobLogged = LOB_LOGGED_EDEFAULT;

	/**
	 * The default value of the '{@link #isLobCompacted() <em>Lob Compacted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLobCompacted()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LOB_COMPACTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLobCompacted() <em>Lob Compacted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLobCompacted()
	 * @generated
	 * @ordered
	 */
	protected boolean lobCompacted = LOB_COMPACTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompression() <em>Compression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompression()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPRESSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompression() <em>Compression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompression()
	 * @generated
	 * @ordered
	 */
	protected String compression = COMPRESSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getInlineLength() <em>Inline Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInlineLength()
	 * @generated
	 * @ordered
	 */
	protected static final int INLINE_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getInlineLength() <em>Inline Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInlineLength()
	 * @generated
	 * @ordered
	 */
	protected int inlineLength = INLINE_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected boolean hidden = HIDDEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecurityLabel() <em>Security Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURITY_LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecurityLabel() <em>Security Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurityLabel()
	 * @generated
	 * @ordered
	 */
	protected String securityLabel = SECURITY_LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected EList options;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWColumnImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_COLUMN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLobLogged() {
		return lobLogged;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLobLogged(boolean newLobLogged) {
		boolean oldLobLogged = lobLogged;
		lobLogged = newLobLogged;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_COLUMN__LOB_LOGGED, oldLobLogged, lobLogged));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLobCompacted() {
		return lobCompacted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLobCompacted(boolean newLobCompacted) {
		boolean oldLobCompacted = lobCompacted;
		lobCompacted = newLobCompacted;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_COLUMN__LOB_COMPACTED, oldLobCompacted, lobCompacted));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCompression() {
		return compression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompression(String newCompression) {
		String oldCompression = compression;
		compression = newCompression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_COLUMN__COMPRESSION, oldCompression, compression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInlineLength() {
		return inlineLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInlineLength(int newInlineLength) {
		int oldInlineLength = inlineLength;
		inlineLength = newInlineLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_COLUMN__INLINE_LENGTH, oldInlineLength, inlineLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHidden(boolean newHidden) {
		boolean oldHidden = hidden;
		hidden = newHidden;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_COLUMN__HIDDEN, oldHidden, hidden));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSecurityLabel() {
		return securityLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurityLabel(String newSecurityLabel) {
		String oldSecurityLabel = securityLabel;
		securityLabel = newSecurityLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_COLUMN__SECURITY_LABEL, oldSecurityLabel, securityLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOptions() {
		if (options == null) {
			options = new EObjectContainmentEList(LUWOption.class, this, LUWPackage.LUW_COLUMN__OPTIONS);
		}
		return options;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_COLUMN__OPTIONS:
				return ((InternalEList)getOptions()).basicRemove(otherEnd, msgs);
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
			case LUWPackage.LUW_COLUMN__LOB_LOGGED:
				return isLobLogged() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_COLUMN__LOB_COMPACTED:
				return isLobCompacted() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_COLUMN__COMPRESSION:
				return getCompression();
			case LUWPackage.LUW_COLUMN__INLINE_LENGTH:
				return new Integer(getInlineLength());
			case LUWPackage.LUW_COLUMN__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_COLUMN__SECURITY_LABEL:
				return getSecurityLabel();
			case LUWPackage.LUW_COLUMN__OPTIONS:
				return getOptions();
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
			case LUWPackage.LUW_COLUMN__LOB_LOGGED:
				setLobLogged(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_COLUMN__LOB_COMPACTED:
				setLobCompacted(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_COLUMN__COMPRESSION:
				setCompression((String)newValue);
				return;
			case LUWPackage.LUW_COLUMN__INLINE_LENGTH:
				setInlineLength(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_COLUMN__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_COLUMN__SECURITY_LABEL:
				setSecurityLabel((String)newValue);
				return;
			case LUWPackage.LUW_COLUMN__OPTIONS:
				getOptions().clear();
				getOptions().addAll((Collection)newValue);
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
			case LUWPackage.LUW_COLUMN__LOB_LOGGED:
				setLobLogged(LOB_LOGGED_EDEFAULT);
				return;
			case LUWPackage.LUW_COLUMN__LOB_COMPACTED:
				setLobCompacted(LOB_COMPACTED_EDEFAULT);
				return;
			case LUWPackage.LUW_COLUMN__COMPRESSION:
				setCompression(COMPRESSION_EDEFAULT);
				return;
			case LUWPackage.LUW_COLUMN__INLINE_LENGTH:
				setInlineLength(INLINE_LENGTH_EDEFAULT);
				return;
			case LUWPackage.LUW_COLUMN__HIDDEN:
				setHidden(HIDDEN_EDEFAULT);
				return;
			case LUWPackage.LUW_COLUMN__SECURITY_LABEL:
				setSecurityLabel(SECURITY_LABEL_EDEFAULT);
				return;
			case LUWPackage.LUW_COLUMN__OPTIONS:
				getOptions().clear();
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
			case LUWPackage.LUW_COLUMN__LOB_LOGGED:
				return lobLogged != LOB_LOGGED_EDEFAULT;
			case LUWPackage.LUW_COLUMN__LOB_COMPACTED:
				return lobCompacted != LOB_COMPACTED_EDEFAULT;
			case LUWPackage.LUW_COLUMN__COMPRESSION:
				return COMPRESSION_EDEFAULT == null ? compression != null : !COMPRESSION_EDEFAULT.equals(compression);
			case LUWPackage.LUW_COLUMN__INLINE_LENGTH:
				return inlineLength != INLINE_LENGTH_EDEFAULT;
			case LUWPackage.LUW_COLUMN__HIDDEN:
				return hidden != HIDDEN_EDEFAULT;
			case LUWPackage.LUW_COLUMN__SECURITY_LABEL:
				return SECURITY_LABEL_EDEFAULT == null ? securityLabel != null : !SECURITY_LABEL_EDEFAULT.equals(securityLabel);
			case LUWPackage.LUW_COLUMN__OPTIONS:
				return options != null && !options.isEmpty();
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
		result.append(" (lobLogged: "); //$NON-NLS-1$
		result.append(lobLogged);
		result.append(", lobCompacted: "); //$NON-NLS-1$
		result.append(lobCompacted);
		result.append(", compression: "); //$NON-NLS-1$
		result.append(compression);
		result.append(", inlineLength: "); //$NON-NLS-1$
		result.append(inlineLength);
		result.append(", hidden: "); //$NON-NLS-1$
		result.append(hidden);
		result.append(", securityLabel: "); //$NON-NLS-1$
		result.append(securityLabel);
		result.append(')');
		return result.toString();
	}

} //LUWColumnImpl
