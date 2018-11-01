/*******************************************************************************
 * Copyright (c) 2007, 2010 SolutionsIQ, Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *   julien.repond - support for multiple EPackages (BZ 132958#c24)
 *   
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.enablement.oda.ecore.util.EPackageUtil;
import org.eclipse.datatools.enablement.oda.ecore.util.StringUtil;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

public class ColumnDefinitionUtil {

	private static String COLUMN_NAME_DELIMITER = "::"; //$NON-NLS-1$

	/**
	 * FIXME: the native data type code should be determined from the EAttributeType or the EReference
	 */
	public static ColumnDefinition createFor(final String columnName) {
		final ColumnDefinition definition = DesignFactory.eINSTANCE.createColumnDefinition();
		final DataElementAttributes attributes = DesignFactory.eINSTANCE.createDataElementAttributes();
		attributes.setName(columnName);
		attributes.setNativeDataTypeCode(DataTypes.DEFAULT_TYPE_CODE);
		attributes.setUiDisplayName(columnName);
		definition.setAttributes(attributes);
		return definition;
	}

	public static ColumnDefinition createFor(final ENamedElement[] featurePath) {
		return createFor(getColumnNameFor(featurePath));
	}

	public static EStructuralFeature[] featuresForColumn(final EObject eObject, final ColumnDefinition columnDefinition) {
		final Collection<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
		final String columnName = columnDefinition.getAttributes().getName();
		final String[] decomposition = columnName.split(ColumnDefinitionUtil.COLUMN_NAME_DELIMITER);
		final String eClassName = decomposition[0];
		EClass eClass = findEClass(eObject, eClassName);
		for (int i = 1; i < decomposition.length; i++) {
			final EStructuralFeature structuralFeature = eClass.getEStructuralFeature(decomposition[i]);
			if (structuralFeature == null) {
				return new EStructuralFeature[] {};
			}
			features.add(structuralFeature);
			if (structuralFeature.getEType() instanceof EClass) {
				eClass = (EClass) structuralFeature.getEType();
			}
		}
		return features.toArray(new EStructuralFeature[features.size()]);
	}

	private static EClass findEClass(final EObject eObject, final String eClassName) {
		final EClass eClass = eObject.eClass();
		final List<EClass> eClassHierarchy = new ArrayList<EClass>();
		eClassHierarchy.add(eClass);
		eClassHierarchy.addAll(eClass.getEAllSuperTypes());
		for (final EClass type : eClassHierarchy) {
			if (type.getName().equals(eClassName)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unable to locate");
	}

	private static String getColumnNameFor(final ENamedElement[] featurePath) {
		final Collection<String> names = new ArrayList<String>();
		for (final ENamedElement element : featurePath) {
			names.add(element.getName());
		}
		return StringUtil.join(names.toArray(new String[names.size()]), COLUMN_NAME_DELIMITER);
	}

	public static ENamedElement[] getFeaturePath(final ColumnDefinition columnDefinition, final Set<EPackage> ePackages) {
		final List<ENamedElement> path = new ArrayList<ENamedElement>();
		final String columnName = columnDefinition.getAttributes().getName();
		final String[] namePath = columnName.split(COLUMN_NAME_DELIMITER);
		EPackage ePackage = EPackageUtil.getPackageForClassifier(namePath[0], ePackages);
		if (ePackage != null) {
			final EClass rootEClass = (EClass) ePackage.getEClassifier(namePath[0]);
			path.add(rootEClass);
			EStructuralFeature nextFeature = rootEClass.getEStructuralFeature(namePath[1]);
			path.add(rootEClass.getEStructuralFeature(namePath[1]));
			for (int i = 2; i < namePath.length; i++) {
				final String next = namePath[i];
				final EStructuralFeature nextStructuralFeature = ((EClass) nextFeature.getEType()).getEStructuralFeature(next);
				path.add(nextStructuralFeature);
				nextFeature = nextStructuralFeature;
			}
		}
		return path.toArray(new ENamedElement[path.size()]);
	}
}
