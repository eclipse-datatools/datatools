/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

import java.util.HashMap;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.utils.Images;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * 
 * @author Hui Cao
 * 
 */
public class PredefinedIntroSection
{
    private String            id              = null;
    private String            heading         = null;
    private String            hyperlink       = null;
    private IIntroHyperAction hyperlinkAction = null;
    private String            text            = null;
    private ImageDescriptor   icon            = null;

    public PredefinedIntroSection(String id, String heading, String hyperlink, String text, ImageDescriptor icon,
            IIntroHyperAction hyperlinkAction)
    {
        super();
        this.id = id;
        this.heading = heading;
        this.hyperlink = hyperlink;
        this.text = text;
        this.icon = icon;
        this.hyperlinkAction = hyperlinkAction;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getHeading()
    {
        return heading;
    }

    public void setHeading(String heading)
    {
        this.heading = heading;
    }

    public String getHyperlink()
    {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink)
    {
        this.hyperlink = hyperlink;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public ImageDescriptor getIcon()
    {
        return icon;
    }

    public void setIcon(ImageDescriptor icon)
    {
        this.icon = icon;
    }

    public IIntroHyperAction getHyperlinkAction()
    {
        if (id.equals(IntroConstants.START_ELEMENT_ID))
        {
            hyperlinkAction = new StartEditAction();
        }
        else if (id.equals(IntroConstants.HELP_ELEMENT_ID))
        {
            hyperlinkAction = new DisplayHelpAction();
        }
        return hyperlinkAction;
    }

    public void setHyperlinkAction(IIntroHyperAction hyperlinkAction)
    {
        this.hyperlinkAction = hyperlinkAction;
    }

    public static final PredefinedIntroSection DESCRIPTION = new PredefinedIntroSection(
                                                                   IntroConstants.DESCRIPTION_ELEMENT_ID,
                                                                   Messages.default_heading, null, null,
                                                                   Images.DESC_MAIN, null);

    public static final PredefinedIntroSection START       = new PredefinedIntroSection(
                                                                   IntroConstants.START_ELEMENT_ID, null,
                                                                   Messages.start, Messages.start_desc,
                                                                   Images.DESC_START, new StartEditAction());

    public static final PredefinedIntroSection HELP        = new PredefinedIntroSection(IntroConstants.HELP_ELEMENT_ID,
                                                                   null, Messages.help, Messages.help_desc,
                                                                   Images.DESC_HELP, new DisplayHelpAction());

    public static HashMap                      SECTIONS    = new HashMap();

    static
    {
        SECTIONS.put(IntroConstants.DESCRIPTION_ELEMENT_ID, DESCRIPTION);
        SECTIONS.put(IntroConstants.START_ELEMENT_ID, START);
        SECTIONS.put(IntroConstants.HELP_ELEMENT_ID, HELP);
    }

}
