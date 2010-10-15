package audio.graph.sound
;

import groovy.util.GroovyTestCase;
class ValuesConverterTest extends GroovyTestCase {
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testConvertToTone() {
		List yValues = [0, 1]
		ValuesConverter converter = new ValuesConverter()
		converter.convertToTone(yValues)
		assert converter.pitch.size() == 2
		assert converter.pitch == ['<TB0>', '<TB200>']
	}
	
	public void testAddLength(){
		
		List x = [0, 0.5, 1]
		ValuesConverter c = new ValuesConverter()
		c.pitch = ['<TB0>', '<TB100>', '<TB200>']
		c.addLength(x)
		assert c.tones.size() == 2
		assert c.tones == ['<TB0>/0.6', '<TB100>/0.6']
	}
	
	public void testtones2string(){
		ValuesConverter c = new ValuesConverter()
		c.tones = ['<TB0>/0.1', '<TB50>/1']
		c.tones2string()
		assert c.melody == '<TB0>/0.1 <TB50>/1' 
		String p = c.getPattern()
		assert p		
		MelodyStreamer streamer = [:]
		streamer.streamIt("Flute", p, null)
	}
}