package com.demo.item.nativeJNA;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Jna01 extends Library {
    Jna01 jna01 = (Jna01) Native.loadLibrary("F:\\Go\\src\\MicroDemo\\JNA\\jna01.dll",Jna01.class);
}
