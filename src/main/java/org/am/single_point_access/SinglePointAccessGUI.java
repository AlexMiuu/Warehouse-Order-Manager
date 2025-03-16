package org.am.single_point_access;

import javax.swing.*;

/**
 * The SinglePointAccessGUI class provides utility methods for creating and manipulating the graphical user interface of the application.
 *

 */
public class SinglePointAccessGUI {

    /**
     * The main JFrame object of the application.
     */
    private static final JFrame appFrame = initFrame();

    /**
     * Initializes and returns the main JFrame of the application.
     *
     * @return The initialized JFrame object.
     */
    private static JFrame initFrame() {
        JFrame frame = new JFrame();
        frame.setSize(1010, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    /**
     * Changes the current panel displayed in the main JFrame.
     *
     * @param panel      The panel to be displayed.
     * @param frameTitle The title of the JFrame.
     */
    public static void changePanel(JPanel panel, String frameTitle) {
        appFrame.setContentPane(panel);
        appFrame.setTitle(frameTitle);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    /**
     * Displays a message dialog box with the given message.
     *
     * @param message The message to be displayed in the dialog box.
     */
    public static void showDialogMessage(String message) {
        JOptionPane.showMessageDialog(appFrame, message);
    }
}