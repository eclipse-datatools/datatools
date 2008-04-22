###############################################################################
# Copyright (c) 2001, 2004 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#     IBM Corporation - initial API and implementation
###############################################################################
pluginName=Eclipse Data Tools Platform SQM UI Plug-in
providerName=Eclipse.org

#
## Extension Point Names
#
LABEL_SERVICE_EXTENSION_POINT_NAME=labelService
ELEMENT_ID_EXTENSION_POINT_NAME=elementID
MARKER_NAVIGATION_PROVIDER_EXTENSION_POINT_NAME=markerNavigationProvider
DATA_SOURCE_EXPLORER_SORTER=sorterValidatorProvider

#
## Model Explorer Selection
#
datatools.core.ui.modelExplorerSelection = Sélection d'explorateur de modèle

#
## Preferences for Label Decoration
#
datatools.core.ui.preferences.labeldecorators = Décorations d'étiquettes

#
## Preferences
#
datatools.core.ui.preferences.data = Développement SQL
datatools.core.ui.preferences.output = Sortie

#
## Views
#
datatools.core.ui.modelElementProperties = Propriétés d'éléments de modèles de données
datatools.core.ui.views.interactiveSQL = Afficher SQL

#
## Model Explorer
#
datatools.core.ui.contentProviders = Fournisseur de contenu de modèle physique
datatools.core.ui.parserProviders = Fournisseur d'analyseur de modèle physique
datatools.core.ui.iconProviders = Fournisseur d'icône de modèle physique

#
## Nature 
#
datatools.core.ui.projectNature = Nature des données des projets de la base de données

#
## Wizards
# 
datatools.core.ui.category.project = Base de données
datatools.core.ui.category.model = Modèle de base de données
datatools.core.ui.project.basic = Projet de conception de données
datatools.core.ui.project.description = Créer un projet de conception de données
datatools.core.ui.model.basic = Modèle de données physique

#
## Preferences
#
datatools.core.ui.preferences.modeler = Données
datatools.core.ui.preferences.default = Défauts de la base de données

#
## Popup && Actions
#
datatools.core.ui.actions.close = Fermer
datatools.core.ui.actions.add = Ajouter un SQL
datatools.core.ui.actions.addMQT = MQT
datatools.core.ui.actions.addSchema = Schéma
datatools.core.ui.actions.addTable = Table
datatools.core.ui.actions.addView = Vue
datatools.core.ui.actions.addColumn = Colonne
datatools.core.ui.actions.delete = Supprimer
datatools.core.ui.actions.addTrigger = Déclencheur
datatools.core.ui.globalActionHandlerProviders = Actions globales de données
datatools.core.ui.actions.selectSupplier = Naviguer vers une table de contraintes unique
datatools.core.ui.actions.fe=Générer un DDL...
datatools.core.ui.actions.re=Ingénierie inverse à partir d'un DDL...
datatools.core.ui.actions.dataModeler = Modeleur de données
datatools.core.ui.actions.addDomain = Domaine
datatools.core.ui.actions.addSequence = Séquence
datatools.core.ui.actions.addStoredProcedure = Procédure stockée
datatools.core.ui.actions.addJavaStoredProcedure = Procédure stockée Java
datatools.core.ui.actions.generateForeignKeyIndex = Index
datatools.core.ui.actions.generatePrimaryKeyIndex = Index
datatools.core.ui.actions.addDatabase = Base de données
datatools.core.ui.actions.addTableIndex = Index
datatools.core.ui.actions.addTableCheckConstraint = Contrainte de vérification
datatools.core.ui.actions.addUniqueConstraint = Contrainte unique
datatools.core.ui.actions.addAlias = Pseudo
datatools.core.ui.actions.addDistinctUDT = Type distinct défini par l'utilisateur
datatools.core.ui.actions.addStructuredUDT = Type structuré défini par l'utilisateur
datatools.core.ui.actions.addMQTIndex = Index
datatools.core.ui.actions.addMQTTrigger = Déclencheur
#
## Editors
#
datatools.core.ui.model.editor = Editeur de modèle SQL

#
## Decorators
#
datatools.core.ui.modelexplorer.modelExplorerColumnDecoration = Outils de données - Type de données de colonne
datatools.core.ui.modelexplorer.modelExplorerForeignKeyDecoration = Outils de données - Indicateur de clé principale ou étrangère
datatools.core.ui.modelexplorer.DependencyDecoration = Outils de données - Décoration de dépendance
datatools.core.ui.modelexplorer.bookmarkDecoration  = Outils de données - Indicateur de signet

#
## Model Explorer Sorter Provider
#
datatools.core.ui.modelexplorer.sorterProviders = Fournisseur de tri d'explorateur de modèle
datatools.core.ui.modelexplorer.menu.storageOrderSorter = Ordre de stockage
datatools.core.ui.modelexplorer.menu.name = Nom
datatools.core.ui.modelexplorer.menu.type = Type
datatools.core.ui.modelexplorer.menu.stereotype = Stéréotype
datatools.core.ui.modelexplorer.menu.visibility = Visibilité

#
## Model Explorer DnD
#
datatools.core.ui.modelexplorer.dragDrop = Glisser-Déplacer

#
## Marker
#
datatools.core.ui.modelexplorer.marker.dangling = Référence pendante


#
## Type for Label Service 
#
database = Base de données
schema = Schéma
baseTable = Table
column = Colonne
domain = Domaine
udt = Type défini par l'utilisateur
index = Index
procedure = Procédure stockée
view = Vue
parameter = Paramètre
trigger = Déclencheur
sequence = Séquence
source= Source
attributeDefinition = Définition d'attribut
function = Fonction définie par l'utilisateur
dependency = Dépendance
primaryKey = Clé principale
foreignKey = Clé étrangère
folder = Dossier logique
CheckConstraint = Contrainte de vérification
UniqueConstraint = Contrainte unique
existingConnection = Dossier de connexions
connectedConnection = Connexion directe
disconnectedConnection = Connexion déconnectée
cachedConnection = Connexion en cache
catalog = Catalogue

DEFAULT_SCHEMA_FILTER_PROPERTY_PAGE_NAME=Filtre de schéma par défaut
DEFAULT_TABLE_FILTER_PROPERTY_PAGE_NAME=Filtre de table par défaut
DEFAULT_STORED_PROCEDURE_FILTER_PROPERTY_PAGE_NAME=Filtre de procédure stockée par défaut
FILTER_SETTINGS_PROPERTY_PAGE_NAME=Paramètres de filtre
