/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalServer;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWRelationalWrapper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relational Server</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#getCpuRatio <em>Cpu Ratio</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#getIoRatio <em>Io Ratio</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#getCommRate <em>Comm Rate</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#isFoldId <em>Fold Id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#isFoldPW <em>Fold PW</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#isCollatingSequence <em>Collating Sequence</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#isPushdown <em>Pushdown</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#getNode <em>Node</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#getDbName <em>Db Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#isIudAppSvptEnforce <em>Iud App Svpt Enforce</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#getPassword <em>Password</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#getRelNicknames <em>Rel Nicknames</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWRelationalServerImpl#getRelWrapper <em>Rel Wrapper</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LUWRelationalServerImpl extends LUWServerImpl implements LUWRelationalServer {
	/**
	 * The default value of the '{@link #getCpuRatio() <em>Cpu Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpuRatio()
	 * @generated
	 * @ordered
	 */
	protected static final long CPU_RATIO_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCpuRatio() <em>Cpu Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCpuRatio()
	 * @generated
	 * @ordered
	 */
	protected long cpuRatio = CPU_RATIO_EDEFAULT;

	/**
	 * The default value of the '{@link #getIoRatio() <em>Io Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIoRatio()
	 * @generated
	 * @ordered
	 */
	protected static final long IO_RATIO_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getIoRatio() <em>Io Ratio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIoRatio()
	 * @generated
	 * @ordered
	 */
	protected long ioRatio = IO_RATIO_EDEFAULT;

	/**
	 * The default value of the '{@link #getCommRate() <em>Comm Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommRate()
	 * @generated
	 * @ordered
	 */
	protected static final long COMM_RATE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCommRate() <em>Comm Rate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommRate()
	 * @generated
	 * @ordered
	 */
	protected long commRate = COMM_RATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isFoldId() <em>Fold Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFoldId()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FOLD_ID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFoldId() <em>Fold Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFoldId()
	 * @generated
	 * @ordered
	 */
	protected boolean foldId = FOLD_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isFoldPW() <em>Fold PW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFoldPW()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FOLD_PW_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFoldPW() <em>Fold PW</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFoldPW()
	 * @generated
	 * @ordered
	 */
	protected boolean foldPW = FOLD_PW_EDEFAULT;

	/**
	 * The default value of the '{@link #isCollatingSequence() <em>Collating Sequence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollatingSequence()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLLATING_SEQUENCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCollatingSequence() <em>Collating Sequence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollatingSequence()
	 * @generated
	 * @ordered
	 */
	protected boolean collatingSequence = COLLATING_SEQUENCE_EDEFAULT;

	/**
	 * The default value of the '{@link #isPushdown() <em>Pushdown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPushdown()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PUSHDOWN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPushdown() <em>Pushdown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPushdown()
	 * @generated
	 * @ordered
	 */
	protected boolean pushdown = PUSHDOWN_EDEFAULT;

	/**
	 * The default value of the '{@link #getNode() <em>Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNode()
	 * @generated
	 * @ordered
	 */
	protected static final String NODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNode() <em>Node</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNode()
	 * @generated
	 * @ordered
	 */
	protected String node = NODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDbName() <em>Db Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbName()
	 * @generated
	 * @ordered
	 */
	protected static final String DB_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDbName() <em>Db Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbName()
	 * @generated
	 * @ordered
	 */
	protected String dbName = DB_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isIudAppSvptEnforce() <em>Iud App Svpt Enforce</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIudAppSvptEnforce()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IUD_APP_SVPT_ENFORCE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIudAppSvptEnforce() <em>Iud App Svpt Enforce</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIudAppSvptEnforce()
	 * @generated
	 * @ordered
	 */
	protected boolean iudAppSvptEnforce = IUD_APP_SVPT_ENFORCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPassword() <em>Password</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPassword()
	 * @generated
	 * @ordered
	 */
	protected String password = PASSWORD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LUWRelationalServerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_RELATIONAL_SERVER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getCpuRatio() {
		return cpuRatio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCpuRatio(long newCpuRatio) {
		long oldCpuRatio = cpuRatio;
		cpuRatio = newCpuRatio;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__CPU_RATIO, oldCpuRatio, cpuRatio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getIoRatio() {
		return ioRatio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIoRatio(long newIoRatio) {
		long oldIoRatio = ioRatio;
		ioRatio = newIoRatio;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__IO_RATIO, oldIoRatio, ioRatio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getCommRate() {
		return commRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCommRate(long newCommRate) {
		long oldCommRate = commRate;
		commRate = newCommRate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__COMM_RATE, oldCommRate, commRate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFoldId() {
		return foldId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFoldId(boolean newFoldId) {
		boolean oldFoldId = foldId;
		foldId = newFoldId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__FOLD_ID, oldFoldId, foldId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFoldPW() {
		return foldPW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFoldPW(boolean newFoldPW) {
		boolean oldFoldPW = foldPW;
		foldPW = newFoldPW;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__FOLD_PW, oldFoldPW, foldPW));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCollatingSequence() {
		return collatingSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollatingSequence(boolean newCollatingSequence) {
		boolean oldCollatingSequence = collatingSequence;
		collatingSequence = newCollatingSequence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__COLLATING_SEQUENCE, oldCollatingSequence, collatingSequence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPushdown() {
		return pushdown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPushdown(boolean newPushdown) {
		boolean oldPushdown = pushdown;
		pushdown = newPushdown;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__PUSHDOWN, oldPushdown, pushdown));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNode() {
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNode(String newNode) {
		String oldNode = node;
		node = newNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__NODE, oldNode, node));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDbName(String newDbName) {
		String oldDbName = dbName;
		dbName = newDbName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__DB_NAME, oldDbName, dbName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIudAppSvptEnforce() {
		return iudAppSvptEnforce;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIudAppSvptEnforce(boolean newIudAppSvptEnforce) {
		boolean oldIudAppSvptEnforce = iudAppSvptEnforce;
		iudAppSvptEnforce = newIudAppSvptEnforce;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__IUD_APP_SVPT_ENFORCE, oldIudAppSvptEnforce, iudAppSvptEnforce));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPassword(String newPassword) {
		String oldPassword = password;
		password = newPassword;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_RELATIONAL_SERVER__PASSWORD, oldPassword, password));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWRelationalWrapper getRelWrapper() {
		LUWRelationalWrapper relWrapper = basicGetRelWrapper();
		return relWrapper != null && relWrapper.eIsProxy() ? (LUWRelationalWrapper)eResolveProxy((InternalEObject)relWrapper) : relWrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LUWRelationalWrapper basicGetRelWrapper() {
		return (LUWRelationalWrapper) wrapper;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getFunctionMappings() {
		EList allFunctionMappings = getLUWDatabase().getFunctionMappings();
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTypeMappings() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReverseTypeMappings() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_RELATIONAL_SERVER__CPU_RATIO:
				return new Long(getCpuRatio());
			case LUWPackage.LUW_RELATIONAL_SERVER__IO_RATIO:
				return new Long(getIoRatio());
			case LUWPackage.LUW_RELATIONAL_SERVER__COMM_RATE:
				return new Long(getCommRate());
			case LUWPackage.LUW_RELATIONAL_SERVER__FOLD_ID:
				return isFoldId() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_RELATIONAL_SERVER__FOLD_PW:
				return isFoldPW() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_RELATIONAL_SERVER__COLLATING_SEQUENCE:
				return isCollatingSequence() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_RELATIONAL_SERVER__PUSHDOWN:
				return isPushdown() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_RELATIONAL_SERVER__NODE:
				return getNode();
			case LUWPackage.LUW_RELATIONAL_SERVER__DB_NAME:
				return getDbName();
			case LUWPackage.LUW_RELATIONAL_SERVER__IUD_APP_SVPT_ENFORCE:
				return isIudAppSvptEnforce() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_RELATIONAL_SERVER__PASSWORD:
				return getPassword();
			case LUWPackage.LUW_RELATIONAL_SERVER__REL_NICKNAMES:
				return getRelNicknames();
			case LUWPackage.LUW_RELATIONAL_SERVER__REL_WRAPPER:
				if (resolve) return getRelWrapper();
				return basicGetRelWrapper();
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
			case LUWPackage.LUW_RELATIONAL_SERVER__CPU_RATIO:
				setCpuRatio(((Long)newValue).longValue());
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__IO_RATIO:
				setIoRatio(((Long)newValue).longValue());
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__COMM_RATE:
				setCommRate(((Long)newValue).longValue());
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__FOLD_ID:
				setFoldId(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__FOLD_PW:
				setFoldPW(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__COLLATING_SEQUENCE:
				setCollatingSequence(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__PUSHDOWN:
				setPushdown(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__NODE:
				setNode((String)newValue);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__DB_NAME:
				setDbName((String)newValue);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__IUD_APP_SVPT_ENFORCE:
				setIudAppSvptEnforce(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__PASSWORD:
				setPassword((String)newValue);
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
			case LUWPackage.LUW_RELATIONAL_SERVER__CPU_RATIO:
				setCpuRatio(CPU_RATIO_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__IO_RATIO:
				setIoRatio(IO_RATIO_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__COMM_RATE:
				setCommRate(COMM_RATE_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__FOLD_ID:
				setFoldId(FOLD_ID_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__FOLD_PW:
				setFoldPW(FOLD_PW_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__COLLATING_SEQUENCE:
				setCollatingSequence(COLLATING_SEQUENCE_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__PUSHDOWN:
				setPushdown(PUSHDOWN_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__NODE:
				setNode(NODE_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__DB_NAME:
				setDbName(DB_NAME_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__IUD_APP_SVPT_ENFORCE:
				setIudAppSvptEnforce(IUD_APP_SVPT_ENFORCE_EDEFAULT);
				return;
			case LUWPackage.LUW_RELATIONAL_SERVER__PASSWORD:
				setPassword(PASSWORD_EDEFAULT);
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
			case LUWPackage.LUW_RELATIONAL_SERVER__CPU_RATIO:
				return cpuRatio != CPU_RATIO_EDEFAULT;
			case LUWPackage.LUW_RELATIONAL_SERVER__IO_RATIO:
				return ioRatio != IO_RATIO_EDEFAULT;
			case LUWPackage.LUW_RELATIONAL_SERVER__COMM_RATE:
				return commRate != COMM_RATE_EDEFAULT;
			case LUWPackage.LUW_RELATIONAL_SERVER__FOLD_ID:
				return foldId != FOLD_ID_EDEFAULT;
			case LUWPackage.LUW_RELATIONAL_SERVER__FOLD_PW:
				return foldPW != FOLD_PW_EDEFAULT;
			case LUWPackage.LUW_RELATIONAL_SERVER__COLLATING_SEQUENCE:
				return collatingSequence != COLLATING_SEQUENCE_EDEFAULT;
			case LUWPackage.LUW_RELATIONAL_SERVER__PUSHDOWN:
				return pushdown != PUSHDOWN_EDEFAULT;
			case LUWPackage.LUW_RELATIONAL_SERVER__NODE:
				return NODE_EDEFAULT == null ? node != null : !NODE_EDEFAULT.equals(node);
			case LUWPackage.LUW_RELATIONAL_SERVER__DB_NAME:
				return DB_NAME_EDEFAULT == null ? dbName != null : !DB_NAME_EDEFAULT.equals(dbName);
			case LUWPackage.LUW_RELATIONAL_SERVER__IUD_APP_SVPT_ENFORCE:
				return iudAppSvptEnforce != IUD_APP_SVPT_ENFORCE_EDEFAULT;
			case LUWPackage.LUW_RELATIONAL_SERVER__PASSWORD:
				return PASSWORD_EDEFAULT == null ? password != null : !PASSWORD_EDEFAULT.equals(password);
			case LUWPackage.LUW_RELATIONAL_SERVER__REL_NICKNAMES:
				return !getRelNicknames().isEmpty();
			case LUWPackage.LUW_RELATIONAL_SERVER__REL_WRAPPER:
				return basicGetRelWrapper() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList getRelNicknames() {
		// This method should always be overridden by a subclass to ensure
		// that a correctly-typed EList is created
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (cpuRatio: "); //$NON-NLS-1$
		result.append(cpuRatio);
		result.append(", ioRatio: "); //$NON-NLS-1$
		result.append(ioRatio);
		result.append(", commRate: "); //$NON-NLS-1$
		result.append(commRate);
		result.append(", foldId: "); //$NON-NLS-1$
		result.append(foldId);
		result.append(", foldPW: "); //$NON-NLS-1$
		result.append(foldPW);
		result.append(", collatingSequence: "); //$NON-NLS-1$
		result.append(collatingSequence);
		result.append(", pushdown: "); //$NON-NLS-1$
		result.append(pushdown);
		result.append(", node: "); //$NON-NLS-1$
		result.append(node);
		result.append(", dbName: "); //$NON-NLS-1$
		result.append(dbName);
		result.append(", iudAppSvptEnforce: "); //$NON-NLS-1$
		result.append(iudAppSvptEnforce);
		result.append(", password: "); //$NON-NLS-1$
		result.append(password);
		result.append(')');
		return result.toString();
	}

} //LUWRelationalServerImpl
