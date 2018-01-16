package org.eclipse.datatools.enablement.sybase.deltaddl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * This class try to find the specified IDeltaDdlGenProvider following this rule: 
 * 1. try to find the most compatitable provider, if not goto 2.
 * 2. try to find the largest version of the specified product and clazz compatible provider, if not goto 3.
 * 3. try to find the super class of specified provider class, and goto 1.
 * 4. if can not find provider, return GenericDeltaDdlGenProvider.INSTANCE;
 * 
 * @author linsong
 */
public final class DeltaDdlGenServiceImpl
{

    public static final DeltaDdlGenServiceImpl INSTANCE = new DeltaDdlGenServiceImpl();

    public IDeltaDdlGenProvider getDeltaDdlGenProvider(String product, String version, EObject obj)
    {
        EClass metaclass = obj.eClass();
        IDeltaDdlGenProvider provider = this.getProvider(product, version, metaclass);
        if (provider == null)
        {
            provider = GenericDeltaDdlGenProvider.INSTANCE;
        }
        return provider;
    }

    private IDeltaDdlGenProvider getProvider(String product, String version, EClass metaclass)
    {
        ProductVersionClass pvc = ProductVersionClass.getInstance(product, version, metaclass);
        if (this.cache.containsKey(pvc))
        {
            return (IDeltaDdlGenProvider) this.cache.get(pvc);
        }

        List sortedClasses = this.computeClassOrder(metaclass);
        IDeltaDdlGenProvider provider = this.getProvider(product, version, sortedClasses);
        if (provider != null)
            this.cache.put(pvc, provider);
        return provider;
    }

    private List computeClassOrder(EClass metaclass)
    {
        List result = new ArrayList(5);
        result.add(metaclass);
        int index = 0;
        for (index = 0; index < result.size(); ++index)
        {
            EClass clazz = (EClass) result.get(index);
            Iterator it = clazz.getESuperTypes().iterator();
            while (it.hasNext())
                result.add(it.next());
        }
        return result;
    }

    private IDeltaDdlGenProvider getProvider(String product, String version, List classes)
    {
        int count = classes.size();
        for (int i = 0; i < count; ++i)
        {
            EClass clazz = (EClass) classes.get(i);
            String uri = clazz.getEPackage().getNsURI();
            String className = clazz.getName();
            String fullName = uri + className;
            //sorted version map
            Map versionMap = (Map) _productsMap.get(product);
            //1. find the most compatible version provider
            Map classMap = (Map)versionMap.get(version);
            
            if (classMap != null)
            {
                if (classMap.containsKey(fullName))
                {
                    return (IDeltaDdlGenProvider) classMap.get(fullName);
                }
            }
            
            //2. find the largest version of the specified product and clazz compatible provider
            Iterator it = versionMap.keySet().iterator();
            while(it.hasNext())
            {
                Object key = it.next();
                if(!key.equals(version))
                {
                    classMap = (Map)versionMap.get(key);
                    if (classMap != null)
                    {
                        if (classMap.containsKey(fullName))
                        {
                            return (IDeltaDdlGenProvider) classMap.get(fullName);
                        }
                    }       
                }
            }
        }
        return null;
    }
    
    private DeltaDdlGenServiceImpl()
    {
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(
                "org.eclipse.datatools.enablement.sybase", "deltaDdlGenProvider"); //$NON-NLS-1$ //$NON-NLS-2$
        IExtension[] extensions = extensionPoint.getExtensions();
        for (int i = 0; i < extensions.length; ++i)
        {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            for (int j = 0; j < configElements.length; ++j)
            {
                if (configElements[j].getName().equals("deltaDdlGenService")) { //$NON-NLS-1$
                    String packageURI = configElements[j].getAttribute("package"); //$NON-NLS-1$
                    String className = configElements[j].getAttribute("class"); //$NON-NLS-1$
                    String product = configElements[j].getAttribute("product"); //$NON-NLS-1$
                    String version = configElements[j].getAttribute("version"); //$NON-NLS-1$
                    IDeltaDdlGenProvider provider = null;
                    try
                    {
                        provider = (IDeltaDdlGenProvider) configElements[j].createExecutableExtension("provider"); //$NON-NLS-1$
                    }
                    catch (CoreException e)
                    {
                        IStatus status = new Status(
                                IStatus.ERROR,
                                RDBCorePlugin.getDefault().getBundle().getSymbolicName(),
                                IStatus.ERROR,
                                "The error was detected when creating the delta ddl generation provider for " + className + " in " + packageURI, //$NON-NLS-1$//$NON-NLS-2$
                                e);
                        RDBCorePlugin.getDefault().getLog().log(status);
                        continue;
                    }

                    initProductsMap(product, version, packageURI, className, provider);

                }
            }
        }
    }

    private void initProductsMap(String product, String version, String packageURI, String className,
            IDeltaDdlGenProvider provider)
    {
        Map versionsMap = (Map)_productsMap.get(product);
        if(versionsMap == null)
        {
            versionsMap = new TreeMap(new Comparator()
            {
                //for the version sorting purpose,
                //we regard the version 12.x is larger than 15.x,
                //and 15.x is larger than 15.9,
                //and the unknown version as the lastest one.
                public int compare(final Object left, final Object right)
                {
                    if (left == null && right == null)
                    {
                        return 0;
                    }
                    else if (left == null)
                    {
                        return 1;
                    }
                    else if (right == null)
                    {
                        return -1;
                    }

                    final String leftString = ((String) left).toLowerCase();
                    final String rightString = ((String) right).toLowerCase();

                    int result = 0;

                    final String[] ls = leftString.split("\\Q.\\E");
                    final String[] rs = rightString.split("\\Q.\\E");
                    final int length = Math.min(ls.length, rs.length);
                    for (int i = 0; i < length; i++)
                    {
                        //only one x can be present in the string array
                        int lxindex = ls[i].indexOf("x");
                        int rxindex = rs[i].indexOf("x");
                        if ((lxindex >= 0 || rxindex >= 0))
                        {
                            if( lxindex != rxindex)
                                return lxindex - rxindex;
                        }
                        else
                        {
                            result = Integer.parseInt(rs[i]) - Integer.parseInt(ls[i]);
                            if (result != 0)
                            {
                                break;
                            }
                        }
                    }
                    return result;
                }
            });
            _productsMap.put(product, versionsMap);
        }
        
        Map classNames = (Map)versionsMap.get(version);
        if(classNames == null)
        {
            classNames = new HashMap();
            versionsMap.put(version, classNames);
        }

        String fullName = packageURI + className;
        classNames.put(fullName, provider);
    }

    //init map: key Product name, value Map [version] --> [Full class name] --> [provider instance] 
    private Map _productsMap = new HashMap();
    //cache map: key ProductVersionClass, value Provider
    private Map cache    = new HashMap();

    static class ProductVersionClass
    {
        String _productName;
        String _version;
        EClass _clazz;

        public static ProductVersionClass getInstance(String product, String version, EClass clazz)
        {
            return new ProductVersionClass(product, version, clazz);
        }

        ProductVersionClass(String product, String version, EClass clazz)
        {
            this._productName = product != null ? product : "";
            this._version = version != null ? version : "";
            this._clazz = clazz;
        }

        public boolean equals(Object obj)
        {
            if (obj instanceof ProductVersionClass)
            {
                ProductVersionClass pvc = (ProductVersionClass) obj;
                return pvc._productName.equals(this._productName) && pvc._version.equals(this._version)
                        && pvc._clazz.equals(this._clazz);
            }
            return false;
        }

        public int hashCode()
        {
            return (_productName + _version + _clazz.getEPackage().getNsURI() + _clazz.getName())
                    .hashCode();
        }
    }
    
}
