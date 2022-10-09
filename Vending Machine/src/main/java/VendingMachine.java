
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class VendingMachine{

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_EXIT_OPTION = "Exit";
    private static final String PURCHASE_MENU_FEED_OPTION = "Feed Money";
    private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_FINISH = "Finish Transaction";
    private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT_OPTION };
    private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_OPTION, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH };
    private static final Double[] MONEY_CUSTOMER_CAN_ENTER = {1.00, 2.00, 5.00, 10.00, 20.00};
    private static final Integer[] HIDDEN_SALES_REPORT = {4};
    private String itemName;
    private String itemLocation;
    private double itemCost;
    public static Map<String, Item> vendingMachineMap = new LinkedHashMap<>();

    private Menu menu;

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);

        VendingMachine cli = new VendingMachine(menu);

        readFileAndMapItems();

        System.out.println();
        cli.run();
    }

    public VendingMachine(Menu menu){
        this.menu = menu;
    }

    //reads item document and adds items to classes
    public static void readFileAndMapItems(){
        File fileToBeRead = new File("vendingmachine.csv");
        try (Scanner fileOpener = new Scanner(fileToBeRead)) {
            while (fileOpener.hasNextLine()) {
                String lineOfText = fileOpener.nextLine();
                String [] fileLine = lineOfText.split("\\|");

                if(fileLine[3].equals("Chip")){
                    Chips chipItems = new Chips(fileLine[0], fileLine[1], Double.parseDouble(fileLine[2]));
                    vendingMachineMap.put(fileLine[0], chipItems);

                } else if(fileLine[3].equals("Candy")){
                    Candy candyItem = new Candy(fileLine[0],fileLine[1], Double.parseDouble(fileLine[2]));
                    vendingMachineMap.put(fileLine[0], candyItem);
                }

                else if(fileLine[3].equals("Drink")){
                    Drink beveragesItem = new Drink(fileLine[0],fileLine[1], Double.parseDouble(fileLine[2]));
                    vendingMachineMap.put(fileLine[0], beveragesItem);


                } else if(fileLine[3].equals("Gum")){
                    Gum gumItem = new Gum(fileLine[0],fileLine[1], Double.parseDouble(fileLine[2]));
                    vendingMachineMap.put(fileLine[0], gumItem);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Product list not found");
        }
    }

    public void run() {

        //Imports the Scanner
        Scanner customerInput = new Scanner(System.in);

        // ===== you nay use/modify the existing Menu class or write your own ======
        while (true) {

            Purchase customerPurchase = new Purchase(itemLocation, itemName, itemCost);
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS); {

                //if customer chooses display items, Map is shown with items
                if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                    for (Map.Entry<String, Item> item : vendingMachineMap.entrySet()) {
                        System.out.println(item.getValue().toString().trim());
                    }

                    //if customer chooses purchase, purchase menu is shown to customer
                } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

                    while(true) {
                        String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

                        //if customer chooses purchase, chooses feed money option
                        if (purchaseChoice.equals(PURCHASE_MENU_FEED_OPTION)) {
                            System.out.println("Current Money Provided: $" + customerPurchase.currentMoneyAsString());
                            Double amountOfMoney = (Double) menu.getChoiceFromOptions(MONEY_CUSTOMER_CAN_ENTER);
                            customerPurchase.feedMoney(amountOfMoney);


                        } else if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {

                            //Prints out the List of Items, their location, name, and price
                            for (Map.Entry<String, Item> item : vendingMachineMap.entrySet()) {
                                System.out.println(item.getValue().getLocation() + "| " + item.getValue().getName() + " - $" + item.getValue().getPrice());
                            }
                            System.out.println();
                            System.out.println("Total Balance: $" + customerPurchase.currentMoneyAsString());
                            System.out.print("Please choose a product: ");

                            //Customer enters item Location - Case insensitive
                            String chooseLocation = customerInput.nextLine().toUpperCase();

                            //Bring in Item class and assign Customer's Selection to Item
                            if (!vendingMachineMap.containsKey(chooseLocation)) {
                                System.out.println("That location is invalid, please try again.");
                                purchaseChoice.equals(menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS));
                                continue;
                            }
                            Item itemSelection = vendingMachineMap.get(chooseLocation);
                            customerPurchase.purchaseMenuSelectItem(itemSelection);

                        } else if (purchaseChoice.equals(PURCHASE_MENU_FINISH)) {
                            customerPurchase.purchaseMenuFinish();
                            break;
                        }
                    }

                } else if (choice.equals(MAIN_MENU_EXIT_OPTION)) {
                    System.exit(1);

                } else if (choice.equals(HIDDEN_SALES_REPORT)) {
                }
            }
        }
    }
//	public void salesReport(Purchase customerPurchase, Item itemSelection){
//		//Writing transaction log to Sales.txt
//		File targetFile = new File("src", "SalesReport.txt");
//
//		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
//		String dateString = dateFormat.format(new Date()).toString();
//
//		try(PrintWriter writer = new PrintWriter(new FileOutputStream(targetFile, true))){
//			writer.println(dateString + " " + itemSelection.getName() + " "
//					+ itemSelection.getLocation() + " " + itemSelection.getPrice() + " "
//					+ customerPurchase.getCurrentMoneyProvided());
//		} catch (FileNotFoundException e) {
//			System.out.println("File not found");;
//		}
//	}

}

