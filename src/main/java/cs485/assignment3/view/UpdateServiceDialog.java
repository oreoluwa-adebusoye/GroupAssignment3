package cs485.assignment3.view;

import cs485.assignment3.controller.ServiceService;
import cs485.assignment3.model.entity.Service;

import javax.swing.*;
import java.awt.event.*;

public class UpdateServiceDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtService;
    private JTextField txtType;
    private JTextField txtDescr;
    private JTextField txtPrice;
    private JTextField txtTime;
    private static Service serv;

    public UpdateServiceDialog(Service s) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.serv = s;
        populate();

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
        try {
            serv.setName(txtService.getText());
            serv.setType(txtType.getText());
            serv.setDescription(txtDescr.getText());
            serv.setBasePrice(Double.valueOf(txtPrice.getText()));
            serv.setEstimatedDuration(Integer.valueOf(txtTime.getText()));

            service.updateService(serv);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void populate() {
        if (serv != null) {
            // NOTE: Ensure your Service entity has these getter methods
            txtService.setText(serv.getName());
            txtType.setText(serv.getType());
            txtDescr.setText(serv.getDescription());
            txtPrice.setText(String.valueOf(serv.getBasePrice()));
            txtTime.setText(String.valueOf(serv.getEstimatedDuration()));
        }
    }

    public static void main(String[] args) {
        UpdateServiceDialog dialog = new UpdateServiceDialog(serv);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
