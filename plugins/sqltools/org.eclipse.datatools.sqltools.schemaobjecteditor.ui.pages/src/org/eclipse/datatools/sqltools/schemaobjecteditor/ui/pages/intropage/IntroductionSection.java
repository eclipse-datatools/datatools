/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CollapseableSection;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.SOEUIPagePlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.ILogger;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.osgi.framework.Bundle;

/**
 * The introduction section
 * 
 * @author Idull
 */
public class IntroductionSection extends CollapseableSection
{
    private String                  _pageExtensionId;
    private String                  _pageName;
    private ISchemaObjectEditorPage _page;
    private ILogger                 _log = SOEUIPagePlugin.getLogger(null);
    private List                    _images;

    public IntroductionSection(String pageExtensionId, FormToolkit toolkit, String title, Display display, int estyle)
    {
        super(toolkit, title, display, estyle);
        this._pageExtensionId = pageExtensionId;
        IConfigurationElement element = getExtension();
        if (element != null)
        {
            _helpContextId = element.getAttribute(IntroConstants.EXTENSION_POINT_INTRO_PAGE_HELPID);
        }
        _images = new ArrayList();
    }

    public void createSectionContent(Composite parent)
    {
        // Set layout and layout data for section
        getSection().setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));
        getSection().setLayout(new TableWrapLayout());
        parent.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB));

        TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 2;
        layout.makeColumnsEqualWidth = false;
        layout.horizontalSpacing = 20;
        layout.verticalSpacing = 20;
        parent.setLayout(layout);

        IConfigurationElement element = getExtension();
        if (element != null)
        {
            _helpContextId = element.getAttribute(IntroConstants.EXTENSION_POINT_INTRO_PAGE_HELPID);
            _pageName = element.getAttribute(IntroConstants.EXTENSION_POINT_INTRO_PAGE_NAME);

            IConfigurationElement[] introElements = element.getChildren(IntroConstants.EXTENSION_POINT_INTRO_ELEMENT);
            for (int i = 0; i < introElements.length; i++)
            {
                String icon = introElements[i].getAttribute(IntroConstants.EXTENSION_POINT_INTRO_ELEMENT_ICON);
                String id = introElements[i].getAttribute(IntroConstants.EXTENSION_POINT_INTRO_ELEMENT_ID);
                String heading = introElements[i].getAttribute(IntroConstants.EXTENSION_POINT_INTRO_ELEMENT_HEADING);
                String text = introElements[i].getAttribute(IntroConstants.EXTENSION_POINT_INTRO_ELEMENT_TEXT);
                String hyperlink = introElements[i]
                        .getAttribute(IntroConstants.EXTENSION_POINT_INTRO_ELEMENT_HYPERLINK);
                String hyperlinkaction = introElements[i]
                        .getAttribute(IntroConstants.EXTENSION_POINT_INTRO_ELEMENT_HYPERLINK_ACTION);
                IIntroHyperAction hyperAction = null;

                String pluginNS = introElements[i].getDeclaringExtension().getNamespaceIdentifier();
                Bundle bundle = Platform.getBundle(pluginNS);
                Image image = readImage(bundle, icon);

                if (id != null)
                {
                    PredefinedIntroSection defSec = (PredefinedIntroSection) PredefinedIntroSection.SECTIONS.get(id);
                    if (defSec != null)
                    {
                        if (image == null && icon == null)
                        {
                            image = defSec.getIcon().createImage();
                        }
                        if (heading == null)
                        {
                            heading = defSec.getHeading();
                        }
                        if (text == null)
                        {
                            text = defSec.getText();
                        }
                        if (hyperlink == null)
                        {
                            hyperlink = defSec.getHyperlink();
                        }
                        if (hyperlinkaction == null)
                        {
                            hyperAction = defSec.getHyperlinkAction();
                        }
                    }
                }

                if (image != null)
                {
                    ImageContainer ic = new ImageContainer(parent);
                    ic.setImage(image);
                    TableWrapData td = new TableWrapData();
                    td.rowspan = 2;
                    ic.setLayoutData(td);
                    _images.add(image);
                }
                else
                {
                    // occupy the position
                    Composite comp = new Composite(parent, SWT.NONE);
                    TableWrapData td = new TableWrapData();
                    td.rowspan = 2;
                    comp.setLayoutData(td);
                }

                // if heading is specified, we won't display the hyperlink
                if (heading != null && heading.trim().length() != 0)
                {
                    Label head = _toolkit.createLabel(parent, heading);
                    head.setFont(JFaceResources.getHeaderFont());
                }
                else if (hyperlink != null && hyperlink.trim().length() != 0)
                {
                    Hyperlink hl = _toolkit.createHyperlink(parent, hyperlink, SWT.NONE);

                    if (hyperAction == null && hyperlinkaction != null)
                    {
                        try
                        {
                            hyperAction = (IIntroHyperAction) introElements[i]
                                    .createExecutableExtension(IntroConstants.EXTENSION_POINT_INTRO_ELEMENT_HYPERLINK_ACTION);
                        }
                        catch (Exception e)
                        {
                            _log.error("IntroductionSection_create_intro_action", e);
                        }
                    }

                    if (hyperAction != null)
                    {
                        hyperAction.setPage(_page);
                    }
                    final IIntroHyperAction hAction = hyperAction;
                    hl.addHyperlinkListener(new HyperlinkAdapter()
                    {

                        public void linkActivated(HyperlinkEvent e)
                        {
                            if (hAction != null)
                            {
                                hAction.run();
                            }
                        }
                    });

                }
                else
                {
                    // occupy the position
                    _toolkit.createLabel(parent, "");
                }

                FormText txt = _toolkit.createFormText(parent, false);
                txt.setText(text == null ? "" : text, false, false);
            }
        }
    }

    private IConfigurationElement getExtension()
    {
        if (_pageExtensionId == null || _pageExtensionId.trim().length() == 0)
        {
            return null;
        }

        // Can not fail editor due to introduction page's error
        IExtension extension = Platform.getExtensionRegistry().getExtension(SOEUIPagePlugin.PLUGIN_ID,
                IntroConstants.EXTENSION_POINT_NAME_INTRO_PAGE, _pageExtensionId);
        if (extension == null)
        {
            return null;
        }
        IConfigurationElement[] elements = extension.getConfigurationElements();

        // There should not be more than one page
        return elements[0];
    }

    private Image readImage(Bundle bundle, String iconLoc)
    {
        if (bundle == null || iconLoc == null || bundle.getEntry(iconLoc) == null)
        {
            return null;
        }
        try
        {
            ImageDescriptor desp = ImageDescriptor.createFromURL(bundle.getEntry(iconLoc));
            return desp.createImage();
        }
        catch (Exception ex)
        {
            _log.error("IntroductionSection_error_read_image", ex);
            return null;
        }
    }

    public String getContextHelpId()
    {
        return _helpContextId;
    }

    public String getPageName()
    {
        return _pageName;
    }

    public void setPage(ISchemaObjectEditorPage _page)
    {
        this._page = _page;
    }

    public void dispose()
    {
        super.dispose();
        Iterator iter = _images.iterator();
        while (iter.hasNext())
        {
            Image i = (Image) iter.next();
            i.dispose();
        }
    }
}
