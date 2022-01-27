package pl.okozaczka.stock;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.okozaczka.stock.payment.PayU;
import pl.okozaczka.stock.payment.RegisterPaymentRequest;
import pl.okozaczka.stock.payment.RegisterPaymentResponse;
import pl.okozaczka.stock.productcatalog.Product;
import pl.okozaczka.stock.productcatalog.ProductCatalog;
import pl.okozaczka.stock.productcatalog.ProductRepository;
import pl.okozaczka.stock.sales.*;
import pl.okozaczka.stock.sales.offerting.OfferMaker;
import pl.okozaczka.stock.sales.ordering.InMemoryReservationStorage;
import pl.okozaczka.stock.sales.ordering.ReservationRepository;
import pl.okozaczka.stock.sales.payment.PayUPaymentGateway;

import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.util.*;

import java.util.Random;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ProductCatalog createProductCatalog(
            ProductRepository productRepository) {
        ProductCatalog productCatalog = new ProductCatalog(productRepository);

        String[] doggos = {"00186969-c51d-462b-948b-30a7e1735908.jpg",
                "00564ba3-e5cb-4b2b-8d97-c65a9ef26c23.png",
                "00b417af-0b5f-42d7-9ad0-6aab6c3db491.jpg",
                "016d49aa-66f0-41de-872b-009367e97325.gif",
                "027eef85-ccc1-4a66-8967-5d74f34c8bb4.jpg",
                "02b1854b-bc69-4417-aa3f-96e63e699858.gif",
                "02f1d7d0-9ff7-44af-8066-dd9247ebe74d.jpg",
                "03024628-188b-408e-a853-d97c9f04f903.jpg",
                "0356c15a-8874-4af3-a02a-ed0ae8d62b55.jpg",
                "037c01a0-99b3-4757-90b5-04df9be427a6.JPG",
                "0415ca3e-0e99-4afa-bec6-bd8a4a7ff6ff.PNG",
                "046e5758-d1ef-436f-b7e2-530134562445.jpg",
                "0508da24-5cbb-487c-815d-9c555e244c21.jpg",
                "054dce82-51c9-49d5-bc7c-4d76e8288d33.jpg",
                "05f4b372-d8ff-42f0-8c11-b78c45d9eaa1.jpg",
                "6be64b1b-8925-40e2-83cd-9291efb6165b.jpg",
                "2a3433a7-c3c5-4fe3-b5e7-c521d136d305.gif",
                "6c32c078-794b-4b95-bbc4-1f272c69486f.jpg"};

        String[] doggosNames = {"Mars","Szarik","Cebo","Azor","Pimpek","Cezar"};

        Random rand = new Random();

        for(int i=0; i<5; i++){

            Product product  = Product.builder()
                    .id(UUID.randomUUID().toString())
                    .price(BigDecimal.valueOf(rand.nextInt(420)+69))
                    .keywords(Arrays.asList())
                    .title("Pieseł " + doggosNames[rand.nextInt(6)])
                    .mediaPath("https://random.dog/" + doggos[rand.nextInt(18)])
                    .build();

            productCatalog.publish(productCatalog.addProduct(product));


        }
        return productCatalog;
    }

    @Bean
    public SalesFacade createSalesFacade(ProductDetailsProvider productDetailsProvider, PayU payU) {
        return new SalesFacade(
                new BasketStorage(),
                productDetailsProvider,
                new OfferMaker(productDetailsProvider),
                new InMemoryReservationStorage(),
                new PayUPaymentGateway(payU));
    }

    @Bean
    public ProductDetailsProvider productDetailsProvider(ProductCatalog productCatalog ) {
        return (id) -> {
            Product product = productCatalog.getById(id);
            return new ProductDetails(
                    product.getId(),
                    product.getPrice()
            );
        };
    }

    @Bean
    public JpaReservationStorage createJpaReervationStorage(ReservationRepository reservationRepository) {
        return new JpaReservationStorage(reservationRepository);
    }
}