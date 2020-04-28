package Graphic.Panels;

import Cards.Cards;
import Cards.Deck;
import Command.Contoller;
import Constants.Constants;
import Graphic.GamePanel;
import Graphic.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Status extends GamePanel {

    private static Status status;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";


    private JButton backMenu;
    //card
    private Cards card;
    private JLabel cardShow;
    private JTextArea detail;

    //deck
    private JTextArea deckDetail;
    private Deck deck;

    private JRadioButton[] topDecks;
    private ButtonGroup decks;

    JComboBox cardSelection;
    DefaultComboBoxModel[] cardModel;


    private JButton showButton;


    private Status() {
        super();
        backgroundFileName = Constants.backgroundFileName;
        setLayout(new GridBagLayout());
        setBackGround(backgroundFileName);
        setButtons();
        setLabels();
        setGridBagConstraints();

        validate();
    }

    public static Status statusPanel() {
        if (status == null)
            status = new Status();
        return status;
    }

    @Override
    protected void setButtons() {
        try {
            File backMenuFile = new File(buttonPath + "woodenBackMenu.png");
            BufferedImage backMenuBufferedImage = ImageIO.read(backMenuFile);
            backMenu = new JButton();
            backMenu.setIcon(new ImageIcon(backMenuBufferedImage));
            backMenu.setBackground(Color.BLACK);
            backMenu.setBorderPainted(false);
            backMenu.setContentAreaFilled(false);
            backMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainPanel.setPanel("menu");
                    Contoller.getPlayer().update();
                }
            });

            ///////////////////////////////////////////////////
            topDecks = new JRadioButton[10];
            decks = new ButtonGroup();
            deckDetail= new JTextArea();

            cardSelection = new JComboBox();
            cardModel = new DefaultComboBoxModel[10];
            cardShow = new JLabel();
            detail = new JTextArea();



            for (int i = 0; i < 10; i++) {
                String name = "Empty";
                if (Contoller.getPlayer()!= null &&Contoller.getPlayer().getTopDecks()!= null && Contoller.getPlayer().getTopDecks()[i] != null)
                    name = Contoller.getPlayer().getTopDecks()[i].getName();

               // System.out.println(name);
                topDecks[i] = new JRadioButton("Deck" + (i+1) + ": " + name);
                topDecks[i].setContentAreaFilled(false);
                topDecks[i].setForeground(Color.WHITE);
                topDecks[i].setActionCommand(i + "");
                topDecks[i].addActionListener(actionListener(i));
                decks.add(topDecks[i]);
            }

            topDecks[0].setSelected(true);
            ////////////////////////////////////////////


            //show
            File showFile = new File(buttonPath + "woodenShow.png");
            BufferedImage showBufferedImage = ImageIO.read(showFile);
            showButton = new JButton();
            showButton.setIcon(new ImageIcon(showBufferedImage));
            showButton.setBackground(Color.BLACK);
            showButton.setBorderPainted(false);
            showButton.setContentAreaFilled(false);
            showButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cardName = cardSelection.getSelectedItem().toString();
                    card = Cards.createCardByName(cardName);

                    try {
                        File shopFile = new File("src\\Graphic\\Cards\\card" + (card.getId() + 1) + ".png");
                        BufferedImage shopBufferedImage = ImageIO.read(shopFile);
                        cardShow.setIcon(new ImageIcon(shopBufferedImage));
                        cardShow.setForeground(Color.DARK_GRAY);

                        detail.setText("Name: " + card.getName() + "\n" + "Type: " + card.getType() + "\n" +
                                "Class: :" + card.getCardClass() + "\n" + "Rarity: " + card.getRarity() + "\n" +
                                "Mana: " + card.getMana() + "\n" + "Price: " + card.getCost());


                    } catch (IOException ex) {
                    }

                }
            });
        } catch (Exception e) {
        }

    }

    @Override
    protected void setLabels() {

    }

    @Override
    protected void setGridBagConstraints() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.fill = GridBagConstraints.NONE;


        gridBagConstraints.insets = new Insets(300, 0, 100, 20);
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        backgroundLabel.add(backMenu, gridBagConstraints);

        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        for (int i = 0; i < 10; i++) {
            gridBagConstraints.insets = new Insets(210+(20*i), 250, 0, 0);
            backgroundLabel.add(topDecks[i],gridBagConstraints);
        }

        gridBagConstraints.insets = new Insets(210, 400, 0, 0);
        backgroundLabel.add(deckDetail,gridBagConstraints);

        gridBagConstraints.insets = new Insets(300, 400, 0, 0);
        backgroundLabel.add(cardSelection, gridBagConstraints);


        gridBagConstraints.insets = new Insets(210, 600, 0, 0);
        backgroundLabel.add(cardShow, gridBagConstraints);


        gridBagConstraints.insets = new Insets(210, 800, 0, 0);
        backgroundLabel.add(detail, gridBagConstraints);

    }

    private ActionListener actionListener(int i) {
        ActionListener answer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Contoller.getPlayer().getTopDecks()==null || Contoller.getPlayer().getTopDecks()[i]==null
                || Contoller.getPlayer().getTopDecks()[i].getAllCards()==null)return;

                Contoller.getPlayer().sortTopDesks();
                for (Cards cards: Contoller.getPlayer().getTopDecks()[i].getAllCards()){
                    cardModel[i].addElement(cards.getName());
                }


                cardSelection.setModel(cardModel[i]);
                setDeckDetail(i);
            }
        };
        return answer;
    }

    private void setDeckDetail(int i) {
        deck= new Deck();
        deck= Contoller.getPlayer().getTopDecks()[i];
        deckDetail= new JTextArea();
        deckDetail.setText("Name: " + deck.getName() + "\n" + "Hero: " + deck.getHero() + "\n" +
                "Cost: :" + deck.getCost() + "\n" + "Games: " + deck.getGames() + "\n" +
                "Wins: " + deck.getWins() + "\n" + "Win / Total: " + deck.getWinsToTotal()+"\n"+
                "Most Common card: "+deck.getMostCommon().getName());
    }
}
