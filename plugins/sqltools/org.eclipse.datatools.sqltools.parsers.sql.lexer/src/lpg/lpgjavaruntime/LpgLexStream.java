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

import java.io.IOException;

/**
 * @author fisher
 *
 */
public abstract class LpgLexStream extends LexStream
{
    /**
     * 
     */
    public LpgLexStream() {
        super();
    }

    /**
     * @param tab
     */
    public LpgLexStream(int tab) {
        super(tab);
    }

    /**
     * @param fileName
     */
    public LpgLexStream(String fileName) throws IOException
    {
    	super(fileName);
    }

    /**
     * @param fileName
     * @param tab
     */
    public LpgLexStream(String fileName, int tab) throws IOException
    {
    	super(fileName, tab);
    }

    /**
     * @param inputChars
     * @param fileName
     */
    public LpgLexStream(char[] inputChars, String fileName) {
        super(inputChars, fileName);
    }

    /**
     * @param lineOffsets
     * @param inputChars
     * @param fileName
     */
    public LpgLexStream(IntSegmentedTuple lineOffsets, char[] inputChars, String fileName) {
        super(lineOffsets, inputChars, fileName);
    }

    /**
     * @param inputChars
     * @param fileName
     * @param tab
     */
    public LpgLexStream(char[] inputChars, String fileName, int tab) {
        super(inputChars, fileName, tab);
    }

    /**
     * @param lineOffsets
     * @param inputChars
     * @param fileName
     * @param tab
     */
    public LpgLexStream(IntSegmentedTuple lineOffsets, char[] inputChars, String fileName, int tab) {
        super(lineOffsets, inputChars, fileName, tab);
    }

    /* (non-Javadoc)
     * @see lpg.javaruntime.TokenStream#getKind(int)
     */
    public abstract int getKind(int i);

    public abstract String[] orderedExportedSymbols();
}
