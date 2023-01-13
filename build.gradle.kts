import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.1.0"
    id("com.github.ben-manes.versions") version "0.42.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.22"
}

val springBootVersion = "3.0.1"
val springSessionVersion = "3.0.0"
val jacksonVersion = "2.14.1"
val pebbleVersion = "3.2.0"
val kotlinSerializationVersion = "1.4.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.pebbletemplates:pebble-spring-boot-starter:$pebbleVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.session:spring-session-core:$springSessionVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")

    developmentOnly("org.springframework.boot:spring-boot-devtools:$springBootVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

java.sourceCompatibility = JavaVersion.VERSION_17

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


tasks.dependencyUpdates {
    resolutionStrategy {
        componentSelection {
            all {
                val rejected = listOf("alpha", "beta", "b", "rc", "cr", "pr", "m", "preview")
                    .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
                    .any { it.matches(candidate.version) }
                if (rejected) {
                    reject("Release candidate")
                }
            }
        }
    }
    checkForGradleUpdate = true
}

tasks {
    processResources {
        filesMatching(listOf("application.properties", "banner.txt")) {
            val tokens = HashMap<String, String>()
            tokens["version"] = project.version.toString()
            filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to tokens)
        }
    }
}
