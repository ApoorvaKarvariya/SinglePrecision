import java.awt.*;
import javax.swing.*;

public class SinglePrecisionSwing {

    // Converts a float to its 32-bit binary representation
    private static String floatToBinary(float number) {
        int intBits = Float.floatToIntBits(number);
        return String.format("%32s", Integer.toBinaryString(intBits)).replace(' ', '0');
    }

    // Extracts components (sign, exponent, mantissa) from the binary representation of a float
    private static String getFloatComponents(float number) {
        String binary = floatToBinary(number);
        String sign = binary.substring(0, 1);
        String exponent = binary.substring(1, 9);
        String mantissa = binary.substring(9);
        int exponentValue = Integer.parseInt(exponent, 2) - 127;

        return String.format(
                "Number: %.6f\n" +
                        "Binary Representation: %s\n" +
                        "Sign Bit: %s (0 = Positive, 1 = Negative)\n" +
                        "Exponent: %s (Stored: %d, Actual: %d)\n" +
                        "Mantissa: %s (Fractional part)",
                number, binary, sign, exponent, Integer.parseInt(exponent, 2), exponentValue, mantissa
        );
    }

 

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Single Precision Representation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Center the window

        // Font settings
        Font font = new Font("Arial", Font.PLAIN, 16);

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(220, 240, 255));
        

        JLabel inputLabel = new JLabel("Enter a floating-point number:");
        inputLabel.setFont(font);

        JTextField inputField = new JTextField(15);
        inputField.setFont(font);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(font);
        calculateButton.setBackground(new Color(0, 123, 255));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);

        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(calculateButton);

        // Result area
        JTextArea resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        resultArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Results"));

        // Add components to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listener for the calculate button
        calculateButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (input.isEmpty()) {
                resultArea.setText("Input cannot be empty! Please enter a valid floating-point number.");
                return;
            }

            try {
                float number = Float.parseFloat(input);
                String result = getFloatComponents(number);
                resultArea.setText(result);
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input! Please enter a valid floating-point number.");
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    // private static javax.swing.border.Border createPaddedBorder(int i) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }


}
