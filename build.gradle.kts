import org.gradle.api.tasks.testing.logging.TestExceptionFormat

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

group = "karazinscalausersgroup"
version = "0.0.1-SNAPSHOT"

val kotlinTestVersion by extra("3.2.1")
val junit5Version by extra("5.4.0")

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.20")

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Kotlin basic libraries
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")

    // Test tools
    testImplementation("io.kotlintest:kotlintest-runner-junit5:$kotlinTestVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junit5Version")
}

application {
    // Define the main class for the application.
    mainClassName = "karazinscalausersgroup.kotlin.coroutines.AppKt"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        outputs.upToDateWhen {false} //always run tests
        events("passed", "skipped", "failed")
        showStandardStreams = true
        exceptionFormat = TestExceptionFormat.FULL
    }
}

