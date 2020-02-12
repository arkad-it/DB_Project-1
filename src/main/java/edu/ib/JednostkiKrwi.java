package edu.ib;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class JednostkiKrwi {

    /**
     * JednostkiKrwi as a class responsible for MySQL data mapping (contains all of the blood unit's table params);
     */

    private SimpleIntegerProperty dawca_id;
    private SimpleFloatProperty dawca_waga;
    private String dawca_data_urodzenia;
    private SimpleStringProperty dawca_imie_nazwisko;
    private SimpleIntegerProperty jednostka_krwi_id;
    private SimpleStringProperty jednostka_krwi_grupa;
    private SimpleStringProperty jednostka_krwi_Rh;
    private String jednostka_krwi_data_oddania;
    private SimpleStringProperty jednostka_krwi_status_var;
    private SimpleIntegerProperty bank_id;

    public JednostkiKrwi() {

        dawca_id = new SimpleIntegerProperty();
        dawca_waga = new SimpleFloatProperty();
        dawca_data_urodzenia = new String();
        dawca_imie_nazwisko = new SimpleStringProperty();
        jednostka_krwi_id = new SimpleIntegerProperty();
        jednostka_krwi_grupa= new SimpleStringProperty();
        jednostka_krwi_Rh = new SimpleStringProperty();
        jednostka_krwi_data_oddania = new String();
        jednostka_krwi_status_var = new SimpleStringProperty();
        bank_id = new SimpleIntegerProperty();

    }

    public int getDawca_id() {
        return dawca_id.get();
    }

    public SimpleIntegerProperty dawca_idProperty() {
        return dawca_id;
    }

    public void setDawca_id(int dawca_id) {
        this.dawca_id.set(dawca_id);
    }

    public float getDawca_waga() {
        return dawca_waga.get();
    }

    public SimpleFloatProperty dawca_wagaProperty() {
        return dawca_waga;
    }

    public void setDawca_waga(float dawca_waga) {
        this.dawca_waga.set(dawca_waga);
    }

    public String getDawca_data_urodzenia() {
        return dawca_data_urodzenia;
    }

    public void setDawca_data_urodzenia(String dawca_data_urodzenia) {
        this.dawca_data_urodzenia = dawca_data_urodzenia;
    }

    public String getDawca_imie_nazwisko() {
        return dawca_imie_nazwisko.get();
    }

    public SimpleStringProperty dawca_imie_nazwiskoProperty() {
        return dawca_imie_nazwisko;
    }

    public void setDawca_imie_nazwisko(String dawca_imie_nazwisko) {
        this.dawca_imie_nazwisko.set(dawca_imie_nazwisko);
    }

    public int getJednostka_krwi_id() {
        return jednostka_krwi_id.get();
    }

    public SimpleIntegerProperty jednostka_krwi_idProperty() {
        return jednostka_krwi_id;
    }

    public void setJednostka_krwi_id(int jednostka_krwi_id) {
        this.jednostka_krwi_id.set(jednostka_krwi_id);
    }

    public String getJednostka_krwi_grupa() {
        return jednostka_krwi_grupa.get();
    }

    public SimpleStringProperty jednostka_krwi_grupaProperty() {
        return jednostka_krwi_grupa;
    }

    public void setJednostka_krwi_grupa(String jednostka_krwi_grupa) {
        this.jednostka_krwi_grupa.set(jednostka_krwi_grupa);
    }

    public String getJednostka_krwi_Rh() {
        return jednostka_krwi_Rh.get();
    }

    public SimpleStringProperty jednostka_krwi_RhProperty() {
        return jednostka_krwi_Rh;
    }

    public void setJednostka_krwi_Rh(String jednostka_krwi_Rh) {
        this.jednostka_krwi_Rh.set(jednostka_krwi_Rh);
    }

    public String getJednostka_krwi_data_oddania() {
        return jednostka_krwi_data_oddania;
    }

    public void setJednostka_krwi_data_oddania(String jednostka_krwi_data_oddania) {
        this.jednostka_krwi_data_oddania = jednostka_krwi_data_oddania;
    }

    public String getJednostka_krwi_status_var() {
        return jednostka_krwi_status_var.get();
    }

    public SimpleStringProperty jednostka_krwi_status_varProperty() {
        return jednostka_krwi_status_var;
    }

    public void setJednostka_krwi_status_var(String jednostka_krwi_status_var) {
        this.jednostka_krwi_status_var.set(jednostka_krwi_status_var);
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
