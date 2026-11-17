# Kasir Mini Market — Java Swing

A simple desktop-based cashier application developed using Java Swing. This project demonstrates the implementation of Object-Oriented Programming (OOP) concepts, particularly graphical user interface development, component organization, layout management, and event-driven programming.

The application simulates a basic mini-market cashier system, allowing users to select products, manage quantities, process transactions, and calculate payment changes through a graphical interface.

## 1. About the Application

Kasir Mini Market is a desktop application designed to simulate a simple cashier transaction process. Users can interact with the application through a graphical interface without performing calculations manually.

The application provides several operations:

- Select a product from the available product list.
- Enter the quantity of the selected product.
- Add products to the transaction.
- Display transaction details in a receipt area.
- Calculate the total purchase amount.
- Enter the customer's payment.
- Calculate and display the change.
- Reset the transaction to start a new purchase.

## 2. How the Application Works

The transaction process follows a sequence of user interactions, starting from product selection and ending with payment calculation.

```text
Start Application
       |
       v
Select Product
       |
       v
Enter Quantity
       |
       v
Add Product
       |
       v
Display Transaction
       |
       v
Enter Payment
       |
       v
Calculate Total and Change
       |
       v
Reset Transaction
```

Each operation is handled through the application's graphical components and event-handling mechanisms.

## 3. Technologies and Components

The application is developed using Java with the Swing and AWT libraries.

| Technology / Component | Purpose |
|---|---|
| Java | Main programming language |
| Java Swing | Building the graphical interface |
| AWT | Layout management and event handling |
| JFrame | Main application window |
| JPanel | Organizing interface sections |
| JLabel | Displaying labels and information |
| JTextField | Receiving user input |
| JTextArea | Displaying transaction details |
| JComboBox | Providing product selection |
| JButton | Executing user actions |
| JScrollPane | Providing scrolling for the receipt area |

## 4. Building the User Interface

### 4.1 Main Window

The main application class extends `JFrame`, which serves as the top-level container. It provides the main window where all interface components are displayed.

```java
public class KasirSederhana extends JFrame implements ActionListener {

    public KasirSederhana() {
        setTitle("Kasir Mini Market");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
```

The constructor initializes the application window, defines its title and dimensions, and specifies the default closing operation.

### 4.2 Organizing Interface Sections

`JPanel` is used as an intermediate container to group related components. The application combines multiple layout managers to organize its interface.

```java
JPanel panelUtama = new JPanel(new BorderLayout());

JPanel panelProduk = new JPanel(new GridLayout(3, 2));
JPanel panelTombol = new JPanel(new FlowLayout());

panelUtama.add(panelProduk, BorderLayout.NORTH);
panelUtama.add(panelTombol, BorderLayout.SOUTH);

add(panelUtama);
```

The application uses three layout managers:

- `BorderLayout` divides the main container into different regions.
- `GridLayout` arranges components in rows and columns.
- `FlowLayout` places components sequentially.

Combining these layouts makes it possible to create an organized interface without manually specifying the position of every component.

### 4.3 Interface Components

Swing provides several components that allow users to interact with the application.

The following example demonstrates the declaration of commonly used components:

```java
JLabel labelProduk = new JLabel("Pilih Produk:");
JLabel labelJumlah = new JLabel("Jumlah:");

JComboBox<String> comboProduk = new JComboBox<>(
    new String[]{"Beras", "Gula", "Minyak Goreng"}
);

JTextField fieldJumlah = new JTextField(10);
JTextArea areaStruk = new JTextArea(10, 30);

JButton tombolTambah = new JButton("Tambah");
JButton tombolBayar = new JButton("Bayar");
JButton tombolReset = new JButton("Reset");
```

Each component has a specific responsibility:

- `JLabel` displays information and descriptions.
- `JComboBox` provides a list of products to select.
- `JTextField` receives numerical input from the user.
- `JTextArea` displays the transaction receipt.
- `JButton` triggers actions such as adding products, processing payments, and resetting transactions.

## 5. Handling User Interactions

The application uses the Delegation Event Model to process interactions between users and graphical components.

### 5.1 ActionListener

The main class implements the `ActionListener` interface. Components register the class as their listener through `addActionListener()`.

```java
tombolTambah.addActionListener(this);
tombolBayar.addActionListener(this);
tombolReset.addActionListener(this);
comboProduk.addActionListener(this);
```

The `actionPerformed()` method is overridden to handle the generated action events.

```java
@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == tombolTambah) {
        tambahProduk();
    } else if (e.getSource() == tombolBayar) {
        prosesPembayaran();
    } else if (e.getSource() == tombolReset) {
        resetTransaksi();
    }
}
```

The `getSource()` method identifies which component generated the event. This allows different actions to be handled within a single method.

### 5.2 Mouse Event Handling

In addition to action events, the application also demonstrates mouse event handling using `MouseAdapter`.

```java
areaStruk.addMouseListener(new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        setTitle("Receipt Selected");
    }
});
```

`MouseAdapter` provides a convenient way to handle specific mouse events without implementing every method in the `MouseListener` interface.

The `mouseClicked()` method is overridden to define the behavior when the receipt area is clicked.

## 6. Transaction Logic

The cashier application performs basic arithmetic operations to calculate the purchase subtotal, total amount, and customer's change.

The calculation process consists of three main operations.

**1. Calculate Subtotal**

Subtotal is calculated by multiplying the product price by the purchased quantity.

```text
Subtotal = Price x Quantity
```

**2. Calculate Total**

The total amount is obtained by adding all subtotals from the purchased products.

```text
Total = Subtotal 1 + Subtotal 2 + ... + Subtotal n
```

**3. Calculate Change**

The customer's change is calculated by subtracting the total purchase amount from the payment.

```text
Change = Payment - Total
```

### Example Calculation

If a customer purchases three items priced at Rp10,000 each:

```java
int harga = 10000;
int jumlah = 3;

int subtotal = harga * jumlah;
```

The calculation result is:

```text
Price    : Rp10,000
Quantity : 3
Subtotal : Rp30,000
Payment  : Rp50,000
Change   : Rp20,000
```

The application must also verify that the payment amount is sufficient before calculating the customer's change.

## 7. Source Code Organization

The project consists of two Java source files that separate application initialization from the main interface and transaction logic.

```text
KasirMiniMarket/
|
|-- KasirSederhana.java
|
|-- Main.java
|
|-- README.md
```

**KasirSederhana.java**

Contains the main application class, graphical interface, Swing components, layout configuration, event listeners, and transaction operations.

**Main.java**

Contains the main method responsible for creating and displaying the cashier application.

The application is launched using the following code:

```java
public class Main {
    public static void main(String[] args) throws Exception {
        KasirSederhana kasir = new KasirSederhana();
        kasir.setVisible(true);
    }
}
```

## 8. Running the Application

To run the project, make sure that the Java Development Kit (JDK) is installed on your computer.

**Step 1 — Clone the Repository**

```bash
git clone https://github.com/username/KasirMiniMarket.git
```

Replace `username` with your GitHub username and use the actual repository URL.

**Step 2 — Open the Project**

Open the project directory using a Java IDE such as NetBeans, IntelliJ IDEA, or Eclipse.

**Step 3 — Check the Source Files**

Make sure that `KasirSederhana.java` and `Main.java` are located in the same package or directory.

**Step 4 — Compile the Application**

Compile both Java source files using your IDE or the terminal.

```bash
javac KasirSederhana.java Main.java
```

**Step 5 — Run the Application**

Execute the main class:

```bash
java Main
```

The cashier application window will appear after successful execution.

## 9. Learning Objectives

This project provides practical experience in applying Java programming concepts through a desktop application.

The main learning objectives include:

1. Understanding the use of top-level containers through `JFrame`.
2. Organizing interface components using intermediate containers such as `JPanel`.
3. Implementing Swing atomic components for user interaction.
4. Applying multiple layout managers within one application.
5. Understanding the Delegation Event Model.
6. Implementing `ActionListener` and overriding `actionPerformed()`.
7. Handling mouse interactions using `MouseAdapter`.
8. Applying basic arithmetic and conditional logic to process cashier transactions.

## 10. Conclusion

Kasir Mini Market demonstrates the implementation of a graphical desktop application using Java Swing. By combining containers, interface components, layout managers, and event handling, the application provides a simple interactive environment for processing cashier transactions.

The project also illustrates how Java programming concepts can be applied to develop a practical application while improving understanding of graphical user interface development and event-driven programming.
