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
import java.io.FileReader;
 // 1st Reciever
public class filereciever
{
   public SocketChannel createServerSocketChannel() throws IOException
{
    ServerSocketChannel serverSocket = null;
    SocketChannel client = null;
    serverSocket = ServerSocketChannel.open();
    serverSocket.socket().bind(new InetSocketAddress(9000));
    //ready to accept connection
    client = serverSocket.accept();
    System.out.println("Connection Established" + client.getRemoteAddress());
    return client;
    // connection accepted and returns the client
}
    public static void main(String args[]) throws Exception
   {
     filereciever server = new filereciever();
     SocketChannel socketchannel = server.createServerSocketChannel();
     //serversocket channel is created
     server.readFileFromSocketChannel(socketchannel);
  }

  public void readFileFromSocketChannel(SocketChannel socketchannel) throws IOException
  {
      Path npath = Paths.get("D:\\New folder1");
      if (!Files.exists(npath))
      {
        try
        {
        Files.createDirectories(npath);
        }
        catch(IOException e)
        {
        e.printStackTrace();
        }
        }
      Path path = Paths.get("D:\\New folder1\\1.csv");
      FileChannel filechannel = FileChannel.open(path,
      EnumSet.of(StandardOpenOption.CREATE,
      StandardOpenOption.TRUNCATE_EXISTING,
      StandardOpenOption.WRITE)
      );
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      while (socketchannel.read(buffer) > 0)
      {
          buffer.flip();
          filechannel.write(buffer);
          buffer.clear();
     }
filechannel.close();
socketchannel.close();
}
}
    