package Graphic.Panels;

import Cards.CardFactory;
import Cards.Cards;
import Command.Contoller;
import Command.Check;
import Constants.Constants;
import Graphic.ButtonController;
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

public class Shop extends GamePanel {
    private static Shop shop;
    private String backgroundFileName;
    private String buttonPath = "src\\Graphic\\Buttons\\";

    private boolean buy = true;

    private JButton backMenu;


    private JLabel cardShow;
    private JTextArea detail;
    private JTextArea wallet;

    private JRadioButton buyButton;
    private JRadioButton sellButton;
    private ButtonGroup trade;
    private JComboBox cardSelection;
    private JButton okButton;
    private JButton showButton;
    private Cards card;

    private Shop() {
        super();
        backgroundFileName = Constants.backgroundFileName;
        setLayout(new GridBagLayout());
        setBackGround(backgroundFileName);
        setButtons();
        setLabels();
        setGridBagConstraints();

        validate();
    }


    public static Shop shopPanel() {
        if (shop == null)
            shop = new Shop();
        return shop;
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
////////////////////////////////////////////////////

            buyButton = new JRadioButton("Buy Card");
            buyButton.setContentAreaFilled(false);
            buyButton.setForeground(Color.WHITE);

            sellButton = new JRadioButton("Sell Card");
            sellButton.setContentAreaFilled(false);
            sellButton.setForeground(Color.WHITE);

            trade = new ButtonGroup();
            trade.add(buyButton);
            trade.add(sellButton);
            buyButton.setSelected(true);

            buyButton.setActionCommand("buy");
            sellButton.setActionCommand("sell");

            cardSelection = new JComboBox();
            cardShow = new JLabel();
            detail = new JTextArea();

            if (Contoller.getPlayer() != null)
                wallet.setText("Wallet: " + Contoller.getPlayer().getGems() + " Gems");


            DefaultComboBoxModel buyModel = new DefaultComboBoxModel();
            DefaultComboBoxModel sellModel = new DefaultComboBoxModel();
            cardSelection.setModel(buyModel);

            if (Contoller.getPlayer() != null && Contoller.getPlayer().getMyCards().size() != 0)
                for (int i = 0; i < Contoller.getPlayer().buyAvailable().size(); i++) {
                    buyModel.addElement(Contoller.getPlayer().buyAvailable().get(i).getName());
                }

            if (Contoller.getPlayer() != null && Contoller.getPlayer().getMyCards().size() != 0)
                for (int i = 0; i < Contoller.getPlayer().getMyCards().size(); i++) {
                    sellModel.addElement(Contoller.getPlayer().getMyCards().get(i).getName());
                }

            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buyModel != null)
                        buyModel.removeAllElements();
                    if (Contoller.getPlayer() != null)
                        for (int i = 0; i < Contoller.getPlayer().buyAvailable().size(); i++) {
                            buyModel.addElement(Contoller.getPlayer().buyAvailable().get(i).getName());
                        }
                    cardSelection.setModel(buyModel);
                    buy = true;
                    if (Contoller.getPlayer() != null)
                        wallet.setText("Wallet: " + Contoller.getPlayer().getGems() + " Gems");
                }
            });
            sellButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sellModel != null)
                        sellModel.removeAllElements();
                    if (Contoller.getPlayer() != null && Contoller.getPlayer().getMyCards().size() != 0)
                        for (int i = 0; i < Contoller.getPlayer().getMyCards().size(); i++) {
                            sellModel.addElement(Contoller.getPlayer().getMyCards().get(i).getName());
                        }
                    cardSelection.setModel(sellModel);
                    buy = false;
                    if (Contoller.getPlayer() != null)
                        wallet.setText("Wallet: " + Contoller.getPlayer().getGems() + " Gems");
                }
            });


            //ok
            File okFile = new File(buttonPath + "woodenOk.png");
            BufferedImage okBufferedImage = ImageIO.read(okFile);
            okButton = new JButton();
            okButton.setIcon(new ImageIcon(okBufferedImage));
            okButton.setBackground(Color.BLACK);
            okButton.setBorderPainted(false);
            okButton.setContentAreaFilled(false);
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cardName = cardSelection.getSelectedItem().toString();
                    card = Cards.createCardByName(cardName);

                    if (buy && Check.buy(card)) {
                        wallet.setText("Wallet: " + Contoller.getPlayer().getGems() + " Gems");

                        if (buyModel != null)
                            buyModel.removeAllElements();
                        if (Contoller.getPlayer() != null && Contoller.getPlayer().getMyCards().size() != 0)
                            for (int i = 0; i < Contoller.getPlayer().buyAvailable().size(); i++) {
                                buyModel.addElement(Contoller.getPlayer().buyAvailable().get(i).getName());
                            }
                        ButtonController ok = new ButtonController(ButtonController.ButtonOptions.Buy, cardName);
                    } else if (buy && !Check.buy(card)) {
                        JOptionPane.showMessageDialog(Shop.shopPanel(), "You can't afford this card", "SHOP MESSAGE", JOptionPane.ERROR_MESSAGE);
                    } else if (!buy) {
                        Check.sell(card);
                        wallet.setText("Wallet: " + Contoller.getPlayer().getGems() + " Gems");

                        sellModel.removeAllElements();
                        if (Contoller.getPlayer() != null && Contoller.getPlayer().getMyCards().size() != 0)
                            for (int i = 0; i < Contoller.getPlayer().getMyCards().size(); i++) {
                                sellModel.addElement(Contoller.getPlayer().getMyCards().get(i).getName());
                            }
                        ButtonController ok = new ButtonController(ButtonController.ButtonOptions.Sell, cardName);
                    } else if (!buy && !Check.buy(card)) {
                        JOptionPane.showMessageDialog(Shop.shopPanel(), "You can't sell this card", "SHOP MESSAGE", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


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
                    if (cardSelection.getSelectedItem() == null) return;
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


                        if (Contoller.getPlayer() != null)
                            wallet.setText("Wallet: " + Contoller.getPlayer().getGems() + " Gems");

                    } catch (IOException ex) {
                    }
                }
            });

            //wallet

            wallet = new JTextArea();
            if (Contoller.getPlayer() != null)
                wallet.setText("Wallet: " + Contoller.getPlayer().getGems() + " Gems");


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
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

        gridBagConstraints.insets = new Insets(210, 320, 0, 0);
        backgroundLabel.add(buyButton, gridBagConstraints);

        gridBagConstraints.insets = new Insets(230, 320, 0, 0);
        backgroundLabel.add(sellButton, gridBagConstraints);

        gridBagConstraints.insets = new Insets(260, 320, 0, 0);
        backgroundLabel.add(cardSelection, gridBagConstraints);

        gridBagConstraints.insets = new Insets(320, 320, 0, 0);
        backgroundLabel.add(showButton, gridBagConstraints);

        gridBagConstraints.insets = new Insets(380, 320, 0, 0);
        backgroundLabel.add(okButton, gridBagConstraints);


        gridBagConstraints.insets = new Insets(210, 500, 0, 0);
        backgroundLabel.add(cardShow, gridBagConstraints);


        gridBagConstraints.insets = new Insets(210, 700, 0, 0);
        backgroundLabel.add(detail, gridBagConstraints);


        gridBagConstraints.insets = new Insets(330, 700, 0, 0);
        backgroundLabel.add(wallet, gridBagConstraints);


        gridBagConstraints.insets = new Insets(300, 0, 100, 20);
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        backgroundLabel.add(backMenu, gridBagConstraints);


        validate();
        SwingUtilities.updateComponentTreeUI(backgroundLabel);
    }
}
