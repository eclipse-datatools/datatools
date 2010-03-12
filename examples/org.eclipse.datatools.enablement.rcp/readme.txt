#Readme added March 12, 2010
When exporting a RCP product on linux, there seems to be an issue with running the 
generated command-line executable without explicit arguments. So when you run it,
run with arguments appropriate for your environment such as:

	./[myexecutable] -os linux -ws gtk -arch x86
	
