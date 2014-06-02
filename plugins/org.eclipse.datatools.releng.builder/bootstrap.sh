# User specific environment and startup programs
umask 002

BASE_PATH=.:/bin:/usr/bin:/usr/bin/X11:/usr/local/bin:/usr/bin:/usr/X11R6/bin
LD_LIBRARY_PATH=.
BASH_ENV=$HOME/.bashrc
USERNAME=`whoami`
xhost +$HOSTNAME
DISPLAY=:0.0
export DISPLAY

# CVSROOT=:ext:xgu@dev.eclipse.org:/cvsroot/datatools
# CVS_RSH=ssh
ulimit -c unlimited
export USERNAME BASH_ENV LD_LIBRARY_PATH DISPLAY

#cvs update -r HEAD -C -d buildAll.xml build.xml eclipse extras
#dos2unix extras/updateFeatureTag.sh extras/checkCompareDone.sh
#chmod -R +x build.xml buildAll.xml eclipse extras

gitUser=xgu
gitPass=xx
buildEclipseUser=xgu
buildEclipsePass=xx
export GitRepo=ssh://${gitUser}@git.eclipse.org/gitroot/datatools/org.eclipse.datatools.build.git
export BranchName=master
rm -rf plugins
git archive --format=tar --remote=$GitRepo $BranchName plugins/org.eclipse.datatools.releng.builder | tar -xf -
cp -f plugins/org.eclipse.datatools.releng.builder/buildAll.xml ./
cp -f plugins/org.eclipse.datatools.releng.builder/build.xml ./
cp -f plugins/org.eclipse.datatools.releng.builder/git-map.sh ./
cp -f plugins/org.eclipse.datatools.releng.builder/git-submission.sh ./
cp -rf plugins/org.eclipse.datatools.releng.builder/eclipse ./
cp -rf plugins/org.eclipse.datatools.releng.builder/extras ./

if [ "x"$ANT_HOME = "x" ]; then export ANT_HOME=/usr/local/apache-ant-1.7.0; fi
if [ "x"$JAVA_HOME = "x" ]; then export JAVA_HOME=/usr/local/j2sdk1.4.2_13; fi
export PATH=${PATH}:${ANT_HOME}/bin:/usr/local/bin

proc=$$

#notification list
recipients=lchan@actuate.com,xgu@actuate.com
sender=qa-build@actuate.com

#sets skip.performance.tests Ant property
skipPerf=""

#sets skip.tests Ant property
skipTest=""

#sets sign Ant property
sign=""

tagMaps=""

#sets fetchTag="HEAD" for nightly builds if required
tag=""

#buildProjectTags=v20060524
buildProjectTags=v20060529

#updateSite property setting
updateSite=""

#flag indicating whether or not mail should be sent to indicate build has started
mail=""

#flag used to build based on changes in map files
compareMaps=""

#buildId - build name
buildId=""

#buildLabel - name parsed in php scripts <buildType>-<buildId>-<datestamp>
buildLabel=""

# tag for build contribution project containing .map files
mapVersionTag=HEAD

# directory in which to export builder projects
builderDir=$HOME/releng.dtp.1120/org.eclipse.datatools.releng.builder

# buildtype determines whether map file tags are used as entered or are replaced with HEAD
buildType=I

# Integration Build, normally done on Friday
FridayBuild=false

# directory where to copy build
postingDirectory=$HOME/releng/BIRTOutput/dtp.output/1.12.0

# flag to indicate if test build
testBuild=""

# path to javadoc executable
javadoc=""

# value used in buildLabel and for text replacement in index.php template file
builddate=`date +%Y%m%d`
buildtime=`date +%H%M`

buildinfoDate=`date +%F%t%H:%M:%S`
buildinfounivDate=`date +%c%z`

timestamp=$builddate$buildtime

echo "======[builddate]: $builddate " > $USER.log
echo "======[buildtime]: $buildtime " >> $USER.log
echo "======[timestamp]: $timestamp " >> $USER.log

# process command line arguments
usage="usage: $0 [-notify emailaddresses][-test][-buildDirectory directory][-buildId name][-buildLabel directory name][-tagMapFiles][-mapVersionTag tag][-builderTag tag][-bootclasspath path][-compareMaps][-skipPerf] [-skipTest][-updateSite site][-sign] M|N|I|S|R"

if [ $# -lt 1 ]
then
		 echo >&2 "$usage"
		 exit 1
fi

while [ $# -gt 0 ]
do
		 case "$1" in
		 		 -buildId) buildId="$2"; shift;;
		 		 -buildLabel) buildLabel="$2"; shift;;
		 		 -mapVersionTag) mapVersionTag="$2"; shift;;
				 -noAutoTag) noAutoTag=true;;
				 -ForceAutoTag) ForceAutoTag=true;;
				 -Fridaybuild) FridayBuild=true;;
		 		 -tagMapFiles) tagMaps="-DtagMaps=true";;
		 		 -noSign) noSign="-DnoSign=true";;
		 		 -skipPerf) skipPerf="-Dskip.performance.tests=true";;
		 		 -skipTest) skipTest="-Dskip.tests=true";;
		 		 -buildDirectory) builderDir="$2"; shift;;
		 		 -notify) recipients="$2"; shift;;
		 		 -test) postingDirectory="/builds/transfer/files/bogus/downloads/drops";testBuild="-Dtest=true";;
		 		 -builderTag) buildProjectTags="$2"; shift;;
		 		 -compareMaps) compareMaps="-DcompareMaps=true";;
		 		 -updateSite) updateSite="-DupdateSite=$2";shift;;
		 		 -sign) sign="-Dsign=true";shift;;
                                 -upload) upload=true;;
				 -CheckPluginVersion) CheckPluginVersion=true;;
		 		 -*)
		 		 		 echo >&2 $usage
		 		 		 exit 1;;
		 		 *) break;;		 # terminate while loop
		 esac
		 shift
done

# After the above the build type is left in $1.
buildType=$1
echo "======[buildType]: $buildType " >> $USER.log

# Set default buildId and buildLabel if none explicitly set
if [ "$buildId" = "" ]
then
		 #buildId=$buildType$builddate-$buildtime
		 buildId=v$builddate-$buildtime
fi

if [ "$buildLabel" = "" ]
then
		 buildLabel=$buildId
fi
echo "======[buildId]: $buildId " >> $USER.log

#Set the tag to HEAD for Nightly builds
if [ "$buildType" = "N" ]
then
        tag="-DfetchTag=CVS=HEAD,GIT=master"
        versionQualifier="-DforceContextQualifier=$buildId"
fi

echo "======[tag]: $tag" >> $USER.log
echo "======[versionQualifier]: $versionQualifier" >> $USER.log

# tag for eclipseInternalBuildTools on ottcvs1
internalToolsTag=$buildProjectTags
echo "======[internalToolsTag]: $internalToolsTag" >> $USER.log

# tag for exporting org.eclipse.releng.basebuilder
baseBuilderTag=$buildProjectTags
echo "======[baseBuilderTag]: $baseBuilderTag" >> $USER.log

# tag for exporting the custom builder
customBuilderTag=$buildProjectTags
echo "======[customBuilderTag]: $customBuilderTag" >> $USER.log

#if [ -e $builderDir ]
#then
#	 builderDir=$builderDir$timestamp
#fi

# directory where features and plugins will be compiled
buildDirectory=$HOME/releng.dtp.1120/src

echo "======[buildDirectory]: $buildDirectory" >> $USER.log

mkdir -p $builderDir
cd $builderDir

#Pull or clone a branch from a repository
#Usage: pull repositoryURL  branch
pull() {
	mkdir -p $builderDir/gitClones
        pushd $builderDir/gitClones
        directory=$(basename $1 .git)
        if [ ! -d $directory ]; then
                echo git clone $1
                git clone $1
        fi
        popd
        pushd $builderDir/gitClones/$directory
	echo git fetch
        git fetch
        echo git checkout $2
        git checkout $2
	echo git pull origin $BranchName
        git pull origin $BranchName
        popd
}
##################AUTO TAGGING START ###########################

if [ "$buildType" == "N" -o "$noAutoTag" ]; then
	echo "Skipping auto plugins tagging for nightly build or -noAutoTag build"
else
	pushd $builderDir

	#remove comments
	rm -f repos-clean.txt clones.txt
	GitRoot=ssh://${gitUser}@git.eclipse.org/gitroot/datatools
	echo "$GitRoot/org.eclipse.datatools.build.git $BranchName" > repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.connectivity.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.doc.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.general.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.hsqldb.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.ibm.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.ingres.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.msft.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.mysql.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.oda.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.oracle.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.postgresql.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.sap.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.sqlite.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.enablement.sybase.git $BranchName" >> repos-clean.txt
#	echo "$GitRoot/org.eclipse.datatools.incubator.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.modelbase.git $BranchName" >> repos-clean.txt
	echo "$GitRoot/org.eclipse.datatools.sqltools.git $BranchName" >> repos-clean.txt
	
	#clone or pull each repository and checkout the appropriate branch
	while read line; do
        	#each line is of the form <repository> <branch>
        	set -- $line
        	pull $1 $2
        	echo $1 | sed 's/ssh:.*@git.eclipse.org/git:\/\/git.eclipse.org/g' >> clones.txt
	done < repos-clean.txt

	cat clones.txt| xargs /bin/bash git-map.sh $builderDir/gitClones \
        $builderDir/gitClones > maps.txt
		
	#Trim out lines that don't require execution
	grep -v ^OK maps.txt | grep -v ^Executed >run.txt
	if ( cat run.txt | grep sed ); then
		/bin/bash run.txt
                mkdir -p $builderDir/report
                cp report.txt $builderDir/report/report$buildId.txt
		if [ "$FridayBuild" == "true" ]; then
			echo "This is Friday build" >> $USER.log
			echo "DoFridayBuild=false" > checkFridayBuild.properties
		else
			echo "This is not Friday build" >> $USER.log
			echo "DoFridayBuild=true" > checkFridayBuild.properties
		fi
	elif [ "$ForceAutoTag" == "true" ]; then
		echo "Continue to build even if no bundles changed for -ForceAutoTag build" >> $USER.log
	elif [ "$FridayBuild" == "true" ]; then
		source checkFridayBuild.properties
		if [ "$DoFridayBuild" == "true" ]; then
			echo "Detected changes since the previous Friday build, continue to build..." >> $USER.log
			echo "DoFridayBuild=false" > checkFridayBuild.properties
		else
			echo "No changes since the previous Friday Integration build. The 1.12.0 Integration Build ($buildId) is canceled." >> $USER.log
			sendEmail -f lchan@actuate.com -t lchan@actuate.com -cc bpayton@us.ibm.com -s localhost:5025 -u "1.12.0 Integration build ($buildId) is canceled" -m "No changes were detected since the previous Integration build.\nThe 1.12.0 Integration Build ($buildId) is canceled" -l mail.log
			exit
		fi
	else
		echo "No change detected. 1.12.0 Nightly Build ($buildId) is canceled." >> $USER.log
		sendEmail -f xgu@actuate.com -t xgu@actuate.com lchan@actuate.com -cc bpayton@us.ibm.com -s localhost:5025 -u "1.12.0 Nightly build ($buildId) is canceled" -m "No changes were detected in all the DTP Git repositories.\nThe 1.12.0 Nightly Build ($buildId) is canceled" -l mail.log
                exit
	fi
	
	popd
fi
#### END OF auto-tagging ######

mkdir -p $postingDirectory/$buildLabel
#chmod -R 755 $builderDir

#default value of the bootclasspath attribute used in ant javac calls.  
bootclasspath="/usr/local/j2sdk1.4.2_13/jre/lib/rt.jar:/usr/local/j2sdk1.4.2_13/jre/lib/jsse.jar:/usr/local/j2sdk1.4.2_13/jre/lib/jce.jar"
bootclasspath_15="/usr/local/jdk1.5.0_02/jre/lib/rt.jar:/usr/local/jdk1.5.0_02/jre/lib/jsse.jar:/usr/local/jdk1.5.0_02/jre/lib/jce.jar:"
bootclasspath_16="/usr/local/jdk1.6.0/jre/lib/rt.jar:/usr/local/jdk1.6.0/jre/lib/jsse.jar:/usr/local/jdk1.6.0/jre/lib/jce.jar"
jvm15_home="/usr/local/jdk1.5.0_02"

cd $HOME/releng.dtp.1120/org.eclipse.datatools.releng.builder

echo buildId=$buildId >> monitor.properties 
echo timestamp=$timestamp >> monitor.properties 
echo buildLabel=$buildLabel >> monitor.properties 
echo currentDay=$builddate >> monitor.properties
echo recipients=$recipients >> monitor.properties
echo sender=$sender >> monitor.properties
echo log=$postingDirectory/$buildLabel/index.php >> monitor.properties

#the base command used to run AntRunner headless
#antRunner="/usr/local/jdk1.5.0_09/bin/java -Xmx500m -jar ../org.eclipse.releng.basebuilder/plugins/org.eclipse.equinox.launcher.jar -Dosgi.os=linux -Dosgi.ws=gtk -Dosgi.arch=ppc -application org.eclipse.ant.core.antRunner"
#antRunner="/usr/local/j2sdk1.4.2_13/bin/java -Xmx500m -jar ../org.eclipse.releng.basebuilder/plugins/org.eclipse.equinox.launcher.jar -Dosgi.os=linux -Dosgi.ws=gtk -Dosgi.arch=ppc -application org.eclipse.ant.core.antRunner"
antRunner="/usr/local/jdk1.6.0/bin/java -Xmx500m -jar ../org.eclipse.releng.basebuilder/plugins/org.eclipse.equinox.launcher.jar -Dosgi.os=linux -Dosgi.ws=gtk -Dosgi.arch=ppc -application org.eclipse.ant.core.antRunner"

echo "==========[antRunner]: $antRunner" >> $USER.log

#$HOME/releng.dtp.1120/BIRTBuilder/replaceBuildInfo.sh $buildinfoDate $buildinfounivDate

#clean drop directories

#full command with args
#buildId=v20110808-1451
echo $tagMaps >> $USER.log
echo $compareMaps >> $USER.log

PackageVersion=1.12.0RC3-$timestamp
echo "======[PackageVersion]: $PackageVersion" >> $USER.log

#cp $HOME/releng.dtp.1120/dtpURLmonitor.properties $HOME/releng.260/src/

buildCommand="$antRunner -q -buildfile buildAll.xml $mail $testBuild $compareMaps \
-DmapVersionTag=$mapVersionTag -DpostingDirectory=$postingDirectory \
-Dbootclasspath=$bootclasspath_15 -DbuildType=$buildType -D$buildType=true \
-DbuildId=$buildId -Dbuildid=$buildId -DbuildLabel=$buildId -Dtimestamp=$timestamp $skipPerf $skipTest $tagMaps $noSign \
-DJ2SE-1.5=$bootclasspath_15 -DJavaSE-1.6=$bootclasspath_16 -DlogExtension=.xml $javadoc $updateSite $sign \
-Djava15-home=$bootclasspath_15 -DbuildDirectory=$HOME/releng.dtp.1120/src \
-DbaseLocation=$HOME/releng.dtp.1120/baseLocation -Dwtp.home=$HOME/releng.dtp.1120/baseLocation \
-DgroupConfiguration=true -DjavacVerbose=true -DjavacFailOnError=false \
-Dbasebuilder=$HOME/releng.dtp.1120/org.eclipse.releng.basebuilder  \
-Djvm15_home=$jvm15_home  -DmapTag.properties=$HOME/releng.dtp.1120/org.eclipse.datatools.releng.builder/mapTag.properties \
-Dbuild.date=$builddate -Dpackage.version=$PackageVersion \
-DmapGitRoot=ssh://${gitUser}@git.eclipse.org/gitroot/datatools \
-DmapVersionTag=$BranchName -DBranchVersion=1.12.0 \
-Dusername.sign=${buildEclipseUser} -Dpassword.sign=${buildEclipsePass} -Dhostname.sign=build.eclipse.org -Dhome.dir=/home/data/users/${buildEclipseUser} -Dsign.dir=/home/data/httpd/download-staging.priv/birt \
-Dorbit.url.token=download.eclipse.org/tools/orbit/downloads/drops/R20110523182458/repository/plugins \
-Dorbit.url.newvalue=qa-build/BIRTOutput/platform/orbit-S20110521195923-Indigo/bundles"


#-DjavacTarget=1.5 -DjavacSource=1.5 \

#-DmapCvsRoot=:ext:${gitUser}@dev.eclipse.org:/cvsroot/datatools \
#-Ddtp.url.token=:ext:${gitUser}@dev.eclipse.org:/cvsroot/datatools \
#-Ddtp.url.newvalue=:ext:${gitUser}@192.168.218.218:/cvsroot/datatools"

#skipPreBuild

#capture command used to run the build
echo $buildCommand>command.txt

#run the build
$buildCommand >> $USER.log
#retCode=$?
#
#if [ $retCode != 0 ]
#then
#        echo "Build failed (error code $retCode)."
#        exit -1
#fi

#clean up
#rm -rf $builderDir
rm -rf $HOME/releng.dtp.1120/src/$buildId
#cp -f $HOME/releng.dtp.1120/src/directory.txt last_directory.txt

#Audit Check of DTP release plugin version updates
if [ "$CheckPluginVersion" = "true" ]; then
  dos2unix -k $builderDir/extras/CheckDTP_PotentialVersionIssue.sh
  source $builderDir/extras/CheckDTP_PotentialVersionIssue.sh $postingDirectory/$buildId $PackageVersion > CheckDTP_PotentialVersionIssue.log
  if [ -s $builderDir/change.txt ]; then
    sendEmail -f lchan@actuate.com -t xgu@actuate.com lchan@actuate.com -cc bpayton@us.ibm.com \
    -s localhost:5025 \
    -u "Automated script: potential version update issue between $PackageVersion and 1.11.2 Release" \
    -o message-file=$builderDir/change.txt \
    -l mail.log
  fi
fi

#upload build to Eclipse site
if [ "$upload" = "true" ]
then
# expects DTP website Git repo is already checked out in datatools folder under the working directory
  cd $builderDir/uploadScripts
  ant -f dtplogupload.1.12.0.xml -l log/dtplogupload.1.12.0.log -Dusername=${buildEclipseUser} -Dpassword=${buildEclipsePass}
  ant -f dtpupload.1.12.0.xml -l log/dtpupload.1.12.0.log -Dusername=${buildEclipseUser} -Dpassword=${buildEclipsePass} -DintegrationBuild=${FridayBuild}
fi

