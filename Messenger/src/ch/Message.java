package ch;

import java.io.Serializable;
import java.time.Instant;

public class Message implements Serializable{
	
	// damit serialisable weiss, welche version das ist.
	private static final long serialVersionUID = 1L; 
	
	private final String text;
	private final Instant date;
	private final String sender;
	
	public Message(String text,String sender){
		this.text = text;
		this.date = Instant.now();
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}

	public Instant getDate() {
		return date;
	}

	public String getText() {
		return text;
	}
	
	public String toString(){
		return "Date: " + date + "Sender: " + sender + " Text: " + text;
	}
}
