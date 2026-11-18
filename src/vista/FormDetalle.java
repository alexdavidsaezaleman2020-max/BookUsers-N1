package vista;

import modelo.Usuario;
import com.sha2cs.inputfile.InputFile;
import javax.swing.*;
import java.awt.Dimension;

public class FormDetalle {
    private JPanel panelPrincipal;
    private JLabel lblFoto;
    private JLabel lblId;
    private JLabel lblTipoIdentificacion;
    private JLabel lblNumeroIdentificacion;
    private JLabel lblPrimerNombre;
    private JLabel lblSegundoNombre;
    private JLabel lblPrimerApellido;
    private JLabel lblSegundoApellido;
    private JLabel lblUsername;
    private JLabel lblTelefono;
    private JLabel lblCorreo;
    private JButton btnGenerarPDF;

    public FormDetalle(Usuario usuario) {

        lblId.setText(String.valueOf(usuario.getId()));
        lblTipoIdentificacion.setText(usuario.getTipoIdentificacion());
        lblNumeroIdentificacion.setText(usuario.getNumeroIdentificacion());
        lblPrimerNombre.setText(usuario.getPrimerNombre());
        lblSegundoNombre.setText(usuario.getSegundoNombre());
        lblPrimerApellido.setText(usuario.getPrimerApellido());
        lblSegundoApellido.setText(usuario.getSegundoApellido());
        lblUsername.setText(usuario.getUsername());
        lblTelefono.setText(usuario.getTelefono());
        lblCorreo.setText(usuario.getCorreo());

        lblFoto.setPreferredSize(new Dimension(120, 120));
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);

        if (!InputFile.mostrar(lblFoto, usuario.getRutaFoto())) {
            InputFile.limpiar(lblFoto);
            lblFoto.setText("Sin fotografía");
        }
    }


    public void mostrar() {

        JFrame ventana = new JFrame("BookUsers - Detalle del usuario");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(750, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }


}
