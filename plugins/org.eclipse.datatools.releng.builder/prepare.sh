folder=/home/adb/releng.dtp.1120
rm -rf $folder/src/plugins/*
rm -rf $folder/src/updateJars
rm -rf $folder/src/features/*
rm -rf $folder/src/maps/*
rm -rf $folder/src/org.eclipse.datatools.build
rm -rf $folder/src/org.eclipse.datatools.releng
rm -rf $folder/src/*.jar
rm -rf $folder/src/nestedJars

rm -rf $folder/src/scmCache
#rm -rf $folder/org.eclipse.releng.basebuilder/plugins/org.eclipse.pde.build_3.7.0.v20110512-1320/scripts/scmCache/*

rm -rf $folder/src/tmpsite* $folder/src/*.properties $folder/src/*.txt $folder/src/*.xml $folder/src/dtp.sc
rm -f  $folder/org.eclipse.datatools.releng.builder/monitor.properties
rm -f /home/adb/releng/BIRTOutput/dtp.output/1.12.0/monitor.properties
