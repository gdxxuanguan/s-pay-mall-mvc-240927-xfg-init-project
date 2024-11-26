package cn.bugstack.dao;

import cn.bugstack.domain.po.PayOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IOrderDao {
    public void insert(PayOrder payorder);

    public PayOrder queryUnPayOrder(PayOrder payOrderReq);
}
