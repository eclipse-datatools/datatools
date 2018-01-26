# Eclipse Data Tools #

As of January 2018, Eclipse Data Tools (DTP) is now build with [Maven 3.5 & Tycho 1.0](https://maven.apache.org/download.cgi).


## Building locally ##

First, fetch the code:

```git clone ssh://{YOUR-ECLIPSE-USER}@git.eclipse.org:29418/datatools/org.eclipse.datatools```

or

```git clone http://git.eclipse.org/gitroot/datatools/org.eclipse.datatools.git/```

then

```cd org.eclipse.datatools```

To build:

```mvn clean install```

To build without tests:

```mvn clean install -DskipTests```


## Contributions ##

To contribute, submit a gerrit request against this repo:

[http://git.eclipse.org/c/datatools/org.eclipse.datatools.git/](http://git.eclipse.org/c/datatools/org.eclipse.datatools.git/)

You can then see your submitted requests here:

[https://git.eclipse.org/r/](https://git.eclipse.org/r/)


## Continuous Builds ##

The master branch is currently built here:

[https://hudson.eclipse.org/datatools/job/org.eclipse.datatools_master/](https://hudson.eclipse.org/datatools/job/org.eclipse.datatools_master/)

Therefore the latest CI build can be found here:

[https://hudson.eclipse.org/datatools/job/org.eclipse.datatools_master/lastSuccessfulBuild/artifact/site/target/](https://hudson.eclipse.org/datatools/job/org.eclipse.datatools_master/lastSuccessfulBuild/artifact/site/target/)


## Deprecated Code

Note that this repo is now the combination of the other [18 org.eclipse.datatools.\*.git repos](http://git.eclipse.org/c/datatools/), which means the other repos are deprecated. Should you want to run the old Ant-based build, check out the 19 repos and then run the build in org.eclipse.datatools, from the ```DTP_1_14_1_Release``` tag, not from the master branch.
