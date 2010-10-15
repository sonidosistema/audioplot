package audio.graph
import audio.graph.data.DataPoints 
import audio.graph.data.Graph 


import grails.test.*

class GraphServiceTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void testCreateDataPoints() {
		
		GraphService graphservice = new GraphService()
		Graph graph = new Graph()
		
		graph = graphservice.createGraph(new File("data/data_x2.txt"))
		assert graph.datapoints.x.size() == 21
		assert graph.description.xmin == -10 
		assert graph.description.extrema[0].x == 0
		assert graph.description.extrema[0].y == 1
		assert graph.description.extrema[0].type == 'min'
	}
}
