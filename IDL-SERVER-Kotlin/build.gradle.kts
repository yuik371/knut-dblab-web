plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	war
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.ldl"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

repositories {
	mavenCentral()
}