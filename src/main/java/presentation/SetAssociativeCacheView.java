package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;

public class SetAssociativeCacheView {
    private JPanel Cache_Settings;
    private JTextField tfCacheSize;
    private JTextField tfMemorySize;
    private JTextField tfOffsetBits;
    private JButton buttonStart;
    private JPanel panel1;
    private JPanel Instruction_Breakdown;
    private JPanel Cache_Table;
    private JButton buttonBack;
    private JLabel labelOffset;
    private JLabel labelIndexBits;
    private JLabel labelTag;
    private JButton randomButton;
    private JButton submitButton;
    private JTextField tfInstruction;
    private JTable cacheTable1;
    private JTable cacheTable2;
    private JTable memoryTable;

    public static void colorTableCell(JTable table, int rowIndex, int columnIndex, Color color) {
        // Check if the indices are valid
        if (rowIndex < 0 || rowIndex >= table.getRowCount() || columnIndex < 0 || columnIndex >= table.getColumnCount()) {
            System.out.println("Invalid indices");
            return;
        }

        // Set the background color directly for the specific cell
        table.setValueAt(table.getValueAt(rowIndex, columnIndex), rowIndex, columnIndex);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (row == rowIndex && col == columnIndex) {
                    c.setBackground(color);
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        // Repaint the table to reflect the changes
        table.repaint();
    }

    public static void colorTableRow(JTable table, int rowIndex, Color color) {
        // Check if the index is valid
        if (rowIndex < 0 || rowIndex >= table.getRowCount()) {
            System.out.println("Invalid index");
            return;
        }

        // Set the background color directly for each cell in the specified row
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.setValueAt(table.getValueAt(rowIndex, columnIndex), rowIndex, columnIndex);
            table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                    if (row == rowIndex) {
                        c.setBackground(color);
                    } else {
                        c.setBackground(Color.WHITE);
                    }
                    return c;
                }
            });
        }

        // Repaint the table to reflect the changes
        table.repaint();
    }


    public static void colorAllCells(JTable table, Color color) {
        // Iterate over all rows and columns
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                // Get the cell renderer for each cell
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getCellRenderer(row, col);

                // Set the background color
                renderer.setBackground(color);
            }
        }

        // Repaint the table to reflect the changes
        table.repaint();
    }

    public static int power2(int cacheSize) {
        return (int) Math.pow(2, cacheSize);
    }

    public static String generateHexSmallerThan (long maxDecimalValue){
        Random random = new Random();
        long generatedDecimal = random.nextLong() % maxDecimalValue;

        generatedDecimal = Math.abs(generatedDecimal);

        String generatedHex = Long.toHexString(generatedDecimal).toUpperCase();

        return generatedHex;
    }

    public static int calculateCacheSize(int desiredResult) {
        return (int) Math.pow(2, Math.floor(Math.log(desiredResult) / Math.log(2)) - 1);
    }

    public static int hexToDecimal(String hex) {

        // Check if the input string starts with "0x" and remove it if present
        if (hex.startsWith("0x") || hex.startsWith("0X")) {
            hex = hex.substring(2);
        }

        // Convert the hexadecimal string to decimal
        int decimal = 0;
        int power = 0;
        for (int i = hex.length() - 1; i >= 0; i--) {
            char digit = hex.charAt(i);
            int digitValue = Character.isDigit(digit) ? (digit - '0') : (Character.toUpperCase(digit) - 'A' + 10);
            decimal += digitValue * Math.pow(16, power);
            power++;
        }

        return decimal;
    }

    public static String hexToBinary(String hexString, int binaryLength) {
        // Remove any leading "0x" or "0X" if present
        hexString = hexString.replaceAll("^0[xX]", "");

        // Parse hex to BigInteger
        BigInteger bigInt = new BigInteger(hexString, 16);

        // Convert to binary string
        String binaryString = bigInt.toString(2);

        // Pad zeros to the left until the desired length is reached
        while (binaryString.length() < binaryLength) {
            binaryString = "0" + binaryString;
        }

        return binaryString;
    }

    public static boolean isTableFull(JTable table) {
        int rowCount = table.getRowCount();
        int colCount = table.getColumnCount();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Object cellValue = table.getValueAt(row, col);
                if (cellValue == null) {
                    // Found an empty cell, table is not full
                    return false;
                }
            }
        }

        // All cells are non-null, table is full
        return true;
    }

    public static boolean isValueInTable(JTable table, String targetValue) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        // Iterate through each cell in the table
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                Object cellValue = model.getValueAt(row, col);

                // Check if the cell value is a string and equals the target value
                if (cellValue instanceof String && targetValue.equals(cellValue)) {
                    // The target value is found in the table
                    return true;
                }
            }
        }

        // The target value is not found in the table
        return false;
    }

    public static boolean isCellOccupied(JTable table, int row, int column) {
        Object cellValue = table.getValueAt(row, column);
        return cellValue != null;
    }

    public static boolean isValueEqualTo(JTable table, int row, int column, String targetValue) {
        Object cellValue = table.getValueAt(row, column);

        // Check if the cell value is not null and equals the target value
        return cellValue != null && cellValue.toString().equals(targetValue);
    }

    static boolean isPowerOfTwo(int n)
    {
        return (int)(Math.ceil((Math.log(n) / Math.log(2))))
                == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }

    public static int log2(int N) {

        // calculate log2 N indirectly
        // using log() method
        int result = (int) (Math.log(N) / Math.log(2));

        return result;
    }

    public SetAssociativeCacheView() {
        final JFrame newFrame = new JFrame("Set Asociativa Cache");
        newFrame.setSize(600,600);
        newFrame.getContentPane().add(panel1);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfCacheSize.getText().isEmpty() || tfMemorySize.getText().isEmpty() || tfOffsetBits.getText().isEmpty()){
                    JOptionPane.showMessageDialog(buttonStart, "Please enter all data needed", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(!isPowerOfTwo(Integer.parseInt(tfCacheSize.getText())))
                {
                    JOptionPane.showMessageDialog(buttonStart, "Cache size should be a power of 2", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(!isPowerOfTwo(Integer.parseInt(tfMemorySize.getText())))
                {
                    JOptionPane.showMessageDialog(buttonStart, "Memory size should be a power of 2", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(Integer.parseInt(tfMemorySize.getText()) <= Integer.parseInt(tfCacheSize.getText()))
                {
                    JOptionPane.showMessageDialog(buttonStart, "Memory size should be bigger then Cache size", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(Integer.parseInt(tfCacheSize.getText()) < power2(1 + Integer.parseInt(tfOffsetBits.getText()))){
                    System.out.println(power2(1 + Integer.parseInt(tfOffsetBits.getText())));
                    JOptionPane.showMessageDialog(buttonStart, "Cache size should be bigger or equal to 2^(1+OffsetBits)", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else
                {
                    Integer offset = Integer.parseInt(tfOffsetBits.getText());
                    Integer instructionLength = log2(Integer.parseInt(tfMemorySize.getText()));
                    Integer aux = 1 << offset;
                    Integer indexBits = log2((Integer.parseInt(tfCacheSize.getText()) / aux) / 2);
                    Integer tag = instructionLength - offset - indexBits;
                    labelOffset.setText("Offset bits: " + String.valueOf(offset));
                    labelIndexBits.setText("Index bits: " + String.valueOf(indexBits));
                    labelTag.setText("Tag: " + String.valueOf(tag));
                    //urmeaza sa fac implementarea, calculez tag, index si offset, iar dupa fac simulare
                    labelOffset.setText("Offset bits: " + String.valueOf(offset));
                    labelIndexBits.setText("Index bits: " + String.valueOf(indexBits));
                    labelTag.setText("Tag: " + String.valueOf(tag));

                    Integer nrCacheLines = calculateCacheSize(Integer.parseInt(tfCacheSize.getText()));
                    Integer offsetPower1 = power2(offset - 1);
                    if(offset > 0)
                        nrCacheLines = nrCacheLines / offsetPower1;
                    else
                        nrCacheLines = nrCacheLines * 2;
                    DefaultTableModel cacheTableModel1 = (DefaultTableModel) cacheTable1.getModel();
                    cacheTableModel1.setRowCount(nrCacheLines / 2);
                    cacheTableModel1.setColumnCount(5);

                    DefaultTableModel cacheTableModel2 = (DefaultTableModel) cacheTable2.getModel();
                    cacheTableModel2.setRowCount(nrCacheLines / 2);
                    cacheTableModel2.setColumnCount(5);

                    // Define the new column headers
                    String[] newColumnHeaders = {"Index", "Valid", "Tag", "Data", "Dirty bit"};
                    // Set the new column headers
                    for (int i = 0; i < newColumnHeaders.length; i++) {
                        cacheTable1.getColumnModel().getColumn(i).setHeaderValue(newColumnHeaders[i]);
                        cacheTable2.getColumnModel().getColumn(i).setHeaderValue(newColumnHeaders[i]);
                    }
                    // Refresh the JTable to reflect the changes
                    cacheTable1.getTableHeader().repaint();
                    cacheTable2.getTableHeader().repaint();

                    System.out.println("nrCacheLines: " + nrCacheLines / 2);
                    System.out.println("Table rows: " + cacheTable1.getRowCount());
                    for (int i = 0; i < nrCacheLines / 2; i++) {
                        cacheTable1.setValueAt(i, i, 0);
                        cacheTable1.setValueAt("0", i, 1);
                        cacheTable1.setValueAt("0", i, 4);

                        cacheTable2.setValueAt(i, i, 0);
                        cacheTable2.setValueAt("0", i, 1);
                        cacheTable2.setValueAt("0", i, 4);
                    }
                    colorAllCells(cacheTable1, Color.WHITE);
                    colorAllCells(cacheTable2, Color.WHITE);

                    Integer memorySize = Integer.parseInt(tfMemorySize.getText());
                    Integer offsetPower = power2(offset);
                    System.out.println("offset Power: " + offsetPower);
                    Integer nrMemoryLines = memorySize / offsetPower;
                    DefaultTableModel memoryTableModel = (DefaultTableModel) memoryTable.getModel();
                    memoryTableModel.setRowCount(nrMemoryLines);
                    memoryTableModel.setColumnCount(offsetPower);
                    for (int i = 0; i < nrMemoryLines; i++) {
                        for ( int j = 0; j < offsetPower; j++) {
                            String memorie = "B" + i + " W" + j;
                            memoryTable.setValueAt(memorie, i, j);
                        }
                    }
                    colorAllCells(memoryTable, Color.WHITE);

                    submitButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            colorAllCells(cacheTable1, Color.WHITE);
                            colorAllCells(cacheTable2, Color.WHITE);
                            colorAllCells(memoryTable, Color.WHITE);

                            if(indexBits != 0) {
                                String binaryInstruction = hexToBinary(tfInstruction.getText(), instructionLength);
                                String tagBinary = binaryInstruction.substring(0, tag);
                                String indexBinary = binaryInstruction.substring(tag, tag + indexBits);
                                String offsetBinary = binaryInstruction.substring(tag + indexBits);
                                String blockBinary = binaryInstruction.substring(0, tag + indexBits);
                                Integer integerTag = Integer.parseInt(tagBinary, 2);
                                Integer integerIndex = Integer.parseInt(indexBinary, 2);
                                Integer integerOffset = Integer.parseInt(offsetBinary, 2);
                                Integer integerBlock = Integer.parseInt(blockBinary, 2);
                                System.out.println("Binary instruction is:" + binaryInstruction);
                                System.out.println("Tag is:" + tagBinary);
                                System.out.println("Index is:" + indexBinary);
                                System.out.println("Offset is:" + offsetBinary);
                                System.out.println("Block is:" + blockBinary);
                                if (isCellOccupied(cacheTable1, integerIndex, 2) == false || isValueEqualTo(cacheTable1, integerIndex, 2, tagBinary) == true) {
                                    cacheTable1.setValueAt(tagBinary, integerIndex, 2);
                                    cacheTable1.setValueAt("Block" + integerBlock, integerIndex, 3);
                                    cacheTable1.setValueAt("1", integerIndex, 1);
                                    colorTableCell(cacheTable1, integerIndex, 3, Color.GREEN);
                                    colorTableRow(memoryTable, integerBlock, Color.GREEN);
                                    JOptionPane.showMessageDialog(submitButton, "The cache line is updated with data from memory table");
                                    colorTableCell(cacheTable1, integerIndex, 3, Color.WHITE);
                                    return;
                                } else {
                                    cacheTable2.setValueAt(tagBinary, integerIndex, 2);
                                    cacheTable2.setValueAt("Block" + integerBlock, integerIndex, 3);
                                    cacheTable2.setValueAt("1", integerIndex, 1);
                                    colorTableCell(cacheTable2, integerIndex, 3, Color.GREEN);
                                    colorTableRow(memoryTable, integerBlock, Color.GREEN);
                                    JOptionPane.showMessageDialog(submitButton, "The cache line is updated with data from memory table");
                                    colorTableCell(cacheTable2, integerIndex, 3, Color.WHITE);
                                    return;
                                }
                            }
                            else{
                                String binaryInstruction = hexToBinary(tfInstruction.getText(), instructionLength);
                                String tagBinary = binaryInstruction.substring(0, tag);
                                String indexBinary = "0";
                                String offsetBinary = binaryInstruction.substring(tag + indexBits);
                                String blockBinary = binaryInstruction.substring(0, tag + indexBits);
                                Integer integerTag = Integer.parseInt(tagBinary, 2);
                                Integer integerIndex = 0;
                                Integer integerOffset = Integer.parseInt(offsetBinary, 2);
                                Integer integerBlock = Integer.parseInt(blockBinary, 2);
                                System.out.println("Binary instruction is:" + binaryInstruction);
                                System.out.println("Tag is:" + tagBinary);
                                System.out.println("Index is:" + indexBinary);
                                System.out.println("Offset is:" + offsetBinary);
                                System.out.println("Block is:" + blockBinary);
                                if (isCellOccupied(cacheTable1, integerIndex, 2) == false || isValueEqualTo(cacheTable1, integerIndex, 2, tagBinary) == true) {
                                    cacheTable1.setValueAt(tagBinary, integerIndex, 2);
                                    cacheTable1.setValueAt("Block" + integerBlock, integerIndex, 3);
                                    cacheTable1.setValueAt("1", integerIndex, 1);
                                    colorTableCell(cacheTable1, integerIndex, 3, Color.GREEN);
                                    colorTableRow(memoryTable, integerBlock, Color.GREEN);
                                    JOptionPane.showMessageDialog(submitButton, "The cache line is updated with data from memory table");
                                    colorTableCell(cacheTable1, integerIndex, 3, Color.WHITE);
                                    return;
                                } else {
                                    cacheTable2.setValueAt(tagBinary, integerIndex, 2);
                                    cacheTable2.setValueAt("Block" + integerBlock, integerIndex, 3);
                                    cacheTable2.setValueAt("1", integerIndex, 1);
                                    colorTableCell(cacheTable2, integerIndex, 3, Color.GREEN);
                                    colorTableRow(memoryTable, integerBlock, Color.GREEN);
                                    JOptionPane.showMessageDialog(submitButton, "The cache line is updated with data from memory table");
                                    colorTableCell(cacheTable2, integerIndex, 3, Color.WHITE);
                                    return;
                                }
                            }
                        }
                    });
                }
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFrame.dispose();
                CacheView cacheView = new CacheView();
            }
        });
        randomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String instruction = generateHexSmallerThan(Integer.parseInt(tfMemorySize.getText()));
                tfInstruction.setText(instruction);
            }
        });

    }
}
