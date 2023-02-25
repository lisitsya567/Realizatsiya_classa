package ru.vshp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Realizatsiya_classa {

    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;


    public Realizatsiya_classa(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.reader = createReader();
            this.writer = createWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Realizatsiya_classa(ServerSocket server) {
        try {
        this.socket = server.accept();
        this.reader = createReader();
        this.writer = createWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void writeLine (String message) {

        try {
                writer.write(message);
                writer.newLine();
                writer.flush();
        } catch (IOException e) {

            throw new RuntimeException(e);
          }

    }


    public String readLine() {

        try {
            return reader.readLine();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }


    private BufferedReader createReader() throws IOException {

        return new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

    }

    private BufferedWriter createWriter() throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override

    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }

}
