public class Kaart {
    private Kleur kleur;
    private Waarde waarde;

    public Kaart(Kleur kleur, Waarde waarde) {
        this.kleur = kleur;
        this.waarde = waarde;
    }

    public String toString(){
        return this.kleur.toString() + "-" + this.waarde.toString();
    }

    public Waarde getWaarde(){
        return this.waarde;
    }
}
