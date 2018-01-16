/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseEvent.java,v 1.3 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getEventType <em>Event Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getEventCreator <em>Event Creator</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getSchedules <em>Schedules</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getConditionDetails <em>Condition Details</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseEvent()
 * @model
 * @generated
 */
public interface SybaseASABaseEvent extends Event
{
    /**
	 * Returns the value of the '<em><b>Event Creator</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Creator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Creator</em>' reference.
	 * @see #setEventCreator(Schema)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseEvent_EventCreator()
	 * @model required="true"
	 * @generated
	 */
	Schema getEventCreator();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getEventCreator <em>Event Creator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Creator</em>' reference.
	 * @see #getEventCreator()
	 * @generated
	 */
	void setEventCreator(Schema value);

    /**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType
	 * @see #setLocation(EventLocationType)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseEvent_Location()
	 * @model
	 * @generated
	 */
	EventLocationType getLocation();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(EventLocationType value);

    /**
	 * Returns the value of the '<em><b>Schedules</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schedules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schedules</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseEvent_Schedules()
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getEvent
	 * @model type="org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule" opposite="event" containment="true"
	 * @generated
	 */
	EList getSchedules();

    /**
	 * Returns the value of the '<em><b>Condition Details</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains the details of the condition statement. See <code>condition</code>. This feature is not guaranteed to be set even when <code>condition</code> contains value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition Details</em>' containment reference list.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseEvent_ConditionDetails()
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition#getEvent
	 * @model type="org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition" opposite="event" containment="true"
	 * @generated
	 */
    EList getConditionDetails();

    /**
	 * Returns the value of the '<em><b>Event Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType
	 * @see #setEventType(EventType)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseEvent_EventType()
	 * @model
	 * @generated
	 */
	EventType getEventType();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getEventType <em>Event Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType
	 * @see #getEventType()
	 * @generated
	 */
	void setEventType(EventType value);

} // SybaseASABaseEvent