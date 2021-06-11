package com.shd.market.persistence;

import com.shd.market.domain.Purchase;
import com.shd.market.domain.repository.PurchaseRepository;
import com.shd.market.persistence.crud.CompraCrudRepository;
import com.shd.market.persistence.entity.Compra;
import com.shd.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/*Vamos a implementar el repositorio en este caso es el PurchaseRepository*/
@Repository
public class CompraRepository implements PurchaseRepository {

    /*Inyectamos la interfaz con la anotacion @Autowired,
    esta interfaz contiene los metodos de CRUD para interactuar con la BD*/
    @Autowired
    private CompraCrudRepository compraCrudRepository;

    /*Inyectamos el mapper, porque este repositorio debe hablar en terminos del dominio*/
    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
