
import java.util.List;
import java.util.Random;

public class Sorteio {
    private Random random;

    public Sorteio() {
        this.random = new Random();
    }

    public Projeto sortear(List<Projeto> projetos) {
        if (projetos.isEmpty()) {
            return null;
        }
        int index = random.nextInt(projetos.size());
        Projeto sorteado = projetos.get(index);
        sorteado.setSorteado(true);
        return sorteado;
    }
}
