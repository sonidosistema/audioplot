package audio.graph.data;


import groovy.util.GroovyTestCase;

class DataDescriptorTest extends GroovyTestCase {
	
										public void testCalcDn() {
DataPoints data = new DataPoints()
data.x = [1, 1.5, 3, 4.1, 5, 6, 7, 8, 9.5, 10]
data.y = [10, 15, 30, 41, 50, 60, 70, 80, 95, 100]
DataDescriptor dn = new DataDescriptor()
dn.calcDn(data)
assert dn.dn.size() == 9
assert dn.dn[1] == 10
		assert dn.dn[-2] == 10  
		}

	public void testFindExtrema() {
		DataReader reader = new DataReader()
		DataPoints data = new DataPoints()
		data = reader.readData('data/data_x2.txt')
		DataDescriptor extrema = new DataDescriptor()
		DataDescription description = new DataDescription()
		extrema.calcDn(data)
		description = extrema.findExtrema(data)
		assert description.extremaX[0] == 0 
		assert description.extremaY[0] == 1
		assert description.extremaType[0] == 'min'
		}
	
	public void testDescribeData(){
		DataPoints data = new DataPoints()
		data.x = [-2, -1, 0, 1, 2]
		data.y = [4, 1, 0, 1, 4]
		DataDescription description = new DataDescription()
		DataDescriptor descriptor = new DataDescriptor()
		description = descriptor.describeData(data)
		assert description.xmin == -2
		assert description.ymax == 4
		assert description.extremaX[0] == 0
		assert  description.extremaY[0] == 0
		}
}
