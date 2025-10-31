package kr.co.wave.controller.admin.product;

import kr.co.wave.dto.ProductDTO;
import kr.co.wave.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    // 목록 가져오기
    @GetMapping("/admin/product/list")
    public String adminProductList(Model model) {
        List<ProductDTO> productList = productService.getProductAll();

        model.addAttribute("productList", productList);

        return "admin/product/list";
    }

    // 상세보기
    @GetMapping("/admin/product/view/{proId}")
    public String adminProductView(@PathVariable int proId, Model model) {
        ProductDTO product = productService.getProductById(proId);
        model.addAttribute("product", product);

        return "admin/product/view";
    }

    // 삭제
    @GetMapping("/admin/product/delete/{proId}")
    public String adminProductDelete(@PathVariable int proId, Model model) {
        productService.deleteProductById(proId);

        return "redirect:/admin/product/view";
    }

    // 등록화면 이동
    @GetMapping("/admin/product/register")
    public String adminProductRegister() {

        return "admin/product/register";
    }

    // 등록
    @PostMapping("/admin/product/register")
    public String adminProductRegister(ProductDTO productDTO) {

        productService.registerProduct(productDTO);

        return "redirect:/admin/product/list";
    }


}
