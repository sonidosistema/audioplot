package audio.graph

import audio.graph.data.DataDescription 
import audio.graph.data.DataDescriptor;
import audio.graph.data.DataPoints 
import audio.graph.data.DataReader 
import audio.graph.data.Graph 

class GraphService {
	DataReader reader = new DataReader()
	
	boolean transactional = true
	/**
	 * reads a datapoints file and returns a graph object with datapoints and description
	 */
	public Graph createGraph(InputStream input) {
		Graph graph = new Graph()

		graph.datapoints = reader.readData(input)
		DataDescription description = new DataDescription()
		DataDescriptor descriptor = new DataDescriptor()
		description = descriptor.describeData(graph.datapoints)
		graph.description = description
		
		return graph
		
	}
}
