import java.util.ArrayList;
import java.util.Random;

public class Deck  {
    private ArrayList<Kaart> kaarten;

    public Deck(){
        this.kaarten = new ArrayList<Kaart>();
    }

    public void maakDeck(){
        for(Kleur kaartKleur : Kleur.values()){
            for(Waarde kaartWaarde : Waarde.values()){
                this.kaarten.add(new Kaart(kaartKleur, kaartWaarde));
            }
        }
    }

    public void schudden(){
        ArrayList<Kaart> tempDeck = new ArrayList<Kaart>();
        Random random = new Random();
        int randomKaartIndex = 0;
        int origineleGrote = this.kaarten.size();
        for(int i = 0; i < origineleGrote; i++){
            randomKaartIndex = random.nextInt((this.kaarten.size()-1 - 0) + 1) + 0;
            tempDeck.add(this.kaarten.get(randomKaartIndex));
            this.kaarten.remove(randomKaartIndex);
        }
        this.kaarten = tempDeck;
    }

    public void kaartVerwijderen(int i){
        this.kaarten.remove(i);
    }

    public Kaart kaartTrekken(int i){
        return this.kaarten.get(i);
    }

    public void kaartToevoegen(Kaart kaartToevoegen){
        this.kaarten.add(kaartToevoegen);
    }

    public void trekken(Deck bron){
        this.kaarten.add(bron.kaartTrekken(0));
        bron.kaartVerwijderen(0);
    }

    //Use to print out deck
    public String toString(){
        String kaartenOutput = "";
        int i = 0;
        for(Kaart eenKaart : this.kaarten){
            kaartenOutput += "\n" + eenKaart.toString();
            i++;
        }
        return kaartenOutput;
    }

    public void stopAllesInDeck(Deck stopAlles){
        int thisDeckSize = this.kaarten.size();
        for(int i = 0; i < thisDeckSize; i++){
            stopAlles.kaartToevoegen(this.kaartTrekken(i));
        }
        for(int i = 0; i < thisDeckSize; i++){
            this.kaartVerwijderen(0);
        }
    }

    public int deckSize(){
        return this.kaarten.size();
    }

    public int kaartenWaarde(){
        int totaleWaarde = 0;
        int azen = 0;
        for(Kaart eenKaart : this.kaarten){
            switch(eenKaart.getWaarde()){
                case TWEE: totaleWaarde += 2; break;
                case DRIE: totaleWaarde += 3; break;
                case VIER: totaleWaarde += 4; break;
                case VIJF: totaleWaarde += 5; break;
                case ZES: totaleWaarde += 6; break;
                case ZEVEN: totaleWaarde += 7; break;
                case ACHT: totaleWaarde += 8; break;
                case NEGEN: totaleWaarde += 9; break;
                case TIEN: totaleWaarde += 10; break;
                case BOER: totaleWaarde += 10; break;
                case VROUW: totaleWaarde += 10; break;
                case KONING: totaleWaarde += 10; break;
                case AAS: azen += 1; break;
            }
        }

        for(int i = 0; i < azen; i++){
            if (totaleWaarde > 10){
                totaleWaarde += 1;
            }
            else{
                totaleWaarde += 11;
            }
        }
        return totaleWaarde;
    }

}
