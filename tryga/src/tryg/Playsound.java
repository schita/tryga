package tryg;

import java.io.File;
import javax.sound.sampled.*;

/* @author Vito*/
public class Playsound {
      File sf ;
    AudioFileFormat aff;
    AudioInputStream ais;
    Clip ol = null;

    public Playsound(String Nome){
            sf = new File(Nome+".wav");
            try {
            aff = AudioSystem.getAudioFileFormat(sf);
            ais = AudioSystem.getAudioInputStream(sf);
            AudioFormat af = aff.getFormat();
            DataLine.Info info = new DataLine.Info(
                    Clip.class,
                    ais.getFormat(), ((int) ais.getFrameLength() * af.getFrameSize()));
            ol = (Clip) AudioSystem.getLine(info);
            ol.open(ais);
            if (ol != null) {
                if (!ol.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    System.out.println("master gain non supportato dalla linea");
                }
            }
        } catch (Exception e) {
        }
        ol.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public void Stop(){
        ol.stop();
        ol.close();
    }
    
}
