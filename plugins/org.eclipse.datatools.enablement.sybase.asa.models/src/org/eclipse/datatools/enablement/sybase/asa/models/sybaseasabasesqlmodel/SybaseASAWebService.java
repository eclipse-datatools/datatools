/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseASAWebService.java,v 1.3 2008/03/27 07:35:07 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sybase ASA Web Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getService_id <em>Service id</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getService_type <em>Service type</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getAuth_required <em>Auth required</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getSecure_required <em>Secure required</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getUrl_path <em>Url path</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getUser_name <em>User name</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getDatabase <em>Database</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService()
 * @model
 * @generated
 */
public interface SybaseASAWebService extends SQLObject
{
    /**
	 * Returns the value of the '<em><b>Service id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service id</em>' attribute.
	 * @see #setService_id(long)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_Service_id()
	 * @model
	 * @generated
	 */
	long getService_id();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getService_id <em>Service id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service id</em>' attribute.
	 * @see #getService_id()
	 * @generated
	 */
	void setService_id(long value);

    /**
	 * Returns the value of the '<em><b>Service type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service type</em>' attribute.
	 * @see #setService_type(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_Service_type()
	 * @model
	 * @generated
	 */
	String getService_type();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getService_type <em>Service type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service type</em>' attribute.
	 * @see #getService_type()
	 * @generated
	 */
	void setService_type(String value);

    /**
	 * Returns the value of the '<em><b>Auth required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auth required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auth required</em>' attribute.
	 * @see #setAuth_required(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_Auth_required()
	 * @model
	 * @generated
	 */
	String getAuth_required();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getAuth_required <em>Auth required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auth required</em>' attribute.
	 * @see #getAuth_required()
	 * @generated
	 */
	void setAuth_required(String value);

    /**
	 * Returns the value of the '<em><b>Secure required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secure required</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secure required</em>' attribute.
	 * @see #setSecure_required(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_Secure_required()
	 * @model
	 * @generated
	 */
	String getSecure_required();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getSecure_required <em>Secure required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secure required</em>' attribute.
	 * @see #getSecure_required()
	 * @generated
	 */
	void setSecure_required(String value);

    /**
	 * Returns the value of the '<em><b>Url path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Url path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Url path</em>' attribute.
	 * @see #setUrl_path(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_Url_path()
	 * @model
	 * @generated
	 */
	String getUrl_path();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getUrl_path <em>Url path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url path</em>' attribute.
	 * @see #getUrl_path()
	 * @generated
	 */
	void setUrl_path(String value);

    /**
	 * Returns the value of the '<em><b>User name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User name</em>' attribute.
	 * @see #setUser_name(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_User_name()
	 * @model
	 * @generated
	 */
	String getUser_name();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getUser_name <em>User name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User name</em>' attribute.
	 * @see #getUser_name()
	 * @generated
	 */
	void setUser_name(String value);

    /**
	 * Returns the value of the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' attribute.
	 * @see #setParameter(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_Parameter()
	 * @model
	 * @generated
	 */
	String getParameter();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getParameter <em>Parameter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' attribute.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(String value);

    /**
	 * Returns the value of the '<em><b>Statement</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statement</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement</em>' attribute.
	 * @see #setStatement(String)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_Statement()
	 * @model
	 * @generated
	 */
	String getStatement();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getStatement <em>Statement</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Statement</em>' attribute.
	 * @see #getStatement()
	 * @generated
	 */
	void setStatement(String value);

    /**
	 * Returns the value of the '<em><b>Database</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getWebServices <em>Web Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Database</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Database</em>' reference.
	 * @see #setDatabase(SybaseASABaseDatabase)
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#getSybaseASAWebService_Database()
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase#getWebServices
	 * @model opposite="webServices" required="true"
	 * @generated
	 */
	SybaseASABaseDatabase getDatabase();

    /**
	 * Sets the value of the '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService#getDatabase <em>Database</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Database</em>' reference.
	 * @see #getDatabase()
	 * @generated
	 */
	void setDatabase(SybaseASABaseDatabase value);

} // SybaseASAWebService