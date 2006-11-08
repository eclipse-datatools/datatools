/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.virtual.IConnectionNode;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IElementIDProvider;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.content.ConnectionNodeUtil;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

import com.ibm.icu.util.StringTokenizer;

/**
 * @author ljulien
 */
public class TransientEObjectUtil implements IElementIDProvider
{
    private static final ContainmentService containment = RDBCorePlugin.getDefault().getContainmentService();
 //   private static final ConnectionManager manager = RDBCorePlugin.getDefault().getConnectionManager();
    
    private static final String SEPARATOR = "#"; //$NON-NLS-1$
    private static final String VIRTUAL_SEPARATOR = "/"; //$NON-NLS-1$
    private static final String NULL = ""; //$NON-NLS-1$

    private static final Stack stack = new Stack ();
    
    private static EObject getRoot (EObject object)
    {
        stack.push(object);
        do
        {
            EObject parent = null;
            if ((parent = containment.getContainer(object)) == null)
            {
                return object;
            }
            object = parent;
            stack.push(object);
        }
        while (object != null);
        return object;
    }
    
    public static ConnectionInfo getConnectionInfo (EObject object)
    {
//        SQLObject root = (SQLObject) getRoot (object);
//        if (root != null)
//        {
//            return manager.getConnectionInfo(root);
//        }
        return null;
    }
    
    private static IConnectionNode getConnectionNode (EObject object) throws Exception
    {
        try
        {
            return ConnectionNodeUtil.getConnectionNode(getConnectionInfo (object));
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    
    private static void buildID (StringBuffer buffer, String name)
    {
        buffer.append(name + SEPARATOR);
    }
    
    private static void buildID (StringBuffer buffer)
    {
        while (!stack.empty())
        {
            ENamedElement eObject = (ENamedElement) stack.pop();
            String groupID = containment.getGroupId(eObject);
            buffer.append(groupID + VIRTUAL_SEPARATOR + eObject.getName() + SEPARATOR);
        }
    }
    
    public static String getEObjectId (EObject object) throws Exception
    {
        StringBuffer buffer = new StringBuffer();
        //TODO:  Restore this if needed
//        stack.clear();
//        IConnectionNode node = getConnectionNode(object);
//        buildID(buffer, node.getName());
//        buildID(buffer);
//        stack.clear();
        return buffer.toString();
    }
    
    public static List getPathFromID (String id)
    {
        StringTokenizer tokens = new StringTokenizer (id, SEPARATOR);
        List list = new ArrayList ();
        while (tokens.hasMoreElements())
        {
            list.add(tokens.nextToken());
        }
        return list;
    }
    
    public static IGroup getGroupInfo (final String subId)
    {
        
        return new IGroup ()
        {
            public String getGroupId()
            {
                return subId.substring(0, subId.indexOf(VIRTUAL_SEPARATOR));
            }

            public String getElementName()
            {
                String elementName = subId.substring(subId.indexOf(VIRTUAL_SEPARATOR) + 1);
                return elementName.equals(NULL) ? null : elementName; 
            }
        };
    }
    
    public static EObject getEObjectFromId (String id)
    {
        return null;
    }

    public String getElementID(EObject eObject)
    {
        try
        {
            return getEObjectId(eObject);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    public interface IGroup 
    {
        public String getGroupId ();
        public String getElementName ();
    }
}
