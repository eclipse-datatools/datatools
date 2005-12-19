/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryObjectImpl.java,v 1.2 2005/12/17 01:46:20 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryPackage;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo;
import org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceWriter;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class SQLQueryObjectImpl extends SQLObjectImpl implements SQLQueryObject {
    private SQLQuerySourceInfo sourceInfo = null;
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected SQLQueryObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return SQLQueryPackage.eINSTANCE.getSQLQueryObject();
	}

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String getSQL() {
        SQLQuerySourceWriter sw = new SQLQuerySourceWriter();
        return sw.getSQL(this);
    }

	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setSQL(String sqlText) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.SQL_QUERY_OBJECT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case SQLQueryPackage.SQL_QUERY_OBJECT__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case SQLQueryPackage.SQL_QUERY_OBJECT__DEPENDENCIES:
					return ((InternalEList)getDependencies()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.SQL_QUERY_OBJECT__EANNOTATIONS:
				return getEAnnotations();
			case SQLQueryPackage.SQL_QUERY_OBJECT__NAME:
				return getName();
			case SQLQueryPackage.SQL_QUERY_OBJECT__DEPENDENCIES:
				return getDependencies();
			case SQLQueryPackage.SQL_QUERY_OBJECT__DESCRIPTION:
				return getDescription();
			case SQLQueryPackage.SQL_QUERY_OBJECT__LABEL:
				return getLabel();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.SQL_QUERY_OBJECT__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SQL_QUERY_OBJECT__NAME:
				setName((String)newValue);
				return;
			case SQLQueryPackage.SQL_QUERY_OBJECT__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection)newValue);
				return;
			case SQLQueryPackage.SQL_QUERY_OBJECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case SQLQueryPackage.SQL_QUERY_OBJECT__LABEL:
				setLabel((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.SQL_QUERY_OBJECT__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case SQLQueryPackage.SQL_QUERY_OBJECT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SQLQueryPackage.SQL_QUERY_OBJECT__DEPENDENCIES:
				getDependencies().clear();
				return;
			case SQLQueryPackage.SQL_QUERY_OBJECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case SQLQueryPackage.SQL_QUERY_OBJECT__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case SQLQueryPackage.SQL_QUERY_OBJECT__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case SQLQueryPackage.SQL_QUERY_OBJECT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SQLQueryPackage.SQL_QUERY_OBJECT__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case SQLQueryPackage.SQL_QUERY_OBJECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case SQLQueryPackage.SQL_QUERY_OBJECT__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
		}
		return eDynamicIsSet(eFeature);
	}

    /**
     * @inheritDoc org.eclipse.datatools.modelbase.sql.query.SQLQueryObject#getSourceInfo()
     * @generated NOT
     */
    public SQLQuerySourceInfo getSourceInfo()
    {
        if (this.sourceInfo == null)
        {
            this.sourceInfo = new SQLQuerySourceInfo(this);
        } 
        else if (this.sourceInfo.getQueryObjectBackReference() != this)
        {
            // the weak reference might have been garbage collected, but it might be needed
            this.sourceInfo.setQueryObjectBackReference(this);
        }
        
        return this.sourceInfo;
    }


    /**
     * @param sourceInfo The sourceInfo to set.
     * @generated NOT
     */
    public void setSourceInfo(SQLQuerySourceInfo sourceInfo)
    {
        this.sourceInfo = sourceInfo;
        if (sourceInfo != null)
        {
            this.sourceInfo.setQueryObjectBackReference(this);
        }
    }
    
} //SQLQueryObjectImpl
