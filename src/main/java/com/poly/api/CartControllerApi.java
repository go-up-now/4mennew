package com.poly.api;

import com.poly.cart.CartItem;
import com.poly.cart.ShoppingCart;
import com.poly.dto.request.OrderDetailCreateRequest;
import com.poly.dto.request.OrderDetailUpdateRequest;
import com.poly.entity.OrderDetail;
import com.poly.service.OrderDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/cart")
public class CartControllerApi {
    @Autowired
    private HttpSession session;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartItem cartItemRequest) {
        Map<String,Object> cartsrs = new HashMap<>();
        Long productId = cartItemRequest.getProductId();
        String productName = cartItemRequest.getProductName();
        Integer quantity = cartItemRequest.getQuantity();
        Double price = cartItemRequest.getPrice();

        if (productId == null || productName == null || quantity == null || price == null) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
        }
        cart.addItem(productId, productName, quantity, price);
        session.setAttribute("cart", cart);
        cartsrs.put("Data",cart);
        return ResponseEntity.ok(cartsrs);
    }

}
