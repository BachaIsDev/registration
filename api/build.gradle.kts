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

dependencies {
    implementation("org.openapitools:openapi-generator-gradle-plugin:$openApiGeneratorPluginVersion")
}

openApiValidate {
    inputSpec = "${projectDir}/src/main/resources/open-api.yaml"
    recommend = true
}

openApiGenerate {
    generatorName = "kotlin-spring"
    inputSpec = "${projectDir}/src/main/resources/open-api.yaml"
    apiPackage = "ru.bacha.api"
    modelPackage = "ru.bacha.model"
    configOptions.apply {
        put("useTags", "true")
        put("interfaceOnly", "true")
        put("library", "spring-boot")
        put("useSpringBoot3", "true")
        put("gradleBuildFile", "true")
        put("useBeanValidation", "true")
        put("skipDefaultInterface", "true")
        put("enumPropertyNaming", "UPPERCASE")
        put("generateConstructorWithAllArgs", "true")
    }
}

sourceSets {
    main {
        java {
            srcDir("$projectDir/build/generated-resources/api/src/main/kotlin")
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    dependsOn("openApiGenerate")
}