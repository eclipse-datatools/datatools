/**
 * Author: dpchou
 * Purpose: As demo code for EclipseWorld 2005, not liable for any consequences
 * as the result of using this code. 
 */
package org.eclipse.datatools.enablement.sybase.ase.providers;

import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.enablement.ase.catalog.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.ui.ASEImages;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.ui.SybaseImages;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEContentProviderUtil;
import org.eclipse.datatools.enablement.sybase.virtual.ParametersNode;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

public class SybaseASELabelProviderExt extends LabelProvider implements ICommonLabelProvider
{
	private static final IDataToolsUIServiceManager imageService = IDataToolsUIServiceManager.INSTANCE;

    /**
     * @return the Image associated with this element
     */
    public Image getImage(Object element)
    {
        if (element instanceof SybaseASETrigger)
        {
            if (!((SybaseASETrigger) element).isEnabled())
            {
                return ASEImages.get(ASEImages.IMG_DISABLEDTRIGGER);
            }
        }
        else if (element instanceof UserDefinedType)
        {
            return SybaseImages.get(SybaseImages.IMG_UDT);
        }
        else if (element instanceof SybaseASEWebServiceTable)
        {
            return ASEImages.get(ASEImages.IMG_WEBSERVICETABLE);
        }
        if (element instanceof SybaseASEProxyTable && !(element instanceof SybaseASEWebServiceTable))
        {
            SybaseASEProxyTable table = (SybaseASEProxyTable) element;
            switch (table.getExternalType().getValue())
            {
                case ProxyTableExternalType.FILE:
                    return ASEImages.get(ASEImages.IMG_FILEPROXYTABLE);
                case ProxyTableExternalType.DIRECTORY:
                    return ASEImages.get(ASEImages.IMG_DIRPROXYTABLE);
                case ProxyTableExternalType.PROCEDURE:
                    return ASEImages.get(ASEImages.IMG_RPCPROXYTABLE);
                case ProxyTableExternalType.TABLE:
                    return ASEImages.get(ASEImages.IMG_REMOTETABLE);
            }
        }
        else if (element instanceof Table)
        {
            if (element instanceof TemporaryTable)
            {
                return SybaseImages.get(SybaseImages.IMG_TEMPORARY_TABLE);
            }
            else if (element instanceof ViewTable)
            {
                return SybaseImages.get(SybaseImages.IMG_VIEW);
            }
            else if (element instanceof SybaseBaseTable && ((SybaseBaseTable) element).isSystem())
            {
                return SybaseImages.get(SybaseImages.IMG_SYSTEM_TABLE);
            }
            return SybaseImages.get(SybaseImages.IMG_TABLE);
        }        
        else if (element instanceof Trigger)
        {
            return SybaseImages.get(SybaseImages.IMG_TRIGGER);
        }
        else if (element instanceof Column)
        {
            if (element instanceof SybaseASEColumn && ((SybaseASEColumn) element).isComputedColumn())
            {
                return SybaseImages.get(SybaseImages.IMG_COMPUTED_COLUMN);
            }
            return SybaseImages.get(SybaseImages.IMG_COLUMN);
        }
        else if (element instanceof Index)
        {
            return SybaseImages.get(SybaseImages.IMG_INDEX);
        }
        else if (element instanceof PrimaryKey)
        {
            return SybaseImages.get(SybaseImages.IMG_PK);
        }
        else if (element instanceof ParametersNode)
        {
            return SybaseImages.get(SybaseImages.IMG_FOLDER);
        }
        else if (element instanceof SybaseParameter)
        {
            SybaseParameter parameter = (SybaseParameter) element;
            if (parameter.getJDBCParameterType().getValue() == JDBCParameterType.IN)
            {
                return SybaseImages.get(SybaseImages.IMG_PARAM_IN);
            }
            else if (parameter.getJDBCParameterType().getValue() == JDBCParameterType.OUT || parameter.getJDBCParameterType().getValue() == JDBCParameterType.IN_OUT)
            {
                return SybaseImages.get(SybaseImages.IMG_PARAM_OUT);
            }
            else
            {
                return SybaseImages.get(SybaseImages.IMG_PARAM);
            }
        }
        else if (element instanceof CheckConstraint)
        {
            return SybaseImages.get(SybaseImages.IMG_CK);
        }
        else if (element instanceof UniqueConstraint)
        {
        	return SybaseImages.get(SybaseImages.IMG_UNIQUE);
        }
        else if (element instanceof ForeignKey)
        {
            return SybaseImages.get(SybaseImages.IMG_FK);
        }
        else if (element instanceof SybaseASECatalog)
        {
            SybaseASECatalog catalog = (SybaseASECatalog) element;
            if (catalog.getCatalogType().getValue() == SybaseASECatalogType.TEMPCATALOG)
            {
                return SybaseImages.get(SybaseImages.IMG_TEMPORARY_DATABASE);
            }
            else if (catalog.getCatalogType().getValue() == SybaseASECatalogType.PROXYCATALOG)
            {
                return SybaseImages.get(SybaseImages.IMG_PROXY_DATABASE);
            }
        }
        return imageService.getLabelService(element).getIcon();
    }

    /**
     * @return the Text associated with this element
     */
    public String getText(Object element)
    {
		if (element instanceof IVirtualNode)
		{
			return ((IVirtualNode) element).getDisplayName();
		}
        else if (element instanceof DistinctUserDefinedType)
        {
            DSEContentProviderUtil.appendOwnerToLabel((SQLObject)element);
            return DSEContentProviderUtil.getUDTDisplayNameFormatedText(element);
        }
        else if (element instanceof SybaseParameter)
        {
            return DSEContentProviderUtil.getParameterDisplayNameFormatedText(element);
        }
        else if(element instanceof SQLObject){
            return DSEContentProviderUtil.appendOwnerToLabel((SQLObject)element);
        }		
        else if (element instanceof ENamedElement)
		{
		    return ((ENamedElement)element).getName();
		}
		else 
		{
		    return super.getText(element);
		}
    }
    
	public void initialize(String aViewerId) {
		// TODO Auto-generated method stub
		
	}

	public String getDescription(Object selection) {
        return IDataToolsUIServiceManager.INSTANCE.getLabelService(selection).getName();
	}

	public void restoreState(IMemento aMemento) {
		// TODO Auto-generated method stub
		
	}

	public void saveState(IMemento aMemento) {
		// TODO Auto-generated method stub
		
	}

	public void init(ICommonContentExtensionSite aConfig) {
		// TODO Auto-generated method stub
		
	}
}
