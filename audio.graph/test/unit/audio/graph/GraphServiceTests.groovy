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
		DataPoints data = new DataPoints()
		 graph = graphservice.createGraph('data/data_x2.txt')
		assert graph.datapoints.x.size() == 21
assert graph.description.xmin == -10 
		assert graph.description.extremaX[0] == 0
		assert graph.description.extremaY[0] == 1
		assert graph.description.extremaType[0] == 'min'
				}
}
