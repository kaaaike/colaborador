package colaborador;

public class TesteRemocao {

    public static void main(String[] args) {
        ColaboradorDAO dao = new ColaboradorDAO();

        dao.remover(3);
        System.out.println("Removido!");
    }
}
