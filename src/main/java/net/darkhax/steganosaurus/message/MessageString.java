package net.darkhax.steganosaurus.message;

/**
 * This message implementation allows for strings to be encoded and decoded.
 */
public class MessageString extends MessageBytes {
    
    /**
     * The original string data.
     */
    private final String originalString;
    
    public MessageString(String original) {
        
        super(original.getBytes());
        this.originalString = original;
    }
    
    /**
     * Gets the content as a string.
     * 
     * @return The contents of the message as a string.
     */
    public String getContentsAsString () {
        
        return this.originalString;
    }
}