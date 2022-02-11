plugins {
    appPlugins(GradlePlugin.Kapt, GradlePlugin.Hilt).forEach(::id)
}

android {

    applyAppDefaultConfig()

    applySigningConfig(
            SigningData(
                    name = release,
                    keystore = file("app_release_keystore.jks"),
                    keystorePassword = "SmockLess31012022!:)*",
                    alias = "release_sign_key",
                    password = "SmockLess31012022!:)*"
            )
    )

    applyAppFlavors()

    applyCompileOptions()

    kotlinOptions { jvmTarget() }

    //excludeCommonPackages()
    namespace = "com.revolhope.smockless"
}

dependencies {

    module(name = "data")
    module(name = "domain")
    module(name = "ui")

    androidCore()
    androidRuntime()
    hilt()

    compose()

    test()
}