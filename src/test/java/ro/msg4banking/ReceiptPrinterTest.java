package ro.msg4banking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiptPrinterTest {

  private final ReceiptPrinter printer = new ReceiptPrinter();

  @Test
  void printsReceiptWithDiscount() {
    Order order = new Order("100", new Customer("Bela", 2), List.of(
      new Item("Cheese", "FOOD", 60.0, 2),
      new Item("Lamp", "OTHER", 50.0, 1)));

    String expected = """
                Order: 100
                2 x Cheese = 130.80
                1 x Lamp = 59.50
                Subtotal: 190.30
                Discount: 10%
                Total: 171.27
                """;

    assertEquals(expected, printer.print(order));
  }

  @Test
  void printsReceiptWithoutDiscount() {
    Order order = new Order("101", new Customer("Anna", 1), List.of(new Item("Lamp", "OTHER", 50.0, 1)));

    String expected = """
                Order: 101
                1 x Lamp = 59.50
                Subtotal: 59.50
                Total: 75.49
                """;

    assertEquals(expected, printer.print(order));
  }
}
