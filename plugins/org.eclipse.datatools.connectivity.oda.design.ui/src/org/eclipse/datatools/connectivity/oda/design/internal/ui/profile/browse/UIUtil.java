/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Internal utility class.
 * @since 3.2.6 (DTP 1.9.2)
 */
public class UIUtil
{
	/**
	 * Blends c1 and c2 based in the provided ratio.
	 * @param c1   first color
	 * @param c2   second color
	 * @param ratio percentage of the first color in the blend (0-100)
	 * @return the RGB value of the blended color
	 */
	static RGB blend( RGB c1, RGB c2, int ratio )
	{
		int r = blend( c1.red, c2.red, ratio );
		int g = blend( c1.green, c2.green, ratio );
		int b = blend( c1.blue, c2.blue, ratio );
		return new RGB( r, g, b );
	}
	
	/**
	 * Blends two primary color components based on the provided ratio.
	 * @param v1   first component
	 * @param v2   second component
	 * @param ratio percentage of the first component in the blend
	 * @return
	 */
	private static int blend( int v1, int v2, int ratio )
	{
		int b = ( ratio * v1 + ( 100 - ratio ) * v2 ) / 100;
		return Math.min( 255, b );
	}
	
	static void drawArrow( GC gc, Rectangle rect, int style )
	{
		Point point = new Point( rect.x + ( rect.width / 2 ), rect.y
				+ ( rect.height / 2 ) );
		int[] points = null;
		switch ( style )
		{
			case SWT.LEFT :
				points = new int[]{
						point.x + 2,
						point.y - 4,
						point.x + 2,
						point.y + 4,
						point.x - 2,
						point.y
				};
				gc.fillPolygon( points );
				break;

			/*
			 * Low efficiency because of Win98 bug.
			 */
			case SWT.UP :
				gc.fillRectangle( new Rectangle( point.x, point.y - 1, 1, 1 ) );
				gc.fillRectangle( new Rectangle( point.x - 1, point.y, 3, 1 ) );
				gc.fillRectangle( new Rectangle( point.x - 2, point.y + 1, 5, 1 ) );
				break;

			case SWT.RIGHT :
				points = new int[]{
						point.x - 2,
						point.y - 4,
						point.x - 2,
						point.y + 4,
						point.x + 2,
						point.y
				};
				gc.fillPolygon( points );
				break;

			/*
			 * Low efficiency because of Win98 bug.
			 */
			default :
				gc.fillRectangle( new Rectangle( point.x - 2, point.y - 1, 5, 1 ) );
				gc.fillRectangle( new Rectangle( point.x - 1, point.y, 3, 1 ) );
				gc.fillRectangle( new Rectangle( point.x, point.y + 1, 1, 1 ) );
				break;
		}

	}
	
	static Shell getDefaultShell( )
	{
		Shell shell = null;
		try
		{
			shell = PlatformUI.getWorkbench( ).getDisplay( ).getActiveShell( );
			if ( shell == null )
			{
				shell = Display.getCurrent( ).getActiveShell( );
			}
			if ( shell == null )
			{
				shell = PlatformUI.getWorkbench( )
						.getActiveWorkbenchWindow( )
						.getShell( );
			}
		}
		catch ( Exception e )
		{
			//do nothing
		}
		if ( shell == null )
		{
			return new Shell( );
		}
		return shell;
	}	

}