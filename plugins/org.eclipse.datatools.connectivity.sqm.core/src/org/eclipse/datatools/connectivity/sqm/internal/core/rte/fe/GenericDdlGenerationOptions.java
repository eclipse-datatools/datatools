package org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe;

import java.util.ResourceBundle;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.EngineeringOptionID;
import org.eclipse.datatools.connectivity.sqm.core.rte.EngineeringOption;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategory;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.EngineeringOptionCategoryID;

public class GenericDdlGenerationOptions {
	public final static byte GENERATE_FULLY_QUALIFIED_NAME = 0;
	public final static byte GENERATE_QUOTED_IDENTIFIER = 1;
	public final static byte GENERATE_DROP_STATEMENTS = 2;
	public final static byte GENERATE_CREATE_STATEMENTS = 3;
	public final static byte GENERATE_TABLES =4;
	public final static byte GENERATE_INDICES = 5;
	public final static byte GENERATE_VIEWS = 6;
	public final static byte GENERATE_TRIGGERS = 7;
	

	public static EngineeringOption[] createDDLGenerationOptions(EngineeringOptionCategory[] categories) {
        ResourceBundle resource = ResourceBundle.getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$

        EngineeringOptionCategory general_options =null;
        EngineeringOptionCategory additional_element =null;
        for (int i = 0; i < categories.length; i++) {
        	if (categories[i].getId().equals(EngineeringOptionCategoryID.GENERATE_OPTIONS)){
        		general_options = categories[i];
        	} else if (categories[i].getId().equals(EngineeringOptionCategoryID.GENERATE_ELEMENTS)){
        		additional_element = categories[i];
        	}
        }
        
        Vector optionVec = new Vector();
        optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_FULLY_QUALIFIED_NAME,resource.getString("GENERATE_FULLY_QUALIFIED_NAME"), resource.getString("GENERATE_FULLY_QUALIFIED_NAME_DES"), false,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
        optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_QUOTED_IDENTIFIER,resource.getString("GENERATE_QUOTED_IDENTIFIER"), resource.getString("GENERATE_QUOTED_IDENTIFIER_DES"),false,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
        optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_DROP_STATEMENTS,resource.getString("GENERATE_DROP_STATEMENTS"), resource.getString("GENERATE_DROP_STATEMENTS_DES"),false,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
        optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_CREATE_STATEMENTS,resource.getString("GENERATE_CREATE_STATEMENTS"), resource.getString("GENERATE_CREATE_STATEMENTS_DES"),true,general_options)); //$NON-NLS-1$ //$NON-NLS-2$
        optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_TABLES,resource.getString("GENERATE_TABLES"), resource.getString("GENERATE_TABLES_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_INDICES,resource.getString("GENERATE_INDEX"), resource.getString("GENERATE_INDEX_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_VIEWS,resource.getString("GENERATE_VIEW"), resource.getString("GENERATE_VIEW_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        optionVec.add(new EngineeringOption(EngineeringOptionID.GENERATE_TRIGGERS,resource.getString("GENERATE_TIGGER"), resource.getString("GENERATE_TIGGER_DES"),true,additional_element)); //$NON-NLS-1$ //$NON-NLS-2$
        
        EngineeringOption[] options = new EngineeringOption[optionVec.size()];
        optionVec.copyInto(options);
		return options;
    }

	public static EngineeringOptionCategory[] createDDLGenerationOptionCategories() {
        ResourceBundle resource = ResourceBundle.getBundle("org.eclipse.datatools.connectivity.sqm.internal.core.rte.fe.GenericDdlGeneration"); //$NON-NLS-1$

        Vector categoryVec = new Vector();
        categoryVec.add(new EngineeringOptionCategory(EngineeringOptionCategoryID.GENERATE_OPTIONS,resource.getString("GENERATION_OPTIONS"), resource.getString("GENERATION_OPTIONS_DES"))); //$NON-NLS-1$ //$NON-NLS-2$
        categoryVec.add(new EngineeringOptionCategory(EngineeringOptionCategoryID.GENERATE_ELEMENTS,resource.getString("ADDITIONAL_ELEMENTS"), resource.getString("ADDITIONAL_ELEMENTS_DES"))); //$NON-NLS-1$ //$NON-NLS-2$
    
        EngineeringOptionCategory[] categories = new EngineeringOptionCategory[categoryVec.size()];
        categoryVec.copyInto(categories);
		return categories;
	}
	
}
