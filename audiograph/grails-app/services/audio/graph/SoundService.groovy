package audio.graph

import audio.graph.data.DataPoints 
import audio.graph.data.DataReader 
import audio.graph.data.Graph 
import audio.graph.data.Interpolation 
import audio.graph.sound.MelodyStreamer 
import audio.graph.sound.ValuesConverter 

class SoundService {
	
	boolean transactional = true
	
	void playSound(Graph graph, File f, String instrument='Flute') {
		DataReader normaliser = new DataReader()
		DataPoints dataNorm = normaliser.normalisation(graph.datapoints)
		Interpolation inter= new Interpolation()
		dataNorm = inter.interpolate(dataNorm, 100)
		
		ValuesConverter converter = new ValuesConverter()
		converter.convertToTone(dataNorm.y)
		converter.addLength(dataNorm.x)
		converter.tones2string()
		String pattern =converter.getPattern()
		
		MelodyStreamer streamer = [:]
		streamer.streamIt(instrument, pattern, f)
	}
}
