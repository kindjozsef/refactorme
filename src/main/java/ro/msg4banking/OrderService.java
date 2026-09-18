package ro.msg4banking;

public class OrderService {
  public double calculateTotal(Order o) {
    if (o == null) {
      throw new IllegalArgumentException("order is null");
    }
    if (o.getItems() == null || o.getItems().size() == 0) {
      return 0;
    }

    double t = 0;
    for (int i = 0; i < o.getItems().size(); i++) {
      Item it = o.getItems().get(i);
      double p = it.getPrice() * it.getQty();
      if (it.getCategory().equals("FOOD")) {
        p = p + p * 0.09;
      } else {
        p = p + p * 0.19;
      }
      t = t + p;
    }
    t = Math.round(t * 100.0) / 100.0;

    if (o.getCustomer() != null) {
      if (o.getCustomer().getType() == 2) {
        t = t - t * 0.10;
      } else if (o.getCustomer().getType() == 3) {
        t = t - t * 0.20;
      }
    }
    t = Math.round(t * 100.0) / 100.0;

    double s = 0;
    if (t < 100) {
      s = 15.99;
    } else {
      s = 0;
    }
    t = t + s;
    t = Math.round(t * 100.0) / 100.0;

    return t;
  }
}
