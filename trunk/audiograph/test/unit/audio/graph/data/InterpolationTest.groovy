package audio.graph.data

import audio.graph.data.Interpolation;
import groovy.util.GroovyTestCase;

class InterpolationTest extends GroovyTestCase {
	
	public void testFindDxMax(){
		DataPoints data = new DataPoints()
data.x = [0, 1, 3, 5, 6]
				Interpolation dx = new Interpolation()
List i = dx.findDxMax(data)
	assert i.size() == 2
assert i == [1, 2]

		}
	
	public void  testAddPoints(){
		DataPoints data = new DataPoints()
		data.x = [1, 2, 3, 4]
		data.y = [2, 4, 6, 8]
		List index = [0,2]
		Interpolation dataInter = new Interpolation()
		data = dataInter.addPoints(index, data)
assert data.x == [1, 1.5, 2, 3, 3.5, 4]
		assert data.y == [2, 3, 4, 6, 7, 8]
	}
	
	public void testInterpolate(){
		DataPoints data = new DataPoints()
		data.x = [1, 2, 4, 7]
		data.y = [2, 4, 8, 14]
		Interpolation inter = new Interpolation()
		data = inter.interpolate(data, 5)
		assert data.x == [1, 2, 4, 5.5, 7]
		data = inter.interpolate(data, 6)
		assert data.x == [1, 2, 3, 4, 5.5, 7]
		data = inter.interpolate(data, 7)
		assert data.x == [1, 2, 3, 4, 4.75, 5.5, 6.25, 7]
		data = inter.interpolate(data, 9)
			assert data.x == [1, 1.5, 2, 2.5, 3, 3.5, 4, 4.75, 5.5, 6.25, 7]
		}
}
