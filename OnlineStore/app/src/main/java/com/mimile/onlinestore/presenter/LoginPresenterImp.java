package com.mimile.onlinestore.presenter;

import com.mimile.onlinestore.app.OnlieStoreApplication;
import com.mimile.onlinestore.entity.User;
import com.mimile.onlinestore.model.IUserModel;
import com.mimile.onlinestore.model.UserModelImp;
import com.mimile.onlinestore.util.CommonCallback;
import com.mimile.onlinestore.view.iview.ILoginView;

/**
 * Created by caidongdong on 2016/12/1 17:42
 * email : mircaidong@163.com
 */
public class LoginPresenterImp implements ILoginPresenter,CommonCallback<User> {

    private IUserModel IUserModel;
    private ILoginView iLoginView;

    public LoginPresenterImp(ILoginView iLoginView) {
        IUserModel = new UserModelImp(this);
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(String userName, String password, String deviceId, String clientType) {
        iLoginView.showProgress();
        IUserModel.login(userName, password, deviceId, clientType);
    }


    @Override
    public void onSuccess(User user) {
        iLoginView.hideProgress();
        OnlieStoreApplication.getInstance().setUser(user);
    }

    @Override
    public void onFailure(String error) {
        iLoginView.hideProgress();
        //TODO 加入登录失败提示
    }
}
