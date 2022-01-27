package pl.okozaczka.stock.sales.payment;

import pl.okozaczka.stock.sales.ordering.CustomerDetails;
import pl.okozaczka.stock.sales.ordering.PaymentDetails;

import java.math.BigDecimal;

public interface PaymentGateway {
    PaymentDetails register(String id, BigDecimal total, CustomerDetails customerDetails);
}