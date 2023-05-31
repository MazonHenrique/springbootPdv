package com.curso.pdv.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.curso.pdv.dto.ProductDTO;
import com.curso.pdv.dto.ProductInfoDTO;
import com.curso.pdv.dto.SaleDTO;
import com.curso.pdv.dto.SaleInfoDTO;
import com.curso.pdv.entity.Sale;
import com.curso.pdv.entity.User;
import com.curso.pdv.entity.ItemSale;
import com.curso.pdv.entity.Product;
import com.curso.pdv.repository.ItemSaleRepository;
import com.curso.pdv.repository.ProductRepositry;
import com.curso.pdv.repository.SaleRepositry;
import com.curso.pdv.repository.UserRepositry;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
    
    private final UserRepositry userRepositry;
    private final ProductRepositry productRepositry;
    private final SaleRepositry saleRepositry;
    private final ItemSaleRepository itemSaleRepository;
    
    //metodo para buscar todas as vendas.
    //este método é chamado na SaleController
    public List<SaleInfoDTO> findAll(){
        return saleRepositry.findAll().stream().map(sale -> getSaleInfo(sale)).collect(Collectors.toList());
    }

    //Metodo para montar uma lista de vendas
    public SaleInfoDTO getSaleInfo(Sale sale){
        SaleInfoDTO saleInfoDTO = new SaleInfoDTO();
        saleInfoDTO.setId(sale.getId());
        saleInfoDTO.setUser(sale.getUser().getName());
        saleInfoDTO.setData(sale.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        saleInfoDTO.setProducts(getProductsInfo(sale.getItems()));
        return saleInfoDTO;
    }
    
    //Metodo para montar uma lista de itens de cada venda
    private List<ProductInfoDTO> getProductsInfo(List<ItemSale> items) {
        return items.stream().map(item -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            productInfoDTO.setDescription(item.getProduct().getName());
            productInfoDTO.setQuantity(item.getQuantity());
            return productInfoDTO;
        }).collect(Collectors.toList());
    }

    //metodo para salvar uma venda. Este metodo e chamado na SaleController
    //Anotação para desfazer o cadastro da venda se der algum erro no cadastro do item da venda.
    @Transactional
    public long save(SaleDTO sale){

        User user =userRepositry.findById(sale.getUserid()).get();

        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setDate(LocalDate.now());
        List<ItemSale> items = getItemSale(sale.getItems());
        
        //salva venda e retorna a venda salva
        //Nesse retorno ja tem o id da venda e posso salvar os itens da venda
        newSale = saleRepositry.save(newSale);
        savaItemSale(items, newSale);

        return newSale.getId();
    
    }

    //Metodo para salvar os itens de uma venda...e chamado depois de salvar a venda, pois precisamos
    //do id da venda para salvar o item da venda.
    private void savaItemSale(List<ItemSale> items, Sale newSale) {
        for(ItemSale item: items){
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products){
        //poderia ser feito tambem com um for ou forech
        return products.stream().map(item -> {
            Product product = productRepositry.getReferenceById(item.getProductid());
            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());
            return itemSale;
        }).collect(Collectors.toList());
    }

    public SaleInfoDTO getById(long id) {
        Sale sale =  saleRepositry.findById(id).get();
        return getSaleInfo(sale);
    }
}