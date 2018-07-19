#include <jni.h>


JNIEXPORT jstring JNICALL
Java_project_com_nybestsellerbooksapp_NyBsClient_getBSIdentifier(JNIEnv *env, jclass type) {

 return (*env)->NewStringUTF(env, "36cca1784b09425492d25bbb4ef5bdf4");
}

JNIEXPORT jstring JNICALL
Java_project_com_nybestsellerbooksapp_NyBsClient_getGIdentitifer(JNIEnv *env, jclass type) {

 return (*env)->  NewStringUTF(env, "AIzaSyDv7UorHAegtFuyyCzQSicwf2gMgynxyFM");
}