package audio.graph.sound


import gervill.demo.Midi2WavRender;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;

import org.jfugue.Player
import org.jfugue.Pattern

class MelodyStreamer {
	String tempo=400
	
	def streamIt(String instrument, String pattern, File saveFile){
		streamIt instrument, new Pattern("I[$instrument] X[EFFECTS_LEVEL]=0 T$tempo X[VOLUME]=16383 $pattern"), saveFile
	}
	
	def streamIt(String instrument, Pattern pattern, File saveFile){
		if(saveFile){
			Player player = new Player(MidiSystem.getSequencer(false));
			Sequence sequence = player.getSequence(pattern);
			Midi2WavRender.render null, sequence, saveFile
			//player.saveMidi(pattern, saveFile);
			return
		}
		Player player = new Player()
		player.play(pattern)
	}
}
