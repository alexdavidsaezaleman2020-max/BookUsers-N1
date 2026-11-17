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

        btnGuardar.addActionListener(e -> {

            if (validarCampos()) {

                Usuario nuevo = new Usuario(
                        menu.obtenerSiguienteId(),
                        cmbTipoId.getSelectedItem().toString(),
                        txtNumeroIdentificacion.getText().trim(),
                        txtPrimerNombre.getText().trim(),
                        txtSegundoNombre.getText().trim(),
                        txtPrimerApellido.getText().trim(),
                        txtSegundoApellido.getText().trim(),
                        new String(pwdPassword.getPassword()),
                        txtTelefono.getText().trim(),
                        txtCorreo.getText().trim(),
                        ""
                );

                usuarios.add(nuevo);

                JOptionPane.showMessageDialog(
                        panelPrincipal,
                        "Usuario registrado correctamente.\n"
                                + "ID: " + nuevo.getId()
                                + "\nUsername: " + nuevo.getUsername()
                );

                limpiarFormulario();
            }
        });

    }


    private void actualizarUsername() {

        String username = Usuario.generarUsername(
                txtPrimerNombre.getText(),
                txtPrimerApellido.getText(),
                txtSegundoApellido.getText()
        );

        lblUsername.setText(username);
    }


    private boolean validarCampos() {

        if (cmbTipoId.getSelectedItem() == null
                || txtNumeroIdentificacion.getText().trim().isEmpty()
                || txtPrimerNombre.getText().trim().isEmpty()
                || txtPrimerApellido.getText().trim().isEmpty()
                || new String(pwdPassword.getPassword()).trim().isEmpty()
                || txtTelefono.getText().trim().isEmpty()
                || txtCorreo.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "Complete todos los campos obligatorios.");

            return false;
        }

        String tipoId = cmbTipoId.getSelectedItem().toString();
        String numeroId = txtNumeroIdentificacion.getText().trim();

        if (!tipoId.startsWith("41 -")
                && !tipoId.startsWith("42 -")
                && !numeroId.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "El número de identificación debe contener solo dígitos.");

            return false;
        }

        if (!txtCorreo.getText().contains("@")) {

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "El correo electrónico debe contener @.");

            return false;
        }

        return true;
    }


    private void limpiarFormulario() {

        cmbTipoId.setSelectedIndex(0);
        txtNumeroIdentificacion.setText("");
        txtPrimerNombre.setText("");
        txtSegundoNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        pwdPassword.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

        lblUsername.setText("Se generará automáticamente");
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
