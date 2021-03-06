package a2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the Menu class. A Menu contains all items on the menu in the restaurant and includes
 * their options and prices.
 */
public class Menu {

  /**
   * All pizzas served in the pizza parlour including types, sizes, and prices
   */
  private HashMap<String, HashMap<String, Float>> pizzaSet;
  /**
   * Add drinks served in the pizza parlour including types and prices
   */
  private HashMap<String, Float> drinkSet;
  /**
   * List of all toppings available for pizzas in the pizza parlour
   */
  private List<String> toppingList;
  /**
   * Price of extra toppings on a pizza
   */
  private Float toppingPrice;

  /**
   * Getter for the available pizza types in the restaurant.
   *
   * @return ArrayList of available pizza types
   */
  List<String> getPizzaTypes() {
    return new ArrayList<>(pizzaSet.keySet());
  }

  /**
   * Getter for the available pizza toppings in the restaurant.
   *
   * @return List of available pizza toppings
   */
  List<String> getPizzaToppings() {
    return toppingList;
  }

  /**
   * Getter for the available pizza sizes for a specific pizza type in the restaurant.
   *
   * @param pizzaType the pizza type to check for
   * @return List of available pizza sizes
   */
  List<String> getPizzaSizes(String pizzaType) {
    return new ArrayList<>(pizzaSet.get(pizzaType).keySet());
  }

  /**
   * Getter for the available drinks in the restaurant.
   *
   * @return List of available drinks
   */
  List<String> getDrinks() {
    return new ArrayList<>(drinkSet.keySet());
  }

  /**
   * Getter for the price of a specific pizza on the menu depending on the type, size and toppings
   * on the pizza.
   *
   * @param pizzaType the type of pizza to check price for
   * @param pizzaSize the size of pizza to check price for
   * @param numExtraToppings the number of extra toppings for pizza
   * @return the price of the specified pizza
   */
  Float getPizzaPrice(String pizzaType, String pizzaSize, int numExtraToppings) {
    return this.pizzaSet.get(pizzaType).get(pizzaSize) + numExtraToppings * this.toppingPrice;
  }

  /**
   * Getter for the price of a specific drink on the menu.
   *
   * @param currDrink the drink to check price for
   * @return the price of the specified drink
   */
  Float getDrinkPrice(String currDrink) {
    return this.drinkSet.get(currDrink);
  }

  /**
   * Setter for available pizza toppings.
   *
   * @param toppingList the toppings to set the menu's toppings to
   */
  void setToppings(List<String> toppingList) {
    this.toppingList = toppingList;
  }

  /**
   * Setter for restaurant's available pizza information, provided as a Hashmap of pizza type to
   * Hashmap of pizza size to price.
   *
   * @param pizzaSet Hashmap containing all pizza information
   */
  void setPizzas(HashMap<String, HashMap<String, Float>> pizzaSet) {
    this.pizzaSet = pizzaSet;
  }

  /**
   * Setter for restaurant's available drink information, provided as a HashMap of drink type to
   * price.
   *
   * @param drinkSet Hashmap containing all drink information
   */
  void setDrinks(HashMap<String, Float> drinkSet) {
    this.drinkSet = drinkSet;
  }

  /**
   * Setter for the price of toppings on the menu.
   *
   * @param toppingPrice the price to set to
   */
  void setToppingPrice(Float toppingPrice) {
    this.toppingPrice = toppingPrice;
  }

  /**
   * Getting for the specific item on the menu with the given specifications. Returns a list of
   * sizes plus prices for a pizza menu item, and the price of a drink menu item.
   *
   * @param menuItem the name of the item to search the menu for
   * @return string representation of the specified menu item
   */
  String getMenuItem(String menuItem) {
    menuItem = menuItem.toLowerCase();
    StringBuilder menuItemString = new StringBuilder();
    if (this.pizzaSet.containsKey(menuItem)) {
      String pizzaString = menuItem + ": ";
      menuItemString.append(pizzaString).append(getPizzaSizesAsString(menuItem));

    } else if (this.drinkSet.containsKey(menuItem)) {
      String drinkString = menuItem + ": ($" + this.drinkSet.get(menuItem).toString() + ")";
      menuItemString.append(drinkString);
    } else {
      menuItemString.append("Item not found");
    }
    return menuItemString.toString().toUpperCase();
  }

  /**
   * Returns a string representation of the different pizza sizes plus prices for the specified
   * pizza type.
   *
   * @param type the type of pizza to check for
   * @return string representation of pizza sizes plus prices
   */
  private String getPizzaSizesAsString(String type) {
    StringBuilder menuString = new StringBuilder();
    for (String size : this.pizzaSet.get(type).keySet()) {
      menuString.append(size).append(" ($").append(this.pizzaSet.get(type).get(size).toString())
          .append(") ");
    }
    return menuString.toString().toUpperCase();
  }

  /**
   * Returns a string representation of the menu.
   *
   * @return string representation of menu
   */
  public String toString() {
    StringBuilder menuString = new StringBuilder();
    menuString.append("Pizzas:\n");
    for (String type : this.pizzaSet.keySet()) {
      String pizzaString = "- " + type + " ";
      menuString.append(pizzaString).append(" ").append(getPizzaSizesAsString(type)).append("\n");
    }
    menuString.append("Drinks:\n");
    for (String drink : this.drinkSet.keySet()) {
      String drinkString = "- " + drink + " ($";
      menuString.append(drinkString).append(this.drinkSet.get(drink).toString()).append(")\n");
    }
    menuString.append("Additional Toppings: ($").append(this.toppingPrice).append(")\n");

    List<String> toppings = getPizzaToppings();
    for (String topping : toppings) {
      String toppingString = "- " + topping + "\n";
      menuString.append(toppingString);
    }

    return menuString.toString().toUpperCase();
  }


}
