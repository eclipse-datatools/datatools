#
#************************************************************************
# Copyright (c) 2004, 2009 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#  Actuate Corporation - initial API and implementation
#  
#************************************************************************
#
logManager_duplicateName=Protokollfunktion mit dem gleichen Namen ist bereits vorhanden.
#
manifest_nullArgument=Das Argument darf nicht Null sein.
manifest_NO_DATA_SOURCE_EXTN_ID_DEFINED=In der Datenquellenerweiterung der Datei 'plugin.xml' des ODA-Treibers fehlt ein Attribut 'id'.
manifest_INVALID_VERSION_VALUE=Die Datei ''plugin.xml'' des ODA-Treibers enthält einen ungültigen Wert für odaVersion ({0}) in der Datenquellenerweiterung ({1}).
manifest_NO_DRIVER_CLASS_DEFINED=In der Datei ''plugin.xml'' des ODA-Treibers fehlt ein Attribut ''driverClass'' in der Datenquellenerweiterung ({0}).
manifest_INVALID_SET_THREAD_CONTEXT_CLASSLOADER_VALUE=Die Datei ''plugin.xml'' des ODA-Treibers enthält einen ungültigen Wert für setThreadContextClassLoader ({0}) in der Datenquellenerweiterung ({1}).
manifest_NO_DATA_SET_TYPES_DEFINED=Für die ODA-Datenquellen-Erweiterung ({0}) ist kein <dataSet>-Element in der Erweiterung definiert. Dies ist eine gültige Konfiguration, begrenzt jedoch die ODA-Erweiterungsfähigkeiten.
manifest_NO_ATTRIBUTE_ID_DEFINED=In der Plug-in-Erweiterung des ODA-Treibers fehlt ein Attribut ({0}) im Element ({1}).
manifest_NO_DATA_TYPE_MAPPINGS_DEFINED=In der Datei ''plugin.xml'' des ODA-Treibers sollte mindestens ein Element <dataTypeMapping> für das Element <dataSet> ({0}) definiert sein.
manifest_NO_NATIVE_TYPE_CODE_DEFINED=In der Datei ''plugin.xml'' des ODA-Treibers fehlt ein Attribut ''nativeDataTypeCode'' im Element <dataTypeMapping> ({0}) des Elements <dataSet> ({1}).
manifest_INVALID_NATIVE_TYPE_CODE_VALUE=Die Datei ''plugin.xml'' des ODA-Treibers enthält einen ungültigen Wert für nativeDataTypeCode ({0}) im Element <dataTypeMapping> ({1}) des Elements <dataSet> ({2}).
manifest_NO_NATIVE_TYPE_NAME_DEFINED=In der Datei ''plugin.xml'' des ODA-Treibers fehlt ein Attribut ''nativeDataType'' im Element <dataTypeMapping> des Elements <dataSet> ({0}).
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_1=In der Datei ''plugin.xml'' des ODA-Treibers fehlt ein Attribut ''odaScalarDataType'' im Element <dataTypeMapping> ({0}).
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_2=In der Datei ''plugin.xml'' des ODA-Treibers fehlt ein Attribut ''odaScalarDataType'' im Element <alternativeOdaDataType> des Elements <dataTypeMapping> ({0}).
manifest_INVALID_ODA_SCALAR_DATA_TYPE_VALUE=Die Datei ''plugin.xml'' des ODA-Treibers enthält einen ungültigen Wert für odaScalarDataType ({0}) im Element <dataTypeMapping> ({1}).
manifest_INVALID_LOG_LEVEL_VALUE=Die Datei ''plugin.xml'' des ODA-Treibers enthält einen ungültigen Wert für logLevel ({0}) in der Datenquellenerweiterung ({1}).
manifest_MISSING_ELEMENT_IN_EXTENSION_MANIFEST=Bei der Erweiterung ({0}) fehlt ein {1}-Element.
manifest_NO_LOG_FILENAME_PREFIX_DEFINED=In der Datei ''plugin.xml'' des ODA-Treibers fehlt ein Attribut ''logFileNamePrefix'' im Element <traceLogging> der Datenquellenerweiterung ({0}).
#
sortSpec_INVALID_SORT_MODE_SPECIFIED=Es wurde ein ungültiger Sortiermodus angegeben\: {0}.
sortSpec_NULL_COLUMN_NAME_SPECIFIED=Es wurde ein Nullspaltenname angegeben.
sortSpec_INVALID_COLUMN_NAME_SPECIFIED=Es wurde ein ungültiger Spaltenname angegeben\: {0}.
sortSpec_INVALID_NULL_ORDERING=Ungültiger Null-Reihenfolgewert: {0}.
sortSpec_INVALID_SORT_ORDER_SPECIFIED=Es wurde eine ungültige Sortierreihenfolge angegeben\: {0}.
sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE=Dieser Wert für 'sortSpec' mit 'sortModeNone' lässt keine dynamischen Sortierschlüssel zu.
sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE=Es ist bereits eine Sortierspalte vorhanden.  Der Modus 'sortModeSingleColumn' lässt maximal eine dynamische Sortierspalte zu.
sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE=Der Modus 'sortModeSingleOrder' unterstützt nur eine Sortierreihenfolge.
sortSpec_ONLY_IN_SINGLE_ORDER_MODE=Wird nur im Modus 'sortModeSingleOrder' unterstützt.
sortSpec_INDEX_OUT_OF_BOUND=Index: {0}, Anzahl: {1}.
#
stringSubUtil_NO_STRING_VALUE_TO_REPLACE=Kein Zeichenfolgewert für Ersetzung\: {0}.
stringSubUtil_DELIMITER_CANNOT_BE_EMPTY=Der Begrenzer darf keine leere Zeichenfolge sein oder ausschließlich Leerzeichen enthalten.
stringSubUtil_DELIMITER_CANNOT_BE_NULL=Der Begrenzer darf nicht Null sein.
stringSubUtil_SUBSTITUTION_LIST_CANNOT_BE_NULL=Die Zeichenfolgensubstitutionsliste darf nicht Null sein.
stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL=Der Zeichenfolgensubstitutionswert darf nicht Null sein.
stringSubUtil_TEXT_STRING_CANNOT_BE_NULL=Die Textzeichenfolge darf nicht Null sein.
stringSubUtil_NAME_VALUE_MAP_CANNOT_BE_NULL=Die Zuordnung Name-Wert darf nicht Null sein.
#
profileProperty_PROFILE_NAME=Name des Verbindungs&profils
profileProperty_PROFILE_STORE_PATH=&URL von Speicher für Verbindungsprofil
profileProperty_GROUP_PROPERTIES=Referenz für externes Verbindungsprofil
#
querySpec_CUSTOM_AGGR_INCOMPATIBLE_DUPL_CHECK=The custom aggregate type ({0}) cannot ignore duplicate values.
querySpec_CUSTOM_AGGR_INCOMPATIBLE_NULL_CHECK=The custom aggregate type ({0}) cannot ignore null values.
querySpec_CUSTOM_FILTER_EXCEED_MAX_ARGS=The custom filter expression ({0}) has {1} arguments, but allows a maximum of {2} arguments.
querySpec_CUSTOM_FILTER_MISSING_MIN_ARGS=The custom filter expression ({0}) has {1} arguments, but requires a minimum of {2} arguments.
querySpec_EXTENSION_ID_NOT_FOUND=No {0} extension found with the id ({1}).
querySpec_INVALID_ARG=Invalid argument: {0}.
querySpec_INVALID_CLASS_TYPE_ATTRIBUTE=Invalid class type ({0}).  The {1} attribute must be an instance of {2}.
querySpec_INVALID_EXT_POINT_ATTR_VALUE=The {0} extension ({1}) has an invalid value ({2}) for the {3} attribute.
querySpec_INVALID_EXT_POINT_ELEMENT=The {0} extension ({1}) is invalid.  See the schema definition for required content.
querySpec_MISSING_COMPOSITE_MIN_CHILDREN=The composite {0} must contain at least {1} child expressions.
querySpec_MISSING_EXT_POINT_ATTR_VALUE=The {0} extension ({1}) is missing the {2} attribute value in the {3} element.
querySpec_MISSING_EXT_POINT_ELEMENT=The {0} extension ({1}) is missing the {2} element.
querySpec_MISSING_EXT_POINT_MIN_ELEMENT=The {0} extension ({1}) must have at least one {2} element defined .
querySpec_NON_DEFINED_CUSTOM_AGGR=The custom aggregate expression ({0}.{1}) is not found in the dynamicResultSet extension registry.
querySpec_NON_DEFINED_CUSTOM_FILTER=The custom filter expression ({0}.{1}) is not found in the dynamicResultSet extension registry.
querySpec_NOT_EXPR_INCOMPATIBLE=Invalid use of a non-negatable expression ({0}) in a {1}.
querySpec_NOT_EXPR_MISSING_CHILD=Missing negating expression in NotExpression: ({0}).
querySpec_UNABLE_ADD_TO_NON_LIST_COLLECTION=Unable to add a value to existing values.  The existing value is not a java.util.List.
