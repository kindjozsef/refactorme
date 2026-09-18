package ro.msg4banking;

import java.util.List;

public class Order {
  private String id;
  private Customer customer;
  private List<Item> items;

  public Order(String id, Customer customer, List<Item> items) {
    this.id = id;
    this.customer = customer;
    this.items = items;
  }

  public String getId() {
    return id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public List<Item> getItems() {
    return items;
  }
}
