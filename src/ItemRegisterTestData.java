/**
 * Class consisting of the testdata for item register.
 * Used for testing the application.
 */
public class ItemRegisterTestData {

  /**
   * Method that stores the test data for this program. Before adding the data, it uses the boolean
   * itemNotExists(itemNumber) to check if the item already exists.
   */
  public static void addTestData(ItemRegister itemRegister) {

    if (itemRegister.itemNotExists("12BK5")) {
      itemRegister.addItem(new Item("12BK5", "Wood door",
          "Tall and heavy", 1500, "Coolest Doors In Town",
          10.5, 1.5, 2.5, "Brown", 100, ItemCategory.DOORS));
    }
    if (itemRegister.itemNotExists("BE394ER")) {
      itemRegister.addItem(new Item("BE394ER", "Steel door",
          "Heavy and shiny", 2000, "Coolest Doors In Town",
          15.00, 2, 3.3, "Silver", 78, ItemCategory.DOORS));
    }
    if (itemRegister.itemNotExists("9898ABC")) {
      itemRegister.addItem(new Item("9898ABC", "Horizontal window",
          "Transparent, with white frames", 400, "Best Windows You Will Find",
          8.4, 4, 2.1, "Withe", 980, ItemCategory.WINDOWS));
    }
    if (itemRegister.itemNotExists("FIAT7865")) {
      itemRegister.addItem(new Item("FIAT7865", "Wood floor laminate",
          "Flat and long piece", 500, "Exclusive Floor Laminates",
          5.0, 0.5, 3.5, "Brown", 40, ItemCategory.FLOOR_LAMINATE));
    }
    if (itemRegister.itemNotExists("4789KIP")) {
      itemRegister.addItem(new Item("4789KIP", "Softwood lumber",
          "Softwood, high quality", 200, "Fresh Lumber",
          20.6, 0.8, 4, "Light brown", 900, ItemCategory.LUMBER));
    }
  }
}
