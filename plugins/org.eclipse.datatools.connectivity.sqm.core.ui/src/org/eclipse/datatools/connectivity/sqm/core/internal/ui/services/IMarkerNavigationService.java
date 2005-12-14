/*
 *+------------------------------------------------------------------------+
 *| Licensed Materials - Property of IBM                                   |
 *| (C) Copyright IBM Corp. 2004.  All Rights Reserved.                    |
 *|                                                                        |
 *| US Government Users Restricted Rights - Use, duplication or disclosure |
 *| restricted by GSA ADP Schedule Contract with IBM Corp.                 |
 *+------------------------------------------------------------------------+
 */
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.services;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IEditorPart;

/**
 * @author ljulien
 */
public interface IMarkerNavigationService
{
    public void gotoMarker (IEditorPart editor, IMarker marker);
}
