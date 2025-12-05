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
    private JButton updateButton;
    private JButton deleteButton;
    private JList listServiceUI;

    public ServiceCrudDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCreate);

        populate();

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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteService();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateService();
            }
        });
    }

    private void updateService() {
        try {
            Service s = (Service) listServiceUI.getSelectedValue();
            if (s != null) {
                UpdateServiceDialog dialog = new UpdateServiceDialog(s);
                dialog.pack();
                dialog.setVisible(true);
                populate();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //deletes the service row
    private void deleteService() {
        try{
            ServiceService service = new ServiceService();
            Service s = (Service) listServiceUI.getSelectedValue();
            if (s != null) {
                service.deleteService(s.getID());
                populate(); //fetches everything again from DB - refresh
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //goes to create service dialog
    private void createService() {
        NewServiceDialog dialog = new NewServiceDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void onCancel() {
        dispose();
    }

    public void populate(){
        ServiceService service = new ServiceService();
        try{
            List<Service> listdata = service.getAllServices();
            listServiceUI.setListData(listdata.toArray());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ServiceCrudDialog dialog = new ServiceCrudDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
