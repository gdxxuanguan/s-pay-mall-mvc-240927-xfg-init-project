package cn.bugstack.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class ProductVO {
    private String productId;
    private String productName;
    private String productDesc;
    private BigDecimal price;
}
