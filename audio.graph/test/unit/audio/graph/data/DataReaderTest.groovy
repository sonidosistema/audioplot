package audio.graph.data

import audio.graph.data.DataReader;
import groovy.util.GroovyTestCase;

class DataReaderTest extends GroovyTestCase {
	
	
	public void testReadData(){
		DataReader reader = new DataReader()
		def data = reader.readData(new File('data/data.txt'))
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
