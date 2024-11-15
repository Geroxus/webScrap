plugins {
    kotlin("jvm") version "2.1.0-Beta2"
}

group = "de.gero"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // define a BOM and its version
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))

    // define any required OkHttp artifacts without version
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.20")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}