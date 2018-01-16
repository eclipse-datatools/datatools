/**
 * <copyright>
 * </copyright>
 *
 * $Id: XMLSerializeFunctionImpl.java,v 1.5 2007/02/08 17:04:21 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.xml.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
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
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage;
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
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.xml.query.SQLXMLQueryModelPackage;
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
    protected XMLSerializeFunctionTarget serializeTarget;

	/**
     * The cached value of the '{@link #getSerializeEncoding() <em>Serialize Encoding</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSerializeEncoding()
     * @generated
     * @ordered
     */
    protected XMLSerializeFunctionEncoding serializeEncoding;

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
        return SQLXMLQueryModelPackage.Literals.XML_SERIALIZE_FUNCTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION, oldContentOption, contentOption));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION, oldSerializeVersion, serializeVersion));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION, oldDeclarationOption, declarationOption));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET, oldSerializeTarget, newSerializeTarget);
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
                msgs = ((InternalEObject)serializeTarget).eInverseRemove(this, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_TARGET__SERIALIZE_FUNCTION, XMLSerializeFunctionTarget.class, msgs);
            if (newSerializeTarget != null)
                msgs = ((InternalEObject)newSerializeTarget).eInverseAdd(this, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION_TARGET__SERIALIZE_FUNCTION, XMLSerializeFunctionTarget.class, msgs);
            msgs = basicSetSerializeTarget(newSerializeTarget, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET, newSerializeTarget, newSerializeTarget));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING, oldSerializeEncoding, newSerializeEncoding);
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
                msgs = ((InternalEObject)serializeEncoding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING, null, msgs);
            if (newSerializeEncoding != null)
                msgs = ((InternalEObject)newSerializeEncoding).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING, null, msgs);
            msgs = basicSetSerializeEncoding(newSerializeEncoding, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING, newSerializeEncoding, newSerializeEncoding));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
                if (serializeTarget != null)
                    msgs = ((InternalEObject)serializeTarget).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET, null, msgs);
                return basicSetSerializeTarget((XMLSerializeFunctionTarget)otherEnd, msgs);
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
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
                return basicSetSerializeTarget(null, msgs);
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
                return basicSetSerializeEncoding(null, msgs);
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
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION:
                return getContentOption();
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION:
                return getSerializeVersion();
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION:
                return getDeclarationOption();
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
                return getSerializeTarget();
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
                return getSerializeEncoding();
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
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION:
                setContentOption((XMLContentType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION:
                setSerializeVersion((String)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION:
                setDeclarationOption((XMLDeclarationType)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
                setSerializeTarget((XMLSerializeFunctionTarget)newValue);
                return;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
                setSerializeEncoding((XMLSerializeFunctionEncoding)newValue);
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
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION:
                setContentOption(CONTENT_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION:
                setSerializeVersion(SERIALIZE_VERSION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION:
                setDeclarationOption(DECLARATION_OPTION_EDEFAULT);
                return;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
                setSerializeTarget((XMLSerializeFunctionTarget)null);
                return;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
                setSerializeEncoding((XMLSerializeFunctionEncoding)null);
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
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__CONTENT_OPTION:
                return contentOption != CONTENT_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_VERSION:
                return SERIALIZE_VERSION_EDEFAULT == null ? serializeVersion != null : !SERIALIZE_VERSION_EDEFAULT.equals(serializeVersion);
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__DECLARATION_OPTION:
                return declarationOption != DECLARATION_OPTION_EDEFAULT;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_TARGET:
                return serializeTarget != null;
            case SQLXMLQueryModelPackage.XML_SERIALIZE_FUNCTION__SERIALIZE_ENCODING:
                return serializeEncoding != null;
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
