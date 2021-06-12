package fivem.connect.launcher;

import com.formdev.flatlaf.FlatOrangeLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FiveMConnectLauncher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOrangeLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FiveMConnectLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Launcher().setVisible(true);    
    } 
}
