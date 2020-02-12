package edu.ib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ZapasyDAO as a class responsible for executing the SQL queries relative to blood unit's reserves inside java through DBUtil class connection;
 */

public class ZapasyDAO {

    private String login;
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    public ZapasyDAO(String login, DBUtil dbUtil, TextArea consoleTextArea) {
        this.login = login;
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    public ZapasyDAO(String login, DBUtil dbUtil) {
        this.login = login;
        this.dbUtil = dbUtil;
    }

    /**
     * @return - SQL table/view of all available blood unit's reserves;
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public ObservableList<Zapasy> showAllZapasy() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM sprawdzenie_zapasow_widok;";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<Zapasy> zapasyObservableList = getZapasyList(resultSet);
        return zapasyObservableList;

    }

    /**
     * @param rs
     * @return - set showAllZapasy()'s return to java variables;
     * @throws SQLException
     */

    private ObservableList<Zapasy> getZapasyList(ResultSet rs) throws SQLException {

        ObservableList<Zapasy> zapasyObservableList = FXCollections.observableArrayList();

        while (rs.next()) {

            Zapasy zapasy = new Zapasy();
            zapasy.setGrupa(rs.getString("grupa"));
            zapasy.setRh(rs.getString("Rh"));
            zapasy.setIlosc(rs.getInt("ilosc"));
            zapasy.setPotrzeba(rs.getString("potrzeba"));
            zapasy.setBank_id(rs.getInt("id_banku"));
            zapasyObservableList.add(zapasy);
        }

        return zapasyObservableList;
    }

}
