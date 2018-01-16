package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SOEUIPlugin;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.ISchemaObjectEditorExtensionsRegistryReader;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.SchemaObjectEditorExtensionsRegistryReader;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EcoreFactoryImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * Contains some static utility methods for schama object editor.
 * 
 * @author Idull
 */
public class SchemaObjectEditorUtils
{
    /**
     * Opens the editor for the given database object of the given database type.
     * 
     * @param dbName the name of the database
     * @param dbVersion the version of the database
     * @param objectTypeId the id of the database object
     * @return an <code>IEditorPart</code> instance if succeeded, <code>null</code> otherwise
     */
    public static IEditorPart openEditor(String dbName, String dbVersion, String objectTypeId, ISchemaObjectEditModel modelObject,
        DatabaseIdentifier databaseIdentifier)
    {
        IEditorDescriptor editor = getEditor(dbName, dbVersion, objectTypeId);
        if (editor != null)
        {
            ISchemaObjectEditorInput input = new SchemaObjectEditorInput(editor, modelObject, databaseIdentifier);
            IEditorPart part = null;
            try
            {
                part = SOEUIPlugin.getActiveWorkbenchPage().openEditor(input, Constants.SCHEMA_EDITOR_ID);
            }
            catch (Exception e)
            {
                IStatus status = new Status(IStatus.ERROR, SOEUIPlugin.PLUGIN_ID, 0,
                    Messages.SchemaObjectEditorUtils_internal_error_when_opening + editor.getEditorName(), e);
                ErrorDialog.openError(SOEUIPlugin.getActiveWorkbenchShell(), Messages.SchemaObjectEditorUtils_error,
                    Messages.SchemaObjectEditorUtils_error_when_open, status);
            }
            return part;
        }
        else
        {
            IStatus status = new Status(IStatus.ERROR, SOEUIPlugin.PLUGIN_ID, 0,
                Messages.SchemaObjectEditorUtils_no_suitable_editor, null);
            ErrorDialog.openError(SOEUIPlugin.getActiveWorkbenchShell(), Messages.SchemaObjectEditorUtils_error,
                Messages.SchemaObjectEditorUtils_vendor_name + dbName + Messages.SchemaObjectEditorUtils_version
                + dbVersion + Messages.SchemaObjectEditorUtils_object_type + objectTypeId
                + Messages.SchemaObjectEditorUtils_no_editor, status);
            return null;
        }
    }

    /**
     * Opens the editor given the editor id
     * 
     * @param editorId the id of the editor
     * @param modelObject the model
     * @param databaseIdentifier
     * @return
     */
    public static IEditorPart openEditor(String editorId, ISchemaObjectEditModel modelObject, DatabaseIdentifier databaseIdentifier)
    {
        IEditorDescriptor editor = getEditorById(editorId);
        if (editor != null)
        {
            ISchemaObjectEditorInput input = new SchemaObjectEditorInput(editor, modelObject, databaseIdentifier);
            IEditorPart part = null;
            try
            {
                part = SOEUIPlugin.getActiveWorkbenchPage().openEditor(input, Constants.SCHEMA_EDITOR_ID);
            }
            catch (Exception e)
            {
                IStatus status = new Status(IStatus.ERROR, SOEUIPlugin.PLUGIN_ID, 0,
                    Messages.SchemaObjectEditorUtils_internal_error_when_opening + editor.getEditorName(), e);
                ErrorDialog.openError(SOEUIPlugin.getActiveWorkbenchShell(), Messages.SchemaObjectEditorUtils_error,
                    Messages.SchemaObjectEditorUtils_error_open, status);
            }
            return part;
        }
        else
        {
            IStatus status = new Status(IStatus.ERROR, SOEUIPlugin.PLUGIN_ID, 0,
                Messages.SchemaObjectEditorUtils_no_suitable_editor, null);
            ErrorDialog.openError(SOEUIPlugin.getActiveWorkbenchShell(), Messages.SchemaObjectEditorUtils_error,
                Messages.SchemaObjectEditorUtils_editor_id + editorId
                + Messages.SchemaObjectEditorUtils_no_extension, status);
            return null;
        }
    }

    /**
     * Returns the editor instance containing extension information given the database object of the given database
     * type. Asks user to choose the editor if there are multiple matches. 
     * 
     * @param dbName the database name
     * @param dbVersion the database version
     * @param objectTypeId the database object id
     * @return the editor instance containing editor extension information and pages information, <code>null</code> if
     *         the extension can not be found
     */
    protected synchronized static IEditorDescriptor getEditor(String dbName, String dbVersion, String objectTypeId)
    {
        ISchemaObjectEditorExtensionsRegistryReader reader = SchemaObjectEditorExtensionsRegistryReader.getInstance();
        IEditorDescriptor[] editors = reader.getEditorDescriptors();
        if (editors != null)
        {
            final List matcheres = new ArrayList();
            final List versions = new ArrayList();
            for (int i = 0; i < editors.length; i++)
            {
                if (editors[i].getVendorName().equals(dbName) && editors[i].getSchemaObjectType().equals(objectTypeId))
                {
                    DatabaseVendorDefinitionId.VersionComparator comparator = new DatabaseVendorDefinitionId.VersionComparator();
                    int result = comparator.compare(dbVersion, editors[i].getVersion());
                    if (result == 0)
                    {
                        return editors[i];
                    }
                    else
                    {
                        matcheres.add(editors[i]);
                        versions.add(editors[i].getVersion());
                    }
                }
            }

            if (matcheres.size() > 0)
            {
                IEditorDescriptor editorToUse = null;
                Collections.sort(versions);
                String highest = (String) versions.get(versions.size() - 1);
                Iterator iter = matcheres.iterator();
                while (iter.hasNext())
                {
                    IEditorDescriptor editor = (IEditorDescriptor) iter.next();
                    if (editor.getVersion().equals(highest))
                    {
                        editorToUse = editor;
                    }
                }
                
                IPreferenceStore store = SOEUIPlugin.getDefault().getPreferenceStore();
                if (!store.getBoolean(Constants.PREFERENCE_USE_LATEST_VERSION))
                {
                    MessageDialogWithToggle d = MessageDialogWithToggle.openYesNoQuestion(SOEUIPlugin
                            .getActiveWorkbenchShell(), Messages.SchemaObjectEditorUtils_question,
                            Messages.SchemaObjectEditorUtils_no_exact_editor + dbVersion
                                    + Messages.SchemaObjectEditorUtils_use_higest_version_editor,
                            Messages.SchemaObjectEditorUtils_always_open, false, store, Constants.PREFERENCE_USE_LATEST_VERSION);
                    int result = d.getReturnCode();
                    switch (result)
                    {
                        case IDialogConstants.YES_ID:
                            if(d.getToggleState())
                            {
                                store.setValue(Constants.PREFERENCE_USE_LATEST_VERSION, true);
                            }
                            return editorToUse;
                        case IDialogConstants.NO_ID:
                            return null;
                        default:
                            break;
                    }
                }
                else
                {
                    return editorToUse;
                }
            }
        }
        return null;
    }

    /**
     * Returns the editor instance containing extension information given the database object of the given database
     * type. Always returns the first match.
     * 
     * @param dbName the database name
     * @param dbVersion the database version
     * @param objectType the database object type to be edit
     * @return the editor instance containing editor extension information and pages information, <code>null</code> if
     *         the extension can not be found
     */
    public synchronized static IEditorDescriptor getEditorByObjectType(String dbName, String dbVersion, EClass objectType)
    {
        ISchemaObjectEditorExtensionsRegistryReader reader = SchemaObjectEditorExtensionsRegistryReader.getInstance();
        IEditorDescriptor[] editors = reader.getEditorDescriptors();
        if (editors != null)
        {
            final List matcheres = new ArrayList();
            final List versions = new ArrayList();
            String objectTypeId = objectType.getInstanceClassName();
            for (int i = 0; i < editors.length; i++)
            {
                if (editors[i].getVendorName().equals(dbName) && editors[i].getSchemaObjectType().equals(objectTypeId))
                {
                    DatabaseVendorDefinitionId.VersionComparator comparator = new DatabaseVendorDefinitionId.VersionComparator();
                    int result = comparator.compare(dbVersion, editors[i].getVersion());
                    if (result == 0)
                    {
                        return editors[i];
                    }
                    else
                    {
                        matcheres.add(editors[i]);
                        versions.add(editors[i].getVersion());
                    }
                }
            }

            if (matcheres.size() > 0)
            {
                return (IEditorDescriptor)matcheres.get(0);
            }
            else
            {
                for (int i = 0; i < editors.length; i++)
                {

                    if (editors[i].getVendorName().equals(dbName))
                    {
                        EClass clazz = EcoreFactoryImpl.eINSTANCE.createEClass();
                        clazz.setInstanceClassName(editors[i].getSchemaObjectType());
                        if (clazz.isSuperTypeOf(objectType))
                        {
                            DatabaseVendorDefinitionId.VersionComparator comparator = new DatabaseVendorDefinitionId.VersionComparator();
                            int result = comparator.compare(dbVersion, editors[i].getVersion());
                            if (result == 0)
                            {
                                return editors[i];
                            }
                            else
                            {
                                matcheres.add(editors[i]);
                                versions.add(editors[i].getVersion());
                            }
                        }
                        
                    }
                }
            }
            
            if (matcheres.size() > 0)
            {
                return (IEditorDescriptor)matcheres.get(0);
            }
        }
        return null;
    }

    /**
     * Returns the editor given the editor id, there may be several editors with the same id, we always return the first
     * one
     * 
     * @param editorId
     * @return
     */
    public static IEditorDescriptor getEditorById(String editorId)
    {
        ISchemaObjectEditorExtensionsRegistryReader reader = SchemaObjectEditorExtensionsRegistryReader.getInstance();
        IEditorDescriptor[] editors = reader.getEditorDescriptors();

        for (int i = 0; i < editors.length; i++)
        {
            if (editors[i].getEditorId().equals(editorId))
            {
                return editors[i];
            }
        }
        return null;
    }

    /**
     * Returns the editors of the given name
     * 
     * @param editorName
     * @return
     */
    public static IEditorDescriptor[] getEditorsByName(String editorName)
    {
        ISchemaObjectEditorExtensionsRegistryReader reader = SchemaObjectEditorExtensionsRegistryReader.getInstance();
        IEditorDescriptor[] editors = reader.getEditorDescriptors();

        List es = new ArrayList();
        for (int i = 0; i < editors.length; i++)
        {
            if (editors[i].getEditorName().equals(editorName))
            {
                es.add(editors[i]);
            }
        }
        return (IEditorDescriptor[]) es.toArray(new IEditorDescriptor[es.size()]);
    }

    /**
     * Returns the editors for the given database
     * 
     * @param vendorName
     * @return
     */
    public static IEditorDescriptor[] getEditorsByVendorName(String vendorName)
    {
        ISchemaObjectEditorExtensionsRegistryReader reader = SchemaObjectEditorExtensionsRegistryReader.getInstance();
        IEditorDescriptor[] editors = reader.getEditorDescriptors();

        List es = new ArrayList();
        for (int i = 0; i < editors.length; i++)
        {
            if (editors[i].getVendorName().equals(vendorName))
            {
                es.add(editors[i]);
            }
        }
        return (IEditorDescriptor[]) es.toArray(new IEditorDescriptor[es.size()]);
    }

    /**
     * Returns all the defined editors, cataloged by db definition
     * 
     * @return
     */
    public static Map getEditorsCatalogedByDBDefinition()
    {
        Map editorsMap = new HashMap();
        ISchemaObjectEditorExtensionsRegistryReader reader = SchemaObjectEditorExtensionsRegistryReader.getInstance();
        IEditorDescriptor[] editors = reader.getEditorDescriptors();
        IEditorDescriptor[] clonedEditors = (IEditorDescriptor[]) editors.clone();

        for (int i = 0; i < clonedEditors.length; i++)
        {
            String dbdefinition = clonedEditors[i].getVendorName() + "_" + clonedEditors[i].getVersion(); //$NON-NLS-1$
            if (editorsMap.get(dbdefinition) != null)
            {
                List es = (List) editorsMap.get(dbdefinition);
                es.add(clonedEditors[i]);
            }
            else
            {
                List es = new ArrayList();
                es.add(clonedEditors[i]);
                editorsMap.put(dbdefinition, es);
            }
        }
        return editorsMap;
    }
    
    /**
     * Expand the tree node and reveal the editing object after refresh
     * For editor, we do not know the status of the tree in the DSE when editing,
     * so we have to expand every node on the path from the root to the editing object.
     * 
     * @return
     */
    public static void expandNode(SQLObject obj)
    {    
//        WizardUtil.expandNode(obj);            
    }
    
    public static TreeItem findAndExpandVirtualNode(TreeItem item, String NodeType, CommonViewer viewer)
    {
    	if(item == null)
    	{
    		return null;
    	}
    	TreeItem treeItems[] = item.getItems();
    	for(int i=0; i < treeItems.length; i++)
    	{           		
    		if(treeItems[i].getData() instanceof VirtualNode && ((VirtualNode)(treeItems[i].getData())).getGroupID().equals(NodeType))
    		{
    			viewer.expandToLevel(treeItems[i].getData(), 1);
    			return treeItems[i];
    		}
    	}
    	return null;
    }
    
    public static TreeItem findAndExpandObjectNode(TreeItem item, SQLObject obj, CommonViewer viewer)
    {
    	if(item == null)
    	{
    		return null;
    	}
    	TreeItem treeItems[] = item.getItems();
    	for(int i=0; i < treeItems.length; i++)
    	{           		
    		if(treeItems[i].getData().equals(obj))
    		{
    			viewer.expandToLevel(treeItems[i].getData(), 1);
    			return treeItems[i];
    		}
    	}
    	return null;
    }
}
