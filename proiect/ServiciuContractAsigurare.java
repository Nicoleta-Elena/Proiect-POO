
import java.util.ArrayList;
import java.util.List;

public class ServiciuContractAsigurare {

    private List<ContractAsigurare> contracteAsigurare;

    public ServiciuContractAsigurare() {
        this.contracteAsigurare = new ArrayList<ContractAsigurare>();
    }

    public ContractAsigurare cautaContract(int numarUnic) {
        for (int i = 0; i < contracteAsigurare.size(); i++) {
            ContractAsigurare contractAsigurare = contracteAsigurare.get(i);
            if (contractAsigurare.getNumarUnic() == numarUnic) {
                return contractAsigurare;
            }
        }
        return null;
    }
}
