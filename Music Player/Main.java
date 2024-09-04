import java.util.Iterator;

public final class Main {
    
    public static void main(String[] args) {
        
        ILinkedList<String> musicPlayer = ILinkedList.create();

        musicPlayer.add("blue", "D:\\file.txt");
        musicPlayer.add("river", "C:\\Users\\Aida\\Desktop\\file2.txt");
        musicPlayer.add("jazz", "E:\\file6.txt");
        musicPlayer.add("morning", "C:\\Users\\Aida\\Desktop\\file3.txt");
        musicPlayer.add("storm", "D:\\Bahareh\\file4.txt");
        musicPlayer.add("love", "D:\\file5.txt");

        musicPlayer.play("storm");
        musicPlayer.play("storm");
        musicPlayer.play("storm");
        musicPlayer.play("love");
        musicPlayer.play("love");
        musicPlayer.play("blue");
        musicPlayer.play("blue");
        musicPlayer.play("river");
        musicPlayer.play("jazz");
        musicPlayer.play("jazz");
        musicPlayer.play("jazz");
        musicPlayer.play("jazz");
        musicPlayer.play("morning");
        musicPlayer.play("morning");
        musicPlayer.play("morning");
        musicPlayer.play("morning");
        musicPlayer.play("morning");


        Iterator<String> freq_it = musicPlayer.iterateByFrequency();
        while (freq_it.hasNext()) {
            System.out.println(freq_it.next());
        }
        System.out.println("-------------------");


        Iterator<String> shuf_it = musicPlayer.iterateByShuffle();
        while (shuf_it.hasNext()) {
            System.out.println(shuf_it.next());
        }
        System.out.println("-------------------");


        Iterator<String> name_it = musicPlayer.iterateByName();
        while (name_it.hasNext()) {
            System.out.println(name_it.next());
        }
    }
}