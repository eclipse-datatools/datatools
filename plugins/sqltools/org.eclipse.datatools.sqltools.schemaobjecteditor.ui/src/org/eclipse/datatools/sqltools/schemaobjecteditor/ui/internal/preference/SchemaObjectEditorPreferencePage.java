/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.preference;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.SchemaObjectEditorUtils;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The preference page to set the visibility and order of pages for editors.
 * 
 * @author Idull
 */
public class SchemaObjectEditorPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
    IPreferenceStore _store       = SOEUIPlugin.getDefault().getPreferenceStore();
    Composite        _parent;
    Combo            _dbType;
    Combo            _editorName;
    List             _displayedPages;
    List             _hiddenPages;
    Button           _rightOne;
    Button           _rightAll;
    Button           _leftOne;
    Button           _leftAll;
    Button           _upMove;
    Button           _downMove;
    Button           _alwaysShowPreview;
    Button           _promptIfNoExactEditorFound;
    Button           _checkWhenActivated;
    Button           _openFileAfterSaveas;

    boolean          _dirty;
    static final int BUTTON_WIDTH = 120; // no longer used (see computeMaxAddRemoveButtonsWidth)

    public SchemaObjectEditorPreferencePage()
    {
    }

    public SchemaObjectEditorPreferencePage(String title)
    {
        super(title);
    }

    public SchemaObjectEditorPreferencePage(String title, ImageDescriptor image)
    {
        super(title, image);
    }

    protected Control createContents(Composite parent)
    {
        _parent = parent;
        GridLayout layout = new GridLayout();
        parent.setLayout(layout);

        Composite container = new Composite(parent, SWT.NONE);
        layout = new GridLayout();
        container.setLayout(layout);
        GridData gd = new GridData(GridData.FILL_BOTH);
        container.setLayoutData(gd);

        Group editorsComp = new Group(container, SWT.NONE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        layout = new GridLayout();
        layout.numColumns = 4;
        layout.makeColumnsEqualWidth = true;
        editorsComp.setLayout(layout);
        editorsComp.setText(Messages.SchemaObjectEditorPreferencePage_available_editors);
        editorsComp.setLayoutData(gd);

        Composite dbdefsComp = new Composite(editorsComp, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 2;
        dbdefsComp.setLayout(layout);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        dbdefsComp.setLayoutData(gd);

        Label dbTypeLabel = new Label(dbdefsComp, SWT.NONE);
        dbTypeLabel.setText(Messages.SchemaObjectEditorPreferencePage_databaseType);

        _dbType = new Combo(dbdefsComp, SWT.READ_ONLY);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.minimumWidth = 130;
        _dbType.setLayoutData(gd);

        Composite edComp = new Composite(editorsComp, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 2;
        edComp.setLayout(layout);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        edComp.setLayoutData(gd);

        Label editorTypeLabel = new Label(edComp, SWT.NONE);
        editorTypeLabel.setText(Messages.SchemaObjectEditorPreferencePage_editors);

        _editorName = new Combo(edComp, SWT.READ_ONLY);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.minimumWidth = 130;
        _editorName.setLayoutData(gd);

        Group pagesComp = new Group(container, SWT.NONE);
        gd = new GridData(GridData.FILL_BOTH);
        layout = new GridLayout();
        layout.numColumns = 3;
        pagesComp.setLayout(layout);
        pagesComp.setLayoutData(gd);
        pagesComp.setText(Messages.SchemaObjectEditorPreferencePage_pagesSetting);

        Composite selectedComposite = new Composite(pagesComp, SWT.NONE);
        gd = new GridData(GridData.FILL_BOTH);
        selectedComposite.setLayoutData(gd);
        layout = new GridLayout();
        selectedComposite.setLayout(layout);

        Label selectedPages = new Label(selectedComposite, SWT.NONE);
        selectedPages.setText(Messages.SchemaObjectEditorPreferencePage_selected_pages);
        gd = new GridData();
        gd.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
        selectedPages.setLayoutData(gd);

        _displayedPages = new List(selectedComposite, SWT.BORDER | SWT.MULTI);
        gd = new GridData(GridData.FILL_BOTH);
        _displayedPages.setLayoutData(gd);

        Composite buttonsComp = new Composite(pagesComp, SWT.NONE);
        gd = new GridData(GridData.FILL_VERTICAL);
        buttonsComp.setLayoutData(gd);
        layout = new GridLayout();
        buttonsComp.setLayout(layout);

        GC gc = new GC(buttonsComp);
        int maxAddRemoveButtonsWidth = computeMaxAddRemoveButtonsWidth(gc);
        gc.dispose();
        
        new Label(buttonsComp, SWT.NONE);
        _rightOne = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxAddRemoveButtonsWidth;
        _rightOne.setLayoutData(gd);
        _rightOne.setText(Messages.SchemaObjectEditorPreferencePage_remove);
        _rightOne.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                String[] selectedItems = _displayedPages.getSelection();
                boolean isSucceeded = false;
                int movedNum = 0;
                for (int i = 0; i < selectedItems.length; i++)
                {
                    String name = selectedItems[i];
                    Object data = _displayedPages.getData(name);
                    IEditorPageDescriptor page = (IEditorPageDescriptor) data;
                    if (page.isRequired())
                    {
                        continue;
                    }
                    _hiddenPages.add(name);
                    _hiddenPages.setData(name, data);
                    _displayedPages.remove(name);
                    isSucceeded = true;
                    movedNum++;
                }
                setOrClearErrorMsg();

                if (!isSucceeded)
                {
                    String[] buttons = new String[]
                    {
                        IDialogConstants.OK_LABEL
                    };
                    MessageDialog d = new MessageDialog(_parent.getShell(),
                            Messages.SchemaObjectEditorPreferencePage_error, null,
                            Messages.SchemaObjectEditorPreferencePage_can_not_remove, MessageDialog.ERROR, buttons, 0);
                    d.open();
                }
                else
                {
                    _dirty = true;
                    int[] indecies = new int[movedNum];
                    for (int i = 0; i < movedNum; i++)
                    {
                        indecies[i] = _hiddenPages.getItemCount() - 1 - i;
                    }
                    _hiddenPages.setSelection(indecies);
                }
                setButtonStatus();
            }
        });

        _rightAll = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxAddRemoveButtonsWidth;
        _rightAll.setLayoutData(gd);
        _rightAll.setText(Messages.SchemaObjectEditorPreferencePage_remove_all);
        _rightAll.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                String[] selectedItems = _displayedPages.getItems();
                boolean isSucceeded = false;
                for (int i = 0; i < selectedItems.length; i++)
                {
                    String name = selectedItems[i];
                    Object data = _displayedPages.getData(name);
                    IEditorPageDescriptor page = (IEditorPageDescriptor) data;
                    if (page.isRequired())
                    {
                        continue;
                    }
                    _hiddenPages.add(name);
                    _hiddenPages.setData(name, data);
                    _displayedPages.remove(name);
                    isSucceeded = true;
                }
                setOrClearErrorMsg();
                setButtonStatus();
                if (!isSucceeded)
                {
                    String[] buttons = new String[]
                    {
                        IDialogConstants.OK_LABEL
                    };
                    MessageDialog d = new MessageDialog(_parent.getShell(),
                            Messages.SchemaObjectEditorPreferencePage_erroe, null,
                            Messages.SchemaObjectEditorPreferencePage_can_not_remove, MessageDialog.ERROR, buttons, 0);
                    d.open();
                }
                else
                {
                    _dirty = true;
                }
            }
        });

        _leftOne = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxAddRemoveButtonsWidth;
        _leftOne.setLayoutData(gd);
        _leftOne.setText(Messages.SchemaObjectEditorPreferencePage_add);
        _leftOne.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                String[] selectedItems = _hiddenPages.getSelection();
                int movedNum = 0;
                for (int i = 0; i < selectedItems.length; i++)
                {
                    String name = selectedItems[i];
                    Object data = _hiddenPages.getData(name);
                    IEditorDescriptor editor = (IEditorDescriptor) _editorName.getData(_editorName.getText());
                    IEditorPageDescriptor page = (IEditorPageDescriptor) data;
                    boolean isPageMandatoryFirstOne = editor.getMandatoryFirstPage() != null
                            && editor.getMandatoryFirstPage().getPageId().equals(page.getPageId());
                    boolean isPageMandatoryLastOne = editor.getMandatoryLastPage() != null
                            && editor.getMandatoryLastPage().getPageId().equals(page.getPageId());

                    if (isPageMandatoryFirstOne)
                    {
                        _displayedPages.add(name, 0);
                        _displayedPages.setData(name, data);
                    }
                    else if (isPageMandatoryLastOne)
                    {
                        _displayedPages.add(name, _displayedPages.getItemCount());
                        _displayedPages.setData(name, data);
                    }
                    else
                    {
                        // Check if the last visible page is a mandatory page
                        if (_displayedPages.getItemCount() > 0)
                        {
                            String displayedLastOne = _displayedPages.getItem(_displayedPages.getItemCount() - 1);
                            IEditorPageDescriptor displayedLast = (IEditorPageDescriptor) _displayedPages
                                    .getData(displayedLastOne);
                            boolean isLastPageMandatory = editor.getMandatoryLastPage() != null
                                    && editor.getMandatoryLastPage().getPageId().equals(displayedLast.getPageId());
                            if (isLastPageMandatory)
                            {
                                _displayedPages.add(name, _displayedPages.getItemCount() - 1);
                                _displayedPages.setData(name, data);
                            }
                            else
                            {
                                _displayedPages.add(name);
                                _displayedPages.setData(name, data);
                            }
                        }
                        else
                        {
                            _displayedPages.add(name);
                            _displayedPages.setData(name, data);
                        }
                    }
                    _hiddenPages.remove(name);
                    movedNum++;
                }
                setOrClearErrorMsg();
                if (movedNum > 0)
                {
                    _dirty = true;
                }
                int[] indecies = new int[movedNum];
                for (int i = 0; i < movedNum; i++)
                {
                    indecies[i] = _displayedPages.getItemCount() - 1 - i;
                }
                _displayedPages.setSelection(indecies);
                setButtonStatus();
            }
        });
        _leftAll = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxAddRemoveButtonsWidth;
        _leftAll.setLayoutData(gd);
        _leftAll.setText(Messages.SchemaObjectEditorPreferencePage_add_all);
        _leftAll.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                String[] selectedItems = _hiddenPages.getItems();
                for (int i = 0; i < selectedItems.length; i++)
                {
                    _dirty = true;
                    String name = selectedItems[i];
                    Object data = _hiddenPages.getData(name);

                    IEditorDescriptor editor = (IEditorDescriptor) _editorName.getData(_editorName.getText());
                    IEditorPageDescriptor page = (IEditorPageDescriptor) data;
                    boolean isPageMandatoryFirstOne = editor.getMandatoryFirstPage() != null
                            && editor.getMandatoryFirstPage().getPageId().equals(page.getPageId());
                    boolean isPageMandatoryLastOne = editor.getMandatoryLastPage() != null
                            && editor.getMandatoryLastPage().getPageId().equals(page.getPageId());

                    if (isPageMandatoryFirstOne)
                    {
                        _displayedPages.add(name, 0);
                        _displayedPages.setData(name, data);
                    }
                    else if (isPageMandatoryLastOne)
                    {
                        _displayedPages.add(name, _displayedPages.getItemCount());
                        _displayedPages.setData(name, data);
                    }
                    else
                    {
                        // Check if the last visible page is a mandatory page
                        if (_displayedPages.getItemCount() > 0)
                        {
                            String displayedLastOne = _displayedPages.getItem(_displayedPages.getItemCount() - 1);
                            IEditorPageDescriptor displayedLast = (IEditorPageDescriptor) _displayedPages
                                    .getData(displayedLastOne);
                            boolean isLastPageMandatory = editor.getMandatoryLastPage() != null
                                    && editor.getMandatoryLastPage().getPageId().equals(displayedLast.getPageId());
                            if (isLastPageMandatory)
                            {
                                _displayedPages.add(name, _displayedPages.getItemCount() - 1);
                                _displayedPages.setData(name, data);
                            }
                            else
                            {
                                _displayedPages.add(name);
                                _displayedPages.setData(name, data);
                            }
                        }
                        else
                        {
                            _displayedPages.add(name);
                            _displayedPages.setData(name, data);
                        }
                    }
                    _hiddenPages.remove(name);
                }
                setOrClearErrorMsg();
                setButtonStatus();
            }
        });

        _upMove = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxAddRemoveButtonsWidth;
        _upMove.setLayoutData(gd);
        _upMove.setText(Messages.SchemaObjectEditorPreferencePage_move_up);
        _upMove.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                moveItem(_displayedPages, true);
            }
        });

        _downMove = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxAddRemoveButtonsWidth;
        _downMove.setLayoutData(gd);
        _downMove.setText(Messages.SchemaObjectEditorPreferencePage_move_down);
        _downMove.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                moveItem(_displayedPages, false);
            }
        });

        Composite availableComp = new Composite(pagesComp, SWT.NONE);
        gd = new GridData(GridData.FILL_BOTH);
        availableComp.setLayoutData(gd);
        layout = new GridLayout();
        availableComp.setLayout(layout);

        Label availablePages = new Label(availableComp, SWT.NONE);
        availablePages.setText(Messages.SchemaObjectEditorPreferencePage_available_pages);
        gd = new GridData();
        gd.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
        availablePages.setLayoutData(gd);

        _hiddenPages = new List(availableComp, SWT.BORDER | SWT.MULTI);
        gd = new GridData(GridData.FILL_BOTH);
        _hiddenPages.setLayoutData(gd);

        final Map editorsMap = SchemaObjectEditorUtils.getEditorsCatalogedByDBDefinition();

        // Sort the dbdefinitions
        java.util.List keys = new ArrayList();
        Iterator iter = editorsMap.keySet().iterator();
        while (iter.hasNext())
        {
            String dbdefi = (String) iter.next();
            keys.add(dbdefi);
        }
        Collections.sort(keys);
        iter = keys.iterator();
        while (iter.hasNext())
        {
            String dbdefi = (String) iter.next();
            _dbType.add(dbdefi);
        }
        _dbType.setFocus();

        _dbType.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {
                // do nothing

            }

            public void widgetSelected(SelectionEvent e)
            {
                String dbdefi = _dbType.getText();
                if (dbdefi != null && dbdefi.trim().length() != 0)
                {
                    _editorName.removeAll();
                    java.util.List editors = (java.util.List) editorsMap.get(dbdefi);
                    if (editors != null)
                    {
                        Collections.sort(editors, new EditorComparator());
                        Iterator iter = editors.iterator();
                        while (iter.hasNext())
                        {
                            IEditorDescriptor editor = (IEditorDescriptor) iter.next();
                            _editorName.add(editor.getEditorName());
                            _editorName.setData(editor.getEditorName(), editor);
                        }
                    }
                    if (_editorName.getItemCount() > 0)
                    {
                        _editorName.select(0);
                        _editorName.notifyListeners(SWT.Selection, new Event());
                    }
                }
            }
        });

        _editorName.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                String name = _editorName.getText();
                if (_dirty && isValid())
                {
                    String[] buttons = new String[]
                    {
                        IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL
                    };
                    MessageDialog d = new MessageDialog(_parent.getShell(),
                            Messages.SchemaObjectEditorPreferencePage_question, null,
                            Messages.SchemaObjectEditorPreferencePage_save_modification, MessageDialog.QUESTION,
                            buttons, 0);
                    int result = d.open();
                    switch (result)
                    {
                        case 0:
                            savePreferences();
                            break;
                        case 1:
                            break;
                        default:
                            break;
                    }
                }
                _dirty = false;

                if (name != null && name.trim().length() != 0)
                {
                    _displayedPages.removeAll();
                    _hiddenPages.removeAll();
                    IEditorDescriptor editor = (IEditorDescriptor) _editorName.getData(name);
                    if (editor != null)
                    {
                        IEditorPageDescriptor[] pages = editor.getPageDescriptors();
                        for (int i = 0; i < pages.length; i++)
                        {
                            if (!_store.getBoolean(Constants.EDITOR_PAGE_VISIABILITY + pages[i].getEditorId()
                                    + pages[i].getPageId()))
                            {
                                _hiddenPages.add(pages[i].getPageName());
                                _hiddenPages.setData(pages[i].getPageName(), pages[i]);
                            }
                        }

                        IEditorPageDescriptor[] sortedPages = editor.getSortedPages();
                        for (int j = 0; j < sortedPages.length; j++)
                        {
                            if (sortedPages[j].isSelectedToShow())
                            {
                                _displayedPages.add(sortedPages[j].getPageName());
                                _displayedPages.setData(sortedPages[j].getPageName(), sortedPages[j]);
                            }
                        }
                    }
                    setOrClearErrorMsg();
                    setButtonStatus();
                }
            }
        });

        String preDBdef = _store.getString(Constants.PREFERENCE_PREVIOUS_DB_DEFINITION);
        String preEditor = _store.getString(Constants.PREFERENCE_PREVIOUS_EDIOR_NAME);
        if (preDBdef != null)
        {
            int index = _dbType.indexOf(preDBdef);
            if (index != -1)
            {
                _dbType.select(index);
                _dbType.notifyListeners(SWT.Selection, new Event());
            }
        }
        if (preEditor != null)
        {
            int index = _editorName.indexOf(preEditor);
            if (index != -1)
            {
                _editorName.select(index);
                _editorName.notifyListeners(SWT.Selection, new Event());
            }
        }

        _displayedPages.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                setButtonStatus();
            }
        });
        _hiddenPages.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                setButtonStatus();
            }
        });
        setButtonStatus();
        _alwaysShowPreview = new Button(container, SWT.CHECK);
        _alwaysShowPreview.setText(Messages.SchemaObjectEditorPreferencePage_always_show_preview);
        _alwaysShowPreview.setSelection(_store.getBoolean(Constants.PREFERENCE_ALWAYS_SHOW_PREVIEW));
        _promptIfNoExactEditorFound = new Button(container, SWT.CHECK);
        _promptIfNoExactEditorFound.setText(Messages.SchemaObjectEditorPreferencePage_use_latest_version_to_open);
        _promptIfNoExactEditorFound
                .setToolTipText(Messages.SchemaObjectEditorPreferencePage_latest_version_open_tooltip);
        _promptIfNoExactEditorFound.setSelection(_store.getBoolean(Constants.PREFERENCE_USE_LATEST_VERSION));

        // add by sul - CR470356-2
        _checkWhenActivated = new Button(container, SWT.CHECK);
        _checkWhenActivated.setText(Messages.SchemaObjectEditorPreferencePage_check_existence_when_activated);
        _checkWhenActivated.setSelection(_store.getBoolean(Constants.PREFERENCE_CHECK_EXISTENCE));
        _checkWhenActivated
                .setToolTipText(Messages.SchemaObjectEditorPreferencePage_check_existence_when_activated_tooltip);
        // add end

        // add by sul - CR497357-1
        _openFileAfterSaveas = new Button(container, SWT.CHECK);
        _openFileAfterSaveas.setText(Messages.SchemaObjectEditorPreferencePage_open_file_after_saveas);
        _openFileAfterSaveas.setSelection(_store.getBoolean(Constants.PREFERENCE_OPEN_FILE_AFTER_SAVEAS));
        _openFileAfterSaveas.setToolTipText(Messages.SchemaObjectEditorPreferencePage_open_file_after_saveas_tooltip);
        // add end

        return container;
    }

    private void setButtonStatus()
    {
        if (_displayedPages.getSelectionCount() > 0)
        {
            _rightOne.setEnabled(true);
            if (_displayedPages.getSelectionCount() == 1 && _displayedPages.getSelectionIndex() != 0)
            {
                _upMove.setEnabled(true);
            }
            else
            {
                _upMove.setEnabled(false);
            }

            if (_displayedPages.getSelectionCount() == 1
                    && _displayedPages.getSelectionIndex() != _displayedPages.getItemCount() - 1)
            {
                _downMove.setEnabled(true);
            }
            else
            {
                _downMove.setEnabled(false);
            }
        }
        else
        {
            _rightOne.setEnabled(false);
            _upMove.setEnabled(false);
            _downMove.setEnabled(false);
        }

        if (_displayedPages.getItemCount() > 0)
        {
            _rightAll.setEnabled(true);
        }
        else
        {
            _rightAll.setEnabled(false);
        }

        if (_hiddenPages.getSelectionCount() > 0)
        {
            _leftOne.setEnabled(true);
        }
        else
        {
            _leftOne.setEnabled(false);
        }

        if (_hiddenPages.getItemCount() > 0)
        {
            _leftAll.setEnabled(true);
        }
        else
        {
            _leftAll.setEnabled(false);
        }

    }

    public void init(IWorkbench workbench)
    {

    }

    private void setOrClearErrorMsg()
    {
        if (_displayedPages.getItemCount() == 0)
        {
            setMessage(Messages.SchemaObjectEditorPreferencePage_no_page, DialogPage.ERROR);
            setValid(false);
            updateApplyButton();
        }
        else
        {
            setMessage(null);
            setValid(true);
            updateApplyButton();
        }
    }

    protected void performApply()
    {
        /*
         * boolean alwaysShowPreview = _alwaysShowPreview.getSelection(); boolean promptIfNoExactEditorFound =
         * _promptIfNoExactEditorFound.getSelection(); boolean checkWhenActivated = _checkWhenActivated.getSelection();
         * _store.setValue(Constants.PREFERENCE_ALWAYS_SHOW_PREVIEW, alwaysShowPreview);
         * _store.setValue(Constants.PREFERENCE_USE_LATEST_VERSION, promptIfNoExactEditorFound);
         * _store.setValue(Constants.PREFERENCE_CHECK_EXISTENCE, checkWhenActivated);
         */
        savePreferences();
        super.performApply();
    }

    protected void performDefaults()
    {
        boolean alwaysShowPreview = _store.getDefaultBoolean(Constants.PREFERENCE_ALWAYS_SHOW_PREVIEW);
        _alwaysShowPreview.setSelection(alwaysShowPreview);

        boolean promptIfNoExactEditorFound = _store.getDefaultBoolean(Constants.PREFERENCE_USE_LATEST_VERSION);
        _promptIfNoExactEditorFound.setSelection(promptIfNoExactEditorFound);

        boolean checkWhenActivated = _store.getDefaultBoolean(Constants.PREFERENCE_CHECK_EXISTENCE);
        _checkWhenActivated.setSelection(checkWhenActivated);

        boolean openFileAfterSaveas = _store.getDefaultBoolean(Constants.PREFERENCE_OPEN_FILE_AFTER_SAVEAS);
        _openFileAfterSaveas.setSelection(openFileAfterSaveas);

        loadDefault();
        super.performDefaults();
    }

    public boolean performOk()
    {
        savePreferences();
        savePreConfiguredItem();
        return super.performOk();
    }

    private void loadDefault()
    {
        String dbdef = _dbType.getText();
        if (dbdef != null && dbdef.trim().length() != 0)
        {
            String editorName = _editorName.getText();
            if (editorName != null && editorName.trim().length() != 0)
            {
                Object obj = _editorName.getData(editorName);
                if (obj != null)
                {
                    IEditorDescriptor editor = (IEditorDescriptor) obj;
                    _displayedPages.removeAll();
                    _hiddenPages.removeAll();

                    IEditorPageDescriptor[] pages = editor.getDefaultSortedPages();
                    for (int i = 0; i < pages.length; i++)
                    {
                        if (pages[i].isRequired() || pages[i].isVisibleByDefault())
                        {
                            _displayedPages.add(pages[i].getPageName());
                            _displayedPages.setData(pages[i].getPageName(), pages[i]);
                        }
                        else
                        {
                            _hiddenPages.add(pages[i].getPageName());
                            _hiddenPages.setData(pages[i].getPageName(), pages[i]);
                        }
                    }
                }
            }
        }
        setOrClearErrorMsg();
        setButtonStatus();
        _dirty = false;
    }

    private void savePreferences()
    {
        boolean alwaysShowPreview = _alwaysShowPreview.getSelection();
        boolean promptIfNoExactEditorFound = _promptIfNoExactEditorFound.getSelection();
        boolean checkWhenActivated = _checkWhenActivated.getSelection();
        boolean openFileAfterSaveas = _openFileAfterSaveas.getSelection();
        _store.setValue(Constants.PREFERENCE_ALWAYS_SHOW_PREVIEW, alwaysShowPreview);
        _store.setValue(Constants.PREFERENCE_USE_LATEST_VERSION, promptIfNoExactEditorFound);
        _store.setValue(Constants.PREFERENCE_CHECK_EXISTENCE, checkWhenActivated);
        _store.setValue(Constants.PREFERENCE_OPEN_FILE_AFTER_SAVEAS, openFileAfterSaveas);

        String[] displayItems = _displayedPages.getItems();
        int num = 1;
        for (int i = 0; i < displayItems.length; i++)
        {
            String item = displayItems[i];
            IEditorPageDescriptor page = (IEditorPageDescriptor) _displayedPages.getData(item);
            if (page != null)
            {
                _store.setValue(Constants.EDITOR_PAGE_VISIABILITY + page.getEditorId() + page.getPageId(), true);
                _store.setValue(Constants.EDITOR_PAGE_ORDER + page.getEditorId() + page.getPageId(), num++);
            }
        }

        String[] hiddenItems = _hiddenPages.getItems();
        for (int i = 0; i < hiddenItems.length; i++)
        {
            String item = hiddenItems[i];
            IEditorPageDescriptor page = (IEditorPageDescriptor) _hiddenPages.getData(item);
            if (page != null)
            {
                _store.setValue(Constants.EDITOR_PAGE_VISIABILITY + page.getEditorId() + page.getPageId(), false);
            }
        }
        _dirty = false;
    }

    private void moveItem(List list, boolean upDirection)
    {
        if (list.getItemCount() == 0)
        {
            // no item
        }
        else if (list.getSelectionCount() == 0)
        {
            // no selection
        }
        else if (list.getSelectionCount() > 1)
        {
            // multiple selections
        }
        else if (list.getSelectionIndex() == 0 && upDirection)
        {
            // can not move the top one up
        }
        else if (list.getSelectionIndex() == list.getItemCount() - 1 && !upDirection)
        {
            // can not move the lowest one down
        }
        else
        {
            int selectionIndex = list.getSelectionIndex();
            String selectionItem = list.getItem(selectionIndex);
            IEditorDescriptor editor = (IEditorDescriptor) _editorName.getData(_editorName.getText());

            String firstItem = list.getItem(0);
            String lastItem = list.getItem(list.getItemCount() - 1);

            IEditorPageDescriptor firstPage = (IEditorPageDescriptor) _displayedPages.getData(firstItem);
            IEditorPageDescriptor lastPage = (IEditorPageDescriptor) _displayedPages.getData(lastItem);

            boolean isFirstPageMandatory = editor.getMandatoryFirstPage() != null
                    && editor.getMandatoryFirstPage().getPageId().equals(firstPage.getPageId());
            boolean isLastPageMandatory = editor.getMandatoryLastPage() != null
                    && editor.getMandatoryLastPage().getPageId().equals(lastPage.getPageId());

            // can not move the mandatory first page
            if (!upDirection && selectionIndex == 0 && isFirstPageMandatory)
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.OK_LABEL
                };
                MessageDialog d = new MessageDialog(_parent.getShell(), "Error", null, //$NON-NLS-1$
                        Messages.SchemaObjectEditorPreferencePage_must_be_first, MessageDialog.ERROR, buttons, 0);
                d.open();
                return;
            }

            // can not move the mandatory last page
            if (upDirection && selectionIndex == list.getItemCount() - 1 && isLastPageMandatory)
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.OK_LABEL
                };
                MessageDialog d = new MessageDialog(_parent.getShell(), "Error", null, //$NON-NLS-1$
                        Messages.SchemaObjectEditorPreferencePage_must_be_last, MessageDialog.ERROR, buttons, 0);
                d.open();
                return;
            }

            // move the second page to the first one
            if (upDirection && selectionIndex == 1 && isFirstPageMandatory)
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.OK_LABEL
                };
                MessageDialog d = new MessageDialog(_parent.getShell(), "Error", null, //$NON-NLS-1$
                        Messages.SchemaObjectEditorPreferencePage_can_not_up, MessageDialog.ERROR, buttons, 0);
                d.open();
                return;
            }

            // move the last but one page to the last one
            if (!upDirection && selectionIndex == list.getItemCount() - 2 && isLastPageMandatory)
            {
                String[] buttons = new String[]
                {
                    IDialogConstants.OK_LABEL
                };
                MessageDialog d = new MessageDialog(_parent.getShell(),
                        "Error", null, Messages.SchemaObjectEditorPreferencePage_can_not_down, //$NON-NLS-1$
                        MessageDialog.ERROR, buttons, 0);
                d.open();
                return;
            }

            _dirty = true;
            if (upDirection)
            {
                list.add(selectionItem, selectionIndex - 1);
                list.remove(selectionIndex + 1);
                list.select(selectionIndex - 1);
            }
            else
            {
                list.add(selectionItem, selectionIndex + 2);
                list.remove(selectionIndex);
                list.select(selectionIndex + 1);
            }
            setButtonStatus();
        }
    }

    public boolean performCancel()
    {
        savePreConfiguredItem();
        return super.performCancel();
    }

    private void savePreConfiguredItem()
    {
        String dbdef = _dbType.getText();
        String editor = _editorName.getText();
        if (dbdef != null && dbdef.trim().length() != 0)
        {
            _store.setValue(Constants.PREFERENCE_PREVIOUS_DB_DEFINITION, dbdef);
        }
        if (editor != null && editor.trim().length() != 0)
        {
            _store.setValue(Constants.PREFERENCE_PREVIOUS_EDIOR_NAME, editor);
        }
    }
    
    /**
     * Computes the max width of the add and remove button label text.
     * 
     * @param gc a graphics context to use to compute string widths
     * @return the width of the widest button text, in pixels
     */
    private int computeMaxAddRemoveButtonsWidth(GC gc) 
    {
        int maxWidth = 0;
        
        maxWidth = getGreaterWidth(gc, Messages.SchemaObjectEditorPreferencePage_add, maxWidth);
        maxWidth = getGreaterWidth(gc, Messages.SchemaObjectEditorPreferencePage_add_all, maxWidth);
        maxWidth = getGreaterWidth(gc, Messages.SchemaObjectEditorPreferencePage_remove, maxWidth);
        maxWidth = getGreaterWidth(gc, Messages.SchemaObjectEditorPreferencePage_remove_all, maxWidth);
        maxWidth = getGreaterWidth(gc, Messages.SchemaObjectEditorPreferencePage_move_up, maxWidth);
        maxWidth = getGreaterWidth(gc, Messages.SchemaObjectEditorPreferencePage_move_down, maxWidth);
        
        return maxWidth;
    }
    
    /**
     * Gets the greater of either the width of the given string or the given comparison value.
     * 
     * @param gc a graphics context to use to compute the string width
     * @param str the string to check
     * @param compareWidth a width to compare against the string width
     * @return the greater of the two values
     */
    private int getGreaterWidth(GC gc, String str, int compareWidth)
    {
        int greaterWidth = compareWidth;
        
        Point strExtentPoint = gc.stringExtent(str);
        int strWidth = strExtentPoint.x;
        if (strWidth > compareWidth)
        {
            greaterWidth = strWidth;
        }
        
        return greaterWidth;
    }
}

class EditorComparator implements Comparator
{
    public int compare(Object arg0, Object arg1)
    {
        Locale locale = Locale.getDefault();
        if (!(arg0 instanceof IEditorDescriptor))
        {
            return -1;
        }
        if (!(arg1 instanceof IEditorDescriptor))
        {
            return 1;
        }
        IEditorDescriptor editor1 = (IEditorDescriptor) arg0;
        IEditorDescriptor editor2 = (IEditorDescriptor) arg1;
        Collator col = Collator.getInstance(locale);
        try
        {
            return col.compare(editor1.getEditorName(), editor2.getEditorName());
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
