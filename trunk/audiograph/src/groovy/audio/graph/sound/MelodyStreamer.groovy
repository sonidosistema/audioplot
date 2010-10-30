package audio.graph.sound

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

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
	
	
	private Pattern buildPattern(String instrument, String pattern){
		new Pattern("I[$instrument] X[EFFECTS_LEVEL]=0 T$tempo X[VOLUME]=16383 $pattern")
	}
	
	def streamIt(String instrument, String pattern){
		streamIt(buildPattern(instrument, pattern))
	}
	def streamIt(Pattern pattern){
		Player player = new Player()
		player.play pattern
	}
	
	def streamIt(String instrument, String pattern, OutputStream out){
		streamIt buildPattern(instrument, pattern), out
	}
	
	def streamIt(Pattern pattern, OutputStream out){
		Player player = new Player(MidiSystem.getSequencer(false));
		log.debug "pattern=$pattern"
		Sequence sequence = player.getSequence(pattern);
		Midi2WavRender.render null, sequence, out
	}
	
	def streamIt(String instrument, String pattern, File saveFile){
		streamIt buildPattern(instrument, pattern), saveFile
	}
	
	def streamIt(Pattern pattern, File saveFile){
		streamIt pattern, new FileOutputStream(saveFile)
	}
}
