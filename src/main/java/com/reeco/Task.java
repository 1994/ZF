package com.reeco;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by reeco_000 on 2015/7/7.
 */
public class Task {

    private Timer timer;

    private Core core;

    private String username;

    private String password;

    private String ROOT = "http://202.115.80.153";

    public Task(int seconds,String username, String password) {
        timer = new Timer();
        core = new Core(username,password,ROOT);
        timer.schedule(new RemindTask(), seconds*1000, seconds*1000);
    }


    class RemindTask extends TimerTask {
        public void run() {
            boolean flag = core.isOpen();
            if(flag)
                System.out.println("选课系统开放了");
            else
                System.out.println("请等待");
        }
    }
}
