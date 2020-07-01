package com.android.asm.time;

import java.util.HashMap;

/**
 * @author lizhifeng
 * @date 2020/7/1 14:26
 */
public class TimeUtils {
    public static volatile TimeUtils timeUtils;
    private HashMap<String,Long> timeMap= new HashMap<>();
    private TimeUtils() {

    }
    public static TimeUtils getInstance(){
        if (timeUtils==null){
            synchronized (TimeUtils.class){
                if (timeUtils==null){
                    timeUtils=new TimeUtils();
                }
            }
        }
        return timeUtils;
    }

    public void initStartTime(String methodName){
        System.out.println("方法："+methodName+"  enter");
        long startTime = System.currentTimeMillis();
        timeMap.put(methodName, startTime);
    }
    public void printTime(String methodName){
        Long start = timeMap.get(methodName);
        if (start==null){
            return;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("方法："+methodName+" exit 耗时："+(endTime - start));
    }
}
