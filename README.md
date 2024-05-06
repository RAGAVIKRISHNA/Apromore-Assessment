# **Introduction**

* Java Selenium framework to test the Jupiter Toys webpage.The framework is designed with data driven approach to source different sets of test data using TestNG DataProvider.It also integrates with Extent Reports for the presentable Reporting.
* Detailed capturing of images in extent reporting are done in such a way that for every step run the test case , the "Expected vs Actual" images are merged together for easy identification for Passed/Failed tests.
* Expected base lined images are sourced and placed inside "expected" folder in resources directory.
* Actual captured images are placed inside "temp" folder in resources directory.
* Code then merges both expected and actual and presents in extent test report for better viewing and presentability. 
* Ashot 3rd party library has been used for image comparison.

## Test Scenario
* Launch the Jupiter toys webpage and provide feedback by entering required fields  
* Add two valentines bear to the shopping cart 
* It captures screenshots at each step and compares them to expected images

# **Demo link**

   https://youtu.be/6Xqu2NnF8l4


# **Requirements**

- Download and Install Java : https://www.java.com/download/ie_manual.jsp
- Download Apache Maven : https://maven.apache.org/download.cgi 
- Download and Install IDE of your choice
- Browser driver - Chrome driver exe : https://chromedriver.chromium.org/downloads
(make sure you have your desired browser driver and class path is your user dir folder where your POM file is.
- IDE Plugins for Maven & TestNG

# **Features**

- Page Object Design Pattern with Selenium PageFactory
- Use of Data Driven Testing using TestNG Data Provider 
- Reporting using extent repor

# **Used tools and framework**
- Selenium
- Maven repository
- Extent Report

# **Test Automation Implementation Approach**

	The automation framework is built using the following implementation approach

	  1. Chrome WebDriver to automate actions on browser.
      2. The framework is build using Java Selenium and OOPS concepts such as Inheritance, abstraction.
      3. TesNG to manage/organize test cases, run same tests with different set of data using data provider, extent reports for reporting.
      4. Have implemented the framework using the test design patterns such as Page object model, page factory to initialise the web elements and classes design bearing single responsibility approach. 
      5. Have used approriate waits and handled exceptions gracefully wherever required.
      
	
# **Execution**

1. Maven build

    "mvn clean install" or "mvn clean install -U" will execute tests by default and Extent report can be found under "projectdir\target\Spark.html"

 
2. Using IDE

    Right click and run using testng.xml 

