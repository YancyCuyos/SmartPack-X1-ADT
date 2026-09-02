import java.awt.*;
import javax.swing.*;

public class SmartPackApp extends JFrame {

    private SmartPack smartPack;

    // Labels
    private JLabel batteryLabel;
    private JLabel loadLabel;
    private JLabel lockLabel;
    private JLabel gpsLabel;
    private JLabel alarmLabel;

    // Item list
    private DefaultListModel<String> itemModel;
    private JList<String> itemList;

    // Status area
    private JTextArea statusArea;

    public SmartPackApp() {

        // Create ADT object
        smartPack = new SmartPack("Yancy", 25);

        // Window settings
        setTitle("SmartPack X1");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createInterface();
        updateDisplay();
    }

    private void createInterface() {

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel("SMARTPACK X1", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitle = new JLabel(
                "Futuristic Intelligent Backpack",
                SwingConstants.CENTER
        );

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.add(title);
        titlePanel.add(subtitle);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // =========================
        // STATUS PANEL
        // =========================

        JPanel statusPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        statusPanel.setBorder(
                BorderFactory.createTitledBorder("SmartPack Status")
        );

        batteryLabel = new JLabel();
        loadLabel = new JLabel();
        lockLabel = new JLabel();
        gpsLabel = new JLabel();
        alarmLabel = new JLabel();

        statusPanel.add(batteryLabel);
        statusPanel.add(loadLabel);
        statusPanel.add(lockLabel);
        statusPanel.add(gpsLabel);
        statusPanel.add(alarmLabel);

        mainPanel.add(statusPanel, BorderLayout.WEST);

        // =========================
        // ITEM LIST
        // =========================

        itemModel = new DefaultListModel<>();
        itemList = new JList<>(itemModel);

        JScrollPane scrollPane = new JScrollPane(itemList);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder("Items Inside Backpack")
        );

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel = new JPanel(
                new GridLayout(2, 4, 8, 8)
        );

        JButton lockButton = new JButton("Unlock");
        JButton solarButton = new JButton("Solar Charge");
        JButton gpsButton = new JButton("GPS");
        JButton alarmButton = new JButton("Anti-Theft");

        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton weightButton = new JButton("Check Weight");
        JButton statusButton = new JButton("Full Status");

        buttonPanel.add(lockButton);
        buttonPanel.add(solarButton);
        buttonPanel.add(gpsButton);
        buttonPanel.add(alarmButton);

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(weightButton);
        buttonPanel.add(statusButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // =========================
        // STATUS TEXT
        // =========================

        statusArea = new JTextArea(5, 20);
        statusArea.setEditable(false);
        statusArea.setLineWrap(true);

        mainPanel.add(
                new JScrollPane(statusArea),
                BorderLayout.EAST
        );

        // =========================
        // BUTTON ACTIONS
        // =========================

        // LOCK / UNLOCK
        lockButton.addActionListener(e -> {

            if (smartPack.isLocked()) {

                smartPack.unlock();
                lockButton.setText("Lock");

                showMessage("SmartPack unlocked!");

            } else {

                smartPack.lock();
                lockButton.setText("Unlock");

                showMessage("SmartPack locked!");
            }

            updateDisplay();
        });

        // SOLAR CHARGE
        solarButton.addActionListener(e -> {

            smartPack.solarCharge();

            showMessage(
                    "☀ Solar charging activated!\n"
                    + "Battery increased by 20%."
            );

            updateDisplay();
        });

        // GPS
        gpsButton.addActionListener(e -> {

            smartPack.toggleGPS();

            if (smartPack.isGPSOn()) {

                showMessage("📍 GPS tracking activated.");

            } else {

                showMessage("📍 GPS tracking disabled.");
            }

            updateDisplay();
        });

        // ANTI-THEFT
        alarmButton.addActionListener(e -> {

            if (smartPack.isAlarmOn()) {

                smartPack.turnOffAlarm();

                showMessage("Anti-theft alarm turned OFF.");

            } else {

                smartPack.triggerAlarm();

                showMessage(
                        "🚨 WARNING!\n"
                        + "Unauthorized access detected!"
                );
            }

            updateDisplay();
        });

        // ADD ITEM
        addButton.addActionListener(e -> addItem());

        // REMOVE ITEM
        removeButton.addActionListener(e -> removeItem());

        // CHECK WEIGHT
        weightButton.addActionListener(e -> {

            if (smartPack.isOverloaded()) {

                showMessage(
                        "⚠ WARNING!\n"
                        + "Backpack is almost full!"
                );

            } else {

                showMessage(
                        "✓ Weight is within safe capacity."
                );
            }

            updateDisplay();
        });

        // FULL STATUS
        statusButton.addActionListener(e -> showFullStatus());

        // Add main panel to window
        add(mainPanel);
    }

    // =========================
    // ADD ITEM
    // =========================

    private void addItem() {

        if (smartPack.isLocked()) {

            showMessage(
                    "🔒 Cannot add item.\n"
                    + "Please unlock the SmartPack first."
            );

            return;
        }

        String item = JOptionPane.showInputDialog(
                this,
                "Enter item name:"
        );

        if (item == null || item.trim().isEmpty()) {
            return;
        }

        String weightInput = JOptionPane.showInputDialog(
                this,
                "Enter item weight (kg):"
        );

        try {

            double weight = Double.parseDouble(weightInput);

            if (smartPack.getCurrentLoad() + weight
                    > smartPack.getCapacity()) {

                showMessage(
                        "❌ Not enough space!"
                );

                return;
            }

            smartPack.addItem(item, weight);

            showMessage(
                    "✓ " + item + " added to SmartPack."
            );

            updateDisplay();

        } catch (Exception e) {

            showMessage(
                    "Please enter a valid number."
            );
        }
    }

    // =========================
    // REMOVE ITEM
    // =========================

    private void removeItem() {

        int selected = itemList.getSelectedIndex();

        if (selected == -1) {

            showMessage(
                    "Select an item first."
            );

            return;
        }

        String item = itemModel.get(selected);

        String weightInput = JOptionPane.showInputDialog(
                this,
                "Enter weight of " + item + " (kg):"
        );

        try {

            double weight = Double.parseDouble(weightInput);

            smartPack.removeItem(item, weight);

            showMessage(
                    "✓ " + item + " removed."
            );

            updateDisplay();

        } catch (Exception e) {

            showMessage(
                    "Please enter a valid number."
            );
        }
    }

    // =========================
    // UPDATE DISPLAY
    // =========================

    private void updateDisplay() {

        batteryLabel.setText(
                "🔋 Battery: "
                + smartPack.getBattery() + "%"
        );

        loadLabel.setText(
                "🎒 Load: "
                + smartPack.getCurrentLoad()
                + " / "
                + smartPack.getCapacity()
                + " kg"
        );

        lockLabel.setText(
                "🔐 Lock: "
                + (smartPack.isLocked()
                ? "LOCKED"
                : "UNLOCKED")
        );

        gpsLabel.setText(
                "📍 GPS: "
                + (smartPack.isGPSOn()
                ? "ON"
                : "OFF")
        );

        alarmLabel.setText(
                "🚨 Alarm: "
                + (smartPack.isAlarmOn()
                ? "ACTIVE"
                : "OFF")
        );

        itemModel.clear();

        for (String item : smartPack.getItems()) {
            itemModel.addElement(item);
        }
    }

    // =========================
    // FULL STATUS
    // =========================

    private void showFullStatus() {

        statusArea.setText("");

        statusArea.append(
                "===== SMARTPACK X1 =====\n\n"
        );

        statusArea.append(
                "Owner: "
                + smartPack.getOwner()
                + "\n"
        );

        statusArea.append(
                "Battery: "
                + smartPack.getBattery()
                + "%\n"
        );

        statusArea.append(
                "Load: "
                + smartPack.getCurrentLoad()
                + " / "
                + smartPack.getCapacity()
                + " kg\n"
        );

        statusArea.append(
                "Locked: "
                + smartPack.isLocked()
                + "\n"
        );

        statusArea.append(
                "GPS: "
                + smartPack.isGPSOn()
                + "\n"
        );

        statusArea.append(
                "Alarm: "
                + smartPack.isAlarmOn()
                + "\n"
        );
    }

    // =========================
    // MESSAGE
    // =========================

    private void showMessage(String message) {

        statusArea.setText(message);
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            SmartPackApp app = new SmartPackApp();

            app.setVisible(true);
        });
    }
}