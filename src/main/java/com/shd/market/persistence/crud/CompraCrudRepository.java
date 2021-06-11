package com.shd.market.persistence.crud;

import com.shd.market.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/*Esta interface va a heredar los metodos del CrudRepository y
le asignaremos los parametros de la Entity y el tipo de su clave primaria
en este caso es Compra y su clave es entera*/
public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    Optional<List<Compra>> findByIdCliente(String idCliente);

}
