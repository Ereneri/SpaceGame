package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font bossBattle;
    BufferedImage health, angel, speed;
    public boolean gameFinished = false;
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        
        // Load Font
        try {
            InputStream is = getClass().getResourceAsStream("/Main/EndlessBossBattleRegular-v7Ey.ttf");
            bossBattle = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // load images
        try {
            health = ImageIO.read(getClass().getResourceAsStream("/object/wrench.png"));
            angel = ImageIO.read(getClass().getResourceAsStream("/Main/hearts.png"));
            speed = ImageIO.read(getClass().getResourceAsStream("/Main/bolt.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(bossBattle);
        g2.setColor(Color.white);

        // title state
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //play state
        if(gp.gameState == gp.playState) {
            // do stuff later
        }

        //paused state
        if(gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // game over state
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        // draw store
        if (gp.gameState == gp.storeState) {
            drawStore();
        }

        // draw options
        if (gp.gameState == gp.optionsState) {
            drawOptions();
        }
        
        // draw help
        if (gp.gameState == gp.helpState) {
            drawHelp();
        }

        // draw save state
        if (gp.gameState == gp.saveState) {
            drawSave();
        }

        // draw leaderboard
        if (gp.gameState == gp.leaderboardState) {
            drawLeaderboard();
        } 
    }

    //draws the pause screen
    public void drawPauseScreen() {

        g2.setFont(bossBattle);

        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50F));

        String text = "Paused";
        int x = getXforCenteringText(text);
        int y = gp.tileSize*3;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(30F));

        // render score and health
        text = "Score: " + gp.ship.getScore();
        x = getXforCenteringText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        text = "Health: " + gp.ship.getHp() + "%";
        x = getXforCenteringText(text);
        y += gp.tileSize-10;
        g2.drawString(text, x, y);

        // render divider
        text = "---";
        x = getXforCenteringText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        // render the store
        g2.setFont(g2.getFont().deriveFont(45F));
        text = "Shop";
        x = getXforCenteringText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // render LeaderBoard
        text = "Leaderboard";
        x = getXforCenteringText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // return to game
        text = "Return";
        x = getXforCenteringText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }


        // render quit
        text = "Quit";
        x = getXforCenteringText(text);
        y += gp.tileSize+55;
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    //draws the store screen
    public void drawStore() {
        g2.setFont(bossBattle);

        g2.setColor(new Color(0,0,0,210));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(96F));

        // draw store title
        String text = "Store";
        int x = getXforCenteringText(text);
        int y = gp.tileSize*3;
        g2.drawString(text, x, y);

        // draw score aka money
        g2.setFont(g2.getFont().deriveFont(30F));
        text = "Score: $" + gp.ship.getScore();
        x = getXforCenteringText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        // render divider
        text = "---";
        x = getXforCenteringText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        // Wings and Speedy Spooter is one time
        text = "Limit 1 for Angel's Wings and Speedy Spooter";
        g2.setFont(g2.getFont().deriveFont(25F));
        x = getXforCenteringText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        // item 1 
        x = 60;
        y += gp.tileSize;
        g2.drawImage(angel, x, y, gp.tileSize+25, gp.tileSize+25, null);
        text = "Angel's Wings ($500)";
        x += gp.tileSize+25;
        g2.drawString(text, x + 25, y+gp.tileSize);
        text = "Buy";
        g2.drawString(text, x + gp.tileSize*9, y+gp.tileSize);
        if (commandNum == 0 || commandNum == -1) {
            if (gp.getScore() < 500 || gp.getAngel()) {
                g2.drawString("x", x + gp.tileSize*9 - gp.tileSize, y+gp.tileSize);
            } else {
                g2.drawString(">", x + gp.tileSize*9 - gp.tileSize, y+gp.tileSize);
            }
        }

        // item 2
        x = 60;
        y += gp.tileSize*2;
        g2.drawImage(speed, x, y, gp.tileSize+25, gp.tileSize+25, null);
        text = "Speedy Shooter ($250)";
        x += gp.tileSize+25;
        g2.drawString(text, x + 25, y+gp.tileSize);
        text = "Buy";
        g2.drawString(text, x + gp.tileSize*9, y+gp.tileSize);
        if (commandNum == 1) {
            if (gp.getScore() < 250 || gp.getSpeedBoost()) {
                g2.drawString("x", x + gp.tileSize*9 - gp.tileSize, y+gp.tileSize);
            } else {
                g2.drawString(">", x + gp.tileSize*9 - gp.tileSize, y+gp.tileSize);
            }
        }

        // item 3
        x = 60;
        y += gp.tileSize*2;
        g2.drawImage(health, x, y, gp.tileSize+25, gp.tileSize+25, null);
        y += 5;
        text = "Mechanics Repair ($200)";
        x += gp.tileSize+25;
        g2.drawString(text, x + 25, y+gp.tileSize);
        text = "Buy";
        g2.drawString(text, x + gp.tileSize*9, y+gp.tileSize);
        if (commandNum == 2) {
            if (gp.getScore() >= 200 && gp.getHealth() < 200) {
                g2.drawString(">", x + gp.tileSize*9 - gp.tileSize, y+gp.tileSize);
            } else {
                g2.drawString("x", x + gp.tileSize*9 - gp.tileSize, y+gp.tileSize);
            }
        }

        // return to game
        g2.setFont(g2.getFont().deriveFont(25F));
        text = "Return";
        x = getXforCenteringText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }


    }

    public void drawHelp() {

        g2.setFont(bossBattle);

        g2.setColor(new Color(0,0,0,210));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(96F));

        // draw store title
        String text = "Help";
        int x = getXforCenteringText(text);
        int y = gp.tileSize*3;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(20F));

        // Movement 
        x = 60;
        y += gp.tileSize;
        text = "W";
        g2.setColor(Color.cyan);
        g2.drawString(text, x+12, y+gp.tileSize);
        text = "A S D";
        g2.drawString(text, x, y+gp.tileSize+20);

        text = "Movement";
        x += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(text, x + 30, y+gp.tileSize+10);
        y += gp.tileSize;
        
        //gun 
        text = "Space";
        g2.setColor(Color.cyan);
        g2.drawString(text, x*3-10, y+11);
        text = "Gun";
        g2.setColor(Color.white);
        g2.drawString(text, x*4-25, y+11);
        
        // H button
        text = "H";
        g2.setColor(Color.cyan);
        g2.drawString(text, x*5-30, y+11);
        text = "Buys Helth";
        g2.setColor(Color.white);
        g2.drawString(text, x*5, y+11);

        // Pause
        x = 60;
        y += gp.tileSize;
        text = "ESC";
        g2.setColor(Color.cyan);
        g2.drawString(text, x+3, y+gp.tileSize-20);
        text = "Shift";
        g2.drawString(text, x-10, y+gp.tileSize);

        text = "Pause and unpause Game";
        x += gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString(text, x + 30, y+gp.tileSize-10);
        y += gp.tileSize;

        // item 1 
        x = 60;
        y += gp.tileSize;
        g2.drawImage(angel, x, y+25, gp.tileSize, gp.tileSize, null);
        text = "When bought it sets your health to 25% when you die";
        x += gp.tileSize;
        g2.drawString(text, x + 25, y+gp.tileSize);

        // item 2
        x = 60;
        y += gp.tileSize*2;
        g2.drawImage(speed, x, y+20, gp.tileSize, gp.tileSize, null);
        text = "When bought it increases Ship Speed by 2 units";
        x += gp.tileSize;
        g2.drawString(text, x + 25, y+gp.tileSize);

        // item 3
        x = 60;
        y += gp.tileSize*2;
        g2.drawImage(health, x, y+20, gp.tileSize, gp.tileSize, null);
        text = "When bought it repairs ship's health by 25%";
        x += gp.tileSize;
        g2.drawString(text, x + 25, y+gp.tileSize);

        // return to game
        g2.setFont(g2.getFont().deriveFont(25F));
        text = "Return";
        x = getXforCenteringText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        g2.drawString(">", x - gp.tileSize, y);


    }

    //draws the title screen
    public void drawTitleScreen() {

        g2.setFont(bossBattle);

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        
        //title name
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96F));
        String text = "SpaceGame";
        int x = getXforCenteringText(text);
        int y = gp.tileSize*3;

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));

        // rocketship image
        x = gp.screenWidth/2-(gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.ship.up2, x, y, gp.tileSize*2, gp.tileSize*2, null);

        // Press Enter to Start
        text = "New Game";
        x = getXforCenteringText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // help screen
        text = "LeaderBoard";
        x = getXforCenteringText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        // help screen
        text = "Help";
        x = getXforCenteringText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // options
        text = "Options";
        x = getXforCenteringText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 3) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // Quit 
        text = "Quit";
        x = getXforCenteringText(text);
        y += 55 + gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 4) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    //draws the options screen
    public void drawOptions() {

            g2.setFont(bossBattle);

            g2.setColor(new Color(0,0,0,150));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);    
            
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 72F));
    
            // title
            String text = "Options";
            int x = getXforCenteringText(text);
            int y = gp.tileSize*3;
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
            
            // sound
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
            text = "Sound: " + (gp.soundOption ? "On" : "Off");
            x = getXforCenteringText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            
            // music
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
            text = "Music: " + (gp.musicOption ? "On" : "Off");
            x = getXforCenteringText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y+10);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y+10);
            }
            
            //difficulty
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
            text = "Difficulty: ";
            String option;
            int diff;
            if(gp.difficultyOption == 0) {
            	g2.setColor(Color.blue);
            	option = "easy";
            }else if(gp.difficultyOption == 1) {
            	g2.setColor(Color.yellow);
            	option = "medium";
            }else {
            	g2.setColor(Color.red);
            	option = "hard";
            }
            text += option;
            x = getXforCenteringText(text);
            y += gp.tileSize + 40;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            // return to game
            g2.setColor(Color.white);
            text = "Return";
            x = getXforCenteringText(text);
            y += 80+ gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
    
    }
    
    //draws the geme over screen
    public void drawGameOverScreen() {
        // set font
        g2.setFont(bossBattle);
        
        // set darken background
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        
        // setup vars
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
        
        // text
        text = "Game Over";
        
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteringText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        
        //Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-4, y-4);
        
        // Score
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        text = "Score: " + gp.getScore();
        x = getXforCenteringText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        
        // Save
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        text = "Save";
        x = getXforCenteringText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        //Retry
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        text = "Retry";
        x = getXforCenteringText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        // back to title screen
        text = "Quit";
        x = getXforCenteringText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
    
    // draw leaderboard
    public void drawLeaderboard() {

        gp.getScore();
        // set font
        g2.setFont(bossBattle);
        
        // set darken background
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        
        // setup vars
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        
        // text
        text = "LeaderBoard";
        
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteringText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        
        //Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-4, y-4);
        x = getXforCenteringText("AAA - 1000")+130;
        y += 55;
        
        // // Score
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        if (gp.sb.scores.size() > 0) {
            int lim = gp.sb.scores.size();
            if (lim > 5) {
                lim = 5;
            }
            for (int i = 0; i < lim; i++) {
                    text = (i+1) + ". " + gp.getName(gp.sb.scores.get(i)) + " - " + gp.getScore(gp.sb.scores.get(i));
                    y += 60;
                    g2.drawString(text, x, y);
                }
        } else {
            text = "No scores yet";
            x = getXforCenteringText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
        }
            
            //Retry
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
            text = "Return";
            x = getXforCenteringText(text);
            y += 55 + gp.tileSize;
            g2.drawString(text, x, y);
            g2.drawString(">", x - gp.tileSize, y);
        }
        
    public void drawSave() {
        // set font
        g2.setFont(bossBattle);
        
        // set darken background
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
        
        // setup vars
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
        
        // text
        text = "Save";
        
        // shadow
        g2.setColor(Color.black);
        x = getXforCenteringText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        
        //Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-4, y-4);
        
        // Score
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        text = "Score: " + gp.getScore();
        x = getXforCenteringText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        
        // draw Name
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        text = "Name: ";
        x = getXforCenteringText("Name: ___");
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        
        // renders x or >
        if (commandNum == 0) {
            if (gp.getName().length() == 3 && gp.nameBuilder) {
                g2.drawString(">", x - gp.tileSize, y);
            } else {
                g2.drawString("X", x - gp.tileSize, y);
                
            }
        }
        
        // draws the name
        text = "___";
        x += 150;
        if (gp.getName().length() == 3) {
            text = gp.getName();
        } else if (gp.getName().length() == 2) {
            text =  gp.getName() + "_";
        } else if (gp.getName().length() == 1) {
            text = gp.getName() + "__";
        }
        g2.drawString(text, x, y);
        
        // draw name input
        g2.drawString(text, x, y);
        g2.drawString(text, x, y);
        
        // Save
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        text = "Save";
        x = getXforCenteringText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        //Retry
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        text = "Retry";
        x = getXforCenteringText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
    
    public int getXforCenteringText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
        
    }