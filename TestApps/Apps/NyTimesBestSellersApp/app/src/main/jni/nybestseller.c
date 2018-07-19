#include <jni.h>


JNIEXPORT jstring JNICALL
Java_project_com_nybestsellerbooksapp_NyBsClient_getBSIdentifier(JNIEnv *env, jclass type) {

 return (*env)->NewStringUTF(env, "");
}

JNIEXPORT jstring JNICALL
Java_project_com_nybestsellerbooksapp_NyBsClient_getGIdentitifer(JNIEnv *env, jclass type) {

 return (*env)->  NewStringUTF(env, "");
}
