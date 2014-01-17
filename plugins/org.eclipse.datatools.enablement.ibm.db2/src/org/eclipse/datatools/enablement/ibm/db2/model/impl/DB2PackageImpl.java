/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;
import org.eclipse.datatools.enablement.ibm.db2.model.IsolationLevelType;
import org.eclipse.datatools.enablement.ibm.db2.model.ReoptType;
import java.util.Collection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#isOperative <em>Operative</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getValid <em>Valid</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getDefaultSchema <em>Default Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getSqlPath <em>Sql Path</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getReoptVar <em>Reopt Var</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getIsolation <em>Isolation</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getUniqueID <em>Unique ID</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getLastBindTS <em>Last Bind TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageImpl#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2PackageImpl extends SQLObjectImpl implements DB2Package {
	/**
	 * The default value of the '{@link #isOperative() <em>Operative</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isOperative()
	 * @generated
	 * @ordered
	 */
    protected static final boolean OPERATIVE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOperative() <em>Operative</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isOperative()
	 * @generated
	 * @ordered
	 */
    protected boolean operative = OPERATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getValid() <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValid()
	 * @generated
	 * @ordered
	 */
	protected static final String VALID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValid() <em>Valid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValid()
	 * @generated
	 * @ordered
	 */
	protected String valid = VALID_EDEFAULT;

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
	 * The default value of the '{@link #getDefaultSchema() <em>Default Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultSchema()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_SCHEMA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultSchema() <em>Default Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultSchema()
	 * @generated
	 * @ordered
	 */
	protected String defaultSchema = DEFAULT_SCHEMA_EDEFAULT;

	/**
	 * The default value of the '{@link #getSqlPath() <em>Sql Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlPath()
	 * @generated
	 * @ordered
	 */
	protected static final String SQL_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSqlPath() <em>Sql Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlPath()
	 * @generated
	 * @ordered
	 */
	protected String sqlPath = SQL_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getReoptVar() <em>Reopt Var</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReoptVar()
	 * @generated
	 * @ordered
	 */
	protected static final ReoptType REOPT_VAR_EDEFAULT = ReoptType.NONE_LITERAL;

	/**
	 * The cached value of the '{@link #getReoptVar() <em>Reopt Var</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReoptVar()
	 * @generated
	 * @ordered
	 */
	protected ReoptType reoptVar = REOPT_VAR_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsolation() <em>Isolation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsolation()
	 * @generated
	 * @ordered
	 */
	protected static final IsolationLevelType ISOLATION_EDEFAULT = IsolationLevelType.REPEATABLE_READ_LITERAL;

	/**
	 * The cached value of the '{@link #getIsolation() <em>Isolation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsolation()
	 * @generated
	 * @ordered
	 */
	protected IsolationLevelType isolation = ISOLATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getUniqueID() <em>Unique ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueID()
	 * @generated
	 * @ordered
	 */
	protected static final String UNIQUE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUniqueID() <em>Unique ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueID()
	 * @generated
	 * @ordered
	 */
	protected String uniqueID = UNIQUE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastBindTS() <em>Last Bind TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastBindTS()
	 * @generated
	 * @ordered
	 */
	protected static final String LAST_BIND_TS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastBindTS() <em>Last Bind TS</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastBindTS()
	 * @generated
	 * @ordered
	 */
	protected String lastBindTS = LAST_BIND_TS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
	protected DB2Schema schema;

	/**
	 * The cached value of the '{@link #getStatements() <em>Statements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatements()
	 * @generated
	 * @ordered
	 */
	protected EList statements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isOperative() {
		return operative;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setOperative(boolean newOperative) {
		boolean oldOperative = operative;
		operative = newOperative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__OPERATIVE, oldOperative, operative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValid() {
		return valid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValid(String newValid) {
		String oldValid = valid;
		valid = newValid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__VALID, oldValid, valid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultSchema() {
		return defaultSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultSchema(String newDefaultSchema) {
		String oldDefaultSchema = defaultSchema;
		defaultSchema = newDefaultSchema;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__DEFAULT_SCHEMA, oldDefaultSchema, defaultSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSqlPath() {
		return sqlPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlPath(String newSqlPath) {
		String oldSqlPath = sqlPath;
		sqlPath = newSqlPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__SQL_PATH, oldSqlPath, sqlPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReoptType getReoptVar() {
		return reoptVar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReoptVar(ReoptType newReoptVar) {
		ReoptType oldReoptVar = reoptVar;
		reoptVar = newReoptVar == null ? REOPT_VAR_EDEFAULT : newReoptVar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__REOPT_VAR, oldReoptVar, reoptVar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsolationLevelType getIsolation() {
		return isolation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsolation(IsolationLevelType newIsolation) {
		IsolationLevelType oldIsolation = isolation;
		isolation = newIsolation == null ? ISOLATION_EDEFAULT : newIsolation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__ISOLATION, oldIsolation, isolation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUniqueID() {
		return uniqueID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUniqueID(String newUniqueID) {
		String oldUniqueID = uniqueID;
		uniqueID = newUniqueID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__UNIQUE_ID, oldUniqueID, uniqueID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLastBindTS() {
		return lastBindTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastBindTS(String newLastBindTS) {
		String oldLastBindTS = lastBindTS;
		lastBindTS = newLastBindTS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__LAST_BIND_TS, oldLastBindTS, lastBindTS));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (DB2Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_PACKAGE__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(DB2Schema newSchema, NotificationChain msgs) {
		DB2Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(DB2Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__PACKAGES, DB2Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, DB2ModelPackage.DB2_SCHEMA__PACKAGES, DB2Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getStatements() {
		if (statements == null) {
			statements = new EObjectContainmentWithInverseEList(DB2PackageStatement.class, this, DB2ModelPackage.DB2_PACKAGE__STATEMENTS, DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE);
		}
		return statements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_PACKAGE__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__PACKAGES, DB2Schema.class, msgs);
				return basicSetSchema((DB2Schema)otherEnd, msgs);
			case DB2ModelPackage.DB2_PACKAGE__STATEMENTS:
				return ((InternalEList)getStatements()).basicAdd(otherEnd, msgs);
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
			case DB2ModelPackage.DB2_PACKAGE__SCHEMA:
				return basicSetSchema(null, msgs);
			case DB2ModelPackage.DB2_PACKAGE__STATEMENTS:
				return ((InternalEList)getStatements()).basicRemove(otherEnd, msgs);
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
			case DB2ModelPackage.DB2_PACKAGE__OPERATIVE:
				return isOperative() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_PACKAGE__VALID:
				return getValid();
			case DB2ModelPackage.DB2_PACKAGE__VERSION:
				return getVersion();
			case DB2ModelPackage.DB2_PACKAGE__DEFAULT_SCHEMA:
				return getDefaultSchema();
			case DB2ModelPackage.DB2_PACKAGE__SQL_PATH:
				return getSqlPath();
			case DB2ModelPackage.DB2_PACKAGE__REOPT_VAR:
				return getReoptVar();
			case DB2ModelPackage.DB2_PACKAGE__ISOLATION:
				return getIsolation();
			case DB2ModelPackage.DB2_PACKAGE__UNIQUE_ID:
				return getUniqueID();
			case DB2ModelPackage.DB2_PACKAGE__LAST_BIND_TS:
				return getLastBindTS();
			case DB2ModelPackage.DB2_PACKAGE__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
			case DB2ModelPackage.DB2_PACKAGE__STATEMENTS:
				return getStatements();
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
			case DB2ModelPackage.DB2_PACKAGE__OPERATIVE:
				setOperative(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_PACKAGE__VALID:
				setValid((String)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__VERSION:
				setVersion((String)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__DEFAULT_SCHEMA:
				setDefaultSchema((String)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__SQL_PATH:
				setSqlPath((String)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__REOPT_VAR:
				setReoptVar((ReoptType)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__ISOLATION:
				setIsolation((IsolationLevelType)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__UNIQUE_ID:
				setUniqueID((String)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__LAST_BIND_TS:
				setLastBindTS((String)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__SCHEMA:
				setSchema((DB2Schema)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE__STATEMENTS:
				getStatements().clear();
				getStatements().addAll((Collection)newValue);
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
			case DB2ModelPackage.DB2_PACKAGE__OPERATIVE:
				setOperative(OPERATIVE_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__VALID:
				setValid(VALID_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__DEFAULT_SCHEMA:
				setDefaultSchema(DEFAULT_SCHEMA_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__SQL_PATH:
				setSqlPath(SQL_PATH_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__REOPT_VAR:
				setReoptVar(REOPT_VAR_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__ISOLATION:
				setIsolation(ISOLATION_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__UNIQUE_ID:
				setUniqueID(UNIQUE_ID_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__LAST_BIND_TS:
				setLastBindTS(LAST_BIND_TS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE__SCHEMA:
				setSchema((DB2Schema)null);
				return;
			case DB2ModelPackage.DB2_PACKAGE__STATEMENTS:
				getStatements().clear();
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
			case DB2ModelPackage.DB2_PACKAGE__OPERATIVE:
				return operative != OPERATIVE_EDEFAULT;
			case DB2ModelPackage.DB2_PACKAGE__VALID:
				return VALID_EDEFAULT == null ? valid != null : !VALID_EDEFAULT.equals(valid);
			case DB2ModelPackage.DB2_PACKAGE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case DB2ModelPackage.DB2_PACKAGE__DEFAULT_SCHEMA:
				return DEFAULT_SCHEMA_EDEFAULT == null ? defaultSchema != null : !DEFAULT_SCHEMA_EDEFAULT.equals(defaultSchema);
			case DB2ModelPackage.DB2_PACKAGE__SQL_PATH:
				return SQL_PATH_EDEFAULT == null ? sqlPath != null : !SQL_PATH_EDEFAULT.equals(sqlPath);
			case DB2ModelPackage.DB2_PACKAGE__REOPT_VAR:
				return reoptVar != REOPT_VAR_EDEFAULT;
			case DB2ModelPackage.DB2_PACKAGE__ISOLATION:
				return isolation != ISOLATION_EDEFAULT;
			case DB2ModelPackage.DB2_PACKAGE__UNIQUE_ID:
				return UNIQUE_ID_EDEFAULT == null ? uniqueID != null : !UNIQUE_ID_EDEFAULT.equals(uniqueID);
			case DB2ModelPackage.DB2_PACKAGE__LAST_BIND_TS:
				return LAST_BIND_TS_EDEFAULT == null ? lastBindTS != null : !LAST_BIND_TS_EDEFAULT.equals(lastBindTS);
			case DB2ModelPackage.DB2_PACKAGE__SCHEMA:
				return schema != null;
			case DB2ModelPackage.DB2_PACKAGE__STATEMENTS:
				return statements != null && !statements.isEmpty();
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
		result.append(" (operative: "); //$NON-NLS-1$
		result.append(operative);
		result.append(", valid: "); //$NON-NLS-1$
		result.append(valid);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", defaultSchema: "); //$NON-NLS-1$
		result.append(defaultSchema);
		result.append(", sqlPath: "); //$NON-NLS-1$
		result.append(sqlPath);
		result.append(", reoptVar: "); //$NON-NLS-1$
		result.append(reoptVar);
		result.append(", isolation: "); //$NON-NLS-1$
		result.append(isolation);
		result.append(", uniqueID: "); //$NON-NLS-1$
		result.append(uniqueID);
		result.append(", lastBindTS: "); //$NON-NLS-1$
		result.append(lastBindTS);
		result.append(')');
		return result.toString();
	}

} //DB2PackageImpl
