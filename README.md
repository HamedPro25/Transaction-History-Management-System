# Transaction History Management System

## Description

- This is a JavaFx project created using `Java` with `JavaFx` plugin with `Scene builder`. <br />
- The project purpose is to follow up the transactions (**Buy/Sell**) that the admin has made with his clients in the two currencices:(**based on the client needs**):
  1. Algerian dinar.<br />
  2. Arabic Emirates dinar<br />
- Also, the app can calculate the total profit of the admin per month or per year with a simple and easy **GUI** to read.

## Table of Contents (Optional)

- [Installation](#installation)
- [Usage](#usage)
- [License](#license)

## Installation

- Java(8.x or more). <br />
- JavaFX plugin. <br />
- [opencsv lib](dist/lib/opencsv-3.8.jar).<br />
- NetBeans IDE.

## Usage

This app can be used to anyone who wants to follow his transactions made with the clients and estimate his profit monthly. <br />
To run the project please follow these steps: <br />

- Run the `System_Managmnet.java` file. <br />
- The first interface will be the login form. <br />
  ![login form](assets/login.png)
- In order to get into the app you need to enter the correct username and password coordinates:<br />
  - for the username: `Admin`. <br />
  - for the password: `Admin`. <br />
- First thing you will see is the `Dashboard` interface which contains all the necessary informations that the admin needs to know. <br />
  ![Dashboard](assets/dahboard.png)
- The `Contacts` part will show you all your registered contacts with a bookmark of their information. Also, there is a filter section in order to facilitate the search for a specific client. You can also get access to the client profile to see other information.<br />
  ![The Contacts interface](/assets/contacts.png)
- The `Add Contact` part will allow to add any contact you want. Simply fill in the necessary information for that client( not all the fields need to be filled in) and click `Save`.<br />
  ![The Add Contact interface](/assets/addcontact.png)
- The `Profile` part is responsable for showing the entire client transaction history along with all his information. Also, you can modify any transaction that happened.And you can add a new transaction in the profile pane.<br />
  ![The Profile interface](/assets/profil.png)
- The `Notifications` part is the interface that shows to admin all actions that happened within the system.<br />
  ![The Notifications interface](/assets/notifications.png)
- The most important part of this system is the `stats` part because this interface will calculate and make the administrator keep track of his profit monthly or annually for both currencies (_DZ/AED_).
  ![The Stat interface](/assets/stat.png)

## License
