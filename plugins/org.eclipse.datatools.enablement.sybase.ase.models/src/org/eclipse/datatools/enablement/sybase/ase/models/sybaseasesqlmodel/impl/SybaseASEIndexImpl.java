/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASEIndexImpl.java,v 1.10 2007/08/24 08:24:35 linsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair;

import org.eclipse.datatools.modelbase.sql.constraints.impl.IndexImpl;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Index</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#getMaxRowPerPage <em>Max Row Per Page</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#getReversePageGap <em>Reverse Page Gap</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#isIgnoreDuplicateKey <em>Ignore Duplicate Key</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#isSortedData <em>Sorted Data</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#isIgnoreDuplicateRow <em>Ignore Duplicate Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#getSegment <em>Segment</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#isLocalIndex <em>Local Index</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#getPartitions <em>Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#getConsumerNum <em>Consumer Num</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#getStatisticsStep <em>Statistics Step</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#isAllowDuplicateRow <em>Allow Duplicate Row</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#isSuspect <em>Suspect</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl#getCacheInfo <em>Cache Info</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASEIndexImpl extends IndexImpl implements SybaseASEIndex 
{
	/**
     * The default value of the '{@link #getMaxRowPerPage() <em>Max Row Per Page</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMaxRowPerPage()
     * @generated
     * @ordered
     */
	protected static final int MAX_ROW_PER_PAGE_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getMaxRowPerPage() <em>Max Row Per Page</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMaxRowPerPage()
     * @generated
     * @ordered
     */
	protected int maxRowPerPage = MAX_ROW_PER_PAGE_EDEFAULT;

	/**
     * The default value of the '{@link #getReversePageGap() <em>Reverse Page Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReversePageGap()
     * @generated
     * @ordered
     */
	protected static final int REVERSE_PAGE_GAP_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getReversePageGap() <em>Reverse Page Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReversePageGap()
     * @generated
     * @ordered
     */
	protected int reversePageGap = REVERSE_PAGE_GAP_EDEFAULT;

	/**
     * The default value of the '{@link #isIgnoreDuplicateKey() <em>Ignore Duplicate Key</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIgnoreDuplicateKey()
     * @generated
     * @ordered
     */
	protected static final boolean IGNORE_DUPLICATE_KEY_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isIgnoreDuplicateKey() <em>Ignore Duplicate Key</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIgnoreDuplicateKey()
     * @generated
     * @ordered
     */
	protected boolean ignoreDuplicateKey = IGNORE_DUPLICATE_KEY_EDEFAULT;

	/**
     * The default value of the '{@link #isSortedData() <em>Sorted Data</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSortedData()
     * @generated
     * @ordered
     */
	protected static final boolean SORTED_DATA_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isSortedData() <em>Sorted Data</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSortedData()
     * @generated
     * @ordered
     */
	protected boolean sortedData = SORTED_DATA_EDEFAULT;

	/**
     * The default value of the '{@link #isIgnoreDuplicateRow() <em>Ignore Duplicate Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIgnoreDuplicateRow()
     * @generated
     * @ordered
     */
	protected static final boolean IGNORE_DUPLICATE_ROW_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isIgnoreDuplicateRow() <em>Ignore Duplicate Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isIgnoreDuplicateRow()
     * @generated
     * @ordered
     */
	protected boolean ignoreDuplicateRow = IGNORE_DUPLICATE_ROW_EDEFAULT;

	/**
     * The cached value of the '{@link #getSegment() <em>Segment</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSegment()
     * @generated
     * @ordered
     */
	protected SybaseASESegment segment;

	/**
     * The default value of the '{@link #isLocalIndex() <em>Local Index</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLocalIndex()
     * @generated
     * @ordered
     */
	protected static final boolean LOCAL_INDEX_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isLocalIndex() <em>Local Index</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isLocalIndex()
     * @generated
     * @ordered
     */
	protected boolean localIndex = LOCAL_INDEX_EDEFAULT;

	/**
     * The cached value of the '{@link #getPartitions() <em>Partitions</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPartitions()
     * @generated
     * @ordered
     */
	protected EList partitions;

	/**
     * The default value of the '{@link #getConsumerNum() <em>Consumer Num</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConsumerNum()
     * @generated
     * @ordered
     */
	protected static final int CONSUMER_NUM_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getConsumerNum() <em>Consumer Num</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConsumerNum()
     * @generated
     * @ordered
     */
	protected int consumerNum = CONSUMER_NUM_EDEFAULT;

	/**
     * The default value of the '{@link #getStatisticsStep() <em>Statistics Step</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStatisticsStep()
     * @generated
     * @ordered
     */
	protected static final int STATISTICS_STEP_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getStatisticsStep() <em>Statistics Step</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStatisticsStep()
     * @generated
     * @ordered
     */
	protected int statisticsStep = STATISTICS_STEP_EDEFAULT;

	/**
     * The default value of the '{@link #isAllowDuplicateRow() <em>Allow Duplicate Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowDuplicateRow()
     * @generated
     * @ordered
     */
	protected static final boolean ALLOW_DUPLICATE_ROW_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isAllowDuplicateRow() <em>Allow Duplicate Row</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isAllowDuplicateRow()
     * @generated
     * @ordered
     */
	protected boolean allowDuplicateRow = ALLOW_DUPLICATE_ROW_EDEFAULT;

	/**
     * The default value of the '{@link #isSuspect() <em>Suspect</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSuspect()
     * @generated
     * @ordered
     */
	protected static final boolean SUSPECT_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isSuspect() <em>Suspect</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSuspect()
     * @generated
     * @ordered
     */
	protected boolean suspect = SUSPECT_EDEFAULT;

	/**
     * The cached value of the '{@link #getCacheInfo() <em>Cache Info</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCacheInfo()
     * @generated
     * @ordered
     */
	protected CacheInfo cacheInfo;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASEIndexImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_INDEX;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getMaxRowPerPage() {
        return maxRowPerPage;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMaxRowPerPage(int newMaxRowPerPage) {
        int oldMaxRowPerPage = maxRowPerPage;
        maxRowPerPage = newMaxRowPerPage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE, oldMaxRowPerPage, maxRowPerPage));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getReversePageGap() {
        return reversePageGap;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setReversePageGap(int newReversePageGap) {
        int oldReversePageGap = reversePageGap;
        reversePageGap = newReversePageGap;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__REVERSE_PAGE_GAP, oldReversePageGap, reversePageGap));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isIgnoreDuplicateKey() {
        return ignoreDuplicateKey;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIgnoreDuplicateKey(boolean newIgnoreDuplicateKey) {
        boolean oldIgnoreDuplicateKey = ignoreDuplicateKey;
        ignoreDuplicateKey = newIgnoreDuplicateKey;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY, oldIgnoreDuplicateKey, ignoreDuplicateKey));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isSortedData() {
        return sortedData;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSortedData(boolean newSortedData) {
        boolean oldSortedData = sortedData;
        sortedData = newSortedData;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SORTED_DATA, oldSortedData, sortedData));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isIgnoreDuplicateRow() {
        return ignoreDuplicateRow;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIgnoreDuplicateRow(boolean newIgnoreDuplicateRow) {
        boolean oldIgnoreDuplicateRow = ignoreDuplicateRow;
        ignoreDuplicateRow = newIgnoreDuplicateRow;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW, oldIgnoreDuplicateRow, ignoreDuplicateRow));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASESegment getSegment() {
        if (segment != null && segment.eIsProxy())
        {
            InternalEObject oldSegment = (InternalEObject)segment;
            segment = (SybaseASESegment)eResolveProxy(oldSegment);
            if (segment != oldSegment)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT, oldSegment, segment));
            }
        }
        return segment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASESegment basicGetSegment() {
        return segment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSegment(SybaseASESegment newSegment) {
        SybaseASESegment oldSegment = segment;
        segment = newSegment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT, oldSegment, segment));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isLocalIndex() {
        return localIndex;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLocalIndex(boolean newLocalIndex) {
        boolean oldLocalIndex = localIndex;
        localIndex = newLocalIndex;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__LOCAL_INDEX, oldLocalIndex, localIndex));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getPartitions() {
        if (partitions == null)
        {
            partitions = new EObjectContainmentEList(PartitionSegmentPair.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__PARTITIONS);
        }
        return partitions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getConsumerNum() {
        return consumerNum;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setConsumerNum(int newConsumerNum) {
        int oldConsumerNum = consumerNum;
        consumerNum = newConsumerNum;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CONSUMER_NUM, oldConsumerNum, consumerNum));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getStatisticsStep() {
        return statisticsStep;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setStatisticsStep(int newStatisticsStep) {
        int oldStatisticsStep = statisticsStep;
        statisticsStep = newStatisticsStep;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__STATISTICS_STEP, oldStatisticsStep, statisticsStep));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isAllowDuplicateRow() {
        return allowDuplicateRow;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAllowDuplicateRow(boolean newAllowDuplicateRow) {
        boolean oldAllowDuplicateRow = allowDuplicateRow;
        allowDuplicateRow = newAllowDuplicateRow;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW, oldAllowDuplicateRow, allowDuplicateRow));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isSuspect() {
        return suspect;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSuspect(boolean newSuspect) {
        boolean oldSuspect = suspect;
        suspect = newSuspect;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SUSPECT, oldSuspect, suspect));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CacheInfo getCacheInfo() {
        return cacheInfo;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCacheInfo(CacheInfo newCacheInfo, NotificationChain msgs) {
        CacheInfo oldCacheInfo = cacheInfo;
        cacheInfo = newCacheInfo;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO, oldCacheInfo, newCacheInfo);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCacheInfo(CacheInfo newCacheInfo) {
        if (newCacheInfo != cacheInfo)
        {
            NotificationChain msgs = null;
            if (cacheInfo != null)
                msgs = ((InternalEObject)cacheInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO, null, msgs);
            if (newCacheInfo != null)
                msgs = ((InternalEObject)newCacheInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO, null, msgs);
            msgs = basicSetCacheInfo(newCacheInfo, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO, newCacheInfo, newCacheInfo));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__PARTITIONS:
                return ((InternalEList)getPartitions()).basicRemove(otherEnd, msgs);
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO:
                return basicSetCacheInfo(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE:
                return new Integer(getMaxRowPerPage());
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__REVERSE_PAGE_GAP:
                return new Integer(getReversePageGap());
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY:
                return isIgnoreDuplicateKey() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SORTED_DATA:
                return isSortedData() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW:
                return isIgnoreDuplicateRow() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT:
                if (resolve) return getSegment();
                return basicGetSegment();
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__LOCAL_INDEX:
                return isLocalIndex() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__PARTITIONS:
                return getPartitions();
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CONSUMER_NUM:
                return new Integer(getConsumerNum());
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__STATISTICS_STEP:
                return new Integer(getStatisticsStep());
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW:
                return isAllowDuplicateRow() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SUSPECT:
                return isSuspect() ? Boolean.TRUE : Boolean.FALSE;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO:
                return getCacheInfo();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(int featureID, Object newValue) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE:
                setMaxRowPerPage(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__REVERSE_PAGE_GAP:
                setReversePageGap(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY:
                setIgnoreDuplicateKey(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SORTED_DATA:
                setSortedData(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW:
                setIgnoreDuplicateRow(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT:
                setSegment((SybaseASESegment)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__LOCAL_INDEX:
                setLocalIndex(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__PARTITIONS:
                getPartitions().clear();
                getPartitions().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CONSUMER_NUM:
                setConsumerNum(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__STATISTICS_STEP:
                setStatisticsStep(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW:
                setAllowDuplicateRow(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SUSPECT:
                setSuspect(((Boolean)newValue).booleanValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO:
                setCacheInfo((CacheInfo)newValue);
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE:
                setMaxRowPerPage(MAX_ROW_PER_PAGE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__REVERSE_PAGE_GAP:
                setReversePageGap(REVERSE_PAGE_GAP_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY:
                setIgnoreDuplicateKey(IGNORE_DUPLICATE_KEY_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SORTED_DATA:
                setSortedData(SORTED_DATA_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW:
                setIgnoreDuplicateRow(IGNORE_DUPLICATE_ROW_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT:
                setSegment((SybaseASESegment)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__LOCAL_INDEX:
                setLocalIndex(LOCAL_INDEX_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__PARTITIONS:
                getPartitions().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CONSUMER_NUM:
                setConsumerNum(CONSUMER_NUM_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__STATISTICS_STEP:
                setStatisticsStep(STATISTICS_STEP_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW:
                setAllowDuplicateRow(ALLOW_DUPLICATE_ROW_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SUSPECT:
                setSuspect(SUSPECT_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO:
                setCacheInfo((CacheInfo)null);
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
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE:
                return maxRowPerPage != MAX_ROW_PER_PAGE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__REVERSE_PAGE_GAP:
                return reversePageGap != REVERSE_PAGE_GAP_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY:
                return ignoreDuplicateKey != IGNORE_DUPLICATE_KEY_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SORTED_DATA:
                return sortedData != SORTED_DATA_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW:
                return ignoreDuplicateRow != IGNORE_DUPLICATE_ROW_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT:
                return segment != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__LOCAL_INDEX:
                return localIndex != LOCAL_INDEX_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__PARTITIONS:
                return partitions != null && !partitions.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CONSUMER_NUM:
                return consumerNum != CONSUMER_NUM_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__STATISTICS_STEP:
                return statisticsStep != STATISTICS_STEP_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW:
                return allowDuplicateRow != ALLOW_DUPLICATE_ROW_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SUSPECT:
                return suspect != SUSPECT_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO:
                return cacheInfo != null;
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
        result.append(" (maxRowPerPage: "); //$NON-NLS-1$
        result.append(maxRowPerPage);
        result.append(", reversePageGap: "); //$NON-NLS-1$
        result.append(reversePageGap);
        result.append(", ignoreDuplicateKey: "); //$NON-NLS-1$
        result.append(ignoreDuplicateKey);
        result.append(", sortedData: "); //$NON-NLS-1$
        result.append(sortedData);
        result.append(", ignoreDuplicateRow: "); //$NON-NLS-1$
        result.append(ignoreDuplicateRow);
        result.append(", localIndex: "); //$NON-NLS-1$
        result.append(localIndex);
        result.append(", consumerNum: "); //$NON-NLS-1$
        result.append(consumerNum);
        result.append(", statisticsStep: "); //$NON-NLS-1$
        result.append(statisticsStep);
        result.append(", allowDuplicateRow: "); //$NON-NLS-1$
        result.append(allowDuplicateRow);
        result.append(", suspect: "); //$NON-NLS-1$
        result.append(suspect);
        result.append(')');
        return result.toString();
    }

//    public Schema getSchema()
//    {
//        Table table = this.getTable();
//        if(table == null)
//            return null;
//        else
//            return table.getSchema();
//    }
} //SybaseASEIndexImpl
