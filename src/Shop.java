import Cards.Cards;
import Player.Player;

public class Shop {
    public void Buy(Player player, Cards card){
        player.setGems(player.Gems-card.cost);
        player.addCard(card);
    }
    public void Sell(Player player, Cards card){
        player.setGems(player.Gems+card.cost);
        player.removeCard(card);
    }
}
