package edu.ib;

import com.mysql.cj.conf.IntegerProperty;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Banki as a class responsible for MySQL data mapping (contains all of the blood bank table params);
 */

public class Banki {

    private SimpleIntegerProperty bank_id;
    private SimpleStringProperty bank_nazwa;
    private SimpleStringProperty bank_miasto;
    private SimpleStringProperty bank_kod_pocztowy;
    private SimpleStringProperty bank_ulica;
    private SimpleStringProperty bank_email;
    private SimpleStringProperty bank_tel;

    public Banki() {

        bank_id = new SimpleIntegerProperty();
        bank_nazwa = new SimpleStringProperty();
        bank_miasto = new SimpleStringProperty();
        bank_kod_pocztowy = new SimpleStringProperty();
        bank_ulica = new SimpleStringProperty();
        bank_email = new SimpleStringProperty();
        bank_tel = new SimpleStringProperty();

    }

    public int getBank_id() {
        return bank_id.get();
    }

    public SimpleIntegerProperty bank_idProperty() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id.set(bank_id);
    }

    public String getBank_nazwa() {
        return bank_nazwa.get();
    }

    public SimpleStringProperty bank_nazwaProperty() {
        return bank_nazwa;
    }

    public void setBank_nazwa(String bank_nazwa) {
        this.bank_nazwa.set(bank_nazwa);
    }

    public String getBank_miasto() {
        return bank_miasto.get();
    }

    public SimpleStringProperty bank_miastoProperty() {
        return bank_miasto;
    }

    public void setBank_miasto(String bank_miasto) {
        this.bank_miasto.set(bank_miasto);
    }

    public String getBank_kod_pocztowy() {
        return bank_kod_pocztowy.get();
    }

    public SimpleStringProperty bank_kod_pocztowyProperty() {
        return bank_kod_pocztowy;
    }

    public void setBank_kod_pocztowy(String bank_kod_pocztowy) {
        this.bank_kod_pocztowy.set(bank_kod_pocztowy);
    }

    public String getBank_ulica() {
        return bank_ulica.get();
    }

    public SimpleStringProperty bank_ulicaProperty() {
        return bank_ulica;
    }

    public void setBank_ulica(String bank_ulica) {
        this.bank_ulica.set(bank_ulica);
    }

    public String getBank_email() {
        return bank_email.get();
    }

    public SimpleStringProperty bank_emailProperty() {
        return bank_email;
    }

    public void setBank_email(String bank_email) {
        this.bank_email.set(bank_email);
    }

    public String getBank_tel() {
        return bank_tel.get();
    }

    public SimpleStringProperty bank_telProperty() {
        return bank_tel;
    }

    public void setBank_tel(String bank_tel) {
        this.bank_tel.set(bank_tel);
    }
}
