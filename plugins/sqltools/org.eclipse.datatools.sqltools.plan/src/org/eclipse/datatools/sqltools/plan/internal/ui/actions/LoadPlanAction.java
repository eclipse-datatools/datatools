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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.plan.IHelpConstants;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanRequest;
import org.eclipse.datatools.sqltools.plan.PlanServiceRegistry;
import org.eclipse.datatools.sqltools.plan.internal.PlanConstants;
import org.eclipse.datatools.sqltools.plan.internal.IPlanInstance;
import org.eclipse.datatools.sqltools.plan.internal.PlanViewPlugin;
import org.eclipse.datatools.sqltools.plan.internal.util.ILogger;
import org.eclipse.datatools.sqltools.plan.internal.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Loads saved plans.
 * <p>
 * The file should conform to the following DTD constraint.
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
public class LoadPlanAction extends Action
{
    private static ILogger _log = PlanViewPlugin.getLogger(null);

    /**
     * Constructor
     *
     */
    public LoadPlanAction()
    {
        setText(Messages.LoadPlanAction_title); 
        setToolTipText(Messages.LoadPlanAction_tooltip); 
        this.setImageDescriptor(Images.DESC_IMPORT_PLAN);
        this.setDisabledImageDescriptor(Images.DESC_IMPORT_PLAN_DISABLE);
        
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.LOAD_PLAN_ACTION, PlanViewPlugin.getDefault().getBundle().getSymbolicName()));
    }

    public void run()
    {
        FileDialog dlg = new FileDialog(PlanViewPlugin.getActiveWorkbenchShell(), SWT.OPEN);
        String file = dlg.open();
        if (file != null)
        {
            File f = new File(file);
            if (!f.exists())
            {
                MessageDialog
                        .openInformation(PlanViewPlugin.getActiveWorkbenchShell(), Messages.LoadPlanAction_info, Messages.LoadPlanAction_filenotfound); 
                return;
            }
            try
            {
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = builder.parse(file);
                NodeList list = document.getDocumentElement().getChildNodes();
                int nodeCount = list.getLength();
                for (int i = 0; i < nodeCount; i++)
                {
                    Node planNode = list.item(i);
                    PlanRequest request = null;
                    NamedNodeMap map = planNode.getAttributes();
                    Node status = map.getNamedItem("status");
                    if (status == null)
                    {
                        continue;
                    }
                    Node type = map.getNamedItem("type");
                    if (type == null)
                    {
                        continue;
                    }
                    NodeList planNodeList = planNode.getChildNodes();
                    for (int j = 0; j < planNodeList.getLength(); j++)
                    {
                        Node subNode = planNodeList.item(j);
                        if (subNode.getNodeName().equals("request"))
                        {
                            NamedNodeMap subNodeAttrs = subNode.getAttributes();
                            Node dbId = subNodeAttrs.getNamedItem("dbDefinitionId");
                            String dbDefinitionId = "";
                            String sql = "";
                            if (dbId == null)
                            {
                                dbDefinitionId = Messages.LoadPlanAction_unknown_db; 
                            }
                            else
                            {
                                dbDefinitionId = dbId.getNodeValue();
                            }
                            IPlanService service = PlanServiceRegistry.getInstance().getPlanService(dbDefinitionId);
                            
                            if (subNode.getFirstChild() != null)
                            {
                                CDATASection sqlCDATA = (CDATASection) subNode.getFirstChild();
                                sql = sqlCDATA.getNodeValue();
                            }
                            request = new PlanRequest(sql, dbDefinitionId, service.getPlanOption().getTypeIdByName(type.getNodeValue()) ,
                                    PlanRequest.VIEW_ACTIVATE);
                        }
                        else if (subNode.getNodeName().equals("rawPlan"))
                        {
                            String rawPlan = "";
                            if (subNode.getFirstChild() != null)
                            {
                                CDATASection rawPlanCDATA = (CDATASection) subNode.getFirstChild();
                                rawPlan = rawPlanCDATA.getNodeValue();
                                rawPlan  = rawPlan.replaceAll(SavePlanAction.CDATA_END_SUB, SavePlanAction.CDATA_END);
                            }
                            if (request != null)
                            {
                                IPlanInstance instance = PlanViewPlugin.getPlanManager().createNewPlanInstance(request);
                                if (getStatus(status.getNodeValue()) == IPlanInstance.SUCCESS)
                                {
                                    instance.finishSuccess(rawPlan);
                                }
                                else
                                {
                                    instance.finishFail(new Throwable(rawPlan));
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                final IStatus fstatus = new Status(IStatus.ERROR, PlanConstants.PLUGIN_ID, IStatus.OK, e.getMessage(), e);
                final String title = Messages.LoadPlanAction_error; 
                final String msg = Messages.LoadPlanAction_errorinfo; 
                Display display = PlanViewPlugin.getActiveWorkbenchShell().getDisplay();
                display.asyncExec(new Runnable()
                {
                    public void run()
                    {
                        ErrorDialog.openError(PlanViewPlugin.getActiveWorkbenchShell(), title, msg, fstatus);
                    }
                });

                _log.error("LoadPlanAction.error.message", e); //$NON-NLS-1$
            }
        }
    }

    private int getStatus(String desc)
    {
        if ("SUCCESS".equals(desc))
        {
            return IPlanInstance.SUCCESS;
        }
        else if ("SUCCESS".equals(desc))
        {
            return IPlanInstance.FAILED;
        }
        else
        {
            return IPlanInstance.RUNNING;
        }
    }

}
