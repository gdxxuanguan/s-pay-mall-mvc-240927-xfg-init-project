package cn.bugstack.service;

import cn.bugstack.domain.req.ShopCartReq;
import cn.bugstack.domain.res.PayOrderRes;
import org.apache.commons.lang3.ClassUtils;

public interface IOrderService {

    PayOrderRes createOrder(ShopCartReq shopCartReq) throws Exception;
}
