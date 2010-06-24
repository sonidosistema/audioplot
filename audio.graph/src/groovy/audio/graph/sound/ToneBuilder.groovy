package audio.graph.sound



import java.util.Map;

import org.jfugue.MicrotoneNotation;
import org.jfugue.Pattern;

/**
 * converts a value between 0 and 1 in to a jfuge string
 */
class ToneBuilder {
	final static TONE_PREFIX="TB"
	
	//the lowest tone (value = 0.0)
	final double min
	
	//the highest tone (value = 1.0)
	final double max
	
	//in how many tones do we divide each tone
	final size=100
	
	MicrotoneNotation microtone
	
	ToneBuilder(Map options=[:]){
		min=options.min?:300
		max=options.max?:1500
	    assert max > min
	    
		size=options.size?:200
		assert size > 1
		
		generateToneList()
	}
	
	/**
	 * from a value between 0 and 1, return a jfugue reday note string
	 */
	String buildTone(Number value){
		assert value >= 0.0
		assert value <= 1.0
		
		if(value == 1.0){
			return "<"+TONE_PREFIX+size+">"
		}
		return "<"+TONE_PREFIX+(value*size).toInteger()+">"
	}
	
	/**
	 * transform a string into a jfigue pattern 
	 * @param s
	 * @return
	 */
	Pattern string2pattern(String s){
		return microtone.getPattern(s)
	}
	
	/**
	 * this method is private and build a list of all tones, from the min to max
	 * it should not be called from outside the constructor
	 * @return
	 */
	private generateToneList(){
		microtone = new MicrotoneNotation();
		(0..size).toList().each{
			microtone.put(TONE_PREFIX+it, min+(it*1.0)*(max-min)/size)
		}
	}
	
}
