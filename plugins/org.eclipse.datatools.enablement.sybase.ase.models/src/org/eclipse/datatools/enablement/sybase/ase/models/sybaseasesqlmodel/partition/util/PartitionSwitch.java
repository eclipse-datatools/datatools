/**
 * <copyright>
 * </copyright>
 *
 * $Id: PartitionSwitch.java,v 1.6 2007/07/06 08:40:22 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.util;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.*;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

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
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage
 * @generated
 */
public class PartitionSwitch {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static PartitionPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PartitionSwitch() {
        if (modelPackage == null)
        {
            modelPackage = PartitionPackage.eINSTANCE;
        }
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	public Object doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage)
        {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else
        {
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
	protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID)
        {
            case PartitionPackage.SYBASE_ASE_PARTITION:
            {
                SybaseASEPartition sybaseASEPartition = (SybaseASEPartition)theEObject;
                Object result = caseSybaseASEPartition(sybaseASEPartition);
                if (result == null) result = caseSQLObject(sybaseASEPartition);
                if (result == null) result = caseENamedElement(sybaseASEPartition);
                if (result == null) result = caseEModelElement(sybaseASEPartition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PartitionPackage.SYBASE_ASE_RANGE_PARTITION:
            {
                SybaseASERangePartition sybaseASERangePartition = (SybaseASERangePartition)theEObject;
                Object result = caseSybaseASERangePartition(sybaseASERangePartition);
                if (result == null) result = caseSybaseASEPartition(sybaseASERangePartition);
                if (result == null) result = caseSQLObject(sybaseASERangePartition);
                if (result == null) result = caseENamedElement(sybaseASERangePartition);
                if (result == null) result = caseEModelElement(sybaseASERangePartition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PartitionPackage.SYBASE_ASE_HASH_PARTITION:
            {
                SybaseASEHashPartition sybaseASEHashPartition = (SybaseASEHashPartition)theEObject;
                Object result = caseSybaseASEHashPartition(sybaseASEHashPartition);
                if (result == null) result = caseSybaseASEPartition(sybaseASEHashPartition);
                if (result == null) result = caseSQLObject(sybaseASEHashPartition);
                if (result == null) result = caseENamedElement(sybaseASEHashPartition);
                if (result == null) result = caseEModelElement(sybaseASEHashPartition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PartitionPackage.SYBASE_ASE_LIST_PARTITION:
            {
                SybaseASEListPartition sybaseASEListPartition = (SybaseASEListPartition)theEObject;
                Object result = caseSybaseASEListPartition(sybaseASEListPartition);
                if (result == null) result = caseSybaseASEPartition(sybaseASEListPartition);
                if (result == null) result = caseSQLObject(sybaseASEListPartition);
                if (result == null) result = caseENamedElement(sybaseASEListPartition);
                if (result == null) result = caseEModelElement(sybaseASEListPartition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PartitionPackage.SYBASE_ASE_ROUNDROBIN_PARTITION:
            {
                SybaseASERoundrobinPartition sybaseASERoundrobinPartition = (SybaseASERoundrobinPartition)theEObject;
                Object result = caseSybaseASERoundrobinPartition(sybaseASERoundrobinPartition);
                if (result == null) result = caseSybaseASEPartition(sybaseASERoundrobinPartition);
                if (result == null) result = caseSQLObject(sybaseASERoundrobinPartition);
                if (result == null) result = caseENamedElement(sybaseASERoundrobinPartition);
                if (result == null) result = caseEModelElement(sybaseASERoundrobinPartition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PartitionPackage.PARTITION_SEGMENT_PAIR:
            {
                PartitionSegmentPair partitionSegmentPair = (PartitionSegmentPair)theEObject;
                Object result = casePartitionSegmentPair(partitionSegmentPair);
                if (result == null) result = caseSQLObject(partitionSegmentPair);
                if (result == null) result = caseENamedElement(partitionSegmentPair);
                if (result == null) result = caseEModelElement(partitionSegmentPair);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PartitionPackage.PARTITION_NUM_IN_SEGMENTS:
            {
                PartitionNumInSegments partitionNumInSegments = (PartitionNumInSegments)theEObject;
                Object result = casePartitionNumInSegments(partitionNumInSegments);
                if (result == null) result = caseSQLObject(partitionNumInSegments);
                if (result == null) result = caseENamedElement(partitionNumInSegments);
                if (result == null) result = caseEModelElement(partitionNumInSegments);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PartitionPackage.LIST_RANGE_PARTITION_ITEM:
            {
                ListRangePartitionItem listRangePartitionItem = (ListRangePartitionItem)theEObject;
                Object result = caseListRangePartitionItem(listRangePartitionItem);
                if (result == null) result = caseSQLObject(listRangePartitionItem);
                if (result == null) result = caseENamedElement(listRangePartitionItem);
                if (result == null) result = caseEModelElement(listRangePartitionItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Partition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Partition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEPartition(SybaseASEPartition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Range Partition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Range Partition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASERangePartition(SybaseASERangePartition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Hash Partition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Hash Partition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEHashPartition(SybaseASEHashPartition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE List Partition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE List Partition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEListPartition(SybaseASEListPartition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Roundrobin Partition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Roundrobin Partition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASERoundrobinPartition(SybaseASERoundrobinPartition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Segment Pair</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Segment Pair</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object casePartitionSegmentPair(PartitionSegmentPair object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Num In Segments</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Num In Segments</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object casePartitionNumInSegments(PartitionNumInSegments object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>List Range Partition Item</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>List Range Partition Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseListRangePartitionItem(ListRangePartitionItem object) {
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
	public Object caseEModelElement(EModelElement object) {
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
	public Object caseENamedElement(ENamedElement object) {
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
	public Object caseSQLObject(SQLObject object) {
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
	public Object defaultCase(EObject object) {
        return null;
    }

} //PartitionSwitch
