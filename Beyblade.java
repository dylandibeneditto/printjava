import java.io.IOExcpetion;
import printjava.*;
import printjava.Meshes.*;

public class Main {
    public static void main(String[] args) throws IOException {
        STL file = new STL("Beyblade");

        BeybladeBase base = new BeybladeBase();
        file.add(base);

        Cone cone = new Cone();

        file.write();
    }
    
}
