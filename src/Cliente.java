import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        boolean recibido = false;
        Scanner sc = new Scanner(System.in);
        System.out.print("> ");
        String palabra1 = sc.nextLine();

        System.out.print("> ");
        String palabra2 = sc.nextLine();


        System.out.print("> ");
        String palabra3 = sc.nextLine();

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(2000);

            for(int intentos= 0;intentos<3;intentos++){
                InetAddress servidor = InetAddress.getByName("localhost");

                String texto = palabra1+","+palabra2+","+palabra3;

                byte[] datos = texto.getBytes("UTF-8");

                socket.send(new DatagramPacket(datos, datos.length, servidor, 5555));

                byte[] buffer = new byte[1024];
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);

                try{
                    socket.receive(respuesta);
                    System.out.println(new String(buffer, 0, respuesta.getLength(), StandardCharsets.UTF_8));
                    recibido = true;
                } catch(SocketTimeoutException e){
                    System.out.println("Sin respuesta: se perdió el paquete o el servidor no está");
                }
            }
            if(recibido == false ){
                System.out.println("Servidor desconectado");
            }
        }
    }
}
