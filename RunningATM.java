import java.util.*;

//This Customer Class deals with all the details of the Customer and their accessibility.
class Customer {
    private ArrayList<Integer> TransactionMoney = new ArrayList<Integer>(); 
    private ArrayList<Date> TransactionDate = new ArrayList<Date>();
    //The above two arrayLists are used to keep track of the transactions(Amount and Date).
    private String Name;
    private String MobNum;
    private String AadharNum;
    private int loginStatus = 0;
    private Date blockDate;
    private Date UnblockTime;
    private int failedLogins = 0;
    //The above variables are the details of the Customer and his account. All of them are private as they should be secured from viewing by everyone.

    protected void addTransaction(int money, Date d) {
        TransactionMoney.add(money);
        TransactionDate.add(d);
    }
    //Method for keeping track of transactions of Customer
    protected void displayTransactionHistory() {
        if (TransactionMoney.size() == 0) {
            System.out.println("YOU HAVEN'T MADE ANY TRANSACTIONS YET\n");
        } else if (TransactionMoney.size() == 1) {
            System.out.println(
                    "\n  You Deposited : " + TransactionMoney.get(0) + " RUPEES ON " + TransactionDate.get(0) + "\n");
        } else if (TransactionMoney.size() == 2) {
            System.out
                    .println("\n  You Deposited : " + TransactionMoney.get(0) + " RUPEES ON " + TransactionDate.get(0));
            if (TransactionMoney.get(1) > 0) {
                System.out.println("\n  You Deposited : " + TransactionMoney.get(1) + " RUPEES ON "
                        + TransactionDate.get(1) + "\n");
            } else {
                System.out.println("\n  You Have Withdrawn : " + (-TransactionMoney.get(1)) + " RUPEES ON "
                        + TransactionDate.get(1) + "\n");
            }
        } else {
            for (int i = TransactionMoney.size() - 3; i <= TransactionMoney.size() - 1; i++) {
                if (TransactionMoney.get(i) > 0) {
                    System.out.println(
                            "\n  You Deposited : " + TransactionMoney.get(i) + " RUPEES ON " + TransactionDate.get(i));
                } else {
                    System.out.println("\n  You Have Withdrawn : " + (-TransactionMoney.get(i)) + " RUPEES ON "
                            + TransactionDate.get(i));
                }
            }
            System.out.println("\n");
        }
    }
    //This method will display the last 3 transcations of the Customer.

    protected void setName(String Name) {
        this.Name = Name;
    }

    protected void setBlockDate() {
        this.blockDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(blockDate);
        c.add(Calendar.DATE, 1);
        UnblockTime = c.getTime();
    }

    protected void reset() {
        Date dt = new Date();
        if (dt.after(UnblockTime)) {
            failedLogins = 0;
        }
    }
    //When the Customer tries to login to his/her account, if his card is blocked, this method will check if the 24 hours duration is over or not.
    //If the duration is over, then it will reset the number of failed logins to 0 and the customer will be able to login to his account.
    protected Date getUnblockTime() {
        return UnblockTime;
    }
    //If the Customer enters wrong password thrice, then this method is useful. It is used to get the unblock time.

    protected String getName() {
        return Name;
    }

    protected void setMobNum(String Num) {
        this.MobNum = Num;
    }

    protected String getMobNum() {
        return MobNum;
    }

    protected void setAadharNum(String Aadhar) {
        this.AadharNum = Aadhar;
    }

    protected String getAadharNum() {
        return AadharNum;
    }

    private int CardNum;
    private int Pin;
    private int AmtInAcc = 0;

    protected void setCardNum() {
        CardNum = 0;
        while (CardNum < 10000) {
            double x = Math.random();
            int y = (int) (100000 * x);
            if (y > 10000) {
                CardNum = y;
            }
        }
    }
    //This method randomly generates a 5-digit Account Number. If this account number is already given to a customer, then this method is called again.
    //The checking process is done in ATM class, where we give account number to the new customer.

    protected int getCardNum() {
        return CardNum;
    }

    protected void setPin(int Pin) {
        this.Pin = Pin;
    }

    protected int getPin() {
        return Pin;
    }

    protected void Deposit(int money) {
        AmtInAcc = AmtInAcc + money;
    }

    protected int ShowBalance() {
        return AmtInAcc;
    }

    protected void WrongPin() {
        failedLogins++;
    }
    //Whenever the customer enters wrong pin, this method is called. It will increment the number of failed logins by 1.

    protected void CorrectPin() {
        failedLogins = 0;
    }
    //When the user enters the correct pin within the limited 3 chances, then this mwthod will be called.
    //It will reset the failed logins to 0.

    protected int getFailedLogins() {
        return failedLogins;
    }

    protected void Login() {
        loginStatus = 1;
    }

    protected void Logout() {
        loginStatus = 0;
    }

    protected void Withdraw(int money) {
        Scanner input = new Scanner(System.in);
        if (money > AmtInAcc) {
            System.out.println("INSUFFICIENT BALANCE!");
            System.out.println("YOUR BALANCE IS ONLY " + AmtInAcc);
        } else {
            Date data = new Date();
            AmtInAcc = AmtInAcc - money;
            System.out.println("MONEY SUCCESSFULLY WITHDRAWN!");
            System.out.println("YOU WITHDREW " + money + " RUPEES");
            System.out.println("--------------------------------------------------------");
            System.out.println("                     YOUR RECEIPT");
            System.out.println("    Your Account Number     :    " + this.getCardNum());
            System.out.println("    Name                    :    " + this.getName());
            System.out.println("    Contact Number          :    " + this.getMobNum());
            System.out.println("    Amount Withdrawn        :    " + money);
            System.out.println("    Date and Time           :    " + data);
            System.out.println("--------------------------------------------------------");
            System.out.println("DO YOU WANT TO CHECK THE CURRENT BLANACE?");
            System.out.println("CLICK '1' IF YOUR ANSWER IS YES, CLICK '0' IF YOUR ANSWER IS NO");
            int x = input.nextInt();
            while (true) {
                if (x == 1) {
                    System.out.println("YOUR CUREENT BALANCE IS " + AmtInAcc + " RUPEES");
                    System.out.println("THANK YOU!");
                    break;
                } else if (x == 0) {
                    System.out.println("THANK YOU!");
                    break;
                } else {
                    System.out.println("PLEASE ENTER A VALID OPTION!");
                    x = input.nextInt();
                }
            }
        }
    }
    //This method is called when the customer wants to withdraw money.
}

interface Display {
    void screen();
}
// An interface is created and this is implemented by the ATM_Screen class. This is an example of Inheritance concept.

class ATM_Screen implements Display {
    public int select;

    public void screen() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n           CUSTOMER PORTAL \n");
        System.out.println("      PLEASE SELECT YOUR OPTION: \n");
        System.out.println("        1. CREATE NEW ACCOUNT");
        System.out.println("        2. SIGN IN TO YOUR ACCOUNT");
        System.out.println("        3. RETURN TO MAIN PORTAL");
        select = input.nextInt();
    }
}
//This class take care of displaying the Customer Portal.
//An object of this class will be created in the "run" method of ATM class.
//According to the input given by customer, that particular process (shown in the above menu) will be executed.
//Code for each process is in ATM class.



//The AdminPortal class will take care of everything related to the Admin i.e., adding money to the ATM, changing the Pin (Pin required for Admin Login).
//And things like Amount in ATM.
//ALl the methods in this class and Customer class are declared as protected because all of them deal with confidential information.
class AdminPortal {

    private int AmountInATM = 0;
    private int password = 0;

    protected void setATM(int AmountInATM) {
        this.AmountInATM = AmountInATM;
    }

    protected void setATM() {
        this.AmountInATM = 500000;
    }

    protected int getAmountInATM() {
        return AmountInATM;
    }

    protected void setPassword(int p) {
        password = p;
    }

    protected int getPassword() {
        return password;
    }

    protected void AdminScreen() {
        Scanner input = new Scanner(System.in);
        if (this.getPassword() == 0) {
            System.out.println("PLEASE ENTER A PIN: ");
            System.out.println("NOTE: THIS PIN WILL BE THE KEY TO FILL THE MONEY IN ATM");
            int p = input.nextInt();
            while (true) {
                if (p >= 1000 && p <= 9999) {
                    this.setPassword(p);
                    System.out.println("THE PASSWORD IS SET! THE ATM IS READY TO USE NOW!!!");
                    break;
                } else {
                    System.out.println("THE PIN SHOULD CONTAIN ONLY 4 DIGITS AND SHOULDN'T START WITH ZERO!");
                    System.out.println("PLEASE TRY AGAIN...");
                    p = input.nextInt();
                }
            }
        }
        while (true) {
            System.out.println("        ADMIN LOGIN\n");
            System.out.println("    PLEASE ENTER THE 4-DIGIT PIN: ");
            System.out.println("YOU CAN CLICK '0' TO RETURN TO MAIN PORTAL");
            int a = input.nextInt();
            while (true) {
                if (a == 0) {
                    break;
                } else if (a == this.getPassword()) {
                    while (true) {
                        System.out.println("\n              WELCOME TO THE ADMIN PORTAL\n");
                        System.out.println("    1. ADD MONEY TO THE ATM:(DEFAULT ADDITION OF AMOUNT) ");
                        System.out.println("    2. ADD MONEY TO THE ATM:(MANUALLY ENTER THE AMOUNT TO BE ADDED)");
                        System.out.println("    3. VIEW REMAINING AMOUNT IN ATM");
                        System.out.println("    4. CHANGE LOGIN PIN");
                        System.out.println("    5. LOGOUT");
                        int choose = input.nextInt();
                        if (choose == 1) {
                            this.setATM();
                            System.out.println("MONEY SUCCESSFULLY ADDED!");
                        } else if (choose == 2) {
                            System.out.println("ENTER THE AMOUNT YOU WANT TO ADD INTO THE ATM: ");
                            System.out.println("YOU CAN CHOOSE '0' IF YOU WANT TO RETURN TO PREVIOUS MENU");
                            int add = input.nextInt();
                            while (true) {
                                if (add > 0) {
                                    this.setATM(add + this.getAmountInATM());
                                    System.out.println("AMOUNT SUCCESSFULLY ADDED");
                                    break;
                                } else if (add == 0) {
                                    System.out.println("RETURNING TO PREVIOUS MENU...");
                                    break;
                                } else {
                                    System.out.println("INVALID AMOUNT TO ADD");
                                    System.out.println("ENTER THE AMOUNT AGAIN: ");
                                    add = input.nextInt();

                                }
                            }
                        } else if (choose == 3) {
                            System.out.println("REMAINING AMOUNT IN ATM: " + this.getAmountInATM());
                        } else if (choose == 4) {
                            System.out.println("ENTER THE NEW PIN: ");
                            System.out.println("NOTE: THIS PIN WILL BE THE KEY TO FILL THE MONEY IN ATM");
                            System.out.println("YOU CAN RETURN TO ADMIN PORTAL IF YOU CLICK '0' ");
                            int p = input.nextInt();
                            while (true) {
                                if (p >= 1000 && p <= 9999) {
                                    this.setPassword(p);
                                    break;
                                } else if (p == 0) {
                                    break;
                                } else {
                                    System.out.println(
                                            "THE PIN SHOULD CONTAIN ONLY 4 DIGITS AND SHOULDN'T START WITH ZERO!");
                                    System.out.println("PLEASE TRY AGAIN...");
                                    p = input.nextInt();
                                }
                            }
                            System.out.println("LOGIN PIN SUCCESSFULLY UPDATED!");
                        } else if (choose == 5) {
                            System.out.println("LOGGED OUT SUCCESSFULLY!");
                            break;
                        } else {
                            System.out.println("PLEASE ENTER A VALID OPTION!");
                        }
                    }
                    break;
                } else if (this.getPassword() != a) {
                    System.out.println("WRONG PIN ENTERED! PLEASE TRY AGAIN...");
                    System.out.println("ENTER THE PIN: ");
                    a = input.nextInt();
                }
            }
            break;
        }
    }
}

//This ATM class will perform almost whole task, like calling all the requited functions, using objects of other classes.
//This class has an arraylist of Customers. each element of the arraylist represents a Customer.
class ATM {

    AdminPortal a = new AdminPortal();
    ArrayList<Customer> arrayList = new ArrayList<Customer>();

    protected void Oper(int select) {
        Scanner input = new Scanner(System.in);
        Customer c = new Customer();
        if (select == 1) {
            System.out.println("PLEASE ENTER YOUR NAME: ");
            c.setName(input.nextLine());
            System.out.println("PLEASE ENTER YOUR AADHAR NUMBER: ");
            c.setAadharNum(input.nextLine());
            System.out.println("PLEASE ENTER YOUR MOBILE NUMBER: ");
            c.setMobNum(input.nextLine());
            if (arrayList.size() == 0) {
                c.setCardNum();
            } else {
                c.setCardNum();
                int num = c.getCardNum();
                int check = 0;
                while (true) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (num == arrayList.get(i).getCardNum()) {
                            check = 1;
                            c.setCardNum();
                            num = c.getCardNum();
                            break;
                        }
                    }
                    if (check == 0) {
                        break;
                    }
                }
            }
            System.out.println("YOUR ACCOUNT NUMBER IS " + c.getCardNum());
            System.out.println("ENTER YOUR PIN(ENTER WHAT PIN YOU WANT TO SET): ");
            int Pin = input.nextInt();
            //The pin should only be a 5-digit number.
            while (true) {
                if (Pin >= 10000 && Pin <= 99999) {
                    c.setPin(Pin);
                    System.out.println("THANK YOU FOR ENTERING THE PIN!");
                    break;
                } else {
                    System.out.println(
                            "SORRY! THE PIN SHOULD CONTAIN 5-DIGITS AND IT SHOULDN'T START WITH 0, TRY AGAIN...");
                    Pin = input.nextInt();
                }
            }
            System.out.println("DO YOU WANT TO DEPOSIT AMOUNT IN YOUR ACCOUNT?");
            System.out.println("CLICK '1' IF YOU WANT TO DEPOSIT, OTHERWISE CLICK '0'...");
            int option = input.nextInt();
            if (option == 1) {
                System.out.println("ENTER THE AMOUNT YOU WANT TO DEPOSIT: ");
                int amount = input.nextInt();
                while (true) {
                    if (amount > 0) {
                        c.Deposit(amount);
                        System.out.println("YOUR AMOUNT IS SUCCESSFULLY DEPOSITED!");
                        Date d = new Date();
                        c.addTransaction(amount, d);
                        System.out.println("--------------------------------------------------------");
                        System.out.println("                     YOUR RECEIPT");
                        System.out.println("    Your Account Number     :    " + c.getCardNum());
                        System.out.println("    Name                    :    " + c.getName());
                        System.out.println("    Contact Number          :    " + c.getMobNum());
                        System.out.println("    Amount Deposited        :    " + amount);
                        System.out.println("    Date and Time           :    " + d);
                        System.out.println("--------------------------------------------------------");
                        break;
                    } else {
                        System.out.println("INVALID AMOUNT TO DEPOSIT");
                        amount = input.nextInt();
                    }
                }

            }
            if (option == 0) {
                System.out.println("YOUR ACCOUNT IS CREATED SUCCESSFULLY!!!");
            }
            arrayList.add(c);
            //After the account is created, we add it into the arrayList.
            // System.out.println(arrayList.size());
        } else if (select == 2 && arrayList.size() == 0) {
            System.out.println("PLEASE CREATE AN ACCOUNT!");
            //If there are no elements in the arrayList, that means, the customer who is trying to sign in, will be the first customer who has no account.
            //So, it will tell the customer to create an account before trying to sign in.
        } else if (select == 2 && arrayList.size() != 0) {
            int AccountNum;
            int Pin;
            int captcha;
            int var1 = 0;
            int var2 = 0;
            int var3 = 0;
            //The above 3 variables will become 1 after the particular step.
            //Like, if the account number is right, then var1 will be 1.
            //If the pin is also right, then var2 will also become 1.
            //If he enters anything wrong, then these 3 variables will become 0 which means, the customer should start from step-1 again.(Step-1 is entering the Account Number)
            int success = 0;

            int i = 0;
            // System.out.println(AccountNum);
            // System.out.println(arrayList.size());

            while (true) {
                System.out.println("ENTER YOUR ACCOUNT NUMBER: ");
                System.out.println("YOU CAN ENTER '0' IF YOU WANT TO RETURN TO CUSTOMER PORTAL");
                AccountNum = input.nextInt();
                if (AccountNum == 0) {
                    System.out.println("RETURNING TO CUSTOMER PORTAL...");
                    break;
                }
                for (i = 0; i < arrayList.size(); i++) {

                    if (AccountNum == arrayList.get(i).getCardNum()) {
                        if (arrayList.get(i).getFailedLogins() == 3) {
                            arrayList.get(i).reset();
                        }
                        var1 = 1;
                        break;
                    }
                }
                //In this for loop, we will traverse through the arraylist and check whether the entered account number exists or not.
                //If that account number doesn't exist, the ATM will tell him that it is an invalid account number.
                if (var1 == 0) {
                    System.out.println("INVALID ACCOUNT NUMBER. TRY AGAIN...");

                }

                else if (arrayList.get(i).getFailedLogins() != 3 && var1 == 1) {

                    System.out.println("PLEASE ENTER YOUR PIN (5-digit) NUMBER: ");
                    Pin = input.nextInt();
                    if (arrayList.get(i).getPin() == Pin) {
                        var2 = 1;
                    } else {
                        System.out.println("WRONG PIN!");
                        arrayList.get(i).WrongPin();
                        if (arrayList.get(i).getFailedLogins() == 3) {
                            arrayList.get(i).setBlockDate();
                            System.out.println("YOUR CARD IS BLOCKED UNTIL " + arrayList.get(i).getUnblockTime());
                        }
                        var1 = 0;
                    }
                } else if (arrayList.get(i).getFailedLogins() == 3 && var1 == 1) {
                    System.out.println("YOUR CARD IS BLOCKED UNTIL " + arrayList.get(i).getUnblockTime());
                    //If the customer's account is blocked and he is trying to login, then this message will be displayed.
                }

                if (var2 == 1) {
                    captcha = 0;
                    while (captcha < 100000) {
                        double a = Math.random();
                        int b = (int) (1000000 * a);
                        if (b > 10000) {
                            captcha = b;
                        }
                    }
                    System.out.println("CAPTCHA: " + captcha);
                    System.out.println("PLEASE ENTER THE CAPTCHA: ");
                    int cap = input.nextInt();
                    if (cap == captcha) {
                        var3 = 1;
                    } else {
                        System.out.println("WRONG CAPTCHA ENTERED! TRY AGAIN...");
                        var1 = 0;
                        var2 = 0;
                    }
                }

                if (var3 == 1) {
                    while (true) {

                        System.out.println("SUCCESSFULLY LOGGED IN!");
                        arrayList.get(i).CorrectPin();
                        arrayList.get(i).Login();
                        //The Customer has 7 options to select.
                        //The below while loop will keep on running until the customer wants to logout.(Click 7)
                        while (true) {
                            System.out.println("\n        CUSTOMER'S PERSONAL PORTAL \n");
                            System.out.println("    PLEASE SELECT THE REQUIRED OPTION\n");
                            System.out.println("        1. DEPOSIT MONEY");
                            System.out.println("        2. WITHDRAW MONEY");
                            System.out.println("        3. VIEW BALANCE");
                            System.out.println("        4. CHANGE MOBILE NUMBER");
                            System.out.println("        5. SHOW LAST 3 TRANSACTIONS/ACTIVITIES");
                            System.out.println("        6. CHANGE YOUR PIN");
                            System.out.println("        7. LOGOUT");
                            int log = input.nextInt();
                            if (log == 1) {
                                System.out.println("ENTER THE AMOUNT YOU WANT TO DEPOSIT: ");
                                System.out.println("YOU CAN CLICK '0' IF YOU WANT TO RETRUN TO YOUR PERSONAL PORTAL!");
                                int money = input.nextInt();
                                while (true) {
                                    if (money > 0) {
                                        arrayList.get(i).Deposit(money);
                                        Date d = new Date();
                                        arrayList.get(i).addTransaction(money, d);
                                        System.out.println("MONEY SUCCESSFULLY DEPOSITED");
                                        System.out.println("--------------------------------------------------------");
                                        System.out.println("                     YOUR RECEIPT");
                                        System.out.println(
                                                "    Your Account Number     :    " + arrayList.get(i).getCardNum());
                                        System.out.println(
                                                "    Name                    :    " + arrayList.get(i).getName());
                                        System.out.println(
                                                "    Contact Number          :    " + arrayList.get(i).getMobNum());
                                        System.out.println("    Amount Deposited        :    " + money);
                                        System.out.println("    Date and Time           :    " + d);
                                        System.out.println("--------------------------------------------------------");
                                        break;

                                    } else if (money == 0) {
                                        System.out.println("RETURNING TO PERSONAL PORTAL...");
                                        break;
                                    } else {
                                        System.out.println("ENTER VALID AMOUNT");
                                        money = input.nextInt();
                                    }
                                }
                            } else if (log == 2) {
                                System.out.println("THE CASH WILL BE IN 100's, 500's, 2000's ONLY.");
                                System.out.println("YOU CAN CLICK '0' IF YOU WANT TO RETURN TO YOUR PERSONAL PORTAL");
                                System.out.println("ENTER THE MONEY YOU WANT TO WITHDRAW: ");
                                int money = input.nextInt();

                                if (a.getAmountInATM() >= money) {
                                    while (true) {
                                        if (money > 0 && money % 100 == 0) {
                                            arrayList.get(i).Withdraw(money);
                                            a.setATM(a.getAmountInATM() - money);
                                            Date d = new Date();
                                            arrayList.get(i).addTransaction(-money, d);
                                            break;
                                        } else if(money == 0){
                                            break;
                                        }
                                         else {
                                            System.out.println(
                                                    "ENTER VALID AMOUNT. THE CASH WILL BE IN 100's, 500's, 200's ONLY.");
                                                    money = input.nextInt();
                                        }
                                    }
                                } else {
                                    System.out.println("INSUFFICIENT AMOUNT IN THE ATM! SORRY FOR THE INCONVINIENCE");
                                }
                            } else if (log == 3) {
                                System.out.println("YOUR CURRENT BALANCE IS: " + arrayList.get(i).ShowBalance());
                            } else if (log == 4) {
                                System.out.println("PLEASE ENTER YOUR NEW MOBILE NUMBER: ");
                                input.nextLine();
                                String num = input.nextLine();
                                arrayList.get(i).setMobNum(num);
                                System.out.println("MOBILE NUMBER SUCCESSFULLY UPDATED!");
                            } else if (log == 5) {
                                arrayList.get(i).displayTransactionHistory();
                            } else if (log == 7) {
                                success = 1;
                                arrayList.get(i).Logout();
                                var1 = var2 = var3 = 0;
                                System.out.println("SUCCESSFULLY LOGGED OUT!");
                                System.out.println("THANK YOU!");
                                break;
                            } else if (log == 6) {
                                System.out.println("ENTER YOUR NEW PIN: ");
                                System.out.println("YOU CAN ENTER '0' IF YOU WANT TO RETURN TO YOUR PERSONAL PORTAL");
                                int pin = input.nextInt();
                                while (true) {
                                    if (pin == 0) {
                                        System.out.println("RETURNING TO PERSONAL PORTAL...");
                                        break;
                                    }
                                    if (10000 < pin && pin < 99999) {
                                        arrayList.get(i).setPin(pin);
                                        System.out.println("PIN UPDATED SUCCESSFULLY");
                                        break;
                                    } else {
                                        System.out.println(
                                                "PLEASE ENTER A VAID PIN. THE PIN SHOULD CONTAIN 5-DIGITS AND SHOULDN'T START WITH ZERO");
                                        pin = input.nextInt();
                                    }
                                }
                            } else {
                                System.out.println("PLEASE SELECT A VALID OPTION");
                            }
                        }
                        break;
                    }
                }

                if (success == 1) {
                    break;
                }
            }
        }
    }

    protected void run() {
        ATM_Screen s = new ATM_Screen();
        while (true) {

            s.screen();
            if (s.select == 3) {
                System.out.println("RETURNING TO MAIN PORTAL...");
                //You will retrun to the Main Portal from the Customer Portal if you select 3.
                break;
            } else if (s.select == 1 || s.select == 2) {
                this.Oper(s.select);
            } else {
                System.out.println("PLEASE SELECT A VALID OPTION!");
            }
        }
    }

    //This method deals with the Main Portal display. According to the option the customer selects, required functions will be called.
    protected void RunningProgram() {

        Scanner input = new Scanner(System.in);
        System.out.println("           WELCOME TO ATM\n");
        System.out.println("            MAIN PORTAL\n");
        System.out.println("    PLEASE CHOOSE APPROPRIATE OPTION: ");
        System.out.println("    1. ADMIN");
        System.out.println("    2. CUSTOMER");
        System.out.println("    3. TERMINATE PROGRAM");
        int choice = input.nextInt();
        int firstTime = 0;
        while (true) {
            if (choice == 1) {
                a.AdminScreen();
                System.out.println("           WELCOME TO ATM\n");
                System.out.println("            MAIN PORTAL\n");
                System.out.println("    PLEASE CHOOSE APPROPRIATE OPTION: ");
                System.out.println("    1. ADMIN");
                System.out.println("    2. CUSTOMER");
                System.out.println("    3. TERMINATE PROGRAM");
                choice = input.nextInt();
                firstTime = 1;
            } else if (choice == 2 && firstTime == 1) {
                this.run();
                System.out.println("           WELCOME TO ATM\n");
                System.out.println("            MAIN PORTAL\n");
                System.out.println("    PLEASE CHOOSE APPROPRIATE OPTION: ");
                System.out.println("    1. ADMIN");
                System.out.println("    2. CUSTOMER");
                System.out.println("    3. TERMINATE PROGRAM");
                choice = input.nextInt();
            } else if (choice == 3) {
                System.out.println("PROGRAM TERMINATED");
                break;
            } else if (firstTime == 0 && choice == 2) {
                System.out.println(
                        "YOU NEED TO INITIALISE THE ATM FIRST BY CREATING AN ADMIN ACCOUNT. PLEASE CLICK '1' FOR DOING THIS!");
                choice = input.nextInt();
            } else {
                System.out.println("PLEASE ENTER A VALID OPTION!");
                choice = input.nextInt();
            }
        }
    }
}

//The ATM does all the main work, we are creating an object of the ATM class and using it here in the main function.
//And, it will be better if we don't write many logics in the main function.
public class RunningATM {
    public static void main(String args[]) {
        ATM t = new ATM();
        t.RunningProgram();
    }
}