#!/bin/sh

export CVSROOT=:ext:xgu@dev.eclipse.org:/cvsroot/datatools
export CVS_RSH=ssh

builddate=`date +%Y%m%d`
buildtime=`date +%H%M`
datetimestamp=$builddate$buildtime

WORKING_FOLDER=$1
echo "Current working folder: $WORKING_FOLDER"

#Check out all dtp features

cd $WORKING_FOLDER/
cvs checkout org.eclipse.datatools.build/R1.6_Feature_Projects
cd org.eclipse.datatools.build
echo "New Feature Tag: v$datetimestamp"
cvs tag v$datetimestamp R1.6_Feature_Projects


#Check out releng map file

cd $WORKING_FOLDER
cvs checkout org.eclipse.datatools.releng
cd org.eclipse.datatools.releng/maps
sed s/=.*,:/=v$datetimestamp,:/ dtp-features.map > dtp-features.map.new 
mv dtp-features.map.new dtp-features.map

#Submit the change for dtp map files
cd $WORKING_FOLDER
cvs commit -m "Update feature version to v$datetimestamp (auto)" org.eclipse.datatools.releng

