package audio.graph.data;
import audio.graph.sound.ValuesConverter 



import audio.graph.sound.MelodyStreamer 
import audio.graph.sound.ValuesConverter 
Graph graph = new Graph()
DataPoints data = new DataPoints()
DataReader reader = new DataReader()
data = reader.readData('d:test.txt')
graph.datapoints = data

DataPoints dataNorm = new DataPoints()
dataNorm = reader.normalisation(data)
 
Interpolation inter= new Interpolation()
dataNorm = inter.interpolate(dataNorm, 100)
ValuesConverter converter = new ValuesConverter()
converter.convertToTone(dataNorm.y)
converter.addLength(dataNorm.x)
converter.tones2string()

String pattern =converter.getPattern()
println pattern
MelodyStreamer streamer = [:]
streamer.streamIt("flute", pattern, null)

