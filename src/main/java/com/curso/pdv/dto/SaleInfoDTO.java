package com.curso.pdv.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleInfoDTO {
    private long id;
    private String user;
    private String data;
    private BigDecimal total;
    private List<ProductInfoDTO> products;
}
