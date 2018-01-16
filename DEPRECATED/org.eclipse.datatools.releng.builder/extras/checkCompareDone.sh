usage="usage: $0 Path_of_cvsDiff.txt DTP_Version \nExample: $0 /home/adb/releng.dtp.182/src 182"
if [ $# -lt 2 ]
then
     echo -e >&2 "$usage"
     exit 1
fi

if [ ! -e $1/cvsDiff.txt ]
then
  sendEmail -f xgu@actuate.com -t yjiang@actuate.com xgu@actuate.com -s localhost:5025 -u "Compare map files time out for today's DTP $2 nightly build" -m "Remind: compare map files time out for today's $2 nightly build,please have a check" -l mail.log
fi