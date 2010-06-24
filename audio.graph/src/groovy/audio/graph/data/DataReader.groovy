package audio.graph.data;

class DataReader {
	
	public DataPoints readData(File f){
		DataPoints datapoints = new DataPoints()		
				f.eachLine{
			List l = it.split('\t')
			datapoints.x<<l[0].toDouble()
			datapoints.y<<l[1].toDouble()
		}
		return datapoints
	}
	
	public DataPoints normalisation(DataPoints datapoints) {
		DataPoints normData = new DataPoints()
		def xmax = datapoints.x.max()
		def xmin = datapoints.x.min()
		def ymin = datapoints.y.min()
		def ymax = datapoints.y.max()
		
		normData.y= datapoints.y.collect{(it-ymin)/(ymax-ymin)}
		normData.x= datapoints.x.collect{(it-xmin)/(xmax-xmin)}
		return normData
	}
}

