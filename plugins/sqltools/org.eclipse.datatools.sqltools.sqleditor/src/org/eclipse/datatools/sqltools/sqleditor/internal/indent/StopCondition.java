/*
 * Created on 2005-5-27
 * 
 * Copyright Sybase, Inc. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.internal.indent;

/**
 * Specifies the stop condition, upon which the <code>scanXXX</code> methods will decide whether to keep scanning
 * or not. This interface may implemented by clients.
 * 
 * @author Li Huang
 */
public interface StopCondition
{
    /**
     * Instructs the scanner to return the current position.
     * 
     * @param ch the char at the current position
     * @param position the current position
     * @param forward the iteration direction
     * @return <code>true</code> if the stop condition is met.
     */
    boolean stop(char ch, int position, boolean forward);
}
