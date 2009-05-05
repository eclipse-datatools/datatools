/**
 * <copyright>
 * </copyright>
 *
 * $Id: EventType.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Event Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getEventType()
 * @model
 * @generated
 */
public final class EventType extends AbstractEnumerator {
    /**
	 * The '<em><b>NOEVENTTYPE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOEVENTTYPE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOEVENTTYPE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NOEVENTTYPE = 0;

    /**
	 * The '<em><b>BACKUPEND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BACKUPEND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BACKUPEND_LITERAL
	 * @model literal="BackupEnd"
	 * @generated
	 * @ordered
	 */
	public static final int BACKUPEND = 1;

    /**
	 * The '<em><b>CONNECT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONNECT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECT_LITERAL
	 * @model literal="\"Connect\""
	 * @generated
	 * @ordered
	 */
	public static final int CONNECT = 2;

    /**
	 * The '<em><b>CONNECTFAILED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONNECTFAILED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECTFAILED_LITERAL
	 * @model literal="ConnectFailed"
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTFAILED = 3;

    /**
	 * The '<em><b>DATABASESTART</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DATABASESTART</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DATABASESTART_LITERAL
	 * @model literal="DatabaseStart"
	 * @generated
	 * @ordered
	 */
	public static final int DATABASESTART = 4;

    /**
	 * The '<em><b>DBDISKSPACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DBDISKSPACE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DBDISKSPACE_LITERAL
	 * @model literal="DBDiskSpace"
	 * @generated
	 * @ordered
	 */
	public static final int DBDISKSPACE = 5;

    /**
	 * The '<em><b>DISCONEECT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DISCONEECT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DISCONEECT_LITERAL
	 * @model literal="\"Disconnect\""
	 * @generated
	 * @ordered
	 */
	public static final int DISCONEECT = 6;

    /**
	 * The '<em><b>GLOBALAUTOINCREMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GLOBALAUTOINCREMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GLOBALAUTOINCREMENT_LITERAL
	 * @model literal="GlobalAutoincrement"
	 * @generated
	 * @ordered
	 */
	public static final int GLOBALAUTOINCREMENT = 7;

    /**
	 * The '<em><b>GROWDB</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GROWDB</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GROWDB_LITERAL
	 * @model literal="GrowDB"
	 * @generated
	 * @ordered
	 */
	public static final int GROWDB = 8;

    /**
	 * The '<em><b>GROWLOG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GROWLOG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GROWLOG_LITERAL
	 * @model literal="GrowLog"
	 * @generated
	 * @ordered
	 */
	public static final int GROWLOG = 9;

    /**
	 * The '<em><b>GROWTEMP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GROWTEMP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GROWTEMP_LITERAL
	 * @model literal="GrowTemp"
	 * @generated
	 * @ordered
	 */
	public static final int GROWTEMP = 10;

    /**
	 * The '<em><b>LOGDISKSPACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOGDISKSPACE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOGDISKSPACE_LITERAL
	 * @model literal="LogDiskSpace"
	 * @generated
	 * @ordered
	 */
	public static final int LOGDISKSPACE = 11;

    /**
	 * The '<em><b>RAISERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RAISERROR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RAISERROR_LITERAL
	 * @model literal="\"RAISERROR\""
	 * @generated
	 * @ordered
	 */
	public static final int RAISERROR = 12;

    /**
	 * The '<em><b>SERVERIDLE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SERVERIDLE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SERVERIDLE_LITERAL
	 * @model literal="ServerIdle"
	 * @generated
	 * @ordered
	 */
	public static final int SERVERIDLE = 13;

    /**
	 * The '<em><b>TEMPDISKSPACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TEMPDISKSPACE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TEMPDISKSPACE_LITERAL
	 * @model literal="TempDiskSpace"
	 * @generated
	 * @ordered
	 */
	public static final int TEMPDISKSPACE = 14;

    /**
	 * The '<em><b>NOEVENTTYPE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOEVENTTYPE
	 * @generated
	 * @ordered
	 */
	public static final EventType NOEVENTTYPE_LITERAL = new EventType(NOEVENTTYPE, "NOEVENTTYPE", "NOEVENTTYPE");

    /**
	 * The '<em><b>BACKUPEND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BACKUPEND
	 * @generated
	 * @ordered
	 */
	public static final EventType BACKUPEND_LITERAL = new EventType(BACKUPEND, "BACKUPEND", "BackupEnd");

    /**
	 * The '<em><b>CONNECT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONNECT
	 * @generated
	 * @ordered
	 */
	public static final EventType CONNECT_LITERAL = new EventType(CONNECT, "CONNECT", "\"Connect\"");

    /**
	 * The '<em><b>CONNECTFAILED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONNECTFAILED
	 * @generated
	 * @ordered
	 */
	public static final EventType CONNECTFAILED_LITERAL = new EventType(CONNECTFAILED, "CONNECTFAILED", "ConnectFailed");

    /**
	 * The '<em><b>DATABASESTART</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DATABASESTART
	 * @generated
	 * @ordered
	 */
	public static final EventType DATABASESTART_LITERAL = new EventType(DATABASESTART, "DATABASESTART", "DatabaseStart");

    /**
	 * The '<em><b>DBDISKSPACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DBDISKSPACE
	 * @generated
	 * @ordered
	 */
	public static final EventType DBDISKSPACE_LITERAL = new EventType(DBDISKSPACE, "DBDISKSPACE", "DBDiskSpace");

    /**
	 * The '<em><b>DISCONEECT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DISCONEECT
	 * @generated
	 * @ordered
	 */
	public static final EventType DISCONEECT_LITERAL = new EventType(DISCONEECT, "DISCONEECT", "\"Disconnect\"");

    /**
	 * The '<em><b>GLOBALAUTOINCREMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GLOBALAUTOINCREMENT
	 * @generated
	 * @ordered
	 */
	public static final EventType GLOBALAUTOINCREMENT_LITERAL = new EventType(GLOBALAUTOINCREMENT, "GLOBALAUTOINCREMENT", "GlobalAutoincrement");

    /**
	 * The '<em><b>GROWDB</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROWDB
	 * @generated
	 * @ordered
	 */
	public static final EventType GROWDB_LITERAL = new EventType(GROWDB, "GROWDB", "GrowDB");

    /**
	 * The '<em><b>GROWLOG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROWLOG
	 * @generated
	 * @ordered
	 */
	public static final EventType GROWLOG_LITERAL = new EventType(GROWLOG, "GROWLOG", "GrowLog");

    /**
	 * The '<em><b>GROWTEMP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GROWTEMP
	 * @generated
	 * @ordered
	 */
	public static final EventType GROWTEMP_LITERAL = new EventType(GROWTEMP, "GROWTEMP", "GrowTemp");

    /**
	 * The '<em><b>LOGDISKSPACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGDISKSPACE
	 * @generated
	 * @ordered
	 */
	public static final EventType LOGDISKSPACE_LITERAL = new EventType(LOGDISKSPACE, "LOGDISKSPACE", "LogDiskSpace");

    /**
	 * The '<em><b>RAISERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RAISERROR
	 * @generated
	 * @ordered
	 */
	public static final EventType RAISERROR_LITERAL = new EventType(RAISERROR, "RAISERROR", "\"RAISERROR\"");

    /**
	 * The '<em><b>SERVERIDLE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SERVERIDLE
	 * @generated
	 * @ordered
	 */
	public static final EventType SERVERIDLE_LITERAL = new EventType(SERVERIDLE, "SERVERIDLE", "ServerIdle");

    /**
	 * The '<em><b>TEMPDISKSPACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TEMPDISKSPACE
	 * @generated
	 * @ordered
	 */
	public static final EventType TEMPDISKSPACE_LITERAL = new EventType(TEMPDISKSPACE, "TEMPDISKSPACE", "TempDiskSpace");

    /**
	 * An array of all the '<em><b>Event Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EventType[] VALUES_ARRAY =
        new EventType[] {
			NOEVENTTYPE_LITERAL,
			BACKUPEND_LITERAL,
			CONNECT_LITERAL,
			CONNECTFAILED_LITERAL,
			DATABASESTART_LITERAL,
			DBDISKSPACE_LITERAL,
			DISCONEECT_LITERAL,
			GLOBALAUTOINCREMENT_LITERAL,
			GROWDB_LITERAL,
			GROWLOG_LITERAL,
			GROWTEMP_LITERAL,
			LOGDISKSPACE_LITERAL,
			RAISERROR_LITERAL,
			SERVERIDLE_LITERAL,
			TEMPDISKSPACE_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Event Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Event Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventType get(String literal)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EventType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Event Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventType getByName(String name)
    {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EventType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Event Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventType get(int value)
    {
		switch (value) {
			case NOEVENTTYPE: return NOEVENTTYPE_LITERAL;
			case BACKUPEND: return BACKUPEND_LITERAL;
			case CONNECT: return CONNECT_LITERAL;
			case CONNECTFAILED: return CONNECTFAILED_LITERAL;
			case DATABASESTART: return DATABASESTART_LITERAL;
			case DBDISKSPACE: return DBDISKSPACE_LITERAL;
			case DISCONEECT: return DISCONEECT_LITERAL;
			case GLOBALAUTOINCREMENT: return GLOBALAUTOINCREMENT_LITERAL;
			case GROWDB: return GROWDB_LITERAL;
			case GROWLOG: return GROWLOG_LITERAL;
			case GROWTEMP: return GROWTEMP_LITERAL;
			case LOGDISKSPACE: return LOGDISKSPACE_LITERAL;
			case RAISERROR: return RAISERROR_LITERAL;
			case SERVERIDLE: return SERVERIDLE_LITERAL;
			case TEMPDISKSPACE: return TEMPDISKSPACE_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EventType(int value, String name, String literal)
    {
		super(value, name, literal);
	}

} //EventType
