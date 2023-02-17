package src;

public interface CardInterface {
    Rank getRank() ;
    Suit getSuit() ;
    int getCribCount() ;
    String toString() ;

    //not sure if these are even supposed to be here
    //static src.Card getCard(src.Rank r, src.Suit s){return new src.Card(r,s);};
    //static src.Card getCard(int i){return new src.Card(i);};
}
