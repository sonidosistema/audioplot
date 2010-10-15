package audio.graph
import audio.graph.data.DataPoints 
import audio.graph.data.Graph 


import grails.test.*

class SoundServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void  testPlaySound() {
		DataPoints data = new DataPoints()
		Graph graph = new Graph()
data.x = [1, 1.5, 2, 2.5]
	data.y = [10, 15, 20, 25] 
		graph.datapoints = data
				SoundService sound = new SoundService()
		File f = new File('d:test_soundService')
		sound.playSound(graph, f)
		assert f
		}
}
