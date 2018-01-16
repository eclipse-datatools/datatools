/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEWebServiceImpl.java,v 1.8 2007/07/06 08:40:20 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Web Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getService_id <em>Service id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getService_type <em>Service type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getAuth_required <em>Auth required</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getSecure_required <em>Secure required</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getUrl_path <em>Url path</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getUser_name <em>User name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getRemarks <em>Remarks</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceImpl#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEWebServiceImpl extends SQLObjectImpl implements SybaseASEWebService 
{
	/**
     * The default value of the '{@link #getService_id() <em>Service id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getService_id()
     * @generated
     * @ordered
     */
	protected static final long SERVICE_ID_EDEFAULT = 0L;

	/**
     * The cached value of the '{@link #getService_id() <em>Service id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getService_id()
     * @generated
     * @ordered
     */
	protected long service_id = SERVICE_ID_EDEFAULT;

	/**
     * The default value of the '{@link #getService_type() <em>Service type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getService_type()
     * @generated
     * @ordered
     */
	protected static final String SERVICE_TYPE_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getService_type() <em>Service type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getService_type()
     * @generated
     * @ordered
     */
	protected String service_type = SERVICE_TYPE_EDEFAULT;

	/**
     * The default value of the '{@link #getAuth_required() <em>Auth required</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAuth_required()
     * @generated
     * @ordered
     */
	protected static final String AUTH_REQUIRED_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getAuth_required() <em>Auth required</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAuth_required()
     * @generated
     * @ordered
     */
	protected String auth_required = AUTH_REQUIRED_EDEFAULT;

	/**
     * The default value of the '{@link #getSecure_required() <em>Secure required</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSecure_required()
     * @generated
     * @ordered
     */
	protected static final String SECURE_REQUIRED_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getSecure_required() <em>Secure required</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSecure_required()
     * @generated
     * @ordered
     */
	protected String secure_required = SECURE_REQUIRED_EDEFAULT;

	/**
     * The default value of the '{@link #getUrl_path() <em>Url path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUrl_path()
     * @generated
     * @ordered
     */
	protected static final String URL_PATH_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getUrl_path() <em>Url path</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUrl_path()
     * @generated
     * @ordered
     */
	protected String url_path = URL_PATH_EDEFAULT;

	/**
     * The default value of the '{@link #getUser_name() <em>User name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUser_name()
     * @generated
     * @ordered
     */
	protected static final String USER_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getUser_name() <em>User name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getUser_name()
     * @generated
     * @ordered
     */
	protected String user_name = USER_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameter()
     * @generated
     * @ordered
     */
	protected static final String PARAMETER_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getParameter() <em>Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameter()
     * @generated
     * @ordered
     */
	protected String parameter = PARAMETER_EDEFAULT;

	/**
     * The default value of the '{@link #getStatement() <em>Statement</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStatement()
     * @generated
     * @ordered
     */
	protected static final String STATEMENT_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getStatement() <em>Statement</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStatement()
     * @generated
     * @ordered
     */
	protected String statement = STATEMENT_EDEFAULT;

	/**
     * The default value of the '{@link #getRemarks() <em>Remarks</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRemarks()
     * @generated
     * @ordered
     */
	protected static final String REMARKS_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getRemarks() <em>Remarks</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRemarks()
     * @generated
     * @ordered
     */
	protected String remarks = REMARKS_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEWebServiceImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_WEB_SERVICE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public long getService_id() {
        return service_id;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setService_id(long newService_id) {
        long oldService_id = service_id;
        service_id = newService_id;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_ID, oldService_id, service_id));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getService_type() {
        return service_type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setService_type(String newService_type) {
        String oldService_type = service_type;
        service_type = newService_type;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_TYPE, oldService_type, service_type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getAuth_required() {
        return auth_required;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAuth_required(String newAuth_required) {
        String oldAuth_required = auth_required;
        auth_required = newAuth_required;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__AUTH_REQUIRED, oldAuth_required, auth_required));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getSecure_required() {
        return secure_required;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSecure_required(String newSecure_required) {
        String oldSecure_required = secure_required;
        secure_required = newSecure_required;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SECURE_REQUIRED, oldSecure_required, secure_required));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getUrl_path() {
        return url_path;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUrl_path(String newUrl_path) {
        String oldUrl_path = url_path;
        url_path = newUrl_path;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__URL_PATH, oldUrl_path, url_path));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getUser_name() {
        return user_name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUser_name(String newUser_name) {
        String oldUser_name = user_name;
        user_name = newUser_name;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__USER_NAME, oldUser_name, user_name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getParameter() {
        return parameter;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setParameter(String newParameter) {
        String oldParameter = parameter;
        parameter = newParameter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__PARAMETER, oldParameter, parameter));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getStatement() {
        return statement;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setStatement(String newStatement) {
        String oldStatement = statement;
        statement = newStatement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__STATEMENT, oldStatement, statement));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getRemarks() {
        return remarks;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRemarks(String newRemarks) {
        String oldRemarks = remarks;
        remarks = newRemarks;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__REMARKS, oldRemarks, remarks));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEDatabase getDatabase() {
        if (eContainerFeatureID != SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE) return null;
        return (SybaseASEDatabase)eContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetDatabase(SybaseASEDatabase newDatabase, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newDatabase, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDatabase(SybaseASEDatabase newDatabase) {
        if (newDatabase != eInternalContainer() || (eContainerFeatureID != SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE && newDatabase != null))
        {
            if (EcoreUtil.isAncestor(this, newDatabase))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newDatabase != null)
                msgs = ((InternalEObject)newDatabase).eInverseAdd(this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES, SybaseASEDatabase.class, msgs);
            msgs = basicSetDatabase(newDatabase, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE, newDatabase, newDatabase));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetDatabase((SybaseASEDatabase)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE:
                return basicSetDatabase(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE:
                return eInternalContainer().eInverseRemove(this, SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE__WEB_SERVICES, SybaseASEDatabase.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_ID:
                return new Long(getService_id());
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_TYPE:
                return getService_type();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__AUTH_REQUIRED:
                return getAuth_required();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SECURE_REQUIRED:
                return getSecure_required();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__URL_PATH:
                return getUrl_path();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__USER_NAME:
                return getUser_name();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__PARAMETER:
                return getParameter();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__STATEMENT:
                return getStatement();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__REMARKS:
                return getRemarks();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE:
                return getDatabase();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_ID:
                setService_id(((Long)newValue).longValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_TYPE:
                setService_type((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__AUTH_REQUIRED:
                setAuth_required((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SECURE_REQUIRED:
                setSecure_required((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__URL_PATH:
                setUrl_path((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__USER_NAME:
                setUser_name((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__PARAMETER:
                setParameter((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__STATEMENT:
                setStatement((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__REMARKS:
                setRemarks((String)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE:
                setDatabase((SybaseASEDatabase)newValue);
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_ID:
                setService_id(SERVICE_ID_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_TYPE:
                setService_type(SERVICE_TYPE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__AUTH_REQUIRED:
                setAuth_required(AUTH_REQUIRED_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SECURE_REQUIRED:
                setSecure_required(SECURE_REQUIRED_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__URL_PATH:
                setUrl_path(URL_PATH_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__USER_NAME:
                setUser_name(USER_NAME_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__PARAMETER:
                setParameter(PARAMETER_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__STATEMENT:
                setStatement(STATEMENT_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__REMARKS:
                setRemarks(REMARKS_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE:
                setDatabase((SybaseASEDatabase)null);
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_ID:
                return service_id != SERVICE_ID_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SERVICE_TYPE:
                return SERVICE_TYPE_EDEFAULT == null ? service_type != null : !SERVICE_TYPE_EDEFAULT.equals(service_type);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__AUTH_REQUIRED:
                return AUTH_REQUIRED_EDEFAULT == null ? auth_required != null : !AUTH_REQUIRED_EDEFAULT.equals(auth_required);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__SECURE_REQUIRED:
                return SECURE_REQUIRED_EDEFAULT == null ? secure_required != null : !SECURE_REQUIRED_EDEFAULT.equals(secure_required);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__URL_PATH:
                return URL_PATH_EDEFAULT == null ? url_path != null : !URL_PATH_EDEFAULT.equals(url_path);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__USER_NAME:
                return USER_NAME_EDEFAULT == null ? user_name != null : !USER_NAME_EDEFAULT.equals(user_name);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__PARAMETER:
                return PARAMETER_EDEFAULT == null ? parameter != null : !PARAMETER_EDEFAULT.equals(parameter);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__STATEMENT:
                return STATEMENT_EDEFAULT == null ? statement != null : !STATEMENT_EDEFAULT.equals(statement);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__REMARKS:
                return REMARKS_EDEFAULT == null ? remarks != null : !REMARKS_EDEFAULT.equals(remarks);
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE__DATABASE:
                return getDatabase() != null;
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
        result.append(" (service_id: "); //$NON-NLS-1$
        result.append(service_id);
        result.append(", service_type: "); //$NON-NLS-1$
        result.append(service_type);
        result.append(", auth_required: "); //$NON-NLS-1$
        result.append(auth_required);
        result.append(", secure_required: "); //$NON-NLS-1$
        result.append(secure_required);
        result.append(", url_path: "); //$NON-NLS-1$
        result.append(url_path);
        result.append(", user_name: "); //$NON-NLS-1$
        result.append(user_name);
        result.append(", parameter: "); //$NON-NLS-1$
        result.append(parameter);
        result.append(", statement: "); //$NON-NLS-1$
        result.append(statement);
        result.append(", remarks: "); //$NON-NLS-1$
        result.append(remarks);
        result.append(')');
        return result.toString();
    }

} //SybaseASEWebServiceImpl
