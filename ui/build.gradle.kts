plugins {
    libPlugins(GradlePlugin.Kapt, GradlePlugin.Hilt).forEach(::id)
}

android {

    namespace = "com.revolhope.ui"

    applyLibModuleDefaultConfig()

    applyLibFlavors()

    applyCompileOptions()

    kotlinOptions { jvmTarget() }

    withFeatures(Feature.Compose)

    composeOptions { kotlinCompilerExtensionVersion = DependencyVersion.compose }

    //excludeCommonPackages()
}

dependencies {

    module(name = "domain")

    androidCore()
    androidRuntime()

    hilt(isComposeNavIncluded = true)

    compose()
    composeUtils()
    toolkitCompose()
    lottieCompose()

    test()
}