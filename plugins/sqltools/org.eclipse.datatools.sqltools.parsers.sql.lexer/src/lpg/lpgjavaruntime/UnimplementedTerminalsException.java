/*******************************************************************************
 * Copyright (c) 2004, 2023 Eclipse contributors and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package lpg.lpgjavaruntime;
import java.util.ArrayList;

public class UnimplementedTerminalsException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    ArrayList symbols;

    public UnimplementedTerminalsException(ArrayList symbols)
    {
        this.symbols = symbols;
    }

    public ArrayList getSymbols() { return symbols; }
}
