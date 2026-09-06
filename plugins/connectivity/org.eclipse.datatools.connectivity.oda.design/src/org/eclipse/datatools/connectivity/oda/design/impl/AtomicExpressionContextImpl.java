/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *
 *************************************************************************
 *
 * $Id: AtomicExpressionContextImpl.java,v 1.3 2009/04/14 02:13:18 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design.impl;

import org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext;
import org.eclipse.datatools.connectivity.oda.design.DesignPackage;
import org.eclipse.datatools.connectivity.oda.design.ExpressionArguments;
import org.eclipse.datatools.connectivity.oda.design.ExpressionVariable;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Atomic Expression Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AtomicExpressionContextImpl#isOptional <em>Optional</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AtomicExpressionContextImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.impl.AtomicExpressionContextImpl#getArguments <em>Arguments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AtomicExpressionContextImpl extends EObjectImpl implements AtomicExpressionContext {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2005, 2010 Actuate Corporation"; //$NON-NLS-1$

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected boolean optional = OPTIONAL_EDEFAULT;

	/**
	 * This is true if the Optional attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean optionalESet;

	/**
	 * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected ExpressionVariable variable;

	/**
	 * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArguments()
	 * @generated
	 * @ordered
	 */
	protected ExpressionArguments arguments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AtomicExpressionContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DesignPackage.Literals.ATOMIC_EXPRESSION_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOptional() {
		return optional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOptional(boolean newOptional) {
		boolean oldOptional = optional;
		optional = newOptional;
		boolean oldOptionalESet = optionalESet;
		optionalESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL,
					oldOptional, optional, !oldOptionalESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetOptional() {
		boolean oldOptional = optional;
		boolean oldOptionalESet = optionalESet;
		optional = OPTIONAL_EDEFAULT;
		optionalESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL,
					oldOptional, OPTIONAL_EDEFAULT, oldOptionalESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetOptional() {
		return optionalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpressionVariable getVariable() {
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariable(ExpressionVariable newVariable, NotificationChain msgs) {
		ExpressionVariable oldVariable = variable;
		variable = newVariable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE, oldVariable, newVariable);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVariable(ExpressionVariable newVariable) {
		if (newVariable != variable) {
			NotificationChain msgs = null;
			if (variable != null) {
				msgs = ((InternalEObject) variable).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE, null, msgs);
			}
			if (newVariable != null) {
				msgs = ((InternalEObject) newVariable).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE, null, msgs);
			}
			msgs = basicSetVariable(newVariable, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE,
					newVariable, newVariable));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpressionArguments getArguments() {
		return arguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArguments(ExpressionArguments newArguments, NotificationChain msgs) {
		ExpressionArguments oldArguments = arguments;
		arguments = newArguments;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS, oldArguments, newArguments);
			if (msgs == null) {
				msgs = notification;
			} else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setArguments(ExpressionArguments newArguments) {
		if (newArguments != arguments) {
			NotificationChain msgs = null;
			if (arguments != null) {
				msgs = ((InternalEObject) arguments).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS, null, msgs);
			}
			if (newArguments != null) {
				msgs = ((InternalEObject) newArguments).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS, null, msgs);
			}
			msgs = basicSetArguments(newArguments, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS,
					newArguments, newArguments));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
			return basicSetVariable(null, msgs);
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
			return basicSetArguments(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL:
			return isOptional();
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
			return getVariable();
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
			return getArguments();
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
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL:
			setOptional((Boolean) newValue);
			return;
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
			setVariable((ExpressionVariable) newValue);
			return;
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
			setArguments((ExpressionArguments) newValue);
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
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL:
			unsetOptional();
			return;
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
			setVariable((ExpressionVariable) null);
			return;
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
			setArguments((ExpressionArguments) null);
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
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__OPTIONAL:
			return isSetOptional();
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__VARIABLE:
			return variable != null;
		case DesignPackage.ATOMIC_EXPRESSION_CONTEXT__ARGUMENTS:
			return arguments != null;
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
		result.append(" (optional: "); //$NON-NLS-1$
		if (optionalESet) {
			result.append(optional);
		} else {
			result.append("<unset>"); //$NON-NLS-1$
		}
		result.append(')');
		return result.toString();
	}

} //AtomicExpressionContextImpl
