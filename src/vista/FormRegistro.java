package vista;

import modelo.Usuario;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FormRegistro {
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JComboBox cmbTipoId;
    private JTextField txtNumeroIdentificacion;
    private JTextField txtPrimerNombre;
    private JTextField txtSegundoNombre;
    private JTextField txtPrimerApellido;
    private JTextField txtSegundoApellido;
    private JLabel lblUsername;
    private JPasswordField pwdPassword;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton btnCargarFoto;
    private JLabel lblFoto;
    private JButton btnGuardar;

    private final ArrayList<Usuario> usuarios;
    private final FormMenu menu;


    public FormRegistro(ArrayList<Usuario> usuarios, FormMenu menu) {

        this.usuarios = usuarios;
        this.menu = menu;

        DocumentListener listener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarUsername();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarUsername();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarUsername();
            }
        };

        txtPrimerNombre.getDocument().addDocumentListener(listener);
        txtPrimerApellido.getDocument().addDocumentListener(listener);
        txtSegundoApellido.getDocument().addDocumentListener(listener);

        actualizarUsername();
    }


    private void actualizarUsername() {

        String username = Usuario.generarUsername(
                txtPrimerNombre.getText(),
                txtPrimerApellido.getText(),
                txtSegundoApellido.getText()
        );

        lblUsername.setText(username);
    }


    public void mostrar() {

        JFrame ventana = new JFrame("BookUsers - Registro de usuarios");

        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ventana.setSize(950, 700);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

}
