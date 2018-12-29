#include <jni.h>

extern "C" {
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_url(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("http://artmurka.com/");
}

JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_consumerkey(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("murka1");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_consumersecret(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("DqUQJzeCPmwD9CRqbHo6sGBzKCb5U4");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_oauthtoken(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("GVFkvsQi7UggkWrMLGePyiOFdTHtkgYJOiEW0O.N");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_oauthtokensecret(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("WLjfZcQLlBAHAUXbrJCwN957ngLbpPvcisERSHmQ");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_oauthversion(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("1.0");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_publicpaykey(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("i32727180241");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_privatepaykey(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("Bju2o1toAnXo93Sece9fOjSjUgqa0rnUvgWsBzy3");
}

JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_applicationemail(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("testmailforartmurka@gmail.com");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_applicationemailpassword(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("Serreal1@");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_sendtoemail(JNIEnv *env, jobject instance) {
return env->NewStringUTF("va.evstropov@gmail.com");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_loginconsumerkey(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("murka1");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_loginconsumersecret(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("DqUQJzeCPmwD9CRqbHo6sGBzKCb5U4");
}
JNIEXPORT jstring JNICALL
Java_com_artmurka_artmurkaapp_Constants_npapikey(JNIEnv *env, jobject instance) {
    return env->NewStringUTF("1c14ba016568145e17e14169fe23179a");
}
}