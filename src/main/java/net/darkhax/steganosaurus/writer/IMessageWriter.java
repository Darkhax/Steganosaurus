package net.darkhax.steganosaurus.writer;

import net.darkhax.steganosaurus.message.IMessage;

public interface IMessageWriter {
    
    boolean isCoverBigEnough (byte[] coverData, IMessage... messages);
    
    byte[] encodeMessages (byte[] coverData, IMessage... messages);
}