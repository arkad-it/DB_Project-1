package edu.ib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ZapasyWybraneDAO {

    private String login;
    private DBUtil dbUtil;
    private TextArea consoleTextArea;
    private String grupa_input;
    private String Rh_input;
    private BankFXController bankFXController;

    public ZapasyWybraneDAO(String grupa_input, String rh_input, DBUtil dbUtil) {
        this.grupa_input = grupa_input;
        this.Rh_input = rh_input;
        this.dbUtil = dbUtil;
    }

    public ZapasyWybraneDAO(String login, DBUtil dbUtil, TextArea consoleTextArea) {
        this.login = login;
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    public ZapasyWybraneDAO(String login, DBUtil dbUtil) {
        this.login = login;
        this.dbUtil = dbUtil;
    }


    public ObservableList<Zapasy> showAllZapasyWybrane() throws SQLException, ClassNotFoundException {

        String selectStmt = "call sprawdzenie_zapasow_wybrane('"+ grupa_input +"', '"+ Rh_input +"');";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<Zapasy> zapasyWybraneObservableList = getZapasyWybraneList(resultSet);
        return zapasyWybraneObservableList;

    }

    private ObservableList<Zapasy> getZapasyWybraneList(ResultSet rs) throws SQLException {

        ObservableList<Zapasy> zapasyWybraneObservableList = FXCollections.observableArrayList();

        while (rs.next()) {

            Zapasy zapasy = new Zapasy();
            zapasy.setGrupa(rs.getString("grupa"));
            zapasy.setRh(rs.getString("Rh"));
            zapasy.setIlosc(rs.getInt("ilosc"));
            zapasy.setPotrzeba(rs.getString("potrzeba"));
            zapasy.setBank_id(rs.getInt("id_banku"));
            zapasyWybraneObservableList.add(zapasy);
        }

        return zapasyWybraneObservableList;
    }

}
