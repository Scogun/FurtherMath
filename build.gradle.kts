plugins {
    kotlin("multiplatform") version "2.1.0"
}

group = "FurtherMath"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
    jvm()
    mingwX64()
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.kotest:kotest-assertions-core:5.9.1")
            }
        }
    }
}

/*dependencies {
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
}*/