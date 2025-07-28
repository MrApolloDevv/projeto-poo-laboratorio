public class Base extends SubstanciaQuimica implements Soluvel {

    private boolean ehForte;
    private static final double LIMITE_SOLUBILIDADE_G_ML = 0.30; // Ex: 30g por 100ml

    public Base(String nomeComum, String formulaQuimica, double massaMolar, boolean ehForte) {
        super(nomeComum, formulaQuimica, massaMolar);
        this.ehForte = ehForte;
    }

    @Override
    public String descreverRisco() {
        return "Risco Moderado a Elevado: Substância cáustica. Pode causar irritação e danos à pele.";
    }

    @Override
    public double dissolverEmAgua(double gramasSubstancia, double mlAgua) throws LimiteDeSolubilidadeException {
        if ((gramasSubstancia / mlAgua) > LIMITE_SOLUBILIDADE_G_ML) {
            throw new LimiteDeSolubilidadeException("Limite de solubilidade da base excedido. Máximo de " + (LIMITE_SOLUBILIDADE_G_ML * 100) + "g por 100ml.");
        }

        System.out.println("Dissolvendo " + gramasSubstancia + "g de " + this.nomeComum + " em " + mlAgua + "ml de água.");
        double volumeEmLitros = mlAgua / 1000.0;
        return gramasSubstancia / volumeEmLitros;
    }

    public boolean isEhForte() {
        return ehForte;
    }
}