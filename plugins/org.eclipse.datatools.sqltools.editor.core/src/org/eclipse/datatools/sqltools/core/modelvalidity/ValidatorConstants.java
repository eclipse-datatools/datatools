package org.eclipse.datatools.sqltools.core.modelvalidity;

public class ValidatorConstants
{

    /**
     * The database identifier which is put in shared context and will be used to validate the identifier
     */
    public static final String DATABASE_IDENTIFIER            = "didentifier";                   //$NON-NLS-1$
    /**
     * If the validator needs to know more than what the editting model can provide, it can refer to the original model
     * object of the same type. Note: might be null.
     */
    public static final String ORIGINAL_MODEL = "original_model";  
    public static final String VALIDATE_DUPLICATE_NAME_VIA_DB = "validate_duplicate_name_via_db"; //$NON-NLS-1$
    public static final String VALIDATE_DEFAULT_VALUE_VIA_DB  = "validate_default_value_via_db"; //$NON-NLS-1$
    public static final String NOT_VALIDATE_DEFAULT_VALUE_OFFLINE = "not_validate_default_value_offline"; //$NON-NLS-1$
    
    public static final String DATA_OFFLINE_VALIDATOR             = "data_offline_validator";
    public static final String VALIDATE_EMPTY_NAME                = "validate_empty_name"; 
}
