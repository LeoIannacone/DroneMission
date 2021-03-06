/*
*  Generated by AN Unibo
*/
package it.unibo.contact.platformuv;
import it.unibo.is.interfaces.platforms.IRawBuffer;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import it.unibo.is.interfaces.IContentSegmentable;

public class ContentSegmentable implements IContentSegmentable{
 protected static final int BUFFER_MAX_LENGTH = 32000;
 protected InputStream source ;
 protected OutputStream destination;
 protected boolean endSent;
	
	public ContentSegmentable( InputStream inpS ){
		source = inpS;
	}
	public ContentSegmentable( OutputStream outS ){
		destination = outS;
	}	
	
//------------ INPUT SECTION ------------------
	
 	public int getSize() throws IOException{
		return source.available();
	}
  	public byte[] nextSegment() throws Exception {
 		if( getSize() <= 0)
		{
 			return null; 
		}
		int length = source.available() < BUFFER_MAX_LENGTH ? source.available() : BUFFER_MAX_LENGTH;		
		byte[] bbuffer = new byte[length];
 		source.read(bbuffer);
		return bbuffer;
	}
 	public void closeSending() throws Exception {
//		System.out.println("closeSendingWork CLOSING " + source.available() );
		source.close();
	}
 
 //------------ OUTPUT SECTION ------------------
	public void newSegment(IRawBuffer segmentRaw) throws Exception {
 		if (destination == null) {
			throw new Exception("Destination cannot be null");
		}
		destination.write(segmentRaw.getRawMsg());
		destination.flush();
	}
	public void closeReceiving() throws Exception {
		if (destination == null) {
			throw new Exception("Destination cannot be null");
		}
//		destination.flush();
		destination.close();
	}
}    
