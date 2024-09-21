Framework Details:
This framework is highly parameterised. Apart from the general TestRunner, feature and stepDefination, below are some key modules to help understand better this framework

Constant.java <-- This class present at \src\main\java\com\example\utils is used to store all global variables that may be needed
to pass on from one stepDefination method to another. These variable are not only limited to String, int, Boolean, etc. but we could
also assign a reference variable to a class.

Reusable.java <-- This class present at \src\main\java\com\example\utils contains all the selenium backend logic like clicks, scrolls, navigate, etc. Apart from these common method, we also have methods that are used by other classes who's logic were repetitive.

Base.java <-- This class present at \src\main\java\com\example\utils contains all the objects that needs to be loaded prior to starting a testcase execution. E.g. Loading the property file

Page Object classes <-- The classes present under \src\main\java\pageObjects are the POM implementation of the Pages. We have all xpaths and reusable methods specific to the page saved here.


Feature testcases description:
1) Currently below 2 testcase are automated for Core products
●	Test Case 1: for CP --> Store product information in log file
●	Test Case 2: for CP --> Count video feeds based on time criteria

2) These 2 testcases have been added to a feature file named "CoreProductTests.feature" , present in \src\test\featureFileDirectory
3) We could run these testcases on different browsers by mentioning it in the example tag.

How to run:
You could run these testcases via:
1) testng.xml present at root level.
2) testNGRunner class present at \src\test\java\com\example\runner (Here you would need to add another tag attribute)


Where to see Reports:
1) The reporting used here is Extent reporting
2) The extent report is stored in the \test-output folder at root level
3) All necessary logs needed to be attached to this report at run-time are present in \test-output\logDump
