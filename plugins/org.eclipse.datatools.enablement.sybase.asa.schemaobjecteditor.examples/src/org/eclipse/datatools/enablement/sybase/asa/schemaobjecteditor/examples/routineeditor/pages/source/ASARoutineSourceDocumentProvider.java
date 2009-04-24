/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.source;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.routineeditor.ui.SQLRoutineDocumentProvider;
import org.eclipse.jface.text.source.IAnnotationModel;

/**
 * This is a DocumentProvider class for ASA/IQ routine source editor
 * @author songj
 */
public class ASARoutineSourceDocumentProvider extends SQLRoutineDocumentProvider
{
    protected IAnnotationModel createAnnotationModel(Object element) throws CoreException
    {
        //Don't create the annotation model to disable to show the breakpoint.
        
        //[CR543192]:Since parser needs annotationModel
        // modified it to extend SQLRoutineDocumentProvider to return a RoutineAnnotationModel
        // and as well as avoid showing breakpoint
        return super.createAnnotationModel(element);
    }
    
    
}
