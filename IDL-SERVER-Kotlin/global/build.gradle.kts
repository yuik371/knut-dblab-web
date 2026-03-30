plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.ldl"
version = "0.0.1-SNAPSHOT"

val queryDslVersion = "5.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.seob7:common-api:0.0.2")

    // Mongo
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Validation
    implementation("org.springframework.boot: spring-boot-starter-validation")

    // Slack
    implementation("com.slack.api:slack-api-client:1.45.4")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // QueryDSL
    implementation("com.querydsl:querydsl-mongodb:${queryDslVersion}") {
        exclude(group = "org.mongodb", module = "mongo-java-driver")
    }
    annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}:general")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

val querydslDir = "src/main/generated"

sourceSets {
    getByName("main").java.srcDirs("src/main/generated")
}

tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory = file(querydslDir)

    // 상단 코드가 적용되지 않을 경우 하단 코드 사용
    options.generatedSourceOutputDirectory.set(file(querydslDir))
}

tasks.named("clean") {
    doLast {
        file(querydslDir).deleteRecursively()
    }
}