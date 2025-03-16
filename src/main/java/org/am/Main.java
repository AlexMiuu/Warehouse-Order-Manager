package org.am;

import javax.swing.*;

import static org.am.single_point_access.SinglePointAccessController.*;

public class Main {
    public static void main(String[] args) {

        for(UIManager.LookAndFeelInfo lafInfo : UIManager.getInstalledLookAndFeels())
        {
            System.out.println(lafInfo.getClassName());
        }
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        getStartController().logic();
    }
}