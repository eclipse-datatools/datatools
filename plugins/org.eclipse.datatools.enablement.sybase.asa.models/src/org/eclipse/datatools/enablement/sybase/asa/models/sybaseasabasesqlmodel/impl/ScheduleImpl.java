/**
 * <copyright>
 * </copyright>
 *
 * $Id: ScheduleImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import java.util.Date;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLObjectImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schedule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#isRecurring <em>Recurring</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#getStartTime <em>Start Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#getStopTime <em>Stop Time</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#getDaysOfWeek <em>Days Of Week</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#getDaysOfMonth <em>Days Of Month</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#getIntervalUnit <em>Interval Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#getIntervalMount <em>Interval Mount</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.ScheduleImpl#getEvent <em>Event</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScheduleImpl extends SQLObjectImpl implements Schedule 
{
    /**
	 * The default value of the '{@link #isRecurring() <em>Recurring</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecurring()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RECURRING_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isRecurring() <em>Recurring</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRecurring()
	 * @generated
	 * @ordered
	 */
	protected boolean recurring = RECURRING_EDEFAULT;

    /**
	 * The default value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date START_TIME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getStartTime() <em>Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartTime()
	 * @generated
	 * @ordered
	 */
	protected Date startTime = START_TIME_EDEFAULT;

    /**
	 * The default value of the '{@link #getStopTime() <em>Stop Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStopTime()
	 * @generated
	 * @ordered
	 */
	protected static final Date STOP_TIME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getStopTime() <em>Stop Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStopTime()
	 * @generated
	 * @ordered
	 */
	protected Date stopTime = STOP_TIME_EDEFAULT;

    /**
	 * The default value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date START_DATE_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getStartDate() <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartDate()
	 * @generated
	 * @ordered
	 */
	protected Date startDate = START_DATE_EDEFAULT;

    /**
	 * The default value of the '{@link #getDaysOfWeek() <em>Days Of Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaysOfWeek()
	 * @generated
	 * @ordered
	 */
	protected static final int DAYS_OF_WEEK_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getDaysOfWeek() <em>Days Of Week</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaysOfWeek()
	 * @generated
	 * @ordered
	 */
	protected int daysOfWeek = DAYS_OF_WEEK_EDEFAULT;

    /**
	 * The default value of the '{@link #getDaysOfMonth() <em>Days Of Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaysOfMonth()
	 * @generated
	 * @ordered
	 */
	protected static final int DAYS_OF_MONTH_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getDaysOfMonth() <em>Days Of Month</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDaysOfMonth()
	 * @generated
	 * @ordered
	 */
	protected int daysOfMonth = DAYS_OF_MONTH_EDEFAULT;

    /**
	 * The default value of the '{@link #getIntervalUnit() <em>Interval Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntervalUnit()
	 * @generated
	 * @ordered
	 */
	protected static final IntervalUnitType INTERVAL_UNIT_EDEFAULT = IntervalUnitType.HOURS_LITERAL;

    /**
	 * The cached value of the '{@link #getIntervalUnit() <em>Interval Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntervalUnit()
	 * @generated
	 * @ordered
	 */
	protected IntervalUnitType intervalUnit = INTERVAL_UNIT_EDEFAULT;

    /**
	 * The default value of the '{@link #getIntervalMount() <em>Interval Mount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntervalMount()
	 * @generated
	 * @ordered
	 */
	protected static final int INTERVAL_MOUNT_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getIntervalMount() <em>Interval Mount</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntervalMount()
	 * @generated
	 * @ordered
	 */
	protected int intervalMount = INTERVAL_MOUNT_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScheduleImpl()
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
		return SybaseasabasesqlmodelPackage.Literals.SCHEDULE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRecurring()
    {
		return recurring;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecurring(boolean newRecurring)
    {
		boolean oldRecurring = recurring;
		recurring = newRecurring;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__RECURRING, oldRecurring, recurring));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getStartTime()
    {
		return startTime;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartTime(Date newStartTime)
    {
		Date oldStartTime = startTime;
		startTime = newStartTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__START_TIME, oldStartTime, startTime));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getStopTime()
    {
		return stopTime;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStopTime(Date newStopTime)
    {
		Date oldStopTime = stopTime;
		stopTime = newStopTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__STOP_TIME, oldStopTime, stopTime));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getStartDate()
    {
		return startDate;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartDate(Date newStartDate)
    {
		Date oldStartDate = startDate;
		startDate = newStartDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__START_DATE, oldStartDate, startDate));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDaysOfWeek()
    {
		return daysOfWeek;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDaysOfWeek(int newDaysOfWeek)
    {
		int oldDaysOfWeek = daysOfWeek;
		daysOfWeek = newDaysOfWeek;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_WEEK, oldDaysOfWeek, daysOfWeek));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDaysOfMonth()
    {
		return daysOfMonth;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDaysOfMonth(int newDaysOfMonth)
    {
		int oldDaysOfMonth = daysOfMonth;
		daysOfMonth = newDaysOfMonth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_MONTH, oldDaysOfMonth, daysOfMonth));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntervalUnitType getIntervalUnit()
    {
		return intervalUnit;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntervalUnit(IntervalUnitType newIntervalUnit)
    {
		IntervalUnitType oldIntervalUnit = intervalUnit;
		intervalUnit = newIntervalUnit == null ? INTERVAL_UNIT_EDEFAULT : newIntervalUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_UNIT, oldIntervalUnit, intervalUnit));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIntervalMount()
    {
		return intervalMount;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntervalMount(int newIntervalMount)
    {
		int oldIntervalMount = intervalMount;
		intervalMount = newIntervalMount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_MOUNT, oldIntervalMount, intervalMount));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASABaseEvent getEvent()
    {
		if (eContainerFeatureID != SybaseasabasesqlmodelPackage.SCHEDULE__EVENT) return null;
		return (SybaseASABaseEvent)eContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEvent(SybaseASABaseEvent newEvent, NotificationChain msgs)
    {
		msgs = eBasicSetContainer((InternalEObject)newEvent, SybaseasabasesqlmodelPackage.SCHEDULE__EVENT, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvent(SybaseASABaseEvent newEvent)
    {
		if (newEvent != eInternalContainer() || (eContainerFeatureID != SybaseasabasesqlmodelPackage.SCHEDULE__EVENT && newEvent != null)) {
			if (EcoreUtil.isAncestor(this, newEvent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEvent != null)
				msgs = ((InternalEObject)newEvent).eInverseAdd(this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__SCHEDULES, SybaseASABaseEvent.class, msgs);
			msgs = basicSetEvent(newEvent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SybaseasabasesqlmodelPackage.SCHEDULE__EVENT, newEvent, newEvent));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SCHEDULE__EVENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEvent((SybaseASABaseEvent)otherEnd, msgs);
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
			case SybaseasabasesqlmodelPackage.SCHEDULE__EVENT:
				return basicSetEvent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
    {
		switch (eContainerFeatureID) {
			case SybaseasabasesqlmodelPackage.SCHEDULE__EVENT:
				return eInternalContainer().eInverseRemove(this, SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT__SCHEDULES, SybaseASABaseEvent.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
		switch (featureID) {
			case SybaseasabasesqlmodelPackage.SCHEDULE__RECURRING:
				return isRecurring() ? Boolean.TRUE : Boolean.FALSE;
			case SybaseasabasesqlmodelPackage.SCHEDULE__START_TIME:
				return getStartTime();
			case SybaseasabasesqlmodelPackage.SCHEDULE__STOP_TIME:
				return getStopTime();
			case SybaseasabasesqlmodelPackage.SCHEDULE__START_DATE:
				return getStartDate();
			case SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_WEEK:
				return new Integer(getDaysOfWeek());
			case SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_MONTH:
				return new Integer(getDaysOfMonth());
			case SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_UNIT:
				return getIntervalUnit();
			case SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_MOUNT:
				return new Integer(getIntervalMount());
			case SybaseasabasesqlmodelPackage.SCHEDULE__EVENT:
				return getEvent();
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
			case SybaseasabasesqlmodelPackage.SCHEDULE__RECURRING:
				setRecurring(((Boolean)newValue).booleanValue());
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__START_TIME:
				setStartTime((Date)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__STOP_TIME:
				setStopTime((Date)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__START_DATE:
				setStartDate((Date)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_WEEK:
				setDaysOfWeek(((Integer)newValue).intValue());
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_MONTH:
				setDaysOfMonth(((Integer)newValue).intValue());
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_UNIT:
				setIntervalUnit((IntervalUnitType)newValue);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_MOUNT:
				setIntervalMount(((Integer)newValue).intValue());
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__EVENT:
				setEvent((SybaseASABaseEvent)newValue);
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
			case SybaseasabasesqlmodelPackage.SCHEDULE__RECURRING:
				setRecurring(RECURRING_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__START_TIME:
				setStartTime(START_TIME_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__STOP_TIME:
				setStopTime(STOP_TIME_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__START_DATE:
				setStartDate(START_DATE_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_WEEK:
				setDaysOfWeek(DAYS_OF_WEEK_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_MONTH:
				setDaysOfMonth(DAYS_OF_MONTH_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_UNIT:
				setIntervalUnit(INTERVAL_UNIT_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_MOUNT:
				setIntervalMount(INTERVAL_MOUNT_EDEFAULT);
				return;
			case SybaseasabasesqlmodelPackage.SCHEDULE__EVENT:
				setEvent((SybaseASABaseEvent)null);
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
			case SybaseasabasesqlmodelPackage.SCHEDULE__RECURRING:
				return recurring != RECURRING_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SCHEDULE__START_TIME:
				return START_TIME_EDEFAULT == null ? startTime != null : !START_TIME_EDEFAULT.equals(startTime);
			case SybaseasabasesqlmodelPackage.SCHEDULE__STOP_TIME:
				return STOP_TIME_EDEFAULT == null ? stopTime != null : !STOP_TIME_EDEFAULT.equals(stopTime);
			case SybaseasabasesqlmodelPackage.SCHEDULE__START_DATE:
				return START_DATE_EDEFAULT == null ? startDate != null : !START_DATE_EDEFAULT.equals(startDate);
			case SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_WEEK:
				return daysOfWeek != DAYS_OF_WEEK_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SCHEDULE__DAYS_OF_MONTH:
				return daysOfMonth != DAYS_OF_MONTH_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_UNIT:
				return intervalUnit != INTERVAL_UNIT_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SCHEDULE__INTERVAL_MOUNT:
				return intervalMount != INTERVAL_MOUNT_EDEFAULT;
			case SybaseasabasesqlmodelPackage.SCHEDULE__EVENT:
				return getEvent() != null;
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
		result.append(" (recurring: ");
		result.append(recurring);
		result.append(", startTime: ");
		result.append(startTime);
		result.append(", stopTime: ");
		result.append(stopTime);
		result.append(", startDate: ");
		result.append(startDate);
		result.append(", daysOfWeek: ");
		result.append(daysOfWeek);
		result.append(", daysOfMonth: ");
		result.append(daysOfMonth);
		result.append(", intervalUnit: ");
		result.append(intervalUnit);
		result.append(", intervalMount: ");
		result.append(intervalMount);
		result.append(')');
		return result.toString();
	}

    public boolean equals(Object obj)
    {
        if (!(obj instanceof ScheduleImpl))
        {
            return false;
        }
        ScheduleImpl another = (ScheduleImpl)obj;
        return another.isRecurring() == recurring &&
                (startTime == null && another.startTime == null || another.startTime != null && another.startTime.equals(startTime)) &&
                (stopTime == null && another.stopTime == null || another.stopTime != null && another.stopTime.equals(stopTime)) &&
                (startDate == null && another.startDate == null || another.startDate != null && another.startDate.equals(startDate)) &&
                another.daysOfWeek == daysOfWeek &&
                another.daysOfMonth == daysOfMonth &&
                another.intervalMount == intervalMount &&
                another.intervalUnit.getValue() == intervalUnit.getValue();
    }

} //ScheduleImpl