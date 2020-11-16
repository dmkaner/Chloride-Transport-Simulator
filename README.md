# Chloride Transport Simulator

COMSOL environment combined with Gaussian Process Regression code to assist in the convergence of Nernst Planck Poisson equations for simulating chloride transport in porous media.

## Gaussian Process Regression in Comsol

In the GPRtest folder there is a successful Maven build of running Gaussian Process Regression in the Comsol environment. The build has nothing to do with solving anything in Comsol yet, but it's a start!

In depth usage coming...

## Setup Penn State ICDS-ACI servers to run SMILE (Statistical Machine Intelligence and Learning Engine)

To work with 3rd party libraries when making custom Comsol simulations with Java on the ICDS-ACI servers, we have to make some changes to the normal workflow of compiling Comsol Java. On the bright side, we'll have a new more structured approach that not only allows for Machine Learning in Comsol with the SMILE library, but any 3rd party Java library you wish to be included. This will be done through a custom setup with Maven.

Checkout an example what this Maven project would look like in the TestMavenProject folder.

**General outline for setting up your ICDS-ACI environment:**
1. Get proper JDK installed with Anaconda & add to $Path
2. Download & add Maven to your $Path
3. Add Comsol .jar files to Maven
4. Load Comsol module
5. Create Maven project and add SMILE dependencies
6. Compile and open .class like normal in Comsol

**Get proper JDK installed with Anaconda & add to $Path**
First thing we need to do is get JDK 11 in your workspace on the server, so if you have an account, ssh in so we can get started. 

Simply enter these commands and we'll have Java ready to go for the project:
```
$ module load python/3.6.3-anaconda5.0.1
$ conda create -n jdk-11
$ source activate jdk-11
$ conda install -c conda-forge openjdk
$ export JAVA_HOME=~/.conda/envs/jdk-11/
$ export PATH=$JAVA_HOME/bin:$PATH
```

Keep in mind you will need to **enter the first, third, fourth, and fifth commands everytime you open up a terminal to compile Java code for this project.** There are some additional commands like this aswell, they will all be included lower in the readme.

**Download & add Maven to your $Path**
Now we need to install Maven, a Java build automation tool which makes it possible for us to use SMILE and COMSOL dependencies together. I suggest make a directory for this and to store our Java projects for Comsol. Once you're inside that directory, download and unzip Maven in it with the following commands:

```
$ wget -P ./ "https://apache.claz.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.zip"

$ unzip apache-maven-3.6.3-bin.zip
```

You then need to to add Maven to your path with the following command, which you will also **need to do every time you open up a new terminal** on the server (Replace abc1234 with your psu username):

```
$ export PATH=/storage/home/abc1234/work/MavenBuilds/apache-maven-3.6.3/bin:$PATH
```

To test the maven installation enter this command:

```
$ mvn -v
```

You should see the proper version number and no errors pop up after that.

**Add Comsol .jar files to Maven**
We now must manually add the Comsol jar files into Maven, for there is no online dependency for it we can directly reference for the API. All we have to do is simply enter the commands included in the maven_include_jar_cmds.txt file. The file is in this repo, just open it up and manually enter each command into your ssh session.

**Load Comsol module**
Simply enter this command:
```
$ module load comsol
```

**Create Maven project and add SMILE dependencies**
For this part you'll want to just download the included GPRtest folder to your server environment and run the demo. It is a Maven project setup exactly how we need for compiling other dependecies along side the Comsol API. Notice dependencies are added in the pom.xml file, where both the SMILE and COMSOL dependencies are included. When you are making your own projects, simply take this project and change out the java files for what you need.

**Compile and open .class like normal in Comsol**
Compile Maven projects with the following command:
```
mvn compile
```

This will then produce a .class file in the target directory of the project. This is file you will open in Comsol to view your project. When opening these .class files in Comsol, you may experience an error. To fix this, select File->Preferences->Security, then in "Java and Java Libraries" set option "File system access:" to "All Files". 


Keep in mind you will need to **enter some of the commands everytime you open up a terminal to compile Java code for this project.** You might just want to put them in a file that looks like this to run everytime you ssh in:
```
$ module load python/3.6.3-anaconda5.0.1
$ source activate jdk-11
$ export JAVA_HOME=~/.conda/envs/jdk-11/
$ export PATH=$JAVA_HOME/bin:$PATH
$ export PATH=/storage/home/abc1234/work/MavenBuilds/apache-maven-3.6.3/bin:$PATH
$ module load comsol
```


## Setup on a regular computer (Non Penn State Server)

instructions coming soon
