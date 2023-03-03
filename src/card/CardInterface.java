package src.card;

public interface CardInterface {
    Rank getRank() ;
    Suit getSuit() ;
    int getCribCount() ;
    String toString() ;

    //not sure if these are even supposed to be here
    //static src.card.Card getCard(src.card.Rank r, src.card.Suit s){return new src.card.Card(r,s);};
    //static src.card.Card getCard(int i){return new src.card.Card(i);};
}
