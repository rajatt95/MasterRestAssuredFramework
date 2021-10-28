# MasterRestAssuredFramework
With further updates - This project is designed and developed with the help of -
1. Udemy course (OmPrakash Chavan - REST Assured API Automation + Framework: From Zero to Hero!)
2. YouTube playlist (Testing Mini Bytes)

------------------------------------------------------------
![image](https://user-images.githubusercontent.com/26399692/137711606-67ffd3dc-41e9-4f46-8f6b-2b8072c08b30.png)
------------------------------------------------------------
Updations done:
------------------------------------------------------------
**ExtentReports V5** 
1. User can apply the Filters - 
- Author - Rajat, Nishant, Gautam, Pankaj 
- TestType - Smoke, Sanity, Regression, BVT

2. Screenshots are attached in the ExtentReport as Base64 format.
![image](https://user-images.githubusercontent.com/26399692/137711916-f8dfae4b-836b-428c-8434-c40af4ea8317.png)
![image](https://user-images.githubusercontent.com/26399692/137711899-bb176614-59c9-4f3c-ae69-cce98aafdda3.png)

------------------------------------------------------------
**Allure Reports** 
![image](https://user-images.githubusercontent.com/26399692/137712191-69c8df13-15f2-4dff-8187-ac5ffc6cb5b6.png)
------------------------------------------------------------
**User has options for customization**
![image](https://user-images.githubusercontent.com/26399692/137712268-96a650d9-8675-413e-bdef-707ffcf29c21.png)
------------------------------------------------------------
**Email to User(s) using Java mail API**
![image](https://user-images.githubusercontent.com/26399692/137712367-88a2fd25-89fb-4cea-8b15-d60b71bc459e.png)
------------------------------------------------------------
**Others implementations:**
1. Retry failed test cases
2. Custom Enums, Exceptions, Annotations 
3. Serialization and Deserialization using Jackson-Databind dependency
4. Zip the ExtentReports directory into Project path (you can send this Zip file as well as an Attachment in Email)
5. Automatically open the report after tests execution.
6. Request and Response details added in ExtentReports:
7. Send EMail using Java mail API to User(s) with attachment(s).  
 - https://mvnrepository.com/artifact/javax.mail/mail/1.4.7
 - https://www.tutorialspoint.com/java/java_sending_email.htm
 - Gmail -> Manage your Google account: 
        ![image](https://user-images.githubusercontent.com/26399692/137579937-12c01d4d-1f62-4867-8c40-c056391d3b7e.png)
 - Security -> Turn on : Less Secure App access:
        ![image](https://user-images.githubusercontent.com/26399692/137579959-e1554f06-5583-4ad1-ad28-ed69ed27b922.png)

------------------------------------------------------------
**How to run the Project from Local machine**
1. Pull the code into your machine and import in IDE (Eclipse/intelliJ).
2. Go to testng_Local.xml -> Run this file as TestNG suite
  It should start the execution -> Parallel Testing.
 - **NOTE:** stg_config.properties is the default configuration file.
3. To view the Allure reports 
 - Setup the Allure in your machine
 - Go to URL (https://docs.qameta.io/allure/) -> Search for Manual installation -> Steps are mentioned
 - Go to your project location
 - Open command prompt -> allure serve allure-results
------------------------------------------------------------
**How to run the Project from Jenkins**
[__Final-OC_RestAssured-Github-ActiveChoiceParams.pdf](https://github.com/rajatt95/MasterRestAssuredFramework/files/7363899/__Final-OC_RestAssured-Github-ActiveChoiceParams.pdf)
![image](https://user-images.githubusercontent.com/26399692/137712986-1a4f88b8-149e-4c71-90de-575bf9fb5f3d.png)
------------------------------------------------------------
**Email to User(s) using Jenkins:**
1. You can refer: https://www.youtube.com/watch?v=MFgbp00hbVI&ab_channel=Mukeshotwani
![image](https://user-images.githubusercontent.com/26399692/137712550-d697fda5-482d-475c-8e3a-6ef92a0f5bb9.png)
------------------------------------------------------------
