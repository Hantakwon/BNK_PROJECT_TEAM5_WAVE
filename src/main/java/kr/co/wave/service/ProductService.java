package kr.co.wave.service;

import kr.co.wave.dto.ProductDTO;
import kr.co.wave.entity.Product;
import kr.co.wave.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    // 상품등록
    public void registerProduct(ProductDTO productDTO) {
        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    // 아이디로 상품 찾기
    public ProductDTO getProductById(int proId) {
        Optional<Product> optProduct = productRepository.findById(proId);
        return optProduct.map(product -> modelMapper.map(product, ProductDTO.class)).orElse(null);
    }

    // 상품 전체 찾기
    public List<ProductDTO> getProductAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        return productDTOList;
    }

    // 상품 삭제
    public void deleteProductById(int proId) {
        productRepository.deleteById(proId);
    }

}
