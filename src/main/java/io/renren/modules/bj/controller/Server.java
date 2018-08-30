package io.renren.modules.bj.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port = 9000;
    private ServerSocket serverSocket;
    private  DataInputStream dataInputStream;
    private static BufferedReader br=null;

    public Server() throws Exception{
        serverSocket = new ServerSocket(port,1);
        System.out.println("服务器启动!");
    }
    public void service(){
        while(true){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("New connection accepted "+
                        socket.getInetAddress()+":"+socket.getPort());
                dataInputStream = new DataInputStream(socket.getInputStream());
                //br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.print(br);
                GetMessageFromClient();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(socket!=null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void GetMessageFromClient(){
        try {
            //获取消息的长度
            int length = dataInputStream.read();

            //System.out.print(dataInputStream.readByte());
            /*System.out.print();
            System.out.print();
            System.out.print();*/
            //获取消息
            byte[] body = new byte[length];
            dataInputStream.read(body);

            System.out.print(body);

            String message = new String(body,"utf-8");
            System.out.println("客户端说：        "+message.substring(message.indexOf("Q")+1,message.indexOf("\r\n")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server();
        Thread.sleep(1000);
        server.service();



        /*byte[] bs1 = {48,56,54};
        String s = new String(bs1);


        String res = new String(bs1,"UTF-8");

        System.out.print(s+"     "+res);*/

    }

}
