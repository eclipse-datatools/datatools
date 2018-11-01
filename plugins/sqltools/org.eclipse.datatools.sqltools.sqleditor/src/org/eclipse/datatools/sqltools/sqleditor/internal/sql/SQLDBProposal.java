/*
* Copyright (c) 2005. IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License 2.0
* which accompanies this distribution, and is available at
* https://www.eclipse.org/legal/epl-2.0/
*
* Contributors:
*     IBM Corporation - initial API and implementation
*/

package org.eclipse.datatools.sqltools.sqleditor.internal.sql;

import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.datatools.sqltools.core.DataTypeProvider;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * Content assist proposal that represents either a catalog, schema, table, column, function, stored procedure, trigger, or event.
 * 
 * @author Hetty Dougherty
 *  
 */
public class SQLDBProposal {

    public static final int SCHEMA_OBJTYPE = 1; //proposal is a schema
    public static final int TABLE_OBJTYPE = 2; // proposal is a table
    public static final int TABLECOLUMN_OBJTYPE = 3; //proposal is table column
    public final static int CATALOG_OBJTYPE = 4; //reserved
    public final static int FUNCTION_OBJTYPE = 5;
	public final static int STORED_PROCEDURE_OBJTYPE = 6;
	public final static int TRIGGER_OBJTYPE = 7;
	public final static int EVENT_OBJTYPE = 8;
	public final static int TABLEALIAS_OBJTYPE = 9;
    public final static int INDEX_OBJTYPE = 10;
    public final static int SEGMENT_OBJTYPE = 11;
    public static final int UNKNOWN_OBJTYPE = -1;

    private EObject fDBObject; // database model object
    private Image fImage = null;
    private String fName = null;
    private String fParentAlias = null;
    private String fParentName = null;
    private String fGrandParentName = null;
    private String fGrandGrandParentName = null;
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
    public SQLDBProposal( EObject dbObject, String alias ) {
    	this(dbObject);
    	this.fParentAlias = alias;
    }

    /**
     * Constructs an instance of this object to represent the given table alias
     * for purpose of a content assist proposal.
     * @param alias the table alias
     */
    public SQLDBProposal( String alias ) {
    	fType = TABLEALIAS_OBJTYPE;
        fName = alias;
        fParentName = null;
        fGrandParentName = null;
        fParentObject = null;
        setImage( SQLEditorResources.getImage( "table_alias" )); //$NON-NLS-1$
    }
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
            Schema schema = (Schema) dbObject;
            fType = SCHEMA_OBJTYPE;
            fName = schema.getName();
            /* Need to deal with Database vs. Catalog as parent. */ 
            Database db = ModelUtil.getDatabase(schema);
            if (db != null) {
                fParentObject = db;
                fParentName = db.getName();
            }
            setImage( SQLEditorResources.getImage( "schema" )); //$NON-NLS-1$
        }
        else if (dbObject instanceof Table) {
            fType = TABLE_OBJTYPE;
            fName = ((Table) dbObject).getName();
            fParentName = ((Table) dbObject).getSchema().getName();
            fParentObject = ((Table) dbObject).getSchema();
            fGrandParentName = ModelUtil.getDatabaseName((Schema) fParentObject);
            fGrandGrandParentName = null;
            if (dbObject instanceof ViewTable) {
                setImage( SQLEditorResources.getImage( "view" )); //$NON-NLS-1$
            } else {
                setImage( SQLEditorResources.getImage( "table" )); //$NON-NLS-1$
            }
        }
        else if (dbObject instanceof Column) {
            DataTypeProvider provider = SQLToolsFacade.getConfigurationByVendorIdentifier(
                    ModelUtil.getDatabaseVendorDefinitionId(((Column) dbObject).getTable().getSchema()))
                    .getSQLDataService().getDataTypeProvider();
            String datatypeStr = provider.getDataTypeString(((Column) dbObject).getDataType(), false);
            if (((Column) dbObject).getDataType() instanceof PredefinedDataType && datatypeStr != null)
            {
                datatypeStr = datatypeStr.toLowerCase();
            }

            fType = TABLECOLUMN_OBJTYPE;
            fName = ((Column) dbObject).getName() + " - " + datatypeStr; //$NON-NLS-1$;
            fParentName = ((Column) dbObject).getTable().getSchema().getName()
                    + "." + ((Column) dbObject).getTable().getName();
            fParentObject = ((Column) dbObject).getTable();
            fGrandParentName = ((Table) fParentObject).getSchema().getName();
            fGrandGrandParentName = ModelUtil.getDatabaseName(((Table) fParentObject).getSchema());
            
            if (((Column) dbObject).isPartOfPrimaryKey())
            {
                setImage(SQLEditorResources.getImage("column_pkey")); //$NON-NLS-1$
            }
            else if (((Column) dbObject).isPartOfForeignKey())
            {
                setImage(SQLEditorResources.getImage("column_fkey")); //$NON-NLS-1$
            }
            else if (((Column) dbObject).isNullable())
            {
                setImage(SQLEditorResources.getImage("column_null")); //$NON-NLS-1$
            }
            else
            {
                setImage(SQLEditorResources.getImage("column")); //$NON-NLS-1$
            }
        }
        else if (dbObject instanceof Function) {
        	fType = FUNCTION_OBJTYPE;
        	fName = ((Function) dbObject).getName();
        	fParentName = ((Function) dbObject).getSchema().getName();
        	fParentObject = ((Function) dbObject).getSchema();
        	fGrandParentName = ModelUtil.getDatabaseName((Schema) fParentObject);
        	fGrandGrandParentName = null;
        	setImage( SQLEditorResources.getImage( "function" )); //$NON-NLS-1$
        }
        else if (dbObject instanceof Procedure) {
        	fType = STORED_PROCEDURE_OBJTYPE;
        	fName = ((Procedure) dbObject).getName();
        	fParentName = ((Procedure) dbObject).getSchema().getName();
        	fParentObject = ((Procedure) dbObject).getSchema();
        	fGrandParentName = ModelUtil.getDatabaseName((Schema) fParentObject);
        	fGrandGrandParentName = null;
        	setImage( SQLEditorResources.getImage( "procedure" )); //$NON-NLS-1$
        }
        else if (dbObject instanceof Event) {
        	fType = EVENT_OBJTYPE;
        	fName = ((Event) dbObject).getName();
        	fParentName = ((Event) dbObject).getDatabase().getName();
        	fParentObject = ((Event) dbObject).getDatabase();
        	fGrandParentName = null;
        	fGrandGrandParentName = null;
        	setImage( SQLEditorResources.getImage( "event" )); //$NON-NLS-1$
        }
        else if (dbObject instanceof Trigger) {
        	fType = TRIGGER_OBJTYPE;
        	fName = ((Trigger) dbObject).getName();
            fParentName = ((Trigger) dbObject).getSubjectTable().getSchema().getName()
            + "." + ((Trigger) dbObject).getSubjectTable().getName();
            fParentObject = ((Trigger) dbObject).getSubjectTable();
            fGrandParentName = ((Table) fParentObject).getSchema().getName();
            fGrandGrandParentName = ModelUtil.getDatabaseName(((Table) fParentObject).getSchema());
            setImage( SQLEditorResources.getImage( "trigger" )); //$NON-NLS-1$
        }
        else if (dbObject instanceof Index) {
            fType = INDEX_OBJTYPE;
            fName = ((Index) dbObject).getName();
            fParentName = ((Index) dbObject).getTable().getSchema().getName()
            + "." + ((Index) dbObject).getTable().getName();
            fParentObject = ((Index) dbObject).getTable();
            fGrandParentName = ((Table) fParentObject).getSchema().getName();
            fGrandGrandParentName = ModelUtil.getDatabaseName(((Table) fParentObject).getSchema());
            setImage( SQLEditorResources.getImage( "index" )); //$NON-NLS-1$
        }
        else if (dbObject instanceof Catalog)
        {
            fType = CATALOG_OBJTYPE;
            fName = ((Catalog) dbObject).getName();
            fParentObject = ((Catalog) dbObject).getDatabase();
            setImage(SQLEditorResources.getImage( "database" ));
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
     * Gets the alias of the database object.
     * 
     * @return the alias of the database object, if none, equals to getParentName()
     */
    public String getParentAlias() {
    	if (fParentAlias != null)
    	{
    		return fParentAlias;
    	}
    	return fParentName;
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
     * Gets the name of the grandparent of the database model object associated 
     * with this proposal.
     * 
     * @return the grand parent name
     */
    public String getGrandParentName() {
    	return fGrandParentName;
    }
    
    /**
     * Gets the name of the grandgrandparent of the database model object associated 
     * with this proposal.
     * 
     * @return the grand parent name
     */
    public String getGrandGrandParentName() {
    	return fGrandGrandParentName;
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
     * Sets the type for this content assist proposal.
     * 
     * @param type the type to set
     */
    public void setType( int type ) {
        fType = type;
    }

    /**
     * Sets the name to the given name.
     * 
     * @param name the name to set
     */
    public void setName( String name ) {
        fName = name;
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