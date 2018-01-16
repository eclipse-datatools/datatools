/**
 * <copyright>
 * </copyright>
 *
 * $Id: EventCondition.java,v 1.1 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getEventCondition()
 * @model
 * @generated
 */
public interface EventCondition extends SQLObject
{
    /**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see #setOperator(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getEventCondition_Operator()
	 * @model
	 * @generated
	 */
    String getOperator();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see #getOperator()
	 * @generated
	 */
    void setOperator(String value);

    /**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * x01 = first day | x02 = second day | ... x40000000 = 31st day
	 *  | x80000000 = last day of month
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getEventCondition_Value()
	 * @model
	 * @generated
	 */
    int getValue();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
    void setValue(int value);

    /**
	 * Returns the value of the '<em><b>Event</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getConditionDetails <em>Condition Details</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' container reference.
	 * @see #setEvent(SybaseASABaseEvent)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getEventCondition_Event()
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getConditionDetails
	 * @model opposite="conditionDetails" required="true" transient="false"
	 * @generated
	 */
    SybaseASABaseEvent getEvent();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getEvent <em>Event</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' container reference.
	 * @see #getEvent()
	 * @generated
	 */
    void setEvent(SybaseASABaseEvent value);

} // EventCondition
