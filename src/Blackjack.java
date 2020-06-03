import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args){

        System.out.println("Welkom bij Blackjack!");

        Deck speelkaarten = new Deck();
        speelkaarten.maakDeck();
        speelkaarten.schudden();

        Deck kaartenSpeler = new Deck();
        double speelGeld = 100.0;
        Deck dealerKaarten = new Deck();

        Scanner input = new Scanner(System.in);

        while(speelGeld>0){
            System.out.println("Je hebt €" + speelGeld + ", hoe veel wil je inzetten?");
            double inzetSpeler = input.nextDouble();
            boolean eindeRonde = false;
            if(inzetSpeler > speelGeld){
                System.out.println("Je kan niet meer geld inzetten dan je hebt!");
                break;
            }

            System.out.println("Kaarten delen...");
            kaartenSpeler.trekken(speelkaarten);
            kaartenSpeler.trekken(speelkaarten);

            dealerKaarten.trekken(speelkaarten);
            dealerKaarten.trekken(speelkaarten);

            while(true)
            {
                System.out.println("Je hand:" + kaartenSpeler.toString());
                System.out.println("Je hand heeft de volgende waarde: " + kaartenSpeler.kaartenWaarde());
                System.out.println("Dealer hand: " + dealerKaarten.kaartTrekken(0).toString() + " en [verborgen]");
                System.out.println("Wat wil je doen (1)Hit of (2)Pas");
                int antwoord = input.nextInt();
                if(antwoord == 1){
                    kaartenSpeler.trekken(speelkaarten);
                    System.out.println("Je trekt een:" + kaartenSpeler.kaartTrekken(kaartenSpeler.deckSize()-1).toString());
                    if(kaartenSpeler.kaartenWaarde() > 21){
                        System.out.println("Je bent bust. Je huidige waarde is: " + kaartenSpeler.kaartenWaarde());
                        speelGeld -= inzetSpeler;
                        eindeRonde = true;
                        break;
                    }
                }

                if(antwoord == 2){
                    break;
                }

            }

            System.out.println("Dealer kaarten:" + dealerKaarten.toString());
            if((dealerKaarten.kaartenWaarde() > kaartenSpeler.kaartenWaarde())&&eindeRonde == false){
                System.out.println("De dealer heeft je verslagen met " + dealerKaarten.kaartenWaarde() + " tegen " + kaartenSpeler.kaartenWaarde());
                speelGeld -= inzetSpeler;
                eindeRonde = true;
            }
            while((dealerKaarten.kaartenWaarde() < 17) && eindeRonde == false){
                dealerKaarten.trekken(speelkaarten);
                System.out.println("Dealer trekt: " + dealerKaarten.kaartTrekken(dealerKaarten.deckSize()-1).toString());
            }
            System.out.println("Handwaarde van dealer: " + dealerKaarten.kaartenWaarde());
            if((dealerKaarten.kaartenWaarde()>21)&& eindeRonde == false){
                System.out.println("Dealer is bust. Je wint!");
                speelGeld += inzetSpeler;
                eindeRonde = true;
            }
            if((dealerKaarten.kaartenWaarde() == kaartenSpeler.kaartenWaarde()) && eindeRonde == false){
                System.out.println("Stand-off.");
                eindeRonde = true;
            }
            if((kaartenSpeler.kaartenWaarde() > dealerKaarten.kaartenWaarde()) && eindeRonde == false){
                System.out.println("Je wint de hand.");
                speelGeld += inzetSpeler;
                eindeRonde = true;
            }
            else if(eindeRonde == false)
            {
                System.out.println("Dealer wint.");
                speelGeld -= inzetSpeler;
            }

            kaartenSpeler.stopAllesInDeck(speelkaarten);
            dealerKaarten.stopAllesInDeck(speelkaarten);
            System.out.println("Einde van de hand.");

        }
        System.out.println("Het spel is afgelopen! Je bent al je geld kwijt. :(");
        input.close();
    }
}