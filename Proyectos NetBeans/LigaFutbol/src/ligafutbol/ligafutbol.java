package ligafutbol;

import com.formdev.flatlaf.FlatOrangeLaf;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ligafutbol {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOrangeLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ligafutbol.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Ventana().setVisible(true);    
    }
}

