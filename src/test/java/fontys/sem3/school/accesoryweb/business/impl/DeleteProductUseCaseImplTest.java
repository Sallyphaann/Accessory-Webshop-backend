package fontys.sem3.school.accesoryweb.business.impl;

import fontys.sem3.school.accesoryweb.persistence.ProductRepository;
import fontys.sem3.school.accesoryweb.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteProductUseCaseImplTest {
    @Mock
    private ProductRepository mockProductRepository;
    @InjectMocks
    DeleteProductUseCaseImpl deleteProductUseCase;
    @Test
    void deleteProduct_shouldReturnDeleteProduct() {
        deleteProductUseCase = new DeleteProductUseCaseImpl(mockProductRepository);
       long productID = 1L;
        deleteProductUseCase.deleteProduct(productID);
        verify(mockProductRepository).deleteById(productID);

    }

}