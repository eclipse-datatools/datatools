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
import org.eclipse.datatools.enablement.ibm.db2.model.DB2XMLSchema;
import org.eclipse.emf.ecore.EObject;

/**
 * @author debbani
 */
public class DB2XMLSchemaContainmentProvider extends
		AbstractContainmentProvider {
	   public Collection getContainedElements(EObject obj) {
	      // causes bug that sometimes shows duplicate entries,
	      // so disabled super call
	      // Collection children = super.getContainedElements(obj);
          DB2XMLSchema xmlSchema = (DB2XMLSchema) obj;
	      Collection children = xmlSchema.getXmlSchemaDocs();
	      return children;
	   }
	   
	   public EObject getContainer(EObject obj) {
	      return ((DB2XMLSchema) obj).getSchema();
	   }
	   
	   public String getGroupId(EObject obj) {
	      if(obj instanceof DB2XMLSchema) {
	         return DB2GroupID.XMLSCHEMA;
	      }	      
	      return null;
	   }	
}
