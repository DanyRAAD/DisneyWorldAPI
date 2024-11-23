plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.dani.disneyworldapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dani.disneyworldapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Retrofit y Gson
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)

    // Interceptor para Retrofit
    implementation(libs.logging.interceptor)

    // Glide y Picasso
    implementation(libs.glide)
    implementation(libs.picasso)

    // Glide para cargar imágenes
    implementation(libs.glide.v4130)

    // RecyclerView
    implementation(libs.androidx.recyclerview)

    // Imágenes con bordes redondeados
    implementation(libs.roundedimageview)

    // Test y Android Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}