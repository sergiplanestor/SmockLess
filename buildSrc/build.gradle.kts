plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.0")
    implementation(kotlin("gradle-plugin", version = "1.5.31"))
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
}