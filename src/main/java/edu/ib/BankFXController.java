package edu.ib;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BankFXController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField wyswietl_zapasy_jednostka_krwi_grupa;

    @FXML
    private TextField wyswietl_zapasy_jednostka_krwi_Rh;

    @FXML
    private Button showReservesButton;

    @FXML
    private TextField dodaj_jednostke_krwi_jednostka_krwi_id;

    @FXML
    private TextField dodaj_jednostke_krwi_dawca_id;

    @FXML
    private TextField dodaj_jednostke_krwi_jednostka_krwi_Rh;

    @FXML
    private TextField dodaj_jednostke_krwi_jednostka_krwi_grupa;

    @FXML
    private TextField dodaj_jednostke_krwi_jednostka_krwi_data_oddania;

    @FXML
    private TextField dodaj_jednostke_krwi_bank_id;

    @FXML
    private Button addBloodUnitButton;

    @FXML
    private TableView<?> bank_tabela_jednostki;

    @FXML
    private TableColumn<?, ?> bank_dawca_id;

    @FXML
    private TableColumn<?, ?> bank_dawca_waga;

    @FXML
    private TableColumn<?, ?> bank_dawca_data_urodzenia;

    @FXML
    private TableColumn<?, ?> bank_dawca_imie_nazwisko;

    @FXML
    private TableColumn<?, ?> bank_jednostka_krwi_id;

    @FXML
    private TableColumn<?, ?> bank_jednostka_krwi_grupa;

    @FXML
    private TableColumn<?, ?> bank_jednostka_krwi_Rh;

    @FXML
    private TableColumn<?, ?> bank_jednostka_krwi_data_oddania;

    @FXML
    private TableColumn<?, ?> bank_jednostka_krwi_status_var;

    @FXML
    private TableColumn<?, ?> bank_tabela_jednostka_krwi_bank_id;

    @FXML
    private TableView<?> bank_tabela_zapasy;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_grupa;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_Rh;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_ilosc;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_potrzeba;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_bank_id;

    @FXML
    private TableView<?> bank_tabela_zapasy_wybrane;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_wybrane_grupa;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_wybrane_Rh;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_wybrane_ilosc;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_wybrane_potrzeba;

    @FXML
    private TableColumn<?, ?> bank_tabela_zapasy_wybrane_bank_id;

    @FXML
    private TextField do_transfuzji_jednostka_krwi_id;

    @FXML
    private Button transfusionButton;

    @FXML
    private TextArea consoleTextArea;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button bank_pokazDaneButton;

    @FXML
    void addBloodUnitButtonOnClick(ActionEvent event) {

    }

    @FXML
    void bank_pokazDaneButtonOnClick(ActionEvent event) {

    }

    @FXML
    void disconnectButtonOnClick(ActionEvent event) {

    }

    @FXML
    void showReservesButtonOnClick(ActionEvent event) {

    }

    @FXML
    void transfusionButtonOnClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert wyswietl_zapasy_jednostka_krwi_grupa != null : "fx:id=\"wyswietl_zapasy_jednostka_krwi_grupa\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert wyswietl_zapasy_jednostka_krwi_Rh != null : "fx:id=\"wyswietl_zapasy_jednostka_krwi_Rh\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert showReservesButton != null : "fx:id=\"showReservesButton\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert dodaj_jednostke_krwi_jednostka_krwi_id != null : "fx:id=\"dodaj_jednostke_krwi_jednostka_krwi_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert dodaj_jednostke_krwi_dawca_id != null : "fx:id=\"dodaj_jednostke_krwi_dawca_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert dodaj_jednostke_krwi_jednostka_krwi_Rh != null : "fx:id=\"dodaj_jednostke_krwi_jednostka_krwi_Rh\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert dodaj_jednostke_krwi_jednostka_krwi_grupa != null : "fx:id=\"dodaj_jednostke_krwi_jednostka_krwi_grupa\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert dodaj_jednostke_krwi_jednostka_krwi_data_oddania != null : "fx:id=\"dodaj_jednostke_krwi_jednostka_krwi_data_oddania\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert dodaj_jednostke_krwi_bank_id != null : "fx:id=\"dodaj_jednostke_krwi_bank_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert addBloodUnitButton != null : "fx:id=\"addBloodUnitButton\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_jednostki != null : "fx:id=\"bank_tabela_jednostki\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_dawca_id != null : "fx:id=\"bank_dawca_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_dawca_waga != null : "fx:id=\"bank_dawca_waga\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_dawca_data_urodzenia != null : "fx:id=\"bank_dawca_data_urodzenia\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_dawca_imie_nazwisko != null : "fx:id=\"bank_dawca_imie_nazwisko\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_jednostka_krwi_id != null : "fx:id=\"bank_jednostka_krwi_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_jednostka_krwi_grupa != null : "fx:id=\"bank_jednostka_krwi_grupa\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_jednostka_krwi_Rh != null : "fx:id=\"bank_jednostka_krwi_Rh\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_jednostka_krwi_data_oddania != null : "fx:id=\"bank_jednostka_krwi_data_oddania\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_jednostka_krwi_status_var != null : "fx:id=\"bank_jednostka_krwi_status_var\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_jednostka_krwi_bank_id != null : "fx:id=\"bank_tabela_jednostka_krwi_bank_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy != null : "fx:id=\"bank_tabela_zapasy\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_grupa != null : "fx:id=\"bank_tabela_zapasy_grupa\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_Rh != null : "fx:id=\"bank_tabela_zapasy_Rh\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_ilosc != null : "fx:id=\"bank_tabela_zapasy_ilosc\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_potrzeba != null : "fx:id=\"bank_tabela_zapasy_potrzeba\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_bank_id != null : "fx:id=\"bank_tabela_zapasy_bank_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_wybrane != null : "fx:id=\"bank_tabela_zapasy_wybrane\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_wybrane_grupa != null : "fx:id=\"bank_tabela_zapasy_wybrane_grupa\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_wybrane_Rh != null : "fx:id=\"bank_tabela_zapasy_wybrane_Rh\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_wybrane_ilosc != null : "fx:id=\"bank_tabela_zapasy_wybrane_ilosc\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_wybrane_potrzeba != null : "fx:id=\"bank_tabela_zapasy_wybrane_potrzeba\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_tabela_zapasy_wybrane_bank_id != null : "fx:id=\"bank_tabela_zapasy_wybrane_bank_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert do_transfuzji_jednostka_krwi_id != null : "fx:id=\"do_transfuzji_jednostka_krwi_id\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert transfusionButton != null : "fx:id=\"transfusionButton\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert consoleTextArea != null : "fx:id=\"consoleTextArea\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert disconnectButton != null : "fx:id=\"disconnectButton\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert bank_pokazDaneButton != null : "fx:id=\"bank_pokazDaneButton\" was not injected: check your FXML file 'bankFX.fxml'.";

    }
}
