# Steganosaurus
Steganosaurus is a steganography framework for Java. The goal of this project is to define an interface for performing steganography while also providing default implementations of the various aproaches. This project has a secondary goal of teaching and demonstrating steganography basics. 

## What is Steganography?
Steganography is the practice of concealing data within another dataset. A common example of this would be concealing the data of a string within the data of an image file. There are several motivations to do this, however the most common reason is to hide secret information without drawing attention to the data. Steganography is not a form of cryptography but can be combined with it to further protect the hidden data. 

## Other uses of Steganography?
While steganography is typically used for secrecy there are several other situations where it can be used. Another common use case is to embed a hidden digital watermark into a file, this can be used later on to trace back the origin of a file and potentially prove authorship or trace a data leak. Another use case is to unify multiple types of data into a single type, for example an image file that can be easily shared online while also acting as a form of downloadable content.

## Least Significant Bits (LSB)
One of the more common approaches is to write your data to the least significant bit of the original data. The least significant bit being the right most bit of a given sequence. This aproach can only be done when the original data can survive having it's data subtly modified, such as image data. 

To give an example, imagine we are given a byte of `0110 0010` and we want to write the bit of `1`. We would overwrite the LSB of the byte to get `0110 0011`. As a result this changes the decimal value from 98 to 99. This change would cause corruption to unicode data, as the lower case b character would be replaced with a lower case c. Alternatively if this were image data such as an RGB color channel component the change in color would be too insignificant to notice, 
