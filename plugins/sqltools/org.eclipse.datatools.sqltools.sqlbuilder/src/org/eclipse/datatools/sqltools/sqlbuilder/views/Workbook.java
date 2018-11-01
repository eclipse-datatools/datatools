/*******************************************************************************
 * Copyright © 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import java.util.Vector;

import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.part.PageBook;


public class Workbook implements SelectionListener
{
  protected TabFolder tabFolder;
  protected PageBook pageBook;

  public Workbook(Composite parent)
  {
    this(parent, SWT.NULL);
  }

  public Workbook(Composite parent, int style)
  {
    tabFolder = new TabFolder(parent, style);
    tabFolder.setLayoutData(ViewUtility.createFill());

    pageBook = new PageBook(tabFolder, style);
    pageBook.setLayoutData(ViewUtility.createFill());
    //
    // We need this for controls to show.
    // You wouldn't think you'd have to do this.
    //
    tabFolder.addListener
      (SWT.Resize,
       new Listener()
       {
         public void handleEvent(Event e)
         {
           pageBook.setBounds(tabFolder.getClientArea());
         }
       });

  }

  public Composite getClientComposite()
  {
    return pageBook;
  }

  public TabFolder getTabFolder()
  {
    return tabFolder;
  }

  public PageBook getPageBook()
  {
    return pageBook;
  }

  protected Vector pages = new Vector();
  public void addPage(Control page, String label, Image image, String toolTipText)
  {
    tabFolder.removeSelectionListener(this);
    TabItem item = new TabItem(tabFolder, SWT.NULL);
    if (label != null)
    {
      item.setText(label);
    } // end of if ()
    if (image != null)
    {
      item.setImage(image);
    } // end of if ()
    if (toolTipText != null)
    {
      item.setToolTipText(toolTipText);
    } // end of if ()
    pages.add(page);
    if (pages.size() == 1)
    {
      // default show first page
      pageBook.showPage(page);
    } // end of if ()
    tabFolder.addSelectionListener(this);
  }

  public void widgetDefaultSelected(SelectionEvent e)
  {
  }

  public void widgetSelected(SelectionEvent e)
  {
    int i = tabFolder.getSelectionIndex();

    //System.out.println("page i = " + new Integer(i));
    pageBook.showPage((Control) pages.get(i));

  }
}// Workbook
