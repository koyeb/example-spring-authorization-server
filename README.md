<div align="center">
  <a href="https://koyeb.com">
    <img src="https://www.koyeb.com/static/images/icons/koyeb.svg" alt="Logo" width="80" height="80">
  </a>
  <h3 align="center">Koyeb Serverless Platform</h3>
  <p align="center">
    Deploy Spring Authorization Server applications on Koyeb
    <br />
    <a href="https://koyeb.com">Learn more about Koyeb</a>
    ·
    <a href="https://koyeb.com/docs">Explore the documentation</a>
    ·
    <a href="https://koyeb.com/tutorials">Discover our tutorials</a>
  </p>
</div>

## About Koyeb and the Spring Authorization Server example applications

Koyeb is a developer-friendly serverless platform to deploy apps globally. No-ops, servers, or infrastructure management.  This repository contains two companion applications: A simple "hello world" client application and a more complex authorization server that authenticates users and includes a user management API.

This example application is designed to show how you can create your own identity provider with Spring Authorization Server and deploy it on Koyeb.  You can find out more about these two applications in the [associated tutorial](https://www.koyeb.com/tutorials/using-spring-authorization-server-as-an-auth-solution-on-koyeb).

## Getting Started

Follow the steps below to deploy and run client (`auth-client`) and server (`auth-server`) applications on your Koyeb account.

### Requirements

You need a Koyeb account to successfully deploy and run this application. If you don't already have an account, you can sign-up for free [here](https://app.koyeb.com/auth/signup).

You will need to [deploy a PostgreSQL database](https://app.koyeb.com/database-services/new) for this project and retrieve it's Java connection string.

### Deploy using the Koyeb button

The fastest way to deploy the two applications is to click the **Deploy to Koyeb** buttons below.

#### Deploy the server

First, deploy the Spring Authorization Server application:

[![Deploy to Koyeb](https://www.koyeb.com/static/images/deploy/button.svg)](https://app.koyeb.com/deploy?name=auth-server&type=git&repository=koyeb%2Fexample-spring-authorization-server&branch=main&workdir=auth-server&env%5BDATABASE_URL%5D=REPLACE_ME&env%5BDATABASE_PASSWORD%5D=REPLACE_ME&env%5BAUTH_CLIENT_URL%5D=REPLACE_ME&ports=8000%3Bhttp%3B%2F)

The required environment variables for the server application are:

* `DATABASE_URL`: The URL where your PostgreSQL database is available.  This should begin with `jdbc:postgresql://` and not include the user or password.
* `DATABASE_PASSWORD`: The password for your PostgreSQL database.
* `AUTH_CLIENT_URL`: The URL where the `auth-client` application will be deployed.  If you are planning to deploy the client with the button below, this will take the form of: `https://auth-client-<YOUR_KOYEB_ORG>.koyeb.app`.

#### Deploy the client

Afterwards, deploy the client:

[![Deploy to Koyeb](https://www.koyeb.com/static/images/deploy/button.svg)](https://app.koyeb.com/deploy?name=auth-client&type=git&repository=koyeb%2Fexample-spring-authorization-server&branch=main&workdir=auth-client&env%5BAUTH_SERVER_URL%5D=REPLACE_ME&ports=8000%3Bhttp%3B%2F)

The required environment variables for the client application are:

* `AUTH_SERVER_URL`: The URL where the `auth-server` application is deployed.  If using the deploy button above, this will take the form of: `https://auth-server-<YOUR_KOYEB_ORG>.koyeb.app`.  Be sure to modify it to match your own URL.

Clicking on these buttons brings you to the Koyeb App creation page with everything pre-set to launch the applications.  Fill out the required information in the environment variables and then click **Apply** to launch the applications.

_To modify this application example, you will need to fork this repository. Check out the [fork and deploy](#fork-and-deploy-to-koyeb) instructions._

### Fork and deploy to Koyeb

If you want to customize and enhance this application, you need to fork this repository.

If you used the **Deploy to Koyeb** button, you can simply link your service to your forked repository to be able to push changes.  Alternatively, you can manually create the application as described below.

On the [Koyeb Control Panel](https://app.koyeb.com/), on the **Overview** tab, click the **Create Web Service** button to begin.

For the `auth-server` application:

1. Select **GitHub** as the deployment method.
2. In the repositories list, select the repository you just forked.
3. Expand the **Builder** section and click the **Work directory** toggle to enable it.  In the associated field, type `auth-server`.
4. Expand the **Environment variables** section.  Add the following variables:
    * `DATABASE_URL`: The URL where your PostgreSQL database is available.  This should begin with `jdbc:postgresql://` and not include the username or password.
    * `DATABASE_PASSWORD`: The password for your PostgreSQL database.
    * `AUTH_CLIENT_URL`: The URL where the `auth-client` application will be deployed.  If you are planning to deploy as described below, this will take the form of: `https://auth-client-<YOUR_KOYEB_ORG>.koyeb.app`.
5. Set the App and Service names to `auth-server`.
6. Click **Deploy**.

Next, deploy the `auth-client` application with the following steps:

1. Select **GitHub** as the deployment method.
2. In the repositories list, select the repository you just forked.
3. Expand the **Builder** section and click the **Work directory** toggle to enable it.  In the associated field, type `auth-client`.
4. Expand the **Environment variables** section.  Add the following variables:
    * `AUTH_SERVER_URL`: The URL where the `auth-server` application is deployed.  If you deployed as described above, this will take the form of: `https://auth-server-<YOUR_KOYEB_ORG>.koyeb.app`.
5. Set the App and Service names to `auth-client`.
6. Click **Deploy**.

By visiting the URL for the client you should be redirected to the server for authentication.

## Contributing

If you have any questions, ideas or suggestions regarding this application sample, feel free to open an [issue](//github.com//koyeb/example-spring-authorization-server/issues) or fork this repository and open a [pull request](//github.com/koyeb/example-spring-authorization-server/pulls).

## Contact

[Koyeb](https://www.koyeb.com) - [@gokoyeb](https://twitter.com/gokoyeb) - [Slack](http://slack.koyeb.com/)
