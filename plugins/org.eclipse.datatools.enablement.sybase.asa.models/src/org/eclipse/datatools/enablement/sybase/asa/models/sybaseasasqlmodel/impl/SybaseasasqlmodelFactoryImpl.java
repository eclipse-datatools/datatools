/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasasqlmodelFactoryImpl.java,v 1.8 2007/06/05 14:41:03 hcao Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.*;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.emf.ecore.EClass;
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
public class SybaseasasqlmodelFactoryImpl extends EFactoryImpl implements SybaseasasqlmodelFactory 
{
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SybaseasasqlmodelFactory init()
    {
		try {
			SybaseasasqlmodelFactory theSybaseasasqlmodelFactory = (SybaseasasqlmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/connectivity/sqm/sybase/asa/sybaseasasqlmodel.ecore"); 
			if (theSybaseasasqlmodelFactory != null) {
				return theSybaseasasqlmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SybaseasasqlmodelFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseasasqlmodelFactoryImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass)
    {
		switch (eClass.getClassifierID()) {
			case SybaseasasqlmodelPackage.SYBASE_ASA_DATABASE: return createSybaseASADatabase();
			case SybaseasasqlmodelPackage.SYBASE_ASA_TABLE: return createSybaseASATable();
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY: return createSybaseASAForeignKey();
			case SybaseasasqlmodelPackage.SYBASE_ASA_INDEX: return createSybaseASAIndex();
			case SybaseasasqlmodelPackage.SYBASE_ASA_TEMP_TABLE: return createSybaseASATempTable();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASADatabase createSybaseASADatabase()
    {
		SybaseASADatabaseImpl sybaseASADatabase = new SybaseASADatabaseImpl();
		return sybaseASADatabase;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASATable createSybaseASATable()
    {
		SybaseASATableImpl sybaseASATable = new SybaseASATableImpl();
		return sybaseASATable;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASAForeignKey createSybaseASAForeignKey()
    {
		SybaseASAForeignKeyImpl sybaseASAForeignKey = new SybaseASAForeignKeyImpl();
		return sybaseASAForeignKey;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASAIndex createSybaseASAIndex()
    {
		SybaseASAIndexImpl sybaseASAIndex = new SybaseASAIndexImpl();
		return sybaseASAIndex;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASATempTable createSybaseASATempTable()
    {
		SybaseASATempTableImpl sybaseASATempTable = new SybaseASATempTableImpl();
		return sybaseASATempTable;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseasasqlmodelPackage getSybaseasasqlmodelPackage()
    {
		return (SybaseasasqlmodelPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static SybaseasasqlmodelPackage getPackage()
    {
		return SybaseasasqlmodelPackage.eINSTANCE;
	}

} //SybaseasasqlmodelFactoryImpl
