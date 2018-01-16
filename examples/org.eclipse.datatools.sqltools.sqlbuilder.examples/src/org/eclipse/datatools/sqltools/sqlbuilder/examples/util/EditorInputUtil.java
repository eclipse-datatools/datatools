package org.eclipse.datatools.sqltools.sqlbuilder.examples.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderStorageEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLBuilderEditorInputUtil;
import org.eclipse.ui.XMLMemento;

public class EditorInputUtil {

	/**
	 * Utility function which runs SQLBuilderEditorInputUtil through its paces.
	 * Creates a SQLBuilderStorageEditorInput from a file, saves this to an XMLMemento then
	 * serializes the XMLMemento to a string. Finally, it creates a new SQLBuilderStorageEditorInput
	 * from the string.
	 * 
	 * @param file
	 * @return
	 */
	public static SQLBuilderStorageEditorInput createSQLBuilderStorageEditorInputFromStringViaFile(IFile file) {
		
		/*
		 * Create SQLBuilderStorageEditorInput from a file
		 */
		SQLBuilderStorageEditorInput storageEditorInput = SQLBuilderEditorInputUtil.createSQLBuilderStorageEditorInput(file);
		
		/*
		 * Save SQLBuilderStorageEditorInput to an XMLMemento
		 */
		XMLMemento memento = SQLBuilderEditorInputUtil.saveSQLBuilderStorageEditorInput(storageEditorInput);
		
		/*
		 *  Write out the XMLMemento to a string
		 */
		String sMemento = SQLBuilderEditorInputUtil.writeXMLMementoToString(memento);
		
		/*
		 * Create a new XMLMemento from the string just created
		 */
		return SQLBuilderEditorInputUtil.createSQLBuilderStorageEditorInput(sMemento);
		
	}

	/**
	 * Creates a SQLBuilderEditorInput based on a file which was created for the
	 * SQL File Editor
	 * 
	 * @param file
	 * @return
	 */
	public static SQLBuilderEditorInput createSQLBuilderEditorInputFromFile(
			IFile file) {
		/*
		 * Create SQLBuilderEditorInput from a file
		 */
		SQLBuilderEditorInput editorInput = SQLBuilderEditorInputUtil.createSQLBuilderEditorInput(file);
		return editorInput;
	}

	/**
	 * Creates a SQLBuilderEditorInput based on a file which was created for the
	 * SQL File Editor, but with no SQL statement, and a specified statementType.
	 * This is used for creating new statements
	 * 
	 * @param file
	 * @param statementType
	 * @return
	 */
	public static SQLBuilderEditorInput createEmptySQLBuilderEditorInputFromFile(
			IFile file, int statementType) {
		SQLBuilderEditorInput editorInput = SQLBuilderEditorInputUtil.createSQLBuilderEditorInput(file);
		
		// Set the SQLStatementInfo to be null
		editorInput.setSQLStatementInfo(null);
		
		// Set the statement type
		editorInput.setStatementType(statementType);
		
		return editorInput;
	}

	
}
