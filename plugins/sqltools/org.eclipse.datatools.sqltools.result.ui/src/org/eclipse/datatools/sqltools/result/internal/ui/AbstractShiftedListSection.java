/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

public abstract class AbstractShiftedListSection
{
    protected List   _leftList;
    protected List   _rightList;

    protected Button _left2RightButton;
    protected Button _left2RightAllButton;
    protected Button _right2LeftButton;
    protected Button _right2LeftAllButton;
    protected Button _upMoveButton;
    protected Button _downMoveButton;

    protected String _groupTitle;
    protected String _leftListTitle;
    protected String _rightListTitle;
    protected String _left2Right;
    protected String _left2RightAll;
    protected String _right2Left;
    protected String _right2LeftAll;
    protected String _moveUp;
    protected String _moveDown;

    static final int BUTTON_WIDTH   = 80; // no longer used (see computeMaxShiftButtonsWidth)

    public AbstractShiftedListSection(String sectionTitle, String leftTitle, String rightTitle)
    {
        _groupTitle = sectionTitle;
        _leftListTitle = leftTitle;
        _rightListTitle = rightTitle;
    }

    public Control createContents(Composite parent)
    {
        Group columnsComp = new Group(parent, SWT.NONE);
        GridData gd = new GridData(GridData.FILL_BOTH);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        columnsComp.setLayout(layout);
        columnsComp.setLayoutData(gd);
        columnsComp.setText(_groupTitle);

        Composite selectedComposite = new Composite(columnsComp, SWT.NONE);
        gd = new GridData(GridData.FILL_BOTH);
        selectedComposite.setLayoutData(gd);
        layout = new GridLayout();
        selectedComposite.setLayout(layout);

        Label selectedPages = new Label(selectedComposite, SWT.NONE);
        selectedPages.setText(_leftListTitle);
        gd = new GridData();
        gd.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
        selectedPages.setLayoutData(gd);

        _leftList = new List(selectedComposite, SWT.BORDER | SWT.MULTI);
        gd = new GridData(GridData.FILL_BOTH);
        _leftList.setLayoutData(gd);

        Composite buttonsComp = new Composite(columnsComp, SWT.NONE);
        gd = new GridData(GridData.FILL_VERTICAL);
        buttonsComp.setLayoutData(gd);
        layout = new GridLayout();
        buttonsComp.setLayout(layout);

        setShiftButtonText();
        GC gc = new GC(buttonsComp);
        int maxShiftButtonsWidth = computeMaxShiftButtonsWidth(gc);
        gc.dispose();
        
        new Label(buttonsComp, SWT.NONE);
        _left2RightButton = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxShiftButtonsWidth;
        _left2RightButton.setLayoutData(gd);
        _left2RightButton.setText(_left2Right);
        _left2RightButton.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                shiftToRightAction();
            }
        });

        _left2RightAllButton = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxShiftButtonsWidth;
        _left2RightAllButton.setLayoutData(gd);
        _left2RightAllButton.setText(_left2RightAll);
        _left2RightAllButton.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                shiftAllToRightAction();

            }
        });

        _right2LeftButton = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxShiftButtonsWidth;
        _right2LeftButton.setLayoutData(gd);
        _right2LeftButton.setText(_right2Left);
        _right2LeftButton.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                shiftToLeftAction();
            }
        });

        _right2LeftAllButton = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxShiftButtonsWidth;
        _right2LeftAllButton.setLayoutData(gd);
        _right2LeftAllButton.setText(_right2LeftAll);
        _right2LeftAllButton.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                shiftAllToLeftAction();
            }
        });

        _upMoveButton = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxShiftButtonsWidth;
        _upMoveButton.setLayoutData(gd);
        _upMoveButton.setText(_moveUp);
        _upMoveButton.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                moveUpAction();
            }
        });

        _downMoveButton = new Button(buttonsComp, SWT.NONE | SWT.LEFT);
        gd = new GridData();
        gd.verticalAlignment = GridData.VERTICAL_ALIGN_CENTER;
        gd.widthHint = maxShiftButtonsWidth;
        _downMoveButton.setLayoutData(gd);
        _downMoveButton.setText(_moveDown);
        _downMoveButton.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                moveDownAction();
            }
        });

        Composite availableComp = new Composite(columnsComp, SWT.NONE);
        gd = new GridData(GridData.FILL_BOTH);
        availableComp.setLayoutData(gd);
        layout = new GridLayout();
        availableComp.setLayout(layout);

        Label availablePages = new Label(availableComp, SWT.NONE);
        availablePages.setText(_rightListTitle);
        gd = new GridData();
        gd.horizontalAlignment = GridData.HORIZONTAL_ALIGN_BEGINNING;
        availablePages.setLayoutData(gd);

        _rightList = new List(availableComp, SWT.BORDER | SWT.MULTI);
        gd = new GridData(GridData.FILL_BOTH);
        _rightList.setLayoutData(gd);

        _leftList.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                _rightList.deselectAll();
                setButtonStatus();
            }
        });
        _rightList.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                _leftList.deselectAll();
                setButtonStatus();
            }
        });
        setButtonStatus();

        return parent;
    }

    public void setInput(String[] leftItems, String[] rightItems, boolean reset)
    {
        if (reset)
        {
            _leftList.removeAll();
            _rightList.removeAll();
        }

        for (int i = 0; leftItems != null && i < leftItems.length; i++)
        {
            _leftList.add(leftItems[i]);
            _leftList.setData(leftItems[i], new Integer(i));
        }

        for (int i = 0; rightItems != null && i < rightItems.length; i++)
        {
            _rightList.add(rightItems[i]);
            _rightList.setData(rightItems[i], new Integer(-1));
        }
        setButtonStatus();
    }

    private void computeLeftData()
    {
        String[] selectedItems = _leftList.getItems();
        for (int i = 0; i < selectedItems.length; i++)
        {
            String name = selectedItems[i];
            _leftList.setData(name, new Integer(i));
        }
    }

    /**
     * Computes the max width of the shift button label text.
     * 
     * @param gc a graphics context to use to compute string widths
     * @return the width of the widest button text, in pixels
     */
    private int computeMaxShiftButtonsWidth(GC gc) 
    {
        int maxWidth = 0;
        
        maxWidth = getGreaterWidth(gc, this._left2Right, maxWidth);
        maxWidth = getGreaterWidth(gc, this._left2RightAll, maxWidth);
        maxWidth = getGreaterWidth(gc, this._right2Left, maxWidth);
        maxWidth = getGreaterWidth(gc, this._right2LeftAll, maxWidth);
        maxWidth = getGreaterWidth(gc, this._moveUp, maxWidth);
        maxWidth = getGreaterWidth(gc, this._moveDown, maxWidth);
        
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
    
    private void moveItem(List list, boolean upDirection)
    {
        if (list.getItemCount() == 0 || list.getSelectionCount() != 1 || (list.getSelectionIndex() == 0 && upDirection)
                || (list.getSelectionIndex() == list.getItemCount() - 1 && !upDirection))
        {
            return;
        }
        else
        {
            int selectionIndex = list.getSelectionIndex();
            String selectionItem = list.getItem(selectionIndex);
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

    private void setButtonStatus()
    {
        if (_leftList.getSelectionCount() > 0)
        {
            _left2RightButton.setEnabled(true);
            if (_leftList.getSelectionCount() == 1 && _leftList.getSelectionIndex() != 0)
            {
                _upMoveButton.setEnabled(true);
            }
            else
            {
                _upMoveButton.setEnabled(false);
            }

            if (_leftList.getSelectionCount() == 1 && _leftList.getSelectionIndex() != _leftList.getItemCount() - 1)
            {
                _downMoveButton.setEnabled(true);
            }
            else
            {
                _downMoveButton.setEnabled(false);
            }
        }
        else
        {
            _left2RightButton.setEnabled(false);
            _upMoveButton.setEnabled(false);
            _downMoveButton.setEnabled(false);
        }

        if (_rightList.getSelectionCount() > 0)
        {
            _right2LeftButton.setEnabled(true);
        }
        else
        {
            _right2LeftButton.setEnabled(false);
        }

        if (_leftList.getItemCount() > 0)
        {
            _left2RightAllButton.setEnabled(true);
        }
        else
        {
            _left2RightAllButton.setEnabled(false);
        }

        if (_rightList.getItemCount() > 0)
        {
            _right2LeftAllButton.setEnabled(true);
        }
        else
        {
            _right2LeftAllButton.setEnabled(false);
        }
    }

    protected void shiftToRightAction()
    {
        String[] selectedItems = _leftList.getSelection();
        int movedNum = 0;
        for (int i = 0; i < selectedItems.length; i++)
        {
            String name = selectedItems[i];
            _rightList.add(name);
            _rightList.setData(name, new Integer(-1));
            _leftList.remove(name);
            movedNum++;
        }

        computeLeftData();

        _leftList.deselectAll();
        int[] indecies = new int[movedNum];
        for (int i = 0; i < movedNum; i++)
        {
            indecies[i] = _rightList.getItemCount() - 1 - i;
        }
        _rightList.setSelection(indecies);
        setButtonStatus();
    }

    protected void shiftAllToRightAction()
    {
        String[] selectedItems = _leftList.getItems();
        for (int i = 0; i < selectedItems.length; i++)
        {
            String name = selectedItems[i];
            _rightList.add(name);
            _rightList.setData(name, new Integer(-1));
            _leftList.remove(name);
        }
        setButtonStatus();
    }

    protected void shiftToLeftAction()
    {
        String[] selectedItems = _rightList.getSelection();
        int movedNum = 0;
        for (int i = 0; i < selectedItems.length; i++)
        {
            String name = selectedItems[i];

            _leftList.add(name);
            _rightList.remove(name);
            movedNum++;
        }

        computeLeftData();

        int[] indecies = new int[movedNum];
        for (int i = 0; i < movedNum; i++)
        {
            indecies[i] = _leftList.getItemCount() - 1 - i;
        }
        _leftList.setSelection(indecies);
        setButtonStatus();
    }

    protected void shiftAllToLeftAction()
    {
        String[] selectedItems = _rightList.getItems();
        for (int i = 0; i < selectedItems.length; i++)
        {
            String name = selectedItems[i];
            _leftList.add(name);
            _rightList.remove(name);
        }

        computeLeftData();

        setButtonStatus();
    }

    protected void moveUpAction()
    {
        moveItem(_leftList, true);
        computeLeftData();
    }

    protected void moveDownAction()
    {
        moveItem(_leftList, false);
        computeLeftData();
    }
    
    protected void setShiftButtonText() {
        _left2Right    = Messages.ShiftListMoveRight;
        _left2RightAll = Messages.ShiftListMoveRightAll;
        _right2Left    = Messages.ShiftListMoveLeft;
        _right2LeftAll = Messages.ShiftListMoveLeftAll;
        _moveUp        = Messages.ShiftListMoveUp;
        _moveDown      = Messages.ShiftListMoveDown;

    }
}
