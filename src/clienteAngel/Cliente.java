package clienteAngel;


import ServidorEmilio.CifradoAES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            // 1. Conectarse al servidor (CAMBIAR LA IP POR LA DE EMILIO)
            // Ejemplo: Socket socket = new Socket("192.168.1.35", 12345);
            Socket socket = new Socket("10.13.0.170", 12345);
            System.out.println("Conectado al servidor");

            // 2. Crear flujos para enviar y recibir datos
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //Cifrado antes de enviar
            String mensajeOriginal = "Hola, servidor. Soy Ángel y este mensaje es TOP SECRET.";
            System.out.println("Enviando (Texto plano): " + mensajeOriginal);

            // 3. Ciframos el mensaje usando nuestra clase de utilidad
            String mensajeCifrado = CifradoAES.cifrar(mensajeOriginal);
            System.out.println("Enviando (Cifrado): " + mensajeCifrado);

            // 4. Enviamos el mensaje cifrado por el socket
            out.println(mensajeCifrado);

            // 5. Cerrar los recursos
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}