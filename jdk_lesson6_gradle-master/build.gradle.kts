plugins {
    id("java")
}

group = "ru.gb"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    // https://mvnrepository.com/artifact/com.google.guava/guava
    implementation("com.google.guava:guava:32.1.3-jre")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar{
    manifest.attributes["Main-Class"] = "ru.gb.Main"
}