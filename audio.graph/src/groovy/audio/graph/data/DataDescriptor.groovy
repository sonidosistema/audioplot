package audio.graph.data;


class DataDescriptor {
 List dn = []
int n


				private void calcDn(DataPoints data){
	n = data.x.size()
	int i = 0
				(n-1).times{
			dn<< ((data.y[i+1] - data.y[i])/(data.x[i+1]-data.x[i]))
			i++}
	}
	
	
		public  DataDescription findExtrema(DataPoints data){
			
			DataDescription description = new DataDescription()
			int 		i=0
		(n-2).times{
				if(Math.signum(dn[i+1]*dn[i])<0){
				description.extremaX<<data.x[i+1]
				description.extremaY<<data.y[i+1]
				if(dn[i]>0) {
description.extremaType<< ('max').toString()} 	
				else{
					description.extremaType<<('min').toString()}}
							i++}
			return description}
	
	public DataDescription describeData(DataPoints data){
			DataDescription description = new DataDescription()
calcDn(data)
description = findExtrema(data)
description.xmin = data.x.min()
description.xmax = data.x.max()
description.ymin = data.y.min()
description.ymax = data.y.max()
return description}

		
}