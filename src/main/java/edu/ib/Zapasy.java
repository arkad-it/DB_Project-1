package edu.ib;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Zapasy {

    private SimpleStringProperty grupa;
    private SimpleStringProperty Rh;
    private SimpleIntegerProperty ilosc;
    private SimpleStringProperty potrzeba;
    private SimpleIntegerProperty bank_id;

    public Zapasy() {

    grupa = new SimpleStringProperty();
    Rh = new SimpleStringProperty();
    ilosc = new SimpleIntegerProperty();
    potrzeba = new SimpleStringProperty();
    bank_id = new SimpleIntegerProperty();

    }

    public String getGrupa() {
        return grupa.get();
    }

    public SimpleStringProperty grupaProperty() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa.set(grupa);
    }

    public String getRh() {
        return Rh.get();
    }

    public SimpleStringProperty rhProperty() {
        return Rh;
    }

    public void setRh(String rh) {
        this.Rh.set(rh);
    }

    public int getIlosc() {
        return ilosc.get();
    }

    public SimpleIntegerProperty iloscProperty() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc.set(ilosc);
    }

    public String getPotrzeba() {
        return potrzeba.get();
    }

    public SimpleStringProperty potrzebaProperty() {
        return potrzeba;
    }

    public void setPotrzeba(String potrzeba) {
        this.potrzeba.set(potrzeba);
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

}
