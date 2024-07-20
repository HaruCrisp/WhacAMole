import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class WhacAWaifu {
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Holo, not Gojo");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[] board = new JButton[9];
    ImageIcon holoIcon;
    ImageIcon gojoIcon;

    JButton currGojoTile; 
    JButton currHoloTile;

    Random random = new Random();
    Timer setGojoTimer;
    Timer setHoloTimer;
    int score;

    WhacAWaifu(){
        //frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.PLAIN,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Click the Holo tiles.");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.black);
        frame.add(boardPanel);

        Image gojoImg = new ImageIcon(getClass().getResource("./gojo.jpg")).getImage();
        gojoIcon = new ImageIcon(gojoImg.getScaledInstance(190,190, java.awt.Image.SCALE_SMOOTH));

        Image holoImg = new ImageIcon(getClass().getResource("./holo.png")).getImage();
        holoIcon = new ImageIcon(holoImg.getScaledInstance(180,180, java.awt.Image.SCALE_SMOOTH));

        score = 0;

        for (int i=0; i<9;i++){
            JButton tile = new JButton();
            board[i] = tile;
            boardPanel.add(tile);
            tile.setFocusable(false);
            //tile.setIcon(gojoIcon);
            //tile.setIcon(holoIcon);

            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton tile = (JButton) e.getSource();
                    if(tile == currHoloTile){
                            score += 1;
                            textLabel.setText("Waifu Count " + Integer.toString(score));
                    }

                    else if (tile == currGojoTile){
                        textLabel.setText("You suck. Only " + Integer.toString(score) + " waifus.");
                        setHoloTimer.stop();
                        setGojoTimer.stop();
                        for (int i = 0; i<9 ; i++){
                            board[i].setEnabled(false);
                        }
                    }
                }
            });
        }

        setHoloTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(currHoloTile !=null){
                    currHoloTile.setIcon(null);
                    currHoloTile = null;
                }

                int num = random.nextInt(9);
                JButton tile = board[num];

                if(currGojoTile == tile) return;

                currHoloTile = tile;
                currHoloTile.setIcon(holoIcon);
            }
        });

        setGojoTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(currGojoTile !=null){
                    currGojoTile.setIcon(null);
                    currGojoTile = null;
                }

                int num = random.nextInt(9);
                JButton tile = board[num];

                if(currHoloTile == tile) return;

                currGojoTile = tile;
                currGojoTile.setIcon(gojoIcon);
            }
        });

        setHoloTimer.start();
        setGojoTimer.start();
        frame.setVisible(true);
    }
}
