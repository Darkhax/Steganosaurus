package net.darkhax.steganosaurus.message;

/**
 * This message implementation serves as a base for simple data types that can easily be
 * converted into their raw bytes.
 */
public class MessageBytes implements IMessage {
    
    /**
     * The contents of the message converted to their raw bytes.
     */
    private final byte[] contents;
    
    public MessageBytes(byte[] contents) {
        
        this.contents = contents;
    }
    
    @Override
    public int getLength () {
        
        return this.contents.length;
    }
    
    @Override
    public byte[] getContents () {
        
        return this.contents;
    }
}