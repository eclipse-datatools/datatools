/**
 * Author: dpchou
 * Purpose: As demo code for EclipseWorld 2005, not liable for any consequences
 * as the result of using this code. 
 */
package org.eclipse.datatools.enablement.sybase.asa.providers;

import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable;
import org.eclipse.datatools.enablement.sybase.ui.SybaseImages;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEContentProviderUtil;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

public class SybaseASALabelProviderExt extends LabelProvider implements ICommonLabelProvider
{
	private static final IDataToolsUIServiceManager imageService = IDataToolsUIServiceManager.INSTANCE;

	/**
     * @return the Image associated with this element
     */
    public Image getImage(Object element)
    {
        if(element instanceof SybaseViewTable)
        {
            if (((SybaseViewTable) element).isSystem())
            {
                return SybaseImages.get(SybaseImages.IMG_SYSTEMVIEW );
            }
//            else
//            {
//                return SybaseImages.get(SybaseImages.IMG_VIEW);
//            }
        }
//        else if (element instanceof UserDefinedType)
//        {
//            return SybaseImages.get(SybaseImages.IMG_UDT);
//        }
//        else if (element instanceof UserDefinedFunction)
//        {
//            return SybaseImages.get(SybaseImages.IMG_UDF);
//        }
//        else if (element instanceof SybaseASABaseProxyTable)
//        {
//            return SybaseImages.get(ASAImages.IMG_REMOTETABLE);
//        }
        else if (element instanceof Table)
        {
//            if (element instanceof TemporaryTable)
//            {
//                return SybaseImages.get(SybaseImages.IMG_TEMPORARY_TABLE);
//            }
//            else if (element instanceof ViewTable)
//            {
//                return SybaseImages.get(SybaseImages.IMG_VIEW);
//            }
//            else 
            	if (((SybaseBaseTable) element).isSystem())
            {
                return SybaseImages.get(SybaseImages.IMG_SYSTEM_TABLE);
            }
//            return SybaseImages.get(SybaseImages.IMG_TABLE);
        }
//        else if (element instanceof Trigger)
//        {
//            return SybaseImages.get(SybaseImages.IMG_TRIGGER);
//        }
//        else if (element instanceof Column)
//        {
//            return SybaseImages.get(SybaseImages.IMG_COLUMN);
//        }
//        else if (element instanceof Index)
//        {
//            return SybaseImages.get(SybaseImages.IMG_INDEX);
//        }
//        else if (element instanceof PredefinedDataType)
//        {
//            return SybaseImages.get(SybaseImages.IMG_SYSTEM_DATA_TYPE);
//        }
//        else if (element instanceof ParametersNode)
//        {
//            return SybaseImages.get(SybaseImages.IMG_FOLDER);
//        }
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
//        else if (element instanceof CheckConstraint)
//        {
//            return SybaseImages.get(SybaseImages.IMG_CK);
//        }
//        else if (element instanceof UniqueConstraint)
//        {
//        	return SybaseImages.get(SybaseImages.IMG_UNIQUE);
//        }
//        else if (element instanceof ForeignKey)
//        {
//            return SybaseImages.get(SybaseImages.IMG_FK);
//        }
//        else if (element instanceof PrimaryKey)
//        {
//            return SybaseImages.get(SybaseImages.IMG_PK);
//        }
//        else if (element instanceof Database)
//        {
//            return SybaseImages.get(SybaseImages.IMG_DATABASE);
//        }
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
        else if (element instanceof SQLObject){
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
		
	}

	public String getDescription(Object selection) {
        return IDataToolsUIServiceManager.INSTANCE.getLabelService(selection).getName();
	}

	public void restoreState(IMemento aMemento) {
	}

	public void saveState(IMemento aMemento) {
	}

	public void init(ICommonContentExtensionSite aConfig) {
		// TODO Auto-generated method stub
		
	}
}
