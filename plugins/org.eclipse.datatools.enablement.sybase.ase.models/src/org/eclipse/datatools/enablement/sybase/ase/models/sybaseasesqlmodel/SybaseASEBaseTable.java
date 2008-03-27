/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEBaseTable.java,v 1.9 2007/08/06 08:29:24 renj Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Base Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getLockSchema <em>Lock Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getFillFactor <em>Fill Factor</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getMaxRowPerPage <em>Max Row Per Page</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getExpRowSize <em>Exp Row Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getReservePageGap <em>Reserve Page Gap</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getIdentityGap <em>Identity Gap</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getSegment <em>Segment</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getConcurrencyOptThreshold <em>Concurrency Opt Threshold</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getPartitionCondition <em>Partition Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTableOnlyCacheInfo <em>Table Only Cache Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTextOnlyCacheInfo <em>Text Only Cache Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getLockPromotion <em>Lock Promotion</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getPartitions <em>Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTextImageSegment <em>Text Image Segment</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#isSystemTable <em>System Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable()
 * @model annotation="GenModel documentation='Store attributes shared by SybaseASETable and SybaseASETempTable'"
 * @generated
 */
public interface SybaseASEBaseTable extends SybaseBaseTable {
	/**
     * Returns the value of the '<em><b>Lock Schema</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lock Schema</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Lock Schema</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType
     * @see #setLockSchema(LockingSchemaType)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_LockSchema()
     * @model
     * @generated
     */
	LockingSchemaType getLockSchema();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getLockSchema <em>Lock Schema</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lock Schema</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType
     * @see #getLockSchema()
     * @generated
     */
	void setLockSchema(LockingSchemaType value);

	/**
     * Returns the value of the '<em><b>Fill Factor</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fill Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Fill Factor</em>' attribute.
     * @see #setFillFactor(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_FillFactor()
     * @model default="-1"
     * @generated
     */
	int getFillFactor();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getFillFactor <em>Fill Factor</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fill Factor</em>' attribute.
     * @see #getFillFactor()
     * @generated
     */
	void setFillFactor(int value);

	/**
     * Returns the value of the '<em><b>Max Row Per Page</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Max Row Per Page</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Max Row Per Page</em>' attribute.
     * @see #setMaxRowPerPage(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_MaxRowPerPage()
     * @model default="-1"
     * @generated
     */
	int getMaxRowPerPage();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getMaxRowPerPage <em>Max Row Per Page</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Row Per Page</em>' attribute.
     * @see #getMaxRowPerPage()
     * @generated
     */
	void setMaxRowPerPage(int value);

	/**
     * Returns the value of the '<em><b>Exp Row Size</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exp Row Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Exp Row Size</em>' attribute.
     * @see #setExpRowSize(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_ExpRowSize()
     * @model default="-1"
     * @generated
     */
	int getExpRowSize();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getExpRowSize <em>Exp Row Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Exp Row Size</em>' attribute.
     * @see #getExpRowSize()
     * @generated
     */
	void setExpRowSize(int value);

	/**
     * Returns the value of the '<em><b>Reserve Page Gap</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reserve Page Gap</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Reserve Page Gap</em>' attribute.
     * @see #setReservePageGap(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_ReservePageGap()
     * @model default="-1"
     * @generated
     */
	int getReservePageGap();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getReservePageGap <em>Reserve Page Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reserve Page Gap</em>' attribute.
     * @see #getReservePageGap()
     * @generated
     */
	void setReservePageGap(int value);

	/**
     * Returns the value of the '<em><b>Identity Gap</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identity Gap</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Identity Gap</em>' attribute.
     * @see #setIdentityGap(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_IdentityGap()
     * @model default="-1"
     * @generated
     */
	int getIdentityGap();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getIdentityGap <em>Identity Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Identity Gap</em>' attribute.
     * @see #getIdentityGap()
     * @generated
     */
	void setIdentityGap(int value);

	/**
     * Returns the value of the '<em><b>Segment</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Segment</em>' reference.
     * @see #setSegment(SybaseASESegment)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_Segment()
     * @model required="true"
     * @generated
     */
	SybaseASESegment getSegment();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getSegment <em>Segment</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Segment</em>' reference.
     * @see #getSegment()
     * @generated
     */
	void setSegment(SybaseASESegment value);

	/**
     * Returns the value of the '<em><b>Concurrency Opt Threshold</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concurrency Opt Threshold</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Concurrency Opt Threshold</em>' attribute.
     * @see #setConcurrencyOptThreshold(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_ConcurrencyOptThreshold()
     * @model
     * @generated
     */
	int getConcurrencyOptThreshold();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getConcurrencyOptThreshold <em>Concurrency Opt Threshold</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Concurrency Opt Threshold</em>' attribute.
     * @see #getConcurrencyOptThreshold()
     * @generated
     */
	void setConcurrencyOptThreshold(int value);

	/**
     * Returns the value of the '<em><b>Partition Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Partition Condition</em>' containment reference.
     * @see #setPartitionCondition(SybaseASEPartition)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_PartitionCondition()
     * @model containment="true"
     * @generated
     */
	SybaseASEPartition getPartitionCondition();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getPartitionCondition <em>Partition Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partition Condition</em>' containment reference.
     * @see #getPartitionCondition()
     * @generated
     */
	void setPartitionCondition(SybaseASEPartition value);

	/**
     * Returns the value of the '<em><b>Table Only Cache Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Only Cache Info</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Table Only Cache Info</em>' containment reference.
     * @see #setTableOnlyCacheInfo(CacheInfo)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_TableOnlyCacheInfo()
     * @model containment="true"
     * @generated
     */
	CacheInfo getTableOnlyCacheInfo();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTableOnlyCacheInfo <em>Table Only Cache Info</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Only Cache Info</em>' containment reference.
     * @see #getTableOnlyCacheInfo()
     * @generated
     */
	void setTableOnlyCacheInfo(CacheInfo value);

	/**
     * Returns the value of the '<em><b>Text Only Cache Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text Only Cache Info</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Text Only Cache Info</em>' containment reference.
     * @see #setTextOnlyCacheInfo(CacheInfo)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_TextOnlyCacheInfo()
     * @model containment="true"
     * @generated
     */
	CacheInfo getTextOnlyCacheInfo();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTextOnlyCacheInfo <em>Text Only Cache Info</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Only Cache Info</em>' containment reference.
     * @see #getTextOnlyCacheInfo()
     * @generated
     */
	void setTextOnlyCacheInfo(CacheInfo value);

	/**
     * Returns the value of the '<em><b>Lock Promotion</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lock Promotion</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Lock Promotion</em>' containment reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_LockPromotion()
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo" containment="true"
     * @generated
     */
	EList getLockPromotion();

	/**
     * Returns the value of the '<em><b>Partitions</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partitions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Partitions</em>' attribute.
     * @see #setPartitions(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_Partitions()
     * @model default="-1"
     * @generated
     */
	int getPartitions();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getPartitions <em>Partitions</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partitions</em>' attribute.
     * @see #getPartitions()
     * @generated
     */
	void setPartitions(int value);

	/**
     * Returns the value of the '<em><b>Text Image Segment</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text Image Segment</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Text Image Segment</em>' reference.
     * @see #setTextImageSegment(SybaseASESegment)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_TextImageSegment()
     * @model
     * @generated
     */
	SybaseASESegment getTextImageSegment();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#getTextImageSegment <em>Text Image Segment</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Image Segment</em>' reference.
     * @see #getTextImageSegment()
     * @generated
     */
	void setTextImageSegment(SybaseASESegment value);

	/**
     * Returns the value of the '<em><b>System Table</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>System Table</em>' attribute.
     * @see #setSystemTable(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEBaseTable_SystemTable()
     * @model
     * @generated
     */
	boolean isSystemTable();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable#isSystemTable <em>System Table</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>System Table</em>' attribute.
     * @see #isSystemTable()
     * @generated
     */
	void setSystemTable(boolean value);

} // SybaseASEBaseTable
