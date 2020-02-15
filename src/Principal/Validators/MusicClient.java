/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Validators.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import musicstreamer.Playlist;
import musicstreamer.Song;

/**
 *
 * @author Lighthouse
 */
public class MusicClient extends Observable {

   public Socket cliente;
   public DataOutputStream buffSalida;
   public ObjectInputStream buffEntrada;
   //public DataInputStream teclado;
   public static String nombre;
   public String ip;
   public int puerto;
   public String received = "";
   public Playlist myList = new Playlist();
   public Playlist otherList = new Playlist();
   public List<String> userList = new ArrayList();
   public int counter = 0;
   public PausablePlayer player = null;
   public BufferedInputStream in = null;
   String url = "ftp://" + "10.10.145.130" + ":3721/";
   

    public void RecibirDatos() {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {

                        
                        Object objIn = buffEntrada.readObject();
                        System.out.println(objIn.toString());
                        System.out.println("Receive");

                        if (objIn instanceof Playlist) {

                            Playlist tempList = (Playlist) objIn;
                            if (tempList.getId() == 'M') {
                                System.out.println("Music");
                                myList = tempList;

                                if (!getMySongs().get(counter).getNombre().equals("-") && player == null) {

                                    in = new BufferedInputStream(new URL(url + getMySongs().get(counter).getUrlname()).openStream());

                                    player = new PausablePlayer(in);
                                }
                                
                            } else if (tempList.getId() == 'O') {
                                otherList = tempList;

                            }

                            
                            tempList = null;
                            setChanged();
                            //System.out.println("For try");
                            notifyObservers(new String(""));

                        } else {
                            String tempString = (String) objIn;
                            if (tempString.contains(",")) {
                                userList.clear();
                                String[] usersDiv = tempString.split(",");
                                for (int i = 0; i < usersDiv.length; i++) {
                                    userList.add(usersDiv[i]);
                                }
                                for (String user : userList) {
                                    System.out.println(user);
                                }
                                setChanged();
                                
                                notifyObservers(new String(""));
                            } else {
                                setChanged();
                                notifyObservers(tempString);
                            }
                            

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        hilo.start();
    }

    public void EscribirDatos(String mensaje) throws IOException {
        buffSalida.writeUTF(mensaje);
        buffSalida.flush();
    }

    public MusicClient(String ip, int puerto) {
        this.puerto = puerto;
        this.ip = ip;
    }

    public void init() {
        try {
            cliente = new Socket(ip, puerto);
            buffSalida = new DataOutputStream(cliente.getOutputStream());
            buffEntrada = new ObjectInputStream(cliente.getInputStream());
            RecibirDatos();
        } catch (Exception e) {

        }
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received;
    }

    public Song getMySong(int i) {
        return myList.getSongs().get(i);

    }

    public List<Song> getMySongs() {
        return myList.getSongs();
    }

    public void setMyList(Playlist myList) {
        this.myList = myList;
    }

    public Song getOtherSong(int i) {
        return otherList.getSongs().get(i);
    }

    public List<Song> getOtherSongs() {
        return otherList.getSongs();
    }

    public void setOtherList(Playlist otherList) {
        this.otherList = otherList;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public List<String> validate(Usuario info) {
        List<Validator> validators = new ArrayList();
        validators.add(new NameValidator());
        validators.add(new EmailRecordValidator());
        validators.add(new PasswordCareerValidator());
        Validator comp = new CompositeValidator(validators);
        List<String> errors = comp.validate(info);

        return errors;
    }

    public void addToList(String name) {
        try {
            if (getMySongs().size() == 1 && getMySong(0).getNombre().equals("-")) {
                getMySongs().remove(0);
            }
            for (Song song : getOtherSongs()) {
                if (song.getNombre().equals(name)) {
                    getMySongs().add(song);
                }
            }
            // getMySongs().add(getOtherSongs().get(selected));
            EscribirDatos("Add:" + name);
            setChanged();
            notifyObservers(new String(""));
        } catch (IOException ex) {
            Logger.getLogger(MusicClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removeFromList(String name) {

        try {
            
            for (int i = 0; i < getMySongs().size(); i++) {
                if (getMySongs().get(i).getNombre().equals(name)) {

                    getMySongs().remove(i);

                }
            }
            
            if (player != null) {
                player.stop();
            }
            
            if (counter == getMySongs().size() && counter != 0) {
                counter--;
            }
            EscribirDatos("Remove:" + name);
            if (getMySongs().isEmpty()) {
                getMySongs().add(new Song("-", "-", "-", 0, "-", "-"));
            }
            setChanged();
            notifyObservers(new String(""));
        } catch (IOException ex) {
            Logger.getLogger(MusicClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void play(String name) {
        try {
            System.out.println("COUNTER: " + counter);
            if (player == null) {

                in = new BufferedInputStream(new URL(url + getMySong(counter).getUrlname()).openStream());

                player = new PausablePlayer(in);
            }
            if (player.isPlaying()) {
                player.pause();
            } else if (player.isStopped()) {
                in = new BufferedInputStream(new URL(url + getMySong(counter).getUrlname()).openStream());
                player = new PausablePlayer(in);
                player.play();
            } else {
                player.play();
            }
        } catch (JavaLayerException e) {
            System.out.println(e);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MusicClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MusicClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void next() {
        try {

            player.stop();
            counter++;

            in = new BufferedInputStream(new URL(url + getMySongs().get(counter)).openStream());
            player = new PausablePlayer(in);
            player.play();
            setChanged();
            notifyObservers(new String(""));

        } catch (Exception e) {

        }
    }

    public void prev() {
        try {

            player.stop();
            counter--;
            in = new BufferedInputStream(new URL(url + getMySongs().get(counter)).openStream());
            player = new PausablePlayer(in);
            player.play();
            setChanged();
            notifyObservers(new String(""));
        } catch (Exception e) {

        }
    }

    public String getSongData() {
        Song current = getMySong(counter);
        String data = "Nombre: " + current.getNombre()
                + "\nArtista: " + current.getArtista()
                + "\nAlbum: " + current.getAlbum()
                + "\nAño: " + current.getYear()
                + "\nGénero: " + current.getGenero();
        return data;
    }

}
