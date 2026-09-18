package ro.msg4banking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest {
  private final OrderService service = new OrderService();

  @Test
  void nullOrderIsRejected() {
    assertThrows(IllegalArgumentException.class, () -> service.calculateTotal(null));
  }

  @Test
  void emptyOrderCostsNothing() {
    Order order = new Order("1", new Customer("Anna", 1), List.of());
    assertEquals(0.0, service.calculateTotal(order), 0.001);
  }

  @Test
  void smallOrderPaysShipping() {
    Order order = new Order("2", new Customer("Anna", 1), List.of(new Item("Lamp", "OTHER", 50.0, 1)));
    assertEquals(75.49, service.calculateTotal(order), 0.001);
  }

  @Test
  void foodHasLowerTaxAndBigOrderShipsFree() {
    Order order = new Order("3", new Customer("Anna", 1), List.of(new Item("Cheese", "FOOD", 60.0, 2)));
    assertEquals(130.80, service.calculateTotal(order), 0.001);
  }

  @Test
  void mixedItemsAreSummed() {
    Order order = new Order("4", new Customer("Anna", 1), List.of(
      new Item("Apple", "FOOD", 3.5, 3),
      new Item("Pen", "OTHER", 7.25, 2)));
    assertEquals(44.69, service.calculateTotal(order), 0.001);
  }

  @Test
  void goldCustomerGetsTenPercentOff() {
    Order order = new Order("5", new Customer("Bela", 2), List.of(new Item("Chair", "OTHER", 100.0, 1)));
    assertEquals(107.10, service.calculateTotal(order), 0.001);
  }

  @Test
  void employeeGetsTwentyPercentOff() {
    Order order = new Order("6", new Customer("Csilla", 3), List.of(new Item("Chair", "OTHER", 100.0, 1)));
    assertEquals(111.19, service.calculateTotal(order), 0.001);
  }
}
