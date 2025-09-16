plugins {
    kotlin("jvm")
    kotlin("plugin.jpa")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "ru.bacha.registration"
val revision: String by project
version = revision

repositories {
    mavenCentral()
    mavenLocal()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

val springBootStarterVersion: String by project
val junitVersion: String by project
val flywayGPVersion: String by project
val lombokVersion: String by project

dependencies {
    implementation(project(":api"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootStarterVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootStarterVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka")

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.flywaydb:flyway-gradle-plugin:$flywayGPVersion")

    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootStarterVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}


tasks.withType<Test> {
    useJUnitPlatform()
}
