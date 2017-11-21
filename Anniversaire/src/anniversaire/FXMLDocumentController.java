/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anniversaire;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;


/**
 *
 * @author taiQui
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea text_printBirthday;
    @FXML
    private Button Btn_add;
    @FXML
    private Font x1;
    @FXML
    private Button BtnSee;
    @FXML
    private Button BtnDelete;
    
    
    Database _database = null;
    @FXML
    private Label label_Nom;
    @FXML
    private Label label_prenom;
    @FXML
    private Label label_daten_naissance;
    @FXML
    private Insets x2;
    @FXML
    private TextField text_nom;
    @FXML
    private TextField text_prenom;
    @FXML
    private TextField text_date_naissance;
    @FXML
    private Font x3;
    @FXML
    private Button BtnValidate;

    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        text_printBirthday.setVisible(true);
        text_printBirthday.setDisable(false);
        
        Btn_add.setVisible(true);
        Btn_add.setDisable(false);
        
        BtnSee.setVisible(true);
        BtnSee.setDisable(false);
        
        BtnDelete.setVisible(true);
        BtnDelete.setDisable(false);
        
        label_Nom.setVisible(false);
        label_Nom.setDisable(true);
        
        label_prenom.setVisible(false);
        label_prenom.setDisable(true);
        
        label_daten_naissance.setVisible(false);
        label_daten_naissance.setDisable(true);
        
        text_nom.setVisible(false);
        text_nom.setDisable(true);
        
        text_prenom.setVisible(false);
        text_prenom.setDisable(true);
        
        text_date_naissance.setVisible(false);
        text_date_naissance.setDisable(true);
        
        BtnValidate.setVisible(false);
        BtnValidate.setDisable(true);
        
        
        try {
            CreateDatabase();
            PrintBirthday();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void onClickBtnAdd(MouseEvent event) throws SQLException {
        
       
        if(Btn_add.getText().equals("Ajouter un anniversaire")){
            Btn_add.setText("Retour");
           

            text_printBirthday.setVisible(false);
            text_printBirthday.setDisable(true);

            Btn_add.setVisible(true);
            Btn_add.setDisable(false);

            BtnSee.setVisible(false);
            BtnSee.setDisable(true);

            BtnDelete.setVisible(false);
            BtnDelete.setDisable(true);

            label_Nom.setVisible(true);
            label_Nom.setDisable(false);

            label_prenom.setVisible(true);
            label_prenom.setDisable(false);

            label_daten_naissance.setVisible(true);
            label_daten_naissance.setDisable(false);

            text_nom.setVisible(true);
            text_nom.setDisable(false);

            text_prenom.setVisible(true);
            text_prenom.setDisable(false);

            text_date_naissance.setVisible(true);
            text_date_naissance.setDisable(false);

            BtnValidate.setVisible(true);
            BtnValidate.setDisable(false);


        } else if (Btn_add.getText().equals("Retour")) {
            Btn_add.setText("Ajouter un anniversaire");
            
            text_printBirthday.setVisible(true);
            text_printBirthday.setDisable(false);

            Btn_add.setVisible(true);
            Btn_add.setDisable(false);

            BtnSee.setVisible(true);
            BtnSee.setDisable(false);

            BtnDelete.setVisible(true);
            BtnDelete.setDisable(false);

            label_Nom.setVisible(false);
            label_Nom.setDisable(true);

            label_prenom.setVisible(false);
            label_prenom.setDisable(true);

            label_daten_naissance.setVisible(false);
            label_daten_naissance.setDisable(true);

            text_nom.setVisible(false);
            text_nom.setDisable(true);

            text_prenom.setVisible(false);
            text_prenom.setDisable(true);

            text_date_naissance.setVisible(false);
            text_date_naissance.setDisable(true);

            BtnValidate.setVisible(false);
            BtnValidate.setDisable(true);
            
            
        }
    
        
    }

    @FXML
    private void onClickBtnSee(MouseEvent event) throws SQLException {
        if( BtnSee.getText().equals("Voir les anniversaires")){
            
            BtnSee.setText("Retour");
            
            text_printBirthday.setVisible(true);
            text_printBirthday.setDisable(false);

            Btn_add.setVisible(false);
            Btn_add.setDisable(true);

            BtnSee.setVisible(true);
            BtnSee.setDisable(false);

            BtnDelete.setVisible(false);
            BtnDelete.setDisable(true);

            label_Nom.setVisible(false);
            label_Nom.setDisable(true);

            label_prenom.setVisible(false);
            label_prenom.setDisable(true);

            label_daten_naissance.setVisible(false);
            label_daten_naissance.setDisable(true);

            text_nom.setVisible(false);
            text_nom.setDisable(true);

            text_prenom.setVisible(false);
            text_prenom.setDisable(true);

            text_date_naissance.setVisible(false);
            text_date_naissance.setDisable(true);

            BtnValidate.setVisible(false);
            BtnValidate.setDisable(true);

            
            ArrayList<Anniversaire> liste = _database.getAllBirthday();
            
            text_printBirthday.setText("");
            if(liste.size() == 0){
                text_printBirthday.setText("Il y'a pas d'anniversaire enregistré");
            } else {
                for(int i = 0; i < liste.size() ; i++){
                    text_printBirthday.setText(liste.get(i).get_prenom() + "   "+liste.get(i).get_nom()+ "   "+liste.get(i).get_date_naissance());
                }                 
            }
           
            
        } else if( BtnSee.getText().equals("Retour")){
            
            BtnSee.setText("Voir les anniversaires");
            
            text_printBirthday.setVisible(true);
            text_printBirthday.setDisable(false);

            Btn_add.setVisible(true);
            Btn_add.setDisable(false);

            BtnSee.setVisible(true);
            BtnSee.setDisable(false);

            BtnDelete.setVisible(true);
            BtnDelete.setDisable(false);

            label_Nom.setVisible(false);
            label_Nom.setDisable(true);

            label_prenom.setVisible(false);
            label_prenom.setDisable(true);

            label_daten_naissance.setVisible(false);
            label_daten_naissance.setDisable(true);

            text_nom.setVisible(false);
            text_nom.setDisable(true);

            text_prenom.setVisible(false);
            text_prenom.setDisable(true);

            text_date_naissance.setVisible(false);
            text_date_naissance.setDisable(true);

            BtnValidate.setVisible(false);
            BtnValidate.setDisable(true);
        }
    }

    @FXML
    private void onClickBtnDelete(MouseEvent event) {
    }
    
    
    
    public void CreateDatabase() throws SQLException{
        System.out.println("Database Creation on Anniversaire");
        _database = new Database();
    }
    
    public void PrintBirthday() throws SQLException{
        java.util.Calendar cal = Calendar.getInstance();
        ArrayList<Anniversaire> liste = _database.getBirthday(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH));
        if(!liste.isEmpty()){
            for(int i = 0 ; i < liste.size(); i++){
                text_printBirthday.setText(text_printBirthday.getText()+'\n'+liste.get(i).get_prenom()+"   "+liste.get(i).get_nom());
            }
        } else {
            text_printBirthday.setText("Il n'y a aucun anniversaire aujourd'hui");
        }
        
    }

    @FXML
    private void onClickBtnValidate(MouseEvent event) throws SQLException {
        
           boolean Add = false;
        
           if(!TextIsEmpty()){
                if(DateIsOk()){
                    Add = true;
                } else {
                    newW("Erreur","Problème avec la date","Il y'a une erreur avec la date, le format dois etre aaaa-MM-jj -> Annee-mois-jour");
                }
            } else {
                newW("Erreur","Problème avec les champs","Il y'a une erreur, les champs ne sont pas remplis");
            }


            if(Add){
                Anniversaire a = new Anniversaire();
                a.setAnniversaire(text_nom.getText(), text_prenom.getText(), text_date_naissance.getText());
                _database.AddBirthday(a);
                newW("Succès","L'anniversaire à bien ete ajouté","");
            }
        
    }
    
    
    private boolean TextIsEmpty(){
        
        return(text_nom.getText().isEmpty() && text_prenom.getText().isEmpty() && text_date_naissance.getText().isEmpty());
        
    }
    
    private boolean DateIsOk(){
        String aux[] = text_date_naissance.getText().split("-");
        boolean continuer = true;
        //System.out.println("test 1 : "+!(aux[0].length() == 4) +" test 2 : "+!(aux[1].length() == 2) + " test 3 : "+!(aux[2].length() == 2));
        if(aux.length == 3){
                if(!(aux[0].length() == 4)){
                    continuer = false;
                }
                if(!(aux[1].length() == 2)){
                    continuer = false;
                }
                if(!(aux[2].length() == 2) ){
                    continuer = false;
                }
                if(Integer.parseInt(aux[0]) > 5000){
                    continuer = false;
                }
                
        } else {
            continuer = false;
        }
        
        if(Integer.parseInt(aux[0]) > 3000){
            continuer = false;
        }
        
        //System.out.println("continuer = "+continuer);
        return continuer;
    }
    
    private void newW(String titre, String header, String content){

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(titre);
                alert.setHeaderText(header);
                alert.setContentText(content);
                alert.initOwner(Btn_add.getScene().getWindow());
                alert.showAndWait();
    }
    
    
}
