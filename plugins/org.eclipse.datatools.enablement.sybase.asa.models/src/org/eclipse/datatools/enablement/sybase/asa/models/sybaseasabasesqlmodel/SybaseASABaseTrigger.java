/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseTrigger.java,v 1.3 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.tables.Trigger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The super actionTime attribute is deprecated and instead of sybaseASABaseActionTime.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getOrder <em>Order</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getSybaseASABaseActionTime <em>Sybase ASA Base Action Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getRemoteName <em>Remote Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#isUpdateColumnType <em>Update Column Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseTrigger()
 * @model
 * @generated
 */
public interface SybaseASABaseTrigger extends Trigger
{
    /**
	 * Returns the value of the '<em><b>Order</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order</em>' attribute.
	 * @see #setOrder(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseTrigger_Order()
	 * @model default="1"
	 * @generated
	 */
	int getOrder();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getOrder <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order</em>' attribute.
	 * @see #getOrder()
	 * @generated
	 */
	void setOrder(int value);

    /**
	 * Returns the value of the '<em><b>Sybase ASA Base Action Time</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sybase ASA Base Action Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sybase ASA Base Action Time</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime
	 * @see #setSybaseASABaseActionTime(SybaseASABaseActionTime)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseTrigger_SybaseASABaseActionTime()
	 * @model
	 * @generated
	 */
	SybaseASABaseActionTime getSybaseASABaseActionTime();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getSybaseASABaseActionTime <em>Sybase ASA Base Action Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sybase ASA Base Action Time</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime
	 * @see #getSybaseASABaseActionTime()
	 * @generated
	 */
	void setSybaseASABaseActionTime(SybaseASABaseActionTime value);

    /**
	 * Returns the value of the '<em><b>Remote Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remote Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remote Name</em>' attribute.
	 * @see #setRemoteName(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseTrigger_RemoteName()
	 * @model
	 * @generated
	 */
	String getRemoteName();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#getRemoteName <em>Remote Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remote Name</em>' attribute.
	 * @see #getRemoteName()
	 * @generated
	 */
	void setRemoteName(String value);

    /**
	 * Returns the value of the '<em><b>Update Column Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * exclusive with other trigger event types. If set to true, triggerColumn cannot be empty
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Update Column Type</em>' attribute.
	 * @see #setUpdateColumnType(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseTrigger_UpdateColumnType()
	 * @model
	 * @generated
	 */
    boolean isUpdateColumnType();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger#isUpdateColumnType <em>Update Column Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Update Column Type</em>' attribute.
	 * @see #isUpdateColumnType()
	 * @generated
	 */
    void setUpdateColumnType(boolean value);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * delegates to getOldRow or getOldTable based on the action granularity
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
    String getOldName();

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * delegates to getNewRow or getNewTable based on the action granularity
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
    String getNewName();

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * delegates to setOldRow or setOldTable based on the action granularity
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
    void setOldName(String oldName);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * delegates to setNewRow or setNewTable based on the action granularity
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
    void setNewName(String newName);

} // SybaseASABaseTrigger