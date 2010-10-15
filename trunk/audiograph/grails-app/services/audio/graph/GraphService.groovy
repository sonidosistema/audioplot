package audio.graph

import audio.graph.data.DataDescription 
import audio.graph.data.DataDescriptor;
import audio.graph.data.DataPoints 
import audio.graph.data.DataReader 
import audio.graph.data.Graph 

class GraphService {
	
	boolean transactional = true
	/**
	 * reads a datapoints file and returns a graph object with datapoints and description
	 */
	public Graph    createGraph(File f) {
		Graph graph = new Graph()
		DataPoints data = new DataPoints()
		DataReader reader = new DataReader()
		data = reader.readData(f)
		graph.datapoints = data
		DataDescription description = new DataDescription()
		DataDescriptor descriptor = new DataDescriptor()
		description = descriptor.describeData(data)
		graph.description = description
		return graph
		
	}
}
