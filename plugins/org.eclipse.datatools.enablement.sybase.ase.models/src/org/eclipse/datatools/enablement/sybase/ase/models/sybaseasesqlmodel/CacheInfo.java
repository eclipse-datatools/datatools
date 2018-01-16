/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cache Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo#getCacheStrategy <em>Cache Strategy</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo#getCache <em>Cache</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getCacheInfo()
 * @model
 * @generated
 */
public interface CacheInfo extends SQLObject
{
    /**
     * Returns the value of the '<em><b>Cache Strategy</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cache Strategy</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Cache Strategy</em>' attribute.
     * @see #setCacheStrategy(int)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getCacheInfo_CacheStrategy()
     * @model
     * @generated
     */
    int getCacheStrategy();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo#getCacheStrategy <em>Cache Strategy</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cache Strategy</em>' attribute.
     * @see #getCacheStrategy()
     * @generated
     */
    void setCacheStrategy(int value);

    /**
     * Returns the value of the '<em><b>Cache</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cache</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Cache</em>' reference.
     * @see #setCache(SybaseASECache)
     * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#getCacheInfo_Cache()
     * @model
     * @generated
     */
    SybaseASECache getCache();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo#getCache <em>Cache</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cache</em>' reference.
     * @see #getCache()
     * @generated
     */
    void setCache(SybaseASECache value);

} // CacheInfo
