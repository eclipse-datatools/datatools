/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * This interface will be implemented by components used in the message wizards. VEP is used to construct the UI code,
 * and due to its limitation that a SWT component can only be designed in a direct subclass of java.lang.Object, this
 * interface is used to get the visible part from the class.
 * 
 * @author Hui Cao
 */
public interface ICompositeProvider
{
    /**
     * Style constant for the basic layout (value is 0).
     */
    public static final int NONE = 0;
    public static final int GROUP = 1;
    /**
     * Style constant for bordered behavior (value is 1&lt;&lt;11).
     * <br>Note that this is a <em>HINT</em>.
     * <p><b>Used By:</b><ul>
     * <li><code>Control</code> and subclasses</li>
     * </ul></p>
     */
    public static final int BORDER = SWT.BORDER; //1 << 11;
    
    /**
     * return the visible part of this section and change the part's parent according to the parameter.
     * The layout of the parent should be GridLayout
     * 
     * @param parent the new parent of the returned object
     * @param formToolkit the form tool kit used to create widgets, could be null
     * @param style style flag to customize the composite
     * @return
     */
    public Composite getComposite(Composite parent, FormToolkit formToolkit, int style);
}