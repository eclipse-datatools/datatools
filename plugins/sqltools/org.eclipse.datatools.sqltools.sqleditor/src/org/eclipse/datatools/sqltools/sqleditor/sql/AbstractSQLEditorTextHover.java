/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.sql;

import java.util.List;

import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLWordFinder;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommand;
import org.eclipse.ui.commands.ICommandManager;
import org.eclipse.ui.commands.IKeySequenceBinding;
import org.eclipse.ui.keys.KeySequence;

/**
 * Provides a common base class for all text hovers of SQL Editor.
 * 
 * @author Hui Cao
 *  
 */
public abstract class AbstractSQLEditorTextHover implements ITextHover, ITextHoverExtension
{

    private static ICommand _fCommand;

    static
    {
        ICommandManager commandManager = PlatformUI.getWorkbench().getCommandSupport().getCommandManager();
        _fCommand = commandManager.getCommand(ISQLEditorActionConstants.SHOW_INFORMATION_ACTION_ID);
        if (!_fCommand.isDefined())
        {
            _fCommand = null;
        }
    }

    /**
     *  
     */
    public AbstractSQLEditorTextHover()
    {
    }

    /**
     * Associates a SQL editor with this hover. Subclass can cache it for later use.
     * 
     * @param editor
     */
    public abstract void setEditor(IEditorPart editor);

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.text.ITextHoverExtension#getHoverControlCreator()
     */
    public IInformationControlCreator getHoverControlCreator()
    {
        return new IInformationControlCreator()
        {
            public IInformationControl createInformationControl(Shell parent)
            {

                int style = SWT.NONE;

                DefaultInformationControl control = new DefaultInformationControl(parent, SWT.RESIZE, style, null,
                    getTooltipAffordanceString());
                control.setSizeConstraints(60, 10);
                return control;
            }
        }
        ;
    }

    /**
     * Returns the tool tip affordance string.
     * 
     * @return the affordance string or <code>null</code> if disabled or no key binding is defined
     * @since 3.0
     */
    protected String getTooltipAffordanceString()
    {
        if (!SQLEditorPlugin.getDefault().getPreferenceStore().getBoolean(
        PreferenceConstants.EDITOR_SHOW_TEXT_HOVER_AFFORDANCE))
        {
            return null;
        }

        KeySequence[] sequences = getKeySequences();
        if (sequences == null)
        {
            return null;
        }

        String keySequence = sequences[0].format();
    return NLS.bind(SQLEditorResources.SQLErrorHover_makeStickyHint, (new String[]{keySequence})); 
    }

    /**
     * Returns the array of valid key sequence bindings for the show tool tip description command.
     * 
     * @return the array with the {@link KeySequence}s
     * 
     * @since 3.0
     */
    private KeySequence[] getKeySequences()
    {
        if (_fCommand != null)
        {
            List list = _fCommand.getKeySequenceBindings();
            if (!list.isEmpty())
            {
                KeySequence[] keySequences = new KeySequence[list.size()];
                for (int i = 0; i < keySequences.length; i++)
                {
                    keySequences[i] = ((IKeySequenceBinding) list.get(i)).getKeySequence();
                }
                return keySequences;
            }
        }
        return null;
    }

    public IRegion getHoverRegion(ITextViewer textViewer, int offset)
    {
        return SQLWordFinder.findWord(textViewer.getDocument(), offset);
    }

}
