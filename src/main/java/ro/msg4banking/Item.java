package ro.msg4banking;

public class Item {
  private String name;
  private String category;
  private double price;
  private int qty;

  public Item(String name, String category, double price, int qty) {
    this.name = name;
    this.category = category;
    this.price = price;
    this.qty = qty;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public double getPrice() {
    return price;
  }

  public int getQty() {
    return qty;
  }
}
