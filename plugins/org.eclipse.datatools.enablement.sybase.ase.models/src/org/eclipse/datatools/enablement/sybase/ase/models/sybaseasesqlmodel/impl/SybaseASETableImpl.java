/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASETableImpl.java,v 1.9 2007/07/06 08:40:21 bshen Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;

import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.tables.impl.PersistentTableImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASE Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getLockSchema <em>Lock Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getFillFactor <em>Fill Factor</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getMaxRowPerPage <em>Max Row Per Page</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getExpRowSize <em>Exp Row Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getReservePageGap <em>Reserve Page Gap</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getIdentityGap <em>Identity Gap</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getSegment <em>Segment</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getConcurrencyOptThreshold <em>Concurrency Opt Threshold</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getPartitionCondition <em>Partition Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getTableOnlyCacheInfo <em>Table Only Cache Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getTextOnlyCacheInfo <em>Text Only Cache Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getLockPromotion <em>Lock Promotion</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getPartitions <em>Partitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#getTextImageSegment <em>Text Image Segment</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl#isSystemTable <em>System Table</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASETableImpl extends PersistentTableImpl implements SybaseASETable 
{
	/**
     * The default value of the '{@link #getLockSchema() <em>Lock Schema</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLockSchema()
     * @generated
     * @ordered
     */
	protected static final LockingSchemaType LOCK_SCHEMA_EDEFAULT = LockingSchemaType.LOCKDATAROWS_LITERAL;

	/**
     * The cached value of the '{@link #getLockSchema() <em>Lock Schema</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLockSchema()
     * @generated
     * @ordered
     */
	protected LockingSchemaType lockSchema = LOCK_SCHEMA_EDEFAULT;

	/**
     * The default value of the '{@link #getFillFactor() <em>Fill Factor</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFillFactor()
     * @generated
     * @ordered
     */
	protected static final int FILL_FACTOR_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getFillFactor() <em>Fill Factor</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFillFactor()
     * @generated
     * @ordered
     */
	protected int fillFactor = FILL_FACTOR_EDEFAULT;

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
     * The default value of the '{@link #getExpRowSize() <em>Exp Row Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExpRowSize()
     * @generated
     * @ordered
     */
	protected static final int EXP_ROW_SIZE_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getExpRowSize() <em>Exp Row Size</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExpRowSize()
     * @generated
     * @ordered
     */
	protected int expRowSize = EXP_ROW_SIZE_EDEFAULT;

	/**
     * The default value of the '{@link #getReservePageGap() <em>Reserve Page Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReservePageGap()
     * @generated
     * @ordered
     */
	protected static final int RESERVE_PAGE_GAP_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getReservePageGap() <em>Reserve Page Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getReservePageGap()
     * @generated
     * @ordered
     */
	protected int reservePageGap = RESERVE_PAGE_GAP_EDEFAULT;

	/**
     * The default value of the '{@link #getIdentityGap() <em>Identity Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIdentityGap()
     * @generated
     * @ordered
     */
	protected static final int IDENTITY_GAP_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getIdentityGap() <em>Identity Gap</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIdentityGap()
     * @generated
     * @ordered
     */
	protected int identityGap = IDENTITY_GAP_EDEFAULT;

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
     * The default value of the '{@link #getConcurrencyOptThreshold() <em>Concurrency Opt Threshold</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConcurrencyOptThreshold()
     * @generated
     * @ordered
     */
	protected static final int CONCURRENCY_OPT_THRESHOLD_EDEFAULT = 0;

	/**
     * The cached value of the '{@link #getConcurrencyOptThreshold() <em>Concurrency Opt Threshold</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConcurrencyOptThreshold()
     * @generated
     * @ordered
     */
	protected int concurrencyOptThreshold = CONCURRENCY_OPT_THRESHOLD_EDEFAULT;

	/**
     * The cached value of the '{@link #getPartitionCondition() <em>Partition Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPartitionCondition()
     * @generated
     * @ordered
     */
	protected SybaseASEPartition partitionCondition;

	/**
     * The cached value of the '{@link #getTableOnlyCacheInfo() <em>Table Only Cache Info</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTableOnlyCacheInfo()
     * @generated
     * @ordered
     */
	protected CacheInfo tableOnlyCacheInfo;

	/**
     * The cached value of the '{@link #getTextOnlyCacheInfo() <em>Text Only Cache Info</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTextOnlyCacheInfo()
     * @generated
     * @ordered
     */
	protected CacheInfo textOnlyCacheInfo;

	/**
     * The cached value of the '{@link #getLockPromotion() <em>Lock Promotion</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLockPromotion()
     * @generated
     * @ordered
     */
	protected EList lockPromotion;

	/**
     * The default value of the '{@link #getPartitions() <em>Partitions</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPartitions()
     * @generated
     * @ordered
     */
	protected static final int PARTITIONS_EDEFAULT = -1;

	/**
     * The cached value of the '{@link #getPartitions() <em>Partitions</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPartitions()
     * @generated
     * @ordered
     */
	protected int partitions = PARTITIONS_EDEFAULT;

	/**
     * The cached value of the '{@link #getTextImageSegment() <em>Text Image Segment</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTextImageSegment()
     * @generated
     * @ordered
     */
	protected SybaseASESegment textImageSegment;

	/**
     * The default value of the '{@link #isSystemTable() <em>System Table</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSystemTable()
     * @generated
     * @ordered
     */
	protected static final boolean SYSTEM_TABLE_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isSystemTable() <em>System Table</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isSystemTable()
     * @generated
     * @ordered
     */
	protected boolean systemTable = SYSTEM_TABLE_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected SybaseASETableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return SybaseasesqlmodelPackage.Literals.SYBASE_ASE_TABLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public LockingSchemaType getLockSchema() {
        return lockSchema;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLockSchema(LockingSchemaType newLockSchema) {
        LockingSchemaType oldLockSchema = lockSchema;
        lockSchema = newLockSchema == null ? LOCK_SCHEMA_EDEFAULT : newLockSchema;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_SCHEMA, oldLockSchema, lockSchema));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getFillFactor() {
        return fillFactor;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setFillFactor(int newFillFactor) {
        int oldFillFactor = fillFactor;
        fillFactor = newFillFactor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__FILL_FACTOR, oldFillFactor, fillFactor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE, oldMaxRowPerPage, maxRowPerPage));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getExpRowSize() {
        return expRowSize;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setExpRowSize(int newExpRowSize) {
        int oldExpRowSize = expRowSize;
        expRowSize = newExpRowSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__EXP_ROW_SIZE, oldExpRowSize, expRowSize));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getReservePageGap() {
        return reservePageGap;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setReservePageGap(int newReservePageGap) {
        int oldReservePageGap = reservePageGap;
        reservePageGap = newReservePageGap;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__RESERVE_PAGE_GAP, oldReservePageGap, reservePageGap));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getIdentityGap() {
        return identityGap;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setIdentityGap(int newIdentityGap) {
        int oldIdentityGap = identityGap;
        identityGap = newIdentityGap;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__IDENTITY_GAP, oldIdentityGap, identityGap));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT, oldSegment, segment));
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
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT, oldSegment, segment));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getConcurrencyOptThreshold() {
        return concurrencyOptThreshold;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setConcurrencyOptThreshold(int newConcurrencyOptThreshold) {
        int oldConcurrencyOptThreshold = concurrencyOptThreshold;
        concurrencyOptThreshold = newConcurrencyOptThreshold;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD, oldConcurrencyOptThreshold, concurrencyOptThreshold));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASEPartition getPartitionCondition() {
        return partitionCondition;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPartitionCondition(SybaseASEPartition newPartitionCondition, NotificationChain msgs) {
        SybaseASEPartition oldPartitionCondition = partitionCondition;
        partitionCondition = newPartitionCondition;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION, oldPartitionCondition, newPartitionCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPartitionCondition(SybaseASEPartition newPartitionCondition) {
        if (newPartitionCondition != partitionCondition)
        {
            NotificationChain msgs = null;
            if (partitionCondition != null)
                msgs = ((InternalEObject)partitionCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION, null, msgs);
            if (newPartitionCondition != null)
                msgs = ((InternalEObject)newPartitionCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION, null, msgs);
            msgs = basicSetPartitionCondition(newPartitionCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION, newPartitionCondition, newPartitionCondition));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CacheInfo getTableOnlyCacheInfo() {
        return tableOnlyCacheInfo;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTableOnlyCacheInfo(CacheInfo newTableOnlyCacheInfo, NotificationChain msgs) {
        CacheInfo oldTableOnlyCacheInfo = tableOnlyCacheInfo;
        tableOnlyCacheInfo = newTableOnlyCacheInfo;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO, oldTableOnlyCacheInfo, newTableOnlyCacheInfo);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTableOnlyCacheInfo(CacheInfo newTableOnlyCacheInfo) {
        if (newTableOnlyCacheInfo != tableOnlyCacheInfo)
        {
            NotificationChain msgs = null;
            if (tableOnlyCacheInfo != null)
                msgs = ((InternalEObject)tableOnlyCacheInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO, null, msgs);
            if (newTableOnlyCacheInfo != null)
                msgs = ((InternalEObject)newTableOnlyCacheInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO, null, msgs);
            msgs = basicSetTableOnlyCacheInfo(newTableOnlyCacheInfo, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO, newTableOnlyCacheInfo, newTableOnlyCacheInfo));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CacheInfo getTextOnlyCacheInfo() {
        return textOnlyCacheInfo;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTextOnlyCacheInfo(CacheInfo newTextOnlyCacheInfo, NotificationChain msgs) {
        CacheInfo oldTextOnlyCacheInfo = textOnlyCacheInfo;
        textOnlyCacheInfo = newTextOnlyCacheInfo;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO, oldTextOnlyCacheInfo, newTextOnlyCacheInfo);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTextOnlyCacheInfo(CacheInfo newTextOnlyCacheInfo) {
        if (newTextOnlyCacheInfo != textOnlyCacheInfo)
        {
            NotificationChain msgs = null;
            if (textOnlyCacheInfo != null)
                msgs = ((InternalEObject)textOnlyCacheInfo).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO, null, msgs);
            if (newTextOnlyCacheInfo != null)
                msgs = ((InternalEObject)newTextOnlyCacheInfo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO, null, msgs);
            msgs = basicSetTextOnlyCacheInfo(newTextOnlyCacheInfo, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO, newTextOnlyCacheInfo, newTextOnlyCacheInfo));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getLockPromotion() {
        if (lockPromotion == null)
        {
            lockPromotion = new EObjectContainmentEList(LockPromotionInfo.class, this, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION);
        }
        return lockPromotion;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int getPartitions() {
        return partitions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPartitions(int newPartitions) {
        int oldPartitions = partitions;
        partitions = newPartitions;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITIONS, oldPartitions, partitions));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASESegment getTextImageSegment() {
        if (textImageSegment != null && textImageSegment.eIsProxy())
        {
            InternalEObject oldTextImageSegment = (InternalEObject)textImageSegment;
            textImageSegment = (SybaseASESegment)eResolveProxy(oldTextImageSegment);
            if (textImageSegment != oldTextImageSegment)
            {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT, oldTextImageSegment, textImageSegment));
            }
        }
        return textImageSegment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASESegment basicGetTextImageSegment() {
        return textImageSegment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTextImageSegment(SybaseASESegment newTextImageSegment) {
        SybaseASESegment oldTextImageSegment = textImageSegment;
        textImageSegment = newTextImageSegment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT, oldTextImageSegment, textImageSegment));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isSystemTable() {
        return systemTable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSystemTable(boolean newSystemTable) {
        boolean oldSystemTable = systemTable;
        systemTable = newSystemTable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SYSTEM_TABLE, oldSystemTable, systemTable));
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public List getCheckConstraints() {
		List result = new ArrayList();
		EList tableConstraints = getConstraints();
		for(int i = 0; i<tableConstraints.size(); i++)
		{
			Object constr = tableConstraints.get(i);
			if(constr instanceof CheckConstraint)
			{
				result.add(constr);
			}
		}
		return result;
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public boolean isSystem()
    {
        return isSystemTable();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION:
                return basicSetPartitionCondition(null, msgs);
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO:
                return basicSetTableOnlyCacheInfo(null, msgs);
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO:
                return basicSetTextOnlyCacheInfo(null, msgs);
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION:
                return ((InternalEList)getLockPromotion()).basicRemove(otherEnd, msgs);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_SCHEMA:
                return getLockSchema();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__FILL_FACTOR:
                return new Integer(getFillFactor());
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE:
                return new Integer(getMaxRowPerPage());
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__EXP_ROW_SIZE:
                return new Integer(getExpRowSize());
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__RESERVE_PAGE_GAP:
                return new Integer(getReservePageGap());
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__IDENTITY_GAP:
                return new Integer(getIdentityGap());
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT:
                if (resolve) return getSegment();
                return basicGetSegment();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD:
                return new Integer(getConcurrencyOptThreshold());
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION:
                return getPartitionCondition();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO:
                return getTableOnlyCacheInfo();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO:
                return getTextOnlyCacheInfo();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION:
                return getLockPromotion();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITIONS:
                return new Integer(getPartitions());
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT:
                if (resolve) return getTextImageSegment();
                return basicGetTextImageSegment();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SYSTEM_TABLE:
                return isSystemTable() ? Boolean.TRUE : Boolean.FALSE;
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_SCHEMA:
                setLockSchema((LockingSchemaType)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__FILL_FACTOR:
                setFillFactor(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE:
                setMaxRowPerPage(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__EXP_ROW_SIZE:
                setExpRowSize(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__RESERVE_PAGE_GAP:
                setReservePageGap(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__IDENTITY_GAP:
                setIdentityGap(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT:
                setSegment((SybaseASESegment)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD:
                setConcurrencyOptThreshold(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION:
                setPartitionCondition((SybaseASEPartition)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO:
                setTableOnlyCacheInfo((CacheInfo)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO:
                setTextOnlyCacheInfo((CacheInfo)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION:
                getLockPromotion().clear();
                getLockPromotion().addAll((Collection)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITIONS:
                setPartitions(((Integer)newValue).intValue());
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT:
                setTextImageSegment((SybaseASESegment)newValue);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SYSTEM_TABLE:
                setSystemTable(((Boolean)newValue).booleanValue());
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_SCHEMA:
                setLockSchema(LOCK_SCHEMA_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__FILL_FACTOR:
                setFillFactor(FILL_FACTOR_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE:
                setMaxRowPerPage(MAX_ROW_PER_PAGE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__EXP_ROW_SIZE:
                setExpRowSize(EXP_ROW_SIZE_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__RESERVE_PAGE_GAP:
                setReservePageGap(RESERVE_PAGE_GAP_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__IDENTITY_GAP:
                setIdentityGap(IDENTITY_GAP_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT:
                setSegment((SybaseASESegment)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD:
                setConcurrencyOptThreshold(CONCURRENCY_OPT_THRESHOLD_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION:
                setPartitionCondition((SybaseASEPartition)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO:
                setTableOnlyCacheInfo((CacheInfo)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO:
                setTextOnlyCacheInfo((CacheInfo)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION:
                getLockPromotion().clear();
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITIONS:
                setPartitions(PARTITIONS_EDEFAULT);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT:
                setTextImageSegment((SybaseASESegment)null);
                return;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SYSTEM_TABLE:
                setSystemTable(SYSTEM_TABLE_EDEFAULT);
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
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_SCHEMA:
                return lockSchema != LOCK_SCHEMA_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__FILL_FACTOR:
                return fillFactor != FILL_FACTOR_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE:
                return maxRowPerPage != MAX_ROW_PER_PAGE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__EXP_ROW_SIZE:
                return expRowSize != EXP_ROW_SIZE_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__RESERVE_PAGE_GAP:
                return reservePageGap != RESERVE_PAGE_GAP_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__IDENTITY_GAP:
                return identityGap != IDENTITY_GAP_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT:
                return segment != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD:
                return concurrencyOptThreshold != CONCURRENCY_OPT_THRESHOLD_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION:
                return partitionCondition != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO:
                return tableOnlyCacheInfo != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO:
                return textOnlyCacheInfo != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION:
                return lockPromotion != null && !lockPromotion.isEmpty();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITIONS:
                return partitions != PARTITIONS_EDEFAULT;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT:
                return textImageSegment != null;
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SYSTEM_TABLE:
                return systemTable != SYSTEM_TABLE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == SybaseAuthorizedObject.class)
        {
            switch (derivedFeatureID)
            {
                default: return -1;
            }
        }
        if (baseClass == SybaseBaseTable.class)
        {
            switch (derivedFeatureID)
            {
                default: return -1;
            }
        }
        if (baseClass == SybaseASEBaseTable.class)
        {
            switch (derivedFeatureID)
            {
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_SCHEMA: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__LOCK_SCHEMA;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__FILL_FACTOR: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__FILL_FACTOR;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__MAX_ROW_PER_PAGE;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__EXP_ROW_SIZE: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__EXP_ROW_SIZE;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__RESERVE_PAGE_GAP: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__RESERVE_PAGE_GAP;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__IDENTITY_GAP: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__IDENTITY_GAP;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__SEGMENT;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__CONCURRENCY_OPT_THRESHOLD;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__PARTITION_CONDITION;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__TABLE_ONLY_CACHE_INFO;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__TEXT_ONLY_CACHE_INFO;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__LOCK_PROMOTION;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITIONS: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__PARTITIONS;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__TEXT_IMAGE_SEGMENT;
                case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SYSTEM_TABLE: return SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__SYSTEM_TABLE;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
        if (baseClass == SybaseAuthorizedObject.class)
        {
            switch (baseFeatureID)
            {
                default: return -1;
            }
        }
        if (baseClass == SybaseBaseTable.class)
        {
            switch (baseFeatureID)
            {
                default: return -1;
            }
        }
        if (baseClass == SybaseASEBaseTable.class)
        {
            switch (baseFeatureID)
            {
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__LOCK_SCHEMA: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_SCHEMA;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__FILL_FACTOR: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__FILL_FACTOR;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__MAX_ROW_PER_PAGE: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__EXP_ROW_SIZE: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__EXP_ROW_SIZE;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__RESERVE_PAGE_GAP: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__RESERVE_PAGE_GAP;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__IDENTITY_GAP: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__IDENTITY_GAP;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__SEGMENT: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__CONCURRENCY_OPT_THRESHOLD: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__PARTITION_CONDITION: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__TABLE_ONLY_CACHE_INFO: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__TEXT_ONLY_CACHE_INFO: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__LOCK_PROMOTION: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__PARTITIONS: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITIONS;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__TEXT_IMAGE_SEGMENT: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT;
                case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE__SYSTEM_TABLE: return SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SYSTEM_TABLE;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (lockSchema: "); //$NON-NLS-1$
        result.append(lockSchema);
        result.append(", fillFactor: "); //$NON-NLS-1$
        result.append(fillFactor);
        result.append(", maxRowPerPage: "); //$NON-NLS-1$
        result.append(maxRowPerPage);
        result.append(", expRowSize: "); //$NON-NLS-1$
        result.append(expRowSize);
        result.append(", reservePageGap: "); //$NON-NLS-1$
        result.append(reservePageGap);
        result.append(", identityGap: "); //$NON-NLS-1$
        result.append(identityGap);
        result.append(", concurrencyOptThreshold: "); //$NON-NLS-1$
        result.append(concurrencyOptThreshold);
        result.append(", partitions: "); //$NON-NLS-1$
        result.append(partitions);
        result.append(", systemTable: "); //$NON-NLS-1$
        result.append(systemTable);
        result.append(')');
        return result.toString();
    }

} //SybaseASETableImpl
