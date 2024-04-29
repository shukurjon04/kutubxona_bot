package registr;

public class USer {
    private String name;
    private String surname;
    private String password;
    private String login;
    private long chatid;

    private String step;
    private String faoliat;

    public USer() {
    }

    public USer(long chatid) {
        this.chatid = chatid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getChatid() {
        return chatid;
    }

    public void setChatid(long chatid) {
        this.chatid = chatid;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getFaoliat() {
        return faoliat;
    }

    public void setFaoliat(String faoliat) {
        this.faoliat = faoliat;
    }

    @Override
    public String toString() {
        return "USer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", chatid=" + chatid +
                ", step='" + step + '\'' +
                ", faoliat='" + faoliat + '\'' +
                '}';
    }
}
