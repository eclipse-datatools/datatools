/*******************************************************************************
 * Copyright (c) 2007, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.util.ArrayList;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

public class AccessControlUtilities {

	public static final String AUTH_ID_TYPE_PUBLIC_TEXT = "(PUBLIC)"; //$NON-NLS-1$
	public static final String AUTH_ID_PUBLIC_TEXT = "PUBLIC"; //$NON-NLS-1$

	protected static boolean asClauseRequested = false;
	protected static boolean genSysGrantedRequested = false;
	protected String product;
	protected String version;
	
	public static boolean isSystemGrantedPrivilege(Privilege privilege) {
		EAnnotation eAnnotation = privilege.getEAnnotation(IBMPluginActivator.PRIVILEGE_PROPERTY);
		if (eAnnotation != null) {
			String detail = (String) eAnnotation.getDetails().get(IBMPluginActivator.PRIVILEGE_SYSTEM_GRANT);
			if (detail != null) return new Boolean(detail).booleanValue();
		}
		ContainmentService contServ=IBMPluginActivator.getInstance().getContainmentService();
		EObject db=privilege.getObject();
		while (!(db instanceof Database) && null != contServ.getContainer(db)) db=contServ.getContainer(db);
		if (db instanceof Database && ((Database)db).getVendor().equalsIgnoreCase("DB2 UDB")) //$NON-NLS-1$
		{
			AuthorizationIdentifier grantor=privilege.getGrantor();
			if (null != grantor)
			{
				String grantorName=grantor.getName();
				if (null != grantorName && grantorName.equalsIgnoreCase("SYSIBM")) return true; //$NON-NLS-1$
			}
		}
		return false;
	}

	public static boolean isDDLSuppressable(Privilege privilege) {
		return (isSystemGrantedPrivilege(privilege) && 
				!isGenSysGrantedRequested());
	}
	
	public boolean isEditable(Privilege priv) {
		return !isSystemGrantedPrivilege(priv);
	}
	
	public boolean isEditable(EClass eClazz,String action) {
		return true;
	}

	public boolean isRoleOptionGrant() {
		return true;
	}
	
	public boolean isGrantableEditable(Privilege priv) {
		return (supportsGrantable(priv)
				&& isEditable(priv)
				&& authIdSupportsGrantable(priv.getGrantee()));
	}

	public boolean supportsGrantable(RoleAuthorization roleAuth) {
		return true;
	}

	public boolean canRoleAdminRole() {
		return true;
	}
	
	public boolean authIdSupportsGrantable(AuthorizationIdentifier authId) {
		return true;
	}

	public boolean authIdSupportsGrantable(EClass eClazz) {
		return true;
	}

	public boolean supportsGrantable(Privilege privilege) {
    	return true;
	}
	
	public boolean supportsGrantable(EClass eClazz,String action) {
		return true;
	}

	public static boolean isAsClauseRequested() {
		return asClauseRequested;
	}
	
	public static void setAsClauseRequested(boolean useAsClause) {
		asClauseRequested = useAsClause;
	}
	
	public static boolean isGenSysGrantedRequested() {
		return genSysGrantedRequested;
	}
	
	public static void setGenSysGrantedRequested(boolean genSysGranted) {
		genSysGrantedRequested = genSysGranted;
	}
	
	public ArrayList getPublicAuthIds() {
		ArrayList list = new ArrayList();
		list.add(AUTH_ID_PUBLIC_TEXT);
		return list;
	}
	
	public boolean isPublicAuthId(String id) {
		if (id == null) return false;
		ArrayList list = getPublicAuthIds();
		return list.contains(id);
	}
	
	public EClass getPublicEClass() {
		return SQLAccessControlPackage.eINSTANCE.getUser();
	}
	
	public Object[] getChildren(Database database,SQLObject parentObject,EClass eClass) {
		return null;
	}
	
	public boolean isValidGranteePrivilegedObjectPair(EClass granteeClass,EClass privilegedObjectClass) {
		return true;
	}
	
	public static boolean match(Privilege e1, Privilege e2) {
	    if (e1 == null || e2 == null) return false;
	    // Check for match of grantable
	    if (e1.isGrantable() != e2.isGrantable()) return false;
	    // Check for match of privilege action
	    String a1 = e1.getAction();
	    if (a1 == null || !a1.equals(e2.getAction())) return false;
	    // Check for match of SQLObject on which privilege granted
	    SQLObject o1 = e1.getObject();
	    SQLObject o2 = e2.getObject();
	    if (!matchSQLObjects(o1, o2)) return false;
	     
	    // Check for match of Authorization Id to which privilege granted
	    AuthorizationIdentifier g1 = e1.getGrantee();
	    AuthorizationIdentifier g2 = e2.getGrantee();
	    if (g1 == null || g2 == null) return false;
	    if (g1.eClass() != g2.eClass()) return false;
	    String n1 = g1.getName();
	    if (n1 == null || !n1.equals(g2.getName())) return false;
	    return true;	    	
	}

	private static boolean matchSQLObjects(SQLObject o1, SQLObject o2) {
		if (o1 == null || o2 == null)
			return false;
		if (o1.eClass() != o2.eClass())
			return false;
		String n1 = o1.getName();
		if (n1 == null || !n1.equals(o2.getName()))
			return false;
		// The names match. Now check the qualifiers.
		ENamedElement c1 = getContainer(o1);
		ENamedElement c2 = getContainer(o2);
		if (c1 != null && c2 != null) {
			if (c1.getName() != null && c1.getName().equals(c2.getName())) {
				return true;
			} else {
				return false;
			}
		} else if (c1 == null && c2 != null || c1 != null && c2 == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Get the container that can be treated as the qualifier of the input object. 
	 * @param o The input object
	 * @return The container of the input object or null.
	 */
    private static ENamedElement getContainer(SQLObject o) {
		EObject c1 = ContainmentServiceImpl.INSTANCE.getContainer(o);
		if (c1 != null) {
			if (SQLSchemaPackage.eINSTANCE.getSchema().isSuperTypeOf(
					c1.eClass()))
				return (Schema) c1;
			if (EcorePackage.eINSTANCE.getENamedElement().isSuperTypeOf(
					c1.eClass())
					&& "ZSeriesDatabaseInstance".equals(c1.eClass().getName())) {
				// zSeries table space is contained in ZSeriesDatabaseInstance
				return (ENamedElement) c1;
			}
			EObject c2 = ContainmentServiceImpl.INSTANCE.getContainer(c1);
			if (c2 != null) {
				if (SQLSchemaPackage.eINSTANCE.getSchema().isSuperTypeOf(
						c2.eClass())) {
					// For example, index is contained in table which is
					// contained in schema.
					return (Schema) c2;
				}
			}
		}
		return null;
	}

	public String getSQLObjectTreeText(Object object) {
       	return ((SQLObject) object).getName(); 
    }

	public boolean isGrantableSupportedForPublic() {
		return true;
	}
	
	/**
	 * @param superClass 
	 * @param objectClass 
	 * @return whether superClass is a valid superClass of objectClass.
	 */
	public boolean isValidSupertype( EClass superClass, EClass objectClass )
	{
		return superClass.isSuperTypeOf( objectClass );
	}
}
