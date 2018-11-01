/*******************************************************************************
 * Copyright © 2007, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.views.source;

import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLDBProposal;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;


// RATLC01136221 bgp 15Jan2007 - new class
/**
 * Content assist proposal that represents either a schema, table, or column. 
 */
public class SQLBuilderDBProposal extends SQLDBProposal {

    private String fName2;
    private String fExtraInfo;
    
    /**
     * Constructs an instance of this object to represent the given database 
     * model object for purpose of a content assist proposal.
     * The database object can be one of <code>Schema</code>, <code>Table</code>,
     * <code>Column</code>.
     * 
     * @param dbObject the database model object
     * @see org.eclipse.datatools.modelbase.sql.schema.Schema
     * @see org.eclipse.datatools.modelbase.sql.schema.Table
     * @see org.eclipse.datatools.modelbase.sql.schema.Column
     */
    public SQLBuilderDBProposal( EObject dbObject ) {
        super( dbObject );
        
        fName2 = null;
        fExtraInfo = null;
        
        if (dbObject instanceof Column) {
//            Column col = (Column) dbObject;
//            IColumnHelperService colHelper = IDataToolsUIServiceManager.INSTANCE.getColumnHelperService();
//            if (colHelper != null) {
//                String datatype = colHelper.getDataType(col);
//                if (datatype != null) {
//                    datatype = datatype.trim();
//                }
//                setExtraInfo(datatype);
//            }
            
//            if (col.isPartOfPrimaryKey() == true) {
//                Image pkColImage = SQLBuilderPlugin.getSQLImage( "icons/PrimaryKeyColumn.gif" ); //$NON-NLS-1$
//                setImage(pkColImage);
//            }
//            else if (col.isPartOfForeignKey() == true) {
//                Image fkColImage = SQLBuilderPlugin.getSQLImage( "icons/ForeignKeyColumn.gif" ); //$NON-NLS-1$
//                setImage(fkColImage);
//            }
            
            /* The superclass appends the datatype to the name, separated by
             * "-".  Move the datatype to the extra info field. */
            String name = getName();
            String typeSeparator = " - ";
            int typeSepIndex = name.indexOf(typeSeparator); //$NON-NLS-1$
            if (typeSepIndex > 0) {
                String shortName = name.substring(0, typeSepIndex);
                if (shortName != null) {
                    setName(shortName);
                }
                String datatypeStr = name.substring(typeSepIndex + typeSeparator.length());
                if (datatypeStr != null) {
                    setExtraInfo(datatypeStr);
                }
            }
        }
    }

    /** 
     * Gets the "extra info" associated with this object.
     * (This is used for column datatype information.)
     * @return the extra info for this object
     */
    public String getExtraInfo() {
        return fExtraInfo;
    }
    
    /**
     * Gets the name of the database object.  This overrides the superclass so
     * we can use our own variable for the name with a "set" operation.  (The name
     * field in the superclass can only be set in the constructor.)
     * 
     * @return the name of the database object
     */
    public String getName() {
        String name = fName2;
        
        if (name == null) {
            name = super.getName();
        }
        
        return name;
    }

    public void setExtraInfo( String extraInfo ) {
        fExtraInfo = extraInfo;
    }
    
    /**
     * Sets the database object name of this database proposal to the given name.
     * 
     * @param name the name to use
     */
    public void setName( String name ) {
        fName2 = name;
    }
    
    /**
     * Gets a string describing this object.  The name attribute of this object
     * is returned. 
     * 
     * @return the string describing this object.
     */
    public String toString() {
        return getName();
    }
}