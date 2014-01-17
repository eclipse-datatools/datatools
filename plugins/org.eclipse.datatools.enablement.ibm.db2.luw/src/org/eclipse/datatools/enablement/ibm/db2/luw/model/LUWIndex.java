/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWIndex.java,v 1.9 2009/07/22 20:11:50 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Index;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getPCTFree <em>PCT Free</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getMinPCTFree <em>Min PCT Free</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isReverseScan <em>Reverse Scan</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isNotPartitioned <em>Not Partitioned</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getXmlPattern <em>Xml Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getAsSQLDataType <em>As SQL Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isAsSQLDataTypeHashed <em>As SQL Data Type Hashed</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isSystemRequired <em>System Required</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getPageSplitType <em>Page Split Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getLevel2PctFree <em>Level2 Pct Free</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getMinPctUsed <em>Min Pct Used</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getCompress <em>Compress</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isCollectStats <em>Collect Stats</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isSampledStats <em>Sampled Stats</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isDetailedStats <em>Detailed Stats</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isIgnoreInvalidValues <em>Ignore Invalid Values</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isExcludeNullKeys <em>Exclude Null Keys</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getTablespace <em>Tablespace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex()
 * @model
 * @generated
 */
public interface LUWIndex extends DB2Index {
	/**
	 * Returns the value of the '<em><b>PCT Free</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>PCT Free</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>PCT Free</em>' attribute.
	 * @see #setPCTFree(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_PCTFree()
	 * @model default="-1"
	 * @generated
	 */
    int getPCTFree();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getPCTFree <em>PCT Free</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>PCT Free</em>' attribute.
	 * @see #getPCTFree()
	 * @generated
	 */
    void setPCTFree(int value);

	/**
	 * Returns the value of the '<em><b>Min PCT Free</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min PCT Free</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Min PCT Free</em>' attribute.
	 * @see #setMinPCTFree(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_MinPCTFree()
	 * @model
	 * @generated
	 */
    int getMinPCTFree();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getMinPCTFree <em>Min PCT Free</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min PCT Free</em>' attribute.
	 * @see #getMinPCTFree()
	 * @generated
	 */
    void setMinPCTFree(int value);

	/**
	 * Returns the value of the '<em><b>Reverse Scan</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reverse Scan</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Reverse Scan</em>' attribute.
	 * @see #setReverseScan(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_ReverseScan()
	 * @model
	 * @generated
	 */
    boolean isReverseScan();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isReverseScan <em>Reverse Scan</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reverse Scan</em>' attribute.
	 * @see #isReverseScan()
	 * @generated
	 */
    void setReverseScan(boolean value);

	/**
	 * Returns the value of the '<em><b>Not Partitioned</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not Partitioned</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not Partitioned</em>' attribute.
	 * @see #setNotPartitioned(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_NotPartitioned()
	 * @model
	 * @generated
	 */
	boolean isNotPartitioned();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isNotPartitioned <em>Not Partitioned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Not Partitioned</em>' attribute.
	 * @see #isNotPartitioned()
	 * @generated
	 */
	void setNotPartitioned(boolean value);

	/**
	 * Returns the value of the '<em><b>Xml Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xml Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Pattern</em>' attribute.
	 * @see #setXmlPattern(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_XmlPattern()
	 * @model
	 * @generated
	 */
	String getXmlPattern();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getXmlPattern <em>Xml Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Pattern</em>' attribute.
	 * @see #getXmlPattern()
	 * @generated
	 */
	void setXmlPattern(String value);

	/**
	 * Returns the value of the '<em><b>As SQL Data Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>As SQL Data Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As SQL Data Type</em>' containment reference.
	 * @see #setAsSQLDataType(PredefinedDataType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_AsSQLDataType()
	 * @model containment="true"
	 * @generated
	 */
	PredefinedDataType getAsSQLDataType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getAsSQLDataType <em>As SQL Data Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As SQL Data Type</em>' containment reference.
	 * @see #getAsSQLDataType()
	 * @generated
	 */
	void setAsSQLDataType(PredefinedDataType value);

	/**
	 * Returns the value of the '<em><b>As SQL Data Type Hashed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>As SQL Data Type Hashed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As SQL Data Type Hashed</em>' attribute.
	 * @see #setAsSQLDataTypeHashed(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_AsSQLDataTypeHashed()
	 * @model
	 * @generated
	 */
	boolean isAsSQLDataTypeHashed();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isAsSQLDataTypeHashed <em>As SQL Data Type Hashed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As SQL Data Type Hashed</em>' attribute.
	 * @see #isAsSQLDataTypeHashed()
	 * @generated
	 */
	void setAsSQLDataTypeHashed(boolean value);

	/**
	 * Returns the value of the '<em><b>System Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Required</em>' attribute.
	 * @see #setSystemRequired(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_SystemRequired()
	 * @model
	 * @generated
	 */
	boolean isSystemRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isSystemRequired <em>System Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Required</em>' attribute.
	 * @see #isSystemRequired()
	 * @generated
	 */
	void setSystemRequired(boolean value);

	/**
	 * Returns the value of the '<em><b>Page Split Type</b></em>' attribute.
	 * The default value is <code>"SYMMETRIC"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Page Split Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Page Split Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType
	 * @see #setPageSplitType(LUWIndexPageSplitType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_PageSplitType()
	 * @model default="SYMMETRIC"
	 * @generated
	 */
	LUWIndexPageSplitType getPageSplitType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getPageSplitType <em>Page Split Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Split Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType
	 * @see #getPageSplitType()
	 * @generated
	 */
	void setPageSplitType(LUWIndexPageSplitType value);

	/**
	 * Returns the value of the '<em><b>Level2 Pct Free</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Level2 Pct Free</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level2 Pct Free</em>' attribute.
	 * @see #setLevel2PctFree(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_Level2PctFree()
	 * @model default="-1"
	 * @generated
	 */
	int getLevel2PctFree();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getLevel2PctFree <em>Level2 Pct Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level2 Pct Free</em>' attribute.
	 * @see #getLevel2PctFree()
	 * @generated
	 */
	void setLevel2PctFree(int value);

	/**
	 * Returns the value of the '<em><b>Min Pct Used</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Pct Used</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Pct Used</em>' attribute.
	 * @see #setMinPctUsed(int)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_MinPctUsed()
	 * @model
	 * @generated
	 */
	int getMinPctUsed();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getMinPctUsed <em>Min Pct Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Pct Used</em>' attribute.
	 * @see #getMinPctUsed()
	 * @generated
	 */
	void setMinPctUsed(int value);

	/**
	 * Returns the value of the '<em><b>Compress</b></em>' attribute.
	 * The default value is <code>"NO"</code>.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compress</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compress</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType
	 * @see #setCompress(LUWIndexCompressType)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_Compress()
	 * @model default="NO"
	 * @generated
	 */
	LUWIndexCompressType getCompress();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getCompress <em>Compress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compress</em>' attribute.
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType
	 * @see #getCompress()
	 * @generated
	 */
	void setCompress(LUWIndexCompressType value);

	/**
	 * Returns the value of the '<em><b>Collect Stats</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collect Stats</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collect Stats</em>' attribute.
	 * @see #setCollectStats(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_CollectStats()
	 * @model default="true"
	 * @generated
	 */
	boolean isCollectStats();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isCollectStats <em>Collect Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collect Stats</em>' attribute.
	 * @see #isCollectStats()
	 * @generated
	 */
	void setCollectStats(boolean value);

	/**
	 * Returns the value of the '<em><b>Sampled Stats</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sampled Stats</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sampled Stats</em>' attribute.
	 * @see #setSampledStats(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_SampledStats()
	 * @model default="true"
	 * @generated
	 */
	boolean isSampledStats();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isSampledStats <em>Sampled Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sampled Stats</em>' attribute.
	 * @see #isSampledStats()
	 * @generated
	 */
	void setSampledStats(boolean value);

	/**
	 * Returns the value of the '<em><b>Detailed Stats</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Detailed Stats</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Detailed Stats</em>' attribute.
	 * @see #setDetailedStats(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_DetailedStats()
	 * @model default="true"
	 * @generated
	 */
	boolean isDetailedStats();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isDetailedStats <em>Detailed Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Detailed Stats</em>' attribute.
	 * @see #isDetailedStats()
	 * @generated
	 */
	void setDetailedStats(boolean value);

	/**
	 * Returns the value of the '<em><b>Ignore Invalid Values</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignore Invalid Values</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ignore Invalid Values</em>' attribute.
	 * @see #setIgnoreInvalidValues(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_IgnoreInvalidValues()
	 * @model default="true"
	 * @generated
	 */
	boolean isIgnoreInvalidValues();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isIgnoreInvalidValues <em>Ignore Invalid Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ignore Invalid Values</em>' attribute.
	 * @see #isIgnoreInvalidValues()
	 * @generated
	 */
	void setIgnoreInvalidValues(boolean value);

	/**
	 * Returns the value of the '<em><b>Exclude Null Keys</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclude Null Keys</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclude Null Keys</em>' attribute.
	 * @see #setExcludeNullKeys(boolean)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_ExcludeNullKeys()
	 * @model default="false"
	 * @generated
	 */
	boolean isExcludeNullKeys();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#isExcludeNullKeys <em>Exclude Null Keys</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclude Null Keys</em>' attribute.
	 * @see #isExcludeNullKeys()
	 * @generated
	 */
	void setExcludeNullKeys(boolean value);

	/**
	 * Returns the value of the '<em><b>Tablespace</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexes <em>Indexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tablespace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tablespace</em>' reference.
	 * @see #setTablespace(LUWTableSpace)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWIndex_Tablespace()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace#getIndexes
	 * @model opposite="indexes"
	 * @generated
	 */
	LUWTableSpace getTablespace();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex#getTablespace <em>Tablespace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tablespace</em>' reference.
	 * @see #getTablespace()
	 * @generated
	 */
	void setTablespace(LUWTableSpace value);

} // LUWIndex
