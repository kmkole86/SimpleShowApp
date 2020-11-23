package dependencies

object Versions {
    const val hilt = "1.0.0-alpha02"
    const val hiltAndroid = "2.28.3-alpha"
    const val hiltAndroidCompiler = "2.28.3-alpha"
    const val room = "2.2.5"
    const val retrofit = "2.9.0"
    const val loggingInterceptor = "4.8.0"
    const val coroutines = "1.4.1"
    const val coroutinesLifecycleKtx = "2.2.0"
    const val navigation = "2.3.1"
    const val glide = "4.11.0"
    const val gson = "2.8.6"
    const val viewModelKtx = "2.2.0"
    const val coreKtx = "1.3.2"
    const val fragmentKtx = "1.2.5"
}

object Deps {

    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesReactiveStreamsKtx =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.coroutinesLifecycleKtx}"
    const val coroutinesViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.coroutinesLifecycleKtx}"
    const val coroutinesLiveDataKtx =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.coroutinesLifecycleKtx}"

    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}