/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableFunctionImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.impl.TableFunctionImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLNamespacesDeclaration;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLQueryArgumentList;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableColumnDefinitionItem;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLTableFunction;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Table Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableFunctionImpl#getTableRowPattern <em>Table Row Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableFunctionImpl#getXqueryArgList <em>Xquery Arg List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableFunctionImpl#getColumnDefList <em>Column Def List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLTableFunctionImpl#getNamespacesDecl <em>Namespaces Decl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLTableFunctionImpl extends TableFunctionImpl implements XMLTableFunction {
	/**
     * The default value of the '{@link #getTableRowPattern() <em>Table Row Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableRowPattern()
     * @generated
     * @ordered
     */
    protected static final String TABLE_ROW_PATTERN_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getTableRowPattern() <em>Table Row Pattern</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTableRowPattern()
     * @generated
     * @ordered
     */
    protected String tableRowPattern = TABLE_ROW_PATTERN_EDEFAULT;

	/**
     * The cached value of the '{@link #getXqueryArgList() <em>Xquery Arg List</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getXqueryArgList()
     * @generated
     * @ordered
     */
    protected XMLQueryArgumentList xqueryArgList;

	/**
     * The cached value of the '{@link #getColumnDefList() <em>Column Def List</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getColumnDefList()
     * @generated
     * @ordered
     */
    protected EList columnDefList;

	/**
     * The cached value of the '{@link #getNamespacesDecl() <em>Namespaces Decl</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNamespacesDecl()
     * @generated
     * @ordered
     */
    protected XMLNamespacesDeclaration namespacesDecl;

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTableFunctionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return SQLXMLQueryModelPackage.Literals.XML_TABLE_FUNCTION;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getTableRowPattern() {
        return tableRowPattern;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTableRowPattern(String newTableRowPattern) {
        String oldTableRowPattern = tableRowPattern;
        tableRowPattern = newTableRowPattern;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN, oldTableRowPattern, tableRowPattern));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLQueryArgumentList getXqueryArgList() {
        return xqueryArgList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetXqueryArgList(XMLQueryArgumentList newXqueryArgList, NotificationChain msgs) {
        XMLQueryArgumentList oldXqueryArgList = xqueryArgList;
        xqueryArgList = newXqueryArgList;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, oldXqueryArgList, newXqueryArgList);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setXqueryArgList(XMLQueryArgumentList newXqueryArgList) {
        if (newXqueryArgList != xqueryArgList) {
            NotificationChain msgs = null;
            if (xqueryArgList != null)
                msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, XMLQueryArgumentList.class, msgs);
            if (newXqueryArgList != null)
                msgs = ((InternalEObject)newXqueryArgList).eInverseAdd(this, SQLXMLQueryModelPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, XMLQueryArgumentList.class, msgs);
            msgs = basicSetXqueryArgList(newXqueryArgList, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, newXqueryArgList, newXqueryArgList));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getColumnDefList() {
        if (columnDefList == null) {
            columnDefList = new EObjectContainmentWithInverseEList(XMLTableColumnDefinitionItem.class, this, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST, SQLXMLQueryModelPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION);
        }
        return columnDefList;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLNamespacesDeclaration getNamespacesDecl() {
        return namespacesDecl;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetNamespacesDecl(XMLNamespacesDeclaration newNamespacesDecl, NotificationChain msgs) {
        XMLNamespacesDeclaration oldNamespacesDecl = namespacesDecl;
        namespacesDecl = newNamespacesDecl;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, oldNamespacesDecl, newNamespacesDecl);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setNamespacesDecl(XMLNamespacesDeclaration newNamespacesDecl) {
        if (newNamespacesDecl != namespacesDecl) {
            NotificationChain msgs = null;
            if (namespacesDecl != null)
                msgs = ((InternalEObject)namespacesDecl).eInverseRemove(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, XMLNamespacesDeclaration.class, msgs);
            if (newNamespacesDecl != null)
                msgs = ((InternalEObject)newNamespacesDecl).eInverseAdd(this, SQLXMLQueryModelPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, XMLNamespacesDeclaration.class, msgs);
            msgs = basicSetNamespacesDecl(newNamespacesDecl, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, newNamespacesDecl, newNamespacesDecl));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
                if (xqueryArgList != null)
                    msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, null, msgs);
                return basicSetXqueryArgList((XMLQueryArgumentList)otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
                return ((InternalEList)getColumnDefList()).basicAdd(otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
                if (namespacesDecl != null)
                    msgs = ((InternalEObject)namespacesDecl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, null, msgs);
                return basicSetNamespacesDecl((XMLNamespacesDeclaration)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
                return basicSetXqueryArgList(null, msgs);
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
                return ((InternalEList)getColumnDefList()).basicRemove(otherEnd, msgs);
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
                return basicSetNamespacesDecl(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN:
                return getTableRowPattern();
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
                return getXqueryArgList();
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
                return getColumnDefList();
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
                return getNamespacesDecl();
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
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN:
                setTableRowPattern((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
                setXqueryArgList((XMLQueryArgumentList)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
                getColumnDefList().clear();
                getColumnDefList().addAll((Collection)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
                setNamespacesDecl((XMLNamespacesDeclaration)newValue);
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
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN:
                setTableRowPattern(TABLE_ROW_PATTERN_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
                setXqueryArgList((XMLQueryArgumentList)null);
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
                getColumnDefList().clear();
                return;
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
                setNamespacesDecl((XMLNamespacesDeclaration)null);
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
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN:
                return TABLE_ROW_PATTERN_EDEFAULT == null ? tableRowPattern != null : !TABLE_ROW_PATTERN_EDEFAULT.equals(tableRowPattern);
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
                return xqueryArgList != null;
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
                return columnDefList != null && !columnDefList.isEmpty();
            case SQLXMLQueryModelPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
                return namespacesDecl != null;
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
        result.append(" (tableRowPattern: ");
        result.append(tableRowPattern);
        result.append(')');
        return result.toString();
    }

} //XMLTableFunctionImpl
