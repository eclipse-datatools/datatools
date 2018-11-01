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
package org.eclipse.datatools.enablement.ibm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

public class CloneUtil {
	
	static private Hashtable packages = getProviders(); 
	
	static private Hashtable requiredFeatures = getRequiredFeatures();
 
	private static ClientConfiguration client = new ClientConfiguration(ClientConfiguration.CLONE_UTIL);
	
	public static EObject[] clone(EObject targetContainer, EObject obj) {
		return cloneWithElementMap(targetContainer, obj, new HashMap());
	}
	
	public static EObject[] clone(EObject[] targetContainers, EObject[] objs) {
		return cloneWithElementMap(targetContainers, objs, new HashMap());
	}
	
	public static EObject[] cloneWithElementMap(final EObject targetContainer, final EObject obj, final Map map) 
    {
	    return cloneWithElementMap(targetContainer, obj, map, false, true);
    }
	
	//This is the one used by Compare and Sync.
	public static EObject[] cloneWithElementMap(final EObject targetContainer, final EObject obj, final Map map, boolean cloneCrossModelExternalReferenced) 
    {
	    return cloneWithElementMap(targetContainer, obj, map, false, true, cloneCrossModelExternalReferenced);
    }

	public static EObject[] cloneWithElementMap(final EObject[] targetContainers, final EObject[] objs, final Map map) {
	    return cloneWithElementMap(targetContainers,objs,map,false,true);
	}

    public static EObject[] cloneWithElementMap(final EObject targetContainer, final EObject obj, final Map map, final boolean isCut, final boolean cloneExternalReferenced) 
    {
        final List result = new ArrayList(1);
//<bgp        DB2PluginActivator.getInstance().getCommandManager().runCommand(new Runnable()
//        {
//            public void run()
//            {
//                result.add(cloneWithElementMap2(new EObject[] {targetContainer}, new EObject[] {obj}, map, new LinkedList(), new LinkedList(),isCut, cloneExternalReferenced, true));
//            }
//bgp>        });
        return (EObject[]) result.toArray(new Object[1])[0];
    }

    public static EObject[] cloneWithElementMap(final EObject targetContainer, final EObject obj, final Map map, final boolean isCut, final boolean cloneExternalReferenced, final boolean cloneCrossModelExternalReferenced) 
    {
        final List result = new ArrayList(1);
//<bgp        DB2PluginActivator.getInstance().getCommandManager().runCommand(new Runnable()
//        {
//            public void run()
//            {
//            	result.add(cloneWithElementMap2(new EObject[] {targetContainer}, new EObject[] {obj}, map, new LinkedList(),new LinkedList(),isCut, cloneExternalReferenced, cloneCrossModelExternalReferenced));
//            }
//bgp>        });
        return (EObject[]) result.toArray(new Object[1])[0];
    }
   
    public static EObject[] cloneWithElementMap(final EObject[] targetContainers, final EObject[] objs, final Map map, final boolean isCut, final boolean cloneExternalReferenced)
    {
        final List result = new ArrayList(1);
//<bgp        DB2PluginActivator.getInstance().getCommandManager().runCommand(new Runnable()
//        {
//            public void run()
//            {
//                result.add(cloneWithElementMap2(targetContainers, objs, map, isCut, cloneExternalReferenced));
//            }
//bgp>        });
        return (EObject[]) result.toArray(new Object[1])[0];
    }
    
    public static EObject[] cloneWithElementMap(final EObject targetContainer, final EObject obj, final Map map, final Collection containmentExclusion, final Collection externalExclusion, final boolean isCut, final boolean cloneExternalReferenced, final boolean cloneCrossModelExternalReferenced) 
    {
        final List result = new ArrayList(1);
//<bgp        DB2PluginActivator.getInstance().getCommandManager().runCommand(new Runnable()
//        {
//            public void run()
//            {
//            	result.add(cloneWithElementMap2(new EObject[] {targetContainer}, new EObject[] {obj}, map, containmentExclusion, externalExclusion ,isCut, cloneExternalReferenced, cloneCrossModelExternalReferenced));
//            }
//bgp>        });
        return (EObject[]) result.toArray(new Object[1])[0];
    }
    
    public static EObject[] cloneWithElementMap(final EObject targetContainer, final EObject obj, final Map map, final Collection containmentExclusion, final boolean isCut, final boolean cloneExternalReferenced, final boolean cloneCrossModelExternalReferenced) 
    {
        final List result = new ArrayList(1);
//<bgp        DB2PluginActivator.getInstance().getCommandManager().runCommand(new Runnable()
//        {
//            public void run()
//            {
//            	result.add(cloneWithElementMap2(new EObject[] {targetContainer}, new EObject[] {obj}, map, containmentExclusion, new LinkedList(),isCut, cloneExternalReferenced, cloneCrossModelExternalReferenced));
//            }
//bgp>        });
        return (EObject[]) result.toArray(new Object[1])[0];
    }
    
    public static void updateReferencesToCloned(EObject[] clonedObjects, ResourceSet resourceSet) {
    	for(int i=0; i<clonedObjects.length; i++) {
    		EObject anObj = clonedObjects[i];
    		Collection affectedObjs = EcoreUtil.UsageCrossReferencer.find(anObj, resourceSet);
        	Iterator itor = affectedObjs.iterator();
        	while (itor.hasNext()) {
				try {
					EStructuralFeature.Setting setting = (EStructuralFeature.Setting) itor.next();
					EObject referencingEObject = setting.getEObject();
					EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
					// Containment relationship is taken care of by reParent().  Performance??
					if (underContainer(anObj, referencingEObject) || underContainer(referencingEObject, anObj)) { 
						continue;
					}

					if (eStructuralFeature.isChangeable()) {
						if(!eStructuralFeature.isMany()) {
							referencingEObject.eSet(eStructuralFeature, anObj);
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}
    	}
    }
    
	private static EObject[] cloneWithElementMap2(EObject[] targetContainers, EObject[] objs, Map originalMap, Collection containmentExclusion, Collection externalExclusion, boolean isCut, boolean cloneExternalReferenced, boolean cloneCrossModelExternalReferenced) {
		LinkedHashMap map = new  LinkedHashMap(originalMap);
		List sourceContainers = new ArrayList();
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		for(int i=0; i<targetContainers.length; ++i) {
	    	final EObject targetContainer = targetContainers[i];
	    	final EObject source = objs[i];
			final EObject sourceContainer = service.getContainer(source);
			sourceContainers.add(sourceContainer);
			final EObject targetRoot = (targetContainer == null)? null : service.getRootElement(targetContainer);
			final EObject sourceRoot = service.getRootElement(source);
			if(targetContainer == null || targetRoot.eClass() != sourceRoot.eClass()) {
				if (isCut) {
					map.put(source,source);
				} else {
					Collection exclude = findAllObjectsWithRequiredExternalReference(source);
					Map containmentMap = cloneContainmentHierarchy(source, exclude);
					map.putAll(containmentMap);
				}
		    }
		    else {
		    	EObject commonContainer = getLeastCommonContainer(sourceContainer, targetContainer);
		    	if (isCut) {  //if cut/paste don't clone the object and containment
			    	map.put(source,source);
		    	} else {
					Map containmentMap = cloneContainmentHierarchy(source, containmentExclusion);
					map.putAll(containmentMap);
		    	}
		    	if (commonContainer == null && !map.containsKey(sourceRoot)) {
		    		map.put(sourceRoot, targetRoot);
		    	}
				mapContainers(sourceContainer, targetContainer, map);
		    }
	    }

		List containers = new LinkedList();
	    if (cloneExternalReferenced) {   //clone referenced element, like copy from SE to PE
		    for(int i=0; i<targetContainers.length; ++i) {
		    	final EObject targetContainer = targetContainers[i];
		    	final EObject source = objs[i];
				final EObject sourceContainer = service.getContainer(source);
				final EObject targetRoot = (targetContainer == null)? null : service.getRootElement(targetContainer);
				final EObject sourceRoot = service.getRootElement(source);
		    	final EObject commonContainer = getLeastCommonContainer(sourceContainer, targetContainer);
	
				if(targetContainer != null && targetRoot.eClass() == sourceRoot.eClass()) {
					if(targetContainer != null && targetRoot.eClass() == sourceRoot.eClass()) {
						Iterator it = findAllRequiredExternalReferencedObjects(source, map).iterator();
						while(it.hasNext()) {
							EObject o = (EObject) it.next();
							if (!cloneCrossModelExternalReferenced && isCrossModelObject(o,source)) continue; //do not clone the cross model referenced obj
							if (externalExclusion.contains(o)) continue;
							if(commonContainer == null || underContainer(o, commonContainer)) { 		
								findOrCloneExternalObject(o, targetRoot, containers, map);
							}
						}
				    }
			    }
		    }
	    }
	    
	    
	    //if cut/paste then do not copy its reference.
	    if (isCut) {
			for(int i=0; i<targetContainers.length; ++i) {
		    	final EObject targetContainer = targetContainers[i];
		    	final EObject source = objs[i];
				final EObject sourceContainer = service.getContainer(source);
				if(targetContainer != null) {
					reParent(source,sourceContainer,targetContainer,map);
				}
			}
	    } else {
			Set exclusion = new HashSet(containmentExclusion);
			exclusion.addAll(externalExclusion);
	    	List keySet = new LinkedList(map.keySet());
			Iterator it  = keySet.iterator();
			while(it.hasNext()) {
				EObject obj = (EObject) it.next();
				if (sourceContainers.contains(obj)) continue;
				//ODA also calls this method with a non-empty map. The change has caused regression
				//in their test cases. Comment out the change for now.
				//(JYEH)WSDBU00261140 Sev 2 Core:SVT - commands generated contain stmts not selected to be copied
				//If this method is called from a compare and sync copy operation, the originalMap is not empty. 
				//We should only consider calling the copyReference for those objects drawn into the map
				//so far during the invocation of this method.
				//Or we may end up calling the copyReference for those unrelated objects and
				//seeing the weird result as described in the defect.

				//if (originalMap.containsKey(obj)) continue;
				copyReference(map, obj,exclusion,!cloneExternalReferenced || !cloneCrossModelExternalReferenced);    
			}
	    }
	    
	    for(int i=objs.length-1; i>=0; --i) {
			EObject cloned = (EObject) map.get(objs[i]);
			containers.add(0, cloned);	  

			//source obj is a root object which has no parent
			EStructuralFeature feature = service.getContainmentFeature(objs[i]);
			if (feature == null && underContainer(cloned, targetContainers[i])) {
				continue;
			}
			if(feature == null) {
			}
			else if(feature.isMany()){
				Collection l = (Collection) targetContainers[i].eGet(feature);
				if (!l.contains(cloned)) {
					l.add(cloned);
				}
			}
			else {
				targetContainers[i].eSet(feature,cloned);
			}
	    }

	    originalMap.putAll(map);
	    EObject[] r = new EObject[containers.size()];
		containers.toArray(r);
		
		// modify namespace if LDM model
		IPostprocessProvider exe = 
			PostProcessingRegistryReader.getInstance().getPostProcessingExecutable();
		if (exe != null)
		{
			exe.postProcess(r);		
		}		
		
		return r;
	}
	
	private static EObject[] cloneWithElementMap2(EObject[] targetContainers, EObject[] objs, Map map, boolean isCut, boolean cloneExternalReferenced) {
		return cloneWithElementMap2(targetContainers, objs, map, new LinkedList(), new LinkedList(), isCut, cloneExternalReferenced, true);
	}

	public static Collection findAllObjectsWithRequiredExternalReference(EObject container) {
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		Set internal = new HashSet();
		internal.add(container);
		internal.addAll(service.getAllContainedElements(container));

		Set goodObjects = new HashSet();
		Set badObjects = new HashSet();
		Set visited = new HashSet();
		Iterator it = internal.iterator();
		while(it.hasNext()) {
		    EObject e = (EObject) it.next();
		    if(!visited.contains(e)) {
		        hasRequiredExternalReference(e, internal, goodObjects, badObjects, visited);
		    }
		}
		
		return badObjects;
	}
	
	public static EObject findMappedElement(EObject x, Map map) {
		if(map.containsKey(x)) return (EObject) map.get(x);
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
	    List containers = service.getAllContainers(x);
	    containers.add(x);
	    Iterator it = containers.iterator();
	    EObject current = null;
	    EObject currentTarget = null;
	    while ((currentTarget == null) && it.hasNext()) {
		    current = (EObject) it.next();
		    currentTarget = (EObject) map.get(current);
	    }
	    if(currentTarget == null) return null;
	    while(it.hasNext()) {
		    current = (EObject) it.next();
		    EObject like = (EObject) map.get(current);
		    
		    if(like == null) {
		        like = findChildLike(currentTarget, current);
		        if(like != null) {
			        map.put(current, like);		        	
		        }
		    }
		    
		    if(like == null) {
		    	return null;
		    }
		    else {
		        currentTarget = like;
		    }
	    }
	    return currentTarget;
	}

	public static Collection findAllRequiredExternalReferencedObjects(EObject container, Map map) {
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		Set internal = new HashSet();
		Set external = new HashSet();
		internal.add(container);
		internal.addAll(service.getAllContainedElements(container));

		Iterator it = internal.iterator();
		while(it.hasNext()) {
		    EObject e = (EObject) it.next();
		    boolean useClientStrategy = false;
			if (e instanceof Privilege) {
				useClientStrategy = true;
			}
			findRequiredExternalReferencedObjects(e, internal, external, map, useClientStrategy);
		}
		
		return external;
	}
	
	private static void findRequiredExternalReferencedObjects(EObject obj, Collection internal, Collection external, Map map, boolean useClientStrategy) 
	{
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		Iterator it = obj.eClass().getEAllReferences().iterator();
		while(it.hasNext()) {
			EReference reference = (EReference) it.next();
			if(isRequired(reference) || isRequired(reference,obj)) {
				if(reference.isMany()) {
					Object collection = null;
					if (useClientStrategy) {
						try {
							collection = (Collection) ClientStrategyResolver.getInstance().getObjects(obj, reference, client);
						} catch (ClientStrategyResolverException exception) {
							collection = (Collection) obj.eGet(reference);
						}
					} else {
						collection = (Collection) obj.eGet(reference);
					}
					Iterator refs = ((Collection) collection).iterator();
					while(refs.hasNext()) {
						EObject referenced = (EObject) refs.next();
						if(internal.contains(referenced)) continue;
						if(external.contains(referenced)) continue;
						external.add(referenced);
						if(findMappedElement(referenced, map) == null) {
							findRequiredExternalReferencedObjects(referenced, internal, external, map, useClientStrategy);
						}
						Iterator containers = service.getAllContainers(referenced).iterator();
						while(containers.hasNext()) {
						    EObject container = (EObject) containers.next();
						    if(!external.contains(container)) {
								external.add(container);
								if(findMappedElement(container, map) == null) {
									findRequiredExternalReferencedObjects(container, internal, external, map, useClientStrategy);
								}
						    }
						}
					}
				}
				else {				
					EObject referenced = null;
					if (useClientStrategy) {
						referenced = (EObject) ClientStrategyResolver.getInstance().getObject(obj, reference, client);
					}
					if (referenced == null) {
						referenced = (EObject) obj.eGet(reference);
					}
					if(referenced != null) {
						if(internal.contains(referenced)) continue;
						if(external.contains(referenced)) continue;
						external.add(referenced);
						if(findMappedElement(referenced, map) == null) {
							findRequiredExternalReferencedObjects(referenced, internal, external, map, useClientStrategy);
						}
						Iterator containers = service.getAllContainers(referenced).iterator();
						while(containers.hasNext()) {
						    EObject container = (EObject) containers.next();
						    if(!external.contains(container)) {
								external.add(container);
								if(findMappedElement(container, map) == null) {
									findRequiredExternalReferencedObjects(container, internal, external, map, useClientStrategy);
								}
						    }
						}
					}
				}
			}
		}
	}
	
	public static EObject findOrCloneExternalObject(EObject x, EObject targetRoot, Collection addedContainers, Map clonedMap) {
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
	    List containers = service.getAllContainers(x);
	    containers.add(x);
	    //comment out the following line so that there is a chance to clone x in the target
	    //even though x does not have a logical container. (10/3/2013)
	    //if(containers.size() == 1) return targetRoot; 
	    Iterator it = containers.iterator();
//	    EObject current = (EObject) it.next();
	    EObject current = null;
	    EObject currentTarget = targetRoot;
	    while(it.hasNext()) {
	    	current = (EObject) it.next();
		    EObject like = (EObject) clonedMap.get(current);
		    
		    if(like == null) {
		        like = findChildLike(currentTarget, current);
		        if(like != null) {
			        clonedMap.put(current, like);		        	
		        }
		    }
		    
		    if(like == null) {
		        /* Get the cloning information provider, if there is
		         * one, for the current source object. */
		        ICloningInfoProvider cip = getProvider(current);
		          
		        /* If there is a cloning info provider and it indicates 
		         * the objects containment hierarchy should be cloned 
		         * when the object is externally referenced then clone 
		         * the hierarchy. */ 
		        if (cip != null &&
		            cip.cloneContainmentHierarchyOnExtRef(current)) {
		            Map containmentMap = cloneContainmentHierarchy(current, new LinkedList());
		            clonedMap.putAll(containmentMap);
		            currentTarget = (EObject)containmentMap.get(current);
		            addedContainers.add(currentTarget);
		        }
		        else {
		            EObject cloned = cloneSingleObject(current); 
		            clonedMap.put(current, cloned); 
		            currentTarget = cloned; 
		            addedContainers.add(cloned); 
		        }
		          
		    }
		    else {
		        currentTarget = like;
		    }
	    }
	    
	    return currentTarget;
	}
	
	public static EObject findChildLike(EObject container, EObject like) {
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		EStructuralFeature feature = service.getContainmentFeature(like);
		if ( feature != null && !feature.getContainerClass().isAssignableFrom(container.getClass())) return null;
		if(feature == null) {
		    Iterator it = service.getContainedElements(container).iterator();
		    while(it.hasNext()) {
		        EObject child = (EObject) it.next();
		        if(service.getContainmentFeature(child) == null) {
		            if(match(child, like)) return child;
		        }
		    }
		}
		else if(feature.isMany()){
		    Iterator it = ((Collection) container.eGet(feature)).iterator();
		    while(it.hasNext()) {
		        EObject child = (EObject) it.next();
	            if(match(child, like)) return child;
		    }
		    it = service.getContainedElements(container).iterator();
		    while(it.hasNext()) {
		        EObject child = (EObject) it.next();
	            if(match(child, like)) return child;
		    }
		}
		else {
		    EObject child = (EObject) container.eGet(feature);
		    return child;
		}
	    return null;
	}
	
	private static boolean match(EObject e1, EObject e2) {
	    if(e1.eClass() != e2.eClass()) return false;
	    if (SQLAccessControlPackage.eINSTANCE.getPrivilege().isSuperTypeOf(e1.eClass()))
	    	return AccessControlUtilities.match((Privilege)e1,(Privilege)e2);

	    if (SQLConstraintsPackage.eINSTANCE.getPrimaryKey().isSuperTypeOf(e1.eClass())) {
	    	if (((PrimaryKey)e1).getMembers().size() == ((PrimaryKey)e2).getMembers().size()) return true;
	    }

	    //(JYEH)do the same thing for logical data model primary keys
	    //except comparing the member sizes.
	    if ("com.ibm.db.models.logical.PrimaryKey".equals(e1.eClass().getInstanceClassName())) {
	    	return true;
	    }
	    
	    if (SQLSchemaPackage.eINSTANCE.getDependency().isSuperTypeOf(e1.eClass())) {
	    	return matchDependency((Dependency)e1, ((Dependency)e2));
	    }
	    
	    if(e1 instanceof ENamedElement) {
	        String name1 = ((ENamedElement) e1).getName();
	        String name2 = ((ENamedElement) e2).getName();
	        if(name1 != null) return name1.equals(name2);
	        if(name2 != null) return name2.equals(name1);
	    }


		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		EStructuralFeature feature = service.getContainmentFeature(e1);
		if(feature.isMany()) {
			Object v1 = service.getContainer(e1).eGet(feature);
			Object v2 = service.getContainer(e2).eGet(feature);
			if(v1 instanceof List) {
				return ((List) v1).indexOf(e1) == ((List) v2).indexOf(e2); 
			}
			return true;
		}
	    else {
		    return true;	    	
	    }
	}
	
	private static boolean matchDependency(Dependency d1, Dependency d2) {

		EObject e1 = d1.getTargetEnd();
		EObject e2 = d2.getTargetEnd();

		if (e1.eClass() != e2.eClass())
			return false;
		if (e1 instanceof ENamedElement) {
			String name1 = ((ENamedElement) e1).getName();
			String name2 = ((ENamedElement) e2).getName();
			if (name1 != null)
				return name1.equals(name2);
			if (name2 != null)
				return name2.equals(name1);
		}

		return false;
	}
	
	public static EObject getLeastCommonContainer(EObject obj1, EObject obj2) {
		if(obj1 == null || obj2 == null) return null;
		if(obj1 == obj2) return obj1;
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		EObject common = null;
	    List containers1 = service.getAllContainers(obj1);
	    List containers2 = service.getAllContainers(obj2);
	    Iterator i1 = containers1.iterator();
	    Iterator i2 = containers2.iterator();
	    while(i1.hasNext() && i2.hasNext()) {
	    	Object c1 = i1.next();
	    	Object c2 = i2.next();
	    	if(c1 == c2) {
	    		common = (EObject) c1;
	    	}
	    }
		return common;
	}
	
	public static boolean underContainer(EObject obj, EObject container) {
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
	    return service.getAllContainers(obj).contains(container);
	}
	
	public static void mapContainers(EObject srcContainer, EObject tgtContainer, Map map) {
		if(srcContainer == null || tgtContainer == null) return;
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
	    List srcContainers = service.getAllContainers(srcContainer);
	    srcContainers.add(srcContainer);
	    List tgtContainers = service.getAllContainers(tgtContainer);
	    tgtContainers.add(tgtContainer);
	    if(srcContainers.size() == tgtContainers.size()) {
	    	Iterator src = srcContainers.iterator();
	    	Iterator tgt = tgtContainers.iterator();
	    	while(src.hasNext()) {
	    		map.put(src.next(), tgt.next());
	    	}
	    } else {
	    	
	    	//Fix for issue wsdbu00696928
	    	//When copy a entity and paste to its parent package, can not reach the map statement
//	    	if (!srcContainers.contains(tgtContainer)) {
	    	//Fix defect 23607
	    	if (!map.containsKey(srcContainer)) {
	    		map.put(srcContainer,tgtContainer);
	    	}
	    }
	}

	private static boolean hasRequiredExternalReference(EObject obj, Collection internals, Collection goodObjects, Collection badObjects, Collection visited) {
		visited.add(obj);
		Iterator it = obj.eClass().getEAllReferences().iterator();
		while(it.hasNext()) {
			EReference reference = (EReference) it.next();
			if(isRequired(reference) || isRequired(reference,obj)) {
				if(reference.isMany()) {
					Iterator refs = ((Collection) obj.eGet(reference)).iterator();
					while(refs.hasNext()) {
						EObject referenced = (EObject) refs.next();
						if(badObjects.contains(referenced)) return true;
						if(goodObjects.contains(referenced)) continue;
						if(visited.contains(referenced)) continue;
						if(internals.contains(referenced)) {
							boolean good = hasRequiredExternalReference(referenced, internals, goodObjects, badObjects, visited);
							if(good) continue;
						}
						badObjects.add(obj);
						return true;						
					}
				}
				else {
					EObject referenced = (EObject) obj.eGet(reference);
					if(referenced != null) {
						if(badObjects.contains(referenced)) return true;
						if(goodObjects.contains(referenced)) continue;
						if(visited.contains(referenced)) continue;
						if(internals.contains(referenced)) {
							boolean good = hasRequiredExternalReference(referenced, internals, goodObjects, badObjects, visited);
							if(good) continue;
						}
						badObjects.add(obj);
						return true;
					}
				}
			}
		}
		goodObjects.add(obj);
		return false;
	}
	
	public static void copyReference(Map map, EObject src, boolean keepOriginalReference) {
		copyReference(map,src,new HashSet(),keepOriginalReference);
	}

	public static void copyReference(Map map, EObject src,Collection exclude, boolean keepOriginalReference) {
		copyReference(map,src,new HashSet(),keepOriginalReference, null);
	}

	/*
	 * This overloaded version of the copyReference method was created to handle
	 * a special case issue in which privileges of data objects outside the
	 * scope of a copy operation should not be accessed.  Outside of compare
	 * synchronization operations, this method should not be directly called
	 * without careful consideration.
	 */
	public static void copyReference(Map map, EObject src,Collection exclude, boolean keepOriginalReference, EObject[] copiedObjects) {
		Iterator it = src.eClass().getEAllStructuralFeatures().iterator();
		EObject cloned = (EObject) map.get(src); 
		while(it.hasNext()) {
			EStructuralFeature reference = (EStructuralFeature) it.next();
			if (!isRequired(reference) && !isRequired(reference,src)) continue;
			if(!reference.isChangeable()) continue;
			if(reference.isDerived()) continue;
			
			// Only copy privileges references which pertain to the selected objects to be
			//   copied.
			if (copiedObjects != null &&
					copiedObjects.length != 0 &&
					reference.equals(SQLSchemaPackage.eINSTANCE.getSQLObject_Privileges())) {
				boolean bypass = true;
				for (EObject obj:copiedObjects) {
					if (obj == src) {
						bypass = false;
						continue;
					}
				}
				if (bypass) continue;
			}
			
			if(reference instanceof EReference) {
				if(reference.isMany()) {
					Collection srcCollection = (Collection) src.eGet(reference);
					if(srcCollection.size() > 0) {
						if (srcCollection instanceof EObjectEList) {
							EObject srcContainer =(( EObjectEList)srcCollection).getEObject();
							Collection targetCollection = (Collection) cloned.eGet(reference);
							EObject targetContainer =(( EObjectEList)targetCollection).getEObject();
							if (!targetContainer.getClass().isAssignableFrom(srcContainer.getClass())) {
								continue;
							}
							Vector elements = new Vector();
							if(targetCollection.size() == 0) {
								Iterator refs = srcCollection.iterator();
								while(refs.hasNext()) {
									Object srcReference = refs.next();
									if (exclude.contains(srcReference)) continue;
									if(map.containsKey(srcReference) && srcReference != map.get(srcReference)) {
										elements.add(map.get(srcReference));
									}
									else if(isRequired(reference) || isRequired(reference,src)) {
										EObject mapped = findMappedElement((EObject) srcReference, map);
										//Use Case: the src object is copy/pasted into the same container.
										//For instance, a LUWPartitionGroupImpl is copy/pasted into the
										//same database and reference is tableSpaces. A table space
										//referenced from src is mapped to itself by the findMappedElement
										//method. 									
										//As soon as we add the table space from srcCollection into targetCollection,
										//the table space will be gone from the srcCollection. This is not desirable.
										//Another use case would be tables and their privileges.
										//if(mapped != null) {
										if(mapped != null && mapped != srcReference) {
											elements.add(map.get(srcReference));
										}
									}
								}

								for (int i = 0; i <elements.size(); i++ ) {
									targetCollection.add(elements.elementAt(i));
								}
							}
						}
						else if (srcCollection instanceof EMap && 
								((EMap)srcCollection).get(0) instanceof EStringToStringMapEntryImpl) {
							// Copy EMaps which contain String keys and values
							//   This was added to support EAnnotation.details,
							//   specifically for CharacterStringDataType length semantics
							EMap targetCollection = (EMap) cloned.eGet(reference);
							Set<String> keys = ((EMap)srcCollection).keySet();
							Iterator<String> keyIter = keys.iterator();
							for (String key:keys) {
								targetCollection.put(key,((EMap)srcCollection).get(key));
							}
						}
					}
				}
				else {
					EObject srcReference = (EObject) src.eGet(reference);
					if(srcReference != null) {
						if (exclude.contains(srcReference)) continue;
						Object targetReference = cloned.eGet(reference);
						if(targetReference == null ) {
							if(map.containsKey(srcReference)) {
								cloned.eSet(reference, map.get(srcReference));
							}
							else if(isRequired(reference)) {
								EObject mapped = findMappedElement((EObject) srcReference, map);
								if(mapped != null) {
									cloned.eSet(reference, mapped);								
								} else if (isShallowCopyRecommended(src, reference, keepOriginalReference)){
									cloned.eSet(reference, srcReference);								
								}
							}
						}
					}
				}
			}
            else if (FeatureMapUtil.isFeatureMap(reference)) {
                FeatureMap featureMap = (FeatureMap)src.eGet(reference);
                FeatureMap copyFeatureMap = (FeatureMap) cloned.eGet(reference);
                Iterator k = featureMap.iterator();
                while(k.hasNext()) {
                    FeatureMap.Entry featureMapEntry = (FeatureMap.Entry)k.next();
                    EStructuralFeature feature = featureMapEntry.getEStructuralFeature();
                    if(feature instanceof EReference) {
                        Object referencedEObject = featureMapEntry.getValue();
                        Object copyReferencedEObject = map.get(referencedEObject);
                        copyFeatureMap.add(feature, copyReferencedEObject == null ? referencedEObject : copyReferencedEObject);
                    }
                    else {
                        copyFeatureMap.add(featureMapEntry);
                    }
                }
            }			
		}































	}

	// Made this public for use in SynchronizationCloneUtilities - rdr 06/24/2011
	/**
	 * This method should only be called from the copyReference method for the purpose of determining
	 * whether the shallow copy of a reference is recommended when the referenced object
	 * is not mapped to the target yet.
	 * @param src The source object whose references are being copied over to the corresponding object 
	 *            in the target.
	 * @param reference The particular reference under consideration.
	 * @param keepOriginalReference The default recommendation.
	 * @return
	 */
	public static boolean isShallowCopyRecommended(EObject src, EStructuralFeature reference, boolean keepOriginalReference) {
		if (!keepOriginalReference) return false;
		
		//A conservative implementation.
		boolean isRequired = true;
		if ("Column".equals(reference.getEContainingClass().getName())) { //$NON-NLS-1$
			if("identitySpecifier".equals(reference.getName())) { //$NON-NLS-1$
				//It does not make sense to have the corresponding column reference the identitySpecifier that
				//is referenced by the source column.
				isRequired = false;
			}
		}
		return isRequired;
		//Here is an alternative solution that makes sense but may have broader impacts.
		//From my understanding, the shallow copy of the reference makes sense only when
		//the referenced object is in a different model.
//		EObject srcReference = (EObject) src.eGet(reference);
//		return isCrossModelObject(srcReference, src);
	}
	
	public static Map cloneContainmentHierarchy(EObject obj, Collection exclude) {
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		Map map = new  LinkedHashMap();

		if(exclude.contains(obj)) return map;
		EObject cloned = cloneSingleObject(obj);
		map.put(obj, cloned);
		Iterator it = service.getContainedElements(obj).iterator();
		Set children = new HashSet();
		while(it.hasNext()) {
			Object o = it.next();
			if(!exclude.contains(o)) {
				children.add(o);
			}
		}
		while(!children.isEmpty()) {
			EObject child = (EObject) children.iterator().next();
			EStructuralFeature feature = service.getContainmentFeature(child);
			if(feature != null && feature.getContainerClass().isAssignableFrom(obj.getClass()))  {
				if(feature.isMany()) {
					Collection l = (Collection) cloned.eGet(feature);
					it = ((Collection) obj.eGet(feature)).iterator();
					Vector childrenVec = new Vector();
					while(it.hasNext()) {
						EObject c = (EObject) it.next();
						if(children.contains(c)) {
							childrenVec.add(c);
						}
					}
					for (int iVec = 0; iVec < childrenVec.size();iVec++ ){
						EObject c = (EObject) childrenVec.get(iVec);
						children.remove(c);
						map.putAll(cloneContainmentHierarchy(c, exclude));
						l.add(map.get(c));
						
					}
				}
				else {
					map.putAll(cloneContainmentHierarchy(child, exclude));
					cloned.eSet(feature, map.get(child));
					children.remove(child);					
				}
			}
			else {
				children.remove(child);				
			}
		}
		return map;
	}
	
	public static EObject cloneSingleObject(EObject src) {
		EClass c = src.eClass();
		EObject target = c.getEPackage().getEFactoryInstance().create(c);
		Iterator it = c.getEAllAttributes().iterator();
		while(it.hasNext()) {
			EAttribute a = (EAttribute) it.next();
			if(a.isChangeable() && !a.isDerived()) copyAttribute(a, src, target);
		}

		return target;
	}
	
	private static void copyAttribute(EAttribute attribute, EObject src, EObject target) {
        if(src.eIsSet(attribute)) {
	        if (FeatureMapUtil.isFeatureMap(attribute)) {
	            FeatureMap srcFeatureMap = (FeatureMap) src.eGet(attribute);
	            FeatureMap tgtFeatureMap = (FeatureMap) src.eGet(attribute);
	            Iterator i = srcFeatureMap.iterator();
	            while(i.hasNext()) {
	              FeatureMap.Entry entry = (FeatureMap.Entry)i.next();
	              EStructuralFeature feature = entry.getEStructuralFeature();
	              if(!(feature instanceof EReference)) {
	                  tgtFeatureMap.add(entry);
	              }
	            }
	        }
	 		else if(attribute.isMany()) {
	 			((Collection)target.eGet(attribute)).addAll((Collection) src.eGet(attribute));
	        }
	        else {	        	
	        	String attrType = attribute.getName();
	        	if (!("namespace".equalsIgnoreCase(attrType) || "namespaceSupported".equalsIgnoreCase(attrType))) //$NON-NLS-1$
				{	        	
	        		target.eSet(attribute, src.eGet(attribute));
				}
	        }
	 	}
	}
	
	// Made this public for use in SynchronizationCloneUtilities - rdr 06/24/2011
	public static boolean isRequired(EStructuralFeature feature) {
		//This method is a hot spot in the performance profile. The code has been enhanced
		//using a hash table mechanism. From now on, exceptions should be added through the
		//new extension point, requiredFeature.
		Hashtable featureNames = (Hashtable)requiredFeatures.get(feature.getEContainingClass().getName());
		if (featureNames != null) {
			Boolean booleanObj = (Boolean)featureNames.get(feature.getName());
			if (booleanObj != null) return booleanObj.booleanValue();
		}

		if (feature.isRequired())
			return true;
		if (!feature.isMany()) {
			if (feature instanceof EReference) {
				EReference ref = (EReference) feature;
				EReference opposite = ref.getEOpposite();
				if (opposite == null)
					return true;
				if (opposite.isMany())
					return true;
			}
		}

		return false;
	}

	// This method was added specifically to allow the length semantics to be copied for
	//   character string data types.  If other applications are found for this method, it
	//   may improve performance to use an extension-point, as with isRequired(EStructuralFeature)
	// This method was used instead of the requiredFeatures extension-point because that
	//   extension-point only allows the qualification of the feature by its defining
	//   container (in the eAnnotations case, that is EModelElement, which would make ALL
	//   eAnnotations "required").  We still rely on the requiredFeatures extension-point
	//   to make the details feature of EAnnotation required.
	public static boolean isRequired(EStructuralFeature feature,EObject obj) {
		if (feature != null && 
				obj instanceof CharacterStringDataType && 
				feature.getName().equals("eAnnotations")) { //$NON-NLS-1$
			return true;
		}
		return false;
	}

	private static void reParent(EObject obj,EObject sourceContainer, EObject targetContainer,Map map){
		if (targetContainer == null) return;
		EObject sourceObj = obj;
		EObject cloned = (EObject) map.get(sourceObj);
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		EStructuralFeature feature = service.getContainmentFeature(obj);
		if(feature == null) {
			return;
		}
		else if(feature.isMany()){
			Collection l = (Collection) targetContainer.eGet(feature);
			if (!l.contains(cloned)) {
				l.add(cloned);
			}
		}
		else {
		    targetContainer.eSet(feature,cloned);
		}

		if (sourceContainer == null || targetContainer == null ) return;
		//resource the srcObject and cloned if sourceContainer and targetContainer are in different resource file when cut/paste
		Resource srcResource = sourceContainer.eResource();
		Resource targetResource = targetContainer.eResource();
		
		if (srcResource != null && targetResource != null) {
			if (srcResource != targetResource) {
				if (cloned.eContainer() != targetContainer ) {
					// keep src object ID for the cloned
					String srcID = null;
					if ((srcResource instanceof XMLResourceImpl) && (targetResource instanceof XMLResourceImpl)){
		            	XMLResourceImpl srcXmlResource = (XMLResourceImpl) srcResource;
		            	srcID = srcXmlResource.getID(sourceObj);
		            	if(srcID != null) {
		            		XMLResourceImpl targetXmlResource = (XMLResourceImpl) targetResource;
		            		targetXmlResource.setID(cloned, srcID);
		            	}
					}
					targetResource.getContents().add(cloned);
					srcResource.getContents().remove(sourceObj);
				}
				reResource(cloned);
			}
		}
	}
	
	public static void reResource(EObject obj){
		final ContainmentService service = ContainmentServiceImpl.INSTANCE;
		Resource targetResource = obj.eResource();
		Iterator it = service.getContainedElements(obj).iterator();
		while (it.hasNext()) {
			EObject child = (EObject)it.next();
			if (obj.eContents().contains(child)) {
				reResource((EObject)child);
				continue;
			}
			if (!targetResource.getContents().contains(child)) {
				targetResource.getContents().add(child);
				reResource((EObject)child);
			}
			
		}
	}

	public static boolean isCrossModelObject(EObject obj1, EObject obj2){
		//Note: objects from the database explorer may not have a resource associated with them
		Resource srcResource = obj1.eResource();
		Resource targetResource = obj2.eResource();
		return (srcResource != null && targetResource != null && srcResource != targetResource);

	}
    private static Hashtable getProviders()
	{
	    Hashtable packages = new Hashtable();
	    IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
	    IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "CloningInfoProvider"); //$NON-NLS-1$ //$NON-NLS-2$
	    IExtension[] extensions = extensionPoint.getExtensions();
	    for(int i=0; i<extensions.length; ++i) {
	      IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
	      for(int j=0; j<configElements.length; ++j) {
	        if(configElements[j].getName().equals("cloning")) { //$NON-NLS-1$
	          String packageURI = configElements[j].getAttribute("package"); //$NON-NLS-1$
	          String className = configElements[j].getAttribute("class"); //$NON-NLS-1$
	          ICloningInfoProvider provider = null;
	          try {
	            provider = (ICloningInfoProvider) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
	          }
	          catch(CoreException e) {
	              IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getDefault().getBundle().getSymbolicName(), IStatus.ERROR,
	                      "The error was detected when creating the cloning info provider for " + className + " in "+ packageURI, //$NON-NLS-1$ //$NON-NLS-2$
	                      e);
	            RDBCorePlugin.getDefault().getLog().log(status);
	            continue;
	          }

	          if(packages.containsKey(packageURI)) {
	            ((Hashtable) packages.get(packageURI)).put(className, provider);
	          }
	          else {
	            Hashtable classNames = new Hashtable();
	            classNames.put(className, provider);
	            packages.put(packageURI, classNames);
	          }
	        }
	      }
	    }
	    return packages;
    }
	  
    private static ICloningInfoProvider getProvider(EObject eObj) {
	    EClass clazz = (EClass) eObj.eClass();
	    String uri = clazz.getEPackage().getNsURI();
	    if(packages.containsKey(uri)) {
	      Hashtable classNames = (Hashtable) packages.get(uri);
	      if(classNames.containsKey(clazz.getName())) {
	        return (ICloningInfoProvider) classNames.get(clazz.getName());
	      }
	    }
	    return null;
	}

    private static Hashtable getRequiredFeatures()
	{
	    Hashtable features = new Hashtable();
	    IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
	    IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("com.ibm.datatools.core", "requiredFeature"); //$NON-NLS-1$ //$NON-NLS-2$
	    IExtension[] extensions = extensionPoint.getExtensions();
	    for(int i=0; i<extensions.length; ++i) {
	      IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
	      for(int j=0; j<configElements.length; ++j) {
	        if(configElements[j].getName().equals("featureInfo")) { //$NON-NLS-1$
	          String containingClass = configElements[j].getAttribute("containingClass"); //$NON-NLS-1$
	          String featureName = configElements[j].getAttribute("featureName"); //$NON-NLS-1$
	          String isRequired = configElements[j].getAttribute("isRequired"); //$NON-NLS-1$
	          if(features.containsKey(containingClass)) {
	        	  ((Hashtable) features.get(containingClass)).put(featureName, new Boolean(isRequired));
	          }
	          else {
	        	  Hashtable featureNames = new Hashtable();
	        	  featureNames.put(featureName, new Boolean(isRequired));
	        	  features.put(containingClass, featureNames);
	          }
	        }
	      }
	    }
	    return features;
    }

    /**
     * This API is added for use by ReferenceItemCloneCommand.
     * This method will clone u in d2 with the exceptions as specified by the
     * two exclusion collections as follows:
     * The containmentExclusion is to exclude those contained within d2.
     * The externalExclusion is to exclude those externally referenced objects from the containment hierarchy of d2.
     * 
     * @param d2
     * @param u
     * @param map
     * @param containmentExclusion
     * @param externalExclusion
     * @param cloneCrossModelReference
     * @return
     */
	public static EObject[] cloneWithElementMap(final EObject d2, final EObject u,
			final Map map, final Collection containmentExclusion, final Collection externalExclusion,
			final boolean cloneCrossModelReference) {
        final List result = new ArrayList(1);
//<bgp        DB2PluginActivator.getInstance().getCommandManager().runCommand(new Runnable()
//        {
//            public void run()
//            {
//            	result.add(cloneWithElementMap2(new EObject[] {d2}, new EObject[] {u}, map, containmentExclusion , externalExclusion, false, true, cloneCrossModelReference));
//            }
//bgp>        });
        return (EObject[]) result.toArray(new Object[1])[0];
	}


	/**
	 * This is a special version of the clone method that does not use a command.
	 * This is used by Compare and Sync when creating a snapshot of catalog objects.
	 * @param targetContainer
	 * @param obj
	 * @return
	 */
	public static EObject[] clone2Target(EObject targetContainer,
			EObject obj) {
		final List result = new ArrayList(1);
		boolean isCut = false;
		boolean cloneExternalReferenced = true;
		result.add(cloneWithElementMap2(
				new EObject[] { targetContainer }, new EObject[] { obj },
				new HashMap(), new LinkedList(), new LinkedList(), isCut,
				cloneExternalReferenced, true));
		return (EObject[]) result.toArray(new Object[1])[0];
	}
}
