/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.content.impl.ServerExplorerContentProviderNav;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.ServerToolsUIConstants;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.ui.CommonContentProviderBase;
import org.eclipse.datatools.connectivity.ui.IContentExtension;
import org.eclipse.swt.graphics.Image;

/**
 * This class is a content provider implemention for navigatorContent
 * extensions. This class provides SQL model content to the navigator.
 */
public class SQLModelContentProviderExtension extends CommonContentProviderBase {
    private static final String LABEL = ResourceLoader.INSTANCE.queryString("SQL_MODEL_CONTENT_EXTENSION_LABEL"); //$NON-NLS-1$

	public SQLModelContentProviderExtension() {
		super(new ServerExplorerContentProviderNav());
	}

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
}
