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
 * $Id: InputElementUIHintsImpl.java,v 1.3 2007/04/11 02:59:52 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.InputElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.InputElementUIHints;
import org.eclipse.datatools.connectivity.oda.design.InputPromptControlStyle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Element UI Hints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementUIHintsImpl#getPromptStyle <em>Prompt Style</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.InputElementUIHintsImpl#getAutoSuggestThreshold <em>Auto Suggest Threshold</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InputElementUIHintsImpl extends EObjectImpl implements InputElementUIHints {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #getPromptStyle() <em>Prompt Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPromptStyle()
	 * @generated
	 * @ordered
	 */
	protected static final InputPromptControlStyle PROMPT_STYLE_EDEFAULT = InputPromptControlStyle.TEXT_FIELD_LITERAL;

	/**
	 * The cached value of the '{@link #getPromptStyle() <em>Prompt Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPromptStyle()
	 * @generated
	 * @ordered
	 */
	protected InputPromptControlStyle promptStyle = PROMPT_STYLE_EDEFAULT;

	/**
	 * This is true if the Prompt Style attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean promptStyleESet;

	/**
	 * The default value of the '{@link #getAutoSuggestThreshold() <em>Auto Suggest Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoSuggestThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final int AUTO_SUGGEST_THRESHOLD_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getAutoSuggestThreshold() <em>Auto Suggest Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAutoSuggestThreshold()
	 * @generated
	 * @ordered
	 */
	protected int autoSuggestThreshold = AUTO_SUGGEST_THRESHOLD_EDEFAULT;

	/**
	 * This is true if the Auto Suggest Threshold attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean autoSuggestThresholdESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputElementUIHintsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.INPUT_ELEMENT_UI_HINTS;
	}

	/**
	 * Returns the prompt style if set, or defaults to
	 * to TextField if no static and dynamic value choices are
	 * defined in container element, InputElementAttributes;
	 * otherwise, defaults to SelectableList.
	 */
	@Override
	public InputPromptControlStyle getPromptStyle() {
		if (isSetPromptStyle()) {
			return getPromptStyleGen();
		}

		// not set, applies default based on settings of
		// the container element, InputElementAttributes
		assert (eContainer() instanceof InputElementAttributes);
		InputElementAttributes parent = (InputElementAttributes) eContainer();
		return parent.hasValueChoices() ? InputPromptControlStyle.SELECTABLE_LIST_LITERAL
				: InputPromptControlStyle.TEXT_FIELD_LITERAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputPromptControlStyle getPromptStyleGen() {
		return promptStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPromptStyle(InputPromptControlStyle newPromptStyle) {
		InputPromptControlStyle oldPromptStyle = promptStyle;
		promptStyle = newPromptStyle == null ? PROMPT_STYLE_EDEFAULT : newPromptStyle;
		boolean oldPromptStyleESet = promptStyleESet;
		promptStyleESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE,
					oldPromptStyle, promptStyle, !oldPromptStyleESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPromptStyle() {
		InputPromptControlStyle oldPromptStyle = promptStyle;
		boolean oldPromptStyleESet = promptStyleESet;
		promptStyle = PROMPT_STYLE_EDEFAULT;
		promptStyleESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE,
					oldPromptStyle, PROMPT_STYLE_EDEFAULT, oldPromptStyleESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPromptStyle() {
		return promptStyleESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getAutoSuggestThreshold() {
		return autoSuggestThreshold;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAutoSuggestThreshold(int newAutoSuggestThreshold) {
		int oldAutoSuggestThreshold = autoSuggestThreshold;
		autoSuggestThreshold = newAutoSuggestThreshold;
		boolean oldAutoSuggestThresholdESet = autoSuggestThresholdESet;
		autoSuggestThresholdESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					DesignPackage.INPUT_ELEMENT_UI_HINTS__AUTO_SUGGEST_THRESHOLD, oldAutoSuggestThreshold,
					autoSuggestThreshold, !oldAutoSuggestThresholdESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAutoSuggestThreshold() {
		int oldAutoSuggestThreshold = autoSuggestThreshold;
		boolean oldAutoSuggestThresholdESet = autoSuggestThresholdESet;
		autoSuggestThreshold = AUTO_SUGGEST_THRESHOLD_EDEFAULT;
		autoSuggestThresholdESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET,
					DesignPackage.INPUT_ELEMENT_UI_HINTS__AUTO_SUGGEST_THRESHOLD, oldAutoSuggestThreshold,
					AUTO_SUGGEST_THRESHOLD_EDEFAULT, oldAutoSuggestThresholdESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAutoSuggestThreshold() {
		return autoSuggestThresholdESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE:
			return getPromptStyle();
		case DesignPackage.INPUT_ELEMENT_UI_HINTS__AUTO_SUGGEST_THRESHOLD:
			return getAutoSuggestThreshold();
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
		case DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE:
			setPromptStyle((InputPromptControlStyle) newValue);
			return;
		case DesignPackage.INPUT_ELEMENT_UI_HINTS__AUTO_SUGGEST_THRESHOLD:
			setAutoSuggestThreshold((Integer) newValue);
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
		case DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE:
			unsetPromptStyle();
			return;
		case DesignPackage.INPUT_ELEMENT_UI_HINTS__AUTO_SUGGEST_THRESHOLD:
			unsetAutoSuggestThreshold();
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
		case DesignPackage.INPUT_ELEMENT_UI_HINTS__PROMPT_STYLE:
			return isSetPromptStyle();
		case DesignPackage.INPUT_ELEMENT_UI_HINTS__AUTO_SUGGEST_THRESHOLD:
			return isSetAutoSuggestThreshold();
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
		result.append(" (promptStyle: "); //$NON-NLS-1$
		if (promptStyleESet) {
			result.append(promptStyle);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(", autoSuggestThreshold: "); //$NON-NLS-1$
		if (autoSuggestThresholdESet) {
			result.append(autoSuggestThreshold);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(')');
		return result.toString();
	}

} //InputElementUIHintsImpl
