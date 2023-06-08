package com.curso.pdv.dto;

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
    private List<ProductInfoDTO> products;
}
