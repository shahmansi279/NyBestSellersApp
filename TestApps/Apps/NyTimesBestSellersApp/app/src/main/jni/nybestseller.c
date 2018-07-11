#include <jni.h>


JNIEXPORT jstring JNICALL
Java_com_bestreads_NyBsClient_getBSIdentifier(JNIEnv *env, jclass type) {

 return (*env)->NewStringUTF(env, "");
}

JNIEXPORT jstring JNICALL
Java_com_bestreads_NyBsClient_getGIdentitifer(JNIEnv *env, jclass type) {

 return (*env)->  NewStringUTF(env, "");
}
