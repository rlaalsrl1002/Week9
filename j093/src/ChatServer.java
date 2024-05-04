import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {

    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(10001);
            System.out.println("Waiting Connections...");
            HashMap<String, PrintWriter> hm = new HashMap<>(); // 제네릭을 사용하여 타입 안정성 확보
            while(true){
                Socket sock = server.accept();
                ChatThread chatthread = new ChatThread(sock, hm);
                chatthread.start();
            } // while
        }catch(Exception e){
            System.out.println(e);
        }
    } // main
}

class ChatThread extends Thread{
    private Socket sock;
    private String id;
    private BufferedReader br;
    private HashMap<String, PrintWriter> hm;
    private boolean initFlag = false;

    public ChatThread(Socket sock, HashMap<String, PrintWriter> hm){
        this.sock = sock;
        this.hm = hm;
        try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            id = br.readLine();
            broadcast(id + " entered.");
            System.out.println("[Server log] " + id + " entered.");
            synchronized(hm){
                hm.put(this.id, pw);
            }
            initFlag = true;
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void run(){
        try{
            String line = null;
            while((line = br.readLine()) != null){
                if(line.equals("/quit"))
                    break;
                if(line.equals("/userlist")) {
                    sendUserList();
                } else if(line.indexOf("/to ") == 0){
                    sendmsg(line);
                }else {
                    broadcast(id + " : " + line);
                }
            }
        }catch(Exception ex){
            System.out.println(ex);
        }finally{
            synchronized(hm){
                hm.remove(id);
            }
            broadcast(id + " exited.");
            System.out.println("[Server log] " + id + " exited.");
            try{
                if(sock != null)
                    sock.close();
            }catch(Exception ex){}
        }
    } // run

    public void sendmsg(String msg){
        int start = msg.indexOf(" ") + 1;
        int end = msg.indexOf(" ", start);
        if(end != -1){
            String to = msg.substring(start, end);
            String msg2 = msg.substring(end + 1);
            Object obj = hm.get(to);
            if(obj != null){
                PrintWriter pw = (PrintWriter)obj;
                pw.println(id + "'s secret message: " + msg2);
                pw.flush();
            } // if
        }
    } // sendmsg

    public void broadcast(String msg){
        synchronized(hm){
            Collection<PrintWriter> collection = hm.values(); // 제네릭을 사용하여 타입 안정성 확보
            Iterator<PrintWriter> iter = collection.iterator(); // 제네릭을 사용하여 타입 안정성 확보
            while(iter.hasNext()){
                PrintWriter pw = iter.next();
                pw.println(msg);
                pw.flush();
            }
        }
    } // broadcast

    public void sendUserList() {
        synchronized(hm) {
            Collection<String> users = hm.keySet(); // 현재 채팅 중인 모든 사용자 이름 가져오기
            StringBuilder userList = new StringBuilder("There are ");
            userList.append(users.size()).append(" users.\n");
            for(String user : users) {
                userList.append(user).append(", ");
            }
            PrintWriter pw = hm.get(id);
            pw.println(userList.toString());
            pw.flush();
        }
    }
}
