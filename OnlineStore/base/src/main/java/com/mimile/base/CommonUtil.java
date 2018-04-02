package com.mimile.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by caidongdong on 2016/11/28 13:29
 * email : mircaidong@163.com
 */
public class CommonUtil {
    /**
     * 检查密码是否符合规范（以字母开头，长度在6~18之间，只能包含字符、数字和下划线）
     * @param str
     * @return
     */
    public static boolean checkPassword(String str) {
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
