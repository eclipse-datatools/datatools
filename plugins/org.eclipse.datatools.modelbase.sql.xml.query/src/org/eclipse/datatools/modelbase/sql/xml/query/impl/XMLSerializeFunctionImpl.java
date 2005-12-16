/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLSerializeFunctionImpl.java,v 1.4 2005/10/25 21:27:10 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;




import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.GroupingExpression;
import org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression;
import org.eclipse.datatools.modelbase.sql.query.PredicateBasic;
import org.eclipse.datatools.modelbase.sql.query.PredicateBetween;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueList;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateIsNull;
import org.eclipse.datatools.modelbase.sql.query.PredicateLike;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect;
import org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect;
import org.eclipse.datatools.modelbase.sql.query.ResultColumn;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryPackage;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLContentType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLDeclarationType;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunction;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionEncoding;
import org.eclipse.datatools.modelbase.sql.xml.query.XMLSerializeFunctionTarget;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;

import org.eclipse.datatools.modelbase.sql.routines.Function;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Serialize Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl#getContentOption <em>Content Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl#getSerializeVersion <em>Serialize Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl#getDeclarationOption <em>Declaration Option</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl#getSerializeTarget <em>Serialize Target</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.xml.query.impl.XMLSerializeFunctionImpl#getSerializeEncoding <em>Serialize Encoding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLSerializeFunctionImpl extends ValueExpressionFunctionImpl implements XMLSerializeFunction {
	/**
	 * The default value of the '{@link #getContentOption() <em>Content Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getContentOption()
	 * @generated
	 * @ordered
	 */
    protected static final XMLContentType CONTENT_OPTION_EDEFAULT = XMLContentType.CONTENT_LITERAL;

	/**
	 * The cached value of the '{@link #getContentOption() <em>Content Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getContentOption()
	 * @generated
	 * @ordered
	 */
    protected XMLContentType contentOption = CONTENT_OPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSerializeVersion() <em>Serialize Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSerializeVersion()
	 * @generated
	 * @ordered
	 */
    protected static final String SERIALIZE_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSerializeVersion() <em>Serialize Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSerializeVersion()
	 * @generated
	 * @ordered
	 */
    protected String serializeVersion = SERIALIZE_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeclarationOption() <em>Declaration Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDeclarationOption()
	 * @generated
	 * @ordered
	 */
    protected static final XMLDeclarationType DECLARATION_OPTION_EDEFAULT = XMLDeclarationType.EXCLUDING_XMLDECLARATION_LITERAL;

	/**
	 * The cached value of the '{@link #getDeclarationOption() <em>Declaration Option</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDeclarationOption()
	 * @generated
	 * @ordered
	 */
    protected XMLDeclarationType declarationOption = DECLARATION_OPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSerializeTarget() <em>Serialize Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSerializeTarget()
	 * @generated
	 * @ordered
	 */
    protected XMLSerializeFunctionTarget serializeTarget = null;

	/**
	 * The cached value of the '{@link #getSerializeEncoding() <em>Serialize Encoding</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSerializeEncoding()
	 * @generated
	 * @ordered
	 */
    protected XMLSerializeFunctionEncoding serializeEncoding = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected XMLSerializeFunctionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLXMLQueryPackage.eINSTANCE.getXMLSerializeFunction();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLContentType getContentOption() {
		return contentOption;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setContentOption(XMLContentType newContentOption) {
		XMLContentType oldContentOption = contentOption;
		contentOption = newContentOption == null ? CONTENT_OPTION_EDEFAULT : newContentOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION, oldContentOption, contentOption));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getSerializeVersion() {
		return serializeVersion;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSerializeVersion(String newSerializeVersion) {
		String oldSerializeVersion = serializeVersion;
		serializeVersion = newSerializeVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION, oldSerializeVersion, serializeVersion));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLDeclarationType getDeclarationOption() {
		return declarationOption;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDeclarationOption(XMLDeclarationType newDeclarationOption) {
		XMLDeclarationType oldDeclarationOption = declarationOption;
		declarationOption = newDeclarationOption == null ? DECLARATION_OPTION_EDEFAULT : newDeclarationOption;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION, oldDeclarationOption, declarationOption));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLSerializeFunctionTarget getSerializeTarget() {
		return serializeTarget;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetSerializeTarget(XMLSerializeFunctionTarget newSerializeTarget, NotificationChain msgs) {
		XMLSerializeFunctionTarget oldSerializeTarget = serializeTarget;
		serializeTarget = newSerializeTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET, oldSerializeTarget, newSerializeTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSerializeTarget(XMLSerializeFunctionTarget newSerializeTarget) {
		if (newSerializeTarget != serializeTarget) {
			NotificationChain msgs = null;
			if (serializeTarget != null)
				msgs = ((InternalEObject)serializeTarget).eInverseRemove(this, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION_TARGET__SERIALIZE_FUNCTION, XMLSerializeFunctionTarget.class, msgs);
			if (newSerializeTarget != null)
				msgs = ((InternalEObject)newSerializeTarget).eInverseAdd(this, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION_TARGET__SERIALIZE_FUNCTION, XMLSerializeFunctionTarget.class, msgs);
			msgs = basicSetSerializeTarget(newSerializeTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET, newSerializeTarget, newSerializeTarget));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public XMLSerializeFunctionEncoding getSerializeEncoding() {
		return serializeEncoding;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetSerializeEncoding(XMLSerializeFunctionEncoding newSerializeEncoding, NotificationChain msgs) {
		XMLSerializeFunctionEncoding oldSerializeEncoding = serializeEncoding;
		serializeEncoding = newSerializeEncoding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING, oldSerializeEncoding, newSerializeEncoding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSerializeEncoding(XMLSerializeFunctionEncoding newSerializeEncoding) {
		if (newSerializeEncoding != serializeEncoding) {
			NotificationChain msgs = null;
			if (serializeEncoding != null)
				msgs = ((InternalEObject)serializeEncoding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING, null, msgs);
			if (newSerializeEncoding != null)
				msgs = ((InternalEObject)newSerializeEncoding).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING, null, msgs);
			msgs = basicSetSerializeEncoding(newSerializeEncoding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING, newSerializeEncoding, newSerializeEncoding));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUES_ROW:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUES_ROW, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__RESULT_COLUMN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__RESULT_COLUMN, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_PATTERN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_PATTERN, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_MATCHING:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_MATCHING, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PREDICATE_NULL:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PREDICATE_NULL, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__GROUPING_EXPR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__GROUPING_EXPR, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_ESCAPE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_ESCAPE, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NEST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NEST, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PARAMETER_LIST:
					return ((InternalEList)getParameterList()).basicAdd(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
					if (serializeTarget != null)
						msgs = ((InternalEObject)serializeTarget).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET, null, msgs);
					return basicSetSerializeTarget((XMLSerializeFunctionTarget)otherEnd, msgs);
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
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DATA_TYPE:
					return basicSetDataType(null, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUES_ROW:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUES_ROW, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__RESULT_COLUMN:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__RESULT_COLUMN, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_RIGHT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_PATTERN:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_PATTERN, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_MATCHING:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_MATCHING, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PREDICATE_NULL:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PREDICATE_NULL, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__GROUPING_EXPR:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__GROUPING_EXPR, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_ESCAPE:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_ESCAPE, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NEST:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NEST, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST:
					return eBasicSetContainer(null, SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PARAMETER_LIST:
					return ((InternalEList)getParameterList()).basicRemove(otherEnd, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
					return basicSetSerializeTarget(null, msgs);
				case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
					return basicSetSerializeEncoding(null, msgs);
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
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__EANNOTATIONS:
				return getEAnnotations();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NAME:
				return getName();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DEPENDENCIES:
				return getDependencies();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DESCRIPTION:
				return getDescription();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LABEL:
				return getLabel();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UNARY_OPERATOR:
				return getUnaryOperator();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DATA_TYPE:
				return getDataType();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUES_ROW:
				return getValuesRow();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR:
				return getOrderByValueExpr();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__RESULT_COLUMN:
				return getResultColumn();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_RIGHT:
				return getBasicRight();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_LEFT:
				return getBasicLeft();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_PATTERN:
				return getLikePattern();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_MATCHING:
				return getLikeMatching();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PREDICATE_NULL:
				return getPredicateNull();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT:
				return getInValueListRight();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT:
				return getInValueListLeft();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT:
				return getInValueRowSelectLeft();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT:
				return getInValueSelectLeft();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT:
				return getQuantifiedRowSelectLeft();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT:
				return getQuantifiedValueSelectLeft();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_LEFT:
				return getBetweenLeft();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1:
				return getBetweenRight1();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2:
				return getBetweenRight2();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST:
				return getValueExprCast();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION:
				return getValueExprFunction();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT:
				return getValueExprCombinedLeft();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT:
				return getValueExprCombinedRight();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__GROUPING_EXPR:
				return getGroupingExpr();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE:
				return getValueExprCaseElse();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE:
				return getValueExprCaseSimple();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				return getValueExprCaseSimpleContentWhen();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				return getValueExprCaseSimpleContentResult();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_ESCAPE:
				return getLikeEscape();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION:
				return getValueExprLabeledDuration();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NEST:
				return getNest();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST:
				return getUpdateSourceExprList();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SPECIAL_REGISTER:
				return isSpecialRegister() ? Boolean.TRUE : Boolean.FALSE;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DISTINCT:
				return isDistinct() ? Boolean.TRUE : Boolean.FALSE;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__COLUMN_FUNCTION:
				return isColumnFunction() ? Boolean.TRUE : Boolean.FALSE;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PARAMETER_LIST:
				return getParameterList();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__FUNCTION:
				if (resolve) return getFunction();
				return basicGetFunction();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION:
				return getContentOption();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION:
				return getSerializeVersion();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION:
				return getDeclarationOption();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
				return getSerializeTarget();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
				return getSerializeEncoding();
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
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NAME:
				setName((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LABEL:
				setLabel((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UNARY_OPERATOR:
				setUnaryOperator((ValueExpressionUnaryOperator)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DATA_TYPE:
				setDataType((DataType)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUES_ROW:
				setValuesRow((ValuesRow)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR:
				setOrderByValueExpr((OrderByValueExpression)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__RESULT_COLUMN:
				setResultColumn((ResultColumn)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_RIGHT:
				setBasicRight((PredicateBasic)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_LEFT:
				setBasicLeft((PredicateBasic)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_PATTERN:
				setLikePattern((PredicateLike)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_MATCHING:
				setLikeMatching((PredicateLike)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PREDICATE_NULL:
				setPredicateNull((PredicateIsNull)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT:
				setInValueListRight((PredicateInValueList)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT:
				setInValueListLeft((PredicateInValueList)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT:
				setInValueRowSelectLeft((PredicateInValueRowSelect)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT:
				setInValueSelectLeft((PredicateInValueSelect)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT:
				setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT:
				setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_LEFT:
				setBetweenLeft((PredicateBetween)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1:
				setBetweenRight1((PredicateBetween)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2:
				setBetweenRight2((PredicateBetween)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST:
				setValueExprCast((ValueExpressionCast)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION:
				setValueExprFunction((ValueExpressionFunction)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT:
				setValueExprCombinedLeft((ValueExpressionCombined)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT:
				setValueExprCombinedRight((ValueExpressionCombined)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE:
				setValueExprCaseElse((ValueExpressionCaseElse)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE:
				setValueExprCaseSimple((ValueExpressionCaseSimple)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_ESCAPE:
				setLikeEscape((PredicateLike)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION:
				setValueExprLabeledDuration((ValueExpressionLabeledDuration)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NEST:
				setNest((ValueExpressionNested)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST:
				setUpdateSourceExprList((UpdateSourceExprList)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SPECIAL_REGISTER:
				setSpecialRegister(((Boolean)newValue).booleanValue());
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DISTINCT:
				setDistinct(((Boolean)newValue).booleanValue());
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__COLUMN_FUNCTION:
				setColumnFunction(((Boolean)newValue).booleanValue());
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PARAMETER_LIST:
				getParameterList().clear();
				getParameterList().addAll((Collection)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__FUNCTION:
				setFunction((Function)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION:
				setContentOption((XMLContentType)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION:
				setSerializeVersion((String)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION:
				setDeclarationOption((XMLDeclarationType)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
				setSerializeTarget((XMLSerializeFunctionTarget)newValue);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
				setSerializeEncoding((XMLSerializeFunctionEncoding)newValue);
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
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UNARY_OPERATOR:
				setUnaryOperator(UNARY_OPERATOR_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DATA_TYPE:
				setDataType((DataType)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUES_ROW:
				setValuesRow((ValuesRow)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR:
				setOrderByValueExpr((OrderByValueExpression)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__RESULT_COLUMN:
				setResultColumn((ResultColumn)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_RIGHT:
				setBasicRight((PredicateBasic)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_LEFT:
				setBasicLeft((PredicateBasic)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_PATTERN:
				setLikePattern((PredicateLike)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_MATCHING:
				setLikeMatching((PredicateLike)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PREDICATE_NULL:
				setPredicateNull((PredicateIsNull)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT:
				setInValueListRight((PredicateInValueList)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT:
				setInValueListLeft((PredicateInValueList)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT:
				setInValueRowSelectLeft((PredicateInValueRowSelect)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT:
				setInValueSelectLeft((PredicateInValueSelect)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT:
				setQuantifiedRowSelectLeft((PredicateQuantifiedRowSelect)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT:
				setQuantifiedValueSelectLeft((PredicateQuantifiedValueSelect)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_LEFT:
				setBetweenLeft((PredicateBetween)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1:
				setBetweenRight1((PredicateBetween)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2:
				setBetweenRight2((PredicateBetween)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST:
				setValueExprCast((ValueExpressionCast)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION:
				setValueExprFunction((ValueExpressionFunction)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT:
				setValueExprCombinedLeft((ValueExpressionCombined)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT:
				setValueExprCombinedRight((ValueExpressionCombined)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__GROUPING_EXPR:
				setGroupingExpr((GroupingExpression)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE:
				setValueExprCaseElse((ValueExpressionCaseElse)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE:
				setValueExprCaseSimple((ValueExpressionCaseSimple)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				setValueExprCaseSimpleContentWhen((ValueExpressionCaseSimpleContent)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				setValueExprCaseSimpleContentResult((ValueExpressionCaseSimpleContent)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				setValueExprCaseSearchContent((ValueExpressionCaseSearchContent)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_ESCAPE:
				setLikeEscape((PredicateLike)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION:
				setValueExprLabeledDuration((ValueExpressionLabeledDuration)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NEST:
				setNest((ValueExpressionNested)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST:
				setUpdateSourceExprList((UpdateSourceExprList)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SPECIAL_REGISTER:
				setSpecialRegister(SPECIAL_REGISTER_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DISTINCT:
				setDistinct(DISTINCT_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__COLUMN_FUNCTION:
				setColumnFunction(COLUMN_FUNCTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PARAMETER_LIST:
				getParameterList().clear();
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__FUNCTION:
				setFunction((Function)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION:
				setContentOption(CONTENT_OPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION:
				setSerializeVersion(SERIALIZE_VERSION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION:
				setDeclarationOption(DECLARATION_OPTION_EDEFAULT);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
				setSerializeTarget((XMLSerializeFunctionTarget)null);
				return;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
				setSerializeEncoding((XMLSerializeFunctionEncoding)null);
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
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UNARY_OPERATOR:
				return unaryOperator != UNARY_OPERATOR_EDEFAULT;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DATA_TYPE:
				return dataType != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUES_ROW:
				return getValuesRow() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__ORDER_BY_VALUE_EXPR:
				return getOrderByValueExpr() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__RESULT_COLUMN:
				return getResultColumn() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_RIGHT:
				return getBasicRight() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BASIC_LEFT:
				return getBasicLeft() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_PATTERN:
				return getLikePattern() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_MATCHING:
				return getLikeMatching() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PREDICATE_NULL:
				return getPredicateNull() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_RIGHT:
				return getInValueListRight() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_LIST_LEFT:
				return getInValueListLeft() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_ROW_SELECT_LEFT:
				return getInValueRowSelectLeft() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__IN_VALUE_SELECT_LEFT:
				return getInValueSelectLeft() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT:
				return getQuantifiedRowSelectLeft() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT:
				return getQuantifiedValueSelectLeft() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_LEFT:
				return getBetweenLeft() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT1:
				return getBetweenRight1() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__BETWEEN_RIGHT2:
				return getBetweenRight2() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CAST:
				return getValueExprCast() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_FUNCTION:
				return getValueExprFunction() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_LEFT:
				return getValueExprCombinedLeft() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_COMBINED_RIGHT:
				return getValueExprCombinedRight() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__GROUPING_EXPR:
				return getGroupingExpr() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_ELSE:
				return getValueExprCaseElse() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE:
				return getValueExprCaseSimple() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN:
				return getValueExprCaseSimpleContentWhen() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT:
				return getValueExprCaseSimpleContentResult() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT:
				return getValueExprCaseSearchContent() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__LIKE_ESCAPE:
				return getLikeEscape() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__VALUE_EXPR_LABELED_DURATION:
				return getValueExprLabeledDuration() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__NEST:
				return getNest() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__UPDATE_SOURCE_EXPR_LIST:
				return getUpdateSourceExprList() != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SPECIAL_REGISTER:
				return specialRegister != SPECIAL_REGISTER_EDEFAULT;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DISTINCT:
				return distinct != DISTINCT_EDEFAULT;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__COLUMN_FUNCTION:
				return columnFunction != COLUMN_FUNCTION_EDEFAULT;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__PARAMETER_LIST:
				return parameterList != null && !parameterList.isEmpty();
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__FUNCTION:
				return function != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION:
				return contentOption != CONTENT_OPTION_EDEFAULT;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION:
				return SERIALIZE_VERSION_EDEFAULT == null ? serializeVersion != null : !SERIALIZE_VERSION_EDEFAULT.equals(serializeVersion);
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION:
				return declarationOption != DECLARATION_OPTION_EDEFAULT;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
				return serializeTarget != null;
			case SQLXMLQueryPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
				return serializeEncoding != null;
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
		result.append(" (contentOption: ");
		result.append(contentOption);
		result.append(", serializeVersion: ");
		result.append(serializeVersion);
		result.append(", declarationOption: ");
		result.append(declarationOption);
		result.append(')');
		return result.toString();
	}

} //XMLSerializeFunctionImpl
