rootProject.name = "SandboxTests"

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        maven(url="https://jcenter.bintray.com/")
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "io.objectbox") {
               useModule("io.objectbox:objectbox-gradle-plugin:2.4.1")
            }
        }
    }
}
