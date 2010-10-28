package audio.graph.data;

/**
 * this class read the a text file
 * create a DataPoints object with the data
 * normalize the data
 */
class DataReader {
	
	/**
	 * reads a text data file
	 * returns a DataPoints object with the data
	 */
	public DataPoints readData(File f){
		DataPoints datapoints = new DataPoints()
				
		boolean firstLine=true
		f.eachLine{String line ->
			if(line.startsWith('#')){
				datapoints.comments+=line+"\n"
				return
			}
			
			if(firstLine && (line =~ /[a-zA-Z]/) ){
				List headers=extractHeader (line)
				datapoints.nameX=headers[0]
				datapoints.nameY=headers[1]
				firstLine=false;
				return
			}
			firstLine = false
			List l = line.split('\t')
			datapoints.x<<l[0].toDouble()
			datapoints.y<<l[1].toDouble()
		}
		return datapoints
	}

	List extractHeader(String line){
		if(line.contains('\t')){
			return line.split(/\t/)
		}
		return line.split(/\s+/)
	}
	
	/**
	 * normalize x and y values
	 * @param DataPoints
	 * * @return a DataPoints object with values between 0 and 1
	 */
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

