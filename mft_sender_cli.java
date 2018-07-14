import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import java.net.Socket; 
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
//2nd cli/sender
public class mft_sender_cli
{
      public SocketChannel CreateChannel() throws IOException 
    {
        SocketChannel socketchannel = SocketChannel.open();
        SocketAddress sockaddr = new InetSocketAddress("localhost",9000);
        //connects to the server
        socketchannel.connect(sockaddr);
        return socketchannel;
    }
    public static void main(String[] args) throws IOException
    {
        mft_sender_cli client = new mft_sender_cli();
        SocketChannel socketchannel = client.CreateChannel();
        //cli socket channel is created
        client.sendFile(socketchannel);
    }
        public void sendFile(SocketChannel socketchannel) throws IOException
    {
       System.out.println("Enter the path");
       Scanner sc =new Scanner(System.in);
       String pathh = sc.nextLine();
       Path path = Paths.get(pathh);
       FileChannel inchannel = FileChannel.open(path);
       ByteBuffer buffer = ByteBuffer.allocate(1024);
       while (inchannel.read(buffer) > 0)
       {
        buffer.flip();
        //sending starts
        socketchannel.write(buffer);
        buffer.clear();
       }
        socketchannel.close();
    }
  
}