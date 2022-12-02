package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ScheduledFuture;


public class Constructor {
    double sumFood = 0;
    double sumOther = 0;
    double sumEntertainment = 0;
    double sumClothes = 0;
    static File myObj = new File("purchases.txt");
    static Scanner scanner = new Scanner(System.in);
    private boolean exit = true;
    private double balance = 0;
    double sumAll = 0;
    private ArrayList<String> foodPurch = new ArrayList<>();
    private ArrayList<String> foodPris = new ArrayList<>();
    private ArrayList<String> ClothesPurch = new ArrayList<>();
    private ArrayList<String> ClothespPris = new ArrayList<>();
    private ArrayList<String> EntertainmentPurch = new ArrayList<>();
    private ArrayList<String> EntertainmentPris = new ArrayList<>();
    private ArrayList<String> OtherPurch = new ArrayList<>();
    private ArrayList<String> OtherPris = new ArrayList<>();
    TreeMap<Double, String> food = new TreeMap<Double, String>(Collections.reverseOrder());
    TreeMap<Double, String> All = new TreeMap<Double, String>(Collections.reverseOrder());
    TreeMap<Double, String> other = new TreeMap<Double, String>(Collections.reverseOrder());
    TreeMap<Double, String> clother = new TreeMap<Double, String>(Collections.reverseOrder());
    TreeMap<Double, String> tree = new TreeMap<Double, String>(Collections.reverseOrder());

    public void constructor () throws FileNotFoundException {
        while (exit) {
            message();
            int action = scanner.nextInt();
            switch (action) {
                case 0:
                    exit();
                    break;
                case 1:
                    addIncome(balance);
                    break;
                case 2:
                    listForAdd();
                    break;
                case 3:
                    listForList();
                    break;
                case 4:
                    balance(getBalance());
                    break;
                case 5:
                    Save();
                    break;
                case 6:
                    Load();
                    break;
                case 7:
                    Sort();
                    break;

            }
        }
    }
    public void exit() {
        exit = false;
        System.out.println();
        System.out.println("Bye!");
    }
    public void message() {
        System.out.println("Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit");
    }
    public void addIncome(double balance) {
        System.out.println();
        System.out.println("Enter income:");
        int add = scanner.nextInt();
        this.balance = balance;
        setBalance(balance + add);
        System.out.println("Income was added!");
        System.out.println();
    }
    public void balance(double balance) {
        this.balance = balance;
        System.out.println();
        System.out.println("Balance:" + "$" + balance);
        System.out.println();
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    public void addPurchase(ArrayList<String> purch, ArrayList<String> pris) {
        System.out.println();
        scanner.nextLine();
        System.out.println("Enter purchase name:");
        String purchase = scanner.nextLine();
        System.out.println("Enter its price:");
        String preis = scanner.nextLine();
        purch.add(purchase);
        pris.add(preis);
        System.out.println("Purchase was added!");
        balance = balance - Double.parseDouble(preis);
    }
    public void listPurchase(ArrayList<String> purch, ArrayList<String> pris) {
       double sum = 0;
       if (purch.size() == 0) {
           System.out.println();
           System.out.println("The purchase list is empty");
           System.out.println();
       } else {
           for (int i = 0; i < purch.size(); i++) {
               sum  += Double.parseDouble(pris.get(i));
               System.out.println(purch.get(i) + "$" + pris.get(i));
           }
           System.out.println("Total sum: $" + sum);
           System.out.println();
       }

    }
    public void messageV2() {
        System.out.println();
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back");
    }
    public void listForAdd() {
        boolean stop = true;
        while (stop) {
            messageV2();
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    addPurchase(foodPurch, foodPris);
                    break;
                case 2:
                    addPurchase(ClothesPurch, ClothespPris);
                    break;
                case 3:
                    addPurchase(EntertainmentPurch, EntertainmentPris);
                    break;
                case 4:
                    addPurchase(OtherPurch, OtherPris);
                    break;
                case 5:
                    System.out.println();
                    stop = false;
                    break;
                default:
                    break;
            }
        }
    }
    public void listForList() {
        if (foodPurch.size() == 0 && ClothesPurch.size() == 0 && EntertainmentPurch.size() == 0 && OtherPurch.size() == 0) {
            System.out.println("The purchase list is empty!");
        } else {
            boolean stop = true;
            while (stop) {
                messageV3();
                int action = scanner.nextInt();
                switch (action) {
                    case 1:
                        System.out.println();
                        System.out.println("Food:");
                        listPurchase(foodPurch, foodPris);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Clothes:");
                        listPurchase(ClothesPurch, ClothespPris);
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Entertainment:");
                        listPurchase(EntertainmentPurch, EntertainmentPris);
                        break;
                    case 4:
                        System.out.println();
                        System.out.println("Other:");
                        listPurchase(OtherPurch, OtherPris);
                        break;
                    case 5:
                        System.out.println();
                        sumAll = 0;
                        System.out.println("ALL:");
                        printALL(foodPurch, foodPris);
                        printALL(ClothesPurch, ClothespPris);
                        printALL(EntertainmentPurch, EntertainmentPris);
                        printALL(OtherPurch, OtherPris);
                        System.out.println("Total sum: $" + sumAll);
                        break;
                    case 6:
                        System.out.println();
                        stop = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
    public void printALL(ArrayList<String> purch, ArrayList<String> pris) {
        for (int i = 0; i < purch.size(); i++) {
            sumAll  += Double.parseDouble(pris.get(i).replace(",", "."));
            System.out.println(purch.get(i) + "$" + pris.get(i));
        }
    }
    public void messageV3() {
        System.out.println();
        System.out.println("Choose the type of purchases\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) All\n" +
                "6) Back");
    }
    public void Save() {
        try {
            FileWriter myWriter = new FileWriter("purchases.txt");
            myWriter.write(String.valueOf(getBalance() + "\n"));
            for (int i = 0; i < foodPurch.size(); i++) {
                myWriter.write("Food\n");
                myWriter.write(foodPurch.get(i) + " $" + foodPris.get(i) + "\n");
            }
            for (int i = 0; i < ClothesPurch.size(); i++) {
                myWriter.write("Clothes\n");
                myWriter.write(ClothesPurch.get(i) + " $" + ClothespPris.get(i) + "\n");
            }
            for (int i = 0; i < EntertainmentPurch.size(); i++) {
                myWriter.write("Entertainment\n");
                myWriter.write(EntertainmentPurch.get(i) + " $" + EntertainmentPris.get(i) + "\n");
            }
            for (int i = 0; i < OtherPurch.size(); i++) {
                myWriter.write("Other\n");
                myWriter.write(OtherPurch.get(i) + " $" + OtherPris.get(i) + "\n");
            }
            myWriter.close();
            System.out.println();
            System.out.println("Purchases were saved!");
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Load() throws FileNotFoundException {
        Main main = new Main();
        Scanner scanner1 = new Scanner(myObj);
        setBalance(Double.parseDouble(scanner1.nextLine()));
        while (scanner1.hasNextLine()) {
            String k = scanner1.nextLine();
            switch (k) {
                case "Food":
                    test(scanner1, foodPurch, foodPris);
                    break;
                case "Clothes":
                    test(scanner1, ClothesPurch, ClothespPris);
                    break;
                case "Entertainment":
                    test(scanner1, EntertainmentPurch, EntertainmentPris);
                    break;
                case "Other":
                    test(scanner1, OtherPurch, OtherPris);
                    break;
            }
        }
        System.out.println();
        System.out.println("Purchases were loaded!");
        System.out.println();
    }
    public void addLoader(String m, ArrayList<String> purch, ArrayList<String> pris) {
        int indexLast$ = m.lastIndexOf("$");
        double price = Double.parseDouble(m.substring(indexLast$ + 1));
        String name = m.substring(0, indexLast$);
        purch.add(name);
        pris.add(String.valueOf(price));
    }
    public void test(Scanner scanner1, ArrayList<String> purch, ArrayList<String> pris) {
        DecimalFormat dec = new DecimalFormat("#0.00");
        String m = scanner1.nextLine();
        int indexLast$ = m.lastIndexOf("$");
        double price = Double.parseDouble(m.substring(indexLast$ + 1));
        String name = m.substring(0, indexLast$);
        purch.add(name);
        pris.add(dec.format(price));

    }
    public void SortMessage() {
        System.out.println("How do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back");
    }
    public void Sort() {
        boolean stop = true;
        while (stop) {
            System.out.println();
            SortMessage();
            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    if (foodPurch.size() == 0 && ClothesPurch.size() == 0 && EntertainmentPurch.size() == 0 && OtherPurch.size() == 0) {
                        System.out.println();
                        System.out.println("The purchase list is empty!");
                    } else {
                        System.out.println();
                        System.out.println("All:");
                        sortMap(All, foodPurch, foodPris);
                        sortMap(All, OtherPurch, OtherPris);
                        sortMap(All, EntertainmentPurch, EntertainmentPris);
                        sortMap(All, ClothesPurch, ClothespPris);
                        All.put(3.501, "Milk ");
                        mapPrint(All);
                        double sumAll = sumType(sumFood, foodPris) + sumType(sumEntertainment, EntertainmentPris)
                                + sumType(sumClothes, ClothespPris) + sumType(sumOther, OtherPris);
                        System.out.println("Total sum: $" + sumAll);
                    }
                    break;
                case 2:
                    SortByType();
                    break;
                case 3:
                        System.out.println();
                        messageFor();
                        int action2 = scanner.nextInt();
                        switch (action2) {
                            case 1:
                                if (foodPurch.size() == 0) {
                                    System.out.println();
                                    System.out.println("The purchase list is empty!");
                                    System.out.println();
                                } else {

                                }
                                System.out.println();
                                System.out.println("Food:");
                                sortMap(food, foodPurch, foodPris);
                                mapPrint(food);
                                System.out.println("Total sum:" + " $" + sumType(sumFood, foodPris));
                                break;
                            case 2:
                                if (ClothesPurch.size() == 0) {
                                    System.out.println();
                                    System.out.println("The purchase list is empty!");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("Clothes:");
                                    sortMap(clother, foodPurch, foodPris);
                                    mapPrint(clother);
                                    System.out.println("Total sum:" + " $" + sumType(sumClothes, ClothespPris));
                                }
                                break;
                            case 3:
                                if (EntertainmentPurch.size() == 0) {
                                    System.out.println();
                                    System.out.println("The purchase list is empty!");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("Entertainment:");
                                    sortMap(tree, foodPurch, foodPris);
                                    mapPrint(tree);
                                    System.out.println("Total sum:" + " $" + sumType(sumEntertainment, EntertainmentPris));
                                }
                                break;
                            case 4:
                                if  (OtherPurch.size() == 0) {
                                    System.out.println();
                                    System.out.println("The purchase list is empty!");
                                    System.out.println();
                                } else {
                                    System.out.println();
                                    System.out.println("Other:");
                                    sortMap(other, foodPurch, foodPris);
                                    mapPrint(other);
                                    System.out.println("Total sum:" + " $" + sumType(sumOther, OtherPris));
                                }
                                break;
                    }
                    break;
                case 4:
                    System.out.println();
                    stop = false;
                    break;
            }
        }
    }
    public void SortByType() {
        System.out.println();
        DecimalFormat dec = new DecimalFormat("#0.00");
        double sumAll = sumType(sumFood, foodPris) + sumType(sumEntertainment, EntertainmentPris)
                + sumType(sumClothes, ClothespPris) + sumType(sumOther, OtherPris);
        System.out.println("Types:");
        System.out.println("Food - $" + dec.format(sumType(sumFood, foodPris)));
        System.out.println("Entertainment - $" + sumType(sumEntertainment, EntertainmentPris));
        System.out.println("Clothes - $" + sumType(sumClothes, ClothespPris));
        System.out.println("Other - $" + dec.format(sumType(sumOther, OtherPris)));
        System.out.println("Total sum: $" + sumAll);
    }
    public void messageFor() {
        System.out.println("Choose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other");
    }
    public double sumType(double sum, ArrayList<String> pris) {
        for (int i = 0; i < pris.size(); i++) {
            sum += Double.parseDouble(pris.get(i).replace(",", "."));
        }
        return sum;
    }
    public void sortMap (TreeMap<Double, String> map, ArrayList<String> purch, ArrayList<String> pris) {
        for (int i = 0; i < purch.size(); i++) {
            map.put(Double.parseDouble(pris.get(i).replace(",", ".")), purch.get(i));
        }
    }
    public void mapPrint (TreeMap<Double, String> map) {
        DecimalFormat dec = new DecimalFormat("#0.00");
        map.forEach((key, value) -> System.out.println(value + "$" + dec.format(key)));
    }
}