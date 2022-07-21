package com.hbacakk.banka.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sube implements Serializable {
    @SerializedName("ID")
    @Expose
    public Integer id;
    @SerializedName("dc_SEHIR")
    @Expose
    public String Sehir;
    @SerializedName("dc_ILCE")
    @Expose
    public String Ilce;
    @SerializedName("dc_BANKA_SUBE")
    @Expose
    public String BankaSube;
    @SerializedName("dc_BANKA_TIPI")
    @Expose
    public String BankaTipi;
    @SerializedName("dc_BANK_KODU")
    @Expose
    public String BankaKodu;
    @SerializedName("dc_ADRES_ADI")
    @Expose
    public String AdresAdi;
    @SerializedName("dc_ADRES")
    @Expose
    public String Adres;
    @SerializedName("dc_POSTA_KODU")
    @Expose
    public String PostaKodu;
    @SerializedName("dc_ON_OFF_LINE")
    @Expose
    public String OnOffLine;
    @SerializedName("dc_ON_OFF_SITE")
    @Expose
    public String OnOffSite;
    @SerializedName("dc_BOLGE_KOORDINATORLUGU")
    @Expose
    public String BolgeKoordinatorlugu;
    @SerializedName("dc_EN_YAKIM_ATM")
    @Expose
    public String EnYakinAtm;


}
