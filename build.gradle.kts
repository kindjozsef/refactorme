import nl.littlerobots.vcu.plugin.resolver.VersionSelectors

plugins {
    java
    application
    alias(libs.plugins.spotless)
    alias(libs.plugins.version.catalog.update)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

testing {
    suites {
        val test =
            named<JvmTestSuite>("test") {
                useJUnitJupiter("6.0.1")
            }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "ro.msg4banking.App"
}

spotless {
    java {
        target("src/**/*.java")
        googleJavaFormat()
        removeUnusedImports()
        formatAnnotations()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

versionCatalogUpdate {
    sortByKey.set(true)
    versionSelector(VersionSelectors.STABLE)
    keep {
        keepUnusedVersions.set(true)
    }
}
