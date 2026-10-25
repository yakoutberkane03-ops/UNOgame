package unoGame;

public class NumberCard extends Card {
    public enum Number {
          Zero,One, Two, Three, Four, Five, Six, Seven, Eight, Nine}
          //Attriburs of class NumberCard 
          private Number number;

        //Constructor of classNumberCard
        public NumberCard (Color color , Number number ) {
         super (color);
         this.number=number;
        }

        public Number getNumber () {
         return number;
        }

        //We can play if same color or same number

        @Override
        public boolean canbeplay (Card topCard)  {

           return (this.getColor () == topCard.getColor() ||  (topCard instanceof NumberCard) && 
                  (((NumberCard)topCard).number==this.number));

        }
        public String toString() {
            return getColor() + " " + number;
        }
 
}
