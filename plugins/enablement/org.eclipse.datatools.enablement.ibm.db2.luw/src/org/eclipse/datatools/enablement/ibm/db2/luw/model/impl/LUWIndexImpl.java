/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWIndexImpl.java,v 1.14 2009/07/22 20:11:50 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2IndexImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndex;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexCompressType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWIndexPageSplitType;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWTableSpace;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getPCTFree <em>PCT Free</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getMinPCTFree <em>Min PCT Free</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isReverseScan <em>Reverse Scan</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isNotPartitioned <em>Not Partitioned</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getXmlPattern <em>Xml Pattern</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getAsSQLDataType <em>As SQL Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isAsSQLDataTypeHashed <em>As SQL Data Type Hashed</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isSystemRequired <em>System Required</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getPageSplitType <em>Page Split Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getLevel2PctFree <em>Level2 Pct Free</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getMinPctUsed <em>Min Pct Used</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getCompress <em>Compress</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isCollectStats <em>Collect Stats</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isSampledStats <em>Sampled Stats</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isDetailedStats <em>Detailed Stats</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isIgnoreInvalidValues <em>Ignore Invalid Values</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#isExcludeNullKeys <em>Exclude Null Keys</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.LUWIndexImpl#getTablespace <em>Tablespace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LUWIndexImpl extends DB2IndexImpl implements LUWIndex {
	/**
	 * The default value of the '{@link #getPCTFree() <em>PCT Free</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPCTFree()
	 * @generated
	 * @ordered
	 */
    protected static final int PCT_FREE_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getPCTFree() <em>PCT Free</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPCTFree()
	 * @generated
	 * @ordered
	 */
    protected int pctFree = PCT_FREE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinPCTFree() <em>Min PCT Free</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMinPCTFree()
	 * @generated
	 * @ordered
	 */
    protected static final int MIN_PCT_FREE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinPCTFree() <em>Min PCT Free</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMinPCTFree()
	 * @generated
	 * @ordered
	 */
    protected int minPCTFree = MIN_PCT_FREE_EDEFAULT;

	/**
	 * The default value of the '{@link #isReverseScan() <em>Reverse Scan</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isReverseScan()
	 * @generated
	 * @ordered
	 */
    protected static final boolean REVERSE_SCAN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReverseScan() <em>Reverse Scan</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isReverseScan()
	 * @generated
	 * @ordered
	 */
    protected boolean reverseScan = REVERSE_SCAN_EDEFAULT;

	/**
	 * The default value of the '{@link #isNotPartitioned() <em>Not Partitioned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNotPartitioned()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NOT_PARTITIONED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNotPartitioned() <em>Not Partitioned</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNotPartitioned()
	 * @generated
	 * @ordered
	 */
	protected boolean notPartitioned = NOT_PARTITIONED_EDEFAULT;

	/**
	 * The default value of the '{@link #getXmlPattern() <em>Xml Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String XML_PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getXmlPattern() <em>Xml Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXmlPattern()
	 * @generated
	 * @ordered
	 */
	protected String xmlPattern = XML_PATTERN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAsSQLDataType() <em>As SQL Data Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsSQLDataType()
	 * @generated
	 * @ordered
	 */
	protected PredefinedDataType asSQLDataType;

	/**
	 * The default value of the '{@link #isAsSQLDataTypeHashed() <em>As SQL Data Type Hashed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsSQLDataTypeHashed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AS_SQL_DATA_TYPE_HASHED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAsSQLDataTypeHashed() <em>As SQL Data Type Hashed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAsSQLDataTypeHashed()
	 * @generated
	 * @ordered
	 */
	protected boolean asSQLDataTypeHashed = AS_SQL_DATA_TYPE_HASHED_EDEFAULT;

	/**
	 * The default value of the '{@link #isSystemRequired() <em>System Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSystemRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYSTEM_REQUIRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSystemRequired() <em>System Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSystemRequired()
	 * @generated
	 * @ordered
	 */
	protected boolean systemRequired = SYSTEM_REQUIRED_EDEFAULT;

	/**
	 * The default value of the '{@link #getPageSplitType() <em>Page Split Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSplitType()
	 * @generated
	 * @ordered
	 */
	protected static final LUWIndexPageSplitType PAGE_SPLIT_TYPE_EDEFAULT = LUWIndexPageSplitType.SYMMETRIC_LITERAL;

	/**
	 * The cached value of the '{@link #getPageSplitType() <em>Page Split Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSplitType()
	 * @generated
	 * @ordered
	 */
	protected LUWIndexPageSplitType pageSplitType = PAGE_SPLIT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getLevel2PctFree() <em>Level2 Pct Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel2PctFree()
	 * @generated
	 * @ordered
	 */
	protected static final int LEVEL2_PCT_FREE_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getLevel2PctFree() <em>Level2 Pct Free</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel2PctFree()
	 * @generated
	 * @ordered
	 */
	protected int level2PctFree = LEVEL2_PCT_FREE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinPctUsed() <em>Min Pct Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinPctUsed()
	 * @generated
	 * @ordered
	 */
	protected static final int MIN_PCT_USED_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinPctUsed() <em>Min Pct Used</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinPctUsed()
	 * @generated
	 * @ordered
	 */
	protected int minPctUsed = MIN_PCT_USED_EDEFAULT;

	/**
	 * The default value of the '{@link #getCompress() <em>Compress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompress()
	 * @generated
	 * @ordered
	 */
	protected static final LUWIndexCompressType COMPRESS_EDEFAULT = LUWIndexCompressType.NO_LITERAL;

	/**
	 * The cached value of the '{@link #getCompress() <em>Compress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompress()
	 * @generated
	 * @ordered
	 */
	protected LUWIndexCompressType compress = COMPRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #isCollectStats() <em>Collect Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollectStats()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLLECT_STATS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isCollectStats() <em>Collect Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollectStats()
	 * @generated
	 * @ordered
	 */
	protected boolean collectStats = COLLECT_STATS_EDEFAULT;

	/**
	 * The default value of the '{@link #isSampledStats() <em>Sampled Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSampledStats()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SAMPLED_STATS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSampledStats() <em>Sampled Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSampledStats()
	 * @generated
	 * @ordered
	 */
	protected boolean sampledStats = SAMPLED_STATS_EDEFAULT;

	/**
	 * The default value of the '{@link #isDetailedStats() <em>Detailed Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDetailedStats()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DETAILED_STATS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isDetailedStats() <em>Detailed Stats</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDetailedStats()
	 * @generated
	 * @ordered
	 */
	protected boolean detailedStats = DETAILED_STATS_EDEFAULT;

	/**
	 * The default value of the '{@link #isIgnoreInvalidValues() <em>Ignore Invalid Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoreInvalidValues()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IGNORE_INVALID_VALUES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIgnoreInvalidValues() <em>Ignore Invalid Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIgnoreInvalidValues()
	 * @generated
	 * @ordered
	 */
	protected boolean ignoreInvalidValues = IGNORE_INVALID_VALUES_EDEFAULT;

	/**
	 * The default value of the '{@link #isExcludeNullKeys() <em>Exclude Null Keys</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExcludeNullKeys()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXCLUDE_NULL_KEYS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExcludeNullKeys() <em>Exclude Null Keys</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExcludeNullKeys()
	 * @generated
	 * @ordered
	 */
	protected boolean excludeNullKeys = EXCLUDE_NULL_KEYS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTablespace() <em>Tablespace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTablespace()
	 * @generated
	 * @ordered
	 */
	protected LUWTableSpace tablespace;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected LUWIndexImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return LUWPackage.Literals.LUW_INDEX;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getPCTFree() {
		return pctFree;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPCTFree(int newPCTFree) {
		int oldPCTFree = pctFree;
		pctFree = newPCTFree;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__PCT_FREE, oldPCTFree, pctFree));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getMinPCTFree() {
		return minPCTFree;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMinPCTFree(int newMinPCTFree) {
		int oldMinPCTFree = minPCTFree;
		minPCTFree = newMinPCTFree;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__MIN_PCT_FREE, oldMinPCTFree, minPCTFree));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isReverseScan() {
		return reverseScan;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setReverseScan(boolean newReverseScan) {
		boolean oldReverseScan = reverseScan;
		reverseScan = newReverseScan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__REVERSE_SCAN, oldReverseScan, reverseScan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNotPartitioned() {
		return notPartitioned;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotPartitioned(boolean newNotPartitioned) {
		boolean oldNotPartitioned = notPartitioned;
		notPartitioned = newNotPartitioned;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__NOT_PARTITIONED, oldNotPartitioned, notPartitioned));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getXmlPattern() {
		return xmlPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXmlPattern(String newXmlPattern) {
		String oldXmlPattern = xmlPattern;
		xmlPattern = newXmlPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__XML_PATTERN, oldXmlPattern, xmlPattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataType getAsSQLDataType() {
		return asSQLDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAsSQLDataType(PredefinedDataType newAsSQLDataType, NotificationChain msgs) {
		PredefinedDataType oldAsSQLDataType = asSQLDataType;
		asSQLDataType = newAsSQLDataType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE, oldAsSQLDataType, newAsSQLDataType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsSQLDataType(PredefinedDataType newAsSQLDataType) {
		if (newAsSQLDataType != asSQLDataType) {
			NotificationChain msgs = null;
			if (asSQLDataType != null)
				msgs = ((InternalEObject)asSQLDataType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE, null, msgs);
			if (newAsSQLDataType != null)
				msgs = ((InternalEObject)newAsSQLDataType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE, null, msgs);
			msgs = basicSetAsSQLDataType(newAsSQLDataType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE, newAsSQLDataType, newAsSQLDataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAsSQLDataTypeHashed() {
		return asSQLDataTypeHashed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsSQLDataTypeHashed(boolean newAsSQLDataTypeHashed) {
		boolean oldAsSQLDataTypeHashed = asSQLDataTypeHashed;
		asSQLDataTypeHashed = newAsSQLDataTypeHashed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE_HASHED, oldAsSQLDataTypeHashed, asSQLDataTypeHashed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSystemRequired() {
		return systemRequired;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemRequired(boolean newSystemRequired) {
		boolean oldSystemRequired = systemRequired;
		systemRequired = newSystemRequired;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__SYSTEM_REQUIRED, oldSystemRequired, systemRequired));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWIndexPageSplitType getPageSplitType() {
		return pageSplitType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageSplitType(LUWIndexPageSplitType newPageSplitType) {
		LUWIndexPageSplitType oldPageSplitType = pageSplitType;
		pageSplitType = newPageSplitType == null ? PAGE_SPLIT_TYPE_EDEFAULT : newPageSplitType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__PAGE_SPLIT_TYPE, oldPageSplitType, pageSplitType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLevel2PctFree() {
		return level2PctFree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLevel2PctFree(int newLevel2PctFree) {
		int oldLevel2PctFree = level2PctFree;
		level2PctFree = newLevel2PctFree;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__LEVEL2_PCT_FREE, oldLevel2PctFree, level2PctFree));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinPctUsed() {
		return minPctUsed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinPctUsed(int newMinPctUsed) {
		int oldMinPctUsed = minPctUsed;
		minPctUsed = newMinPctUsed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__MIN_PCT_USED, oldMinPctUsed, minPctUsed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWIndexCompressType getCompress() {
		return compress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompress(LUWIndexCompressType newCompress) {
		LUWIndexCompressType oldCompress = compress;
		compress = newCompress == null ? COMPRESS_EDEFAULT : newCompress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__COMPRESS, oldCompress, compress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCollectStats() {
		return collectStats;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollectStats(boolean newCollectStats) {
		boolean oldCollectStats = collectStats;
		collectStats = newCollectStats;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__COLLECT_STATS, oldCollectStats, collectStats));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSampledStats() {
		return sampledStats;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSampledStats(boolean newSampledStats) {
		boolean oldSampledStats = sampledStats;
		sampledStats = newSampledStats;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__SAMPLED_STATS, oldSampledStats, sampledStats));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDetailedStats() {
		return detailedStats;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDetailedStats(boolean newDetailedStats) {
		boolean oldDetailedStats = detailedStats;
		detailedStats = newDetailedStats;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__DETAILED_STATS, oldDetailedStats, detailedStats));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIgnoreInvalidValues() {
		return ignoreInvalidValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIgnoreInvalidValues(boolean newIgnoreInvalidValues) {
		boolean oldIgnoreInvalidValues = ignoreInvalidValues;
		ignoreInvalidValues = newIgnoreInvalidValues;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__IGNORE_INVALID_VALUES, oldIgnoreInvalidValues, ignoreInvalidValues));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExcludeNullKeys() {
		return excludeNullKeys;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExcludeNullKeys(boolean newExcludeNullKeys) {
		boolean oldExcludeNullKeys = excludeNullKeys;
		excludeNullKeys = newExcludeNullKeys;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__EXCLUDE_NULL_KEYS, oldExcludeNullKeys, excludeNullKeys));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace getTablespace() {
		if (tablespace != null && tablespace.eIsProxy()) {
			InternalEObject oldTablespace = (InternalEObject)tablespace;
			tablespace = (LUWTableSpace)eResolveProxy(oldTablespace);
			if (tablespace != oldTablespace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LUWPackage.LUW_INDEX__TABLESPACE, oldTablespace, tablespace));
			}
		}
		return tablespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LUWTableSpace basicGetTablespace() {
		return tablespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTablespace(LUWTableSpace newTablespace, NotificationChain msgs) {
		LUWTableSpace oldTablespace = tablespace;
		tablespace = newTablespace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__TABLESPACE, oldTablespace, newTablespace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTablespace(LUWTableSpace newTablespace) {
		if (newTablespace != tablespace) {
			NotificationChain msgs = null;
			if (tablespace != null)
				msgs = ((InternalEObject)tablespace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__INDEXES, LUWTableSpace.class, msgs);
			if (newTablespace != null)
				msgs = ((InternalEObject)newTablespace).eInverseAdd(this, LUWPackage.LUW_TABLE_SPACE__INDEXES, LUWTableSpace.class, msgs);
			msgs = basicSetTablespace(newTablespace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LUWPackage.LUW_INDEX__TABLESPACE, newTablespace, newTablespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case LUWPackage.LUW_INDEX__TABLESPACE:
				if (tablespace != null)
					msgs = ((InternalEObject)tablespace).eInverseRemove(this, LUWPackage.LUW_TABLE_SPACE__INDEXES, LUWTableSpace.class, msgs);
				return basicSetTablespace((LUWTableSpace)otherEnd, msgs);
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
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE:
				return basicSetAsSQLDataType(null, msgs);
			case LUWPackage.LUW_INDEX__TABLESPACE:
				return basicSetTablespace(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LUWPackage.LUW_INDEX__PCT_FREE:
				return new Integer(getPCTFree());
			case LUWPackage.LUW_INDEX__MIN_PCT_FREE:
				return new Integer(getMinPCTFree());
			case LUWPackage.LUW_INDEX__REVERSE_SCAN:
				return isReverseScan() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__NOT_PARTITIONED:
				return isNotPartitioned() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__XML_PATTERN:
				return getXmlPattern();
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE:
				return getAsSQLDataType();
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE_HASHED:
				return isAsSQLDataTypeHashed() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__SYSTEM_REQUIRED:
				return isSystemRequired() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__PAGE_SPLIT_TYPE:
				return getPageSplitType();
			case LUWPackage.LUW_INDEX__LEVEL2_PCT_FREE:
				return new Integer(getLevel2PctFree());
			case LUWPackage.LUW_INDEX__MIN_PCT_USED:
				return new Integer(getMinPctUsed());
			case LUWPackage.LUW_INDEX__COMPRESS:
				return getCompress();
			case LUWPackage.LUW_INDEX__COLLECT_STATS:
				return isCollectStats() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__SAMPLED_STATS:
				return isSampledStats() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__DETAILED_STATS:
				return isDetailedStats() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__IGNORE_INVALID_VALUES:
				return isIgnoreInvalidValues() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__EXCLUDE_NULL_KEYS:
				return isExcludeNullKeys() ? Boolean.TRUE : Boolean.FALSE;
			case LUWPackage.LUW_INDEX__TABLESPACE:
				if (resolve) return getTablespace();
				return basicGetTablespace();
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
			case LUWPackage.LUW_INDEX__PCT_FREE:
				setPCTFree(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_INDEX__MIN_PCT_FREE:
				setMinPCTFree(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_INDEX__REVERSE_SCAN:
				setReverseScan(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__NOT_PARTITIONED:
				setNotPartitioned(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__XML_PATTERN:
				setXmlPattern((String)newValue);
				return;
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE:
				setAsSQLDataType((PredefinedDataType)newValue);
				return;
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE_HASHED:
				setAsSQLDataTypeHashed(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__SYSTEM_REQUIRED:
				setSystemRequired(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__PAGE_SPLIT_TYPE:
				setPageSplitType((LUWIndexPageSplitType)newValue);
				return;
			case LUWPackage.LUW_INDEX__LEVEL2_PCT_FREE:
				setLevel2PctFree(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_INDEX__MIN_PCT_USED:
				setMinPctUsed(((Integer)newValue).intValue());
				return;
			case LUWPackage.LUW_INDEX__COMPRESS:
				setCompress((LUWIndexCompressType)newValue);
				return;
			case LUWPackage.LUW_INDEX__COLLECT_STATS:
				setCollectStats(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__SAMPLED_STATS:
				setSampledStats(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__DETAILED_STATS:
				setDetailedStats(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__IGNORE_INVALID_VALUES:
				setIgnoreInvalidValues(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__EXCLUDE_NULL_KEYS:
				setExcludeNullKeys(((Boolean)newValue).booleanValue());
				return;
			case LUWPackage.LUW_INDEX__TABLESPACE:
				setTablespace((LUWTableSpace)newValue);
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
			case LUWPackage.LUW_INDEX__PCT_FREE:
				setPCTFree(PCT_FREE_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__MIN_PCT_FREE:
				setMinPCTFree(MIN_PCT_FREE_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__REVERSE_SCAN:
				setReverseScan(REVERSE_SCAN_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__NOT_PARTITIONED:
				setNotPartitioned(NOT_PARTITIONED_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__XML_PATTERN:
				setXmlPattern(XML_PATTERN_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE:
				setAsSQLDataType((PredefinedDataType)null);
				return;
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE_HASHED:
				setAsSQLDataTypeHashed(AS_SQL_DATA_TYPE_HASHED_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__SYSTEM_REQUIRED:
				setSystemRequired(SYSTEM_REQUIRED_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__PAGE_SPLIT_TYPE:
				setPageSplitType(PAGE_SPLIT_TYPE_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__LEVEL2_PCT_FREE:
				setLevel2PctFree(LEVEL2_PCT_FREE_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__MIN_PCT_USED:
				setMinPctUsed(MIN_PCT_USED_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__COMPRESS:
				setCompress(COMPRESS_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__COLLECT_STATS:
				setCollectStats(COLLECT_STATS_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__SAMPLED_STATS:
				setSampledStats(SAMPLED_STATS_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__DETAILED_STATS:
				setDetailedStats(DETAILED_STATS_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__IGNORE_INVALID_VALUES:
				setIgnoreInvalidValues(IGNORE_INVALID_VALUES_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__EXCLUDE_NULL_KEYS:
				setExcludeNullKeys(EXCLUDE_NULL_KEYS_EDEFAULT);
				return;
			case LUWPackage.LUW_INDEX__TABLESPACE:
				setTablespace((LUWTableSpace)null);
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
			case LUWPackage.LUW_INDEX__PCT_FREE:
				return pctFree != PCT_FREE_EDEFAULT;
			case LUWPackage.LUW_INDEX__MIN_PCT_FREE:
				return minPCTFree != MIN_PCT_FREE_EDEFAULT;
			case LUWPackage.LUW_INDEX__REVERSE_SCAN:
				return reverseScan != REVERSE_SCAN_EDEFAULT;
			case LUWPackage.LUW_INDEX__NOT_PARTITIONED:
				return notPartitioned != NOT_PARTITIONED_EDEFAULT;
			case LUWPackage.LUW_INDEX__XML_PATTERN:
				return XML_PATTERN_EDEFAULT == null ? xmlPattern != null : !XML_PATTERN_EDEFAULT.equals(xmlPattern);
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE:
				return asSQLDataType != null;
			case LUWPackage.LUW_INDEX__AS_SQL_DATA_TYPE_HASHED:
				return asSQLDataTypeHashed != AS_SQL_DATA_TYPE_HASHED_EDEFAULT;
			case LUWPackage.LUW_INDEX__SYSTEM_REQUIRED:
				return systemRequired != SYSTEM_REQUIRED_EDEFAULT;
			case LUWPackage.LUW_INDEX__PAGE_SPLIT_TYPE:
				return pageSplitType != PAGE_SPLIT_TYPE_EDEFAULT;
			case LUWPackage.LUW_INDEX__LEVEL2_PCT_FREE:
				return level2PctFree != LEVEL2_PCT_FREE_EDEFAULT;
			case LUWPackage.LUW_INDEX__MIN_PCT_USED:
				return minPctUsed != MIN_PCT_USED_EDEFAULT;
			case LUWPackage.LUW_INDEX__COMPRESS:
				return compress != COMPRESS_EDEFAULT;
			case LUWPackage.LUW_INDEX__COLLECT_STATS:
				return collectStats != COLLECT_STATS_EDEFAULT;
			case LUWPackage.LUW_INDEX__SAMPLED_STATS:
				return sampledStats != SAMPLED_STATS_EDEFAULT;
			case LUWPackage.LUW_INDEX__DETAILED_STATS:
				return detailedStats != DETAILED_STATS_EDEFAULT;
			case LUWPackage.LUW_INDEX__IGNORE_INVALID_VALUES:
				return ignoreInvalidValues != IGNORE_INVALID_VALUES_EDEFAULT;
			case LUWPackage.LUW_INDEX__EXCLUDE_NULL_KEYS:
				return excludeNullKeys != EXCLUDE_NULL_KEYS_EDEFAULT;
			case LUWPackage.LUW_INDEX__TABLESPACE:
				return tablespace != null;
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
		result.append(" (PCTFree: "); //$NON-NLS-1$
		result.append(pctFree);
		result.append(", minPCTFree: "); //$NON-NLS-1$
		result.append(minPCTFree);
		result.append(", reverseScan: "); //$NON-NLS-1$
		result.append(reverseScan);
		result.append(", notPartitioned: "); //$NON-NLS-1$
		result.append(notPartitioned);
		result.append(", xmlPattern: "); //$NON-NLS-1$
		result.append(xmlPattern);
		result.append(", asSQLDataTypeHashed: "); //$NON-NLS-1$
		result.append(asSQLDataTypeHashed);
		result.append(", systemRequired: "); //$NON-NLS-1$
		result.append(systemRequired);
		result.append(", pageSplitType: "); //$NON-NLS-1$
		result.append(pageSplitType);
		result.append(", level2PctFree: "); //$NON-NLS-1$
		result.append(level2PctFree);
		result.append(", minPctUsed: "); //$NON-NLS-1$
		result.append(minPctUsed);
		result.append(", compress: "); //$NON-NLS-1$
		result.append(compress);
		result.append(", collectStats: "); //$NON-NLS-1$
		result.append(collectStats);
		result.append(", sampledStats: "); //$NON-NLS-1$
		result.append(sampledStats);
		result.append(", detailedStats: "); //$NON-NLS-1$
		result.append(detailedStats);
		result.append(", ignoreInvalidValues: "); //$NON-NLS-1$
		result.append(ignoreInvalidValues);
		result.append(", excludeNullKeys: "); //$NON-NLS-1$
		result.append(excludeNullKeys);
		result.append(')');
		return result.toString();
	}

} //LUWIndexImpl
