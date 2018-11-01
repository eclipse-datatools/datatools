/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.action;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author Hui Cao
 * 
 */
public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    
    
    private Messages()
    {
    }

    public static String EditRoutineObjectAction_opening;
    public static String EditRoutineObjectAction_open_routine_editor;
    public static String EditRoutineObjectAction_read_objects;
    public static String EditRoutineObjectAction_initializing;
    public static String EditRoutineObjectAction_obj_not_found;
    public static String EditRoutineObjectAction_obj_not_found_detail;
    
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    
}
