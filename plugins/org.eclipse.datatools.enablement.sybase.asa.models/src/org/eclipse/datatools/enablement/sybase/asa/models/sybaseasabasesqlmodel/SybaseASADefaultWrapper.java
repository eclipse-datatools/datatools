/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASADefaultWrapper.java,v 1.4 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Default Wrapper</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#isIsLiteral <em>Is Literal</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getPartitionSize <em>Partition Size</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASADefaultWrapper()
 * @model
 * @generated
 */
public interface SybaseASADefaultWrapper extends EObject
{
    public static final String[] STRING_TYPE_SYSTEM_DEFAULTS  = new String[]
                                                              {
        "timestamp", "utc timestamp", "last user", "current date", "current time", "current timestamp",
        "current utc timestamp", "current user", "current publisher", "current database"
                                                              };
    public static final String[] NUMERIC_TYPE_SYSTEM_DEFAULTS = new String[]
                                                                  {
        "autoincrement", "global autoincrement"
                                                                  };

    //IQ does not support "GLOBAL AUTOINCREMENT" type
    public static final String[] NUMERIC_TYPE_SYSTEM_DEFAULTS_NO_GLOBAL_AUTO = new String[]
                                                                             {
                                                                                 "autoincrement"
                                                                             };
    
    public static final String[] TIME_TYPE_SYSTEM_DEFAULTS    = new String[]
                                                              {
                                                                  "current time"
                                                              };
    public static final String[] DATE_TYPE_SYSTEM_DEFAULTS    = new String[]
                                                              {
                                                                  "current date"
                                                              };
    public static final String[] TS_TYPE_SYSTEM_DEFAULTS      = new String[]
                                                              {
        "current timestamp", "current utc timestamp", "timestamp", "utc timestamp"
                                                              };
    public static final String[] BINARY_TYPE_SYSTEM_DEFAULTS  = new String[]
                                                              {
        "current database", "current user", "current publisher", "last user"
                                                              };
    public static final String   GLOBAL_AUTOINCREMENT         = "global autoincrement";
    
    public static final String   TIME_TYPE                    = "time";

    public static final String   TIMESTAMP_TYPE               = "timestamp";
    
    /**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASADefaultWrapper_Value()
	 * @model
	 * @generated
	 */
    String getValue();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
    void setValue(String value);

    /**
	 * Returns the value of the '<em><b>Is Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Literal</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Literal</em>' attribute.
	 * @see #setIsLiteral(boolean)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASADefaultWrapper_IsLiteral()
	 * @model
	 * @generated
	 */
    boolean isIsLiteral();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#isIsLiteral <em>Is Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Literal</em>' attribute.
	 * @see #isIsLiteral()
	 * @generated
	 */
    void setIsLiteral(boolean value);

    /**
	 * Returns the value of the '<em><b>Partition Size</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Partition Size</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Partition Size</em>' attribute.
	 * @see #setPartitionSize(int)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASADefaultWrapper_PartitionSize()
	 * @model default="-1"
	 * @generated
	 */
    int getPartitionSize();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getPartitionSize <em>Partition Size</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partition Size</em>' attribute.
	 * @see #getPartitionSize()
	 * @generated
	 */
    void setPartitionSize(int value);

    /**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault
	 * @see #setType(TypeOfDefault)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASADefaultWrapper_Type()
	 * @model
	 * @generated
	 */
    TypeOfDefault getType();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault
	 * @see #getType()
	 * @generated
	 */
    void setType(TypeOfDefault value);

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
    void parse();

    public String getRawValue();
    
    public boolean isSystemDefault();
} // SybaseASADefaultWrapper