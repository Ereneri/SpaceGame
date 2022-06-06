package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import rocketship.rocketship.shieldSound;

public class KeyHandler implements KeyListener {

    // boolean for if a key is pressed to render an sprite in the game
    public boolean upPressed, downPressed, leftPressed, rightPressed, shotKeyPressed, hKeyPressed;
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
        // System.out.println(gp.gameState);

        // Title Controls
        if (gp.gameState == gp.titleState) {
        	
        	//going up in selections
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            	
            	// scrolling look (if your at the top and go up again, now you at the bottom)
            	if(gp.ui.commandNum == -1) {
            		gp.ui.commandNum = 4;
            		gp.playSE(11);
            	}else {
            		gp.ui.commandNum --;
            		gp.playSE(11);
            	}
            }
            
            //going down in selections
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                // scrolling look (if your at the bottom and go sown again, now you at the top)
            	if(gp.ui.commandNum == 4) {         	
            		gp.ui.commandNum = 0;
            		gp.playSE(11);
            	}else {
            		gp.ui.commandNum ++;
            		gp.playSE(11);
            	}
            }
            
            //choosing your selection
            if (code == KeyEvent.VK_ENTER) {
            	
            	// new game
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.newGame();
                    gp.playSE(2);
                    gp.stopMusic();
                    gp.playMusic(1);
                }

                // leaderboard
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.leaderboardState;
                    gp.ui.commandNum = 0;
                    gp.playSE(2);
                }
                
                //help
                if (gp.ui.commandNum == 2) {
                    gp.gameState = gp.helpState;
                    gp.ui.commandNum = 0;
                    gp.playSE(2);
                }
                // options
                if (gp.ui.commandNum == 3) {
                    gp.gameState = gp.optionsState;
                    gp.ui.commandNum = 0;
                    gp.playSE(2);
                }
                //quit
                if (gp.ui.commandNum == 4) {
                    gp.playSE(3);
                    System.exit(0);
                }
            }
        }

        if (gp.gameState == gp.gameOverState) {
        	
        	//below is up and down selection movement with scrolling
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            	if(gp.ui.commandNum == 0) {
            		gp.ui.commandNum = 2;
            		gp.playSE(11);
            	}else {
            		gp.ui.commandNum --;
            		gp.playSE(11);
            	}
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            	if(gp.ui.commandNum == 2) {
            		gp.ui.commandNum = 0;
            		gp.playSE(11);
            	}else {
            		gp.ui.commandNum ++;
            		gp.playSE(11);
            	}
            }
            
            //choosing your selection
            if (code == KeyEvent.VK_ENTER) {

                // save
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.saveState;
                    gp.ui.commandNum = 0;
                    gp.playSE(3);
                }
            	
            	//retry
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.playState;
                    gp.reset();
                    gp.newGame();
                    gp.playSE(2);
                    gp.stopMusic();
                    gp.playMusic(1);
                }
                
                //quit
                if (gp.ui.commandNum == 2) {
                    gp.gameState = gp.titleState;
                    gp.ui.commandNum = 0;
                    gp.reset();
                    gp.playSE(3);
                }
            }
        }

        if (gp.gameState == gp.pauseState) {
        	//below is up and down selection movement with scrolling
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                if (gp.ui.commandNum == 0) {
                    gp.ui.commandNum = 3;
                    gp.playSE(11);
                } else {
                    gp.ui.commandNum--;
                    gp.playSE(11);
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                if (gp.ui.commandNum == 3) {
                    gp.ui.commandNum = 0;
                    gp.playSE(11);
                } else {
                    gp.ui.commandNum++;
                    gp.playSE(11);
                }
            }
            
            //choosing your selection
            if (code == KeyEvent.VK_ENTER) {
            	//store
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.storeState;
                    gp.playSE(2);
                    gp.ui.commandNum = -1;
                }
                
                //leaderboard
                if (gp.ui.commandNum == 1) {
                    gp.playSE(2);
                    gp.gameState = gp.leaderboardState;
                    gp.ui.commandNum = -1;
                }
                
                //resume
                if (gp.ui.commandNum == 2) {
                    gp.gameState = gp.playState;
                    gp.playSE(2);
                    gp.ui.commandNum = -1;
                    gp.stopMusic();
                    gp.playMusic(1);
                }
                
                //quit
                if (gp.ui.commandNum == 3) {
                	gp.playSE(3);
                    gp.gameState = gp.titleState;
                    gp.ui.commandNum = 0;
                    gp.paused = false;
                    gp.reset();
                }
            }
            if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
                gp.ui.commandNum = 0;
                gp.playSE(3);
            }
        }

        if (gp.gameState == gp.storeState) {
            // up
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum == -1) {
                    gp.ui.commandNum = 3;
                    gp.playSE(11);
                }else {
                	gp.playSE(11);
                }
            }
            // down
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
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
                    if (gp.ship.score >= 500 && gp.getAngel() == false) {
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
                    if (gp.ship.score >= 250 && gp.getSpeedBoost() == false) {
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
                    if (gp.ship.score >= 200 && gp.ship.hp < 200) {
                        gp.ship.hp += 25;
                        gp.ship.score -= 200;
                        gp.playSE(2);
                        gp.playSE(10);
                        
                        //checks if the shieldUp sound has been played and if not plays the sound
                        if(!shieldSound.hasPlayedShieldSound && gp.ship.hp == 100) {
                        	gp.playSE(12);
                        	shieldSound.hasPlayedShieldSound = true;
                        }
                        
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

            // resume key
            if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
                gp.ui.commandNum = 0;
                gp.playSE(3);
            }
        }

        if (gp.gameState == gp.optionsState) {
        	//up
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum == -1) {
                    gp.ui.commandNum = 3;
                    gp.playSE(11);
                }else {
                	gp.playSE(11);
                }
            }
            // down
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum == 4) {
                    gp.ui.commandNum = 0;
                    gp.playSE(11);
                }else {
                	gp.playSE(11);
                }
            }

            if (code == KeyEvent.VK_ENTER) {
            	
                // toggles sound eddects
                if (gp.ui.commandNum == 0) {
                    boolean tmp = gp.soundOption;
                    gp.setSound(!tmp);
                }
                
             // toggles music eddects
                if (gp.ui.commandNum == 1) {
                    boolean tmp = gp.musicOption;
                    gp.setMusic(!tmp);
                    if(!tmp == false) {
                    	gp.stopMusic();
                    }else {
                    	gp.playMusic(0);
                    }
                }
                
                // changes difficulty
                if (gp.ui.commandNum == 2) {
                    if(gp.difficultyOption == 0) {
                    	gp.setDiff(1);
                    }else if(gp.difficultyOption == 1) {
                    	gp.setDiff(2);
                    }else if(gp.difficultyOption == 2) {
                    	gp.setDiff(0);
                    }
                }
                
                // returns to menu
                if (gp.ui.commandNum == 3) {
                    gp.gameState = gp.titleState;
                    gp.ui.commandNum = 0;
                    gp.playSE(3);
                }
            }

            // pause key
            if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.titleState;
                gp.ui.commandNum = 0;
                gp.playSE(3);
            }
        }

        if (gp.gameState == gp.leaderboardState) {
            if (code == KeyEvent.VK_ENTER) {
                // returns to menu
                if (gp.ui.commandNum == 1) {
                    if (gp.paused) {
                        gp.gameState = gp.pauseState;
                    } else {
                        gp.gameState = gp.titleState;
                        gp.reset();
                    }
                    gp.ui.commandNum = 0;
                    gp.playSE(3);
                }
                gp.ui.commandNum++;
            }
            if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_ESCAPE) {
                // returns to menu
                if (gp.paused) {
                    gp.gameState = gp.pauseState;
                } else {
                    gp.gameState = gp.titleState;
                    gp.reset();
                }
                gp.ui.commandNum = 0;
                gp.playSE(3);
            }
        }

        if (gp.gameState == gp.saveState) {
            // get name input 
            if (gp.nameBuilder == true) {
                System.out.println(code);
                if (code == KeyEvent.VK_DELETE || code == KeyEvent.VK_BACK_SPACE) {
                    gp.ship.removeLetter();
                }
                if (code == KeyEvent.VK_ENTER && gp.ship.name.length() == 3) {
                    gp.nameBuilder = false;
                }
                if (gp.ship.name.length() != 3) {
                    if (code == KeyEvent.VK_A) {
                        gp.ship.addLetter("A");
                    }
                    if (code == KeyEvent.VK_B) {
                        gp.ship.addLetter("B");
                    }
                    if (code == KeyEvent.VK_C) {
                        gp.ship.addLetter("C");
                    }
                    if (code == KeyEvent.VK_D) {
                        gp.ship.addLetter("D");
                    }
                    if (code == KeyEvent.VK_E) {
                        gp.ship.addLetter("E");
                    }
                    if (code == KeyEvent.VK_F) {
                        gp.ship.addLetter("F");
                    }
                    if (code == KeyEvent.VK_G) {
                        gp.ship.addLetter("G");
                    }
                    if (code == KeyEvent.VK_H) {
                        gp.ship.addLetter("H");
                    }
                    if (code == KeyEvent.VK_I) {
                        gp.ship.addLetter("I");
                    }
                    if (code == KeyEvent.VK_J) {
                        gp.ship.addLetter("J");
                    }
                    if (code == KeyEvent.VK_K) {
                        gp.ship.addLetter("K");
                    }
                    if (code == KeyEvent.VK_L) {
                        gp.ship.addLetter("L");
                    }
                    if (code == KeyEvent.VK_M) {
                        gp.ship.addLetter("M");
                    }
                    if (code == KeyEvent.VK_N) {
                        gp.ship.addLetter("N");
                    }
                    if (code == KeyEvent.VK_O) {
                        gp.ship.addLetter("O");
                    }
                    if (code == KeyEvent.VK_P) {
                        gp.ship.addLetter("P");
                    }
                    if (code == KeyEvent.VK_Q) {
                        gp.ship.addLetter("Q");
                    }
                    if (code == KeyEvent.VK_R) {
                        gp.ship.addLetter("R");
                    }
                    if (code == KeyEvent.VK_S) {
                        gp.ship.addLetter("S");
                    }
                    if (code == KeyEvent.VK_T) {
                        gp.ship.addLetter("T");
                    }
                    if (code == KeyEvent.VK_U) {
                        gp.ship.addLetter("U");
                    }
                    if (code == KeyEvent.VK_V) {
                        gp.ship.addLetter("V");
                    }
                    if (code == KeyEvent.VK_W) {
                        gp.ship.addLetter("W");
                    }
                    if (code == KeyEvent.VK_X) {
                        gp.ship.addLetter("X");
                    }
                    if (code == KeyEvent.VK_Y) {
                        gp.ship.addLetter("Y");
                    }
                    if (code == KeyEvent.VK_Z) {
                        gp.ship.addLetter("Z");
                    }
                }

            } else {
                //up
                if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum == -1) {
                        gp.ui.commandNum = 2;
                        gp.playSE(11);
                    }else {
                        gp.playSE(11);
                    }
                }
                // down
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum == 3) {
                        gp.ui.commandNum = 0;
                        gp.playSE(11);
                    }else {
                        gp.playSE(11);
                    }
                }

                // enter to save or return to menu
                if (code == KeyEvent.VK_ENTER) {
                    
                    // saves score and redirects to leaderboard
                    if (gp.ui.commandNum == 1) {
                        gp.saveScore();
                        gp.updateScores();
                        gp.gameState = gp.leaderboardState;
                        gp.ui.commandNum = 0;
                        gp.nameBuilder = true;
                        gp.ship.name = "";
                        gp.playSE(3);
                        gp.paused = false;
                    }
                    
                    // returns to menu
                    if (gp.ui.commandNum == 2) {
                        gp.paused = false;
                        gp.reset();
                        gp.gameState = gp.titleState;
                        gp.ui.commandNum = 0;
                        gp.playSE(3);
                    }
                }
            }
        }

        if (gp.gameState == gp.helpState) {
            // return
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 1) {
                    gp.gameState = gp.titleState;
                } else {
                    gp.ui.commandNum++;
                }
            }
        }

        if (gp.gameState == gp.playState) {
            // Game controls
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                shotKeyPressed = true;
            }
    
            // paused screen
            if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
                gp.paused = true;
                gp.ui.commandNum = 0;
                gp.playSE(3);
                gp.stopMusic();
                gp.playMusic(0);
            }
        }
    }

    // takes the boolean values from the key released and sets them to false
    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            shotKeyPressed = false;
        }
        if (code == KeyEvent.VK_H) {
        	gp.hKeyCount = 1;
        }
    }
}
