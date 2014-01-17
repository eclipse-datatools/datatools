/**
 * <copyright>
 * </copyright>
 *
 * $Id: LUWPartitionElement.java,v 1.4 2007/10/12 23:05:36 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.luw.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Partition Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getStarting <em>Starting</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getEnding <em>Ending</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getLUWPartitionExpression <em>LUW Partition Expression</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getPartition <em>Partition</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getEveryClause <em>Every Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionElement()
 * @model
 * @generated
 */
public interface LUWPartitionElement extends SQLObject {
	/**
	 * Returns the value of the '<em><b>Starting</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Starting</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Starting</em>' attribute.
	 * @see #setStarting(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionElement_Starting()
	 * @model
	 * @generated
	 */
	String getStarting();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getStarting <em>Starting</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Starting</em>' attribute.
	 * @see #getStarting()
	 * @generated
	 */
	void setStarting(String value);

	/**
	 * Returns the value of the '<em><b>Ending</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ending</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ending</em>' attribute.
	 * @see #setEnding(String)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionElement_Ending()
	 * @model
	 * @generated
	 */
	String getEnding();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getEnding <em>Ending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ending</em>' attribute.
	 * @see #getEnding()
	 * @generated
	 */
	void setEnding(String value);

	/**
	 * Returns the value of the '<em><b>LUW Partition Expression</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getPartitionElements <em>Partition Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>LUW Partition Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>LUW Partition Expression</em>' container reference.
	 * @see #setLUWPartitionExpression(LUWPartitionExpression)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionElement_LUWPartitionExpression()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionExpression#getPartitionElements
	 * @model opposite="partitionElements" required="true"
	 * @generated
	 */
	LUWPartitionExpression getLUWPartitionExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getLUWPartitionExpression <em>LUW Partition Expression</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>LUW Partition Expression</em>' container reference.
	 * @see #getLUWPartitionExpression()
	 * @generated
	 */
	void setLUWPartitionExpression(LUWPartitionExpression value);

	/**
	 * Returns the value of the '<em><b>Partition</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getPartitionElements <em>Partition Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition</em>' reference.
	 * @see #setPartition(LUWDataPartition)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionElement_Partition()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWDataPartition#getPartitionElements
	 * @model opposite="partitionElements" required="true"
	 * @generated
	 */
	LUWDataPartition getPartition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getPartition <em>Partition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition</em>' reference.
	 * @see #getPartition()
	 * @generated
	 */
	void setPartition(LUWDataPartition value);

	/**
	 * Returns the value of the '<em><b>Every Clause</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement#getLUWPartitionElement <em>LUW Partition Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Every Clause</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Every Clause</em>' containment reference.
	 * @see #setEveryClause(LUWPartitionEveryClauseElement)
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage#getLUWPartitionElement_EveryClause()
	 * @see org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionEveryClauseElement#getLUWPartitionElement
	 * @model opposite="LUWPartitionElement" containment="true"
	 * @generated
	 */
	LUWPartitionEveryClauseElement getEveryClause();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPartitionElement#getEveryClause <em>Every Clause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Every Clause</em>' containment reference.
	 * @see #getEveryClause()
	 * @generated
	 */
	void setEveryClause(LUWPartitionEveryClauseElement value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	Boolean hasEveryClause();

} // LUWPartitionElement
