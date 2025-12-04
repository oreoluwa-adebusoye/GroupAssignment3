package cs485.assignment3.view;

import javax.swing.*;


public class MainFrame extends javax.swing.JFrame{

    public MainFrame() {
        super("Vet Clinic Service");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        createMenuBar();
    }

    public void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu mnuFile = new JMenu("File");
        JMenuItem mnuExit = new JMenuItem("Exit");
        mnuExit.addActionListener(e -> { System.exit(0); });
        mnuFile.add(mnuExit);

        menuBar.add(mnuFile);
        this.setJMenuBar(menuBar);

    }
}
