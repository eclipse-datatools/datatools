/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLTableFunctionImpl.java,v 1.3 2005/10/22 01:40:26 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;




import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.TableJoined;
import org.eclipse.datatools.modelbase.sql.query.TableNested;
import org.eclipse.datatools.modelbase.sql.query.impl.TableFunctionImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
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
    protected XMLQueryArgumentList xqueryArgList = null;

	/**
	 * The cached value of the '{@link #getColumnDefList() <em>Column Def List</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getColumnDefList()
	 * @generated
	 * @ordered
	 */
    protected EList columnDefList = null;

	/**
	 * The cached value of the '{@link #getNamespacesDecl() <em>Namespaces Decl</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNamespacesDecl()
	 * @generated
	 * @ordered
	 */
    protected XMLNamespacesDeclaration namespacesDecl = null;

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
		return SQLXMLQueryPackage.eINSTANCE.getXMLTableFunction();
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
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN, oldTableRowPattern, tableRowPattern));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, oldXqueryArgList, newXqueryArgList);
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
				msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, SQLXMLQueryPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, XMLQueryArgumentList.class, msgs);
			if (newXqueryArgList != null)
				msgs = ((InternalEObject)newXqueryArgList).eInverseAdd(this, SQLXMLQueryPackage.XML_QUERY_ARGUMENT_LIST__TABLE_FUNCTION, XMLQueryArgumentList.class, msgs);
			msgs = basicSetXqueryArgList(newXqueryArgList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, newXqueryArgList, newXqueryArgList));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getColumnDefList() {
		if (columnDefList == null) {
			columnDefList = new EObjectContainmentWithInverseEList(XMLTableColumnDefinitionItem.class, this, SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST, SQLXMLQueryPackage.XML_TABLE_COLUMN_DEFINITION_ITEM__TABLE_FUNCTION);
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, oldNamespacesDecl, newNamespacesDecl);
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
				msgs = ((InternalEObject)namespacesDecl).eInverseRemove(this, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, XMLNamespacesDeclaration.class, msgs);
			if (newNamespacesDecl != null)
				msgs = ((InternalEObject)newNamespacesDecl).eInverseAdd(this, SQLXMLQueryPackage.XML_NAMESPACES_DECLARATION__TABLE_FUNCTION, XMLNamespacesDeclaration.class, msgs);
			msgs = basicSetNamespacesDecl(newNamespacesDecl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, newNamespacesDecl, newNamespacesDecl));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_LEFT, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__QUERY_SELECT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_TABLE_FUNCTION__QUERY_SELECT, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_TABLE_FUNCTION__NEST, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_CORRELATION:
					if (tableCorrelation != null)
						msgs = ((InternalEObject)tableCorrelation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_CORRELATION, null, msgs);
					return basicSetTableCorrelation((TableCorrelation)otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
					if (xqueryArgList != null)
						msgs = ((InternalEObject)xqueryArgList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST, null, msgs);
					return basicSetXqueryArgList((XMLQueryArgumentList)otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
					return ((InternalEList)getColumnDefList()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
					if (namespacesDecl != null)
						msgs = ((InternalEObject)namespacesDecl).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL, null, msgs);
					return basicSetNamespacesDecl((XMLNamespacesDeclaration)otherEnd, msgs);
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
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_LEFT, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__QUERY_SELECT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_TABLE_FUNCTION__QUERY_SELECT, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NEST:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_TABLE_FUNCTION__NEST, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_LIST:
					return ((InternalEList)getColumnList()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_CORRELATION:
					return basicSetTableCorrelation(null, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
					return ((InternalEList)getResultTableAllColumns()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
					return ((InternalEList)getValueExprColumns()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
					return basicSetXqueryArgList(null, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
					return ((InternalEList)getColumnDefList()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
					return basicSetNamespacesDecl(null, msgs);
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
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT:
				return getTableJoinedRight();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_LEFT:
				return getTableJoinedLeft();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__QUERY_SELECT:
				return getQuerySelect();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NEST:
				return getNest();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_LIST:
				return getColumnList();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_CORRELATION:
				return getTableCorrelation();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
				return getResultTableAllColumns();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
				return getValueExprColumns();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN:
				return getTableRowPattern();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
				return getXqueryArgList();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
				return getColumnDefList();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
				return getNamespacesDecl();
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
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__QUERY_SELECT:
				setQuerySelect((QuerySelect)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NEST:
				setNest((TableNested)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_LIST:
				getColumnList().clear();
				getColumnList().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				getResultTableAllColumns().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				getValueExprColumns().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN:
				setTableRowPattern((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
				setXqueryArgList((XMLQueryArgumentList)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
				getColumnDefList().clear();
				getColumnDefList().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
				setNamespacesDecl((XMLNamespacesDeclaration)newValue);
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
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT:
				setTableJoinedRight((TableJoined)null);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_LEFT:
				setTableJoinedLeft((TableJoined)null);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__QUERY_SELECT:
				setQuerySelect((QuerySelect)null);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NEST:
				setNest((TableNested)null);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_LIST:
				getColumnList().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_CORRELATION:
				setTableCorrelation((TableCorrelation)null);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
				getResultTableAllColumns().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
				getValueExprColumns().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN:
				setTableRowPattern(TABLE_ROW_PATTERN_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
				setXqueryArgList((XMLQueryArgumentList)null);
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
				getColumnDefList().clear();
				return;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
				setNamespacesDecl((XMLNamespacesDeclaration)null);
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
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_RIGHT:
				return getTableJoinedRight() != null;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_JOINED_LEFT:
				return getTableJoinedLeft() != null;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__QUERY_SELECT:
				return getQuerySelect() != null;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NEST:
				return getNest() != null;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_LIST:
				return columnList != null && !columnList.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_CORRELATION:
				return tableCorrelation != null;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS:
				return resultTableAllColumns != null && !resultTableAllColumns.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__VALUE_EXPR_COLUMNS:
				return valueExprColumns != null && !valueExprColumns.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__TABLE_ROW_PATTERN:
				return TABLE_ROW_PATTERN_EDEFAULT == null ? tableRowPattern != null : !TABLE_ROW_PATTERN_EDEFAULT.equals(tableRowPattern);
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__XQUERY_ARG_LIST:
				return xqueryArgList != null;
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__COLUMN_DEF_LIST:
				return columnDefList != null && !columnDefList.isEmpty();
			case SQLXMLQueryPackage.XML_TABLE_FUNCTION__NAMESPACES_DECL:
				return namespacesDecl != null;
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
		result.append(" (tableRowPattern: ");
		result.append(tableRowPattern);
		result.append(')');
		return result.toString();
	}

} //XMLTableFunctionImpl
