# Chloride Transport Simulator

COMSOL environment combined with Gaussian Process Regression code to assist in the convergence of Nernst Planck Poisson equations for simulating chloride transport in porous media.

## Gaussian Process Regression in Comsol

In the GPRtest folder there is a successful Maven build of running Gaussian Process Regression in the Comsol environment. The build has nothing to do with solving anything in Comsol yet, but it's a start!

In depth usage coming...

## Setup Penn State ICDS-ACI servers to run SMILE (Statistical Machine Intelligence and Learning Engine)

To work with 3rd party libraries when making custom Comsol simulations with Java on the ICDS-ACI servers, we have to make some changes to the normal workflow of compiling Comsol Java. On the bright side, we'll have a new more structured approach that not only allows for Machine Learning in Comsol with the SMILE library, but any 3rd party Java library you wish to be included. This will be done through a custom setup with Maven.

Checkout an example what this Maven project would look like in the TestMavenProject folder.

General outline for setting up your ICDS-ACI environment:
1.) Get proper JDK installed with Anaconda & add to $Path
2.) Download & add Maven to your $Path
3.) Add Comsol .jar files to Maven
4.) Create Maven project and add SMILE dependencies
5.) Load Comsol module
6.) Write Comsol code and compile with Maven
7.) Open .class like normal in Comsol

In depth instructions coming...
