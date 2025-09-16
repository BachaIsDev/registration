plugins {
    kotlin("jvm")
    id("org.openapi.generator")
}

group = "ru.bacha.registration"
val revision: String by project
version = revision

repositories {
    mavenCentral()
}

val openApiGeneratorPluginVersion: String by project
val springBootStarterVersion: String by project
val jakartaValidationVersion: String by project
val openApiStarterVersion: String by project
val jakartaAnnotationVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootStarterVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootStarterVersion")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openApiStarterVersion")
    implementation("jakarta.validation:jakarta.validation-api:$jakartaValidationVersion")
    implementation("jakarta.annotation:jakarta.annotation-api:$jakartaAnnotationVersion")
}

tasks.openApiValidate {
    inputSpec = "${projectDir}/open-api.yaml"
    recommend = true
}

tasks.openApiGenerate {
    generatorName = "kotlin-spring"
    inputSpec = "${projectDir}/open-api.yaml"
    apiPackage = "ru.bacha.api"
    modelPackage = "ru.bacha.model"
    configOptions.apply {
        put("useTags", "true")
        put("interfaceOnly", "true")
        put("library", "spring-boot")
        put("useSpringBoot3", "true")
        put("useBeanValidation", "true")
        put("skipDefaultInterface", "true")
        put("enumPropertyNaming", "UPPERCASE")
        put("generateConstructorWithAllArgs", "true")
    }
}

sourceSets {
    main {
        java {
            srcDir("${projectDir}/build/generate-resources/main/src/main/kotlin")
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    dependsOn("openApiGenerate")
}