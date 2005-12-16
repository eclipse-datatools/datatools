/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableColumnDefinitionRegularImpl.java,v 1.3 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLPassingType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionDefault;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionRegular;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Table Column Definition Regular</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionRegularImpl#getDataType <em>Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionRegularImpl#getPassingOption <em>Passing Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionRegularImpl#getTableColumnPattern <em>Table Column Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableColumnDefinitionRegularImpl#getColumnDefinitionDefault <em>Column Definition Default</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLTableColumnDefinitionRegularImpl extends XMLTableColumnDefinitionItemImpl implements XMLTableColumnDefinitionRegular {
	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
    protected DataType dataType = null;

	/**
	 * The default value of the '{@link #getPassingOption() <em>Passing Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPassingOption()
	 * @generated
	 * @ordered
	 */
    protected static final XMLPassingType PASSING_OPTION_EDEFAULT = XMLPassingType.BY_REF_LITERAL;

	/**
	 * The cached value of the '{@link #getPassingOption() <em>Passing Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPassingOption()
	 * @generated
	 * @ordered
	 */
    protected XMLPassingType passingOption = PASSING_OPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTableColumnPattern() <em>Table Column Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTableColumnPattern()
	 * @generated
	 * @ordered
	 */
    protected static final String TABLE_COLUMN_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTableColumnPattern() <em>Table Column Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTableColumnPattern()
	 * @generated
	 * @ordered
	 */
    protected String tableColumnPattern = TABLE_COLUMN_PATTERN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getColumnDefinitionDefault() <em>Column Definition Default</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getColumnDefinitionDefault()
	 * @generated
	 * @ordered
	 */
    protected XMLTableColumnDefinitionDefault columnDefinitionDefault = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLTableColumnDefinitionRegularImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLTableColumnDefinitionRegular();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DataType getDataType() {
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetDataType(DataType newDataType, NotificationChain msgs) {
		DataType oldDataType = dataType;
		dataType = newDataType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE, oldDataType, newDataType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDataType(DataType newDataType) {
		if (newDataType != dataType) {
			NotificationChain msgs = null;
			if (dataType != null)
				msgs = ((InternalEObject)dataType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE, null, msgs);
			if (newDataType != null)
				msgs = ((InternalEObject)newDataType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE, null, msgs);
			msgs = basicSetDataType(newDataType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE, newDataType, newDataType));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLPassingType getPassingOption() {
		return passingOption;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPassingOption(XMLPassingType newPassingOption) {
		XMLPassingType oldPassingOption = passingOption;
		passingOption = newPassingOption == null ? PASSING_OPTION_EDEFAULT : newPassingOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION, oldPassingOption, passingOption));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getTableColumnPattern() {
		return tableColumnPattern;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setTableColumnPattern(String newTableColumnPattern) {
		String oldTableColumnPattern = tableColumnPattern;
		tableColumnPattern = newTableColumnPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN, oldTableColumnPattern, tableColumnPattern));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLTableColumnDefinitionDefault getColumnDefinitionDefault() {
		return columnDefinitionDefault;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetColumnDefinitionDefault(XMLTableColumnDefinitionDefault newColumnDefinitionDefault, NotificationChain msgs) {
		XMLTableColumnDefinitionDefault oldColumnDefinitionDefault = columnDefinitionDefault;
		columnDefinitionDefault = newColumnDefinitionDefault;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT, oldColumnDefinitionDefault, newColumnDefinitionDefault);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setColumnDefinitionDefault(XMLTableColumnDefinitionDefault newColumnDefinitionDefault) {
		if (newColumnDefinitionDefault != columnDefinitionDefault) {
			NotificationChain msgs = null;
			if (columnDefinitionDefault != null)
				msgs = ((InternalEObject)columnDefinitionDefault).eInverseRemove(this, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_DEFAULT__COLUMN_DEFINITION_REGULAR, XMLTableColumnDefinitionDefault.class, msgs);
			if (newColumnDefinitionDefault != null)
				msgs = ((InternalEObject)newColumnDefinitionDefault).eInverseAdd(this, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_DEFAULT__COLUMN_DEFINITION_REGULAR, XMLTableColumnDefinitionDefault.class, msgs);
			msgs = basicSetColumnDefinitionDefault(newColumnDefinitionDefault, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT, newColumnDefinitionDefault, newColumnDefinitionDefault));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
					if (columnDefinitionDefault != null)
						msgs = ((InternalEObject)columnDefinitionDefault).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT, null, msgs);
					return basicSetColumnDefinitionDefault((XMLTableColumnDefinitionDefault)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
					return basicSetDataType(null, msgs);
				case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
					return basicSetColumnDefinitionDefault(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION:
				return getTableFunction();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
				return getDataType();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION:
				return getPassingOption();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN:
				return getTableColumnPattern();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
				return getColumnDefinitionDefault();
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
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION:
				setTableFunction((XMLTableFunction)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
				setDataType((DataType)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION:
				setPassingOption((XMLPassingType)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN:
				setTableColumnPattern((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
				setColumnDefinitionDefault((XMLTableColumnDefinitionDefault)newValue);
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
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION:
				setTableFunction((XMLTableFunction)null);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
				setDataType((DataType)null);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION:
				setPassingOption(PASSING_OPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN:
				setTableColumnPattern(TABLE_COLUMN_PATTERN_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
				setColumnDefinitionDefault((XMLTableColumnDefinitionDefault)null);
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
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_FUNCTION:
				return getTableFunction() != null;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
				return dataType != null;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION:
				return passingOption != PASSING_OPTION_EDEFAULT;
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN:
				return TABLE_COLUMN_PATTERN_EDEFAULT == null ? tableColumnPattern != null : !TABLE_COLUMN_PATTERN_EDEFAULT.equals(tableColumnPattern);
			case SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
				return columnDefinitionDefault != null;
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
		result.append(" (passingOption: ");
		result.append(passingOption);
		result.append(", tableColumnPattern: ");
		result.append(tableColumnPattern);
		result.append(')');
		return result.toString();
	}

} //XMLTableColumnDefinitionRegularImpl
