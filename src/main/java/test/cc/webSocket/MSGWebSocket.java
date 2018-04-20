package test.cc.webSocket;

import org.springframework.stereotype.Component;
import test.cc.model.Message;
import test.cc.util.BaseBeanUtil;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 陈诚[os1040]
 * company qianmi.com
 * Date    2018/04/09.
 */
@Component
@ServerEndpoint("/MSGWebSocket")
public class MSGWebSocket {

    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<MSGWebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        String[] msgArr = message.split("::");
        Message msg = Message.builder()
                .cellphone(msgArr[0])
                .userName(msgArr[1])
                .content(msgArr[2])
                .build();
        for (MSGWebSocket item : webSocketSet){
            try{
                item.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable e){
        System.out.println("发生错误");
        e.printStackTrace();
    }


    public void sendMessage(Message message) throws IOException {
        this.session.getBasicRemote().sendText(BaseBeanUtil.setData(message, 1));
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MSGWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MSGWebSocket.onlineCount--;
    }
}
