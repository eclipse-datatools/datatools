###############################################################################
# Copyright (c) 2004, 2008 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# https://www.eclipse.org/legal/epl-2.0/
# 
# Contributors:
#  Actuate Corporation - initial API and implementation
#  
###############################################################################
# wizard dialog messages
wizard.title.selectFolder=Source de données XML
wizard.defaultMessage.selectFolder=Définissez les URL du fichier XML et les informations du schéma.
wizard.title.newDataSet=Nouveau jeu de données
wizard.title.selectXmlFile=Sélectionner des données XML pour l'aperçu
wizard.defaultMessage.selectFile=Sélectionnez une source XML exemple pour permettre l'aperçu de données lors de cette session.
wizard.title.xPathChoose=Définir la mise en correspondance de table
wizard.defaultMessage.selectXPath=Définir la mise en correspondance de la ligne du jeu de données en sélectionnant un élément de la structure XML.\nChaque occurrence de l'élément XML sélectionné dans le document XML est mis en correspondance avec une ligne du jeu de données.
wizard.title.selectColumn=Définir la mise en correspondance de colonne
wizard.title.defineColumnMapping=Créez des colonnes de jeux de données en sélectionnant et en ajoutant des éléments provenant de la structure XML.\nChaque attribut ou chaque élément XML ajouté est associé à une colonne de jeu de données. 

#preference
designer.preview.preference.numberOfElementPassedToGetSchema=Nombre d'éléments de données à analyser pour le schéma XML :
designer.preview.preference.numberOfElementPassedToGetSchema.errormessage=Entrez un nombre compris entre 0 et {0}.
designer.preview.preference.columnMappingPage.group.title=Mise en correspondance de colonne

#XML data source folder selection wizard page
XMLSelectionWizardPage.message.error.emptyXMLSource=Veuillez préciser l'URL source XML ou sélectionner le fichier XML.

#row mapping page messages
xPathChoosePage.messages.elementSelection.label=Sélection de l'élément XML
xPathChoosePage.messages.elementSelection.item.absolutePath=Eléments &XML intitulés "{0}" sur un chemin d'accès absolu fixe\n
xPathChoosePage.messages.elementSelection.item.anyLocation=&Eléments XML intitulés "{0}" n'importe où\n
xPathChoosePage.messages.elementSelection.disable.absolutePath=Eléments &XML à tout chemin d'accès absolu fixe\n 
xPathChoosePage.messages.elementSelection.disable.anyLocation=&Eléments XML n'importe où\n 
xPathChoosePage.messages.elementSelection.item.custom=Exp&ression XPath personnalisée
xPathChoosePage.messages.xPathExpression=Expression &XPath
xPathChoosePage.messages.xmlStructure=Structure XML
xPathChoosePage.messages.xmlColumnMapping=Mise en correspondance de colonne
xPathChoosePage.messages.xpathChange=Vérifiez que la mise en correspondance de table modifiée est compatible avec la mise en correspondance de colonne le cas échéant.   
xPathChoosePage.FileNameIcon=Icône du nom de fichier
xPathChoosePage.XMLElementIcon=Icône d'élément XML
xPathChoosePage.ColumnIcon=Icône de colonne
xPathChoosePage.messages.btnAdd.tooltip=Utilisez le nœud sélectionné comme expression XPath.
xPathChoosePage.messages.noURLOfSourceOrSchema=Aucune URL de source XML ou de schéma XML fournie.

#sample xml data file page messages
xmlDataSetSelectionPage.messages.lineofdata=premières lignes de données source
xmlDataSetSelectionPage.warning.errorReadXMLFile=Erreur lors de la lecture des données XML à partir du fichier [{0}]\n{1}. Voulez-vous tout de même continuer ?

#column mapping page messages
button.refresh=Actualiser
menu.menuItem.removeAll=Supprimer tout
menu.menuItem.remove=Supprimer
menu.button.add=&Ajouter...
menu.button.edit=Modifi&er...
menu.button.remove=Supp&rimer
menu.button.up=Vers le ha&ut
menu.button.down=Vers le &bas
menu.button.preview=Afficher le&s exemples de données
lable.selectXmlFile=Entrez l'URL de la source &XML ou accédez au fichier contenant les données :
label.selectEncoding=Sélectionner le &codage du schéma et de la source XML spécifiés ci-dessus :
lable.selectXmlSchmaFile=Entrez l'URL du schéma X&ML ou accédez au fichier contenant le\n schéma. Ne renseignez pas ce champ si aucun schéma n'est disponible :
label.preview=A&perçu des
label.useXMLFileFromDataSource=&Utilisez le fichier XML défini dans la source de données.
RowMappingDialog.title=Sélectionner ou modifier l'expression XPath.
RowMappingDialog.info=Sélectionnez une des expressions XPath prédéfinies pour le noeud sélectionné ou\n entrez une autre expression :
ColumnMappingPage.AddSingleButton.tooltip=Utilisez le nœud choisi comme mise en correspondance de colonne.
ColumnMappingPage.AddAllButton.tooltip=Utilisez toutes les colonnes filles du nœud choisi comme mise en correspondance de colonne.
ColumnMappingTable.upButton.tooltip=Déplacez la mise en correspondance de colonne vers le haut.
ColumnMappingTable.downButton.tooltip=Déplacez la mise en correspondance de colonne vers le bas.
ColumnMappingTable.removeButton.tooltip=Supprimez la mise en correspondance de colonne.
ColumnMappingTable.editButton.tooltip=Modifiez la mise en correspondance de colonne.
ColumnMappingTable.previewButton.tooltip=Affichez les données XML exemples. 
ColumnMappingDialog.group.message=Sélection XPath
ColumnMappingDialog.info.columnName=Nom de &colonne :
ColumnMappingDialog.info.xPath=Expression &XPath :
ColumnMappingDialog.info.dataType=Type de &données :
ColumnMappingDialog.dialog.info=Sélectionnez une des expressions XPath prédéfinies pour la colonne en cours ou entrez une nouvelle expression XPath. 
ColumnMappingDialog.prompt.maxRow=La source de données dépasse 500 lignes. Cette page ne permet d'afficher que les 500 premières lignes.
ColumnMappingDialog.MenuItem.CreateParameter=Créer un paramètre
ColumnMappingDialog.MenuItem.DeleteParameter=Supprimer le paramètre
XMLDataPreviewDialog.title=Données XML exemples

dataset.editor.columnName=Nom
dataset.editor.xpathexpression=XPath
dataset.editor.datatype=Type
dataset.editor.rowelement=Elément de ligne
dataset.error.populateXMLTree=La source de données XML ne peut pas être récupérée. Le fichier de source de données XML est incorrect ou le fichier n'existe pas.
dataset.error.CannotPopulateXMLTree=La source de données XML ne peut pas être récupérée.

error.label=Erreur
error.selectFolder=L'URL de fichier sélectionnée n'est pas valide ou le fichier n'existe pas.
error.emptyPath=L'URL du fichier est vide.
error.xpath.canNotContainAttribute=L'expression XPath ne peut mettre en correspondance l'attribut XML.
error.xpath.getPathList=Une erreur s'est produite lors de la récupération de la liste XPath par l'élément d'arborescence XML sélectionné.
error.dataset.Error.MissingFileLocation=Veuillez préciser l'emplacement du fichier XML.
error.dataset.maxRowNumberError=Le nombre maximal de lignes doit être renseigné par un nombre.
error.columnMapping.createPage=Une erreur s'est produite dans la page de mise en correspondance de colonne 
error.columnMapping.columnElement.create=Une erreur s'est produite lors de la mise en correspondance d'un élément de mise en correspondance de colonne.
error.columnMapping.columnElement.edit=Une erreur s'est produite lors de la modification d'un élément d'une colonne de mise en correspondance.
error.columnMapping.ATreeNode.getChildren=Une erreur s'est produite lors le la récupération des nœuds enfants de l'élément XML sélectionné.
error.columnMapping.SelectedTreeItem.notNull=Aucun élément d'arborescence n'est sélectionné pour créer un élément de mise en correspondance de colonne.
error.columnMapping.noMappingColumn=Veuillez mettre au moins une colonne en correspondance.
error.columnMapping.tableMappingXPathNotExist=L'expression XPath de mise en correspondance de table n'existe pas.
error.file.null=Toutes les URL sont vides. Vous n'avez donc pas sélectionné de source de données. Souhaitez-vous continuer ?
error.columnMapping.sameColumnName=Le nom de colonne [{0}] existe déjà. Utilisez un autre nom.
error.invalidXpath=L'expression XPath est incorrecte.
error.invalidSource=Fichier source xml introuvable.

#data types for column mapping
datatypes.dateTime=Date Heure
datatypes.decimal=Décimales
datatypes.float=Flottant
datatypes.integer=Entier
datatypes.string=Chaîne
datatypes.date=Date
datatypes.time=Heure
datatypes.boolean=Booléen

#column name in column mapping table
columnName.name=Nom
columnName.xPathName=XPath
columnName.typeName=Type

dataset.new=Nouveau jeu de données
file.choose=Parc&ourir...
schema.choose=&Parcourir...
title.warning=Avertissement
FileSelectionButton.menuItem.absolutePath=Chemin d'accès &absolu
FileSelectionButton.menuItem.relativePath=Chemin d'accès &relatif
SelectRelativeFileDialog.Title=Sélectionner un fichier

datasource.propertyPage.tooltip=Source de données XML
wizard.autoEncoding=Auto
