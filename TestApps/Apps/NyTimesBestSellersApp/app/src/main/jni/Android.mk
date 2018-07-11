LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := nybestseller
LOCAL_SRC_FILES := nybestseller.c

include $(BUILD_SHARED_LIBRARY)