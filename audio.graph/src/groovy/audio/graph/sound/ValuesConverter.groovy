package audio.graph.sound
;
import java.util.List;
class ValuesConverter {
	 
	List pitch = []
	List tones = []	  
String melody = ''	


public void convertToTone(List yValues){
		ToneBuilder builder = new ToneBuilder()
		yValues.each{
				String s = builder.buildTone(it as Number)
			pitch<<s}
		}
	
	public void addLength(List x){
		int n = pitch.size()
		int i = 0
(n-1).times{
			def deltaLoc = (x[i+1]-x[i])+0.1
						tones<<pitch[i]+'/'+deltaLoc
						i++}}
	
	public void tones2string(){
		melody = tones.join(" ")
		melody.toString()}
	
	String getPattern() {
	ToneBuilder builder = new ToneBuilder()
	String p = builder.string2pattern(melody)
return p}
}		

