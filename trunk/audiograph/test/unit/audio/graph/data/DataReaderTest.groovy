package audio.graph.data

import audio.graph.data.DataReader;
import groovy.util.GroovyTestCase;

class DataReaderTest extends GroovyTestCase {
	
	
	public void testExtractHeaderTab(){
		DataReader reader = new DataReader()
		
		String line = 'time (s)	height (m)'
		List headers = reader.extractHeader(line)
		assert headers.size() == 2
		assert headers[0] == 'time (s)'
		assert headers[1] == 'height (m)'
	}
	
	public void testExtractHeaderNoTab(){
		DataReader reader = new DataReader()
		
		String line = 'time  height'
		List headers = reader.extractHeader(line)
		assert headers.size() == 2
		assert headers[0] == 'time'
		assert headers[1] == 'height'
	}
	
	public void testReadData(){
		DataReader reader = new DataReader()
		DataPoints data = reader.readData(new File('test/data/data.txt'))
		assert data.x.size() == 35
		assert data.y.size() == 35
	}
	
	public void testReadDataTabHeader(){
		DataReader reader = new DataReader()
		DataPoints data = reader.readData(new File('test/data/data-header-tab.txt'))
		
		assert data.nameX == 'time (s)'
		assert data.nameY == 'height (m)'
		assert !data.comments
		
		assert data.x.size() == 35
		assert data.y.size() == 35
	}
	
	public void testReadDataNoTabHeader(){
		DataReader reader = new DataReader()
		DataPoints data = reader.readData(new File('test/data/data-header-no-tab.txt'))
		
		assert data.nameX == 'time'
		assert data.nameY == 'height'
		
		assert data.comments
		assert data.comments.split(/\n/).size() == 2
		assert data.comments.startsWith ('#pipo')
		assert data.comments.contains ('kinkin')
		
		assert data.x.size() == 35
		assert data.y.size() == 35
	}
	

	
	public void testNormalisation(){
		DataPoints datapoints = new DataPoints()
		datapoints.x = [0, 1, 5, 10]
		datapoints.y = [0, 10, 50, 83]
		DataReader data = new DataReader()
		def normdata = data.normalisation(datapoints)
		assert normdata.x.size() == 4
		assert normdata.y.size() == 4
		assert normdata.y[0] == 0
		assert normdata.y[-1] == 1
		
	}
}
