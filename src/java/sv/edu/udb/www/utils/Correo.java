/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.utils;

import static com.lowagie.text.Annotation.URL;
import static com.lowagie.text.ElementTags.URL;
import static com.lowagie.text.html.HtmlTags.URL;
import static com.lowagie.text.pdf.PdfName.URL;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static javax.servlet.SessionTrackingMode.URL;

/**
 *
 * @author ivanm
 */
public class Correo {

    private String usuario;
    private String clave;
    private String destinatario;
    private String asunto;
    private String mensaje;

    public Correo() {
        this.usuario = "becas.fantel.sa.sv@gmail.com";
        this.clave = "proyectolp";
        this.destinatario = "";
        this.asunto = "";
        this.mensaje = "";

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean enviarCorreo() {

        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", usuario);
            p.setProperty("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(p, null);
            MimeMultipart cuerpo = new MimeMultipart();
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje, "text/html");
            cuerpo.addBodyPart(texto);

            MimeMessage correo = new MimeMessage(sesion);
            correo.setFrom(new InternetAddress(usuario));
            correo.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            correo.setSubject(asunto);
            correo.setContent(cuerpo);

            Transport t = sesion.getTransport("smtp");
            t.connect(usuario, clave);
            t.sendMessage(correo, correo.getAllRecipients());
            t.close();

            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean enviarCorreo(List<String> clientes) {

        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", usuario);
            p.setProperty("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(p, null);
            MimeMultipart cuerpo = new MimeMultipart();
            BodyPart texto = new MimeBodyPart();
            texto.setContent(mensaje, "text/html");
            cuerpo.addBodyPart(texto);

            MimeMessage correo = new MimeMessage(sesion);
            correo.setFrom(new InternetAddress(usuario));

            Address[] destinos = new Address[clientes.size()];//Aqui usamos el arreglo de destinatarios
            for (int i = 0; i < destinos.length; i++) {
                destinos[i] = new InternetAddress(clientes.get(i));
            }

            correo.addRecipients(Message.RecipientType.BCC, destinos);
            correo.setSubject(asunto);
            correo.setContent(cuerpo);

            Transport t = sesion.getTransport("smtp");
            t.connect(usuario, clave);
            t.sendMessage(correo, correo.getAllRecipients());
            t.close();

            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean enviarOfertas(List<String> clientes, String imagen) throws MalformedURLException {

        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", usuario);
            p.setProperty("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(p, null);

            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart();

            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = mensaje;
            messageBodyPart.setContent(htmlText, "text/html");
          

            // second part (the image)
            BodyPart messageBodyPart1 = new MimeBodyPart();
            DataSource fds = new URLDataSource(new URL(imagen));

            messageBodyPart1.setDataHandler(new DataHandler(fds));
            messageBodyPart1.setHeader("Content-ID", "<image>");

            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messageBodyPart1);

            MimeMessage correo = new MimeMessage(sesion);
            correo.setFrom(new InternetAddress(usuario));

            Address[] destinos = new Address[clientes.size()];//Aqui usamos el arreglo de destinatarios
            for (int i = 0; i < destinos.length; i++) {
                destinos[i] = new InternetAddress(clientes.get(i));
            }

            correo.addRecipients(Message.RecipientType.TO, destinos);
            correo.setSubject(asunto);
            correo.setContent(multipart);

            Transport t = sesion.getTransport("smtp");
            t.connect(usuario, clave);
            t.sendMessage(correo,destinos);
            t.close();

            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
}
