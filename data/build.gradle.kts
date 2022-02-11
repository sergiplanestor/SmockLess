plugins {
    libPlugins(
        GradlePlugin.Kapt,
        GradlePlugin.Hilt
    ).forEach(::id)
}

android {

    applyLibModuleDefaultConfig()

    applyLibFlavors()

    applyCompileOptions()

    kotlinOptions { jvmTarget() }

    excludeCommonPackages()
    namespace = "com.revolhope.data"
}

dependencies {

    module(name = "domain")

    androidCore()
    androidRuntime()

    hilt()

    gson()
}