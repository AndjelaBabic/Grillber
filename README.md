# Grillber

Grillber is a small clojure web application, that offers delivery service for grill rentals.

## About project

The project consist of the following functionalities: 

- Login and sign up page, in order to use the application user must be authenticated.
- Form that allows a user to book a grill for a certain day, and stores that order in a database. Besides date, 
user must input address, type of bbq etc.
- Order details page - where user can see all orders he made, with additional details. User is allowed to update
his orders, as well as cancel an order(s). 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

You will need Leiningen 2.0.0 or above and MySQL installed.

### Setup

To run applicaiton, the steps are:

Login to MySQL server and create database call grillber:

CREATE DATABASE grillber DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

To create database schema, navigate to project directory, and execute migrations files using command:

lein migratus migrate

To start a web server for the application, run:

lein ring server

## Built With

Libraries: yesql, migratus, compojure, ring, struct



