plugins {
    val kotlinVersion = "1.9.25"
    val springBootVersion = "3.5.0"
    val springBootDependencyManagementVersion = "1.1.7"
    val openApiGeneratorVersion = "7.7.0"
    val ktlintVersion = "12.1.1"
    val openapiGradlePlugin = "1.9.0"
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
    id("org.springframework.boot") version springBootVersion apply false
    id("io.spring.dependency-management") version springBootDependencyManagementVersion apply false
    id("org.openapi.generator") version openApiGeneratorVersion apply false
    id("org.jlleitschuh.gradle.ktlint") version ktlintVersion apply false
    id("org.springdoc.openapi-gradle-plugin") version openapiGradlePlugin apply false
}