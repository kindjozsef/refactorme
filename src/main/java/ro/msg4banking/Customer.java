package ro.msg4banking;

public class Customer {
  public String name;
  public int type;

  public Customer(String name, int type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public int getType() {
    return type;
  }
}
