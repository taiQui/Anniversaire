/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anniversaire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author taiQui
 */
public class Anniversaire extends Application {

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    String _nom;
    String _prenom;
    String _date_naissance;
    String _id;
    
    
    
    public void setAnniversaire(String nom,String prenom, String date_naissance,String id){
        this._nom = nom;
        this._prenom = prenom;
        this._date_naissance = date_naissance;
        this._id = id;
    }
    
    public String get_nom(){
        return(_nom);
    }
    
    public String get_prenom(){
        return(_prenom);
    }
    
    public String get_date_naissance(){
        return(_date_naissance);
    }
    
    public String get_id(){
        return (_id);
    }















}
