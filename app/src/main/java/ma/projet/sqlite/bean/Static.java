package ma.projet.sqlite.bean;

public class Static {
    private String  libelle;
    private int nb;

    public Static() {
    }

    public Static(String libelle, int nb) {
        this.libelle = libelle;
        this.nb = nb;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
}
