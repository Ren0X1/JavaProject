package Launcher;
import javax.swing.*;
import java.io.IOException;
public class launchermain {
    public static void main(String[] args) {
        JFrame f=new JFrame("Launcher");
        JButton b=new JButton("Prueba");
        b.setBounds(140,12,95,30);
        f.add(b);
        f.setSize(400,100);
        f.setLayout(null);
        f.setVisible(true);
        b.addActionListener(e -> {
            try {
                Ejecutar("fivem.exe", "fivem://connect/localhost");
            } catch (IOException ioException) {
                System.out.println("Error al ejecutar");
            }
        });
    }
    private static void Ejecutar(String proceso, String parametro) throws IOException {
        ProcessBuilder p = new ProcessBuilder();
        p.command(proceso, parametro);
        p.start();
    }
}
