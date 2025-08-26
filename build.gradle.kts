plugins {
    id("java")
    id("io.freefair.lombok") version "8.14.2"
}

group = "net.lcpr.protocol"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:2.0.17")
    implementation("com.google.guava:guava:33.4.8-jre")

    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

var delomboked = sourceSets.create("delombok")
delomboked.java.srcDir("build/generated/sources/delombok")

tasks.withType<Javadoc> {
    dependsOn(tasks.named("delombok"))
    source = delomboked.java
    options {
        this as StandardJavadocDocletOptions
        tags = listOf(
            "c2s:a:C2S context:",
            "s2c:a:S2C context:"
        )
        //stylesheetFile = file("javadocs.css")
    }
}
