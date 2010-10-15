package audio.graph.sound

;

import groovy.util.GroovyTestCase;

class ToneBuilderTest extends GroovyTestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	void test_constructor_with_map(){		
		ToneBuilder builder=new ToneBuilder(min:400, max:500, size:200)
		assert 400 == builder.min
		assert 500 == builder.max
		assert builder.size == 200
	}
	
	void test_ten_tones(){
		ToneBuilder builder=new ToneBuilder(min:400, max:500, size:10)
		                                        
		def checkValues=[[0, '0'], [0.5, '5'], [0.2, '2'], [0.11, '1'], [0.099, '0'], [0.999, '9']]
		checkValues.each{val, str ->
			assert builder.buildTone(val) == "<${ToneBuilder.TONE_PREFIX}$str>"
		}
	}
}
