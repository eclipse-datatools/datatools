package org.eclipse.datatools.enablement.ibm.db2.ddl;

import java.math.BigInteger;

import org.eclipse.datatools.enablement.ibm.db2.model.DB2IdentitySpecifier;
import org.eclipse.datatools.enablement.ibm.ddl.CoreDeltaDdlGenerator;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.Sequence;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.change.FeatureChange;

public abstract class DB2DeltaDdlGenerator extends CoreDeltaDdlGenerator {

	protected DB2DataPreservationDdlBuilder dPDdlBuilder = null;

	protected DB2DataPreservationDdlBuilder getDPDdlBuilder() {
		if (dPDdlBuilder == null) initDPDdlBuilder();
		return dPDdlBuilder;
	}

	protected abstract  void initDPDdlBuilder();

	protected boolean canAlter(PredefinedDataType type) {
		String t2 = type.getName();
		String t1 = (String) getOldValue(EcorePackage.eINSTANCE.getENamedElement_Name(), type);
		int l2 = 0;
		int s2 = 0;
		int l1 = 0;
		int s1 = 0;
		EStructuralFeature f = type.eClass().getEStructuralFeature("length"); //$NON-NLS-1$
		if(f != null) {
			l2 = ((Integer) type.eGet(f)).intValue();
			l1 = ((Integer) getOldValue(f, type)).intValue();
		}
		f = type.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
		if(f != null) {
			l2 = ((Integer) type.eGet(f)).intValue();
			l1 = ((Integer) getOldValue(f, type)).intValue();
		}
		f = type.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
		if(f != null) {
			s2 = ((Integer) type.eGet(f)).intValue();
			s1 = ((Integer) getOldValue(f, type)).intValue();
		}
		
		return canAlter(t1, t2, l1, l2, s1, s2);
	}

	protected boolean canAlter(PredefinedDataType type1, PredefinedDataType type2) {
		String t1 = type1.getName();
		int l1 = 0;
		int s1 = 0;
		EStructuralFeature f = type1.eClass().getEStructuralFeature("length"); //$NON-NLS-1$
		if(f != null) {
			l1 = ((Integer) type1.eGet(f)).intValue();
		}
		f = type1.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
		if(f != null) {
			l1 = ((Integer) type1.eGet(f)).intValue();
		}
		f = type1.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
		if(f != null) {
			s1 = ((Integer) type1.eGet(f)).intValue();
		}

		String t2 = type2.getName();
		int l2 = 0;
		int s2 = 0;
		f = type2.eClass().getEStructuralFeature("length"); //$NON-NLS-1$
		if(f != null) {
			l2 = ((Integer) type2.eGet(f)).intValue();
		}
		f = type2.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
		if(f != null) {
			l2 = ((Integer) type2.eGet(f)).intValue();
		}
		f = type2.eClass().getEStructuralFeature("scale"); //$NON-NLS-1$
		if(f != null) {
			s2 = ((Integer) type2.eGet(f)).intValue();
		}

		return canAlter(t1, t2, l1, l2, s1, s2);
	}

	protected boolean canAlter(Sequence changed, EStructuralFeature feature, FeatureChange setting) {
		Object changedObj = changed.eGet(feature);
		if(changedObj instanceof DB2IdentitySpecifier) {
			DB2IdentitySpecifier is = (DB2IdentitySpecifier)changedObj;
			EStructuralFeature f = is.eClass().getEStructuralFeature(SQLSchemaPackage.eINSTANCE.IDENTITY_SPECIFIER__START_VALUE);
			BigInteger startValue = (BigInteger)is.eGet(f);
			DB2IdentitySpecifier changeToValue = (DB2IdentitySpecifier)setting.getValue();
			BigInteger changedToStartValue = (BigInteger)changeToValue.eGet(f);
			if(startValue.intValue() != changedToStartValue.intValue()) {
				return false;
			}
		}
		return true;
	}
	
	abstract protected boolean canAlter(String type1, String type2, int length1, int length2, int scale1, int scale2);
}
