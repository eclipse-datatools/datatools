/*
 ******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *     
 ******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.datatools.connectivity.oda.nls.Messages;

/**
 * StringSubstitutionUtil is a general utility that any ODA provider can use, 
 * which performs string substitutions.  The utility is designed for ODA 
 * data sources that has a concept of embedded delimited strings in text strings.  
 * For example, an ODA driver query text could contain embedded parameters in the 
 * form of a colon followed by a parameter name, like ":myVariable".  Two forms of 
 * string substitutions are supported by the utility: subsitution by index 
 * or by name.
 */
public final class StringSubstitutionUtil 
{	
	private StringSubstitutionUtil()
	{
		// class is not meant to be instantiated
	}

	//---------------------------------------------------------------
	// For logging purposes
	//---------------------------------------------------------------
	
	private static String sm_loggerName = StringSubstitutionUtil.class.getName();;
	private static Logger sm_logger = Logger.getLogger( sm_loggerName );
	
	/**
	 * Sets the <code>StringSubstitutionUtil</code> logger to log its 
	 * utility methods.
	 * @param logger	the logger that <code>StringSubstitutionUti</code> 
	 * 					uses to log its methods.
	 */
	public static void setLogger( Logger logger )
	{
		sm_logger = logger;
	}
	
	/**
	 * Resets the <code>StringSubstitutionUtil</code> logger.  The caller 
	 * of <code>setLogger</code> should call this to remove its logger from 
	 * the <code>StringSubstituionUtil</code>.  Otherwise, subsequent calls 
	 * to this utility's methods by other callers may be logged in the 
	 * set logger.
	 */
	public static void resetLogger()
	{
		sm_logger = null;
	}
	
	private static void log( String message )
	{
		if( sm_logger != null )
			sm_logger.log( Level.FINEST, message );
	}
	
	private static void log( Throwable thrown )
	{
		if( sm_logger != null )
			sm_logger.log( Level.WARNING, thrown.getLocalizedMessage(),
			        		thrown );
	}
	
	/**
	 * Returns the number of named and un-named delimited strings in the text argument, where 
	 * the delimited strings are labeled by only a start delimiter. Calls 
	 * <code>getDelimitedStringCount( text, startDelimiter, false )</code>.
	 * @param text				string containing delimited strings.
	 * @param startDelimiter	the start delimiter string.
	 * @return					the number of named and un-named delimited strings.
	 * @see StringSubstitutionUtil#getDelimitedStringCount(String, String, boolean) 
	 * 		getDelimitedStringCount
	 */
	public static int getDelimitedStringCount( String text,
									 		   String startDelimiter )
	{
		return getDelimitedStringCount( text, startDelimiter, 
										false /* requiresNamedDelimiters */ );
	}
	
	/**
	 * Returns the number of delimited strings in the text argument, where the delimited 
	 * strings are labeled by only a start delimiter.  If <code>requiresNamedDelimiters</code> 
	 * is set to true, only the named delimiters will be counted.  This should be used when 
	 * the caller wants to perform substitution by name on the text string. Otherwise, both named 
	 * and un-named delimiters will be counted.  This should be used when the caller wants 
	 * to perform substitution by index on the text string.
	 * <br>
	 * <br>
	 * <b>For example:</b><br>
	 * text = ":param1 :param2 :param3"<br>
	 * startDelimiter = ":"<br>
	 * returns: 3
	 * @param text					string containing delimited strings.
	 * @param startDelimiter		the start delimiter string.
	 * @param requiresNamedDelimiters
	 * 								determines whether only named delimiters will be counted.
	 * @return						the number of delimited strings.
	 * @throws NullPointerException	if text or startDelimiter is <code>null</code>.
	 */
	public static int getDelimitedStringCount( String text,
	 		   								   String startDelimiter, 
											   boolean requiresNamedDelimiters )
	{
		String context = "StringSubstitutionUtil.getDelimitedStringCount( " + //$NON-NLS-1$
						 text + ", " + startDelimiter + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
						 requiresNamedDelimiters + " )\t"; //$NON-NLS-1$
		log( context + "Called." ); //$NON-NLS-1$
	
		sanityCheck( text, startDelimiter );
	
		// we'll remove all beginning and trailing whitespaces
		startDelimiter = sanityCheckDelimiter( startDelimiter );
		int startDelimiterLength = startDelimiter.length();
	
		StringBuffer stringBuffer = new StringBuffer( text );
	
		int index = 0;
		int numOfDelimitedStrings = 0;
		while( ( index = text.indexOf( startDelimiter, index ) ) >= 0 )
		{
			log( context + "index: " + index ); //$NON-NLS-1$
	
			int endIndex = getDelimitedStringEndIndex( stringBuffer, index, 
						   							   startDelimiterLength,
													   requiresNamedDelimiters );
			if( endIndex == -1 )
			{
				index += startDelimiterLength;
				continue;
			}
	
			numOfDelimitedStrings++;
			index = endIndex + 1;
		}
	
		log( context + "Exiting: " + numOfDelimitedStrings ); //$NON-NLS-1$
		return numOfDelimitedStrings;
	}
	
	/**
	 * Returns the number of named and un-named delimited strings in the text 
	 * argument, where the delimited strings are labeled by a start delimiter 
	 * and an end delimiter. Calls 
	 * <code>getDelimitedStringCount( text, startDelimiter, endDelimiter, false )</code>.
	 * @param text				string containing delimited strings.
	 * @param startDelimiter	the start delimiter string.
	 * @param endDelimiter		the end delimiter string.
	 * @return					the number of named and un-named delimited strings.
	 * @see	StringSubstitutionUtil#getDelimitedStringCount(String, String, String, boolean)
	 * 		getDelimitedStringCount
	 */
	public static int getDelimitedStringCount( String text,
											   String startDelimiter,
											   String endDelimiter )
	{
		return getDelimitedStringCount( text, startDelimiter, endDelimiter, 
				 						false /* requiresNamedDelimiters */ );
	}
	
	/**
	 * Returns the number of delimited strings in the text argument, where the delimited 
	 * strings are labeled by a start delimiter and an end delimiter. If <code>requiresNamedDelimiters</code> 
	 * is set to true, only the named delimiters will be counted.  This should be used when 
	 * the caller wants to perform substitution by name on the text string. Otherwise, both named 
	 * and un-named delimiters will be counted.  This should be used when the caller wants 
	 * to perform substitution by index on the text string.
	 * <br>
	 * <br>
	 * <b>For example:</b><br>
	 * text = "select &lt;start&gt;param1&lt;end&gt;.* from STUDENT"<br>
	 * startDelimiter = "&lt;start&gt;"<br>
	 * endDelimiter = "&lt;end&gt;"<br>
	 * returns: 1
	 * @param text					string containing delimited strings.
	 * @param startDelimiter		the start delimiter string.
	 * @param endDelimiter			the end delimiter string.
	 * @param requiresNamedDelimiters
	 * 								determines whether only named delimiters will be counted.
	 * @return						the number of delimited strings.
	 * @throws NullPointerException	if text, startDelimiter, or endDelimiter
	 * 								is <code>null</code>.
	 */
	public static int getDelimitedStringCount( String text,
			   								   String startDelimiter,
											   String endDelimiter,
											   boolean requiresNamedDelimiters )
	{
		String context = "StringSubstitutionUtil.getDelimitedStringCount( " + //$NON-NLS-1$
		 				 text + ", " + startDelimiter + ", " + endDelimiter +  //$NON-NLS-1$ //$NON-NLS-2$
						 ", " + requiresNamedDelimiters +" )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		log( context + "Called." ); //$NON-NLS-1$

		sanityCheck( text, startDelimiter, endDelimiter );

		// we'll remove all beginning and trailing whitespaces
		startDelimiter = sanityCheckDelimiter( startDelimiter );
		endDelimiter = sanityCheckDelimiter( endDelimiter );

		int startDelimiterLength = startDelimiter.length();
		int endDelimiterLength = endDelimiter.length();

		StringBuffer stringBuffer = new StringBuffer( text );

		int startIndex = 0;
		int currIndex = 0;
		int numOfDelimitedStrings = 0;
		while( ( startIndex = text.indexOf( startDelimiter, currIndex ) ) >= 0 )
		{
			log( context + "startIndex: " + startIndex + ", currIndex: " + currIndex ); //$NON-NLS-1$ //$NON-NLS-2$

			currIndex = startIndex + startDelimiterLength;

			// check whether it's really a start delimiter
			if( ! isStartDelimiter( startIndex, stringBuffer ) )
				continue;

			int endIndex = getDelimitedStringEndIndex( stringBuffer, startIndex, 
								 	   				   startDelimiterLength, 
													   endDelimiter,
													   requiresNamedDelimiters );

			if( endIndex < 0 )
				break;

			numOfDelimitedStrings++;
			endIndex += endDelimiterLength;
			currIndex = endIndex;
		}

		log( context + "Exiting: " + numOfDelimitedStrings ); //$NON-NLS-1$
		return numOfDelimitedStrings;		
	}
	
	/**
	 * Performs string substitution based on index, where the delimited strings
	 * are labeled by only a start delimiter.
	 * <br>
	 * <br>
	 * <b>For example:</b><br>
	 * text = "SELECT STUDENT.:COLUMN, STUDENT.:COLUMN FROM STUDENT"<br>
	 * startDelimiter = ":"<br>
	 * substitutionList = ["ID", "NAME"]<br>
	 * returns: "SELECT STUDENT.ID, STUDENT.NAME FROM STUDENT"
	 * @param text					text string containing delimited strings.
	 * @param startDelimiter		the start delimiter string.
	 * @param substitutionList		list of substitution values for the delimited strings.
	 * @return						the fully substituted string.
	 * @throws NullPointerException	if text, startDelimiter, or substitutionList
	 * 								is <code>null</code>.
	 */
	public static String substituteByIndex( String text,
											String startDelimiter,
											List substitutionList )
	{
		String context = "StringSubstitutionUtil.substituteByIndex( " +  //$NON-NLS-1$
						 text + ", " + startDelimiter + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
						 substitutionList + " )\t"; //$NON-NLS-1$
		log( context + "Called." ); //$NON-NLS-1$
		
		sanityCheck( text, startDelimiter, substitutionList );
		
		// we'll remove all beginning and trailing whitespaces
		startDelimiter = sanityCheckDelimiter( startDelimiter );
		int startDelimiterLength = startDelimiter.length();
		
		// string buffer is more efficient for manipulating strings
		StringBuffer stringBuffer = new StringBuffer( text );
		
		int index = 0;
		ListIterator substitutionListIter = substitutionList.listIterator();
		// find the index of the delimiter
		while( ( index = stringBuffer.toString().indexOf( startDelimiter, index ) ) >= 0 )
		{
			log( context + "index: " + index ); //$NON-NLS-1$
			
			int endIndex = getDelimitedStringEndIndex( stringBuffer, index, 
													   startDelimiterLength,
													   false /* requiresNamedDelimiters */ );
			if( endIndex == -1 )
			{
				index += startDelimiterLength;
				continue;
			}
			
			if( ! substitutionListIter.hasNext() )
			{
			    String message = Messages.bind( Messages.stringSubUtil_NO_STRING_VALUE_TO_REPLACE,
						   				stringBuffer.substring( index + startDelimiterLength, 
						   			        								endIndex ) );            
				throw newIllegalArgumentException( message );
			}
			
			String replacementString = ( String ) substitutionListIter.next();
			if( replacementString == null )
			{
				throw newIllegalArgumentException( Messages.stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL );
			}
			
			stringBuffer.replace( index, endIndex, replacementString );
			index += ( replacementString.length() );
		}
		
		String ret = stringBuffer.toString();
		
		log( context + "Exiting: " + ret ); //$NON-NLS-1$
		return ret; 
	}

	/**
	 * Performs string substitution based on index, where the delimited strings
	 * are labeled by a start delimiter and an end delimiter.
	 * <br>
	 * <br>
	 * <b>For example:</b><br>
	 * text = "SELECT &lt;start&gt;TABLE&lt;end&gt;.&lt;start&gt;COLUMN&lt;end&gt; FROM STUDENT"<br>
	 * startDelimiter = "&lt;start&gt;"<br>
	 * endDelimiter = "&lt;end&gt;"<br>
	 * substitutionList = ["STUDENT", "NAME"]<br>
	 * returns: "SELECT STUDENT.NAME FROM STUDENT"
	 * @param text					text string containing delimited strings.
	 * @param startDelimiter		the start delimiter string.
	 * @param endDelimiter			the end delimiter string.
	 * @param substitutionList		list of substitution values for the delimited strings.
	 * @return						the fully substituted string.
	 * @throws NullPointerException	if text, startDelimiter, endDelimiter, or
	 * 								substitutionList is <code>null</code>.
	 */
	public static String substituteByIndex( String text,
											String startDelimiter,
											String endDelimiter,
											List substitutionList )
	{
		String context = "StringSubstitutionUtil.substituteByIndex( " +  //$NON-NLS-1$
						 text + ", " + startDelimiter + ", " + endDelimiter +  //$NON-NLS-1$ //$NON-NLS-2$
						 ", " + substitutionList + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		log( context + "Called." ); //$NON-NLS-1$
		
		sanityCheck( text, startDelimiter, endDelimiter, 
					 substitutionList );

		// we'll remove all beginning and trailing whitespaces
		startDelimiter = sanityCheckDelimiter( startDelimiter );
		endDelimiter = sanityCheckDelimiter( endDelimiter );
		
		int startDelimiterLength = startDelimiter.length();
		int endDelimiterLength = endDelimiter.length();

		// string buffer is more efficient for manipulating strings
		StringBuffer stringBuffer = new StringBuffer( text );
		
		ListIterator substitutionListIter = substitutionList.listIterator();
		int startIndex = 0;
		int currIndex = 0;
		while( ( startIndex = stringBuffer.toString().indexOf( startDelimiter, currIndex ) ) >= 0 )
		{
			log( context + "startIndex: " + startIndex + ", currIndex: " + currIndex ); //$NON-NLS-1$ //$NON-NLS-2$
			
			currIndex = startIndex + startDelimiterLength;
			
			// check whether it's really a start delimiter
			if( ! isStartDelimiter( startIndex, stringBuffer ) )
				continue;
			
			int endIndex = getDelimitedStringEndIndex( stringBuffer, startIndex, 
												 	   startDelimiterLength, 
													   endDelimiter,
													   false /* requiresNamedDelimiters */ );

			if( endIndex < 0 )
				break;
			
			if( ! substitutionListIter.hasNext() )
			{
			    String message = Messages.bind( Messages.stringSubUtil_NO_STRING_VALUE_TO_REPLACE,
											   stringBuffer.substring( startIndex + startDelimiterLength, 
											           						endIndex ) );
				throw newIllegalArgumentException( message );
			}
			
			String replacementString = ( String ) substitutionListIter.next();	
			if( replacementString == null )
				throw newIllegalArgumentException( Messages.stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL );
			
			endIndex += endDelimiterLength;
			stringBuffer.replace( startIndex, endIndex, replacementString );
			currIndex = startIndex + replacementString.length();
		}

		String ret = stringBuffer.toString();
		
		log( context + "Exiting: " + ret ); //$NON-NLS-1$
		return ret;
	}
	
	/**
	 * Performs string substitution based on name, where the delimited strings
	 * are labeled by only a start delimiter.
	 * <br>
	 * <br>
	 * <b>For example:</b><br>
	 * text = "SELECT ?PARAM.?PARAM1 FROM ?PARAM"<br>
	 * startDelimiter = "?"<br>
	 * nameValues = {PARAM=PEOPLE, PARAM1=NAME}<br>
	 * returns: "SELECT PEOPLE.NAME FROM PEOPLE"<br>
	 * @param text					text string containing delimited strings.
	 * @param startDelimiter		the start delimiter string.
	 * @param nameValues			map of substitution name-value pairs.
	 * @return						the fully substituted string.
	 * @throws NullPointerException	if text, startDelimiter, or nameValues is
	 * 								<code>null</code>.
	 */
	public static String substituteByName( String text,
										   String startDelimiter,
										   Map nameValues )
	{
		String context = "StringSubstitutionUtil.substituteByName( " +  //$NON-NLS-1$
						 text + ", " + startDelimiter + ", " + nameValues + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		log( context + "Called." ); //$NON-NLS-1$
		
		sanityCheck( text, startDelimiter, nameValues );
		
		// we'll remove all beginning and trailing whitespaces
		startDelimiter = sanityCheckDelimiter( startDelimiter );
		int startDelimiterLength = startDelimiter.length();
		
		// string buffer is more efficient for manipulating strings
		StringBuffer stringBuffer = new StringBuffer( text );
		
		int index = 0;
		// find the index of the delimiter
		while( ( index = stringBuffer.toString().indexOf( startDelimiter, index ) ) >= 0 )
		{
			log( context + "index: " + index ); //$NON-NLS-1$
			
			int endIndex = getDelimitedStringEndIndex( stringBuffer, index, 
													   startDelimiterLength,
													   true /* requiresNamedDelimiters */ );
			if( endIndex == -1 )
			{
				index += startDelimiterLength;
				continue;
			}
			
			// found the start and end of the delimited string
			String delimitedString = stringBuffer.substring( index + startDelimiterLength, 
														   endIndex );
			String replacementString = ( String ) nameValues.get( delimitedString );
			if( replacementString == null )
			{
			    String message = Messages.stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL;
			    message += " [" + delimitedString + "]"; //$NON-NLS-1$ //$NON-NLS-2$
				throw newIllegalArgumentException( message );
			}
			
			stringBuffer.replace( index, endIndex, replacementString );
			index += ( replacementString.length() );
		}
		
		String ret = stringBuffer.toString();
		
		log( context + "Exiting: " + ret ); //$NON-NLS-1$
		return ret;
	}
	
	/**
	 * Performs string substitution based on name, where the delimited strings
	 * are labeled by a start delimiter and an end delimiter.
	 * <br>
	 * <br>
	 * <b>For example:</b><br>
	 * text = "SELECT :PARAM:.:PARAM1: FROM :PARAM:<br>
	 * startDelimiter = ":"<br>
	 * endDelimiter = ":"<br>
	 * nameValues = {PARAM=PEOPLE, PARAM1=NAME}<br>
	 * returns: "SELECT PEOPLE.NAME FROM PEOPLE"<br>
	 * @param text					text string containing delimited strings.
	 * @param startDelimiter		the start delimiter string.
	 * @param endDelimiter			the end delimiter string.
	 * @param nameValues			map of substitution name-value pairs.
	 * @return						the fully substituted string.
	 * @throws NullPointerException	if text, startDelimiter, endDelimiter, or
	 * 								nameValues is <code>null</code>.
	 */
	public static String substituteByName( String text,
										   String startDelimiter,
										   String endDelimiter,
										   Map nameValues )
	{
		String context = "StringSubstitutionUtil.substituteByName( " +  //$NON-NLS-1$
						 text + ", " + startDelimiter + ", " + endDelimiter +  //$NON-NLS-1$ //$NON-NLS-2$
						 ", " + nameValues + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		log( context + "Called." ); //$NON-NLS-1$
		
		sanityCheck( text, startDelimiter, 
					 endDelimiter, nameValues );

		// we'll remove all beginning and trailing whitespaces
		startDelimiter = sanityCheckDelimiter( startDelimiter );
		endDelimiter = sanityCheckDelimiter( endDelimiter );

		int startDelimiterLength = startDelimiter.length();
		int endDelimiterLength = endDelimiter.length();
		
		// string buffer is more efficient for manipulating strings
		StringBuffer stringBuffer = new StringBuffer( text );

		int startIndex = 0;
		int currIndex = 0;
		while( ( startIndex = stringBuffer.toString().indexOf( startDelimiter, currIndex ) ) >= 0 )
		{
			log( context + "startIndex: " + startIndex + ", currIndex: " + currIndex ); //$NON-NLS-1$ //$NON-NLS-2$
			
			currIndex = startIndex + startDelimiterLength;
			
			// check whether it's really a start delimiter
			if( ! isStartDelimiter( startIndex, stringBuffer ) )
				continue;
			
			int endIndex = getDelimitedStringEndIndex( stringBuffer, startIndex, 
												 	   startDelimiterLength, 
													   endDelimiter,
													   true /* requiresNamedDelimiters */ );

			if( endIndex < 0 )
				break;
			
			String delimitedString = stringBuffer.substring( startIndex + startDelimiterLength,
														   endIndex );
			
			String replacementString = ( String ) nameValues.get( delimitedString );
			if( replacementString == null )
			{
			    String message = Messages.stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL;
			    message += " [" + delimitedString + "]"; //$NON-NLS-1$ //$NON-NLS-2$
				throw newIllegalArgumentException( message );
			}
			
			endIndex += endDelimiterLength;
			stringBuffer.replace( startIndex, endIndex, replacementString );
			currIndex = startIndex + replacementString.length();
		}

		String ret = stringBuffer.toString();
		
		log( context + "Exiting: " + ret ); //$NON-NLS-1$
		return ret;
	}

	//-------------------------------------------------------------------------
	// helper methods for sanity checks
	
	private static void sanityCheck( String text,
									 String startDelimiter,
									 String endDelimiter,
									 Object listOrMap )
	{
		sanityCheck( text, startDelimiter, endDelimiter );
		
		sanityCheck( listOrMap );
	}
	
	private static void sanityCheck( String text, 
									 String startDelimiter,
									 Object listOrMap )
	{
		sanityCheck( text, startDelimiter );
		
		sanityCheck( listOrMap );
	}

	private static void sanityCheck( String text,
									 String startDelimiter,
									 String endDelimiter )
	{
		sanityCheck( text, startDelimiter );
		
		if( endDelimiter == null )
			throw newNullPointerException( Messages.stringSubUtil_DELIMITER_CANNOT_BE_NULL );
	}
	
	private static void sanityCheck( String text,
								     String startDelimiter )
	{
		if( text == null )
			throw newNullPointerException( Messages.stringSubUtil_TEXT_STRING_CANNOT_BE_NULL );
		
		if( startDelimiter == null )
			throw newNullPointerException( Messages.stringSubUtil_DELIMITER_CANNOT_BE_NULL );
	}
	
	private static void sanityCheck( Object listOrMap )
	{
		if( listOrMap == null )
		{
			String message = listOrMap instanceof List  ? 
        						Messages.stringSubUtil_SUBSTITUTION_LIST_CANNOT_BE_NULL :
        						Messages.stringSubUtil_NAME_VALUE_MAP_CANNOT_BE_NULL;	
			throw newNullPointerException( message );
		}
	}
	
	private static String sanityCheckDelimiter( String delimiter )
	{
		String trimmed = delimiter.trim();
		int length = trimmed.length();
		if( length == 0 )
			throw newIllegalArgumentException( Messages.stringSubUtil_DELIMITER_CANNOT_BE_EMPTY );
		
		return trimmed;
	}
	
	// returns -1 if we see that the delimiter isn't part of a delimited string, 
	// otherwise it will return the end index of the delimited string
	
	private static int getDelimitedStringEndIndex( StringBuffer stringBuffer, 
												   int index,
												   int startDelimiterLength, 
												   boolean requiresNamedDelimiters )
	{
		String context = "StringSubstitutionUtil.getDelimitedStringEndIndex( " + //$NON-NLS-1$
						 stringBuffer + ", " + index + ", " + //$NON-NLS-1$ //$NON-NLS-2$
						 startDelimiterLength + " )\t"; //$NON-NLS-1$
		log( context + "Called." ); //$NON-NLS-1$
		
		// check whether it's really a start delimiter
		if( ! isStartDelimiter( index, stringBuffer ) )
		{
			log( context + "Not a start delimiter. Exiting: -1" ); //$NON-NLS-1$
			return -1;
		}
		
		int endIndex = index + startDelimiterLength;
		while( endIndex < stringBuffer.length() && 
			   isStringMarkerCharacter( stringBuffer.charAt( endIndex ) ) )
			endIndex++;

		// if the endIndex is still the same as the index after the start delimiter, 
		// then it may be an empty marker.  We only support empty markers for substitution 
		// by index.  We also need to check the character to see whether it's the 
		// end of the string or a white space character.
		// if that's the case, then it would be considered an embedded marker.
		// i.e.: select * from aaa where id  = ? and aaaId < ?
		// where the ? is the delimiter
		if( endIndex == index + startDelimiterLength &&
			( requiresNamedDelimiters || 
			  ( endIndex < stringBuffer.length() &&
			  	! Character.isWhitespace( stringBuffer.charAt( endIndex ) ) ) ) )
		{
			log( context + "Not an embedded marker. Exiting: -1" ); //$NON-NLS-1$
			return -1;
		}

		log( context + "Exiting: " + endIndex ); //$NON-NLS-1$
		return endIndex; 
	}
	
	private static int getDelimitedStringEndIndex( StringBuffer stringBuffer, 
											 	   int startIndex, 
												   int startDelimiterLength,
												   String endDelimiter,
												   boolean requiresNamedDelimiters )
	{
		String context = "StringSubstitutional.getDelimitedStringEndIndex( " + //$NON-NLS-1$
						 stringBuffer + ", " + startIndex + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
						 startDelimiterLength + ", " + endDelimiter + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		log( context + "Called." ); //$NON-NLS-1$
		
		int currIndex = startIndex + startDelimiterLength;
		int endDelimiterLength = endDelimiter.length();
		
		int endIndex = -1;
		String string = stringBuffer.toString();
		while( ( endIndex = string.indexOf( endDelimiter, currIndex ) ) >= 0 )
		{
			log( context + "endIndex: " + endIndex + ", currIndex: " + currIndex ); //$NON-NLS-1$ //$NON-NLS-2$
			
			// check whether it's really an end delimiter
			if( isEndDelimiter( startIndex + startDelimiterLength, endIndex,
				  			    endDelimiterLength, string, requiresNamedDelimiters ) )
				break;
			
			// look for another end delimiter
			currIndex = endIndex + endDelimiterLength;
		}		
		
		log( context + "Exiting: " + endIndex ); //$NON-NLS-1$
		return endIndex;
	}

	private static boolean isStringMarkerCharacter( char c )
	{
		return Character.isLetterOrDigit( c ) ||
			   isContinuatorCharacter( c );
	}
	
	private static boolean isContinuatorCharacter( char c )
	{
		return ( c == '_' );
	}
	
	private static boolean isEscapeCharacter( char c )
	{
		return ( c == '\\' );
	}

	private static boolean isStartDelimiter( int startDelimiterIndex,
											 StringBuffer stringBuffer )
	{
		String context = "StringSubstitutionUtil.isStartDelimiter( " +  //$NON-NLS-1$
						 startDelimiterIndex + ", " + stringBuffer + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		log( context + "Called." ); //$NON-NLS-1$
		
		// it's a start delimiter if it's at the beginning of the string
		if( startDelimiterIndex == 0 )
		{
			log( context + "Beginning of string. Exiting: " + true ); //$NON-NLS-1$
			return true;
		}
		
		// check that the character before the start delimiter isn't 
		// an alpha numeric character or underscore or the escape character
		char prevChar = stringBuffer.charAt( startDelimiterIndex - 1 );
		boolean ret = ( ! isStringMarkerCharacter( prevChar ) &&
				 		! isEscapeCharacter( prevChar ) );
		
		log( context + "Exiting: " + ret ); //$NON-NLS-1$
		return ret;
	}
	
	private static boolean isEndDelimiter( int afterStartDelimiterIndex, int endIndex,
										   int endDelimiterLength, String string,
										   boolean requiresNamedDelimiters )
	{
		String context = "StringSubsitutionUtil.isEndDelimiter( " +  //$NON-NLS-1$
						 afterStartDelimiterIndex + ", " + endIndex + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
						 endDelimiterLength + ", " + string + " )\t"; //$NON-NLS-1$ //$NON-NLS-2$
		log( context + "Called." ); //$NON-NLS-1$
		
		// if the end delimiter occurs right after the start delimiter and 
		// we require named delimiters, then it's not an end delimiter.
		if( endIndex == afterStartDelimiterIndex &&
			requiresNamedDelimiters )
		{
			log( context + "Requires a named delimiter. Exiting: " + false ); //$NON-NLS-1$
			return false;
		}
		
		// check whether the character right before the end delimiter is the
		// escape character, which would mean it's not really an end delimiter 
		// unless it's part of the start delimiter
		if( endIndex - 1 >= afterStartDelimiterIndex &&
			isEscapeCharacter( string.charAt( endIndex - 1 ) ) )
		{
			log( context + "End delimiter escaped. Exiting: " + false ); //$NON-NLS-1$
			return false;
		}
		
		// it also wouldn't be an end delimiter if the end delimiter were 
		// immediately followed by an alpha numeric character		
		boolean ret = !( endIndex + endDelimiterLength < string.length() &&
					  	 isStringMarkerCharacter( string.charAt( endIndex + endDelimiterLength ) ) );
		
		log( context + "Exiting: " + ret ); //$NON-NLS-1$
		return ret;
	}
	
	private static IllegalArgumentException newIllegalArgumentException( String msg )
	{
		IllegalArgumentException ex = 
			new IllegalArgumentException( msg );
		
		log( ex );
		return ex;
	}
	
	private static NullPointerException newNullPointerException( String msg )
	{
		NullPointerException ex = 
			new NullPointerException( msg );
		
		log( ex );
		return ex;
	}

}
