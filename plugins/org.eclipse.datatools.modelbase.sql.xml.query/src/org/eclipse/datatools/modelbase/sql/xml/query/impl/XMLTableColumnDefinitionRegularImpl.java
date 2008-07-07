/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableColumnDefinitionRegularImpl.java,v 1.4 2007/02/08 17:04:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;


import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
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
    protected DataType dataType;

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
    protected XMLTableColumnDefinitionDefault columnDefinitionDefault;

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
        return SQLXMLQueryModelPackage.Literals.XML_TABLE_COLUMN_DEFINITION_REGULAR;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE, oldDataType, newDataType);
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
                msgs = ((InternalEObject)dataType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE, null, msgs);
            if (newDataType != null)
                msgs = ((InternalEObject)newDataType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE, null, msgs);
            msgs = basicSetDataType(newDataType, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE, newDataType, newDataType));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION, oldPassingOption, passingOption));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN, oldTableColumnPattern, tableColumnPattern));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT, oldColumnDefinitionDefault, newColumnDefinitionDefault);
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
                msgs = ((InternalEObject)columnDefinitionDefault).eInverseRemove(this, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_DEFAULT__COLUMN_DEFINITION_REGULAR, XMLTableColumnDefinitionDefault.class, msgs);
            if (newColumnDefinitionDefault != null)
                msgs = ((InternalEObject)newColumnDefinitionDefault).eInverseAdd(this, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_DEFAULT__COLUMN_DEFINITION_REGULAR, XMLTableColumnDefinitionDefault.class, msgs);
            msgs = basicSetColumnDefinitionDefault(newColumnDefinitionDefault, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT, newColumnDefinitionDefault, newColumnDefinitionDefault));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
                if (columnDefinitionDefault != null)
                    msgs = ((InternalEObject)columnDefinitionDefault).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT, null, msgs);
                return basicSetColumnDefinitionDefault((XMLTableColumnDefinitionDefault)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
                return basicSetDataType(null, msgs);
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
                return basicSetColumnDefinitionDefault(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
                return getDataType();
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION:
                return getPassingOption();
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN:
                return getTableColumnPattern();
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
                return getColumnDefinitionDefault();
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
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
                setDataType((DataType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION:
                setPassingOption((XMLPassingType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN:
                setTableColumnPattern((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
                setColumnDefinitionDefault((XMLTableColumnDefinitionDefault)newValue);
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
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
                setDataType((DataType)null);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION:
                setPassingOption(PASSING_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN:
                setTableColumnPattern(TABLE_COLUMN_PATTERN_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
                setColumnDefinitionDefault((XMLTableColumnDefinitionDefault)null);
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
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__DATA_TYPE:
                return dataType != null;
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__PASSING_OPTION:
                return passingOption != PASSING_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__TABLE_COLUMN_PATTERN:
                return TABLE_COLUMN_PATTERN_EDEFAULT == null ? tableColumnPattern != null : !TABLE_COLUMN_PATTERN_EDEFAULT.equals(tableColumnPattern);
            case SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_REGULAR__COLUMN_DEFINITION_DEFAULT:
                return columnDefinitionDefault != null;
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
        result.append(" (passingOption: ");
        result.append(passingOption);
        result.append(", tableColumnPattern: ");
        result.append(tableColumnPattern);
        result.append(')');
        return result.toString();
    }

} //XMLTableColumnDefinitionRegularImpl
