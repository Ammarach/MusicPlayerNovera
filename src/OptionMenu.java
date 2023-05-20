import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OptionMenu{
    Scanner input = new Scanner(System.in);
    int menuOption;
    int songOption;
    public void MainMenu()  {
        System.out.println("\n______________________________________________");
        System.out.println("*** Welcome to the Spotifoo Music Player. ***");
        System.out.println("______________________________________________\n");
        System.out.println("Main Menu Options: ");
        System.out.println("1. Songs");
        System.out.println("2. Artists");
        System.out.println("3. Albums");
        System.out.println("4. Genres");
        System.out.println("5. Search");
        System.out.println("100. Exit");
        System.out.print("Choose an option and Press Enter: ");
        menuOption = input.nextInt();

        if (menuOption == 1) {
            SongsMenu();
        }
        else if (menuOption == 2) {
            ArtistMenu();
        } else if (menuOption == 3) {
            AlbumMenu();
        }
        else if (menuOption == 4) {
            GenreMenu();
        } else if (menuOption == 5) {
            SearchSong();
        } else if (menuOption == 100) {
            System.exit(0);
        }
    }

    public void SongsMenu() {
        System.out.println("\n______________________________________________");
        System.out.println("Songs Menu: ");
        System.out.println("______________________________________________\n");
        ArrayList<String> songsName = new ArrayList<>();
        ArrayList<String> songsFile = new ArrayList<>();
        try {
            BufferedReader dataFileReader = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\data.txt"));
            String line;
            while ((line = dataFileReader.readLine()) != null) {
                String[] data = line.split(",");
                songsName.add(data[0]);
                songsFile.add(data[4]);
            }
            dataFileReader.close();
            for (int i = 0; i < songsName.size();i++)
            {
                System.out.println(" ["+(i+1)+"] " +songsName.get(i));
            }

        } catch (IOException ex) {
            System.out.println("File not Found");
            ex.printStackTrace();
        }
        System.out.println(" [0] For Back to Main Menu");
        System.out.print("Choose an option and Press Enter: ");
        menuOption = input.nextInt();

        if (menuOption == 0) {
            MainMenu();
        }
        else if (menuOption > 0 && menuOption < songsName.size()+1){
            try {
                Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Windows Media Player\\wmplayer" +
                        ".exe\"\"C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\songs\\"+songsFile.get(menuOption-1));

            } catch (IOException e) {

                System.out.println(e);

            }
        }
        else if (menuOption > 20 || menuOption < 0) {
            System.out.println("Invalid input please enter Valid input");
            SongsMenu();
        }
    }

    public void ArtistMenu() {
        String path = null;
        System.out.println("\n______________________________________________");
        System.out.println("Artist Menu: ");
        System.out.println("______________________________________________\n");
        ArrayList<String> artistName = new ArrayList<>();
        ArrayList<String> songsName = new ArrayList<>();
        ArrayList<String> songsFile = new ArrayList<>();
        ArrayList<String> userSelectedSong = new ArrayList<>();
        ArrayList<Object> temp = new ArrayList<>();
        HashMap<String, String> SongArtistHashMap = new HashMap<>();
        HashMap<String, String> SongFileHashMap = new HashMap<>();

        try {
            BufferedReader dataFileReader = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\data.txt"));
            String line;
            while ((line = dataFileReader.readLine()) != null) {
                String[] data = line.split(",");
                songsName.add(data[0]);
                artistName.add(data[1]);
                songsFile.add(data[4]);
                SongArtistHashMap.put(data[0], data[1]);
                SongFileHashMap.put(data[4], data[0]);
            }
            dataFileReader.close();
            artistName = (ArrayList<String>) artistName.stream().distinct().collect(Collectors.toList());
            for (int i = 0; i < artistName.size();i++)
            {
                System.out.println(" ["+(i+1)+"] " +artistName.get(i));
            }
        } catch (IOException ex) {
            System.out.println("File not Found");
            ex.printStackTrace();
        }
        System.out.println(" [0] For Back to Main Menu");
        System.out.print("Choose an option and Press Enter: ");
        menuOption = input.nextInt();
        if (menuOption == 0) {
            MainMenu();
        }
        else {
            int counter=0;
            for (Map.Entry entry : SongArtistHashMap.entrySet()) {
                if (entry.getValue().equals(artistName.get(menuOption-1))) {
                    counter++;
                    System.out.println(" [" + counter + "] " + entry.getKey());
                    temp.add(entry.getKey());
                    userSelectedSong.add((String) entry.getKey());
                }
            }
        }


        System.out.println(" [0] For Back to Main Menu");
        System.out.print("Choose an option and Press Enter: ");
        songOption = input.nextInt();
        if (songOption == 0) {
            MainMenu();
        }
        else {
            try {
                for (Map.Entry entry : SongFileHashMap.entrySet()) {
                    if (entry.getValue().equals(userSelectedSong.get(songOption-1))) {
                        path = (String) entry.getKey();
                    }
                }

                Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Windows Media Player\\wmplayer" +
                        ".exe\"\"C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\songs\\"+path );

            } catch (IOException e) {

                System.out.println(e);

            }
        }
    }

    public void AlbumMenu() {
        System.out.println("\n______________________________________________");
        System.out.println("Album Menu: ");
        System.out.println("______________________________________________\n");
        ArrayList<String> albumName = new ArrayList<>();
        ArrayList<String> songsName = new ArrayList<>();
        ArrayList<String> songsFile = new ArrayList<>();
        ArrayList<String> userSelectedSong = new ArrayList<>();
        HashMap<String, String> SongFileHashMap = new HashMap<>();
        HashMap<String, String> SongAlbumHashMap = new HashMap<>();
        String path= null;

        try {
            BufferedReader dataFileReader = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\data.txt"));
            String line;
            while ((line = dataFileReader.readLine()) != null) {
                String[] data = line.split(",");
                songsName.add(data[0]);
                albumName.add(data[2]);
                songsFile.add(data[4]);
                SongAlbumHashMap.put(data[0], data[2]);
                SongFileHashMap.put(data[4], data[0]);
            }
            dataFileReader.close();
            albumName = (ArrayList<String>) albumName.stream().distinct().collect(Collectors.toList());
            for (int i = 0; i < albumName.size();i++)
            {
                System.out.println(" ["+(i+1)+"] " +albumName.get(i));
            }
        } catch (IOException ex) {
            System.out.println("File not Found");
            ex.printStackTrace();
        }
        System.out.println(" [0] For Back to Main Menu");
        System.out.print("Choose an option and Press Enter: ");
        menuOption = input.nextInt();
        if (menuOption == 0) {
            MainMenu();
        }
        else {
            int counter=0;
            for (Map.Entry entry : SongAlbumHashMap.entrySet()) {
                if (entry.getValue().equals(albumName.get(menuOption-1))) {
                    counter++;
                    System.out.println(" [" + counter + "] " + entry.getKey());
                    userSelectedSong.add((String) entry.getKey());
                }
            }
        }


        System.out.println(" [0] For Back to Main Menu");
        System.out.print("Choose an option and Press Enter: ");
        songOption = input.nextInt();
        if (songOption == 0) {
            MainMenu();
        }
        else {
            try {
                for (Map.Entry entry : SongFileHashMap.entrySet()) {
                    if (entry.getValue().equals(userSelectedSong.get(songOption-1))) {
                        path = (String) entry.getKey();
                    }
                }

                Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Windows Media Player\\wmplayer" +
                        ".exe\"\"C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\songs\\"+path );
            } catch (IOException e) {

                System.out.println(e);

            }
        }
    }

    public void GenreMenu() {
        System.out.println("\n______________________________________________");
        System.out.println("Genre Menu: ");
        System.out.println("______________________________________________\n");
        ArrayList<String> genreName = new ArrayList<>();
        ArrayList<String> songsName = new ArrayList<>();
        ArrayList<String> songsFile = new ArrayList<>();
        ArrayList<String> userSelectedSong = new ArrayList<>();
        HashMap<String, String> SongFileHashMap = new HashMap<>();
        HashMap<String, String> SongGenreHashMap = new HashMap<>();
        String path = null;
        String[] data = null;
        try {
            BufferedReader dataFileReader = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\data.txt"));
            String line;
            while ((line = dataFileReader.readLine()) != null) {
                data = line.split(",");
                songsName.add(data[0]);
                genreName.add(data[3]);
                songsFile.add(data[4]);
                SongGenreHashMap.put(data[0], data[3]);
                SongFileHashMap.put(data[4], data[0]);
            }
            dataFileReader.close();
            genreName = (ArrayList<String>) genreName.stream().distinct().collect(Collectors.toList());
            for (int i = 0; i < genreName.size();i++)
            {
                System.out.println(" ["+(i+1)+"] " +genreName.get(i));
            }
        } catch (IOException ex) {
            System.out.println("File not Found");
            ex.printStackTrace();
        }
        System.out.println(" [0] For Back to Main Menu");
        System.out.print("Choose an option and Press Enter: ");
        menuOption = input.nextInt();
        if (menuOption == 0) {
            MainMenu();
        }
        else {
            int counter=0;
            for (Map.Entry entry : SongGenreHashMap.entrySet()) {
                if (entry.getValue().equals(genreName.get(menuOption - 1))) {
                    counter++;
                    userSelectedSong.add((String) entry.getKey());
                    System.out.println(" [" + counter + "] " + entry.getKey());
                }
            }
        }

        System.out.println(" [0] For Back to Main Menu");
        System.out.print("Choose an option and Press Enter: ");
        songOption = input.nextInt();
        if (songOption == 0) {
            MainMenu();
        }
        else {
            try {
                for (Map.Entry entry : SongFileHashMap.entrySet()) {
                    if (entry.getValue().equals(userSelectedSong.get(songOption-1))) {
                        path = (String) entry.getKey();
                    }
                }

                Runtime.getRuntime().exec("\"C:\\Program Files (x86)\\Windows Media Player\\wmplayer" +
                        ".exe\"\"C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\songs\\"+path );
            } catch (IOException e) {

                System.out.println(e);

            }
        }
    }

    public void SearchSong() {
        System.out.println("\n______________________________________________");
        System.out.println("Search Song: ");
        System.out.println("______________________________________________\n");
        ArrayList<String> songsName = new ArrayList<>();
        ArrayList<String> songsFile = new ArrayList<>();
        HashMap <String, String> search = new HashMap<>();
        ArrayList<String> userSelectedSong = new ArrayList<>();
        String keyWords = null;
        int counter = 0;
        System.out.println("Enter words for Search the Song: ");
        keyWords = input.next();
        try {
            BufferedReader dataFileReader = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\MusicPlayerNovera\\assets\\data.txt"));
            String line;
            while ((line = dataFileReader.readLine()) != null) {
                String[] data = line.split(",");
                songsName.add(data[0]);
                songsFile.add(data[4]);
                search.put(data[0], keyWords);

                for (Map.Entry entry : search.entrySet()) {
                    if (entry.getValue().equals(search.get(keyWords.toLowerCase()))) {
                        counter++;
                        System.out.println(" [" + counter + "] " + entry.getKey());
                        userSelectedSong.add((String) entry.getKey());
                    }
                }
            }
            dataFileReader.close();

        } catch (IOException ex) {
            System.out.println("File not Found");
            ex.printStackTrace();
        }
    }

}
