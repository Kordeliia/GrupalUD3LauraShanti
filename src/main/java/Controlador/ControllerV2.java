package Controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Modelo.Pedido;

public class ControllerV2 implements Initializable {

    @FXML
    private TextField tfnombre;
    @FXML
    private TextField tfapellidos;
    @FXML
    private TextField tfdni;
    @FXML
    private TextField tfcorreo;
    @FXML
    private TextField tfntelefono;
    @FXML
    private ChoiceBox tftpedido;
    @FXML
    private TextField tfobs;
    @FXML
    private Button btnBORRAR;
    @FXML
    private Button btnEnviardatos;
    @FXML
    private Button btnSalir;

    private Pedido pedido;

    private ObservableList<Pedido> olpedido;
    boolean emailverfificado=false;


    public void initialize(URL url, ResourceBundle rb) {
    	tftpedido.setItems(FXCollections.observableArrayList("Desarrollo Web","Desarrollo Apps","Mockup", "Disenyo grafico"));

    }

    public void initAttributtes(ObservableList<Pedido> olpedido) {
        this.olpedido = olpedido;
    }
    public void initAttributtes(ObservableList<Pedido> olpedido, Modelo.Pedido ped) {
        this.olpedido = olpedido;
        this.pedido=ped;
        this.tfnombre.setText(ped.getNombre());
        this.tfapellidos.setText(ped.getApellidos());
        this.tfdni.setText(ped.getDni());
        this.tfcorreo.setText(ped.getCorreo());
        this.tfntelefono.setText(ped.getTelefono());
        this.tftpedido.setValue(ped.getTpedido());
        this.tfobs.setText(ped.getObs());
    }

    @FXML
    private void enviarpedidos(ActionEvent event) {
    	validarEmail();
		if(tfnombre.getText()=="")
		{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Ha ocurrido un error. El campo de Nombre esta vacio");
			a.show();
		}
		else if(tfapellidos.getText()=="")
		{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Ha ocurrido un error. El campo de Apellidos está vacío");
			a.show();
		}
		else if(tfdni.getText()=="")
		{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Ha ocurrido un error. El campo de DNI está vacío");
			a.show();
		}
		else if(tfcorreo.getText()=="")
		{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Ha ocurrido un error. El campo de Correo está vacío");
			a.show();
		}
		else if(tftpedido.getValue()=="")
		{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Ha ocurrido un error. El campo de Tipo de pedido electrónico está vacío");
			a.show();
		}
		else if(tfntelefono.getText()=="")
		{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Ha ocurrido un error. El campo de telefono está vacío");
			a.show();
		}
		else if(tfobs.getText()=="")
		{
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setContentText("Ha ocurrido un error. El campo de observaciones está vacío");
			a.show();
		} if(emailverfificado==false)
		{
			Alert a = new Alert(Alert.AlertType.ERROR); a.setContentText("Ha ocurrido un error. El campo de correo no tiene estructura nombre@example.es");
			a.show();
		}
		else {

        // Cojo los datos
        String nombre = this.tfnombre.getText();
        String apellidos = this.tfapellidos.getText();
        String dni = this.tfdni.getText();
        String correo = this.tfcorreo.getText();
        String telefono = this.tfntelefono.getText();
        String tpedido = (String) this.tftpedido.getValue();
        String obs = this.tfobs.getText();

        // Creo la persona
      Modelo.Pedido nuevopedido = new Modelo.Pedido(nombre, apellidos, dni, correo, telefono, tpedido, obs);
       if(!olpedido.contains(nuevopedido)) {
    	   
    	   if(this.pedido != null ) {
    		   
    		   this.pedido.setNombre(nombre);
    		   this.pedido.setApellidos(apellidos);
    		   this.pedido.setDni(dni);
    		   this.pedido.setCorreo(correo);
    		   this.pedido.setTelefono(telefono);
    		   this.pedido.setTpedido(tpedido);
    		   this.pedido.setObs(obs);
    		   
    		   
    		   
    	   } else {
    		   this.pedido=nuevopedido;
    		   Modelo.PedidoDAO.insertarPedidoBBDD(nuevopedido);
    		   
        	   Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setHeaderText(null);
               alert.setTitle("Éxito en la operación");
               alert.setContentText("Se ha añadido de forma correcta un nuevo pedido");
               alert.showAndWait();
    		   
    	   }
    	  
           Stage stage = (Stage) this.btnEnviardatos.getScene().getWindow();
           stage.close();
    	   } else {
    		   Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText(null);
               alert.setTitle("Ha ocurrido un error");
               alert.setContentText("Este pedido ya ha sido anteriormente creado");
               alert.showAndWait();
    	   
    	   }}
    }
    private boolean validarEmail() {
		 Pattern pattern = Pattern
				 .compile("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");
		 String email = tfcorreo.getText();
		 Matcher mather = pattern.matcher(email);
		 if (mather.find() == true) {
			 emailverfificado=true;
	        } else {
	        	emailverfificado=false;
	        }
		 
		return emailverfificado;
    }
    

    @FXML
    private void salir(ActionEvent event) throws IOException{
    	this.pedido=null;
        // Cerrar ventana
        Stage stage = (Stage) this.btnEnviardatos.getScene().getWindow();
        stage.close();

    }

    public Modelo.Pedido getPedido() {
        return pedido;
    }
    @FXML
    private void clearFields()
    {
    	tfnombre.clear();
    	tfapellidos.clear();
    	tfdni.clear();
    	tfcorreo.clear();
    	tfntelefono.clear();
    	tftpedido.setValue("");
    	tfobs.clear();
    	
    }
    
}
