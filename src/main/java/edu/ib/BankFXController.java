package edu.ib;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BankFXController {

    public String login;
    public String password;
    public DBUtil dbUtil;

    public JednostkiKrwiDAO jednostkiKrwiDAO;
    public ZapasyDAO zapasyDAO;

    public String grupa_input;
    public String Rh_input;
    public ZapasyWybraneDAO zapasyWybraneDAO = new ZapasyWybraneDAO(grupa_input, Rh_input);

    public ZapasyWybraneDAO getZapasyWybraneDAO() {
        return zapasyWybraneDAO;
    }

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
    private TableView<JednostkiKrwi> bank_tabela_jednostki;

    @FXML
    private TableColumn<JednostkiKrwi, Integer> bank_dawca_id;

    @FXML
    private TableColumn<JednostkiKrwi, Integer> bank_dawca_waga;

    @FXML
    private TableColumn<JednostkiKrwi, String> bank_dawca_data_urodzenia;

    @FXML
    private TableColumn<JednostkiKrwi, String> bank_dawca_imie_nazwisko;

    @FXML
    private TableColumn<JednostkiKrwi, Integer> bank_jednostka_krwi_id;

    @FXML
    private TableColumn<JednostkiKrwi, String> bank_jednostka_krwi_grupa;

    @FXML
    private TableColumn<JednostkiKrwi, String> bank_jednostka_krwi_Rh;

    @FXML
    private TableColumn<JednostkiKrwi, String> bank_jednostka_krwi_data_oddania;

    @FXML
    private TableColumn<JednostkiKrwi, String> bank_jednostka_krwi_status_var;

    @FXML
    private TableColumn<JednostkiKrwi, Integer> bank_tabela_jednostka_krwi_bank_id;

    @FXML
    private TableView<Zapasy> bank_tabela_zapasy;

    @FXML
    private TableColumn<Zapasy, String> bank_tabela_zapasy_grupa;

    @FXML
    private TableColumn<Zapasy, String> bank_tabela_zapasy_Rh;

    @FXML
    private TableColumn<Zapasy, Integer> bank_tabela_zapasy_ilosc;

    @FXML
    private TableColumn<Zapasy, String> bank_tabela_zapasy_potrzeba;

    @FXML
    private TableColumn<Zapasy, Integer> bank_tabela_zapasy_bank_id;

    @FXML
    private TableView<Zapasy> bank_tabela_zapasy_wybrane;

    @FXML
    private TableColumn<Zapasy, String> bank_tabela_zapasy_wybrane_grupa;

    @FXML
    private TableColumn<Zapasy, String> bank_tabela_zapasy_wybrane_Rh;

    @FXML
    private TableColumn<Zapasy, Integer> bank_tabela_zapasy_wybrane_ilosc;

    @FXML
    private TableColumn<Zapasy, String> bank_tabela_zapasy_wybrane_potrzeba;

    @FXML
    private TableColumn<Zapasy, Integer> bank_tabela_zapasy_wybrane_bank_id;

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
    void bank_pokazDaneButtonOnClick(ActionEvent event) throws SQLException, ClassNotFoundException {

        loadData();

    }

    @FXML
    void disconnectButtonOnClick(ActionEvent event) throws SQLException {

        dbUtil.dbDisconnect();

        Stage stage = (Stage) disconnectButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void showReservesButtonOnClick(ActionEvent event) throws SQLException, ClassNotFoundException {

        this.grupa_input = wyswietl_zapasy_jednostka_krwi_grupa.getText();
        this.Rh_input = wyswietl_zapasy_jednostka_krwi_Rh.getText();

        dbUtil.dbExecuteUpdate("call sprawdzenie_zapasow();");
        dbUtil.dbExecuteUpdate("call sprawdzenie_daty();");



        ObservableList<Zapasy> zapasyWybraneObservableList = null;
        try {
            zapasyWybraneObservableList = zapasyWybraneDAO.showAllZapasyWybrane();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateWybraneZapasy(zapasyWybraneObservableList);

    }

    @FXML
    void transfusionButtonOnClick(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (!do_transfuzji_jednostka_krwi_id.getText().isEmpty()) {

            String selectStmt = "call transfuzja(" + do_transfuzji_jednostka_krwi_id.getText() + ");";


            try {
                dbUtil.dbExecuteUpdate(selectStmt);
            } catch (SQLException e) {
                consoleTextArea.appendText("Error occurred while transfuzja(); Operation."  + "\n");
                throw e;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            consoleTextArea.appendText("Transfusion succeeded!" + "\n");
        }
        else {consoleTextArea.appendText("Transfusion failed! - all textfields must be filled with data." + "\n");}


        loadData();

    }

    private void loadData() throws SQLException, ClassNotFoundException {

        dbUtil.dbExecuteUpdate("call sprawdzenie_zapasow();");
        dbUtil.dbExecuteUpdate("call sprawdzenie_daty();");

        ObservableList<JednostkiKrwi> jednostkiKrwiWybraneObservableList = null;
        try {
            jednostkiKrwiWybraneObservableList = jednostkiKrwiDAO.showWybraneJednostki();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateJednostki(jednostkiKrwiWybraneObservableList);



        ObservableList<Zapasy> zapasyObservableList = null;
        try {
            zapasyObservableList = zapasyDAO.showAllZapasy();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        populateZapasy(zapasyObservableList);

    }

    private void populateZapasy(ObservableList<Zapasy> zapasyObservableList) {
        bank_tabela_zapasy.setItems(zapasyObservableList);
    }
    private void populateJednostki(ObservableList<JednostkiKrwi> jednostkiKrwiWybraneObservableList) {
        bank_tabela_jednostki.setItems(jednostkiKrwiWybraneObservableList);
    }
    private void populateWybraneZapasy(ObservableList<Zapasy> zapasyWybraneObservableList) {
        bank_tabela_zapasy_wybrane.setItems(zapasyWybraneObservableList);
    }

    @FXML
    void initialize() {
        assert wyswietl_zapasy_jednostka_krwi_grupa != null : "fx:id=\"wyswietl_zapasy_jednostka_krwi_grupa\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert wyswietl_zapasy_jednostka_krwi_Rh != null : "fx:id=\"wyswietl_zapasy_jednostka_krwi_Rh\" was not injected: check your FXML file 'bankFX.fxml'.";
        assert showReservesButton != null : "fx:id=\"showReservesButton\" was not injected: check your FXML file 'bankFX.fxml'.";
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
