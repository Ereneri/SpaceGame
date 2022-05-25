package Main;

import java.net.*;
import javax.sound.sampled.*;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
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
		if(clip != null) {
			clip.close();
		}
	}
	
	public void flush() {
		clip.flush();
	}
	
	
	
}
