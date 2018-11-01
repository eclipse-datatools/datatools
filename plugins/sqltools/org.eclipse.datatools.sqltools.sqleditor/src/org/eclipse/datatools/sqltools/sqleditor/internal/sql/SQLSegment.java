/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import org.eclipse.jface.text.Position;

/**
 * This class defines a segment in a SQL source document. 
 */
public class SQLSegment {
    
    private int fIndex = -1;
    private String fKind = null;
    private String fMessage = null;
    private String fPartitionLabel = null;
    private String fPartitionType = null;
    private Position fPosition = null;
    private boolean fValid = false;

    /**
     * Creates an instance of this class.  This is the default constructor.
     */
    public SQLSegment() {
        super();
    }

    /**
     * Gets the index of this partition segment.
     * 
     * @return the partition segment index
     */
    public int getIndex() {
        return fIndex;
    }

    /**
     * Gets a string indicating the kind of partition segment this is.
     * 
     * @return the partition segment kind
     */
    public String getKind() {
        return fKind;
    }

    /**
     * Gets the message associated with this partition segment, or null if the
     * partition segment has no message.
     * 
     * @return the message associated with the segment
     */
    public String getMessage() {
        return fMessage;
    }

    /**
     * Gets the partition label of this partition segment.
     * 
     * @return the partition segment label
     */
    public String getPartitionLabel() {
        if (fPartitionLabel == null)
            setPartitionLabel("Not Labelled"); //$NON-NLS-1$
        return fPartitionLabel.toLowerCase();
    }

    /**
     * Gets the position of this segment.
     * 
     * @return the segments position
     */
    public Position getPosition() {
        return fPosition;
    }

    /**
     * Gets the type of this partition segment.
     * 
     * @return the partition segment's type
     */
    public String getType() {
        return fPartitionType;
    }

    /**
     * Gets whether or not the partition segment is valid.
     * 
     * @return <code>true</code> when the partition segment is valid, otherwise false
     */
    public boolean isValid() {
        return fValid;
    }

    /**
     * Sets the index of the partition segment to the given value.
     * 
     * @param index the partition segment index
     */
    public void setIndex(int index) {
        fIndex = index;
    }

    /**
     * Sets what kind of partition segment this is.
     * 
     * @param kind the partition segment kind
     */
    public void setKind( String kind ) {
        fKind = kind;
    }

    /** 
     * Sets the message associated with this partition segment to the given message.
     * 
     * @param message the message to associate with the segment
     */
    public void setMessage( String message ) {
        fMessage = message;
    }

    /**
     * Sets the partition label of this partition segment to the given label.
     * 
     * @param partitionLabel the partition label to set
     */
    public void setPartitionLabel( String partitionLabel ) {
        fPartitionLabel = partitionLabel;
    }

    /**
     * Sets the position of the partition segment to the given position.
     * 
     * @param position the position of this partition segment
     */
    public void setPosition( Position position ) {
        fPosition = position;
    }

    /**
     * Sets the type of this partition segment to the given type.
     * 
     * @param partitionType the partition segment type to set
     */
    public void setType( String partitionType ) {
        fPartitionType = partitionType;
    }

    /**
     * Sets whether or not the partition segment is valid.
     * 
     * @param valid <code>true</code> when the partition segment is valid, otherwise false
     */
    public void setValid( boolean valid ) {
        fValid = valid;
    }

    /**
     * Returns a String that represents the value of this object.
     * 
     * @return a string representation of the receiver
     */
    public String toString() {
        return getPartitionLabel();
    }

} // end class