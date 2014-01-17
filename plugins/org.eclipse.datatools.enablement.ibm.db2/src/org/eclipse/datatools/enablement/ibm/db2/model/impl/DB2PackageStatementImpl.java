/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2PackageStatementImpl.java,v 1.1 2008/06/10 20:19:39 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2PackageStatement;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Package Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageStatementImpl#getStatementNumber <em>Statement Number</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageStatementImpl#getSectionNumber <em>Section Number</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageStatementImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2PackageStatementImpl#getSqlStatement <em>Sql Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2PackageStatementImpl extends SQLObjectImpl implements DB2PackageStatement {
	/**
	 * The default value of the '{@link #getStatementNumber() <em>Statement Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatementNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int STATEMENT_NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStatementNumber() <em>Statement Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatementNumber()
	 * @generated
	 * @ordered
	 */
	protected int statementNumber = STATEMENT_NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getSectionNumber() <em>Section Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int SECTION_NUMBER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSectionNumber() <em>Section Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionNumber()
	 * @generated
	 * @ordered
	 */
	protected int sectionNumber = SECTION_NUMBER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSqlStatement() <em>Sql Statement</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSqlStatement()
	 * @generated
	 * @ordered
	 */
	protected SQLStatement sqlStatement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2PackageStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_PACKAGE_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStatementNumber() {
		return statementNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatementNumber(int newStatementNumber) {
		int oldStatementNumber = statementNumber;
		statementNumber = newStatementNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE_STATEMENT__STATEMENT_NUMBER, oldStatementNumber, statementNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSectionNumber() {
		return sectionNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSectionNumber(int newSectionNumber) {
		int oldSectionNumber = sectionNumber;
		sectionNumber = newSectionNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE_STATEMENT__SECTION_NUMBER, oldSectionNumber, sectionNumber));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DB2Package getPackage() {
		if (eContainerFeatureID() != DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE) return null;
		return (DB2Package)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackage(DB2Package newPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPackage, DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackage(DB2Package newPackage) {
		if (newPackage != eInternalContainer() || (eContainerFeatureID() != DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE && newPackage != null)) {
			if (EcoreUtil.isAncestor(this, newPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPackage != null)
				msgs = ((InternalEObject)newPackage).eInverseAdd(this, DB2ModelPackage.DB2_PACKAGE__STATEMENTS, DB2Package.class, msgs);
			msgs = basicSetPackage(newPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE, newPackage, newPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLStatement getSqlStatement() {
		return sqlStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSqlStatement(SQLStatement newSqlStatement, NotificationChain msgs) {
		SQLStatement oldSqlStatement = sqlStatement;
		sqlStatement = newSqlStatement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT, oldSqlStatement, newSqlStatement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlStatement(SQLStatement newSqlStatement) {
		if (newSqlStatement != sqlStatement) {
			NotificationChain msgs = null;
			if (sqlStatement != null)
				msgs = ((InternalEObject)sqlStatement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT, null, msgs);
			if (newSqlStatement != null)
				msgs = ((InternalEObject)newSqlStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT, null, msgs);
			msgs = basicSetSqlStatement(newSqlStatement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT, newSqlStatement, newSqlStatement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPackage((DB2Package)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE:
				return basicSetPackage(null, msgs);
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT:
				return basicSetSqlStatement(null, msgs);
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
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE:
				return eInternalContainer().eInverseRemove(this, DB2ModelPackage.DB2_PACKAGE__STATEMENTS, DB2Package.class, msgs);
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
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__STATEMENT_NUMBER:
				return new Integer(getStatementNumber());
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SECTION_NUMBER:
				return new Integer(getSectionNumber());
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE:
				return getPackage();
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT:
				return getSqlStatement();
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
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__STATEMENT_NUMBER:
				setStatementNumber(((Integer)newValue).intValue());
				return;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SECTION_NUMBER:
				setSectionNumber(((Integer)newValue).intValue());
				return;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE:
				setPackage((DB2Package)newValue);
				return;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT:
				setSqlStatement((SQLStatement)newValue);
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
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__STATEMENT_NUMBER:
				setStatementNumber(STATEMENT_NUMBER_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SECTION_NUMBER:
				setSectionNumber(SECTION_NUMBER_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE:
				setPackage((DB2Package)null);
				return;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT:
				setSqlStatement((SQLStatement)null);
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
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__STATEMENT_NUMBER:
				return statementNumber != STATEMENT_NUMBER_EDEFAULT;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SECTION_NUMBER:
				return sectionNumber != SECTION_NUMBER_EDEFAULT;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__PACKAGE:
				return getPackage() != null;
			case DB2ModelPackage.DB2_PACKAGE_STATEMENT__SQL_STATEMENT:
				return sqlStatement != null;
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
		result.append(" (statementNumber: "); //$NON-NLS-1$
		result.append(statementNumber);
		result.append(", sectionNumber: "); //$NON-NLS-1$
		result.append(sectionNumber);
		result.append(')');
		return result.toString();
	}

} //DB2PackageStatementImpl
