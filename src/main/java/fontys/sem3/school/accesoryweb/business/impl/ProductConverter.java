package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.domain.Product;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;

final class ProductConverter {
    private ProductConverter(){

    }
    public static Product convert(ProductEntity product){
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .category(CategoryConverter.convert(product.getCategory()))
                .build();

    }
}
