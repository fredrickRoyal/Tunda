/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 *
 * @author royal
 */
public class Welcome {

    public static JFrame mainWindow;
    public static JMenuBar menuBar;
    private static JMenu fileMenu;
    private static JMenu settingsMenu;
    public static JMenuItem serverSetting;
    public static JMenuItem discountSetting;
    static String title = "TUNDA POS [Version 1.0]";
    public static int uniformWidth;
    public static int uniformHeight;

    private static class MenuBarActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dim = toolkit.getScreenSize();
            int width = dim.width;
            int height = dim.height;
            String command = ev.getActionCommand();
            if (command.equalsIgnoreCase("Server Settings")) {
                ServerSettingDialog serverSettingDialog = new ServerSettingDialog(Welcome.mainWindow, true);
                serverSettingDialog.setTitle("Server Settings");
                serverSettingDialog.setLocation(width / 4, height / 5);
                serverSettingDialog.setVisible(true);
            } else if (command.equalsIgnoreCase("Discount Settings")) {
                DiscountSettingsDialog discountSettingsDialog = new DiscountSettingsDialog(Welcome.mainWindow, true);
                discountSettingsDialog.setTitle("Set Discount");
                discountSettingsDialog.setLocation(width / 4, height / 5);
                discountSettingsDialog.setVisible(true);
            }
        }
    }

    Welcome() {
        //new Controller().createTableIdAdmin();
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
            //UIManager.setLookAndFeel (WebLookAndFeel.class.getCanonicalName ());
            //UIManager.put("Synthetica.dialog.icon.enabled", true);

            //UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel");

            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");


            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        serverSetting = new JMenuItem("Server Settings");
        discountSetting = new JMenuItem("Discount Settings");
        serverSetting.addActionListener(new MenuBarActionListener());
        discountSetting.addActionListener(new MenuBarActionListener());
        settingsMenu = new JMenu("Settings");
        settingsMenu.add(serverSetting);
        settingsMenu.add(discountSetting);
        menuBar.add(fileMenu);
        menuBar.add(settingsMenu);


        //menuBar.setBackground(Color.);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dim = toolkit.getScreenSize();
        int width = dim.width;
        int height = dim.height;
        mainWindow = new JFrame(title);

        mainWindow.setIconImage(getResourceImage("logo.png"));
        mainWindow.setJMenuBar(menuBar);
        mainWindow.setBackground(new Color(212, 201, 201));
        mainWindow.setContentPane(new LoginWindow());

        int variation = (int) (0.05 * height);
        uniformWidth = ((int) ((96.40625 / 100) * width));
        uniformHeight = height - variation;
        //height - variation
        mainWindow.setSize(width, uniformHeight);
        //mainWindow.setLocation(((int)((1.5625/100)*width)), 0);
        mainWindow.setLocation(0, 0);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(true);


    }

    public static void main(String[] args) {
        new Welcome();
        new vpsposserver.VPSPOSSEVER();


    }

    public Image getResourceImage(String fileName) {
        String imageDirectory = "images/";
        URL imgURL = getClass().getResource(imageDirectory + fileName);
        //JOptionPane.showMessageDialog(null, imgURL);
        Image image = null;
        try {
            image = ImageIO.read(imgURL);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return image;
    }
}
