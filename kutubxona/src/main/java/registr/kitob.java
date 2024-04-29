package registr;

public class kitob {
    private String name;
    private String desc;
    private String file;

    @Override
    public String toString() {
        return "kitob{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", file='" + file + '\'' +
                '}';
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public  String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  String getDesc() {
        return desc;
    }

    public  void setDesc(String desc) {
        this.desc = desc;
    }

}
