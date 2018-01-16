/**
 * <copyright>
 * </copyright>
 *
 * $Id: PLSQLPackageImpl.java,v 1.2 2009/03/06 22:38:09 xli Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension;
import org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.PLSQLPackageBody;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.routines.DataAccess;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.routines.impl.RoutineImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PLSQL Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getSpecificName <em>Specific Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getParameterStyle <em>Parameter Style</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#isDeterministic <em>Deterministic</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getSqlDataAccess <em>Sql Data Access</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getCreationTS <em>Creation TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getLastAlteredTS <em>Last Altered TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getAuthorizationID <em>Authorization ID</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getSecurity <em>Security</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getExternalName <em>External Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getFenced <em>Fenced</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getThreadsafe <em>Threadsafe</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#isDbInfo <em>Db Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#isImplicitSchema <em>Implicit Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#isFederated <em>Federated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getParmCcsid <em>Parm Ccsid</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getSpecialRegister <em>Special Register</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getChangeState <em>Change State</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getDebugId <em>Debug Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getProgramType <em>Program Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getOrigSchemaName <em>Orig Schema Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getOrigParmSig <em>Orig Parm Sig</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getExtendedOptions <em>Extended Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getRoutineExtensions <em>Routine Extensions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.PLSQLPackageImpl#getPackageBody <em>Package Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PLSQLPackageImpl extends LUWModuleImpl implements PLSQLPackage {
	/**
	 * The default value of the '{@link #getSpecificName() <em>Specific Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificName()
	 * @generated
	 * @ordered
	 */
	protected static final String SPECIFIC_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpecificName() <em>Specific Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecificName()
	 * @generated
	 * @ordered
	 */
	protected String specificName = SPECIFIC_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected String language = LANGUAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getParameterStyle() <em>Parameter Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterStyle()
	 * @generated
	 * @ordered
	 */
	protected static final String PARAMETER_STYLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParameterStyle() <em>Parameter Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterStyle()
	 * @generated
	 * @ordered
	 */
	protected String parameterStyle = PARAMETER_STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isDeterministic() <em>Deterministic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeterministic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DETERMINISTIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeterministic() <em>Deterministic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeterministic()
	 * @generated
	 * @ordered
	 */
	protected boolean deterministic = DETERMINISTIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getSqlDataAccess() <em>Sql Data Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlDataAccess()
	 * @generated
	 * @ordered
	 */
	protected static final DataAccess SQL_DATA_ACCESS_EDEFAULT = DataAccess.NO_SQL_LITERAL;

	/**
	 * The cached value of the '{@link #getSqlDataAccess() <em>Sql Data Access</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlDataAccess()
	 * @generated
	 * @ordered
	 */
	protected DataAccess sqlDataAccess = SQL_DATA_ACCESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationTS() <em>Creation TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTS()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATION_TS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreationTS() <em>Creation TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTS()
	 * @generated
	 * @ordered
	 */
	protected String creationTS = CREATION_TS_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastAlteredTS() <em>Last Altered TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastAlteredTS()
	 * @generated
	 * @ordered
	 */
	protected static final String LAST_ALTERED_TS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastAlteredTS() <em>Last Altered TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastAlteredTS()
	 * @generated
	 * @ordered
	 */
	protected String lastAlteredTS = LAST_ALTERED_TS_EDEFAULT;

	/**
	 * The default value of the '{@link #getAuthorizationID() <em>Authorization ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationID()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHORIZATION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthorizationID() <em>Authorization ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthorizationID()
	 * @generated
	 * @ordered
	 */
	protected String authorizationID = AUTHORIZATION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getSecurity() <em>Security</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurity()
	 * @generated
	 * @ordered
	 */
	protected static final String SECURITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSecurity() <em>Security</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecurity()
	 * @generated
	 * @ordered
	 */
	protected String security = SECURITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getExternalName() <em>External Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalName()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTERNAL_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExternalName() <em>External Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalName()
	 * @generated
	 * @ordered
	 */
	protected String externalName = EXTERNAL_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList parameters;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Source source;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected Schema schema;

	/**
	 * The default value of the '{@link #getFenced() <em>Fenced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFenced()
	 * @generated
	 * @ordered
	 */
	protected static final String FENCED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFenced() <em>Fenced</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFenced()
	 * @generated
	 * @ordered
	 */
	protected String fenced = FENCED_EDEFAULT;

	/**
	 * The default value of the '{@link #getThreadsafe() <em>Threadsafe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreadsafe()
	 * @generated
	 * @ordered
	 */
	protected static final String THREADSAFE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getThreadsafe() <em>Threadsafe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreadsafe()
	 * @generated
	 * @ordered
	 */
	protected String threadsafe = THREADSAFE_EDEFAULT;

	/**
	 * The default value of the '{@link #isDbInfo() <em>Db Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDbInfo()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DB_INFO_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDbInfo() <em>Db Info</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDbInfo()
	 * @generated
	 * @ordered
	 */
	protected boolean dbInfo = DB_INFO_EDEFAULT;

	/**
	 * The default value of the '{@link #isImplicitSchema() <em>Implicit Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplicitSchema()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMPLICIT_SCHEMA_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isImplicitSchema() <em>Implicit Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplicitSchema()
	 * @generated
	 * @ordered
	 */
	protected boolean implicitSchema = IMPLICIT_SCHEMA_EDEFAULT;

	/**
	 * The default value of the '{@link #isFederated() <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFederated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FEDERATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFederated() <em>Federated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFederated()
	 * @generated
	 * @ordered
	 */
	protected boolean federated = FEDERATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getParmCcsid() <em>Parm Ccsid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParmCcsid()
	 * @generated
	 * @ordered
	 */
	protected static final String PARM_CCSID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParmCcsid() <em>Parm Ccsid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParmCcsid()
	 * @generated
	 * @ordered
	 */
	protected String parmCcsid = PARM_CCSID_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpecialRegister() <em>Special Register</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecialRegister()
	 * @generated
	 * @ordered
	 */
	protected static final String SPECIAL_REGISTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpecialRegister() <em>Special Register</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecialRegister()
	 * @generated
	 * @ordered
	 */
	protected String specialRegister = SPECIAL_REGISTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getChangeState() <em>Change State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeState()
	 * @generated
	 * @ordered
	 */
	protected static final int CHANGE_STATE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getChangeState() <em>Change State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChangeState()
	 * @generated
	 * @ordered
	 */
	protected int changeState = CHANGE_STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDebugId() <em>Debug Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDebugId()
	 * @generated
	 * @ordered
	 */
	protected static final String DEBUG_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDebugId() <em>Debug Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDebugId()
	 * @generated
	 * @ordered
	 */
	protected String debugId = DEBUG_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getProgramType() <em>Program Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProgramType()
	 * @generated
	 * @ordered
	 */
	protected static final String PROGRAM_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProgramType() <em>Program Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProgramType()
	 * @generated
	 * @ordered
	 */
	protected String programType = PROGRAM_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrigSchemaName() <em>Orig Schema Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigSchemaName()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIG_SCHEMA_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrigSchemaName() <em>Orig Schema Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigSchemaName()
	 * @generated
	 * @ordered
	 */
	protected String origSchemaName = ORIG_SCHEMA_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrigParmSig() <em>Orig Parm Sig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigParmSig()
	 * @generated
	 * @ordered
	 */
	protected static final String ORIG_PARM_SIG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrigParmSig() <em>Orig Parm Sig</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigParmSig()
	 * @generated
	 * @ordered
	 */
	protected String origParmSig = ORIG_PARM_SIG_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtendedOptions() <em>Extended Options</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedOptions()
	 * @generated
	 * @ordered
	 */
	protected EList extendedOptions;

	/**
	 * The cached value of the '{@link #getRoutineExtensions() <em>Routine Extensions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoutineExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList routineExtensions;

	/**
	 * The cached value of the '{@link #getPackageBody() <em>Package Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageBody()
	 * @generated
	 * @ordered
	 */
	protected PLSQLPackageBody packageBody;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PLSQLPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.PLSQL_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpecificName() {
		return specificName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecificName(String newSpecificName) {
		String oldSpecificName = specificName;
		specificName = newSpecificName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__SPECIFIC_NAME, oldSpecificName, specificName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLanguage(String newLanguage) {
		String oldLanguage = language;
		language = newLanguage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__LANGUAGE, oldLanguage, language));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParameterStyle() {
		return parameterStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterStyle(String newParameterStyle) {
		String oldParameterStyle = parameterStyle;
		parameterStyle = newParameterStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__PARAMETER_STYLE, oldParameterStyle, parameterStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeterministic() {
		return deterministic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeterministic(boolean newDeterministic) {
		boolean oldDeterministic = deterministic;
		deterministic = newDeterministic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__DETERMINISTIC, oldDeterministic, deterministic));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAccess getSqlDataAccess() {
		return sqlDataAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlDataAccess(DataAccess newSqlDataAccess) {
		DataAccess oldSqlDataAccess = sqlDataAccess;
		sqlDataAccess = newSqlDataAccess == null ? SQL_DATA_ACCESS_EDEFAULT : newSqlDataAccess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__SQL_DATA_ACCESS, oldSqlDataAccess, sqlDataAccess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreationTS() {
		return creationTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationTS(String newCreationTS) {
		String oldCreationTS = creationTS;
		creationTS = newCreationTS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__CREATION_TS, oldCreationTS, creationTS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLastAlteredTS() {
		return lastAlteredTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastAlteredTS(String newLastAlteredTS) {
		String oldLastAlteredTS = lastAlteredTS;
		lastAlteredTS = newLastAlteredTS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__LAST_ALTERED_TS, oldLastAlteredTS, lastAlteredTS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthorizationID() {
		return authorizationID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthorizationID(String newAuthorizationID) {
		String oldAuthorizationID = authorizationID;
		authorizationID = newAuthorizationID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__AUTHORIZATION_ID, oldAuthorizationID, authorizationID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSecurity() {
		return security;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecurity(String newSecurity) {
		String oldSecurity = security;
		security = newSecurity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__SECURITY, oldSecurity, security));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExternalName() {
		return externalName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalName(String newExternalName) {
		String oldExternalName = externalName;
		externalName = newExternalName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__EXTERNAL_NAME, oldExternalName, externalName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentWithInverseEList(Parameter.class, this, LUWPackage.PLSQL_PACKAGE__PARAMETERS, SQLRoutinesPackage.PARAMETER__ROUTINE);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Source newSource, NotificationChain msgs) {
		Source oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Source newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.PLSQL_PACKAGE__SOURCE, null, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LUWPackage.PLSQL_PACKAGE__SOURCE, null, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.PLSQL_PACKAGE__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(Schema newSchema, NotificationChain msgs) {
		Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__ROUTINES, Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, SQLSchemaPackage.SCHEMA__ROUTINES, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFenced() {
		return fenced;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFenced(String newFenced) {
		String oldFenced = fenced;
		fenced = newFenced;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__FENCED, oldFenced, fenced));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getThreadsafe() {
		return threadsafe;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThreadsafe(String newThreadsafe) {
		String oldThreadsafe = threadsafe;
		threadsafe = newThreadsafe;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__THREADSAFE, oldThreadsafe, threadsafe));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDbInfo() {
		return dbInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDbInfo(boolean newDbInfo) {
		boolean oldDbInfo = dbInfo;
		dbInfo = newDbInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__DB_INFO, oldDbInfo, dbInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImplicitSchema() {
		return implicitSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplicitSchema(boolean newImplicitSchema) {
		boolean oldImplicitSchema = implicitSchema;
		implicitSchema = newImplicitSchema;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__IMPLICIT_SCHEMA, oldImplicitSchema, implicitSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFederated() {
		return federated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFederated(boolean newFederated) {
		boolean oldFederated = federated;
		federated = newFederated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__FEDERATED, oldFederated, federated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getParmCcsid() {
		return parmCcsid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParmCcsid(String newParmCcsid) {
		String oldParmCcsid = parmCcsid;
		parmCcsid = newParmCcsid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__PARM_CCSID, oldParmCcsid, parmCcsid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpecialRegister() {
		return specialRegister;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecialRegister(String newSpecialRegister) {
		String oldSpecialRegister = specialRegister;
		specialRegister = newSpecialRegister;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__SPECIAL_REGISTER, oldSpecialRegister, specialRegister));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getChangeState() {
		return changeState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChangeState(int newChangeState) {
		int oldChangeState = changeState;
		changeState = newChangeState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__CHANGE_STATE, oldChangeState, changeState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDebugId() {
		return debugId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDebugId(String newDebugId) {
		String oldDebugId = debugId;
		debugId = newDebugId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__DEBUG_ID, oldDebugId, debugId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProgramType() {
		return programType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProgramType(String newProgramType) {
		String oldProgramType = programType;
		programType = newProgramType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__PROGRAM_TYPE, oldProgramType, programType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOrigSchemaName() {
		return origSchemaName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrigSchemaName(String newOrigSchemaName) {
		String oldOrigSchemaName = origSchemaName;
		origSchemaName = newOrigSchemaName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__ORIG_SCHEMA_NAME, oldOrigSchemaName, origSchemaName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOrigParmSig() {
		return origParmSig;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrigParmSig(String newOrigParmSig) {
		String oldOrigParmSig = origParmSig;
		origParmSig = newOrigParmSig;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__ORIG_PARM_SIG, oldOrigParmSig, origParmSig));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExtendedOptions() {
		if (extendedOptions == null) {
			extendedOptions = new EObjectContainmentEList(DB2ExtendedOptions.class, this, LUWPackage.PLSQL_PACKAGE__EXTENDED_OPTIONS);
		}
		return extendedOptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRoutineExtensions() {
		if (routineExtensions == null) {
			routineExtensions = new EObjectResolvingEList(DB2RoutineExtension.class, this, LUWPackage.PLSQL_PACKAGE__ROUTINE_EXTENSIONS);
		}
		return routineExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PLSQLPackageBody getPackageBody() {
		return packageBody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackageBody(PLSQLPackageBody newPackageBody, NotificationChain msgs) {
		PLSQLPackageBody oldPackageBody = packageBody;
		packageBody = newPackageBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY, oldPackageBody, newPackageBody);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageBody(PLSQLPackageBody newPackageBody) {
		if (newPackageBody != packageBody) {
			NotificationChain msgs = null;
			if (packageBody != null)
				msgs = ((InternalEObject)packageBody).eInverseRemove(this, LUWPackage.PLSQL_PACKAGE_BODY__PACKAGE, PLSQLPackageBody.class, msgs);
			if (newPackageBody != null)
				msgs = ((InternalEObject)newPackageBody).eInverseAdd(this, LUWPackage.PLSQL_PACKAGE_BODY__PACKAGE, PLSQLPackageBody.class, msgs);
			msgs = basicSetPackageBody(newPackageBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY, newPackageBody, newPackageBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.PLSQL_PACKAGE__PARAMETERS:
				return ((InternalEList)getParameters()).basicAdd(otherEnd, msgs);
			case LUWPackage.PLSQL_PACKAGE__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, SQLSchemaPackage.SCHEMA__ROUTINES, Schema.class, msgs);
				return basicSetSchema((Schema)otherEnd, msgs);
			case LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY:
				if (packageBody != null)
					msgs = ((InternalEObject)packageBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY, null, msgs);
				return basicSetPackageBody((PLSQLPackageBody)otherEnd, msgs);
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
			case LUWPackage.PLSQL_PACKAGE__PARAMETERS:
				return ((InternalEList)getParameters()).basicRemove(otherEnd, msgs);
			case LUWPackage.PLSQL_PACKAGE__SOURCE:
				return basicSetSource(null, msgs);
			case LUWPackage.PLSQL_PACKAGE__SCHEMA:
				return basicSetSchema(null, msgs);
			case LUWPackage.PLSQL_PACKAGE__EXTENDED_OPTIONS:
				return ((InternalEList)getExtendedOptions()).basicRemove(otherEnd, msgs);
			case LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY:
				return basicSetPackageBody(null, msgs);
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
			case LUWPackage.PLSQL_PACKAGE__SPECIFIC_NAME:
				return getSpecificName();
			case LUWPackage.PLSQL_PACKAGE__LANGUAGE:
				return getLanguage();
			case LUWPackage.PLSQL_PACKAGE__PARAMETER_STYLE:
				return getParameterStyle();
			case LUWPackage.PLSQL_PACKAGE__DETERMINISTIC:
				return isDeterministic() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.PLSQL_PACKAGE__SQL_DATA_ACCESS:
				return getSqlDataAccess();
			case LUWPackage.PLSQL_PACKAGE__CREATION_TS:
				return getCreationTS();
			case LUWPackage.PLSQL_PACKAGE__LAST_ALTERED_TS:
				return getLastAlteredTS();
			case LUWPackage.PLSQL_PACKAGE__AUTHORIZATION_ID:
				return getAuthorizationID();
			case LUWPackage.PLSQL_PACKAGE__SECURITY:
				return getSecurity();
			case LUWPackage.PLSQL_PACKAGE__EXTERNAL_NAME:
				return getExternalName();
			case LUWPackage.PLSQL_PACKAGE__PARAMETERS:
				return getParameters();
			case LUWPackage.PLSQL_PACKAGE__SOURCE:
				return getSource();
			case LUWPackage.PLSQL_PACKAGE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case LUWPackage.PLSQL_PACKAGE__FENCED:
				return getFenced();
			case LUWPackage.PLSQL_PACKAGE__THREADSAFE:
				return getThreadsafe();
			case LUWPackage.PLSQL_PACKAGE__DB_INFO:
				return isDbInfo() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.PLSQL_PACKAGE__IMPLICIT_SCHEMA:
				return isImplicitSchema() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.PLSQL_PACKAGE__FEDERATED:
				return isFederated() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.PLSQL_PACKAGE__PARM_CCSID:
				return getParmCcsid();
			case LUWPackage.PLSQL_PACKAGE__SPECIAL_REGISTER:
				return getSpecialRegister();
			case LUWPackage.PLSQL_PACKAGE__CHANGE_STATE:
				return new Integer(getChangeState());
			case LUWPackage.PLSQL_PACKAGE__DEBUG_ID:
				return getDebugId();
			case LUWPackage.PLSQL_PACKAGE__PROGRAM_TYPE:
				return getProgramType();
			case LUWPackage.PLSQL_PACKAGE__ORIG_SCHEMA_NAME:
				return getOrigSchemaName();
			case LUWPackage.PLSQL_PACKAGE__ORIG_PARM_SIG:
				return getOrigParmSig();
			case LUWPackage.PLSQL_PACKAGE__EXTENDED_OPTIONS:
				return getExtendedOptions();
			case LUWPackage.PLSQL_PACKAGE__ROUTINE_EXTENSIONS:
				return getRoutineExtensions();
			case LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY:
				return getPackageBody();
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
			case LUWPackage.PLSQL_PACKAGE__SPECIFIC_NAME:
				setSpecificName((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__PARAMETER_STYLE:
				setParameterStyle((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__DETERMINISTIC:
				setDeterministic(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.PLSQL_PACKAGE__SQL_DATA_ACCESS:
				setSqlDataAccess((DataAccess)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__CREATION_TS:
				setCreationTS((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__LAST_ALTERED_TS:
				setLastAlteredTS((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__AUTHORIZATION_ID:
				setAuthorizationID((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__SECURITY:
				setSecurity((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__EXTERNAL_NAME:
				setExternalName((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__SOURCE:
				setSource((Source)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__FENCED:
				setFenced((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__THREADSAFE:
				setThreadsafe((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__DB_INFO:
				setDbInfo(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.PLSQL_PACKAGE__IMPLICIT_SCHEMA:
				setImplicitSchema(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.PLSQL_PACKAGE__FEDERATED:
				setFederated(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.PLSQL_PACKAGE__PARM_CCSID:
				setParmCcsid((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__SPECIAL_REGISTER:
				setSpecialRegister((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__CHANGE_STATE:
				setChangeState(((Integer)newValue).intValue());
				return;
			case LUWPackage.PLSQL_PACKAGE__DEBUG_ID:
				setDebugId((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__PROGRAM_TYPE:
				setProgramType((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__ORIG_SCHEMA_NAME:
				setOrigSchemaName((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__ORIG_PARM_SIG:
				setOrigParmSig((String)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__EXTENDED_OPTIONS:
				getExtendedOptions().clear();
				getExtendedOptions().addAll((Collection)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__ROUTINE_EXTENSIONS:
				getRoutineExtensions().clear();
				getRoutineExtensions().addAll((Collection)newValue);
				return;
			case LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY:
				setPackageBody((PLSQLPackageBody)newValue);
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
			case LUWPackage.PLSQL_PACKAGE__SPECIFIC_NAME:
				setSpecificName(SPECIFIC_NAME_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__PARAMETER_STYLE:
				setParameterStyle(PARAMETER_STYLE_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__DETERMINISTIC:
				setDeterministic(DETERMINISTIC_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__SQL_DATA_ACCESS:
				setSqlDataAccess(SQL_DATA_ACCESS_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__CREATION_TS:
				setCreationTS(CREATION_TS_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__LAST_ALTERED_TS:
				setLastAlteredTS(LAST_ALTERED_TS_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__AUTHORIZATION_ID:
				setAuthorizationID(AUTHORIZATION_ID_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__SECURITY:
				setSecurity(SECURITY_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__EXTERNAL_NAME:
				setExternalName(EXTERNAL_NAME_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__PARAMETERS:
				getParameters().clear();
				return;
			case LUWPackage.PLSQL_PACKAGE__SOURCE:
				setSource((Source)null);
				return;
			case LUWPackage.PLSQL_PACKAGE__SCHEMA:
				setSchema((Schema)null);
				return;
			case LUWPackage.PLSQL_PACKAGE__FENCED:
				setFenced(FENCED_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__THREADSAFE:
				setThreadsafe(THREADSAFE_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__DB_INFO:
				setDbInfo(DB_INFO_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__IMPLICIT_SCHEMA:
				setImplicitSchema(IMPLICIT_SCHEMA_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__FEDERATED:
				setFederated(FEDERATED_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__PARM_CCSID:
				setParmCcsid(PARM_CCSID_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__SPECIAL_REGISTER:
				setSpecialRegister(SPECIAL_REGISTER_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__CHANGE_STATE:
				setChangeState(CHANGE_STATE_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__DEBUG_ID:
				setDebugId(DEBUG_ID_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__PROGRAM_TYPE:
				setProgramType(PROGRAM_TYPE_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__ORIG_SCHEMA_NAME:
				setOrigSchemaName(ORIG_SCHEMA_NAME_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__ORIG_PARM_SIG:
				setOrigParmSig(ORIG_PARM_SIG_EDEFAULT);
				return;
			case LUWPackage.PLSQL_PACKAGE__EXTENDED_OPTIONS:
				getExtendedOptions().clear();
				return;
			case LUWPackage.PLSQL_PACKAGE__ROUTINE_EXTENSIONS:
				getRoutineExtensions().clear();
				return;
			case LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY:
				setPackageBody((PLSQLPackageBody)null);
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
			case LUWPackage.PLSQL_PACKAGE__SPECIFIC_NAME:
				return SPECIFIC_NAME_EDEFAULT == null ? specificName != null : !SPECIFIC_NAME_EDEFAULT.equals(specificName);
			case LUWPackage.PLSQL_PACKAGE__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
			case LUWPackage.PLSQL_PACKAGE__PARAMETER_STYLE:
				return PARAMETER_STYLE_EDEFAULT == null ? parameterStyle != null : !PARAMETER_STYLE_EDEFAULT.equals(parameterStyle);
			case LUWPackage.PLSQL_PACKAGE__DETERMINISTIC:
				return deterministic != DETERMINISTIC_EDEFAULT;
			case LUWPackage.PLSQL_PACKAGE__SQL_DATA_ACCESS:
				return sqlDataAccess != SQL_DATA_ACCESS_EDEFAULT;
			case LUWPackage.PLSQL_PACKAGE__CREATION_TS:
				return CREATION_TS_EDEFAULT == null ? creationTS != null : !CREATION_TS_EDEFAULT.equals(creationTS);
			case LUWPackage.PLSQL_PACKAGE__LAST_ALTERED_TS:
				return LAST_ALTERED_TS_EDEFAULT == null ? lastAlteredTS != null : !LAST_ALTERED_TS_EDEFAULT.equals(lastAlteredTS);
			case LUWPackage.PLSQL_PACKAGE__AUTHORIZATION_ID:
				return AUTHORIZATION_ID_EDEFAULT == null ? authorizationID != null : !AUTHORIZATION_ID_EDEFAULT.equals(authorizationID);
			case LUWPackage.PLSQL_PACKAGE__SECURITY:
				return SECURITY_EDEFAULT == null ? security != null : !SECURITY_EDEFAULT.equals(security);
			case LUWPackage.PLSQL_PACKAGE__EXTERNAL_NAME:
				return EXTERNAL_NAME_EDEFAULT == null ? externalName != null : !EXTERNAL_NAME_EDEFAULT.equals(externalName);
			case LUWPackage.PLSQL_PACKAGE__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case LUWPackage.PLSQL_PACKAGE__SOURCE:
				return source != null;
			case LUWPackage.PLSQL_PACKAGE__SCHEMA:
				return schema != null;
			case LUWPackage.PLSQL_PACKAGE__FENCED:
				return FENCED_EDEFAULT == null ? fenced != null : !FENCED_EDEFAULT.equals(fenced);
			case LUWPackage.PLSQL_PACKAGE__THREADSAFE:
				return THREADSAFE_EDEFAULT == null ? threadsafe != null : !THREADSAFE_EDEFAULT.equals(threadsafe);
			case LUWPackage.PLSQL_PACKAGE__DB_INFO:
				return dbInfo != DB_INFO_EDEFAULT;
			case LUWPackage.PLSQL_PACKAGE__IMPLICIT_SCHEMA:
				return implicitSchema != IMPLICIT_SCHEMA_EDEFAULT;
			case LUWPackage.PLSQL_PACKAGE__FEDERATED:
				return federated != FEDERATED_EDEFAULT;
			case LUWPackage.PLSQL_PACKAGE__PARM_CCSID:
				return PARM_CCSID_EDEFAULT == null ? parmCcsid != null : !PARM_CCSID_EDEFAULT.equals(parmCcsid);
			case LUWPackage.PLSQL_PACKAGE__SPECIAL_REGISTER:
				return SPECIAL_REGISTER_EDEFAULT == null ? specialRegister != null : !SPECIAL_REGISTER_EDEFAULT.equals(specialRegister);
			case LUWPackage.PLSQL_PACKAGE__CHANGE_STATE:
				return changeState != CHANGE_STATE_EDEFAULT;
			case LUWPackage.PLSQL_PACKAGE__DEBUG_ID:
				return DEBUG_ID_EDEFAULT == null ? debugId != null : !DEBUG_ID_EDEFAULT.equals(debugId);
			case LUWPackage.PLSQL_PACKAGE__PROGRAM_TYPE:
				return PROGRAM_TYPE_EDEFAULT == null ? programType != null : !PROGRAM_TYPE_EDEFAULT.equals(programType);
			case LUWPackage.PLSQL_PACKAGE__ORIG_SCHEMA_NAME:
				return ORIG_SCHEMA_NAME_EDEFAULT == null ? origSchemaName != null : !ORIG_SCHEMA_NAME_EDEFAULT.equals(origSchemaName);
			case LUWPackage.PLSQL_PACKAGE__ORIG_PARM_SIG:
				return ORIG_PARM_SIG_EDEFAULT == null ? origParmSig != null : !ORIG_PARM_SIG_EDEFAULT.equals(origParmSig);
			case LUWPackage.PLSQL_PACKAGE__EXTENDED_OPTIONS:
				return extendedOptions != null && !extendedOptions.isEmpty();
			case LUWPackage.PLSQL_PACKAGE__ROUTINE_EXTENSIONS:
				return routineExtensions != null && !routineExtensions.isEmpty();
			case LUWPackage.PLSQL_PACKAGE__PACKAGE_BODY:
				return packageBody != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == Routine.class) {
			switch (derivedFeatureID) {
				case LUWPackage.PLSQL_PACKAGE__SPECIFIC_NAME: return SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME;
				case LUWPackage.PLSQL_PACKAGE__LANGUAGE: return SQLRoutinesPackage.ROUTINE__LANGUAGE;
				case LUWPackage.PLSQL_PACKAGE__PARAMETER_STYLE: return SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE;
				case LUWPackage.PLSQL_PACKAGE__DETERMINISTIC: return SQLRoutinesPackage.ROUTINE__DETERMINISTIC;
				case LUWPackage.PLSQL_PACKAGE__SQL_DATA_ACCESS: return SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS;
				case LUWPackage.PLSQL_PACKAGE__CREATION_TS: return SQLRoutinesPackage.ROUTINE__CREATION_TS;
				case LUWPackage.PLSQL_PACKAGE__LAST_ALTERED_TS: return SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS;
				case LUWPackage.PLSQL_PACKAGE__AUTHORIZATION_ID: return SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID;
				case LUWPackage.PLSQL_PACKAGE__SECURITY: return SQLRoutinesPackage.ROUTINE__SECURITY;
				case LUWPackage.PLSQL_PACKAGE__EXTERNAL_NAME: return SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME;
				case LUWPackage.PLSQL_PACKAGE__PARAMETERS: return SQLRoutinesPackage.ROUTINE__PARAMETERS;
				case LUWPackage.PLSQL_PACKAGE__SOURCE: return SQLRoutinesPackage.ROUTINE__SOURCE;
				case LUWPackage.PLSQL_PACKAGE__SCHEMA: return SQLRoutinesPackage.ROUTINE__SCHEMA;
				default: return -1;
			}
		}
		if (baseClass == DB2AccessPlan.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DB2Routine.class) {
			switch (derivedFeatureID) {
				case LUWPackage.PLSQL_PACKAGE__FENCED: return DB2ModelPackage.DB2_ROUTINE__FENCED;
				case LUWPackage.PLSQL_PACKAGE__THREADSAFE: return DB2ModelPackage.DB2_ROUTINE__THREADSAFE;
				case LUWPackage.PLSQL_PACKAGE__DB_INFO: return DB2ModelPackage.DB2_ROUTINE__DB_INFO;
				case LUWPackage.PLSQL_PACKAGE__IMPLICIT_SCHEMA: return DB2ModelPackage.DB2_ROUTINE__IMPLICIT_SCHEMA;
				case LUWPackage.PLSQL_PACKAGE__FEDERATED: return DB2ModelPackage.DB2_ROUTINE__FEDERATED;
				case LUWPackage.PLSQL_PACKAGE__PARM_CCSID: return DB2ModelPackage.DB2_ROUTINE__PARM_CCSID;
				case LUWPackage.PLSQL_PACKAGE__SPECIAL_REGISTER: return DB2ModelPackage.DB2_ROUTINE__SPECIAL_REGISTER;
				case LUWPackage.PLSQL_PACKAGE__CHANGE_STATE: return DB2ModelPackage.DB2_ROUTINE__CHANGE_STATE;
				case LUWPackage.PLSQL_PACKAGE__DEBUG_ID: return DB2ModelPackage.DB2_ROUTINE__DEBUG_ID;
				case LUWPackage.PLSQL_PACKAGE__PROGRAM_TYPE: return DB2ModelPackage.DB2_ROUTINE__PROGRAM_TYPE;
				case LUWPackage.PLSQL_PACKAGE__ORIG_SCHEMA_NAME: return DB2ModelPackage.DB2_ROUTINE__ORIG_SCHEMA_NAME;
				case LUWPackage.PLSQL_PACKAGE__ORIG_PARM_SIG: return DB2ModelPackage.DB2_ROUTINE__ORIG_PARM_SIG;
				case LUWPackage.PLSQL_PACKAGE__EXTENDED_OPTIONS: return DB2ModelPackage.DB2_ROUTINE__EXTENDED_OPTIONS;
				case LUWPackage.PLSQL_PACKAGE__ROUTINE_EXTENSIONS: return DB2ModelPackage.DB2_ROUTINE__ROUTINE_EXTENSIONS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == Routine.class) {
			switch (baseFeatureID) {
				case SQLRoutinesPackage.ROUTINE__SPECIFIC_NAME: return LUWPackage.PLSQL_PACKAGE__SPECIFIC_NAME;
				case SQLRoutinesPackage.ROUTINE__LANGUAGE: return LUWPackage.PLSQL_PACKAGE__LANGUAGE;
				case SQLRoutinesPackage.ROUTINE__PARAMETER_STYLE: return LUWPackage.PLSQL_PACKAGE__PARAMETER_STYLE;
				case SQLRoutinesPackage.ROUTINE__DETERMINISTIC: return LUWPackage.PLSQL_PACKAGE__DETERMINISTIC;
				case SQLRoutinesPackage.ROUTINE__SQL_DATA_ACCESS: return LUWPackage.PLSQL_PACKAGE__SQL_DATA_ACCESS;
				case SQLRoutinesPackage.ROUTINE__CREATION_TS: return LUWPackage.PLSQL_PACKAGE__CREATION_TS;
				case SQLRoutinesPackage.ROUTINE__LAST_ALTERED_TS: return LUWPackage.PLSQL_PACKAGE__LAST_ALTERED_TS;
				case SQLRoutinesPackage.ROUTINE__AUTHORIZATION_ID: return LUWPackage.PLSQL_PACKAGE__AUTHORIZATION_ID;
				case SQLRoutinesPackage.ROUTINE__SECURITY: return LUWPackage.PLSQL_PACKAGE__SECURITY;
				case SQLRoutinesPackage.ROUTINE__EXTERNAL_NAME: return LUWPackage.PLSQL_PACKAGE__EXTERNAL_NAME;
				case SQLRoutinesPackage.ROUTINE__PARAMETERS: return LUWPackage.PLSQL_PACKAGE__PARAMETERS;
				case SQLRoutinesPackage.ROUTINE__SOURCE: return LUWPackage.PLSQL_PACKAGE__SOURCE;
				case SQLRoutinesPackage.ROUTINE__SCHEMA: return LUWPackage.PLSQL_PACKAGE__SCHEMA;
				default: return -1;
			}
		}
		if (baseClass == DB2AccessPlan.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DB2Routine.class) {
			switch (baseFeatureID) {
				case DB2ModelPackage.DB2_ROUTINE__FENCED: return LUWPackage.PLSQL_PACKAGE__FENCED;
				case DB2ModelPackage.DB2_ROUTINE__THREADSAFE: return LUWPackage.PLSQL_PACKAGE__THREADSAFE;
				case DB2ModelPackage.DB2_ROUTINE__DB_INFO: return LUWPackage.PLSQL_PACKAGE__DB_INFO;
				case DB2ModelPackage.DB2_ROUTINE__IMPLICIT_SCHEMA: return LUWPackage.PLSQL_PACKAGE__IMPLICIT_SCHEMA;
				case DB2ModelPackage.DB2_ROUTINE__FEDERATED: return LUWPackage.PLSQL_PACKAGE__FEDERATED;
				case DB2ModelPackage.DB2_ROUTINE__PARM_CCSID: return LUWPackage.PLSQL_PACKAGE__PARM_CCSID;
				case DB2ModelPackage.DB2_ROUTINE__SPECIAL_REGISTER: return LUWPackage.PLSQL_PACKAGE__SPECIAL_REGISTER;
				case DB2ModelPackage.DB2_ROUTINE__CHANGE_STATE: return LUWPackage.PLSQL_PACKAGE__CHANGE_STATE;
				case DB2ModelPackage.DB2_ROUTINE__DEBUG_ID: return LUWPackage.PLSQL_PACKAGE__DEBUG_ID;
				case DB2ModelPackage.DB2_ROUTINE__PROGRAM_TYPE: return LUWPackage.PLSQL_PACKAGE__PROGRAM_TYPE;
				case DB2ModelPackage.DB2_ROUTINE__ORIG_SCHEMA_NAME: return LUWPackage.PLSQL_PACKAGE__ORIG_SCHEMA_NAME;
				case DB2ModelPackage.DB2_ROUTINE__ORIG_PARM_SIG: return LUWPackage.PLSQL_PACKAGE__ORIG_PARM_SIG;
				case DB2ModelPackage.DB2_ROUTINE__EXTENDED_OPTIONS: return LUWPackage.PLSQL_PACKAGE__EXTENDED_OPTIONS;
				case DB2ModelPackage.DB2_ROUTINE__ROUTINE_EXTENSIONS: return LUWPackage.PLSQL_PACKAGE__ROUTINE_EXTENSIONS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

   /**
    * Gets a list of IN and INOUT parameters associated with this routine.
    * <p>
    * @return a list of IN and INOUT parameters associated with this routine.
    */
	public EList getInputParameters() {
		return getParameters(ParameterMode.IN);
	}

   /**
    * Gets a list of OUT and INOUT parameters associated with this routine.
    * <p>
    * @return a list of OUT and INOUT parameters associated with this routine.
    */
	public EList getOutputParameters() {
		return getParameters(ParameterMode.OUT);
	}

   /**
    * Gets a list of IN and INOUT parameters associated with this routine. If
    * the input parameter mode is <code>ParameterMode.IN</code>, then we
    * return parameters that are both IN and INOUT. Similarly, if the input
    * parameter mode is <code>ParameterMode.OUT</code>, then we return
    * parameters that are both OUT and INOUT.
    * <p>
    * @param aMode Either IN or OUT, INOUT is returned for either case.
    * @return A list of parameters according to the specified parameter
    * @see Parameter
    */
	protected EList getParameters(int aMode) {
		Parameter parm;
		EList allList = getParameters();
		EList filteredList = new BasicEList();
		for (int i = 0, parmCnt = allList.size(); i < parmCnt; i++) {
			parm = (Parameter)allList.get(i);
			if ((aMode == ParameterMode.IN) && (parm.getMode().getValue() != ParameterMode.OUT))
				filteredList.add(parm);
			else if ((aMode == ParameterMode.OUT) && (parm.getMode().getValue() != ParameterMode.IN))
				filteredList.add(parm);
		}
		return filteredList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (specificName: "); //$NON-NLS-1$
		result.append(specificName);
		result.append(", language: "); //$NON-NLS-1$
		result.append(language);
		result.append(", parameterStyle: "); //$NON-NLS-1$
		result.append(parameterStyle);
		result.append(", deterministic: "); //$NON-NLS-1$
		result.append(deterministic);
		result.append(", sqlDataAccess: "); //$NON-NLS-1$
		result.append(sqlDataAccess);
		result.append(", creationTS: "); //$NON-NLS-1$
		result.append(creationTS);
		result.append(", lastAlteredTS: "); //$NON-NLS-1$
		result.append(lastAlteredTS);
		result.append(", authorizationID: "); //$NON-NLS-1$
		result.append(authorizationID);
		result.append(", security: "); //$NON-NLS-1$
		result.append(security);
		result.append(", externalName: "); //$NON-NLS-1$
		result.append(externalName);
		result.append(", fenced: "); //$NON-NLS-1$
		result.append(fenced);
		result.append(", threadsafe: "); //$NON-NLS-1$
		result.append(threadsafe);
		result.append(", dbInfo: "); //$NON-NLS-1$
		result.append(dbInfo);
		result.append(", implicitSchema: "); //$NON-NLS-1$
		result.append(implicitSchema);
		result.append(", federated: "); //$NON-NLS-1$
		result.append(federated);
		result.append(", parmCcsid: "); //$NON-NLS-1$
		result.append(parmCcsid);
		result.append(", specialRegister: "); //$NON-NLS-1$
		result.append(specialRegister);
		result.append(", changeState: "); //$NON-NLS-1$
		result.append(changeState);
		result.append(", debugId: "); //$NON-NLS-1$
		result.append(debugId);
		result.append(", programType: "); //$NON-NLS-1$
		result.append(programType);
		result.append(", origSchemaName: "); //$NON-NLS-1$
		result.append(origSchemaName);
		result.append(", origParmSig: "); //$NON-NLS-1$
		result.append(origParmSig);
		result.append(')');
		return result.toString();
	}

} //PLSQLPackageImpl
