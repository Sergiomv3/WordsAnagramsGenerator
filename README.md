

## What can you get with this app?

You'll get anagrams for a input word or phrase.

**How to deploy?**

Clone the project, make sure that you have JVM installed, and run `./mvnw spring-boot:run` in a command line promt or powershell.

**After that, you can test the app with the following address:**
http://localhost:8080/anagram

You can do a GET request by using ***word*** param. E.g.:
[http://localhost:8080/anagram?word=testing](http://localhost:8080/anagram?word=testing)

---
To launch the **full application**, go to https://github.com/Sergiomv3/WAGFront, clone the repo, and after that run `./mvnw spring-boot:run`

The address port is set to 8081, hence you have to go to  http://localhost:8081 and the app will be displayed.

---

## Technical side
The proposal of this project is to develop an application which has to **get the anagrams** of a given word or phrase, but **these have to be content in a provided dictionary**. There are some **requirements** to develop the project:

 - [x] REST service
 - [x] Spring MVC
 - [x] Use of new functions of Java 8
 - [x] Unit tests
 - [x] Implement a Front-End Framework
 
 ***What have I added?***
 - [x] Bootstrap framework
 - [x] RESTful Web Service + **Spring Boot**

***What else would I like to add or I missed?***

 - [ ] Angular Framework (this app can be a SPA)
 - [ ] More unit tests
 - [ ] Use more Java 8's utilities

Let's see step by step:

 - **RESTful Web Service + Spring Boot:** I decided to use Spring Boot because is so fast to develop with this framework. Also, the Spring configuration is simple. **Spring Boot** provides an embed Tomcat, some dependencies to fast build, a don't need to have an XML file for the configuration. 
 About the **RESTful coding and structure**, I designed a **DTO**, that contains two attributes: a String object (the word introduced) and a List of strings (that contains the list of anagrams)
The **AnagramController** class contains a method annotated as **@RequestMapping** with `/anagram`  value. **This is the method that** **returns the DTO** with the corresponding data; the data that the logic of the app solves (calculating the anagrams). The **@RequestParam** needed is the word, provided by **GET** method.
In addition, there is a **@Service**, **AnagramGenerator** that contains the logic that solves the anagrams, in other words, the algorithm. *I know that this algorithm isn't working properly*
Also, I added a **@Component**, **DictionaryReader** that reads the provided dictionary.

- **Spring MVC + Bootstrap:** In this side, as needed, I have implemented the same **DTO** (with a String word and a List of String with the anagrams)
There is a **@Service**, **RequestService**, that manages the request action, and **returns the DTO provided by the RESTful service**
The controller (**AnagramController**) maps the corresponding view to the received request (in this case, a GET request from http://localhost:8080/anagram).
The **view** is composed by **two very similar HTML documents** (this is the reason due I think that this app should be developed as SPA with Angular).
The introduced **word** and its corresponding **anagrams** **will be displayed** in the view, supported by a **Bootstrap Framework** that makes easy the **layout**, and the use of JavaScript for the **input validation**. In addition, I have used an external JavaScript alert (SweetAlert) to "beautify" the app design. ***Please, consider that I have implemented this functionality using an external resource, so if you are disconnected from the Internet, it won't work.***
