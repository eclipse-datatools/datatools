#
#************************************************************************
# Copyright (c) 2007 Actuate Corporation.
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
# Externalized messages of the Web Services Data Source Designer

#page.type.name

# Common

# WebServiceSelectionPage
webServiceSelectionPage.message.default=Source de données Services Web
webServiceSelectionPage.message.error=Spécifiez l'URL de point de fin SOAP ou sélectionnez un descripteur WSDL.
webServiceSelectionPage.group.wsdl=Descripteur WSDL
webServiceSelectionPage.group.endPoint=Point de fin &SOAP
webServiceSelectionPage.group.customClass=&Classe de pilote personnalisée
webServiceSelectionPage.group.classPath=Che&min de la classe de pilote
webServiceSelectionPage.label.wsdl=Si le descripteur WSDL des Services Web est disponible, entrez son URL ou recherchez son emplacement dans le système de fichiers local.
webServiceSelectionPage.label.wsdlURI=URL ou emplacement du descripteur &WSDL :
webServiceSelectionPage.label.endPoint=Spécifiez l'URL du point de fin SOAP. Cette valeur est facultative si le point de fin défini dans le WSDL doit être utilisé,\nou si vous utilisez une classe de connexion personnalisée qui ne requiert pas d'URL de point de fin.
webServiceSelectionPage.label.customClass=Si les connexions et les requêtes vers les Services Web sont effectuées par l'intermédiaire d'une classe Java personnalisée, indiquez ici le nom de classe complet.
webServiceSelectionPage.label.classPath=Liste des répertoires et des fichiers JAR supplémentaires séparés par des points-virgules (;) dans laquelle effectuer une recherche de la classe de pilote personnalisée.
webServiceSelectionPage.button.browse=Pa&rcourir...

# OperationPage
operationPage.message.default=Sélectionnez une opération définie dans le descripteur WSDL de la source de données.
operationPage.message.regenerate.SOAPRequest=Générez à nouveau la requête SOAP et reconnectez le point de fin selon l'opération modifiée.
operationPage.title.operationChanged=Opération modifiée
operationPage.label.selectOpearation=Opération &sélectionnée
operationPage.label.document=&Documentation

# SoapParametersPage
soapParametersPage.message.default=Entrez les paramètres de l'opération WSDL sélectionnée.
soapParametersPage.button.selectAll=&Sélectionner tout
soapParametersPage.button.deselectAll=&Désélectionner tout
soapParametersPage.column.paramName=N&om
soapParametersPage.column.dataType=Type de &données

# SoapRequestPage
soapRequestPage.message.defaultTitle=Modifiez le modèle de requête SOAP de ce jeu de données. 
soapRequestPage.message.regenerateTemplate=Le modèle de texte de requête sera à nouveau généré d'après l'opération SOAP sélectionnée. Les modifications que vous avez apportées au texte seront supprimées.\n\nVous confirmez ?
soapRequestPage.label.prompt=Inutile de remplir cette zone si la classe de connexion personnalisée ne requiert aucun texte de requête.
soapRequestPage.title.regenerateTemplate=Générer à nouveau un modèle
soapRequestPage.button.regenerateTemplate=Génére&r à nouveau un modèle
soapRequestPage.button.insertParameter=Modifi&er les paramètres...
soapRequestPage.button.clear=&Effacer
soapRequestPage.paramEditDialog.title=Modifier la propriété de paramètre

# ParameterInputDialog
parameterInputDialog.column.name=&Nom
parameterInputDialog.column.type=Type de &données
parameterInputDialog.column.defaultValue=&Valeur par défaut
parameterInputDialog.button.edit=Modifi&er...

# SoapResponsePage
soapResponsePage.message.default=Modifiez la réponse SOAP.
soapResponsePage.group.schema=Sélectionnez le schéma de réponse SOAP.
soapResponsePage.group.xml=Sélectionnez l'exemple de message de réponse SOAP.
soapResponsePage.radio.defaultSchema=Utilisez le schéma de réponse d'&opération défini dans la source de données WSDL.
soapResponsePage.radio.reponseSchema=Utilisez le schéma de la &réponse.
soapResponsePage.radio.externalSchema=Utiliser le fichier de schéma &XML externe :
soapResponsePage.radio.endPoint=Services Web de la requête au point de &fin SOAP :
soapResponsePage.radio.externalXML=Utiliser l'exemple de fichier de &données externe
soapResponsePage.button.browse1=Par&courir...
soapResponsePage.button.browse2=&Parcourir...
soapResponsePage.button.connect=&Se connecter maintenant

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=La réponse SOAP ne peut pas être récupérée, vérifiez le réseau ou le paramètre de la réponse SOAP.
