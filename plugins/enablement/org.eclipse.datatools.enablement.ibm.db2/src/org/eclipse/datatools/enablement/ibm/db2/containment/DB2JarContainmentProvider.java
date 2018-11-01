/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2Jar;
import org.eclipse.datatools.enablement.ibm.db2.model.DB2ModelPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author cdchu
 * Clifford Chu (cdchu@us.ibm.com)
 */
public class DB2JarContainmentProvider extends AbstractContainmentProvider {
   public Collection getContainedElements(EObject obj) {
      Collection children = super.getContainedElements(obj);
      DB2Jar jar = (DB2Jar) obj;
      children.addAll(jar.getProcedures());
      return children;
   }
   
   public EObject getContainer(EObject obj) {
      return ((DB2Jar) obj).getSchema();
   }
   
   public EStructuralFeature getContainmentFeature(EObject obj) {
      if (this.getContainer(obj) == null)
         return null;
      else
         return DB2ModelPackage.eINSTANCE.getDB2Schema_Jars();
   }
   
   public String getGroupId(EObject obj) {
      if(obj instanceof DB2Jar) {
         return DB2GroupID.JAR;
      }
      
      return null;
   }	
}
