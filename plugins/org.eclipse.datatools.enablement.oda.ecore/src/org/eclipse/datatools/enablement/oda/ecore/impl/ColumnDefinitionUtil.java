/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.impl;

import static org.eclipse.emf.ecore.util.EcoreUtil.getObjectsByType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.DataElementAttributes;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

public class ColumnDefinitionUtil {

	private static String COLUMN_NAME_DELIMITER = "::";

	public static ColumnDefinition createFor(final EStructuralFeature feature) {
		final ColumnDefinition definition = DesignFactory.eINSTANCE.createColumnDefinition();
		final DataElementAttributes attributes = DesignFactory.eINSTANCE.createDataElementAttributes();
		attributes.setName(getColumnNameFor(feature));
		attributes.setUiDisplayName(feature.getName());
		definition.setAttributes(attributes);
		return definition;
	}

	public static EStructuralFeature featureForColumn(final EObject eObject, final ColumnDefinition columnDefinition) {
		final EClass eClass = eObject.eClass();
		final List<EClass> eClassHierarchy = new ArrayList<EClass>();
		eClassHierarchy.add(eClass);
		eClassHierarchy.addAll(eClass.getEAllSuperTypes());
		final String columnName = columnDefinition.getAttributes().getName();
		final String[] decomposition = columnName.split(ColumnDefinitionUtil.COLUMN_NAME_DELIMITER);
		final String eClassName = decomposition[0];
		final String featureName = decomposition[1];
		for (final EClass type : eClassHierarchy) {
			if (type.getName().equals(eClassName)) {
				return eClass.getEStructuralFeature(featureName);
			}
		}
		return null;
	}

	public static String getColumnNameFor(final EStructuralFeature feature) {
		return feature.getEContainingClass().getName() + COLUMN_NAME_DELIMITER + feature.getName();
	}

	public static List<EStructuralFeature> getAllEStructuralFeatures(final Collection<EObject> eObjects) {
		final List<EStructuralFeature> structuralFeatures = new ArrayList<EStructuralFeature>();
		final Set<EPackage> ePackages = new HashSet<EPackage>();
		for (final EObject eObject : eObjects) {
			final EPackage ePackage = eObject.eClass().getEPackage();
			if (ePackages.contains(ePackage)) {
				continue;
			}
			structuralFeatures.addAll(getAllEStructuralFeatures(ePackage));
			ePackages.add(ePackage);
		}
		return structuralFeatures;
	}

	public static List<EStructuralFeature> getAllEStructuralFeatures(final EPackage ePackage) {
		final List<EStructuralFeature> allEStructuralFeatures = new ArrayList<EStructuralFeature>();
		final Collection<EClass> eClasses = getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.ECLASS);
		for (final EClass eClass : eClasses) {
			allEStructuralFeatures.addAll(eClass.getEStructuralFeatures());
		}
		return allEStructuralFeatures;
	}
}
