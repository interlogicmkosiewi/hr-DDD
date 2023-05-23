import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
    id("io.gitlab.arturbosch.detekt")
    id("org.asciidoctor.jvm.convert")
}

group = "dk.lessor"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

val asciidoctorExt: Configuration by configurations.creating
val logstashVersion: String by project
val openPdfVersion: String by project
val apachePoiVersion: String by project
val zalandoProblemVersion: String by project
val springBootStarterAopVersion: String by project
val springdocOpenapiStarterWebmvcUiVersion: String by project

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-config")
    implementation("org.springframework.security:spring-security-web")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-logging")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop:$springBootStarterAopVersion")

    implementation(project(mapOf("path" to ":hr-service-query")))
    implementation(project(mapOf("path" to ":hr-service-command")))
    implementation(project(mapOf("path" to ":hr-service-common")))

    implementation("io.vavr:vavr-kotlin:0.10.2")
    implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")

    implementation("org.jetbrains.exposed:exposed-core")
    implementation("org.jetbrains.exposed:exposed-dao")
    implementation("org.jetbrains.exposed:exposed-jdbc")
    implementation("org.jetbrains.exposed:exposed-java-time")


    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocOpenapiStarterWebmvcUiVersion")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.dropwizard.metrics:metrics-core")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("io.micrometer:micrometer-tracing")
    implementation("org.zalando:problem-spring-web-starter:$zalandoProblemVersion")
	// Logging
	implementation("net.logstash.logback:logstash-logback-encoder:$logstashVersion")
	// Document
	implementation("com.github.librepdf:openpdf:$openPdfVersion")
	implementation("org.apache.poi:poi:$apachePoiVersion")
	implementation("org.apache.poi:poi-ooxml:$apachePoiVersion")

    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.restdocs:spring-restdocs-core")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

detekt {
    config = files("$projectDir/detekt-config.yml")
    buildUponDefaultConfig = true
    allRules = false
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = JavaVersion.VERSION_17.toString()
    exclude("**/*Test.kt")
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = JavaVersion.VERSION_17.toString()
}

// Asciidoctor
val snippetsDir by extra { file("build/generated-snippets") }
tasks {
    test {
        outputs.dir(snippetsDir)
    }

    asciidoctor {
        inputs.dir(snippetsDir)
        configurations("asciidoctorExt")
        dependsOn(test)
    }

    bootJar {
        dependsOn(asciidoctor)
        copy {
            from("${project.buildDir}/docs/asciidoc")
            into("${project.buildDir}/resources/main/static")
        }
    }
}