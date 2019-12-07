plugins {
    java
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
}

sourceSets["main"].java.srcDir("build/generated/source/kapt/main")
sourceSets["main"].java.srcDir("src/main/java")
sourceSets["main"].java.srcDir("src/main/kotlin")
//sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
//    kotlin.srcDir("src/main/kotlin")
//}

group = "com.memetoclasm"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    google()
    maven(url = "https://jitpack.io" )
    //maven(url = "http://objectbox.net")
}

dependencies {
    //implementation("io.objectbox:objectbox-gradle-plugin:2.4.1")

    implementation(kotlin("stdlib-jdk8"))

    implementation("org.aaronhe:threetenbp-gson-adapter:1.0.2")
    implementation("com.github.leokraemer:android-gson-javatime-serialisers:2.1.2")
    implementation("io.objectbox:objectbox-kotlin:2.4.1")
    kapt("io.objectbox:objectbox-processor:2.4.1")

    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}