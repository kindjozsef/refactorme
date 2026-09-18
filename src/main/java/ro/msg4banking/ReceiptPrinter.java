package ro.msg4banking;

import java.util.Locale;

public class ReceiptPrinter {

  public String print(Order o) {
    StringBuilder sb = new StringBuilder();
    sb.append("Order: ").append(o.getId()).append("\n");

    double t = 0;
    for (Item it : o.getItems()) {
      double p = it.getPrice() * it.getQty();
      if (it.getCategory().equals("FOOD")) {
        p = p + p * 0.09;
      } else {
        p = p + p * 0.19;
      }
      t = t + p;
      sb.append(it.getQty()).append(" x ").append(it.getName())
        .append(" = ").append(String.format(Locale.US, "%.2f", p)).append("\n");
    }
    t = Math.round(t * 100.0) / 100.0;
    sb.append("Subtotal: ").append(String.format(Locale.US, "%.2f", t)).append("\n");

    if (o.getCustomer() != null) {
      if (o.getCustomer().getType() == 2) {
        sb.append("Discount: 10%\n");
      } else if (o.getCustomer().getType() == 3) {
        sb.append("Discount: 20%\n");
      }
    }

    OrderService os = new OrderService();
    double total = os.calculateTotal(o);
    sb.append("Total: ").append(String.format(Locale.US, "%.2f", total)).append("\n");
    return sb.toString();
  }
}
