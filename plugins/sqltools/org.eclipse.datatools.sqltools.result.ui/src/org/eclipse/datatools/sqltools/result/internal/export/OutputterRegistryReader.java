/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.export;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;

/**
 * The registry reader for result outputters
 * 
 * @author Dafan Yang
 */
public class OutputterRegistryReader
{
    private static OutputterRegistryReader _instance;
    private List                           _outputters;

    private OutputterRegistryReader()
    {
        
    }
    
    public synchronized static OutputterRegistryReader getInstance()
    {
        if(_instance == null)
        {
            _instance = new OutputterRegistryReader();
        }
        return _instance;
    }

    public synchronized List getOutputters()
    {
        if(_outputters == null)
        {
            _outputters = new ArrayList();
            init();
        }
        return _outputters;
    }
    
    public List getOutputtersSupportXML()
    {
        List outputters = new ArrayList();
        getOutputters();
        Iterator iter = _outputters.iterator();
        while (iter.hasNext())
        {
            IOutputterDescriptor descriptor = (IOutputterDescriptor) iter.next();
            if (descriptor.supportXMLResult())
            {
                outputters.add(descriptor);
            }
        }
        return outputters;
    }
    
    public AbstractOutputter getOutputter(String typeId)
    {
        if (typeId == null)
        {
            return null;
        }
        getOutputters();
        Iterator iter = _outputters.iterator();
        while (iter.hasNext())
        {
            IOutputterDescriptor descriptor = (IOutputterDescriptor) iter.next();
            if (descriptor.getTypeId().equals(typeId))
            {
                return descriptor.getOutputter();
            }
        }
        return null;
    }
    
    /**
     * Once the outputter is loaded, the index wont change for a specific outputter
     * 
     * @param index the index of the outputter, based on 0
     * @return
     */
    public IOutputterDescriptor getOutputterDesciptor(boolean supportXML, int index)
    {
        if (index < 0)
        {
            return null;
        }
        getOutputters();
        List outputters = supportXML?getOutputtersSupportXML():_outputters;
        if (index >= outputters.size())
        {
            return null;
        }
        return (OutputterDescriptor) outputters.get(index);
    }
    
    /**
     * Returns the outputter display strings
     * @param supportXML returns outputters which support XML result set if <code>true</code> is given, otherwise
     *            returns all outputters
     * @return the outputter display strings
     */
    public String[] getOutputterDspStrings(boolean supportXML)
    {
        List outputterDspStrings = new ArrayList();
        getOutputters();
        Iterator iter = supportXML ? getOutputtersSupportXML().iterator() : _outputters.iterator();
        while (iter.hasNext())
        {
            IOutputterDescriptor descriptor = (IOutputterDescriptor) iter.next();
            outputterDspStrings.add(descriptor.getDisplayString());
        }
        return (String[]) outputterDspStrings.toArray(new String[outputterDspStrings.size()]);
    }
    
    public String[] getFilterStrings(boolean supportXML)
    {
        List filterStrings = new ArrayList();
        getOutputters();
        Iterator iter = supportXML ? getOutputtersSupportXML().iterator() : _outputters.iterator();
        while (iter.hasNext())
        {
            IOutputterDescriptor descriptor = (IOutputterDescriptor) iter.next();
            filterStrings.add(descriptor.getExtFilterString());
        }
        return (String[]) filterStrings.toArray(new String[filterStrings.size()]);
    }
    
    private void init()
    {
        List outputters = new ArrayList();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint point = registry.getExtensionPoint(ResultsViewPlugin.getPluginId(), OutputterConstants.EXTENSION_POINT_NAME);
        if(point == null)
        {
            // Should not happen
            return;
        }
        IExtension[] extensions = point.getExtensions();
        for (int i = 0; i < extensions.length; i++)
        {
            IConfigurationElement[] elements = extensions[i].getConfigurationElements();
            for (int j = 0; j < elements.length; j++)
            {
                String typeId = elements[j].getAttribute(OutputterConstants.OUTPUTTER_ID);
                String typeName = elements[j].getAttribute(OutputterConstants.OUTPUTTER_NAME);
                String fileExt = elements[j].getAttribute(OutputterConstants.OUTPUTTER_FILE_EXT);
                String extensionFilter = elements[j].getAttribute(OutputterConstants.OUTPUTTER_EXTENSION_FILTER);
                String supportDelimiterStr = elements[j].getAttribute(OutputterConstants.OUTPUTTER_SUPPORT_DELIMITER);
                String supportXMLResultStr = elements[j].getAttribute(OutputterConstants.OUTPUTTER_SUPPORT_XML);
                if (typeName == null || typeName.trim().length() == 0)
                {
                    continue;
                }
                
                boolean supportDelimiter = false;
                boolean supportXMLResult = false;
                AbstractOutputter outputter = null;
                if (supportDelimiterStr != null && supportDelimiterStr.length() > 0)
                {
                    try
                    {
                        supportDelimiter = Boolean.valueOf(supportDelimiterStr).booleanValue();
                    }
                    catch (Exception e)
                    {
                    }
                }
                
                if (supportXMLResultStr != null && supportXMLResultStr.length() > 0)
                {
                    try
                    {
                        supportXMLResult = Boolean.valueOf(supportXMLResultStr).booleanValue();
                    }
                    catch (Exception e)
                    {
                    }
                }
                try
                {
                    outputter = (AbstractOutputter) elements[j].createExecutableExtension(OutputterConstants.OUTPUTTER_CLASS);
                }
                catch (Exception e)
                {
                    continue;
                }

                IOutputterDescriptor descriptor = new OutputterDescriptor(typeId, typeName, fileExt, supportDelimiter,
                        outputter, supportXMLResult, extensionFilter);
                outputters.add(descriptor);
            }
        }
        
        //Add the four types of built-in outputters as the first four types
        Iterator iter = outputters.iterator();
        
        List builtInOutputters = new ArrayList();
        
        while (iter.hasNext())
        {
            IOutputterDescriptor descriptor = (IOutputterDescriptor) iter.next();
            if(descriptor.getTypeId().equals(OutputterConstants.PLAIN_TXT_OUTPUTTER_ID))
            {
                builtInOutputters.add(descriptor);
            }
            if(descriptor.getTypeId().equals(OutputterConstants.XML_OUTPUTTER_ID))
            {
                builtInOutputters.add(descriptor);
            }
            if(descriptor.getTypeId().equals(OutputterConstants.HTML_OUTPUTTER_ID))
            {
                builtInOutputters.add(descriptor);
            }
            if(descriptor.getTypeId().equals(OutputterConstants.CSV_OUTPUTTER_ID))
            {
                builtInOutputters.add(descriptor);
            }
        }
        
        _outputters.addAll(builtInOutputters);
        outputters.removeAll(builtInOutputters);
        _outputters.addAll(outputters);
    }
}
