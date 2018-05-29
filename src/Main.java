import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    //Main.class.getResourceAsStream("/resources/strings.txt"))
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("/resources/strings.txt")));
        System.out.println(br.readLine());
    }
    
}
