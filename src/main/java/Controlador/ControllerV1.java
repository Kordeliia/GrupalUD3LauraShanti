package Controlador;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Modelo.Pedido;
import Modelo.PedidoDAO;

public class ControllerV1 implements Initializable {

    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnRefrescar;
    @FXML
    private TextField tfnombre;
    @FXML
    private TextField tfapellidos;
    @FXML
    private TableView<Pedido> tblPedidos;
    @FXML
    private TableColumn colnombre;
    @FXML
    private TableColumn colapellidos;
    @FXML
    private TableColumn coldni;
    @FXML
    private TableColumn colcorreo;
    @FXML
    private TableColumn colntelefono;
    @FXML
    private TableColumn colpedido;
    @FXML
    private TableColumn colobs;
    private ObservableList<Pedido> olpedido;

    

    public void initialize(URL url, ResourceBundle rb) {
    	olpedido = FXCollections.observableArrayList();
    	this.colnombre.setCellValueFactory(new PropertyValueFactory("nombre"));
    	this.colapellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
    	this.coldni.setCellValueFactory(new PropertyValueFactory("dni"));
    	this.colcorreo.setCellValueFactory(new PropertyValueFactory("correo"));
    	this.colntelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
    	this.colpedido.setCellValueFactory(new PropertyValueFactory("tpedido"));
    	this.colobs.setCellValueFactory(new PropertyValueFactory("obs"));
    	
    	
    }
    @FXML
    private void refrescar(ActionEvent event) { 
    	if(olpedido!=null) {
    		olpedido.clear();
    	}
    
    		ArrayList<Pedido> nuevospedidos = PedidoDAO.mostrarPedidos();
        	this.olpedido.addAll(nuevospedidos);
        	this.tblPedidos.refresh();

    }
   

    @FXML
    private void agregarPedido(ActionEvent event) {
        try {
            // Cargo la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/vista2.fxml"));
            // Cargo la ventana
            Parent root1 = loader.load();
            // Cojo el controlador
            ControllerV2 controlador = (ControllerV2) loader.getController();
            
            controlador.initAttributtes(olpedido);
            // Creo el Scene
            Scene scene = new Scene(root1);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            // cojo la persona devuelta
            Modelo.Pedido nuevopedido = controlador.getPedido();
            if (nuevopedido != null) {

                // Añado la persona
                this.olpedido.add(nuevopedido);
                this.tblPedidos.setItems(olpedido);

                // Refresco la tabla
                this.tblPedidos.refresh();
            }

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        
        }}
    @FXML
    private void refrescar() {
    	this.tblPedidos.refresh();
    	
    }
    
    @FXML
    	private void eliminar (ActionEvent event) {
    	Modelo.Pedido nuevopedido = this.tblPedidos.getSelectionModel().getSelectedItem();
    	if (nuevopedido==null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Ha ocurrido un error");
            alert.setContentText("Debes seleccionar un pedido para poder eliminarlo");
            alert.showAndWait();   		
    	} else {
    		this.olpedido.remove(nuevopedido);
    		this.tblPedidos.setItems(olpedido);
    		this.tblPedidos.refresh();
    		Modelo.PedidoDAO.borrarPedidoBBDD(nuevopedido);
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("La tarea se ha efectuado con exito");
            alert.setContentText("Se ha eliminado el pedido con exito");
            alert.showAndWait();  
    	}     }
    @FXML
    private void modificar() {
    	Modelo.Pedido nuevopedido = this.tblPedidos.getSelectionModel().getSelectedItem();

        if (nuevopedido == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debes seleccionar una persona para poder modificarla");
            alert.showAndWait();
        } else {
            try {
                // Cargo la vista
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/Vista/vista2.fxml"));

                // Cargo la ventana
                Parent root2 = loader2.load();

                // Cojo el controlador
                ControllerV2 controlador2 = loader2.getController();
                controlador2.initAttributtes(olpedido, nuevopedido);
                
                // Creo el Scene
                Scene scene2 = new Scene(root2);
                Stage stage2 = new Stage();
                stage2.initModality(Modality.APPLICATION_MODAL);
                stage2.setScene(scene2);
                stage2.showAndWait();

                // cojo la persona devuelta
                Modelo.Pedido pedidoauxiliar = controlador2.getPedido();
                Modelo.PedidoDAO.actualizarPedidoBBDD(nuevopedido);
                if (pedidoauxiliar != null) {
                    this.tblPedidos.refresh();
                }

            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }       }    } } 