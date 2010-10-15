package audio.graph.sound
;
import java.util.List;
/**
 * this class converts datapoints into a JFugue pattern
 */
class ValuesConverter {
	
	//List of pitch corresponding to y values
	List pitch = []
	
	// list of tones correspong to datapoints
	List tones = []	  
	String melody = ''	
	
	// converts a list of y values into pitch
	public void convertToTone(List yValues){
		ToneBuilder builder = new ToneBuilder()
		yValues.each{
			String s = builder.buildTone(it as Number)
			pitch<<s
		}
	}
	
	//adds a duration to each pitch 
	public void addLength(List x){
		int n = pitch.size()
		int i = 0
		(n-1).times{
			def deltaLoc = (x[i+1]-x[i])+0.1
			tones<<pitch[i]+'/'+deltaLoc
			i++
		}
	}
	//converts a list of tones to a string
	public void tones2string(){
		melody = tones.join(" ")
		melody.toString()
	}
	
	
	// transform a list of jfugue tones into a jfugue pattern	
	String getPattern() {
		ToneBuilder builder = new ToneBuilder()
		String p = builder.string2pattern(melody)
		return p
	}
}		

