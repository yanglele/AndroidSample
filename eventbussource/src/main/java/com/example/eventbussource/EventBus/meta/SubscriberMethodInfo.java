/*
 * Copyright (C) 2012-2016 Markus Junginger, greenrobot (http://greenrobot.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.eventbussource.EventBus.meta;


import com.example.eventbussource.EventBus.ThreadMode;

/**
 * 订阅方法实体类
 */
public class SubscriberMethodInfo {
    final String methodName;//方法名
    final ThreadMode threadMode;//订阅的线程
    final Class<?> eventType;//自定义的订阅事件class类型
    final int priority;//优先级
    final boolean sticky;//是否粘性事件

    public SubscriberMethodInfo(String methodName, Class<?> eventType, ThreadMode threadMode,
                                int priority, boolean sticky) {
        this.methodName = methodName;
        this.threadMode = threadMode;
        this.eventType = eventType;
        this.priority = priority;
        this.sticky = sticky;
    }

    public SubscriberMethodInfo(String methodName, Class<?> eventType) {
        this(methodName, eventType, ThreadMode.POSTING, 0, false);
    }

    public SubscriberMethodInfo(String methodName, Class<?> eventType, ThreadMode threadMode) {
        this(methodName, eventType, threadMode, 0, false);
    }

}