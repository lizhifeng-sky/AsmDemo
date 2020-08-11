package com.android.asm.simulate;


/**
 * @author lizhifeng
 * @date 2020/5/6 11:25
 */
public class ExecFieldTest {
    private String value;
    public String execFieldMethod() {
        try {
            if (value==null){
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return value;
    }
}
