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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provides content for a tree viewer that shows only containers.
 * 
 * The original implementation of this class was copied from
 * org.eclipse.ui.internal.ide.misc.ContainerContentProvider.
 */
public class DataContainerContentProvider implements ITreeContentProvider {
    private boolean showClosedProjects = true;

    // show all files by default
    private String[] fileFilter = new String[0];

    // show files by default
    private boolean showFiles = true;

    private String[] projectNatureFilter = new String[0];
    
    private String[] filesToExclude = new String[0];

    /**
     * Creates a new ResourceContentProvider.
     */
    public DataContainerContentProvider() {
    }

    /**
     * The visual part that is using this content provider is about to be
     * disposed. Deallocate all allocated SWT resources.
     */
    public void dispose() {
    }

    /**
     * @see ITreeContentProvider#getChildren
     */
    public Object[] getChildren(Object element) {
        Object[] theChildren = new Object[0];

        if (element instanceof IWorkspace) {
            // check if closed projects should be shown
            IProject[] allProjects = ((IWorkspace) element).getRoot()
                    .getProjects();
            if (showClosedProjects) {
                theChildren = allProjects;
            } else {
                ArrayList accessibleProjects = new ArrayList();
                for (int i = 0; i < allProjects.length; i++) {
                    if (allProjects[i].isOpen()) {
                        accessibleProjects.add(allProjects[i]);
                    }
                }
                theChildren = accessibleProjects.toArray();
            }
            if (this.projectNatureFilter.length > 0) {
                ArrayList filteredProjects = new ArrayList();
                for (int i = 0; i < theChildren.length; i++) {
                    try {
                        boolean isProjectAllowed = false;
                        for (int natureIndex = 0; natureIndex < this.projectNatureFilter.length; natureIndex++) {
                            if (((IProject) theChildren[i])
                                    .isNatureEnabled(projectNatureFilter[natureIndex])) {
                                isProjectAllowed = true;
                            }
                            if (isProjectAllowed) {
                                filteredProjects.add(theChildren[i]);
                            }
                        }
                    } catch (Exception e) {
                        // If exception occurs move onto next project
                    }
                }
                theChildren = filteredProjects.toArray();
            }
        } else if (element instanceof IContainer) {
            IContainer container = (IContainer) element;
            if (container.isAccessible()) {
                try {
                    List children = new ArrayList();
                    IResource[] members = container.members();
                    for (int i = 0; i < members.length; i++) {
                        if (members[i].getType() != IResource.FILE) {
                            children.add(members[i]);
                        }
                        
                        if (this.showFiles && (members[i].getType() == IResource.FILE)) {
                            if (satisfiesFileFilter((IFile)members[i]) && satifiesExcludedFiles((IFile)members[i])){
                                children.add(members[i]);
                            }
                        }
                    }
                    theChildren = children.toArray();
                } catch (CoreException e) {
                    // this should never happen because we call #isAccessible
                    // before invoking #members
                }
            }
        }
        return theChildren;
    }

    private boolean satisfiesFileFilter(IFile resource){
        boolean isSatisfied = false;
        if ((this.fileFilter == null) || (this.fileFilter.length == 0)){
            isSatisfied = true;
        } else {
           for (int index = 0; index < this.fileFilter.length; index++){
               if ((resource.getFileExtension() != null) && (fileFilter[index].toLowerCase().equals(resource.getFileExtension().toLowerCase()))){
                   isSatisfied = true;
               }
           }
        }
        
        return isSatisfied;
    }
      
    private boolean satifiesExcludedFiles(IFile resource){
        boolean isSatisfied = false;
        if ((this.filesToExclude == null) || (this.filesToExclude.length == 0)){
            isSatisfied = true;
        } else {
            boolean isExcluded = false;
           for (int index = 0; index < this.filesToExclude.length; index++){
               if (filesToExclude[index].equals(resource.getFullPath().toOSString())){
                   isExcluded = true;
               }
           }
           if (!isExcluded){
               isSatisfied = true;
           }
        }
        
        return isSatisfied;
    }
    
    /**
     * @see ITreeContentProvider#getElements
     */
    public Object[] getElements(Object element) {
        return getChildren(element);
    }

    /**
     * @see ITreeContentProvider#getParent
     */
    public Object getParent(Object element) {
        if (element instanceof IResource)
            return ((IResource) element).getParent();
        return null;
    }

    /**
     * @see ITreeContentProvider#hasChildren
     */
    public boolean hasChildren(Object element) {
        return getChildren(element).length > 0;
    }

    /**
     * @see IContentProvider#inputChanged
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
    }

    /**
     * Specify whether or not to show closed projects in the tree viewer.
     * Default is to show closed projects.
     * 
     * @param show
     *            boolean if false, do not show closed projects in the tree
     */
    public void showClosedProjects(boolean show) {
        showClosedProjects = show;
    }

    public void showFiles(boolean show) {
        this.showFiles = show;
    }

    public void setFileFilter(String[] filter) {
        this.fileFilter = filter;
    }

    /**
     * Specify the natures of projects to be displayed. Default is to show
     * projects regardless of nature.
     * 
     * @param naturesToShow
     *            String[] containing the project natures a project must have to
     *            be displayed
     */
    public void setProjectNatureFilter(String[] naturesToShow) {
        this.projectNatureFilter = naturesToShow;
    }
    
    public void setExcludedFiles(String[] filesToExclude){
        this.filesToExclude = filesToExclude;
    }
    
}