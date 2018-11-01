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
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;

/**
 * This class creates a composite with statement name, type of statement
 * template and whether to create the template in the SQL Builder or SQL editor.
 */
public class NewSQLStatementComposite extends Composite implements ModifyListener, SelectionListener {

    // Define constants for statement types. Note: these values must
    // match the values loaded into the statement type combo control in the
    // dialog area control.
    //    public static final int STATEMENT_TYPE_SELECT = 0;
    //    public static final int STATEMENT_TYPE_INSERT = 1;
    //    public static final int STATEMENT_TYPE_UPDATE = 2;
    //    public static final int STATEMENT_TYPE_DELETE = 3;
    //    public static final int STATEMENT_TYPE_FULLSELECT = 4;
    //    public static final int STATEMENT_TYPE_WITH = 5;
    // Note: the values previously defined above are now in
    // StatementHelper. However this code requires that the list indexes of
    // the choices in this control match the values defined above. Also the
    // values of following two choices must start one higher than the
    // largest value above.
    public static final int STATEMENT_TYPE_SAMPLE = 6;
    public static final int STATEMENT_TYPE_NONE = 7;

    // Define contants for editor type.
    private static final int QUERY_BUILDER = 0;
    private static final int SQL_SOURCE_EDITOR = 1;

    private static final int INITIAL_TEXTFIELD_WIDTH = 250;

    protected Text   fStatementNameText;
    protected Combo  fStatementTypeCombo;
    protected Button fQueryBuilderButton;
    protected Button fSQLEditorButton;
    protected Label  fErrorLabel;

    private String fStatementName;
    private int fStatementType = StatementHelper.STATEMENT_TYPE_SELECT;
    private int fEditorType = QUERY_BUILDER;
    private int fPreviousSelectedIndex = 0;
    private boolean fSupportsExtendedStatementTypesForEditor = true;

    /**
     * Creates an instance of this class with the given parent composite and
     * style flags.
     * 
     * @param parent the parent composite
     * @param style the composite style
     */
    public NewSQLStatementComposite(Composite parent, int style) {
        super(parent, style);

        // Create the statement identification composite.
        createStatementIDPart(this);

        // Create the editor choice control composite.
        createEditorPart(this);

        GridData idData = new GridData();
        idData.grabExcessHorizontalSpace = false;
        idData.horizontalAlignment = GridData.FILL;
        idData.horizontalSpan = 2;

        fErrorLabel = new Label(this, SWT.WRAP);
        fErrorLabel.setLayoutData(idData);
    }

    /**
     * Creates the statement identification part of the composite.
     * 
     * @param parent the parent composite
     */
    private void createStatementIDPart(Composite parent) {
        // Create a group for the statement identification.
        Group statementGroup = new Group(parent, SWT.SHADOW_IN);
        statementGroup.setText(Messages.datatools_sqlbuilder_newStatementPage_statementID);
        GridData idData = new GridData();
        idData.grabExcessHorizontalSpace = true;
        idData.horizontalAlignment = GridData.FILL;
        statementGroup.setLayoutData(idData);
        GridLayout idLayout = new GridLayout();
        idLayout.numColumns = 2;
        statementGroup.setLayout(idLayout);
        
        // Create a label for statement name.
        Label nameLabel = new Label(statementGroup, SWT.LEAD);
        nameLabel.setText(Messages.datatools_sqlbuilder_newStatementPage_statementName);
        GridData nameData = new GridData();
        nameData.horizontalAlignment = GridData.BEGINNING;
        
        // Crate a text field for statement name.
        fStatementNameText = new Text(statementGroup, SWT.BORDER);
        GridData nameTextData = new GridData();
        nameTextData.widthHint = INITIAL_TEXTFIELD_WIDTH;
        nameTextData.grabExcessHorizontalSpace = true;
        nameTextData.horizontalAlignment = GridData.FILL;
        fStatementNameText.setLayoutData(nameTextData);
        fStatementNameText.addModifyListener(this);
        
        // Create a label for the statement type combo.
        Label typeLabel = new Label(statementGroup, SWT.LEAD);
        typeLabel.setText(Messages.datatools_sqlbuilder_newStatementPage_statementType);
        GridData typeData = new GridData();
        typeData.horizontalAlignment = GridData.BEGINNING;
        
        // Create a Combo box control for statement type.
        fStatementTypeCombo = new Combo(statementGroup, SWT.DROP_DOWN | SWT.READ_ONLY);
        fStatementTypeCombo.addSelectionListener(this);
        fStatementTypeCombo.setItems( getStatementTypes() );
        fStatementTypeCombo.select(0); // select first one as default
        GridData comboData = new GridData();
        comboData.horizontalAlignment = GridData.FILL;
        fStatementTypeCombo.setLayoutData(comboData);

    }

    /**
     * Creates the editor selection part of the composite with the given parent.
     * 
     * @param parent the parent control
     */
    private void createEditorPart(Composite parent) {
        // Create a group for editor choice.
        Group editorGroup = new Group(parent, SWT.SHADOW_IN);
        editorGroup.setText(Messages.datatools_sqlbuilder_newStatementPage_editUsing);
        GridData editData = new GridData();
        editData.grabExcessHorizontalSpace = true;
        editData.horizontalAlignment = GridData.FILL;
        editorGroup.setLayoutData(editData);
        GridLayout editLayout = new GridLayout();
        editorGroup.setLayout(editLayout);
        
        // Create a radio button for Query builder.
        fQueryBuilderButton = new Button(editorGroup, SWT.RADIO);
        fQueryBuilderButton.setSelection(true);
        fQueryBuilderButton.addSelectionListener(this);
        fQueryBuilderButton.setText(Messages.datatools_sqlbuilder_newStatementPage_sqlQueryBuilder);
        GridData queryBuilderData = new GridData();
        queryBuilderData.horizontalAlignment = GridData.BEGINNING;
        queryBuilderData.grabExcessHorizontalSpace = true;
        fQueryBuilderButton.setLayoutData(queryBuilderData);
        
        // Create a radio button for SQL editor.
        fSQLEditorButton = new Button(editorGroup, SWT.RADIO);
        fSQLEditorButton.addSelectionListener(this);
        fSQLEditorButton.setText(Messages.datatools_sqlbuilder_newStatementPage_sqlEditor);
        GridData sqlEditorData = new GridData();
        sqlEditorData.horizontalAlignment = GridData.BEGINNING;
        sqlEditorData.grabExcessHorizontalSpace = true;
        fSQLEditorButton.setLayoutData(sqlEditorData);
    }

    /**
     * Gets the ID of editor the user selected. The value returned is an editor
     * ID defined in the plugin.xml file of the editor.
     * 
     * @return the ID of the editor the user wants to use
     */
    public String getEditorID() {
        String editorID = null;
        if (fEditorType == QUERY_BUILDER) {
            editorID = WorkbenchUtility.SQL_BUILDER_ID;
        }
        else if (fEditorType == SQL_SOURCE_EDITOR) {
            editorID = WorkbenchUtility.SQL_EDITOR_ID;
        }
        return editorID;
    }

    /**
     * Gets the type of editor the user selected to the given value. The value
     * is a constant defined in this class, either <code>QUERY_BUILDER</code>
     * or <code>SQL_SOURCE_EDITOR</code>.
     * 
     * @param editorType the value indicating the editor type the user wants to
     *            use
     */
    protected int getEditorType() {
        return fEditorType;
    }

    /**
     * Gets the statement name that the user entered in the dialog.
     * 
     * @return the statement name
     */
    public String getStatementName() {
        return fStatementName;
    }

    /**
     * Gets the statement type value that the user selected in the dialog. See
     * the statement type constants defined for this dialog.
     * 
     * @return the statement type
     */
    public int getStatementType() {
        return fStatementType;
    }

    /**
     * Gets the statement types
     * 
     * @return All statement types supported by this wizard
     */
    protected String[] getStatementTypes() {
        
        /* Create the basic list of statement types. */
        List stmtTypeList = new ArrayList();
        
        stmtTypeList.add( "SELECT" ); //$NON-NLS-1$
        stmtTypeList.add( "INSERT" ); //$NON-NLS-1$
        stmtTypeList.add( "UPDATE" ); //$NON-NLS-1$
        stmtTypeList.add( "DELETE" ); //$NON-NLS-1$
        stmtTypeList.add( "FULLSELECT" ); //$NON-NLS-1$
        stmtTypeList.add( "WITH" ); //$NON-NLS-1$

        /* The SQL Editor supports more statement types (unless this feature has
         * been turned off). */
        if (getEditorType() == SQL_SOURCE_EDITOR && this.getSupportsExtendedStatementTypesForEditor() == true ) {
           stmtTypeList.add( Messages.datatools_sqlbuilder_newStatementPage_sampleStatements); 
           stmtTypeList.add( Messages.datatools_sqlbuilder_newStatementPage_none);
        }
        
        /* Return the result as an array rather than a list. */
        String[] stmtTypeArray = (String[]) stmtTypeList.toArray( new String[ stmtTypeList.size() ] );
        return stmtTypeArray;
    }

    /**
     * Gets the statementName Text widget.
     * 
     * @return the statementName Text widget
     */
    public Text getStatementNameText() {
        return fStatementNameText;
    }

    /**
     * Gets the statementType combo widget.
     * 
     * @return the statementType combo widget
     */
    public Combo getStatementTypeCombo() {
        return fStatementTypeCombo;
    }

    /**
     * Gets the SQLBuilderEditor Button Widget
     * 
     * @return the SQLBuilderEditor Button Widget
     */
    public Button getQueryBuilderButton() {
        return fQueryBuilderButton;
    }

    /**
     * Gets the SQLEditor Button Widget
     * 
     * @return the SQLEditor Button Widget
     */
    public Button getSQLEditorButton() {
        return fSQLEditorButton;
    }

    /**
     * Gets whether or not the extended statement types (SAMPLE, NONE) are
     * supported for the SQL Editor.  (The are never supported for the SQL
     * Builder.)
     * 
     * @return true when the extended statement types are supported,
     * otherwise false
     */
    public boolean getSupportsExtendedStatementTypesForEditor() {
        return fSupportsExtendedStatementTypesForEditor;
    }

    /**
     * Gets called when the text widget that this object is listening to is
     * modified.
     * 
     * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
     */
    public void modifyText(ModifyEvent evt) {
        Object source = evt.getSource();
        if (source == getStatementNameText()) {
            String statementName = getStatementNameText().getText().trim();
            setStatementName(statementName);
        }
    }

    /**
     * Sets the type of editor the user selected to the given value. The value
     * should be a constant defined in this class, either
     * <code>QUERY_BUILDER</code> or <code>SQL_SOURCE_EDITOR</code>.
     * 
     * @param editorType the value indicating the editor type the user wants to
     *            use
     */
    protected void setEditorType(int editorType) {
        fEditorType = editorType;
    }

    /**
     * Sets the statement name that the user entered in the dialog to the given
     * name.
     * 
     * @param statementName the statement name to set
     */
    public void setStatementName(String statementName) {
        fStatementName = statementName;
    }

    public void setErrorLabelText(String text) {
        fErrorLabel.setText(text);
        this.update();
    }

    /**
     * Sets the statement type value that the user selected in the dialog to the
     * given value. See the statement type constants defined for this dialog.
     * 
     * @param statementType the statement type to set
     */
    public void setStatementType(int statementType) {
        fStatementType = statementType;
    }

    /**
     * Sets whether or not the extended statement types (SAMPLE, NONE) are
     * supported for the SQL Editor.  (The are never supported for the SQL
     * Builder.)
     * 
     * @param supported true when the extended statement types should be supported,
     * otherwise false
     */
    public void setSupportsExtendedStatementTypesForEditor( boolean supported ) {
        fSupportsExtendedStatementTypesForEditor = supported;
    }
    
    /**
     * Gets called when the selection changes in a control that we are listening
     * to.
     * 
     * @param evt the selection event
     */
    public void widgetSelected(SelectionEvent evt) {
        Object source = evt.getSource();
        if (source == getStatementTypeCombo()) {
            int statementType = getStatementTypeCombo().getSelectionIndex();
            fPreviousSelectedIndex = statementType;
            setStatementType(statementType);
        }
        else if (source == getQueryBuilderButton()) {
            setEditorType( QUERY_BUILDER );
            
            /* Rebuild the list of statement types, since it may be different
             * based on the selected editor type. */
            String[] statementTypes = getStatementTypes();
            fStatementTypeCombo.setItems(statementTypes);
            
            /* Restore the currently selected statement type to what it was before
             * we reset the list of statement types.  However, when the editor type 
             * is Builder, it's possible that the previously selected statement 
             * type is no longer available, since the number of available choices 
             * may be smaller.  In that case set the select to a default selection
             * (SELECT, the first statement type). */
            if (fPreviousSelectedIndex < statementTypes.length) {
                fStatementTypeCombo.select(fPreviousSelectedIndex); // select previous selection
                setStatementType(fPreviousSelectedIndex);
            }
            else {
                fPreviousSelectedIndex = 0;
                fStatementTypeCombo.select(fPreviousSelectedIndex);
                setStatementType(fPreviousSelectedIndex);
            }
        }
        else if (source == getSQLEditorButton()) {
            setEditorType( SQL_SOURCE_EDITOR );
            String[] statementTypes = getStatementTypes();
            fStatementTypeCombo.setItems(statementTypes);
            fStatementTypeCombo.select(fPreviousSelectedIndex); // select previous selection
            setStatementType(fPreviousSelectedIndex);
        }
    }

    /**
     * Gets called when the "default selection" occurs (for example, the user
     * hits the return key in a Text widget).
     * 
     * @param evt the selection event
     */
    public void widgetDefaultSelected(SelectionEvent evt) {
        // do nothing
    }

}