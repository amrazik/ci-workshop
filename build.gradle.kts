import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("application")
    id("java")
    id("jacoco")
    id("org.springframework.boot") version "4.0.3"
    id("io.spring.dependency-management") version "1.1.7"
    alias(libs.plugins.test.logger)
    alias(libs.plugins.spotless)
}

group = "com.signavio.ci"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.groovy)
    implementation(libs.spring.boot.starter.jdbc)
    implementation(libs.spring.boot.starter.jpa)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.thymeleaf)
    implementation(libs.thymeleaf.extras.springsecurity6)
    implementation(libs.h2)
    implementation(libs.postgresql)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.spring.boot.starter.jdbc.test)
    testImplementation(libs.spring.boot.starter.jpa.test)
    testImplementation(libs.spring.boot.starter.security.test)
    testImplementation(libs.spring.boot.starter.webmvc.test)
    testImplementation(libs.spring.boot.starter.thymeleaf.test)

    testRuntimeOnly(libs.junit.platform.launcher)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass.set("com.signavio.ci.Main")
}

spotless {
    java {
        palantirJavaFormat()
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.register("coverage") {
    dependsOn(tasks.jacocoTestReport)
}

jacoco {
    toolVersion = "0.8.14"
}

testlogger {
    theme = ThemeType.MOCHA_PARALLEL
    showExceptions = true
    showStackTraces = true
    showFullStackTraces = false
    showCauses = true
    slowThreshold = 2000
    showSummary = true
    showSimpleNames = false
    showPassed = true
    showSkipped = true
    showFailed = true
    showOnlySlow = false
    showStandardStreams = false
    showPassedStandardStreams = true
    showSkippedStandardStreams = true
    showFailedStandardStreams = true
    logLevel = LogLevel.LIFECYCLE
}
