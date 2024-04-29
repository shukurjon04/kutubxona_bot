package nusxalar;
import registr.Admin;
import registr.Kitobxon;
import registr.kitob;
import registr.parollar;
import servisimpl.Adminservicimpl;
import servisimpl.Kitobserceimpl;

public class Container {
    public static Admin admin = new Admin();
    public static Adminservicimpl adminservicimpl = new Adminservicimpl();
    public static Kitobxon kitobxon = new Kitobxon();
    public static Kitobserceimpl kitobserceimpl = new Kitobserceimpl();
    public static parollar parollar = new parollar();
    public static kitob kitob = new kitob();
}
