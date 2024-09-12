import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    private static final String FILE_NAME = "projetos.dat";

    public static void salvarProjetos(List<Projeto> projetos) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(projetos);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Projeto> carregarProjetos() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Projeto>) ois.readObject();
        }
    }
}
