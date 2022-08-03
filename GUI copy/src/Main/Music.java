package Main;

import java.net.*;
import javax.sound.sampled.*;

public class Music {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Music() {
		
		//main music
		soundURL[0] = getClass().getResource("/Sounds/menue.wav");
		soundURL[1] = getClass().getResource("/Sounds/playingGame.wav");
		
		//selection SE
		soundURL[2] = getClass().getResource("/Sounds/Confirm.wav");
		soundURL[3] = getClass().getResource("/Sounds/Cancel.wav");
		
		//shooting
		soundURL[4] = getClass().getResource("/Sounds/Laser_shoot.wav");
		
		//his and explosion
		soundURL[5] = getClass().getResource("/Sounds/Explosion.wav");
		soundURL[6] = getClass().getResource("/Sounds/gameOver.wav");
		
		//loading screne
		soundURL[7] = getClass().getResource("/Sounds/loading.wav");
		
		//collecting
		soundURL[8] = getClass().getResource("/Sounds/collect.wav");
		
		//repairing
		soundURL[9] = getClass().getResource("/Sounds/repair.wav");
		
		// buying
		soundURL[10] = getClass().getResource("/Sounds/buying.wav");
		
		// movement with w and s in the different screnes
		soundURL[11] = getClass().getResource("/Sounds/selectionMovement2.wav");
		
		// shield sounds
		soundURL[12] = getClass().getResource("/Sounds/shieldUp.wav");
		soundURL[13] = getClass().getResource("/Sounds/shieldBreak2.wav");
		
		// teleport sound
		soundURL[14] = getClass().getResource("/Sounds/teleport3.wav");


	}
	
	public void setFile(int i) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
		}
	}
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		
			clip.close();
	}
	
	public void flush() {
		clip.flush();
	}
	
	
	
}
