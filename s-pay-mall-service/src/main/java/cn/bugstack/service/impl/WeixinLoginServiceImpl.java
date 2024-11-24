package cn.bugstack.service.impl;

import cn.bugstack.service.ILoginService;
import cn.bugstack.service.weixin.IWeixinApiService;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WeixinLoginServiceImpl implements ILoginService {

    @Value("${weixin.config.app-id}")
    private String appid;
    @Value("${weixin.config.app-secret}")
    private String appSecret;
    @Value("${weixin.config.template_id}")
    private String template_id;

    @Resource
    private Cache<String,String> weixinAccessToken;
    @Resource
    private IWeixinApiService weixinApiService;
    @Resource
    private Cache<String,String> openidToken;

    @Override
    public String createQrCodeTicket() throws Exception {
        String accessToken = weixinAccessToken.getIfPresent(appid);
        if(accessToken == null){
            weixinApiService.getToken("client")
        }
        return accessToken;
    }

    @Override
    public String checkLogin(String ticket) {
        return "";
    }

    @Override
    public void saveLoginSate(String ticket, String openid) {

    }
}
