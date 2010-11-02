package audio.graph
import audio.graph.data.DataPoints 
import audio.graph.data.Graph 


import grails.test.*

class SoundServiceTests extends GrailsUnitTestCase {
	SoundService soundService
	ServerFileService sfService

	protected void setUp() {
		soundService = new SoundService()
		sfService = new ServerFileService()
		soundService.serverFileService=sfService
		
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}
	
	void  testPlaySound() {
		mockDomain(ServerFile)
		mockLogging ServerFileService
		
		DataPoints data = new DataPoints()
		Graph graph = new Graph()
		data.x = [1, 1.5, 2, 2.5]
		data.y = [10, 15, 20, 25]
		graph.datapoints = data
		
		ServerFile sf = soundService.playSound(graph)
		
		assert sf
		assert sfService.loadData(sf).size() > 0
	}
}
