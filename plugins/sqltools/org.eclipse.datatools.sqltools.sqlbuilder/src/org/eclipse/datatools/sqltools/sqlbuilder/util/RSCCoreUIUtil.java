/*******************************************************************************
 * Copyright © 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class RSCCoreUIUtil {

   public static int launchSyncDialog(final MessageDialog dialog) {
      Display.getDefault().syncExec(new Runnable() {
         public void run() {
            dialog.open();
         };
      });
      return dialog.getReturnCode();
   }
   public static int launchASyncDialog(final MessageDialog dialog) {
      Display.getDefault().asyncExec(new Runnable() {
         public void run() {
            dialog.open();
         };
      });
      return dialog.getReturnCode();
   }
   /** Sorts a given ArrayList. */
   public static ArrayList sortList(ArrayList list) {
      Object[] a = list.toArray();
      Arrays.sort(a);
      list.clear();
      for (int i = 0; i < a.length; i++)
         list.add(a[i]);
      return list;
   }
   /** Sorts a given vector. */
   public static Vector sortVector(Vector vIn) {
      Object[] a = vIn.toArray();
      Arrays.sort(a);
      vIn.clear();
      for (int i = 0; i < a.length; i++)
         vIn.add(a[i]);
      return vIn;
   }
   /**
   * Gets a constructor.
   *
   * @param cclass
   *            The class with the method to call.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @return The constructor of class c.
   */
   protected static Constructor dynamicConstructor(Class cclass,
         Class[] classes) {
      Constructor constructor = null;
      try {
         constructor = cclass.getConstructor(classes);
      } catch (NoSuchMethodException nsme) {
         System.err
               .println("RSCCoreUIUtil.dynamicConstructor NoSuchMethodException: " //$NON-NLS-1$
                     + nsme.getMessage());
      }
      return constructor;
   }
   /**
   * Gets a constructor.
   *
   * @param classname
   *            The class with the method to call.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @return The constructor of class c.
   */
   protected static Constructor dynamicConstructor(String classname,
         Class[] classes) {
      Constructor constructor = null;
      try {
         Class cclass = Class.forName(classname);
         constructor = cclass.getConstructor(classes);
      } catch (ClassNotFoundException cnfe) {
         System.err
               .println("RSCCoreUIUtil.dynamicConstructor ClassNotFoundException: " //$NON-NLS-1$
                     + cnfe.getMessage());
      } catch (NoSuchMethodException nsme) {
         System.err
               .println("RSCCoreUIUtil.dynamicConstructor NoSuchMethodException: " //$NON-NLS-1$
                     + nsme.getMessage());
      }
      return constructor;
   }
   /**
   * Gets a method on an instance.
   *
   * @param cclass
   *            The class with the method to call.
   * @param methodname
   *            The method we want to call.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @return The Method for methodname in the instance of class classname.
   */
   protected static Method dynamicMethod(Class cclass, String methodname,
         Class[] classes) {
      Method method = null;
      try {
         method = cclass.getMethod(methodname, classes);
      } catch (NoSuchMethodException nsme) {
         System.err
               .println("RSCCoreUIUtil.dynamicMethod NoSuchMethodException: " //$NON-NLS-1$
                     + nsme.getMessage());
      }
      return method;
   }
   /**
   * Gets a method on an instance.
   *
   * @param classname
   *            The class with the method to call.
   * @param methodname
   *            The method we want to call.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @return The Method for methodname in the instance of class classname.
   */
   protected static Method dynamicMethod(String classname, String methodname,
         Class[] classes) {
      Method method = null;
      try {
         Class cclass = Class.forName(classname);
         method = dynamicMethod(cclass, methodname, classes);
      } catch (ClassNotFoundException cnfe) {
         System.err
               .println("RSCCoreUIUtil.dynamicMethod ClassNotFoundException: " //$NON-NLS-1$
                     + cnfe.getMessage());
      }
      return method;
   }
   /**
   * Gets an instance of a class using its constructor.
   *
   * @param cclass
   *            The class we want to construct.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @param args
   *            Array of arguments to the constructor, or null.
   * @return The instance of class classname.
   */
   public static Object dynamicInstance(Class cclass, Class[] classes,
         Object[] args) {
      Object result = null;
      Constructor constructor = dynamicConstructor(cclass, classes);
      if (constructor != null) {
         try {
            result = constructor.newInstance(args);
         } catch (InstantiationException ie) {
            System.err
                  .println("RSCCoreUIUtil.dynamicInstance InstantiationException: " //$NON-NLS-1$
                        + ie.getMessage());
         } catch (IllegalAccessException iae) {
            System.err
                  .println("RSCCoreUIUtil.dynamicInstance IllegalAccessException: " //$NON-NLS-1$
                        + iae.getMessage());
         } catch (InvocationTargetException ite) {
            String msg = ite.getMessage();
            if (msg == null)
               msg = ite.getTargetException().getMessage();
            System.err
                  .println("RSCCoreUIUtil.dynamicInstance InvocationTargetException: " //$NON-NLS-1$
                        + msg);
         } catch (IllegalArgumentException iarge) {
            System.err
                  .println("RSCCoreUIUtil.dynamicInstance IllegalArgumentException: " //$NON-NLS-1$
                        + iarge.getMessage());
         }
      }
      return result;
   }
   /**
   * Gets an instance of a class using its constructor.
   *
   * @param classname
   *            The class we want to construct.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @param args
   *            Array of arguments to the constructor, or null.
   * @return The instance of class classname.
   */
   public static Object dynamicInstance(String classname, Class[] classes,
         Object[] args) {
      Object result = null;
      Constructor constructor = dynamicConstructor(classname, classes);
      if (constructor != null) {
         try {
            result = constructor.newInstance(args);
         } catch (InstantiationException ie) {
            System.err
                  .println("RSCCoreUIUtil.dynamicInstance InstantiationException: " //$NON-NLS-1$
                        + ie.getMessage());
         } catch (IllegalAccessException iae) {
            System.err
                  .println("RSCCoreUIUtil.dynamicInstance IllegalAccessException: " //$NON-NLS-1$
                        + iae.getMessage());
         } catch (InvocationTargetException ite) {
            String msg = ite.getMessage();
            if (msg == null)
               msg = ite.getTargetException().getMessage();
            System.err
                  .println("RSCCoreUIUtil.dynamicInstance InvocationTargetException: " //$NON-NLS-1$
                        + msg);
         } catch (IllegalArgumentException iarge) {
            System.err
                  .println("RSCCoreUIUtil.dynamicInstance IllegalArgumentException: " //$NON-NLS-1$
                        + iarge.getMessage());
         }
      }
      return result;
   }
   /**
   * Invokes a dynamic method in a given instance.
   *
   * @param instance
   *            The instance.
   * @param methodname
   *            The method to call.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @param args
   *            Array of arguments to the constructor, or null.
   * @return Null if the invocation fails; Boolean.TRUE or the return from the
   *         invocation if it succeeds.
   */
   public static Object dynamicInvoke(Object instance, String methodname,
         Class[] classes, Object[] args) {
      Object ok = Boolean.TRUE;
      try {
         Method method = dynamicMethod(instance.getClass(), methodname,
               classes);
         if (method != null) {
            ok = method.invoke(instance, args);
         } else {
            ok = null;
         }
      } catch (NullPointerException npe) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke NullPointerException: " //$NON-NLS-1$
                     + npe.getMessage());
         ok = null;
      } catch (IllegalAccessException iae) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke IllegalAccessException: " //$NON-NLS-1$
                     + iae.getMessage());
         ok = null;
      } catch (InvocationTargetException ite) {
         String msg = ite.getMessage();
         if (msg == null)
            msg = ite.getTargetException().getMessage();
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke InvocationTargetException: " //$NON-NLS-1$
                     + msg);
         ok = null;
      } catch (IllegalArgumentException iarge) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke IllegalArgumentException: " //$NON-NLS-1$
                     + iarge.getMessage());
         ok = null;
      }
      return ok;
   }
   /**
   * Invokes a static method in a given class.
   *
   * @param classname
   *            The class with the static method.
   * @param methodname
   *            The method to call.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @param args
   *            Array of arguments to the constructor, or null.
   * @return Null if the invocation fails; Boolean.TRUE or the return from the
   *         invocation if it succeeds.
   */
   public static Object staticInvoke(String classname, String methodname,
         Class[] classes, Object[] args) {
      Object ok = Boolean.TRUE;
      try {
         Method method = dynamicMethod(classname, methodname, classes);
         if (method != null) {
            ok = method.invoke(null, args);
         }
      } catch (NullPointerException npe) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke NullPointerException: " //$NON-NLS-1$
                     + npe.getMessage());
         ok = null;
      } catch (IllegalAccessException iae) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke IllegalAccessException: " //$NON-NLS-1$
                     + iae.getMessage());
         ok = null;
      } catch (InvocationTargetException ite) {
         String msg = ite.getMessage();
         if (msg == null)
            msg = ite.getTargetException().getMessage();
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke InvocationTargetException: " //$NON-NLS-1$
                     + msg);
         ok = null;
      } catch (IllegalArgumentException iarge) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke IllegalArgumentException: " //$NON-NLS-1$
                     + iarge.getMessage());
         ok = null;
      }
      return ok;
   }
   /**
   * Invokes a static method in a given class.
   *
   * @param cclass
   *            The class with the static method.
   * @param methodname
   *            The method to call.
   * @param classes
   *            Array of Class describing the argument types, or null.
   * @param args
   *            Array of arguments to the constructor, or null.
   * @return Null if the invocation fails; Boolean.TRUE or the return from the
   *         invocation if it succeeds.
   */
   public static Object staticInvoke(Class cclass, String methodname,
         Class[] classes, Object[] args) {
      Object ok = Boolean.TRUE;
      try {
         Method method = dynamicMethod(cclass, methodname, classes);
         if (method != null) {
            ok = method.invoke(null, args);
         }
      } catch (NullPointerException npe) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke NullPointerException: " //$NON-NLS-1$
                     + npe.getMessage());
         ok = null;
      } catch (IllegalAccessException iae) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke IllegalAccessException: " //$NON-NLS-1$
                     + iae.getMessage());
         ok = null;
      } catch (InvocationTargetException ite) {
         String msg = ite.getMessage();
         if (msg == null)
            msg = ite.getTargetException().getMessage();
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke InvocationTargetException: " //$NON-NLS-1$
                     + msg);
         ok = null;
      } catch (IllegalArgumentException iarge) {
         System.err
               .println("RSCCoreUIUtil.dynamicInvoke IllegalArgumentException: " //$NON-NLS-1$
                     + iarge.getMessage());
         ok = null;
      }
      return ok;
   }
   /**
   * Gets the value of a static field.
   *
   * @param cclass
   *            The class with the static method.
   * @param fieldname
   *            The name of the field whose value is wanted.
   * @return Null if the invocation fails; the value of the field otherwise.
   */
   public static Object staticField(Class cclass, String fieldname) {
      Object value = null;
      try {
         Field field = cclass.getDeclaredField(fieldname);
         value = field.get(null);
      } catch (NoSuchFieldException nsfe) {
         System.err
               .println("RSCCoreUIUtil.staticField NoSuchFieldException: " //$NON-NLS-1$
                     + nsfe.getMessage());
      } catch (IllegalAccessException iae) {
         System.err
               .println("RSCCoreUIUtil.staticField NoSuchFieldException: " //$NON-NLS-1$
                     + iae.getMessage());
      }
      return value;
   }
   /**
   * Gets the value of a static field.
   *
   * @param classname
   *            The class with the static method.
   * @param fieldname
   *            The name of the field whose value is wanted.
   * @return Null if the invocation fails; the value of the field otherwise.
   */
   public static Object staticField(String classname, String fieldname) {
      Object value = null;
      try {
         Class cclass = Class.forName(classname);
         Field field = cclass.getDeclaredField(fieldname);
         value = field.get(null);
      } catch (ClassNotFoundException cnfe) {
         System.err
               .println("RSCCoreUIUtil.staticField ClassNotFoundException: " //$NON-NLS-1$
                     + cnfe.getMessage());
      } catch (NoSuchFieldException nsfe) {
         System.err
               .println("RSCCoreUIUtil.staticField NoSuchFieldException: " //$NON-NLS-1$
                     + nsfe.getMessage());
      } catch (IllegalAccessException iae) {
         System.err
               .println("RSCCoreUIUtil.staticField NoSuchFieldException: " //$NON-NLS-1$
                     + iae.getMessage());
      }
      return value;
   }
   /**
   * Gets the Class object for a given class name.
   *
   * @param classname
   *            The class name.
   * @return Null if the invocation fails; the Class otherwise.
   */
   public static Class dynamicClass(String classname) {
      Class cclass = null;
      try {
         cclass = Class.forName(classname);
      } catch (ClassNotFoundException cnfe) {
         System.err
               .println("RSCCoreUIUtil.dynamicClass ClassNotFoundException: " //$NON-NLS-1$
                     + cnfe.getMessage());
      }
      return cclass;
   }
   /**
   * Check if two EObjects are "equal".
   *
   * @param working
   *            working object
   * @param base
   *            base object
   * @param chkCrossReferences
   *            check eCrossReferences
   * @return
   */
   public static boolean chkIfEObjectsMatched(EObject working, EObject base,
         boolean chkCrossReferences) {
      if (base == null || working == null)
         return false;
      if (base.equals(working)) // match?
         return true;
      // check its cross references
      if (chkCrossReferences)
         if (chkCrossReferences(working.eCrossReferences(), base) == true)
            return true;
      // check its parent
      EObject parent = working.eContainer();
      if (parent == null)
         return false;
      return chkIfEObjectsMatched(parent, base, chkCrossReferences);
   }
   /**
   * Check cross references
   *
   * @param refs
   *            cross references
   * @param base
   *            base object
   * @return
   */
   public static boolean chkCrossReferences(EList refs, EObject base) {
      for (int i = 0; i < refs.size(); i++) {
         Object obj = refs.get(i); // each cross reference
         if (obj == null || !(obj instanceof EObject))
            continue;
         if (chkIfEObjectsMatched((EObject) obj, base, false) == true)
            return true;
      }
      return false;
   }


}
