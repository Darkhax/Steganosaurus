package net.darkhax.steganosaurus.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MessageSerializable<T extends Serializable> extends MessageBytes {
    
    public MessageSerializable(T original) throws IOException {
        
        super(serialize(original));
    }
    
    private static byte[] serialize (Serializable original) throws IOException {
        
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(bos);) {
            
            oos.writeObject(original);
            oos.flush();
            return bos.toByteArray();
        }
    }
}