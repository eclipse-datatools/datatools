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

package org.eclipse.datatools.connectivity.console.profile.nls;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.console.profile.nls.messages"; //$NON-NLS-1$

    public static String editorAppl_argKeyValue;
    public static String editorAppl_promptEditProfileName;
    public static String editorAppl_exitAppl;
    public static String editorAppl_promptInputFile;
    public static String editorAppl_invalidFileName;
    public static String editorAppl_invalidInputFile;
    public static String editorAppl_invalidOutputFile;
    public static String editorAppl_missingArgValue;
    public static String editorAppl_noProfileChanges;
    public static String editorAppl_promptOutputFile;
    public static String editorAppl_overwriteExistingFile;
    public static String editorAppl_startAppl;
    public static String editorAppl_syntaxArguments;

    public static String editorAppl_syntaxCommand;
    public static String editorAppl_syntaxHelpInstruction;
    public static String editorAppl_syntaxInArgInstruction;
    public static String editorAppl_syntaxNullArgInstruction;
    public static String editorAppl_syntaxProfileNameInstruction2;
    public static String editorAppl_syntaxOutArgInstruction;
    public static String editorAppl_syntaxProfileNameInstruction1;
    public static String editorAppl_syntaxUsage;
    public static String ioutil_askReviewStack;
    public static String ioutil_askUpdateValue;
    public static String ioutil_confirmPromptInstruction;
    public static String ioutil_errorSystemInput;
    public static String ioutil_exceptionCause;
    public static String ioutil_newValuePrompt;

    public static String ioutil_confirmUserInputChar;
    public static String profileFile_askEncryptOutput;
    public static String profileFile_askUpdateProfile;
    public static String profileFile_errorInstruction;
    public static String profileFile_errorReadingFile;
    public static String profileFile_errorSavingFile;
    public static String profileFile_inputPromptDelimiter;
    public static String profileFile_noOdaPropertyDefn;
    public static String profileFile_noProfileFound;
    public static String profileFile_propertyDriverClass;
    public static String profileFile_propertyJarList;
    public static String profileFile_propertyPassword;
    public static String profileFile_profileIdentifier;
    public static String profileFile_propertyUrl;
    public static String profileFile_propertyUser;
    public static String profileFile_specifiedProfileNotFound;
    public static String profileFile_statusDone;
    public static String profileFile_statusIteratingProfiles;
    public static String profileFile_statusReadingFile;
    public static String profileFile_statusSavingFile;

    static
    {
        // initialize resource bundle
        NLS.initializeMessages( BUNDLE_NAME, Messages.class );
    }

    private Messages()
    {
    }
}
