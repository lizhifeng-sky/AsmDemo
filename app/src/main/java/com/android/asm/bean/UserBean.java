package com.android.asm.bean;

import com.android.asm.bean.manager.CheckNullBeanManager;

/**
 * @author lizhifeng
 * @date 2020/8/10 17:07
 */
@Bean
public class UserBean {
    @CheckNull("用户名不能为null")
    private String userName;
    @CheckNull("年龄不能为null")
    private String userAge;
//    @CheckNull("性别不能为null")
    private String userSex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        try {
            if (userSex==null){
                throw CheckNullBeanManager
                        .getInstance()
                        .throwNull("UserBean","userSex","性别不能为null");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
