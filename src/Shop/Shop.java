package Shop;

import Cards.Cards;
import Player.Player;

import java.io.IOException;

public  class Shop {
    public static void Buy(Player player, Cards card) throws IOException {
        if (player.getGems()>=card.getCost()){
        player.setGems(player.getGems()-card.getCost());
        player.addCard(card);
            System.out.println("Congratulation! You bought"+card.getName());
        }
        else {
            System.out.println("Sorry :(   You don't have enough Gems to buy "+card.getName());

        }
    }
    public static void Sell(Player player, Cards card) throws IOException {
        player.setGems(player.getGems()+card.getCost());
        player.removeCard(card);

    }
}
