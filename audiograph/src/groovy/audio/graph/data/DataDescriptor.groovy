package audio.graph.data;

/**
 *this class makes a numerical description of data 
 */ 


class DataDescriptor {
	//list of differences between adjacent x values
	List dn = []
	//nb of datapoints
	int n
	
	
	/**
	 * Calculates the difference between each adjacent datapoints
	 */
	private void calcDn(DataPoints data){
		n = data.x.size()
		int i = 0
		(n-1).times{
			dn<< ((data.y[i+1] - data.y[i])/(data.x[i+1]-data.x[i]))
			i++
		}
	}
	
	
	/** 
	 * finds local extrema
	 * @param datapoints
	 *@return datadescription  
	 */
	
	public  DataDescription findExtrema(DataPoints data){
		
		DataDescription description = new DataDescription()
		
		
		int 		i=0
		(n-2).times{
			if(Math.signum(dn[i+1]*dn[i])<0){
				Extrema extrema = new Extrema()
				extrema.x =data.x[i+1]
				extrema.y = data.y[i+1]
				if(dn[i]>0) {
					extrema.type = ('max').toString()
				} 	
				else{ 
					extrema.type= ('min').toString()
				}
				description.extrema<<extrema
				
			}
			
			
			i++
		}
		
		
		return description
	}
	
	/**
	 * calculates x and y maximum and minimum
	 * finds data local extrema
	 * @param a DataPoints object
	 *  @return a DataDescription object
	 */
	
	public DataDescription describeData(DataPoints data){
		DataDescription description = new DataDescription()
		calcDn(data)
		description = findExtrema(data)
		description.xmin = data.x.min()
		description.xmax = data.x.max()
		description.ymin = data.y.min()
		description.ymax = data.y.max()
		return description
	}
	
	
	
}