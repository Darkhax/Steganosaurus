package net.darkhax.steganosaurus.writer;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.nio.ByteBuffer;

import net.darkhax.steganosaurus.message.IMessage;

public class MessageWriterLSB implements IMessageWriter {
    
    public BufferedImage encodeMessages (BufferedImage cover, IMessage... messages) {
        
        final byte[] rasterBytes = ((DataBufferByte) cover.getRaster().getDataBuffer()).getData();
        this.encodeMessages(rasterBytes, messages);
        return cover;
    }
    
    @Override
    public boolean isCoverBigEnough (byte[] coverData, IMessage... messages) {
        
        return false;
    }
    
    @Override
    public byte[] encodeMessages (byte[] coverData, IMessage... messages) {
        
        final byte[] messageLength = ByteBuffer.allocate(4).putInt(messages[0].getLength()).array();
        final byte[] messageBytes = messages[0].getContents();
        
        // Write the length of the message at the start. These 4 bytes will take up 32 bytes of
        // the coverData.
        this.writeBytes(coverData, messageLength, 0);
        
        // Write the bytes of the message. Offset is 32 to account for the previous 4 bytes
        // used for length.
        this.writeBytes(coverData, messageBytes, 32);
        
        return coverData;
    }
    
    private byte[] writeBytes (byte[] coverData, byte[] toWrite, int offset) {
        
        // Prevent writing past the limit of coverData.
        final int messageEndPos = toWrite.length + offset;
        
        if (messageEndPos > coverData.length) {
            
            throw new IllegalArgumentException("Input too large for cover data. Cover: " + coverData.length + " Input: " + messageEndPos);
        }
        
        // Iterate through each byte we want to write.
        for (final byte add : toWrite) {
            
            // Iterate through bits in a descending order.
            // Update the offset to keep our place.
            for (int bit = 7; bit >= 0; bit--, offset++) {
                
                // Shift the byte to get the bit to write.
                final int currentBit = add >>> bit & 1;
                
                // Use binary operations to write the current bit to the least significant bit
                // of the current coverData byte. And with 1111 1110 to clear the last bit.
                // Then or with the bit to write the value.
                coverData[offset] = (byte) (coverData[offset] & 0xFE | currentBit);
            }
        }
        return coverData;
    }
    
    // TODO make decoding a proper system. 
    public byte[] decode (byte[] encodedCoverData) {
        
        int length = 0;
        
        // Start the offset as 32, as we are expecting 32 bytes to be used for length.
        int offset = 32;
        
        // Read the first 32 length bits.
        for (int lengthBit = 0; lengthBit < 32; lengthBit++) {
            
            length = length << 1 | encodedCoverData[lengthBit] & 1;
        }
        
        final byte[] decodedData = new byte[length];
        
        // Loop for every expected byte of data so it can be reconstructed.
        for (int byteIndex = 0; byteIndex < decodedData.length; byteIndex++) {
            
            // Loop once for each bit of the expected byte.
            for (int bitOffset = 0; bitOffset < 8; bitOffset++, offset++) {
                
                // Get the LSB of the current encoded byte, and shift it on to the byte being
                // reconstructed.
                decodedData[byteIndex] = (byte) (decodedData[byteIndex] << 1 | encodedCoverData[offset] & 1);
            }
        }
        
        return decodedData;
    }
}