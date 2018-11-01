/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph.commands;

import org.eclipse.datatools.sqltools.sqlbuilder.views.graph.editparts.TableEditPart;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This command move the TableViewObject to the new location
 */
public class SetLocationCommand 
     extends org.eclipse.gef.commands.Command
{
  TableEditPart part;
  Rectangle newRect;

  public SetLocationCommand()
  {  
    super("set location"); //$NON-NLS-1$
  }

  public void setPart(TableEditPart part)
  {
    this.part = part;
  }

  public void setConstraint(Rectangle rect)
  {
    newRect = rect;
  }

  public void cancel()
  {
  }

  public boolean canUndo()
  {
    return false;
  }

  public boolean canExecute() 
  {
    Point topLeft = newRect.getTopLeft();
    Point bottomRight = newRect.getBottomRight();
    // Restrict the user so they can drag the left side of the table
    // and shrink it less that 20 pixels.
    if (topLeft.x > (bottomRight.x - 20) ||
        topLeft.y > (bottomRight.y - 20)) 
    {
      return false;
    }
    
    return true;
  }

  //
  // Get the figure that corresponds to this TableEditPart and sets its new bounds
  //
  public void execute() 
  {
    org.eclipse.draw2d.IFigure child = part.getFigure();
    child.getParent().setConstraint(child, newRect);
  }

  public String getDescription()
  {
    return "Set position "; //$NON-NLS-1$
  }

  public void redo() 
  {
  }

  public void undo() 
  {
  }
}
