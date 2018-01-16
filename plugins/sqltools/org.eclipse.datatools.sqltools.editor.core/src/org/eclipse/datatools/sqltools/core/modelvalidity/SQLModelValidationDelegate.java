/**
 * Created on 2006-11-16
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core.modelvalidity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * The validator to validate the validity of a given SQL model.
 * <p>
 * The logic to validate each feature in the given SQL model goes into the specific SQL object validator, for example, a
 * sybase ase's table validator should extends <code>SQLModelValidator</code> to validator each feature, and it will
 * in turn to call the column's validator to validate all columns.
 * <p>
 * We will take Sybase Adaptive Server Anywhere table validation as an exmaple here to demostrate how to validate the
 * given model:<br>
 * 
 * <pre>
 * Map context = SQLModelValidationDelegate.getBasicValidationContext();
 * List items = new ArrayList();
 * 
 * // check the validity of the table name
 * items.add(new ValidationItem(SybaseasasqlmodelPackage.SYBASE_ASA_TABLE__NAME));
 * 
 * // check all columns (for containment reference, should construct ContainmentFeatureValidationItem)
 * ContainmentFeatureValidationItem item = new ContainmentFeatureValidationItem(
 *         SybaseasasqlmodelPackage.SYBASE_ASA_TABLE__COLUMNS);
 * item.setContext(SQLModelValidationDelegate.getPlainValidationContext());
 * 
 * items.add(item);
 * 
 * // Only primary key (Only check one reference in pk)
 * item = new ContainmentFeatureValidationItem(SybaseasasqlmodelPackage.SYBASE_ASA_TABLE__CONSTRAINTS);
 * Map constraintsContext = SQLModelValidationDelegate.getBasicValidationContext();
 * 
 * Map pkContext = SQLModelValidationDelegate.getBasicValidationContext();
 * List pkItems = new ArrayList();
 * // check the members in pk 
 * pkItems.add(new ValidationItem(SybaseasasqlmodelPackage.SYBASE_ASA_PRIMARY_KEY__MEMBERS));
 * pkContext.put(SQLModelValidationDelegate.VALIDATION_ITEMS_KEY, pkItems);
 * 
 * constraintsContext.put(SybaseASAConstraintValidator.PK_VALIDATION_TYPE, pkContext);
 * item.setContext(constraintsContext);
 * 
 * items.add(item);
 * 
 * context.put(SQLModelValidationDelegate.VALIDATION_ITEMS_KEY, items);
 * context.put(SQLModelValidationDelegate.VALIDATIOR_KEY, new SybaseASATableValidator());
 * 
 * //table is an instance of SybaseASATable
 * DiagnosticChain diagnostics = new BasicDiagnostic();
 * boolean valid = SQLModelValidationDelegate.getInstance().validate(table, diagnostics, context, new HashMap());
 * </pre>
 * 
 * In this example, things are somewhat complicated -- we perform a fine-grained validation against the given table
 * object: check the name of the table, check all the features in the columns of the table (complete validation with
 * depth 1), only check "members" reference in primary key of the table.
 * 
 * @author Idull
 */
public class SQLModelValidationDelegate extends EObjectValidator
{
    /**
     * The items to be validated, it should be specified when the scope is VALIDATION_SCOPE_BASIC_VALUE.
     * <p>
     * The value for this key must be a list of <code>IValidationItem</code>
     * 
     * @see SQLModelValidationDelegate#VALIDATION_SCOPE_BASIC_VALUE
     */
    public static final String                VALIDATION_ITEMS_KEY            = "vitems";

    /**
     * The validation scope
     * <p>
     * expected:
     * 
     * @see SQLModelValidationDelegate#VALIDATION_SCOPE_BASIC_VALUE
     * @see SQLModelValidationDelegate#VALIDATION_SCOPE_COMPLETE_VALUE
     */
    public static final String                VALIDATION_SCOPE_KEY            = "vscope";

    /**
     * The validator, should always be specified to validate the given SQL object
     * <p>
     * The value for this key must be an instance of <code>SQLModelValidator</code>
     */
    public static final String                VALIDATIOR_KEY                  = "validator";

    /**
     * The depth of the validation to be performed, should be specified when the scope is complete. Otherwise the depth
     * 1 will be used.
     * <p>
     * Depth=1, means performing a plain validation, that is, don't validate the features in the containment reference.<br>
     * Depth=2, means validating all the features of the object, also need to validate all feature in the containment
     * reference.<br>
     * the rest may be deduced by analogy...
     */
    public static final String                VALIDATION_DEPTH_KEY            = "vdepth";

    /**
     * The mode of the validation.
     * <p>
     * expected:
     * 
     * @see SQLModelValidationDelegate#VALIDATION_MODE_ONLINE_VALUE
     * @see SQLModelValidationDelegate#VALIDATION_MODE_OFFLINE_VALUE
     */
    public static final String                VALIDATION_MODE_KEY             = "vmode";

    // Constant values for VALIDATION_SCOPE_KEY parameter
    /**
     * If the type is basic type, should also specify the VALIDATION_ITEMS_KEY to be validated.<br>
     * This type means the user customize the validation, he/she is responsible for pointing out which feature needs to
     * be validated and which need not.
     */
    public static final String                VALIDATION_SCOPE_BASIC_VALUE    = "vscopebasic";

    /**
     * If the type is complete type, should also specify the VALIDATION_DEPTH_KEY that the validator need to perform to.<br>
     * This type means the user needs all the features of the given object should be validated, but whether validate the
     * feature of each containment reference or not depends on the VALIDATION_DEPTH_KEY.
     */
    public static final String                VALIDATION_SCOPE_COMPLETE_VALUE = "vscopecomplete";

    // Constant values for VALIDATION_MODE_KEY parameter
    /**
     * Online validation mode. Thing can be different when validating the given object in online mode and offline mode.<br>
     * For example, to validate the name of a table, user may query the database in online mode, but only check the
     * duplication based on SQL Model in offline mode.
     */
    public static final String                VALIDATION_MODE_ONLINE_VALUE    = "vmodeonline";

    /**
     * Offline validation mode.
     */
    public static final String                VALIDATION_MODE_OFFLINE_VALUE   = "vmodeoffline";

    /**
     * Singleton
     */
    private static SQLModelValidationDelegate _instance;

    private SQLModelValidationDelegate()
    {

    }

    /**
     * Returns the instance
     * 
     * @return
     */
    public synchronized static SQLModelValidationDelegate getInstance()
    {
        if (_instance == null)
        {
            _instance = new SQLModelValidationDelegate();
        }
        return _instance;
    }

    public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map context)
    {
        return validate(eObject, diagnostics, context, new HashMap());
    }

    /**
     * Validates the given SQL object, the validator, which extends <code>SQLModelValidator</code>, and features to
     * be validated are stored in the context object. Also, the validation scope is also passed in.
     * <p>
     * This method is not itself a recursive one, but the sub class of <code>SQLModelValidator</code> may invoke this
     * method again to validate its contained SQL objects
     * 
     * @param eObject the SQL object to be validated
     * @param diagnostics the diagnostic chain used to store the validation result
     * @param context the validation context
     * @param sharedParams shared parameters for all the containing features
     */
    public boolean validate(EObject eObject, DiagnosticChain diagnostics, Map context, Map sharedParams)
    {
        boolean isValid = true;
        boolean isBasicScope = true;

        String scope = (String) context.get(VALIDATION_SCOPE_KEY);
        if (scope != null && scope.equals(VALIDATION_SCOPE_COMPLETE_VALUE))
        {
            isBasicScope = false;
        }
        Object v = context.get(VALIDATIOR_KEY);
        if (v == null || !(v instanceof SQLModelValidator))
        {
            //Try to get the validator from the registry
            DatabaseVendorDefinitionId dbVendorId = null;
            DatabaseIdentifier dbIdentifier = (DatabaseIdentifier)sharedParams.get(ValidatorConstants.DATABASE_IDENTIFIER);
            if(dbIdentifier != null)
            {
                dbVendorId = ProfileUtil.getDatabaseVendorDefinitionId(dbIdentifier.getProfileName());
            }
            SQLModelValidatorRegistry registry = EditorCorePlugin.getDefault().getSQLModelValidatorRegistry();
            v = registry.getValidator(eObject.eClass(), dbVendorId);
        }
        if (v == null || !(v instanceof SQLModelValidator))
        {
            return true;
        }

        if (!isBasicScope)
        {
            // perform full validation starting from this object, to do this, reconstruct a new context containing
            // all features and references which need to be vlidated and perform BASIC validation again
            Map basicContext = (Map) ((HashMap) context).clone();
            basicContext.put(VALIDATION_SCOPE_KEY, VALIDATION_SCOPE_BASIC_VALUE);
            basicContext.put(VALIDATIOR_KEY, context.get(VALIDATIOR_KEY));
            List itemsList = (List)basicContext.get(VALIDATION_ITEMS_KEY);
            //always tries to use the original items list whenever possible
            if (itemsList == null)
            {
                itemsList = new ArrayList();
            }

            int depth = 1;
            Integer depthObj = (Integer) context.get(VALIDATION_DEPTH_KEY);
            try
            {
                depth = depthObj.intValue();
            }
            catch (Exception e)
            {

            }
            EClass modelClass = eObject.eClass();
            EList features = modelClass.getEAllStructuralFeatures();
            Iterator iter = features.iterator();
            while (iter.hasNext())
            {
                EStructuralFeature feature = (EStructuralFeature) iter.next();
                IValidationItem item = null;
                if (feature instanceof EAttribute)
                {
                    EAttribute attr = (EAttribute) feature;
                    item = new ValidationItem(attr.getFeatureID());
                    if (!itemsList.contains(item))
                    {
                        itemsList.add(item);
                    }
                }
                else if (feature instanceof EReference)
                {
                    EReference reference = (EReference) feature;

                    // If the depth is greater than 1, should perform validation for features in the containment
                    // feature, otherwise plain validation will be performed
                    if (reference.isContainment() && depth > 1)
                    {
                        item = new ContainmentFeatureValidationItem(feature.getFeatureID(),
                            IValidationItem.REFERENCE_TYPE);

                        // build the context for the containment feature
                        Map subContext = (Map) ((HashMap) context).clone();
                        subContext.put(VALIDATION_SCOPE_KEY, VALIDATION_SCOPE_COMPLETE_VALUE);
                        subContext.put(VALIDATION_DEPTH_KEY, new Integer(depth - 1));
                        ((ContainmentFeatureValidationItem) item).setContext(subContext);

                        if (!itemsList.contains(item))
                        {
                            itemsList.add(item);
                        }

                    }
                    else
                    {
                        // for normal reference (Not a containment), only perform level one validation
                        item = new ValidationItem(feature.getFeatureID(), IValidationItem.REFERENCE_TYPE);
                        if (!itemsList.contains(item))
                        {
                            itemsList.add(item);
                        }
                    }
                }
            }

            basicContext.put(VALIDATION_ITEMS_KEY, itemsList);

            // validate them
            return SQLModelValidationDelegate.this.validate(eObject, diagnostics, basicContext, sharedParams);
        }

        // The validation items, it should be specified if the validation scope is basic
        List items = (List) context.get(VALIDATION_ITEMS_KEY);
        if (items == null)
        {
            return true;
        }

        SQLModelValidator validator = (SQLModelValidator) v;
        Iterator iter = items.iterator();
        while (iter.hasNext())
        {
            Object obj = iter.next();
            if (obj == null || !(obj instanceof IValidationItem))
            {
                continue;
            }
            IValidationItem item = (IValidationItem) obj;

            if (item.getContext() == null)
            {
                // avoid null pointer
                item.setContext(new HashMap());
            }

            switch (item.getType())
            {
                case IValidationItem.ATTRIBUTE_TYPE:
                case IValidationItem.REFERENCE_TYPE:
                    if (item.getFeature(eObject.eClass()) == null)
                    {
                        continue;
                    }
                case IValidationItem.SEMANTIC_TYPE:
                    isValid &= validator.validate(eObject, item, diagnostics, sharedParams);
                    break;
                default:
                    break;
            }
        }
        return isValid;
    }

    /**
     * Utility method to return a context for a complete validation
     * 
     * @param depth
     * @return
     */
    public static Map getCompleteValidationContext(int depth)
    {
        if (depth < 1)
        {
            depth = 1;
        }
        Map context = new HashMap();
        context.put(VALIDATION_SCOPE_KEY, VALIDATION_SCOPE_COMPLETE_VALUE);
        context.put(VALIDATION_DEPTH_KEY, new Integer(depth));
        return context;
    }

    /**
     * Utility method to return a context for a basic validation
     * 
     * @return
     */
    public static Map getBasicValidationContext()
    {
        Map context = new HashMap();
        context.put(VALIDATION_SCOPE_KEY, VALIDATION_SCOPE_BASIC_VALUE);
        context.put(VALIDATION_MODE_KEY, VALIDATION_MODE_ONLINE_VALUE);
        return context;
    }

    /**
     * Utility method to return a context for complete validation with depth 1
     * 
     * @return
     */
    public static Map getPlainValidationContext()
    {
        return getCompleteValidationContext(1);
    }
}
