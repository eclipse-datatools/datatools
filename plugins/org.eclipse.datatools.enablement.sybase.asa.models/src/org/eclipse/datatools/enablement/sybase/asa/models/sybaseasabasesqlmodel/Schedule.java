/**
 * <copyright>
 * </copyright>
 *
 * $Id: Schedule.java,v 1.3 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Date;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schedule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#isRecurring <em>Recurring</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStopTime <em>Stop Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getDaysOfWeek <em>Days Of Week</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getDaysOfMonth <em>Days Of Month</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getIntervalUnit <em>Interval Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getIntervalMount <em>Interval Mount</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule()
 * @model
 * @generated
 */
public interface Schedule extends SQLObject
{
    /**
	 * Returns the value of the '<em><b>Recurring</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Recurring</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Recurring</em>' attribute.
	 * @see #setRecurring(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_Recurring()
	 * @model
	 * @generated
	 */
	boolean isRecurring();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#isRecurring <em>Recurring</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Recurring</em>' attribute.
	 * @see #isRecurring()
	 * @generated
	 */
	void setRecurring(boolean value);

    /**
	 * Returns the value of the '<em><b>Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Time</em>' attribute.
	 * @see #setStartTime(Date)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_StartTime()
	 * @model
	 * @generated
	 */
	Date getStartTime();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStartTime <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Time</em>' attribute.
	 * @see #getStartTime()
	 * @generated
	 */
	void setStartTime(Date value);

    /**
	 * Returns the value of the '<em><b>Stop Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stop Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stop Time</em>' attribute.
	 * @see #setStopTime(Date)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_StopTime()
	 * @model
	 * @generated
	 */
	Date getStopTime();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStopTime <em>Stop Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stop Time</em>' attribute.
	 * @see #getStopTime()
	 * @generated
	 */
	void setStopTime(Date value);

    /**
	 * Returns the value of the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Date</em>' attribute.
	 * @see #setStartDate(Date)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_StartDate()
	 * @model
	 * @generated
	 */
	Date getStartDate();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getStartDate <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Date</em>' attribute.
	 * @see #getStartDate()
	 * @generated
	 */
	void setStartDate(Date value);

    /**
	 * Returns the value of the '<em><b>Days Of Week</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * x01 = Sunday | x02 = Monday | x04 = Tuesday | x08 = Wednesday
	 *  | x10 = Thursday | x20 = Friday | x40 = Saturday
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Days Of Week</em>' attribute.
	 * @see #setDaysOfWeek(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_DaysOfWeek()
	 * @model
	 * @generated
	 */
	int getDaysOfWeek();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getDaysOfWeek <em>Days Of Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Days Of Week</em>' attribute.
	 * @see #getDaysOfWeek()
	 * @generated
	 */
	void setDaysOfWeek(int value);

    /**
	 * Returns the value of the '<em><b>Days Of Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * x01 = first day | x02 = second day | ... x40000000 = 31st day
	 *  | x80000000 = last day of month
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Days Of Month</em>' attribute.
	 * @see #setDaysOfMonth(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_DaysOfMonth()
	 * @model
	 * @generated
	 */
	int getDaysOfMonth();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getDaysOfMonth <em>Days Of Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Days Of Month</em>' attribute.
	 * @see #getDaysOfMonth()
	 * @generated
	 */
	void setDaysOfMonth(int value);

    /**
	 * Returns the value of the '<em><b>Interval Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interval Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interval Unit</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType
	 * @see #setIntervalUnit(IntervalUnitType)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_IntervalUnit()
	 * @model
	 * @generated
	 */
	IntervalUnitType getIntervalUnit();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getIntervalUnit <em>Interval Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interval Unit</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType
	 * @see #getIntervalUnit()
	 * @generated
	 */
	void setIntervalUnit(IntervalUnitType value);

    /**
	 * Returns the value of the '<em><b>Interval Mount</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interval Mount</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interval Mount</em>' attribute.
	 * @see #setIntervalMount(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_IntervalMount()
	 * @model
	 * @generated
	 */
	int getIntervalMount();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getIntervalMount <em>Interval Mount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interval Mount</em>' attribute.
	 * @see #getIntervalMount()
	 * @generated
	 */
	void setIntervalMount(int value);

    /**
	 * Returns the value of the '<em><b>Event</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getSchedules <em>Schedules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' container reference.
	 * @see #setEvent(SybaseASABaseEvent)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSchedule_Event()
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent#getSchedules
	 * @model opposite="schedules" required="true" transient="false"
	 * @generated
	 */
	SybaseASABaseEvent getEvent();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule#getEvent <em>Event</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' container reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(SybaseASABaseEvent value);

} // Schedule