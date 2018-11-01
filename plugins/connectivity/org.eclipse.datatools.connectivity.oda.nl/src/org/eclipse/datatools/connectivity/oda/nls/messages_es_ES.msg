#
#************************************************************************
# Copyright (c) 2004, 2013 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0/
#
# Contributors:
#  Actuate Corporation - initial API and implementation
#  
#************************************************************************
#
logManager_duplicateName=Ya existe un anotador con el mismo nombre.
#
manifest_CANNOT_FIND_EXTENSION=No se puede encontrar la extensión ({0}) de fuente de datos de ODA.\n Consulte el archivo de registro del área de trabajo en caso de producirse problemas con la carga del paquete de extensión y sus dependencias.
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
manifest_UNKNOWN_BUNDLE_LOCATION=No se puede localizar la ruta de instalación de la extensión ODA ({0}).  La aplicación del consumidor ODA debe especificar un ResourceIdentifiers en appContext para resolver la ruta.
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
querySpec_CUSTOM_AGGR_EXCEED_MAX_ARGS=The custom aggregate expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_AGGR_INCOMPATIBLE_DUPL_CHECK=El tipo de total personalizado ({0}) no puede omitir valores duplicados.
querySpec_CUSTOM_AGGR_INCOMPATIBLE_NULL_CHECK=El tipo de total personalizado ({0}) no puede omitir valores nulos.
querySpec_CUSTOM_AGGR_LESS_THAN_MIN_ARGS=La expresión de total personalizada ({0}) tiene {1} argumentos. Sin embargo, el número mínimo de argumentos necesario es {2}.
querySpec_CUSTOM_FILTER_EXCEED_MAX_ARGS=The custom filter expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_FILTER_LESS_THAN_MIN_ARGS=La expresión de filtro personalizada ({0}) tiene {1} argumentos. Sin embargo, el número mínimo de argumentos necesario es {2}.
querySpec_CUSTOM_FUNC_EXCEED_MAX_ARGS=The custom function value expression ({0}) has {1} arguments, which exceeds the allowed maximum of {2} arguments.
querySpec_CUSTOM_FUNC_INCOMPATIBLE_DUPL_CHECK=The custom function type ({0}) cannot ignore duplicate values.
querySpec_CUSTOM_FUNC_LESS_THAN_MIN_ARGS=The custom function value expression ({0}) has {1} arguments, but requires a minimum of {2} arguments.
querySpec_EXTENSION_ID_NOT_FOUND=No se ha encontrado la extensión {0} con el ID ({1}).
querySpec_ILLEGAL_CONSTRUCTOR_ARG=Illegal {0} constructor argument
querySpec_INCOMPLETE_COMBINED_QUERY=Especificación de consulta combinada incompleta.
querySpec_INVALID_AGGR_EXPR=Expresión de total no válida.
querySpec_INVALID_AGGR_HIDE_COLUMN=Proyección de agregación no válida en una columna oculta {0}.
querySpec_INVALID_ARG=Argumento no válido: {0}.
querySpec_INVALID_BASE_QUERY=Consulta de base no válida.
querySpec_INVALID_CLASS_TYPE_ATTRIBUTE=Tipo de clase no válido: ({0}).  El atributo {1} debe ser una instancia de {2}.
querySpec_INVALID_COLUMN_IDENTIFIER=Invalid column reference {0}.
querySpec_INVALID_EXT_POINT_ATTR_VALUE=La extensión de {0} ({1}) contiene un valor no válido ({2}) para el atributo {3}.
querySpec_INVALID_EXT_POINT_ELEMENT=La extensión de {0} ({1}) no es válida.  Consulte la definición del esquema para obtener información acerca del contenido necesario.
querySpec_INVALID_FILTER_EXPR=Expresión de filtro no válida.
querySpec_INVALID_RESULT_PROJ=Proyección de resultado no válida.
querySpec_INVALID_SORT_KEY=Clave de ordenación no válida.
querySpec_INVALID_SORT_SPEC=Especificación de ordenación no válida.
querySpec_INVALID_VALUE_EXPR=Invalid value expression.
querySpec_MAX_ONE_NEGATING_EXPR=Sólo se puede añadir una expresión negativa a {0}.
querySpec_MISSING_ATOMIC_QUERY_TEXT=Falta el texto de consulta en la consulta de base atómica.
querySpec_MISSING_COMBINED_MATCHING_EXPR=Falta la expresión de coincidencia en la condición combinada.
querySpec_MISSING_COMPOSITE_MIN_CHILDREN=La expresión compuesta {0} debe contener al menos {1} expresiones secundarias.
querySpec_MISSING_EXPR_VARIABLE=Falta una expresión variable asociada a la expresión de filtro ({0}).
querySpec_MISSING_EXT_POINT_ATTR_VALUE=Falta el valor de atributo {2} del elemento {3} en la extensión de {0} ({1}).
querySpec_MISSING_EXT_POINT_ELEMENT=Falta el elemento {2} en la extensión de {0} ({1}).
querySpec_MISSING_EXT_POINT_MIN_ELEMENT=La extensión de {0} ({1}) tener al menos un elemento {2} definido.
querySpec_NON_DEFINED_COMBINED_OP=No se encuentra el operador de expresión de combinación de tipo ({0}) en el registro de extensión dynamicResultSet.
querySpec_NON_DEFINED_CUSTOM_AGGR=No se encuentra la expresión de total personalizada ({0} en el registro de extensión dynamicResultSet.
querySpec_NON_DEFINED_CUSTOM_FILTER=No se encuentra la expresión de filtro personalizada ({0}) en el registro de extensión dynamicResultSet.
querySpec_NON_DEFINED_CUSTOM_FUNC=The custom function ({0}) is not found in the dynamicResultSet extension registry.
querySpec_NON_RELATED_QUERYSPEC=La columna {0} de una expresión de consulta combinada no puede estar cualificada por una consulta que no sea una de las consultas combinadas.
querySpec_NONSUPPORTED_NULL_ORDERING=El proveedor de fuente de datos no admite la especificación de orden nula.
querySpec_NONSUPPORTED_VAR_DATA_TYPE=The ODA data type ({0}) of the value expression ({1}) is not supported by the type of custom expression used.
querySpec_NOT_EXPR_INCOMPATIBLE=Uso no válido de una expresión que no puede ser negada ({0}) en {1}.
querySpec_NOT_EXPR_MISSING_CHILD=Falta una expresión de negación en NotExpression: ({0}).
querySpec_NULL_CONSTRUCTOR_3ARGS=Null {0} constructor argument(s): {1}, {2}, {3}.
querySpec_NULL_CONSTRUCTOR_ARG=Null {0} constructor argument
querySpec_UNABLE_ADD_TO_NON_LIST_COLLECTION=No se puede añadir un valor a los ya existentes.  El valor existente no es un valor java.util.List.
querySpec_UNEXPECTED_AGGR_EXPR_EXTENSION=No es posible admitir la expresión de total personalizada ({0}) de otra extensión.
querySpec_UNEXPECTED_AGGR_EXPR_TYPE=La expresión de total personalizada ({0} no es una instancia {1}. La aplicación del consumidor debe utilizar ExpressionFactory\#createCustomAggregate para crear una instancia de expresión personalizada.
querySpec_UNEXPECTED_CUSTOM_EXPR_EXTENSION=No es posible admitir la expresión de filtro personalizada ({0}) de otra extensión.
querySpec_UNEXPECTED_CUSTOM_EXPR_TYPE=La expresión de filtro personalizada ({0}) no es una instancia {1}.\n La aplicación del consumidor debe utilizar ExpressionFactory\#createCustomExpression para crear una instancia de expresión de filtro personalizada.
querySpec_UNEXPECTED_EXPR_VARIABLE_TYPE=No se puede admitir este tipo de ExpressionVariable ({1}).
querySpec_UNEXPECTED_FUNC_EXPR_EXTENSION=No es posible admitir la función personalizada ({0}) de otra extensión.
querySpec_UNEXPECTED_FUNC_EXPR_TYPE=La función personalizada ({0} no es una instancia {1}. La aplicación del consumidor debe utilizar ExpressionFactory\#createCustomFunction para crear una instancia de función personalizada.
