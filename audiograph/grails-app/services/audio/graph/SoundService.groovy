package audio.graph

import java.io.ByteArrayOutputStream;

import com.google.appengine.api.datastore.Blob;

import audio.graph.data.DataPoints 
import audio.graph.data.DataReader 
import audio.graph.data.Graph 
import audio.graph.data.Interpolation 
import audio.graph.sound.MelodyStreamer 
import audio.graph.sound.ValuesConverter 

class SoundService {
	ServerFileService serverFileService
	
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
	
	ServerFile playSound(Graph graph, String instrument='soprano_sax') {
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
		ByteArrayOutputStream out=new  ByteArrayOutputStream()
		streamer.streamIt(instrument, pattern, out)

		ServerFile sf = newServerFile()
		serverFileService.storeBlob(sf, new Blob(out.toByteArray()))
		sf
	}
	
	private ServerFile newServerFile(){
		ServerFile sf = new ServerFile()
		sf.save(flush:true, failOnError:true)
		sf
	}
}
