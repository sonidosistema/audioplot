package audio.graph.data;

import java.util.List;

/**
 * this class performs data interpolation
 */
class Interpolation {
	/**find the maximum difference between 2 x values
	 * @param DataPoints
	 * @return list with posotion where points have to be inserted
	 */
	List findDxMax(DataPoints datapoints) {
		int n = datapoints.x.size()
		List dx = []
		List index = []
		int i = 0
		(n-1).times{
			dx<<(datapoints.x[i+1]-datapoints.x[i])
			i++
		}
		
		i = 0
		def max = dx.max()
		dx.each{
			if(it==max) {index<<i
			}
			i++
		}
		return index
	}
	
	/**
	 * adds points at positions given in the index list
	 */
	
	
	DataPoints addPoints(List index, DataPoints datapoints){
		List rIndex = 		index.reverse()
		
		rIndex.each{
			def newValue = (datapoints.x[it]+datapoints.x[it+1])/2
			
			datapoints.x[(it+1)..<(it+1)] = newValue 
			def newY = (datapoints.y[it]+datapoints.y[it+1])/2 
			datapoints.y[(it+1)..<(it+1)] = newY
		}
		
		return datapoints
	}
	/**
	 * adds points where delta x is max
	 * repeats the process until the desired nb of points is reached
	 * @param datapoints
	 * @param desired nb of points n
	 * @return DataPoints object with at least n points
	 */ 
	public DataPoints interpolate(DataPoints data, int nb) {
		int n = data.x.size()
		while(n<nb) {
			List index = 			findDxMax(data)
			data = addPoints(index, data)
			n = data.x.size()
		}
		return data
	}
	
}				
