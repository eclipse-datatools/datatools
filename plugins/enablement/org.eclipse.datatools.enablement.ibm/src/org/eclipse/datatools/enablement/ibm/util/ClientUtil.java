/*******************************************************************************
 * Copyright (c) 2000, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.datatools.enablement.ibm.IBMPluginActivator;

/**
 * Wraps native methods.
 * @author Thomas Sharp
 */
public class ClientUtil
{
   /** Flag to turn on prints for debug. */
   public static final boolean DEBUG = false;

   /** Key for an arbitrary registry path and key. */
   public static String REGISTRYVALUE = "getRegistryValue";
   /** Key for DB2 path. */
   public static String DB2PATH = "getDB2Path";
   /** Key for JDK version. */
   public static String JDKLEVEL = "getJdkLevel";
   /** Key for the Oracle path. */
   public static String ORACLEPATH = "getOraclePath";
   /** Key for the DB2 client version, release, and modification in the form 08022. */
   public static String DB2CLIENTLEVEL = "getDB2ClientLevel";
   /** Key for the DB2 client version. */
   public static String DB2CLIENTVERSION = "getDB2ClientVersion";
   /** Key for the DB2 client release. */
   public static String DB2CLIENTRELEASE = "getDB2ClientRelease";
   /** Key for the DB2 client modification level. */
   public static String DB2CLIENTMODIFICATION = "getDB2ClientModification";
   /** Key for the Visual Explain path. */
   public static String VEPATH = "getVEPath";
   /** Key for the Visual Explain version.release.modification. */
   public static String VELEVEL = "getVELevel";
   /** Key for the Visual Explain version. */
   public static String VEVERSION = "getVEVersion";
   /** Key for the Visual Explain release. */
   public static String VERELEASE = "getVERelease";
   /** Key for the Visual Explain modification level. */
   public static String VEMODIFICATION = "getVEModification";
   /** Key for the alias count. The default value for this method is 0. */
   public static String ALIASCOUNT = "getAliasCount";
   /** Key for the aliases and comments. No default value exists for this method. */
   public static String ALIASES = "getAliases";
   /** Key for the aliases, hosts, dbnames, and ports. No default value exists for this method. */
   public static String HOSTINFO = "getHostInfo";
   /**
    * Key for getting an environment variable.
    * The full key for an override of a value is getEnv(varname),
    * but the argument is specified with the argument property
    * of the defaultValue or actualValue tag.
    */
   public static String ENVVAR = "getEnv";

   /** Table of defaults. */
   protected static HashMap defaultValues;
   /** Table of implementation methods. */
   protected static HashMap implMethods;
   /** Implementations of IServicePrompt$promptValue methods. */
   protected static HashMap promptMethods;
   /** Table of actual values. */
   protected static HashMap actualValues;

   private static final String PATH_SEPARATOR = java.io.File.pathSeparator;

   // Static section:
   static
   {
      // Initialize internal default values
      initDefaults();
      // Override with ExternalService/defaultValue contributions
//<bgp      initOverrides();
//		// Test whether we have a local DB2 with version > 7 installed:
//		if (!hasDB2PathImpl() && getDB2ClientVersion() < 8)
//		{
//			DB2Util.setTryNative(false);
//bgp>		}
   }

//<bgp   /**
//    * Registers a base name for the JNI library that implements OSUtil native functions.
//    * @param lib A base name for the JNI library that implements OSUtil native functions.
//    */
//   public static synchronized void setCutilLibrary(String lib)
//   {
//      OSUtil.setOSUtilLibrary(lib);
//   }
//
//   /**
//    * @return The base name for the JNI library that implements OSUtil native functions.
//    */
//   public static String getCutilLibrary()
//   {
//      return OSUtil.getOSUtilLibrary();
//   }
//
//   /**
//    * Registers a base name for the JNI library that implements DB2Util native functions.
//    * @param lib A base name for the JNI library that implements DB2Util native functions.
//    */
//   public static synchronized void setDB2UtilLibrary(String lib)
//   {
//      DB2Util.setDB2UtilLibrary(lib);
//   }
//
//   /**
//    * @return The base name for the JNI library that implements DB2Util native functions.
//    */
//   public static String getDB2UtilLibrary()
//   {
//      return DB2Util.getDB2UtilLibrary();
//   }

   /**
    * Initializes default values.
    */
   protected static void initDefaults()
   {
      // Actual values (cache of the result during the first call)
      actualValues = new HashMap(37);
      // Default values
      defaultValues = new HashMap(37);
      // Implementation methods to be used instead of protected JNI methods
      implMethods = new HashMap(37);
      // Implementations of IServicePrompt$promptValue methods.
      promptMethods = new HashMap(37);
      // DB2Path, OraclePath
      //if (System.getProperty("os.name").indexOf("Win") > -1)
      //{
      defaultValues.put(DB2PATH, "null");
//bgp      defaultValues.put(ORACLEPATH, "null");
//bgp      defaultValues.put(VEPATH, "null");
      //}
      //else
      //{
      //   defaultValues.put(DB2PATH, "null");
      //   defaultValues.put(ORACLEPATH, "null");
      //   defaultValues.put(VEPATH, "null");
      //}
//<bgp      defaultValues.put(VELEVEL, "1.0.0");
//      defaultValues.put(VEVERSION, "1");
//      defaultValues.put(VERELEASE, "0");
//bgp>      defaultValues.put(VEMODIFICATION, "0");
   }

//   /**
//    * Overrides internal default values and implementations with
//    * externalService/defaultValue and implementation contributions.
//    */
//   protected static void initOverrides()
//   {
//      IExtensionRegistry reg = Platform.getExtensionRegistry();
//      if (reg != null) {
//	      IConfigurationElement[] domainConfigs = reg.getConfigurationElementsFor(
//	               "com.ibm.datatools.externalservices", "externalServices");
//	      IConfigurationElement config;
//	      //ConfigurationProperty[] properties;
//	      String ename, mname, pvalue, iclass, imethod, argval;
//	      for (int e = 0; e < domainConfigs.length; e++)
//	      {
//	         ename = domainConfigs[e].getName();
//	         config = domainConfigs[e];
//	         mname = config.getAttribute("methodName");
//	         if (mname != null)
//	         {
//	            mname = mapMethodName(mname);
//	            if (ename.equalsIgnoreCase("defaultValue"))
//	            {
//	               pvalue = config.getAttribute("value");
//	               if (pvalue != null && pvalue.length() > 0)
//	               {
//	                  if (mname.equals(ALIASCOUNT))
//	                  {
//	                     defaultValues.put(ALIASCOUNT, Integer.getInteger(pvalue));
//	                  }
//	                  else if (mname.equals(ENVVAR))
//	                  {
//	                     argval = config.getAttribute("argument");
//	                     defaultValues.put("getEnv(" + argval + ")", pvalue);
//	                  }
//	                  else
//	                  {
//	                     defaultValues.put(mname, pvalue);
//	                  }
//	               }
//	            }
//	            else if (ename.equalsIgnoreCase("actualValue"))
//	            {
//	               pvalue = config.getAttribute("value");
//	               if (pvalue != null && pvalue.length() > 0)
//	               {
//	                  if (mname.equals(ALIASCOUNT))
//	                  {
//	                     actualValues.put(ALIASCOUNT, Integer.getInteger(pvalue));
//	                  }
//	                  else if (mname.equals(ENVVAR))
//	                  {
//	                     argval = config.getAttribute("argument");
//	                     actualValues.put("getEnv(" + argval + ")", pvalue);
//	                  }
//	                  else
//	                  {
//	                     actualValues.put(mname, pvalue);
//	                  }
//	               }
//	            }
//	            else if (ename.equalsIgnoreCase("implementation"))
//	            {
//	               iclass = config.getAttribute("implClass");
//	               imethod = config.getAttribute("implMethod");
//	               if (iclass != null && imethod != null)
//	               {
//	                  Method m = getMethod(config, iclass, imethod);
//	                  if (m != null)
//	                  {
//	                     implMethods.put(mname, m);
//	                  }
//	               }
//	            }
//	            else if (ename.equalsIgnoreCase("prompt"))
//	            {
//	               pvalue = config.getAttribute("enable");
//	               if (pvalue != null && pvalue.equalsIgnoreCase("true"))
//	               {
//	                  iclass = config.getAttribute("servicePrompt");
//	                  if (iclass != null)
//	                  {
//	                     Object[] cm = getClassAndMethod(config, iclass, "promptValue", new Class[]{String.class, Object.class});
//	                     if (cm != null)
//	                     {
//	                        promptMethods.put(mname, cm);
//	                     }
//	                  }
//	               }
//	               else
//	               {
//	                  promptMethods.remove(mname);
//	               }
//	            }
//	         }
//	         else if (ename.equalsIgnoreCase("oslibrary"))
//	         {
//	            pvalue = domainConfigs[e].getAttribute("basename");
//	            setCutilLibrary(pvalue);
//	         }
//	         else if (ename.equalsIgnoreCase("db2library"))
//	         {
//	            pvalue = domainConfigs[e].getAttribute("basename");
//	            setDB2UtilLibrary(pvalue);
//	         }
//	      }
//      }
//   }
//
//   /**
//    * Given a methodName value from an extension,
//    * returns our method key.
//    * @param mname The methodName attribute from a contribution.
//    * @return The value of our static final String for the method.
//    */
//   protected static String mapMethodName(String mname)
//   {
//      String key = "DEFAULT";
//      if (mname.equalsIgnoreCase(REGISTRYVALUE))
//         key = REGISTRYVALUE;
//      else if (mname.equalsIgnoreCase(DB2PATH))
//         key = DB2PATH;
//      else if (mname.equalsIgnoreCase(JDKLEVEL))
//         key = JDKLEVEL;
//      else if (mname.equalsIgnoreCase(ORACLEPATH))
//         key = ORACLEPATH;
//      else if (mname.equalsIgnoreCase(VEPATH))
//         key = VEPATH;
//      else if (mname.equalsIgnoreCase(VELEVEL))
//         key = VELEVEL;
//      else if (mname.equalsIgnoreCase(VEVERSION))
//         key = VEVERSION;
//      else if (mname.equalsIgnoreCase(VERELEASE))
//         key = VERELEASE;
//      else if (mname.equalsIgnoreCase(VEMODIFICATION))
//         key = VEMODIFICATION;
//      else if (mname.equalsIgnoreCase(ALIASCOUNT))
//         key = ALIASCOUNT;
//      else if (mname.equalsIgnoreCase(ENVVAR))
//         key = ENVVAR;
//      return key;
//bgp>   }
   
   /**
    * Gets the default value for a method.
    * @param methodname The name of the method whose default value we want.
    * @return The default value of the method.
    */
   public static Object getDefault(String methodname)
   {
      return defaultValues.get(methodname);
   }

//<bgp   /**
//    * Sets the default value for a method.
//    * @param methodname The name of the method whose default value we want.
//    * If the method requires an argument, give it in parentheses,
//    * such as "getEnv(HOME)."
//    * @param value The default value of the method, or null.
//    * If the value is null, the entry is cleared from the table.
//    */
//   public static synchronized void setDefault(String methodname, String value)
//   {
//      if (value == null)
//         defaultValues.remove(methodname);
//      else
//         defaultValues.put(methodname, value);
//   }
//
//   /**
//    * Sets the actual value for a method.
//    * @param methodname The name of the method whose default value we want.
//    * If the method requires an argument, give it in parentheses,
//    * such as "getEnv(HOME)."
//    * @param value The actual value of the method, or null.
//    * If the value is null, the entry is cleared from the table.
//    */
//   public static synchronized void setActualValue(String methodname, String value)
//   {
//      if (value == null)
//         actualValues.remove(methodname);
//      else
//         actualValues.put(methodname, value);
//   }
//
//   /**
//    * Resets the cached actual value for a method.
//    * @param methodname The name of the method whose default value we want.
//    */
//   public static synchronized void clearCache(String methodname)
//   {
//      actualValues.remove(methodname);
//   }
//
//
//   /**
//    * Gets the Method to implement the named method.
//    * @param methodname The name of the method whose default value we want.
//    * @return The implementation Method, or null if it is not set
//    * using setImplementation or an externalService/implementation contribution.
//    * The default implementation method in this class is not returned.
//    */
//   public static Method getImplementation(String methodname)
//   {
//      return (Method)implMethods.get(methodname);
//   }
//
//   /**
//    * Sets the implementation Method.
//    * @param methodname The name of the method whose default value we want.
//    * @param method The implementation Method.
//    */
//   public static synchronized void setImplementation(String methodname, Method method)
//   {
//      implMethods.put(methodname, method);
//   }
//
//   /**
//    * Gets a method in a class.
//    *
//    * @param config
//    *            The ConfigurationElement for the contribution to our extension point.
//    * @param classname
//    *            The class with the method to call.
//    * @param methodname
//    *            The method we want to call.
//    * @return The Method for methodname in the instance of class classname.
//    */
//   protected static Method getMethod(IConfigurationElement config, String classname, String methodname)
//   {
//      return getMethod(config, classname, methodname, null);
//   }
//
//   /**
//    * Gets a method in a class.
//    *
//    * @param config
//    *            The ConfigurationElement for the contribution to our extension point.
//    * @param classname
//    *            The class with the method to call.
//    * @param methodname
//    *            The method we want to call.
//    * @param types
//    *            An array of Class describing the types of the arguments.
//    * @return The Method for methodname in the instance of class classname.
//    */
//   protected static Method getMethod(IConfigurationElement config, String classname, String methodname, Class[] types)
//   {
//      Object[] candm = getClassAndMethod(config, classname, methodname, types);
//      return (candm == null) ? null : (Method)candm[1];
//   }
//   /**
//    * Gets a method in a class.
//    *
//    * @param config
//    *            The ConfigurationElement for the contribution to our extension point.
//    * @param classname
//    *            The class with the method to call.
//    * @param methodname
//    *            The method we want to call.
//    * @param types
//    *            An array of Class describing the types of the arguments.
//    * @return An array whose first element is the Class object and whose second is the Method.
//    */
//   protected static Object[] getClassAndMethod(IConfigurationElement config, String classname, String methodname, Class[] types)
//   {
//      Class cclass = null;
//      Method method = null;
//      try
//      {
//         cclass = ExternalServicesPlugin.getDefault().getBundle().loadClass(classname);
//      }
//      catch (ClassNotFoundException cnfe)
//      {
//         // createExecutableExtension(InternalPlatform.getDefault().getBundle(getDeclaringExtension().getNamespace()),
//         //    pluginName, className, initData, this, attributeName);
//         String namespace = config.getDeclaringExtension().getNamespaceIdentifier();
//         Bundle pbundle = Platform.getBundle(namespace);
//         //String id = pbundle.getSymbolicName();
//         try {
//            cclass = pbundle.loadClass(classname);
//         } catch (Exception e1) {
//            cclass = null;
//         } catch (LinkageError e) {
//            cclass = null;
//            if (DEBUG) System.err.println("ClientUtil.getMethod ClassNotFoundException: "
//                     + cnfe.getMessage());
//         }
//      }
//      if (cclass != null)
//      {
//         try
//         {
//            method = cclass.getMethod(methodname, types);
//         }
//         catch (NoSuchMethodException nsme)
//         {
//            if (DEBUG) System.err
//            .println("ClientUtil.getMethod NoSuchMethodException: "
//                     + nsme.getMessage());
//         }
//      }
//      return new Object[]{cclass, method};
//bgp>   }

   /**
    * Invokes a static method with no argument and returns a String value.
    * @param method A Method object representing a static method with no parameter
    * that returns a String.
    * @return The value returned by the method, or null.
    */
   protected static Object callMethod(Method method)
   {
      return callMethod(method, null, null);
   }
   
   /**
    * Invokes a static method with no argument and returns a String value.
    * @param method A Method object representing a static method with no parameter
    * that returns a String.
    * @param instance Null for a static method, an instance otherwize.
    * @param args An array of values for input to the method.
    * @return The value returned by the method, or null.
    */
   protected static Object callMethod(Method method, Object instance, Object[] args)
   {
      Object rv = null;
      try {
         if (method != null) {
            // Ensure this method is static
            // or construct an instance using the default constructor.
            boolean isStatic = Modifier.isStatic(method.getModifiers());
//bgp            if (instance == null && !isStatic)
//bgp               instance = ReflectionUtil.dynamicInstance(method.getDeclaringClass(), null, null);
            if (instance != null || isStatic)
               rv = method.invoke(instance, args);
         }
      } catch (NullPointerException npe) {
         if (DEBUG) System.err
         .println("ClientUtil.callMethod NullPointerException: "
                  + npe.getMessage());
      } catch (IllegalAccessException iae) {
         if (DEBUG) System.err
         .println("ClientUtil.callMethod IllegalAccessException: "
                  + iae.getMessage());
      } catch (InvocationTargetException ite) {
         String msg = ite.getMessage();
         if (msg == null)
         {
            Throwable te = ite.getTargetException();
            msg = te.getClass().getName() + ": " + te.getMessage();
         }
         if (DEBUG) System.err
         .println("ClientUtil.callMethod InvocationTargetException: "
                  + msg);
      } catch (IllegalArgumentException iarge) {
         if (DEBUG) System.err
         .println("ClientUtil.callMethod IllegalArgumentException: "
                  + iarge.getMessage());
      }
      return rv;
   }

   /**
    * Gets a value from the dialog settings.
    * This value overrides everything except an actual value.
    * A null value means we haven't remembered anything.
    * The value "null" means no and don't ask again.
    * @param methodname One of the method name keys, such as DB2PATH.
    * @param args An array of argument values.
    * @return A value from the dialog settings, or null.
    */
   protected static Object getStoredValue(String methodname, Object[] args)
   {
      // Check the dialog settings
	   IBMPluginActivator plugin = IBMPluginActivator.getInstance();
	   String returnString = null;
	   if (plugin != null) {
		   Preferences prefStore = plugin.getPluginPreferences();
		   String prefKey = getPrefKey(methodname, args);
		   if (prefStore != null) {
			   returnString = prefStore.getString(prefKey);
		   }
	   }
	   return returnString;
   }

   /**
    * @param methodname The name of the method whose value we are caching.
    * @param args An array of arguments, or null.
    * @return A String in the form: "methodname[([arg1[,arg2...])]".
    */
   protected static String getPrefKey(String methodname, Object[] args)
   {
      if (args != null)
      {
         StringBuffer prefKey = new StringBuffer();
         prefKey.append(methodname).append('(');
         int m = args.length;
         if (m > 0)
         {
            prefKey.append(args[0]);
            int a = 1;
            while (a < m)
            {
               prefKey.append(',').append(args[a]);
               a++;
            }
         }
         prefKey.append(')');
         return prefKey.toString();
      }
      return methodname;
//    if (methodname.equalsIgnoreCase(ENVVAR) && args != null && args.length == 1)
//    prefKey += "(" + args[0] + ")";
//    else if (methodname.equalsIgnoreCase(REGISTRYVALUE) && args != null && args.length == 2)
//    prefKey += "(" + args[0] + "," + args[1] + ")";
//    return prefKey;
   }

//<bgp   /**
//    * Gets a String value from a registered implementation method
//    * or from our own JNI method.
//    * @param methodname One of the method name keys, such as DB2PATH.
//    * @param args An array of argument values.
//    */
//   protected static Object getImplValue(String methodname, Object[] args)
//   {
//      Object value = null;
//      Method m = (Method)implMethods.get(methodname);
//      if (m != null)
//         value = callMethod(m, null, args);
//      if (value == null)
//      {
//         if (methodname.equalsIgnoreCase(ENVVAR)
//         && args != null && args.length == 1 && args[0] instanceof String)
//         {
//            value = getenvJNI((String)args[0]);
//         }
//         else if (methodname.equalsIgnoreCase(REGISTRYVALUE)
//         && args != null && args.length == 2 && args[0] instanceof String  && args[1] instanceof String)
//         {
//            value = getRegistryValueJNI((String)args[0], (String)args[1]);
//         }
//         else if (methodname.equalsIgnoreCase(JDKLEVEL)
//         && args != null && args.length == 1 && args[0] instanceof String)
//         {
//            value = getJdkLevelImpl((String)args[0]);
//         }
//      }
//      return value;
//bgp>   }

   /**
    * Gets a String value from a registered implementation method
    * or from our own JNI method.
    * @param methodname One of the method name keys, such as DB2PATH.
    */
   protected static Object getImplValue(String methodname)
   {
      Object value = null;
      Method m = (Method)implMethods.get(methodname);
      if (m != null)
         value = callMethod(m);
      if (value == null)
      {
         if (methodname.equalsIgnoreCase(DB2PATH))
            value = getDB2PathJNI();
//bgp         else if (methodname.equalsIgnoreCase(DB2CLIENTVERSION))
//            value = getDB2ClientVersionImpl();
//         else if (methodname.equalsIgnoreCase(DB2CLIENTRELEASE))
//            value = getDB2ClientReleaseImpl();
//         else if (methodname.equalsIgnoreCase(DB2CLIENTMODIFICATION))
//            value = getDB2ClientModificationImpl();
//         else if (methodname.equalsIgnoreCase(ORACLEPATH))
//            value = getOraclePathJNI();
//         else if (methodname.equalsIgnoreCase(VEPATH))
//            value = getVEPathJNI();
//         else if (methodname.equalsIgnoreCase(VELEVEL))
//         {
//            StringBuffer valbuf = new StringBuffer();
//            value = getImplValue(VEVERSION);
//            if (value != null && value.toString().length() > 0)
//            {
//               valbuf.append(value);
//               value = getImplValue(VERELEASE);
//               if (value != null && value.toString().length() > 0)
//               {
//                  valbuf.append('.').append(value);
//                  value = getImplValue(VEMODIFICATION);
//                  if (value != null && value.toString().length() > 0)
//                  {
//                     valbuf.append('.').append(value);
//                     value = valbuf.toString();
//                  }
//                  else
//                  {
//                     value = null;
//                  }
//               }
//               else
//               {
//                  value = null;
//               }
//            }
//         }
//         else if (methodname.equalsIgnoreCase(VEVERSION))
//            value = getVEVersionJNI();
//         else if (methodname.equalsIgnoreCase(VERELEASE))
//            value = getVEReleaseJNI();
//         else if (methodname.equalsIgnoreCase(VEMODIFICATION))
//            value = getVEModificationJNI();
//         else if (methodname.equalsIgnoreCase(ALIASCOUNT))
//            value = new Integer(getAliasCountJNI());
//         else if (methodname.equalsIgnoreCase(ALIASES))
//            value = getAliasesNative();
//         else if (methodname.equalsIgnoreCase(HOSTINFO))
//bgp>            value = getHostInfoNative();
      }
      return value;
   }

   /**
    * TODO: candidate for common
    * Tests whether a directory or file exists.
    * @param file The name of a directory or file.
    * @return True if the file exists.
    */
   public static boolean fileExists(String file)
   {
      File f = new File(file);
      return f.exists();
   }

//<bgp   /**
//    * Prompts the user for a value.
//    * @param key Our method key for what to prompt for.
//    * @param value The current value.
//    * @return The new value, or null if the user declines to enter a new value.
//    */
//   protected static Object promptUser(String key, String value)
//   {
//      Object[] prompter = (Object[])promptMethods.get(key);
//      if (prompter != null)
//      {
//         Class promptclass = (Class)prompter[0];
//         Method promptmethod = (Method)prompter[1];
//         if (promptclass != null && promptmethod != null)
//         {
//            Object promptinstance = ReflectionUtil.dynamicInstance(promptclass, null, null);
//            if (promptinstance != null)
//               return callMethod(promptmethod, promptinstance,
//                        new Object[]{key, value});
//         }
//      }
//      return null;
//   }
//
//   /**
//    * Returns true if we can query the registry.
//    * @return True if we can query the registry using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasRegistryValueImpl()
//   {
//      String value = (String)getImplValue(REGISTRYVALUE, new String[]{"HARDWARE\\DEVICEMAP\\VIDEO","VgaCompatible"});
//      if (value != null && value.length() > 0)
//      {
//         return true;
//      }
//      else
//      {
//         return false;
//      }
//   }
//
//   /**
//    * Gets a String value from the OS registry at a given path and key,
//    * such as [HKEY_LOCAL_MACHINE\SOFTWARE\IBM\DB2\Developer Workbench\products\com.ibm.db2.dwb]
//    * and "location"
//    * @param path The path using backslashes for Windows, forward slashes for Linux.
//    * @param key  The key for the value that we want to get.
//    * @return The value from the registry, or null if the library cannot be loaded.
//    */
//   public static String getRegistryValue(String path, String key)
//   {
//      String[] args = new String[]{path, key};
//      String prefKey = getPrefKey(REGISTRYVALUE, args);
//      String value = (String)actualValues.get(prefKey);
//      if (value == null)
//      {
//         value = (String)getStoredValue(REGISTRYVALUE, args);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(REGISTRYVALUE, args);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(prefKey);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            cacheRegistryValue(path, key, value);
//      }
//      return value;
//   }
//
//   /**
//    * Caches a value to replace anything that may be in the OS registry.
//    * @param path The path using backslashes for Windows, forward slashes for Linux.
//    * @param key  The key for the value that we want to get.
//    * @param value The value to replace the use of the registry.
//    * This value cannot be null.
//    */
//   public static void cacheRegistryValue(String path, String key, String value)
//   {
//      if (value != null)
//      {
//         String[] args = new String[]{path, key};
//         actualValues.put(getPrefKey(REGISTRYVALUE, args), value);
//      }
//   }
//   
//   /**
//    * Gets a String value from the OS registry at a given path and key
//    * @param path The path using backslashes for Windows, forward slashes for Linux.
//    * @param key  The key for the value that we want to get.
//    * @return The value from the registry, or null if the library cannot be loaded.
//    */
//   public static String getRegistryValueJNI(String path, String key)
//   {
//      return OSUtil.getRegistryValue(path, key);
//   }
//
//   /**
//    * Returns true if we can find where a local DB2 is installed.
//    * @return True if a DB2Path is found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasDB2PathImpl()
//   {
//      String value = (String)getImplValue(DB2PATH);
//      if (value != null && value.length() > 0)
//      {
//         return setDB2Path(value);
//      }
//      else
//      {
//         return false;
//      }
//bgp>   }

   /**
    * Gets the path where a local DB2 is installed.
    * @return The name of the directory where DB2 is installed.
    */
   public static synchronized String getDB2Path()
   {
      String value = (String)actualValues.get(DB2PATH);
      if (value == null)
      {
         value = (String)getStoredValue(DB2PATH, null);
         if (value == null || value.length() == 0)
            value = (String)getImplValue(DB2PATH);
         if (value == null || value.length() == 0)
            value = (String)getDefault(DB2PATH);
         if (value == null || value.equals("null"))
            value = null;
         else
            setDB2Path(cleanPath(value));
      }
      if (value != null)
          return cleanPath(value);
      else
         return null;
   }

   /**
    * Removes a trailing path separator.
    * @param path A path that could end in a slash or backslash.
    * @return The same path without the trailing path separator.
    */
   protected static synchronized String cleanPath(String path)
   {
      String slash = System.getProperty("file.separator");
      if (slash != null && slash.length() > 0)
      {
         int len = path.length();
         if (len > 0 && path.charAt(len - 1) == slash.charAt(0))
         {
            return path.substring(0,len - 1);
         }
      }
      return path;
   }
   
   /**
    * Sets the path where a local DB2 is installed.
    * @param path The name of the directory where DB2 is installed.
    * @return True if we get a valid path.
    */
   public static synchronized boolean setDB2Path(String path)
   {
      path = cleanPath(path);
      if (fileExists(path))
      {
         actualValues.put(DB2PATH, path);
      }
//<bgp      else
//      {
//         Object result = promptUser(DB2PATH, path);
//         if (result != null
//                  && result instanceof String
//                  && fileExists((String)result))
//         {
//            actualValues.put(DB2PATH, (String)result);
//bgp>         }
         else
         {
            actualValues.remove(DB2PATH);
            return false;
         }
//bgp      }
      return true;
   }

   /**
    * Gets the DB2 path from the Windows registry
    * or based on a UNIX environment variable.
    * @return The name of the directory where DB2 is installed.
    */
   public static String getDB2PathJNI()
	{
//		return OSUtil.getDB2Path();
       return null;
	}

//<bgp   /**
//    * Returns true if we can find where a local Oracle is installed.
//    * @return True if a OraclePath is found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasOraclePathImpl()
//   {
//      String value = (String)getImplValue(ORACLEPATH);
//      if (value != null && value.length() > 0)
//      {
//         actualValues.put(ORACLEPATH, value);
//         return true;
//      }
//      else
//      {
//         return false;
//      }
//   }
//
//   /**
//    * Gets the path where a local Oracle is installed.
//    * @return The name of the directory where Oracle is installed.
//    */
//   public static synchronized String getOraclePath()
//   {
//      String value = (String)actualValues.get(ORACLEPATH);
//      if (value == null)
//      {
//         value = (String)getStoredValue(ORACLEPATH, null);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(ORACLEPATH);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(ORACLEPATH);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            actualValues.put(ORACLEPATH, value);
//      }
//      return value;
//   }
//
//   /**
//    * Gets the Oracle path from the Windows registry
//    * or the ORACLE_HOME environment variable.
//    * @return The name of the directory where Oracle is installed.
//    */
//   public static String getOraclePathJNI()
//	{
//		return OSUtil.getOraclePath();
//	}
//
//   /**
//    * Returns true if we can find where a local Visual Explain is installed.
//    * @return True if a Visual Explain path is found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasVEPathImpl()
//   {
//      String value = (String)getImplValue(VEPATH);
//      return (value != null && value.length() > 0);
//   }
//
//   /**
//    * Gets the path where a local Visual Explain is installed.
//    * @return The name of the directory where Visual Explain is installed.
//    */
//   public static synchronized String getVEPath()
//   {
//      String value = (String)actualValues.get(VEPATH);
//      if (value == null)
//      {
//         value = (String)getStoredValue(VEPATH, null);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(VEPATH);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(VEPATH);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            actualValues.put(VEPATH, value);
//      }
//      return value;
//   }
//
//   /**
//    * Get the value of the install directory of VisualExplain from the Windows registry.
//    * @return The String value for VEPath
//    */
//   public static String getVEPathJNI()
//	{
//		return OSUtil.getVEPath();
//	}
//
//   /**
//    * Returns true if we can find the version of a local Visual Explain.
//    * @return True if a Visual Explain version is found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasVELevelImpl()
//   {
//      String value = (String)getImplValue(VELEVEL);
//      return (value != null && value.length() > 0);
//   }
//
//   /**
//    * Gets the version of a local Visual Explain.
//    * @return The Visual Explain version.
//    */
//   public static synchronized String getVELevel()
//   {
//      String value = (String)actualValues.get(VELEVEL);
//      if (value == null)
//      {
//         value = (String)getStoredValue(VELEVEL, null);
//         if (value == null || value.length() == 0)
//            value = (String)getStoredValue(VELEVEL, null);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(VELEVEL);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(VELEVEL);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            actualValues.put(VELEVEL, value);
//      }
//      return value;
//   }
//
//   /**
//    * Returns true if we can find the version of a local Visual Explain.
//    * @return True if a Visual Explain version is found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasVEVersionImpl()
//   {
//      String value = (String)getImplValue(VEVERSION);
//      return (value != null && value.length() > 0);
//   }
//
//   /**
//    * Gets the version of a local Visual Explain.
//    * @return The Visual Explain version.
//    */
//   public static synchronized String getVEVersion()
//   {
//      String value = (String)actualValues.get(VEVERSION);
//      if (value == null)
//      {
//         value = (String)getStoredValue(VEVERSION, null);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(VEVERSION);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(VEVERSION);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            actualValues.put(VEVERSION, value);
//      }
//      return value;
//   }
//
//   /**
//    * Get the value of the Version of VisualExplain from the Windows registry.
//    * @return The String value for the Visual Explain version
//    */
//   public static String getVEVersionJNI()
//	{
//		return OSUtil.getVEVersion();
//	}
//
//   /**
//    * Returns true if we can find the release of a local Visual Explain.
//    * @return True if a Visual Explain release is found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasVEReleaseImpl()
//   {
//      String value = (String)getImplValue(VERELEASE);
//      return (value != null && value.length() > 0);
//   }
//
//   /**
//    * Gets the release of a local Visual Explain.
//    * @return The Visual Explain release.
//    */
//   public static synchronized String getVERelease()
//   {
//      String value = (String)actualValues.get(VERELEASE);
//      if (value == null)
//      {
//         value = (String)getStoredValue(VERELEASE, null);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(VERELEASE);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(VERELEASE);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            actualValues.put(VERELEASE, value);
//      }
//      return value;
//   }
//
//   /**
//    * Get the value of the Release of Visual Explain from the Windows registry.
//    * @return The String value for the Visual Explain Release
//    */
//   public static String getVEReleaseJNI()
//	{
//		return OSUtil.getVERelease();
//	}
//
//   /**
//    * Returns true if we can find the modification level of a local Visual Explain.
//    * @return True if a Visual Explain modification level
//    * is found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasVEModificationImpl()
//   {
//      String value = (String)getImplValue(VEMODIFICATION);
//      return (value != null && value.length() > 0);
//   }
//
//   /**
//    * Gets the modification of a local Visual Explain.
//    * @return The Visual Explain modification level.
//    */
//   public static synchronized String getVEModification()
//   {
//      String value = (String)actualValues.get(VEMODIFICATION);
//      if (value == null)
//      {
//         value = (String)getStoredValue(VEMODIFICATION, null);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(VEMODIFICATION);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(VEMODIFICATION);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            actualValues.put(VEMODIFICATION, value);
//      }
//      return value;
//   }
//
//   /**
//    * Gets the value of the Modification of VisualExplain from the Windows registry.
//    * @return The String value for the Visual Explain modification level.
//    */
//   public static String getVEModificationJNI()
//	{
//		return OSUtil.getVEModification();
//	}
//
//   /**
//    * Returns true if we can find the alias count using our native implementation
//    * or overridden method.
//    * @return True if the alias count
//    * is found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasAliasCountImpl()
//   {
//      Integer value = (Integer)getImplValue(ALIASCOUNT);
//      return (value != null && value.intValue() > -1);
//   }
//
//   /**
//    * Gets the count of aliases in the client directory.
//    * @return The count.
//    */
//   public static synchronized int getAliasCount()
//   {
//      Integer value = (Integer)actualValues.get(ALIASCOUNT);
//      if (value == null)
//      {
//         // DB2DiscoveryUtil:
//         //value = DB2DiscoveryUtil.getAliasCount();
//         if (value == null)
//         {
//            // Regular override or JNI:
//            value = (Integer)getImplValue(ALIASCOUNT);
//            if (value == null) // Default:
//               value = new Integer(0);
//         }
//         actualValues.put(ALIASCOUNT, value);
//      }
//      return value.intValue();
//   }
//
//   /**
//    * Gets the count of aliases in the client directory.
//    * @return The count.
//    */
//   public static int getAliasCountJNI()
//	{
//		return DB2Util.getAliasCount();
//	}
//
//   /**
//    * Gets the names of the aliases in the client directory.
//    * @param  alias   An array of alias names (output)
//    * @param  cmt     An array of comments (output)
//    */
//   public static synchronized void getAliases(String[] alias, String[] cmt)
//   {
//      Object value = actualValues.get(ALIASES);
//      String[] aliases = null;
//      String[] cmts = null;
//      if (value == null)
//      {
//         // Regular override or JNI:
//         value = getImplValue(ALIASES);
//         if (value != null)
//         {
//            aliases = (String[])((Object[])value)[0];
//            cmts = (String[])((Object[])value)[1];
//            actualValues.put(ALIASES, value);
//         }
//      }
//      else // value != null
//      {
//         aliases = (String[])((Object[])value)[0];
//         cmts = (String[])((Object[])value)[1];
//      }
//      if (aliases != null)
//      {
//         int size = aliases.length;
//         for (int a = 0; a < size; a++)
//         {
//            alias[a] = aliases[a];
//            cmt[a] = cmts[a];
//         }
//      }
//   }
//
//   /**
//    * Gets the names of the aliases in the client directory.
//    * @return An array of two String arrays:
//    * the alias names and the comments.
//    */
//   protected static Object[] getAliasesNative()
//   {
//      int count = getAliasCountJNI();
//      String[] alias = new String[count];
//      String[] cmt = new String[count];
//      if (count > 0)
//         getAliasesJNI(alias, cmt);
//      return new Object[]{alias, cmt};
//   }
//
//   /**
//    * Gets the names of the aliases in the client directory.
//    * @param  alias   An array of alias names (output)
//    * @param  cmt     An array of comments (output)
//    */
//   public static void getAliasesJNI(String[] alias, String[] cmt)
//	{
//		DB2Util.getAliases(alias, cmt);
//	}
//
//   /**
//    * Gets the list of hostname, port number, and db name of for a list of db aliases.
//    * For each alias in the input array, its hostname, port number, and db name can be
//    * found in the corresponding entries in the output arrays.
//    *
//    * @param  alias   The array of db aliases cataloged on the client.
//    * @param  hostname (Output)The array of host names returned by the method.
//    * @param  port     (Output)The array of port numbers returned by the method.
//    * @param  dbname   (Output)The array of db names returned by the method.
//    */
//   public static synchronized void getHostInfo(
//            String[] alias,
//            String[] hostname,
//            String[] port,
//            String[] dbname)
//   {
//      Object value = actualValues.get(HOSTINFO);
//      String[] aliases = null;
//      //String[] cmts = null;
//      String[] hostnames = null;
//      String[] ports = null;
//      String[] dbnames = null;
//      if (value == null)
//      {
//         // Regular override or JNI:
//         value = getImplValue(HOSTINFO);
//         if (value != null)
//         {
//            
//            aliases = (String[])((Object[])value)[0];
//            hostnames = (String[])((Object[])value)[1];
//            ports = (String[])((Object[])value)[2];
//            dbnames = (String[])((Object[])value)[3];
//            actualValues.put(HOSTINFO, value);
//         }
//      }
//      else
//      {
//         aliases = (String[])((Object[])value)[0];
//         hostnames = (String[])((Object[])value)[1];
//         ports = (String[])((Object[])value)[2];
//         dbnames = (String[])((Object[])value)[3];
//      }
//      if (aliases != null)
//      {
//         int size = aliases.length;
//         for (int a = 0; a < size; a++)
//         {
//            alias[a] = aliases[a];
//            hostname[a] = hostnames[a];
//            port[a] = ports[a];
//            dbname[a] = dbnames[a];
//         }
//      }
//   }
//
//   /**
//    * Gets the names of the aliases in the client directory.
//    * @return An array of four String arrays:
//    * the alias names, the hostnames, the ports, and the dbnames.
//    */
//   protected static Object[] getHostInfoNative()
//   {
//      int count = getAliasCountJNI();
//      String[] alias = new String[count];
//      String[] hostname = new String[count];
//      String[] port = new String[count];
//      String[] dbname = new String[count];
//      if (count > 0)
//      {
//         String[] cmt = new String[count];
//         getAliases(alias, cmt);
//         getHostInfoJNI(alias, hostname, port, dbname);
//      }
//      return new Object[]{alias, hostname, port, dbname};
//   }
//
//   /**
//    * Gets the list of hostname, port number, and db name of for a list of db aliases.
//    * For each alias in the input array, its hostname, port number, and db name can be
//    * found in the corresponding entries in the output arrays.
//    *
//    * @param  alias   The array of db aliases cataloged on the client.
//    * @param  hostname (Output)The array of host names returned by the method.
//    * @param  port     (Output)The array of port numbers returned by the method.
//    * @param  dbname   (Output)The array of db names returned by the method.
//    */
//   public static void getHostInfoJNI(
//            String[] alias,
//            String[] hostname,
//            String[] port,
//            String[] dbname)
//	{
//		DB2Util.getHostInfo(alias, hostname, port, dbname);
//   }
//
//   /**
//    * Execute a command in the current OS environment .
//    * @param  cmd The command to execute
//    * @param  emsg The error messages in the first element (output)
//    * @return If successful, 0; otherwise, a negative number
//    */
//   public static int executeEnvJNI(
//            String cmd,
//            String workDir,
//            String javaHome,
//            Object[] emsg)
//	{
//		return OSUtil.executeEnv(cmd, workDir, javaHome, emsg);
//	}
//
//   /**
//    * Runs a command using the JDK level in a given connection
//    * in a given working directory.
//    public static String runit(String cmd, String workDir, int[] rc)
//    {
//    return runit(null, cmd, workDir, rc);
//    }
//    */
//   /**
//    * Runs a command in a given working directory.
//    * Caller must make sure the javaHome value is set by calling the setJavaHome method.
//    * <p>
//    * @param connection The connection object against which the command will run.
//    * @param cmd The command string to be executed.
//    * @param workDir The working directory to be used by the command execution.
//    * @param rc A array of int for the result of the execution.
//    public synchronized static String runit(
//    RLDBConnection connection,
//    String cmd,
//    String workDir,
//    int[] rc)
//    {
//    if (connection != null)
//    {
//    //String jdkLevel = connection.getJdkLevel();
//     //((BaseOptionsMgr)ComponentMgr.getInstance().getObjMgr(BaseOptionsMgr.OPTIONS_MGR)).getJavaHome(jdkLevel);
//      String sep = System.getProperty("file.separator");
//      StringBuffer jdkbuf = new StringBuffer(256);
//      if (javaHome != null && javaHome.length() != 0)
//      {
//      jdkbuf.append(javaHome);
//      if (!javaHome.endsWith(sep))
//      jdkbuf.append(sep);
//      jdkbuf.append("bin");
//      }
//      javaHome = jdkbuf.toString();
//      }
//      //System.out.println("ClientUtil.runit javaHome: " + javaHome);
//       Object messages[] = new Object[1];
//       rc[0] = executeEnvJNI(cmd, workDir, javaHome, messages);
//       String exemsg = (String) messages[0];
//       if (exemsg != null && exemsg.equals(""))
//       {
//       return null;
//       }
//       else
//       {
//       return exemsg;
//       }
//       }
//       */
//
//   /**
//    * Returns true if we can find an environment variable implementation.
//    * @return True if getEnv("HOME") can be found using a given implementation
//    * or native JNI call; false otherwise.
//    */
//   public static synchronized boolean hasEnvImpl()
//   {
//      String value = (String)getImplValue(ENVVAR, new Object[]{"HOME"});
//      return (value != null && value.length() > 0);
//   }
//
//   /**
//    * Gets the value of an environment variable.
//    *
//    * @param evName The environment variable's name
//    * @return The value contained in the environment variable
//    */
//   public static synchronized String getEnv(String evName)
//   {
//      String key = ENVVAR +"(" + evName + ")";
//      String value = (String)actualValues.get(key);
//      if (value == null)
//      {
//         Object[] args = new Object[]{evName};
//         value = (String)getStoredValue(ENVVAR, args);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(ENVVAR, args);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(key);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            actualValues.put(key, value);
//      }
//      return value;
//   }
//
//   /**
//    * Gets the value of an environment variable.
//    *
//    * @return The value contained in the environment variable
//    */
//   public static String getenvJNI(String evName)
//	{
//		return OSUtil.getEnv(evName);
//	}
//
//   /**
//    * Given a host, port, and database name (on the host),
//    * returns a matching DB2 alias, or null if none can be found.
//    * @param myHostName The host name or TCP/IP address of the database server.
//    * @param myPort     The DB2 server's connection port.
//    * @param myDBName   The name of the database on the server.
//    * @return The alias name or null if none match.
//    */
//   public static String findAlias(String myHostName, String myPort, String myDBName)
//   {
//		int count = ClientUtil.getAliasCount();
//		if (count > -1)
//      {
//		   String[] alias = new String[count];
//		   String[] hostname = new String[count];
//		   String[] port = new String[count];
//		   String[] dbname = new String[count];
//		   ClientUtil.getHostInfo(alias, hostname, port, dbname);
//		   for (int x = 0; x < count; x++)
//		   {
//		      //System.out.println("DbUtil - alias[x], cmt[x], hostname[x], port[x], dbname[x] " +
//		      //      alias[x] + " " +  hostname[x] + " " + port[x] + " " + dbname[x]);
//		      if (hostname[x].trim().equalsIgnoreCase(myHostName.trim())
//		      && port[x].trim().equalsIgnoreCase(myPort.trim())
//		      && dbname[x].trim().equalsIgnoreCase(myDBName.trim()))
//		         return alias[x];
//		   }
//      }
//		return null;
//   }
//
//   /**
//    * Clears the caches for ALIASCOUNT, ALIASES, and HOSTINFO,
//    * resulting in a fresh snapshot of the DB2 client information.
//    * This can be used when aliases haave been created or removed.
//    */
//	public static void cleanAliases()
//   {
//		ClientUtil.clearCache(ClientUtil.ALIASCOUNT);
//		ClientUtil.clearCache(ClientUtil.ALIASES);
//		ClientUtil.clearCache(ClientUtil.HOSTINFO);
//bgp>	}

	 /**
	  * This method uses an algorithm specifically for zSeries since 
	  * zSeries connections require the zSeries license jar.
	  */
    public static String getDB2zSeriesUniversalDriverClientJarsPath() 
    {
       // RATLC01124570 (Should pick jcc jar files from RAD directory, not from sqllib)
       // Look to pick up Universal driver jars from the RAD installation first. Theoretically,
       // the program should never get to pick up the jars from DB2 because the RAD installation will 
       // always be there (picking up the jars from the DB2 installation is kept just in case...) rm
       StringBuffer db2DriverPath = new StringBuffer();
       StringBuffer db2zSeriesLicensePath = new StringBuffer();
       StringBuffer fullPath = new StringBuffer(); 
       db2DriverPath = new StringBuffer();
       db2zSeriesLicensePath = new StringBuffer();
       String path = null;
     
       // locate plug-in driver
       try {
         path = FileLocator.resolve(
                Platform.getBundle("com.ibm.datatools.db2").getEntry( //$NON-NLS-1$
                "/")).getPath(); //$NON-NLS-1$
          
          db2DriverPath.append(path).append(File.separator)
               .append("driver").append(File.separator) //$NON-NLS-1$
               .append("db2jcc.jar"); //$NON-NLS-1$
          db2zSeriesLicensePath.append(path).append(File.separator)
               .append("driver").append(File.separator) //$NON-NLS-1$
               .append("db2jcc_license_cisuz.jar"); //$NON-NLS-1$
          File driverJar = new File(db2DriverPath.toString());
          File licenseJar = new File(db2zSeriesLicensePath.toString());
          if (driverJar.exists() && licenseJar.exists()) {
             fullPath.append(driverJar.getAbsolutePath()).append(PATH_SEPARATOR)
                  .append(licenseJar.getAbsolutePath());
          }
       } catch (Exception e) {
          // if there is an exception, then assume the driver is not found
       }
       
       if (fullPath.toString().equals("")) { //$NON-NLS-1$
          path = ClientUtil.getDB2Path();
          if (path != null && !path.equals("")) {
             db2DriverPath.append(path).append(File.separator)
                   .append("java").append(File.separator) //$NON-NLS-1$
                   .append("db2jcc.jar"); //$NON-NLS-1$
             
             db2zSeriesLicensePath.append(path).append(File.separator)
                   .append("java").append(File.separator) //$NON-NLS-1$
                   .append("db2jcc_license_cisuz.jar"); //$NON-NLS-1$
             // If this jar exists here, we have a client DB2 V8 or later.
             File driverJar = new File(db2DriverPath.toString());
             File licenseJar = new File(db2zSeriesLicensePath.toString());
             if (driverJar.exists() && licenseJar.exists()) {
                fullPath.append(db2DriverPath.toString()).append(PATH_SEPARATOR)
                      .append(db2zSeriesLicensePath.toString());
             }
          }
       }
       return fullPath.toString();
    }
    
	 /**
	  * This method uses an algorithm specifically for zSeries since 
	  * zSeries connections require the zSeries license jar.
	  */
   public static String getDB2zSeriesUniversalDriverJDBC4ClientJarsPath() 
   {
      // RATLC01124570 (Should pick jcc jar files from RAD directory, not from sqllib)
      // Look to pick up Universal driver jars from the RAD installation first. Theoretically,
      // the program should never get to pick up the jars from DB2 because the RAD installation will 
      // always be there (picking up the jars from the DB2 installation is kept just in case...) rm
      StringBuffer db2DriverPath = new StringBuffer();
      StringBuffer db2zSeriesLicensePath = new StringBuffer();
      StringBuffer fullPath = new StringBuffer(); 
      db2DriverPath = new StringBuffer();
      db2zSeriesLicensePath = new StringBuffer();
      String path = null;
    
      // locate plug-in driver
      try {
        path = FileLocator.resolve(
               Platform.getBundle("com.ibm.datatools.db2").getEntry( //$NON-NLS-1$
               "/")).getPath(); //$NON-NLS-1$
         
         db2DriverPath.append(path).append(File.separator)
              .append("driver").append(File.separator) //$NON-NLS-1$
              .append("db2jcc4.jar"); //$NON-NLS-1$
         db2zSeriesLicensePath.append(path).append(File.separator)
              .append("driver").append(File.separator) //$NON-NLS-1$
              .append("db2jcc_license_cisuz.jar"); //$NON-NLS-1$
         File driverJar = new File(db2DriverPath.toString());
         File licenseJar = new File(db2zSeriesLicensePath.toString());
         if (driverJar.exists() && licenseJar.exists()) {
            fullPath.append(driverJar.getAbsolutePath()).append(PATH_SEPARATOR)
                 .append(licenseJar.getAbsolutePath());
         }
      } catch (Exception e) {
         // if there is an exception, then assume the driver is not found
      }
      
      return fullPath.toString();
   }
   
   public static String getDB2iSeriesJT400ClientJarsPath()
   {

		/* RATLC01371512: The jt400.jar driver file is shipped with the product
	    * Hence we should pick the jar from the iseries plugin and not from the
	    *  DB2 installation */
		try {
			String path = FileLocator.resolve(
					Platform.getBundle("com.ibm.datatools.db2.iseries") //$NON-NLS-1$
                   .getEntry("driver/jt400.jar")).getPath(); //$NON-NLS-1$
			if (new File(path).exists())
				return new File(path).getAbsolutePath();
		
		/* If the driver is not found in the iseries plugin,
		 * try to locate in in the DB2 installation. This will
		 * only work if there is a local DB2 installation present
		 */
	  	    String db2Path = ClientUtil.getDB2Path();
			if (db2Path != null && !db2Path.equals("")) {
				StringBuffer db2DriverPath = new StringBuffer();
				db2DriverPath.append(db2Path).append(File.separator)
						.append("tools").append(File.separator) //$NON-NLS-1$
						.append("jt400.jar"); //$NON-NLS-1$
				File driverJar = new File(db2DriverPath.toString());
				if (driverJar.exists())
					return db2DriverPath.toString();
			}

		} catch (Exception e) {
			// if there is an exception, then assume the driver is not found
		}

		return "";

	}
    
	 /**
	  * This method uses an algorithm specifically for non-zSeries servers since
	  * non-zSeries connections do not require the zSeries license jar.
	  */
    public static String getDB2UniversalDriverClientJarsPath() 
    {
       // RATLC01124570 (Should pick jcc jar files from RAD directory, not from sqllib)
       // Look to pick up Universal driver jars from the RAD installation first. Theoretically,
       // the program should never get to pick up the jars from DB2 because the RAD installation will 
       // always be there (picking up the jars from the DB2 installation is kept just in case...) rm
       StringBuffer db2DriverPath = new StringBuffer();
       StringBuffer db2LicensePath = new StringBuffer();
       StringBuffer db2zSeriesLicensePath = new StringBuffer();
       StringBuffer fullPath = new StringBuffer(); 
       db2DriverPath = new StringBuffer();
       db2zSeriesLicensePath = new StringBuffer();
       String path = null;
      
       // locate plug-in driver
       try {
          path = FileLocator.resolve(
                Platform.getBundle("com.ibm.datatools.db2").getEntry( //$NON-NLS-1$
                "/")).getPath(); //$NON-NLS-1$
          
          db2DriverPath.append(path).append(File.separator)
               .append("driver").append(File.separator) //$NON-NLS-1$
               .append("db2jcc.jar"); //$NON-NLS-1$
          db2zSeriesLicensePath.append(path).append(File.separator)
               .append("driver").append(File.separator) //$NON-NLS-1$
               .append("db2jcc_license_cisuz.jar"); //$NON-NLS-1$
          File driverJar = new File(db2DriverPath.toString());
          File licenseJar = new File(db2zSeriesLicensePath.toString());
          if (driverJar.exists() && licenseJar.exists()) {
             fullPath.append(driverJar.getAbsolutePath()).append(PATH_SEPARATOR)
                  .append(licenseJar.getAbsolutePath());
          }
       } catch (Exception e) {
          // if there is an exception, then assume the driver is not found
       }
       
       if (fullPath.toString().equals("")) { //$NON-NLS-1$
          path = ClientUtil.getDB2Path();
          if (path != null && !path.equals("")) {
             db2DriverPath.append(path).append(File.separator)
                   .append("java").append(File.separator) //$NON-NLS-1$
                   .append("db2jcc.jar"); //$NON-NLS-1$
             
             db2LicensePath.append(path).append(File.separator)
             		.append("java").append(File.separator) //$NON-NLS-1$
   				.append("db2jcc_license_cu.jar"); //$NON-NLS-1$
             
             db2zSeriesLicensePath.append(path).append(File.separator)
                   .append("java").append(File.separator) //$NON-NLS-1$
                   .append("db2jcc_license_cisuz.jar"); //$NON-NLS-1$
           
             // If this jar exists here, we have a client DB2 V8 or later.
             File driverJar = new File(db2DriverPath.toString());
             File licenseJar = new File(db2LicensePath.toString());
             File zSeriesLicenseJar = new File(db2zSeriesLicensePath.toString());
             if (driverJar.exists() && zSeriesLicenseJar.exists()) {
                fullPath.append(db2DriverPath.toString()).append(PATH_SEPARATOR)
                		.append(db2zSeriesLicensePath.toString());
             } else if (driverJar.exists() && licenseJar.exists()) {
               fullPath.append(db2DriverPath.toString()).append(PATH_SEPARATOR)
               		.append(db2LicensePath.toString());
             }
          }
       }
       
       return fullPath.toString();
    }
 
    
	 /**
	  * This method uses an algorithm specifically for non-zSeries servers since
	  * non-zSeries connections do not require the zSeries license jar.
	  */
   public static String getDB2UniversalDriverJDBC4ClientJarsPath() 
   {
      // RATLC01124570 (Should pick jcc jar files from RAD directory, not from sqllib)
      // Look to pick up Universal driver jars from the RAD installation first. Theoretically,
      // the program should never get to pick up the jars from DB2 because the RAD installation will 
      // always be there (picking up the jars from the DB2 installation is kept just in case...) rm
      StringBuffer db2DriverPath = new StringBuffer();
      StringBuffer db2zSeriesLicensePath = new StringBuffer();
      StringBuffer fullPath = new StringBuffer(); 
      db2DriverPath = new StringBuffer();
      db2zSeriesLicensePath = new StringBuffer();
      String path = null;
     
      // locate plug-in driver
      try {
         path = FileLocator.resolve(
               Platform.getBundle("com.ibm.datatools.db2").getEntry( //$NON-NLS-1$
               "/")).getPath(); //$NON-NLS-1$
         
         db2DriverPath.append(path).append(File.separator)
              .append("driver").append(File.separator) //$NON-NLS-1$
              .append("db2jcc4.jar"); //$NON-NLS-1$
         db2zSeriesLicensePath.append(path).append(File.separator)
              .append("driver").append(File.separator) //$NON-NLS-1$
              .append("db2jcc_license_cisuz.jar"); //$NON-NLS-1$
         File driverJar = new File(db2DriverPath.toString());
         File licenseJar = new File(db2zSeriesLicensePath.toString());
         if (driverJar.exists() && licenseJar.exists()) {
            fullPath.append(driverJar.getAbsolutePath()).append(PATH_SEPARATOR)
                 .append(licenseJar.getAbsolutePath());
         }
      } catch (Exception e) {
         // if there is an exception, then assume the driver is not found
      }
      
      return fullPath.toString();
   }
    
//<bgp    /**
//	  * This method uses an algorithm specifically for DB2 Alias support 
//	  * in the Database Explorer.
//	  */
//   public static String getDB2AliasSupportClientJarsPath() 
//   {
//    StringBuffer db2DriverPath = new StringBuffer();
//    StringBuffer db2zSeriesLicensePath = new StringBuffer();
//    StringBuffer fullPath = new StringBuffer(); 
//    String path = ClientUtil.getDB2Path();
//    if (path != null && !path.equals("")) {
//       db2DriverPath.append(path).append(File.separator)
//             .append("java").append(File.separator) //$NON-NLS-1$
//             .append("db2jcc.jar"); //$NON-NLS-1$
//       
//       db2zSeriesLicensePath.append(path).append(File.separator)
//             .append("java").append(File.separator) //$NON-NLS-1$
//             .append("db2jcc_license_cisuz.jar"); //$NON-NLS-1$
//       // If this jar exists here, we have a client DB2 V8 or later;
//       // otherwise, we test for sqlj.zip; then at leat we have a client
//       // DB2.
//       File driverJar = new File(db2DriverPath.toString());
//       File licenseJar = new File(db2zSeriesLicensePath.toString());
//       if (driverJar.exists() && licenseJar.exists()) {
//          fullPath.append(db2DriverPath.toString()).append(PATH_SEPARATOR)
//                .append(db2zSeriesLicensePath.toString());
//       }
//    }
//    return fullPath.toString();
//   } 
//   
//   /**
//    * This method locates the DB2 APP driver zip of an installed DB2 client.
//    * This method is provided as legacy application support for AST.
//    */
//   public static String getDB2AppDriverClientZipPath() 
//   {
//      StringBuffer db2DriverPath = new StringBuffer();
//      StringBuffer fullPath = new StringBuffer(); 
//      String path = ClientUtil.getDB2Path();
//      if (path != null && !path.equals("")) {
//         db2DriverPath.append(path).append(File.separator)
//         .append("java").append(File.separator) //$NON-NLS-1$
//         .append("db2java.zip"); //$NON-NLS-1$
//
//         File driverJar = new File(db2DriverPath.toString());
//         if (driverJar.exists()) {
//            fullPath.append(db2DriverPath.toString());
//         }
//      }
//      return fullPath.toString();
//   }
//   
//   /**
//    * Returns the Java version for a given JDK home directory.
//    * @param jdkHome A JDK home directory, such as "c:\sqllib\java."
//    * @return The result from the "java -version" command.
//    * Returns null if the process fails.
//    * 
//    * TODO: ClientUtil APIs can be extended.
//    */
//   public static synchronized String getJdkLevel(String jdkHome)
//   {
//      String[] jdkarg = new String[]{jdkHome};
//      String value = (String)actualValues.get(getPrefKey(JDKLEVEL, jdkarg));
//      if (value == null)
//      {
//         value = (String)getStoredValue(JDKLEVEL, jdkarg);
//         if (value == null || value.length() == 0)
//            value = (String)getImplValue(JDKLEVEL, jdkarg);
//         if (value == null || value.length() == 0)
//            value = (String)getDefault(JDKLEVEL);
//         if (value == null || value.equals("null"))
//            value = null;
//         else
//            setActualValue(JDKLEVEL, value);
//      }
//      return value;
//   }
//   /** 
//    * Our implementation of getJdkLevel(String).
//    */ 
//	public static String getJdkLevelImpl(String jdkHome) {
//		String cmd = null;
//		if (jdkHome.length() > 0) {
//			StringBuffer sb;
//			if (-1 != jdkHome.indexOf(" ") && -1 == jdkHome.indexOf("\"")){
//				sb = new StringBuffer("\"" + jdkHome);
//				sb.append(File.separatorChar).append("bin") //$NON-NLS-1$
//				.append(File.separatorChar).append("java\" -version"); //$NON-NLS-1$
//			}else{
//				sb = new StringBuffer(jdkHome);
//				sb.append(File.separatorChar).append("bin") //$NON-NLS-1$
//						.append(File.separatorChar).append("java -version"); //$NON-NLS-1$
//			}
//			cmd = sb.toString();
//		} else {
//			cmd = "java -version"; //$NON-NLS-1$
//		}
//		try {
//			Process p = Runtime.getRuntime().exec(cmd);
//			InputStream istream = p.getInputStream();
//			InputStream estream = p.getErrorStream();
//			// int rc =
//			p.waitFor();
//			byte[] lbytes = new byte[250];
//			int c = istream.read(lbytes);
//			if (c < 0)
//				c = estream.read(lbytes);
//			if (c > 0) {
//				String output = new String(lbytes, 0, c);
//				int pos = output.indexOf("\""); //$NON-NLS-1$
//				if (pos < 0)
//					return null;
//				output = output.substring(pos + 1);
//				pos = output.indexOf("\""); //$NON-NLS-1$
//				output = output.substring(0, pos);
//				return output;
//			}
//			return null;
//		} catch (Exception e) {
//			// System.out.println(e.getMessage());
//			return null;
//		}
//	}
//    
//    /**
//     * Returns the translated output stream from the db2level command.
//     */
//    public static String getDb2levelOutput()
//    {
//       try
//       {
//          String cmd = "db2level";
//          Process p = Runtime.getRuntime().exec(cmd);
//          InputStream istream = p.getInputStream();
//          //int rc = 
//          p.waitFor();
//          byte[] lbytes = new byte[250];
//          int c = istream.read(lbytes);
//          return new String(lbytes, 0, c);
//       }
//       catch (Exception e)
//       {
//          //System.out.println(e.getMessage());
//          return null;
//       }
//    }
//    
//    /**
//     * Returns the DB2 level, such as "08022" for 8.2.2.
//     */
//    public static String getDB2ClientLevel()
//    {
//       String level = (String)actualValues.get(DB2CLIENTLEVEL);
//       if (level == null)
//       {
//          String outstr = getDb2levelOutput();
//          if (outstr != null)
//          {
//             int p = outstr.indexOf("\"SQL");
//             if (p > -1)
//             {
//                level = outstr.substring(p+4, p+9);
//                actualValues.put(DB2CLIENTLEVEL, level);
//             }
//             else
//             {
//               // dump out the string for now since there is no error handling.... cwu
//               System.out.println( "Unexpected output from db2level: " + outstr );
//             }
//          }
//       }
//       return level;
//    }
//    
//    /**
//     * Returns the DB2 client version, such as 8.
//     */
//    public static int getDB2ClientVersion()
//    {
//       Integer actualValue = (Integer)actualValues.get(DB2CLIENTVERSION);
//       
//       if (actualValue == null)
//       {
//          String storedValue = null;
//          if (Platform.getExtensionRegistry() != null)
//             storedValue = (String)getStoredValue(DB2CLIENTVERSION, null);
//          
//          if (storedValue == null || storedValue.equals (""))
//          	// note that getImplValue returns type Integer for key = DB2CLIENTVERSION (see code)
//          	actualValue = (Integer)getImplValue(DB2CLIENTVERSION);
//                    
//          if (actualValue == null || actualValue.intValue() == 0) {
//          	// FIXME Currently, it doesn't look like setDefault is ever called with the key DB2CLIENTVERSION!
//          	String defaultValue = (String)getDefault(DB2CLIENTVERSION);
//          
//            if (defaultValue != null)
//            	actualValue = new Integer (defaultValue);
//          }  
//       }
//       if (actualValue == null)
//          return 0;
//       else 
//          return actualValue.intValue();
//    }
//    /**
//     * Returns the DB2 client version, such as 8.
//     */
//    public static Integer getDB2ClientVersionImpl()
//    {
//       Integer version = null;
//       String level = getDB2ClientLevel();
//       if (level != null && level.length() > 1)
//       {
//          String ver = level.substring(0,2);
//          try
//          {
//             version = Integer.valueOf(ver, 10);
//             actualValues.put(DB2CLIENTVERSION, version);
//          }
//          catch (NumberFormatException e) {}
//       }
//       return version;
//    }
//    
//    /**
//     * Returns true if we can find the modification level of a local DB2 client.
//     * @return True if a DB2 client modification level
//     * is found using a given implementation
//     * or native JNI call; false otherwise.
//     */
//    public static synchronized boolean hasDB2ClientVersionImpl()
//    {
//       Integer value = (Integer)getImplValue(DB2CLIENTVERSION);
//       return (value != null && value.intValue() > 0);
//    }
//    
//    /**
//     * Returns the DB2 client release, such as 2.
//     */
//    public static int getDB2ClientRelease()
//    {
//       Integer value = (Integer)actualValues.get(DB2CLIENTRELEASE);
//       if (value == null)
//       {
////         value = (Integer)getStoredValue(DB2CLIENTRELEASE, null);
////         if (value == null || value.intValue() == 0)
////            value = (Integer)getImplValue(DB2CLIENTRELEASE);
////         if (value == null || value.intValue() == 0)
////            value = (Integer)getDefault(DB2CLIENTRELEASE);
//         
//         String storedValue = null;
//         if (Platform.getExtensionRegistry() != null)
//            storedValue = (String)getStoredValue(DB2CLIENTRELEASE, null);
//         
//         if (storedValue == null || storedValue.equals (""))
//          value = (Integer)getImplValue(DB2CLIENTRELEASE);
//                   
//         if (value == null || value.intValue() == 0) 
//         {
//           String defaultValue = (String)getDefault(DB2CLIENTRELEASE);
//             
//           if (defaultValue != null)
//             value = new Integer (defaultValue);
//         } 
//       }         
//
//       if (value == null)
//          return 0;
//       else 
//          return value.intValue();
//    }
//
//    /**
//     * Returns the DB2 client release, such as 2.
//     */
//    public static Integer getDB2ClientReleaseImpl()
//    {
//       Integer release = null;
//       String level = getDB2ClientLevel();
//       if (level != null && level.length() > 3)
//       {
//          String rel = level.substring(2,4);
//          try
//          {
//             release = Integer.valueOf(rel, 10);
//             actualValues.put(DB2CLIENTRELEASE, release);
//          }
//          catch (NumberFormatException e) {}
//       }
//       return release;
//    }
//    
//    /**
//     * Returns true if we can find the modification level of a local DB2 client.
//     * @return True if a DB2 client modification level
//     * is found using a given implementation
//     * or native JNI call; false otherwise.
//     */
//    public static synchronized boolean hasDB2ClientReleaseImpl()
//    {
//       Integer value = (Integer)getImplValue(DB2CLIENTRELEASE);
//       return (value != null && value.intValue() > 0);
//    }
//    
//    /**
//     * Returns the DB2 client modification, such as 2.
//     */
//    public static int getDB2ClientModification()
//    {
//       Integer value = (Integer)actualValues.get(DB2CLIENTMODIFICATION);
//       if (value == null)
//       {
//      	  String valueStr = (String)getStoredValue(DB2CLIENTMODIFICATION, null);
//      	  if ((valueStr != null) && (valueStr.length() > 0)) {
//            try {
//							value = Integer.parseInt(valueStr);
//						} catch (NumberFormatException e) {
//						}
//      	  }
//          if (value == null || value.intValue() == 0)
//             value = (Integer)getImplValue(DB2CLIENTMODIFICATION);
//          if (value == null || value.intValue() == 0)
//             value = (Integer)getDefault(DB2CLIENTMODIFICATION);
//       }
//       if (value == null)
//          return 0;
//       else 
//          return value.intValue();
//    }
//    /**
//     * Returns the DB2 client modification, such as 2.
//     */
//    public static Integer getDB2ClientModificationImpl()
//    {
//       Integer modification = null;
//       String level = getDB2ClientLevel();
//       if (level != null && level.length() > 4)
//       {
//          String mod = level.substring(4);
//          try
//          {
//             modification = Integer.valueOf(mod, 10);
//             actualValues.put(DB2CLIENTMODIFICATION, modification);
//          }
//          catch (NumberFormatException e) {}
//       }
//       return modification;
//    }
//
//    /**
//     * Returns true if we can find the modification level of a local DB2 client.
//     * @return True if a DB2 client modification level
//     * is found using a given implementation
//     * or native JNI call; false otherwise.
//     */
//    public static synchronized boolean hasDB2ClientModificationImpl()
//    {
//       Integer value = (Integer)getImplValue(DB2CLIENTMODIFICATION);
//       return (value != null && value.intValue() > 0);
//bgp>    }

}
