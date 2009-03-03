#
#************************************************************************
# Copyright (c) 2007 Actuate Corporation.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
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
webServiceSelectionPage.group.endPoint=&SOAP End Point
webServiceSelectionPage.group.customClass=&Custom Driver Class
webServiceSelectionPage.group.classPath=&Driver Class Path
webServiceSelectionPage.label.wsdl=Si le descripteur WSDL des Services Web est disponible, entrez son URL ou recherchez son emplacement dans le système de fichiers local.
webServiceSelectionPage.label.wsdlURI=URL ou emplacement du descripteur &WSDL :
webServiceSelectionPage.label.endPoint=Specify the SOAP End Point URL. You may omit this value if the end point defined in the WSDL is to be used,\nor if you are using a custom connection class that does not require a end point URL.
webServiceSelectionPage.label.customClass=If connection and query to the Web Services is made through a custom Java class, enter the fully qualified class name here.
webServiceSelectionPage.label.classPath=Liste des répertoires et des fichiers JAR supplémentaires séparés par des points-virgules (;) dans laquelle effectuer une recherche de la classe de pilote personnalisée.
webServiceSelectionPage.button.browse=B&rowse...

# OperationPage
operationPage.message.default=Sélectionnez une opération définie dans le descripteur WSDL de la source de données.
operationPage.message.operationChanged=Confirmez pour générer à nouveau la requête SOAP et reconnecter le point de fin.
operationPage.title.operationChanged=Opération modifiée
operationPage.label.selectOpearation=&Selected Operation
operationPage.label.document=&Documentation

# SoapParametersPage
soapParametersPage.message.default=Entrez les paramètres de l'opération WSDL sélectionnée.
soapParametersPage.button.selectAll=&Sélectionner tout
soapParametersPage.button.deselectAll=&Désélectionner tout
soapParametersPage.column.paramName=N&ame
soapParametersPage.column.dataType=Data &Type

# SoapRequestPage
soapRequestPage.message.default=Modifiez le modèle de requête SOAP de ce jeu de données. Inutile de remplir cette zone si le code de classe de la connexion personnalisée de votre source de données ne requiert aucun texte de requête.
soapRequestPage.message.regenerateTemplate=Le modèle de texte de requête sera à nouveau généré d'après l'opération SOAP sélectionnée. Les modifications que vous avez apportées au texte seront supprimées.\n\nVous confirmez ?
soapRequestPage.title.regenerateTemplate=Générer à nouveau un modèle
soapRequestPage.button.regenerateTemplate=Génére&r à nouveau un modèle
soapRequestPage.button.insertParameter=&Edit Parameter...
soapRequestPage.button.clear=&Effacer
soapRequestPage.paramEditDialog.title=Modifier la propriété de paramètre

# ParameterInputDialog
parameterInputDialog.column.name=&Name
parameterInputDialog.column.type=Data &Type
parameterInputDialog.column.defaultValue=Default &Value
parameterInputDialog.button.edit=&Edit...

# SoapResponsePage
soapResponsePage.message.default=Modifiez la réponse SOAP.
soapResponsePage.group.schema=Sélectionnez le schéma de réponse SOAP.
soapResponsePage.group.xml=Sélectionnez l'exemple de message de réponse SOAP.
soapResponsePage.radio.defaultSchema=Utilisez le schéma de réponse d'&opération défini dans la source de données WSDL.
soapResponsePage.radio.reponseSchema=Utilisez le schéma de la &réponse.
soapResponsePage.radio.externalSchema=Use external &XML schema file:
soapResponsePage.radio.endPoint=Services Web de la requête au point de &fin SOAP :
soapResponsePage.radio.externalXML=Utiliser l'exemple de fichier de &données externe
soapResponsePage.button.browse1=Bro&wse...
soapResponsePage.button.browse2=Brow&se...
soapResponsePage.button.connect=&Se connecter maintenant

# XMLTableMappingPage

# XMLColumnMappingPage

# WSConsole
wsConsole.message.error.cantRetrieveSOAPResponse=La réponse SOAP ne peut pas être récupérée, vérifiez le réseau ou le paramètre de la réponse SOAP.
