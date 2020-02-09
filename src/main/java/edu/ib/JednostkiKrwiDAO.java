package edu.ib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JednostkiKrwiDAO {

    private String login;
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public JednostkiKrwiDAO(String login, DBUtil dbUtil, TextArea consoleTextArea) {
        this.login = login;
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    public JednostkiKrwiDAO(String login, DBUtil dbUtil) {
        this.login = login;
        this.dbUtil = dbUtil;
    }

    public ObservableList<JednostkiKrwi> showAllJednostki() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM dawca_jednostka_krwi_status_widok;";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<JednostkiKrwi> jednostkiKrwiObservableList = getJednostkiList(resultSet);
        return jednostkiKrwiObservableList;

    }

    private ObservableList<JednostkiKrwi> getJednostkiList(ResultSet rs) throws SQLException {

        ObservableList<JednostkiKrwi> jednostkiKrwiObservableList = FXCollections.observableArrayList();

        while (rs.next()) {

            JednostkiKrwi jednostkiKrwi = new JednostkiKrwi();
            jednostkiKrwi.setDawca_id(rs.getInt("dawca_id"));
            jednostkiKrwi.setDawca_waga(rs.getFloat("dawca_waga"));
            jednostkiKrwi.setDawca_data_urodzenia(rs.getString("dawca_data_urodzenia"));
            jednostkiKrwi.setDawca_imie_nazwisko(rs.getString("dawca_imie_nazwisko"));
            jednostkiKrwi.setJednostka_krwi_id(rs.getInt("jednostka_krwi_id"));
            jednostkiKrwi.setJednostka_krwi_grupa(rs.getString("jednostka_krwi_grupa"));
            jednostkiKrwi.setJednostka_krwi_Rh(rs.getString("jednostka_krwi_Rh"));
            jednostkiKrwi.setJednostka_krwi_data_oddania(rs.getString("jednostka_krwi_data_oddania"));
            jednostkiKrwi.setJednostka_krwi_status_var(rs.getString("jednostka_krwi_status_var"));
            jednostkiKrwi.setBank_id(rs.getInt("bank_id"));
            jednostkiKrwiObservableList.add(jednostkiKrwi);
        }

        return jednostkiKrwiObservableList;
    }

}
