package com.reeco;

import jodd.http.HttpBrowser;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.jerry.Jerry;


/**
 * Created by reeco_000 on 2015/7/7.
 */
public class Core {
    private String username;

    private String password;

    private String ROOT;

    private HttpBrowser httpBrowser = null;

    private final String CHARACTERSET = "gb2312";

    public Core(String username, String password, String ROOT) {

        if(httpBrowser==null)
            httpBrowser = new HttpBrowser();

        this.username = username;
        this.password = password;
        this.ROOT = ROOT;
    }

    public boolean isOpen(){

        String path = ROOT+"/default_vsso.aspx";

        try{
            HttpRequest httpRequest = HttpRequest.post(path)
                    .form("TextBox1",username)
                    .form("TextBox2", password)
                    .charset(CHARACTERSET);

           httpBrowser.sendRequest(httpRequest);

            Jerry doc = Jerry.jerry(httpBrowser.getPage());

            String name = doc.$("#xhxm").html().substring(0, doc.$("#xhxm").html().length() - 2).trim();

            String keyWord = "您现在不能选该类课程！";

            StringBuilder selectPath = new StringBuilder(ROOT);
            selectPath.append("/xf_xsqxxxk.aspx?xh=" + username);
            selectPath.append("&xm="+name);
            selectPath.append("&gnmkdm=N121203");

            httpBrowser.sendRequest(HttpRequest.get(selectPath.toString()));

            return !httpBrowser.getPage().contains(keyWord);

        } catch (Exception e){
            System.out.println("Please check your network is available.Make sure your network is available,and check your username and password");
            return false;
        }


    }


}
