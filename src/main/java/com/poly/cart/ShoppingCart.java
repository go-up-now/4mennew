package com.poly.cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Long productId, String productName,int quantity, double price) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        for (CartItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Nếu không, thêm sản phẩm mới vào giỏ hàng
        CartItem newItem = new CartItem(productId, productName, quantity,price  );
        items.add(newItem);
    }
}
