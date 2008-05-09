################################################################################
## Copyright (c) 2005, 2008 Sybase, Inc.
## All rights reserved. This program and the accompanying materials
## are made available under the terms of the Eclipse Public License v1.0
## which accompanies this distribution, and is available at
## http://www.eclipse.org/legal/epl-v10.html
##
## Contributors:
##    Sybase, Inc. - initial API and implementation
##	  IBM Corporation = bug fix #203829
################################################################################ 
plugin.internal_error=Error interno\: org.eclipse.datatools.connectivity
ImportProfilesDialog.btnOverwrite.text=Sobrescribir &perfiles de conexión existentes con el mismo nombre
ExportProfilesDialog.button.text2=E&xaminar...
ExportProfilesDialog.group.text=Seleccione los perfiles de conexión a exportar\:
ExportProfilesDialog.button.text1=&Deseleccionar todo
ExportProfilesDialog.button.text=&Seleccionar todo
ImportProfilesDialog.button.text=E&xaminar...
ExportProfilesDialog.label.text=Especifique un nombre de &archivo\:
ImportProfilesDialog.label.text=Especifique un nombre de &archivo\:
ImportProfilesDialog.text.text=Nota\: el perfil de conexión a importar debe ser de un tipo que pueda crear \n  utilizando el asistente de perfil de conexión nuevo; los tipos de \n  conexión no soportados se descartarán.
ExportProfilesDialog.null.title=Exportar perfiles de conexión
ImportProfilesDialog.null.title=Importar perfiles de conexión
ExportProfilesDialog.btnEncryption.text=Cifrar el contenido del archivo
actions.export.noselection=¡Por favor, seleccione un perfil de conexión como mínimo\!
actions.export.nofile=¡Por favor, especifique un nombre de archivo\!
actions.export.norepository = Especifique un repositorio de destino.
actions.export.notvalidfile = Especifique un nombre de archivo y ruta válidos.
dialog.exception.title=Excepción
dialog.exception.message=Excepción capturada\: {0}
NewCPWizard.title=Perfil de conexión nuevo
IntroductionWizardPage.title=Página de introducción
IntroductionWizardPage.intro=Este asistente le lleva a través de los pasos necesarios para crear un perfil de conexión.
CPWizardSelectionPage.title = Perfil de conexión
CPWizardSelectionPage.desc = Seleccionar un tipo de perfil de conexión.
CPWizardSelectionPage.group = Tipos de perfil de &conexión:
CPWizardSelectionPage.defaultName =Nuevo {0}
actions.connect.job=Conectar trabajo servidor
actions.connect.uijob=Renovando la vista
actions.ping.job=Hacer ping a trabajo servidor
actions.ping.uijob=Mostrar trabajo de diálogo
actions.connect.title=Conectando con el servidor...
actions.ping.title=Haciendo ping al servidor...
actions.ping.success=¡Ping satisfactorio\!
actions.ping.failure=¡Ping anómalo\!
actions.delete.title=Confirmación de supresión
actions.delete.confirm=¿Está seguro de que desea suprimir el perfil de conexión "{0}"?
properties.dialog=Diálogo Propiedades
properties.category.general=General
properties.connectionprofile.name=Nombre de perfil
properties.connectionprofile.desc=Descripción
properties.connectionprofile.autoconnect=Conexión automática
properties.category.name=Nombre de categoría
properties.category.id=ID de categoría
ServersView.action.showproperties=&Propiedades
ServersView.action.refresh=&Renovar
ServersView.action.newCP=&Nuevo...
ServersView.action.exportCPs=E&xportar...
ServersView.action.importCPs=&Importar...
NewConnectionProfileWizard.title=Perfil de conexión nuevo
NewConnectionProfileWizard.create.failure=Anomalía al crear un perfil de conexión
NewConnectionProfileWizardPage.Status.DuplicateName=¡Ya existe el nombre de perfil de conexión\!
NewConnectionProfileWizardPage.Status.NoName=¡Por favor, especifique un nombre de perfil de conexión\!
NewConnectionProfileWizardPage.Status.NoRepository = Especifique un repositorio.
NewConnectionProfileWizardPage.title = Crear perfil de conexión
NewConnectionProfileWizardPage.desc = Introducir la información del perfil.
NewConnectionProfileWizardPage.cp_name = No&mbre:
NewConnectionProfileWizardPage.cp_desc = &Descripción (opcional):
NewConnectionProfileWizardPage.choices=Seleccione\:
NewConnectionProfileWizardPage.summary_cp_name=Nombre
NewConnectionProfileWizardPage.summary_cp_desc=Descripción
NewConnectionProfileWizardPage.Button.AutoConnect = Conectar &automáticamente al iniciar
NewConnectionProfileWizardPage.Button.AutoConnect1 = Conectar automáticamente al iniciar.
NewConnectionProfileWizardPage.summary_autoconnect = Conectar automáticamente al iniciar
NewConnectionProfileWizardPage.summary_autoconnect_on_finish = Conectar automáticamente al finalizar
NewConnectionProfileWizardPage.localRepository = Crear en repositorio de área de trabajo (local)
NewConnectionProfileWizardPage.chooseRepository = Seleccionar repositorio:
ConnectionProfileDetailsPage.Button.TestConnection=&Probar conexión
ConnectionProfileDetailsPage.AskConfirmation=Diálogo de confirmación
ConnectionProfileDetailsPage.AskReconnect=La información de conexión de este perfil de conexión ha cambiado. ¿Desea volver a conectar el servidor?
SummaryWizardPage.title=Resumen
SummaryWizardPage.description=Información recopilada de páginas anteriores.
SummaryWizardPage.property=Propiedad
SummaryWizardPage.value=Valor
dialog.title.error=Error
dialog.title.success=Satisfactorio
rename.dialog.title=Diálogo de redenominación
rename.dialog.message=Especifique un nombre de perfil de conexión nuevo\:
rename.dialog.errmsg.existing=¡El nombre ya existe, especifique uno diferente\!
rename.dialog.errmsg.invalid=¡El nombre no es válido, especifique un nombre válido\!
error.connect={1}.  Vea si hay más detalles en el archivo de anotaciones.
error.contentExtension=Error al conectar con la extensión "{0}" en el perfil "{1}" (Error\: {2})
CPVersionPropertyPage.desc=Información de versión para {0}.\nEsta información se ha obtenido a partir del último intento de conexión.  Si el último intento de conexión no ha sido satisfactorio, no habrá ninguna información de versión disponible.
CPVersionPropertyPage.button.updateVersion=Conectar y actualizar la información de la versión
CPVersionPropertyPage.label.versionUnavailable=La información de versión no está disponible para este perfil.
CPVersionPropertyPage.label.techVersions=Las tecnologías siguientes están disponibles en este servidor\:
ConnectionProfileSummaryPage.button.testConnection=&Probar conexión
CommonLabelProviderBase.label.version=v.
CommonLabelProviderBase.label.version.versionless={0} ({1}) 
CommonLabelProviderBase.label.localRepository=Repositorio de área de trabajo (Local)

properties.connectionprofile.type = Tipo de perfil
DSE.Refresh.label = Actua&lizar

CPDeleteChange.name = Eliminar perfil de conexión {0}
CPDeleteChange.error.ProfileDoesNotExist = El perfil de conexión que está intentando eliminar no existe.
CPDeleteChange.delete.failure = Error al eliminar el perfil de conexión.
CPDeleteProcessor.name = Procesador de eliminación de conectividad de DTP
CPDuplicateProcessor.name = Procesador de duplicación de perfiles de conexión de DTP
CPCopyChange.name = Duplicar {0} perfil de conexión
CPCopyChange.error.ProfileDoesNotExist = El perfil de conexión que está intentando duplicar no existe.
CPCopyChange.title.error = Error al duplicar el perfil de conexión.
CPCopyChange.error.InvalidTarget = El destino especificado no es un repositorio de perfiles de conexión válido.
CPCopyChange.error.RepositoryNotConnected = No conectado al repositorio de perfiles de conexión de destino {0}.
CPMoveProcessor.name = Procesador de desplazamiento de perfiles de conexión de DTP
CPMoveChange.name = Desplazar perfil de conexión {0}
CPMoveChange.error.ProfileDoesNotExist = El perfil de conexión que está intentando desplazar no existe.
CPMoveChange.title.error = Error al desplazar el perfil de conexión.
CPCreateChange.name = Crear perfil de conexión {0}
CPCreateChange.error.NewProfileDoesNotHaveName = Se requiere un nombre para crear un perfil de conexión nuevo.
CPCreateChange.error.NewProfileAlreadyExists = Ya existe un perfil de conexión con este nombre.
CPCreateChange.error.NewProfileDoesNotHaveProviderID = Se requiere un ID de proveedor de perfil de conexión para crear el nuevo perfil de conexión.
CPCreateChange.error.NewProfileDoesNotHaveNonNullPropertiesObject = Se requiere un objeto de propiedades no nulo para crear el nuevo perfil de conexión.
CPCreateChange.create.failure = Error al crear el perfil de conexión.
CPCreateProcessor.name = Procesador de creación de conectividad de DTP
CPRenameChange.name = Cambiar nombre al perfil de conexión {0}
CPRenameProcessor.name = Procesador de cambio de nombre de conectividad de DTP
CPRenameChange.title.error = Error al cambiar el nombre al perfil de conexión.

PropertyDescriptor.error.title = Error al procesar el descriptor de propiedad para la definición de controlador
PropertyDescriptor.error.msg = Se ha encontrado un error al procesar propiedades para la definición de controlador.
PasswordTextPropertyDescriptor.property.label = Contraseña
DriverClassBrowsePropertyDescriptor.property.label = Clase de controlador
DriverClassBrowsePropertyDescriptor.jardialog.title = Clases disponibles en la lista Jar
DriverClassBrowsePropertyDescriptor.jardialog.msg = Indicar el nombre de la clase de controlador o seleccionar una clase de los archivos Jar disponibles.
DriverClassBrowsePropertyDescriptor.noclasses.title = Sin clases de controlador disponibles
DriverClassBrowsePropertyDescriptor.noclasses.msg = No hay clases de controlador disponibles para la lista jar actual.

StatusBar.Connected = Conectado
StatusBar.Disconnected = Desconectado
StatusBar.ConnectedMessage = {0} ({1})

StatusBar.WorkingOffline = Trabajo sin conexión

DATATOOLS.SERVER.UI.EXPLORER.WORKOFFLINE = &Trabajar sin conexión
DATATOOLS.SERVER.UI.EXPLORER.UPDATE_OFFLINE = &Guardar sin conexión

DriverPropertyEditorDescriptor.InvalidID = Falta un ID válido para el descriptor del editor de propiedades del controlador
DriverPropertyEditorDescriptor.InvalidTargetTemplateID = Falta un ID de plantilla de destino válido para el descriptor del editor de propiedades del controlador
DriverPropertyEditorDescriptor.InvalidTargetPropertyId = Falta un ID de propiedades de destino válido para el descriptor del editor de propiedades del controlador
DriverPropertyEditorDescriptor.InvalidCustomPropertyEditorClass = Falta un editor de propiedades de personalización válido para el descriptor del editor de propiedades del controlador

ExtensibleProfileDetailsWizardPage.title = Especifique un controlador y los detalles de conexión
ExtensibleProfileDetailsWizardPage.description = Seleccione un controlador del menú desplegable y proporcione los detalles de conexión.
ExtensibleProfileDetailsWizardPage.driverCombo.label = Controla&dores:
ExtensibleNewConnectionProfileWizard.title = Nuevo perfil de conexión

DriverUIContributorComposite.group = &Propiedades

OtherDriverUIContributor.generaltab = General
OtherDriverUIContributor.optionaltab = Opcional
OtherDriverUIContributor.databaseName.label = B&ase de datos:
OtherDriverUIContributor.url.label = &URL:
OtherDriverUIContributor.userName.label = Nombre de &usuario:
OtherDriverUIContributor.password.label = &Contraseña:
OtherDriverUIContributor.savePassword.label = Guar&dar contraseña
OtherDriverUIContributor.optionalProps.label = Propiedades adicionales:
OtherDriverUIContributor.summaryData.database = Base de datos
OtherDriverUIContributor.summaryData.username = Nombre de usuario
OtherDriverUIContributor.summaryData.savePassword = Guardar contraseña
OtherDriverUIContributor.summaryData.true = true
OtherDriverUIContributor.summaryData.false = false
OtherDriverUIContributor.summaryData.url = URL

OtherDriverUIContributor.errormessage.requiresdatabase = Introduzca un nombre de base de datos.
OtherDriverUIContributor.errormessage.requiresurl = Introduzca una URL.
OtherDriverUIContributor.errormessage.requiresusername = Introduzca un nombre de usuario.

ConnectAction.title = Properties for {0}

