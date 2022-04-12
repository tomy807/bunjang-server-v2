package com.example.bunjangv2.src.product;

import com.example.bunjangv2.entity.*;
import com.example.bunjangv2.src.address.AddressRepository;
import com.example.bunjangv2.src.category.CategoryService;
import com.example.bunjangv2.src.product.dto.ProductDto;
import com.example.bunjangv2.src.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createProduct(ProductDto productDto, User user) {

        CategoryLarge categoryLarge = categoryService.findCategoryLarge(productDto.getCategoryLarge());
        CategoryMiddle categoryMiddle = categoryService.findCategoryMiddle(productDto.getCategoryMiddle());
        CategorySmall categorySmall = categoryService.findCategorySmall(productDto.getCategorySmall());
        user= userRepository.findById(user.getId()).get();
        List<Address> addresses = user.getAddresses();

        String directAddress = "지역정보없음";
        for (Address address : addresses) {
            if (address.getMain().equals("MAIN") && address.getAddressType().equals("DIRECT")) {
                directAddress = address.getAddress();
                break;
            }
        }
//        Optional<Address> directAddressOP = addressRepository.findAddressByUserAndMainAndAddressType(user, "MAIN", "DIRECT");
//        if (directAddressOP.isEmpty()) {
//            directAddress = "지역정보없음";
//        } else {
//            directAddress = directAddressOP.get().getAddress();
//        }
        Product product = Product.createProduct(productDto, categoryLarge, categoryMiddle, categorySmall, user, directAddress);


        productRepository.save(product);
    }

    public List<ProductDto> getProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(new ProductDto(product));
        }

        return productDtos;

    }

    public ProductDto getProduct(Long productIdx) {
        Product product = productRepository.findById(productIdx).orElseThrow(EntityNotFoundException::new);
        return new ProductDto(product);
    }

}
