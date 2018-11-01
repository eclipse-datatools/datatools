/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.editorext;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.debugger.model.SPDebugModelUtil;
import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineAnnotationModel;
import org.eclipse.jface.text.IDocument;

/**
 * Temparary implementation. Need do more investigation on how annotationmodel work.
 * 
 * @author Yang Liu
 * @author Hui Cao
 */
public class SPBreakpointAnnotationModel extends RoutineAnnotationModel
{
    /**
     * 
     */
    public SPBreakpointAnnotationModel(ProcIdentifier procid)
    {
        super(procid);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractMarkerAnnotationModel#isAcceptable(org.eclipse.core.resources.IMarker)
     */
    protected boolean isAcceptable(IMarker marker)
    {
        return SPDebugModelUtil.isSPBreakpointMarker(marker, _procIdentifier);
    }

    protected boolean isAffected(IMarkerDelta markerDelta)
    {
        return SPDebugModelUtil.isSPBreakpointMarkerDelta(markerDelta, _procIdentifier);
    }

    /*
     * See bug 363935
     * 
     * the annotation model will add position into document, and the document will
     * automatically update the positions. So when user modify the file, the positions
     * get updated, and the annotation get moved without changing the IMarker.
     * after user save, we will call document.setText(), which will remove all the positions, 
     * thus resulting the editor showing no breakpoint in ruler as described in 363935.
     * 
     * For ResourceMarkerAnnotationModel, it is when the resource get saved, then will use the
     * position information to update the Marker.
     * 
     * Here, we do it simple, when the document saved, we will refresh the model.
     */
    public void documentSaved(IDocument doc)
    {
        //XXX: not the best way to work around this problem.
        this.disconnect(doc);
        this.connect(doc);
    }

}
