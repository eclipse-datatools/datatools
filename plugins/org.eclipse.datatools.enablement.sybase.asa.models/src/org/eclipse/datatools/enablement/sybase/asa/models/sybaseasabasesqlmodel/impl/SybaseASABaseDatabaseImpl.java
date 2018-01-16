/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseDatabaseImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import java.util.Collection;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.DatabaseImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sybase ASA Base Database</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getDataTypes <em>Data Types</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getWebServices <em>Web Services</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getDbSpaces <em>Db Spaces</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getDatabaseFileName <em>Database File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getLogFileName <em>Log File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getMirrorFileName <em>Mirror File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#isCaseSensitive <em>Case Sensitive</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getCollation <em>Collation</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#isBlankPaddingOn <em>Blank Padding On</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#isCheckSumOn <em>Check Sum On</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#isJConnectOn <em>JConnect On</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getPageSize <em>Page Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getEncryptionInfo <em>Encryption Info</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getJavaSupport <em>Java Support</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseDatabaseImpl#getPasswordCaseSensitive <em>Password Case Sensitive</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SybaseASABaseDatabaseImpl extends DatabaseImpl implements SybaseASABaseDatabase 
{
    /**
	 * The cached value of the '{@link #getDataTypes() <em>Data Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataTypes()
	 * @generated
	 * @ordered
	 */
	protected EList dataTypes;

    /**
	 * The cached value of the '{@link #getWebServices() <em>Web Services</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebServices()
	 * @generated
	 * @ordered
	 */
	protected EList webServices;

    /**
	 * The cached value of the '{@link #getDbSpaces() <em>Db Spaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDbSpaces()
	 * @generated
	 * @ordered
	 */
	protected EList dbSpaces;

    /**
	 * The default value of the '{@link #getDatabaseFileName() <em>Database File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabaseFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String DATABASE_FILE_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getDatabaseFileName() <em>Database File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatabaseFileName()
	 * @generated
	 * @ordered
	 */
	protected String databaseFileName = DATABASE_FILE_NAME_EDEFAULT;

    /**
	 * The default value of the '{@link #getLogFileName() <em>Log File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String LOG_FILE_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getLogFileName() <em>Log File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogFileName()
	 * @generated
	 * @ordered
	 */
	protected String logFileName = LOG_FILE_NAME_EDEFAULT;

    /**
	 * The default value of the '{@link #getMirrorFileName() <em>Mirror File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMirrorFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String MIRROR_FILE_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getMirrorFileName() <em>Mirror File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMirrorFileName()
	 * @generated
	 * @ordered
	 */
	protected String mirrorFileName = MIRROR_FILE_NAME_EDEFAULT;

    /**
	 * The default value of the '{@link #isCaseSensitive() <em>Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCaseSensitive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CASE_SENSITIVE_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isCaseSensitive() <em>Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCaseSensitive()
	 * @generated
	 * @ordered
	 */
	protected boolean caseSensitive = CASE_SENSITIVE_EDEFAULT;

    /**
	 * The default value of the '{@link #getCollation() <em>Collation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollation()
	 * @generated
	 * @ordered
	 */
	protected static final String COLLATION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getCollation() <em>Collation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollation()
	 * @generated
	 * @ordered
	 */
	protected String collation = COLLATION_EDEFAULT;

    /**
	 * The default value of the '{@link #isBlankPaddingOn() <em>Blank Padding On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBlankPaddingOn()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BLANK_PADDING_ON_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isBlankPaddingOn() <em>Blank Padding On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBlankPaddingOn()
	 * @generated
	 * @ordered
	 */
	protected boolean blankPaddingOn = BLANK_PADDING_ON_EDEFAULT;

    /**
	 * The default value of the '{@link #isCheckSumOn() <em>Check Sum On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCheckSumOn()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CHECK_SUM_ON_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isCheckSumOn() <em>Check Sum On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCheckSumOn()
	 * @generated
	 * @ordered
	 */
	protected boolean checkSumOn = CHECK_SUM_ON_EDEFAULT;

    /**
	 * The default value of the '{@link #isJConnectOn() <em>JConnect On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isJConnectOn()
	 * @generated
	 * @ordered
	 */
	protected static final boolean JCONNECT_ON_EDEFAULT = true;

    /**
	 * The cached value of the '{@link #isJConnectOn() <em>JConnect On</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isJConnectOn()
	 * @generated
	 * @ordered
	 */
	protected boolean jConnectOn = JCONNECT_ON_EDEFAULT;

    /**
	 * The default value of the '{@link #getPageSize() <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSize()
	 * @generated
	 * @ordered
	 */
	protected static final int PAGE_SIZE_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getPageSize() <em>Page Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageSize()
	 * @generated
	 * @ordered
	 */
	protected int pageSize = PAGE_SIZE_EDEFAULT;

    /**
	 * The cached value of the '{@link #getEncryptionInfo() <em>Encryption Info</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncryptionInfo()
	 * @generated
	 * @ordered
	 */
	protected EncryptionInfo encryptionInfo;

    /**
	 * The default value of the '{@link #getJavaSupport() <em>Java Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaSupport()
	 * @generated
	 * @ordered
	 */
	protected static final JavaSupportType JAVA_SUPPORT_EDEFAULT = JavaSupportType.OFF_LITERAL;

    /**
	 * The cached value of the '{@link #getJavaSupport() <em>Java Support</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaSupport()
	 * @generated
	 * @ordered
	 */
	protected JavaSupportType javaSupport = JAVA_SUPPORT_EDEFAULT;

    /**
	 * The default value of the '{@link #getPasswordCaseSensitive() <em>Password Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPasswordCaseSensitive()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PASSWORD_CASE_SENSITIVE_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getPasswordCaseSensitive() <em>Password Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPasswordCaseSensitive()
	 * @generated
	 * @ordered
	 */
	protected Boolean passwordCaseSensitive = PASSWORD_CASE_SENSITIVE_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseASABaseDatabaseImpl()
    {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass()
    {
		return SybaseasabasesqlmodelPackage.Literals.SYBASE_ASA_BASE_DATABASE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDataTypes()
    {
		if (dataTypes == null) {
			dataTypes = new EObjectWithInverseResolvingEList(SybaseASABasePredefinedDataType.class, this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE);
		}
		return dataTypes;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getWebServices()
    {
		if (webServices == null) {
			webServices = new EObjectWithInverseResolvingEList(SybaseASAWebService.class, this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES, SybaseasabasesqlmodelPackage.SYBASE_ASA_WEB_SERVICE__DATABASE);
		}
		return webServices;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDbSpaces()
    {
		if (dbSpaces == null) {
			dbSpaces = new EObjectContainmentWithInverseEList(SybaseASABaseDBSpace.class, this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DB_SPACE__DATABASE);
		}
		return dbSpaces;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDatabaseFileName()
    {
		return databaseFileName;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDatabaseFileName(String newDatabaseFileName)
    {
		String oldDatabaseFileName = databaseFileName;
		databaseFileName = newDatabaseFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME, oldDatabaseFileName, databaseFileName));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLogFileName()
    {
		return logFileName;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLogFileName(String newLogFileName)
    {
		String oldLogFileName = logFileName;
		logFileName = newLogFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME, oldLogFileName, logFileName));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMirrorFileName()
    {
		return mirrorFileName;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMirrorFileName(String newMirrorFileName)
    {
		String oldMirrorFileName = mirrorFileName;
		mirrorFileName = newMirrorFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME, oldMirrorFileName, mirrorFileName));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCaseSensitive()
    {
		return caseSensitive;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCaseSensitive(boolean newCaseSensitive)
    {
		boolean oldCaseSensitive = caseSensitive;
		caseSensitive = newCaseSensitive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE, oldCaseSensitive, caseSensitive));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCollation()
    {
		return collation;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollation(String newCollation)
    {
		String oldCollation = collation;
		collation = newCollation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__COLLATION, oldCollation, collation));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBlankPaddingOn()
    {
		return blankPaddingOn;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlankPaddingOn(boolean newBlankPaddingOn)
    {
		boolean oldBlankPaddingOn = blankPaddingOn;
		blankPaddingOn = newBlankPaddingOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON, oldBlankPaddingOn, blankPaddingOn));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCheckSumOn()
    {
		return checkSumOn;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCheckSumOn(boolean newCheckSumOn)
    {
		boolean oldCheckSumOn = checkSumOn;
		checkSumOn = newCheckSumOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON, oldCheckSumOn, checkSumOn));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isJConnectOn()
    {
		return jConnectOn;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJConnectOn(boolean newJConnectOn)
    {
		boolean oldJConnectOn = jConnectOn;
		jConnectOn = newJConnectOn;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JCONNECT_ON, oldJConnectOn, jConnectOn));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPageSize()
    {
		return pageSize;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPageSize(int newPageSize)
    {
		int oldPageSize = pageSize;
		pageSize = newPageSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PAGE_SIZE, oldPageSize, pageSize));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EncryptionInfo getEncryptionInfo()
    {
		if (encryptionInfo != null && encryptionInfo.eIsProxy()) {
			InternalEObject oldEncryptionInfo = (InternalEObject)encryptionInfo;
			encryptionInfo = (EncryptionInfo)eResolveProxy(oldEncryptionInfo);
			if (encryptionInfo != oldEncryptionInfo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO, oldEncryptionInfo, encryptionInfo));
			}
		}
		return encryptionInfo;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EncryptionInfo basicGetEncryptionInfo()
    {
		return encryptionInfo;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncryptionInfo(EncryptionInfo newEncryptionInfo)
    {
		EncryptionInfo oldEncryptionInfo = encryptionInfo;
		encryptionInfo = newEncryptionInfo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO, oldEncryptionInfo, encryptionInfo));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaSupportType getJavaSupport()
    {
		return javaSupport;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaSupport(JavaSupportType newJavaSupport)
    {
		JavaSupportType oldJavaSupport = javaSupport;
		javaSupport = newJavaSupport == null ? JAVA_SUPPORT_EDEFAULT : newJavaSupport;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT, oldJavaSupport, javaSupport));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getPasswordCaseSensitive()
    {
		return passwordCaseSensitive;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPasswordCaseSensitive(Boolean newPasswordCaseSensitive)
    {
		Boolean oldPasswordCaseSensitive = passwordCaseSensitive;
		passwordCaseSensitive = newPasswordCaseSensitive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE, oldPasswordCaseSensitive, passwordCaseSensitive));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isBaseOnASA10() {
		String version = this.getVersion();
		int index = version.indexOf('.');
		if(index == -1) index = version.length();
        
		try
        {
            int iVer = new Integer(version.substring(0, index)).intValue();
            return iVer == 10;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES:
				return ((InternalEList)getDataTypes()).basicAdd(otherEnd, msgs);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES:
				return ((InternalEList)getWebServices()).basicAdd(otherEnd, msgs);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES:
				return ((InternalEList)getDbSpaces()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES:
				return ((InternalEList)getDataTypes()).basicRemove(otherEnd, msgs);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES:
				return ((InternalEList)getWebServices()).basicRemove(otherEnd, msgs);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES:
				return ((InternalEList)getDbSpaces()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES:
				return getDataTypes();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES:
				return getWebServices();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES:
				return getDbSpaces();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME:
				return getDatabaseFileName();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME:
				return getLogFileName();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME:
				return getMirrorFileName();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE:
				return isCaseSensitive() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__COLLATION:
				return getCollation();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON:
				return isBlankPaddingOn() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON:
				return isCheckSumOn() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JCONNECT_ON:
				return isJConnectOn() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PAGE_SIZE:
				return new Integer(getPageSize());
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO:
				if (resolve) return getEncryptionInfo();
				return basicGetEncryptionInfo();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT:
				return getJavaSupport();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE:
				return getPasswordCaseSensitive();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES:
				getDataTypes().clear();
				getDataTypes().addAll((Collection)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES:
				getWebServices().clear();
				getWebServices().addAll((Collection)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES:
				getDbSpaces().clear();
				getDbSpaces().addAll((Collection)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME:
				setDatabaseFileName((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME:
				setLogFileName((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME:
				setMirrorFileName((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE:
				setCaseSensitive(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__COLLATION:
				setCollation((String)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON:
				setBlankPaddingOn(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON:
				setCheckSumOn(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JCONNECT_ON:
				setJConnectOn(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PAGE_SIZE:
				setPageSize(((Integer)newValue).intValue());
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO:
				setEncryptionInfo((EncryptionInfo)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT:
				setJavaSupport((JavaSupportType)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE:
				setPasswordCaseSensitive((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES:
				getDataTypes().clear();
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES:
				getWebServices().clear();
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES:
				getDbSpaces().clear();
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME:
				setDatabaseFileName(DATABASE_FILE_NAME_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME:
				setLogFileName(LOG_FILE_NAME_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME:
				setMirrorFileName(MIRROR_FILE_NAME_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE:
				setCaseSensitive(CASE_SENSITIVE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__COLLATION:
				setCollation(COLLATION_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON:
				setBlankPaddingOn(BLANK_PADDING_ON_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON:
				setCheckSumOn(CHECK_SUM_ON_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JCONNECT_ON:
				setJConnectOn(JCONNECT_ON_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PAGE_SIZE:
				setPageSize(PAGE_SIZE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO:
				setEncryptionInfo((EncryptionInfo)null);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT:
				setJavaSupport(JAVA_SUPPORT_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE:
				setPasswordCaseSensitive(PASSWORD_CASE_SENSITIVE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES:
				return dataTypes != null && !dataTypes.isEmpty();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES:
				return webServices != null && !webServices.isEmpty();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES:
				return dbSpaces != null && !dbSpaces.isEmpty();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME:
				return DATABASE_FILE_NAME_EDEFAULT == null ? databaseFileName != null : !DATABASE_FILE_NAME_EDEFAULT.equals(databaseFileName);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME:
				return LOG_FILE_NAME_EDEFAULT == null ? logFileName != null : !LOG_FILE_NAME_EDEFAULT.equals(logFileName);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME:
				return MIRROR_FILE_NAME_EDEFAULT == null ? mirrorFileName != null : !MIRROR_FILE_NAME_EDEFAULT.equals(mirrorFileName);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE:
				return caseSensitive != CASE_SENSITIVE_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__COLLATION:
				return COLLATION_EDEFAULT == null ? collation != null : !COLLATION_EDEFAULT.equals(collation);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON:
				return blankPaddingOn != BLANK_PADDING_ON_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON:
				return checkSumOn != CHECK_SUM_ON_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JCONNECT_ON:
				return jConnectOn != JCONNECT_ON_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PAGE_SIZE:
				return pageSize != PAGE_SIZE_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO:
				return encryptionInfo != null;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT:
				return javaSupport != JAVA_SUPPORT_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE:
				return PASSWORD_CASE_SENSITIVE_EDEFAULT == null ? passwordCaseSensitive != null : !PASSWORD_CASE_SENSITIVE_EDEFAULT.equals(passwordCaseSensitive);
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString()
    {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (databaseFileName: ");
		result.append(databaseFileName);
		result.append(", logFileName: ");
		result.append(logFileName);
		result.append(", mirrorFileName: ");
		result.append(mirrorFileName);
		result.append(", caseSensitive: ");
		result.append(caseSensitive);
		result.append(", collation: ");
		result.append(collation);
		result.append(", blankPaddingOn: ");
		result.append(blankPaddingOn);
		result.append(", checkSumOn: ");
		result.append(checkSumOn);
		result.append(", jConnectOn: ");
		result.append(jConnectOn);
		result.append(", pageSize: ");
		result.append(pageSize);
		result.append(", javaSupport: ");
		result.append(javaSupport);
		result.append(", passwordCaseSensitive: ");
		result.append(passwordCaseSensitive);
		result.append(')');
		return result.toString();
	}
	
	 /**
     * @NOT generated
     */
    public EList getDatabaseSchemas()
    {
        return super.getSchemas();
    }

} //SybaseASABaseDatabaseImpl