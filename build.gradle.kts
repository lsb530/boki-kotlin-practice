plugins {
    kotlin("jvm") version "2.0.10"
}

group = "com.boki"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://central.sonatype.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    // https://github.com/oshai/kotlin-logging?tab=readme-ov-file
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}