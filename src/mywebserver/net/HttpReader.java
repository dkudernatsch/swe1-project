package mywebserver.net;

import mywebserver.web.http.Request;
import mywebserver.web.http.parser.RequestParser;

import java.io.*;

public class HttpReader {

    private static final int DEFAUlT_BUF_SIZE = 1024 * 4;

    Request read(InputStream ins) throws IOException {
        BufferedInputStream bins = new BufferedInputStream(ins, DEFAUlT_BUF_SIZE);
        int read;
        byte[] buff = new byte[DEFAUlT_BUF_SIZE];
        int pos = 0;

        while ((read = bins.read()) != -1) {
            buff[pos] = (byte) read;
            if(read == 13 && checkCrLf(buff, pos)){
                //TODO: build request and return

            }else{
                pos++;
            }
        }
        throw new RuntimeException("not implemented");
    }


    private static Request parseRequest(byte[] read) {
        throw new RuntimeException("not implemented");
    }


    private static boolean checkCrLf(byte[] buf, int pos){
        if(buf[pos] == 10){
            if(buf[pos-1] == 10){
                return true;
            }else if(buf[pos-1] == 13){
                if(buf[pos-2] == 10)
                    return true;
                else
                    return false;

            }else return false;
        } else return false;


    }

}
