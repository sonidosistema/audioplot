package audio.graph

import audio.graph.data.DataPoints 
import audio.graph.data.DataReader 
import audio.graph.data.Graph 
import audio.graph.data.Interpolation 
import audio.graph.sound.MelodyStreamer 
import audio.graph.sound.ValuesConverter 

class SoundService {
	static List availableInstruments = [
		'CHURCH_ORGAN',
		'HARMONICA',
		'VIOLIN',
		'CONTRABASS',
		'TRUMPET',
		'TUBA',
		'SOPRANO_SAX',
		'CLARINET',
		'FLUTE',
		'WHISTLE',
		'PAN_FLUTE',
		]
	
	boolean transactional = true
	
	void playSound(Graph graph, File f, String instrument='flute') {
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
