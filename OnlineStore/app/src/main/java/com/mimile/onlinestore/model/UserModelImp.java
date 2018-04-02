package com.mimile.onlinestore.model;

import com.mimile.onlinestore.api.ApiManager;
import com.mimile.onlinestore.entity.HomeCampaign;
import com.mimile.onlinestore.entity.User;
import com.mimile.onlinestore.util.CommonCallback;
import com.mimile.onlinestore.util.CommonErrorCode;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by caidongdong on 2016/12/2 10:50
 * email : mircaidong@163.com
 * 用户处理数据的具体实现
 */
public class UserModelImp implements IUserModel {
    private CommonCallback callback;

    public UserModelImp(CommonCallback callback) {
        this.callback = callback;
    }

    @Override
    public void login(String userName, String password, String deviceId, String clientType) {
        ApiManager.getInstance().userService.login(userName, password, deviceId, clientType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        //TODO
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(CommonErrorCode.BUSINESS_ERROR);
                    }

                    @Override
                    public void onNext(User user) {
                        callback.onSuccess(user);
                    }
                });
    }

    @Override
    public void logout(String userName, String sessionId, String clientType) {

    }

    @Override
    public void registeWithEmail(String userName, String password, String email, String clientType) {

    }

    @Override
    public void registeWithPhoneNum(String userName, String password, String verificationCode, String clientType) {

    }

    @Override
    public void forgetPassword(String userName, String emailAddress) {

    }

    @Override
    public void forgetPasswordGetVerificationCode(String phoneNum) {

    }

    @Override
    public void forgetPasswordRestPasswrodValidate(String phoneNum, String verificationCode) {

    }

    @Override
    public void forgetPasswordResetPassword(String phoneNum, String authKey) {

    }

    @Override
    public void getAccoutInfo(String sessionId) {

    }

    @Override
    public void validateMobilePhone() {

    }

    @Override
    public void getVerificationCode() {

    }

    @Override
    public void modifyPayPassword() {

    }

    @Override
    public void setHeadImg() {

    }

    @Override
    public void getAddressList() {

    }

    @Override
    public void addAddress() {

    }

    @Override
    public void deleteAddress() {

    }

    @Override
    public void modifyAddress() {

    }

    @Override
    public void signDaily() {

    }

    @Override
    public void getFavoriteStoreList() {

    }

    @Override
    public void addFavoriteStore() {

    }

    @Override
    public void deleteFavoriteStore() {

    }

    @Override
    public void getFavoriteGoodsList() {

    }

    @Override
    public void deleteFavoriteGoodsItem() {

    }

    @Override
    public void addFavoriteGoodsItem() {

    }

    @Override
    public void getBrowseHistory() {

    }

    @Override
    public void deleteBrowseHistoryItem() {

    }

    @Override
    public void rechargeMobilePhone() {

    }

    @Override
    public void rechargeWithGiftCard() {

    }

    @Override
    public void shakeRecommend() {

    }

    @Override
    public void getHotSearchWords() {

    }

    @Override
    public void test() {
        ApiManager.getInstance().userService.test()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<HomeCampaign>>() {
                    @Override
                    public void onCompleted() {
                        //TODO
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(CommonErrorCode.BUSINESS_ERROR);
                    }

                    @Override
                    public void onNext(List<HomeCampaign> list) {
                        callback.onSuccess(list);
                    }
                });
    }
}
