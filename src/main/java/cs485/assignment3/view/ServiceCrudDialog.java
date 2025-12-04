package cs485.assignment3.view;

import javax.swing.*;
import java.awt.event.*;

public class ServiceCrudDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCreate;
    private JButton buttonCancel;
    private JButton updateButton;
    private JButton deleteButton;

    public ServiceCrudDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCreate);

        buttonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createService();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void createService() {
        NewServiceDialog dialog = new NewServiceDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ServiceCrudDialog dialog = new ServiceCrudDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
