package colaborador;

import java.util.Calendar;

public class TesteCadastro {

    public static void main(String[] args) {

        ColaboradorDAO dao= new ColaboradorDAO();
        Colaborador colaborador= new Colaborador();

        colaborador.setNome("Teste Cadastro");
        colaborador.setEmail("teste@gmail.com");
        colaborador.setSalario(3000);

        colaborador.setDataContratacao(Calendar.getInstance());

        dao.cadastrar(colaborador);
        System.out.println("Cadastrado!");
    }
}
