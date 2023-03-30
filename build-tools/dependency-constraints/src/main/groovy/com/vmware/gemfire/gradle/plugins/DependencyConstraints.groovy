/*
 * Copyright (c) VMware, Inc. 2022-2023. All rights reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

package com.vmware.gemfire.gradle.plugins

import org.gradle.api.Project

class DependencyConstraints {
/** By necessity, the version of those plugins used in the build-scripts are defined in the
 * buildscript {} configuration in the root project's build.gradle. */
  static Map<String, String> disparateDependencies = initExternalDependencies()

  static String get(String name) {
    return disparateDependencies.get(name)
  }

  static private Map<String, String> initExternalDependencies() {
    Map<String, String> depVersionMapping = new HashMap<>()

    depVersionMapping.put("antlrVersion", "2.7.7")
    depVersionMapping.put("apacheTaglibsVersion", "1.2.5")
    depVersionMapping.put("assertjVersion", "3.23.1")
    depVersionMapping.put("javaxServletApiVersion", "4.0.4")
    depVersionMapping.put("javaxServletJspJstlApiVersion", "1.2.7")
    depVersionMapping.put("log4jVersion", "2.19.0")
    depVersionMapping.put("logbackVersion", "1.2.3")
    depVersionMapping.put("junitVersion", "4.13.2")
    depVersionMapping.put("mockitoVersion", "4.4.0")
    depVersionMapping.put("multithreadedtcVersion", "1.01")
    depVersionMapping.put("seleniumHTMLDriverVersion", "3.61.0")
    depVersionMapping.put("seleniumVersion", "3.141.59")
    depVersionMapping.put("slf4jVersion", "1.7.36")
    depVersionMapping.put("springSecurityVersion", "5.7.7")
    depVersionMapping.put("springShellVersion", "1.2.0.RELEASE")
    depVersionMapping.put("findbugsVersion","3.0.2")
    depVersionMapping.put("springVersion","5.3.26")
    depVersionMapping.put("springBootVersion","2.7.10")
    depVersionMapping.put("springDataCommonsVersion","2.7.10")
    depVersionMapping.put("springSessionVersion","2.7.1")
    depVersionMapping.put("multithreadedtcVersion","1.01")
    depVersionMapping.put("apacheLogging","2.14.1")
    depVersionMapping.put("jsr305Version","3.0.2")

    return depVersionMapping
  }

  void apply(Project project) {

    project.dependencies {
      api(platform(group: 'org.springframework', name: 'spring-framework-bom', version: get('springVersion')))
      api(platform(group: 'org.springframework.security', name: 'spring-security-bom', version: get('springSecurityVersion')))
      constraints {
        api(group: 'jakarta.servlet', name: 'jakarta.servlet-api', version: get('javaxServletApiVersion'))
        api(group: 'jakarta.servlet.jsp.jstl', name: 'jakarta.servlet.jsp.jstl-api', version: get('javaxServletJspJstlApiVersion'))
        api(group: 'antlr', name: 'antlr', version: get('antlrVersion'))
        api(group: 'ch.qos.logback', name: 'logback-classic', version: get('logbackVersion'))
        api(group: 'edu.umd.cs.mtc', name: 'multithreadedtc', version: get('multithreadedtcVersion'))
        api(group: 'junit', name: 'junit', version: get('junitVersion'))
        api(group: 'org.apache.logging.log4j', name: 'log4j-to-slf4j', version: get('apacheLogging'))
        api(group: 'org.apache.taglibs', name: 'taglibs-standard-impl', version: get('apacheTaglibsVersion'))
        api(group: 'org.apache.taglibs', name: 'taglibs-standard-jstlel', version: get('apacheTaglibsVersion'))
        api(group: 'org.apache.taglibs', name: 'taglibs-standard-spec', version: get('apacheTaglibsVersion'))
        api(group: 'org.assertj', name: 'assertj-core', version: get('assertjVersion'))
        api(group: 'org.mockito', name: 'mockito-core', version: get('mockitoVersion'))
        api(group: 'org.seleniumhq.selenium', name: 'htmlunit-driver', version: get('seleniumHTMLDriverVersion'))
        api(group: 'org.slf4j', name: 'slf4j-api', version: get('slf4jVersion'))
        api(group: 'org.slf4j', name: 'jcl-over-slf4j', version: get('slf4jVersion'))
        api(group: 'org.slf4j', name: 'log4j-over-slf4j', version: get('slf4jVersion'))
        api(group: 'org.springframework.data', name: 'spring-data-geode-test', version: get('sdgtVersion'))
        api(group: 'org.springframework', name: 'spring-tx', version: get('springVersion'))
        api(group: 'org.springframework.security', name: 'spring-security-test', version: get('springSecurityVersion'))
        api(group: 'org.springframework.security', name: 'spring-security-web', version: get('springSecurityVersion'))
        api(group: 'org.springframework.security', name: 'spring-security-core', version: get('springSecurityVersion'))
        api(group: 'org.springframework.session', name: 'spring-session-core', version: get('springSessionVersion'))
        api(group: 'org.springframework.shell', name: 'spring-shell', version: get('springShellVersion'))
        api(group: 'org.webjars', name: 'bootstrap', version: '2.3.2')
        api(group: 'org.webjars', name: 'jquery', version: '1.12.4')
        api(group: 'org.webjars', name: 'webjars-locator-core', version: '0.32')
        api(group: 'org.webjars', name: 'webjars-locator', version: '0.32-1')
        api(group: 'org.webjars', name: 'webjars-taglib', version: '0.3')
        api(group: 'org.springframework.boot', name: 'spring-boot-starter-thymeleaf', version: get('springBootVersion'))
        api(group: 'org.springframework.boot', name: 'spring-boot-starter-web', version: get('springBootVersion'))
        api(group: 'org.springframework.boot', name: 'spring-boot-starter-test', version: get('springBootVersion'))
        api(group: 'org.seleniumhq.selenium', name: 'selenium-api', version: get('seleniumVersion'))
        api(group: 'org.seleniumhq.selenium', name: 'selenium-remote-driver', version: get('seleniumVersion'))
        api(group: 'org.seleniumhq.selenium', name: 'selenium-support', version: get('seleniumVersion'))
        api(group: 'edu.umd.cs.mtc', name: 'multithreadedtc', version: get('multithreadedtcVersion'))
        api(group: 'com.google.code.findbugs', name: 'jsr305', version: get('jsr305Version'))
      }
    }
  }
}
