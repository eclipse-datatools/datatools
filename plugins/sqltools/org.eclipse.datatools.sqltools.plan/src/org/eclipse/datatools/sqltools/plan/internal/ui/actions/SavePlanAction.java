/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal.ui.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.plan.IHelpConstants;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.plan.PlanServiceRegistry;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanConstants;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.plan.internal.util.ILogger;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

/**
 * Saves the plans.
 * <p>
 * Uses the following document constraint.
 * <pre>
 * <?xml version="1.0" encoding="UTF-8"?>
 * <!ELEMENT plans(plan)*>
 * <!ELEMENT plan(request,rawPlan)>
 * <!ELEMENT request(#PCDATA)>
 * <!ELEMENT rawPlan(#PCDATA)>
 * <!ATTLIST plan status CDATA #required type CDATA #required>
 * <!ATTLIST request dbDefinitionId CDATA #required>
 * </pre>
 * 
 * @author Hui Cao
 *  
 */
public class SavePlanAction extends Action
{
    public static final String CDATA_END     = "]]>";
    public static final String CDATA_END_SUB = "]]&gt;";
    private static ILogger _log = PlanViewPlugin.getLogger(null);

    /**
     * Constructor
     *
     */
    public SavePlanAction()
    {
        setText(Messages.SavePlanAction_saveplan_title); 
        setToolTipText(Messages.SavePlanAction_saveplan_tooltip); 
        this.setImageDescriptor(Images.DESC_EXPORT_PLAN);
        this.setDisabledImageDescriptor(Images.DESC_EXPORT_PLAN_DISABLE);
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.SAVE_PLAN_ACTION, PlanViewPlugin.getDefault().getBundle().getSymbolicName()));
    }

    /**
     * If the file exists then popup a dialog to promte overwrite or not, if choose yes, then overwrite it, if choose
     * No,go back to the save dialog, if choose Cancel, close the dialog and do nothing.
     * 
     * @param file
     * @param backInputDlg
     * @return the input file name
     */
    private String checkFileExists(String file, boolean backInputDlg)
    {
        if (file == null)
        {
            return "";
        }
        if (backInputDlg)
        {
            FileDialog dlg = new FileDialog(PlanViewPlugin.getActiveWorkbenchShell(), SWT.SAVE);
            dlg.setFileName(file);
            String retFile = dlg.open();
            if(retFile == null)
            {
                return "";
            }
            return checkFileExists(retFile, false);
        }
        File f = new File(file);
        if (f.exists())
        {
            String[] buttons = new String[]
            {
                IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.CANCEL_LABEL
            }
            ;
            String question = NLS.bind(Messages.SavePlanAction_overwrite_q, (new Object[]
			{
			    file
			}));
            // pop up a new dialog to promote overwrite or not
            MessageDialog d = new MessageDialog(PlanViewPlugin.getActiveWorkbenchWindow().getShell(), Messages.SavePlanAction_question, null, question, MessageDialog.QUESTION, buttons, 0);
            switch (d.open())
            {
                case 0: // Yes
                    return file;
                case 1: // No
                    // return to input file dialog
                    return checkFileExists(file, true);
                case 2: // Cancel
                    return "";
                default:
                    return "";
            }
        }
        return file;
    }

    public void run()
    {
        FileDialog dlg = new FileDialog(PlanViewPlugin.getActiveWorkbenchShell(), SWT.SAVE);
        String file = dlg.open();
        if (file != null)
        {
            file = checkFileExists(file, false);
            if ("".equals(file))
            {
                return;
            }
            
            DocumentBuilder builder = null;
            try
            {
                builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            }
            catch(Exception ex)
            {
                _log.error("SavePlanAction.builder.error",ex);
            }
            
            Document document = builder.newDocument();
            Element root = document.createElement("plans");
            document.appendChild(root);

            IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
            for (int i = 0; i < instances.length; i++)
            {
                IPlanInstance instance = instances[i];
                PlanRequest planRequest = instance.getPlanRequest();
            	IPlanService service = PlanServiceRegistry.getInstance().getPlanService(instance.getPlanRequest().getDatabaseDefinitionId());
                
                if(instance.isFinished())
                {
                    Element planNode = document.createElement("plan");
                    Attr status = document.createAttribute("status");
                    status.setNodeValue(getStatusString(instance.getStatus()));
                    planNode.setAttributeNode(status);
                    
                    Attr type = document.createAttribute("type");
                    type.setNodeValue(service.getPlanOption().getTypeNameById(instance.getPlanRequest().getPlanType()));
                    planNode.setAttributeNode(type);
                    root.appendChild(planNode);

                    Element requestNode = document.createElement("request");
                    Attr dbId = document.createAttribute("dbDefinitionId");
                    dbId.setNodeValue(planRequest.getDatabaseDefinitionId());
                    requestNode.setAttributeNode(dbId);

                    CDATASection sql = document.createCDATASection(planRequest.getSql());
                    requestNode.appendChild(sql);
                    planNode.appendChild(requestNode);

                    Element rawPlanNode = document.createElement("rawPlan");
                    planNode.appendChild(rawPlanNode);

                    if (instance.getStatus() == IPlanInstance.SUCCESS)
                    {
                        if(instance.getRawPlan() == null)
                        {
                            CDATASection rawPlanData = document.createCDATASection("");
                            rawPlanNode.appendChild(rawPlanData);
                        }
                        else
                        {
                            CDATASection rawPlanData = document.createCDATASection(instance.getRawPlan().toString()
                                    .replaceAll(CDATA_END, CDATA_END_SUB));
                            rawPlanNode.appendChild(rawPlanData);
                        }
                    }
                    else
                    {
                        CDATASection failureInfo = document.createCDATASection(instance.getFailThrowable().getMessage());
                        rawPlanNode.appendChild(failureInfo);
                    }
                }
            }
            // write to a file
            try
            {
                File exportedFile = new File(file);
                FileOutputStream fos = new FileOutputStream(exportedFile);
                String enc = PlanViewPlugin.getDefault().getPreferenceStore().getString(
                        PreferenceConstants.EXPORT_FORMAT_PREF_ENCODING);
                OutputStreamWriter osw = new OutputStreamWriter(fos, enc);
//                if(document instanceof DocumentImpl)
//                {
//                    ((DocumentImpl)document).setXmlEncoding(enc);
//                    ((DocumentImpl)document).setInputEncoding(enc);
//                }
                DOMSource source = new DOMSource(document.getDocumentElement());
                StreamResult result = new StreamResult(osw);
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, result);
            }
            catch (Exception e)
            {
                final IStatus fstatus = new Status(IStatus.ERROR, PlanConstants.PLUGIN_ID, IStatus.OK, e.getMessage(), e);
                final String title = Messages.SavePlanAction_error; 
                final String msg = Messages.SavePlanAction_error_info; 
                Display display = PlanViewPlugin.getActiveWorkbenchShell().getDisplay();
                display.asyncExec(new Runnable()
                {
                    public void run()
                    {
                        ErrorDialog.openError(PlanViewPlugin.getActiveWorkbenchShell(), title, msg, fstatus);
                    }
                }
                );
                _log.error("SavePlanAction.error.message", e);
            }

        }
    }

    private String getStatusString(int status)
    {
        switch (status)
        {
            case IPlanInstance.SUCCESS:
                return "SUCCESS";
            case IPlanInstance.FAILED:
                return "FAILED";
            default:
                return "RUNNING";
        }
    }

    public void update()
    {
        boolean shouldEnable = false;
        IPlanInstance[] instances = PlanViewPlugin.getPlanManager().getAllPlanInstances();
        for(int i=0;i<instances.length;i++)
        {
            if(instances[i].isFinished())
            {
                shouldEnable = true;
                break;
            }
        }
        setEnabled(shouldEnable);
    }
}
