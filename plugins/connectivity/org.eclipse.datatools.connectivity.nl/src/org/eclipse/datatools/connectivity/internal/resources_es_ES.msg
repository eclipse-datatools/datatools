## Copyright (c) 2005, 2011 Sybase, Inc. and others.
## All rights reserved. This program and the accompanying materials
## are made available under the terms of the Eclipse Public License v1.0
## which accompanies this distribution, and is available at
## http://www.eclipse.org/legal/epl-v10.html
##
## Contributors:
##    Sybase, Inc. - initial API and implementation
##    IBM Corporation - migrated to new wizard framework
##    IBM Corporation - fix for defect 222818
################################################################################ 
plugin.internal_error=Error interno\: org.eclipse.datatools.connectivity
ConnectionProfileConstants.unknownVersion=desconocida
ConnectJob.name=Creando conexiones a {0}.
ConnectJob.status.info=No se han podido crear conexiones a {0} para una o varias extensiones.
ConnectJob.status.error=No se ha podido conectar a {0}.
DisconnectJob.name=Cerrando conexiones a {0}.
DisconnectJob.status.info=No se han podido cerrar conexiones a {0} para una o varias extensiones.
DisconnectJob.status.error=No se ha podido desconectar de {0}.
CreateConnectionJob.name=Creando {0} a {1}.
CreateConnectionJob.error=Error al crear la conexión {0} a {1}. (Error\: {2})
OpenConnectionEventJob.name=Procesando evento de apertura de conexión para {0}.
CloseManagedConnectionJob.name=Cerrando la conexión {0} de {1}.
CloseManagedConnectionJob.error=Ha ocurrido un error al cerrar la conexión {0} de {1} (Error\: {2}).
CloseConnectionEventJob.name=Procesando evento de cierre de conexión para {0}.
assert.invalid.profile.duplicateFactory = Error al analizar\: id de fábrica duplicado y prioridad duplicada ({0})
assert.invalid.profile.nullFactory = Error al analizar\: fábrica o id de fábrica nulos ({0}/{1})
drivermarker.error = Existe un problema con el controlador "{0}." (Error\: {1})
drivermarker.import.error = Falta la lista jar para la definición de controlador creada durante la importación de perfil "{0}."
profile.duplicate =Nombre de perfil de conexión duplicado "{0}"\!
DriverConnectionBase.error.unknown=La conexión ha fallado con un error no especificado.
DriverConnectionBase.error.driverDefinitionNotSpecified=No se ha especificado la definición de controlador.
DriverConnectionBase.error.driverDefinitionNotFound=No se ha encontrado la definición de controlador.
error.loaddriversxml = Error al cargar los datos de definición de controlador.
error.savedriversxml = Error al guardar los datos de definición de controlador.

trace.error.migration = Error al cargar el elemento de migración para el proveedor de perfil de conexión (id={0}, clase de migración={1})
trace.error.drivermigration = Error al crear la instancia de perfil de controlador (id={0}, id de controlador={1})

migration.error.providerDoesNotExist = Error al cargar el elemento de migración: el newProviderID especificado no es válido (newProviderID={0})
migration.error.invalidPropertyMigrationElement = Error al cargar el elemento de migración: propertyMigration debe especificar una newPropertyKey, una oldPropertyKey o ambas
migration.error.unknownParentElement = Error al cargar el elemento de migración: elemento principal desconocido (nombre del elemento principal={0})

DriverUtil.taskName = Procesando clases en {0}

Category.Name.Unknown=No admitido

WorkOfflineJob.name = Cambio de {0} para trabajar en modo desconectado.
WorkOfflineJob.status.error = No se han podido crear conexiones offline con {1} para una o más extensiones.

SaveWorkOfflineDataJob.name = Guardar datos sin conexión para {0}.
SaveWorkOfflineDataJob.status = Guardar el estado de los datos sin conexión.

CreateOfflineConnectionJob.name = Creando conexiones {0} offline con {1}.
CreateOfflineConnectionJob.error = Error al crear conexiones {0} offline con {1}. (Error\: {2})

ManagedConnection_save_not_connected_error = Debe estar conectado al servidor para guardar los datos sin conexión.
ManagedConnection_offline_not_supported_error = {0} no es compatible con el trabajo sin conexión.

drivers.error.finding.property.no.template = Se ha producido un problema al intentar recuperar {0} de la plantilla de controlador.

JDBCConnection.technologyName = JDBC
JDBCConnection.invalidDriverDefinition = {0}\: {1}. Utilizar las propiedades de controlador definidas en el perfil de bases de datos en su lugar.

error.database.not.start = No se ha introducido ningún comando de inicio de base de datos. ErrorCode = {0}, SQLState = {1}.
PluginResourceLocator.invalidURL = URL no válida {0} (Error\: {1})
ConnectivityPlugin.error.noDefaultWorkspace = No es posible determinar la ubicación predeterminada del área de trabajo.  Compruebe la configuración de la plataforma sin OSGi de la ruta del área de trabajo de las herramientas de datos o el complemento.

