/*
*  Generated by AN Unibo
*/
package it.unibo.contact.platformuv;
import java.util.Arrays;

public class RawBuffer implements it.unibo.is.interfaces.platforms.IRawBuffer{
protected int dim = 0;
protected byte[] buffer = null;
	public RawBuffer( ){
 		dim = 0;
	}
	public RawBuffer(byte[] msg, int dim){
		this.dim = dim;
		buffer = new byte[dim];
  		buffer = Arrays.copyOf(msg, dim);
 	}	
	public byte[] getRawMsg(){
		return buffer;
	}	
	public int getDim(){
		return dim;
	}
}   
