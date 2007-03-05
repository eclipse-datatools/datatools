/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASABaseColumn.java,v 1.3 2007/02/08 01:41:33 linsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.tables.Column;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Base Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#getColumnConstraint <em>Column Constraint</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#getTypeOfDefault <em>Type Of Default</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#isIsComputedColumn <em>Is Computed Column</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseColumn()
 * @model
 * @generated
 */
public interface SybaseASABaseColumn extends Column
{
    /**
     * Returns the value of the '<em><b>Column Constraint</b></em>' containment reference.
     * The default value is <code>""</code>.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint#getColumn <em>Column</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Constraint</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Column Constraint</em>' containment reference.
     * @see #setColumnConstraint(SybaseASABaseColumnCheckConstraint)
     * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseColumn_ColumnConstraint()
     * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint#getColumn
     * @model opposite="column" containment="true"
     * @generated
     */
	SybaseASABaseColumnCheckConstraint getColumnConstraint();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#getColumnConstraint <em>Column Constraint</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Constraint</em>' containment reference.
     * @see #getColumnConstraint()
     * @generated
     */
	void setColumnConstraint(SybaseASABaseColumnCheckConstraint value);

    /**
     * Returns the value of the '<em><b>Type Of Default</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Of Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type Of Default</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault
     * @see #setTypeOfDefault(TypeOfDefault)
     * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseColumn_TypeOfDefault()
     * @model
     * @generated
     */
	TypeOfDefault getTypeOfDefault();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#getTypeOfDefault <em>Type Of Default</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type Of Default</em>' attribute.
     * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault
     * @see #getTypeOfDefault()
     * @generated
     */
	void setTypeOfDefault(TypeOfDefault value);

    /**
     * Returns the value of the '<em><b>Unique</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Unique</em>' attribute.
     * @see #setUnique(boolean)
     * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseColumn_Unique()
     * @model
     * @generated
     */
	boolean isUnique();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#isUnique <em>Unique</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unique</em>' attribute.
     * @see #isUnique()
     * @generated
     */
	void setUnique(boolean value);

    /**
     * Returns the value of the '<em><b>Is Computed Column</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Computed Column</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Is Computed Column</em>' attribute.
     * @see #setIsComputedColumn(boolean)
     * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASABaseColumn_IsComputedColumn()
     * @model
     * @generated
     */
	boolean isIsComputedColumn();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn#isIsComputedColumn <em>Is Computed Column</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Computed Column</em>' attribute.
     * @see #isIsComputedColumn()
     * @generated
     */
	void setIsComputedColumn(boolean value);

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
	boolean isLiteralDefault();

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * -1 stand for default value
     * <!-- end-model-doc -->
     * @model kind="operation"
     * @generated
     */
	int getGlobalIncrementPartitionSize();

} // SybaseASABaseColumn