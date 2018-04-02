package com.mimile.onlinestore.api;

import com.mimile.onlinestore.entity.HomeCampaign;
import com.mimile.onlinestore.entity.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by caidongdong on 2016/12/2 11:51
 * email : mircaidong@163.com
 */
public interface IUserService {

    /**
     * Android客户端登录
     * @param userName
     * @param password
     * @param deviceId
     * @param clientType
     * @return
     */
    @POST("index.php?act=login")
    Observable<User> login(String userName, String password, String deviceId, String clientType);

    /**
     * Android客户端注销
     * @param userName
     * @param sessionId
     * @param clientType
     * @return
     */
    @POST("index.php?act=logout")
    Observable<ResponseBody> logout(String userName,String sessionId,String clientType);

    /**
     * Android客户端邮箱注册
     * @param userName
     * @param password
     * @param email
     * @param clientType
     * @return
     */
    @POST("index.php?act=login&op=register")
    Observable<ResponseBody> registeWithEmail(String userName,String password,String email,String clientType);

    /**
     * Android客户端手机号注册
     * @param userName
     * @param password
     * @param verificationCode
     * @param clientType
     * @return
     */
    @POST("index.php?act=login&op=register_sms")
    Observable<ResponseBody> registeWithPhoneNum(String userName,String password,String verificationCode,String clientType);

    /**
     * 忘记密码找回(邮箱注册用户)
     * @param userName
     * @param emailAddress
     * @return
     */
    @POST("index.php?act=forget_password")
    Observable<ResponseBody> forgetPassword(String userName,String emailAddress);

    /**
     * 忘记密码获取验证码(手机号注册用户)
     * @param phoneNum
     * @return
     */
    @POST("index.php?act=login&op=send_findsms")
    Observable<ResponseBody> forgetPasswordGetVerificationCode(String phoneNum);

    /**
     * 进行修改密码前进行资格验证
     * @param phoneNum
     * @param verificationCode
     * @return
     */
    @POST("index.php?act=login&op=find_password")
    Observable<ResponseBody> forgetPasswordRestPasswrodValidate(String phoneNum,String verificationCode);

    /**
     * 修改密码
     * @param phoneNum
     * @param authKey
     * @return
     */
    @POST("index.php?act=login&op=resetPassword")
    Observable<ResponseBody> forgetPasswordResetPassword(String phoneNum,String authKey);


    /**
     * 我的页面所有
     * @param sessionId
     * @return
     */
    @POST("index.php?act=member_index")
    Observable<ResponseBody> getAccountInfo(String sessionId);

    /**
     * 测试使用京东banner
     * @return
     */
    @GET("campaign/recommend")
    Observable<List<HomeCampaign>> test();


}
