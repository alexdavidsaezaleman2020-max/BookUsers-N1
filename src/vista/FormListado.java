package vista;

import modelo.Usuario;
import java.util.ArrayList;
import javax.swing.*;

public class FormListado {
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JList lstUsuarios;
    private JButton btnVerDetalle;

    public FormListado(ArrayList<Usuario> usuarios) {

        DefaultListModel<Usuario> modelo = new DefaultListModel<>();

        for (Usuario usuario : usuarios) {
            modelo.addElement(usuario);
        }

        lstUsuarios.setModel(modelo);

        btnVerDetalle.addActionListener(e -> {

            if (lstUsuarios.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(
                        panelPrincipal,
                        "Seleccione un usuario de la lista."
                );
                return;
            }

            Usuario seleccionado = (Usuario) lstUsuarios.getSelectedValue();

            FormDetalle detalle = new FormDetalle(seleccionado);
            detalle.mostrar();
        });

    }

    public void mostrar() {

        JFrame ventana = new JFrame("BookUsers - Listado de usuarios - Victor Rodriguez y Alex Saez");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(650, 450);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }


}
