package org.eclipse.datatools.sqltools.sqlbuilder.examples.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderStorageEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLBuilderEditorInputUtil;
import org.eclipse.ui.XMLMemento;

public class EditorInputUtil {

	/**
	 * Utility function which runs SQLBuilderInputFactory through its paces.
	 * Creates a SQLBuilderEditorInput from a file, saves this to an XMLMemento then
	 * serializes the XMLMemento to a string. Finally, it creates a new SQLBuilderEditorInput
	 * from the string.
	 * 
	 * @param file
	 * @return
	 */
	public static SQLBuilderStorageEditorInput createSQLBuilderEditorInputFromStringViaFile(IFile file) {
		
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

	
}
