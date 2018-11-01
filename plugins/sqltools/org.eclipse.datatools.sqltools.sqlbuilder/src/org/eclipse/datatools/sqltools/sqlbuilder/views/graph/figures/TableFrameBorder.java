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
package org.eclipse.datatools.sqltools.sqlbuilder.views.graph.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.LabeledBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.SchemeBorder;
import org.eclipse.draw2d.SimpleLoweredBorder;
import org.eclipse.draw2d.TitleBarBorder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public class TableFrameBorder extends CompoundBorder implements LabeledBorder {

    protected TitleBarBorder titleBar;

    private static final SchemeBorder.Scheme raisedBorderScheme = new SchemeBorder.Scheme(new Color[] { ColorConstants.lightGray,
            ColorConstants.buttonLightest, ColorConstants.button }, new Color[] { ColorConstants.buttonDarkest, ColorConstants.buttonDarker,
            ColorConstants.button });

    public TableFrameBorder() {
        outer = new SchemeBorder(raisedBorderScheme);

        inner = new CompoundBorder(titleBar = new TitleBarBorder(), new SimpleLoweredBorder(2));
        titleBar.setBackgroundColor(ColorConstants.button);
        titleBar.setTextColor(ColorConstants.black);
        titleBar.setTextAlignment(PositionConstants.CENTER);
        titleBar.setPadding(2);
    }

    public String getLabel() {
        return titleBar.getLabel();
    }

    public void setFont(Font f) {
        titleBar.setFont(f);
    }

    public void setLabel(String s) {
        titleBar.setLabel(s);
    }

}
