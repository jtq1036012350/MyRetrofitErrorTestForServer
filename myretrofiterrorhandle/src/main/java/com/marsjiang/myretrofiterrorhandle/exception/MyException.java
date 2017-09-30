package com.marsjiang.myretrofiterrorhandle.exception;

/**
 * Created by Jiang on 2017-09-28.
 */

public class MyException extends RuntimeException  {
    public MyException(){
        super();
    }
    public MyException(String msg){
        super(msg);
    }
}
