/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SybasesqlmodelFactoryImpl extends EFactoryImpl implements SybasesqlmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SybasesqlmodelFactory init() {
		try {
			SybasesqlmodelFactory theSybasesqlmodelFactory = (SybasesqlmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/enablement/sybase/sybasesqlmodel.ecore"); 
			if (theSybasesqlmodelFactory != null) {
				return theSybasesqlmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SybasesqlmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybasesqlmodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SybasesqlmodelPackage.SYBASE_PARAMETER: return createSybaseParameter();
			case SybasesqlmodelPackage.SYBASE_BASE_TABLE: return createSybaseBaseTable();
			case SybasesqlmodelPackage.SYBASE_VIEW_TABLE: return createSybaseViewTable();
			case SybasesqlmodelPackage.SYBASE_AUTHORIZATION_IDENTIFIER: return createSybaseAuthorizationIdentifier();
			case SybasesqlmodelPackage.SYBASE_INDEX_MEMBER: return createSybaseIndexMember();
			case SybasesqlmodelPackage.SYBASE_PRIVILEGE: return createSybasePrivilege();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SybasesqlmodelPackage.JDBC_PARAMETER_TYPE:
				return createJDBCParameterTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SybasesqlmodelPackage.JDBC_PARAMETER_TYPE:
				return convertJDBCParameterTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseParameter createSybaseParameter() {
		SybaseParameterImpl sybaseParameter = new SybaseParameterImpl();
		return sybaseParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseBaseTable createSybaseBaseTable() {
		SybaseBaseTableImpl sybaseBaseTable = new SybaseBaseTableImpl();
		return sybaseBaseTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseViewTable createSybaseViewTable() {
		SybaseViewTableImpl sybaseViewTable = new SybaseViewTableImpl();
		return sybaseViewTable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseAuthorizationIdentifier createSybaseAuthorizationIdentifier() {
		SybaseAuthorizationIdentifierImpl sybaseAuthorizationIdentifier = new SybaseAuthorizationIdentifierImpl();
		return sybaseAuthorizationIdentifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseIndexMember createSybaseIndexMember() {
		SybaseIndexMemberImpl sybaseIndexMember = new SybaseIndexMemberImpl();
		return sybaseIndexMember;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybasePrivilege createSybasePrivilege() {
		SybasePrivilegeImpl sybasePrivilege = new SybasePrivilegeImpl();
		return sybasePrivilege;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JDBCParameterType createJDBCParameterTypeFromString(EDataType eDataType, String initialValue) {
		JDBCParameterType result = JDBCParameterType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJDBCParameterTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybasesqlmodelPackage getSybasesqlmodelPackage() {
		return (SybasesqlmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static SybasesqlmodelPackage getPackage() {
		return SybasesqlmodelPackage.eINSTANCE;
	}

} //SybasesqlmodelFactoryImpl
