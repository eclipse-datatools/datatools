###############################################################################
# Copyright (c) 2001, 2008 IBM Corporation and others.
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
DATATOOLS.CORE.UI.NEW.COLUMN_NAME = Colonne 
DATATOOLS.CORE.UI.NEW.SCHEMA_NAME = Schéma
DATATOOLS.CORE.UI.NEW.TABLE_NAME = Table
DATATOOLS.CORE.UI.NEW.TRIGGER_NAME = Déclencheur
DATATOOLS.CORE.UI.NEW.DOMAIN_NAME = Domaine
DATATOOLS.CORE.UI.NEW.SEQUENCE_NAME = Séquence
DATATOOLS.CORE.UI.NEW.STORED_PROCEDURE_NAME = Procédure_stockée
DATATOOLS.CORE.UI.NEW.DATABASE = Nouvelle base de données

#
## Label Decorations
#
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.DECORATION = Paramètres du décorateur de données :
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.TAB = Texte
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.FORMAT = Spécifier le format d'affichage pour les colonnes dans l'explorateur :
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.CFORMAT = Format de &colonne :
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.VARIABLE = Ajouter des &variables...
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.EXAMPLE = &Exemple :
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.TITLE = Ajouter des variables
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.MESSAGE = Sélectionner les variables à ajouter au format de décoration :
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.DATATYPE_DESC = {datatype} - type de données de colonne
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.NULLABLE_DESC = {nullable} - indique que la colonne est annulable
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.PK_DESC = {primaryKey} - indique que la colonne appartient à la contrainte de clé principale
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.FK_DESC = {foreignKey} - indique que la colonne appartient à la contrainte de clé étrangère

DATATOOLS.CORE.UI.STATUS.CONNECTION = {0} - Connexion "{1}"
DATATOOLS.CORE.UI.STATUS.PROJECT = {0} - Projet "{1}"

#
## Model Explorer Strings
#
DATATOOLS.CORE.UI.EXPLORER.DEPENDENCY=Dépendance externe

#
## Commands
#
DATATOOLS.CORE.UI.COMMAND.RENAMING = Attribution d'un nouveau nom
DATATOOLS.CORE.UI.COMMAND.ADD_DATABASE = Ajouter une base de données
DATATOOLS.CORE.UI.COMMAND.ADD_FOREIGN_KEY_INDEX= Ajouter un index de clés étrangères
DATATOOLS.CORE.UI.COMMAND.ADD_PRIMARY_KEY_INDEX= Ajouter un index de clés principales
DATATOOLS.CORE.UI.COMMAND.ADD_SEQUENCE= Ajouter une séquence
DATATOOLS.CORE.UI.COMMAND.ADD_INDEX = Ajouter un index
DATATOOLS.CORE.UI.COMMAND.ADD_CHECK_CONSTRAINT = Ajouter une contrainte de vérification
DATATOOLS.CORE.UI.COMMAND.ADD_ALIAS = Ajouter un pseudo
DATATOOLS.CORE.UI.COMMAND.ADD_UNIQUE_CONSTRAINT = Ajouter une contrainte unique

#
## Explorer Description
#
DATATOOLS.CORE.UI.EXPLORER.MULTI_SELECTION= {0} éléments sélectionnés

#
## DnD Actions
#
DATATOOLS.CORE.UI.DND.MOVE = Déplacer
DATATOOLS.CORE.UI.DND.COPY = Copier
DATATOOLS.CORE.UI.DND.LINK = Lien

#
## Quick Fix
#
DATATOOLS.CORE.UI.QUICKFIX.ADD_DANGLING = Ajouter une référence pendante au modèle
DATATOOLS.CORE.UI.QUICKFIX.REMOVE_DANGLING = Supprimer une référence pendante du modèle

#
## FE Action
#
GENERATE_DDL_MENU_TEXT = Générer &DDL...

#
## FE and RE Wizards
#
FE_WIZARD_TITLEBAR_TEXT = Générer un DDL
FE_WELCOME_PAGE_TITLE_TEXT = Bienvenue dans l'assistant d'ingénierie directe 
FE_WELCOME_PAGE_DESCRIPTION_TEXT = Cet assistant vous aide à générer\nun script DDL à partir d'un modèle de données. Vous pourrez enregistrer\nle script et aurez la possibilité de l'exécuter sur une\nbase de données.\r\n \r\n  ...pour continuer, cliquez sur Suivant... 
FE_COMPLETION_PAGE_TITLE_TEXT = Fin de l'assistant d'ingénierie directe 
FE_COMPLETION_PAGE_DESCRIPTION_TEXT = Vous avez terminé l'assistant d'ingénierie directe avec succès. 
FE_SELECT_OPTIONS_PAGE_HEADER_TITLE	= Sélectionner des éléments du modèle 
FE_SELECT_OPTIONS_PAGE_HEADER_SUBTITLE = Sélectionner les éléments du modèle que vous souhaitez générer dans un script DDL. 
FE_SELECT_DATABASE_PAGE_HEADER_TITLE = Enregistrer et exécuter le DDL 
FE_SELECT_DATABASE_PAGE_HEADER_SUBTITLE = Spécifier un chemin d'accès pour enregistrer le script DDL généré.  Vous pouvez exécuter le script DDL en fournissant les informations de connexion à votre base de données. 
FE_PROGRESS_BAR_PAGE_HEADER_TITLE = Génération d'un DDL 
FE_PROGRESS_BAR_PAGE_HEADER_SUBTITLE = Veuillez patienter le temps que le processus d'ingénierie directe se termine. 
COMPLETION_PAGE_TITLE_FONT = Verdana
GENERATE_NO_MNEMONIC_LABEL_TEXT	= Générer : 
DROP_STATEMENTS_CHECKBOX_TEXT = Instructions &Drop 
TABLES_CHECKBOX_TEXT = &Tables 
TRIGGERS_CHECKBOX_TEXT_2 = Déclencheu&rs 
FULLY_QUALIFIED_NAMES_CHECKBOX_TEXT	= Noms pleinement quali&fiés 
COMMENTS_CHECKBOX_TEXT = &Commentaires 
VIEWS_CHECKBOX_TEXT = &Vues 
QUOTED_IDENTIFIERS_CHECKBOX_TEXT = Ide&ntificateurs entre guillemets 
INDEXES_CHECKBOX_TEXT = &Index 
TRIGGERS_CHECKBOX_TEXT=	&Déclencheurs
STORED_PROCEDURES_CHECKBOX_TEXT	= Procédures &stockées
TABLESPACES_CHECKBOX_TEXT = Espaces t&ables
FILE_PATH_LABEL_TEXT = Nom de &fichier :
BROWSE_BUTTON_TEXT_2 = Parc&ourir... 
EXECUTE_CHECKBOX_TEXT =	Exécute&r 
FORWARD_SLASH_SQL_DELIMITER_CHECKBOX_TEXT = &Utiliser '/' comme délimiteur d'instruction SQL 
DEFAULT_FILE_EXTENSION = .ddl
FILE_FILTER_SCRIPT_FILES = *.ddl;*.sql
FILE_FILTER_ALL_FILES =	*.*
FILE_FILTER_NAME_SCRIPT_FILES =	Fichiers de scripts (*.ddl;*.sql)
FILE_FILTER_NAME_ALL_FILES = Tous les fichiers (*.*)

#
## SQL View Menu
#
DATATOOLS.CORE.UI.SQLVIEW.LINK=Lien avec l'éditeur
DATATOOLS.CORE.UI.SQLVIEW.GENERATE=Générer un SQL à partir de l'éditeur

#
## Bookmark
#
DATATOOLS.PROJECT.UI.BOOKMARK.TITLE=Ajouter un signet
DATATOOLS.PROJECT.UI.BOOKMARK.MESSAGE=Entrer un nom de signet :

#
## Inactive Relationship
#
DATATOOLS.PROJECT.UI.INACTIVE_RELATIONSHIP = Implicite

#
## Filtering Decoration
#
DATATOOLS.SERVER.UI.EXPLORER.FILTER_DECORATION = [Filtré]

#
## Catalog Util
#
DATATOOLS.CORE.UI.LOADING=Chargement

DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.MESSAGE=Sélectionner des éléments :
DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.TITLE=Sélectionner des éléments
DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.FILTERLABEL=Appliquer un filtre au nom (?= Tout caractère, * = Toute chaîne)

#
## Output Preferences
#
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.DESCRIPTION = Vous pouvez spécifier des préférences pour les ensembles de résultats renvoyés.
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.LIMIT_ROWS_RETRIEVED = Nombre maximum de &lignes récupérées
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.MAX_ROW_RETRIEVED = Nombre maximum de lignes à &récupérer :
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.MAX_LOB_LENGTH = Nombre &maximum d'octets à récupérer pour la colonne binaire ou caractère :
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.ERROR_INVALID_INT = Spécifier une valeur comprise entre {0} et {1}.

#
## Index and Trigger Decorations
#
DATATOOLS.CORE.UI.INDEX_UNIQUE.DECORATION = UNIQUE
DATATOOLS.CORE.UI.TRIGGER_UPDATE.DECORATION = UPDATE
DATATOOLS.CORE.UI.TRIGGER_DELETE.DECORATION = DELETE
DATATOOLS.CORE.UI.TRIGGER_INSERT.DECORATION = INSERT
DATATOOLS.CORE.UI.TRIGGER_BEFORE.DECORATION = BEFORE
DATATOOLS.CORE.UI.TRIGGER_AFTER.DECORATION = AFTER
DATATOOLS.CORE.UI.TRIGGER_INSTEADOF.DECORATION = INSTEAD OF

#
## Resource Chooser Dialog
#
_UI_TITLE_FILE_CHOOSER = Sélection de fichier
_UI_TITLE_FOLDER_CHOOSER = Sélection de dossier
_UI_DESCRIPTION_FILE = Entrer ou sélectionner un fichier :
_UI_DESCRIPTION_FOLDER = Entrer ou sélectionner le dossier parent :
_UI_DEFAULT_MSG_NEW_ALLOWED = &Entrer ou sélectionner le dossier parent :
_UI_DEFAULT_MSG_SELECT_ONLY = Sélectionner le dossier :

#
## Filter Dialog
#
_UI_TITLE_FILTER_DIALOG = Propriétés du filtre de la connexion
_UI_TITLE_FILTER = Spécifier un filtre
_UI_DESCRIPTION_FILTER = Spécifier un filtre en sélectionnant un prédicat et en entrant une valeur ou en indiquant si une sélection d'éléments doit être incluse ou exclue.
_UI_DESCRIPTION_SELECTION_ONLY = Spécifier un filtre en indiquant si une sélection d'éléments doit être incluse ou exclue.
_UI_DESCRIPTION_EXPRESSION_ONLY = Spécifier un filtre en sélectionnant un prédicat et en entrant une valeur.
_UI_RADIO_BUTTON_EXPRESSION = &Expression
_UI_LABEL_EXPRESSION = &Nom
_UI_COMBO_STARTS_WITH = Commence par les caractères
_UI_COMBO_NOT_START_WITH = Ne commence pas par les caractères
_UI_COMBO_CONTAINS = Contient les caractères
_UI_COMBO_NOT_CONTAIN = Ne contient pas les caractères
_UI_COMBO_ENDS_WITH = Finit par les caractères
_UI_COMBO_NOT_END_WITH = Ne finit pas par les caractères
_UI_RADIO_BUTTON_SELECTION = Sé&lection
_UI_COMBO_INCLUDE_ITEMS = Inclure les éléments sélectionnés
_UI_COMBO_EXCLUDE_ITEMS = Exclure les éléments sélectionnés
_UI_BUTTON_SELECT_ALL = &Sélectionner tout
_UI_BUTTON_DESELECT_ALL = &Désélectionner tout
_UI_CHECKBOX_DISABLE_FILTER = D&ésactiver le filtre
_UI_MESSAGE_CRITERIA_REQUIRED = Spécifier des critères de filtrage.
_UI_MESSAGE_NO_QUOTES = La valeur de l'expression ne doit pas contenir de guillemets.
_UI_MESSAGE_SELECTION_REQUIRED = Sélectionner au moins un élément.
_UI_SP_FILTER_PAGE_TITLE = Propriétés de filtre de procédure stockée
_UI_TABLE_FILTER_PAGE_TITLE = Propriétés de filtre de table
_UI_SCHEMA_FILTER_PAGE_TITLE = Propriétés de filtre de schéma
_UI_TOOLTIP_NEW = Nouveau
_UI_TOOLTIP_DELETE = Supprimer
_UI_RADIO_BUTTON_AND = Remplir toutes les conditions
_UI_RADIO_BUTTON_OR = Remplir certaines conditions
_UI_COMBO_PREDICATE = Prédicat
_UI_TEXT_VALUE = Valeur

#
## Actions for Schema Management
#
SCHEMA_MANAGEMENT_CREATE_SCHEMA = Schéma
SCHEMA_MANAGEMENT_CREATE_TABLE = Table
SCHEMA_MANAGEMENT_CREATE_VIEW = Vue
SCHEMA_MANAGEMENT_CREATE_INDEX = Index
SCHEMA_MANAGEMENT_CREATE_BUFFER_POOL = Réserve du tampon
SCHEMA_MANAGEMENT_CREATE_PARTITION_GROUP = Groupe de partition
SCHEMA_MANAGEMENT_CREATE_DISTINCT_TYPE = Type distinct défini par l'utilisateur
SCHEMA_MANAGEMENT_CREATE_STRUCTURED_TYPE = Type structuré défini par l'utilisateur
SCHEMA_MANAGEMENT_CREATE_ARRAY_TYPE = Type de données de tableau
SCHEMA_MANAGEMENT_CREATE_ROW_TYPE = Type de données de ligne
SCHEMA_MANAGEMENT_CREATE_SEQUENCE = Séquence
SCHEMA_MANAGEMENT_CREATE_MQT = MQT
SCHEMA_MANAGEMENT_CREATE_ALIAS = Pseudo
SCHEMA_MANAGEMENT_CREATE_TRIGGER = Déclencheur
SCHEMA_MANAGEMENT_CREATE_ROLE = Rôle
SCHEMA_MANAGEMENT_CREATE_USER = Utilisateur
SCHEMA_MANAGEMENT_CREATE_GROUP = Groupe
#
SCHEMA_MANAGEMENT_ADD_COLUMN = Colonne
SCHEMA_MANAGEMENT_ADD_CHECK_CONSTRAINT = Contrainte de vérification
SCHEMA_MANAGEMENT_ADD_UNIQUE_CONSTRAINT = Contrainte unique
SCHEMA_MANAGEMENT_ADD_FOREIGN_KEY = Clé étrangère
