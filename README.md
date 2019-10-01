# Grillber

Grillber is a small clojure web application, that offers delivery service for grill rentals.

# Prerequisites

You will need Leiningen 2.0.0 or above and MySQL installed.

# Setup

To run applicaiton, the steps are:

Login to MySQL server and create database call grillber

CREATE DATABASE grillber DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

To create database schema navigate to project directory and execute migrations files using command

lein migratus migrate

To start a web server for the application, run:

lein ring server

