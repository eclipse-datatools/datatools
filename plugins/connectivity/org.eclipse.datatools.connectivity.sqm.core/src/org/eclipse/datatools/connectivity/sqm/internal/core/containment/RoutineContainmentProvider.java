/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public class RoutineContainmentProvider extends AbstractContainmentProvider {
	public EObject getContainer(EObject obj) {
		return ((Routine) obj).getSchema();
   }
   
	public EStructuralFeature getContainmentFeature(EObject obj) {
      if (this.getContainer(obj) == null)
         return null;
      else
         return SQLSchemaPackage.eINSTANCE.getSchema_Routines();
   }
	
	public String getGroupId(EObject obj) {
		if(obj instanceof Procedure) {
			return GroupID.PROCEDURE;
		}
		
		if(obj instanceof Function) {
			return GroupID.FUNCTION;			
		}
		
		return null;
	}	
}
