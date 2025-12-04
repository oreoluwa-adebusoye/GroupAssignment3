package cs485.assignment3.view;

import cs485.assignment3.controller.ServiceService;
import cs485.assignment3.model.dao.ServiceDAO;
import cs485.assignment3.model.entity.Service;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class NewServiceDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtService;
    private JTextField txtType;
    private JTextField txtDescr;
    private JTextField txtPrice;
    private JTextField txtTime;

    public NewServiceDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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

    private void onOK() {
        ServiceService service = new ServiceService();
        String serviceName = txtService.getText();
        String type = txtType.getText();
        String descr = txtDescr.getText();
        Double price = Double.valueOf(txtPrice.getText());
        Integer duration = Integer.valueOf(txtTime.getText());

        try{
            service.createService(serviceName, type, descr, price, duration);
            dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        NewServiceDialog dialog = new NewServiceDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
