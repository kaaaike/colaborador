package colaborador;

public class TesteAlteracao {

    public static void main(String[] args) {

        ColaboradorDAO dao = new ColaboradorDAO();

        Colaborador colaborador = dao.buscarPorId(1);
        System.out.println(colaborador.getCodigo() + " " + colaborador.getNome() + " " + colaborador.getEmail() + " " + colaborador.getSalario() + " " + colaborador.getDataContratacao().getTime());

        colaborador.setSalario(3500);
        colaborador.setEmail("kaike_soares123@hotmail.com");

        dao.atualizar(colaborador);
    }
}
