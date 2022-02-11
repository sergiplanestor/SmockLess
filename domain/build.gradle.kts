plugins {
    libPlugins(
        GradlePlugin.Parcelize,
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
    namespace = "com.revolhope.domain"
}

dependencies {
    androidCore()
    androidRuntime()
    hilt()
}