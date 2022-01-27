package pl.okozaczka.stock.sales;

public interface ProductDetailsProvider {
    ProductDetails getProductDetails(String productId);
}