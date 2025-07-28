import java.util.ArrayList;
import java.util.List;

public class Laboratorio {

    private List<SubstanciaQuimica> estoque;

    public Laboratorio() {
        this.estoque = new ArrayList<>();
    }

    public void adicionarSubstancia(SubstanciaQuimica substancia) {
        this.estoque.add(substancia);
        System.out.println("Substância " + substancia.nomeComum + " adicionada ao estoque.");
    }

    public SubstanciaQuimica buscarSubstancia(String formula) {
        for (SubstanciaQuimica s : this.estoque) {
            if (s.formulaQuimica.equalsIgnoreCase(formula)) {
                return s;
            }
        }
        return null;
    }

    public void realizarMistura(String formula1, String formula2) throws ReacaoIncompativelException {
        SubstanciaQuimica s1 = buscarSubstancia(formula1);
        SubstanciaQuimica s2 = buscarSubstancia(formula2);

        if (s1 == null || s2 == null) {
            throw new ReacaoIncompativelException("Uma ou ambas as substâncias não foram encontradas no estoque.");
        }

        if (s1.getClass() == s2.getClass()) {
            throw new ReacaoIncompativelException("Reação incompatível: não é possível misturar duas substâncias do tipo " + s1.getClass().getSimpleName());
        }

        System.out.println("Mistura de " + s1.nomeComum + " e " + s2.nomeComum + " realizada com sucesso (simulação).");
    }

    public static void main(String[] args) {
        System.out.println("--- Iniciando Simulação no Laboratório ---");
        Laboratorio lab = new Laboratorio();

        lab.adicionarSubstancia(new Acido("Ácido Clorídrico", "HCl", 36.46, false));
        lab.adicionarSubstancia(new Base("Soda Cáustica", "NaOH", 40.00, true));
        lab.adicionarSubstancia(new Acido("Ácido Sulfúrico", "H2SO4", 98.08, true));
        System.out.println("----------------------------------------");

        System.out.println("\n>>> Teste 1: Tentando dissolver uma quantidade válida de Ácido Clorídrico...");
        try {
            SubstanciaQuimica acido = lab.buscarSubstancia("HCl");
            if (acido instanceof Soluvel) {
                Soluvel substanciaSoluvel = (Soluvel) acido;
                double concentracao = substanciaSoluvel.dissolverEmAgua(20, 100);
                System.out.println("SUCESSO! Concentração final: " + String.format("%.2f", concentracao) + " g/L.");
            }
        } catch (LimiteDeSolubilidadeException e) {
            System.err.println("ERRO INESPERADO: " + e.getMessage());
        }

        System.out.println("\n>>> Teste 2: Tentando dissolver uma quantidade excessiva de Soda Cáustica...");
        try {
            SubstanciaQuimica base = lab.buscarSubstancia("NaOH");
            if (base instanceof Soluvel) {
                Soluvel substanciaSoluvel = (Soluvel) base;
                substanciaSoluvel.dissolverEmAgua(50, 100);
            }
        } catch (LimiteDeSolubilidadeException e) {
            System.err.println("ERRO CAPTURADO: " + e.getMessage());
        }

        System.out.println("\n>>> Teste 3: Tentando misturar dois ácidos...");
        try {
            lab.realizarMistura("HCl", "H2SO4");
        } catch (ReacaoIncompativelException e) {
            System.err.println("ERRO CAPTURADO: " + e.getMessage());
        }

        System.out.println("\n--- Simulação Finalizada ---");
    }
}