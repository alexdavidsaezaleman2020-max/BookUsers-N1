package vista;
import modelo.Usuario;
import java.util.ArrayList;

import javax.swing.*;

public class FormMenu {
    private JPanel panelPrincipal;
    private JButton btnRegistro;
    private JButton btnListado;
    private JButton btnCreditos;


    private final ArrayList<Usuario> usuarios = new ArrayList<>();
    private int siguienteId = 1;


    public FormMenu() {

        btnCreditos.addActionListener(e -> {
            FormCreditos creditos = new FormCreditos();
            creditos.mostrar();
        });

        btnRegistro.addActionListener(e -> {
            FormRegistro registro = new FormRegistro(usuarios, this);
            registro.mostrar();
        });

        btnListado.addActionListener(e -> {
            FormListado listado = new FormListado(usuarios);
            listado.mostrar();
        });

    }


    public int obtenerSiguienteId() {
        return siguienteId++;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame ventana = new JFrame("BookUsers - Victor Rodriguez y Alex Saez");

            ventana.setContentPane(new FormMenu().panelPrincipal);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ventana.setSize(450, 300);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
