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

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;

import com.ibm.icu.util.StringTokenizer;

/**
 * This class provides static methods for some common String operations
 */

public class StringUtility
{
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; //$NON-NLS-1$
  private static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; //$NON-NLS-1$
  private static final String NUMERIC = "0123456789"; //$NON-NLS-1$

   // change all occurrences of oldPat to newPat
   public  static String change(String in, String oldPat, String newPat)
   {
      if (oldPat.length() == 0)
         return in;
      if (oldPat.length() == 1 && newPat.length() == 1)
         return in.replace(oldPat.charAt(0), newPat.charAt(0));

      int lastIndex = 0;
      int newIndex = 0;
      StringBuffer newString = new StringBuffer();
      for(;;)
      {
         newIndex = in.indexOf(oldPat, lastIndex);
         if (newIndex != -1)
         {
            newString.append(in.substring(lastIndex, newIndex) + newPat);
            lastIndex = newIndex + oldPat.length();
         }
         else
         {
            newString.append(in.substring(lastIndex));
            break;
         }
      }
      return newString.toString();
   }

   // change the occurrences of oldPat to newPat starting at startPosition
   // for number of numChanges
   // Note: the 1st char in the string has position of 0

   public  static String change(String in, String oldPat, String newPat
                                , int startPos
                                , int numChanges
                                )
   {
      if (oldPat.length() == 0)
         return in;
      if (oldPat.length() == 1 && newPat.length() == 1)
         return in.replace(oldPat.charAt(0), newPat.charAt(0));

      int inLen = in.length();

      if ( startPos >= inLen )
         return in;

      int lastIndex = startPos;
      int newIndex = 0;
      int countChanges = 0;


      StringBuffer newString = new StringBuffer();

      for(;;)
      {
         newIndex = in.indexOf(oldPat, lastIndex);
         if (newIndex != -1)
         {
            newString.append(in.substring(lastIndex, newIndex) + newPat);
            lastIndex = newIndex + oldPat.length();
            countChanges++;
         }
         else
         {
            newString.append(in.substring(lastIndex));
            break;
         }

         if ( countChanges == numChanges)
         {
           newString.append(in.substring(lastIndex));
           break;
         }
      }

      return newString.toString();
   }

   public static String word(String in, int i)
   {
      StringTokenizer st = new StringTokenizer(in);
      if ( i <= 0 || i >st.countTokens())
         return ""; //$NON-NLS-1$
      else
      {
         String ret = new String();
         while (st.hasMoreTokens())
         {
            ret = st.nextToken();
            if (--i == 0)
               return ret;
         }
      }
      return ""; //$NON-NLS-1$
   }

   public static String words(String in, int i)
   {
      StringTokenizer st = new StringTokenizer(in);
      if ( i <= 0 || i >st.countTokens())
         return ""; //$NON-NLS-1$
      else
      {
         while (st.hasMoreTokens())
         {
            if (--i == 0)
               break;
            st.nextToken();
         }
         if (st.hasMoreTokens())
            return st.nextToken(""); //$NON-NLS-1$
         else
            return ""; //$NON-NLS-1$
      }
   }

   public static int numWords(String in)
   {
      StringTokenizer st = new StringTokenizer(in);
      return st.countTokens();
   }

   // return the first index within srcString that is not in the validString
   // example:
   //   srcString = "abcdefg"
   //   validString = "bcfg"
   //   return = 0 (i.e. char a is not in "bcfg")  - 1st index = 0

   public static int indexOfAnyBut(String srcString, String validString)
   {
      int result = -1;
      int srcLen = srcString.length();

      // walk backward to find if a char within srcString is in validString
      for( int i=0; i <srcLen;  i++)
      {
        // not found, stop it
        if ( validString.indexOf( srcString.charAt(i) ) == -1 )
        {
          result = i;
          break;
        }

      }

      return result;
   }

   // return the last index within srcString that is not in the validString
   // example:
   //   srcString = "abcdefg"
   //   validString = "bcfg"
   //   return = 4 (i.e. char e is not in "bcfg")  - 1st index = 0

   public static int lastIndexOfAnyBut(String srcString, String validString)
   {
      int result = -1;
      int srcLen = srcString.length();

      // walk backward to find if a char within srcString is in validString
      for( int i = srcLen-1; i >= 0 ; i-- )
      {
        // not found, stop it
        if ( validString.indexOf( srcString.charAt(i) ) == -1 )
        {
          result = i;
          break;
        }

      }

      return result;
   }

   // return number of occurrences of searchChar within srcString
   // example:
   //   srcString = "::f::f::g"
   //   seachrChar = ':'
   //   return = 6

   public static int occurrenceOf(String srcString, char searchChar)
   {
      int result = 0;
      // walk backward to find if a char within srcString is in validString
      if ( srcString.length() > 0 )
      {

        for( int i=0; i<srcString.length() ; i++ )
        {
          //  found, increment the count
          if ( searchChar == srcString.charAt(i) )
            result++;
        }
      }

      return result;
   }

   // strip the leading pString in the srcString
   // example:
   //   srcString = "::f::f::g"
   //   pString "::"
   //   return = "f::f::g"

   public static String stripLeading(String srcString, String pString)
   {
      String result;

      if ( srcString.startsWith(pString) )  // leading patString found
        result = srcString.substring( pString.length(), srcString.length());
      else                                   // not found
        result = srcString;

      return result;
   }

   // strip the trailing pString in the srcString
   // example:
   //   srcString = "f::f::g::"
   //   pString "::"
   //   return = "f::f::g"

   public static String stripTrailing(String srcString, String pString)
   {
      String result;

      if ( srcString.endsWith(pString) )  // leading patString found
        result = srcString.substring(0, srcString.lastIndexOf(pString));
      else                                   // not found
        result = srcString;

      return result;
   }

   /**
    *  strip the trailing blanks in the src
    */
   public static String stripTrailingBlanks(String src)
   {

      if (src != null) {
         while (src.length() >0) {
            if ( src.endsWith(" ") ) //$NON-NLS-1$
               src = src.substring(0, src.length()-1);
            else
               break;
         }
      }

      return src;
   }



   // return a string that contains number of copies of srcString
   // example:
   //   srcString = "abc"
   //   numberOfCopies = 2
   //   return string = "abcabc"

   public static String copy(String srcString, int numberOfCopies)
   {
      StringBuffer result = new StringBuffer();

      if ( numberOfCopies > 0 )
      {
        for(int i=1; i<= numberOfCopies; i++)
          result.append(srcString);
      }
      else
          result = new StringBuffer(srcString);

      return result.toString();
   }

   //
   // return true if all chars in srcString are in {a...z} or {A...Z}

   public static boolean isAlphabetic(String srcString)
   {
      return ( lastIndexOfAnyBut( srcString, ALPHABET ) == -1  );
   }

   //
   // return true if all chars in srcString are in {a...z,} or {A...Z} {0...9}

   public static boolean isAlphanumeric(String srcString)
   {
      return ( lastIndexOfAnyBut( srcString, ALPHANUMERIC ) == -1  );
   }


   //
   // return true if all chars are in '0' - '9'

   public static boolean isDigits(String srcString)
   {
      return ( lastIndexOfAnyBut( srcString, NUMERIC ) == -1  ) ;
   }


   public static boolean isEmptyOrNull(String string)
   {
     return string == null || string.length() == 0;
   }


   //
   // return the string after the matching token is removed
   public static String match(String in, String token) throws Exception
   {
      if (in == null)
         return null;

      in = in.trim();
      if (in.startsWith(token))
         return in.substring(token.length(), in.length());
      else
         throw new Exception(Messages.STRING_MATCH_EXPECTED_EXCEPTION_MESSAGE + token + Messages.STRING_MATCH_RETURNED_EXCEPTION_MESSAGE + word(in, 1)); 
   }

  public static long getLong(String str)
  {
     try {
        return Long.parseLong(str);
     }
     catch(Exception m) {
        return 0;
     }
  }

   // return true if the "  " appears within srcString
   // example:
   //   srcString = "a  m"
   //   return = true

   public static boolean containsDoubleBlanks(String srcString)
   {
      String bb = "  "; //$NON-NLS-1$
      char b = bb.charAt(0);

      if ( srcString.length() > 0 )
      {
        for(int i=0; i < (srcString.length()-1) ; i++)
        {
          if ((b == srcString.charAt(i)) & (b == srcString.charAt(i+1)))
            return true;
        }
      }
      return false;
   }

  public static String stripNewLines(String label)
  {
    String newLabel = ""; //$NON-NLS-1$
    String newLine = System.getProperties().getProperty("line.separator"); //$NON-NLS-1$

    int i = label.indexOf(newLine);
    if (i == -1)
    {
      newLabel = label;
    } // end of if ()
    else
    {
      while (i != -1)
      {
        newLabel += label.substring(0, i);
        label = label.substring(i + 2);
        i = label.indexOf(newLine);
      } // end of if ()
      if (label.length() > 0)
      {
        newLabel += label;
      } // end of if ()
    } // end of else
    return newLabel;
  }

  public static String toUnicode(String value)
  {
    if (value == null)
    {
      return value;
    }

    String result = ""; //$NON-NLS-1$
    for (int i=0; i < value.length(); i++)
    {
      char character = value.charAt(i);
      if (character == '>')
      {
        result += "&gt;"; //$NON-NLS-1$
      }
      else if (character == '<')
      {
        result += "&lt;"; //$NON-NLS-1$
      }
      else
      {
        result += character;
      }
    }
    //System.out.println(value + " " + result);
    return result;
  }
};

