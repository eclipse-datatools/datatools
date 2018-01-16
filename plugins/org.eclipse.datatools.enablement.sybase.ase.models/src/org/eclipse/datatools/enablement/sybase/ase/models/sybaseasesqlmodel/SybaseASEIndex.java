/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEIndex.java,v 1.7 2007/07/06 08:40:16 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.constraints.Index;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASE Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getMaxRowPerPage <em>Max Row Per Page</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getReversePageGap <em>Reverse Page Gap</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isIgnoreDuplicateKey <em>Ignore Duplicate Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isSortedData <em>Sorted Data</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isIgnoreDuplicateRow <em>Ignore Duplicate Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getSegment <em>Segment</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isLocalIndex <em>Local Index</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getPartitions <em>Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getConsumerNum <em>Consumer Num</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getStatisticsStep <em>Statistics Step</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isAllowDuplicateRow <em>Allow Duplicate Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isSuspect <em>Suspect</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getCacheInfo <em>Cache Info</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex()
 * @model
 * @generated
 */
public interface SybaseASEIndex extends Index, SQLObject {
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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_MaxRowPerPage()
     * @model default="-1"
     * @generated
     */
	int getMaxRowPerPage();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getMaxRowPerPage <em>Max Row Per Page</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max Row Per Page</em>' attribute.
     * @see #getMaxRowPerPage()
     * @generated
     */
	void setMaxRowPerPage(int value);

	/**
     * Returns the value of the '<em><b>Reverse Page Gap</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reverse Page Gap</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Reverse Page Gap</em>' attribute.
     * @see #setReversePageGap(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_ReversePageGap()
     * @model default="-1"
     * @generated
     */
	int getReversePageGap();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getReversePageGap <em>Reverse Page Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reverse Page Gap</em>' attribute.
     * @see #getReversePageGap()
     * @generated
     */
	void setReversePageGap(int value);

	/**
     * Returns the value of the '<em><b>Ignore Duplicate Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignore Duplicate Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Ignore Duplicate Key</em>' attribute.
     * @see #setIgnoreDuplicateKey(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_IgnoreDuplicateKey()
     * @model
     * @generated
     */
	boolean isIgnoreDuplicateKey();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isIgnoreDuplicateKey <em>Ignore Duplicate Key</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ignore Duplicate Key</em>' attribute.
     * @see #isIgnoreDuplicateKey()
     * @generated
     */
	void setIgnoreDuplicateKey(boolean value);

	/**
     * Returns the value of the '<em><b>Sorted Data</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sorted Data</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Sorted Data</em>' attribute.
     * @see #setSortedData(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_SortedData()
     * @model
     * @generated
     */
	boolean isSortedData();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isSortedData <em>Sorted Data</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Sorted Data</em>' attribute.
     * @see #isSortedData()
     * @generated
     */
	void setSortedData(boolean value);

	/**
     * Returns the value of the '<em><b>Ignore Duplicate Row</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignore Duplicate Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Ignore Duplicate Row</em>' attribute.
     * @see #setIgnoreDuplicateRow(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_IgnoreDuplicateRow()
     * @model
     * @generated
     */
	boolean isIgnoreDuplicateRow();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isIgnoreDuplicateRow <em>Ignore Duplicate Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ignore Duplicate Row</em>' attribute.
     * @see #isIgnoreDuplicateRow()
     * @generated
     */
	void setIgnoreDuplicateRow(boolean value);

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
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_Segment()
     * @model
     * @generated
     */
	SybaseASESegment getSegment();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getSegment <em>Segment</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Segment</em>' reference.
     * @see #getSegment()
     * @generated
     */
	void setSegment(SybaseASESegment value);

	/**
     * Returns the value of the '<em><b>Local Index</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local Index</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Local Index</em>' attribute.
     * @see #setLocalIndex(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_LocalIndex()
     * @model
     * @generated
     */
	boolean isLocalIndex();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isLocalIndex <em>Local Index</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Local Index</em>' attribute.
     * @see #isLocalIndex()
     * @generated
     */
	void setLocalIndex(boolean value);

	/**
     * Returns the value of the '<em><b>Partitions</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partitions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Partitions</em>' containment reference list.
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_Partitions()
     * @model type="org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair" containment="true"
     * @generated
     */
	EList getPartitions();

	/**
     * Returns the value of the '<em><b>Consumer Num</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Consumer Num</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Consumer Num</em>' attribute.
     * @see #setConsumerNum(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_ConsumerNum()
     * @model default="-1"
     * @generated
     */
	int getConsumerNum();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getConsumerNum <em>Consumer Num</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Consumer Num</em>' attribute.
     * @see #getConsumerNum()
     * @generated
     */
	void setConsumerNum(int value);

	/**
     * Returns the value of the '<em><b>Statistics Step</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statistics Step</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Statistics Step</em>' attribute.
     * @see #setStatisticsStep(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_StatisticsStep()
     * @model default="-1"
     * @generated
     */
	int getStatisticsStep();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getStatisticsStep <em>Statistics Step</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Statistics Step</em>' attribute.
     * @see #getStatisticsStep()
     * @generated
     */
	void setStatisticsStep(int value);

	/**
     * Returns the value of the '<em><b>Allow Duplicate Row</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow Duplicate Row</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Allow Duplicate Row</em>' attribute.
     * @see #setAllowDuplicateRow(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_AllowDuplicateRow()
     * @model
     * @generated
     */
	boolean isAllowDuplicateRow();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isAllowDuplicateRow <em>Allow Duplicate Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Allow Duplicate Row</em>' attribute.
     * @see #isAllowDuplicateRow()
     * @generated
     */
	void setAllowDuplicateRow(boolean value);

	/**
     * Returns the value of the '<em><b>Suspect</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Suspect</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Suspect</em>' attribute.
     * @see #setSuspect(boolean)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_Suspect()
     * @model
     * @generated
     */
	boolean isSuspect();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#isSuspect <em>Suspect</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Suspect</em>' attribute.
     * @see #isSuspect()
     * @generated
     */
	void setSuspect(boolean value);

	/**
     * Returns the value of the '<em><b>Cache Info</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cache Info</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Cache Info</em>' containment reference.
     * @see #setCacheInfo(CacheInfo)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getSybaseASEIndex_CacheInfo()
     * @model containment="true"
     * @generated
     */
	CacheInfo getCacheInfo();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex#getCacheInfo <em>Cache Info</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cache Info</em>' containment reference.
     * @see #getCacheInfo()
     * @generated
     */
	void setCacheInfo(CacheInfo value);

} // SybaseASEIndex
