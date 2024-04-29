package Adminservice;

import databasecollection.KitobxonTable;
import databasecollection.kitobtable;
import registr.Admin;
import registr.USer;
import registr.kitob;

public interface Adminservice {
    void setchatid(long chatid);
    void registr();
    String addbaza(String state , String text);
    void kitob_qoshish(String text, long chatid);
    void kitobo_chirish(String text, long chatid, kitobtable kitobtable) ;
    void kitobxon_ochirish(String text, USer uSer, KitobxonTable kitobxonTable);
    void tizimga_kirish(String state,String faoliyat,String text,USer uSer);
    void kitob_royxati(long chatid);
    void kitobxonlar_royxati(KitobxonTable kitobxonTable,USer uSer);
}
