package org.rkjavahub.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalesServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Spy
    SalesReporter salesReporter;

    @InjectMocks
    SalesService salesService;

    @Test
    void getSalesSummary() {
        when(orderRepository.getOrders("P100")).thenReturn(
                List.of(
                        new Order(1L, "P100", new BigDecimal("12.0"), 3),
                        new Order(2L, "P100", new BigDecimal("12.0"), 2)
                )
        );
        SalesSummary salesSummary = salesService.getSalesSummary("P100");
        assertThat(salesSummary.getSoldItemsCount()).isEqualTo(5);
        assertThat(salesSummary.getTotalRevenue()).isEqualTo(new BigDecimal("60.0"));

        verify(salesReporter).generateSalesSummary(any());
    }

    @Test
    void getMostSoldProduct() {
        when(orderRepository.getAllOrders()).thenReturn(
                List.of(
                        new Order(1L, "P100", new BigDecimal("10.0"), 2),
                        new Order(2L, "P101", new BigDecimal("15.0"), 5),
                        new Order(3L, "P102", new BigDecimal("20.0"), 3),
                        new Order(4L, "P101", new BigDecimal("15.0"), 4)
                )
        );
        String mostSoldProduct = salesService.getMostSoldProduct();
        assertThat(mostSoldProduct).isEqualTo("P101");
    }

}
