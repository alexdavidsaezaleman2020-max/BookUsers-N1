package vista;

import modelo.Usuario;
import com.sha2cs.inputfile.InputFile;
import javax.swing.*;
import java.awt.Dimension;
import com.sha2cs.generatepdf.DocumentoPDF;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

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
    private final Usuario usuario;

    public FormDetalle(Usuario usuario) {
        this.usuario = usuario;

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
        btnGenerarPDF.addActionListener(e -> generarPDF());
    }


    public void mostrar() {

        JFrame ventana = new JFrame("BookUsers - Detalle del usuario - Victor Rodriguez y Alex Saez");
        ventana.setContentPane(panelPrincipal);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(750, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }


    private void generarPDF() {

        try {
            DocumentoPDF documento = new DocumentoPDF("Ficha de usuario - BookUsers");

            String rutaFoto = usuario.getRutaFoto();

            if (rutaFoto != null && !rutaFoto.isBlank()
                    && new File(rutaFoto).isFile()) {
                documento.imagen(rutaFoto);
            }

            documento.campo("ID", usuario.getId());
            documento.campo("Tipo de identificación", usuario.getTipoIdentificacion());
            documento.campo("Número de identificación", usuario.getNumeroIdentificacion());
            documento.campo("Primer nombre", usuario.getPrimerNombre());
            documento.campo("Segundo nombre", usuario.getSegundoNombre());
            documento.campo("Primer apellido", usuario.getPrimerApellido());
            documento.campo("Segundo apellido", usuario.getSegundoApellido());
            documento.campo("Username", usuario.getUsername());
            documento.campo("Teléfono", usuario.getTelefono());
            documento.campo("Correo", usuario.getCorreo());

            File pdf = documento.guardar("usuario-" + usuario.getId() + ".pdf");

            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "PDF generado correctamente:\n" + pdf.getAbsolutePath()
            );

            Desktop.getDesktop().open(pdf);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    panelPrincipal,
                    "No se pudo generar el PDF: " + e.getMessage()
            );
        }
    }

}
