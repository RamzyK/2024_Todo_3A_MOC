plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
}

android {
    namespace = "com.example.myfirstapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myfirstapp"
        minSdk = 27
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.screengrab)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.converter.gson)

    // Room DB
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.room.compiler)

    // To use Kotlin annotation processing tool (kapt)
    kapt(libs.room.compiler)

}