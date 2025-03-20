package boostclinic;

public class Physiotherapist {
    private String id;
    private String name;
    private String expertise;
    private String address;
    private String telephone;

    public Physiotherapist(String id, String name, String expertise, String address, String telephone) {
        this.id = id;
        this.name = name;
        this.expertise = expertise;
        this.address = address;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExpertise() {
        return expertise;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }
}