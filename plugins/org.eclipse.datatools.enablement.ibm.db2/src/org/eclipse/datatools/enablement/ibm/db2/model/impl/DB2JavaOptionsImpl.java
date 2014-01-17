/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2JavaOptionsImpl.java,v 1.9 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Procedure;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Java Options</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl#isSqlj <em>Sqlj</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl#getProcedure <em>Procedure</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JavaOptionsImpl#getJar <em>Jar</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2JavaOptionsImpl extends SQLObjectImpl implements DB2JavaOptions {
	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
   protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
   protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
   protected static final String METHOD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
   protected String methodName = METHOD_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isSqlj() <em>Sqlj</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isSqlj()
	 * @generated
	 * @ordered
	 */
   protected static final boolean SQLJ_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSqlj() <em>Sqlj</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #isSqlj()
	 * @generated
	 * @ordered
	 */
   protected boolean sqlj = SQLJ_EDEFAULT;

	/**
	 * The cached value of the '{@link #getJar() <em>Jar</em>}' reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getJar()
	 * @generated
	 * @ordered
	 */
   protected DB2Jar jar;

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected DB2JavaOptionsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_JAVA_OPTIONS;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAVA_OPTIONS__CLASS_NAME, oldClassName, className));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getMethodName() {
		return methodName;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setMethodName(String newMethodName) {
		String oldMethodName = methodName;
		methodName = newMethodName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAVA_OPTIONS__METHOD_NAME, oldMethodName, methodName));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public boolean isSqlj() {
		return sqlj;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setSqlj(boolean newSqlj) {
		boolean oldSqlj = sqlj;
		sqlj = newSqlj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAVA_OPTIONS__SQLJ, oldSqlj, sqlj));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2Procedure getProcedure() {
		if (eContainerFeatureID() != DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE) return null;
		return (DB2Procedure)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcedure(DB2Procedure newProcedure, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newProcedure, DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setProcedure(DB2Procedure newProcedure) {
		if (newProcedure != eInternalContainer() || (eContainerFeatureID() != DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE && newProcedure != null)) {
			if (EcoreUtil.isAncestor(this, newProcedure))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newProcedure != null)
				msgs = ((InternalEObject)newProcedure).eInverseAdd(this, DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS, DB2Procedure.class, msgs);
			msgs = basicSetProcedure(newProcedure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE, newProcedure, newProcedure));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2Jar getJar() {
		if (jar != null && jar.eIsProxy()) {
			InternalEObject oldJar = (InternalEObject)jar;
			jar = (DB2Jar)eResolveProxy(oldJar);
			if (jar != oldJar) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_JAVA_OPTIONS__JAR, oldJar, jar));
			}
		}
		return jar;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2Jar basicGetJar() {
		return jar;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public NotificationChain basicSetJar(DB2Jar newJar, NotificationChain msgs) {
		DB2Jar oldJar = jar;
		jar = newJar;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAVA_OPTIONS__JAR, oldJar, newJar);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setJar(DB2Jar newJar) {
		if (newJar != jar) {
			NotificationChain msgs = null;
			if (jar != null)
				msgs = ((InternalEObject)jar).eInverseRemove(this, DB2ModelPackage.DB2_JAR__PROCEDURES, DB2Jar.class, msgs);
			if (newJar != null)
				msgs = ((InternalEObject)newJar).eInverseAdd(this, DB2ModelPackage.DB2_JAR__PROCEDURES, DB2Jar.class, msgs);
			msgs = basicSetJar(newJar, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAVA_OPTIONS__JAR, newJar, newJar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetProcedure((DB2Procedure)otherEnd, msgs);
			case DB2ModelPackage.DB2_JAVA_OPTIONS__JAR:
				if (jar != null)
					msgs = ((InternalEObject)jar).eInverseRemove(this, DB2ModelPackage.DB2_JAR__PROCEDURES, DB2Jar.class, msgs);
				return basicSetJar((DB2Jar)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE:
				return basicSetProcedure(null, msgs);
			case DB2ModelPackage.DB2_JAVA_OPTIONS__JAR:
				return basicSetJar(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE:
				return eInternalContainer().eInverseRemove(this, DB2ModelPackage.DB2_PROCEDURE__JAVA_OPTIONS, DB2Procedure.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_JAVA_OPTIONS__CLASS_NAME:
				return getClassName();
			case DB2ModelPackage.DB2_JAVA_OPTIONS__METHOD_NAME:
				return getMethodName();
			case DB2ModelPackage.DB2_JAVA_OPTIONS__SQLJ:
				return isSqlj() ? Boolean.TRUE : Boolean.FALSE;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE:
				return getProcedure();
			case DB2ModelPackage.DB2_JAVA_OPTIONS__JAR:
				if (resolve) return getJar();
				return basicGetJar();
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
			case DB2ModelPackage.DB2_JAVA_OPTIONS__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__METHOD_NAME:
				setMethodName((String)newValue);
				return;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__SQLJ:
				setSqlj(((Boolean)newValue).booleanValue());
				return;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE:
				setProcedure((DB2Procedure)newValue);
				return;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__JAR:
				setJar((DB2Jar)newValue);
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
			case DB2ModelPackage.DB2_JAVA_OPTIONS__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__METHOD_NAME:
				setMethodName(METHOD_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__SQLJ:
				setSqlj(SQLJ_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE:
				setProcedure((DB2Procedure)null);
				return;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__JAR:
				setJar((DB2Jar)null);
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
			case DB2ModelPackage.DB2_JAVA_OPTIONS__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
			case DB2ModelPackage.DB2_JAVA_OPTIONS__METHOD_NAME:
				return METHOD_NAME_EDEFAULT == null ? methodName != null : !METHOD_NAME_EDEFAULT.equals(methodName);
			case DB2ModelPackage.DB2_JAVA_OPTIONS__SQLJ:
				return sqlj != SQLJ_EDEFAULT;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__PROCEDURE:
				return getProcedure() != null;
			case DB2ModelPackage.DB2_JAVA_OPTIONS__JAR:
				return jar != null;
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
		result.append(" (className: "); //$NON-NLS-1$
		result.append(className);
		result.append(", methodName: "); //$NON-NLS-1$
		result.append(methodName);
		result.append(", sqlj: "); //$NON-NLS-1$
		result.append(sqlj);
		result.append(')');
		return result.toString();
	}

} //DB2JavaOptionsImpl
