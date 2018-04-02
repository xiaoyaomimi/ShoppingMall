package com.mimile.onlinestore.view.iview;

import com.mimile.base.view.IBaseView;

/**
 * Created by caidongdong on 2016/12/1 17:44
 * email : mircaidong@163.com
 */
public interface ILoginView<User> extends IBaseView {

    /**
     * 登录成功
     * @param usr
     */
    void loginSuccess(User usr);

    /**
     * 登录失败
     * @param str
     */
    void loginFailed(String str);
}
