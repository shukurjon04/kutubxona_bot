package Adminservice;

import databasecollection.KitobxonTable;
import registr.USer;

public interface Kitobxonservice {
    void registr();
    void setchatid(long chatid);
    void addbaza(String text, USer uSer, KitobxonTable kitobxonTable);
    void kitoblar_royxati(long chatid);
    void kitob_olish(long chatid);
}
