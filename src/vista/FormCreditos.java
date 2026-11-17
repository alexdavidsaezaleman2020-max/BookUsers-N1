package vista;

import javax.swing.*;

public class FormCreditos {
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblIntegrante1;
    private JLabel lblNombre1;
    private JLabel lblCodigo1;
    private JLabel lblCorreo1;
    private JLabel lblCorreo2;
    private JLabel lblCodigo2;
    private JLabel lblNombre2;
    private JLabel lblIntegrante2;


    public void mostrar() {

        JFrame ventana = new JFrame("BookUsers - Créditos");

        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ventana.setSize(650, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }


}
