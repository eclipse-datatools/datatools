###############################################################################
# Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#     IBM Corporation - initial API and implementation
###############################################################################
##
###
##  !!! This property file should only contain resources that are "translatable" !!!
###
##

DATATOOLS.CORE.UI.TEST = Test

#
## Add Action
#
DATATOOLS.CORE.UI.NEW.COLUMN_NAME = Columna 
DATATOOLS.CORE.UI.NEW.SCHEMA_NAME = Esquema
DATATOOLS.CORE.UI.NEW.TABLE_NAME = Tabla
DATATOOLS.CORE.UI.NEW.TRIGGER_NAME = Activador
DATATOOLS.CORE.UI.NEW.DOMAIN_NAME = Dominio
DATATOOLS.CORE.UI.NEW.SEQUENCE_NAME = Secuencia
DATATOOLS.CORE.UI.NEW.STORED_PROCEDURE_NAME = Stored_Procedure
DATATOOLS.CORE.UI.NEW.DATABASE = Nueva base de datos

#
## Label Decorations
#
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.DECORATION = Ajustes de decorador de datos:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.TAB = Texto
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.FORMAT = Especificar el formato de visualización de columnas en el explorador:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.CFORMAT = Formato de &columnas:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.VARIABLE = Añadir &variables...
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.EXAMPLE = &Ejemplo:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.TITLE = Añadir variables
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.MESSAGE = Seleccionar las variables para añadir al formato de decoración:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.DATATYPE_DESC = {tipo de datos}: el tipo de datos de la columna
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.NULLABLE_DESC = {nulo}: indica que la columna es nula
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.PK_DESC = {clavePrimaria}: indica que la columna pertenece a la restricción de clave primaria
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.FK_DESC = {claveExterna}: indica que la columna pertenece a la restricción de clave externa

DATATOOLS.CORE.UI.STATUS.CONNECTION = {0} - Conexión "{1}"
DATATOOLS.CORE.UI.STATUS.PROJECT = {0} - Proyecto "{1}"

#
## Model Explorer Strings
#
DATATOOLS.CORE.UI.EXPLORER.DEPENDENCY=Dependencia externa

#
## Commands
#
DATATOOLS.CORE.UI.COMMAND.RENAMING = Cambio de nombre
DATATOOLS.CORE.UI.COMMAND.ADD_DATABASE = Añadir base de datos
DATATOOLS.CORE.UI.COMMAND.ADD_FOREIGN_KEY_INDEX= Añadir índice de clave externa
DATATOOLS.CORE.UI.COMMAND.ADD_PRIMARY_KEY_INDEX= Añadir índice de clave primaria
DATATOOLS.CORE.UI.COMMAND.ADD_SEQUENCE= Añadir secuencia
DATATOOLS.CORE.UI.COMMAND.ADD_INDEX = Añadir índice
DATATOOLS.CORE.UI.COMMAND.ADD_CHECK_CONSTRAINT = Añadir comprobación de restricción
DATATOOLS.CORE.UI.COMMAND.ADD_ALIAS = Añadir alias
DATATOOLS.CORE.UI.COMMAND.ADD_UNIQUE_CONSTRAINT = Añadir restricción exclusiva

#
## Explorer Description
#
DATATOOLS.CORE.UI.EXPLORER.MULTI_SELECTION= {0} elementos seleccionados

#
## DnD Actions
#
DATATOOLS.CORE.UI.DND.MOVE = Mover
DATATOOLS.CORE.UI.DND.COPY = Copiar
DATATOOLS.CORE.UI.DND.LINK = Enlazar

#
## Quick Fix
#
DATATOOLS.CORE.UI.QUICKFIX.ADD_DANGLING = Añadir referencia colgada al modelo
DATATOOLS.CORE.UI.QUICKFIX.REMOVE_DANGLING = Quitar referencia colgada del modelo

#
## FE Action
#
GENERATE_DDL_MENU_TEXT = Generar DDL...

#
## FE and RE Wizards
#
FE_WIZARD_TITLEBAR_TEXT = Generar DDL
FE_WELCOME_PAGE_TITLE_TEXT = Bienvenido al asistente de ingeniería directa 
FE_WELCOME_PAGE_DESCRIPTION_TEXT = Este asistente le guiará en el proceso de generación\nde un script DDL a partir de un modelo de datos. Podrá grabar\nel script y tener la opción de ejecutarlo en una\nbase de datos.\r\n \r\n  ...para continuar, haga clic en Siguiente... 
FE_COMPLETION_PAGE_TITLE_TEXT = Completando el asistente de ingeniería directa 
FE_COMPLETION_PAGE_DESCRIPTION_TEXT = Ha completado correctamente el asistente de ingeniería directa. 
FE_SELECT_OPTIONS_PAGE_HEADER_TITLE	= Seleccione los elementos del modelo 
FE_SELECT_OPTIONS_PAGE_HEADER_SUBTITLE = Seleccione los elementos del modelo que desee generar en un script DDL. 
FE_SELECT_DATABASE_PAGE_HEADER_TITLE = Grabar y ejecutar DDL 
FE_SELECT_DATABASE_PAGE_HEADER_SUBTITLE = Especifique una ruta para grabar el script DDL generado. Puede ejecutar el script DDL proporcionando la información de conexión de su base de datos. 
FE_PROGRESS_BAR_PAGE_HEADER_TITLE = Generando DDL 
FE_PROGRESS_BAR_PAGE_HEADER_SUBTITLE = Espere mientras finaliza el proceso de ingeniería directa. 
COMPLETION_PAGE_TITLE_FONT = Verdana
GENERATE_NO_MNEMONIC_LABEL_TEXT	= Generar: 
DROP_STATEMENTS_CHECKBOX_TEXT = &Suprimir sentencias 
TABLES_CHECKBOX_TEXT = &Tablas 
TRIGGERS_CHECKBOX_TEXT_2 = Activado&res 
FULLY_QUALIFIED_NAMES_CHECKBOX_TEXT	= &Nombres completos 
COMMENTS_CHECKBOX_TEXT = &Comentarios 
VIEWS_CHECKBOX_TEXT = &Vistas 
QUOTED_IDENTIFIERS_CHECKBOX_TEXT = &Identificadores entre comillas 
INDEXES_CHECKBOX_TEXT = &Índices 
TRIGGERS_CHECKBOX_TEXT=	&Activadores
STORED_PROCEDURES_CHECKBOX_TEXT	= &Stored Procedures
TABLESPACES_CHECKBOX_TEXT = Esp&acios de tabla
FILE_PATH_LABEL_TEXT = &Nombre de archivo:
BROWSE_BUTTON_TEXT_2 = Examina&r... 
EXECUTE_CHECKBOX_TEXT =	Ejecuta&r 
FORWARD_SLASH_SQL_DELIMITER_CHECKBOX_TEXT = &Usar '/' como delimitador de sentencias SQL 
DEFAULT_FILE_EXTENSION = .ddl
FILE_FILTER_SCRIPT_FILES = *.ddl;*.sql
FILE_FILTER_ALL_FILES =	*.*
FILE_FILTER_NAME_SCRIPT_FILES =	Archivos de scripts (*.ddl;*.sql)
FILE_FILTER_NAME_ALL_FILES = Todos los archivos (*.*)

#
## SQL View Menu
#
DATATOOLS.CORE.UI.SQLVIEW.LINK=Link con editor
DATATOOLS.CORE.UI.SQLVIEW.GENERATE=Generar SQL desde el editor

#
## Bookmark
#
DATATOOLS.PROJECT.UI.BOOKMARK.TITLE=Añadir marcador
DATATOOLS.PROJECT.UI.BOOKMARK.MESSAGE=Introducir nombre de marcador:

#
## Inactive Relationship
#
DATATOOLS.PROJECT.UI.INACTIVE_RELATIONSHIP = Implícito

#
## Filtering Decoration
#
DATATOOLS.SERVER.UI.EXPLORER.FILTER_DECORATION = [Filtrado]

#
## Catalog Util
#
DATATOOLS.CORE.UI.LOADING=Cargando

DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.MESSAGE=Seleccionar elementos:
DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.TITLE=Seleccionar elementos
DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.FILTERLABEL=Aplicar filtro de nombre (? = cualquier carácter, * = cualquier string)

#
## Output Preferences
#
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.DESCRIPTION = Puede especificar preferencias para los conjuntos de resultados devueltos.
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.LIMIT_ROWS_RETRIEVED = &Limitar número de filas recuperadas
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.MAX_ROW_RETRIEVED = Líneas máximas que recupe&rar:
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.MAX_LOB_LENGTH = Bytes &máximos que recuperar para la columna de caracteres o de binarios:
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.ERROR_INVALID_INT = Especificar un valor entre {0} y {1}.

#
## Index and Trigger Decorations
#
DATATOOLS.CORE.UI.INDEX_UNIQUE.DECORATION = EXCLUSIVO
DATATOOLS.CORE.UI.TRIGGER_UPDATE.DECORATION = UPDATE
DATATOOLS.CORE.UI.TRIGGER_DELETE.DECORATION = DELETE
DATATOOLS.CORE.UI.TRIGGER_INSERT.DECORATION = INSERT
DATATOOLS.CORE.UI.TRIGGER_BEFORE.DECORATION = BEFORE
DATATOOLS.CORE.UI.TRIGGER_AFTER.DECORATION = AFTER
DATATOOLS.CORE.UI.TRIGGER_INSTEADOF.DECORATION = EN LUGAR DE

#
## Resource Chooser Dialog
#
_UI_TITLE_FILE_CHOOSER = Selección de archivo
_UI_TITLE_FOLDER_CHOOSER = Selección de carpeta
_UI_DESCRIPTION_FILE = Introduzca o seleccione un archivo:
_UI_DESCRIPTION_FOLDER = Introduzca o seleccione la carpeta principal:
_UI_DEFAULT_MSG_NEW_ALLOWED = &Introduzca o seleccione la carpeta principal:
_UI_DEFAULT_MSG_SELECT_ONLY = Seleccione la carpeta:

#
## Filter Dialog
#
_UI_TITLE_FILTER = Especificar un filtro
_UI_DESCRIPTION_FILTER = Especifique un filtro seleccionando un predicado y escribiendo un valor o bien indicando si desea incluir o excluir una selección de elementos.
_UI_DESCRIPTION_SELECTION_ONLY = Especifique un filtro indicando si desea incluir o excluir una selección de elementos.
_UI_DESCRIPTION_EXPRESSION_ONLY = Especifique un filtro seleccionando un predicado e introduciendo un valor.
_UI_RADIO_BUTTON_EXPRESSION = &Expresión
_UI_LABEL_EXPRESSION = N&ombre
_UI_COMBO_STARTS_WITH = Empieza con los caracteres
_UI_COMBO_NOT_START_WITH = No empieza con los caracteres
_UI_COMBO_CONTAINS = Contiene los caracteres
_UI_COMBO_NOT_CONTAIN = No contiene los caracteres
_UI_COMBO_ENDS_WITH = Acaba con los caracteres
_UI_COMBO_NOT_END_WITH = No acaba con los caracteres
_UI_RADIO_BUTTON_SELECTION = Se&lección
_UI_COMBO_INCLUDE_ITEMS = Incluir elementos seleccionados
_UI_COMBO_EXCLUDE_ITEMS = Excluir elementos seleccionados
_UI_BUTTON_SELECT_ALL = &Seleccionar todo
_UI_BUTTON_DESELECT_ALL = &Anular la selección de todo
_UI_CHECKBOX_DISABLE_FILTER = D&esactivar filtro
_UI_MESSAGE_CRITERIA_REQUIRED = Especifique los criterios del filtro.
_UI_MESSAGE_NO_QUOTES = El valor de la expresión no debe contener comillas.
_UI_MESSAGE_SELECTION_REQUIRED = Seleccione al menos un elemento.
_UI_SP_FILTER_PAGE_TITLE = Propiedades del filtro de Stored Procedure
_UI_TABLE_FILTER_PAGE_TITLE = Propiedades del filtro de tabla
_UI_SCHEMA_FILTER_PAGE_TITLE = Propiedades del filtro de esquema
_UI_TOOLTIP_NEW = Nuevo
_UI_TOOLTIP_DELETE = Eliminar
_UI_RADIO_BUTTON_AND = Cumplir todas las condiciones
_UI_RADIO_BUTTON_OR = Cumplir alguna condición
_UI_COMBO_PREDICATE = Predicado
_UI_TEXT_VALUE = Valor

#
## Actions for Schema Management
#
SCHEMA_MANAGEMENT_CREATE_SCHEMA = Esquema
SCHEMA_MANAGEMENT_CREATE_TABLE = Tabla
SCHEMA_MANAGEMENT_CREATE_VIEW = Ver
SCHEMA_MANAGEMENT_CREATE_INDEX = Índice
SCHEMA_MANAGEMENT_CREATE_BUFFER_POOL = Grupo de búfer
SCHEMA_MANAGEMENT_CREATE_PARTITION_GROUP = Grupo de partición
SCHEMA_MANAGEMENT_CREATE_DISTINCT_TYPE = Tipo diferenciado definido por el usuario 
SCHEMA_MANAGEMENT_CREATE_STRUCTURED_TYPE = Tipo estructurado definido por el usuario 
SCHEMA_MANAGEMENT_CREATE_SEQUENCE = Secuencia
SCHEMA_MANAGEMENT_CREATE_MQT = MQT
SCHEMA_MANAGEMENT_CREATE_ALIAS = Alias
SCHEMA_MANAGEMENT_CREATE_TRIGGER = Activador
SCHEMA_MANAGEMENT_CREATE_ROLE = Función
#
SCHEMA_MANAGEMENT_ADD_COLUMN = Columna
SCHEMA_MANAGEMENT_ADD_CHECK_CONSTRAINT = Comprobar restricción
SCHEMA_MANAGEMENT_ADD_UNIQUE_CONSTRAINT = Restricción exclusiva
SCHEMA_MANAGEMENT_ADD_FOREIGN_KEY = Clave externa
