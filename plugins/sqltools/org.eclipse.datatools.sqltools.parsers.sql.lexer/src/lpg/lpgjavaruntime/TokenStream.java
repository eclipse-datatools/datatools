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

public interface TokenStream
{
    int getToken();

    int getToken(int end_token);

    int getKind(int i);

    int getNext(int i);

    int getPrevious(int i);

    String getName(int i);

    int peek();

    void reset(int i);

    void reset();

    int badToken();

    int getLine(int i);

    int getColumn(int i);

    int getEndLine(int i);

    int getEndColumn(int i);

    boolean afterEol(int i);

    String getFileName();

    int getStreamLength();
    
    void makeToken(int startLoc, int endLoc, int kind);

    int getFirstErrorToken(int i);

    int getLastErrorToken(int i);

    int makeErrorToken(int first, int last, int error, int kind);
    
    void reportError(int i, String code);

    void reportError(int left_char, int right_char);

    void reportError(int errorCode, String locationInfo, String tokenText);

    void reportError(int errorCode, String locationInfo, int leftToken, int rightToken, String tokenText);
}
