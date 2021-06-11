package com.shd.market.persistence.mapper;

import com.shd.market.domain.PurchaseItem;
import com.shd.market.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/*Ponemos la anotacion mapper con el atributo
componentModel para poderlo inyectar desde otros lugares*/
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    /*mapeamos la clase PurchaseItem con ComprasProducto*/
    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    /*Conversion contraria que nos permite usar los mismos campos del
    mapping pero a la inversa con la anotacion siguiente*/
    @InheritInverseConfiguration
    /*Con la siguiente anotacion vamos a ignorar los atributos que no mapearemos
    * ya que todos los atributos de las clases implicadas se deben incluir en un mapper */
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
