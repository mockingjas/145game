import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;


public class SoundEffect {

	Clip clip;   
	public SoundEffect(String fn) {
		
		try {
			this.clip = clip;
			File soundFile = new File(fn);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			if( fn.equals("data/start.wav") || fn.equals("data/startBg.wav") ) clip.loop(Clip.LOOP_CONTINUOUSLY);
			else clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
