/**
 * <copyright>
 * </copyright>
 *
 * $Id: Event.java,v 1.1 2006/03/03 21:46:27 dpchou Exp $
 */
package org.eclipse.datatools.modelbase.sql.schema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Event#getFor <em>For</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Event#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Event#getAction <em>Action</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Event#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.schema.Event#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends SQLObject {
	/**
	 * Returns the value of the '<em><b>For</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * the type of object on which the event applies
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>For</em>' attribute.
	 * @see #setFor(String)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getEvent_For()
	 * @model
	 * @generated
	 */
	String getFor();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Event#getFor <em>For</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For</em>' attribute.
	 * @see #getFor()
	 * @generated
	 */
	void setFor(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * the condition to be satisfied inorder to activate the event
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getEvent_Condition()
	 * @model
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Event#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * the action that the event performs after being activated
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see #setAction(String)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getEvent_Action()
	 * @model
	 * @generated
	 */
	String getAction();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Event#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see #getAction()
	 * @generated
	 */
	void setAction(String value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * event state
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getEvent_Enabled()
	 * @model
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Event#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Database#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' reference.
	 * @see #setDatabase(Database)
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage#getEvent_Database()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database#getEvents
	 * @model opposite="events" required="true"
	 * @generated
	 */
	Database getDatabase();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.schema.Event#getDatabase <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(Database value);

} // Event
