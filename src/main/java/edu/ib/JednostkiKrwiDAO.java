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

    public ObservableList<JednostkiKrwi> showWybraneJednostki() throws SQLException, ClassNotFoundException {

        String selectStmt = "call bank_jednostki(get_bank_id('"+login+"'));";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<JednostkiKrwi> jednostkiKrwiWybraneObservableList = getJednostkiList(resultSet);
        return jednostkiKrwiWybraneObservableList;

    }

    private ObservableList<JednostkiKrwi> getJednostkiList(ResultSet rs) throws SQLException {

        ObservableList<JednostkiKrwi> jednostkiKrwiObservableList = FXCollections.observableArrayList();

        while (rs.next()) {

            JednostkiKrwi wybraneJednostkiKrwi = new JednostkiKrwi();
            wybraneJednostkiKrwi.setDawca_id(rs.getInt("dawca_id"));
            wybraneJednostkiKrwi.setDawca_waga(rs.getFloat("dawca_waga"));
            wybraneJednostkiKrwi.setDawca_data_urodzenia(rs.getString("dawca_data_urodzenia"));
            wybraneJednostkiKrwi.setDawca_imie_nazwisko(rs.getString("dawca_imie_nazwisko"));
            wybraneJednostkiKrwi.setJednostka_krwi_id(rs.getInt("jednostka_krwi_id"));
            wybraneJednostkiKrwi.setJednostka_krwi_grupa(rs.getString("jednostka_krwi_grupa"));
            wybraneJednostkiKrwi.setJednostka_krwi_Rh(rs.getString("jednostka_krwi_Rh"));
            wybraneJednostkiKrwi.setJednostka_krwi_data_oddania(rs.getString("jednostka_krwi_data_oddania"));
            wybraneJednostkiKrwi.setJednostka_krwi_status_var(rs.getString("jednostka_krwi_status_var"));
            wybraneJednostkiKrwi.setBank_id(rs.getInt("bank_id"));
            jednostkiKrwiObservableList.add(wybraneJednostkiKrwi);
        }

        return jednostkiKrwiObservableList;
    }

}
