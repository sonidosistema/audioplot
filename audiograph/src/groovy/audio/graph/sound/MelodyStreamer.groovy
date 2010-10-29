package audio.graph.sound

import gervill.demo.Midi2WavRender;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log
import org.jfugue.Player
import org.jfugue.Pattern

class MelodyStreamer {
	String tempo=400
	Log log = LogFactory.getLog(MelodyStreamer)
	
	def streamIt(String instrument, String pattern, File saveFile){
		streamIt instrument, new Pattern("I[$instrument] X[EFFECTS_LEVEL]=0 T$tempo X[VOLUME]=16383 $pattern"), saveFile
	}
	
	def streamIt(String instrument, Pattern pattern, File saveFile){
		if(saveFile){
			Player player = new Player(MidiSystem.getSequencer(false));
			log.debug "pattern=$pattern"
			Sequence sequence = player.getSequence(pattern);
			Midi2WavRender.render null, sequence, saveFile
			//player.saveMidi(pattern, saveFile);
			return
		}
		Player player = new Player()
		player.play(pattern)
	}
}
