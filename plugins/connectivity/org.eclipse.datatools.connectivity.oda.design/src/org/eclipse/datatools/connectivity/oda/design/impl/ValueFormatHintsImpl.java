/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *
 *************************************************************************
 *
 * $Id: ValueFormatHintsImpl.java,v 1.3 2007/04/11 02:59:53 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.HorizontalAlignment;
import org.eclipse.datatools.connectivity.oda.design.TextFormatType;
import org.eclipse.datatools.connectivity.oda.design.TextWrapType;
import org.eclipse.datatools.connectivity.oda.design.ValueFormatHints;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Format Hints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getDisplaySize <em>Display Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getDisplayFormat <em>Display Format</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getTextFormatType <em>Text Format Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getHorizontalAlignment <em>Horizontal Alignment</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.ValueFormatHintsImpl#getTextWrapType <em>Text Wrap Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ValueFormatHintsImpl extends EObjectImpl implements ValueFormatHints {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getDisplaySize() <em>Display Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplaySize()
	 * @generated
	 * @ordered
	 */
	protected static final int DISPLAY_SIZE_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getDisplaySize() <em>Display Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplaySize()
	 * @generated
	 * @ordered
	 */
	protected int displaySize = DISPLAY_SIZE_EDEFAULT;

	/**
	 * This is true if the Display Size attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean displaySizeESet;

	/**
	 * The default value of the '{@link #getDisplayFormat() <em>Display Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayFormat()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_FORMAT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayFormat() <em>Display Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayFormat()
	 * @generated
	 * @ordered
	 */
	protected String displayFormat = DISPLAY_FORMAT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTextFormatType() <em>Text Format Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextFormatType()
	 * @generated
	 * @ordered
	 */
	protected static final TextFormatType TEXT_FORMAT_TYPE_EDEFAULT = TextFormatType.PLAIN_LITERAL;

	/**
	 * The cached value of the '{@link #getTextFormatType() <em>Text Format Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextFormatType()
	 * @generated
	 * @ordered
	 */
	protected TextFormatType textFormatType = TEXT_FORMAT_TYPE_EDEFAULT;

	/**
	 * This is true if the Text Format Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean textFormatTypeESet;

	/**
	 * The default value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizontalAlignment()
	 * @generated
	 * @ordered
	 */
	protected static final HorizontalAlignment HORIZONTAL_ALIGNMENT_EDEFAULT = HorizontalAlignment.AUTOMATIC_LITERAL;

	/**
	 * The cached value of the '{@link #getHorizontalAlignment() <em>Horizontal Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHorizontalAlignment()
	 * @generated
	 * @ordered
	 */
	protected HorizontalAlignment horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;

	/**
	 * This is true if the Horizontal Alignment attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean horizontalAlignmentESet;

	/**
	 * The default value of the '{@link #getTextWrapType() <em>Text Wrap Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextWrapType()
	 * @generated
	 * @ordered
	 */
	protected static final TextWrapType TEXT_WRAP_TYPE_EDEFAULT = TextWrapType.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getTextWrapType() <em>Text Wrap Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextWrapType()
	 * @generated
	 * @ordered
	 */
	protected TextWrapType textWrapType = TEXT_WRAP_TYPE_EDEFAULT;

	/**
	 * This is true if the Text Wrap Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean textWrapTypeESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueFormatHintsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.VALUE_FORMAT_HINTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDisplayFormat() {
		return displayFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisplayFormat(String newDisplayFormat) {
		String oldDisplayFormat = displayFormat;
		displayFormat = newDisplayFormat;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT,
					oldDisplayFormat, displayFormat));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDisplaySize() {
		return displaySize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisplaySize(int newDisplaySize) {
		int oldDisplaySize = displaySize;
		displaySize = newDisplaySize;
		boolean oldDisplaySizeESet = displaySizeESet;
		displaySizeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE,
					oldDisplaySize, displaySize, !oldDisplaySizeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDisplaySize() {
		int oldDisplaySize = displaySize;
		boolean oldDisplaySizeESet = displaySizeESet;
		displaySize = DISPLAY_SIZE_EDEFAULT;
		displaySizeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE,
					oldDisplaySize, DISPLAY_SIZE_EDEFAULT, oldDisplaySizeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDisplaySize() {
		return displaySizeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TextFormatType getTextFormatType() {
		return textFormatType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTextFormatType(TextFormatType newTextFormatType) {
		TextFormatType oldTextFormatType = textFormatType;
		textFormatType = newTextFormatType == null ? TEXT_FORMAT_TYPE_EDEFAULT : newTextFormatType;
		boolean oldTextFormatTypeESet = textFormatTypeESet;
		textFormatTypeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE,
					oldTextFormatType, textFormatType, !oldTextFormatTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTextFormatType() {
		TextFormatType oldTextFormatType = textFormatType;
		boolean oldTextFormatTypeESet = textFormatTypeESet;
		textFormatType = TEXT_FORMAT_TYPE_EDEFAULT;
		textFormatTypeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE,
					oldTextFormatType, TEXT_FORMAT_TYPE_EDEFAULT, oldTextFormatTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTextFormatType() {
		return textFormatTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHorizontalAlignment(HorizontalAlignment newHorizontalAlignment) {
		HorizontalAlignment oldHorizontalAlignment = horizontalAlignment;
		horizontalAlignment = newHorizontalAlignment == null ? HORIZONTAL_ALIGNMENT_EDEFAULT : newHorizontalAlignment;
		boolean oldHorizontalAlignmentESet = horizontalAlignmentESet;
		horizontalAlignmentESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT, oldHorizontalAlignment, horizontalAlignment,
					!oldHorizontalAlignmentESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetHorizontalAlignment() {
		HorizontalAlignment oldHorizontalAlignment = horizontalAlignment;
		boolean oldHorizontalAlignmentESet = horizontalAlignmentESet;
		horizontalAlignment = HORIZONTAL_ALIGNMENT_EDEFAULT;
		horizontalAlignmentESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET,
					DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT, oldHorizontalAlignment,
					HORIZONTAL_ALIGNMENT_EDEFAULT, oldHorizontalAlignmentESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetHorizontalAlignment() {
		return horizontalAlignmentESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TextWrapType getTextWrapType() {
		return textWrapType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTextWrapType(TextWrapType newTextWrapType) {
		TextWrapType oldTextWrapType = textWrapType;
		textWrapType = newTextWrapType == null ? TEXT_WRAP_TYPE_EDEFAULT : newTextWrapType;
		boolean oldTextWrapTypeESet = textWrapTypeESet;
		textWrapTypeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE,
					oldTextWrapType, textWrapType, !oldTextWrapTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTextWrapType() {
		TextWrapType oldTextWrapType = textWrapType;
		boolean oldTextWrapTypeESet = textWrapTypeESet;
		textWrapType = TEXT_WRAP_TYPE_EDEFAULT;
		textWrapTypeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE,
					oldTextWrapType, TEXT_WRAP_TYPE_EDEFAULT, oldTextWrapTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTextWrapType() {
		return textWrapTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE:
			return getDisplaySize();
		case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT:
			return getDisplayFormat();
		case DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE:
			return getTextFormatType();
		case DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT:
			return getHorizontalAlignment();
		case DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE:
			return getTextWrapType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE:
			setDisplaySize((Integer) newValue);
			return;
		case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT:
			setDisplayFormat((String) newValue);
			return;
		case DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE:
			setTextFormatType((TextFormatType) newValue);
			return;
		case DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT:
			setHorizontalAlignment((HorizontalAlignment) newValue);
			return;
		case DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE:
			setTextWrapType((TextWrapType) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE:
			unsetDisplaySize();
			return;
		case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT:
			setDisplayFormat(DISPLAY_FORMAT_EDEFAULT);
			return;
		case DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE:
			unsetTextFormatType();
			return;
		case DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT:
			unsetHorizontalAlignment();
			return;
		case DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE:
			unsetTextWrapType();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_SIZE:
			return isSetDisplaySize();
		case DesignPackage.VALUE_FORMAT_HINTS__DISPLAY_FORMAT:
			return DISPLAY_FORMAT_EDEFAULT == null ? displayFormat != null
					: !DISPLAY_FORMAT_EDEFAULT.equals(displayFormat);
		case DesignPackage.VALUE_FORMAT_HINTS__TEXT_FORMAT_TYPE:
			return isSetTextFormatType();
		case DesignPackage.VALUE_FORMAT_HINTS__HORIZONTAL_ALIGNMENT:
			return isSetHorizontalAlignment();
		case DesignPackage.VALUE_FORMAT_HINTS__TEXT_WRAP_TYPE:
			return isSetTextWrapType();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (displaySize: "); //$NON-NLS-1$
		if (displaySizeESet) {
			result.append(displaySize);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(", displayFormat: "); //$NON-NLS-1$
		result.append(displayFormat);
		result.append(", textFormatType: "); //$NON-NLS-1$
		if (textFormatTypeESet) {
			result.append(textFormatType);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(", horizontalAlignment: "); //$NON-NLS-1$
		if (horizontalAlignmentESet) {
			result.append(horizontalAlignment);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(", textWrapType: "); //$NON-NLS-1$
		if (textWrapTypeESet) {
			result.append(textWrapType);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(')');
		return result.toString();
	}

} //ValueFormatHintsImpl
