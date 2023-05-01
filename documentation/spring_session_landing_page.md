---
title: Spring Session for VMware GemFire Quick Start
---

Spring Session for VMware GemFire provides an implementation of the core Spring Session framework using VMware GemFire to manage a user’s Session information by leveraging Spring Data for VMware GemFire.

By integrating with VMware GemFire, you have the full power of this technology (Strong Consistency, Low Latency, High Availability, Resiliency, etc.) at your fingertips in your Spring Boot or Spring Data applications.

This reference guide explains how to add the Spring Session for VMware GemFire dependency to your Spring Boot or Spring Data project. Once the dependency has been added, refer to the [Spring Boot for Apache Geode Reference Guide](https://docs.spring.io/spring-boot-data-geode-build/current/reference/html5/) and [Spring Session](https://docs.spring.io/spring-session-data-geode/docs/current/reference/html5/) for in-depth information about using the dependency.

## Compatibility

Spring Session for VMware GemFire 1.1.0 is compatibile with the following:

<table>
    <tr>
        <td>Spring Session</td>
        <td>2.6, 2.7, 3.0</td>
    </tr>
    <tr>
        <td>GemFire</td>
        <td>9.15.0 to 9.15.4</td>
    </tr>
</table>


## Release Notes:

### 1.1.1

* Update to latest Spring Session patch version (3.0.1, 2.7.1 and 2.6.3)
* Update to latest Spring Framework patch version (6.0.7 and 5.3.26)
* Update all dependencies to latest patch version

### 1.1.0

* First release that removes dependency on Spring Session For Apache Geode
* **"Bring your own GemFire" (ByoG)**:  You can now explicitly specify the GemFire version that you need. See below for examples of declaring the GemFire version. ByoG requires that you provide a working version of VMware GemFire.

### 1.0.0

* Initial Spring Session For VMware GemFire release, still dependent on Spring Session For Apache Geode

## Add Spring Data for VMware GemFire to a Project

The Spring Session for VMware GemFire dependencies are available from the [Pivotal Commercial Maven Repository](https://commercial-repo.pivotal.io/login/auth). Access to the Pivotal Commercial Maven Repository requires a one-time registration step to create an account.

Spring Session for VMware GemFire requires users to add the GemFire repository to their projects.

To add Spring Session for VMware GemFire to a project:

1. In a browser, navigate to the [Pivotal Commercial Maven Repository](https://commercial-repo.pivotal.io/login/auth).

1. Click the **Create Account** link.

1. Complete the information in the registration page.

1. Click **Register**.

1. After registering, you will receive a confirmation email. Follow the instruction in this email to activate your account.

1. After account activation, log in to the [Pivotal Commercial Maven Repository](https://commercial-repo.pivotal.io/login/auth) to access the configuration information found in [gemfire-release-repo](https://commercial-repo.pivotal.io/repository/gemfire-release-repo).

1. Add the GemFire repository to your project:

    * **Maven**: Add the following block to the `pom.xml` file:

        ```xml
        <repository>
            <id>gemfire-release-repo</id>
            <name>Pivotal GemFire Release Repository</name>
            <url>https://commercial-repo.pivotal.io/data3/gemfire-release-repo/gemfire</url>
        </repository>
        ```

    * **Gradle**: Add the following block to the `repositories` section of the `build.gradle` file:

        ```xml
        repositories {
            mavenCentral()
            maven {
                credentials {
                    username "$gemfireRepoUsername"
                    password "$gemfireRepoPassword"
                }
                url = uri("https://commercial-repo.pivotal.io/data3/gemfire-release-repo/gemfire")
            }
        }
        ```

1. Add your Pivotal Commercial Maven Repository credentials.

    * **Maven**: Add the following to the `.m2/settings.xml` file. Replace `MY-USERNAME@example` and `MY-DECRYPTED-PASSWORD` with your Pivotal Commercial Maven Repository credentials.

        ```xml
        <settings>
            <servers>
                <server>
                    <id>gemfire-release-repo</id>
                    <username>MY-USERNAME@example.com</username>
                    <password>MY-DECRYPTED-PASSWORD</password>
                </server>
            </servers>
        </settings>
        ```

    * **Gradle**: Add the following to the local (`.gradle/gradle.properties`) or project `gradle.properties` file. Replace `MY-USERNAME@example` and `MY-DECRYPTED-PASSWORD` with your Pivotal Commercial Maven Repository credentials.

        ```xml
        gemfireRepoUsername=MY-USERNAME@example.com 
        gemfireRepoPassword=MY-DECRYPTED-PASSWORD
        ```

1. After you have set up the repository and credentials, add the Spring Session for VMware GemFire dependency to your application.

    For version 1.0.0:

    * **Maven**: Add the following to your `pom.xml` file. Replace `VERSION` with the current version of Spring Session for VMware GemFire available.

        ```xml
        <dependency>
            <groupId>com.vmware.gemfire</groupId>
            <artifactId>spring-session-2.7-gemfire-9.15</artifactId>
            <version>VERSION</version>
        </dependency>
        ```

    * **Gradle**: Add the following to your `build.gradle` file. Replace `VERSION` with the current version of Spring Session for VMware GemFire available.

        ```xml
        dependencies {
            implementation "com.vmware.gemfire:spring-session-2.7-gemfire-9.15:VERSION"
        }
        ```

    For version 1.1.0 and later:

    Starting in version 1.1.0, you will be required to "Bring Your Own GemFire," which will allow for improved flexibility with GemFire patch versions. In addition to the Spring Session for VMware GemFire dependency, you must add an explicit dependency on the desired version of GemFire. The required dependencies will differ for clients and servers.

    For clients:

    * **Maven**: Add the following to your `pom.xml` file. Replace `VERSION` with the current version of Spring Session for VMware GemFire available and `GEMFIRE_VERSION` with the version of VMware GemFire being used for the project.

        ```xml
        <dependency>
            <groupId>com.vmware.gemfire</groupId>
            <artifactId>spring-session-2.7-gemfire-9.15</artifactId>
            <version>VERSION</version>
        </dependency>
        <dependency>
            <groupId>com.vmware.gemfire</groupId>
            <artifactId>geode-core</artifactId>
            <version>GEMFIRE_VERSION</version>
        </dependency>
        <!--if using continuous queries-->
        <dependency>
            <groupId>com.vmware.gemfire</groupId>
            <artifactId>geode-cq</artifactId>
            <version>GEMFIRE_VERSION</version>
        </dependency>
        ```

    * **Gradle**: Add the following to your `build.gradle` file. Replace `VERSION` with the current version of Spring Session for VMware GemFire available and `GEMFIRE_VERSION` with the version of VMware GemFire being used for the project.

        ```xml
        dependencies {
            implementation "com.vmware.gemfire:spring-session-2.7-gemfire-9.15:VERSION"
            implementation "com.vmware.gemfire:geode-core:GEMFIRE_VERSION"
            // if using continuous queries
            implementation "com.vmware.gemfire:geode-cq:GEMFIRE_VERSION"
        }
        ```

    For servers:

    NOTE: The server dependencies are only required if the user is starting an embedded GemFire server using Spring.

    * **Maven**: Add the following to your `pom.xml` file. Replace `VERSION` with the current version of Spring Session for VMware GemFire available and `GEMFIRE_VERSION` with the version of VMware GemFire being used for the project.

        ```xml
        <dependency>
            <groupId>com.vmware.gemfire</groupId>
            <artifactId>spring-session-2.7-gemfire-9.15</artifactId>
            <version>VERSION</version>
        </dependency>
        <dependency>
            <groupId>com.vmware.gemfire</groupId>
            <artifactId>geode-server-all</artifactId>
            <version>GEMFIRE_VERSION</version>
            <exclusions>
                <exclusion>
                    <groupId>com.vmware.gemfire</groupId>
                    <artifactId>geode-log4j</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        ```

    * **Gradle**: Add the following to your `build.gradle` file. Replace `VERSION` with the current version of Spring Boot for VMware GemFire available and `GEMFIRE_VERSION` with the version of VMware GemFire being used for the project.

        ```xml
        dependencies {
            implementation "com.vmware.gemfire:spring-session-2.7-gemfire-9.15:VERSION"
            implementation ("com.vmware.gemfire:geode-server-all:GEMFIRE_VERSION"){
                exclude group: 'com.vmware.gemfire', module: 'geode-log4j'
            }
        }
        ```

1. Your application is now ready to connect with your GemFire instance.

## Reference Guide

For further information, refer to the [Spring Boot for Apache Geode Reference Guide](https://docs.spring.io/spring-boot-data-geode-build/current/reference/html5/) and [Spring Session](https://docs.spring.io/spring-session-data-geode/docs/current/reference/html5/) reference documentation.
