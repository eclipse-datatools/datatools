package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.RoutineInfoASALoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseParameterLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProcedureImpl;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseProcedure extends SybaseASABaseProcedureImpl implements ICatalogObject{

	private static final long serialVersionUID = 6507002865454988134L;

	protected Boolean procedureInfoLoaded = Boolean.FALSE;
	protected Boolean parameterInfoLoaded = Boolean.FALSE;
	
	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (procedureInfoLoaded) {
			if(procedureInfoLoaded.booleanValue())
			{
				procedureInfoLoaded = Boolean.FALSE;
			}
		}
		synchronized (parameterInfoLoaded) {
			if(parameterInfoLoaded.booleanValue())
			{
				parameterInfoLoaded = Boolean.FALSE;
                //the result set is retrieved from parameters
                if (resultSet != null)
                {
                    resultSet.clear();
                }
			}
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROCEDURE__DESCRIPTION:
			getDescription();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROCEDURE__SOURCE:
			getSource();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROCEDURE__PARAMETERS:
			getParameters();
			break;
		}
		return super.eIsSet(eFeature);
	}
	
	public String getDescription() {
		synchronized (procedureInfoLoaded) {
			if(!procedureInfoLoaded.booleanValue())
			{
				loadProcedureInfo();
				procedureInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDescription();
	}
	
	public Source getSource() {
		synchronized (procedureInfoLoaded) {
			if(!procedureInfoLoaded.booleanValue())
			{
				loadProcedureInfo();
				procedureInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getSource();
	}
	
	public EList getParameters() {
		synchronized (parameterInfoLoaded) {
			if(!parameterInfoLoaded.booleanValue())
			{
				loadParameterInfo();
				parameterInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getParameters();
	}

	protected void loadProcedureInfo() {
		new RoutineInfoASALoader(this).loadRoutineInfo();
	}

	protected void loadParameterInfo()
	{
		new SybaseASABaseParameterLoader(this).loadParameterInfo(super.getParameters());
	}	
}
