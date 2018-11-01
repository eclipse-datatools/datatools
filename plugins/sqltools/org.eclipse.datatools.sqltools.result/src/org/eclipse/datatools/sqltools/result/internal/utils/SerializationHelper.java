/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;

/**
 * A utility class to serialize and deserialize an Object array.
 * 
 * @author juewu
 */
public class SerializationHelper
{
    public static String   RESULT_FLAG        = "results";

    // DO change the versions when adding/removing non-transient fields in ResultInstance class.
    public static int      RI_VERSION_MAJOR   = 1;
    public static int      RI_VERSION_MINOR   = 0;
    public static int      RI_VERSION_SERVICE = 0;

    private static String  DOT                = ".";

    private static ILogger _log               = ResultsViewPlugin.getLogger(null);

    private SerializationHelper()
    {
    }

    public static void SaveObjects(Object[] objs, String fileName)
    {
        File file = new File(ResultsViewPlugin.getDefault().getStateLocation().append(fileName).toOSString());

        if (!file.exists())
        {
            try
            {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

                oos.writeObject(objs);
                oos.flush();
                oos.close();
            }
            catch (Exception e)
            {
                _log.error("SerializationHelper_serialization_error", e);
            }
        }
    }

    public static Object[] LoadObjects(String fileName)
    {
        if (fileName == null)
        {
            return null;
        }

        File file = new File(ResultsViewPlugin.getDefault().getStateLocation().append(fileName).toOSString());

        try
        {
            if (file.exists() && file.isFile())
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

                Object obj = ois.readObject();
                ois.close();

                if (obj instanceof Object[])
                {
                    return (Object[]) obj;
                }
            }
        }
        catch (Exception e)
        {
            _log.error("SerializationHelper_deserialization_error", e);
        }
        return null;
    }

    public static boolean resultManagerVersionCompatible(String versionId)
    {
        if (!versionId.startsWith(RESULT_FLAG))
        {
            return false;
        }
        String verSec = versionId.substring(RESULT_FLAG.length());

        String[] vers = verSec.split("\\" + DOT);

        if (vers != null && vers.length > 0 && vers[0] != null
                && Integer.valueOf(vers[0]).intValue() == Integer.valueOf(RI_VERSION_MAJOR).intValue())
        {
            return true;
        }

        return false;
    }

    public static String getResultManagerVersion()
    {
        return RESULT_FLAG + String.valueOf(RI_VERSION_MAJOR) + DOT + String.valueOf(RI_VERSION_MINOR) + DOT
                + String.valueOf(RI_VERSION_SERVICE);
    }
}
