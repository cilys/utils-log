#include <jni.h>
#include <android/log.h>

#include "md5.h"
#include "com_cily_utils_encrypt_MD5Utils.h"


#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, "lib-md5", __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG , "lib-md5", __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO  , "lib-md5", __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN  , "lib-md5", __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR  , "lib-md5", __VA_ARGS__)

#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_cily_utils_encrypt_MD5Utils
 * Method:    encrypt
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL com_cily_utils_encrypt_MD5Utils_encrypt
  (JNIEnv *env, jobject jo, jstring jstr){
        const char *s;
        s = (*env)->GetStringUTFChars(env, jstr, NULL);

        size_t len = strlen(s);

        uint8_t result[16];
        md5((uint8_t*)s, len, result);

        char *tmp = (char*)malloc(2 * sizeof(char));
        char *res = (char*)malloc(32 * sizeof(char));
        memset(res, '\0', sizeof(res));

        for(int i = 0; i < 16; i++){
            sprintf(tmp, "%2.2x", result[i]);
            strcat(res, tmp);
        }

//        free(tmp);
        return (*env)->NewStringUTF(env, res);
  }


/*
 * Class:     com_cily_utils_encrypt_MD5Utils
 * Method:    check
 * Signature: (Landroid/content/Context;)I
 */
JNIEXPORT jint JNICALL Java_com_cily_utils_encrypt_MD5Utils_check
  (JNIEnv *env, jclass jc, jobject jo){
        jclass context_class = (*env)->GetObjectClass(env, jo);

        //context.getPackageManager()
        jmethodID methodId = (*env)->GetMethodID(env, context_class, "getPackageManager", "()Landroid/content/pm/PackageManager;");
        jobject pm_obj = (*env)->CallObjectMethod(env, jo, methodId);
        if(pm_obj == NULL){
            LOGI("getPackageManager() failed...");
            return -1;
        }

        //context.getPackageName()
        methodId = (*env)->GetMethodID(env, context_class, "getPackageName", "()Ljava/lang/String;");
        jstring pm_name_string = (jstring)(*env)->CallObjectMethod(env, jo, methodId);
        if(pm_name_string == NULL){
            LOGI("getPackageName failed...");
            return -2;
        }
        /***************************************/
        const char *pmName = (*env)->GetStringUTFChars(env, pm_name_string, 0);
        LOGI("pmName = %s", pmName);
        const char *PM = "com.vv";
        if(strstr(pmName, PM)){
            LOGI("The pm has right");
        }else{
            LOGI("No Right!!!");
        }
        /***************************************/
        (*env)->DeleteLocalRef(env, context_class);
        return 0;

        //PackageManager.getPackageInfo(Sting, int)
//        jclass pm_class = (*env)->GetObjectClass(env, pm_obj);
//        methodId = (*env)->GetMethodID(env, pm_class, "getPackageInfo", "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
//        (*env)->DeleteLocalRef(env, pm_class);
//
//        jobject pm_info_obj = (*env)->CallObjectMethod(env, pm_obj, methodId, pm_name_string, 64);
//        if(pm_info_obj == NULL){
//            LOGI("getPackageInfo failed...");
//            return -3;
//        }
//        (*env)->DeleteLocalRef(env, pm_obj);
//
//        //PackageInfo.signatures[0]
//        jclass pm_info_class = (*env)->GetObjectClass(env, pm_info_obj);
//        jfieldID fieldId = (*env)->GetFieldID(env, pm_info_class, "signatures", "[Landroid/content/pm/Signature;");
//        (*env)->DeleteLocalRef(env, pm_info_class);
//
//        jobjectArray signature_obj_array = (jobjectArray)(*env)->GetObjectField(env, pm_info_obj, fieldId);
//        if(signature_obj_array == NULL){
//            LOGI("PackageInfo.signatures[] is null");
//            return -4;
//        }
//
//        jobject signature_obj = (*env)->GetObjectArrayElement(env, signature_obj_array, 0);
//
//        (*env)->DeleteLocalRef(env, pm_info_obj);
//
//        //Signature.toCharsString()
//        jclass signature_class = (*env)->GetObjectClass(env, signature_obj);
//        methodId = (*env)->GetMethodID(env, signature_class, "toCharsString", "()Ljava/lang/String;");
//        (*env)->DeleteLocalRef(env, signature_class);
//        jstring signature_string = (jstring) (*env)->CallObjectMethod(env, signature_obj, methodId);
//
//        jstring sign_str = com_cily_utils_encrypt_MD5Utils_encrypt(env, jo, signature_string);
//        const char *s;
//                s = (*env)->GetStringUTFChars(env, sign_str, NULL);
//        LOGI("sign = %s", s);
//        const char *signNum = "315abe3c0ca78758ea2d3747c72a0cef";
//        if(strcmp(signNum, s) == 0){
//        LOGI("---------------------------");
//            return 0;
//        }else{
//            jclass jc_sys = (*env)->FindClass(env, "com/cily/utils/app/Sys");
//            if(jc_sys == 0){
//                LOGI("can not find sys...");
//                return -8;
//            }
//
//            jmethodID mid = (*env)->GetStaticMethodID(env, jc_sys, "helloToJava", "()V");
//            if(mid == NULL){
//                LOGI("******mid exit == NULL");
//                return -5;
//            }else{
//                if(mid == 0){
//                    LOGI("The methodId is 0...");
//                    return -6;
//                }
//
//                LOGI("******mid exit != NULL");
//                (*env)->CallStaticVoidMethod(env, jc_sys, mid);
//                LOGI("******mid exit != NULL********* CallObjectMethod ");
//                (*env)->DeleteLocalRef(env, jc_sys);
//                LOGI("******mid exit != NULL********* DeleteLocalRef ");
//                return 0;
//            }
//            return 0;
//        }
  }

#ifdef __cplusplus
}
#endif
