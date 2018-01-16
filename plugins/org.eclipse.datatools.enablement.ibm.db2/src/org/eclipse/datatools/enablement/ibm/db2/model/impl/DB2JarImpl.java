/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2JarImpl.java,v 1.9 2008/02/05 02:01:23 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2JavaOptions;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Schema;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>DB2 Jar</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl#getOwner <em>Owner</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl#getCreatedTS <em>Created TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl#getAlteredTS <em>Altered TS</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl#getProcedures <em>Procedures</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.model.impl.DB2JarImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DB2JarImpl extends SQLObjectImpl implements DB2Jar {
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
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
   protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
   protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getOwner() <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
   protected static final String OWNER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOwner() <em>Owner</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getOwner()
	 * @generated
	 * @ordered
	 */
   protected String owner = OWNER_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreatedTS() <em>Created TS</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getCreatedTS()
	 * @generated
	 * @ordered
	 */
   protected static final String CREATED_TS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreatedTS() <em>Created TS</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getCreatedTS()
	 * @generated
	 * @ordered
	 */
   protected String createdTS = CREATED_TS_EDEFAULT;

	/**
	 * The default value of the '{@link #getAlteredTS() <em>Altered TS</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getAlteredTS()
	 * @generated
	 * @ordered
	 */
   protected static final String ALTERED_TS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlteredTS() <em>Altered TS</em>}' attribute.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getAlteredTS()
	 * @generated
	 * @ordered
	 */
   protected String alteredTS = ALTERED_TS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProcedures() <em>Procedures</em>}' reference list.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getProcedures()
	 * @generated
	 * @ordered
	 */
   protected EList procedures;

	/**
	 * The cached value of the '{@link #getSchema() <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @see #getSchema()
	 * @generated
	 * @ordered
	 */
   protected DB2Schema schema;

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected DB2JarImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   protected EClass eStaticClass() {
		return DB2ModelPackage.Literals.DB2_JAR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAR__FILE_NAME, oldFileName, fileName));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAR__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getOwner() {
		return owner;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setOwner(String newOwner) {
		String oldOwner = owner;
		owner = newOwner;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAR__OWNER, oldOwner, owner));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getCreatedTS() {
		return createdTS;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setCreatedTS(String newCreatedTS) {
		String oldCreatedTS = createdTS;
		createdTS = newCreatedTS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAR__CREATED_TS, oldCreatedTS, createdTS));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public String getAlteredTS() {
		return alteredTS;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setAlteredTS(String newAlteredTS) {
		String oldAlteredTS = alteredTS;
		alteredTS = newAlteredTS;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAR__ALTERED_TS, oldAlteredTS, alteredTS));
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public EList getProcedures() {
		if (procedures == null) {
			procedures = new EObjectWithInverseResolvingEList(DB2JavaOptions.class, this, DB2ModelPackage.DB2_JAR__PROCEDURES, DB2ModelPackage.DB2_JAVA_OPTIONS__JAR);
		}
		return procedures;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2Schema getSchema() {
		if (schema != null && schema.eIsProxy()) {
			InternalEObject oldSchema = (InternalEObject)schema;
			schema = (DB2Schema)eResolveProxy(oldSchema);
			if (schema != oldSchema) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DB2ModelPackage.DB2_JAR__SCHEMA, oldSchema, schema));
			}
		}
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public DB2Schema basicGetSchema() {
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public NotificationChain basicSetSchema(DB2Schema newSchema, NotificationChain msgs) {
		DB2Schema oldSchema = schema;
		schema = newSchema;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAR__SCHEMA, oldSchema, newSchema);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
	 * @generated
	 */
   public void setSchema(DB2Schema newSchema) {
		if (newSchema != schema) {
			NotificationChain msgs = null;
			if (schema != null)
				msgs = ((InternalEObject)schema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__JARS, DB2Schema.class, msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, DB2ModelPackage.DB2_SCHEMA__JARS, DB2Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DB2ModelPackage.DB2_JAR__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DB2ModelPackage.DB2_JAR__PROCEDURES:
				return ((InternalEList)getProcedures()).basicAdd(otherEnd, msgs);
			case DB2ModelPackage.DB2_JAR__SCHEMA:
				if (schema != null)
					msgs = ((InternalEObject)schema).eInverseRemove(this, DB2ModelPackage.DB2_SCHEMA__JARS, DB2Schema.class, msgs);
				return basicSetSchema((DB2Schema)otherEnd, msgs);
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
			case DB2ModelPackage.DB2_JAR__PROCEDURES:
				return ((InternalEList)getProcedures()).basicRemove(otherEnd, msgs);
			case DB2ModelPackage.DB2_JAR__SCHEMA:
				return basicSetSchema(null, msgs);
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
			case DB2ModelPackage.DB2_JAR__FILE_NAME:
				return getFileName();
			case DB2ModelPackage.DB2_JAR__PATH:
				return getPath();
			case DB2ModelPackage.DB2_JAR__OWNER:
				return getOwner();
			case DB2ModelPackage.DB2_JAR__CREATED_TS:
				return getCreatedTS();
			case DB2ModelPackage.DB2_JAR__ALTERED_TS:
				return getAlteredTS();
			case DB2ModelPackage.DB2_JAR__PROCEDURES:
				return getProcedures();
			case DB2ModelPackage.DB2_JAR__SCHEMA:
				if (resolve) return getSchema();
				return basicGetSchema();
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
			case DB2ModelPackage.DB2_JAR__FILE_NAME:
				setFileName((String)newValue);
				return;
			case DB2ModelPackage.DB2_JAR__PATH:
				setPath((String)newValue);
				return;
			case DB2ModelPackage.DB2_JAR__OWNER:
				setOwner((String)newValue);
				return;
			case DB2ModelPackage.DB2_JAR__CREATED_TS:
				setCreatedTS((String)newValue);
				return;
			case DB2ModelPackage.DB2_JAR__ALTERED_TS:
				setAlteredTS((String)newValue);
				return;
			case DB2ModelPackage.DB2_JAR__PROCEDURES:
				getProcedures().clear();
				getProcedures().addAll((Collection)newValue);
				return;
			case DB2ModelPackage.DB2_JAR__SCHEMA:
				setSchema((DB2Schema)newValue);
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
			case DB2ModelPackage.DB2_JAR__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_JAR__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_JAR__OWNER:
				setOwner(OWNER_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_JAR__CREATED_TS:
				setCreatedTS(CREATED_TS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_JAR__ALTERED_TS:
				setAlteredTS(ALTERED_TS_EDEFAULT);
				return;
			case DB2ModelPackage.DB2_JAR__PROCEDURES:
				getProcedures().clear();
				return;
			case DB2ModelPackage.DB2_JAR__SCHEMA:
				setSchema((DB2Schema)null);
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
			case DB2ModelPackage.DB2_JAR__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
			case DB2ModelPackage.DB2_JAR__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case DB2ModelPackage.DB2_JAR__OWNER:
				return OWNER_EDEFAULT == null ? owner != null : !OWNER_EDEFAULT.equals(owner);
			case DB2ModelPackage.DB2_JAR__CREATED_TS:
				return CREATED_TS_EDEFAULT == null ? createdTS != null : !CREATED_TS_EDEFAULT.equals(createdTS);
			case DB2ModelPackage.DB2_JAR__ALTERED_TS:
				return ALTERED_TS_EDEFAULT == null ? alteredTS != null : !ALTERED_TS_EDEFAULT.equals(alteredTS);
			case DB2ModelPackage.DB2_JAR__PROCEDURES:
				return procedures != null && !procedures.isEmpty();
			case DB2ModelPackage.DB2_JAR__SCHEMA:
				return schema != null;
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
		result.append(", path: "); //$NON-NLS-1$
		result.append(path);
		result.append(", owner: "); //$NON-NLS-1$
		result.append(owner);
		result.append(", createdTS: "); //$NON-NLS-1$
		result.append(createdTS);
		result.append(", alteredTS: "); //$NON-NLS-1$
		result.append(alteredTS);
		result.append(')');
		return result.toString();
	}

} //DB2JarImpl
