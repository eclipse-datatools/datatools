/*
* Copyright (c) 2005. IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     IBM Corporation - initial API and implementation
*/

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorResources;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * Content assist proposal that represents either a schema, table, or column.
 * 
 * @author Hetty Dougherty
 *  
 */
public class SQLDBProposal {

    public static final int SCHEMA_OBJTYPE = 1; //proposal is a schema
    public static final int TABLE_OBJTYPE = 2; // proposal is a table
    public static final int TABLECOLUMN_OBJTYPE = 3; //proposal is table column
    public static final int UNKNOWN_OBJTYPE = -1;

    private EObject fDBObject; // database model object
    private Image fImage = null;
    private String fName = null;
    private String fParentName = null;
    private EObject fParentObject; //parent of the database model object
    private int fType = UNKNOWN_OBJTYPE;

    /**
     * Constructs an instance of this object to represent the given database 
     * model object for purpose of a content assist proposal.
     * The database object can be one of <code>Schema</code>, <code>Table</code>,
     * <code>Column</code>.
     * 
     * @param dbObject
     *            the database model object
     * @see org.eclipse.datatools.modelbase.sql.schema.Schema
     * @see org.eclipse.datatools.modelbase.sql.schema.Table
     * @see org.eclipse.datatools.modelbase.sql.schema.Column
     */
    public SQLDBProposal( EObject dbObject ) {
        this.fDBObject = dbObject;
        if (dbObject instanceof Schema) {
            fType = SCHEMA_OBJTYPE;
            fName = ((Schema) dbObject).getName();
            fParentName = ((Schema) dbObject).getDatabase().getName();
            fParentObject = ((Schema) dbObject).getDatabase();
            setImage( SQLEditorResources.getImage( "schema" )); //$NON-NLS-1$
        }
        else if (dbObject instanceof Table) {
            fType = TABLE_OBJTYPE;
            fName = ((Table) dbObject).getName();
            fParentName = ((Table) dbObject).getSchema().getName();
            fParentObject = ((Table) dbObject).getSchema();
            setImage( SQLEditorResources.getImage( "table" )); //$NON-NLS-1$
        }
        else if (dbObject instanceof Column) {
            fType = TABLECOLUMN_OBJTYPE;
            fName = ((Column) dbObject).getName();
            fParentName = ((Column) dbObject).getTable().getSchema().getName()
                    + "." + ((Column) dbObject).getTable().getName();

            fParentObject = ((Column) dbObject).getTable();
            setImage( SQLEditorResources.getImage( "column" )); //$NON-NLS-1$
        }
    }

    /**
     * Gets the database model object that this proposal represents.
     * 
     * @return the database model object
     */
    public EObject getDBObject() {
        return fDBObject;
    }

    /**
     * Gets the image to be used for this content assist proposal.
     * 
     * @return the image for this model object type
     */
    public Image getImage() {
        return fImage;
    }

    /**
     * Gets the name of the database object.
     * 
     * @return the name of the database object
     */
    public String getName() {
        return fName;
    }

    /**
     * Gets the name of the parent of the database model object associated 
     * with this proposal.
     * 
     * @return the parent name
     */
    public String getParentName() {
        return fParentName;
    }

    /**
     * Gets the parent of the database object.
     * 
     * @return the parent of the database object
     */
    public EObject getParentObject() {
        return fParentObject;
    }

    /**
     * Gets the type of the proposal.
     * 
     * @return type of proposal, which is one of
     *         <ol>
     *         <li>SCHEMA_OBJTYPE
     *         <li>TABLE_OBJTYPE
     *         <li>TABLECOLUMN_OBJTYPE <eol>
     */
    public int getType() {
        return fType;
    }

    /**
     * Sets the image to be used for this content assist proposal.
     * 
     * @param image the <code>Image</code> to use for this proposal
     */
    public void setImage( Image image ) {
        fImage = image;
    }

    /**
     * Sets the parent name to the given name.
     * 
     * @param parentName the parent name to set
     */
    public void setParentName( String parentName ) {
        fParentName = parentName;
    }

    /**
     * Gets a string describing this object.  The name attribute of this object
     * is returned. 
     * 
     * @return the string describing this object.
     */
    public String toString() {
        return fName;
    }
}