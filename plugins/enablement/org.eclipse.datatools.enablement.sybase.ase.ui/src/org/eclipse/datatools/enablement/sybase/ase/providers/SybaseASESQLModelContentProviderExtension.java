/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.providers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.providers.content.virtual.VirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.SQLModelContentExtension;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.ServerToolsUIConstants;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.ui.CommonContentProviderBase;
import org.eclipse.datatools.connectivity.ui.IContentExtension;
import org.eclipse.datatools.enablement.ase.IJDBCASEConnectionProfileConstants;
import org.eclipse.datatools.enablement.ase.catalog.SybaseASECatalogDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEUtil;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.navigator.IPipelinedTreeContentProvider;
import org.eclipse.ui.navigator.PipelinedShapeModification;
import org.eclipse.ui.navigator.PipelinedViewerUpdate;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SybaseASESQLModelContentProviderExtension extends
CommonContentProviderBase implements IPipelinedTreeContentProvider{

	public SybaseASESQLModelContentProviderExtension() {
		super(new SybaseASEContentProvider());
	}

    private static final String LABEL = ResourceLoader.INSTANCE.queryString("SQL_MODEL_CONTENT_EXTENSION_LABEL"); //$NON-NLS-1$

	protected IContentExtension createContentExtension(
			IConnectionProfile profile) {
		return new SQLModelContentExtension(profile);
	}

    public IContentExtension getContentExtension(final IConnectionProfile profile) 
    {
        final Properties prop = profile.getProperties(ServerToolsUIConstants.OFFLINE_PROPERTY_SET);
        if (prop == null || prop.isEmpty())
        {
            return super.getContentExtension(profile);
        }
        else
        {
            return new IContentExtension ()
            {
                public void closeConnection()
                {
                }
                public void dispose()
                {
                }
                public IConnection getConnection()
                {
                    return new IConnection ()
                    {

                        public void close()
                        {
                        }

                        public Throwable getConnectException()
                        {
                            return null;
                        }

                        public IConnectionProfile getConnectionProfile()
                        {
                            return profile;
                        }

                        public Object getRawConnection()
                        {
                            return prop.get(ServerToolsUIConstants.CONNECTION_INFO);
                        }
                    };
                }
                public IConnectionProfile getConnectionProfile()
                {
                    return profile;
                }
                public Image getImage()
                {
                    return null;
                }
                public String getLabel()
                {
                    return LABEL;
                }
                public boolean isVisible()
                {
                    return false;
                }
                public void openConnection()
                {
                }
            };
        }
    }

	public void getPipelinedChildren(Object aParent, Set theCurrentChildren) {
		//just to make sure we won't tamper with other connection profiles
		if (aParent != null && aParent instanceof IConnectionProfile && !((IConnectionProfile)aParent).getProviderId().equals(IJDBCASEConnectionProfileConstants.PROVIDER_ID))
		{
			return;
		}
		theCurrentChildren.clear();
		Object[] children = getChildren(aParent);
		for(int i=0; i< children.length; i++)
		{
			theCurrentChildren.add(children[i]);
		}
	}

	public void getPipelinedElements(Object anInput, Set theCurrentElements) {
		
	}

	public Object getPipelinedParent(Object anObject, Object aSuggestedParent) {
		// TODO Auto-generated method stub
		return null;
	}

	public PipelinedShapeModification interceptAdd(PipelinedShapeModification anAddModification) {
		Object parent = anAddModification.getParent();
		
		if(parent instanceof EObject || (parent instanceof IVirtualNode && ((IVirtualNode)parent).getParent() instanceof EObject))
		{
			EObject eObj = parent instanceof EObject ? (EObject)parent : (EObject)((IVirtualNode)parent).getParent();
			
			Database database = DSEUtil.findDatabaseByChild(eObj);
			if(!(database instanceof SybaseASEDatabase))
			{
				return anAddModification;
			}
		}
		
		List removing = new ArrayList();
		Set children = anAddModification.getChildren();
		for (Iterator iterator = children.iterator(); iterator.hasNext();) 
		{
			Object obj = (Object) iterator.next();

			if(obj instanceof SybaseASECatalogDatabase)
			{
				removing.add(obj);
			}
			else if(obj instanceof VirtualNode)
			{
				
				String groupId = ((VirtualNode)obj).getGroupID();
				if(parent instanceof SybaseASESchema && (groupId.equals(GroupID.PROCEDURE)||groupId.equals(GroupID.VIEW)
						||groupId.equals(GroupID.TABLE)||groupId.equals(GroupID.USER_DEFINED_TYPE)||groupId.equals(GroupID.ROLE)
						||groupId.equals(GroupID.FUNCTION)) || groupId.equals(GroupID.DEPENDENCY) || groupId.equals(GroupID.CONSTRAINT)
						||groupId.equals(GroupID.INDEX))
				{
					removing.add(obj);					
				}
				else if(obj instanceof SybaseASEIndex && ((SybaseASEIndex)obj).isSystemGenerated())
				{
					removing.add(obj);
				}
			}
		}
		anAddModification.getChildren().removeAll(removing);
		return anAddModification;
	}

	public boolean interceptRefresh(PipelinedViewerUpdate aRefreshSynchronization) {
		// TODO Auto-generated method stub
		return false;
	}

	public PipelinedShapeModification interceptRemove(PipelinedShapeModification aRemoveModification) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean interceptUpdate(PipelinedViewerUpdate anUpdateSynchronization) {
		// TODO Auto-generated method stub
		return false;
	}

}
