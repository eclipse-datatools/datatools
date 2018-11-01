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
adaptableDataSourceProfile_unableAccessProfile=Impossible d'accéder au profil lié sous ({0}) ; utilisez les propriétés locales de la source de données.\n Cause de l'exception : {1}
common_notInDesignSession=Pas dans une session de conception en cours.
common_nullArgument=L'argument ne doit pas être null.
common_missingDataSourceDesign=Conception de source de données manquante dans l'argument OdaDesignSession.
common_createClassFailed=Impossible de trouver la classe ({0}) ou de la créer.
extension_mustInheritFromODAPage=Implémentation ({0}) de page d''assistant incorrecte. Une page d''assistant personnalisé ODA doit se développer à partir de {1}.
extension_mustInheritFromODAWizard=Implémentation ({0}) d''assistant incorrecte. Un assistant personnalisé ODA doit se développer à partir de {1} ou l''utiliser.
extension_missingManifestElement=Implémentation du point d''extension d''interface utilisateur de conception ODA ({0}) manquante
extension_missingPropertyPage=Implémentation de page de propriétés manquante dans l'extension d'interface utilisateur de conception ODA.
wizard_dataSource_defaultTitle=Nouvelle source de données
wizard_missingDataSourceId=ID de source de données ODA manquante dans l'attribut Profil de connexion.
wizard_invalidManifest=Contenu du manifeste de plug-in incorrect dans l'extension oda.design.ui.dataSource.
page_defaultDataSourceTitle=Propriétés de la source de données personnalisée
designSession_alreadyHasLinkedProfile=La source de données ({0}) a déjà externalisé ses propriétés dans un profil de connexion.  Il est impossible de les réexporter.
designSession_flushFailed=Impossible de vider la session de conception de la requête du jeu de données actuelles
designSession_invalidArgument=Argument incorrect spécifié dans la méthode de la session de conception.
designSession_invalidEditApiCall=Appel API de session de conception non valide pour la modification du modèle.
designSession_invalidNewDesignApiCall=Appel API de session de conception non valide pour la création d'un nouveau modèle.
designSession_invalidProfileName=Impossible de localiser le profil lié ({0}) dans l'emplacement de stockage des profils de connexion indiqué.
designSession_invalidProfileNameOrFilePath=Impossible de localiser le profil lié ({0}) ou le fichier de stockage des profils de connexion ({1}) indiqué dans le modèle de source de données.
designSession_missingDataSetUIElement=Aucun élément dataSetUI n''est défini pour OdaDataSourceId ({0}) et OdaDataSetId ({1}).
manifest_missingAttributeValue=Valeur manquante dans l''attribut obligatoire ({0}).
manifest_missingDataSetElementId=Une valeur est requise pour l'argument dataSetElementID.
manifest_invalidDataSetElementId=ID d''élément de fichier incorrect ({0}).
manifest_dataSetUi_missingPageId=Une valeur d'ID page est requise.
manifest_dataSetUi_invalidPageId=Valeur d''ID page incorrecte ({0}).
manifest_dataSetUi_missingElement=Un élément {0} est requis dans l''extension.
ui_defaultDataSourceTitle=Propriétés de connexion à la source de données
ui_replaceFilePrompt={0} existe déjà. Voulez-vous le remplacer ?
ui_requiredFieldsMissingValue=Une valeur est manquante dans une ou plusieurs zones (signalées par '*').
ui_errorLabel=Erreur
ui_errorCreatingCustomCtrls=Une erreur est survenue lors de la création et de l'initialisation des commandes personnalisées.
ui_saveAsTitle=Enregistrer sous
profilePage_pageTitle=Sélectionner un profil de connexion
profilePage_pageLabel=Profil de connexion
profilePage_selectProfileDefaultMessage=Sélectionnez le profil de connexion à partir duquel créer la source de données
profilePage_label_profileStore=Emplacement de &stockage des profils de connexion :
profilePage_button_browse=Pa&rcourir...
profilePage_button_relativepath=Chemin d'accès &relatif
profilePage_button_absolutepath=Chemin d'accès &absolu
profilePage_button_browse_filter=&Extension(s) de fichier à afficher :
profilePage_button_browse_filter_tooltiptext=Spécifiez une ou plusieurs extensions de fichier séparées par des virgules pour filtrer les fichiers affichés ci-dessous.\nPar exemple : .txt, .profiles
profilePage_button_new=&Nouveau...
profilePage_button_browse_tooltip=Recherchez un fichier de stockage des profils de connexion.\nChemin d'accès relatif : chemin d'accès relatif basé sur les ressources hôtes.\nChemin d'accès absolu : chemin d'accès absolu du fichier système.
profilePage_resourcebaseuri_failure=Impossible d'obtenir l'URI de base des ressources de l'application.
profilePage_browse_noselection_error=Veuillez spécifier le nom du fichier de stockage des profils de connexion 
profilePage_odaTreeName=Sources de données ODA
profilePage_checkboxLabel_useDefaultName=&Utiliser le nom de la source de données par défaut
profilePage_label_dataSourceName=Nom de la source de &données :
profilePage_error_emptyName=Le nom de la source de données doit être renseigné.
profilePage_checkboxTooltip_maintainLink=Toujours utiliser les valeurs de propriété externalisées de l'emplacement de stockage des profils de connexion dans la source de données.
profilePage_checkboxLabel_maintainLink=Utiliser les propriétés &externalisées dans l'emplacement de stockage des profils de connexion
profilePage_error_invalidName=Le nom de la source de données n'est pas valide.
profilePage_error_invalidProfileStorePath=Chemin de l'emplacement de stockage des profils de connexion incorrect
profilePage_deprecatedProfileDisplayName={0} (remplacé par {1})
profileStoreCreationDialog_button_new=&Nouveau...
profileStoreCreationDialog_title=Créer un emplacement de stockage des profils de connexion
profileStoreCreationDialog_grouptext=Sélectionnez les &profils de connexion à inclure dans l'emplacement de stockage :
profileStoreCreationDialog_newbutton_tooltiptext=Créer un nouveau profil de connexion
profileStoreCreationDialog_fileext_error=Le fichier de stockage des profils de connexion doit disposer d'une extension de fichier.\nAppuyez sur OK pour ajouter l'extension de fichier par défaut ".{0}" au nom du fichier.
dbProfilePage_defaultPageMessage=Vérifiez le pilote JDBC et les informations de connexion
dbProfilePage_editPageMessage=Modifier le pilote JDBC et les informations de connexion
dbProfilePage_invalidDataSource=Source de données incorrecte.  Mettre à jour la référence du profil de connexion ou créer une nouvelle source de données.
dbProfilePage_notInDesignSession=Impossible d'ouvrir une page de propriétés en dehors d'une session de conception ODA.
dbProfilePage_noCustomPage=Impossible de trouver et d'ouvrir la page de propriété des pilotes de base de données.
preferencesPage_openDataAccessDescription=Définissez les préférences pour les sources de données Open Data Access individuelles \nen utilisant les pages sous ce nœud.
