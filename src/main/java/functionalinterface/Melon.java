package functionalinterface;

public class Melon {
  String rank;

  public Melon(String rank) {
    this.rank = rank;
  }

  public void introduce() {
    System.out.println("I am " + rank);
  }
}
