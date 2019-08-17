package net.darkhax.steganosaurus.message;

/**
 * This interface defines the requirements for an encodable message type. For a data type to
 * qualify it must be convertible to and from bytes, and must also have a fixed length value.
 */
public interface IMessage {
    
    /**
     * Gets the length of the message in bytes.
     * 
     * @return The byte length of the message.
     */
    int getLength ();
    
    /**
     * Gets the contents of the message.
     * 
     * @return The contents of the message.
     */
    byte[] getContents ();
}