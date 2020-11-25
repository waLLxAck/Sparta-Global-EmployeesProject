![Employee-Project](https://user-images.githubusercontent.com/15944458/97095701-b5ece100-165a-11eb-8079-184dc6b824f9.png)

*Created: Oct 20, 2020 8:40 PM*

*Updated: Oct 25, 2020 00:39 AM*

## Table of contents

- [Introduction](#introduction)
- Built with
- Getting started
- Usage
- Contributions
- Contact

## Introduction

Employee Project is a product that allow for the import of CSV files to a MySQL database. Through the use of threading and batch execution, it is capable of quick imports. It encompasses concepts such as: database link, threading, reading/writing to a file, logging, DTO, DAO, functional programming.

- Database

    MySQL database.

- Threading

    Allowed for the **persistence** of data to occur in *~4.5 seconds* from *~370 seconds*. The number of threads can be adjusted in the setup screen.

- Reading/Writing

    Making use of the `BufferedReader` in combination with `FileReader`.

- Logging

    Duplicate records are stored into an external file to be manually reviewed.

- Data transfer object (DTO)

    Used to reduce the number of calls to the database. It is present as the Employee class.

- Data access object (DAO)

    Provides CRUD operations without exposing details about the database.

The project makes use of **Maven** as the dependency management and build tool, **JUnit-Jupiter** for unit testing, **log4j2** for logging throughout the program, **mysql-connector** to establish a database link.

## Built with

The project was built with [IntelliJ](https://www.jetbrains.com/idea/).

The database designed to work with the product is [MySQL](https://www.mysql.com/).

## Getting started

You need to have downloaded and installed:
 
1. IntelliJ, either the [community version](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC) or the [ultimate](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows) one.

2. [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)

3. [MySQL Server](https://dev.mysql.com/downloads/windows/installer/8.0.html)

Then you need to clone the repo. For windows users, run the following command in Git:

`git clone https://github.com/waLLxAck/Sparta-Global-SortManager/tree/master`

*Note: You can use IntelliJ for cloning, too.*

Once you've done that, you have to open up the project in the IntelliJ and create a `database.properties` file inside the *resources* folder with the following information:

*Please change database settings accordingly.*

`url = jdbc:mysql://localhost:3306/dbEmployees?serverTimezone=GMT` 

`username = root`

`password = *ENTER PASSWORD*`

dbEmployees - this is the name of the database

GMT - this is your time zone

root - default username

password - provide the password you used in the MySQL server installation

## Usage

This project can be used to efficiently persist CSV files to a database.

*Warning: For any alterations of the provided Employee format, DTO must be changed.* 

## Contributions

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your own branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Adding some Amazing Feature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
6. Wait for me to review and merge :)

## Contact

**Name**: Svilen Petrov 

**Website**: [svilenpetrov.com](http://svilenpetrov.com) 

**Email**: svilen.petrov97@gmail.com
