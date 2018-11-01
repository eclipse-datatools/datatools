/*
 *************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.console.profile.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.datatools.connectivity.console.profile.nls.Messages;

/**
 * Internal utility class to handle user interaction using system input and output.
 */
public class SystemIOUtil
{
    private static final String USER_CONFIRM_INPUT_CHAR = Messages.ioutil_confirmUserInputChar;
    private static final String CONFIRM_PROMPT =  // ['y' to confirm]
        Messages.bind( " [''{0}'' {1}]", USER_CONFIRM_INPUT_CHAR, Messages.ioutil_confirmPromptInstruction ); //$NON-NLS-1$
    private static final String INPUT_PROMPT_DELIMITER = ": "; //$NON-NLS-1$

    static final String EMPTY_STRING = ""; //$NON-NLS-1$
    
    private BufferedReader m_stdin;
    
    SystemIOUtil() {}

    String promptForInput( String promptMsg )
    {
        printInputPrompt( promptMsg + INPUT_PROMPT_DELIMITER );
        return readLn();
    }
    
    boolean promptYesNoResponse( String promptMsg )
    {
        String response = promptForInput( promptMsg + CONFIRM_PROMPT );
        return( response != null && response.equalsIgnoreCase( USER_CONFIRM_INPUT_CHAR ) );
    }
    
    /**
     * Ask the user whether to update the existing value.  If user confirms, ask for new input value.
     * @return  the new value entered by an user; or null if user does not want to change the current value
     */
    String promptForUpdateValue()
    {
        String newValue = null;
        if( promptYesNoResponse( Messages.ioutil_askUpdateValue ) )
        {
            newValue = promptForInput( Messages.ioutil_newValuePrompt );
            if( newValue == null )
                newValue = EMPTY_STRING;
        }
        return newValue;
    }
    
    void printException( String errorMsg, Exception ex )
    {
        if( errorMsg != null )
            println( errorMsg );
        String exceptionMsg = ex.getLocalizedMessage();
        if( exceptionMsg != null )
            println( Messages.bind( Messages.ioutil_exceptionCause, exceptionMsg ));
        
        if( promptYesNoResponse( Messages.ioutil_askReviewStack ))
            ex.printStackTrace();
    }
    
    void printNewLine( String message )
    {
        printNewLine();
        println( message );
    }
    
    void printNewLine()
    {
        System.out.println();
    }
    
    void println( String message )
    {
        System.out.println( message );
    }

    void printInputPrompt( String message )
    {
        System.out.print( message );
        System.out.flush(); // empties buffer, before prompting for input text
    }
    
    String readLn()
    {
        if( m_stdin == null )
            m_stdin = new BufferedReader( new InputStreamReader( System.in ) );
        
        String message = null;
        try
        {
            message = m_stdin.readLine();
        }
        catch( IOException ex )
        {
            printException( Messages.ioutil_errorSystemInput, ex );
        }
        return message;
    }

    void close()
    {
        System.out.flush();
        
        if( m_stdin != null )
        {
            try
            {
                m_stdin.close();
                m_stdin = null;
            }
            catch( IOException ex )
            { }
        }
    }

}
