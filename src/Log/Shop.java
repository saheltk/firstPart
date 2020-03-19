package Log;

import Cards.Cards;
import Player.Player;

import java.io.IOException;

public  class Shop {
    public static void Buy(Player player, Cards card) throws IOException {
        if (player.Gems>=card.cost){
        player.setGems(player.Gems-card.cost);
        player.addCard(card);
            System.out.println("Congratulation! You bought"+card.Name);
        }
        else {
            System.out.println("Sorry :(   You don't have enough Gems to buy "+card.Name);

        }
    }
    public static void Sell(Player player, Cards card) throws IOException {
        player.setGems(player.Gems+card.cost);
        player.removeCard(card);

    }
}
