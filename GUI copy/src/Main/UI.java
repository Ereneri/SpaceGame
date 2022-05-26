package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.lang.module.FindException;
import java.awt.FontFormatException;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font bossBattle;
    public boolean gameFinished = false;
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        
        try {
            InputStream is = getClass().getResourceAsStream("/Main/EndlessBossBattleRegular-v7Ey.ttf");
            bossBattle = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
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
    }

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
        y += gp.tileSize;
        g2.drawString(text, x, y);

    }

    public void drawTitleScreen() {

        g2.setFont(bossBattle);

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
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

        // Quit 
        text = "Quit";
        x = getXforCenteringText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public int getXforCenteringText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

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

        //Retry
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48F));
        text = "Retry";
        x = getXforCenteringText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        // back to title screen
        text = "Quit";
        x = getXforCenteringText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    // public void drawSubWindow(int x, int y, int width, int height) {

    //     Color c = new Color(0,0,0,210);
    //     g2.setColor(c);
    //     g2.fillRect(x, y, width, height);

    //     c = new Color(255,255,255);
    //     g2.setColor(c);
    //     g2.fillRect(x+5, y+5, width-10, height-10);
    // }

}
