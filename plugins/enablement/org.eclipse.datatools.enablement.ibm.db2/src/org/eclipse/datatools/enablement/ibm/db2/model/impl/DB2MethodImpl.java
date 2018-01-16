/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.routines.impl.MethodImpl;
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
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Function;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Method;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Routine;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2RoutineExtension;
import org.eclipse.datatools.enablement.ibm.db2.model.OriginType;
import org.eclipse.datatools.enablement.ibm.db2.model.SourceDialect;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getFenced <em>Fenced</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getThreadsafe <em>Threadsafe</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isDbInfo <em>Db Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isImplicitSchema <em>Implicit Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isFederated <em>Federated</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getParmCcsid <em>Parm Ccsid</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getSpecialRegister <em>Special Register</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getChangeState <em>Change State</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getDebugId <em>Debug Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getProgramType <em>Program Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getOrigSchemaName <em>Orig Schema Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getOrigParmSig <em>Orig Parm Sig</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getExtendedOptions <em>Extended Options</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getRoutineExtensions <em>Routine Extensions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isFinalCall <em>Final Call</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isScratchPad <em>Scratch Pad</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getScratchPadLength <em>Scratch Pad Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getFunctionType <em>Function Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isExternalAction <em>External Action</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isAllowParallel <em>Allow Parallel</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getReturnClause <em>Return Clause</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getOrigin <em>Origin</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isInheritLockRequest <em>Inherit Lock Request</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getDialect <em>Dialect</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isInline <em>Inline</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isSecured <em>Secured</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isReturnsSelfAsResult <em>Returns Self As Result</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2MethodImpl#isImplemented <em>Implemented</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2MethodImpl extends MethodImpl implements DB2Method {
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
	 * The default value of the '{@link #isFinalCall() <em>Final Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinalCall()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_CALL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinalCall() <em>Final Call</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinalCall()
	 * @generated
	 * @ordered
	 */
	protected boolean finalCall = FINAL_CALL_EDEFAULT;

	/**
	 * The default value of the '{@link #isScratchPad() <em>Scratch Pad</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScratchPad()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCRATCH_PAD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isScratchPad() <em>Scratch Pad</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScratchPad()
	 * @generated
	 * @ordered
	 */
	protected boolean scratchPad = SCRATCH_PAD_EDEFAULT;

	/**
	 * The default value of the '{@link #getScratchPadLength() <em>Scratch Pad Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScratchPadLength()
	 * @generated
	 * @ordered
	 */
	protected static final int SCRATCH_PAD_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getScratchPadLength() <em>Scratch Pad Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScratchPadLength()
	 * @generated
	 * @ordered
	 */
	protected int scratchPadLength = SCRATCH_PAD_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getFunctionType() <em>Function Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionType()
	 * @generated
	 * @ordered
	 */
	protected static final String FUNCTION_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFunctionType() <em>Function Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionType()
	 * @generated
	 * @ordered
	 */
	protected String functionType = FUNCTION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredicate() <em>Predicate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredicate()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredicate() <em>Predicate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredicate()
	 * @generated
	 * @ordered
	 */
	protected String predicate = PREDICATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isExternalAction() <em>External Action</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isExternalAction()
	 * @generated
	 * @ordered
	 */
   protected static final boolean EXTERNAL_ACTION_EDEFAULT = false;

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
	 * The default value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
   protected static final int CARDINALITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
   protected int cardinality = CARDINALITY_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllowParallel() <em>Allow Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isAllowParallel()
	 * @generated
	 * @ordered
	 */
   protected static final boolean ALLOW_PARALLEL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllowParallel() <em>Allow Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isAllowParallel()
	 * @generated
	 * @ordered
	 */
   protected boolean allowParallel = ALLOW_PARALLEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getReturnClause() <em>Return Clause</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getReturnClause()
	 * @generated
	 * @ordered
	 */
   protected static final String RETURN_CLAUSE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReturnClause() <em>Return Clause</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getReturnClause()
	 * @generated
	 * @ordered
	 */
   protected String returnClause = RETURN_CLAUSE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected static final OriginType ORIGIN_EDEFAULT = OriginType.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigin()
	 * @generated
	 * @ordered
	 */
	protected OriginType origin = ORIGIN_EDEFAULT;

	/**
	 * The default value of the '{@link #isInheritLockRequest() <em>Inherit Lock Request</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInheritLockRequest()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INHERIT_LOCK_REQUEST_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInheritLockRequest() <em>Inherit Lock Request</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInheritLockRequest()
	 * @generated
	 * @ordered
	 */
	protected boolean inheritLockRequest = INHERIT_LOCK_REQUEST_EDEFAULT;

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
	 * The default value of the '{@link #isInline() <em>Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInline()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INLINE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInline() <em>Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInline()
	 * @generated
	 * @ordered
	 */
	protected boolean inline = INLINE_EDEFAULT;

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
	 * The default value of the '{@link #isSecured() <em>Secured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSecured()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SECURED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSecured() <em>Secured</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSecured()
	 * @generated
	 * @ordered
	 */
	protected boolean secured = SECURED_EDEFAULT;

	/**
	 * The default value of the '{@link #isReturnsSelfAsResult() <em>Returns Self As Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnsSelfAsResult()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RETURNS_SELF_AS_RESULT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReturnsSelfAsResult() <em>Returns Self As Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReturnsSelfAsResult()
	 * @generated
	 * @ordered
	 */
	protected boolean returnsSelfAsResult = RETURNS_SELF_AS_RESULT_EDEFAULT;

	/**
	 * The default value of the '{@link #isImplemented() <em>Implemented</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplemented()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IMPLEMENTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isImplemented() <em>Implemented</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isImplemented()
	 * @generated
	 * @ordered
	 */
	protected boolean implemented = IMPLEMENTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2MethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_METHOD;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__FENCED, oldFenced, fenced));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__THREADSAFE, oldThreadsafe, threadsafe));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__DB_INFO, oldDbInfo, dbInfo));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__IMPLICIT_SCHEMA, oldImplicitSchema, implicitSchema));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__FEDERATED, oldFederated, federated));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__PARM_CCSID, oldParmCcsid, parmCcsid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__SPECIAL_REGISTER, oldSpecialRegister, specialRegister));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__CHANGE_STATE, oldChangeState, changeState));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__DEBUG_ID, oldDebugId, debugId));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__PROGRAM_TYPE, oldProgramType, programType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__ORIG_SCHEMA_NAME, oldOrigSchemaName, origSchemaName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__ORIG_PARM_SIG, oldOrigParmSig, origParmSig));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__DIALECT, oldDialect, dialect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInline() {
		return inline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInline(boolean newInline) {
		boolean oldInline = inline;
		inline = newInline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__INLINE, oldInline, inline));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSecured() {
		return secured;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecured(boolean newSecured) {
		boolean oldSecured = secured;
		secured = newSecured;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__SECURED, oldSecured, secured));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList getExtendedOptions() {
		if (extendedOptions == null) {
			extendedOptions = new EObjectContainmentEList(DB2ExtendedOptions.class, this, DB2ModelPackage.DB2_METHOD__EXTENDED_OPTIONS);
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
			routineExtensions = new EObjectResolvingEList(DB2RoutineExtension.class, this, DB2ModelPackage.DB2_METHOD__ROUTINE_EXTENSIONS);
		}
		return routineExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinalCall() {
		return finalCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinalCall(boolean newFinalCall) {
		boolean oldFinalCall = finalCall;
		finalCall = newFinalCall;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__FINAL_CALL, oldFinalCall, finalCall));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isScratchPad() {
		return scratchPad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScratchPad(boolean newScratchPad) {
		boolean oldScratchPad = scratchPad;
		scratchPad = newScratchPad;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__SCRATCH_PAD, oldScratchPad, scratchPad));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getScratchPadLength() {
		return scratchPadLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScratchPadLength(int newScratchPadLength) {
		int oldScratchPadLength = scratchPadLength;
		scratchPadLength = newScratchPadLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__SCRATCH_PAD_LENGTH, oldScratchPadLength, scratchPadLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFunctionType() {
		return functionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionType(String newFunctionType) {
		String oldFunctionType = functionType;
		functionType = newFunctionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__FUNCTION_TYPE, oldFunctionType, functionType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredicate() {
		return predicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredicate(String newPredicate) {
		String oldPredicate = predicate;
		predicate = newPredicate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__PREDICATE, oldPredicate, predicate));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__EXTERNAL_ACTION, oldExternalAction, externalAction));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public int getCardinality() {
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setCardinality(int newCardinality) {
		int oldCardinality = cardinality;
		cardinality = newCardinality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__CARDINALITY, oldCardinality, cardinality));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public boolean isAllowParallel() {
		return allowParallel;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setAllowParallel(boolean newAllowParallel) {
		boolean oldAllowParallel = allowParallel;
		allowParallel = newAllowParallel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__ALLOW_PARALLEL, oldAllowParallel, allowParallel));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getReturnClause() {
		return returnClause;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setReturnClause(String newReturnClause) {
		String oldReturnClause = returnClause;
		returnClause = newReturnClause;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__RETURN_CLAUSE, oldReturnClause, returnClause));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OriginType getOrigin() {
		return origin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrigin(OriginType newOrigin) {
		OriginType oldOrigin = origin;
		origin = newOrigin == null ? ORIGIN_EDEFAULT : newOrigin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__ORIGIN, oldOrigin, origin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInheritLockRequest() {
		return inheritLockRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInheritLockRequest(boolean newInheritLockRequest) {
		boolean oldInheritLockRequest = inheritLockRequest;
		inheritLockRequest = newInheritLockRequest;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__INHERIT_LOCK_REQUEST, oldInheritLockRequest, inheritLockRequest));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReturnsSelfAsResult() {
		return returnsSelfAsResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnsSelfAsResult(boolean newReturnsSelfAsResult) {
		boolean oldReturnsSelfAsResult = returnsSelfAsResult;
		returnsSelfAsResult = newReturnsSelfAsResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__RETURNS_SELF_AS_RESULT, oldReturnsSelfAsResult, returnsSelfAsResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isImplemented() {
		return implemented;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplemented(boolean newImplemented) {
		boolean oldImplemented = implemented;
		implemented = newImplemented;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_METHOD__IMPLEMENTED, oldImplemented, implemented));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_METHOD__EXTENDED_OPTIONS:
				return ((InternalEList)getExtendedOptions()).basicRemove(otherEnd, msgs);
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
			case DB2ModelPackage.DB2_METHOD__FENCED:
				return getFenced();
			case DB2ModelPackage.DB2_METHOD__THREADSAFE:
				return getThreadsafe();
			case DB2ModelPackage.DB2_METHOD__DB_INFO:
				return isDbInfo() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__IMPLICIT_SCHEMA:
				return isImplicitSchema() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__FEDERATED:
				return isFederated() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__PARM_CCSID:
				return getParmCcsid();
			case DB2ModelPackage.DB2_METHOD__SPECIAL_REGISTER:
				return getSpecialRegister();
			case DB2ModelPackage.DB2_METHOD__CHANGE_STATE:
				return new Integer(getChangeState());
			case DB2ModelPackage.DB2_METHOD__DEBUG_ID:
				return getDebugId();
			case DB2ModelPackage.DB2_METHOD__PROGRAM_TYPE:
				return getProgramType();
			case DB2ModelPackage.DB2_METHOD__ORIG_SCHEMA_NAME:
				return getOrigSchemaName();
			case DB2ModelPackage.DB2_METHOD__ORIG_PARM_SIG:
				return getOrigParmSig();
			case DB2ModelPackage.DB2_METHOD__EXTENDED_OPTIONS:
				return getExtendedOptions();
			case DB2ModelPackage.DB2_METHOD__ROUTINE_EXTENSIONS:
				return getRoutineExtensions();
			case DB2ModelPackage.DB2_METHOD__FINAL_CALL:
				return isFinalCall() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD:
				return isScratchPad() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD_LENGTH:
				return new Integer(getScratchPadLength());
			case DB2ModelPackage.DB2_METHOD__FUNCTION_TYPE:
				return getFunctionType();
			case DB2ModelPackage.DB2_METHOD__PREDICATE:
				return getPredicate();
			case DB2ModelPackage.DB2_METHOD__EXTERNAL_ACTION:
				return isExternalAction() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__CARDINALITY:
				return new Integer(getCardinality());
			case DB2ModelPackage.DB2_METHOD__ALLOW_PARALLEL:
				return isAllowParallel() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__RETURN_CLAUSE:
				return getReturnClause();
			case DB2ModelPackage.DB2_METHOD__ORIGIN:
				return getOrigin();
			case DB2ModelPackage.DB2_METHOD__INHERIT_LOCK_REQUEST:
				return isInheritLockRequest() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__DIALECT:
				return getDialect();
			case DB2ModelPackage.DB2_METHOD__INLINE:
				return isInline() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__VERSION:
				return getVersion();
			case DB2ModelPackage.DB2_METHOD__SECURED:
				return isSecured() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__RETURNS_SELF_AS_RESULT:
				return isReturnsSelfAsResult() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_METHOD__IMPLEMENTED:
				return isImplemented() ? Boolean.TRUE : Boolean.FALSE;
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
			case DB2ModelPackage.DB2_METHOD__FENCED:
				setFenced((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__THREADSAFE:
				setThreadsafe((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__DB_INFO:
				setDbInfo(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__IMPLICIT_SCHEMA:
				setImplicitSchema(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__FEDERATED:
				setFederated(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__PARM_CCSID:
				setParmCcsid((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__SPECIAL_REGISTER:
				setSpecialRegister((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__CHANGE_STATE:
				setChangeState(((Integer)newValue).intValue());
				return;
			case DB2ModelPackage.DB2_METHOD__DEBUG_ID:
				setDebugId((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__PROGRAM_TYPE:
				setProgramType((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__ORIG_SCHEMA_NAME:
				setOrigSchemaName((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__ORIG_PARM_SIG:
				setOrigParmSig((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__EXTENDED_OPTIONS:
				getExtendedOptions().clear();
				getExtendedOptions().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__ROUTINE_EXTENSIONS:
				getRoutineExtensions().clear();
				getRoutineExtensions().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__FINAL_CALL:
				setFinalCall(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD:
				setScratchPad(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD_LENGTH:
				setScratchPadLength(((Integer)newValue).intValue());
				return;
			case DB2ModelPackage.DB2_METHOD__FUNCTION_TYPE:
				setFunctionType((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__PREDICATE:
				setPredicate((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__EXTERNAL_ACTION:
				setExternalAction(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__CARDINALITY:
				setCardinality(((Integer)newValue).intValue());
				return;
			case DB2ModelPackage.DB2_METHOD__ALLOW_PARALLEL:
				setAllowParallel(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__RETURN_CLAUSE:
				setReturnClause((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__ORIGIN:
				setOrigin((OriginType)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__INHERIT_LOCK_REQUEST:
				setInheritLockRequest(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__DIALECT:
				setDialect((SourceDialect)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__INLINE:
				setInline(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__VERSION:
				setVersion((String)newValue);
				return;
			case DB2ModelPackage.DB2_METHOD__SECURED:
				setSecured(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__RETURNS_SELF_AS_RESULT:
				setReturnsSelfAsResult(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_METHOD__IMPLEMENTED:
				setImplemented(((Boolean)newValue).booleanValue());
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
			case DB2ModelPackage.DB2_METHOD__FENCED:
				setFenced(FENCED_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__THREADSAFE:
				setThreadsafe(THREADSAFE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__DB_INFO:
				setDbInfo(DB_INFO_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__IMPLICIT_SCHEMA:
				setImplicitSchema(IMPLICIT_SCHEMA_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__FEDERATED:
				setFederated(FEDERATED_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__PARM_CCSID:
				setParmCcsid(PARM_CCSID_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__SPECIAL_REGISTER:
				setSpecialRegister(SPECIAL_REGISTER_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__CHANGE_STATE:
				setChangeState(CHANGE_STATE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__DEBUG_ID:
				setDebugId(DEBUG_ID_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__PROGRAM_TYPE:
				setProgramType(PROGRAM_TYPE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__ORIG_SCHEMA_NAME:
				setOrigSchemaName(ORIG_SCHEMA_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__ORIG_PARM_SIG:
				setOrigParmSig(ORIG_PARM_SIG_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__EXTENDED_OPTIONS:
				getExtendedOptions().clear();
				return;
			case DB2ModelPackage.DB2_METHOD__ROUTINE_EXTENSIONS:
				getRoutineExtensions().clear();
				return;
			case DB2ModelPackage.DB2_METHOD__FINAL_CALL:
				setFinalCall(FINAL_CALL_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD:
				setScratchPad(SCRATCH_PAD_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD_LENGTH:
				setScratchPadLength(SCRATCH_PAD_LENGTH_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__FUNCTION_TYPE:
				setFunctionType(FUNCTION_TYPE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__PREDICATE:
				setPredicate(PREDICATE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__EXTERNAL_ACTION:
				setExternalAction(EXTERNAL_ACTION_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__CARDINALITY:
				setCardinality(CARDINALITY_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__ALLOW_PARALLEL:
				setAllowParallel(ALLOW_PARALLEL_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__RETURN_CLAUSE:
				setReturnClause(RETURN_CLAUSE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__ORIGIN:
				setOrigin(ORIGIN_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__INHERIT_LOCK_REQUEST:
				setInheritLockRequest(INHERIT_LOCK_REQUEST_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__DIALECT:
				setDialect(DIALECT_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__INLINE:
				setInline(INLINE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__SECURED:
				setSecured(SECURED_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__RETURNS_SELF_AS_RESULT:
				setReturnsSelfAsResult(RETURNS_SELF_AS_RESULT_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_METHOD__IMPLEMENTED:
				setImplemented(IMPLEMENTED_EDEFAULT);
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
			case DB2ModelPackage.DB2_METHOD__FENCED:
				return FENCED_EDEFAULT == null ? fenced != null : !FENCED_EDEFAULT.equals(fenced);
			case DB2ModelPackage.DB2_METHOD__THREADSAFE:
				return THREADSAFE_EDEFAULT == null ? threadsafe != null : !THREADSAFE_EDEFAULT.equals(threadsafe);
			case DB2ModelPackage.DB2_METHOD__DB_INFO:
				return dbInfo != DB_INFO_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__IMPLICIT_SCHEMA:
				return implicitSchema != IMPLICIT_SCHEMA_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__FEDERATED:
				return federated != FEDERATED_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__PARM_CCSID:
				return PARM_CCSID_EDEFAULT == null ? parmCcsid != null : !PARM_CCSID_EDEFAULT.equals(parmCcsid);
			case DB2ModelPackage.DB2_METHOD__SPECIAL_REGISTER:
				return SPECIAL_REGISTER_EDEFAULT == null ? specialRegister != null : !SPECIAL_REGISTER_EDEFAULT.equals(specialRegister);
			case DB2ModelPackage.DB2_METHOD__CHANGE_STATE:
				return changeState != CHANGE_STATE_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__DEBUG_ID:
				return DEBUG_ID_EDEFAULT == null ? debugId != null : !DEBUG_ID_EDEFAULT.equals(debugId);
			case DB2ModelPackage.DB2_METHOD__PROGRAM_TYPE:
				return PROGRAM_TYPE_EDEFAULT == null ? programType != null : !PROGRAM_TYPE_EDEFAULT.equals(programType);
			case DB2ModelPackage.DB2_METHOD__ORIG_SCHEMA_NAME:
				return ORIG_SCHEMA_NAME_EDEFAULT == null ? origSchemaName != null : !ORIG_SCHEMA_NAME_EDEFAULT.equals(origSchemaName);
			case DB2ModelPackage.DB2_METHOD__ORIG_PARM_SIG:
				return ORIG_PARM_SIG_EDEFAULT == null ? origParmSig != null : !ORIG_PARM_SIG_EDEFAULT.equals(origParmSig);
			case DB2ModelPackage.DB2_METHOD__EXTENDED_OPTIONS:
				return extendedOptions != null && !extendedOptions.isEmpty();
			case DB2ModelPackage.DB2_METHOD__ROUTINE_EXTENSIONS:
				return routineExtensions != null && !routineExtensions.isEmpty();
			case DB2ModelPackage.DB2_METHOD__FINAL_CALL:
				return finalCall != FINAL_CALL_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD:
				return scratchPad != SCRATCH_PAD_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD_LENGTH:
				return scratchPadLength != SCRATCH_PAD_LENGTH_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__FUNCTION_TYPE:
				return FUNCTION_TYPE_EDEFAULT == null ? functionType != null : !FUNCTION_TYPE_EDEFAULT.equals(functionType);
			case DB2ModelPackage.DB2_METHOD__PREDICATE:
				return PREDICATE_EDEFAULT == null ? predicate != null : !PREDICATE_EDEFAULT.equals(predicate);
			case DB2ModelPackage.DB2_METHOD__EXTERNAL_ACTION:
				return externalAction != EXTERNAL_ACTION_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__CARDINALITY:
				return cardinality != CARDINALITY_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__ALLOW_PARALLEL:
				return allowParallel != ALLOW_PARALLEL_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__RETURN_CLAUSE:
				return RETURN_CLAUSE_EDEFAULT == null ? returnClause != null : !RETURN_CLAUSE_EDEFAULT.equals(returnClause);
			case DB2ModelPackage.DB2_METHOD__ORIGIN:
				return origin != ORIGIN_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__INHERIT_LOCK_REQUEST:
				return inheritLockRequest != INHERIT_LOCK_REQUEST_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__DIALECT:
				return dialect != DIALECT_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__INLINE:
				return inline != INLINE_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case DB2ModelPackage.DB2_METHOD__SECURED:
				return secured != SECURED_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__RETURNS_SELF_AS_RESULT:
				return returnsSelfAsResult != RETURNS_SELF_AS_RESULT_EDEFAULT;
			case DB2ModelPackage.DB2_METHOD__IMPLEMENTED:
				return implemented != IMPLEMENTED_EDEFAULT;
		}
		return super.eIsSet(featureID);
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
				case DB2ModelPackage.DB2_METHOD__FENCED: return DB2ModelPackage.DB2_ROUTINE__FENCED;
				case DB2ModelPackage.DB2_METHOD__THREADSAFE: return DB2ModelPackage.DB2_ROUTINE__THREADSAFE;
				case DB2ModelPackage.DB2_METHOD__DB_INFO: return DB2ModelPackage.DB2_ROUTINE__DB_INFO;
				case DB2ModelPackage.DB2_METHOD__IMPLICIT_SCHEMA: return DB2ModelPackage.DB2_ROUTINE__IMPLICIT_SCHEMA;
				case DB2ModelPackage.DB2_METHOD__FEDERATED: return DB2ModelPackage.DB2_ROUTINE__FEDERATED;
				case DB2ModelPackage.DB2_METHOD__PARM_CCSID: return DB2ModelPackage.DB2_ROUTINE__PARM_CCSID;
				case DB2ModelPackage.DB2_METHOD__SPECIAL_REGISTER: return DB2ModelPackage.DB2_ROUTINE__SPECIAL_REGISTER;
				case DB2ModelPackage.DB2_METHOD__CHANGE_STATE: return DB2ModelPackage.DB2_ROUTINE__CHANGE_STATE;
				case DB2ModelPackage.DB2_METHOD__DEBUG_ID: return DB2ModelPackage.DB2_ROUTINE__DEBUG_ID;
				case DB2ModelPackage.DB2_METHOD__PROGRAM_TYPE: return DB2ModelPackage.DB2_ROUTINE__PROGRAM_TYPE;
				case DB2ModelPackage.DB2_METHOD__ORIG_SCHEMA_NAME: return DB2ModelPackage.DB2_ROUTINE__ORIG_SCHEMA_NAME;
				case DB2ModelPackage.DB2_METHOD__ORIG_PARM_SIG: return DB2ModelPackage.DB2_ROUTINE__ORIG_PARM_SIG;
				case DB2ModelPackage.DB2_METHOD__EXTENDED_OPTIONS: return DB2ModelPackage.DB2_ROUTINE__EXTENDED_OPTIONS;
				case DB2ModelPackage.DB2_METHOD__ROUTINE_EXTENSIONS: return DB2ModelPackage.DB2_ROUTINE__ROUTINE_EXTENSIONS;
				default: return -1;
			}
		}
		if (baseClass == DB2Function.class) {
			switch (derivedFeatureID) {
				case DB2ModelPackage.DB2_METHOD__FINAL_CALL: return DB2ModelPackage.DB2_FUNCTION__FINAL_CALL;
				case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD: return DB2ModelPackage.DB2_FUNCTION__SCRATCH_PAD;
				case DB2ModelPackage.DB2_METHOD__SCRATCH_PAD_LENGTH: return DB2ModelPackage.DB2_FUNCTION__SCRATCH_PAD_LENGTH;
				case DB2ModelPackage.DB2_METHOD__FUNCTION_TYPE: return DB2ModelPackage.DB2_FUNCTION__FUNCTION_TYPE;
				case DB2ModelPackage.DB2_METHOD__PREDICATE: return DB2ModelPackage.DB2_FUNCTION__PREDICATE;
				case DB2ModelPackage.DB2_METHOD__EXTERNAL_ACTION: return DB2ModelPackage.DB2_FUNCTION__EXTERNAL_ACTION;
				case DB2ModelPackage.DB2_METHOD__CARDINALITY: return DB2ModelPackage.DB2_FUNCTION__CARDINALITY;
				case DB2ModelPackage.DB2_METHOD__ALLOW_PARALLEL: return DB2ModelPackage.DB2_FUNCTION__ALLOW_PARALLEL;
				case DB2ModelPackage.DB2_METHOD__RETURN_CLAUSE: return DB2ModelPackage.DB2_FUNCTION__RETURN_CLAUSE;
				case DB2ModelPackage.DB2_METHOD__ORIGIN: return DB2ModelPackage.DB2_FUNCTION__ORIGIN;
				case DB2ModelPackage.DB2_METHOD__INHERIT_LOCK_REQUEST: return DB2ModelPackage.DB2_FUNCTION__INHERIT_LOCK_REQUEST;
				case DB2ModelPackage.DB2_METHOD__DIALECT: return DB2ModelPackage.DB2_FUNCTION__DIALECT;
				case DB2ModelPackage.DB2_METHOD__INLINE: return DB2ModelPackage.DB2_FUNCTION__INLINE;
				case DB2ModelPackage.DB2_METHOD__VERSION: return DB2ModelPackage.DB2_FUNCTION__VERSION;
				case DB2ModelPackage.DB2_METHOD__SECURED: return DB2ModelPackage.DB2_FUNCTION__SECURED;
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
				case DB2ModelPackage.DB2_ROUTINE__FENCED: return DB2ModelPackage.DB2_METHOD__FENCED;
				case DB2ModelPackage.DB2_ROUTINE__THREADSAFE: return DB2ModelPackage.DB2_METHOD__THREADSAFE;
				case DB2ModelPackage.DB2_ROUTINE__DB_INFO: return DB2ModelPackage.DB2_METHOD__DB_INFO;
				case DB2ModelPackage.DB2_ROUTINE__IMPLICIT_SCHEMA: return DB2ModelPackage.DB2_METHOD__IMPLICIT_SCHEMA;
				case DB2ModelPackage.DB2_ROUTINE__FEDERATED: return DB2ModelPackage.DB2_METHOD__FEDERATED;
				case DB2ModelPackage.DB2_ROUTINE__PARM_CCSID: return DB2ModelPackage.DB2_METHOD__PARM_CCSID;
				case DB2ModelPackage.DB2_ROUTINE__SPECIAL_REGISTER: return DB2ModelPackage.DB2_METHOD__SPECIAL_REGISTER;
				case DB2ModelPackage.DB2_ROUTINE__CHANGE_STATE: return DB2ModelPackage.DB2_METHOD__CHANGE_STATE;
				case DB2ModelPackage.DB2_ROUTINE__DEBUG_ID: return DB2ModelPackage.DB2_METHOD__DEBUG_ID;
				case DB2ModelPackage.DB2_ROUTINE__PROGRAM_TYPE: return DB2ModelPackage.DB2_METHOD__PROGRAM_TYPE;
				case DB2ModelPackage.DB2_ROUTINE__ORIG_SCHEMA_NAME: return DB2ModelPackage.DB2_METHOD__ORIG_SCHEMA_NAME;
				case DB2ModelPackage.DB2_ROUTINE__ORIG_PARM_SIG: return DB2ModelPackage.DB2_METHOD__ORIG_PARM_SIG;
				case DB2ModelPackage.DB2_ROUTINE__EXTENDED_OPTIONS: return DB2ModelPackage.DB2_METHOD__EXTENDED_OPTIONS;
				case DB2ModelPackage.DB2_ROUTINE__ROUTINE_EXTENSIONS: return DB2ModelPackage.DB2_METHOD__ROUTINE_EXTENSIONS;
				default: return -1;
			}
		}
		if (baseClass == DB2Function.class) {
			switch (baseFeatureID) {
				case DB2ModelPackage.DB2_FUNCTION__FINAL_CALL: return DB2ModelPackage.DB2_METHOD__FINAL_CALL;
				case DB2ModelPackage.DB2_FUNCTION__SCRATCH_PAD: return DB2ModelPackage.DB2_METHOD__SCRATCH_PAD;
				case DB2ModelPackage.DB2_FUNCTION__SCRATCH_PAD_LENGTH: return DB2ModelPackage.DB2_METHOD__SCRATCH_PAD_LENGTH;
				case DB2ModelPackage.DB2_FUNCTION__FUNCTION_TYPE: return DB2ModelPackage.DB2_METHOD__FUNCTION_TYPE;
				case DB2ModelPackage.DB2_FUNCTION__PREDICATE: return DB2ModelPackage.DB2_METHOD__PREDICATE;
				case DB2ModelPackage.DB2_FUNCTION__EXTERNAL_ACTION: return DB2ModelPackage.DB2_METHOD__EXTERNAL_ACTION;
				case DB2ModelPackage.DB2_FUNCTION__CARDINALITY: return DB2ModelPackage.DB2_METHOD__CARDINALITY;
				case DB2ModelPackage.DB2_FUNCTION__ALLOW_PARALLEL: return DB2ModelPackage.DB2_METHOD__ALLOW_PARALLEL;
				case DB2ModelPackage.DB2_FUNCTION__RETURN_CLAUSE: return DB2ModelPackage.DB2_METHOD__RETURN_CLAUSE;
				case DB2ModelPackage.DB2_FUNCTION__ORIGIN: return DB2ModelPackage.DB2_METHOD__ORIGIN;
				case DB2ModelPackage.DB2_FUNCTION__INHERIT_LOCK_REQUEST: return DB2ModelPackage.DB2_METHOD__INHERIT_LOCK_REQUEST;
				case DB2ModelPackage.DB2_FUNCTION__DIALECT: return DB2ModelPackage.DB2_METHOD__DIALECT;
				case DB2ModelPackage.DB2_FUNCTION__INLINE: return DB2ModelPackage.DB2_METHOD__INLINE;
				case DB2ModelPackage.DB2_FUNCTION__VERSION: return DB2ModelPackage.DB2_METHOD__VERSION;
				case DB2ModelPackage.DB2_FUNCTION__SECURED: return DB2ModelPackage.DB2_METHOD__SECURED;
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
		result.append(", finalCall: "); //$NON-NLS-1$
		result.append(finalCall);
		result.append(", scratchPad: "); //$NON-NLS-1$
		result.append(scratchPad);
		result.append(", scratchPadLength: "); //$NON-NLS-1$
		result.append(scratchPadLength);
		result.append(", functionType: "); //$NON-NLS-1$
		result.append(functionType);
		result.append(", predicate: "); //$NON-NLS-1$
		result.append(predicate);
		result.append(", externalAction: "); //$NON-NLS-1$
		result.append(externalAction);
		result.append(", cardinality: "); //$NON-NLS-1$
		result.append(cardinality);
		result.append(", allowParallel: "); //$NON-NLS-1$
		result.append(allowParallel);
		result.append(", returnClause: "); //$NON-NLS-1$
		result.append(returnClause);
		result.append(", origin: "); //$NON-NLS-1$
		result.append(origin);
		result.append(", inheritLockRequest: "); //$NON-NLS-1$
		result.append(inheritLockRequest);
		result.append(", dialect: "); //$NON-NLS-1$
		result.append(dialect);
		result.append(", inline: "); //$NON-NLS-1$
		result.append(inline);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", secured: "); //$NON-NLS-1$
		result.append(secured);
		result.append(", returnsSelfAsResult: "); //$NON-NLS-1$
		result.append(returnsSelfAsResult);
		result.append(", implemented: "); //$NON-NLS-1$
		result.append(implemented);
		result.append(')');
		return result.toString();
	}

} //DB2MethodImpl
