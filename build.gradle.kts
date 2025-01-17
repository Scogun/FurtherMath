import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.0"
}

group = "FurtherMath"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.4")
}

tasks {
    compileKotlin {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
    }
    compileTestKotlin {
        compilerOptions.jvmTarget.set(JvmTarget.JVM_21)
    }
}