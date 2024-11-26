package cn.bugstack.service.impl;

import cn.bugstack.common.constants.Constants;
import cn.bugstack.dao.IOrderDao;
import cn.bugstack.domain.po.PayOrder;
import cn.bugstack.domain.req.ShopCartReq;
import cn.bugstack.domain.res.PayOrderRes;
import cn.bugstack.domain.vo.ProductVO;
import cn.bugstack.service.IOrderService;
import cn.bugstack.service.rpc.ProductRPC;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {


    @Resource
    private IOrderDao orderDao;
    @Autowired
    private ProductRPC productRPC;

    @Override
    public PayOrderRes createOrder(ShopCartReq shopCartReq) throws Exception {
        //查询当前用户是否存在未支付订单或掉单
        PayOrder payOrderReq = new PayOrder();
        payOrderReq.setUserId(shopCartReq.getUserId());
        payOrderReq.setProductId(shopCartReq.getProductId());

        PayOrder unpaidOrder = orderDao.queryUnPayOrder(payOrderReq);
        if(unpaidOrder != null &&
                Constants.OrderStatusEnum.PAY_WAIT.getCode().equals(unpaidOrder.getStatus())){
            log.info("创建订单-存在未支付订单。userID:{} productId:{} " +
                    "orderId:{}", shopCartReq.getUserId(), shopCartReq.getProductId(),
                    unpaidOrder.getOrderId());
            return PayOrderRes.builder()
                    .orderId(unpaidOrder.getOrderId())
                    .payUrl(unpaidOrder.getPayUrl())
                    .build();
        }else if(unpaidOrder != null &&
                Constants.OrderStatusEnum.CREATE.getCode().equals(unpaidOrder.getStatus())){
            //todo
        }
        //2.首次下单,查询商品，创建订单
        ProductVO productVO=productRPC.queryProductByProductId(shopCartReq.getProductId());
        String orderId= RandomStringUtils.randomNumeric(16);
        orderDao.insert(PayOrder.builder()
                .userId(shopCartReq.getUserId())
                .productId(shopCartReq.getProductId())
                .productName(productVO.getProductName())
                .orderId(orderId)
                .totalAmount(productVO.getPrice())
                .orderTime(new Date())
                .status(Constants.OrderStatusEnum.CREATE.getCode())
                .build());

        //创建支付单 todo

        return PayOrderRes.builder()
                .orderId(orderId)
                .payUrl("todo")
                .build();
    }
}
