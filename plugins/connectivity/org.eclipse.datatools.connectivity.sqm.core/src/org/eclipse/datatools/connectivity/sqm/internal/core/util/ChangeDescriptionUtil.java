/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.util.DelegatingFeatureMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

public class ChangeDescriptionUtil {
	private ChangeDescription changeDescription;
	private Set deletedObjects;
	private Set createdObjects;
	
	public ChangeDescriptionUtil (ChangeDescription changeDescription) {
		this.changeDescription = changeDescription;		
	}
	
	public boolean isCreated (EObject element) {
	    if (createdObjects == null) {
	        createdObjects = new HashSet();
	        for (Iterator i = EcoreUtil.getAllContents(this.changeDescription.getObjectsToDetach()); i.hasNext(); )
	        {
	            createdObjects.add(i.next());
	        }           
	    }
	    return createdObjects.contains(element);    
	}
	
    public boolean isDeleted (EObject element) {
        if (deletedObjects == null) {
	        Set deletedObjects = new HashSet();
	        for (Iterator i = EcoreUtil.getAllContents(this.changeDescription.getObjectsToAttach()); i.hasNext(); )
	        {
	            deletedObjects.add(i.next());
	        }
        }
	    return deletedObjects != null && deletedObjects.contains(element);
    }
    
    public Set getDeletedObjects()
    {
    	Set deletedObjects = null;
      if (deletedObjects == null)
      {
        deletedObjects = new HashSet();
        for (Iterator i = EcoreUtil.getAllContents(this.changeDescription.getObjectsToAttach()); i.hasNext(); )
        {
          deletedObjects.add(i.next());
        }
      }
      return deletedObjects;
    }

    public EList getChangedDataObjectsGen()
    {
      EList result = new UniqueEList.FastCompare(getDeletedObjects());
      result.addAll(this.changeDescription.getObjectsToDetach());
      for (Iterator i = this.changeDescription.getObjectChanges().iterator(); i.hasNext(); )
      {
        Map.Entry entry = (Map.Entry)i.next();
        result.add(entry.getKey());
      }
      return result;
    }

	public List getOldValues(EObject dataObject) {
		List settings = (List) ((EMap) changeDescription.getObjectChanges())
				.get(dataObject);
		if (settings == null) {
			settings = new ArrayList();
		} else {
			for (int i = 0; i < settings.size(); i++) {
				FeatureChange change = (FeatureChange) settings.get(i);
				EStructuralFeature feature = change.getFeature();
				if (FeatureMapUtil.isFeatureMap(feature)) {
					final List values = (List) change.getValue();
					DelegatingFeatureMap featureMap = new DelegatingFeatureMap(
							((InternalEObject) dataObject), feature) {
						private static final long serialVersionUID = 1L;

						protected final List theList = values;

						// @Override
						protected List delegateList() {
							return theList;
						}
					};

				}
			}
		}
		return settings;
	}

}
