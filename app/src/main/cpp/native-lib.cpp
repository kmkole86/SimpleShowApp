#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_simpleshow_framework_datasource_network_retrofit_interceptors_AuthInterceptor_apiKey(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "1deb0507629447785d7776cbfb915c2f";
    return env->NewStringUTF(hello.c_str());
}