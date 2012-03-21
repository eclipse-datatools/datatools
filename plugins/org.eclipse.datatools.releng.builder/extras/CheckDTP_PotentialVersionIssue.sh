#Script that can automatically compare each plugin's version between the current version and the last release version.
#And send the list of plugins that have potential version update issue in an email to the PMC members.
#For example, the org.eclipse.datatools.enablement.oda.xml plugin has version 1.2.1.v201104121500 in the last release 1.9.1.
#The plugin has apparently changes in 1.9.2 nightly build, with a later datetime stamp, but it still has version 1.2.1.
#So this plugin would be a candidate to alert the PMC that the plugin micro/minor/major version should have been incremented.

if [ $# -lt 2 ]; then
	echo "USAGE: $0 CurrentBuildPostDirectory CurrentBuildId"
	echo "Example: $0 $HOME/releng/BIRTOutput/dtp.output/1.9.2/v20111202-1046 1.9.2RC1-201112021046"
	exit 1
fi

CurrentPostDir=$1
CurrentBuildId=$2
LastReleasePostDir=$HOME/releng/BIRTOutput/dtp.output/1.9.2/v20120205-0500-Release
LastReleaseBuildId=1.9.2RC3-201202050500

if [ ! -e $CurrentPostDir/dtp-sdk-${CurrentBuildId}.zip ]; then
	echo "Currect build does not exist,cancel comparison script."
	exit
fi

echo "Start running comparison script"
rm -rf DtpCurrent DtpLast
rm -f nochange.txt change.txt allchange.txt excludechange.txt

echo "unzip -q -o $CurrentPostDir/dtp-sdk-${CurrentBuildId}.zip -d DtpCurrent"
unzip -q -o $CurrentPostDir/dtp-sdk-${CurrentBuildId}.zip -d DtpCurrent
echo "unzip -q -o $CurrentPostDir/dtp-incubator-sdk-${CurrentBuildId}.zip -d DtpCurrent"
unzip -q -o $CurrentPostDir/dtp-incubator-sdk-${CurrentBuildId}.zip -d DtpCurrent

echo "unzip -q -o $LastReleasePostDir/dtp-sdk-${LastReleaseBuildId}.zip -d DtpLast"
unzip -q -o $LastReleasePostDir/dtp-sdk-${LastReleaseBuildId}.zip -d DtpLast
echo "unzip -q -o $LastReleasePostDir/dtp-incubator-sdk-${LastReleaseBuildId}.zip -d DtpLast"
unzip -q -o $LastReleasePostDir/dtp-incubator-sdk-${LastReleaseBuildId}.zip -d DtpLast

ls DtpCurrent/eclipse/plugins/ | grep org.eclipse.datatools | grep -v .source_ > DtpCurrent.txt
ls DtpLast/eclipse/plugins/ | grep org.eclipse.datatools | grep -v .source_ > DtpLast.txt

while read LINE; do
	PluginName=$( echo $LINE | sed 's/\(.*datatools.*\)_.*/\1/g' )
	MajorVersion=$( echo $LINE | sed 's/.*_\([0-9]*\.[0-9]*\.[0-9]*\)\..*/\1/g' )
	TimeStamp=$( echo $LINE | sed 's/.*_[0-9]*\.[0-9]*\.[0-9]*\.\(.*\)\.jar/\1/g' )
	
	LINE2=$( cat DtpCurrent.txt | grep ${PluginName}_ )
	PluginName2=$( echo $LINE2 | sed 's/\(.*datatools.*\)_.*/\1/g' )
        MajorVersion2=$( echo $LINE2 | sed 's/.*_\([0-9]*\.[0-9]*\.[0-9]*\)\..*/\1/g' )
        TimeStamp2=$( echo $LINE2 | sed 's/.*_[0-9]*\.[0-9]*\.[0-9]*\.\(.*\)\.jar/\1/g' )
	
	if [ "$TimeStamp" = "$TimeStamp2" ]; then
		echo "$PluginName: no change since last release"
		echo "$PluginName" >> nochange.txt
	elif [ "$MajorVersion" = "$MajorVersion2" ]; then
		#Exclude current bundle version timestamp of v20110722 in list of plugins with potential version issue
		#This seems to be the changes made to "rename .cvsignore to .gitignore" on 7/22 when we first migrated to Git
		if ( echo "$TimeStamp2" | grep v20110722 >/dev/null ); then
			echo "$PluginName: Exclude in list of plugins with potential version issue"
                        echo "$PluginName" >> excludechange.txt
                        echo "Last_Release_Version: $MajorVersion.$TimeStamp" >> excludechange.txt
                        echo "Current_Version:      $MajorVersion2.$TimeStamp2" >> excludechange.txt
                        echo "====================" >> excludechange.txt
		else
			echo "$PluginName: TimeStamp changed but Major Version do not change"
			echo "$PluginName" >> change.txt
			echo "Last_Release_Version: $MajorVersion.$TimeStamp" >> change.txt
			echo "Current_Version:      $MajorVersion2.$TimeStamp2" >> change.txt
			echo "====================" >> change.txt		
		fi
	else
		echo "$PluginName: TimeStamp and Major Version both changed"
		echo "$PluginName" >> allchange.txt
		echo "Last_Release_Version: $MajorVersion.$TimeStamp" >> allchange.txt
                echo "Current_Version:      $MajorVersion2.$TimeStamp2" >> allchange.txt
                echo "====================" >> allchange.txt

	fi
done < DtpLast.txt
