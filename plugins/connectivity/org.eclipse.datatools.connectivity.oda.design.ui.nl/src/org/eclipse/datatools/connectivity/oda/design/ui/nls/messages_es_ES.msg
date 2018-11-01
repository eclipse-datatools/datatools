#
#************************************************************************
# Copyright (c) 2006, 2013 Actuate Corporation.
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
adaptableDataSourceProfile_unableAccessProfile=No se puede acceder al perfil vinculado en ({0}); se utilizarán las propiedades locales del diseño de fuente de datos.\n Provocado por la excepción: {1}
common_notInDesignSession=Actualmente no está en una sesión de diseño.
common_nullArgument=El argumento no debe ser nulo.
common_missingDataSourceDesign=Falta el diseño de origen de datos en el argumento OdaDesignSession.
common_createClassFailed=No se puede encontrar ni crear la clase ({0}).
extension_mustInheritFromODAPage=Implementación de página de asistente ({0}) no válida. Una página de asistente personalizada de ODA debe extenderse de {1}.
extension_mustInheritFromODAWizard=Implementación de asistente ({0}) no válida. Un asistente personalizado de ODA debe utilizar o extenderse de {1}.
extension_missingManifestElement=Falta la implementación del punto de extensión de diseño de ODA ({0})
extension_missingPropertyPage=Falta la implementación de página de propiedades en la extensión de ui de diseño de ODA.
wizard_dataSource_defaultTitle=Origen de datos nuevo
wizard_missingDataSourceId=Falta el ID de origen de datos ODA en el atributo de perfil de conexión.
wizard_invalidManifest=Contenido de manifiesto de plug-in no válido en la extensión oda.design.ui.dataSource.
page_defaultDataSourceTitle=Propiedades de origen de datos personalizado
designSession_alreadyHasLinkedProfile=La fuente de datos ({0}) ya ha externalizado sus propiedades en un perfil de conexión.  No se puede volver a exportar.
designSession_flushFailed=No ha sido posible procesar la sesión de diseño del conjunto de datos actual.
designSession_invalidArgument=Se ha especificado un argumento no válido en el método de sesión de diseño.
designSession_invalidEditApiCall=Llamada API de sesión de diseño no válida para editar un diseño.
designSession_invalidNewDesignApiCall=Llamada API de sesión de diseño no válida para crear un diseño.
designSession_invalidProfileName=No se puede localizar el perfil vinculado ({0}) en el almacenamiento de perfil de conexión especificado.
designSession_invalidProfileNameOrFilePath=No se puede localizar el perfil vinculado ({0}) ni el archivo de almacenamiento del perfil de conexión ({1}) especificado en el diseño de fuente de datos.
designSession_missingDataSetUIElement=No se ha definido ningún elemento dataSetUI para OdaDataSourceId ({0}) ni OdaDataSetId ({1}).
manifest_missingAttributeValue=Falta el valor en el atributo necesario ({0}).
manifest_missingDataSetElementId=El argumento dataSetElementID necesita un valor.
manifest_invalidDataSetElementId=ID de elemento dataSet no válido ({0}).
manifest_dataSetUi_missingPageId=Se necesita un valor de pageId.
manifest_dataSetUi_invalidPageId=Valor de pageId no válido ({0}).
manifest_dataSetUi_missingElement=Se necesita un elemento {0} en la extensión.
ui_defaultDataSourceTitle=Propiedades de conexión de fuente de datos
ui_replaceFilePrompt={0} ya existe. ¿Desea sustituirla?
ui_requiredFieldsMissingValue=Falta un valor para uno o varios campos necesarios (marcados con '*').
ui_errorLabel=Error
ui_errorCreatingCustomCtrls=Se ha producido un error al crear e inicializar controles personalizados.
ui_saveAsTitle=Guardar como
profilePage_pageTitle=Seleccionar un perfil de conexión
profilePage_pageLabel=Perfil de conexión
profilePage_selectProfileDefaultMessage=Seleccionar el perfil de conexión desde el que crear una fuente de datos
profilePage_label_profileStore=Almacenamiento de perfil de &conexión:
profilePage_button_browse=&Examinar...
profilePage_button_relativepath=&Ruta relativa
profilePage_button_absolutepath=&Ruta absoluta
profilePage_button_browse_filter=&Extensiones de archivo que se van a mostrar:
profilePage_button_browse_filter_tooltiptext=Especifique una o varias extensiones de archivo separadas por coma para filtrar los archivos mostrados más abajo.\nPor ejemplo: .txt, .profiles
profilePage_button_new=&Nuevo...
profilePage_button_browse_tooltip=Busque un archivo de almacenamiento del perfil de conexión.\nRuta relativa: ruta relativa basada en los recursos del anfitrión.\nRuta absoluta: ruta absoluta del sistema de archivos.
profilePage_resourcebaseuri_failure=Error al obtener URI de base de recurso de aplicación
profilePage_browse_noselection_error=Especifique un nombre de archivo de almacenamiento del perfil de conexión 
profilePage_odaTreeName=Fuentes de datos ODA
profilePage_checkboxLabel_useDefaultName=&Utilizar el nombre de fuente de datos predeterminado
profilePage_label_dataSourceName=&Nombre de fuente de datos:
profilePage_error_emptyName=El nombre de la fuente de datos no puede estar vacío.
profilePage_checkboxTooltip_maintainLink=Utilice siempre los valores de propiedad externalizados del almacenamiento del perfil de conexión en la fuente de datos.
profilePage_checkboxLabel_maintainLink=Utilizar propiedades &externalizadas en el almacenamiento de perfil de conexión
profilePage_error_invalidName=El nombre de la fuente de datos no es válido.
profilePage_error_invalidProfileStorePath=Ruta no válida para almacenamiento de perfil de conexión
profilePage_deprecatedProfileDisplayName={0} (reemplazado por {1})
profileStoreCreationDialog_button_new=&Nuevo...
profileStoreCreationDialog_title=Crear almacenamiento de perfil de conexión
profileStoreCreationDialog_grouptext=Seleccione los &perfiles de conexión que se incluirán en el almacenamiento:
profileStoreCreationDialog_newbutton_tooltiptext=Crear un nuevo perfil de conexión
profileStoreCreationDialog_fileext_error=El archivo de almacenamiento del perfil de conexión debe tener una extensión de archivo.\nPulse Aceptar para agregar la extensión de archivo predeterminada".{0}" al nombre del archivo.
dbProfilePage_defaultPageMessage=Verifique el controlador JDBC y los detalles de conexión
dbProfilePage_editPageMessage=Editar el controlador JDBC y los detalles de conexión
dbProfilePage_invalidDataSource=Fuente de datos no válida.  Actualice la referencia del perfil de conexión o cree una nueva fuente de datos.
dbProfilePage_notInDesignSession=No es posible abrir la página de propiedades fuera de una sesión de diseño ODA.
dbProfilePage_noCustomPage=No es posible encontrar y abrir la página de propiedades específica del controlador de la base de datos.
preferencesPage_openDataAccessDescription=Establezca las preferencias para las fuentes de datos de acceso a datos abiertos individuales \na través de las páginas recogidas en este nodo.
