package cn.bugstack.service;

import cn.bugstack.domain.req.ShopCartReq;
import cn.bugstack.domain.res.PayOrderRes;
import org.apache.commons.lang3.ClassUtils;
import java.util.List;

public interface IOrderService {

    PayOrderRes createOrder(ShopCartReq shopCartReq) throws Exception;

    void changeOrderPaySuccess(String orderId);

    List<String> queryNoPayNotifyOrder();

    List<String> queryTimeoutCloseOrderList();

    boolean changeOrderClose(String orderId);


}
