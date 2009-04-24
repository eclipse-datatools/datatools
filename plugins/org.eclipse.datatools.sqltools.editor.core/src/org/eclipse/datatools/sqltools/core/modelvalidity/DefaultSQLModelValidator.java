package org.eclipse.datatools.sqltools.core.modelvalidity;

import java.util.Map;

import org.eclipse.datatools.sqltools.sql.identifier.IIdentifierValidator;
import org.eclipse.datatools.sqltools.sql.identifier.ValidatorMessage;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;

/**
 * The default SQL model validator
 * 
 * @author Idull
 */
public class DefaultSQLModelValidator extends SQLModelValidator
{
    protected ContainmentFeatureValidationItem _containmentItem;
    protected int                              _depth;

    /**
     * Context for the current validation item
     */
    protected Map                              _currentItemContext;

    public boolean validate(EObject eObject, IValidationItem item, DiagnosticChain diagnostics, Map sharedParams)
    {
        _currentItemContext = item.getContext();

        // If it's a containment item
        _containmentItem = null;
        if (item instanceof ContainmentFeatureValidationItem)
        {
            _containmentItem = (ContainmentFeatureValidationItem) item;
            _depth = _containmentItem.getDepth();
        }

        boolean isValid = true;
        int featureId = -1;

        switch (item.getType())
        {
            case IValidationItem.ATTRIBUTE_TYPE:
                featureId = item.getIdentityId();
                isValid &= validateAttribute(eObject, featureId, diagnostics, sharedParams);
                break;
            case IValidationItem.REFERENCE_TYPE:
                featureId = item.getIdentityId();
                isValid &= validateReference(eObject, featureId, diagnostics, sharedParams);
                break;
            case IValidationItem.SEMANTIC_TYPE:
                isValid &= validateSemantic(eObject, featureId, diagnostics, sharedParams);
            default:
                break;
        }

        return isValid;
    }

    /**
     * Sub class should override this method to validate the given attribute
     * 
     * @param eObject the given SQL object
     * @param featureId id of the attribute
     * @param diagnostics the diagnostic chain
     * @param sharedParams
     * @return
     */
    protected boolean validateAttribute(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        return true;
    }

    /**
     * Sub class should override this method to validate the given reference
     * 
     * @param eObject the given SQL object
     * @param featureId id of the attribute
     * @param diagnostics the diagnostic chain
     * @param sharedParams
     * @return
     */
    protected boolean validateReference(EObject eObject, int featureId, DiagnosticChain diagnostics, Map sharedParams)
    {
        return true;
    }

    /**
     * Sub class should override this method to validate the given semantic item
     * 
     * @param eObject the given SQL object
     * @param semanticId id of the semantic item
     * @param diagnostics the diagnostic chain
     * @param sharedParams
     * @return
     */
    protected boolean validateSemantic(EObject eObject, int semanticId, DiagnosticChain diagnostics, Map sharedParams)
    {
        return true;
    }
    
    protected int getDiagnosticCode(int idValidatorCode)
    {
        if (idValidatorCode == ValidatorMessage.ERROR)
        {
            return Diagnostic.ERROR;
        }
        else if (idValidatorCode == ValidatorMessage.WARNING)
        {
            return Diagnostic.WARNING;
        }
        return Diagnostic.INFO;
    }
}
