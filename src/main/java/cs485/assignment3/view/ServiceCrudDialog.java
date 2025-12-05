package cs485.assignment3.view;

import cs485.assignment3.controller.ServiceService;
import cs485.assignment3.model.entity.Service;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class ServiceCrudDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonCreate;
    private JButton buttonCancel;
    private JButton deleteButton;
    private JButton updateButton;
    private JList lstServiceUI;

    public ServiceCrudDialog() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonCreate);
        populateUI();

        buttonCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newClick();
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClick();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClick();
            }
        });
    }


    private void newClick() {
        NewServiceDialog dialog = new NewServiceDialog();   // create mode
        dialog.pack();
        dialog.setVisible(true);

        // refresh list after close
        populateUI();
    }

    private void onCancel() {
        dispose();
    }

    private void deleteClick() {
        try {
            ServiceService serviceService = new ServiceService();
            Service s = (Service) lstServiceUI.getSelectedValue();

            if (s != null) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete: " + s.getName() + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    serviceService.deleteService(s.getID());
                    lstServiceUI.clearSelection();
                    populateUI(); // reload from DB
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a service to delete.",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error deleting service: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void populateUI() {
        try {
            ServiceService serviceService = new ServiceService();
            List<Service> lstdata = serviceService.getAllServices();

            lstServiceUI.setListData(lstdata.toArray());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error loading services: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateClick() {
        try {
            Service selected = (Service) lstServiceUI.getSelectedValue();

            if (selected == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a service to update.",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // open dialog in EDIT mode, passing the selected service
            NewServiceDialog dialog = new NewServiceDialog(selected);
            dialog.pack();
            dialog.setVisible(true);

            // refresh after editing
            populateUI();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Error updating service: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ServiceCrudDialog dialog = new ServiceCrudDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
