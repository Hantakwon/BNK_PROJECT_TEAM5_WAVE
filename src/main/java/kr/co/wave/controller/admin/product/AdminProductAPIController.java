package kr.co.wave.controller.admin.product;

import kr.co.wave.dto.ProductDTO;
import kr.co.wave.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminProductAPIController {

    private final ProductService productService;


    @GetMapping("/admin/product/api/list")
    public ResponseEntity<List<ProductDTO>> adminProductList() {
        List<ProductDTO> productList = productService.getProductAll();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("/admin/product/api/view/{id}")
    public ResponseEntity<ProductDTO> adminProductView(@PathVariable int id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/admin/product/api/register")
    public String adminProductRegister(ProductDTO productDTO) {

        productService.registerProduct(productDTO);

        return "redirect:/admin/product/list";
    }


}
