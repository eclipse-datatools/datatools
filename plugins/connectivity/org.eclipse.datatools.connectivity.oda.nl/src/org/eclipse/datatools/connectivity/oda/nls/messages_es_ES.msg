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
logManager_duplicateName=Ya existe un anotador con el mismo nombre.
#
manifest_nullArgument=El argumento no debe ser nulo.
manifest_NO_DATA_SOURCE_EXTN_ID_DEFINED=En la extensión de origen de datos del archivo plugin.xml del controlador de ODA falta un atributo id.
manifest_INVALID_VERSION_VALUE=El archivo plugin.xml del controlador de ODA tiene un valor de odaVersion no válido ({0}) en la extensión de origen de datos ({1}).
manifest_NO_DRIVER_CLASS_DEFINED=En el archivo plugin.xml del controlador de ODA falta un atributo driverClass en la extensión de origen de datos ({0}).
manifest_INVALID_SET_THREAD_CONTEXT_CLASSLOADER_VALUE=El archivo plugin.xml del controlador de ODA tiene un valor de setThreadContextClassLoader no válido ({0}) en la extensión de origen de datos ({1}).
manifest_NO_DATA_SET_TYPES_DEFINED=La extensión de fuente de datos ODA ({0}) no tiene ningún elemento <dataSet> definido en el manifiesto de extensión. Esta configuración es válida, pero limita las capacidades de extensión de ODA.
manifest_NO_ATTRIBUTE_ID_DEFINED=En la extensión del plug-in del controlador de ODA falta un atributo ({0}) en el elemento ({1}).
manifest_NO_DATA_TYPE_MAPPINGS_DEFINED=El archivo plugin.xml del controlador de ODA debe tener un elemento <dataTypeMapping> como mínimo definido para el <dataSet> ({0}).
manifest_NO_NATIVE_TYPE_CODE_DEFINED=En el archivo plugin.xml del controlador de ODA falta un atributo nativeDataTypeCode en el <dataTypeMapping> ({0}) del <dataSet> ({1}).
manifest_INVALID_NATIVE_TYPE_CODE_VALUE=El archivo plugin.xml del controlador de ODA tiene un valor de nativeDataTypeCode no válido ({0}) en el <dataTypeMapping> ({1}) del <dataSet> ({2}).
manifest_NO_NATIVE_TYPE_NAME_DEFINED=En el archivo plugin.xml del controlador de ODA falta un atributo nativeDataType en el <dataTypeMapping> del <dataSet> ({0}).
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_1=En el archivo plugin.xml del controlador de ODA falta un atributo odaScalarDataType en el <dataTypeMapping> ({0}).
manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_2=En el archivo plugin.xml del controlador de ODA falta un atributo odaScalarDataType en el <alternativeOdaDataType> del <dataTypeMapping> ({0}).
manifest_INVALID_ODA_SCALAR_DATA_TYPE_VALUE=El archivo plugin.xml del controlador de ODA tiene un valor de odaScalarDataType ({0}) en el <dataTypeMapping> ({1}).
manifest_INVALID_LOG_LEVEL_VALUE=El archivo plugin.xml del controlador de ODA tiene un valor de logLevel no válido ({0}) en la extensión de origen de datos ({1}).
manifest_MISSING_ELEMENT_IN_EXTENSION_MANIFEST=Falta un elemento {1} de la extensión ({0}).
manifest_NO_LOG_FILENAME_PREFIX_DEFINED=En el archivo plugin.xml del controlador de ODA falta un atributo logFileNamePrefix en el <traceLogging> de la extensión de origen de datos ({0}).
#
sortSpec_INVALID_SORT_MODE_SPECIFIED=Se ha especificado una modalidad de ordenación no válida\: {0}.
sortSpec_NULL_COLUMN_NAME_SPECIFIED=Se ha especificado un nombre de columna nulo.
sortSpec_INVALID_COLUMN_NAME_SPECIFIED=Se ha especificado un nombre de columna no válido\: {0}.
sortSpec_INVALID_NULL_ORDERING=Valor de orden nulo no válido: {0}.
sortSpec_INVALID_SORT_ORDER_SPECIFIED=Se ha especificado un orden de clasificación no válido\: {0}.
sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE=Este sortSpec con sortModeNone no permite ninguna clave de ordenación dinámica.
sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE=Ya existe una columna de ordenación.  La modalidad sortModeSingleColumn permite una columna de ordenación dinámica como máximo.
sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE=La modalidad sortModeSingleOrder soporta solamente un orden de clasificación.
sortSpec_ONLY_IN_SINGLE_ORDER_MODE=Solo soportado en la modalidad sortModeSingleOrder.
sortSpec_INDEX_OUT_OF_BOUND=Índice: {0}, recuento: {1}.
#
stringSubUtil_NO_STRING_VALUE_TO_REPLACE=No hay ningún valor de serie a sustituir\: {0}.
stringSubUtil_DELIMITER_CANNOT_BE_EMPTY=El delimitador no puede ser una serie vacía ni contener solamente espacios.
stringSubUtil_DELIMITER_CANNOT_BE_NULL=El delimitador no puede ser nulo.
stringSubUtil_SUBSTITUTION_LIST_CANNOT_BE_NULL=La lista de sustitución de serie no puede ser nula.
stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL=El valor de sustitución de serie no puede ser nulo.
stringSubUtil_TEXT_STRING_CANNOT_BE_NULL=La serie de texto no puede ser nula.
stringSubUtil_NAME_VALUE_MAP_CANNOT_BE_NULL=La correlación nombre-valor no puede ser nula.
#
profileProperty_PROFILE_NAME=Nombre del &perfil de conexión
profileProperty_PROFILE_STORE_PATH=&URL de almacenamiento del perfil de conexión
profileProperty_GROUP_PROPERTIES=Referencia de perfil de conexión externo
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
