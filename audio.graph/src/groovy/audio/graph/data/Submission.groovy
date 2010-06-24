package audio.graph.data;

import audio.graph.sound.MelodyStreamer 
import audio.graph.sound.ValuesConverter 


class Submission {
	public File submit(File f) {
DataPoints data = new DataPoints()
		DataReader reader = new DataReader()
		data = reader.readData(f)
		DataPoints dataNorm = new DataPoints()
		dataNorm = reader.normalisation(data)
		
		Interpolation inter= new Interpolation()
		dataNorm = inter.interpolate(dataNorm, 100)
		ValuesConverter converter = new ValuesConverter()
		converter.convertToTone(dataNorm.y)
		converter.addLength(dataNorm.x)
		converter.tones2string()
		
		String pattern =converter.getPattern()
		
		MelodyStreamer streamer = [:]
		streamer.streamIt("flute", pattern, null)
	}}
	