package com.example.socket;


import javax.imageio.IIOException;
import javax.websocket.*;

import com.example.controller.SolveController;
import com.example.domain.Car;

import java.io.File;

import com.example.utils.DeleteImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import java.util.Map;

@Component
@ServerEndpoint("/ws/{sendId}/{receviceId}")
public class WebSocket {
    private static Map<String, Session> sessionMap = new HashMap<>();



    @OnOpen
    public void onOpen(Session session, @PathParam("sendId") String id){


        DeleteImages.delete(id);
        sessionMap.put(id,session);

    }

    @OnMessage
    public void onMessage(String message,@PathParam("sendId") String sendId, @PathParam("receviceId") String receviceId, Session session){




        sendToOneClient(sendId,receviceId,message);
    }

    @OnClose
    public void onClose(Session session, @PathParam("sendId") String id){


        DeleteImages.delete(id);
        sessionMap.remove(id);
    }


    public static void sendToOneClient(String sendId,String receviceId, String message) {

       try {


           Session session1 = sessionMap.get(receviceId);

           session1.getBasicRemote().sendText(message);
       }catch (NullPointerException n){

           if(sendId.equals("1")){
               Session session1 = sessionMap.get(sendId);
               try {
                   session1.getBasicRemote().sendText("400");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }

       }
       catch (IOException e){
         e.printStackTrace();
       }

    }

    public static void sendToAllClient(String message) {

        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}



