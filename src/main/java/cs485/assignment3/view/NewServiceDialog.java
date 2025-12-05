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

    private Service service;   // null = create, not null = edit
    private boolean isEdit = false;

    // CREATE mode
    public NewServiceDialog() {
        this(null);
    }

    // EDIT mode
    public NewServiceDialog(Service existingService) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.service = existingService;
        this.isEdit = (existingService != null);

        if (isEdit) {
            txtService.setText(service.getName());
            txtType.setText(service.getType());
            txtDescr.setText(service.getDescription());
            txtPrice.setText(String.valueOf(service.getBasePrice()));
            txtTime.setText(String.valueOf(service.getEstimatedDuration()));
        }

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
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    // create or update
    private void onOK() {
        ServiceService serviceService = new ServiceService();
        String serviceName = txtService.getText();
        String type = txtType.getText();
        String descr = txtDescr.getText();
        Double price = Double.valueOf(txtPrice.getText());
        Integer duration = Integer.valueOf(txtTime.getText());

        try {
            if (isEdit) {
                // update existing record
                service.setName(serviceName);
                service.setType(type);
                service.setDescription(descr);
                service.setBasePrice(price);
                service.setEstimatedDuration(duration);

                serviceService.updateService(service);
            } else {
                // create new record
                serviceService.createService(serviceName, type, descr, price, duration);
            }
            dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }

    public static void main(String[] args) {
        NewServiceDialog dialog = new NewServiceDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}