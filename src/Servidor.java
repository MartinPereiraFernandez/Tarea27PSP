import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static void main(String args[]) throws Exception{
        DatagramSocket socket = new DatagramSocket(5555);
        byte[] buffer = new byte[1024];

        while (true){
            DatagramPacket recibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(recibido);

            String mensaje = new String(recibido.getData(),0,recibido.getLength(),StandardCharsets.UTF_8);
            String palabras[] = mensaje.split(",");

            String palabra1 = palabras[0];
            String palabra2 = palabras[1];
            String palabra3 = palabras[2];

            if(palabra1.length()>palabra2.length()&&palabra1.length()>palabra3.length()){
                byte[] resp = palabra1.getBytes(StandardCharsets.UTF_8);
                socket.send(new DatagramPacket(resp,resp.length,recibido.getAddress(), recibido.getPort()));
            } else if (palabra2.length()>palabra1.length()&&palabra2.length()>palabra3.length()) {
                byte[] resp = palabra2.getBytes(StandardCharsets.UTF_8);
                socket.send(new DatagramPacket(resp,resp.length,recibido.getAddress(), recibido.getPort()));
            } else if (palabra3.length()>palabra1.length()&&palabra3.length()>palabra2.length()){
                byte[] resp = palabra3.getBytes(StandardCharsets.UTF_8);
                socket.send(new DatagramPacket(resp,resp.length,recibido.getAddress(), recibido.getPort()));
            } else {
                byte[] resp = "Empate".getBytes(StandardCharsets.UTF_8);
                socket.send(new DatagramPacket(resp, resp.length, recibido.getAddress(), recibido.getPort()));
            }
        }
    }
}
