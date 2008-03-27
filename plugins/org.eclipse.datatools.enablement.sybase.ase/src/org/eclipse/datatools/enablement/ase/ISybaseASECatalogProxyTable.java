/**
 * Created on 2007-6-14
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.ase;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType;

/**
 * 
 * @author Hao wang
 */
public interface ISybaseASECatalogProxyTable extends ISybaseASECatalogTable
{
    public String getFileDelimiterSuper();
    public String getExternalPathSuper();
    public ProxyTableExternalType getExternalTypeSuper();
    public boolean isExistingSuper();
}
