    public abstract class SubstanciaQuimica {

        protected String nomeComum;
        protected String formulaQuimica;
        protected double massaMolar;

        public SubstanciaQuimica(String nomeComum, String formulaQuimica, double massaMolar) {
            this.nomeComum = nomeComum;
            this.formulaQuimica = formulaQuimica;
            this.massaMolar = massaMolar;
        }

        public String getDetalhes() {
            return "Substância: " + this.nomeComum + " (" + this.formulaQuimica + ") | Massa Molar: " + this.massaMolar + " g/mol";
        }

        public abstract String descreverRisco();
    }
