package com.mimile.onlinestore.model;

/**
 * Created by caidongdong on 2016/12/2 09:24
 * email : mircaidong@163.com
 * 用户基本操作接口
 */
public interface IUserModel {

    /**
     * 用户登录
     * @param userName  用户名
     * @param password  密码
     * @param deviceId  允许为空
     * @param clientType Android OR IOS
     */
    void login(String userName,String password,String deviceId,String clientType);

    /**
     * 注销登录
     * @param userName
     * @param sessionId
     * @param clientType
     */
    void logout(String userName,String sessionId,String clientType);

    /**
     * 邮箱注册
     * @param userName
     * @param password
     * @param email
     * @param clientType
     */
    void registeWithEmail(String userName,String password,String email,String clientType);

    /**
     * 手机号注册
     * @param userName
     * @param password
     * @param verificationCode
     * @param clientType
     */
    void registeWithPhoneNum(String userName,String password,String verificationCode,String clientType);

    /**
     * 邮箱注册用户忘记密码
     * @param userName
     * @param emailAddress
     */
    void forgetPassword(String userName,String emailAddress);

    /**
     * 手机注册用户忘记密码获取验证码
     * @param phoneNum
     */
    void forgetPasswordGetVerificationCode(String phoneNum);

    /**
     * 修改密码资格验证
     * @param phoneNum
     * @param verificationCode
     */
    void forgetPasswordRestPasswrodValidate(String phoneNum,String verificationCode);

    /**
     * 修改密码
     * @param phoneNum
     * @param authKey
     */
    void forgetPasswordResetPassword(String phoneNum,String authKey);

    /**
     * 获取我的界面展示数据
     * @param sessionId
     */
    void getAccoutInfo(String sessionId);

    void validateMobilePhone();

    void getVerificationCode();

    void modifyPayPassword();

    void setHeadImg();

    void getAddressList();

    void addAddress();

    void deleteAddress();

    void modifyAddress();

    void signDaily();

    void getFavoriteStoreList();

    void addFavoriteStore();

    void deleteFavoriteStore();

    void getFavoriteGoodsList();

    void deleteFavoriteGoodsItem();

    void addFavoriteGoodsItem();

    void getBrowseHistory();

    void deleteBrowseHistoryItem();

    void rechargeMobilePhone();

    void rechargeWithGiftCard();

    void shakeRecommend();

    void getHotSearchWords();

    //TODO 测试方法

    void test();

}
