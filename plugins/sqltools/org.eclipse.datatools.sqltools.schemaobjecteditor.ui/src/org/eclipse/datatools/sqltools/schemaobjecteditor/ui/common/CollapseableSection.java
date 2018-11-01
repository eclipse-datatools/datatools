/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

/**
 * A common used section in multiple forms based editor. Which will set the SEPARATOR color to use to BORDER color,
 * because in Eclipse 3.1, the SEAPARTOR color looks bad. Also, the help context id can be set to this section (A small
 * help icon will be add to the section, if end-user clicks the icon, help page will be displayed).
 * 
 * @author Idull
 */
public abstract class CollapseableSection extends AbstractFormPart
{
    protected boolean     _isWhiteBackgroud;
    protected FormToolkit _toolkit;
    protected Section     _sec;
    protected String      _title;
    protected Display     _display;
    protected int         _estyle;
    protected String      _helpContextId;
    private Composite     _content;
    protected String      _pluginId;

    /**
     * Constructs a collapseable section
     * 
     * @param toolkit the form toolkit
     * @param title the title of the section
     * @param display the <code>Display</code> instance
     * @param isCollapseable whether the section is collapseable
     * @param isCollapsed the initial status
     */
    public CollapseableSection(FormToolkit toolkit, String title, Display display, boolean isCollapseable,
            boolean isCollapsed, int estyle)
    {
        this._toolkit = toolkit;
        this._title = title;
        _display = display;
        _estyle = estyle;
        // justify the SEPARTOR color for Eclipse 3.1
        _isWhiteBackgroud = toolkit.getColors().getBackground().equals(_display.getSystemColor(SWT.COLOR_WHITE));
        if (_isWhiteBackgroud)
        {
            _estyle |= Section.TITLE_BAR;
        }
        else
        {
            FormColors colors = toolkit.getColors();
            if (colors.getBorderColor() != null)
            {
                colors.createColor(FormColors.SEPARATOR, colors.getBorderColor().getRGB());
            }
        }
        if (isCollapseable)
        {
            _estyle |= Section.FOCUS_TITLE | Section.TWISTIE;
        }
        _estyle |= isCollapsed ? Section.COMPACT : Section.EXPANDED;
    }

    public CollapseableSection(FormToolkit toolkit, String title, Display display, boolean isCollapsed, int estyle)
    {
        this(toolkit, title, display, true, isCollapsed, estyle);
    }

    public CollapseableSection(FormToolkit toolkit, String title, Display display, int estyle)
    {
        this(toolkit, title, display, false, false, estyle);
    }

    /**
     * User of this class need to call this method to create this section
     * 
     * @param parent the parent in which this section is created
     * @param horizontalSpan the horizontal span
     * @param helpContextId the id of the context help
     * @return the section instance
     */
    public Control createControl(Composite parent, int horizontalSpan, String helpContextId)
    {
        _helpContextId = helpContextId;
        _sec = _toolkit.createSection(parent, _estyle);
        hookListeners();
        _sec.setText(_title);
        if (!_isWhiteBackgroud)
        {
            _toolkit.createCompositeSeparator(_sec);
        }

        _content = _toolkit.createComposite(_sec, SWT.NULL);
        _content.setBackground(parent.getBackground());

        createSectionContent(_content);

        if (_helpContextId != null)
        {
            ImageHyperlink helplink = new ImageHyperlink(_sec, SWT.NONE);
            _toolkit.adapt(helplink, false, false);
            helplink.setImage(Images.get(Images.IMG_HELP));
            helplink.setToolTipText("Help");
            helplink.addHyperlinkListener(new HyperlinkAdapter()
            {
                public void linkActivated(HyperlinkEvent e)
                {
                    displayHelp(_helpContextId);
                }
            });
            _sec.setTextClient(helplink);

        }
        _sec.setClient(_content);
        Object data = _sec.getLayoutData();
        if (data != null)
        {
            if (data instanceof GridData)
            {
                GridData gd = (GridData) data;
                gd.horizontalSpan = horizontalSpan;
                _sec.setLayoutData(gd);
            }
            else if (data instanceof TableWrapData)
            {
                TableWrapData td = (TableWrapData) data;
                td.colspan = horizontalSpan;
                _sec.setLayoutData(td);
            }
        }
        return _sec;
    }

    private void displayHelp(String contextHelpId)
    {
        String contextId = HelpUtil.getContextId(contextHelpId, _pluginId);
        if ((contextId != null) && (contextId.length() > 0))
        {
            PlatformUI.getWorkbench().getHelpSystem().displayHelp(contextId);
        }
        else
        {
            PlatformUI.getWorkbench().getHelpSystem().displayHelp();
        }
    }

    /**
     * Creates the section content, subclass should implement this method to create controls in the contect
     * 
     * @param parent the section content composite
     */
    protected abstract void createSectionContent(Composite parent);

    /**
     * Returns the section instance
     * 
     * @return the section instance
     */
    public Section getSection()
    {
        return _sec;
    }

    /**
     * Returns the section content composite
     * 
     * @return the section content composite
     */
    public Composite getSectionContent()
    {
        return _content;
    }

    /**
     * Adds listeners to the underlying widget.
     */
    protected void hookListeners()

    {
        if ((_sec.getExpansionStyle() & Section.TWISTIE) != 0 || (_sec.getExpansionStyle() & Section.TREE_NODE) != 0)
        {
            _sec.addExpansionListener(new ExpansionAdapter()
            {
                public void expansionStateChanging(ExpansionEvent e)

                {
                    CollapseableSection.this.expansionStateChanging(e.getState());
                }

                public void expansionStateChanged(ExpansionEvent e)
                {
                    CollapseableSection.this.expansionStateChanged(e.getState());
                }
            });
        }
    }

    /**
     * The section is about to expand or collapse.
     * 
     * @param expanding <code>true</code> for expansion, <code>false</code> for collapse.
     */
    protected void expansionStateChanging(boolean expanding)
    {

    }

    /**
     * The section has expanded or collapsed.
     * 
     * @param expanded <code>true</code> for expansion, <code>false</code> for collapse.
     */
    protected void expansionStateChanged(boolean expanded)
    {
        if (getManagedForm() != null)
        {
            getManagedForm().getForm().reflow(false);
        }
    }

    public void setPluginId(String id)
    {
        _pluginId = id;
    }
}
