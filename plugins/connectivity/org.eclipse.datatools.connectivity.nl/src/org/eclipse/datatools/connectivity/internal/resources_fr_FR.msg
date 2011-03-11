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
plugin.internal_error=Erreur interne - org.eclipse.datatools.connectivity
ConnectionProfileConstants.unknownVersion=inconnue
dialog.title.error.loadconnectionfactory=Erreur lors du chargement de la fabrique de connexions dans "{0}".
ConnectJob.name=Création des connexions à {0}.
ConnectJob.status.info=Impossible de créer des connexions à {0} pour une ou plusieurs extensions.
ConnectJob.status.error=Connexion impossible à {0}.
DisconnectJob.name=Fermeture des connexions à {0}.
DisconnectJob.status.info=Impossible de fermer les connexions à {0} pour une ou plusieurs extensions.
DisconnectJob.status.error=Impossible de se déconnecter de {0}.
CreateConnectionJob.name=Création de {0} sur {1}.
CreateConnectionJob.error=Erreur lors de la création de la connexion de {0} à {1}. (Erreur \: {2})
OpenConnectionEventJob.name=Traitement de l''événement d''ouverture de connexion pour {0}.
CloseManagedConnectionJob.name=Fermeture de la connexion de {0} à {1}.
CloseManagedConnectionJob.error=Erreur lors de la fermeture de la connexion de {0} à {1}. (Erreur \: {2})
CloseConnectionEventJob.name=Traitement de l''événement de fermeture de connexion pour {0}.
assert.invalid.profile=Erreur d''analyse syntaxique \: attribut non valide ({0})
assert.invalid.profile.duplicateFactory = Erreur d'analyse \: id de l'usine en double avec priorité en double ({0})
assert.invalid.profile.nullFactory = Erreur d''analyse\ : aucune usine ou id de l''usine ({0}/{1})
dialog.title.error.loadwizard=Erreur lors du chargement du nouvel assistant dans "{0}"\!
operation.new.connectionprofile=Création du profil \: "{0}"
error.connect=Erreur lors de la connexion au profil "{0}."  Pour plus de détails, consultez le fichier de consignation.
marker.error=Erreur lors de la connexion au profil {0}.  (Erreur \: {1})
drivermarker.error = Problème avec le pilote "{0}". (Erreur\ : {1})
drivermarker.import.error = La liste jar pour la définition de pilote créée lors de l'importation du profil "{0}" est manquante.
profile.duplicate =Nom du profil de connexion en double "{0}".
profile.notexist=Le profil de connexion "{0}" n''existe pas.
duplicate.profile.name=Copie de {0} ({1})
DriverConnectionBase.error.unknown=La connexion a échoué avec une erreur non spécifiée.
DriverConnectionBase.error.driverDefinitionNotSpecified=La définition du pilote n'est pas spécifiée.
DriverConnectionBase.error.driverDefinitionNotFound=La définition du pilote est introuvable.
error.loadprofilesxml=Erreur lors du chargement des données du profil de connexion.
error.saveprofilesxml=Erreur lors de la sauvegarde des données du profil de connexion.
error.loaddriversxml = Erreur lors du chargement des données de définition du pilote.
error.savedriversxml = Erreur lors de l'enregistrement des données de définition du pilote.
trace.error.propertiesPersistenceHook=Impossible de charger propertiesPersistenceHook, utilisation des propriétés par défaut (type de profil\={0}, extension\={1})
trace.error.propertiesPersistenceHookSaveError=Erreur lors de l''appel du point d''ancrage persistant des propriétés; conservation de toutes les propriétés (profil\={0}, extension\={1})
trace.error.propertiesPersistenceHookLoadError=Erreur lors de l''appel du point d''ancrage persistant des propriétés; propriétés transitoires non définies (profil\={0}, extension\={1})
trace.error.migration = Erreur lors du chargement de l'élément de migration pour le fournisseur de profil de connexion (id={0}, classe de migration={1})
trace.error.drivermigration = Erreur de création d''une instance de pilote du profil (id={0}, driver id={1})

migration.error.providerDoesNotExist = Erreur lors du chargement de l'élément de migration : l'ID newProviderID spécifié est incorrect (newProviderID={0})
migration.error.invalidPropertyMigrationElement = Erreur lors du chargement de l'élément de migration : propertyMigration doit spécifier une clé newPropertyKey, une clé oldPropertyKey ou les deux
migration.error.unknownParentElement = Erreur lors du chargement de l'élément de migration : élément parent inconnu (nom de l'élément parent={0})

DriverUtil.taskName = Traitement des classes dans {0}
Category.Name.Unknown=Non pris en charge

WorkOfflineJob.name = Modification de {0} pour travailler en mode hors ligne.
WorkOfflineJob.status.error = Impossible de créer des connexions hors ligne à {1} pour au moins une extension.

SaveWorkOfflineDataJob.name = Enregistrer les données hors ligne pour {0}.
SaveWorkOfflineDataJob.status = Enregistrer l'état des données hors ligne.

CreateOfflineConnectionJob.name = Création de la connexion hors ligne de {0} à {1}.
CreateOfflineConnectionJob.error = Erreur lors de la création de la connexion hors ligne de {0} à {1}. (Erreur \: {2})

ManagedConnection_save_not_connected_error = Connexion indispensable au serveur pour enregistrer des données hors ligne.
ManagedConnection_offline_not_supported_error = {0} ne prend pas en charge le travail hors ligne.

drivers.error.finding.property.no.template = Un problème a été rencontré lors de la tentative de récupération de {0} à partir d'un modèle de pilote.

JDBCConnection.technologyName = JDBC
JDBCConnection.invalidDriverDefinition = {0}\: {1}. Utiliser plutôt les propriétés de pilote définies dans le profil de la base de données.

error.database.not.start = Aucune commande de base de données de démarrage établie. Code d'erreur = {0}, état SQL = {1}.
PluginResourceLocator.invalidURL = Invalid URL {0} (Error\: {1})

