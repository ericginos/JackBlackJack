import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class BackgroundMusic{

	String soundStatus;
	AudioInputStream audioInputStream; 
	Clip clip;
	long clipTimePosition = 0;	
	BooleanControl muteControl;
	
	BackgroundMusic(){
		try {
			
			//audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sounds/Background.wav").getAbsoluteFile());
			audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sounds/BackgroundMusic.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			
		} catch (Exception ex) {
			System.out.println("Error: cannot find background music file.");
			ex.printStackTrace();
		}
	}
	
	public void playBackgroundMusic() {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
		// Get the gain control from clip
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		// set the gain (between 0.0 and 1.0)
		double gain = 1.0;   
		float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);

		gainControl.setValue(dB);
	    muteControl = (BooleanControl) clip
	            .getControl(BooleanControl.Type.MUTE);
	        

	        muteControl.setValue(false);

	}
	
	public void stopBackgroundMusic() {
		//clip.stop();
		muteControl.setValue(true);
	}
   
  }