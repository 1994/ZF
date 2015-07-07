package com.reeco;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by reeco_000 on 2015/7/7.
 */
public class CoreTest extends TestCase{

    private Core core = new Core("your username","your password","your url");


    @Test
    public void testLogin(){
        core.isOpen();
    }

    @Test
    public void testTask(){
        new Task(5,"your username","your password");
    }

}
