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
DATATOOLS.CORE.UI.NEW.COLUMN_NAME = Spalte 
DATATOOLS.CORE.UI.NEW.SCHEMA_NAME = Schema
DATATOOLS.CORE.UI.NEW.TABLE_NAME = Tabelle
DATATOOLS.CORE.UI.NEW.TRIGGER_NAME = Auslösen
DATATOOLS.CORE.UI.NEW.DOMAIN_NAME = Domäne
DATATOOLS.CORE.UI.NEW.SEQUENCE_NAME = Sequenz
DATATOOLS.CORE.UI.NEW.STORED_PROCEDURE_NAME = Stored_Procedure
DATATOOLS.CORE.UI.NEW.DATABASE = Neue Datenbank

#
## Label Decorations
#
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.DECORATION = Einstellungen Data Decorator:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.TAB = Text
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.FORMAT = Bestimmen Sie das Anzeigeformat der Spalten im Explorer:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.CFORMAT = &Spaltenformat:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.VARIABLE = &Variablen hinzufügen...
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.EXAMPLE = &Beispiel:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.TITLE = Variablen hinzufügen
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.MESSAGE = Wählen Sie Variablen aus, um diese dem Dekorationsformat hinzuzufügen:
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.DATATYPE_DESC = {datatype} - der Spaltendatentyp
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.NULLABLE_DESC = {nullable} - zeigt an, dass für die Spalte Nullwerte zulässig sind
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.PK_DESC = {primaryKey} - zeigt an, dass die Spalte zum eingeschränkten Primärschlüssel gehört
DATATOOLS.CORE.UI.PREFERENCES.COLUMN.FK_DESC = {foreignKey} - zeigt an, dass die Spalte zum eingeschränkten Fremdschlüssel gehört

DATATOOLS.CORE.UI.STATUS.CONNECTION = {0} - Verbindung "{1}"
DATATOOLS.CORE.UI.STATUS.PROJECT = {0} - Projekt "{1}"

#
## Model Explorer Strings
#
DATATOOLS.CORE.UI.EXPLORER.DEPENDENCY=Externe Abhängigkeit

#
## Commands
#
DATATOOLS.CORE.UI.COMMAND.RENAMING = Umbenennung
DATATOOLS.CORE.UI.COMMAND.ADD_DATABASE = Datenbank hinzufügen
DATATOOLS.CORE.UI.COMMAND.ADD_FOREIGN_KEY_INDEX= Fremdschlüssel-Index hinzufügen
DATATOOLS.CORE.UI.COMMAND.ADD_PRIMARY_KEY_INDEX= Primärschlüssel-Index hinzufügen
DATATOOLS.CORE.UI.COMMAND.ADD_SEQUENCE= Sequenz hinzufügen
DATATOOLS.CORE.UI.COMMAND.ADD_INDEX = Index hinzufügen
DATATOOLS.CORE.UI.COMMAND.ADD_CHECK_CONSTRAINT = Prüf-Einschränkung hinzufügen
DATATOOLS.CORE.UI.COMMAND.ADD_ALIAS = Alias hinzufügen
DATATOOLS.CORE.UI.COMMAND.ADD_UNIQUE_CONSTRAINT = Eindeutige Einschränkung hinzufügen

#
## Explorer Description
#
DATATOOLS.CORE.UI.EXPLORER.MULTI_SELECTION= {0} Elemente ausgewählt

#
## DnD Actions
#
DATATOOLS.CORE.UI.DND.MOVE = Verschieben
DATATOOLS.CORE.UI.DND.COPY = Kopieren
DATATOOLS.CORE.UI.DND.LINK = Verknüpfung

#
## Quick Fix
#
DATATOOLS.CORE.UI.QUICKFIX.ADD_DANGLING = Alleinstehende Referenz zum Modell hinzufügen
DATATOOLS.CORE.UI.QUICKFIX.REMOVE_DANGLING = Alleinstehende Referenz von Modell entfernen

#
## FE Action
#
GENERATE_DDL_MENU_TEXT = DDL erstellen...

#
## FE and RE Wizards
#
FE_WIZARD_TITLEBAR_TEXT = Erstellen Sie DDL
FE_WELCOME_PAGE_TITLE_TEXT = Willkommen zum Forward-Engineering-Assistenten 
FE_WELCOME_PAGE_DESCRIPTION_TEXT = Dieser Assistent führt Sie durch den Prozess der Erstellung\neines DDL-Skriptes aus einem Datenmodell. Sie können das Skript\nspeichern und optional auf\neine Datenbank anwenden.\r\n \r\nUm fortzufahren, klicken Sie auf 'Weiter'. 
FE_COMPLETION_PAGE_TITLE_TEXT = Beenden des Forward-Engineering-Assistenten 
FE_COMPLETION_PAGE_DESCRIPTION_TEXT = Sie haben den Forward-Engineering-Assistenten erfolgreich beendet. 
FE_SELECT_OPTIONS_PAGE_HEADER_TITLE	= Modell-Elemente auswählen 
FE_SELECT_OPTIONS_PAGE_HEADER_SUBTITLE = Wählen Sie die Modell-Elemente aus, die sie in einem DDL-Skript erstellen möchten. 
FE_SELECT_DATABASE_PAGE_HEADER_TITLE = DDL speichern und ausführen 
FE_SELECT_DATABASE_PAGE_HEADER_SUBTITLE = Bestimmen Sie den Pfad, unter dem Sie das erstellte DDL-Skript speichern möchten. Sie können das DDL-Skript ausführen, indem Sie die Informationen zur Datenbankverbindung angeben. 
FE_PROGRESS_BAR_PAGE_HEADER_TITLE = DDL wird erstellt... 
FE_PROGRESS_BAR_PAGE_HEADER_SUBTITLE = Bitte warten Sie, während der Forward-Engineering-Prozess abgeschlossen wird. 
COMPLETION_PAGE_TITLE_FONT = Verdana
GENERATE_NO_MNEMONIC_LABEL_TEXT	= Erstellen: 
DROP_STATEMENTS_CHECKBOX_TEXT = &Drop-Anweisungen 
TABLES_CHECKBOX_TEXT = &Tabellen 
TRIGGERS_CHECKBOX_TEXT_2 = T&rigger 
FULLY_QUALIFIED_NAMES_CHECKBOX_TEXT	= &Voll qualifizierte Namen 
COMMENTS_CHECKBOX_TEXT = &Kommentare 
VIEWS_CHECKBOX_TEXT = &Ansichten 
QUOTED_IDENTIFIERS_CHECKBOX_TEXT = &Bezeichner in Anführungszeichen 
INDEXES_CHECKBOX_TEXT = &Indizes 
TRIGGERS_CHECKBOX_TEXT=	&Trigger
STORED_PROCEDURES_CHECKBOX_TEXT	= &Gespeicherte Prozeduren
TABLESPACES_CHECKBOX_TEXT = T&abelleneigenschaften
FILE_PATH_LABEL_TEXT = &Dateiname:
BROWSE_BUTTON_TEXT_2 = Du&rchsuchen... 
EXECUTE_CHECKBOX_TEXT =	&Ausführen 
FORWARD_SLASH_SQL_DELIMITER_CHECKBOX_TEXT = &Nutzen Sie '/' als SQL-Anweisungs-Trennzeichen 
DEFAULT_FILE_EXTENSION = .ddl
FILE_FILTER_SCRIPT_FILES = *.ddl;*.sql
FILE_FILTER_ALL_FILES =	*.*
FILE_FILTER_NAME_SCRIPT_FILES =	Skriptdateien (*.ddl;*.sql)
FILE_FILTER_NAME_ALL_FILES = Alle Dateien (*.*)

#
## SQL View Menu
#
DATATOOLS.CORE.UI.SQLVIEW.LINK=Verbinden mit Editor
DATATOOLS.CORE.UI.SQLVIEW.GENERATE=SQL aus Editor erstellen

#
## Bookmark
#
DATATOOLS.PROJECT.UI.BOOKMARK.TITLE=Lesezeichen hinzufügen
DATATOOLS.PROJECT.UI.BOOKMARK.MESSAGE=Geben Sie den Lesezeichen-Namen ein:

#
## Inactive Relationship
#
DATATOOLS.PROJECT.UI.INACTIVE_RELATIONSHIP = Implizit

#
## Filtering Decoration
#
DATATOOLS.SERVER.UI.EXPLORER.FILTER_DECORATION = [Gefiltert]

#
## Catalog Util
#
DATATOOLS.CORE.UI.LOADING=Laden

DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.MESSAGE=Elemente auswählen:
DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.TITLE=Wählen Sie die Elemente aus.
DATATOOLS.CORE.UI.DIALOGS.FILTERLISTSELECTIONDIALOG.FILTERLABEL=Namensfilter anwenden (? = Beliebiges Zeichen, * = Beliebige Zeichenfolge)

#
## Output Preferences
#
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.DESCRIPTION = Sie können die Präferenzen für die erhaltenen Ergebnisse bestimmen.
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.LIMIT_ROWS_RETRIEVED = &Anzahl der abzurufenden Zeilen begrenzen
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.MAX_ROW_RETRIEVED = Maximale Anzahl der abgerufenen &Reihen:
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.MAX_LOB_LENGTH = &Maximale Bytes für Zeichen- oder Binärspalte:
DATATOOLS.CORE.UI.PREFERENCES.OUTPUT.ERROR_INVALID_INT = Bestimmen sie einen Wert zwischen {0} und {1}.

#
## Index and Trigger Decorations
#
DATATOOLS.CORE.UI.INDEX_UNIQUE.DECORATION = EINDEUTIG
DATATOOLS.CORE.UI.TRIGGER_UPDATE.DECORATION = UPDATE
DATATOOLS.CORE.UI.TRIGGER_DELETE.DECORATION = DELETE
DATATOOLS.CORE.UI.TRIGGER_INSERT.DECORATION = INSERT
DATATOOLS.CORE.UI.TRIGGER_BEFORE.DECORATION = BEFORE
DATATOOLS.CORE.UI.TRIGGER_AFTER.DECORATION = AFTER
DATATOOLS.CORE.UI.TRIGGER_INSTEADOF.DECORATION = ANSTELLE VON

#
## Resource Chooser Dialog
#
_UI_TITLE_FILE_CHOOSER = Dateiauswahl
_UI_TITLE_FOLDER_CHOOSER = Ordnerauswahl
_UI_DESCRIPTION_FILE = Wählen Sie eine Datei aus oder geben Sie einen Namen ein:
_UI_DESCRIPTION_FOLDER = Wählen Sie den übergeordneten Ordner aus oder geben Sie einen Namen ein:
_UI_DEFAULT_MSG_NEW_ALLOWED = Wählen Sie den übergeordneten Ordner aus, oder &geben Sie einen Namen ein:
_UI_DEFAULT_MSG_SELECT_ONLY = Wählen Sie den Ordner aus:

#
## Filter Dialog
#
_UI_TITLE_FILTER_DIALOG = Verbindungs-Filtereigenschaften
_UI_TITLE_FILTER = Filter festlegen
_UI_DESCRIPTION_FILTER = Legen Sie einen Filter durch die Auswahl einer Eigenschaft und durch die Eingabe eines Wertes oder durch die Angabe, ob eine Auswahl an Elementen enthalten oder ausgeschlossen werden soll, fest.
_UI_DESCRIPTION_SELECTION_ONLY = Legen Sie einen Filter durch die Angabe, ob eine Auswahl an Elementen enthalten oder ausgeschlossen werden soll, fest.
_UI_DESCRIPTION_EXPRESSION_ONLY = Legen Sie einen Filter durch die Auswahl einer Eigenschaft und durch die Eingabe eines Wertes fest.
_UI_RADIO_BUTTON_EXPRESSION = &Ausdruck
_UI_LABEL_EXPRESSION = N&ame
_UI_COMBO_STARTS_WITH = Beginnt mit den Zeichen
_UI_COMBO_NOT_START_WITH = Beginnt nicht mit den Zeichen
_UI_COMBO_CONTAINS = Enthält die Zeichen
_UI_COMBO_NOT_CONTAIN = Enthält nicht die Zeichen
_UI_COMBO_ENDS_WITH = Endet mit den Zeichen
_UI_COMBO_NOT_END_WITH = Endet nicht mit den Zeichen
_UI_RADIO_BUTTON_SELECTION = A&uswahl
_UI_COMBO_INCLUDE_ITEMS = Schließen Sie die ausgewählten Elemente ein
_UI_COMBO_EXCLUDE_ITEMS = Schließen Sie die ausgewählten Elemente aus
_UI_BUTTON_SELECT_ALL = &Alles markieren
_UI_BUTTON_DESELECT_ALL = A&uswahl aufheben
_UI_CHECKBOX_DISABLE_FILTER = F&ilter deaktivieren
_UI_MESSAGE_CRITERIA_REQUIRED = Bestimmen Sie die Filterkriterien.
_UI_MESSAGE_NO_QUOTES = Der Ausdruckswert darf keine Anführungszeichen enthalten.
_UI_MESSAGE_SELECTION_REQUIRED = Wählen Sie mindestens ein Element aus.
_UI_SP_FILTER_PAGE_TITLE = Eigenschaften der Filter für gespeicherte Prozedur
_UI_TABLE_FILTER_PAGE_TITLE = Eigenschaften Tabellenfilter
_UI_SCHEMA_FILTER_PAGE_TITLE = Eigenschaften Schemafilter
_UI_TOOLTIP_NEW = Neu
_UI_TOOLTIP_DELETE = Löschen
_UI_RADIO_BUTTON_AND = Entspricht allen Bedingungen
_UI_RADIO_BUTTON_OR = Entspricht einer oder mehreren Bedingungen
_UI_COMBO_PREDICATE = Predicate
_UI_TEXT_VALUE = Wert

#
## Actions for Schema Management
#
SCHEMA_MANAGEMENT_CREATE_SCHEMA = Schema
SCHEMA_MANAGEMENT_CREATE_TABLE = Tabelle
SCHEMA_MANAGEMENT_CREATE_VIEW = Ansicht
SCHEMA_MANAGEMENT_CREATE_INDEX = Index
SCHEMA_MANAGEMENT_CREATE_BUFFER_POOL = Puffer-Pool
SCHEMA_MANAGEMENT_CREATE_PARTITION_GROUP = Partitionsgruppe
SCHEMA_MANAGEMENT_CREATE_DISTINCT_TYPE = Bestimmter benutzerdefinierter Typ
SCHEMA_MANAGEMENT_CREATE_STRUCTURED_TYPE = Strukturierter benutzerdefinierter Typ
SCHEMA_MANAGEMENT_CREATE_SEQUENCE = Sequenz
SCHEMA_MANAGEMENT_CREATE_MQT = MQT
SCHEMA_MANAGEMENT_CREATE_ALIAS = Alias
SCHEMA_MANAGEMENT_CREATE_TRIGGER = Auslösen
SCHEMA_MANAGEMENT_CREATE_ROLE = Rolle
SCHEMA_MANAGEMENT_CREATE_USER = Benutzer
SCHEMA_MANAGEMENT_CREATE_GROUP = Gruppe
#
SCHEMA_MANAGEMENT_ADD_COLUMN = Spalte
SCHEMA_MANAGEMENT_ADD_CHECK_CONSTRAINT = Prüf-Einschränkung
SCHEMA_MANAGEMENT_ADD_UNIQUE_CONSTRAINT = Eindeutige Einschränkung
SCHEMA_MANAGEMENT_ADD_FOREIGN_KEY = Fremdschlüssel
