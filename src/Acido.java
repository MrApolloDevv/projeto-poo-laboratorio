public class Acido extends SubstanciaQuimica implements Soluvel {

    private boolean ehOxigenado;
    private static final double LIMITE_SOLUBILIDADE_G_ML = 0.36; // Ex: 36g por 100ml

    public Acido(String nomeComum, String formulaQuimica, double massaMolar, boolean ehOxigenado) {
        super(nomeComum, formulaQuimica, massaMolar);
        this.ehOxigenado = ehOxigenado;
    }

    @Override
    public String descreverRisco() {
        return "Risco Elevado: Substância corrosiva. Causa queimaduras severas em contato com a pele e tecidos.";
    }

    @Override
    public double dissolverEmAgua(double gramasSubstancia, double mlAgua) throws LimiteDeSolubilidadeException {
        if ((gramasSubstancia / mlAgua) > LIMITE_SOLUBILIDADE_G_ML) {
            throw new LimiteDeSolubilidadeException("Limite de solubilidade do ácido excedido. Máximo de " + (LIMITE_SOLUBILIDADE_G_ML * 100) + "g por 100ml.");
        }

        System.out.println("Dissolvendo " + gramasSubstancia + "g de " + this.nomeComum + " em " + mlAgua + "ml de água.");
        double volumeEmLitros = mlAgua / 1000.0;
        return gramasSubstancia / volumeEmLitros;
    }

    public boolean isEhOxigenado() {
        return ehOxigenado;
    }
}