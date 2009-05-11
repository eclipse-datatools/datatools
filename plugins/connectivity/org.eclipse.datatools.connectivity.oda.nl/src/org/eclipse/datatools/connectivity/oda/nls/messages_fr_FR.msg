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
logManager_duplicateName=Un consignateur du même nom existe déjà.
#
manifest_nullArgument=L'argument ne doit pas être null.
manifest_NO_DATA_SOURCE_EXTN_ID_DEFINED=L'extension de la source de données plugin.xml du pilote ODA ne contient pas d'attribut d'ID.
manifest_INVALID_VERSION_VALUE=Le fichier plugin.xml du pilote ODA contient une valeur odaVersion incorrecte ({0}) dans l''extension de la source de données ({1}).
manifest_NO_DRIVER_CLASS_DEFINED=Le fichier plugin.xml du pilote ODA ne contient pas d''attribut driverClass dans l''extension de la source de données ({0}).
manifest_INVALID_SET_THREAD_CONTEXT_CLASSLOADER_VALUE=Le fichier plugin.xml du pilote ODA contient une valeur setThreadContextClassLoader incorrecte ({0}) dans l''extension de la source de données ({1}).
manifest_NO_DATA_SET_TYPES_DEFINED=Aucun élément <dataSet> n''est défini pour l''extension de la source de données ODA ({0}) dans le manifeste d''extension.  Cette configuration est valide, mais elle limite les capacités de l''extension ODA.
manifest_NO_ATTRIBUTE_ID_DEFINED=L''extension du plug-in du pilote ODA ne contient pas d''attribut ({0}) dans l''élément ({1}).
manifest_NO_DATA_TYPE_MAPPINGS_DEFINED=Le fichier plugin.xml du pilote ODA doit avoir au moins un élément <dataTypeMapping> défini pour l''élément <dataSet> ({0}).
manifest_NO_NATIVE_TYPE_CODE_DEFINED=Le fichier plugin.xml du pilote ODA ne contient pas d''attribut nativeDataTypeCode dans l''élément <dataTypeMapping> ({0}) du fichier <dataSet> ({1}).
manifest_INVALID_NATIVE_TYPE_CODE_VALUE=Le fichier plugin.xml du pilote ODA contient une valeur nativeDataTypeCode incorrecte ({0}) dans l''élément <dataTypeMapping> ({1}) de <dataSet> ({2}).
manifest_NO_NATIVE_TYPE_NAME_DEFINED=Le fichier plugin.xml du pilote ODA ne contient pas d''attribut nativeDataType dans l''élément <dataTypeMapping> de <dataSet> ({0}).
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_1=Le fichier plugin.xml du pilote ODA ne contient pas d''attribut odaScalarDataType dans l''élément <dataTypeMapping> ({0}).
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_2=Le fichier plugin.xml du pilote ODA ne contient pas d''attribut odaScalarDataType dans l''élément <alternativeOdaDataType> de <dataTypeMapping> ({0}).
manifest_INVALID_ODA_SCALAR_DATA_TYPE_VALUE=Le fichier plugin.xml du pilote ODA contient une valeur incorrecte odaScalarDataType ({0}) dans l''élément <dataTypeMapping> ({1}).
manifest_INVALID_LOG_LEVEL_VALUE=Le fichier plugin.xml du pilote ODA contient une valeur logLevel incorrecte ({0}) dans l''extension de la source de données ({1}).
manifest_MISSING_ELEMENT_IN_EXTENSION_MANIFEST=Un élément {1} manque pour l'extension ({0}).
manifest_NO_LOG_FILENAME_PREFIX_DEFINED=Le fichier plugin.xml du pilote ODA ne contient pas d''attribut logFileNamePrefix dans l''élément <traceLogging> de l''extension de la source de données ({0}).
#
sortSpec_INVALID_SORT_MODE_SPECIFIED=Mode de tri spécifié incorrect \: {0}.
sortSpec_NULL_COLUMN_NAME_SPECIFIED=Nom de colonne null spécifié.
sortSpec_INVALID_COLUMN_NAME_SPECIFIED=Nom de colonne spécifié incorrect \: {0}.
sortSpec_INVALID_NULL_ORDERING=Valeur de commande nulle incorrecte : {0}.
sortSpec_INVALID_SORT_ORDER_SPECIFIED=Ordre de tri spécifié incorrect \: {0}.
sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE=Cette spécification de tri avec sortModeNone n'autorise aucune clé de tri dynamique.
sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE=Une colonne de tri existe déjà.  Le mode sortModeSingleColumn autorise au maximum une colonne de tri dynamique.
sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE=Le mode sortModeSingleOrder prend en charge uniquement un ordre de tri.
sortSpec_ONLY_IN_SINGLE_ORDER_MODE=Pris en charge uniquement en mode sortModeSingleOrder.
sortSpec_INDEX_OUT_OF_BOUND=Index : {0}, nombre : {1}.
#
stringSubUtil_NO_STRING_VALUE_TO_REPLACE=Aucune valeur de chaîne à remplacer \: {0}.
stringSubUtil_DELIMITER_CANNOT_BE_EMPTY=Le délimiteur ne peut pas être une chaîne vide ou contenir uniquement des espaces.
stringSubUtil_DELIMITER_CANNOT_BE_NULL=Le délimiteur ne peut pas être null.
stringSubUtil_SUBSTITUTION_LIST_CANNOT_BE_NULL=La liste de substitution des chaînes ne peut pas être null.
stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL=La valeur de substitution des chaînes ne peut pas être null.
stringSubUtil_TEXT_STRING_CANNOT_BE_NULL=La chaîne de texte ne peut pas être null.
stringSubUtil_NAME_VALUE_MAP_CANNOT_BE_NULL=La mappe nom-valeur ne peut pas être null.
#
profileProperty_PROFILE_NAME=Nom de &profil de connexion
profileProperty_PROFILE_STORE_PATH=&URL de l'emplacement de stockage des profils de connexion
profileProperty_GROUP_PROPERTIES=Référence du profil de connexion externe
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
