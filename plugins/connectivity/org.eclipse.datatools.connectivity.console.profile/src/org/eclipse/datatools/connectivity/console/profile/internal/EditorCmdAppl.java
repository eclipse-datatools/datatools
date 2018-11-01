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

import java.io.File;

import org.eclipse.datatools.connectivity.console.profile.nls.Messages;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 *  Command-line editor application to view and update the contents of an exported file
 *  containing DTP connection profiles.
 */
public class EditorCmdAppl implements IApplication
{
    private static final String INPUT_FILE_ARG_KEY = "-in"; //$NON-NLS-1$
    private static final String OUTPUT_FILE_ARG_KEY = "-out"; //$NON-NLS-1$
    private static final String PROFILE_NAME_ARG_KEY = "-profile"; //$NON-NLS-1$
    private static final String HELP_SYNTAX = "-?"; //$NON-NLS-1$
    
    private static final String APPLICATION_ID = "StorageFileEditor"; //$NON-NLS-1$
    private static final int NUM_PROMPT_FILE_RETRY = 3;
    
    private File m_inFile;
    private File m_outFile;
    private String m_editProfileName;
    private SystemIOUtil m_ioUtil = new SystemIOUtil();

    /* (non-Javadoc)
     * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
     */
    public Object start( IApplicationContext context ) throws Exception
    {
        m_ioUtil.printNewLine( Messages.bind( Messages.editorAppl_startAppl, APPLICATION_ID ));

        processCmdArguments( context );
        
        // no valid input or output file specified in command line arguments
        if( m_inFile == null )
        {
            m_inFile = promptForInputFile();
            if( m_inFile == null )
                return normalExit();
        }
        if( m_outFile == null )
        {
            m_outFile = promptForOutputFile();
            if( m_outFile == null )
                return normalExit();
        }

        if( m_editProfileName == null )
            m_editProfileName = promptForProfileName();
        
        // read and process the specified input file; and save in specified output file
        
        ProfileFileProcessor handler = new ProfileFileProcessor( m_ioUtil );
        int numProfiles = handler.loadProfileFile( m_inFile );
        if( numProfiles == 0 )
            return normalExit();

        boolean hasUpdates = handler.updateProfiles( m_editProfileName );
        if( hasUpdates )
            handler.saveUpdatedProfiles( m_outFile );
        else
            m_ioUtil.println( Messages.editorAppl_noProfileChanges );
        
        return normalExit();
    }

    private Object normalExit()
    {
        m_ioUtil.println( Messages.bind( Messages.editorAppl_exitAppl, APPLICATION_ID ));
        m_ioUtil.close();
        return EXIT_OK;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.equinox.app.IApplication#stop()
     */
    public void stop()
    {
        m_ioUtil.close();
    }

    private void processCmdArguments( IApplicationContext context )
    {
        String[] args = (String[]) context.getArguments().get( IApplicationContext.APPLICATION_ARGS );

        if( args.length == 0 )    // has no arguments
            return;

        for( int i=0; i < args.length; i++ )
        {
            String arg = args[i];
            
            if( arg.equalsIgnoreCase( INPUT_FILE_ARG_KEY ) )
            {
                if( (i+1) >= args.length )  // no more arguments available
                    printMissingArgumentError( INPUT_FILE_ARG_KEY );
                else    // has input argument value
                {
                    // get next argument for input file name
                    String inFileName = (String) args[++i];                    
                    printArgumentValue( INPUT_FILE_ARG_KEY, inFileName );
                    m_inFile = validateInputFile( inFileName );
                }
            }
            else if( arg.equalsIgnoreCase( OUTPUT_FILE_ARG_KEY ) )
            {
                if( (i+1) >= args.length )  // no more arguments available
                    printMissingArgumentError( OUTPUT_FILE_ARG_KEY );
                else    // has output argument value
                {
                    // get next argument for output file name
                    String outFileName = (String) args[++i];                   
                    printArgumentValue( OUTPUT_FILE_ARG_KEY, outFileName );
                    m_outFile = validateOutputFile( outFileName );
                }                   
            }
            else if( arg.equalsIgnoreCase( PROFILE_NAME_ARG_KEY ) )
            {
                if( (i+1) >= args.length )  // no more arguments available
                    printMissingArgumentError( PROFILE_NAME_ARG_KEY );
                else    // has profile name argument value
                {
                    // get next argument for argument value
                    m_editProfileName = (String) args[++i];                   
                    printArgumentValue( PROFILE_NAME_ARG_KEY, m_editProfileName );
                }                   
            }
            else if( arg.equalsIgnoreCase( HELP_SYNTAX ) )
            {
                printCommandSyntax();
            }
        }
    }

    private void printMissingArgumentError( String argKey )
    {
        m_ioUtil.println( Messages.bind( Messages.editorAppl_missingArgValue, argKey ));
    }

    private void printArgumentValue( String argKey, String argValue )
    {
        m_ioUtil.println( Messages.bind( Messages.editorAppl_argKeyValue, argKey, argValue ));
    }
    
    private File validateInputFile( String inFileName )
    {
        if( inFileName.trim().length() == 0 )
        {
            m_ioUtil.println( Messages.bind( Messages.editorAppl_invalidFileName, inFileName ) );
            return null;
        }
        
        File inFile = new File( inFileName );
        if( inFile.exists() && inFile.isFile() )
            return inFile;
        
        // invalid input file
        String errorMsg = Messages.bind( Messages.editorAppl_invalidInputFile, inFile.getAbsoluteFile() );
        m_ioUtil.println( errorMsg );
        return null;
    }
    
    private File validateOutputFile( String outFileName )
    {
        if( outFileName.trim().length() == 0 )
        {
            m_ioUtil.println( Messages.bind( Messages.editorAppl_invalidFileName, outFileName ) );
            return null;
        }
        
        File outFile = new File( outFileName );
        if( ! outFile.exists() )    // a non-existing output file
            return outFile;         // ok to use it

        // outfile already exists
        if( outFile.isFile() )
        {
            if( m_ioUtil.promptYesNoResponse( Messages.bind( Messages.editorAppl_overwriteExistingFile, 
                    outFile.getAbsoluteFile() ) ))
                return outFile;
        }
        
        // invalid output file to use
        String errorMsg = Messages.bind( Messages.editorAppl_invalidOutputFile, outFile.getAbsoluteFile() );
        m_ioUtil.println( errorMsg );
        return null;
    }
    
    private File promptForInputFile()
    {
        String inFileName;
        File inFile = null;
        m_ioUtil.printNewLine();
        for( int i=0; inFile == null && i < NUM_PROMPT_FILE_RETRY; i++ )
        {
            inFileName = m_ioUtil.promptForInput( Messages.editorAppl_promptInputFile );
            inFile = validateInputFile( inFileName );
        }
        return inFile;
    }
    
    private File promptForOutputFile()
    {
        String outFileName;
        File outFile = null;
        m_ioUtil.printNewLine();
        for( int i=0; outFile == null && i < NUM_PROMPT_FILE_RETRY; i++ )
        {
            outFileName = m_ioUtil.promptForInput( Messages.editorAppl_promptOutputFile );
            outFile = validateOutputFile( outFileName );
        }
        return outFile;
    }
    
    private String promptForProfileName()
    {
        m_ioUtil.printNewLine();
        return m_ioUtil.promptForInput( Messages.editorAppl_promptEditProfileName );
    }
    
    private void printCommandSyntax()
    {
        m_ioUtil.printNewLine( Messages.editorAppl_syntaxUsage );
        m_ioUtil.println( "    eclipse[c] -nosplash -application org.eclipse.datatools.connectivity.console.profile.StorageFileEditor" ); //$NON-NLS-1$
        //      "[ -? | -in <connectionProfileFile> | -out <saveInFile> | -profile <profileName> ]"
        m_ioUtil.println( "      [ " + HELP_SYNTAX + " |" ); //$NON-NLS-1$ //$NON-NLS-2$
        m_ioUtil.println( "        " + INPUT_FILE_ARG_KEY + " <connectionProfileFile> |" ); //$NON-NLS-1$ //$NON-NLS-2$
        m_ioUtil.println( "        " + OUTPUT_FILE_ARG_KEY +  " <saveAsFile> |" ); //$NON-NLS-1$ //$NON-NLS-2$
        m_ioUtil.println( "        " + PROFILE_NAME_ARG_KEY +  " <profileName> ]" ); //$NON-NLS-1$ //$NON-NLS-2$

        m_ioUtil.printNewLine( Messages.editorAppl_syntaxCommand );
        m_ioUtil.printNewLine( Messages.editorAppl_syntaxArguments);
        m_ioUtil.println( "    " + HELP_SYNTAX + " : " + Messages.editorAppl_syntaxHelpInstruction ); //$NON-NLS-1$ //$NON-NLS-2$
        m_ioUtil.println( "    " + INPUT_FILE_ARG_KEY + " <connectionProfileFile> : " + Messages.editorAppl_syntaxInArgInstruction ); //$NON-NLS-1$ //$NON-NLS-2$
        m_ioUtil.println( "    " + OUTPUT_FILE_ARG_KEY +  " <saveAsFile> : " + Messages.editorAppl_syntaxOutArgInstruction ); //$NON-NLS-1$ //$NON-NLS-2$
        m_ioUtil.println( "    " + PROFILE_NAME_ARG_KEY +  " <profileName> : " + Messages.editorAppl_syntaxProfileNameInstruction1 );  //$NON-NLS-1$//$NON-NLS-2$
        m_ioUtil.println( "                             " + Messages.editorAppl_syntaxProfileNameInstruction2 ); //$NON-NLS-1$
        m_ioUtil.println( "    " + Messages.editorAppl_syntaxNullArgInstruction ); //$NON-NLS-1$
        m_ioUtil.printNewLine();
    }
    
}
