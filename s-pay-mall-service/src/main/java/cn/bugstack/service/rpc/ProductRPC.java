package cn.bugstack.service.rpc;

import cn.bugstack.domain.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductRPC {

    public ProductVO queryProductByProductId(String productId) {
        ProductVO productVO=new ProductVO();
        productVO.setProductId(productId);
        productVO.setProductName("测试商品");
        productVO.setProductDesc("这是测试商品");
        productVO.setPrice(new BigDecimal("1.69"));
        return productVO;
    }
}
