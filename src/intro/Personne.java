package intro;

import java.time.LocalDate;
import java.util.Objects;

public class Personne implements Comparable<Personne> {

    private String nom;
    private int taille;
    private int poid;
    private LocalDate dateNaiss;

    public Personne(String nom, int taille, int poid, LocalDate dateNaiss) {
        this.nom = nom;
        this.taille = taille;
        this.poid = poid;
        this.dateNaiss = dateNaiss;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public LocalDate getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(LocalDate dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    @Override
    public String toString() {
        return "intro.Personne{" +
                "nom='" + nom + '\'' +
                ", taille=" + taille +
                ", poid=" + poid +
                ", dateNaiss=" + dateNaiss +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if( !(o instanceof Personne) )
            return false;

        return this.getNom().charAt(0) == ((Personne) o).getNom().charAt(0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, taille, poid, dateNaiss);
    }

    @Override
    public int compareTo(Personne o) {
        return this.getPoid() - o.getPoid();
    }
}
