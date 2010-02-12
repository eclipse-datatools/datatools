/**
 * <copyright>
 * </copyright>
 *
 * $Id: FieldQualifierDefinitionImpl.java,v 1.2 2006/10/11 20:34:54 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition;

import org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field Qualifier Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl#getValidTrailingFieldQualifierDefinitions <em>Valid Trailing Field Qualifier Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl#getMaximumPrecision <em>Maximum Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl#getDefaultPrecision <em>Default Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl#isPrecisionSupported <em>Precision Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl#getMaximumScale <em>Maximum Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl#getDefaultScale <em>Default Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.FieldQualifierDefinitionImpl#isScaleSupported <em>Scale Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldQualifierDefinitionImpl extends EObjectImpl implements FieldQualifierDefinition {
	/**
	 * The cached value of the '{@link #getValidTrailingFieldQualifierDefinitions() <em>Valid Trailing Field Qualifier Definitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValidTrailingFieldQualifierDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList validTrailingFieldQualifierDefinitions;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final IntervalQualifierType NAME_EDEFAULT = IntervalQualifierType.YEAR_LITERAL;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected IntervalQualifierType name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumPrecision() <em>Maximum Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumPrecision() <em>Maximum Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPrecision()
	 * @generated
	 * @ordered
	 */
	protected int maximumPrecision = MAXIMUM_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultPrecision() <em>Default Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int DEFAULT_PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDefaultPrecision() <em>Default Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultPrecision()
	 * @generated
	 * @ordered
	 */
	protected int defaultPrecision = DEFAULT_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrecisionSupported() <em>Precision Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrecisionSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRECISION_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrecisionSupported() <em>Precision Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrecisionSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean precisionSupported = PRECISION_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumScale() <em>Maximum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumScale()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_SCALE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumScale() <em>Maximum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumScale()
	 * @generated
	 * @ordered
	 */
	protected int maximumScale = MAXIMUM_SCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultScale() <em>Default Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultScale()
	 * @generated
	 * @ordered
	 */
	protected static final int DEFAULT_SCALE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDefaultScale() <em>Default Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultScale()
	 * @generated
	 * @ordered
	 */
	protected int defaultScale = DEFAULT_SCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #isScaleSupported() <em>Scale Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScaleSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCALE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isScaleSupported() <em>Scale Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScaleSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean scaleSupported = SCALE_SUPPORTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldQualifierDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.FIELD_QUALIFIER_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getValidTrailingFieldQualifierDefinitions() {
		if (validTrailingFieldQualifierDefinitions == null) {
			validTrailingFieldQualifierDefinitions = new EObjectResolvingEList(FieldQualifierDefinition.class, this, DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__VALID_TRAILING_FIELD_QUALIFIER_DEFINITIONS);
		}
		return validTrailingFieldQualifierDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntervalQualifierType getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(IntervalQualifierType newName) {
		IntervalQualifierType oldName = name;
		name = newName == null ? NAME_EDEFAULT : newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumPrecision() {
		return maximumPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumPrecision(int newMaximumPrecision) {
		int oldMaximumPrecision = maximumPrecision;
		maximumPrecision = newMaximumPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_PRECISION, oldMaximumPrecision, maximumPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDefaultPrecision() {
		return defaultPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultPrecision(int newDefaultPrecision) {
		int oldDefaultPrecision = defaultPrecision;
		defaultPrecision = newDefaultPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_PRECISION, oldDefaultPrecision, defaultPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrecisionSupported() {
		return precisionSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecisionSupported(boolean newPrecisionSupported) {
		boolean oldPrecisionSupported = precisionSupported;
		precisionSupported = newPrecisionSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__PRECISION_SUPPORTED, oldPrecisionSupported, precisionSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumScale() {
		return maximumScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumScale(int newMaximumScale) {
		int oldMaximumScale = maximumScale;
		maximumScale = newMaximumScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_SCALE, oldMaximumScale, maximumScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDefaultScale() {
		return defaultScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultScale(int newDefaultScale) {
		int oldDefaultScale = defaultScale;
		defaultScale = newDefaultScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_SCALE, oldDefaultScale, defaultScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isScaleSupported() {
		return scaleSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScaleSupported(boolean newScaleSupported) {
		boolean oldScaleSupported = scaleSupported;
		scaleSupported = newScaleSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__SCALE_SUPPORTED, oldScaleSupported, scaleSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__VALID_TRAILING_FIELD_QUALIFIER_DEFINITIONS:
				return getValidTrailingFieldQualifierDefinitions();
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__NAME:
				return getName();
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_PRECISION:
				return new Integer(getMaximumPrecision());
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_PRECISION:
				return new Integer(getDefaultPrecision());
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__PRECISION_SUPPORTED:
				return isPrecisionSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_SCALE:
				return new Integer(getMaximumScale());
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_SCALE:
				return new Integer(getDefaultScale());
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__SCALE_SUPPORTED:
				return isScaleSupported() ? Boolean.TRUE : Boolean.FALSE;
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
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__VALID_TRAILING_FIELD_QUALIFIER_DEFINITIONS:
				getValidTrailingFieldQualifierDefinitions().clear();
				getValidTrailingFieldQualifierDefinitions().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__NAME:
				setName((IntervalQualifierType)newValue);
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_PRECISION:
				setMaximumPrecision(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_PRECISION:
				setDefaultPrecision(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__PRECISION_SUPPORTED:
				setPrecisionSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_SCALE:
				setMaximumScale(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_SCALE:
				setDefaultScale(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__SCALE_SUPPORTED:
				setScaleSupported(((Boolean)newValue).booleanValue());
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
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__VALID_TRAILING_FIELD_QUALIFIER_DEFINITIONS:
				getValidTrailingFieldQualifierDefinitions().clear();
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_PRECISION:
				setMaximumPrecision(MAXIMUM_PRECISION_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_PRECISION:
				setDefaultPrecision(DEFAULT_PRECISION_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__PRECISION_SUPPORTED:
				setPrecisionSupported(PRECISION_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_SCALE:
				setMaximumScale(MAXIMUM_SCALE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_SCALE:
				setDefaultScale(DEFAULT_SCALE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__SCALE_SUPPORTED:
				setScaleSupported(SCALE_SUPPORTED_EDEFAULT);
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
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__VALID_TRAILING_FIELD_QUALIFIER_DEFINITIONS:
				return validTrailingFieldQualifierDefinitions != null && !validTrailingFieldQualifierDefinitions.isEmpty();
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__NAME:
				return name != NAME_EDEFAULT;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_PRECISION:
				return maximumPrecision != MAXIMUM_PRECISION_EDEFAULT;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_PRECISION:
				return defaultPrecision != DEFAULT_PRECISION_EDEFAULT;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__PRECISION_SUPPORTED:
				return precisionSupported != PRECISION_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__MAXIMUM_SCALE:
				return maximumScale != MAXIMUM_SCALE_EDEFAULT;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__DEFAULT_SCALE:
				return defaultScale != DEFAULT_SCALE_EDEFAULT;
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION__SCALE_SUPPORTED:
				return scaleSupported != SCALE_SUPPORTED_EDEFAULT;
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
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", maximumPrecision: "); //$NON-NLS-1$
		result.append(maximumPrecision);
		result.append(", defaultPrecision: "); //$NON-NLS-1$
		result.append(defaultPrecision);
		result.append(", precisionSupported: "); //$NON-NLS-1$
		result.append(precisionSupported);
		result.append(", maximumScale: "); //$NON-NLS-1$
		result.append(maximumScale);
		result.append(", defaultScale: "); //$NON-NLS-1$
		result.append(defaultScale);
		result.append(", scaleSupported: "); //$NON-NLS-1$
		result.append(scaleSupported);
		result.append(')');
		return result.toString();
	}

} //FieldQualifierDefinitionImpl
