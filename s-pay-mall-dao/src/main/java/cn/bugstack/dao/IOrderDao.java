package cn.bugstack.dao;

import cn.bugstack.domain.po.PayOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IOrderDao {
    public void insert(PayOrder payorder);

    public PayOrder queryUnPayOrder(PayOrder payOrderReq);

    void updateOrderPayInfo(PayOrder payOrder);

    void changeOrderPaySuccess(PayOrder order);

    List<String> queryNoPayNotifyOrder();

    List<String> queryTimeoutCloseOrderList();

    boolean changeOrderClose(String orderId);


}
