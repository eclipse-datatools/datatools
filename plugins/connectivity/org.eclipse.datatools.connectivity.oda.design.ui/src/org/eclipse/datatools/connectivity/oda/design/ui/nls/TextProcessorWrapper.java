/*
 *************************************************************************
 * Copyright (c) 2007, 2010 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.ui.nls;

import java.lang.reflect.Method;

import org.eclipse.osgi.util.TextProcessor;

/**
 *  Wrapper class to provide 3.3 functionality to the 
 *  platform osgi 3.2 version of org.eclipse.osgi.util.TextProcessor, whose
 *  {@link #deprocess(String)} method was not available till 3.3.
 *  <br>Note: Since DTP no longer needs to be compatible with Platform 3.2.x, 
 *  the wrapper class now simply redirects its calls to the TextProcessor. 
 */
public class TextProcessorWrapper
{    
    private static final String DEPROCESS_METHOD_NAME = "deprocess"; //$NON-NLS-1$
    private static Method sm_deprocessMethod = null;	// lazy initialization
    private static boolean sm_hasDeprocessMethod = true;

    // following variables duplicated from the eclipse osgi 3.3 TextProcessor implementation
    // @see org.eclipse.osgi.util.TextProcessor
    
	// left to right marker
    private static final char LRM = '\u200e';

    // left to right embedding
    private static final char LRE = '\u202a';

    // pop directional format
    private static final char PDF = '\u202c';

    // whether or not the locale BiDi
    private static boolean isBidi = false;

    // whether or not the current platform supports directional characters
    private static boolean isSupportedPlatform = false;
    
    /**
     * Provides compatibility to {@link #deprocess(String)} method introduced in the eclipse osgi 3.3 TextProcessor.
     * @see org.eclipse.osgi.util.TextProcessor#deprocess(String)
     */
    public static String deprocess( String str )
    {
        /* since DTP no longer needs to be compatible with Platform 3.2.x, 
         * calls the TextProcessor method directly
         */
        return TextProcessor.deprocess( str );
    }
    
    /**
     * Use reflection API to call platform version of deprocess method, 
     * if exists.
     * @param str	a String value to deprocess
     * @return		deprocessed string value
     * @throws Exception	if the deprocess static method does not exist, or
     * 				if encountered error during its invocation
     */
    protected static String invokeDeprocess( String str ) 
    	throws Exception
    {
    	Method deprocessMethod = getDeprocessMethod();

    	// invoke static method with specified argument
    	Object returnValue = deprocessMethod.invoke( null, new Object[] { str } );
    	if( returnValue instanceof String )
    		return (String) returnValue;
    	
		sm_hasDeprocessMethod = false;
    	throw new NoSuchMethodException( DEPROCESS_METHOD_NAME );
    }
    
    /**
     * Returns the deprocess method if exists, using reflection API.
     * @return
     * @throws Exception	if method is not found
     */
    private static Method getDeprocessMethod() 
		throws Exception
    {
    	if( ! sm_hasDeprocessMethod )
        	throw new NoSuchMethodException( DEPROCESS_METHOD_NAME );
    	
    	if( sm_deprocessMethod == null )	// not yet initialized
    	{
        	Class<TextProcessor> processorClass = TextProcessor.class;
        	try 
        	{
				sm_deprocessMethod = processorClass.getMethod( DEPROCESS_METHOD_NAME, 
											new Class[] { String.class } );
			} 
        	catch ( Exception e ) 
        	{
        		sm_hasDeprocessMethod = false;
        		throw e;
			}
    	}
    	return sm_deprocessMethod;
    }
    
    /**
     * Local implementation of the eclipse osgi 3.3 TextProcessor.deprocess method.
     * @param str
     * @return
     * @see org.eclipse.osgi.util.TextProcessor#deprocess(String)
     */
    protected static String deprocessImpl( String str )
    {
        // don't do all the work if not a valid case 
        if (str == null || str.length() <= 1 || !isSupportedPlatform || !isBidi)
            return str;
        
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            switch(c){
                case LRE: continue;
                case PDF: continue;
                case LRM: continue;
                default:
                    buf.append(c);
            }
        }
        
        return buf.toString();
    }

    /*
     * @see org.eclipse.osgi.util.TextProcessor#process(String)
     */
    public static String process( String text )
    {
        return TextProcessor.process( text );
    }
    
    /*
     * @see org.eclipse.osgi.util.TextProcessor#process(String, String)
     */
    public static String process( String str, String delimiter ) 
    {
        return TextProcessor.process( str, delimiter );
    }
    
    /*
     * @see org.eclipse.osgi.util.TextProcessor#getDefaultDelimiters()
     */
    public static String getDefaultDelimiters()
    {
        return TextProcessor.getDefaultDelimiters();
    }
    
}
