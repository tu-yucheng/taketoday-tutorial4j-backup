/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class cn_tuyucheng_taketoday_jni_ExampleObjectsJNI */

#ifndef _Included_cn_tuyucheng_taketoday_jni_ExampleObjectsJNI
#define _Included_cn_tuyucheng_taketoday_jni_ExampleObjectsJNI
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     cn_tuyucheng_taketoday_jni_ExampleObjectsJNI
 * Method:    createUser
 * Signature: (Ljava/lang/String;D)Lcn/tuyucheng/taketoday/jni/UserData;
 */
JNIEXPORT jobject JNICALL Java_cn_tuyucheng_taketoday_jni_ExampleObjectsJNI_createUser
  (JNIEnv *, jobject, jstring, jdouble);

/*
 * Class:     cn_tuyucheng_taketoday_jni_ExampleObjectsJNI
 * Method:    printUserData
 * Signature: (Lcn/tuyucheng/taketoday/jni/UserData;)V
 */
JNIEXPORT jstring JNICALL Java_cn_tuyucheng_taketoday_jni_ExampleObjectsJNI_printUserData
  (JNIEnv *, jobject, jobject);

#ifdef __cplusplus
}
#endif
#endif
