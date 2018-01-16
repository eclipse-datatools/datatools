/**
 * <copyright>
 * </copyright>
 *
 * $Id: DB2RoutineExtension.java,v 1.5 2007/10/12 23:05:34 hskolwal Exp $
 */
package org.eclipse.datatools.enablement.ibm.db2.model;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DB2 Routine Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * General purpose extension mechanism for adding complext attributes to 
 * DB2Routines for application specific purposes without adding unnecessary
 * classes some might not need.  
 * 
 * For instance, run time information can be saved about each routine; deploy 
 * information can be saved about each routine; extra dependency information 
 * can be stored for each routine, and so on.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage#getDB2RoutineExtension()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface DB2RoutineExtension extends SQLObject {
} // DB2RoutineExtension
