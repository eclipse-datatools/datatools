/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasasqlmodelSwitch.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.util;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.*;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage
 * @generated
 */
public class SybaseasasqlmodelSwitch 
{
    /**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SybaseasasqlmodelPackage modelPackage;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseasasqlmodelSwitch()
    {
		if (modelPackage == null) {
			modelPackage = SybaseasasqlmodelPackage.eINSTANCE;
		}
	}

    /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject)
    {
		return doSwitch(theEObject.eClass(), theEObject);
	}

    /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject)
    {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

    /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject)
    {
		switch (classifierID) {
			case SybaseasasqlmodelPackage.SYBASE_ASA_DATABASE: {
				SybaseASADatabase sybaseASADatabase = (SybaseASADatabase)theEObject;
				Object result = caseSybaseASADatabase(sybaseASADatabase);
				if (result == null) result = caseSybaseASABaseDatabase(sybaseASADatabase);
				if (result == null) result = caseDatabase(sybaseASADatabase);
				if (result == null) result = caseSQLObject(sybaseASADatabase);
				if (result == null) result = caseENamedElement(sybaseASADatabase);
				if (result == null) result = caseEModelElement(sybaseASADatabase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasasqlmodelPackage.SYBASE_ASA_TABLE: {
				SybaseASATable sybaseASATable = (SybaseASATable)theEObject;
				Object result = caseSybaseASATable(sybaseASATable);
				if (result == null) result = caseSybaseASABaseTable(sybaseASATable);
				if (result == null) result = casePersistentTable(sybaseASATable);
				if (result == null) result = caseSybaseBaseTable(sybaseASATable);
				if (result == null) result = caseBaseTable(sybaseASATable);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASATable);
				if (result == null) result = caseTable(sybaseASATable);
				if (result == null) result = caseSQLObject(sybaseASATable);
				if (result == null) result = caseENamedElement(sybaseASATable);
				if (result == null) result = caseEModelElement(sybaseASATable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasasqlmodelPackage.SYBASE_ASA_FOREIGN_KEY: {
				SybaseASAForeignKey sybaseASAForeignKey = (SybaseASAForeignKey)theEObject;
				Object result = caseSybaseASAForeignKey(sybaseASAForeignKey);
				if (result == null) result = caseSybaseASABaseForeignKey(sybaseASAForeignKey);
				if (result == null) result = caseForeignKey(sybaseASAForeignKey);
				if (result == null) result = caseReferenceConstraint(sybaseASAForeignKey);
				if (result == null) result = caseTableConstraint(sybaseASAForeignKey);
				if (result == null) result = caseConstraint(sybaseASAForeignKey);
				if (result == null) result = caseSQLObject(sybaseASAForeignKey);
				if (result == null) result = caseENamedElement(sybaseASAForeignKey);
				if (result == null) result = caseEModelElement(sybaseASAForeignKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasasqlmodelPackage.SYBASE_ASA_INDEX: {
				SybaseASAIndex sybaseASAIndex = (SybaseASAIndex)theEObject;
				Object result = caseSybaseASAIndex(sybaseASAIndex);
				if (result == null) result = caseSybaseASABaseIndex(sybaseASAIndex);
				if (result == null) result = caseIndex(sybaseASAIndex);
				if (result == null) result = caseSQLObject(sybaseASAIndex);
				if (result == null) result = caseENamedElement(sybaseASAIndex);
				if (result == null) result = caseEModelElement(sybaseASAIndex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasasqlmodelPackage.SYBASE_ASA_TEMP_TABLE: {
				SybaseASATempTable sybaseASATempTable = (SybaseASATempTable)theEObject;
				Object result = caseSybaseASATempTable(sybaseASATempTable);
				if (result == null) result = caseSybaseASABaseTempTable(sybaseASATempTable);
				if (result == null) result = caseTemporaryTable(sybaseASATempTable);
				if (result == null) result = caseSybaseBaseTable(sybaseASATempTable);
				if (result == null) result = caseBaseTable(sybaseASATempTable);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASATempTable);
				if (result == null) result = caseTable(sybaseASATempTable);
				if (result == null) result = caseSQLObject(sybaseASATempTable);
				if (result == null) result = caseENamedElement(sybaseASATempTable);
				if (result == null) result = caseEModelElement(sybaseASATempTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Database</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Database</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASADatabase(SybaseASADatabase object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASATable(SybaseASATable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Foreign Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Foreign Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASAForeignKey(SybaseASAForeignKey object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASAIndex(SybaseASAIndex object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Temp Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Temp Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASATempTable(SybaseASATempTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEModelElement(EModelElement object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseENamedElement(ENamedElement object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>SQL Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SQL Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSQLObject(SQLObject object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Database</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Database</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDatabase(Database object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Database</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Database</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseDatabase(SybaseASABaseDatabase object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTable(Table object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Base Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Base Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseBaseTable(BaseTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Persistent Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Persistent Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePersistentTable(PersistentTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Authorized Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Authorized Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseAuthorizedObject(SybaseAuthorizedObject object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Base Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Base Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseBaseTable(SybaseBaseTable object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseTable(SybaseASABaseTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConstraint(Constraint object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Table Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTableConstraint(TableConstraint object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Reference Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseReferenceConstraint(ReferenceConstraint object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Foreign Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Foreign Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseForeignKey(ForeignKey object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Foreign Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Foreign Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseForeignKey(SybaseASABaseForeignKey object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseIndex(Index object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseIndex(SybaseASABaseIndex object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Temporary Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Temporary Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTemporaryTable(TemporaryTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Temp Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Temp Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseTempTable(SybaseASABaseTempTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object)
    {
		return null;
	}

} //SybaseasasqlmodelSwitch
