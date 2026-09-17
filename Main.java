import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

public class Main extends JFrame {

    // =========================================================
    // LOGIN
    // =========================================================

    private static final String LOGIN_USERNAME = "admin";
    private static final String DEFAULT_LOGIN_PASSWORD = "asish08";

    private String loginPassword = DEFAULT_LOGIN_PASSWORD;

    // =========================================================
    // COLORS
    // =========================================================

    // Sidebar
    private static final Color SIDEBAR_COLOR =
            new Color(20, 32, 52);

    private static final Color SIDEBAR_BUTTON =
            new Color(35, 53, 78);

    private static final Color SIDEBAR_HOVER =
            new Color(55, 82, 120);

    private static final Color SIDEBAR_ACTIVE =
            new Color(37, 99, 180);

    // Main
    private static final Color MAIN_BACKGROUND =
            new Color(244, 247, 251);

    private static final Color CARD_BACKGROUND =
            Color.WHITE;

    // Primary blue
    private static final Color PRIMARY_COLOR =
            new Color(37, 99, 235);

    private static final Color PRIMARY_HOVER =
            new Color(29, 78, 216);

    // Danger red
    private static final Color DANGER_COLOR =
            new Color(220, 53, 69);

    private static final Color DANGER_HOVER =
            new Color(185, 42, 55);

    // Success green
    private static final Color SUCCESS_COLOR =
            new Color(25, 135, 84);

    private static final Color SUCCESS_HOVER =
            new Color(20, 108, 67);

    // Secondary grey
    private static final Color SECONDARY_COLOR =
            new Color(232, 238, 246);

    private static final Color SECONDARY_HOVER =
            new Color(211, 220, 232);

    // Text
    private static final Color TEXT_COLOR =
            new Color(24, 39, 61);

    private static final Color SECONDARY_TEXT =
            new Color(92, 108, 128);

    // Borders
    private static final Color BORDER_COLOR =
            new Color(210, 218, 228);

    private static final Color INPUT_BORDER =
            new Color(165, 177, 193);

    private static final Color INPUT_FOCUS =
            new Color(37, 99, 235);

    // Table
    private static final Color TABLE_HEADER =
            new Color(37, 62, 91);

    private static final Color TABLE_ALTERNATE =
            new Color(248, 250, 253);

    private static final Color TABLE_SELECTED =
            new Color(215, 229, 247);

    // =========================================================
    // COMPONENTS
    // =========================================================

    private JPanel contentPanel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderBox;
    private JTextField courseField;
    private JTextField phoneField;
    private JTextField cgpaField;

    private JTextField searchField;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private JLabel totalStudentsLabel;
    private JLabel cseStudentsLabel;
    private JLabel eceStudentsLabel;
    private JLabel averageCgpaLabel;

    private String currentUsername = "";

    private final StudentDAO dao = new StudentDAO();

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {

            Main app = new Main();

            app.showLogin();

            app.setVisible(true);
        });
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Main() {

        setTitle("Student Management System");

        setSize(1280, 780);

        setMinimumSize(
                new Dimension(1100, 700)
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        getContentPane().setBackground(
                MAIN_BACKGROUND
        );
    }

    // =========================================================
    // LOGIN PAGE
    // =========================================================

    private void showLogin() {

        getContentPane().removeAll();

        JPanel background =
                new JPanel(new GridBagLayout());

        background.setBackground(
                MAIN_BACKGROUND
        );

        JPanel loginCard =
                new JPanel();

        loginCard.setPreferredSize(
                new Dimension(450, 480)
        );

        loginCard.setBackground(
                CARD_BACKGROUND
        );

        loginCard.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_COLOR,
                                1
                        ),
                        new EmptyBorder(
                                35,
                                42,
                                35,
                                42
                        )
                )
        );

        loginCard.setLayout(
                new BoxLayout(
                        loginCard,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel logo =
                new JLabel("SMS");

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        46
                )
        );

        logo.setForeground(
                PRIMARY_COLOR
        );

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel title =
                new JLabel(
                        "Student Management System"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        title.setForeground(
                TEXT_COLOR
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitle =
                new JLabel("Admin Login");

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginCard.add(logo);

        loginCard.add(
                Box.createVerticalStrut(8)
        );

        loginCard.add(title);

        loginCard.add(
                Box.createVerticalStrut(5)
        );

        loginCard.add(subtitle);

        loginCard.add(
                Box.createVerticalStrut(30)
        );

        JLabel usernameLabel =
                createLabel("Username");

        JTextField usernameField =
                createTextField();

        JLabel passwordLabel =
                createLabel("Password");

        JPasswordField passwordField =
                new JPasswordField();

        styleTextField(passwordField);

        loginCard.add(usernameLabel);

        loginCard.add(
                Box.createVerticalStrut(6)
        );

        loginCard.add(usernameField);

        loginCard.add(
                Box.createVerticalStrut(18)
        );

        loginCard.add(passwordLabel);

        loginCard.add(
                Box.createVerticalStrut(6)
        );

        loginCard.add(passwordField);

        loginCard.add(
                Box.createVerticalStrut(25)
        );

        JButton loginButton =
                createPrimaryButton("LOGIN");

        loginButton.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        loginCard.add(loginButton);

        JLabel hint =
                new JLabel(
                        "Demo Login: admin / asish08"
                );

        hint.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        hint.setForeground(
                SECONDARY_TEXT
        );

        hint.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginCard.add(
                Box.createVerticalStrut(18)
        );

        loginCard.add(hint);

        loginButton.addActionListener(e -> {

            String username =
                    usernameField
                            .getText()
                            .trim();

            String password =
                    new String(
                            passwordField
                                    .getPassword()
                    );

            if (username.equals(LOGIN_USERNAME)
                    && password.equals(loginPassword)) {

                currentUsername = username;

                showMainApplication();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        passwordField.addActionListener(
                e -> loginButton.doClick()
        );

        background.add(loginCard);

        getContentPane().add(background);

        revalidate();
        repaint();
    }

    // =========================================================
    // MAIN APPLICATION
    // =========================================================

    private void showMainApplication() {

        getContentPane().removeAll();

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                MAIN_BACKGROUND
        );

        mainPanel.add(
                createSidebar(),
                BorderLayout.WEST
        );

        contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                MAIN_BACKGROUND
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        getContentPane().add(mainPanel);

        showDashboard();

        revalidate();
        repaint();
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setPreferredSize(
                new Dimension(260, 0)
        );

        sidebar.setBackground(
                SIDEBAR_COLOR
        );

        sidebar.setBorder(
                new EmptyBorder(
                        30,
                        22,
                        22,
                        22
                )
        );

        // -------------------------
        // TOP
        // -------------------------

        JPanel top =
                new JPanel();

        top.setBackground(
                SIDEBAR_COLOR
        );

        top.setLayout(
                new BoxLayout(
                        top,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel logo =
                new JLabel("SMS");

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        46
                )
        );

        logo.setForeground(
                new Color(
                        80,
                        160,
                        255
                )
        );

        JLabel title =
                new JLabel(
                        "Student Manager"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        title.setForeground(
                Color.WHITE
        );

        top.add(logo);

        top.add(
                Box.createVerticalStrut(5)
        );

        top.add(title);

        top.add(
                Box.createVerticalStrut(45)
        );

        JButton dashboardButton =
                createSidebarButton(
                        "Dashboard"
                );

        JButton studentsButton =
                createSidebarButton(
                        "Students"
                );

        JButton changePasswordButton =
                createSidebarButton(
                        "Change Password"
                );

        dashboardButton.addActionListener(
                e -> showDashboard()
        );

        studentsButton.addActionListener(
                e -> showStudents()
        );

        changePasswordButton.addActionListener(
                e -> showChangePassword()
        );

        top.add(dashboardButton);

        top.add(
                Box.createVerticalStrut(10)
        );

        top.add(studentsButton);

        top.add(
                Box.createVerticalStrut(10)
        );

        top.add(changePasswordButton);

        sidebar.add(
                top,
                BorderLayout.NORTH
        );

        // -------------------------
        // BOTTOM
        // -------------------------

        JPanel bottom =
                new JPanel();

        bottom.setBackground(
                SIDEBAR_COLOR
        );

        bottom.setLayout(
                new BoxLayout(
                        bottom,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel tech1 =
                createSidebarInfo(
                        "Java Swing"
                );

        JLabel tech2 =
                createSidebarInfo(
                        "JDBC + Oracle"
                );

        JLabel userLabel =
                createSidebarInfo(
                        "Logged in: "
                                + currentUsername
                );

        JButton logoutButton =
                createSidebarButton(
                        "Logout"
                );

        logoutButton.addActionListener(e -> {

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to logout?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice ==
                    JOptionPane.YES_OPTION) {

                currentUsername = "";

                showLogin();
            }
        });

        bottom.add(tech1);

        bottom.add(
                Box.createVerticalStrut(4)
        );

        bottom.add(tech2);

        bottom.add(
                Box.createVerticalStrut(4)
        );

        bottom.add(userLabel);

        bottom.add(
                Box.createVerticalStrut(18)
        );

        bottom.add(logoutButton);

        sidebar.add(
                bottom,
                BorderLayout.SOUTH
        );

        return sidebar;
    }

    // =========================================================
    // SIDEBAR INFO LABEL
    // =========================================================

    private JLabel createSidebarInfo(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        label.setForeground(
                new Color(
                        150,
                        180,
                        220
                )
        );

        return label;
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void showDashboard() {

        contentPanel.removeAll();

        JPanel page =
                new JPanel(
                        new BorderLayout(0, 25)
                );

        page.setBackground(
                MAIN_BACKGROUND
        );

        page.setBorder(
                new EmptyBorder(
                        38,
                        40,
                        35,
                        40
                )
        );

        // -------------------------
        // HEADER
        // -------------------------

        JPanel header =
                new JPanel();

        header.setBackground(
                MAIN_BACKGROUND
        );

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading =
                new JLabel(
                        "Dashboard"
                );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36
                )
        );

        heading.setForeground(
                TEXT_COLOR
        );

        JLabel subtitle =
                new JLabel(
                        "Welcome, "
                                + currentUsername
                                + "  •  Student Management Overview"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

        header.add(heading);

        header.add(
                Box.createVerticalStrut(6)
        );

        header.add(subtitle);

        page.add(
                header,
                BorderLayout.NORTH
        );

        // -------------------------
        // CENTER
        // -------------------------

        JPanel center =
                new JPanel();

        center.setBackground(
                MAIN_BACKGROUND
        );

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        // -------------------------
        // STAT CARDS
        // -------------------------

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                18,
                                0
                        )
                );

        statsPanel.setBackground(
                MAIN_BACKGROUND
        );

        totalStudentsLabel =
                createValueLabel();

        cseStudentsLabel =
                createValueLabel();

        eceStudentsLabel =
                createValueLabel();

        averageCgpaLabel =
                createValueLabel();

        statsPanel.add(
                createStatCard(
                        "Total Students",
                        totalStudentsLabel,
                        PRIMARY_COLOR
                )
        );

        statsPanel.add(
                createStatCard(
                        "CSE Students",
                        cseStudentsLabel,
                        new Color(13, 110, 253)
                )
        );

        statsPanel.add(
                createStatCard(
                        "ECE Students",
                        eceStudentsLabel,
                        new Color(111, 66, 193)
                )
        );

        statsPanel.add(
                createStatCard(
                        "Average CGPA",
                        averageCgpaLabel,
                        SUCCESS_COLOR
                )
        );

        center.add(statsPanel);

        center.add(
                Box.createVerticalStrut(28)
        );

        // -------------------------
        // QUICK ACTION CARD
        // -------------------------

        JPanel quickCard =
                createWhiteCard();

        quickCard.setLayout(
                new BorderLayout(
                        20,
                        0
                )
        );

        quickCard.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                25,
                                28,
                                25,
                                28
                        )
                )
        );

        JLabel quickTitle =
                new JLabel(
                        "Quick Actions"
                );

        quickTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        quickTitle.setForeground(
                TEXT_COLOR
        );

        JLabel quickText =
                new JLabel(
                        "Manage student records using the Students section."
                );

        quickText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        quickText.setForeground(
                SECONDARY_TEXT
        );

        JPanel quickLeft =
                new JPanel();

        quickLeft.setBackground(
                Color.WHITE
        );

        quickLeft.setLayout(
                new BoxLayout(
                        quickLeft,
                        BoxLayout.Y_AXIS
                )
        );

        quickLeft.add(quickTitle);

        quickLeft.add(
                Box.createVerticalStrut(7)
        );

        quickLeft.add(quickText);

        JButton manageButton =
                createPrimaryButton(
                        "Manage Students"
                );

        manageButton.setPreferredSize(
                new Dimension(
                        180,
                        45
                )
        );

        manageButton.addActionListener(
                e -> showStudents()
        );

        quickCard.add(
                quickLeft,
                BorderLayout.CENTER
        );

        quickCard.add(
                manageButton,
                BorderLayout.EAST
        );

        center.add(quickCard);

        page.add(
                center,
                BorderLayout.CENTER
        );

        contentPanel.add(page);

        refreshDashboard();

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // =========================================================
    // STAT VALUE LABEL
    // =========================================================

    private JLabel createValueLabel() {

        JLabel label =
                new JLabel("0");

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        34
                )
        );

        return label;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            JLabel valueLabel,
            Color valueColor) {

        JPanel card =
                createWhiteCard();

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                20,
                                22,
                                20,
                                22
                        )
                )
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        titleLabel.setForeground(
                SECONDARY_TEXT
        );

        valueLabel.setForeground(
                valueColor
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(12)
        );

        card.add(valueLabel);

        return card;
    }

    // =========================================================
    // DASHBOARD REFRESH
    // =========================================================

    private void refreshDashboard() {

        if (totalStudentsLabel == null) {
            return;
        }

        int total =
                dao.getTotalStudents();

        int cse =
                dao.getStudentsByCourse("CSE");

        int ece =
                dao.getStudentsByCourse("ECE");

        double average =
                dao.getAverageCgpa();

        totalStudentsLabel.setText(
                String.valueOf(total)
        );

        cseStudentsLabel.setText(
                String.valueOf(cse)
        );

        eceStudentsLabel.setText(
                String.valueOf(ece)
        );

        averageCgpaLabel.setText(
                String.format(
                        Locale.US,
                        "%.2f",
                        average
                )
        );
    }

    // =========================================================
    // STUDENTS PAGE
    // =========================================================

    private void showStudents() {

        contentPanel.removeAll();

        JPanel page =
                new JPanel(
                        new BorderLayout(0, 20)
                );

        page.setBackground(
                MAIN_BACKGROUND
        );

        page.setBorder(
                new EmptyBorder(
                        35,
                        40,
                        30,
                        40
                )
        );

        // -------------------------
        // HEADER
        // -------------------------

        JPanel header =
                new JPanel();

        header.setBackground(
                MAIN_BACKGROUND
        );

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading =
                new JLabel("Students");

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36
                )
        );

        heading.setForeground(
                TEXT_COLOR
        );

        JLabel subtitle =
                new JLabel(
                        "Add, update, search and delete student records"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

        header.add(heading);

        header.add(
                Box.createVerticalStrut(5)
        );

        header.add(subtitle);

        page.add(
                header,
                BorderLayout.NORTH
        );

        // -------------------------
        // CENTER
        // -------------------------

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
                        )
                );

        center.setBackground(
                MAIN_BACKGROUND
        );

        center.add(
                createStudentForm(),
                BorderLayout.NORTH
        );

        center.add(
                createStudentTable(),
                BorderLayout.CENTER
        );

        page.add(
                center,
                BorderLayout.CENTER
        );

        contentPanel.add(page);

        loadStudents();

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // =========================================================
    // STUDENT FORM
    // =========================================================

    private JPanel createStudentForm() {

        JPanel card =
                createWhiteCard();

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        card.setLayout(
                new BorderLayout()
        );

        JPanel fields =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                15,
                                10
                        )
                );

        fields.setBackground(
                Color.WHITE
        );

        // ID
        JPanel idPanel =
                createFieldPanel(
                        "Student ID"
                );

        idField =
                createTextField();

        idPanel.add(
                idField,
                BorderLayout.CENTER
        );

        // Name
        JPanel namePanel =
                createFieldPanel(
                        "Name"
                );

        nameField =
                createTextField();

        namePanel.add(
                nameField,
                BorderLayout.CENTER
        );

        // Age
        JPanel agePanel =
                createFieldPanel(
                        "Age"
                );

        ageField =
                createTextField();

        agePanel.add(
                ageField,
                BorderLayout.CENTER
        );

        // Gender
        JPanel genderPanel =
                createFieldPanel(
                        "Gender"
                );

        genderBox =
                new JComboBox<>(
                        new String[]{
                                "Male",
                                "Female",
                                "Other"
                        }
                );

        styleComboBox(genderBox);

        genderPanel.add(
                genderBox,
                BorderLayout.CENTER
        );

        // Course
        JPanel coursePanel =
                createFieldPanel(
                        "Course"
                );

        courseField =
                createTextField();

        coursePanel.add(
                courseField,
                BorderLayout.CENTER
        );

        // Phone
        JPanel phonePanel =
                createFieldPanel(
                        "Phone"
                );

        phoneField =
                createTextField();

        phonePanel.add(
                phoneField,
                BorderLayout.CENTER
        );

        // CGPA
        JPanel cgpaPanel =
                createFieldPanel(
                        "CGPA"
                );

        cgpaField =
                createTextField();

        cgpaPanel.add(
                cgpaField,
                BorderLayout.CENTER
        );

        // Buttons
        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                8,
                                8
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        JButton addButton =
                createPrimaryButton(
                        "Add"
                );

        JButton updateButton =
                createPrimaryButton(
                        "Update"
                );

        JButton deleteButton =
                createDangerButton(
                        "Delete"
                );

        JButton clearButton =
                createSecondaryButton(
                        "Clear"
                );

        addButton.addActionListener(
                e -> addStudent()
        );

        updateButton.addActionListener(
                e -> updateStudent()
        );

        deleteButton.addActionListener(
                e -> deleteStudent()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        fields.add(idPanel);
        fields.add(namePanel);
        fields.add(agePanel);
        fields.add(genderPanel);

        fields.add(coursePanel);
        fields.add(phonePanel);
        fields.add(cgpaPanel);
        fields.add(buttonPanel);

        card.add(
                fields,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // FIELD PANEL
    // =========================================================

    private JPanel createFieldPanel(
            String title) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                5
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.add(
                createLabel(title),
                BorderLayout.NORTH
        );

        return panel;
    }

    // =========================================================
    // TABLE
    // =========================================================

    private JPanel createStudentTable() {

        JPanel card =
                createWhiteCard();

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                15,
                                0,
                                0,
                                0
                        )
                )
        );

        card.setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );

        // -------------------------
        // TOP
        // -------------------------

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setBackground(
                Color.WHITE
        );

        top.setBorder(
                new EmptyBorder(
                        0,
                        15,
                        0,
                        15
                )
        );

        JLabel title =
                new JLabel(
                        "Student Records"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        title.setForeground(
                TEXT_COLOR
        );

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                7,
                                0
                        )
                );

        searchPanel.setBackground(
                Color.WHITE
        );

        searchField =
                createTextField();

        searchField.setPreferredSize(
                new Dimension(
                        180,
                        36
                )
        );

        JButton searchButton =
                createPrimaryButton(
                        "Search"
                );

        JButton refreshButton =
                createSecondaryButton(
                        "Refresh"
                );

        JButton exportButton =
                createSuccessButton(
                        "Export CSV"
                );

        searchButton.addActionListener(
                e -> searchStudents()
        );

        refreshButton.addActionListener(
                e -> {

                    searchField.setText("");

                    loadStudents();
                }
        );

        exportButton.addActionListener(
                e -> exportCSV()
        );

        searchField.addActionListener(
                e -> searchStudents()
        );

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);
        searchPanel.add(exportButton);

        top.add(
                title,
                BorderLayout.WEST
        );

        top.add(
                searchPanel,
                BorderLayout.EAST
        );

        card.add(
                top,
                BorderLayout.NORTH
        );

        // -------------------------
        // TABLE MODEL
        // -------------------------

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "ID",
                                "Name",
                                "Age",
                                "Gender",
                                "Course",
                                "Phone",
                                "CGPA"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        studentTable =
                new JTable(tableModel);

        styleTable();

        JScrollPane scrollPane =
                new JScrollPane(
                        studentTable
                );

        scrollPane.setBorder(
                new LineBorder(
                        BORDER_COLOR
                )
        );

        styleScrollPane(scrollPane);

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );

        studentTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        loadSelectedStudent();
                    }
                });

        return card;
    }

    // =========================================================
    // TABLE STYLE
    // =========================================================

    private void styleTable() {

        studentTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        studentTable.setForeground(
                TEXT_COLOR
        );

        studentTable.setBackground(
                Color.WHITE
        );

        studentTable.setRowHeight(40);

        studentTable.setGridColor(
                new Color(
                        222,
                        227,
                        234
                )
        );

        studentTable.setSelectionBackground(
                TABLE_SELECTED
        );

        studentTable.setSelectionForeground(
                TEXT_COLOR
        );

        studentTable.setShowGrid(true);

        studentTable.setFillsViewportHeight(
                true
        );

        studentTable.setIntercellSpacing(
                new Dimension(
                        1,
                        1
                )
        );

        // -------------------------
        // HEADER
        // -------------------------

        studentTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                14
                        )
                );

        studentTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                42
                        )
                );

        studentTable
                .getTableHeader()
                .setOpaque(true);

        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer();

        headerRenderer.setOpaque(true);

        headerRenderer.setBackground(
                TABLE_HEADER
        );

        headerRenderer.setForeground(
                Color.WHITE
        );

        headerRenderer.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        headerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0;
             i < studentTable
                     .getColumnCount();
             i++) {

            studentTable
                    .getColumnModel()
                    .getColumn(i)
                    .setHeaderRenderer(
                            headerRenderer
                    );
        }

        // -------------------------
        // ROW RENDERER
        // -------------------------

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer() {

                    @Override
                    public Component
                    getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        super.getTableCellRendererComponent(
                                table,
                                value,
                                isSelected,
                                hasFocus,
                                row,
                                column
                        );

                        setHorizontalAlignment(
                                SwingConstants.CENTER
                        );

                        setForeground(
                                TEXT_COLOR
                        );

                        if (isSelected) {

                            setBackground(
                                    TABLE_SELECTED
                            );

                        } else if (row % 2 == 0) {

                            setBackground(
                                    Color.WHITE
                            );

                        } else {

                            setBackground(
                                    TABLE_ALTERNATE
                            );
                        }

                        setBorder(
                                new EmptyBorder(
                                        0,
                                        8,
                                        0,
                                        8
                                )
                        );

                        return this;
                    }
                };

        for (int i = 0;
             i < studentTable
                     .getColumnCount();
             i++) {

            studentTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            renderer
                    );
        }

        // -------------------------
        // COLUMN WIDTHS
        // -------------------------

        studentTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(70);

        studentTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(170);

        studentTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(60);

        studentTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(90);

        studentTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(100);

        studentTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(130);

        studentTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(80);
    }

    // =========================================================
    // SCROLLBAR STYLE
    // =========================================================

    private void styleScrollPane(
            JScrollPane scrollPane) {

        scrollPane.setBackground(
                Color.WHITE
        );

        scrollPane.getViewport()
                .setBackground(
                        Color.WHITE
                );

        JScrollBar vertical =
                scrollPane.getVerticalScrollBar();

        vertical.setBackground(
                MAIN_BACKGROUND
        );

        vertical.setPreferredSize(
                new Dimension(
                        12,
                        0
                )
        );

        vertical.setUI(
                new BasicScrollBarUI() {

                    @Override
                    protected void
                    configureScrollBarColors() {

                        this.thumbColor =
                                new Color(
                                        165,
                                        177,
                                        193
                                );

                        this.trackColor =
                                MAIN_BACKGROUND;
                    }

                    @Override
                    protected JButton
                    createDecreaseButton(
                            int orientation) {

                        return createInvisibleButton();
                    }

                    @Override
                    protected JButton
                    createIncreaseButton(
                            int orientation) {

                        return createInvisibleButton();
                    }
                }
        );
    }

    private JButton createInvisibleButton() {

        JButton button =
                new JButton();

        button.setPreferredSize(
                new Dimension(
                        0,
                        0
                )
        );

        button.setMinimumSize(
                new Dimension(
                        0,
                        0
                )
        );

        button.setMaximumSize(
                new Dimension(
                        0,
                        0
                )
        );

        return button;
    }

    // =========================================================
    // LOAD STUDENTS
    // =========================================================

    private void loadStudents() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);

        List<Student> students =
                dao.viewAllStudents();

        for (Student student : students) {

            tableModel.addRow(
                    new Object[]{
                            student.getStudentId(),
                            student.getName(),
                            student.getAge(),
                            student.getGender(),
                            student.getCourse(),
                            student.getPhone(),
                            formatCgpa(
                                    student.getCgpa()
                            )
                    }
            );
        }
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchStudents() {

        String keyword =
                searchField
                        .getText()
                        .trim();

        if (keyword.isEmpty()) {

            loadStudents();

            return;
        }

        tableModel.setRowCount(0);

        List<Student> students =
                dao.searchStudents(keyword);

        for (Student student : students) {

            tableModel.addRow(
                    new Object[]{
                            student.getStudentId(),
                            student.getName(),
                            student.getAge(),
                            student.getGender(),
                            student.getCourse(),
                            student.getPhone(),
                            formatCgpa(
                                    student.getCgpa()
                            )
                    }
            );
        }

        if (students.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No student found.",
                    "Search",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // =========================================================
    // SELECTED STUDENT
    // =========================================================

    private void loadSelectedStudent() {

        int row =
                studentTable
                        .getSelectedRow();

        if (row == -1) {
            return;
        }

        idField.setText(
                tableModel
                        .getValueAt(row, 0)
                        .toString()
        );

        nameField.setText(
                tableModel
                        .getValueAt(row, 1)
                        .toString()
        );

        ageField.setText(
                tableModel
                        .getValueAt(row, 2)
                        .toString()
        );

        genderBox.setSelectedItem(
                tableModel
                        .getValueAt(row, 3)
                        .toString()
        );

        courseField.setText(
                tableModel
                        .getValueAt(row, 4)
                        .toString()
        );

        phoneField.setText(
                tableModel
                        .getValueAt(row, 5)
                        .toString()
        );

        cgpaField.setText(
                tableModel
                        .getValueAt(row, 6)
                        .toString()
        );
    }

    // =========================================================
    // ADD STUDENT
    // =========================================================

    private void addStudent() {

        try {

            if (!validateFields()) {
                return;
            }

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );

            if (dao.studentExists(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID already exists.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String name =
                    nameField
                            .getText()
                            .trim();

            int age =
                    Integer.parseInt(
                            ageField
                                    .getText()
                                    .trim()
                    );

            String gender =
                    genderBox
                            .getSelectedItem()
                            .toString();

            String course =
                    courseField
                            .getText()
                            .trim();

            String phone =
                    phoneField
                            .getText()
                            .trim();

            double cgpa =
                    Double.parseDouble(
                            cgpaField
                                    .getText()
                                    .trim()
                    );

            Student student =
                    new Student(
                            id,
                            name,
                            age,
                            gender,
                            course,
                            phone,
                            cgpa
                    );

            dao.addStudent(student);

            JOptionPane.showMessageDialog(
                    this,
                    "Student Added Successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

            loadStudents();

            refreshDashboard();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // UPDATE
    // =========================================================

    private void updateStudent() {

        try {

            if (!validateFields()) {
                return;
            }

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );

            if (!dao.studentExists(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String name =
                    nameField
                            .getText()
                            .trim();

            int age =
                    Integer.parseInt(
                            ageField
                                    .getText()
                                    .trim()
                    );

            String gender =
                    genderBox
                            .getSelectedItem()
                            .toString();

            String course =
                    courseField
                            .getText()
                            .trim();

            String phone =
                    phoneField
                            .getText()
                            .trim();

            double cgpa =
                    Double.parseDouble(
                            cgpaField
                                    .getText()
                                    .trim()
                    );

            dao.updateStudent(
                    id,
                    name,
                    age,
                    gender,
                    course,
                    phone,
                    cgpa
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Student Updated Successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

            loadStudents();

            refreshDashboard();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // DELETE
    // =========================================================

    private void deleteStudent() {

        try {

            String idText =
                    idField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter Student ID.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int id =
                    Integer.parseInt(idText);

            if (!dao.studentExists(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete this student?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice !=
                    JOptionPane.YES_OPTION) {

                return;
            }

            dao.deleteStudent(id);

            JOptionPane.showMessageDialog(
                    this,
                    "Student Deleted Successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

            loadStudents();

            refreshDashboard();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Student ID must be a number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // VALIDATION
    // =========================================================

    private boolean validateFields() {

        if (idField.getText().trim().isEmpty()
                || nameField.getText().trim().isEmpty()
                || ageField.getText().trim().isEmpty()
                || courseField.getText().trim().isEmpty()
                || phoneField.getText().trim().isEmpty()
                || cgpaField.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        try {

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );

            if (id < 1 || id > 99999) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID must be between 1 and 99999.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }

            int age =
                    Integer.parseInt(
                            ageField
                                    .getText()
                                    .trim()
                    );

            if (age < 1 || age > 100) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be between 1 and 100.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }

            double cgpa =
                    Double.parseDouble(
                            cgpaField
                                    .getText()
                                    .trim()
                    );

            if (cgpa < 0 || cgpa > 10) {

                JOptionPane.showMessageDialog(
                        this,
                        "CGPA must be between 0 and 10.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }

            String phone =
                    phoneField
                            .getText()
                            .trim();

            if (!phone.matches("\\d{10}")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Phone number must contain exactly 10 digits.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return false;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID, Age and CGPA must contain valid numbers.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        idField.setText("");

        nameField.setText("");

        ageField.setText("");

        genderBox.setSelectedIndex(0);

        courseField.setText("");

        phoneField.setText("");

        cgpaField.setText("");

        if (studentTable != null) {

            studentTable.clearSelection();
        }

        if (searchField != null) {

            searchField.setText("");
        }
    }

    // =========================================================
    // EXPORT CSV
    // =========================================================

    private void exportCSV() {

        if (tableModel == null
                || tableModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "No student records to export.",
                    "Export CSV",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        JFileChooser chooser =
                new JFileChooser();

        chooser.setDialogTitle(
                "Save Student Records"
        );

        chooser.setSelectedFile(
                new File(
                        "student_records.csv"
                )
        );

        int result =
                chooser.showSaveDialog(
                        this
                );

        if (result !=
                JFileChooser.APPROVE_OPTION) {

            return;
        }

        File file =
                chooser.getSelectedFile();

        try (
                PrintWriter writer =
                        new PrintWriter(
                                file,
                                StandardCharsets.UTF_8
                        )
        ) {

            writer.println(
                    "Student ID,Name,Age,Gender,Course,Phone,CGPA"
            );

            for (
                    int row = 0;
                    row < tableModel.getRowCount();
                    row++
            ) {

                StringBuilder line =
                        new StringBuilder();

                for (
                        int col = 0;
                        col < tableModel.getColumnCount();
                        col++
                ) {

                    if (col > 0) {

                        line.append(",");
                    }

                    Object value =
                            tableModel.getValueAt(
                                    row,
                                    col
                            );

                    line.append(
                            escapeCSV(
                                    value == null
                                            ? ""
                                            : value.toString()
                            )
                    );
                }

                writer.println(line);
            }

            JOptionPane.showMessageDialog(
                    this,
                    "CSV exported successfully.",
                    "Export CSV",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to export CSV.",
                    "Export Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CSV ESCAPE
    // =========================================================

    private String escapeCSV(
            String value) {

        if (value.contains(",")
                || value.contains("\"")
                || value.contains("\n")) {

            return "\""
                    + value.replace(
                            "\"",
                            "\"\""
                    )
                    + "\"";
        }

        return value;
    }

    // =========================================================
    // CHANGE PASSWORD
    // =========================================================

    private void showChangePassword() {

        contentPanel.removeAll();

        JPanel page =
                new JPanel(
                        new BorderLayout()
                );

        page.setBackground(
                MAIN_BACKGROUND
        );

        page.setBorder(
                new EmptyBorder(
                        40,
                        40,
                        30,
                        40
                )
        );

        // -------------------------
        // HEADER
        // -------------------------

        JPanel header =
                new JPanel();

        header.setBackground(
                MAIN_BACKGROUND
        );

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading =
                new JLabel(
                        "Change Password"
                );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        36
                )
        );

        heading.setForeground(
                TEXT_COLOR
        );

        JLabel subtitle =
                new JLabel(
                        "Update your admin login password"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

        header.add(heading);

        header.add(
                Box.createVerticalStrut(6)
        );

        header.add(subtitle);

        page.add(
                header,
                BorderLayout.NORTH
        );

        // -------------------------
        // CARD
        // -------------------------

        JPanel card =
                createWhiteCard();

        card.setPreferredSize(
                new Dimension(
                        600,
                        430
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                30,
                                35,
                                30,
                                35
                        )
                )
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel oldLabel =
                createLabel(
                        "Current Password"
                );

        JPasswordField oldPassword =
                new JPasswordField();

        styleTextField(oldPassword);

        JLabel newLabel =
                createLabel(
                        "New Password"
                );

        JPasswordField newPassword =
                new JPasswordField();

        styleTextField(newPassword);

        JLabel confirmLabel =
                createLabel(
                        "Confirm New Password"
                );

        JPasswordField confirmPassword =
                new JPasswordField();

        styleTextField(confirmPassword);

        JButton changeButton =
                createPrimaryButton(
                        "Change Password"
                );

        changeButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        card.add(oldLabel);

        card.add(
                Box.createVerticalStrut(7)
        );

        card.add(oldPassword);

        card.add(
                Box.createVerticalStrut(18)
        );

        card.add(newLabel);

        card.add(
                Box.createVerticalStrut(7)
        );

        card.add(newPassword);

        card.add(
                Box.createVerticalStrut(18)
        );

        card.add(confirmLabel);

        card.add(
                Box.createVerticalStrut(7)
        );

        card.add(confirmPassword);

        card.add(
                Box.createVerticalStrut(25)
        );

        card.add(changeButton);

        changeButton.addActionListener(e -> {

            String oldPass =
                    new String(
                            oldPassword
                                    .getPassword()
                    );

            String newPass =
                    new String(
                            newPassword
                                    .getPassword()
                    );

            String confirmPass =
                    new String(
                            confirmPassword
                                    .getPassword()
                    );

            // -------------------------
            // CURRENT PASSWORD
            // -------------------------

            if (!oldPass.equals(
                    loginPassword)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Current password is incorrect.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // -------------------------
            // NEW PASSWORD
            // -------------------------

            if (newPass.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "New password cannot be empty.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (newPass.length() < 6) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password must contain at least 6 characters.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (!newPass.equals(
                    confirmPass)) {

                JOptionPane.showMessageDialog(
                        this,
                        "New passwords do not match.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // -------------------------
            // DATABASE UPDATE
            // -------------------------

            boolean databaseChanged =
                    dao.changePassword(
                            currentUsername,
                            oldPass,
                            newPass
                    );

            // Local password is also updated.
            // This keeps login working even if
            // LOGIN table is not configured.

            loginPassword = newPass;

            if (databaseChanged) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password changed successfully and saved to database.",
                        "Password",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Password changed successfully for this application session.",
                        "Password",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            oldPassword.setText("");

            newPassword.setText("");

            confirmPassword.setText("");
        });

        // -------------------------
        // WRAPPER
        // -------------------------

        JPanel wrapper =
                new JPanel(
                        new GridBagLayout()
                );

        wrapper.setBackground(
                MAIN_BACKGROUND
        );

        wrapper.add(card);

        page.add(
                wrapper,
                BorderLayout.CENTER
        );

        contentPanel.add(page);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // =========================================================
    // CGPA FORMAT
    // =========================================================

    private String formatCgpa(
            double cgpa) {

        return String.format(
                Locale.US,
                "%.2f",
                cgpa
        )
                .replaceAll(
                        "0+$",
                        ""
                )
                .replaceAll(
                        "\\.$",
                        ""
                );
    }

    // =========================================================
    // WHITE CARD
    // =========================================================

    private JPanel createWhiteCard() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                CARD_BACKGROUND
        );

        return panel;
    }

    // =========================================================
    // LABEL
    // =========================================================

    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        styleTextField(field);

        return field;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            JTextField field) {

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        field.setForeground(
                TEXT_COLOR
        );

        field.setBackground(
                Color.WHITE
        );

        field.setCaretColor(
                TEXT_COLOR
        );

        field.setOpaque(true);

        setTextFieldBorder(
                field,
                INPUT_BORDER
        );

        field.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(
                            FocusEvent e) {

                        setTextFieldBorder(
                                field,
                                INPUT_FOCUS
                        );
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e) {

                        setTextFieldBorder(
                                field,
                                INPUT_BORDER
                        );
                    }
                }
        );
    }

    // =========================================================
    // TEXT FIELD BORDER
    // =========================================================

    private void setTextFieldBorder(
            JTextField field,
            Color borderColor) {

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                borderColor,
                                1
                        ),
                        new EmptyBorder(
                                7,
                                10,
                                7,
                                10
                        )
                )
        );
    }

    // =========================================================
    // COMBO BOX
    // =========================================================

    private void styleComboBox(
            JComboBox<String> comboBox) {

        comboBox.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        comboBox.setForeground(
                TEXT_COLOR
        );

        comboBox.setBackground(
                Color.WHITE
        );

        comboBox.setOpaque(true);

        comboBox.setBorder(
                new LineBorder(
                        INPUT_BORDER
                )
        );

        // Custom dropdown renderer
        comboBox.setRenderer(
                new DefaultListCellRenderer() {

                    @Override
                    public Component
                    getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus) {

                        JLabel label =
                                (JLabel) super
                                        .getListCellRendererComponent(
                                                list,
                                                value,
                                                index,
                                                isSelected,
                                                cellHasFocus
                                        );

                        label.setOpaque(true);

                        label.setBorder(
                                new EmptyBorder(
                                        6,
                                        8,
                                        6,
                                        8
                                )
                        );

                        if (isSelected) {

                            label.setBackground(
                                    PRIMARY_COLOR
                            );

                            label.setForeground(
                                    Color.WHITE
                            );

                        } else {

                            label.setBackground(
                                    Color.WHITE
                            );

                            label.setForeground(
                                    TEXT_COLOR
                            );
                        }

                        return label;
                    }
                }
        );
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private JButton createPrimaryButton(
            String text) {

        JButton button =
                new JButton(text);

        styleButton(
                button,
                PRIMARY_COLOR,
                PRIMARY_HOVER,
                Color.WHITE
        );

        return button;
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private JButton createSecondaryButton(
            String text) {

        JButton button =
                new JButton(text);

        styleButton(
                button,
                SECONDARY_COLOR,
                SECONDARY_HOVER,
                TEXT_COLOR
        );

        return button;
    }

    // =========================================================
    // DANGER BUTTON
    // =========================================================

    private JButton createDangerButton(
            String text) {

        JButton button =
                new JButton(text);

        styleButton(
                button,
                DANGER_COLOR,
                DANGER_HOVER,
                Color.WHITE
        );

        return button;
    }

    // =========================================================
    // SUCCESS BUTTON
    // =========================================================

    private JButton createSuccessButton(
            String text) {

        JButton button =
                new JButton(text);

        styleButton(
                button,
                SUCCESS_COLOR,
                SUCCESS_HOVER,
                Color.WHITE
        );

        return button;
    }

    // =========================================================
    // BUTTON STYLE
    // =========================================================

    private void styleButton(
            JButton button,
            Color normalColor,
            Color hoverColor,
            Color textColor) {

        // Force our own Swing painting.
        // This prevents Windows theme from
        // changing the button colors.

        button.setUI(
                new BasicButtonUI()
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                textColor
        );

        button.setBackground(
                normalColor
        );

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setFocusPainted(false);

        button.setBorderPainted(true);

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                normalColor
                        ),
                        new EmptyBorder(
                                7,
                                13,
                                7,
                                13
                        )
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                hoverColor
                        );

                        button.setBorder(
                                BorderFactory.createCompoundBorder(
                                        new LineBorder(
                                                hoverColor
                                        ),
                                        new EmptyBorder(
                                                7,
                                                13,
                                                7,
                                                13
                                        )
                                )
                        );

                        button.repaint();
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                normalColor
                        );

                        button.setBorder(
                                BorderFactory.createCompoundBorder(
                                        new LineBorder(
                                                normalColor
                                        ),
                                        new EmptyBorder(
                                                7,
                                                13,
                                                7,
                                                13
                                        )
                                )
                        );

                        button.repaint();
                    }
                }
        );
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createSidebarButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setUI(
                new BasicButtonUI()
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                SIDEBAR_BUTTON
        );

        button.setOpaque(true);

        button.setContentAreaFilled(true);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        46
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        button.setBackground(
                                SIDEBAR_HOVER
                        );

                        button.repaint();
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        button.setBackground(
                                SIDEBAR_BUTTON
                        );

                        button.repaint();
                    }
                }
        );

        return button;
    }
}