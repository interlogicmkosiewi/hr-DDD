rootProject.name = "document-service"

pluginManagement {
    plugins {
        id("org.springframework.boot") version "3.0.5"
        id("io.spring.dependency-management") version "1.1.0"
        kotlin("jvm") version "1.8.20"
        kotlin("plugin.spring") version "1.8.20"
        id("io.gitlab.arturbosch.detekt") version "1.22.0"
        id("org.asciidoctor.jvm.convert") version "3.3.2"
    }
}

include(
    "hr-service-command",
    "hr-service-common",
    "hr-service-query"
)
