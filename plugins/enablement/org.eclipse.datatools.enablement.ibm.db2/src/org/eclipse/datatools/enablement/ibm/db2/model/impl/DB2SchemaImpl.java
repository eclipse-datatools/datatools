/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2AccessPlan;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Mask;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2OLAPObject;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Package;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Permission;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XSRObject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getAccessPlans <em>Access Plans</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getOlapObjects <em>Olap Objects</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getJars <em>Jars</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getXsrObjects <em>Xsr Objects</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getPackages <em>Packages</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getMasks <em>Masks</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getModules <em>Modules</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2SchemaImpl#getGlobalVariables <em>Global Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2SchemaImpl extends SchemaImpl implements DB2Schema {
	/**
	 * The cached value of the '{@link #getAccessPlans() <em>Access Plans</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessPlans()
	 * @generated
	 * @ordered
	 */
	protected EList accessPlans;

	/**
	 * The cached value of the '{@link #getOlapObjects() <em>Olap Objects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOlapObjects()
	 * @generated
	 * @ordered
	 */
	protected EList olapObjects;

	/**
	 * The cached value of the '{@link #getJars() <em>Jars</em>}' reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getJars()
	 * @generated
	 * @ordered
	 */
   protected EList jars;

	/**
	 * The cached value of the '{@link #getXsrObjects() <em>Xsr Objects</em>}' reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getXsrObjects()
	 * @generated
	 * @ordered
	 */
   protected EList xsrObjects;

	/**
	 * The cached value of the '{@link #getPackages() <em>Packages</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackages()
	 * @generated
	 * @ordered
	 */
	protected EList packages;

	/**
	 * The cached value of the '{@link #getMasks() <em>Masks</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMasks()
	 * @generated
	 * @ordered
	 */
	protected EList masks;

	/**
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList permissions;

	/**
	 * The cached value of the '{@link #getModules() <em>Modules</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModules()
	 * @generated
	 * @ordered
	 */
	protected EList modules;

	/**
	 * The cached value of the '{@link #getGlobalVariables() <em>Global Variables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGlobalVariables()
	 * @generated
	 * @ordered
	 */
	protected EList globalVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DB2SchemaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_SCHEMA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAccessPlans() {
		if (accessPlans == null) {
			accessPlans = new EObjectResolvingEList(DB2AccessPlan.class, this, DB2ModelPackage.DB2_SCHEMA__ACCESS_PLANS);
		}
		return accessPlans;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOlapObjects() {
		if (olapObjects == null) {
			olapObjects = new EObjectWithInverseResolvingEList(DB2OLAPObject.class, this, DB2ModelPackage.DB2_SCHEMA__OLAP_OBJECTS, DB2ModelPackage.DB2OLAP_OBJECT__SCHEMA);
		}
		return olapObjects;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList getJars() {
		if (jars == null) {
			jars = new EObjectWithInverseResolvingEList(DB2Jar.class, this, DB2ModelPackage.DB2_SCHEMA__JARS, DB2ModelPackage.DB2_JAR__SCHEMA);
		}
		return jars;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList getXsrObjects() {
		if (xsrObjects == null) {
			xsrObjects = new EObjectWithInverseResolvingEList(DB2XSRObject.class, this, DB2ModelPackage.DB2_SCHEMA__XSR_OBJECTS, DB2ModelPackage.DB2XSR_OBJECT__SCHEMA);
		}
		return xsrObjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPackages() {
		if (packages == null) {
			packages = new EObjectWithInverseResolvingEList(DB2Package.class, this, DB2ModelPackage.DB2_SCHEMA__PACKAGES, DB2ModelPackage.DB2_PACKAGE__SCHEMA);
		}
		return packages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMasks() {
		if (masks == null) {
			masks = new EObjectWithInverseResolvingEList(DB2Mask.class, this, DB2ModelPackage.DB2_SCHEMA__MASKS, DB2ModelPackage.DB2_MASK__SCHEMA);
		}
		return masks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPermissions() {
		if (permissions == null) {
			permissions = new EObjectWithInverseResolvingEList(DB2Permission.class, this, DB2ModelPackage.DB2_SCHEMA__PERMISSIONS, DB2ModelPackage.DB2_PERMISSION__SCHEMA);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getModules() {
//		if (modules == null) {
//			modules = new EObjectWithInverseResolvingEList(LUWModule.class, this, DB2ModelPackage.DB2_SCHEMA__MODULES, LUWPackage.LUW_MODULE__OWNING_SCHEMA);
//		}
		return modules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getGlobalVariables() {
//		if (globalVariables == null) {
//			globalVariables = new EObjectWithInverseResolvingEList(LUWGlobalVariable.class, this, DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES, LUWPackage.LUW_GLOBAL_VARIABLE__SCHEMA);
//		}
		return globalVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_SCHEMA__OLAP_OBJECTS:
				return ((InternalEList)getOlapObjects()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__JARS:
				return ((InternalEList)getJars()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__XSR_OBJECTS:
				return ((InternalEList)getXsrObjects()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__PACKAGES:
				return ((InternalEList)getPackages()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__MASKS:
				return ((InternalEList)getMasks()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__PERMISSIONS:
				return ((InternalEList)getPermissions()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__MODULES:
				return ((InternalEList)getModules()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES:
				return ((InternalEList)getGlobalVariables()).basicAdd(otherEnd, msgs);
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
			case DB2ModelPackage.DB2_SCHEMA__OLAP_OBJECTS:
				return ((InternalEList)getOlapObjects()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__JARS:
				return ((InternalEList)getJars()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__XSR_OBJECTS:
				return ((InternalEList)getXsrObjects()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__PACKAGES:
				return ((InternalEList)getPackages()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__MASKS:
				return ((InternalEList)getMasks()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__PERMISSIONS:
				return ((InternalEList)getPermissions()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__MODULES:
				return ((InternalEList)getModules()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES:
				return ((InternalEList)getGlobalVariables()).basicRemove(otherEnd, msgs);
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
			case DB2ModelPackage.DB2_SCHEMA__ACCESS_PLANS:
				return getAccessPlans();
			case DB2ModelPackage.DB2_SCHEMA__OLAP_OBJECTS:
				return getOlapObjects();
			case DB2ModelPackage.DB2_SCHEMA__JARS:
				return getJars();
			case DB2ModelPackage.DB2_SCHEMA__XSR_OBJECTS:
				return getXsrObjects();
			case DB2ModelPackage.DB2_SCHEMA__PACKAGES:
				return getPackages();
			case DB2ModelPackage.DB2_SCHEMA__MASKS:
				return getMasks();
			case DB2ModelPackage.DB2_SCHEMA__PERMISSIONS:
				return getPermissions();
			case DB2ModelPackage.DB2_SCHEMA__MODULES:
				return getModules();
			case DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES:
				return getGlobalVariables();
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
			case DB2ModelPackage.DB2_SCHEMA__ACCESS_PLANS:
				getAccessPlans().clear();
				getAccessPlans().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SCHEMA__OLAP_OBJECTS:
				getOlapObjects().clear();
				getOlapObjects().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SCHEMA__JARS:
				getJars().clear();
				getJars().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SCHEMA__XSR_OBJECTS:
				getXsrObjects().clear();
				getXsrObjects().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SCHEMA__PACKAGES:
				getPackages().clear();
				getPackages().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SCHEMA__MASKS:
				getMasks().clear();
				getMasks().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SCHEMA__PERMISSIONS:
				getPermissions().clear();
				getPermissions().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SCHEMA__MODULES:
				getModules().clear();
				getModules().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES:
				getGlobalVariables().clear();
				getGlobalVariables().addAll((Collection)newValue);
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
			case DB2ModelPackage.DB2_SCHEMA__ACCESS_PLANS:
				getAccessPlans().clear();
				return;
			case DB2ModelPackage.DB2_SCHEMA__OLAP_OBJECTS:
				getOlapObjects().clear();
				return;
			case DB2ModelPackage.DB2_SCHEMA__JARS:
				getJars().clear();
				return;
			case DB2ModelPackage.DB2_SCHEMA__XSR_OBJECTS:
				getXsrObjects().clear();
				return;
			case DB2ModelPackage.DB2_SCHEMA__PACKAGES:
				getPackages().clear();
				return;
			case DB2ModelPackage.DB2_SCHEMA__MASKS:
				getMasks().clear();
				return;
			case DB2ModelPackage.DB2_SCHEMA__PERMISSIONS:
				getPermissions().clear();
				return;
			case DB2ModelPackage.DB2_SCHEMA__MODULES:
				getModules().clear();
				return;
			case DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES:
				getGlobalVariables().clear();
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
			case DB2ModelPackage.DB2_SCHEMA__ACCESS_PLANS:
				return accessPlans != null && !accessPlans.isEmpty();
			case DB2ModelPackage.DB2_SCHEMA__OLAP_OBJECTS:
				return olapObjects != null && !olapObjects.isEmpty();
			case DB2ModelPackage.DB2_SCHEMA__JARS:
				return jars != null && !jars.isEmpty();
			case DB2ModelPackage.DB2_SCHEMA__XSR_OBJECTS:
				return xsrObjects != null && !xsrObjects.isEmpty();
			case DB2ModelPackage.DB2_SCHEMA__PACKAGES:
				return packages != null && !packages.isEmpty();
			case DB2ModelPackage.DB2_SCHEMA__MASKS:
				return masks != null && !masks.isEmpty();
			case DB2ModelPackage.DB2_SCHEMA__PERMISSIONS:
				return permissions != null && !permissions.isEmpty();
			case DB2ModelPackage.DB2_SCHEMA__MODULES:
				return modules != null && !modules.isEmpty();
			case DB2ModelPackage.DB2_SCHEMA__GLOBAL_VARIABLES:
				return globalVariables != null && !globalVariables.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DB2SchemaImpl
