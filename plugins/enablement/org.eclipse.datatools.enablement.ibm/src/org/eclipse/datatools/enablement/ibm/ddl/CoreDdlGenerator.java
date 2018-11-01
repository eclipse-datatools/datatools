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
package org.eclipse.datatools.enablement.ibm.ddl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.connectivity.sqm.core.rte.DDLGenerator;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.core.rte.IEngineeringCallBack;
import org.eclipse.datatools.enablement.ibm.catalog.ICatalogAuthorizationIdentifier;
import org.eclipse.datatools.enablement.ibm.util.DependencyImpactAnalyst;
import org.eclipse.datatools.enablement.ibm.util.DependencyImpactDescription;
import org.eclipse.datatools.enablement.ibm.util.EngineeringOptionID;
import org.eclipse.datatools.modelbase.sql.schema.Dependency;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.EObject;

public abstract class CoreDdlGenerator implements DDLGenerator {
	
	protected Set<SQLObject> suppressCreateElementSet = null;
	
    /**
     * @deprecated 
    */
    public String[] generateDDL(SQLObject[] elements, IProgressMonitor progressMonitor){
        accessControlInitialize(elements,progressMonitor);
        return this.generateDDL(elements, progressMonitor, null);
    }
    /**
     * @deprecated 
    */
    public String[] createSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor){
        return this.createSQLObjects(elements, quoteIdentifiers, qualifyNames, progressMonitor,null);
    }
    /**
     * @deprecated 
    */
    public String[] dropSQLObjects(SQLObject[] elements, boolean quoteIdentifiers, boolean qualifyNames, IProgressMonitor progressMonitor){
        return this.dropSQLObjects(elements, quoteIdentifiers, qualifyNames, progressMonitor,null);
    }

	
	public String[] generateDDL(SQLObject[] elements, SQLObject[] impacts, IProgressMonitor progressMonitor,IEngineeringCallBack callback){
		accessControlInitialize(elements,progressMonitor);
		Vector v = new Vector(Arrays.asList(elements));
		Vector v_impacts = new Vector(Arrays.asList(impacts));
		for (int i = 0; i < v_impacts.size(); i++){
			SQLObject impact = (SQLObject) v_impacts.elementAt(i);
			if (v.contains(impact)) continue;
			this.calculateDependency(v, v_impacts, impact);
		}

		SQLObject[] allElements = new SQLObject[v.size()];
		v.copyInto(allElements);
		
		return this.generateDDL(allElements, progressMonitor,callback);
	}
	
    public String[] createSQLObjects(SQLObject[] elements, SQLObject[] impacts, IProgressMonitor progressMonitor,IEngineeringCallBack callback){
		Vector v = new Vector(Arrays.asList(elements));
		Vector v_impacts = new Vector(Arrays.asList(impacts));
		for (int i = 0; i < v_impacts.size(); i++){
			SQLObject impact = (SQLObject) v_impacts.elementAt(i);
			if (v.contains(impact)) continue;
			this.calculateDependency(v, v_impacts, impact);
		}
		SQLObject[] allElements = new SQLObject[v.size()];
		v.copyInto(allElements);
		EngineeringOption[] options = this.getOptions(allElements);
    	return this.createSQLObjects(allElements, EngineeringOptionID.generateQuotedIdentifiers(options), EngineeringOptionID.generateFullyQualifiedNames(options), progressMonitor,callback);
    	
    }
    public String[] dropSQLObjects(SQLObject[] elements, SQLObject[] impacts, IProgressMonitor progressMonitor,IEngineeringCallBack callback){
		Vector v = new Vector(Arrays.asList(elements));
		Vector v_impacts = new Vector(Arrays.asList(impacts));
		for (int i = 0; i < v_impacts.size(); i++){
			SQLObject impact = (SQLObject) v_impacts.elementAt(i);
			if (v.contains(impact)) continue;
			this.calculateDependency(v, v_impacts, impact);
		}
		SQLObject[] allElements = new SQLObject[v.size()];
		v.copyInto(allElements);
		EngineeringOption[] options = this.getOptions(allElements);
    	return this.dropSQLObjects(allElements, EngineeringOptionID.generateQuotedIdentifiers(options), EngineeringOptionID.generateFullyQualifiedNames(options), progressMonitor,callback);
    }
	
    public String[] alterTableDropColumn(SQLObject[] columns, SQLObject[] impacts, IProgressMonitor progressMonitor,IEngineeringCallBack callback){
    	return null;
    }
    
	private void calculateDependency(Vector v,Vector impacts, SQLObject currentImpact){
		Iterator iter = currentImpact.getDependencies().iterator();
		while (iter.hasNext()) {
			Dependency dependency = (Dependency) iter.next();
			SQLObject targetEnd = (SQLObject) dependency.getTargetEnd();
			if (impacts.contains(targetEnd)) {
				this.calculateDependency(v, impacts,targetEnd);
			}
		}
		v.add(currentImpact);
	}

	protected boolean checkModel(SQLObject[] sqlObjects, EngineeringOption[] options) {
		boolean ret = true;
		if (! EngineeringOptionID.checkModel(options)) return ret;
		
		ModelValidationProvider provider = DdlGenerationUtility.getModelValidationProvider();
		if (provider != null) {
			ret = provider.checkModel(sqlObjects);
		}
		return ret;
	}

    protected EngineeringOption[] getSelectedOptions(SQLObject[] elements) {
    	// Default behavior is to load options again.  Subclasses can cache options as necessary.
       	return this.getOptions(elements);
    }
    
	protected void accessControlInitialize(SQLObject[] elements,IProgressMonitor progressMonitor) {
		EngineeringOption[] options = this.getSelectedOptions(elements);
		for (int j=0;j<options.length;j++) {
			if (options[j].getId().equals(EngineeringOptionID.GENERATE_PRIVILEGE)) {
				if (options[j].getBoolean()) {
					for (int i=0;i<elements.length;i++) {
						SQLObject element = elements[i];
						if (element instanceof ICatalogAuthorizationIdentifier) {
							String type = element.eClass().getName();
							String name = " <" + type + "> " + element.getName();  //$NON-NLS-1$//$NON-NLS-2$
							progressMonitor.subTask(DdlGenerationMessages.LOADING_PRIVILEGES_PROGRESS + name);
							((ICatalogAuthorizationIdentifier) element).getCatalogReceivedPrivileges();
							progressMonitor.subTask(null);
						}
					}
				}
				else return;
			}
		}
	}
	
	/**
	 * Get the builder that is associated with the generator.
	 * Subclass needs to override this method if needed.
	 * @return
	 */
	public DdlBuilder getDdlBuilder() {
		return null;
	}
	
	
	public void suppressCreate(SQLObject element) {
		if (suppressCreateElementSet == null)
			suppressCreateElementSet = new HashSet<SQLObject>();
		suppressCreateElementSet.add(element);
	}

	public void suppressCreate(Set<SQLObject> elements) {
		if (suppressCreateElementSet == null)
			suppressCreateElementSet = new HashSet<SQLObject>();
		suppressCreateElementSet.addAll(elements);
	}
	
	protected Set<SQLObject> removeCreateSuppressedElements(Set<SQLObject> elements) {
		if (suppressCreateElementSet == null) return elements;
		elements.removeAll(suppressCreateElementSet);
		return elements;
	}
	
	protected Set sortDependency(Collection set){
        LinkedHashSet sorted = new LinkedHashSet();
        for (Iterator iter= set.iterator(); iter.hasNext();){
            dependencySorting(sorted,set,(EObject)iter.next());
        }
        return sorted;
    }
    
	private void dependencySorting(LinkedHashSet sorted,Collection orginalSet, EObject object){
        if (sorted.contains(object)) return;
        if (enableDependency(object)) {
            DependencyImpactAnalyst depAnalayst = DependencyImpactAnalyst.getInstance();
            DependencyImpactDescription[] array = depAnalayst.getDirectDependencies( object );
            if(array!=null && array.length > 0){
                for(int i = array.length-1;i >= 0;i--){
                    DependencyImpactDescription depend = (DependencyImpactDescription)array[i];
                    EObject targetType = depend.getTarget();
                    if (orginalSet.contains(targetType) && !sorted.contains(targetType)){
                        dependencySorting(sorted,orginalSet,targetType);
                    }
                }
            }
        }
        sorted.add(object);
    }

    protected Set sortImpact(Collection set){
        LinkedHashSet sorted = new LinkedHashSet();
        for (Iterator iter= set.iterator(); iter.hasNext();){
            impactSorting(sorted,set,(EObject)iter.next());
        }
        //Dependency map should be cleared at the end of call
        DependencyImpactAnalyst depAnalayst = DependencyImpactAnalyst.getInstance();
        depAnalayst.clearDependencyMap();
        return sorted;
    }

    private void impactSorting(LinkedHashSet sorted,Collection orginalSet, EObject object){
        if (sorted.contains(object)) return;
        if (enableDependency(object)) {
            DependencyImpactAnalyst depAnalayst = DependencyImpactAnalyst.getInstance();
            DependencyImpactDescription[] array = depAnalayst.getDirectImpacted2(object );
            if(array!=null && array.length > 0){
                for(int i = array.length-1;i >= 0;i--){
                    DependencyImpactDescription depend = (DependencyImpactDescription)array[i];
                    EObject targetType = depend.getTarget();
                    if (orginalSet.contains(targetType) && !sorted.contains(targetType)){
                        impactSorting(sorted,orginalSet,targetType);
                    }
                }
            }
        }
        sorted.add(object);
    }

    //individual ddlGnerator enable this bit
    protected boolean enableDependency(EObject object) {
      return false;
    }

}
