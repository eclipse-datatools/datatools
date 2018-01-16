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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ExtendedOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Extended Options</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl#getClasspathCompileJars <em>Classpath Compile Jars</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl#getPreCompileOpts <em>Pre Compile Opts</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl#isForDebug <em>For Debug</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl#isBuilt <em>Built</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl#getCompileOpts <em>Compile Opts</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl#getLinkOpts <em>Link Opts</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl#getBindOpts <em>Bind Opts</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2ExtendedOptionsImpl#getColid <em>Colid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2ExtendedOptionsImpl extends SQLObjectImpl implements DB2ExtendedOptions {
	/**
	 * The default value of the '{@link #getClasspathCompileJars() <em>Classpath Compile Jars</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasspathCompileJars()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASSPATH_COMPILE_JARS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClasspathCompileJars() <em>Classpath Compile Jars</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasspathCompileJars()
	 * @generated
	 * @ordered
	 */
	protected String classpathCompileJars = CLASSPATH_COMPILE_JARS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreCompileOpts() <em>Pre Compile Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreCompileOpts()
	 * @generated
	 * @ordered
	 */
	protected static final String PRE_COMPILE_OPTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreCompileOpts() <em>Pre Compile Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreCompileOpts()
	 * @generated
	 * @ordered
	 */
	protected String preCompileOpts = PRE_COMPILE_OPTS_EDEFAULT;

	/**
	 * The default value of the '{@link #isForDebug() <em>For Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForDebug()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FOR_DEBUG_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isForDebug() <em>For Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isForDebug()
	 * @generated
	 * @ordered
	 */
	protected boolean forDebug = FOR_DEBUG_EDEFAULT;

	/**
	 * The default value of the '{@link #isBuilt() <em>Built</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBuilt()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BUILT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBuilt() <em>Built</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBuilt()
	 * @generated
	 * @ordered
	 */
	protected boolean built = BUILT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompileOpts() <em>Compile Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getCompileOpts()
	 * @generated
	 * @ordered
	 */
   protected static final String COMPILE_OPTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCompileOpts() <em>Compile Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getCompileOpts()
	 * @generated
	 * @ordered
	 */
   protected String compileOpts = COMPILE_OPTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getLinkOpts() <em>Link Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getLinkOpts()
	 * @generated
	 * @ordered
	 */
   protected static final String LINK_OPTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLinkOpts() <em>Link Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getLinkOpts()
	 * @generated
	 * @ordered
	 */
   protected String linkOpts = LINK_OPTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getBindOpts() <em>Bind Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getBindOpts()
	 * @generated
	 * @ordered
	 */
   protected static final String BIND_OPTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBindOpts() <em>Bind Opts</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getBindOpts()
	 * @generated
	 * @ordered
	 */
   protected String bindOpts = BIND_OPTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getColid() <em>Colid</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getColid()
	 * @generated
	 * @ordered
	 */
   protected static final String COLID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getColid() <em>Colid</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getColid()
	 * @generated
	 * @ordered
	 */
   protected String colid = COLID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2ExtendedOptionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_EXTENDED_OPTIONS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClasspathCompileJars() {
		return classpathCompileJars;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClasspathCompileJars(String newClasspathCompileJars) {
		String oldClasspathCompileJars = classpathCompileJars;
		classpathCompileJars = newClasspathCompileJars;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_EXTENDED_OPTIONS__CLASSPATH_COMPILE_JARS, oldClasspathCompileJars, classpathCompileJars));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPreCompileOpts() {
		return preCompileOpts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreCompileOpts(String newPreCompileOpts) {
		String oldPreCompileOpts = preCompileOpts;
		preCompileOpts = newPreCompileOpts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_EXTENDED_OPTIONS__PRE_COMPILE_OPTS, oldPreCompileOpts, preCompileOpts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isForDebug() {
		return forDebug;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForDebug(boolean newForDebug) {
		boolean oldForDebug = forDebug;
		forDebug = newForDebug;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_EXTENDED_OPTIONS__FOR_DEBUG, oldForDebug, forDebug));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBuilt() {
		return built;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBuilt(boolean newBuilt) {
		boolean oldBuilt = built;
		built = newBuilt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_EXTENDED_OPTIONS__BUILT, oldBuilt, built));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getCompileOpts() {
		return compileOpts;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setCompileOpts(String newCompileOpts) {
		String oldCompileOpts = compileOpts;
		compileOpts = newCompileOpts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_EXTENDED_OPTIONS__COMPILE_OPTS, oldCompileOpts, compileOpts));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getLinkOpts() {
		return linkOpts;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setLinkOpts(String newLinkOpts) {
		String oldLinkOpts = linkOpts;
		linkOpts = newLinkOpts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_EXTENDED_OPTIONS__LINK_OPTS, oldLinkOpts, linkOpts));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getBindOpts() {
		return bindOpts;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setBindOpts(String newBindOpts) {
		String oldBindOpts = bindOpts;
		bindOpts = newBindOpts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_EXTENDED_OPTIONS__BIND_OPTS, oldBindOpts, bindOpts));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getColid() {
		return colid;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setColid(String newColid) {
		String oldColid = colid;
		colid = newColid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_EXTENDED_OPTIONS__COLID, oldColid, colid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__CLASSPATH_COMPILE_JARS:
				return getClasspathCompileJars();
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__PRE_COMPILE_OPTS:
				return getPreCompileOpts();
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__FOR_DEBUG:
				return isForDebug() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__BUILT:
				return isBuilt() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__COMPILE_OPTS:
				return getCompileOpts();
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__LINK_OPTS:
				return getLinkOpts();
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__BIND_OPTS:
				return getBindOpts();
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__COLID:
				return getColid();
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
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__CLASSPATH_COMPILE_JARS:
				setClasspathCompileJars((String)newValue);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__PRE_COMPILE_OPTS:
				setPreCompileOpts((String)newValue);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__FOR_DEBUG:
				setForDebug(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__BUILT:
				setBuilt(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__COMPILE_OPTS:
				setCompileOpts((String)newValue);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__LINK_OPTS:
				setLinkOpts((String)newValue);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__BIND_OPTS:
				setBindOpts((String)newValue);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__COLID:
				setColid((String)newValue);
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
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__CLASSPATH_COMPILE_JARS:
				setClasspathCompileJars(CLASSPATH_COMPILE_JARS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__PRE_COMPILE_OPTS:
				setPreCompileOpts(PRE_COMPILE_OPTS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__FOR_DEBUG:
				setForDebug(FOR_DEBUG_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__BUILT:
				setBuilt(BUILT_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__COMPILE_OPTS:
				setCompileOpts(COMPILE_OPTS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__LINK_OPTS:
				setLinkOpts(LINK_OPTS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__BIND_OPTS:
				setBindOpts(BIND_OPTS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__COLID:
				setColid(COLID_EDEFAULT);
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
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__CLASSPATH_COMPILE_JARS:
				return CLASSPATH_COMPILE_JARS_EDEFAULT == null ? classpathCompileJars != null : !CLASSPATH_COMPILE_JARS_EDEFAULT.equals(classpathCompileJars);
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__PRE_COMPILE_OPTS:
				return PRE_COMPILE_OPTS_EDEFAULT == null ? preCompileOpts != null : !PRE_COMPILE_OPTS_EDEFAULT.equals(preCompileOpts);
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__FOR_DEBUG:
				return forDebug != FOR_DEBUG_EDEFAULT;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__BUILT:
				return built != BUILT_EDEFAULT;
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__COMPILE_OPTS:
				return COMPILE_OPTS_EDEFAULT == null ? compileOpts != null : !COMPILE_OPTS_EDEFAULT.equals(compileOpts);
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__LINK_OPTS:
				return LINK_OPTS_EDEFAULT == null ? linkOpts != null : !LINK_OPTS_EDEFAULT.equals(linkOpts);
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__BIND_OPTS:
				return BIND_OPTS_EDEFAULT == null ? bindOpts != null : !BIND_OPTS_EDEFAULT.equals(bindOpts);
			case DB2ModelPackage.DB2_EXTENDED_OPTIONS__COLID:
				return COLID_EDEFAULT == null ? colid != null : !COLID_EDEFAULT.equals(colid);
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
		result.append(" (classpathCompileJars: "); //$NON-NLS-1$
		result.append(classpathCompileJars);
		result.append(", preCompileOpts: "); //$NON-NLS-1$
		result.append(preCompileOpts);
		result.append(", forDebug: "); //$NON-NLS-1$
		result.append(forDebug);
		result.append(", built: "); //$NON-NLS-1$
		result.append(built);
		result.append(", compileOpts: "); //$NON-NLS-1$
		result.append(compileOpts);
		result.append(", linkOpts: "); //$NON-NLS-1$
		result.append(linkOpts);
		result.append(", bindOpts: "); //$NON-NLS-1$
		result.append(bindOpts);
		result.append(", colid: "); //$NON-NLS-1$
		result.append(colid);
		result.append(')');
		return result.toString();
	}

} //DB2ExtendedOptionsImpl
