/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

/**
 * Constants for introduction page
 * 
 * @author Idull
 */
public class IntroConstants
{
    public static final String EXTENSION_POINT_NAME_INTRO_PAGE                = "introductionPage";
    public static final String EXTENSION_POINT_INTRO_PAGE                     = "introPage";
    public static final String EXTENSION_POINT_INTRO_PAGE_NAME                = "Name";
    public static final String EXTENSION_POINT_INTRO_PAGE_HELPID              = "ContextHelpId";
    public static final String EXTENSION_POINT_INTRO_ELEMENT                  = "introElement";
    public static final String EXTENSION_POINT_INTRO_ELEMENT_ICON             = "icon";
    public static final String EXTENSION_POINT_INTRO_ELEMENT_HEADING          = "heading";
    public static final String EXTENSION_POINT_INTRO_ELEMENT_TEXT             = "text";
    public static final String EXTENSION_POINT_INTRO_ELEMENT_ID               = "id";
    public static final String EXTENSION_POINT_INTRO_ELEMENT_HYPERLINK        = "hyperlink";
    public static final String EXTENSION_POINT_INTRO_ELEMENT_HYPERLINK_ACTION = "hyperlinkaction";
    public static final String INTRODUCTION_PAGE_ID                           = "org.eclipse.datatools.sqltools.schemaobjecteditor.pages.introduction.page";

    // default element ids
    public static final String HELP_ELEMENT_ID                                = "help";
    public static final String CHEAT_SHEET_ELEMENT_ID                         = "cheatsheet";
    public static final String START_ELEMENT_ID                               = "start";
    public static final String DESCRIPTION_ELEMENT_ID                         = "description";
}
