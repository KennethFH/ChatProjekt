import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static ServerSocket socket;
    static Socket client;
    public static void main(String[] args) throws IOException
    {
        try {
            socket = new ServerSocket(4711);
            System.out.println("Server: waiting for connection");
            client = socket.accept();
            while (true)
            {
                InputStream eingang;
                try
                {
                    eingang = client.getInputStream();
                }
                catch(Exception e2)
                {
                    e2.printStackTrace();
                    break;
                }
                byte[]      buf     = new byte[100];
                try
                {
                    eingang.read(buf);
                }
                catch(Exception e2)
                {
                    e2.printStackTrace();
                    break;
                }
                String nachricht = new String(buf);
                String Nachricht = "ACK";
                byte[] abuf = Nachricht.getBytes();
                OutputStream ack = client.getOutputStream();
                ack.write(abuf);
                OutputStream loop = client.getOutputStream();
                loop.write(buf);
                System.out.println("Nachricht: " +  nachricht);
                System.out.println("Von: " + client.getInetAddress() + ": " + client.getPort());
                if(buf[0] == 't' && buf[1] == 's' && buf[2] == 'c' && buf[3] == 'h' && buf[4] == 'a' && buf[5] == 'u')
                {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    static void client()
    {
        try
        {
            client = new Socket("localhost", 4711);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static void clientloop()
    {
        try
        {
            OutputStream stream  = client.getOutputStream();
            String       message = "Hallo";
            byte[]         buf    = message.getBytes();
            stream.write(buf);
            byte[] abuf = new byte[100];
            InputStream in = client.getInputStream();
            in.read(abuf);
            byte[] loop = new byte[100];
            InputStream loopback = client.getInputStream();
            loopback.read(loop);
            System.out.println("Nachricht: " + new String(abuf, 0, abuf.length) + "\nHier ist der Loopback " + new String(loop, 0, loop.length));
            //client.close();
        }
        catch(Exception e2)
        {
            e2.printStackTrace();
        }
    }
}