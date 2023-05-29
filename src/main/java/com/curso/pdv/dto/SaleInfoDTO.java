package com.curso.pdv.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleInfoDTO {
    private String user;
    private String data;
    private List<ProductInfoDTO> productos;
}
