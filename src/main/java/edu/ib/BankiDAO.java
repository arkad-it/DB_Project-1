package edu.ib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BankiDAO as a class responsible for executing the SQL queries relative to blood banks inside java through DBUtil class connection;
 */

public class BankiDAO {

    private String login;
    private DBUtil dbUtil;
    private TextArea consoleTextArea;

    /**
     * @param login - database passed username;
     * @param dbUtil - a DB connection required to be passed to the DAO class in order to keep the availability of the DB data;
     * @param consoleTextArea - nfo/error terminal;
     */

    public BankiDAO(String login, DBUtil dbUtil, TextArea consoleTextArea) {
        this.login = login;
        this.dbUtil = dbUtil;
        this.consoleTextArea = consoleTextArea;
    }

    public BankiDAO(String login, DBUtil dbUtil) {
        this.login = login;
        this.dbUtil = dbUtil;
    }

    /**
     * @return - SQL table/view;
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public ObservableList<Banki> showAllBanki() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM bank_widok;";
        ResultSet resultSet = dbUtil.dbExecuteQuery(selectStmt);
        ObservableList<Banki> bankiObservableList = getBankiList(resultSet);
        return bankiObservableList;

    }

    /**
     * @param rs
     * @return - set showAllBanki()'s return to java variables;
     * @throws SQLException
     */

    private ObservableList<Banki> getBankiList(ResultSet rs) throws SQLException {

        ObservableList<Banki> bankiObservableList = FXCollections.observableArrayList();

        while (rs.next()) {

            Banki bank = new Banki();
            bank.setBank_id(rs.getInt("bank_id"));
            bank.setBank_nazwa(rs.getString("bank_nazwa"));
            bank.setBank_miasto(rs.getString("bank_miasto"));
            bank.setBank_kod_pocztowy(rs.getString("bank_kod_pocztowy"));
            bank.setBank_ulica(rs.getString("bank_ulica"));
            bank.setBank_email(rs.getString("bank_email"));
            bank.setBank_tel(rs.getString("bank_tel"));
            bankiObservableList.add(bank);
        }

        return bankiObservableList;
    }

}
