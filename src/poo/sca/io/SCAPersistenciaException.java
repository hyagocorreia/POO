package poo.sca.io;

@SuppressWarnings("serial")
public class SCAPersistenciaException extends Exception {
	public SCAPersistenciaException(String msg){
		super(msg);
	}
	public SCAPersistenciaException(String msg, Throwable e){
		super(msg,e);
	}
}
