package cn.bugstack.service;

public interface ILoginService  {

    String createQrCodeTicket() throws Exception;
    String checkLogin(String ticket);
    void saveLoginSate(String ticket,String openid);
}
