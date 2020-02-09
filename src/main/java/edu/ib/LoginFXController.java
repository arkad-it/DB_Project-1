/**
 * Sample Skeleton for 'loginFX.fxml' Controller Class
 */

package edu.ib;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginFXController {

    public DBUtil dbUtil;
    public String password;
    public String login;
    private BankiDAO bankiDAO;
    private JednostkiKrwiDAO jednostkiKrwiDAO;
    private ZapasyDAO zapasyDAO;

    public BankiDAO getBankiDAO() {
        return bankiDAO;
    }

    public JednostkiKrwiDAO getJednostkiKrwiDAO(){
        return jednostkiKrwiDAO;
    }

    public ZapasyDAO getZapasyDAO(){
        return zapasyDAO;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="userEditText"
    private TextField userEditText; // Value injected by FXMLLoader

    @FXML // fx:id="passwordEditText"
    private PasswordField passwordEditText; // Value injected by FXMLLoader

    @FXML // fx:id="connectButton"
    private Button connectButton; // Value injected by FXMLLoader

    @FXML // fx:id="logTextView"
    private TextArea logTextView; // Value injected by FXMLLoader

    @FXML
    void connectButtonOnClick(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        dbUtil = new DBUtil(userEditText.getText(), passwordEditText.getText(), logTextView);
        dbUtil.dbConnect();
        logTextView.appendText("Access granted for user: \"" + userEditText.getText() + "\"." + "\n");
        login = userEditText.getText();
        password = passwordEditText.getText();

        bankiDAO = new BankiDAO(login,dbUtil);
        jednostkiKrwiDAO= new JednostkiKrwiDAO(login, dbUtil);
        zapasyDAO= new ZapasyDAO(login, dbUtil);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/centrumFX.fxml"));


        Parent root = loader.load();

        // centrum modal
        CentrumFXController centrumFXController = loader.getController();
        Stage stageCentrum = new Stage();
        stageCentrum.setScene(new Scene(root));
        stageCentrum.setTitle("centrum interface");
        stageCentrum.initModality(Modality.WINDOW_MODAL);
        centrumFXController.dbUtil = this.dbUtil;
        centrumFXController.login = this.login;
        centrumFXController.bankiDAO = this.bankiDAO;
        centrumFXController.jednostkiKrwiDAO = this.jednostkiKrwiDAO;
        centrumFXController.zapasyDAO = this.zapasyDAO;
        stageCentrum.show();



    }

    public DBUtil getDbUtil() {
        return dbUtil;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert userEditText != null : "fx:id=\"userEditText\" was not injected: check your FXML file 'loginFX.fxml'.";
        assert passwordEditText != null : "fx:id=\"passwordEditText\" was not injected: check your FXML file 'loginFX.fxml'.";
        assert connectButton != null : "fx:id=\"connectButton\" was not injected: check your FXML file 'loginFX.fxml'.";
        assert logTextView != null : "fx:id=\"logTextView\" was not injected: check your FXML file 'loginFX.fxml'.";

    }

}
