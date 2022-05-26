package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // boolean for if a key is pressed to render an sprite in the game
    public boolean upPressed, downPressed, leftPressed, rightPressed, shotKeyPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    // TBH I don't know what this does
    @Override
    public void keyTyped(KeyEvent e) {
    }

    // takes the boolean values from the key pressed and sets them to true
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        int code = e.getKeyCode();
        // System.out.println(code);

        // Title Controls
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
            	if(gp.ui.commandNum == 0) {
            		gp.ui.commandNum = 1;
            		gp.playSE(11);
            	}else {
            		gp.ui.commandNum --;
            		gp.playSE(11);
            	}
            }
            if (code == KeyEvent.VK_S) {
            	if(gp.ui.commandNum == 1) {
            		gp.ui.commandNum = 0;
            		gp.playSE(11);
            	}else {
            		gp.ui.commandNum ++;
            		gp.playSE(11);
            	}
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    //gp.stopMusic();
                    //gp.playMusic(1);
                    gp.playSE(2);

                }
                if (gp.ui.commandNum == 1) {
                    gp.playSE(3);
                    System.exit(0);
                }
            }
        }

        // game over controls
        if (gp.gameState == gp.gameOverState) {
            if (code == KeyEvent.VK_W) {
            	if(gp.ui.commandNum == 0) {
            		gp.ui.commandNum = 1;
            		gp.playSE(11);
            	}else {
            		gp.ui.commandNum --;
            		gp.playSE(11);
            	}
            }
            if (code == KeyEvent.VK_S) {
            	if(gp.ui.commandNum == 1) {
            		gp.ui.commandNum = 0;
            		gp.playSE(11);
            	}else {
            		gp.ui.commandNum ++;
            		gp.playSE(11);
            	}
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.reset();
                    gp.stopMusic();
                    gp.newGame();
                    gp.playSE(2);
                }
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                    gp.reset();
                    gp.playSE(3);
                }
            }
        }

        if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_W) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.commandNum = 2;
                    gp.playSE(11);
                } else {
                    gp.ui.commandNum--;
                    gp.playSE(11);
                }
            }
            if (code == KeyEvent.VK_S) {
                if (gp.ui.commandNum == 2) {
                    gp.ui.commandNum = 0;
                    gp.playSE(11);
                } else {
                    gp.ui.commandNum++;
                    gp.playSE(11);
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.storeState;
                    gp.playSE(2);
                    gp.ui.commandNum = -1;
                }
                if (gp.ui.commandNum == 1) {
                	gp.playSE(2);
                    gp.gameState = gp.scoreBoardState;

                }
                if (gp.ui.commandNum == 2) {
                	gp.playSE(3);
                    gp.gameState = gp.titleState;
                    gp.ui.commandNum = 0;
                    gp.reset();
                }
            }
        }

        if (gp.gameState == gp.storeState) {
            // up
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum == -1) {
                    gp.ui.commandNum = 3;
                    gp.playSE(11);
                }else {
                	gp.playSE(11);
                }
            }
            // down
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum == 4) {
                    gp.ui.commandNum = 0;
                    gp.playSE(11);
                }else {
                	gp.playSE(11);
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                // buys angel wings
                if (gp.ui.commandNum == 0) {
                    if (gp.ship.score >= 500) {
                        gp.setAngel(true);
                        gp.ship.score -= 500;
                        gp.playSE(2);
                        gp.playSE(10);
                    }else {
                    	gp.playSE(3);
                    }
                }
                // buys speedy shooter
                if (gp.ui.commandNum == 1) {
                    if (gp.ship.score >= 250) {
                        gp.addBooster();
                        gp.ship.score -= 250;
                        gp.playSE(2);
                        gp.playSE(10);
                    }else {
                    	gp.playSE(3);
                    }
                }
                // buys health shot
                if (gp.ui.commandNum == 2) {
                    if (gp.ship.score >= 100 && gp.ship.hp < 500) {
                        gp.ship.hp += 25;
                        gp.ship.score -= 100;
                        gp.playSE(2);
                        gp.playSE(10);
                    }else {
                    	gp.playSE(3);
                    }
                }
                // returns to pause screen
                if (gp.ui.commandNum == 3) {
                    gp.gameState = gp.pauseState;
                    gp.ui.commandNum = 0;
                    gp.playSE(3);
                }
            }

            // out of store
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
                gp.ui.commandNum = 0;
                gp.playSE(3);
            }
        }

        // Game controls
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            shotKeyPressed = true;
        }

        // paused screen
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
            	gp.playSE(3);
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                gp.playSE(3);
            }
        }
    }

    // takes the boolean values from the key released and sets them to false
    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            shotKeyPressed = false;
        }
    }
}
