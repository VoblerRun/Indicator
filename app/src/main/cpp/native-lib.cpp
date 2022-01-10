#include <jni.h>
#include <string>
#include <iostream>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_indicator_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */, jint a) {
    std::string hello = std::to_string(a);;
    return env->NewStringUTF(hello.c_str());
}