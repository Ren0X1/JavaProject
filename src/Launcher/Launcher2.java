package Launcher;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

public class Launcher2 {
    private JPanel panelPrincipal;
    private JButton JUGARButton;
    private JProgressBar progressBar1;
    private JLabel envidiaosRPLauncherLabel;
    private JLabel estadoLabel;
    private JLabel ONLINELabel;
    public Launcher2() {
        JUGARButton.addActionListener(e -> {
            try {
                Ejecutar("FiveM.exe", "fivem://connect/localhost");
            } catch (IOException ioException) {
                System.out.println("Error al ejecutar");
            }
        });
        JUGARButton.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (progressBar1.getValue()!=100) {
                    for(int i=0;i!=progressBar1.getMaximum()+1;i++) {
                        progressBar1.setValue(i);
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("EnvidiaosRP");
        frame.setContentPane(new Launcher2().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private static void Ejecutar(String proceso, String parametro) throws IOException {
        ProcessBuilder p = new ProcessBuilder();
        p.command(proceso, parametro);
        p.start();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
