/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.provider.rdbschema;

import com.ibm.icu.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.DerivedTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory;
import org.eclipse.datatools.modelbase.sql.query.TableCorrelation;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;

/**
 * This class acts as both a content provider and lable provider for  
 * a tree viewer that is used to select a table from a collection of
 * available tables.  The collection is displayed as a tree of schemas
 * at the top level with tables at the next level.  Tables under a
 * schema are not loaded until the schema node is expanded by the user.
 */
public class AvailableTablesTreeProvider extends LabelProvider implements ITreeContentProvider {

    private List fSchemaList; // the list of schemas displayed at the top level of the tree
    SQLDomainModel domainModel;

    /**
     * Constructs an instance of this class using the given list of Schema objects.
     * @param schemaList the list of schemas to display in the tree
     */
    public AvailableTablesTreeProvider(List schemaList, SQLDomainModel domainModel) {
        fSchemaList = schemaList;
        this.domainModel = domainModel;
        if (fSchemaList == null) {
            fSchemaList = new ArrayList();
        }

        // Remove schema NULLID if it doesn't have any tables under it.  An empty
        // NULLID schema is an annoying problem on DB2.
        Iterator schemaListIter = fSchemaList.iterator();
        while (schemaListIter.hasNext()) {
            Schema schema = (Schema) schemaListIter.next();
            if (schema.getName().equals("NULLID")) {
                if (schema.getTables().size() == 0) {
                    schemaListIter.remove();
                }
            }
        }
    }

    /**
     * Implements {@link ITreeContentProvider#getChildren ITreeContentProvider.getChildren}.
     */
    public Object[] getChildren(Object parentElement) {
        // By default, return the list of schemas.
        List childList = fSchemaList;
        List withTables = new ArrayList();
        QueryStatement stmt = domainModel.getSQLStatement();
        if (stmt instanceof QuerySelectStatement) {
            QuerySelectStatement selectStmt = (QuerySelectStatement) stmt;
            QueryExpressionRoot qRoot = selectStmt.getQueryExpr();
            if (qRoot != null) {
                withTables = qRoot.getWithClause();
                if (withTables.size() > 0) {
                    // added TableCorrelation for With Tables Node 
                    // so that we can show all the with tables of a statement under one node
                    // and be consistent with Schema->Table hierarchy
                    SQLQueryModelFactory queryFactory = SQLQueryModelFactory.eINSTANCE;
                    TableCorrelation tableCorr = queryFactory.createTableCorrelation();
                    tableCorr.setName(Messages._UI_AVAILABLE_TABLES_TREE_PROVIDER);
                    childList.add(tableCorr);
                }
            }
        }

        if (parentElement instanceof TableCorrelation) {
            childList = new ArrayList();
            stmt = domainModel.getSQLStatement();
            if (stmt instanceof QuerySelectStatement) {
                QuerySelectStatement selectStmt = (QuerySelectStatement) stmt;
                QueryExpressionRoot qRoot = selectStmt.getQueryExpr();
                if (qRoot != null) {
                    withTables = qRoot.getWithClause();
                    if (withTables.size() > 0) {
                        childList.addAll(withTables);
                    }
                }
            }
        }
        // When the parent element is a schema, fetch the Table objects belonging
        // to that schema.
        if (parentElement instanceof Schema) {
            childList = new ArrayList();
            Schema schema = (Schema) parentElement;
            childList.addAll(schema.getTables());
        }

        // We want to sort the list before returning it.  We do this with a Comparator
        // that uses a Collator to do a text-based sort based on the names of the 
        // objects.  We assume that all the objects in the list subclass SQLObject
        // (and thus have a name).
        Comparator comp = new Comparator() {

            Collator fCollator = Collator.getInstance();

            // Compares two objects. Returns -1 if o1 is less than o2, 0 if they
            // are equal, +1 if o1 is greater than o2.
            public int compare(Object o1, Object o2) {
                int result = 0;
                // Assume o1 and o2 are SQLObjects
                SQLObject so1 = (SQLObject) o1;
                SQLObject so2 = (SQLObject) o2;
                result = fCollator.compare(so1.getName(), so2.getName());
                return result;
            }

            // Returns true when the Comparator is equal to the given object
            // (that is, another Comparator).
            public boolean equals(Object obj) {
                return this.equals(obj);
            }
        };

        // Sort the list.
        Collections.sort(childList, comp);

        return childList.toArray();
    }

    /**
     * Implements {@link ITreeContentProvider#getParent ITreeContentProvider.getParent}.
     */
    public Object getParent(Object element) {
        return null;
    }

    /**
     * Implements {@link ITreeContentProvider#hasChildren ITreeContentProvider.hasChildren}.
     */
    public boolean hasChildren(Object element) {
        boolean hasChildren = false;

        if (element instanceof Schema) {
            hasChildren = true;
        }
        else if (element instanceof TableCorrelation) { // used TableCorrelation for With Tables Node
            hasChildren = true;
        }

        return hasChildren;
    }

    /**
     * Implements {@link ITreeContentProvider#getElements ITreeContentProvider.getElements}.
     */
    public Object[] getElements(Object inputElement) {
        return getChildren(inputElement);
    }

    /**
     * Implements {@link ITreeContentProvider#dispose ITreeContentProvider.dispose}.
     */
    public void dispose() {
        // do nothing
    }

    /**
     * Implements {@link ITreeContentProvider#inputChanged ITreeContentProvider.inputChanged}.
     */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // do nothing
    }

    /**
     * Overrides {@link LabelProvider#getText LabelProvider.getText}.  When the given
     * object is a schema or table, the name of the schema or table object is returned.
     */
    public String getText(Object object) {
        String text = "";
        if (object instanceof Schema) {
            text = ((Schema) object).getName();
        }
        else if (object instanceof Table) {
            text = ((Table) object).getName();
        }
        else if (object instanceof WithTableSpecification) {
            text = ((WithTableSpecification) object).getName();
        }
        // used TableCorrelation for With Tables Node as a dummy node and be different with other objects
        // appear in the tree.  And show all the With Tables under one place.
        else if (object instanceof TableCorrelation) {
            text = ((TableCorrelation) object).getName();
        }

        if (text == null) {
            text = "";
        }
        return text;
    }

    /**
     * Overrides super to return the image associated with the input object
     * @param object the object associated with the image
     * @return the image for the object
     */
    public Image getImage(Object object) {
        Image image = null;
        // used TableCorrelation for With Tables Node
        if (object instanceof Schema || object instanceof TableCorrelation) {
            image = SQLBuilderPlugin.getPlugin().getImage("icons/schema.gif");
        }
        else if (object instanceof BaseTable) {
            image = SQLBuilderPlugin.getPlugin().getImage("icons/tables_tab.gif");
        }
        else if (object instanceof DerivedTable || object instanceof WithTableSpecification) {
            image = SQLBuilderPlugin.getPlugin().getImage("icons/view.gif");
        }
        else {
            // alias
            image = SQLBuilderPlugin.getPlugin().getImage("icons/alias.gif");
        }        return image;
    }

}
