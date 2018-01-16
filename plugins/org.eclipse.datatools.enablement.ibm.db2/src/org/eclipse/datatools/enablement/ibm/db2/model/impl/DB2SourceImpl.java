/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.routines.impl.SourceImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Source;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Source</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl#getDb2PackageName <em>Db2 Package Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl#getLastModified <em>Last Modified</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl#getSupporting <em>Supporting</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SourceImpl#getPrimary <em>Primary</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2SourceImpl extends SourceImpl implements DB2Source {
	/**
	 * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected String fileName = FILE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageName() <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageName()
	 * @generated
	 * @ordered
	 */
	protected String packageName = PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDb2PackageName() <em>Db2 Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDb2PackageName()
	 * @generated
	 * @ordered
	 */
	protected static final String DB2_PACKAGE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDb2PackageName() <em>Db2 Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDb2PackageName()
	 * @generated
	 * @ordered
	 */
	protected String db2PackageName = DB2_PACKAGE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastModified() <em>Last Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastModified()
	 * @generated
	 * @ordered
	 */
	protected static final String LAST_MODIFIED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastModified() <em>Last Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastModified()
	 * @generated
	 * @ordered
	 */
	protected String lastModified = LAST_MODIFIED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSupporting() <em>Supporting</em>}' containment reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getSupporting()
	 * @generated
	 * @ordered
	 */
   protected EList supporting;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2SourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_SOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileName(String newFileName) {
		String oldFileName = fileName;
		fileName = newFileName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_SOURCE__FILE_NAME, oldFileName, fileName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageName(String newPackageName) {
		String oldPackageName = packageName;
		packageName = newPackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_SOURCE__PACKAGE_NAME, oldPackageName, packageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDb2PackageName() {
		return db2PackageName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDb2PackageName(String newDb2PackageName) {
		String oldDb2PackageName = db2PackageName;
		db2PackageName = newDb2PackageName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_SOURCE__DB2_PACKAGE_NAME, oldDb2PackageName, db2PackageName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLastModified() {
		return lastModified;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastModified(String newLastModified) {
		String oldLastModified = lastModified;
		lastModified = newLastModified;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_SOURCE__LAST_MODIFIED, oldLastModified, lastModified));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList getSupporting() {
		if (supporting == null) {
			supporting = new EObjectContainmentWithInverseEList(DB2Source.class, this, DB2ModelPackage.DB2_SOURCE__SUPPORTING, DB2ModelPackage.DB2_SOURCE__PRIMARY);
		}
		return supporting;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2Source getPrimary() {
		if (eContainerFeatureID() != DB2ModelPackage.DB2_SOURCE__PRIMARY) return null;
		return (DB2Source)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrimary(DB2Source newPrimary, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPrimary, DB2ModelPackage.DB2_SOURCE__PRIMARY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setPrimary(DB2Source newPrimary) {
		if (newPrimary != eInternalContainer() || (eContainerFeatureID() != DB2ModelPackage.DB2_SOURCE__PRIMARY && newPrimary != null)) {
			if (EcoreUtil.isAncestor(this, newPrimary))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPrimary != null)
				msgs = ((InternalEObject)newPrimary).eInverseAdd(this, DB2ModelPackage.DB2_SOURCE__SUPPORTING, DB2Source.class, msgs);
			msgs = basicSetPrimary(newPrimary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_SOURCE__PRIMARY, newPrimary, newPrimary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_SOURCE__SUPPORTING:
				return ((InternalEList)getSupporting()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_SOURCE__PRIMARY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPrimary((DB2Source)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_SOURCE__SUPPORTING:
				return ((InternalEList)getSupporting()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_SOURCE__PRIMARY:
				return basicSetPrimary(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DB2ModelPackage.DB2_SOURCE__PRIMARY:
				return eInternalContainer().eInverseRemove(this, DB2ModelPackage.DB2_SOURCE__SUPPORTING, DB2Source.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DB2ModelPackage.DB2_SOURCE__FILE_NAME:
				return getFileName();
			case DB2ModelPackage.DB2_SOURCE__PACKAGE_NAME:
				return getPackageName();
			case DB2ModelPackage.DB2_SOURCE__DB2_PACKAGE_NAME:
				return getDb2PackageName();
			case DB2ModelPackage.DB2_SOURCE__LAST_MODIFIED:
				return getLastModified();
			case DB2ModelPackage.DB2_SOURCE__SUPPORTING:
				return getSupporting();
			case DB2ModelPackage.DB2_SOURCE__PRIMARY:
				return getPrimary();
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
			case DB2ModelPackage.DB2_SOURCE__FILE_NAME:
				setFileName((String)newValue);
				return;
			case DB2ModelPackage.DB2_SOURCE__PACKAGE_NAME:
				setPackageName((String)newValue);
				return;
			case DB2ModelPackage.DB2_SOURCE__DB2_PACKAGE_NAME:
				setDb2PackageName((String)newValue);
				return;
			case DB2ModelPackage.DB2_SOURCE__LAST_MODIFIED:
				setLastModified((String)newValue);
				return;
			case DB2ModelPackage.DB2_SOURCE__SUPPORTING:
				getSupporting().clear();
				getSupporting().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SOURCE__PRIMARY:
				setPrimary((DB2Source)newValue);
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
			case DB2ModelPackage.DB2_SOURCE__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_SOURCE__PACKAGE_NAME:
				setPackageName(PACKAGE_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_SOURCE__DB2_PACKAGE_NAME:
				setDb2PackageName(DB2_PACKAGE_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_SOURCE__LAST_MODIFIED:
				setLastModified(LAST_MODIFIED_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_SOURCE__SUPPORTING:
				getSupporting().clear();
				return;
			case DB2ModelPackage.DB2_SOURCE__PRIMARY:
				setPrimary((DB2Source)null);
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
			case DB2ModelPackage.DB2_SOURCE__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case DB2ModelPackage.DB2_SOURCE__PACKAGE_NAME:
				return PACKAGE_NAME_EDEFAULT == null ? packageName != null : !PACKAGE_NAME_EDEFAULT.equals(packageName);
			case DB2ModelPackage.DB2_SOURCE__DB2_PACKAGE_NAME:
				return DB2_PACKAGE_NAME_EDEFAULT == null ? db2PackageName != null : !DB2_PACKAGE_NAME_EDEFAULT.equals(db2PackageName);
			case DB2ModelPackage.DB2_SOURCE__LAST_MODIFIED:
				return LAST_MODIFIED_EDEFAULT == null ? lastModified != null : !LAST_MODIFIED_EDEFAULT.equals(lastModified);
			case DB2ModelPackage.DB2_SOURCE__SUPPORTING:
				return supporting != null && !supporting.isEmpty();
			case DB2ModelPackage.DB2_SOURCE__PRIMARY:
				return getPrimary() != null;
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
		result.append(" (fileName: "); //$NON-NLS-1$
		result.append(fileName);
		result.append(", packageName: "); //$NON-NLS-1$
		result.append(packageName);
		result.append(", db2PackageName: "); //$NON-NLS-1$
		result.append(db2PackageName);
		result.append(", lastModified: "); //$NON-NLS-1$
		result.append(lastModified);
		result.append(')');
		return result.toString();
	}

} //DB2SourceImpl
