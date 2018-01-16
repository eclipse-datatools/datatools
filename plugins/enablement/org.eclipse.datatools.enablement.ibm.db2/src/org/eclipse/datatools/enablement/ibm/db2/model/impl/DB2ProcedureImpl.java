/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.routines.impl.ProcedureImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ProcedureDeploy;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension;
import org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Procedure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getFenced <em>Fenced</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getThreadsafe <em>Threadsafe</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#isDbInfo <em>Db Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#isImplicitSchema <em>Implicit Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#isFederated <em>Federated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getParmCcsid <em>Parm Ccsid</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getSpecialRegister <em>Special Register</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getChangeState <em>Change State</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getDebugId <em>Debug Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getProgramType <em>Program Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getOrigSchemaName <em>Orig Schema Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getOrigParmSig <em>Orig Parm Sig</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getExtendedOptions <em>Extended Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getRoutineExtensions <em>Routine Extensions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#isModelResultSets <em>Model Result Sets</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#isNullInput <em>Null Input</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getDialect <em>Dialect</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#isExternalAction <em>External Action</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getReturn <em>Return</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getJavaOptions <em>Java Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ProcedureImpl#getDeploy <em>Deploy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2ProcedureImpl extends ProcedureImpl implements DB2Procedure {
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
	 * The default value of the '{@link #isModelResultSets() <em>Model Result Sets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isModelResultSets()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MODEL_RESULT_SETS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isModelResultSets() <em>Model Result Sets</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isModelResultSets()
	 * @generated
	 * @ordered
	 */
	protected boolean modelResultSets = MODEL_RESULT_SETS_EDEFAULT;

	/**
	 * The default value of the '{@link #isNullInput() <em>Null Input</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isNullInput()
	 * @generated
	 * @ordered
	 */
   protected static final boolean NULL_INPUT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNullInput() <em>Null Input</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isNullInput()
	 * @generated
	 * @ordered
	 */
   protected boolean nullInput = NULL_INPUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
   protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
   protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDialect() <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDialect()
	 * @generated
	 * @ordered
	 */
	protected static final SourceDialect DIALECT_EDEFAULT = SourceDialect.UNKNOWN_LITERAL;

	/**
	 * The cached value of the '{@link #getDialect() <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDialect()
	 * @generated
	 * @ordered
	 */
	protected SourceDialect dialect = DIALECT_EDEFAULT;

	/**
	 * The default value of the '{@link #isExternalAction() <em>External Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExternalAction()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXTERNAL_ACTION_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isExternalAction() <em>External Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExternalAction()
	 * @generated
	 * @ordered
	 */
	protected boolean externalAction = EXTERNAL_ACTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReturn() <em>Return</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturn()
	 * @generated
	 * @ordered
	 */
	protected IntegerDataType return_;

	/**
	 * The cached value of the '{@link #getJavaOptions() <em>Java Options</em>}' containment reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getJavaOptions()
	 * @generated
	 * @ordered
	 */
   protected DB2JavaOptions javaOptions;

	/**
	 * The cached value of the '{@link #getDeploy() <em>Deploy</em>}' containment reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getDeploy()
	 * @generated
	 * @ordered
	 */
   protected DB2ProcedureDeploy deploy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2ProcedureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_PROCEDURE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__FENCED, oldFenced, fenced));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__THREADSAFE, oldThreadsafe, threadsafe));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__DB_INFO, oldDbInfo, dbInfo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA, oldImplicitSchema, implicitSchema));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__FEDERATED, oldFederated, federated));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID, oldParmCcsid, parmCcsid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER, oldSpecialRegister, specialRegister));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE, oldChangeState, changeState));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID, oldDebugId, debugId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE, oldProgramType, programType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME, oldOrigSchemaName, origSchemaName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG, oldOrigParmSig, origParmSig));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList getExtendedOptions() {
		if (extendedOptions == null) {
			extendedOptions = new EObjectContainmentEList(DB2ExtendedOptions.class, this, DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS);
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
			routineExtensions = new EObjectResolvingEList(DB2RoutineExtension.class, this, DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS);
		}
		return routineExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2ProcedureDeploy getDeploy() {
		return deploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeploy(DB2ProcedureDeploy newDeploy, NotificationChain msgs) {
		DB2ProcedureDeploy oldDeploy = deploy;
		deploy = newDeploy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__DEPLOY, oldDeploy, newDeploy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeploy(DB2ProcedureDeploy newDeploy) {
		if (newDeploy != deploy) {
			NotificationChain msgs = null;
			if (deploy != null)
				msgs = ((InternalEObject)deploy).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_PROCEDURE__DEPLOY, null, msgs);
			if (newDeploy != null)
				msgs = ((InternalEObject)newDeploy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_PROCEDURE__DEPLOY, null, msgs);
			msgs = basicSetDeploy(newDeploy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__DEPLOY, newDeploy, newDeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS:
				if (javaOptions != null)
					msgs = ((InternalEObject)javaOptions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS, null, msgs);
				return basicSetJavaOptions((DB2JavaOptions)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS:
				return ((InternalEList)getExtendedOptions()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_PROCEDURE__RETURN:
				return basicSetReturn(null, msgs);
			case DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS:
				return basicSetJavaOptions(null, msgs);
			case DB2ModelPackage.DB2_PROCEDURE__DEPLOY:
				return basicSetDeploy(null, msgs);
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
			case DB2ModelPackage.DB2_PROCEDURE__FENCED:
				return getFenced();
			case DB2ModelPackage.DB2_PROCEDURE__THREADSAFE:
				return getThreadsafe();
			case DB2ModelPackage.DB2_PROCEDURE__DB_INFO:
				return isDbInfo() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA:
				return isImplicitSchema() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_PROCEDURE__FEDERATED:
				return isFederated() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID:
				return getParmCcsid();
			case DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER:
				return getSpecialRegister();
			case DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE:
				return new Integer(getChangeState());
			case DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID:
				return getDebugId();
			case DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE:
				return getProgramType();
			case DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME:
				return getOrigSchemaName();
			case DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG:
				return getOrigParmSig();
			case DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS:
				return getExtendedOptions();
			case DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS:
				return getRoutineExtensions();
			case DB2ModelPackage.DB2_PROCEDURE__MODEL_RESULT_SETS:
				return isModelResultSets() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_PROCEDURE__NULL_INPUT:
				return isNullInput() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_PROCEDURE__VERSION:
				return getVersion();
			case DB2ModelPackage.DB2_PROCEDURE__DIALECT:
				return getDialect();
			case DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_ACTION:
				return isExternalAction() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_PROCEDURE__RETURN:
				return getReturn();
			case DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS:
				return getJavaOptions();
			case DB2ModelPackage.DB2_PROCEDURE__DEPLOY:
				return getDeploy();
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
			case DB2ModelPackage.DB2_PROCEDURE__FENCED:
				setFenced((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__THREADSAFE:
				setThreadsafe((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__DB_INFO:
				setDbInfo(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA:
				setImplicitSchema(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_PROCEDURE__FEDERATED:
				setFederated(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID:
				setParmCcsid((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER:
				setSpecialRegister((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE:
				setChangeState(((Integer)newValue).intValue());
				return;
			case DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID:
				setDebugId((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE:
				setProgramType((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME:
				setOrigSchemaName((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG:
				setOrigParmSig((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS:
				getExtendedOptions().clear();
				getExtendedOptions().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS:
				getRoutineExtensions().clear();
				getRoutineExtensions().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__MODEL_RESULT_SETS:
				setModelResultSets(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_PROCEDURE__NULL_INPUT:
				setNullInput(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_PROCEDURE__VERSION:
				setVersion((String)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__DIALECT:
				setDialect((SourceDialect)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_ACTION:
				setExternalAction(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_PROCEDURE__RETURN:
				setReturn((IntegerDataType)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS:
				setJavaOptions((DB2JavaOptions)newValue);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__DEPLOY:
				setDeploy((DB2ProcedureDeploy)newValue);
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
			case DB2ModelPackage.DB2_PROCEDURE__FENCED:
				setFenced(FENCED_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__THREADSAFE:
				setThreadsafe(THREADSAFE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__DB_INFO:
				setDbInfo(DB_INFO_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA:
				setImplicitSchema(IMPLICIT_SCHEMA_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__FEDERATED:
				setFederated(FEDERATED_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID:
				setParmCcsid(PARM_CCSID_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER:
				setSpecialRegister(SPECIAL_REGISTER_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE:
				setChangeState(CHANGE_STATE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID:
				setDebugId(DEBUG_ID_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE:
				setProgramType(PROGRAM_TYPE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME:
				setOrigSchemaName(ORIG_SCHEMA_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG:
				setOrigParmSig(ORIG_PARM_SIG_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS:
				getExtendedOptions().clear();
				return;
			case DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS:
				getRoutineExtensions().clear();
				return;
			case DB2ModelPackage.DB2_PROCEDURE__MODEL_RESULT_SETS:
				setModelResultSets(MODEL_RESULT_SETS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__NULL_INPUT:
				setNullInput(NULL_INPUT_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__DIALECT:
				setDialect(DIALECT_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_ACTION:
				setExternalAction(EXTERNAL_ACTION_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__RETURN:
				setReturn((IntegerDataType)null);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS:
				setJavaOptions((DB2JavaOptions)null);
				return;
			case DB2ModelPackage.DB2_PROCEDURE__DEPLOY:
				setDeploy((DB2ProcedureDeploy)null);
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
			case DB2ModelPackage.DB2_PROCEDURE__FENCED:
				return FENCED_EDEFAULT == null ? fenced != null : !FENCED_EDEFAULT.equals(fenced);
			case DB2ModelPackage.DB2_PROCEDURE__THREADSAFE:
				return THREADSAFE_EDEFAULT == null ? threadsafe != null : !THREADSAFE_EDEFAULT.equals(threadsafe);
			case DB2ModelPackage.DB2_PROCEDURE__DB_INFO:
				return dbInfo != DB_INFO_EDEFAULT;
			case DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA:
				return implicitSchema != IMPLICIT_SCHEMA_EDEFAULT;
			case DB2ModelPackage.DB2_PROCEDURE__FEDERATED:
				return federated != FEDERATED_EDEFAULT;
			case DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID:
				return PARM_CCSID_EDEFAULT == null ? parmCcsid != null : !PARM_CCSID_EDEFAULT.equals(parmCcsid);
			case DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER:
				return SPECIAL_REGISTER_EDEFAULT == null ? specialRegister != null : !SPECIAL_REGISTER_EDEFAULT.equals(specialRegister);
			case DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE:
				return changeState != CHANGE_STATE_EDEFAULT;
			case DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID:
				return DEBUG_ID_EDEFAULT == null ? debugId != null : !DEBUG_ID_EDEFAULT.equals(debugId);
			case DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE:
				return PROGRAM_TYPE_EDEFAULT == null ? programType != null : !PROGRAM_TYPE_EDEFAULT.equals(programType);
			case DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME:
				return ORIG_SCHEMA_NAME_EDEFAULT == null ? origSchemaName != null : !ORIG_SCHEMA_NAME_EDEFAULT.equals(origSchemaName);
			case DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG:
				return ORIG_PARM_SIG_EDEFAULT == null ? origParmSig != null : !ORIG_PARM_SIG_EDEFAULT.equals(origParmSig);
			case DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS:
				return extendedOptions != null && !extendedOptions.isEmpty();
			case DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS:
				return routineExtensions != null && !routineExtensions.isEmpty();
			case DB2ModelPackage.DB2_PROCEDURE__MODEL_RESULT_SETS:
				return modelResultSets != MODEL_RESULT_SETS_EDEFAULT;
			case DB2ModelPackage.DB2_PROCEDURE__NULL_INPUT:
				return nullInput != NULL_INPUT_EDEFAULT;
			case DB2ModelPackage.DB2_PROCEDURE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case DB2ModelPackage.DB2_PROCEDURE__DIALECT:
				return dialect != DIALECT_EDEFAULT;
			case DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_ACTION:
				return externalAction != EXTERNAL_ACTION_EDEFAULT;
			case DB2ModelPackage.DB2_PROCEDURE__RETURN:
				return return_ != null;
			case DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS:
				return javaOptions != null;
			case DB2ModelPackage.DB2_PROCEDURE__DEPLOY:
				return deploy != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isModelResultSets() {
		return modelResultSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelResultSets(boolean newModelResultSets) {
		boolean oldModelResultSets = modelResultSets;
		modelResultSets = newModelResultSets;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__MODEL_RESULT_SETS, oldModelResultSets, modelResultSets));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public boolean isNullInput() {
		return nullInput;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setNullInput(boolean newNullInput) {
		boolean oldNullInput = nullInput;
		nullInput = newNullInput;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__NULL_INPUT, oldNullInput, nullInput));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SourceDialect getDialect() {
		return dialect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDialect(SourceDialect newDialect) {
		SourceDialect oldDialect = dialect;
		dialect = newDialect == null ? DIALECT_EDEFAULT : newDialect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__DIALECT, oldDialect, dialect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExternalAction() {
		return externalAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalAction(boolean newExternalAction) {
		boolean oldExternalAction = externalAction;
		externalAction = newExternalAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__EXTERNAL_ACTION, oldExternalAction, externalAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerDataType getReturn() {
		return return_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReturn(IntegerDataType newReturn, NotificationChain msgs) {
		IntegerDataType oldReturn = return_;
		return_ = newReturn;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__RETURN, oldReturn, newReturn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturn(IntegerDataType newReturn) {
		if (newReturn != return_) {
			NotificationChain msgs = null;
			if (return_ != null)
				msgs = ((InternalEObject)return_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_PROCEDURE__RETURN, null, msgs);
			if (newReturn != null)
				msgs = ((InternalEObject)newReturn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_PROCEDURE__RETURN, null, msgs);
			msgs = basicSetReturn(newReturn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__RETURN, newReturn, newReturn));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2JavaOptions getJavaOptions() {
		return javaOptions;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public NotificationChain basicSetJavaOptions(DB2JavaOptions newJavaOptions, NotificationChain msgs) {
		DB2JavaOptions oldJavaOptions = javaOptions;
		javaOptions = newJavaOptions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS, oldJavaOptions, newJavaOptions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setJavaOptions(DB2JavaOptions newJavaOptions) {
		if (newJavaOptions != javaOptions) {
			NotificationChain msgs = null;
			if (javaOptions != null)
				msgs = ((InternalEObject)javaOptions).eInverseRemove(this, DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE, DB2JavaOptions.class, msgs);
			if (newJavaOptions != null)
				msgs = ((InternalEObject)newJavaOptions).eInverseAdd(this, DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE, DB2JavaOptions.class, msgs);
			msgs = basicSetJavaOptions(newJavaOptions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS, newJavaOptions, newJavaOptions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == DB2AccessPlan.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DB2Routine.class) {
			switch (derivedFeatureID) {
				case DB2ModelPackage.DB2_PROCEDURE__FENCED: return DB2ModelPackage.DB2_ROUTINE__FENCED;
				case DB2ModelPackage.DB2_PROCEDURE__THREADSAFE: return DB2ModelPackage.DB2_ROUTINE__THREADSAFE;
				case DB2ModelPackage.DB2_PROCEDURE__DB_INFO: return DB2ModelPackage.DB2_ROUTINE__DB_INFO;
				case DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA: return DB2ModelPackage.DB2_ROUTINE__IMPLICIT_SCHEMA;
				case DB2ModelPackage.DB2_PROCEDURE__FEDERATED: return DB2ModelPackage.DB2_ROUTINE__FEDERATED;
				case DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID: return DB2ModelPackage.DB2_ROUTINE__PARM_CCSID;
				case DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER: return DB2ModelPackage.DB2_ROUTINE__SPECIAL_REGISTER;
				case DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE: return DB2ModelPackage.DB2_ROUTINE__CHANGE_STATE;
				case DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID: return DB2ModelPackage.DB2_ROUTINE__DEBUG_ID;
				case DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE: return DB2ModelPackage.DB2_ROUTINE__PROGRAM_TYPE;
				case DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME: return DB2ModelPackage.DB2_ROUTINE__ORIG_SCHEMA_NAME;
				case DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG: return DB2ModelPackage.DB2_ROUTINE__ORIG_PARM_SIG;
				case DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS: return DB2ModelPackage.DB2_ROUTINE__EXTENDED_OPTIONS;
				case DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS: return DB2ModelPackage.DB2_ROUTINE__ROUTINE_EXTENSIONS;
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
		if (baseClass == DB2AccessPlan.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DB2Routine.class) {
			switch (baseFeatureID) {
				case DB2ModelPackage.DB2_ROUTINE__FENCED: return DB2ModelPackage.DB2_PROCEDURE__FENCED;
				case DB2ModelPackage.DB2_ROUTINE__THREADSAFE: return DB2ModelPackage.DB2_PROCEDURE__THREADSAFE;
				case DB2ModelPackage.DB2_ROUTINE__DB_INFO: return DB2ModelPackage.DB2_PROCEDURE__DB_INFO;
				case DB2ModelPackage.DB2_ROUTINE__IMPLICIT_SCHEMA: return DB2ModelPackage.DB2_PROCEDURE__IMPLICIT_SCHEMA;
				case DB2ModelPackage.DB2_ROUTINE__FEDERATED: return DB2ModelPackage.DB2_PROCEDURE__FEDERATED;
				case DB2ModelPackage.DB2_ROUTINE__PARM_CCSID: return DB2ModelPackage.DB2_PROCEDURE__PARM_CCSID;
				case DB2ModelPackage.DB2_ROUTINE__SPECIAL_REGISTER: return DB2ModelPackage.DB2_PROCEDURE__SPECIAL_REGISTER;
				case DB2ModelPackage.DB2_ROUTINE__CHANGE_STATE: return DB2ModelPackage.DB2_PROCEDURE__CHANGE_STATE;
				case DB2ModelPackage.DB2_ROUTINE__DEBUG_ID: return DB2ModelPackage.DB2_PROCEDURE__DEBUG_ID;
				case DB2ModelPackage.DB2_ROUTINE__PROGRAM_TYPE: return DB2ModelPackage.DB2_PROCEDURE__PROGRAM_TYPE;
				case DB2ModelPackage.DB2_ROUTINE__ORIG_SCHEMA_NAME: return DB2ModelPackage.DB2_PROCEDURE__ORIG_SCHEMA_NAME;
				case DB2ModelPackage.DB2_ROUTINE__ORIG_PARM_SIG: return DB2ModelPackage.DB2_PROCEDURE__ORIG_PARM_SIG;
				case DB2ModelPackage.DB2_ROUTINE__EXTENDED_OPTIONS: return DB2ModelPackage.DB2_PROCEDURE__EXTENDED_OPTIONS;
				case DB2ModelPackage.DB2_ROUTINE__ROUTINE_EXTENSIONS: return DB2ModelPackage.DB2_PROCEDURE__ROUTINE_EXTENSIONS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (fenced: "); //$NON-NLS-1$
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
		result.append(", modelResultSets: "); //$NON-NLS-1$
		result.append(modelResultSets);
		result.append(", nullInput: "); //$NON-NLS-1$
		result.append(nullInput);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", dialect: "); //$NON-NLS-1$
		result.append(dialect);
		result.append(", externalAction: "); //$NON-NLS-1$
		result.append(externalAction);
		result.append(')');
		return result.toString();
	}

} //DB2ProcedureImpl
