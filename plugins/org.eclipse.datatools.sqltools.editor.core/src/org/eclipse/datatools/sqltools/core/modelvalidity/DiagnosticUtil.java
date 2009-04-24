/**
 * Created on 2006-11-15
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.modelvalidity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;

/**
 * 
 * Diagnotic related utilities
 * @author Idull
 */
public class DiagnosticUtil
{
    /**
     * Convert a diagnostic or a diagnostic chain to array of <code>IStatus</code>
     * 
     * @param diagnostic
     * @return
     */
    public static IStatus[] convert2Status(BasicDiagnostic diagnostic)
    {
        if (diagnostic.getChildren().size() == 0)
        {
            return new IStatus[]
            {
                new Status(IStatus.OK, EditorCorePlugin.PLUGIN_ID, 0, "", null)
            }
            ;
        }

        // regard as a diagnostic chain
        if (diagnostic.getChildren().size() > 0)
        {
            List statuses = new ArrayList();

            Iterator iter = diagnostic.getChildren().iterator();
            while (iter.hasNext())
            {
                Diagnostic d = (Diagnostic) iter.next();
                IStatus stat = new Status(convertSeverity(d.getSeverity()), EditorCorePlugin.PLUGIN_ID, d.getCode(),
                    d.getMessage() == null ? "" : d.getMessage(), null);
                statuses.add(stat);
            }

            return (IStatus[]) statuses.toArray(new IStatus[statuses.size()]);
        }

        // regard as a diagnostic
        return new IStatus[]
        {
            new Status(convertSeverity(diagnostic.getSeverity()), EditorCorePlugin.PLUGIN_ID, diagnostic.getCode(),
                diagnostic.getMessage(), null)
        }
        ;
    }

    public static IStatus convert2StatusIngnoreChildren(Diagnostic diagnostic)
    {
        return new Status(diagnostic.getSeverity(), EditorCorePlugin.PLUGIN_ID, diagnostic.getCode(), diagnostic
            .getMessage(), null);
    }

    private static int convertSeverity(int severityInDiagnostic)
    {
        switch (severityInDiagnostic)
        {
            case Diagnostic.OK:
                return IStatus.OK;
            case Diagnostic.CANCEL:
                return IStatus.CANCEL;
            case Diagnostic.WARNING:
                return IStatus.WARNING;
            case Diagnostic.INFO:
                return IStatus.INFO;
            case Diagnostic.ERROR:
                return IStatus.ERROR;
            default:
                return IStatus.ERROR;
        }
    }

    /**
     * Returns the diagnostic caused by the given source
     * @param source
     * @param diagnostic
     * @return
     */
    public static Diagnostic getDiagnostic(String source, BasicDiagnostic diagnostic, Object obj)
    {
        if(source == null)
        {
            return null;
        }
        Iterator iter = diagnostic.getChildren().iterator();
        while(iter.hasNext())
        {
            Diagnostic d = (Diagnostic)iter.next();
            if(d.getSource().equals(source) && d.getData().contains(obj))
            {
                return d;
            }
        }
        return null;
    }
    
    /**
     * Returns the diagnostic caused by the given object
     * @param source
     * @param diagnostic
     * @return
     */
    public static Diagnostic getDiagnostic(BasicDiagnostic diagnostic, Object obj)
    {
        if(obj == null)
        {
            return null;
        }
        Iterator iter = diagnostic.getChildren().iterator();
        while(iter.hasNext())
        {
            Diagnostic d = (Diagnostic)iter.next();
            if(d.getData().contains(obj))
            {
                return d;
            }
        }
        return null;
    }
}
