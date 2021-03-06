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
import java.util.Date;
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
    
    
final String jan  = "Janvier" ;
final String  feb  ="Fevrier" ;
final String  mar ="Mars";
final String  avr ="Avril";
final String  mai ="Mai";
final String  jui ="Juin";
final String  juil ="Juillet";
final String  aout= "Aout";
final String sep ="Septembre";
final String oct ="Octobre";
final String  nov ="Novembre";
final String dec =  "Decembre";
    
    String tab[]={jan,feb,mar,avr,mai,jui,juil,aout,sep,oct,nov,dec};
    @FXML
    private Label labelInfo;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        text_printBirthday.setVisible(true);
        text_printBirthday.setDisable(false);
        text_printBirthday.setStyle("-fx-font: 20 arial;");
        
        Btn_add.setVisible(true);
        Btn_add.setDisable(false);
        
        BtnSee.setVisible(true);
        BtnSee.setDisable(false);
        
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
        
        labelInfo.setVisible(false);
        
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

             labelInfo.setVisible(true);

        } else if (Btn_add.getText().equals("Retour")) {
            Btn_add.setText("Ajouter un anniversaire");
            
            text_printBirthday.setVisible(true);
            text_printBirthday.setDisable(false);

            Btn_add.setVisible(true);
            Btn_add.setDisable(false);

            BtnSee.setVisible(true);
            BtnSee.setDisable(false);

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
            text_date_naissance.setText("");

            BtnValidate.setVisible(false);
            BtnValidate.setDisable(true);
            
             labelInfo.setVisible(false);
            
            PrintBirthday();
            
            
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

            label_Nom.setVisible(false);
            label_Nom.setDisable(true);

            label_prenom.setVisible(false);
            label_prenom.setDisable(true);

            label_daten_naissance.setVisible(true);
            label_daten_naissance.setDisable(false);
            label_daten_naissance.setText("Id");

            text_nom.setVisible(false);
            text_nom.setDisable(true);

            text_prenom.setVisible(false);
            text_prenom.setDisable(true);

            text_date_naissance.setVisible(true);
            text_date_naissance.setDisable(false);

            BtnValidate.setVisible(true);
            BtnValidate.setDisable(false);
            BtnValidate.setText("Supprimer");

             labelInfo.setVisible(true);
            
            ArrayList<Anniversaire> liste = _database.getAllBirthday();

            text_printBirthday.setText("");

            if(liste.size() == 0){
                text_printBirthday.setText("Il y'a pas d'anniversaire enregistré");
            } else {
                text_printBirthday.setText("Id");
                for(int i = 0; i < liste.size() ; i++){
                               
                    text_printBirthday.setText(text_printBirthday.getText()+'\n'+liste.get(i).get_id()+"             "+liste.get(i).get_prenom() + "         "+liste.get(i).get_nom()+ "         "+liste.get(i).get_date_naissance());
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

            label_Nom.setVisible(false);
            label_Nom.setDisable(true);

            label_prenom.setVisible(false);
            label_prenom.setDisable(true);

            label_daten_naissance.setVisible(false);
            label_daten_naissance.setDisable(true);
            label_daten_naissance.setText("Date naissance");

            text_nom.setVisible(false);
            text_nom.setDisable(true);

            text_prenom.setVisible(false);
            text_prenom.setDisable(true);
            
            text_date_naissance.setText("");
            text_date_naissance.setVisible(false);
            text_date_naissance.setDisable(true);
            
            BtnValidate.setText("Valider");
            BtnValidate.setVisible(false);
            BtnValidate.setDisable(true);
            
             labelInfo.setVisible(false);
            
            PrintBirthday(); 
        }
    }


    
    
    public void CreateDatabase() throws SQLException{
        System.out.println("Database Creation on Anniversaire");
        _database = new Database();
    }
    
    public void PrintBirthday() throws SQLException{
        //text_printBirthday.setStyle("-fx-font: 30 arial;");
        java.util.Calendar cal = Calendar.getInstance();
        System.out.println("mois : "+ (Calendar.getInstance().get(Calendar.MONTH)+1) + "    jour : "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        ArrayList<Anniversaire> liste = _database.getBirthday((Calendar.getInstance().get(Calendar.MONTH)+1),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        if(!liste.isEmpty()){
            text_printBirthday.setText("C'est l'anniversaire de : ");
            for(int i = 0 ; i < liste.size(); i++){
                java.util.Calendar cal1 = Convertisseur.stringToCalendar(liste.get(i).get_date_naissance(),"yyyy-MM-dd");
                java.util.Calendar cal_base = Calendar.getInstance();
                
                long timeBetween = cal_base.getTime().getTime() - cal1.getTime().getTime();
                double years = timeBetween / 3.156e+10;
                int age = (int)Math.floor(years);
                //System.out.println("age : "+age);
                
                text_printBirthday.setText(text_printBirthday.getText()+'\n'+liste.get(i).get_prenom()+"   "+liste.get(i).get_nom() +" ça lui fais "+ age + "ans");
            }
        } else {
            text_printBirthday.setText("Il n'y a aucun anniversaire aujourd'hui");
            if(_database.NextBirthday()!= null)
                text_printBirthday.setText(text_printBirthday.getText() + "\n\n\n" + "Le prochain anniversaire est celui de "+_database.NextBirthday().get_prenom()+" "+_database.NextBirthday().get_nom() +" le "+ Convertisseur.stringToCalendar(_database.NextBirthday().get_date_naissance(), "yyyy-MM-dd").get(Calendar.DAY_OF_MONTH) + " " + tab[(Convertisseur.stringToCalendar(_database.NextBirthday().get_date_naissance(), "yyyy-MM-dd").get(Calendar.MONTH)+1)-1]);
        }
        
    }

    @FXML
    private void onClickBtnValidate(MouseEvent event) throws SQLException {
        
        if(BtnValidate.getText().equals("Valider")){
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
                a.setAnniversaire(text_nom.getText(), text_prenom.getText(), text_date_naissance.getText(),"");
                _database.AddBirthday(a);
                newW("Succès","L'anniversaire à bien ete ajouté","");
            }
        } else if (BtnValidate.getText().equals("Supprimer")){
            if(!_database.IdIsOk(text_date_naissance.getText())){
                _database.DeleteBirthday(text_date_naissance.getText());
                newW("Reussis","La suppresions c'est bien passé","");
            } else {
                newW("Erreur","Rechercher dans la base de donné","Il n'y a pas de personne avec cette ID");
            }
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
