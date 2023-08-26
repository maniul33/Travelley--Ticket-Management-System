public class Customer {
    private String cus_name;
    private int cus_id;
    private String cus_email;
    private String cus_phoneno;
    private String cus_pass;

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_email() {
        return cus_email;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public String getCus_phoneno() {
        return cus_phoneno;
    }

    public void setCus_phoneno(String cus_phoneno) {
        this.cus_phoneno = cus_phoneno;
    }

    public String getCus_pass() {
        return cus_pass;
    }

    public void setCus_pass(String cus_pass) {
        this.cus_pass = cus_pass;
    }

    public Customer(String cus_name, int cus_id, String cus_email, String cus_phoneno, String cus_pass) {
        this.cus_name = cus_name;
        this.cus_id = cus_id;
        this.cus_email = cus_email;
        this.cus_phoneno = cus_phoneno;
        this.cus_pass = cus_pass;
    }
}
