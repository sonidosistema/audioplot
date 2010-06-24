package audio.graph.sound


import org.jfugue.Player
import org.jfugue.Pattern





class MelodyStreamer {
	String tempo=400
	def streamIt(String instrument, pattern, File saveFile){
		Pattern p1 = new Pattern ("I[$instrument] X[EFFECTS_LEVEL]=0 T$tempo X[VOLUME]=16383 $pattern")
		Player player = new Player()
		if(saveFile){
			player.saveMidi(pattern, saveFile);
			return
		}
		player.play(p1)
	}

}
